package za.co.invoiceworx.servlets;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import za.co.invoiceworx.dto.InvoiceSearchCriteria;
import za.co.invoiceworx.dto.UserSearchCriteria;
import za.co.invoiceworx.ejb.InvoiceWorkflowEJB;
import za.co.invoiceworx.ejb.UserEJB;
import za.co.invoiceworx.entity.Invoice;
import za.co.invoiceworx.entity.InvoiceItem;
import za.co.invoiceworx.entity.User;
import za.co.invoiceworx.entity.UserType;
import za.co.invoiceworx.exception.InvoiceWorXServiceException;
import za.co.invoiceworx.util.DateUtil;

/**
 *
 * @author F4657314
 */
public class InvoiceServlet extends IXServlet {

	private final Logger log = Logger.getLogger(InvoiceServlet.class);

	@EJB
	private UserEJB userEJB;

	@EJB
	private InvoiceWorkflowEJB invoiceWorkflowEJB;

	@Override
	protected void processRequest() {
		session.setAttribute("error_msg", "");

		if (action == null) {
			session.setAttribute("error_msg", "Invalid action was requested");
			redirectToRequestor();
			return;
		}

		if (action.equalsIgnoreCase("search_invoice")) {
			findInvoices();
			redirect(NextPage.LIST_INVOICES);
		}

		if (action.equalsIgnoreCase("create_invoice")) {
			createInvoice();
			redirect(NextPage.CREATE_INVOICE);
		}

		if (action.equalsIgnoreCase("prepare_invoice")) {
			prepareCreateInvoice();
			redirect(NextPage.CREATE_INVOICE);
		}

		if (action.equalsIgnoreCase("view_invoice")) {

			Invoice invoice = invoiceWorkflowEJB.findInvoice(request
					.getParameter("invRefNumber"));

			session.setAttribute("invoice", invoice);

			redirect(NextPage.VIEW_INVOICE);
		}

		if (action.equalsIgnoreCase("select_inv_status")) {
			// populate status
			redirect(NextPage.SELECT_INVOICE_STATUS);
		}

		if (action.equalsIgnoreCase("list_invoices")) {
			listInvoices();
			redirect(NextPage.LIST_INVOICES);
		}
		
		
		if (action.equalsIgnoreCase("Pay Now")) {
			String invRef = getInvoiceRefFromRequest();
			payNow(invRef,getPrincipalUser().getId());
			redirect(NextPage.LIST_INVOICES);
		}
		
		if (action.equalsIgnoreCase("Buy")) {
			String invRef = getInvoiceRefFromRequest();
			buy(invRef,getPrincipalUser().getId());
			redirect(NextPage.LIST_INVOICES);
		}
		
		if (action.equalsIgnoreCase("Sell")) {
			String invRef = getInvoiceRefFromRequest();
			
			sell(invRef,getPrincipalUser().getId());
			
			redirect(NextPage.LIST_INVOICES);
		}
		
		if (action.equalsIgnoreCase("Verify")) {
			String invRef = getInvoiceRefFromRequest();
			verify(invRef,getPrincipalUser().getId());
			redirect(NextPage.LIST_INVOICES);
		}
		
		if (action.equalsIgnoreCase("Approve")) {
			String invRef = getInvoiceRefFromRequest();
			approve(invRef,getPrincipalUser().getId());
			redirect(NextPage.LIST_INVOICES);
		}
		
		if (action.equalsIgnoreCase("Reject")) {
			String invRef = getInvoiceRefFromRequest();
			reject(invRef,getPrincipalUser().getId());
			redirect(NextPage.LIST_INVOICES);
		}
		if (action.equalsIgnoreCase("Confirmation")) {			
			
			String invRef = getInvoiceRefFromRequest();
			UserType userType = new UserType();			
			userType.setRole(request.getParameter("paymentConfirmationUserType"));
			
			String amount = request.getParameter("paidAmount");
			
			if(amount ==null){
				session.setAttribute("error_msg", "Amount can not be null");
				return;
			}
			BigDecimal amountToConfirm = new BigDecimal(amount);
			
			confirmPaymentAmount(invRef, amountToConfirm,getPrincipalUser().getId(), userType);
			redirect(NextPage.LIST_INVOICES);
		}


	}

