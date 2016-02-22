package za.co.invoiceworx.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
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
@Table(name = "transaction_detail")
@NamedQueries({
    @NamedQuery(name = "TransactionDetail.findAll", query = "SELECT t FROM TransactionDetail t")})
public class TransactionDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "tran_seq", sequenceName = "tran_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tran_seq")
    private Long id;
    @Size(max = 100)
    @Column(name = "tran_ref_number")
    private String tranRefNumber;
    @Column(name = "created_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTs;
    @Column(name = "tran_amount")
    private BigDecimal tranAmount;
    @Column(name = "tran_vat_amount")
    private BigDecimal tranVatAmount;
    @Column(name = "confirmed_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmedTS;

    @JoinColumn(name = "current_status_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private TransactionStatusType currentStatusType;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User user;
    @JoinColumn(name = "confirmed_by", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User confirmedBy;
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Invoice invoice;
    @JoinColumn(name = "ix_comm_acc_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private IxCommissionAccount ixCommissionAccount;
    @JoinColumn(name = "ix_pooler_acc_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private IxPoolerAccount ixPoolerAccount;

    @OneToMany(mappedBy = "transactionDetail", cascade = CascadeType.ALL)
    private List<TransactionStatus> transactionStatusses;

    public void addStatus(TransactionStatus status) {
        if (status == null) {
            return;
        }

        if (transactionStatusses == null) {
            transactionStatusses = new ArrayList<>();
        }
        status.setTransactionDetail(this);
        transactionStatusses.add(status);
    }

    public TransactionDetail() {
    }

    public TransactionDetail(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTranRefNumber() {
        return tranRefNumber;
    }

    public void setTranRefNumber(String tranRefNumber) {
        this.tranRefNumber = tranRefNumber;
    }

    public Date getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Date createdTs) {
        this.createdTs = createdTs;
    }

    public BigDecimal getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(BigDecimal tranAmount) {
        this.tranAmount = tranAmount;
    }

    public BigDecimal getTranVatAmount() {
        return tranVatAmount;
    }

    public void setTranVatAmount(BigDecimal tranVatAmount) {
        this.tranVatAmount = tranVatAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public IxCommissionAccount getIxCommissionAccount() {
        return ixCommissionAccount;
    }

    public void setIxCommissionAccount(IxCommissionAccount ixCommissionAccount) {
        this.ixCommissionAccount = ixCommissionAccount;
    }

    public IxPoolerAccount getIxPoolerAccount() {
        return ixPoolerAccount;
    }

    public void setIxPoolerAccount(IxPoolerAccount ixPoolerAccount) {
        this.ixPoolerAccount = ixPoolerAccount;
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
        if (!(object instanceof TransactionDetail)) {
            return false;
        }
        TransactionDetail other = (TransactionDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.TransactionDetail[ id=" + id + " ]";
    }

    public List<TransactionStatus> getTransactionStatusses() {
        return transactionStatusses;
    }

    public void setTransactionStatusses(List<TransactionStatus> transactionStatusses) {
        this.transactionStatusses = transactionStatusses;
    }

    public TransactionStatusType getCurrentStatusType() {
        return currentStatusType;
    }

    public void setCurrentStatusType(TransactionStatusType currentStatusType) {
        this.currentStatusType = currentStatusType;
    }

    public Date getConfirmedTS() {
        return confirmedTS;
    }

    public void setConfirmedTS(Date confirmedTS) {
        this.confirmedTS = confirmedTS;
    }

    public User getConfirmedBy() {
        return confirmedBy;
    }

    public void setConfirmedBy(User confirmedBy) {
        this.confirmedBy = confirmedBy;
    }

}
