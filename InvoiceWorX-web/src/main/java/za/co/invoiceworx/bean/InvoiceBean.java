package za.co.invoiceworx.bean;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author F4657314
 */
public class InvoiceBean {

    private String action;

    // invoice info
    private Long id;
    private String invRefNumber;
    private String invStatus;
    private BigDecimal invAmount;
    private BigDecimal invVatAmount;
    private BigDecimal commission;
    private BigDecimal fundedAmount;
    private BigDecimal funderCommission;

    private UserBean createdBy;
    private Date createdTs;

    private UserBean approvedBy;
    private Date approvedTs;

    private UserBean fundedBy;
    private Date fundedTs;

    private Map<Integer, File> docFiles;

    // invoice items|statusses|trans
    private Map<Integer, String> invoiceStatusses;
    private List<TransactionBean> transactions;
    private List<InvoiceItemBean> invoiceItems;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
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

    public UserBean getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserBean createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Date createdTs) {
        this.createdTs = createdTs;
    }

    public UserBean getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(UserBean approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApprovedTs() {
        return approvedTs;
    }

    public void setApprovedTs(Date approvedTs) {
        this.approvedTs = approvedTs;
    }

    public UserBean getFundedBy() {
        return fundedBy;
    }

    public void setFundedBy(UserBean fundedBy) {
        this.fundedBy = fundedBy;
    }

    public Date getFundedTs() {
        return fundedTs;
    }

    public void setFundedTs(Date fundedTs) {
        this.fundedTs = fundedTs;
    }

    public Map<Integer, File> getDocFiles() {
        return docFiles;
    }

    public void setDocFiles(Map<Integer, File> docFiles) {
        this.docFiles = docFiles;
    }

    public Map<Integer, String> getInvoiceStatusses() {
        return invoiceStatusses;
    }

    public void setInvoiceStatusses(Map<Integer, String> invoiceStatusses) {
        this.invoiceStatusses = invoiceStatusses;
    }

    public List<TransactionBean> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionBean> transactions) {
        this.transactions = transactions;
    }

    public List<InvoiceItemBean> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItemBean> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

}
