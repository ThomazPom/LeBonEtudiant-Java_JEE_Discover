<%-- 
    Document   : tredit
    Created on : 4 mars 2016, 13:31:07
    Author     : Myriam
--%>
<%@page import="model.Annonce"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<% model.Annonce annonce = (model.Annonce)request.getAttribute("annonce"); %>


<input value="<%=annonce.getId() %>" name="idVenteAnnonce" form="formVente" id="idVenteAnnonce"/>
<div class="confirmVenteOverlay" style="">
    <%  if (session.getAttribute("success") != null) {%><div class="alert alert-success" role="alert"> <%out.println(session.getAttribute("success"));
        session.removeAttribute("message");
        session.removeAttribute("success");%></div>
    <%}
            if (session.getAttribute("info") != null) {%><div class="alert alert-info" role="alert"><%out.println(session.getAttribute("info")); %><% session.removeAttribute("info");%></div>
    <%}
            if (session.getAttribute("warning") != null) {%><div class="alert alert-warning" role="alert"><%out.println(session.getAttribute("warning")); %><% session.removeAttribute("warning");%></div>
    <%}
            if (session.getAttribute("danger") != null) {%><div class="alert alert-danger" role="alert"><%out.println(session.getAttribute("danger")); %><% session.removeAttribute("danger");%></div>
    <% }%>
    <div class="confirmVenteOverlayTitle">
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&#160;Titre</span>
            <p>
                <%=annonce.getTitre() %>
            </p>
        </div>

    </div>
    <div  class="confirmVenteOverlayBody">
        
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span>&#160;Vendeur</span>
            <p>
                <%=annonce.getProprietaire().getPrenom()+" "+annonce.getProprietaire().getNom() %>
            </p>
        </div>
        
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-home"></span>&#160;Email</span>
            <p>
                <%=annonce.getEmailOverride() %>
            </p>
        </div>
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&#160;N°de téléphone de contact</span>
            <p>
                <%=annonce.getNumeroOverride() %>
            </p>
        </div>

        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&#160;Date de publication</span>
            <p>
                <%=annonce.getDatePublication() %>
            </p>
        </div>
        
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&#160;Date de fin</span>
            <p>
                <%=annonce.getDateFin() %>
            </p>
        </div>
            
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-euro"></span>&#160;Prix</span>
            <p style="color:orange">
                <%=annonce.getPrix() %> €
            </p>
        </div>
        <div   class="input-group">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&#160;Description</span>
            <textarea readonly="" class="form-control">
                <%=annonce.getDescription() %>
            </textarea>
        </div>
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-globe"></span>&#160;Lieux</span>
            <select multiple="multiple">
                <c:forEach var="u" items="${annonce.getEtablissements()}"> 
                    <option value="${u.id}">${u.nom}</option>
                </c:forEach>
            </select>
        </div>
        
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-shopping-cart"></span>&#160;Categorie</span>
            <select multiple="multiple" readonly style="background: #FAFAFA">
                <c:forEach var="u" items="${annonce.getCategories()}"> 
                    <option value="${u.id}">${u.libelle}</option>
                </c:forEach>
            </select>
        </div>


    </div>

    <div class="confirmVenteOverlayFooter">

        <button class="btn btn-warning editNewAnnonce">Modifier mon annonce</button>
        <button class="btn btn-primary postOtherAnnonce">Poster une autre annonce</button>
        <button class="btn btn-danger delNewAnnonce">Supprimer cette annonce</button>
        <button class="btn btn-success">Retourner à la recherche</button>
    </div>
</div>