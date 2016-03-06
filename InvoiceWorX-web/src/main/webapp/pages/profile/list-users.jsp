<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<form>
    <div class="panel panel-default">
        <a href="#users" class="panel-heading" data-toggle="collapse">User Search Results<span class="label label-warning"> ${sessionScope.userSearchResults.size()}</span></a>
        <div id="#users" class="panel-body collapse in">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Second Name</th>
                        <th>Last Name</th>
                        <th>Organization Name</th>
                        <th>Email Address</th>
                        <th>Cellphone</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="usr" items="${sessionScope.userSearchResults}">

                        <tr>
                            <td>
                                <input type="text" id="firstName" name="firstName" value="${usr.getPerson().getFname()}" class="form-control span12" readonly/>
                            </td>
                            <td>
                                <input type="text" id="secondName" name="secondName" value="${usr.getPerson().getMname()}" class="form-control span12" readonly/>
                            </td>
                            <td>
                                <input type="text" id="lastName" name="lastName" value="${usr.getPerson().getLname()}" class="form-control span12" readonly/>
                            </td>
                            <td>
                                <input type="text" id="orgName" name="orgName" value="${usr.getOrg().getOrgName()}" class="form-control span12" readonly/>
                            </td>
                            <td>
                                <input type="text" id="emailAddress" name="emailAddress" value="${usr.getPerson().getContact().getEmail()}" class="form-control span12" readonly/>
                            </td>
                            <td>
                                <input type="text" id="cellphone" name="cellphone" value="${usr.getPerson().getContact().getCellphone()}" class="form-control span12" readonly/>
                            </td>
                            <td>
                                <input type="hidden" name="action" value="prepare-maintainProfile" />
                                <a href="<%=request.getContextPath()%>/UserServlet?action=viewUser&userId=${usr.getId()}">view</a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</form>
