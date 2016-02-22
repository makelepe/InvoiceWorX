
package za.co.invoiceworx.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author F4657314
 */
@Entity
@Table(name = "preferences")
public class Preferences implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "key")
    private String key;
    @Column(name = "value")
    private String value;

    public Preferences() {
    }

    public Preferences(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Preferences)) {
            return false;
        }
        if (this.getId() == null) {
            return false;
        }
        Preferences other = (Preferences) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "za.co.invoiceworx.entity.Preferences[ id=" + id + " ]";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
