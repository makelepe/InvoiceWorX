package za.co.invoiceworx.soap;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import za.co.invoiceworx.ejb.SecurityEJB;
import za.co.invoiceworx.util.Cache;
import za.co.invoiceworx.ejb.UserEJB;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;

/**
 *
 * @author F4657314
 */
@WebService(serviceName = "InvoiceworXWebService")
@Stateless()
public class InvoiceworXWebService {

    @EJB
    private UserEJB userEJB;

    @EJB
    private SecurityEJB securityEJB;

    @Inject
    private Cache cache;

    /**
     * Web service operation
     *
     * @return
     */
    @WebMethod(operationName = "ping")
    public String ping() {
        cache.init();
        return "I am up and running";
    }

    @WebMethod(operationName = "register")
    public Long register(@WebParam(name = "user") User user) throws InvoiceWorXServiceException {
        return userEJB.register(user);
    }

    @WebMethod(operationName = "login")
    public User login(
            @WebParam(name = "username") String username, 
            @WebParam(name = "password") String password,
            @WebParam(name = "sessionId") String sessionId) throws InvoiceWorXServiceException {
        
        return securityEJB.login(username, password, sessionId);
    }

}
