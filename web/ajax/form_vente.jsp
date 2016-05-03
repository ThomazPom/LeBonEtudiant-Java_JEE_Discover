<%@page import="model.Annonce"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% Annonce anc = request.getAttribute("annonce") == null ? null : (Annonce) request.getAttribute("annonce");
    Boolean nwann = anc == null;
%>
    <form method="post" name="formVente" id="formVente" action="AjaxServlet">
        <input hidden value="<%=(nwann) ? "-1" : anc.getId() %>" name="idAnnonce"  id="idVenteAnnonce"/>
        <input required name="action" value="sendAnnonce" hidden/>
        <input required name="typeAnnonce" value="vente" hidden/>
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="modalVenteLabel">Vendre un objet :</h4>
        </div>
        <div class="modal-body">
            <div class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&nbsp;Titre</span>
                <input value="<%=(nwann) ? "" : anc.getTitre()%>" required name="titre" type="text" class="form-control" placeholder="Table de chevet bien stylée, bouteille de vin cassée.." aria-describedby="basic-addon1">
            </div>
            <div class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span>&nbsp;Email</span>
                <input value="<%=(nwann) ? "" : anc.getEmailOverride()%>"  name="email" type="email" class="form-control" placeholder="email@example.com" aria-describedby="basic-addon1">
            </div>
            <div class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&nbsp;N°de téléphone</span>
                <input value="<%=(nwann) ? "" : anc.getNumeroOverride()%>" name="telephone" type="text" class="form-control" placeholder="0682858320" aria-describedby="basic-addon1">
            </div>
            <div   class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&nbsp;Description</span>
                <textarea    required  name="description" class="form-control" width="100%" height="300px" placeholder="Dimensions, couleur, état.." aria-describedby="basic-addon1"><%=(nwann) ? "" : anc.getDescription()%></textarea>
            </div>


            <div   class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&nbsp;Date de fin</span>
                <input  value="<%=(nwann) ? "" : anc.getDateFin()%>"  readonly required name="date-fin" class="datepicker" placeholder="31/12/2005" aria-describedby="basic-addon1">
            </div>
            <div    class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-globe"></span>&nbsp;Lieux</span>
                <select required  name="etabSelectAnnonce" id="etabSelectVente"  multiple="multiple">
                    <%@include file="opt_etab.jsp" %>
                </select></div>
            <div  class="input-group prix">
                <table><tr>
                        <td style="width: 300px">
                            <span class="input-group-addon" id="basic-addon1" >
                                <span class="glyphicon glyphicon glyphicon-euro"></span>
                                &nbsp;Prix : &nbsp;
                                <input type="text" id="amount-annonce-vente" readonly>
                                <input  value="<%=(nwann) ? "3000" : anc.getPrix()%>"  name="amountAnnonce" type="text" id="hidden-amount-annonce-vente" hidden>
                                <div class="pricebuttons">
                                    <button type="button" class=".slideminus btn btn-default" aria-label="Left Align">
                                        <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                    </button>
                                    <button type="button" class=".slideplus btn btn-default" aria-label="Left Align">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                    </button>
                                </div></span>
                        <td style="padding-left: 50px">
                            <div id="slider-vente" class="sliderform"></div>
                        </td>
                    </tr>
                </table>

            </div>
            <div  class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;Catégorie(s) de l'objet</span>
                <select required name="categSelect-annonce" id="categSelect-vente"  multiple="multiple">
                    <%@include file="opt_categ.jsp" %>
                </select>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-warning effacerForm-Vente"><span class="glyphicon glyphicon-refresh"></span>&nbsp;Tout effacer</button>
            <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>&nbsp;Fermer</button>
            <input type="submit" class="btn btn-primary" id="sendAnnonce" value="Envoyer">
        </div>
    </form>
