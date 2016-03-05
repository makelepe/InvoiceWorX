package za.co.invoiceworx.ejb;

import java.math.BigDecimal;

import za.co.invoiceworx.dto.InvoiceSearchCriteria;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;

import org.apache.log4j.Logger;

import za.co.invoiceworx.connector.SageConnector;
import za.co.invoiceworx.util.Cache;
import za.co.invoiceworx.util.DateUtil;
import za.co.invoiceworx.util.ReferenceGenerator;
import za.co.invoiceworx.entity.Invoice;
import za.co.invoiceworx.entity.InvoiceStatusType;
import za.co.invoiceworx.entity.TransactionStatusType;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.entity.UserType;
import za.co.invoiceworx.exception.ExCode;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;
import za.co.invoiceworx.repository.InvoiceRepository;
import za.co.invoiceworx.repository.UserRepository;
import za.co.invoiceworx.util.HelperUtil;
import za.co.invoiceworx.util.IXCalculator;

/**
 *
 * @author Samuel Radingwane
 * @since 14 January 2016
 */
@Stateless
public class InvoiceWorkflowEJB {

    private final Logger log = Logger.getLogger(Invoice.class);

    @Inject
    private Cache cache;

    @Inject
    private HelperUtil helper;

    @Inject
    private InvoiceRepository invoiceRepo;

    @Inject
    private UserRepository userRepo;

    @Inject
    private SageConnector sageConnector;

    @Inject
    private IXCalculator calculator;


    /*
     *  1. Supplier create a new invoice
     *   [inv status=NEW]
     */
    public String createInvoice(Invoice inv, Long supplierId, Long adminId) throws InvoiceWorXServiceException {
        log.info("::::: creating invoice");

        User supplier = userRepo.getUser(supplierId);
        if (supplier == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "User, id = " + supplierId + ", does not exist");
        }

        if (inv == null) {
            throw new InvoiceWorXServiceException(ExCode.UNKNOWN, "Invalid Invoice");
        }

