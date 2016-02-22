package za.co.invoiceworx.ejb;

import java.math.BigDecimal;
import za.co.invoiceworx.dto.InvoiceSearchCriteria;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import za.co.invoiceworx.connector.SageConnector;
import za.co.invoiceworx.util.Cache;
import za.co.invoiceworx.util.DateUtil;
import za.co.invoiceworx.util.ReferenceGenerator;
import za.co.invoiceworx.entity.Invoice;
import za.co.invoiceworx.entity.InvoiceStatusType;
import za.co.invoiceworx.entity.TransactionDetail;
import za.co.invoiceworx.entity.TransactionStatusType;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.exception.ExCode;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;
import za.co.invoiceworx.repository.InvoiceRepository;
import za.co.invoiceworx.repository.UserRepository;
import za.co.invoiceworx.util.HelperUtil;

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


    /*
     *  1. Supplier create a new invoice
     *   [inv status=NEW]
     */
    public String createInvoice(Invoice inv, Long supplierId) throws InvoiceWorXServiceException {
        log.info("::::: creating invoide");

        User supplier = userRepo.getUser(supplierId);
        if (supplier == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "User, id = " + supplierId + ", does not exist");
        }

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

        return inv.getInvRefNumber();
    }

    /*
     * 2. Buyer authorise invoice
     * [inv status=APPROVED | REJECTED]
     */
    public String authoriseInvoice(String invRef, Long buyerId, boolean approve) throws InvoiceWorXServiceException {
        log.info("Authorising invoice id=" + invRef + ". Status changed to APPROVED or REJECT");

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
        if (!buyer.isBuyer()) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_BUYER,
                    "User [id = " + buyer.getId() + ", name = " + buyer.getPerson().getFname() + "] is not a Buyer. only buyer can authorise invoice");
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
            BigDecimal fundedAmount = invoice.getInvAmount().multiply(new BigDecimal(0.8));

            sageConnector.requestPaymentInstruction(funder.getAccount(), cache.getPoolerAccountDetails(), fundedAmount);
            helper.createTransaction(invoiceRepo,
                    invoice,
                    funder,
                    invoice.getFundedAmount(),
                    TransactionStatusType.FUNDER_IN_PAYMENT,
                    "Payment Instruction requested to debit Funder's account through SAGE PAY");

            log.info("Funder payment successful. Funder Amount = " + invoice.getFundedAmount());

            helper.createInvoiceStatus(funder,
                    invoice,
                    InvoiceStatusType.SOLD,
                    "Invoice is sold. Funder deposited :R" + invoice.getFundedAmount() + " and supplier got paid :R" + invoice.getSupplierInitialAmount());

            invoice.setFundedBy(funder);
            invoice.setFundedTs(DateUtil.newDate());

            invoiceRepo.updateInvoice(invoice);

        } catch (InvoiceWorXServiceException iwxse) {
            throw iwxse;
        }

    }

    /*
     * 6. Admin manually confirms funder payment  
     * [inv status=FUNDER_PAYMENT_CONFIRMED]
     */
    public void confirmFunderPayment(Long adminId, Long tranId, BigDecimal fundedAmount) throws InvoiceWorXServiceException {

        // get admin
        User admin = userRepo.getUser(adminId);
        if (admin == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "Admin user " + adminId + " does not exist");
        }

        TransactionDetail tran = invoiceRepo.getTransaction(tranId);
        if (tran == null) {
            throw new InvoiceWorXServiceException(ExCode.TRANSACTION_NOT_FOUND, "Transaction with id = " + tranId + " does not exist");
        }

        tran.setConfirmedBy(admin);
        tran.setConfirmedTS(DateUtil.newDate());
        tran.getInvoice().setFundedAmount(fundedAmount);

        invoiceRepo.updateTransaction(tran);

        helper.createInvoiceStatus(tran.getInvoice().getFundedBy(),
                tran.getInvoice(),
                InvoiceStatusType.FUNDER_PAYMENT_CONFIRMED,
                "Admin confirmed funder payment");
        invoiceRepo.updateInvoice(tran.getInvoice());

    }

    /*
     * 7. Admin pay Supplier 78% of total invoice value 
     * [inv status=SUPPLIER_PAID_INIT_AMT]
     * 5.1 Tran Type [SUPPLIER_OUT_PAYMENT] created - Manual Payment
     */
    public void paySupplier(Invoice invoice, User supplier) {

        BigDecimal supplierAmount = invoice.getInvAmount().multiply(new BigDecimal(0.78));
        invoice.setSupplierInitialAmount(supplierAmount);

        sageConnector.pay(cache.getPoolerAccountDetails(), invoice.getCreatedBy().getAccount(), invoice.getSupplierInitialAmount());

        helper.createTransaction(invoiceRepo,
                invoice,
                supplier,
                invoice.getSupplierInitialAmount(),
                TransactionStatusType.SUPPLIER_OUT_PAYMENT,
                "78% of total invoice value paid to supplier");

        helper.createInvoiceStatus(invoice.getFundedBy(),
                invoice,
                InvoiceStatusType.SUPPLIER_PAID_INIT_AMT,
                "Supplier got paid initial amount");
        invoiceRepo.updateInvoice(invoice);

        log.info("Supplier paid successful. Supplier initial payment = " + invoice.getSupplierInitialAmount());
    }

    /*
     * 8. Admin manually pay platform fee, 2% of total invoice - IX pooler account into IX comm account
     * [inv status=PLATFORM_FEE_PAID]
     * 5.1 Tran Type [PLATFORM_FEE_OUT_PAYMENT] created - Manual Payment
     */
    public void payPlatformFee(String invRef) throws InvoiceWorXServiceException {

        Invoice invoice = invoiceRepo.findInvoice(invRef);
        if (invoice == null) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_FOUND, "Invoice with ref = " + invRef + " does not exist");
        }
        BigDecimal platformFee = invoice.getInvAmount().multiply(new BigDecimal(0.02));
        invoice.setPlatformFee(platformFee);

        sageConnector.pay(cache.getPoolerAccountDetails(), cache.getCommissionAccountDetails(), platformFee);

        helper.createTransaction(invoiceRepo,
                invoice,
                cache.getSystemUser(),
                platformFee,
                TransactionStatusType.SUPPLIER_OUT_PAYMENT,
                "2% of total invoice value paid to IX commission account");

        helper.createInvoiceStatus(cache.getSystemUser(),
                invoice,
                InvoiceStatusType.SUPPLIER_PAID_INIT_AMT,
                "System pay platform fee");
        invoiceRepo.updateInvoice(invoice);

        log.info("Platform fee paid. amount = " + platformFee);
    }

    /*
     * 9. Supplier confirm the receipt of the initial payment
     * [inv status=SUPPLIER_PAYMENT_CONFIRMED]
     */
    public void confirmInitialPaymentReceipt(Long tranId, Long userId) throws InvoiceWorXServiceException {

        TransactionDetail tran = invoiceRepo.getTransaction(tranId);
        if (tran == null) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_FOUND, "Transaction with id = " + tranId + " does not exist");
        }

        User supplier = userRepo.getUser(userId);
        if (supplier == null) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_FOUND, "User with id = " + userId + " does not exist");
        }

        tran.setConfirmedBy(supplier);
        tran.setConfirmedTS(DateUtil.newDate());

        invoiceRepo.updateTransaction(tran);

        helper.createInvoiceStatus(supplier,
                tran.getInvoice(),
                InvoiceStatusType.SUPPLIER_PAYMENT_CONFIRMED,
                "Supplier confirm the receipt of initial payment");
        invoiceRepo.updateInvoice(tran.getInvoice());

        log.info("Supplier[" + supplier.getPerson().getFullname() + "] confirmed the reciept of initial payment");
    }

    /*
     * 10. Buyer settle invoice - payment made directly into IX account not via SAGE PAY
     * [inv status=BUYER_SETTLED]
     * 10.1 Tran Type [BUYER_SETTLEMENT_PAY] created - 
     */
    public void buyerSettleInvoice(Long adminId, String invRef, BigDecimal amountPaid) throws InvoiceWorXServiceException {

        Invoice invoice = invoiceRepo.findInvoice(invRef);
        if (invoice == null) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_FOUND, "Invoice with ref = " + invRef + " does not exist");
        }

        User admin = userRepo.getUser(adminId);
        if (admin == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "User with id = " + adminId + " does not exist");
        }
        
        invoice.setBuyerSettlementAmount(amountPaid);

        helper.createTransaction(invoiceRepo,
                invoice,
                admin,
                amountPaid,
                TransactionStatusType.BUYER_SETTLED,
                "100% of total invoice value plus interest paid to IX pooler account");

        helper.createInvoiceStatus(admin,
                invoice,
                InvoiceStatusType.SUPPLIER_PAYMENT_CONFIRMED,
                "Supplier confirm the receipt of initial payment");
        invoiceRepo.updateInvoice(invoice);

        log.info("Admin[" + invoice.getApprovedBy().getPerson().getFullname() + "] confirm buyer settlement of invoice");
    }

    /*
     * 11. Admin manually pay platform fee, 2% of total invoice - IX pooler account into IX comm account
     * [inv status=FUNDER_PAID]
     * Tran Type [FUNDER_OUT_PAYMENT] created - Manual Payment
     */
    public void payFunder(String invRef, Long adminId) throws InvoiceWorXServiceException {

        Invoice invoice = invoiceRepo.findInvoice(invRef);
        if (invoice == null) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_FOUND, "Invoice with ref = " + invRef + " does not exist");
        }
        User admin = userRepo.getUser(adminId);
        if (admin == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "User with id = " + adminId + " does not exist");
        }
        BigDecimal funderFee = invoice.getInvAmount().multiply(new BigDecimal(0.8));
        invoice.setFunderFee(funderFee);

        Integer noOfMonths = DateUtil.getNumberOfMonths(invoice.getCreatedTs(), DateUtil.newDate());
        BigDecimal funderComm = invoice.getInvAmount().multiply(new BigDecimal(0.3)).multiply(new BigDecimal(noOfMonths));
        BigDecimal platformComm = funderComm.multiply(new BigDecimal(0.1));
        
        invoice.setPlatformFee(platformComm);
        invoice.setFunderCommission(funderComm);

        invoice.setFunderCommission(funderComm);

        //sageConnector.pay(cache.getPoolerAccountDetails(), cache.getCommissionAccountDetails(), platformFee);
        helper.createTransaction(invoiceRepo,
                invoice,
                admin,
                funderFee.add(funderComm),
                TransactionStatusType.FUNDER_OUT_PAYMENT,
                "80% of total invoice value plus 3% by number of months");
        helper.createTransaction(invoiceRepo,
                invoice,
                admin,
                funderFee.add(platformComm),
                TransactionStatusType.PLATFORM_COMM_OUT_PAYMENT,
                "10% of funder commission");

        helper.createInvoiceStatus(admin,
                invoice,
                InvoiceStatusType.FUNDER_PAID,
                "Supplier got paid initial amount");
        invoiceRepo.updateInvoice(invoice);

        log.info("Funder and Platform Comm paid out. funder fee = " + funderFee +", funder comm :"+ funderComm);
    }
    
    
    
      /*
     * 12. Admin settle invoice by paying out the remaining amount into supplier account 
     * [inv status=SETTLED]
     * Tran Type [SETTLED] created - Manual Payment
     */
    public void settleInvoice(String invRef, Long adminId) throws InvoiceWorXServiceException {

        Invoice invoice = invoiceRepo.findInvoice(invRef);
        if (invoice == null) {
            throw new InvoiceWorXServiceException(ExCode.INVOICE_NOT_FOUND, "Invoice with ref = " + invRef + " does not exist");
        }
        
        User admin = userRepo.getUser(adminId);
        if (admin == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "User with id = " + adminId + " does not exist");
        }
        BigDecimal remainingAmount = invoice.getBuyerSettlementAmount()
                .subtract(invoice.getFunderFee())
                .subtract(invoice.getFunderCommission());
        invoice.setRemainingAmount(remainingAmount);


        //sageConnector.pay(cache.getPoolerAccountDetails(), cache.getCommissionAccountDetails(), platformFee);
        helper.createTransaction(invoiceRepo,
                invoice,
                admin,
                remainingAmount,
                TransactionStatusType.BUYER_SETTLED,
                "pay remaining amount to supplier");

        helper.createInvoiceStatus(admin,
                invoice,
                InvoiceStatusType.SETTLED,
                "remaining amount paid to supplier");
        invoiceRepo.updateInvoice(invoice);

        log.info("Settled. remaining amt = " + remainingAmount );
    }


    /*
     * search invoices
     */
    public List<Invoice> findInvoices(InvoiceSearchCriteria criteria) {
        // ref number
        return null;
    }

    /*
     * find invoice
     */
    public Invoice getInvoice(Long invoiceId) {

        return invoiceRepo.getInvoice(invoiceId);
    }

}
