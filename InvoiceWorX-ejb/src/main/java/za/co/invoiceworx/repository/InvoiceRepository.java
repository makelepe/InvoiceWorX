
package za.co.invoiceworx.repository;

import java.util.List;
import za.co.invoiceworx.entity.InvoiceDocumentFile;
import za.co.invoiceworx.entity.Invoice;
import za.co.invoiceworx.entity.InvoiceStatusType;
import za.co.invoiceworx.entity.TransactionDetail;
import za.co.invoiceworx.entity.User;

/**
 *
 * @author F4657314
 */
public interface InvoiceRepository extends IRepository{
    
    public Long createInvoice (Invoice invoice);
    
    public List<Invoice> findInvoices (InvoiceStatusType statusType);
    
    public List<Invoice> findInvoices (InvoiceStatusType statusType, User user);
    
    public Invoice getInvoice (Long invoiceId);
    
    public Invoice findInvoice (String invoiceRef);

    public Boolean updateInvoice (Invoice inv);

    public List<InvoiceDocumentFile> getInvoiceDocuments(Invoice invoice);
    
    public Long createTransaction (TransactionDetail transactionDetail);

    public TransactionDetail getTransaction (Long tranId);

    public Boolean updateTransaction (TransactionDetail tran);
}
