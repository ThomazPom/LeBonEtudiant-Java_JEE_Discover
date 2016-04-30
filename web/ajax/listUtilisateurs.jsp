<%@page import="controller.UserController"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% UserController.UserPage userpage = (UserController.UserPage) request.getAttribute("wrapListPage");%>
<form name="listUserPagin" method="post" action="AjaxServlet">
    <input hidden name="action" value="listUtilisateurs">
    <div class="input-group" aria-describedby="nbresultaddon"><span class="input-group-addon" id="nbresultaddon">Nombre de résultats à afficher par page :</span>  
        <select class="form-control" name="nbResultPage">

            <% for (int a = 5; a < userpage.getNbresultPage(); a += 5) {
                    out.print("<option value='" + a + "'>" + a + "</option>");
                }
                out.print("<option value='" + userpage.getNbresultPage() + "' selected>" + userpage.getNbresultPage() + "</option>");

                for (int a = userpage.getNbresultPage() + 5; a <= 100; a += 5) {
                    out.print("<option value='" + a + "'>" + a + "</option>");
                }
            %>

        </select>
    </div>
    <div class="text-center">
        <nav>
            <input name="pageCourante" value="${wrapListPage.getPageCourante()}" hidden></input>
            <ul class="pagination">
                <li data="0">
                    <a aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <% for (int i = userpage.getPageCourante() - 4; i <= userpage.getPageCourante() + 4 && i <= userpage.getNbPages(); i++) {
                        if (i >= 0) {
                %>
                <% out.println("<li class='" + ((userpage.getPageCourante() == i) ? "active" : "inactive") + "' data=" + i + "><a>" + (i + 1) + "</a></li>"); %>

                <%}
                    }%>
                <li data="${wrapListPage.getNbPages()}">
                    <a  aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li> 
            </ul>
        </nav>
    </div>
    <table class="table">
        <thead>
            <tr>
                <th>#</th>
                <th>Rôle</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Email</th>
                <th>Téléphone</th>
                <th>Formation</th>
                <th>Ville</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ap" items="${wrapListPage.getResultList()}"> 
                <tr>
                    <td>${ap.getId()}</td>
                    <td>${ap.getRole()}</td>
                    <td>${ap.getNom()}</td>
                    <td>${ap.getPrenom()}</td>
                    <td>${ap.getLogin()}</td>
                    <td>${ap.getNumtel()}</td>
                    <td >
                        <c:forEach var="ae" items="${ap.getEtabsUser()}">
                            ${ae.getNom()}
                        </c:forEach>
                    </td>
                    <td >
                        <c:forEach var="ae" items="${ap.getEtabsUser()}">
                            ${ae.getVille().getLibelle()}
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</form>
