package za.co.invoiceworx.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author F4657314
 */
@Entity
@Table(name = "invoice_status")
@NamedQueries({
    @NamedQuery(name = "InvoiceStatus.findAll", query = "SELECT i FROM InvoiceStatus i")})
public class InvoiceStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "inv_status_seq", sequenceName = "inv_status_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inv_status_seq")
    private Long id;
    @Column(name = "created_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTs;
    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "status_comment")
    private String statusComment;

    @JoinColumn(name = "status_type_id", referencedColumnName = "id")
    @ManyToOne
    private InvoiceStatusType invoiceStatusType;
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    public InvoiceStatus() {
    }

    public InvoiceStatus(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Date createdTs) {
        this.createdTs = createdTs;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatusComment() {
        return statusComment;
    }

    public void setStatusComment(String statusComment) {
        this.statusComment = statusComment;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public InvoiceStatusType getInvoiceStatusType() {
        return invoiceStatusType;
    }

    public void setInvoiceStatusType(InvoiceStatusType invoiceStatusType) {
        this.invoiceStatusType = invoiceStatusType;
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
        if (!(object instanceof InvoiceStatus)) {
            return false;
        }
        InvoiceStatus other = (InvoiceStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.InvoiceStatus[ id=" + id + " ]";
    }
    
}
