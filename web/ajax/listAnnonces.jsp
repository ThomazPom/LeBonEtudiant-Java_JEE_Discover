<%-- 
    Document   : tredit
    Created on : 4 mars 2016, 13:31:07
    Author     : Myriam
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>Résultats de la recherche :</h3>
<table class="table">
    <thead>
        <tr>
            <th>#</th>
            <th>Titre</th>
            <th>Vendeur</th>
            <td>Date</td>
            <th>Prix</th>
            <td>Région</td>
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
                <td>${a.getEtablissements().get(0).getVille().getLibelle()}</td>
            </tr>
        </c:forEach>
        

