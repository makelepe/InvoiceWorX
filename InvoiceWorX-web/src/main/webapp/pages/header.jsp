<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title><%=(String) session.getAttribute("form-title")%></title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Invoice Worx">
<meta name="author" content="Samuel Radingwane">

  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
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


<script
	src="<%=request.getContextPath()%>/lib/jQuery-Knob/js/jquery.knob.js"
	type="text/javascript">
</script>


<script type="text/javascript">
    $(function () {
        $(".knob").knob();
    });
</script>


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

    <script type="text/javascript">
    function addLine() {
        var lineCount = parseInt(document.getElementById("lineCounter").value);
        lineCount = lineCount + 1;
        document.getElementById("lineCounter").value = lineCount;

        $('#lines').append(
                '<tr id="lineRow' + lineCount + '">' +
                    '<td>' +
                        '<input type="text" id="inv_date" name="invDate' + lineCount + '"  class="form-control span12"/>(dd/MM/yyyy)' +
                    '</td>' +
                    '<td>' +
                        '<input type="text" name="description' + lineCount + '" value="" class="form-control span12"/>' +
                    '</td>' +
                    '<td>' +
                        '<input type="number" id="quantity' + lineCount + '" onchange="calculate(' + lineCount + ')" name="quantity' + lineCount + '" value="0" class="form-control span12"/>' +
                    '</td>' +
                    '<td>' +
                        ' <input type="text" id="unitPrice' + lineCount + '" onchange="calculate(' + lineCount + ')" name="unitPrice' + lineCount + '" value="0.00" class="form-control span12"/>' +
                    '</td>' +
                    '<td>' +
                        '<input type="text" id="lineTotal' + lineCount + '"  name="lineTotal' + lineCount + '" value="0.00" class="form-control span12" readonly/>' +
                    '</td>' +
                    '<td>' +
                        '<div class="form-group">' +
                        '<p>' +
                        '<a id="remove' + lineCount + '" href="#" onclick="removeLine(' + lineCount + ')" class="btn btn-primary pull-right">' +
                        'Remove' +
                        '</a>' +
                        '</p>' +
                        '</div>' +
                        '<div class="clearfix"></div>' +
                    '</td>' +
                '</tr>');

    }

    function removeLine(index) {

        /** set line counter */
        var lineCount = parseInt(document.getElementById("lineCounter").value);
        lineCount = lineCount - 1;
        document.getElementById("lineCounter").value = lineCount;

        var lineTotal = document.getElementById("lineTotal" + index).value;
        var totalInvoiceAmt = document.getElementById("totalInvoiceAmt").value;
        totalInvoiceAmt = totalInvoiceAmt - lineTotal;
        document.getElementById("totalInvoiceAmt").value = parseFloat(totalInvoiceAmt).toFixed(2);

        /** remove line */
        $('#lineRow' + index).remove();
    }

    function calculate(line) {
        // alert('calculate ' + line);
        var quantity = parseInt(document.getElementById("quantity" + line).value);
        var unitPrice = parseFloat(document.getElementById("unitPrice" + line).value).toFixed(2);
        var lineTotal = document.getElementById("lineTotal" + line).value;
        var totalInvoiceAmt = document.getElementById("totalInvoiceAmt").value;

        if (isNaN(quantity)) {
            document.getElementById("quantity" + line).value = 0;
            quantity = 0;
        }

        if (isNaN(unitPrice)) {
            document.getElementById("unitPrice" + line).value = 0;
            unitPrice = 0;
        }

        //alert('quantity '+quantity);
        /** subtract current line total from inv tot amt*/
        totalInvoiceAmt = totalInvoiceAmt - lineTotal;
        /** calculate new line total*/
        lineTotal = quantity * unitPrice;
        /** add new line total to inv tot*/
        totalInvoiceAmt = totalInvoiceAmt + lineTotal;

        document.getElementById("unitPrice" + line).value = unitPrice;
        document.getElementById("lineTotal" + line).value = parseFloat(lineTotal).toFixed(2);
        document.getElementById("totalInvoiceAmt").value = parseFloat(totalInvoiceAmt).toFixed(2);

    }
    
    
</script>   



</head>
