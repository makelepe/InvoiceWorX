package za.co.invoiceworx.ejb;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import za.co.invoiceworx.repository.InvoiceRepository;

/**
 *
 * @author F4657314
 */
@Singleton
public class SchedulerEJB {

    private final Logger log = Logger.getLogger(SchedulerEJB.class);

    @Inject
    private InvoiceRepository invoiceRepo;

    @Schedule(year = "*", month = "*", dayOfWeek = "*", hour = "20", minute = "0", second = "0")
    public void debitCorporates() {

    }

    @Schedule(year = "*", month = "*", dayOfWeek = "*", hour = "22", minute = "0", second = "0")
    public void payFunders() {

    }

    @Schedule(year = "*", month = "*", dayOfWeek = "*", hour = "23", minute = "59", second = "59")
    public void settleInvoices() {

    }

}
