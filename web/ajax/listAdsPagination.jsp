<%-- 
    Document   : tredit
    Created on : 16 avr. 2016, 18:00:42
    Author     : Myriam
--%>
<%@page import="controller.AnnonceController"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<script src="js/appjvs.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<form name="listAdPagin" method="post" action="AjaxServlet?action=listAdsPagination">
    <div class="input-group" aria-describedby="nbresultaddon"><span class="input-group-addon" id="nbresultaddon">Nombre de résultats à afficher par page :</span>  
        <select class="form-control" name="nbResultPage">
            <optgroup label="Sélectionné :"
                      <option value ="${wrapListPage.getNbresultPage()}" selected> ${wrapListPage.getNbresultPage()}</option>
            </optgroup>
            <optgroup label="Autres">
            <option value="1">1</option>
            <option value="5">5</option>
            <option value="20">10</option>
            <option value="20">20</option>
            <option value="30">30</option>
            <option value="40">40</option>
            <option value="50">50</option>
            <option value="60">60</option>
            <option value="70">70</option>
            <option value="80">80</option>
            <option value="90">90</option>
            <option value="100">100</option></optgroup>
        </select>
    </div>

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
            <c:forEach var="ap" items="${wrapListPage.getResultList()}"> 
                <tr>
                    <td>${ap.getId()}</td>
                    <td>${ap.getTitre()}</td>
                    <td>${ap.getProprietaire().getPrenom()}&#160;${ap.getProprietaire().getNom()}</td>
                    <td>${ap.getDatePublication()}</td>
                    <td>${ap.getPrix()}</td>
                    <td>${ap.getEtablissements().get(0).getVille().getDepartement().getRegion().getLibelle()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="text-center">
        <nav>
            <input name="pageCourante" value="${wrapListPage.getPageCourante()}" hidden></input>
            <ul class="pagination">
                <li data="1">
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <% AnnonceController.AnnoncePage annnoncepage = (AnnonceController.AnnoncePage) request.getAttribute("wrapListPage");
                  //  for (int i = annnoncepage.getPageCourante() - 4; i < annnoncepage.getPageCourante() + 4 && i >= 0 && i <= annnoncepage.getNbPages(); i++) { 
                for (int i = annnoncepage.getPageCourante() - 4; i <= annnoncepage.getPageCourante() + 4  && i < annnoncepage.getNbPages(); i++) { 
                    if(i>=0)
                    {
                %>
                <% out.println("<li data="+i+"><a>"+(i+1)+"</a></li>"); %>

                <%}}%>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</form>
<script src="js/appjvs.js"></script>