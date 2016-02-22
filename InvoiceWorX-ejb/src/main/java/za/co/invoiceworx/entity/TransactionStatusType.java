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
@Table(name = "transaction_status_type")
public class TransactionStatusType implements Serializable {

    public static final String FUNDER_IN_PAYMENT = "FUNDER_IN_PAYMENT";
    public static final String SUPPLIER_OUT_PAYMENT = "SUPPLIER_OUT_PAYMENT";
    public static final String BUYER_SETTLED = "BUYER_SETTLED";
    public static final String SETTLED = "SETTLED";
    public static final String ERROR = "ERROR";
    public static final String FUNDER_OUT_PAYMENT = "FUNDER_OUT_PAYMENT";
    public static final String PLATFORM_COMM_OUT_PAYMENT = "PLATFORM_COMM_OUT_PAYMENT";
    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "trantype")
    private String trantype;

    public TransactionStatusType() {
    }

    public TransactionStatusType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrantype() {
        return trantype;
    }

    public void setTrantype(String trantype) {
        this.trantype = trantype;
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
        if (!(object instanceof TransactionStatusType)) {
            return false;
        }
        TransactionStatusType other = (TransactionStatusType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.TransactionStatusType[ id=" + id + " ]";
    }

}
