package za.co.invoiceworx.repository;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.apache.log4j.Logger;
import za.co.invoiceworx.entity.InvoiceDocumentFile;
import za.co.invoiceworx.entity.Invoice;
import za.co.invoiceworx.entity.InvoiceStatusType;
import za.co.invoiceworx.entity.TransactionDetail;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.entity.UserType;

/**
 *
 * @author F4657314
 */
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final Logger log = Logger.getLogger(InvoiceRepositoryImpl.class);

    @Inject
    private JPARepositoryImpl jpa;

    @Override
    public Long createInvoice(Invoice invoice) {
        jpa.add(invoice);

        return invoice.getId();
    }

    @Override
    public List<Invoice> findInvoices(InvoiceStatusType statusType) {
        try {
            String query = "select i from " + Invoice.class.getName() + " i where i.currentStatusType = ?1 ";

            List<Object> params = new ArrayList();
            params.add(statusType);
            return (List<Invoice>) jpa.readList(query, params);

        } catch (NoResultException ne) {
            log.error("Invoice not found (by invoice status type = " + statusType.getInvtype() + ")");
            return null;
        }
    }

    @Override
    public List<Invoice> findInvoices(InvoiceStatusType statusType, User user) {
        try {
            String userType = user.getUserType().getRole();

            String query = "select i from " + Invoice.class.getName() + " i where i.currentStatusType = ?1 ";
            if (userType.equalsIgnoreCase(UserType.SUPPLIER)) {
                query += " and i.createdBy = ?2";

            } else if (userType.equalsIgnoreCase(UserType.CLIENT)) {
                query += " and i.approvedBy = ?2";

            } else if (userType.equalsIgnoreCase(UserType.FUNDER)) {
                query += " and i.fundedBy = ?2";

            } else {
                log.warn("User Type not supplied ... ");
                return null;
            }

            List<Object> params = new ArrayList();
            params.add(statusType);
            return (List<Invoice>) jpa.readList(query, params);

        } catch (NoResultException ne) {
            log.error("Invoice not found (by invoice status type = " + statusType.getInvtype() + ")");
            return null;
        }
    }

    @Override
    public Invoice getInvoice(Long invoiceId) {
        try {
            String query = "select i from " + Invoice.class.getName() + " i where i.id = ?1";

            List<Object> params = new ArrayList();
            params.add(invoiceId);
            return (Invoice) jpa.read(query, params);

        } catch (NoResultException ne) {
            log.error("Invoice not found (by invoice id = " + invoiceId + ")");
            return null;
        }
    }

    @Override
    public Boolean updateInvoice(Invoice invoice) {
        try {
            jpa.update(invoice);
            return true;

        } catch (NoResultException ne) {
            log.error("Error updating invoice (invoice ref = " + invoice.getInvRefNumber() + ")");
            return false;
        }

    }

    @Override
    public List<InvoiceDocumentFile> getInvoiceDocuments(Invoice invoice) {
        try {
            String query = "select doc from " + InvoiceDocumentFile.class.getName() + " doc where doc.invoice.id = ?1";

            List<Object> params = new ArrayList();
            params.add(invoice.getId());
            return (List<InvoiceDocumentFile>) jpa.readList(query, params);

        } catch (NoResultException ne) {
            log.error("Invoice documents not found (by invoice id = " + invoice.getId() + ")");
            return null;
        }
    }

    @Override
    public Invoice findInvoice(String invoiceRef) {
        try {
            String query = "select i from " + Invoice.class.getName() + " i where i.invRefNumber = ?1";

            List<Object> params = new ArrayList();
            params.add(invoiceRef);
            return (Invoice) jpa.read(query, params);

        } catch (NoResultException ne) {
            log.error("Invoice not found (by invoice ref = " + invoiceRef + ")");
            return null;
        }
    }

    @Override
    public Long createTransaction(TransactionDetail transactionDetail) {
        jpa.add(transactionDetail);

        return transactionDetail.getId();
    }
    
    @Override
    public TransactionDetail getTransaction (Long tranId){
        try {
            String query = "select t from " + TransactionDetail.class.getName() + " t where t.id = ?1";

            List<Object> params = new ArrayList();
            params.add(tranId);
            return (TransactionDetail) jpa.read(query, params);

        } catch (NoResultException ne) {
            log.error("Transaction not found (by tran id = " + tranId + ")");
            return null;
        }
    }
    
    @Override
     public Boolean updateTransaction (TransactionDetail tran){
        try {
            jpa.update(tran);
            return true;

        } catch (NoResultException ne) {
            log.error("Error updating transaction (tran id = " + tran.getId() + ")");
            return false;
        }
     }

    @Override
    public Boolean ping() {
        return jpa.ping();
    }

}
