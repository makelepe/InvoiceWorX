package za.co.invoiceworx.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "invoice")
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "inv_seq", sequenceName = "inv_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inv_seq")
    private Long id;
    @Size(max = 100)
    @Column(name = "inv_ref_number")
    private String invRefNumber;
    @Size(max = 50)
    @Column(name = "inv_status")
    private String invStatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "inv_amount")
    private BigDecimal invAmount;
    @Column(name = "inv_vat_amount")
    private BigDecimal invVatAmount;
    @Column(name = "platform_fee")
    private BigDecimal platformFee;
    @Column(name = "funded_amount")
    private BigDecimal fundedAmount;
    @Column(name = "funder_commission")
    private BigDecimal funderCommission;
    @Column(name = "supplier_initial_amount_paid")
    private BigDecimal supplierInitialAmount;

    @Column(name = "funder_fee")
    private BigDecimal funderFee;
    @Column(name = "platform_comm")
    private BigDecimal platformComm;
    @Column(name = "remaining_amount")
    private BigDecimal remainingAmount;
    @Column(name = "buyer_settlement_amt")
    private BigDecimal buyerSettlementAmount;

    @Column(name = "verified_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date verifiedTs;
    @Column(name = "created_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTs;
    @Column(name = "approved_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedTs;
    @Column(name = "funded_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fundedTs;

    @JoinColumn(name = "current_status_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private InvoiceStatusType currentStatusType;
    @JoinColumn(name = "verified_by", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User verifiedBy;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User createdBy;
    @JoinColumn(name = "approved_by", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User approvedBy;
    @JoinColumn(name = "funded_by", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User fundedBy;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InvoiceDocumentFile> docFiles;
    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItems;
    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InvoiceStatus> invoiceStatusses;
    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TransactionDetail> transactions;

    public void addItem(InvoiceItem item) {
        if (item == null) {
            return;
        }

        if (invoiceItems == null) {
            invoiceItems = new ArrayList<>();
        }
        item.setInvoice(this);
        invoiceItems.add(item);
    }

    public void addDocument(InvoiceDocumentFile docFile) {
        if (docFile == null) {
            return;
        }

        if (docFiles == null) {
            docFiles = new ArrayList<>();
        }
        docFile.setInvoice(this);
        docFiles.add(docFile);
    }

    public void removeDocument(InvoiceDocumentFile docFile) {
        if (docFile == null) {
            return;
        }

        if (docFiles == null) {
            return;
        }
        docFiles.remove(docFile);
    }

    public void addTransaction(TransactionDetail transactionDetail) {
        if (transactionDetail == null) {
            return;
        }

        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        transactionDetail.setInvoice(this);
        transactions.add(transactionDetail);
    }

    public void addStatus(InvoiceStatus status) {
        if (status == null) {
            return;
        }

        if (invoiceStatusses == null) {
            invoiceStatusses = new ArrayList<>();
        }
        status.setInvoice(this);
        invoiceStatusses.add(status);
    }


    public Boolean isOwnsBy(User supplier) {
        if (supplier == null) {
            return false;
        }
        return supplier.getId().equals(this.getCreatedBy().getId());
    }

    public Boolean isNew() {
        return (this.getCurrentStatusType() != null) && (this.getCurrentStatusType().getInvtype().equalsIgnoreCase(InvoiceStatusType.NEW));
    }

    public Boolean isApproved() {
        return (this.getCurrentStatusType() != null) && (this.getCurrentStatusType().getInvtype().equalsIgnoreCase(InvoiceStatusType.APPROVED));
    }

    public Boolean isPendingVerification() {
        return (this.getCurrentStatusType() != null) && (this.getCurrentStatusType().getInvtype().equalsIgnoreCase(InvoiceStatusType.PENDING_VERIFICATION));
    }

    public Boolean isOnSale() {
        return (this.getCurrentStatusType() != null) && (this.getCurrentStatusType().getInvtype().equalsIgnoreCase(InvoiceStatusType.ON_SALE));
    }

    public Boolean isSold() {
        return (this.getCurrentStatusType() != null) && (this.getCurrentStatusType().getInvtype().equalsIgnoreCase(InvoiceStatusType.SOLD));
    }

    public Invoice() {
    }

    public Invoice(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvRefNumber() {
        return invRefNumber;
    }

    public void setInvRefNumber(String invRefNumber) {
        this.invRefNumber = invRefNumber;
    }

    public String getInvStatus() {
        return invStatus;
    }

    public void setInvStatus(String invStatus) {
        this.invStatus = invStatus;
    }

    public BigDecimal getInvAmount() {
        return invAmount;
    }

    public void setInvAmount(BigDecimal invAmount) {
        this.invAmount = invAmount;
    }

    public BigDecimal getInvVatAmount() {
        return invVatAmount;
    }

    public void setInvVatAmount(BigDecimal invVatAmount) {
        this.invVatAmount = invVatAmount;
    }

    public BigDecimal getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(BigDecimal platformFee) {
        this.platformFee = platformFee;
    }

    public BigDecimal getFundedAmount() {
        return fundedAmount;
    }

    public void setFundedAmount(BigDecimal fundedAmount) {
        this.fundedAmount = fundedAmount;
    }

    public BigDecimal getFunderCommission() {
        return funderCommission;
    }

    public void setFunderCommission(BigDecimal funderCommission) {
        this.funderCommission = funderCommission;
    }

    public Date getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Date createdTs) {
        this.createdTs = createdTs;
    }

    public Date getApprovedTs() {
        return approvedTs;
    }

    public void setApprovedTs(Date approvedTs) {
        this.approvedTs = approvedTs;
    }

    public Date getFundedTs() {
        return fundedTs;
    }

    public void setFundedTs(Date fundedTs) {
        this.fundedTs = fundedTs;
    }

    public List<InvoiceDocumentFile> getDocFiles() {
        return docFiles;
    }

    public void setDocFiles(List<InvoiceDocumentFile> docFiles) {
        this.docFiles = docFiles;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public User getFundedBy() {
        return fundedBy;
    }

    public void setFundedBy(User fundedBy) {
        this.fundedBy = fundedBy;
    }

    public List<InvoiceStatus> getInvoiceStatusses() {
        return invoiceStatusses;
    }

    public void setInvoiceStatusses(List<InvoiceStatus> invoiceStatusses) {
        this.invoiceStatusses = invoiceStatusses;
    }

    public List<TransactionDetail> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDetail> transactions) {
        this.transactions = transactions;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.Invoice[ id=" + id + " ]";
    }

    public InvoiceStatusType getCurrentStatusType() {
        return currentStatusType;
    }

    public void setCurrentStatusType(InvoiceStatusType currentStatusType) {
        this.currentStatusType = currentStatusType;
    }

    public Date getVerifiedTs() {
        return verifiedTs;
    }

    public void setVerifiedTs(Date verifiedTs) {
        this.verifiedTs = verifiedTs;
    }

    public User getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(User verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public BigDecimal getSupplierInitialAmount() {
        return supplierInitialAmount;
    }

    public void setSupplierInitialAmount(BigDecimal supplierInitialAmount) {
        this.supplierInitialAmount = supplierInitialAmount;
    }

    public BigDecimal getFunderFee() {
        return funderFee;
    }

    public void setFunderFee(BigDecimal funderFee) {
        this.funderFee = funderFee;
    }

    public BigDecimal getPlatformComm() {
        return platformComm;
    }

    public void setPlatformComm(BigDecimal platformComm) {
        this.platformComm = platformComm;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public BigDecimal getBuyerSettlementAmount() {
        return buyerSettlementAmount;
    }

    public void setBuyerSettlementAmount(BigDecimal buyerSettlementAmount) {
        this.buyerSettlementAmount = buyerSettlementAmount;
    }

}
