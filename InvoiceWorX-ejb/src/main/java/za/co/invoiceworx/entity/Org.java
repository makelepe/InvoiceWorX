package za.co.invoiceworx.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "org")
@NamedQueries({
    @NamedQuery(name = "Org.findAll", query = "SELECT o FROM Org o")})
public class Org implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "org_seq", sequenceName = "org_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_seq")
    private Long id;
    @Size(max = 100)
    @Column(name = "org_name")
    private String orgName;
    @Size(max = 100)
    @Column(name = "org_reg_num")
    private String orgRegNum;
    @Column(name = "org_reg_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orgRegDate;

    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @ManyToOne (cascade = CascadeType.ALL)
    private Address address;
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Contact contact;

    public Org() {
    }

    public Org(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgRegNum() {
        return orgRegNum;
    }

    public void setOrgRegNum(String orgRegNum) {
        this.orgRegNum = orgRegNum;
    }

    public Date getOrgRegDate() {
        return orgRegDate;
    }

    public void setOrgRegDate(Date orgRegDate) {
        this.orgRegDate = orgRegDate;
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
        if (!(object instanceof Org)) {
            return false;
        }
        Org other = (Org) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.Org[ id=" + id + " ]";
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}
