package za.co.invoiceworx.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.apache.log4j.Logger;
import za.co.invoiceworx.entity.InvoiceStatusType;
import za.co.invoiceworx.entity.Login;
import za.co.invoiceworx.entity.Otp;
import za.co.invoiceworx.entity.PasswordHistory;
import za.co.invoiceworx.entity.Preferences;
import za.co.invoiceworx.entity.TransactionStatusType;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.entity.UserType;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;

/**
 *
 * @author F4657314
 */
public class UserRepositoryImpl implements UserRepository {

    private final Logger log = Logger.getLogger(UserRepositoryImpl.class);

    @Inject
    private JPARepositoryImpl jpa;

    @Override
    public Login findActiveUserLoginSession(String username) throws InvoiceWorXServiceException {
        try {
            String query = "select l from " + Login.class.getName() + " l where i.user.username = ?1 ";

            List<Object> params = new ArrayList();
            params.add(username);
            return (Login) jpa.read(query, params);

        } catch (NoResultException ne) {
            log.error("Cannot find active login (for username = " + username + ")");
            return null;
        }
    }

    @Override
    public Long addLogin(Login login) throws InvoiceWorXServiceException {
        jpa.add(login);

        return login.getId();
    }

    @Override
    public Long addPasswordHistory(PasswordHistory history) throws InvoiceWorXServiceException {
        jpa.add(history);

        return history.getId();
    }

    @Override
    public Otp findOTP(String username, String otpValue) {
        try {
            String query = "select o from " + Otp.class.getName() + " o where o.user.username = ?1 and o.otpValue = ?2 and not o.confirmed and not o.expired";

            List<Object> params = new ArrayList();
            params.add(username);
            params.add(otpValue);
            return (Otp) jpa.read(query, params);

        } catch (NoResultException ne) {
            log.error("Cannot find active login (for username = " + username + ")");
            return null;
        }
    }

    @Override
    public Long addOTP(Otp otp) {
        jpa.add(otp);

        return otp.getId();
    }

    @Override
    public User getUser(Long userId) throws InvoiceWorXServiceException {
        try {
            String query = "select u from " + User.class.getName() + " u where u.id = ?1 and u.active=true";

            List<Object> params = new ArrayList();
            params.add(userId);
            return (User) jpa.read(query, params);

        } catch (NoResultException ne) {
            log.error("Cannot find user (for user ID = " + userId + ")");
            return null;
        }
    }

    @Override
    public User findUser(String username) throws InvoiceWorXServiceException {
        try {
            String query = "select u from " + User.class.getName() + " u where u.username = ?1 and u.active=true";

            List<Object> params = new ArrayList();
            params.add(username);
            return (User) jpa.read(query, params);

        } catch (NoResultException ne) {
            log.error("Cannot find user (for Username = " + username + ")");
            return null;
        }
    }

    @Override
    public List<User> findUsers(UserType userType) throws InvoiceWorXServiceException {
        try {
            String query = "select u from " + User.class.getName() + " u where u.userType = ?1 and u.active=true";

            List<Object> params = new ArrayList();
            params.add(userType);
            return (List<User>) jpa.readList(query, params);

        } catch (NoResultException ne) {
            log.error("Cannot find user (for user role = " + userType.getRole() + ")");
            return null;
        }
    }

    @Override
    public User findUser(String username, String password) throws InvoiceWorXServiceException {
        try {
            String query = "select u from " + User.class.getName() + " u where u.username = ?1 and u.password = ?2 and u.active=true";

            List<Object> params = new ArrayList();
            params.add(username);
            params.add(password);
            return (User) jpa.read(query, params);

        } catch (NoResultException ne) {
            log.error("Cannot find user (for username = " + username + ")");
            return null;
        }
    }

