
package za.co.invoiceworx.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author F4657314
 */
@Entity
@Table(name = "address")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")})
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    private Long id;
    @Size(max = 100)
    @Column(name = "line1")
    private String line1;
    @Size(max = 50)
    @Column(name = "surburb")
    private String surburb;
    @Size(max = 50)
    @Column(name = "city")
    private String city;
    @Size(max = 30)
    @Column(name = "province")
    private String province;
    @Size(max = 4)
    @Column(name = "postal_code")
    private String postalCode;


    public Address() {
    }

    public Address(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getSurburb() {
        return surburb;
    }

    public void setSurburb(String surburb) {
        this.surburb = surburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.Address[ id=" + id + " ]";
    }
    
}
