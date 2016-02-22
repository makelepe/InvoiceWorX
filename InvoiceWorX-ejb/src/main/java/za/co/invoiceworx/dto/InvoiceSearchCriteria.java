
package za.co.invoiceworx.dto;

import java.util.Date;

/**
 *
 * @author F4657314
 */
public class InvoiceSearchCriteria {
    
    private Long id;
    private String invoiceRef;
    private Date from;
    private Date to;
    private String firstName;
    private String secondName;
    private String surname;
    private Long idNumber;

    private Boolean searchByCreator;
    private Boolean searchByApprover;
    private Boolean searchByFunder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceRef() {
        return invoiceRef;
    }

    public void setInvoiceRef(String invoiceRef) {
        this.invoiceRef = invoiceRef;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public Boolean getSearchByCreator() {
        return searchByCreator;
    }

    public void setSearchByCreator(Boolean searchByCreator) {
        this.searchByCreator = searchByCreator;
    }

    public Boolean getSearchByApprover() {
        return searchByApprover;
    }

    public void setSearchByApprover(Boolean searchByApprover) {
        this.searchByApprover = searchByApprover;
    }

    public Boolean getSearchByFunder() {
        return searchByFunder;
    }

    public void setSearchByFunder(Boolean searchByFunder) {
        this.searchByFunder = searchByFunder;
    }

    
    
}