    @Override
    public User findUserToReset(String username, String email) throws InvoiceWorXServiceException {
        try {
            String query = "select u from " + User.class.getName() + " u where u.username = ?1 and u.person.contact.emailAddress = ?2 and u.active=true";

            List<Object> params = new ArrayList();
            params.add(username);
            params.add(email);
            return (User) jpa.read(query, params);

        } catch (NoResultException ne) {
            log.error("Cannot find user (for username = " + username + ")");
            return null;
        }
    }

    @Override
    public List<User> findUsersByType(UserType userType) throws InvoiceWorXServiceException {
        try {
            String query = "select u from " + User.class.getName() + " u where i.userType = ?1 ";

            List<Object> params = new ArrayList();
            params.add(userType);
            return (List<User>) jpa.readList(query, params);

        } catch (NoResultException ne) {
            log.error("Cannot find users (by type = " + userType.getRole() + ")");
            return null;
        }
    }

    @Override
    public Long addUser(User user) throws InvoiceWorXServiceException {
        log.info("jpa = " + jpa);
        log.info("user = " + user);

        jpa.add(user);

        return user.getId();
    }

    @Override
    public Boolean updateUser(User user) throws InvoiceWorXServiceException {
        try {
            jpa.update(user);
            return true;

        } catch (NoResultException ne) {
            log.error("Error updating user (invoice ref = " + user.getUsername() + ")");
            return false;
        }
    }

    @Override
    public Map<String, List<Object>> loadProperties() {
        Map<String, List<Object>> properties = new HashMap<>();
        String query;

        try {

            query = "select p from " + UserType.class.getName() + " p ";
            List userTypes = jpa.readList(query, null);

            if (userTypes != null) {
                log.info("user types : " + userTypes.size());
                properties.put("USER_TYPE", userTypes);
                log.info("successfuly loaded user types");
            } else {
                log.info("no user type is loaded");
            }
        } catch (NoResultException ne) {
            log.error("Cannot find user types: " + ne.getMessage(), ne);
        }

        try {
            log.info("loading invoice status types ...");
            query = "select p from " + InvoiceStatusType.class.getName() + " p ";
            List invoiceStatusTypes = jpa.readList(query, null);

            if (invoiceStatusTypes != null) {
                log.info("invoice status types : " + invoiceStatusTypes.size());
                properties.put("INVOICE_STATUS_TYPE", invoiceStatusTypes);
                log.info("successfuly loaded invoice status types");
            } else {
                log.info("no invoice status type is loaded");
            }
        } catch (NoResultException ne) {
            log.error("Cannot find invoice status types: " + ne.getMessage(), ne);
        }
        try {
            log.info("loading transation status types ...");
            query = "select p from " + TransactionStatusType.class.getName() + " p ";
            List transactionStatusType = jpa.readList(query, null);

            if (transactionStatusType != null) {
                log.info("transaction status types : " + transactionStatusType.size());
                properties.put("TRANSACTION_STATUS_TYPE", transactionStatusType);
                log.info("successfuly loaded transaction status types");
            } else {
                log.info("no transaction status type is loaded");
            }
        } catch (NoResultException ne) {
            log.error("Cannot find transaction status types: " + ne.getMessage(), ne);
        }
        try {
            log.info("loading preferances ...");
            query = "select p from " + Preferences.class.getName() + " p ";
            List preferences = jpa.readList(query, null);

            if (preferences != null) {
                log.info("user types : " + preferences.size());
                properties.put("PREFERENCES", preferences);
                log.info("successfuly loaded preferences");
            } else {
                log.info("no preference is loaded");
            }
        } catch (NoResultException ne) {
            log.error("Cannot find preferences : " + ne.getMessage(), ne);
        }

        return properties;
    }

    @Override
    public Boolean updateLogin(Login login) {
        try {
            jpa.update(login);
            return true;

        } catch (NoResultException ne) {
            log.error("Error updating login (invoice ref = " + login.getUser().getUsername() + ")");
            return false;
        }
    }

    @Override
    public Boolean ping() {
        return jpa.ping();
    }

}
