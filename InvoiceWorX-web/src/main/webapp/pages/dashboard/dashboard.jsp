<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="za.co.invoiceworx.bean.*"%>
<%@ page import="java.util.*"%>
<%
    boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    boolean isSupplier = (Boolean) session.getAttribute("isSupplier");
    boolean isFunder = (Boolean) session.getAttribute("isFunder");
    boolean isBuyer = (Boolean) session.getAttribute("isBuyer");

    List<KnobContainer> knobContainers = (List<KnobContainer>) session.getAttribute("knobs");
%>           

<div class="main-content">
    <div class="panel panel-default">
        <a href="#page-stats" class="panel-heading" data-toggle="collapse">Latest Stats</a>
        <div id="page-stats" class="panel-collapse panel-body collapse in">
            <div class="row">
                <% for (KnobContainer knob : knobContainers) {%>
                    <div class="col-md-3 col-sm-6">
                        <div class="knob-container">
                            <input class="knob" 
                                   data-width="200" 
                                   data-min="0" 
                                   data-max="<%=knob.getMaxValue()%>" 
                                   data-displayPrevious="true" 
                                   value="<%=knob.getValue()%>" 
                                   data-fgColor="#92A3C2" 
                                   data-readOnly=true;>
                            <h3 class="text-muted text-center"><%=knob.getLabel()%></h3>
                        </div>
                    </div>
                <%}%>
            </div>
        </div>
    </div>

</div>
 --%>