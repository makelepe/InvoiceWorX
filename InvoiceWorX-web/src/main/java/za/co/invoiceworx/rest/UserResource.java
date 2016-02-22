package za.co.invoiceworx.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import za.co.invoiceworx.dto.UserSearchCriteria;
import za.co.invoiceworx.ejb.UserEJB;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.entity.UserType;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;

/**
 *
 * @author F4657314
 */
@Path("user")
public class UserResource {
    
    @EJB
    private UserEJB ejb;

    
    public void register(User user, UserType userType) {
        try {
            ejb.register(user);
        } catch (InvoiceWorXServiceException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(User user, UserType role, Long userId) {
        try {
            ejb.update(user, userId);
        } catch (InvoiceWorXServiceException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUser(Long userId) {
        try {
            return ejb.getUser(userId);
        } catch (InvoiceWorXServiceException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<User> search(UserSearchCriteria searchCriteria) {
        return ejb.search(searchCriteria);
    }

    public void disableUser(Long userId) {
        try {
            ejb.disableUser(userId, "");
        } catch (InvoiceWorXServiceException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disableUserRegistrationType(UserType userType) {
        try {
            ejb.disableUserRegistrationType(userType);
        } catch (InvoiceWorXServiceException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enableUser(Long userId) {
        try {
            ejb.enableUser(userId);
        } catch (InvoiceWorXServiceException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enableUserRegistrationType(UserType userType) {
        try {
            ejb.enableUserRegistrationType(userType);
        } catch (InvoiceWorXServiceException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendUserNotificationEmail(User user, String body, String subject) {
        try {
            ejb.sendUserNotificationEmail(user, body, subject);
        } catch (InvoiceWorXServiceException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendUserNotificationSMS(User user, String body, String subject) throws InvoiceWorXServiceException {
        ejb.sendUserNotificationSMS(user, body);
    }

}
