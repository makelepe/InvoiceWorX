<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<
<div class="main-content">
    <div class="row padding-top">
        <div class="col-md-10 col-md-offset-1">

            <form action="POST">
                <!--  Person details  details -->
                <div class="panel panel-default">
                    <a href="#personalDetails" class="panel-heading" data-toggle="collapse">Personal Details</a>
                    <div id="personalDetails" class="panel-collapse panel-body collapse">
                        <div class="form-group">
                            <label>Title</label> 
                            <select name="title" id="title" class="form-control span12">
                                <option value="${sessionScope.selectedUser.getPerson().getTitle()}" selected>
                                    ${sessionScope.selectedUser.getPerson().getTitle()}
                                </option>
                                <option value="Miss">Miss</option>
                                <option value="Ms">Ms</option>
                                <option value="Mr">Mr</option>
                                <option value="Mrs">Mrs</option>
                                <option value="Dr">Dr</option>
                                <option value="Sir">Prof</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>First Name</label> 
                            <input type="text" name="fname" value="${sessionScope.selectedUser.getPerson().getFname()}" class="form-control span12">
                        </div>
                        <div class="form-group">
                            <label>Middle Name</label> 
                            <input type="text" name="mname" value="${sessionScope.selectedUser.getPerson().getMname()}" class="form-control span12">
                        </div>
                        <div class="form-group">
                            <label>Last Name</label> 
                            <input type="text" name="lname" value="${sessionScope.selectedUser.getPerson().getLname()}" class="form-control span12">
                        </div>


                        <div class="form-group">
                            <label>Gender</label> 
                            <select name="gender" id="gender" class="form-control span12">
                                <option value="${sessionScope.selectedUser.getPerson().getGender()}" selected>
                                    ${sessionScope.selectedUser.getPerson().getGender()}
                                </option>
                                <option value="m">Male</option>
                                <option value="f">Female</option>
                            </select>
                        </div>


                        <div class="form-group">
                            <label>Race</label> 
                            <select name="race" id="race" class="form-control span12">
                                <option value="${sessionScope.selectedUser.getPerson().getRace()}" selected="">
                                    ${sessionScope.selectedUser.getPerson().getRace()}
                                </option>
                                <option value="Black">Black</option>
                                <option value="Coloured">Coloured</option>
                                <option value="White">White</option>
                                <option value="Indian">Indian</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Id number</label> 
                            <input type="text" name="idNum" value="${sessionScope.selectedUser.getPerson().getIdNumber()}"  class="form-control span12">
                        </div>

                        <div class="form-group">
                            <label>Passport</label> 
                            <input type="text" name="passport" value="${sessionScope.selectedUser.getPerson().getPassport()}"  class="form-control span12">
                        </div>
                    </div>
                </div>

                <!--     Start Address details -->
                <div class="panel panel-default">
                    <a href="#addressDetails" class="panel-heading" data-toggle="collapse">Personal Address Details</a>
                    <div  id="addressDetails" class="panel-collapse panel-body collapse">
                        <div class="form-group">

                            <div class="form-group">
                                <label>line1</label>
                                <input type="text" name="line1" value="${sessionScope.selectedUser.getPerson().getAddress().getLine1()}"   class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>Surburb</label> 
                                <input type="text" name="surburb" value="${sessionScope.selectedUser.getPerson().getAddress().getSurburb()}"  class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>City</label> 
                                <input type="text" name="city" value="${sessionScope.selectedUser.getPerson().getAddress().getCity()}"  class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>Province</label> 
                                <input type="text" name="province" value="${sessionScope.selectedUser.getPerson().getAddress().getProvince()}"  class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>Postal Code</label> 
                                <input type="text" name="postal_code" value="${sessionScope.selectedUser.getPerson().getAddress().getPostalCode()}"  class="form-control span12">
                            </div>

                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>

                <!--  Start contact details -->

                <div class="panel panel-default">
                    <a href="#contactDetails" class="panel-heading" data-toggle="collapse">Contact Details</a>
                    <div id="contactDetails" class="panel-collapse panel-body collapse">
                        <div class="form-group">
                            <label>Cell phone</label> 
                            <input type="text" name="cellphone" value="${sessionScope.selectedUser.getPerson().getContact().getCellphone()}"  class="form-control span12">
                        </div>
                        <div class="form-group">
                            <label>Home phone</label> 
                            <input type="text" name="homephone" value="${sessionScope.selectedUser.getPerson().getContact().getHomephone()}"  class="form-control span12">
                        </div>
                        <div class="form-group">
                            <label>Work phone</label> 
                            <input type="text" name="workphone" value="${sessionScope.selectedUser.getPerson().getContact().getWorkphone()}"  class="form-control span12">
                        </div>
                        <div class="form-group">
                            <label>Email Address</label> 
                            <input type="text" name="email" value="${sessionScope.selectedUser.getPerson().getContact().getEmail()}"  class="form-control span12">
                        </div>
                        <div class="form-group">
                            <label>Website</label> 
                            <input type="text" name="website" value="${sessionScope.selectedUser.getPerson().getContact().getWebsite()}"  class="form-control span12">
                        </div>

                    </div>
                </div>

                <!--  Start User details -->

                <div class="panel panel-default">
                    <a href="#userDetails" class="panel-heading" data-toggle="collapse">User Details</a>
                    <div id="userDetails" class="panel-collapse panel-body collapse">
                        <div class="form-group">
                            <label>Username</label> 
                            <input type="text" name="username"  value="${sessionScope.selectedUser.getUsername()}"  class="form-control span12">
                        </div>
                        <div class="form-group">
                            <label>Password</label> 
                            <input type="password" name="password" value="${sessionScope.selectedUser.getPassword()}"  class="form-control span12">
                        </div>

                        <div class="form-group">
                            <label>User Type</label> 
                            <select name="userType" id="userType"  class="form-control span12">
                                <option value="${sessionScope.selectedUser.getUserType()!=null? sessionScope.selectedUser.getUserType() : ""}" selected>
                                    ${sessionScope.selectedUser.getUserType()!=null? sessionScope.selectedUser.getUserType() : "Select User Type"}
                                </option>
                                <option value="FUNDER">FUNDER</option>
                                <option value="SUPPLIER">SUPPLIER</option>
                            </select>
                        </div>
                        <div class="clearfix"></div>

                    </div>
                </div>


                <!--     Start Organisation details -->

                <div class="panel panel-default">
                    <a href="#orgDetails" class="panel-heading" data-toggle="collapse">Organizational Details</a>
                    <div id="orgDetails" class="panel-collapse panel-body collapse">
                        <div class="form-group">
                            <label>Organization name</label> 
                            <input type="text" name="org_name" value="${sessionScope.selectedUser.getOrg().getOrgName()}"  class="form-control span12">
                        </div>
                        <div class="form-group">
                            <label>Organization registration number</label> 
                            <input type="text" name="org_reg_num" value="${sessionScope.selectedUser.getOrg().getOrgRegNum()}"  class="form-control span12">
                        </div>
                        <div class="form-group">
                            <label>Organization Registration Date</label> 
                            <input type="text" id="inv_date" name="org_reg_date" value="${sessionScope.selectedUser.getOrg().getOrgRegDate()}"  class="form-control span12">(dd/MM/yyyy)
                        </div>

                        <div class="clearfix"></div>
                    </div>
                </div>

                <!--  Start acc details -->
                <div class="panel panel-default">
                    <a href="#accountDetails" class="panel-heading" data-toggle="collapse">Account Details</a>
                    <div id="accountDetails" class="panel-collapse panel-body collapse">
                        <div class="form-group">
                            <label>Account holder name</label> 
                            <input type="text" name="accountHolder" value="${sessionScope.selectedUser.getAccount().getTitle()}"  class="form-control span12">
                        </div>

                        <div class="form-group">
                            <label>Account type</label> 
                            <select name="accType" id="accType">
                                <option value="${sessionScope.selectedUser.getAccount().getGender()}" selected>
                                    ${sessionScope.selectedUser.getAccount().getGender()}
                                </option>
                                <option value="Cheque">Cheque</option>
                                <option value="Savings">Savings</option>

                            </select>
                        </div>
                        <div class="form-group">
                            <label>Account number</label> 
                            <input type="text" name="accNumber"  value="${sessionScope.selectedUser.getAccount().getTitle()}"  class="form-control span12">
                        </div>

                        <div class="form-group">
                            <label>Bank</label> 
                            <select name="bank" id="bank" class="form-control span12">
                                <option value="${sessionScope.selectedUser.getAccount().getGender()}" selected>
                                    ${sessionScope.selectedUser.getPerson().getGender()}
                                </option>
                                <option value="FNB">FNB</option>
                                <option value="ABSA">ABSA</option>
                                <option value="Standard bank">Standard bank</option>
                                <option value="Capitec">Capitec</option>
                                <option value="Nedbank">Nedbank</option>
                                <option value="Investec">Investec</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Branch</label> 
                            <input type="text" name="branch" value="${sessionScope.selectedUser.getAccount().getBranch()}"  class="form-control span12">
                        </div>
                        <div class="form-group">
                            <label>Branch Code</label> 
                            <input type="text" name="branchCode" value="${sessionScope.selectedUser.getAccount().getBranchCode()}"  class="form-control span12">
                        </div>


                        <div class="clearfix"></div>
                    </div>
                </div>

                <!--  Start buttons -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <input type="hidden" name="action" value="maintainProfile" />
                            <p>
                                <input type="submit" value="Update User Profile"
                                       class="btn btn-primary pull-right" />
                            </p>
                            <label class="remember-me"><input type="checkbox">
                                I agree with the <a href="terms-and-conditions.html">Terms
                                    and Conditions</a></label>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

