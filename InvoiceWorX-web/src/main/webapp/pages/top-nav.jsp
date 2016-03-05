<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="" href="/index.jsp"><span class="navbar-brand"><span class="fa fa-paper-plane"></span>  Invoice <i><font style="color: #008dde">worX</font></i> </span></a></div>

    <div class="navbar-collapse collapse" style="height: 1px;">
        <ul id="main-menu" class="nav navbar-nav navbar-right">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user padding-right-small" style="position:relative;top: 3px;"></span> 
                    <%=(String)session.getAttribute("loginName")%>
                    <i class="fa fa-caret-down"></i>
                </a>

                <ul class="dropdown-menu">
                    <li><a href="./">Search Invoice</a></li>
                    <li class="divider"></li>
                    <li><a href="<%=request.getContextPath()%>/SecurityServlet?action=resetPassword">Reset Password</a></li>
                    <li><a href="<%=request.getContextPath()%>/UserServlet?action=prepare-maintainProfile">Update User Profile</a></li>
                    <li class="divider"></li>
                    <li><a tabindex="-1" href="<%=request.getContextPath()%>/SecurityServlet?action=logout">Logout</a></li>
                </ul>
            </li>
        </ul>

    </div>
</div>