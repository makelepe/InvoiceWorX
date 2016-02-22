package za.co.invoiceworx.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author F4657314
 */
public abstract class IXServlet extends HttpServlet {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected String action;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        request = req;
        response = res;
        action = request.getParameter("action");
        session = request.getSession();

        processRequest();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        request = req;
        response = res;
        action = request.getParameter("action");
        session = request.getSession();

        processRequest();
    }

    protected void redirect(String page) {
        try {
            request.getRequestDispatcher(page).forward(request, response);

        } catch (ServletException | IOException ex) {
            session.setAttribute("username", null);
            session.setAttribute("loggedIn", Boolean.FALSE);
            session.setAttribute("error_msg", "Error redirecting to : " + page);
        }
    }

    protected abstract void processRequest();
}
