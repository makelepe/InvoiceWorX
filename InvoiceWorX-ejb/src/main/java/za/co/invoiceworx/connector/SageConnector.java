package za.co.invoiceworx.connector;

import java.math.BigDecimal;

import za.co.invoiceworx.entity.Account;
import za.co.invoiceworx.entity.Invoice;
import za.co.invoiceworx.entity.User;

/**
 *
 * @author F4657314
 */
public class SageConnector {
	
	public String debitFunderAndPaySupplier (User funder, User supplier, BigDecimal amount) {
		return null;
	}
    
   
    public String pay (Account fromAccount, Account toAccount, BigDecimal tranAmount) {
        // pay supplier 78% of total invoice.
        
        return null;
    }
}
