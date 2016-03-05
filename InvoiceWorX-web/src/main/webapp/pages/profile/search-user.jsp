<div class="main-content">
    <div class="row padding-top">
        <div class="col-md-10 col-md-offset-1">
            <div class="row">
                <form action="<%=request.getContextPath()%>/UserServlet" method="POST">

                    <div class="panel panel-default">
                        <p class="panel-heading no-collapse">Search User</p>
                        <div class="panel-body">
                            <p align="center" >${sessionScope.success_msg}</p>

                            <div class="form-group">
                                <label>First Name</label> 
                                <input type="text" name="firstName" value="" class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>Last Name</label> 
                                <input type="text" name="lastName" value="" class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>ID Number</label> 
                                <input type="text" name="idNumber" value="" class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>Username</label> 
                                <input type="text" name="username" value="" class="form-control span12">
                            </div>

                            <div class="form-group">
                                <label>User Type</label> 
                                <select name="userType" id="userType" class="form-control span12">
                                    <option value="Select title">Select User Type</option>
                                    <option value="ADMIN">Admin</option>
                                    <option value="CLIENT">Client</option>
                                    <option value="FUNDER">Funder</option>
                                    <option value="SUPPLIER">Supplier</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Cellphone</label> 
                                <input type="text" name="cellphone" value="" class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>Email Address</label> 
                                <input type="text" name="emailAddress" value="" class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>Organization Name</label> 
                                <input type="text" name="orgName" value="" class="form-control span12">
                            </div>

                            <div class="form-group">

                                <input type="hidden" name="action" value="searchUser" />
                                <p>
                                    <input type="submit" value="Search" class="btn btn-primary pull-right" />
                                </p>
                            </div>
                            <div class="clearfix"></div>

                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

