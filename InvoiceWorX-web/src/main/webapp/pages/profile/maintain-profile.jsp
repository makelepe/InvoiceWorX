
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
                                            <%-- <option value="userType">Select user type</option>
                                                <c:forEach var="item" items="${sessionScope.products}">
                                                 <option value="${item}" >${item}</option>
                                                </c:forEach> --%>
                                            <option value="Select title">Select title</option>
                                            <option value="Miss">Miss</option>
                                            <option value="Ms">Ms</option>
                                            <option value="Mr">Sir</option>
                                            <option value="Sir">Sir</option>
                                            <option value="Mrs">Mrs</option>
                                            <option value="Dr">Dr</option>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label>First Name</label> <input type="text" name="fname"
                                                                         value="first name" class="form-control span12">
                                    </div>
                                    <div class="form-group">
                                        <label>Middle Name</label> <input type="text" name="mname"
                                                                          value="middle name" class="form-control span12">
                                    </div>
                                    <div class="form-group">
                                        <label>Last Name</label> <input type="text" name="lname"
                                                                        value="last name" class="form-control span12">
                                    </div>


                                    <div class="form-group">
                                        <label>Gender</label> 
                                        <select name="gender" id="gender" class="form-control span12">
                                            <option value="Select gender">Select gender</option>
                                            <option value="m">Male</option>
                                            <option value="f">Female</option>
                                        </select>
                                    </div>


                                    <div class="form-group">
                                        <label>Race</label> 
                                        <select name="race" id="race" class="form-control span12">
                                            <option value="Select race">Select race</option>
                                            <option value="Black">Black</option>
                                            <option value="Coloured">Coloured</option>
                                            <option value="White">White</option>
                                            <option value="Indian">Indian</option>
                                        </select>


                                    </div>

                                    <div class="form-group">
                                        <label>Id number</label> <input type="text" name="idNum"
                                                                        value="ID number" class="form-control span12">
                                    </div>

                                    <div class="form-group">
                                        <label>Passport</label> <input type="text" name="passport"
                                                                       value="passport" class="form-control span12">
                                    </div>
                                </div>
                            </div>

                            <!--     Start Address details -->
                            <div class="panel panel-default">
                                <a href="#addressDetails" class="panel-heading" data-toggle="collapse">Address Details</a>
                                <div  id="addressDetails" class="panel-collapse panel-body collapse">
                                    <div class="form-group">

                                        <div class="form-group">
                                            <label>line1</label>
                                            <input type="text" name="line1"   class="form-control span12">
                                        </div>
                                        <div class="form-group">
                                            <label>Surburb</label> <input type="text" name="surburb"
                                                                          value="surburb" class="form-control span12">
                                        </div>
                                        <div class="form-group">
                                            <label>City</label> <input type="text" name="city" value="city"
                                                                       class="form-control span12">
                                        </div>
                                        <div class="form-group">
                                            <label>Province</label> <input type="text" name="province"
                                                                           value="province" class="form-control span12">
                                        </div>
                                        <div class="form-group">
                                            <label>Postal code</label> <input type="text" name="postal_code"
                                                                              value="postal code" class="form-control span12">
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
                                        <label>Cell phone</label> <input type="text" name="cellphone"
                                                                         value="cell phone" class="form-control span12">
                                    </div>
                                    <div class="form-group">
                                        <label>Home phone</label> <input type="text" name="homephone"
                                                                         value="home phone" class="form-control span12">
                                    </div>
                                    <div class="form-group">
                                        <label>Work phone</label> <input type="text" name="workphone"
                                                                         value="work phone" class="form-control span12">
                                    </div>
                                    <div class="form-group">
                                        <label>Email Address</label> <input type="text" name="email"
                                                                            value="Email address" class="form-control span12">
                                    </div>
                                    <div class="form-group">
                                        <label>Website</label> <input type="text" name="website"
                                                                      value="website" class="form-control span12">
                                    </div>

                                </div>
                            </div>

                            <!--  Start User details -->

                            <div class="panel panel-default">
                                <a href="#userDetails" class="panel-heading" data-toggle="collapse">User Details</a>
                                <div id="userDetails" class="panel-collapse panel-body collapse">
                                    <div class="form-group">
                                        <label>Username</label> <input type="text" name="username"
                                                                       value="username" class="form-control span12">
                                    </div>
                                    <div class="form-group">
                                        <label>Password</label> <input type="password" name="password"
                                                                       value="password" class="form-control span12">
                                    </div>

                                    <div class="form-group">
                                        <label>User type</label> 
                                        <select name="userType" id="userType"  class="form-control span12">
                                            <option value="Select user type">Select user type</option>
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
                                        <label>Organisation name</label> <input type="text"
                                                                                name="org_name" value="organisation name"
                                                                                class="form-control span12">
                                    </div>
                                    <div class="form-group">
                                        <label>Organisation registration number</label> <input
                                            type="text" name="org_reg_num"
                                            value="organisation registration number"
                                            class="form-control span12">
                                    </div>
                                    <div class="form-group">
                                        <label>Organisation registration date</label> <input type="text"
                                                                                             id="inv_date" name="org_reg_date" class="form-control span12">(dd/MM/yyyy)
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
                                        <input type="text" name="accountHolder" value="account holder" class="form-control span12">
                                    </div>

                                    <div class="form-group">
                                        <label>Account type</label> 
                                        <select name="accType" id="accType">
                                            <option value="Select account type">Select account type</option>
                                            <option value="Cheque">Cheque</option>
                                            <option value="Savings">Savings</option>

                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Account number</label> <input type="text" name="accNumber"
                                                                             value="account number" class="form-control span12">
                                    </div>

                                    <div class="form-group">
                                        <label>Bank</label> 
                                        <select name="bank" id="bank" class="form-control span12">
                                            <option value="Select bank">Select bank</option>
                                            <option value="FNB">FNB</option>
                                            <option value="ABSA">ABSA</option>
                                            <option value="Standar bank">Standar bank</option>
                                            <option value="Capitech">Capitech</option>
                                            <option value="Nedbank">Nedbank</option>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label>Branch</label> 
                                        <input type="text" name="branch"
                                               value="branch" class="form-control span12">
                                    </div>
                                    <div class="form-group">
                                        <label>Branch code</label> 
                                        <input type="text" name="branchCode"
                                               value="branch Code" class="form-control span12">
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
                                            <input type="submit" value="Update User Details!"
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

 