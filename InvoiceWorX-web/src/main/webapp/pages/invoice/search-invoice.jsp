<div class="main-content">
    <div class="row padding-top">
        <div class="col-md-10 col-md-offset-1">
            <div class="row">
                <form action="<%=request.getContextPath()%>/InvoiceServlet" method="POST">

                    <div class="panel panel-default">
                        <p class="panel-heading no-collapse">Search Invoice</p>
                        <div class="panel-body">
                            <p align="center" >${sessionScope.success_msg}</p>

                            <div class="form-group">
                                <label>Supplier Name</label> 
                                <input type="text" name="supplierName" value="" class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>Funder Name</label> 
                                <input type="text" name="funderName" value="" class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>Client Name</label> 
                                <input type="text" name="clientName" value="" class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>Invoice Reference Number</label> 
                                <input type="text" name="invRef" value="" class="form-control span12">
                            </div>
                            <div class="form-group">
                                <label>User Type</label> 
                                <select name="title" id="title" class="form-control span12">
                                    <option value="Select title">Select User Type</option>
                                    <option value="ADMIN">Admin</option>
                                    <option value="CLIENT">Client</option>
                                    <option value="FUNDER">Funder</option>
                                    <option value="SUPPLIER">Supplier</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Invoice Date Range</label> 
                                <input type="text" name="username" value="" placeholder="From Date" class="form-control span12">
                                <input type="text" name="username" value="" placeholder="To Date" class="form-control span12">
                            </div>

                            <div class="form-group">
                                <input type="hidden" name="action" value="searchInvoice" />
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

