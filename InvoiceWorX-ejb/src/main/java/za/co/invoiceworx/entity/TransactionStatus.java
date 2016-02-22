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
@Table(name = "transaction_status")
public class TransactionStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "tran_status_seq", sequenceName = "tran_status_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tran_status_seq")
    private Long id;
    @Column(name = "created_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTs;
    @Column(name = "status_comment")
    private String statusComment;

    @JoinColumn(name = "status_type_id", referencedColumnName = "id")
    @ManyToOne (fetch = FetchType.EAGER)
    private TransactionStatusType statusType;
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TransactionDetail transactionDetail;
    @JoinColumn(name = "created_ty", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    public TransactionStatus() {
    }

    public TransactionStatus(Long id) {
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

    public String getStatusComment() {
        return statusComment;
    }

    public void setStatusComment(String statusComment) {
        this.statusComment = statusComment;
    }

    public TransactionStatusType getTransactionStatusType() {
        return statusType;
    }

    public void setTransactionStatusType(TransactionStatusType statusType) {
        this.statusType = statusType;
    }

    public TransactionDetail getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(TransactionDetail transactionDetail) {
        this.transactionDetail = transactionDetail;
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
        if (!(object instanceof TransactionStatus)) {
            return false;
        }
        TransactionStatus other = (TransactionStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.TransactionStatus[ id=" + id + " ]";
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

}
