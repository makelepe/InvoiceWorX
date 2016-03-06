package za.co.invoiceworx.servlets;

import java.io.IOException;
import java.util.logging.Level;
import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import za.co.invoiceworx.common.ExceptionHandler;

import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;

/**
 *
 * @author F4657314
 */
public abstract class IXServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(IXServlet.class);

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected String action;

    @Inject
    private ExceptionHandler handler;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            request = req;
            response = res;
            action = request.getParameter("action");
            session = request.getSession();
            log.info("Action = " + action);
            log.info("Servlet path = " + request.getServletPath());

            processRequest();
        } catch (InvoiceWorXServiceException ex) {
            handler.handle(session, ex);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            request = req;
            response = res;
            action = request.getParameter("action");
            session = request.getSession();
            log.info("Action = " + action);
            log.info("Servlet path = " + request.getServletPath());

            processRequest();
        } catch (InvoiceWorXServiceException ex) {
            handler.handle(session,ex);
        }
    }

    protected void redirectToRequestor() {
        try {
            request.getRequestDispatcher(request.getServletPath()).forward(request, response);
        } catch (ServletException | IOException ex) {
            session.setAttribute("username", null);
            session.setAttribute("loggedIn", Boolean.FALSE);
            session.setAttribute("error_msg", "Error redirecting to : " + request.getServletPath());
        }
    }

    protected void redirect(NextPage page) {
        setNextPath(page);
        try {

            switch (page) {
                case LOGIN:
                case REGISTRATION:
                case RESET_PASSWORD_OUTOF_LOGIN:
                case FORGOT_PASSWORD:
                    request.getRequestDispatcher(page.getPageName()).forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("pages/main.jsp").forward(request, response);
            }

        } catch (ServletException | IOException ex) {
            session.setAttribute("username", null);
            session.setAttribute("loggedIn", Boolean.FALSE);
            session.setAttribute("error_msg", "Error redirecting to : " + page.getPageName());
            
        }
    }

    protected enum NextPage {

        LOGIN("index.jsp"),
        REGISTRATION("sign-up.jsp"),
        FORGOT_PASSWORD("forgot-password.jsp"),
        /**
         * all dashboard pages
         */
        SEARCH_USER("searchUser"),
        LIST_USERS("listUsers"),
        VIEW_USER("viewUser"),
        MAIN_DASHBOARD("mainDashboard"),
        TODO_LIST("todoList"),
        AUTHORIZE_INVOICE("authorizeInvoice"),
        BUY_INVOICE("buyInvoice"),
        CREATE_INVOICE("createInvoice"),
        LIST_INVOICES("listInvoices"),
        SEARCH_INVOICE("searchInvoice"),
        SELL_INVOICE("sellInvoice"),
        VERIFY_INVOICE("verifyInvoice"),
        VIEW_INVOICE("viewInvoice"),
        SELECT_INVOICE_STATUS("selectInvoiceStatus"),
        PRIVACY_POLICY("privacyPolicy"),
        TERMS_AND_CONDITIONS("termsAndConditions"),
        CONTACT_INFO("contactInfo"),
        FAQs("faqs"),
        HELP("help"),
        PAYNOW("paynow"),
        CONFIRM_CLIENT_SETTLEMENT("confirmClientSettlement"),
        CONFIRM_FUNDER_PAYMENT("confirmFunderPayment"),
        CONFIRM_SUPPLIER_PAYMENT("confirmSupplierPayment"),
        RESET_PASSWORD_OUTOF_LOGIN("/pages/profile/reset-password.jsp"),
        RESET_PASSWORD("resetPassword"),
        MAINTAIN_PROFILE("maintainProfile"),
        UPDATE_USER_DETAILS("updateUserDetails");

        private final String pageName;

        NextPage(String pageName) {
            this.pageName = pageName;
        }

        public String getPageName() {
            return pageName;
        }
    }

    private void setNextPath(NextPage nextPage) {
        for (NextPage page : NextPage.values()) {
            session.setAttribute(page.getPageName(), false);
        }
        session.setAttribute(nextPage.getPageName(), true);
    }

    public User getPrincipalUser() {
        User user = (User) session.getAttribute("user");
        return user;
    }

    protected abstract void processRequest() throws InvoiceWorXServiceException;
}
