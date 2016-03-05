<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
    boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    boolean isSupplier = (Boolean) session.getAttribute("isSupplier");
    boolean isFunder = (Boolean) session.getAttribute("isFunder");
    boolean isBuyer = (Boolean) session.getAttribute("isBuyer");

    Boolean mainDashboard = (Boolean) session.getAttribute("mainDashboard");
    Boolean todoList = (Boolean) session.getAttribute("todoList");
    Boolean authorizeInvoice = (Boolean) session.getAttribute("authorizeInvoice");
    Boolean buyInvoice = (Boolean) session.getAttribute("buyInvoice");
    Boolean createInvoice = (Boolean) session.getAttribute("createInvoice");
    Boolean listInvoices = (Boolean) session.getAttribute("listInvoices");
    Boolean searchInvoice = (Boolean) session.getAttribute("searchInvoice");
    Boolean sellInvoice = (Boolean) session.getAttribute("sellInvoice");
    Boolean verifyInvoice = (Boolean) session.getAttribute("verifyInvoice");
    Boolean viewInvoice = (Boolean) session.getAttribute("viewInvoice");
    Boolean privacyPolicy = (Boolean) session.getAttribute("privacyPolicy");
    Boolean termsAndConditions = (Boolean) session.getAttribute("termsAndConditions");
    Boolean faqs = (Boolean) session.getAttribute("faqs");
    Boolean help = (Boolean) session.getAttribute("help");
    Boolean contactInfo = (Boolean) session.getAttribute("contactInfo");
    Boolean confirmClientSettlement = (Boolean) session.getAttribute("confirmClientSettlement");
    Boolean confirmFunderPayment = (Boolean) session.getAttribute("confirmFunderPayment");
    Boolean confirmSupplierPayment = (Boolean) session.getAttribute("confirmSupplierPayment");
    Boolean resetPassword = (Boolean) session.getAttribute("resetPassword");
    Boolean maintainProfile = (Boolean) session.getAttribute("maintainProfile");
    Boolean selectInvoiceStatus = (Boolean) session.getAttribute("selectInvoiceStatus");

%>


