<%
Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
Boolean isSupplier = (Boolean) session.getAttribute("isSupplier");
Boolean isFunder = (Boolean) session.getAttribute("isFunder");
Boolean isBuyer = (Boolean) session.getAttribute("isBuyer");
String userId = (String) session.getAttribute("user_id");

%>

<div class="sidebar-nav">
    <ul>
        <li>
            <a href="/UserServlet?action=view_dashboard&user_id=<%=userId%>" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse">
                <i class="fa fa-fw fa-dashboard"></i> 
                Dashboard
                <i class="fa fa-collapse"></i>
            </a>
        </li>

        <li>
            <a href="/UserServlet?action=todo&user_id=<%=userId%>" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse">
                <i class="fa fa-fw fa-dashboard"></i> 
                Todo List
                <i class="fa fa-collapse"></i>
            </a>
        </li>

        <li data-popover="true" data-content="Invoice management" rel="popover" data-placement="right">
            <a href="#" data-target=".premium-menu" class="nav-header collapsed" data-toggle="collapse">
                <i class="fa fa-fw fa-fighter-jet"></i> 
                Invoice Management
                <i class="fa fa-collapse"></i>
            </a>
        </li>
        
        <% if (isAdmin) { %>
        <li>
            <ul class="premium-menu nav nav-list collapse">
                <li>
                    <a href="pages/create-invoice.jsp"><span class="fa fa-caret-right"></span> Create New Invoice</a>
                </li>
                <li>
                    <a href="pages/search-invoice.jsp"><span class="fa fa-caret-right"></span> Search Invoice</a>
                </li>
                <li>
                    <a href="pages/confirm-funder-payment.jsp"><span class="fa fa-caret-right"></span>New Invoices</a>
                </li>
                <li>
                    <a href="pages/confirm-funder-payment.jsp"><span class="fa fa-caret-right"></span>Pending Approval Invoices</a>
                </li>
                <li>
                    <a href="pages/pay-supplier.jsp"><span class="fa fa-caret-right"></span>Invoices On Sale</a>
                </li>
                <li>
                    <a href="pages/confirm-funder-payment.jsp"><span class="fa fa-caret-right"></span>Sold Invoices</a>
                </li>
                <li >
                    <a href="pages/pay-platform-fee.jsp"><span class="fa fa-caret-right"></span> Pay Platform Fee</a>
                </li>
                <li >
                    <a href="pages/confirm-supplier-payment-receipt.jsp"><span class="fa fa-caret-right"></span> Confirm Supplier Payment Receipt</a>
                </li>
                <li>
                    <a href="pages/confirm-buyer-settlement.jsp"><span class="fa fa-caret-right"></span> Confirm Buyer Settlement</a>
                </li>
                <li >
                    <a href="pages/pay-funder.jsp"><span class="fa fa-caret-right"></span>Pay Funder</a>
                </li>
                <li >
                    <a href="pages/settle-invoice.jsp"><span class="fa fa-caret-right"></span>Settle Invoice</a>
                </li>
                <li>
                    <a href="pages/view-active-invoices.jsp"><span class="fa fa-caret-right"></span>View Active Invoices</a>
                </li>
            </ul>
        </li>
        <% } %>
        
        <% else if (isSupplier) { %>
        <li>
            <ul class="premium-menu nav nav-list collapse">
                <li >
                    <a href="pages/track-invoice.jsp"><span class="fa fa-caret-right"></span> Track Invoice</a>
                </li>
                <li >
                    <a href="pages/create-invoice.jsp"><span class="fa fa-caret-right"></span> Create New Invoice</a>
                </li>
                <li >
                    <a href="pages/sell-invoice.jsp"><span class="fa fa-caret-right"></span> Sell Invoice</a>
                </li>
                <li >
                    <a href="pages/confirm-supplier-payment-receipt.jsp"><span class="fa fa-caret-right"></span> Confirm Supplier Payment Receipt</a>
                </li>
                <li >
                    <a href="pages/confirm-supplier-payment-receipt.jsp"><span class="fa fa-caret-right"></span> Confirm Invoice Settlement</a>
                </li>
                <li>
                    <a href="pages/view-active-invoices.jsp"><span class="fa fa-caret-right"></span>View Active Invoices</a>
                </li>
            </ul>
        </li>
        <% } %>

        <% else if (isFunder) { %>
        <li>
            <ul class="premium-menu nav nav-list collapse">
                <li >
                    <a href="pages/track-invoice.jsp"><span class="fa fa-caret-right"></span> Track Invoice</a>
                </li>
                <li >
                    <a href="pages/create-invoice.jsp"><span class="fa fa-caret-right"></span> Buy Invoice</a>
                </li>
                <li >
                    <a href="pages/confirm-supplier-payment-receipt.jsp"><span class="fa fa-caret-right"></span> Confirm Payment</a>
                </li>
                <li>
                    <a href="pages/view-invoices.jsp"><span class="fa fa-caret-right"></span>View Invoices</a>
                </li>
            </ul>
        </li>
        <% } %>

        <% else if (isBuyer) { %>
        <li>
            <ul class="premium-menu nav nav-list collapse">
                <li >
                    <a href="pages/track-invoice.jsp"><span class="fa fa-caret-right"></span> Track Invoice</a>
                </li>
                <li >
                    <a href="pages/authorize-invoice.jsp"><span class="fa fa-caret-right"></span> Search Invoices</a>
                </li>
                <li >
                    <a href="pages/upload-proof-of-payment.jsp"><span class="fa fa-caret-right"></span> Confirm Payment</a>
                </li>
            </ul>
        </li>
        <% } %>

        <li>
            <ul class="premium-menu nav nav-list collapse">
                <li >
                    <a href="premium-profile.html"><span class="fa fa-caret-right"></span> Track Invoice</a>
                </li>
                <li >
                    <a href="premium-profile.html"><span class="fa fa-caret-right"></span>View On-Sale Invoices</a>
                </li>
                <li >
                    <a href="premium-invoice.html"><span class="fa fa-caret-right"></span>View Sold Invoices</a>
                </li>
                <li >
                    <a href="premium-blog.html"><span class="fa fa-caret-right"></span>View Settled Invoices</a>
                </li>
            </ul>
        </li>

<li>
    <a href="#" data-target=".accounts-menu" class="nav-header collapsed" data-toggle="collapse">
        <i class="fa fa-fw fa-briefcase"></i> Profile Maintenance  <i class="fa fa-collapse"></i>
    </a>
</li>
<li>
    <ul class="accounts-menu nav nav-list collapse">
        <li ><a href="sign-in.html"><span class="fa fa-caret-right"></span> Update Personal Details </a></li>
        <li ><a href="sign-up.html"><span class="fa fa-caret-right"></span> Update Banking Details</a></li>
        <li ><a href="reset-password.html"><span class="fa fa-caret-right"></span> Reset Password</a></li>
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
