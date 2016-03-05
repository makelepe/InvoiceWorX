<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="za.co.invoiceworx.entity.*"%>
<%

    boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    boolean isSupplier = (Boolean) session.getAttribute("isSupplier");
    boolean isFunder = (Boolean) session.getAttribute("isFunder");
   // boolean isBuyer = (Boolean) session.getAttribute("isBuyer");
    Long userId = (Long) session.getAttribute("user_id");

%>

<div class="sidebar-nav">
    <ul>
        <li data-popover="true" data-content="" rel="popover" data-placement="right">
            <a href="#" 
               data-target=".dashboard-menu" 
               class="nav-header" 
               data-toggle="collapse">
                <i class="fa fa-fw fa-dashboard"></i> 
                Dashboard
                <i class="fa fa-collapse"></i>
            </a>
        </li>
        
        <li>
            <ul class="dashboard-menu nav nav-list collapse">
                <li>
                    <a href="<%=request.getContextPath()%>/UserServlet?action=view_dashboard&user_id=<%=userId%>">
                        <span class="fa fa-caret-right"></span> 
                        Dashboard
                    </a>
                </li>
            </ul>
        </li>



        <li data-popover="true" data-content="" rel="popover" data-placement="right">
            <a href="#" data-target=".invoice-menu" class="nav-header collapsed" data-toggle="collapse">
                <i class="fa fa-fw fa-fighter-jet"></i> 
                Invoice Management
                <i class="fa fa-collapse"></i>
            </a>
        </li>

        <li>
            <ul class="invoice-menu nav nav-list collapse">
                <% if (isAdmin || isSupplier) { %>
                <li>
                    <a href="<%=request.getContextPath()%>/InvoiceServlet?action=prepare_invoice"><span class="fa fa-caret-right"></span> Create New Invoice</a>
                </li>
                <% }%>
                <li>
                    <a href="<%=request.getContextPath()%>/InvoiceServlet?action=search_invoice"><span class="fa fa-caret-right"></span> Search Invoice</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/InvoiceServlet?action=select_inv_status"><span class="fa fa-caret-right"></span>View Invoice</a>
                </li>
            </ul>
        </li>

        <li data-popover="true" data-content="" rel="popover" data-placement="right">
            <a href="#" data-target=".payment-menu" class="nav-header collapsed" data-toggle="collapse">
                <i class="fa fa-fw fa-fighter-jet"></i> 
                Payments
                <i class="fa fa-collapse"></i>
            </a>
        </li>

        <li>
            <ul class="payment-menu nav nav-list collapse">
                <% if (isAdmin) {%>
                <li>
                    <a href="<%=request.getContextPath()%>/InvoiceServlet?action=list_invoices&&invoiceStatusType=SOLD&&userType=CLIENT"><span class="fa fa-caret-right"></span> Confirm Client Payment</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/InvoiceServlet?action=list_invoices&&invoiceStatusType=SETTLED&&userType=SUPPLIER"><span class="fa fa-caret-right"></span>Confirm Supplier Payment Receipt</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/InvoiceServlet?action=list_invoices&&invoiceStatusType=SETTLED&&userType=FUNDER"><span class="fa fa-caret-right"></span>Confirm Funder Payment Receipt</a>
                </li>
                <% }%>
                <% if (isSupplier) {%>
                <li>
                    <a href="<%=request.getContextPath()%>/PaymentServlet?action=confirmSupplierPayReceipt"><span class="fa fa-caret-right"></span>Confirm Payment Receipt</a>
                </li>
                <% }%>
                <% if (isFunder) {%>
                <li>
                    <a href="<%=request.getContextPath()%>/PaymentServlet?action=confirmFunderPayReceipt"><span class="fa fa-caret-right"></span>Confirm Payment Receipt</a>
                </li>
                <% }%>
            </ul>
        </li>

        <li>
            <a href="#" data-target=".accounts-menu" class="nav-header collapsed" data-toggle="collapse">
                <i class="fa fa-fw fa-briefcase"></i> Profile Maintenance  <i class="fa fa-collapse"></i>
            </a>
        </li>
        <li>
            <ul class="accounts-menu nav nav-list collapse">
               <li ><a href="<%=request.getContextPath()%>/UserServlet?action=prepare-maintainProfile"><span class="fa fa-caret-right"></span> Maintain profile</a></li>
                <li ><a href="<%=request.getContextPath()%>/SecurityServlet?action=resetPassword"><span class="fa fa-caret-right"></span> Reset Password</a></li>
            </ul>
        </li>

        <li><a href="#" data-target=".legal-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-legal"></i> Legal<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="legal-menu nav nav-list collapse">
                <li ><a href="privacy-policy.html"><span class="fa fa-caret-right"></span> Privacy Policy</a></li>
                <li ><a href="terms-and-conditions.html"><span class="fa fa-caret-right"></span> Terms and Conditions</a></li>
            </ul>
        </li>

        <li><a href="#" data-target=".other" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-legal"></i> Other<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="other nav nav-list collapse">
                <li ><a href="privacy-policy.html"><span class="fa fa-caret-right"></span>Help</a></li>
                <li ><a href="terms-and-conditions.html"><span class="fa fa-caret-right"></span> FAQs</a></li>
                <li ><a href="terms-and-conditions.html"><span class="fa fa-caret-right"></span> Contact Invoice <i><font style="color: #008dde">worX</font></i></a></li>
            </ul>
        </li>
    </ul>
</div>
