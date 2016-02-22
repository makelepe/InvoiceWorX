package za.co.invoiceworx.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import za.co.invoiceworx.util.Cache;
import za.co.invoiceworx.util.DateUtil;
import za.co.invoiceworx.util.Notifier;
import za.co.invoiceworx.dto.UserSearchCriteria;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.entity.UserType;
import za.co.invoiceworx.exception.ExCode;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;
import za.co.invoiceworx.repository.UserRepository;

/**
 *
 * @author Samuel Radingwane
 * @since 14 January 2016
 */
@Stateless
public class UserEJB {

    
    private final Logger log = Logger.getLogger(UserEJB.class);

    @Inject
    private Cache cache;

    @Inject
    private UserRepository userRepo;

    @Inject
    private Notifier notifier;

    public Long register(User user) throws InvoiceWorXServiceException {
        if (user == null) {
            throw new InvoiceWorXServiceException(ExCode.INVALID_INPUT, "Empty user object during registration");
        }
        user.setCreatedTs(DateUtil.newDate());
        user.setActive(Boolean.TRUE);
        
        if (user.getUserType() == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_TYPE_IS_REQUIRED);
        }
        
        UserType userType = cache.getUserType(user.getUserType().getRole());
        user.setUserType(userType);
        
        userRepo.addUser(user);
        log.info("successfully registered user : id = " + user.getId());

        return user.getId();
    }

    public Boolean update(User user, Long userId) throws InvoiceWorXServiceException {
        User _user = userRepo.getUser(userId);
        user.setId(_user.getId());

        _user = user;
        userRepo.updateUser(_user);

        return true;
    }

    public User getUser(Long userId) throws InvoiceWorXServiceException {
        return userRepo.getUser(userId);
    }

    public List<User> search(UserSearchCriteria searchCriteria) {
        return null;
    }

    public Boolean disableUser(Long userId, String reason) throws InvoiceWorXServiceException {
        User _user = userRepo.getUser(userId);

        _user.setActive(Boolean.FALSE);
        _user.setDeactivationReason(reason);

        userRepo.updateUser(_user);

        return true;
    }

    public User disableUserRegistrationType(UserType userType) throws InvoiceWorXServiceException {
        throw new InvoiceWorXServiceException(ExCode.UNKNOWN);
    }

    public Boolean enableUser(Long userId) throws InvoiceWorXServiceException {
        User _user = userRepo.getUser(userId);

        _user.setActive(Boolean.TRUE);
        _user.setDeactivationReason(null);

        userRepo.updateUser(_user);

        return true;
    }

    public User enableUserRegistrationType(UserType userType) throws InvoiceWorXServiceException {
        throw new InvoiceWorXServiceException(ExCode.UNKNOWN);

    }

    public void sendUserNotificationEmail(User user, String body, String subject) throws InvoiceWorXServiceException {
        if (user == null) {
            throw new InvoiceWorXServiceException(ExCode.INVALID_INPUT, "No user was provided. ");
        }

        User _user = userRepo.findUser(user.getUsername());

        if (_user == null) {
            throw new InvoiceWorXServiceException(ExCode.UNKNOWN, "User[with username = " + user.getUsername() + "] not found ");
        }

        notifier.sendEmail(subject, body, new String[]{_user.getPerson().getContact().getEmail()});
    }

    public void sendUserNotificationSMS(User user, String body) throws InvoiceWorXServiceException {
        if (user == null) {
            throw new InvoiceWorXServiceException(ExCode.INVALID_INPUT, "No user was provided. ");
        }

        User _user = userRepo.findUser(user.getUsername());

        if (_user == null) {
            throw new InvoiceWorXServiceException(ExCode.UNKNOWN, "User[with username = " + user.getUsername() + "] not found ");
        }

        notifier.sendSms(body, _user.getPerson().getContact().getCellphone());
    }
}
