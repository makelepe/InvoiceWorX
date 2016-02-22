package za.co.invoiceworx.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author F4657314
 */
@Entity
@Table(name = "invoice_status_type")
public class InvoiceStatusType implements Serializable {

    public static final String NEW = "NEW";
    public static final String APPROVED = "APPROVED";
    public static final String REJECT = "REJECT";
    public static final String PENDING_VERIFICATION = "PENDING_VERIFICATION";
    public static final String ON_SALE = "ON_SALE";
    public static final String SOLD = "SOLD";
    public static final String FUNDER_PAYMENT_CONFIRMED = "FUNDER_PAYMENT_CONFIRMED";
    public static final String SETTLED = "SETTLED";
    public static final String AWAITING_PAYMENT = "AWAITING_PAYMENT";
    public static final String ERROR = "ERROR";
    
    public static final String SUPPLIER_PAID_INIT_AMT = "SUPPLIER_PAID_INIT_AMT";
    public static final String PLATFORM_FEE_PAID = "PLATFORM_FEE_PAID";
    public static final String SUPPLIER_PAYMENT_CONFIRMED = "SUPPLIER_PAYMENT_CONFIRMED";
    public static final String BUYER_SETTLED = "BUYER_SETTLED";
    public static final String FUNDER_PAID = "FUNDER_PAID";
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "invtype")
    private String invtype;

    public InvoiceStatusType() {
    }

    public InvoiceStatusType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvtype() {
        return invtype;
    }

    public void setInvtype(String invtype) {
        this.invtype = invtype;
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
        if (!(object instanceof InvoiceStatusType)) {
            return false;
        }
        InvoiceStatusType other = (InvoiceStatusType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.InvoiceStatusType[ id=" + id + " ]";
    }
    
}
