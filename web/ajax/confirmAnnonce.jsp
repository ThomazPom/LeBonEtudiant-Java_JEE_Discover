<%@page import="model.Annonce"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<% model.Annonce annonce = (model.Annonce) request.getAttribute("annonce"); %>


<% if (annonce.isTypeVente()) {%>
<input value="<%=annonce.getId()%>" name="idAnnonce" hidden form="formVente" id="idVenteAnnonce"/>
<div class="confirmVenteOverlay" style="">
    <% } else {%>
    <input value="<%=annonce.getId()%>" name="idAnnonce" hidden form="formDemande" id="idDemandeAnnonce"/>
    <div class="confirmDemandeOverlay" style="">
        <%}%>
        <%@include file="generalAnnonce.jsp" %>
        <div class="confirmAnnonceOverlayTitle">
            <div class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&#160;Titre</span>
                <p><b><%=annonce.getTitre()%></b></p>
            </div>

        </div>
        <div  class="confirmAnnonceOverlayBody">

            <div class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span>&#160;Vendeur</span>
                <p>
                    <%=annonce.getProprietaire().getPrenom() + " " + annonce.getProprietaire().getNom()%>
                </p>
            </div>

            <div class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-home"></span>&#160;Email</span>
                <p>
                    <%=annonce.getEmailOverride()%>
                </p>
            </div>
            <div class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&#160;N°de téléphone de contact</span>
                <p>
                    <%=annonce.getNumeroOverride()%>
                </p>
            </div>

            <div class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&#160;Date de publication</span>
                <p>
                    <%=annonce.getDatePublication()%>
                </p>
            </div>

            <div class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&#160;Date de fin</span>
                <p>
                    <%=annonce.getDateFin()%>
                </p>
            </div>

            <div class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-euro"></span>&#160;Prix</span>
                <p style="color:orange">
                    <%=annonce.getPrix()%> €
                </p>
            </div>
            <div   class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&#160;Description</span>
                <textarea readonly="" class="form-control"><%=annonce.getDescription()%></textarea>
            </div>
            <div class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-globe"></span>&#160;Lieux</span>
                <select multiple="multiple">
                    <c:forEach var="u" items="${annonce.getEtablissements()}"> 
                        <option value="${u.id}">${u.nom} (${u.getVille().getLibelle()},${u.getVille().getDepartement().getLibelle()})</option>
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



        <div class="confirmAnnonceOverlayFooter">
            <% if (annonce.isTypeVente()) {%>
            <button class="btn btn-primary postOtherVente">Poster une nouvelle offre</button>
            <c:if test="${isUserProprietaire}">
                <button class="btn btn-warning editVente">Modifier ma vente</button>
            </c:if>
            <% } else {%>
            <button class="btn btn-primary postOtherDemande">Poster une nouvelle demande</button>

            <c:if test="${isUserProprietaire}">
                <button class="btn btn-warning editDemande">Modifier ma recherche</button>
            </c:if>
            <%}%>
            <c:if test="${isUserProprietaire}">
                <div style="display: inline;">
                    <form style="display: inline;" method="post" name="formVente" action="AjaxServlet">
                        <input required="" value="${annonce.getId()}" name="idAnnonce" hidden/>
                        <input required name="activeAnnonce" value="${!annonce.isActive()}" hidden/>
                        <input required name="action" value="disableAnnonce" hidden/>
                        <c:choose>
                            <c:when test="${annonce.active}">
                                <input type="submit" class="btn btn-danger delAnnonce" value="Supprimer cette annonce">
                            </c:when>
                            <c:otherwise>
                                <input type="submit" class="btn btn-warning" value="Activer l'annonce">
                            </c:otherwise>
                        </c:choose>
                    </form>
                </div>
            </c:if>

            <button data-dismiss="modal" class="btn btn-success">Fermer cette page</button>
        </div>
    </div>