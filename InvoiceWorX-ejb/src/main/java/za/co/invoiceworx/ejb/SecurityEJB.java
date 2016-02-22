package za.co.invoiceworx.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import za.co.invoiceworx.util.DateUtil;
import za.co.invoiceworx.util.Notifier;
import za.co.invoiceworx.util.ReferenceGenerator;
import za.co.invoiceworx.entity.Login;
import za.co.invoiceworx.entity.Otp;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.exception.ExCode;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;
import za.co.invoiceworx.repository.UserRepository;

/**
 *
 * @author F4657314
 */
@Stateless
public class SecurityEJB {

    private final Logger log = Logger.getLogger(SecurityEJB.class);

    @Inject
    private UserRepository userRepo;

    @Inject
    private Notifier notifier;

    public User login(String username, String password, String sessionId) throws InvoiceWorXServiceException {
        log.info("LOGIN ::::: username = " + username);
        User user = userRepo.findUser(username, password);

        if (user == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "Unsuccessful login for username : " + username);
        }
        log.info("successfuly logged in...");

        Login login = new Login();
        login.setLoginTs(DateUtil.newDate());
        login.setSessionId(sessionId);
        login.setUser(user);
        userRepo.addLogin(login);

        log.info("User Type : " + user.getUserType().getRole());

        return user;
    }

    public void logout(String username) throws InvoiceWorXServiceException {
        Login login = userRepo.findActiveUserLoginSession(username);

        login.setLogoutTs(DateUtil.newDate());

        userRepo.updateLogin(login);

    }

    public void sendOTP(String username) throws InvoiceWorXServiceException {
        User user = userRepo.findUser(username);

        Otp otp = new Otp();
        otp.setConfirmed(Boolean.FALSE);
        otp.setExpired(Boolean.FALSE);
        otp.setCreatedTs(DateUtil.newDate());
        otp.setOtpValue(ReferenceGenerator.generateOTP());
        otp.setUser(user);

        userRepo.addOTP(otp);
        log.info("OTP value: " + otp.getOtpValue());

        notifier.sendSms("Your are about to authorise the InvoiceWorx transaction. Please confirm the OTP. " + otp.getOtpValue(),
                user.getPerson().getContact().getCellphone());

    }

    public Boolean verifyOTP(String username, String otpValue) throws InvoiceWorXServiceException {

        Otp otp = userRepo.findOTP(username, otpValue);
        if (otp == null) {
            throw new InvoiceWorXServiceException(ExCode.OTP_NOT_FOUND, "There is no OTP: " + otpValue + " value for username : " + username);
        }

        return true;
    }

    public Boolean changePassword(String username, String oldPassword, String newPassword) throws InvoiceWorXServiceException {
        User user = userRepo.findUser(username, oldPassword);

        if (user == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "User not found, Invalid Password or Username or User is inactive");
        }
        user.setPassword(newPassword);
        user.setLastPasswordChangedTs(DateUtil.newDate());

        userRepo.updateUser(user);

        notifier.sendEmail("Your InvoiceWorX password has been changed",
                "Hi, ",
                new String[]{user.getPerson().getContact().getEmail()});

        return true;
    }

    public void resetPassword(String username, String email) throws InvoiceWorXServiceException {
        User user = userRepo.findUserToReset(username, email);

        if (user == null) {
            throw new InvoiceWorXServiceException(ExCode.USER_NOT_FOUND, "User was not found to reset password");
        }
        user.setPassword(ReferenceGenerator.generatePassword());
        user.setLastPasswordChangedTs(DateUtil.newDate());

        userRepo.updateUser(user);

        notifier.sendEmail("Your InvoiceWorX password has been reset",
                "Hi " + user.getPerson().getFname() + ", This is your temporary password : " + user.getPassword() + ". Please change your password asap. Thanks",
                new String[]{user.getPerson().getContact().getEmail()});

    }

}