	private void approve(String invRef, Long id) {
		try {
			invoiceWorkflowEJB.authoriseInvoice(invRef, id,true);
                        session.setAttribute("success_msg", "Invoice ["+invRef+"] is successfully APPROVED.");
		} catch (InvoiceWorXServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		private void reject(String invRef, Long id) {
		try {
			invoiceWorkflowEJB.authoriseInvoice(invRef, id,false);
                        session.setAttribute("success_msg", "Invoice ["+invRef+"] is successfully REJECTED.");
		} catch (InvoiceWorXServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void verify(String invRef, Long id) {
		try {
			invoiceWorkflowEJB.verifyInvoice(invRef, id);
                        session.setAttribute("success_msg", "Invoice ["+invRef+"] is successfully VERIFIED.");
		} catch (InvoiceWorXServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void sell(String invRef, Long id) {
		
		try {
			invoiceWorkflowEJB.sellInvoice(invRef, id);
                        session.setAttribute("success_msg", "You have successfully sold invoice ["+invRef+"].");
		} catch (InvoiceWorXServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void buy(String invRef, Long id) {

		try {
			invoiceWorkflowEJB.buyInvoice(invRef, id);
            session.setAttribute("success_msg", "You have successfully bought invoice ["+invRef+"].");
		} catch (InvoiceWorXServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void payNow(String invRef, Long id) {
		/*try {
			invoiceWorkflowEJB.paySupplier(invRef, getPrincipalUser());
		} catch (InvoiceWorXServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	private void findInvoices() {
		String firstName = request.getParameter("firstName");
		String secondName = request.getParameter("firstName");
		String surname = request.getParameter("firstName");
		String idNumber = request.getParameter("idNumber");
		String id = request.getParameter("id");
		String invoiceRef = request.getParameter("invoiceRef");
		String byClient = request.getParameter("client");
		String bySupplier = request.getParameter("supplier");
		String byFunder = request.getParameter("funder");
		String sFrom = request.getParameter("from");
		String sTo = request.getParameter("to");

		InvoiceSearchCriteria criteria = new InvoiceSearchCriteria();
		criteria.setFirstName(firstName);
		criteria.setSecondName(secondName);
		criteria.setSurname(surname);
		criteria.setInvoiceRef(invoiceRef);
		if (!StringUtils.isBlank(idNumber)) {
			criteria.setIdNumber(Long.valueOf(idNumber));
		}
		if (!StringUtils.isBlank(id)) {
			criteria.setId(Long.valueOf(id));
		}
		if (!StringUtils.isBlank(byClient)) {
			criteria.setSearchByApprover(Boolean.valueOf(byClient));
		}
		if (!StringUtils.isBlank(bySupplier)) {
			criteria.setSearchByCreator(Boolean.valueOf(bySupplier));
		}
		if (!StringUtils.isBlank(byFunder)) {
			criteria.setSearchByFunder(Boolean.valueOf(byFunder));
		}
		if (!StringUtils.isBlank(sFrom)) {
			criteria.setFrom(Date.valueOf(sFrom));
		}
		if (!StringUtils.isBlank(sTo)) {
			criteria.setTo(Date.valueOf(sTo));
		}

		List<Invoice> invoices = invoiceWorkflowEJB.findInvoices(criteria);

		session.setAttribute("invoices", invoices);

	}

	private void prepareCreateInvoice() {
		log.info("prepareCreateInvoice");
		try {
			User user = getPrincipalUser();

			session.setAttribute("allClients", userEJB.findAllClients());

			if (user.isAdmin()) {
				session.setAttribute("allSuppliers", userEJB.findAllSuppliers());
			}

		} catch (InvoiceWorXServiceException ex) {
			log.error("Exception " + ex.getMessage(), ex);
		}
	}

	private void createInvoice() {
		session.setAttribute("error_msg", null);

		try {
			User user = getPrincipalUser();

			if (user == null) {
				log.warn("You need to be logged to perform this action");
				session.setAttribute("error_msg",
						"You need to be logged to perform this action.");
				return;
			}

			if (!user.isAdmin() && !user.isSupplier()) {
				log.warn("Only supplier of Administrator can create invoice");
				session.setAttribute("error_msg",
						"Only supplier of Administrator can create invoice.");
				return;
			}

			Long adminId = null;
			Long supplierId = user.getId();

			if (user.isAdmin()) {
				adminId = user.getId();
				supplierId = Long.valueOf(request.getParameter("supplierId"));
			}

			invoiceWorkflowEJB.createInvoice(
					populateInvoice(userEJB.getUser(supplierId)), supplierId,
					adminId);
			session.setAttribute("success_msg", "Successfully created invoice");

		} catch (InvoiceWorXServiceException ex) {
			java.util.logging.Logger.getLogger(InvoiceServlet.class.getName())
					.log(Level.SEVERE, null, ex);
		}

	}

	private Invoice populateInvoice(User creator) {
		try {
			Invoice invoice = new Invoice();

			String totInvAmt = request.getParameter("totalInvoiceAmt");
			String lnCount = request.getParameter("lineCounter");
			String client = request.getParameter("clientId");

			log.info("totInvAmt : " + totInvAmt);
			log.info("lnCount : " + lnCount);
			log.info("client : " + client);

			Long clientId = Long.valueOf(client);
			Double totalInvAmnt = Double.valueOf(totInvAmt);
			Integer lineCounter = Integer.valueOf(lnCount);

			invoice.setApprovedBy(userEJB.getUser(clientId));
			invoice.setInvAmount(BigDecimal.valueOf(totalInvAmnt));

			invoice.setCreatedBy(creator);
			invoice.setCreatedTs(DateUtil.newDate());

			log.info("line counter :" + lineCounter);

			for (int i = 1; i <= lineCounter; i++) {
				InvoiceItem item = new InvoiceItem();
				log.info("desc" + i + " : "
						+ request.getParameter("description" + i));
				log.info("quantity" + i + " : "
						+ request.getParameter("quantity" + i));
				log.info("unitPrice" + i + " : "
						+ request.getParameter("unitPrice" + i));
				log.info("lineTotal" + i + " : "
						+ request.getParameter("lineTotal" + i));
				log.info("invDate" + i + " : "
						+ request.getParameter("invDate" + i));

				item.setDescription(request.getParameter("description" + i));
				item.setQuantity(Integer.valueOf(request.getParameter("quantity" + i)));
				item.setUnitPrice(new BigDecimal(request.getParameter("unitPrice" + i)));
				item.setVatAmount(new BigDecimal(request.getParameter("lineTotal" + i)));
				item.setLineDate(DateUtil.toDate(request.getParameter("invDate"+ i), "dd/MM/2015"));
				invoice.addItem(item);
			}

			return invoice;
		} catch (InvoiceWorXServiceException ex) {
			java.util.logging.Logger.getLogger(InvoiceServlet.class.getName())
					.log(Level.SEVERE, null, ex);
		}
		return null;
	}



	private void listInvoices() {
		String statusType = request.getParameter("invoiceStatusType")
				.toString();
		List<Invoice> invoicesByStatusType = invoiceWorkflowEJB
				.findInvoicesByStatusType(statusType);

		session.setAttribute("invoicesByStatusType", invoicesByStatusType);
		
		String confirmPaymentForUserTpye = request.getParameter("userType");
		
		session.setAttribute("paymentConfirmationUserType", confirmPaymentForUserTpye);

	}


	
	public String getInvoiceRefFromRequest(){
		String invRef = request.getParameter("invoiceRef");
		
		return invRef !=null ? invRef :"Invoice reference not found";
	}
	
	void confirmPaymentAmount(String invRef, BigDecimal amount, Long adminUserId, UserType userTypeToBeConfirmed){
		try {
			invoiceWorkflowEJB.confirmPaymentAmount(invRef, adminUserId, userTypeToBeConfirmed, amount);
		} catch (InvoiceWorXServiceException e) {
			e.printStackTrace();
		}
	}

}
