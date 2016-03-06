package za.co.invoiceworx.servlets;

import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import za.co.invoiceworx.dto.UserSearchCriteria;
import za.co.invoiceworx.ejb.UserEJB;
import za.co.invoiceworx.entity.Account;
import za.co.invoiceworx.entity.Address;
import za.co.invoiceworx.entity.Contact;
import za.co.invoiceworx.entity.Org;
import za.co.invoiceworx.entity.Person;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.entity.UserType;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;
import za.co.invoiceworx.util.DateUtil;

/**
 *
 * @author F4657314
 */
public class UserServlet extends IXServlet {

    @EJB
    private UserEJB userEJB;

    @Override
    protected void processRequest() throws InvoiceWorXServiceException{
        session.setAttribute("error_msg", "");

        if (action == null) {
            session.setAttribute("error_msg", "Invalid action was requested");
            redirect(NextPage.LOGIN);
            return;
        }

        if (action.equalsIgnoreCase("prepare-registration")) {
            redirect(NextPage.REGISTRATION);
        }

        if (action.equalsIgnoreCase("register")) {
            register(request, "Successfully registerd", true);

            session.setAttribute("success_msg", "You are successfully registered. Please login.");
            redirect(NextPage.LOGIN);
        }

        if (action.equalsIgnoreCase("prepare-maintainProfile")) {
            redirect(NextPage.MAINTAIN_PROFILE);
        }

        if (action.equalsIgnoreCase("maintainProfile")) {
            register(request, "Successfully maintained user", false);
        }

        if (action.equalsIgnoreCase("view_dashboard")) {
            viewDashboard();
            redirect(NextPage.MAIN_DASHBOARD);
        }

        if (action.equalsIgnoreCase("searchUser")) {
            session.setAttribute("userSearchResults", null);
            searchUser();
            redirect(NextPage.LIST_USERS);
        }

        if (action.equalsIgnoreCase("viewUser")) {
            viewUser();
            redirect(NextPage.MAINTAIN_PROFILE);
        }

        if (action.equalsIgnoreCase("viewPrincipalUser")) {
            session.setAttribute("selectedUser", getPrincipalUser());
            redirect(NextPage.MAINTAIN_PROFILE);
        }
    }

    private void register(HttpServletRequest request, String msg, boolean signUp) {

        User user = populateUser(request, signUp);

        try {
            userEJB.register(user);
            session.setAttribute("success_msg", msg);
            if (signUp) {
                redirect(NextPage.LOGIN);
            } else {
                redirect(NextPage.MAINTAIN_PROFILE);
            }

        } catch (InvoiceWorXServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private User populateUser(HttpServletRequest request, boolean signup) {

        User user;
        UserType userType;
        Org org;
        Account account;
        Address personAddress;
        Person person;
        Contact personContact;

        if (signup) {
            user = new User();
            userType = new UserType();
            userType.setRole(request.getParameter("userType"));
            org = new Org();
            account = new Account();
            personAddress = new Address();
            person = new Person();
            personContact = new Contact();

        } else {
            user = (User) session.getAttribute("user");
            userType = user.getUserType() != null ? user.getUserType() : new UserType();

            if (StringUtils.isBlank(userType.getRole())) {
                userType.setRole(request.getParameter("userType"));
            }

            org = user.getOrg();
            account = user.getAccount();

            if (!(user.getPerson().getAddress() == null)) {
                personAddress = user.getPerson().getAddress();
            } else {
                personAddress = new Address();
            }

            person = user.getPerson();
            personContact = user.getPerson().getContact();
        }

        person.setFname(request.getParameter("fname"));
        person.setGender(request.getParameter("gender"));
        person.setIdNumber(request.getParameter("idNum"));
        person.setLname(request.getParameter("lname"));
        person.setMname(request.getParameter("mname"));
        person.setPassport(request.getParameter("passport"));
        person.setRace(request.getParameter("race"));
        person.setTitle(request.getParameter("title"));

        personContact.setCellphone(request.getParameter("cellphone"));
        personContact.setEmail(request.getParameter("email"));
        personContact.setHomephone(request.getParameter("homephone"));
        personContact.setWebsite(request.getParameter("website"));
        personContact.setWorkphone(request.getParameter("workphone"));

        person.setContact(personContact);

        personAddress.setCity(request.getParameter("city"));
        personAddress.setLine1(request.getParameter("line1"));
        personAddress.setPostalCode(request.getParameter("postal_code"));
        personAddress.setProvince(request.getParameter("province"));
        personAddress.setSurburb(request.getParameter("surburb"));

        person.setAddress(personAddress);

        account.setAccountHolder(request.getParameter("accountHolder"));
        account.setAccountNumber(request.getParameter("accNumber"));
        account.setAccountType(request.getParameter("accType"));
        account.setBank(request.getParameter("bank"));
        account.setBranch(request.getParameter("branch"));
        account.setBranchCode(request.getParameter("branchCode"));

        user.setAccount(account);

        org.setOrgName(request.getParameter("org_name"));
        org.setOrgRegDate(DateUtil.toDate(request.getParameter("org_reg_date"), "dd-MM-yyyy"));
        org.setOrgRegNum(request.getParameter("org_reg_num"));

        //continue populate user object
        user.setPerson(person);
        user.setUserType(userType);
        user.setActive(true);
        user.setCreatedTs(DateUtil.newDate());
        user.setExpiryTs(DateUtil.newDate());
        user.setOrg(org);
        user.setPassword(request.getParameter("password"));
        user.setUsername(request.getParameter("username"));
        return user;
    }

    private void viewDashboard() {
        Long userId = Long.valueOf(request.getParameter("user_id"));
    }

    private void searchUser() {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String idNumber = request.getParameter("idNumber");
        String username = request.getParameter("username");
        String userType = request.getParameter("userType");
        String cellphone = request.getParameter("cellphone");
        String emailAddress = request.getParameter("emailAddress");
        String orgName = request.getParameter("orgName");

        UserSearchCriteria criteria = new UserSearchCriteria();
        criteria.setFirstName(firstName);
        criteria.setLastName(lastName);
        criteria.setIdNumber(idNumber);
        criteria.setUsername(username);
        criteria.setUserType(userType);
        criteria.setCellphone(cellphone);
        criteria.setEmailAddress(emailAddress);
        criteria.setOrganizationName(orgName);

        List<User> users = userEJB.search(criteria);
        session.setAttribute("userSearchResults", users);
    }

    public void viewUser() throws InvoiceWorXServiceException {
        Long userId = Long.valueOf(request.getParameter("userId"));
        User user = userEJB.getUser(userId);
        session.setAttribute("selectedUser", user);
    }

}
