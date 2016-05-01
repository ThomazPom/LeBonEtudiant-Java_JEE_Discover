<%-- 
    Document   : admin
    Created on : 11 avr. 2016, 11:57:40
    Author     : Benoit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <%@include file="inc/headerscripts.jspf" %>
    </head>
    <body>
        <%@include  file="inc/bodyheader.jspf" %>

        <div class="container-fluid" id="paginationcontainer">
            <% if ("listAdsPagination".equals(request.getParameter("action"))) {
                   %>
            <%@include file="ajax/listAdsPagination.jsp" %>
            <% } else if ("listUtilisateurs".equals(request.getParameter("action"))) {%>
            <%@include file="ajax/listUtilisateurs.jsp" %>
            <%}%>
        </div>
        <%@include file="inc/modalswrapper.jspf" %>
    </body>
</html>

<%@include file="inc/footerscripts.jspf" %>
