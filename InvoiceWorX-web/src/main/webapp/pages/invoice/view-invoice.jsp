<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<script type="text/javascript">
    function  getInvoiceRef(action) {

        document.location = '<%=request.getContextPath()%>/InvoiceServlet?invoiceRef=${sessionScope.invoice.getInvRefNumber()}&&action=' + action;


    }
</script>

<script>
function myFunction() {
    var amountPaid = document.getElementById("amountPaid").value;
    document.location = '<%=request.getContextPath()%>/InvoiceServlet?invoiceRef=${sessionScope.invoice.getInvRefNumber()}&&paymentConfirmationUserType=${sessionScope.paymentConfirmationUserType}&&action=Confirmation&&paidAmount=' + amountPaid;
    
}
</script>


<div class="main-content">
    <div class="row padding-top">
        <div class="col-md-10 col-md-offset-1">
            <form>
                <div class="row">
                    <div class="col-md-6">
                        <span style="font-size: 28px;"><span class="fa fa-paper-plane"></span> InvoiceWorX  <%=(String) session.getAttribute("loginName")%></span>
                        <address>${sessionScope.invoice.getCreatedBy().getOrg().getAddress().getLine1()}
                        </address>
                    </div>
                    <div class="pull-right well">
                        <table>
                            <tbody>
                                <tr>
                                    <td class="pull-right padding-right"><strong>Customer #</strong></td>
                                    <td>2239-19377329-207</td>
                                </tr>
                                <tr>
                                    <td class="pull-right padding-right"><strong>Invoice #</strong></td>
                                    <td>${sessionScope.invoice.getInvRefNumber()}</td>
                                </tr>
                                <tr>
                                    <td class="pull-right padding-right"><strong>Date</strong></td>
                                    <td>${sessionScope.invoice.getCreatedTs()}</td>
                                </tr>
                                <tr>
                                    <td class="pull-right padding-right"><strong>Period</strong></td>
                                    <td>6/30/2104 ? 8/31/2014</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h3>Sunset Corp.</h3>
                        <address>
                            ${sessionScope.invoice.getCreatedBy().getOrg().getAddress().getLine1()}<br>
                            Contact no : ${sessionScope.invoice.getCreatedBy().getOrg().getContact().getCellphone()}
                        </address>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <h2>Invoice</h2>
                    </div>
                </div>
                <h3>Goods And Services</h3>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>Description</th>
                                    <th>Date</th>
                                    <th>Unit price</th>
                                    <th>Vat amount</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${sessionScope.invoice.getInvoiceItems()}">
                                    <tr>
                                        <td>${item.getDescription()}<br><p class="text-sm"></p></td>
                                        <td>${item.getLineDate()}</td>
                                        <td>${item.getUnitPrice()}</td>
                                        <td>${item.getVatAmount()}</td>
                                    </tr>
                                </c:forEach>

                                <tr>
                                    <td>&nbsp;</td>
                                    <td><strong>Total</strong></td>
                                    <td><strong>R ${sessionScope.invoice.getInvAmount()}</strong></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">

                        <c:if test="${sessionScope.user.isAdmin()}">
                            <c:if test="${sessionScope.invoice.isApproved()}">
                                <a class="btn btn-warning" href="<%=request.getContextPath()%>/InvoiceServlet?invoiceRef=${sessionScope.invoice.getInvRefNumber()}&&action=Sell"><span class="fa fa-credit-card"> Sell</span></a>  
                            </c:if>
                            <c:if test="${sessionScope.invoice.isPendingVerification()}">
                                <a class="btn btn-warning" href="<%=request.getContextPath()%>/InvoiceServlet?invoiceRef=${sessionScope.invoice.getInvRefNumber()}&&action=Verify"><span class="fa fa-credit-card"> Verify</span></a>
                            </c:if>
                        </c:if>
                        <c:if test="${sessionScope.user.isSupplier() && sessionScope.invoice.isApproved()}">
                            <a class="btn btn-warning" href="<%=request.getContextPath()%>/InvoiceServlet?invoiceRef=${sessionScope.invoice.getInvRefNumber()}&&action=Sell"><span class="fa fa-credit-card"> Sell</span></a>  
                        </c:if>
                        <c:if test="${sessionScope.user.isAdmin()}">
                            <a class="btn btn-warning"  onclick="myFunction()"><span class="fa fa-credit-card"> Confirm</span></a>
                            <div class="form-group">
                                <label>Amount paid : R</label> <input type="text" id="amountPaid" name="amountPaid" value="" class="form-control span12">
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.user.isClient() && sessionScope.invoice.isNew()}">
                            <a class="btn btn-warning" href="<%=request.getContextPath()%>/InvoiceServlet?invoiceRef=${sessionScope.invoice.getInvRefNumber()}&&action=Approve"><span class="fa fa-credit-card"> Approve</span></a>  
                            <a class="btn btn-warning" href="<%=request.getContextPath()%>/InvoiceServlet?invoiceRef=${sessionScope.invoice.getInvRefNumber()}&&action=Reject"><span class="fa fa-credit-card"> Reject</span></a> 
                        </c:if>
                        <c:if test="${sessionScope.user.isFunder() && sessionScope.invoice.isOnSale()}">
                            <!--a class="btn btn-warning" href="< %=request.getContextPath()%>/InvoiceServlet?invoiceRef=$ {sessionScope.invoice.getInvRefNumber()}&&action=Pay Now" ><span class="fa fa-credit-card"> Pay Now</span></a-->  
                            <a class="btn btn-warning" href="<%=request.getContextPath()%>/InvoiceServlet?invoiceRef=${sessionScope.invoice.getInvRefNumber()}&&action=Buy"><span class="fa fa-credit-card"> Buy</span></a>
                        </c:if>

                    </div>
                </div>
                <div class="row padding-top">
                    <div class="col-md-12">
                        <div class="well">
                            Thank you for choosing Invoice WorX platform.
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>