<div class="content">
    <div class="header">
        <div class="stats">
            <%if (isAdmin) {%>
                <p class="stat"><span class="label label-success"><%=session.getAttribute("tot-new-invoices")%></span> New Invoices</p>
                <p class="stat"><span class="label label-danger"><%=session.getAttribute("tot-approved-invoices")%></span> Approved Invoices</p>
                <p class="stat"><span class="label label-info"><%=session.getAttribute("tot-pendingverification-invoices")%></span> Pending Verification Invoices</p>
                <p class="stat"><span class="label label-info"><%=session.getAttribute("tot-onsale-invoices")%></span> On-Sale Invoices</p>
                <p class="stat"><span class="label label-success"><%=session.getAttribute("tot-sold-invoices")%></span> Sold Invoices</p>
                <p class="stat"><span class="label label-danger"><%=session.getAttribute("tot-settled-invoices")%></span> Settled Invoices</p>
            <%} else if (isFunder) {%>
                <p class="stat"><span class="label label-success"><%=session.getAttribute("tot-onsale-invoices")%></span> On-Sale Invoices</p>
                <p class="stat"><span class="label label-danger"><%=session.getAttribute("tot-sold-invoices")%></span> Sold Invoices</p>
                <p class="stat"><span class="label label-info"><%=session.getAttribute("tot-settled-invoices")%></span> Settled Invoices</p>
            <%} else if (isSupplier) {%>
                <p class="stat"><span class="label label-success"><%=session.getAttribute("tot-new-invoices")%></span> New Invoices</p>
                <p class="stat"><span class="label label-danger"><%=session.getAttribute("tot-approved-invoices")%></span> Approved Invoices</p>
                <p class="stat"><span class="label label-info"><%=session.getAttribute("tot-pendingverification-invoices")%></span> Pending Verification Invoices</p>
                <p class="stat"><span class="label label-info"><%=session.getAttribute("tot-onsale-invoices")%></span> On-Sale Invoices</p>
                <p class="stat"><span class="label label-success"><%=session.getAttribute("tot-sold-invoices")%></span> Sold Invoices</p>
            <%} else if (isBuyer) { %>
                <p class="stat"><span class="label label-success"><%=session.getAttribute("tot-new-invoices")%></span> New Invoices</p>
                <p class="stat"><span class="label label-danger"><%=session.getAttribute("tot-approved-invoices")%></span> Approved Invoices</p>
                <p class="stat"><span class="label label-info"><%=session.getAttribute("tot-settled-invoices")%></span> Settled Invoices</p>
            <%}%>
        </div>

        <h1 class="page-title"><%=(String) session.getAttribute("form-title")%></h1>
        <ul class="breadcrumb">
            <li><a href="main.html">Home</a> </li>
            <!--li class="active">Dashboard</li>
        </ul>
    </div-->


            <%
                if (mainDashboard) {
                    %>
                    <li class="active">Dashboard</li></ul> </div>
                    <jsp:include page="dashboard/dashboard.jsp"/>
                    <%
                } else if (todoList) {
                    %>
                    <li class="active">Todo</li></ul> </div>
                    <jsp:include page="dashboard/todo.jsp"/>
                    <%
                } else if (authorizeInvoice) {
                    %>
                    <li class="active">Authorize Invoice</li></ul> </div>
                    <jsp:include page="invoice/authorize-invoice.jsp"/>
                    <%
                } else if (buyInvoice) {
                    %>
                    <li class="active">Buy Invoice</li></ul> </div>
                    <jsp:include page="invoice/buy-invoice.jsp"/>
                    <%
                } else if (createInvoice) {
                    %>
                    <li class="active">Create Invoice</li></ul> </div>
                    <jsp:include page="invoice/create-invoice.jsp"/>
                    <%
                } else if (listInvoices) {
                    %>
                    <li class="active">Invoice list</li></ul> </div>
                    <jsp:include page="invoice/list-invoices.jsp"/>
                    <%
                } else if (searchInvoice) {
                    %>
                    <li class="active">Search Invoice</li></ul> </div>
                    <jsp:include page="invoice/search-invoice.jsp"/>
                    <%
                } else if (sellInvoice) {
                    %>
                    <li class="active">Sell Invoice</li></ul> </div>
                    <jsp:include page="invoice/sell-invoice.jsp"/>
                    <%
                } else if (verifyInvoice) {
                    %>
                    <li class="active">Verify Invoice</li></ul> </div>
                    <jsp:include page="invoice/verify-invoice.jsp"/>
                    <%
                } else if (viewInvoice) {
                    %>
                    <li class="active">View Invoice</li></ul> </div>
                    <jsp:include page="invoice/view-invoice.jsp"/>
                    <%
                } else if (privacyPolicy) {
                    %>
                    <li class="active">Privacy Policy</li></ul> </div>
                    <jsp:include page="legal/privacy-policy.jsp"/>
                    <%
                } else if (termsAndConditions) {
                    %>
                    <li class="active">Terms And Conditions</li></ul> </div>
                    <jsp:include page="legal/terms-and-conditions.jsp"/>
                    <%
                } else if (faqs) {
                    %>
                    <li class="active">FAQs</li></ul> </div>
                    <jsp:include page="other/faqs.jsp"/>
                    <%
                } else if (help) {
                    %>
                    <li class="active">Help</li></ul> </div>
                    <jsp:include page="other/help.jsp"/>
                    <%
                } else if (contactInfo) {
                    %>
                    <li class="active">Contact Information</li></ul> </div>
                    <jsp:include page="other/contact-info.jsp"/>
                    <%
                } else if (confirmClientSettlement) {
                    %>
                    <li class="active">Confirm Client Settlement</li></ul> </div>
                    <jsp:include page="payment/confirm-client-settlement.jsp"/>
                    <%
                } else if (confirmFunderPayment) {
                    %>
                    <li class="active">Confirm Funder Payment</li></ul> </div>
                    <jsp:include page="payment/confirm-funder-payment.jsp"/>
                    <%
                } else if (confirmSupplierPayment) {
                    %>
                    <li class="active">Confirm Supplier Payment</li></ul> </div>
                    <jsp:include page="payment/confirm-supplier-payment.jsp"/>
                    <%
                } else if (resetPassword) {
                    %>
                    <li class="active">Reset Password</li></ul> </div>
                    <jsp:include page="profile/reset-password.jsp"/>
                    <%
                } else if (maintainProfile) {
                   %>
                    <li class="active">Maintain profile</li></ul> </div>
                    <jsp:include page="profile/maintain-profile.jsp"/>
                    <%
                } else if (selectInvoiceStatus) {
                   %>
                    <li class="active">Select Invoice status type</li></ul> </div>
                    <jsp:include page="invoice/selectInvoiceStatus.jsp"/>
                    <%
                }else{
                    %>
                    <li class="active">Dashboard</li></ul> </div>
                    <jsp:include page="dashboard/dashboard.jsp"/>
                    <%
                }
            %>

            <jsp:include page="footer.jsp" />
    </div>