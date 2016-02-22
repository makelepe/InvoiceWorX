package za.co.invoiceworx.servlets;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import org.apache.commons.lang.StringUtils;
import za.co.invoiceworx.ejb.SecurityEJB;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;

/**
 *
 * @author F4657314
 */

public class SecurityServlet extends IXServlet {

    @EJB
    private SecurityEJB securityEJB;

    @Override
    protected void processRequest() {
        session.setAttribute("error_msg", "");

        if (action == null) {
             session.setAttribute("error_msg", "Invalid state, Please try again.");
            redirect("index.jsp");
            return;
        }

        if (action.equalsIgnoreCase("login")) {
            String emailAddress = request.getParameter("Email Address");
            String password = request.getParameter("password");
            login(emailAddress, password);
        }
    }

    private void login(String emailAddress, String password) {
        try {
            if (StringUtils.isBlank(emailAddress) || StringUtils.isBlank(password)) {
                session.setAttribute("error_msg", "Invalid input, please enter both email and password");
                redirect("login.jsp");
            }
            
            User user = securityEJB.login(emailAddress, password, session.getId());

            if (user == null) {
                session.setAttribute("loggedIn", Boolean.FALSE);
                session.setAttribute("error_msg", "Login failed. Invalid username or password");
                redirect("login.jsp");
            }
            session.setAttribute("user", user);
            session.setAttribute("loggedIn", Boolean.TRUE);

            redirect("pages/dashboard.jsp");

        } catch (InvoiceWorXServiceException ex) {
            session.setAttribute("email", null);
            session.setAttribute("loggedIn", Boolean.FALSE);
            session.setAttribute("error_msg", "Login failed. Invalid email or password");
            redirect("login.jsp");
        }
    }

    private void resetPassword(String email, String oldPassword, String newPassword) {
        try {
            Boolean success = securityEJB.changePassword(email, oldPassword, newPassword);

            if (!success) {
                session.setAttribute("password_reset_success", Boolean.FALSE);
                session.setAttribute("error_msg", "There was an error reseting password. Please try again later.");
                redirect("forgot-password.jsp");
            }
            
            session.setAttribute("success_msg", "Your temporary password has been sent to xx**@**.com. Please use link in your email to reset your password..");
            redirect("pages/dashboard.jsp");

        } catch (InvoiceWorXServiceException ex) {
            session.setAttribute("password_reset_success", Boolean.FALSE);
            session.setAttribute("error_msg", "There was an error reseting password. Please try again later.");
            redirect("forgot-password.jsp");
        }
    }

}
