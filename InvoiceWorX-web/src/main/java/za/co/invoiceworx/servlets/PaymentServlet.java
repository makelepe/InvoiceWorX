package za.co.invoiceworx.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import za.co.invoiceworx.util.KnobContainer;
import za.co.invoiceworx.ejb.InvoiceWorkflowEJB;
import za.co.invoiceworx.ejb.SecurityEJB;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;
import za.co.invoiceworx.servlets.IXServlet.NextPage;

/**
 *
 * @author F4657314
 */
public class PaymentServlet extends IXServlet {

    private final Logger log = Logger.getLogger(IXServlet.class);

    @EJB
    private SecurityEJB securityEJB;

    @EJB
    private InvoiceWorkflowEJB invoiceWorkflowEJB;

    @Override
    protected void processRequest() {
        session.setAttribute("error_msg", "");

        if (action == null) {
            session.setAttribute("error_msg", "Invalid state, Please try again.");
            redirectToRequestor();
            return;
        }

        if (action.equalsIgnoreCase("paynow")) {
            redirect(NextPage.PAYNOW);
        }

        if (action.equalsIgnoreCase("confirmClientPayment")) {
            redirect(NextPage.CONFIRM_CLIENT_SETTLEMENT);
        }
        
        if (action.equalsIgnoreCase("confirmSupplierPayReceipt")) {
            redirect(NextPage.CONFIRM_SUPPLIER_PAYMENT);
        }
        
        if (action.equalsIgnoreCase("confirmFunderPayReceipt")) {
            redirect(NextPage.CONFIRM_FUNDER_PAYMENT);
        }
    }

   }
