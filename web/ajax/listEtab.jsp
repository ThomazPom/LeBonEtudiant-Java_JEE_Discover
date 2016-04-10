<%-- 
    Document   : tredit
    Created on : 4 mars 2016, 13:31:07
    Author     : Myriam
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table>
    <c:forEach var="ae" items="${lst_etab}"> 
        <tr class="infoEtab">
        <td class="idEtab">${ae.getId()}</td>
        <td class="nomEtab">${ae.getNom()}</td>
        <td class="lonEtab">${ae.getLongitudeX()}</td>
        <td class="latEtab">${ae.getLatitudeY()}</td>
    </tr>
</c:forEach>
</table>