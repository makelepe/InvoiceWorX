package za.co.invoiceworx.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "login")
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "login_seq", sequenceName = "login_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_seq")
    private Long id;
    @Column(name = "login_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTs;
    @Column(name = "logout_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutTs;
    @Column(name = "session_id")
    private String sessionId;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    public Login() {
    }

    public Login(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLoginTs() {
        return loginTs;
    }

    public void setLoginTs(Date loginTs) {
        this.loginTs = loginTs;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.Login[ id=" + id + " ]";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLogoutTs() {
        return logoutTs;
    }

    public void setLogoutTs(Date logoutTs) {
        this.logoutTs = logoutTs;
    }

}
