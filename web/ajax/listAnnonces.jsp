<%-- 
    Document   : tredit
    Created on : 4 mars 2016, 13:31:07
    Author     : Myriam
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3 style="color:#cc0000;text-align: center">${annonces.size()} résultats de la recherche</h3>
<table class="table">
    <thead>
        <tr>
            <th>#</th>
            <th>Titre</th>
            <th>Vendeur</th>
            <th>Date</th>
            <th>Prix</th>
            <th>Région</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="a" items="${annonces}"> 
            <tr>
                <td>${a.getId()}</td>
                <td>${a.getTitre()}</td>
                <td>${a.getProprietaire().getPrenom()}&#160;${a.getProprietaire().getNom()}</td>
                <td>${a.getDatePublication()}</td>
                <td>${a.getPrix()}</td>
                <td>${a.getEtablissements().get(0).getVille().getDepartement().getRegion().getLibelle()}</td>
                <td class="hidden infoetabs">
                        <c:forEach var="ae" items="${a.getEtablissements()}"> 
                    <ul class="infoetab">

                            <li class="idEtab">${ae.getId()}</li>
                            <li class="nomEtab">${ae.getNom()}</li>
                            <li class="lonEtab">${ae.getLongitudeX()}</li>
                            <li class="latEtab">${ae.getLatitudeY()}</li>

                    </ul>
                        </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>


