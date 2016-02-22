package za.co.invoiceworx.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.co.fnb.paypal.africa.qualifier.InvoiceWorxDS;


/**
 * @author <a href="mailto:makelepe1@gmail.com">Samuel Radingwane</a>
 * @since 10 Jan 2016
 */


public class ResourcesUtil {

   // private static final Logger log = Logger.getLogger(ResourcesUtil.class);

    // use @SuppressWarnings to tell IDE to ignore warnings about field not being referenced directly
    @SuppressWarnings("unused")
    @Produces
    @InvoiceWorxDS
    @PersistenceContext(unitName = "invoiceworxPU")
    private EntityManager em;
/*
    private VodsSyncMessageManager vodsService;

    @Produces
    public NotificationServiceImpl getNotificationService() throws ServiceException {
        try {
            if (notificationService == null) {
                if (ServiceConfig.getProperty("notification_enabled").equalsIgnoreCase("true")) {
                    URL url = new URL(ServiceConfig.getProperty("notification_service"));
                    NotificationService service = new NotificationService(url);
                    notificationService = service.getNotificationServiceImplPort();
                }
            }

            return notificationService;

        } catch (MalformedURLException ex) {
            throw new ServiceException(ExCode.NOTIFICATION_CONNECTION_ERROR, ex.getMessage(), ex);
        }
    }
*/

    /*@Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }*/

}