        /* User admin = null;
         if (adminId != null) {
         admin = userRepo.getUser(adminId);
         }*/
        // only supplier can create new invoice.
        if (!supplier.isSupplier()) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_SUPPLIER,
                    "User [id = " + supplier.getId() + ", name = " + supplier.getPerson().getFname() + "] is not a supplier user. Only supplier user can create new Invoice");
        }

        inv.setInvRefNumber(ReferenceGenerator.generateInvoiceReference());
        log.info("::::: new invoice ref : " + inv.getInvRefNumber());

        helper.createInvoiceStatus(supplier, inv, InvoiceStatusType.NEW, "Create New Invoice");
        inv.setCreatedBy(supplier);
        inv.setCreatedTs(DateUtil.newDate());

        invoiceRepo.createInvoice(inv);
        log.info("successfully created invoice : " + inv.getInvRefNumber());

        return inv.getInvRefNumber();
    }

    /*
     * 2. Buyer authorise invoice
     * [inv status=APPROVED | REJECTED]
     */
    public String authoriseInvoice(String invRef, Long buyerId, boolean approve) throws InvoiceWorXServiceException {
        log.info("Authorising invoice id=" + invRef + ".");

        User buyer = userRepo.getUser(buyerId);
        if (buyer == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "User, id = " + buyerId + ", does not exist");
        }

        Invoice invoice = invoiceRepo.findInvoice(invRef);
        if (invoice == null) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_FOUND, "Invoice with ref = " + invRef + " does not exist");
        }
        // only new invoices can be authorised
        if (!invoice.isNew()) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_APPROVED, "Invoice with ref = " + invRef + " is not a new invoice. only new invoices can be authorised");
        }
        // only buyer can authorise invoice.
        if (!buyer.isClient()) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_BUYER,
                    "User [id = " + buyer.getId() + ", name = " + buyer.getPerson().getFname() + "] is not a Buyer. Only buyer can authorise invoice");
        }

        helper.createInvoiceStatus(buyer, invoice, approve ? InvoiceStatusType.APPROVED : InvoiceStatusType.REJECT, "Authorise Invoice");
        invoice.setApprovedBy(buyer);
        invoice.setApprovedTs(DateUtil.newDate());

        invoiceRepo.updateInvoice(invoice);

        return invoice.getInvRefNumber();
    }

    /*
     * 3. Supplier sell invoice 
     * [inv status=PENDING VERIFICATION]
     */
    public String sellInvoice(String invRef, Long supplierId) throws InvoiceWorXServiceException {
        log.info("Selling invoice id=" + invRef + ". Status changed to PENDING VERIFICATION");

        User supplier = userRepo.getUser(supplierId);
        if (supplier == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "User, id = " + supplierId + ", does not exist");
        }

        Invoice invoice = invoiceRepo.findInvoice(invRef);
        if (invoice == null) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_FOUND, "Invoice with ref = " + invRef + " does not exist");
        }
        // only approved invoices can be on sale
        if (!invoice.isApproved()) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_APPROVED, "Invoice with ref = " + invRef + " is not approved by the buyer");
        }
        // only supplier who created this invoice can sell it.
        if (!supplier.isSupplier() && invoice.isOwnsBy(supplier)) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_ADMIN,
                    "User [id = " + supplier.getId() + ", name = " + supplier.getPerson().getFname() + "] is not an Original creator of this invoice");
        }

        helper.createInvoiceStatus(supplier, invoice, InvoiceStatusType.PENDING_VERIFICATION, "Sell Invoice");
        invoiceRepo.updateInvoice(invoice);

        return invoice.getInvRefNumber();
    }

    /*
     * 4. Administrator verify invoice
     * [inv status=ON SALE]
     */
    public Boolean verifyInvoice(String invRef, Long adminUserId) throws InvoiceWorXServiceException {
        log.info("Selling invoice id=" + invRef + ". Status changed to PENDING VERIFICATION");

        Invoice invoice = invoiceRepo.findInvoice(invRef);
        if (invoice == null) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_FOUND, "Invoice with ref = " + invRef + " does not exist");
        }

        User adminUser = userRepo.getUser(adminUserId);
        if (adminUser == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "User, id = " + adminUserId + ", does not exist");
        }

        // only admin user can verify invoice
        if (!adminUser.isAdmin()) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_ADMIN, "User [id = " + adminUser.getId() + ", name = " + adminUser.getPerson().getFname() + "] is not an Admin User");
        }

        // only invoices pending verification can be verified
        if (!invoice.isPendingVerification()) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_APPROVED, "Invoice with ref = " + invRef + " is not approved by the buyer");
        }

        helper.createInvoiceStatus(adminUser, invoice, InvoiceStatusType.ON_SALE, "Verify Invoice");
        invoice.setVerifiedBy(adminUser);
        invoice.setVerifiedTs(DateUtil.newDate());

        invoiceRepo.updateInvoice(invoice);

        return true;
    }

    /*
     * 5. Funder buy invoice 
     * [inv status=SOLD]
     * 5.1 Tran Type [FUNDER_IN_PAYMENT] created - Payment via SAGE
     */
    public void buyInvoice(String invRef, Long funderId) throws InvoiceWorXServiceException {
        log.info("buying invoice:::: invoice ref = " + invRef);

        try {
            Invoice invoice = invoiceRepo.findInvoice(invRef);
            if (invoice == null) {
                throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_FOUND, "Invoice with ref = " + invRef + " does not exist");
            }
            // get funder
            User funder = userRepo.getUser(funderId);
            if (funder == null) {
                throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "Funder user " + funderId + " does not exist");
            }
            log.info("bought by : " + funder.getPerson().getFullname());

            // verify the current status of invoice [must be on sale]
            if (!invoice.isOnSale()) {
                throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_FOR_SALE, "Invoice " + invoice.getInvRefNumber() + " is not for sale");
            }

            // debit funder
            BigDecimal fundedAmount = calculator.calculateFundedAmount(invoice);

            sageConnector.debitFunderAndPaySupplier(funder, invoice.getCreatedBy(), fundedAmount);
            helper.createTransaction(invoiceRepo,
                    invoice,
                    funder,
                    invoice.getFundedAmount(),
                    TransactionStatusType.BUY_INVOICE,
                    "Payment Instruction requested to debit Funder's account and Pay Supplier [80% of invoice total] through SAGE PAY");

            log.info("Funder payment successful. Funder Amount = " + invoice.getFundedAmount());

            helper.createInvoiceStatus(funder,
                    invoice,
                    InvoiceStatusType.SOLD,
                    "Invoice is sold. Funder deposited :R" + invoice.getFundedAmount() + " and supplier got paid :R" + invoice.getSupplierInitialAmount());

            invoice.setFundedBy(funder);
            invoice.setFundedTs(DateUtil.newDate());
            invoice.setFundedAmount(fundedAmount);

            invoiceRepo.updateInvoice(invoice);

        } catch (InvoiceWorXServiceException iwxse) {
            throw iwxse;
        }

    }

    /*
     * 5. Admin confirm payments 
     * [inv status=SETTLED]
     * 5.1 Tran Type [FUNDER_IN_PAYMENT] created - Payment via SAGE
     */
    public void confirmPaymentAmount(String invRef, Long adminUserId, UserType userTypeToBeConfirmed, BigDecimal amount) throws InvoiceWorXServiceException {

        if (userTypeToBeConfirmed == null || userTypeToBeConfirmed.getRole() == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_TYPE_IS_REQUIRED);
        }
        User admin = userRepo.getUser(adminUserId);
        if (admin == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "Funder user " + adminUserId + " does not exist");
        }
        if (!admin.isAdmin()) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_ADMIN, "User [id = " + admin.getId() + ", name = " + admin.getPerson().getFname() + "] is not an Admin User");
        }

        Invoice invoice = invoiceRepo.findInvoice(invRef);
        if (invoice == null) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_FOUND, "Invoice with ref = " + invRef + " does not exist");
        }

        if (userTypeToBeConfirmed.getRole().equalsIgnoreCase(UserType.CLIENT)) {
            log.info("Client settlement confirmation");
            if (!invoice.isSold()) {
                throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_SOLD, "Invoice " + invoice.getInvRefNumber() + " is not sold");
            }
            invoice.setBuyerSettlementAmount(amount);
            invoice.setFunderFee(calculator.calculateFunderFee(invoice));
            invoice.setFunderCommission(calculator.calculateFunderCommission(invoice));
            invoice.setPlatformFee(calculator.calculatePlatformFee(invoice));
            invoice.setRemainingAmount(calculator.calculateRemainingAmount(invoice));

            helper.createInvoiceStatus(admin,
                    invoice,
                    InvoiceStatusType.SETTLED,
                    "Client settled invoice");

            helper.createTransaction(invoiceRepo,
                    invoice,
                    admin,
                    amount,
                    TransactionStatusType.CONFIRM_CLIENT_SETTLEMENT,
                    "Confirm Client invoice settlement");

        } else if (userTypeToBeConfirmed.getRole().equalsIgnoreCase(UserType.SUPPLIER)) {
            log.info("supplier payment confirmation");
            helper.createTransaction(invoiceRepo,
                    invoice,
                    admin,
                    amount,
                    TransactionStatusType.CONFIRM_SUPPLIER_PAYMENT_RECEIPT,
                    "Confirms supplier remaining balance payment");

        } else if (userTypeToBeConfirmed.getRole().equalsIgnoreCase(UserType.FUNDER)) {
            log.info("funder payment confirmation");
            helper.createTransaction(invoiceRepo,
                    invoice,
                    admin,
                    amount,
                    TransactionStatusType.CONFIRM_FUNDER_PAYMENT_RECEIPT,
                    "Confirms funder fee payment");
        }
        
        invoiceRepo.updateInvoice(invoice);
    }

    /*
     * search invoices
     */
    public List<Invoice> findInvoices(InvoiceSearchCriteria criteria) {
        // ref number
        return null;
    }

    public Invoice findInvoice(String invRefNumber) {
        Invoice invoice = invoiceRepo.findInvoice(invRefNumber);
        return invoice != null ? invoice : null;
    }

    public List<Invoice> findInvoicesByStatusType(String statusType) {
        InvoiceStatusType invoiceStatusType = cache.getInvoiceStatusType(statusType);

        List<Invoice> invoice = invoiceRepo.findInvoices(invoiceStatusType);

        return invoice;

    }

    /*
     * find invoice
     */
    public Invoice getInvoice(Long invoiceId) {

        return invoiceRepo.getInvoice(invoiceId);
    }

    public List<InvoiceStatusType> getInvoiceStatuses(User user) {

        if (user == null) {
            return null;
        }

        if (user.isAdmin()) {
            return cache.getInvoiceStatusTypes();
        }

        if (user.isSupplier()) {
            List<InvoiceStatusType> invoiceStatusTypes = new ArrayList<>();
            invoiceStatusTypes.add(cache.getInvoiceStatusType(InvoiceStatusType.NEW));
            invoiceStatusTypes.add(cache.getInvoiceStatusType(InvoiceStatusType.PENDING_VERIFICATION));
            invoiceStatusTypes.add(cache.getInvoiceStatusType(InvoiceStatusType.APPROVED));
            invoiceStatusTypes.add(cache.getInvoiceStatusType(InvoiceStatusType.ON_SALE));
            invoiceStatusTypes.add(cache.getInvoiceStatusType(InvoiceStatusType.SOLD));
            return invoiceStatusTypes;
        }

        if (user.isClient()) {
            List<InvoiceStatusType> invoiceStatusTypes = new ArrayList<>();
            invoiceStatusTypes.add(cache.getInvoiceStatusType(InvoiceStatusType.NEW));
            invoiceStatusTypes.add(cache.getInvoiceStatusType(InvoiceStatusType.APPROVED));
            invoiceStatusTypes.add(cache.getInvoiceStatusType(InvoiceStatusType.SETTLED));
            return invoiceStatusTypes;
        }

        if (user.isFunder()) {
            List<InvoiceStatusType> invoiceStatusTypes = new ArrayList<>();
            invoiceStatusTypes.add(cache.getInvoiceStatusType(InvoiceStatusType.ON_SALE));
            invoiceStatusTypes.add(cache.getInvoiceStatusType(InvoiceStatusType.SOLD));
            invoiceStatusTypes.add(cache.getInvoiceStatusType(InvoiceStatusType.SETTLED));
            return invoiceStatusTypes;
        }

        return null;
    }

}
