<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <title>Invoice WorX Registration</title>
        <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Invoice Worx">
        <meta name="author" content="IX Developers">

        <link rel="stylesheet"
              href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">

        <script type="text/javascript">
            $(function () {

                $("#inv_date").datepicker({
                    changeMonth: true,
                    changeYear: true
                });

            });

        </script>


        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700'
              rel='stylesheet' type='text/css'>

        <link rel="stylesheet" type="text/css"
              href="<%=request.getContextPath()%>/lib/bootstrap/css/bootstrap.css">
        <link rel="stylesheet"
              href="<%=request.getContextPath()%>/lib/font-awesome/css/font-awesome.css">


        <link rel="stylesheet" type="text/css"
              href="<%=request.getContextPath()%>/css/theme.css">
        <link rel="stylesheet" type="text/css"
              href="<%=request.getContextPath()%>/css/premium.css">

        <script type="text/javascript">
            $(function () {
                var match = document.cookie.match(new RegExp('color=([^;]+)'));
                if (match)
                    var color = match[1];
                if (color) {
                    $('body').removeClass(function (index, css) {
                        return (css.match(/\btheme-\S+/g) || []).join(' ')
                    })
                    $('body').addClass('theme-' + color);
                }

                $('[data-popover="true"]').popover({html: true});

            });
        </script>
        <style type="text/css">
            #line-chart {
                height: 300px;
                width: 800px;
                margin: 0px auto;
                margin-top: 1em;
            }

            .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover {
                color: #fff;
            }
        </style>

        <script type="text/javascript">
            $(function () {
                var uls = $('.sidebar-nav > ul > *').clone();
                uls.addClass('visible-xs');
                $('#main-menu').append(uls.clone());
            });
        </script>


        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Le fav and touch icons -->
        <link rel="shortcut icon" href="../assets/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144"
              href="../assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114"
              href="../assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72"
              href="../assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed"
              href="../assets/ico/apple-touch-icon-57-precomposed.png">


        <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
        <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
        <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
        <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
        <!--[if (gt IE 9)|!(IE)]><!-->

        <!--<![endif]-->
    </head>

    <body class=" theme-blue">
      <div class="navbar navbar-default" role="navigation">
               <div class="navbar-header">
                     <a class="navbar-brand" href="index.jsp">Invoice <font
                                style="color: #008dde; font: bold">WorX</font></a>
               </div>

               <ul id="main-menu" class="nav navbar-nav navbar-right" >
                    <li>
                        <a href="index.jsp">
                            <span class="glyphicon glyphicon-user padding-right-small" style="position:relative;top: 3px;"></span> 
                            Registration Form
                        </a>
                    </li>
                </ul>       
        </div>

        <div class="content">

            <div class="main-content">
                <div class="row padding-top">
                    <div class="col-md-10 col-md-offset-1">

                        <form action="<%=request.getContextPath()%>/SecurityServlet" method="POST" >
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
                                        <input type="hidden" name="action" value="register" />
                                        <p>
                                            <input type="submit" value="Sign Up!"
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
            <footer>
                <hr>
                <p class="pull-right">
                    A <a href="#" target="_blank"> Platform to trade invoices. </a>

                </p>
                <p>
                    © 2016 <a href="#" target="_blank"> Invoice WorX </a>
                </p>
            </footer>
        </div>


        <script src="lib/bootstrap/js/bootstrap.js"></script>
    </body>
</html>
