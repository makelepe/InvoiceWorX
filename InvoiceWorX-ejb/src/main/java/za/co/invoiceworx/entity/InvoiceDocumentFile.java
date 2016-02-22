package za.co.invoiceworx.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author F4657314
 */
@Entity
@Table(name = "doc_file")
public class InvoiceDocumentFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "doc_file_seq", sequenceName = "doc_file_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_file_seq")
    private Integer id;
    @Column(name = "uploaded_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedTs;

    @Lob
    @Column(name = "purchase_order")
    private byte[] purchaseOrder;
    @Lob
    @Column(name = "delivery_note")
    private byte[] deliveryNote;

    @Column(name = "archived")
    private Boolean archived;

    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    @ManyToOne (fetch = FetchType.LAZY)
    private Invoice invoice;

    public InvoiceDocumentFile() {
    }

    public InvoiceDocumentFile(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUploadedTs() {
        return uploadedTs;
    }

    public void setUploadedTs(Date uploadedTs) {
        this.uploadedTs = uploadedTs;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceDocumentFile)) {
            return false;
        }
        InvoiceDocumentFile other = (InvoiceDocumentFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.DocFile[ id=" + id + " ]";
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public byte[] getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(byte[] purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public byte[] getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(byte[] deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

}
