package za.co.invoiceworx.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class SecurityServlet extends IXServlet {

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

        if (action.equalsIgnoreCase("login")) {
            String emailAddress = request.getParameter("emailAddress");
            String password = request.getParameter("password");
            login(emailAddress, password);
        }
        
        if (action.equalsIgnoreCase("resetPassword")) {
        	redirect(NextPage.RESET_PASSWORD);
        }
        
        if (action.equalsIgnoreCase("reset")) {
            String username = request.getParameter("username");
            String newPassword = request.getParameter("newPassword");
            String oldPassword = request.getParameter("oldPassword");
            
            resetPassword(username, oldPassword, newPassword);
            
            session.setAttribute("success_msg", "You have successfully reset your password. Please login.");
            redirect(NextPage.LOGIN);
        }
        
        if (action.equalsIgnoreCase("logout")) {
        	logout(request, response);
        }
        
        
    }

    private void login(String username, String password) {
        try {
            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                session.setAttribute("error_msg", "Invalid input, please enter both email and password");
                redirect(NextPage.LOGIN);
            }

            User user = securityEJB.login(username, password, session.getId());

            if (user == null) {
                session.setAttribute("loggedIn", Boolean.FALSE);
                session.setAttribute("error_msg", "Login failed. Invalid username or password");
                redirect(NextPage.LOGIN);
                return;
            }
            session.setAttribute("user", user);
            session.setAttribute("loggedIn", Boolean.TRUE);
            session.setAttribute("user_id", user.getId());
            session.setAttribute("isAdmin", user.isAdmin());
            session.setAttribute("isFunder", user.isFunder());
            session.setAttribute("isSupplier", user.isSupplier());
            session.setAttribute("isBuyer", user.isClient());
            session.setAttribute("invoiceType", invoiceWorkflowEJB.getInvoiceStatuses(user));

            // prepare dashboard
            _prepareDashboard(user);
            
            if (user.isAdmin()) {
                session.setAttribute("form-title", "Admin Dashboard");
            }
            if (user.isFunder()) {
                session.setAttribute("form-title", "Funder Dashboard");
            }
            if (user.isSupplier()) {
                session.setAttribute("form-title", "Supplier Dashboard");
            }
            if (user.isClient()) {
                session.setAttribute("form-title", "Client Dashboard");
            }

            redirect(NextPage.MAIN_DASHBOARD);

        } catch (InvoiceWorXServiceException ex) {
            session.setAttribute("email", null);
            session.setAttribute("loggedIn", Boolean.FALSE);
            session.setAttribute("error_msg", "Login failed. Invalid email or password");
            redirect(NextPage.LOGIN);
        }
    }

    private void _prepareDashboard(User user) {
         session.setAttribute("knobs", null);
        
        
        session.setAttribute("tot-new-invoices", "0");
        session.setAttribute("tot-pendingverification-invoices", "0");
        session.setAttribute("tot-approved-invoices", "0");
        session.setAttribute("tot-onsale-invoices", "0");
        session.setAttribute("tot-sold-invoices", "0");
        session.setAttribute("tot-settled-invoices", "0");
        
        KnobContainer newInvoices = new KnobContainer();
        newInvoices.setLabel("New Invoices");
        newInvoices.setMaxValue("1500");
        newInvoices.setValue("34");
        
        KnobContainer approvedInvoices = new KnobContainer();
        approvedInvoices.setLabel("Approved Invoices");
        approvedInvoices.setMaxValue("1500");
        approvedInvoices.setValue("920");
        
        KnobContainer pendingVerificationInvoices = new KnobContainer();
        pendingVerificationInvoices.setLabel("Settled Invoices");
        pendingVerificationInvoices.setMaxValue("1500");
        pendingVerificationInvoices.setValue("920");
        
        KnobContainer onSaleInvoices = new KnobContainer();
        onSaleInvoices.setLabel("Settled Invoices");
        onSaleInvoices.setMaxValue("1500");
        onSaleInvoices.setValue("920");
        
        KnobContainer soldInvoices = new KnobContainer();
        soldInvoices.setLabel("Settled Invoices");
        soldInvoices.setMaxValue("1500");
        soldInvoices.setValue("920");
        
        KnobContainer settledInvoices = new KnobContainer();
        settledInvoices.setLabel("Settled Invoices");
        settledInvoices.setMaxValue("1500");
        settledInvoices.setValue("920");
        
        List<KnobContainer> knobs = new ArrayList<>();
        if (user.isAdmin() || user.isSupplier()) {
            knobs.add(newInvoices);
            knobs.add(pendingVerificationInvoices);
            knobs.add(approvedInvoices);
            knobs.add(onSaleInvoices);
            knobs.add(soldInvoices);
            knobs.add(settledInvoices);
            
        } else if( user.isClient() ) {
            knobs.add(newInvoices);
            knobs.add(approvedInvoices);
            knobs.add(settledInvoices);

        } else if( user.isFunder()) {
            knobs.add(onSaleInvoices);
            knobs.add(soldInvoices);
            knobs.add(settledInvoices);
        
        }
        session.setAttribute("knobs", knobs);
        
        
    }

    private void resetPassword(String email, String oldPassword, String newPassword) {
        try {
            Boolean success = securityEJB.changePassword(email, oldPassword, newPassword);

            if (!success) {
                session.setAttribute("password_reset_success", Boolean.FALSE);
                session.setAttribute("error_msg", "There was an error reseting password. Please try again later.");
            } else {
                session.setAttribute("success_msg", "You have successfully reset your password. Please login");
            }

            redirect(NextPage.RESET_PASSWORD);

        } catch (InvoiceWorXServiceException ex) {
            session.setAttribute("password_reset_success", Boolean.FALSE);
            session.setAttribute("error_msg", "There was an error reseting password. Please try again later.");
            redirectToRequestor();
        }
    }
    
    void logout(HttpServletRequest request,HttpServletResponse  resoponse){
    	
    	session.invalidate();
    	try {
			request.getRequestDispatcher("index.jsp").include(request, response);
			
			log.info("successfuly logged out...");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    }

}
