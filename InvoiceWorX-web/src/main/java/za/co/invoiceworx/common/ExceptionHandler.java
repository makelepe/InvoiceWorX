
package za.co.invoiceworx.common;

import za.co.invoiceworx.exception.ExCode;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;

/**
 *
 * @author F4657314
 */
public class ExceptionHandler {
    
    
    public static String handle (InvoiceWorXServiceException iwse) {
        ExCode exCode = iwse.getCode();
//switch
        
        
        return exCode.getDesc();
    }
    
}
