package za.co.invoiceworx.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author F4657314
 */
@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
     @Id
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "fname")
    private String fname;
    @Column(name = "mname")
    private String mname;
    @Column(name = "lname")
    private String lname;
    @Column(name = "gender")
    private String gender;
    @Column(name = "race")
    private String race;
    @Column(name = "id_number")
    private String idNumber;
    @Column(name = "passport")
    private String passport;
    
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Contact contact;

    public Person() {
    }
    
    public String getFullname () {
        return this.getTitle() + " " + this.getFname() + " " + this.getMname() + " " + this.getLname();
    }

    public Person(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.Person[ id=" + id + " ]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
