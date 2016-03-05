<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<%--  <script type="text/javascript">
  function  getColumns(statusType){

	  document.location = '<%=request.getContextPath()%>/InvoiceServlet?action=view_invoice&&invoiceStatusType='+statusType;
	 

	  }
  </script> --%>
  
<form>
<div class="panel panel-default">
                                    <a href="#invoiceItems" class="panel-heading" data-toggle="collapse">Invoices<span class="label label-warning"> ${sessionScope.invoicesByStatusType.size()}</span></a>
                                    <div id="#invoiceItems" class="panel-body collapse in">
                                        <table class="table table-bordered table-striped">
                                            <thead>
                                                <tr>
                                                    <th>Invoice Ref Number</th>
                                                    <th>Invoice amount</th>
                                                    <th>Supplier</th>
                                                    <th>Client</th>
                                                    <th>View invoice</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                         
                                          <c:forEach var="item" items="${sessionScope.invoicesByStatusType}">
					
                                                <tr>
                                                    <td>
                                                        <input type="text" id="invRefNumber" name="invRefNumber" value="${item.getInvRefNumber()}" class="form-control span12">
                                                    </td>
                                                    <td>
                                                        <input type="text" id="inv_amount" name="inv_amount" value="${item.getInvAmount()}" class="form-control span12"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" id="created_by" name="created_by" value="${item.getCreatedBy().getPerson().getFullname ()}" class="form-control span12"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" id="approved_by" name="approved_by" value="" class="form-control span12"/>
                                                    </td>
                                                    <td>
                                                        <a href="<%=request.getContextPath()%>/InvoiceServlet?action=view_invoice&invRefNumber=${item.getInvRefNumber()}">view</a>
                                                    </td>
                                                    <td>
                                                       
                                                        <div class="clearfix"></div>
                                                    </td>
                                                </tr>
                                          </c:forEach>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
</form>
