
package za.co.invoiceworx.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "password_history")
public class PasswordHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "password_hist_seq", sequenceName = "password_hist_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "password_hist_seq")
    private Long id;
    @Column(name = "changed_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedTs;
    @Size(max = 100)
    @Column(name = "password")
    private String password;


    public PasswordHistory() {
    }

    public PasswordHistory(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getChangedTs() {
        return changedTs;
    }

    public void setChangedTs(Date changedTs) {
        this.changedTs = changedTs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(object instanceof PasswordHistory)) {
            return false;
        }
        PasswordHistory other = (PasswordHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.PasswordHistory[ id=" + id + " ]";
    }
    
}
