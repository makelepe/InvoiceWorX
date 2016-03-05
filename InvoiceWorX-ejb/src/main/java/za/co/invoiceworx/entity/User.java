package za.co.invoiceworx.entity;

import java.io.Serializable;
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

/**
 *
 * @author F4657314
 */
@Entity
@Table(name = "_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "created_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTs;
    @Column(name = "expiry_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryTs;
    @Column(name = "last_password_changed_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordChangedTs;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "deactivation_reason")
    private String deactivationReason;

    // update during registration
    @JoinColumn(name = "user_type_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private UserType userType;
    
    // new insert during registration
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Org org;
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Person person;
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Account account;

    public Boolean isAdmin() {
        return (this.getUserType()!= null) && (this.getUserType().getRole().equalsIgnoreCase(UserType.ADMIN));
    }

    public Boolean isSupplier() {
        return (this.getUserType()!= null) && (this.getUserType().getRole().equalsIgnoreCase(UserType.SUPPLIER));
    }
    
    public Boolean isFunder() {
        return (this.getUserType()!= null) && (this.getUserType().getRole().equalsIgnoreCase(UserType.FUNDER));
    }

    public Boolean isClient() {
        return (this.getUserType()!= null) && (this.getUserType().getRole().equalsIgnoreCase(UserType.CLIENT));
    }

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Date createdTs) {
        this.createdTs = createdTs;
    }

    public Date getExpiryTs() {
        return expiryTs;
    }

    public void setExpiryTs(Date expiryTs) {
        this.expiryTs = expiryTs;
    }

    public Date getLastPasswordChangedTs() {
        return lastPasswordChangedTs;
    }

    public void setLastPasswordChangedTs(Date lastPasswordChangedTs) {
        this.lastPasswordChangedTs = lastPasswordChangedTs;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDeactivationReason() {
        return deactivationReason;
    }

    public void setDeactivationReason(String deactivationReason) {
        this.deactivationReason = deactivationReason;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    


    /**
     * @return the userType
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    

    @Override
	public String toString() {
		return "User [id=" + id +", createdTs=" + createdTs + ", expiryTs="
				+ expiryTs + ", lastPasswordChangedTs=" + lastPasswordChangedTs
				+ ", active=" + active + ", deactivationReason="
				+ deactivationReason + ", userType=" + userType + ", org="
				+ org + ", person=" + person + ", account=" + account + "]";
	}

}
