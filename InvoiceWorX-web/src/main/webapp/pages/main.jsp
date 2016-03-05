<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="za.co.invoiceworx.servlets.*"%>
<%@ page import="za.co.invoiceworx.entity.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%
    Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
    User user = (User) session.getAttribute("user");

    if (user == null || !loggedIn) {
        session.setAttribute("error_msg", "This page requires login.");
        request.getRequestDispatcher("../index.jsp").forward(request, response);
    }

    String loginName = user.getPerson().getFullname();
    session.setAttribute("loginName", loginName);

%>
<!doctype html>
<html lang="en">

    <jsp:include page="header.jsp"/>

    <body class="theme-blue">

        <jsp:include page="top-nav.jsp" />

        <jsp:include page="left-nav.jsp" /> 

        <jsp:include page="content.jsp" /> 


        <script src="<%=request.getContextPath()%>/lib/bootstrap/js/bootstrap.js"></script>
        <script type="text/javascript">
            $("[rel=tooltip]").tooltip();
            $(function () {
                $('.demo-cancel-click').click(function () {
                    return false;
                });
            });
        </script>

    </body>
</html>
