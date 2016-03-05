<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="za.co.invoiceworx.servlets.*"%>
<%@ page import="za.co.invoiceworx.entity.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%
    Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

    if (!loggedIn) {
        session.setAttribute("error_msg", "This page requires login.");
        request.getRequestDispatcher("../index.jsp").forward(request, response);
    }

    User user = (User) session.getAttribute("user");

    String loginName = user.getPerson().getFullname();
    session.setAttribute("loginName", loginName);
%>
<!doctype html>
<html lang="en">

    <jsp:include page="header.jsp"/>

    <body class="theme-blue">

        <jsp:include page="top-nav.jsp" />


        <div class="content">
            <div class="header">
                <div class="stats">
                    <p class="stat"><span class="label label-success">22</span> On-Sale Invoices</p>
                    <p class="stat"><span class="label label-danger">15</span> Your Sold List Invoices</p>
                    <p class="stat"><span class="label label-info">40</span> Your Settled Invoices</p>
                </div>

                <h1 class="page-title">Funder Dashboard</h1>
                <ul class="breadcrumb">
                    <li><a href="index.html">Home</a> </li>
                    <li class="active">Dashboard</li>
                </ul>

            </div>

            <div class="main-content">
                <div class="panel panel-default">
                    <a href="#page-stats" class="panel-heading" data-toggle="collapse">Latest Stats</a>
                    <div id="page-stats" class="panel-collapse panel-body collapse in">

                        <div class="row">
                            <!--div class="col-md-3 col-sm-6">
                                <div class="knob-container">
                                    <input class="knob" data-width="200" data-min="0" data-max="300" data-displayPrevious="true" value="40" data-fgColor="#92A3C2" data-readOnly=true;>
                                    <h3 class="text-muted text-center">New Invoices</h3>
                                </div>
                            </div-->
                            <div class="col-md-3 col-sm-6">
                                <div class="knob-container">
                                    <input class="knob" data-width="200" data-min="0" data-max="150" data-displayPrevious="true" value="22" data-fgColor="#92A3C2" data-readOnly=true;>
                                    <h3 class="text-muted text-center">On Sale Invoices</h3>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="knob-container">
                                    <input class="knob" data-width="200" data-min="0" data-max="700" data-displayPrevious="true" value="520" data-fgColor="#92A3C2" data-readOnly=true;>
                                    <h3 class="text-muted text-center">Sold Invoices</h3>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="knob-container">
                                    <input class="knob" data-width="200" data-min="0" data-max="1500" data-displayPrevious="true" value="920" data-fgColor="#92A3C2" data-readOnly=true;>
                                    <h3 class="text-muted text-center">Settled Invoices</h3>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12 col-md-12">
                        <div class="panel panel-default">
                            <a href="#widget1container" class="panel-heading" data-toggle="collapse">On-Sale Invoices <span class="label label-warning">+10</span></a>
                            <div id="#widget1container" class="panel-body collapse in">
                                <table class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>Doc Number</th>
                                            <th>Invoice Number</th>
                                            <th>Invoice Date</th>
                                            <th>Created By</th>
                                            <th>Approved By</th>
                                            <th>&nbsp;</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>102</td>
                                            <td>545121545412</td>
                                            <td>12-Jan-2014</td>
                                            <td>Bokang Radingwane</td>
                                            <td>Vodacom</td>
                                            <td><a href="#">Open</a> / <a href="#">Buy</a> / <a href="#">Download</a></td>
                                        </tr>
                                        <tr>
                                            <td>104</td>
                                            <td>545121545412</td>
                                            <td>12-Jan-2014</td>
                                            <td>Gabriel Mukwevho</td>
                                            <td>Dept of Agric</td>
                                            <td><a href="#">Open</a> / <a href="#">Buy</a> / <a href="#">Download</a></td>
                                        </tr>
                                        <tr>
                                            <td>111</td>
                                            <td>545121545412</td>
                                            <td>12-Jan-2014</td>
                                            <td>James Motsamai</td>
                                            <td>Dept of Edu</td>
                                            <td><a href="#">Open</a> / <a href="#">Buy</a> / <a href="#">Download</a></td>
                                        </tr>
                                        <tr>
                                            <td>132</td>
                                            <td>545121545412</td>
                                            <td>12-Jan-2014</td>
                                            <td>Boitumelo Radingwane</td>
                                            <td>City Of JHB</td>
                                            <td><a href="#">Open</a> / <a href="#">Buy</a> / <a href="#">Download</a></td>
                                        </tr>
                                        <tr>
                                            <td>551</td>
                                            <td>545121545412</td>
                                            <td>12-Jan-2014</td>
                                            <td>Makelepe Radingwane</td>
                                            <td>NEW</td>
                                            <td><a href="#">Open</a> / <a href="#">Buy</a> / <a href="#">Download</a></td>
                                        </tr>
                                        <tr>
                                            <td>121</td>
                                            <td>545121545412</td>
                                            <td>12-Jan-2014</td>
                                            <td>Grace Radingwane</td>
                                            <td>Standard Bank</td>
                                            <td><a href="#">Open</a> / <a href="#">Buy</a> / <a href="#">Download</a></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>


                <footer>
                    <hr>

                    <!-- Purchase a site license to remove this link from the footer: http://www.portnine.com/bootstrap-themes -->
                    <p class="pull-right">A <a href="http://www.portnine.com/bootstrap-themes" target="_blank">Free Bootstrap Theme</a> by <a href="http://www.portnine.com" target="_blank">Portnine</a></p>
                    <p>© 2014 <a href="http://www.portnine.com" target="_blank">Portnine</a></p>
                </footer>
            </div>
        </div>


        <script src="lib/bootstrap/js/bootstrap.js"></script>
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
