package za.co.invoiceworx.servlets;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import za.co.invoiceworx.dto.UserSearchCriteria;
import za.co.invoiceworx.ejb.InvoiceWorkflowEJB;
import za.co.invoiceworx.ejb.UserEJB;
import za.co.invoiceworx.util.DateUtil;

/**
 *
 * @author F4657314
 */
public class InvoiceServlet extends IXServlet  {

    @EJB
    private UserEJB userEJB;
    
     @EJB
    private InvoiceWorkflowEJB invoiceWorkflowEJB;

    @Override
    protected void processRequest() {
        session.setAttribute("error_msg", "");

        if (action == null) {
            session.setAttribute("error_msg", "Invalid action was requested");
            redirect(request.getServletPath());
            return;
        }

        if (action.equalsIgnoreCase("register")) {
           // String username = request.getParameter("username");
           // String password = request.getParameter("password");
            register();

            session.setAttribute("success_msg", "You are successfully registered. Please login.");
            redirect("login.jsp");
        }

        if (action.equalsIgnoreCase("find_users")) {
            UserSearchCriteria criteria = new UserSearchCriteria();
            criteria.setFirstName(request.getParameter("first_name"));
            criteria.setSecondName(request.getParameter("second_name"));
            criteria.setSurname(request.getParameter("surname"));
            criteria.setInvoiceRef(request.getParameter("invoice_ref"));
            criteria.setSearchByApprover(Boolean.valueOf(request.getParameter("by_buyer_type")));
            criteria.setSearchByCreator(Boolean.valueOf(request.getParameter("by_supplier_type")));
            criteria.setSearchByFunder(Boolean.valueOf(request.getParameter("by_funder_type")));
            criteria.setIdNumber(Long.valueOf(request.getParameter("id_number")));

            criteria.setFrom(DateUtil.toDate(request.getParameter("from_date"), DateUtil.yyyy_MM_dd_HH_mm_ss));
            criteria.setTo(DateUtil.toDate(request.getParameter("to_date"), DateUtil.yyyy_MM_dd_HH_mm_ss));

            findUsers(criteria);

            redirect("pages/dashboard.jsp");
        }

        if (action.equalsIgnoreCase("get_user")) {
            String userId = request.getParameter("user_id");
            register();
        }

        if (action.equalsIgnoreCase("get_user_username")) {
            String username = request.getParameter("username");
            
        }

        if (action.equalsIgnoreCase("disable_user")) {
            String userId = request.getParameter("user_id");
            register();
        }

        if (action.equalsIgnoreCase("enable_user")) {
            String userId = request.getParameter("user_id");
            register();
        }
    }

    private void register() {
        session.setAttribute("action_results", "view_users");
        session.setAttribute("action_results_value", "view_users");

    }

    private void findUsers(UserSearchCriteria searchCriteria) {
        session.setAttribute("action_results", "view_users");
        session.setAttribute("action_results_value", "view_users");

    }

    private void getUserById(String userId) {
        session.setAttribute("action_results", "view_users");
        session.setAttribute("action_results_value", "view_users");

    }

    private void getUserByUsername(String username) {
        session.setAttribute("action_results", "view_users");
        session.setAttribute("action_results_value", "view_users");

    }

    private void disableUser(String userId) {
        session.setAttribute("action_results", "view_users");
        session.setAttribute("action_results_value", "view_users");

    }

    private void enableUser(String userId) {
        session.setAttribute("action_results", "view_users");
        session.setAttribute("action_results_value", "view_users");

    }
}
