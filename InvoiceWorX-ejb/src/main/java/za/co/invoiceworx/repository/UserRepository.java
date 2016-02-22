
package za.co.invoiceworx.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import za.co.invoiceworx.entity.Login;
import za.co.invoiceworx.entity.Otp;
import za.co.invoiceworx.entity.PasswordHistory;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.entity.UserType;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;


/**
 *
 * @author F4657314
 */
public interface UserRepository  extends IRepository{
    
    public Map<String, List<Object>> loadProperties();
    
    public Login findActiveUserLoginSession (String username) throws InvoiceWorXServiceException;
    
    public Long addLogin (Login login) throws InvoiceWorXServiceException;

    public Boolean updateLogin (Login login) throws InvoiceWorXServiceException;

    public Long addPasswordHistory (PasswordHistory history) throws InvoiceWorXServiceException;
    
    public Otp findOTP (String username, String otpValue) ;
    
    public Long addOTP (Otp otp) ;
    
    public User findUser (String username, String password) throws InvoiceWorXServiceException;
    
    public User findUser (String username) throws InvoiceWorXServiceException;
    
    public User findUserToReset (String username, String email) throws InvoiceWorXServiceException;

    public List<User> findUsersByType (UserType userType) throws InvoiceWorXServiceException;
    
    public Long addUser (User user) throws InvoiceWorXServiceException;
    
    public Boolean updateUser (User user) throws InvoiceWorXServiceException;
    
    public User getUser(Long userId) throws InvoiceWorXServiceException;
    
}
