<%@page import="controller.AnnonceController"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% AnnonceController.AnnoncePage annnoncepage = (AnnonceController.AnnoncePage) request.getAttribute("wrapListPage");%>
<form name="listAdPagin" method="post" action="AjaxServlet">
    <input hidden name="action" value="listAdsPagination">

    <div class="input-group container" aria-describedby="nbresultaddon"><span class="input-group-addon" id="nbresultaddon">Nombre de r�sultats � afficher par page :</span>  
        <select class="form-control" name="nbResultPage">

            <% for (int a = 5; a < annnoncepage.getNbresultPage(); a += 5) {
                    out.print("<option value='" + a + "'>" + a + "</option>");
                }
                out.print("<option value='" + annnoncepage.getNbresultPage() + "' selected>" + annnoncepage.getNbresultPage() + "</option>");

                for (int a = annnoncepage.getNbresultPage() + 5; a <= 100; a += 5) {
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
                <% for (int i = annnoncepage.getPageCourante() - 4; i <= annnoncepage.getPageCourante() + 4 && i <= annnoncepage.getNbPages(); i++) {
                        if (i >= 0) {
                %>
                <% out.println("<li class='" + ((annnoncepage.getPageCourante() == i) ? "active" : "inactive") + "' data=" + i + "><a>" + (i + 1) + "</a></li>"); %>

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
                    <div class="resultPagination">
    <table class="table tableResultAnnonce" >
        <thead>
            <tr>
                <th>#</th>
                <th>Titre</th>
                <th>Vendeur</th>
                <th></th>
                <th>Date</th>
                <th>Prix</th>
                <th>R�gion</th>
                <th>Type</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ap" items="${wrapListPage.getResultList()}"> 
                <tr>
                    <td class="idAnnonce">${ap.getId()}</td>
                    <td>${ap.getTitre()}</td>
                    <td>${ap.getProprietaire().getPrenom()}&#160;${ap.getProprietaire().getNom()}</td>
                    <td><button class="btn  btn-sm btn-default noBig"><span class="glyphicon glyphicon-eye-open"></span></button></td>

                    <td>${ap.getDatePublication()}</td>
                    <td>${ap.getPrix()}</td>
                    <td>${ap.getEtablissements().get(0).getVille().getDepartement().getRegion().getLibelle()}</td>
                    <td>${ap.getType()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
                        </div>
</form>