package za.co.invoiceworx.util;

import java.math.BigDecimal;
import javax.inject.Inject;
import za.co.invoiceworx.entity.Invoice;
import za.co.invoiceworx.entity.InvoiceStatus;
import za.co.invoiceworx.entity.TransactionDetail;
import za.co.invoiceworx.entity.TransactionStatus;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.repository.InvoiceRepository;

/**
 *
 * @author F4657314
 */
public class HelperUtil {

    @Inject
    private Cache cache;

    public void createTransaction(InvoiceRepository invoiceRepo, Invoice inv, User user, BigDecimal tranAmount, String tranStatusType, String comment) {

        TransactionDetail tran = new TransactionDetail();
        tran.setCreatedTs(DateUtil.newDate());
        tran.setTranRefNumber(ReferenceGenerator.generateInvoiceReference());
        tran.setInvoice(inv);
        tran.setTranAmount(tranAmount);
        tran.setUser(user);

        String sVAT = String.valueOf(cache.get(CacheConstant.VAT_PERCENTAGE));
        BigDecimal vatPercentage = (sVAT == null || sVAT.equalsIgnoreCase("")) ? new BigDecimal(0.00) : new BigDecimal(Double.valueOf(sVAT));
        tran.setTranVatAmount(vatPercentage.multiply(tranAmount));

        TransactionStatus status = new TransactionStatus();
        status.setCreatedBy(user);
        status.setStatusComment(comment);
        status.setTransactionStatusType(cache.getTransactionStatusType(tranStatusType));
        tran.addStatus(status);

        tran.setCurrentStatusType(status.getTransactionStatusType());

    }

    public void createInvoiceStatus(User user, Invoice inv, String statusType, String comment) {
        InvoiceStatus status = new InvoiceStatus();
        status.setCreatedBy(user.getId());
        status.setCreatedTs(DateUtil.newDate());
        status.setInvoice(inv);
        status.setStatusComment(comment);
        status.setInvoiceStatusType(cache.getInvoiceStatusType(statusType));
        inv.setCurrentStatusType(status.getInvoiceStatusType());
        inv.addStatus(status);
        
    }

}
