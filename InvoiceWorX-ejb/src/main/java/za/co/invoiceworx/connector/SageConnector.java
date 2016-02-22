package za.co.invoiceworx.connector;

import java.math.BigDecimal;
import za.co.invoiceworx.entity.Account;
import za.co.invoiceworx.entity.Invoice;

/**
 *
 * @author F4657314
 */
public class SageConnector {
    
    public String requestPaymentInstruction (Account fromAccount, Account toAccount, BigDecimal amount) {
        // request payment to be made from Funder's account into Invoice WorX sage account
        // The amount of [80% of total invoice value]
        //invoice.setFundedAmount(fundedAmount);
        
        return null;
    }
    
    public String pay (Account fromAccount, Account toAccount, BigDecimal tranAmount) {
        // pay supplier 78% of total invoice.
        
        return null;
    }
}
