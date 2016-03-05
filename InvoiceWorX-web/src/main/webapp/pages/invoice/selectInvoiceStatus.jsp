<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<script type="text/javascript">
    function  getInvoices(statusType) {

        document.location = '<%=request.getContextPath()%>/InvoiceServlet?action=list_invoices&&invoiceStatusType=' + statusType;


    }
</script>

<form>

    <div class="dialog">
        <div class="panel panel-default">
            <p class="panel-heading no-collapse">Search invoice criteria</p>
            <div class="panel-body">


                <div class="form-group">
                    <label>Select Invoice status type</label> 
                    <select name="invoiceStatusType" id="invoiceStatusType" onchange="getInvoices(this.value);">
                        <option value="invoiceStatusType">Select invoice type</option>
                        <c:forEach var="item" items="${sessionScope.invoiceType}">
                            <option value="${item.getInvtype()}" >${item.getInvtype()}</option>
                        </c:forEach>
                    </select>


                </div>
            </div>
        </div>
    </div>
</form>
