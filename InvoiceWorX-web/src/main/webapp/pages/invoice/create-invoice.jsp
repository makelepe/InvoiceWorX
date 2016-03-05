<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="java.util.List"%>
<%@page import="za.co.invoiceworx.entity.User"%>

<div class="main-content">
    <div class="row padding-top">
        <div class="col-md-10 col-md-offset-1">
            <form method="post" action="<%=request.getContextPath()%>/InvoiceServlet">
                <div class="row">

                    <div class="panel panel-default">
                        <p class="panel-heading no-collapse">Create Invoice</p>
                        <div class="panel-body">
                            <p align="center">${sessionScope.success_msg}</p>


                            <%
                                User user = (User) session.getAttribute("user");

                                if (user.isAdmin()) {
                            %>

                            <div class="form-group">
                                <label>Invoice Supplier</label>
                                <select name="supplierId" id="supplierId" class="form-control span12">
                                    <option value="0">Select Supplier</option>

                                    <%--    <%  
                                           List<User> suppliers = (List<User>)session.getAttribute("allSuppliers");
                                           for (User supplier : suppliers){
                                       %>
                                            <option value="<%=supplier.getId()%>"><%=supplier.getOrg().getOrgName()%></option>
                                       <%}%> --%>

                                    <c:forEach var="item" items="${sessionScope.allSuppliers}">
                                        <option value="${item.getId()}" >${item.getOrg().getOrgName()}</option>
                                    </c:forEach>

                                </select>
                            </div>
                            <%}%>


                            <div class="form-group">
                                <label>Invoice Client</label>
                                <select name="clientId" id="clientId" class="form-control span12">
                                    <option value="0">Select Client</option>
                                    <c:forEach var="item" items="${sessionScope.allClients}">
                                        <option value="${item.getId()}" >${item.getOrg().getOrgName()}</option>
                                    </c:forEach>
                                  <%--   <%
                                        List<User> clients = (List<User>) session.getAttribute("allClients");
                                        for (User client : clients) {
                                    <option value="<%=client.getId()%>"><%=client.getOrg().getOrgName()%></option>
                                    %>
                                    <%}%>
                                --%>


                                </select>
                            </div>        

                            <div class="form-group">
                                <label>Invoice Total Amount</label>                                 
                                <input type="text"
                                       id="totalInvoiceAmt"
                                       name="totalInvoiceAmt" 
                                       value="0.00" 
                                       class="form-control span12" readonly/>

                            </div>
                            <hr />

                            <div class="panel panel-default">
                                <a href="#invoiceItems" class="panel-heading" data-toggle="collapse">Invoice Items</a>
                                <div id="#invoiceItems" class="panel-body collapse in">
                                    <table id="lines" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Date</th>
                                                <th>Description</th>
                                                <th>Quantity</th>
                                                <th>Unit Price</th>
                                                <th>Line Total</th>
                                                <th><a id="add1" href="#" onclick="addLine()" class="btn btn-primary pull-right">Add</a></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr id="lineRow1">
                                                <td>
                                                    <input type="text" id="inv_date" name="invDate1"  class="form-control span12">(dd/MM/yyyy)
                                                </td>
                                                <td>
                                                    <input type="text" name="description1" value="" class="form-control span12"/>
                                                </td>
                                                <td>
                                                    <input type="number" id="quantity1" name="quantity1" onchange="calculate(1)" value="0" class="form-control span12" required/>
                                                </td>
                                                <td> 
                                                    <input type="text" id="unitPrice1" name="unitPrice1" onchange="calculate(1)" value="0.00" class="form-control span12" required/>
                                                </td>
                                                <td> 
                                                    <input type="text" id="lineTotal1" name="lineTotal1" value="0.00" class="form-control span12" readonly/>
                                                </td>
                                                <td>
                                                    &nbsp;
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <input id="lineCounter" type="hidden" value="1" name="lineCounter" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">

                    <input type="hidden" name="action" value="create_invoice" />
                    <p>
                        <input type="submit" value="Create"
                               class="btn btn-primary pull-right" />
                    </p>
                </div>
                <div class="clearfix"></div>
            </form>

        </div>
    </div>

</div>
