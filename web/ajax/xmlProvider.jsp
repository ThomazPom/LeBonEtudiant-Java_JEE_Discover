<%-- 
    Document   : tredit
    Created on : 4 mars 2016, 13:31:07
    Author     : Myriam
--%>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<root>
<data>
    <total><%= request.getAttribute("total") %></total>
    <maxOffset id="maxOffset" ><%= request.getAttribute("maxOffset") %></maxOffset>
    <offsetpage><%= request.getAttribute("offsetpage") %></offsetpage>
</data>
<users>
<c:if test="${param['action'] == 'listerLesUtilisateurs'}" >
    
    <c:forEach var="u" items="${listeDesUsers}"> 
        <tr> <td class="tdLg">${u.login}</td>
            <td class="tdFn">${u.firstname}</td>
            <td class="tdLn">${u.lastname}</td>
            <td class="tddit"><button class="btedit btn btn-primary"><span class="glyphicon glyphicon-edit"></span></button></td>
            <td class="tddel "><button class="btdel btn btn-danger"><span class="glyphicon glyphicon-remove"></span></button></td></tr>
            </c:forEach>
        </c:if>
        </users>
</root>