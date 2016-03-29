<%-- 
    Document   : tredit
    Created on : 4 mars 2016, 13:31:07
    Author     : Myriam
--%>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<root>
    <c:forEach var="u" items="${opt_categ}"> 
            <option value="${u.id}">${u.libelle.replace("&","et")}</option>

    </c:forEach>
</root>