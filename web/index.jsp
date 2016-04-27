<%-- 
    Document   : index
    Created on : 22 mars 2016, 20:42:46
    Author     : Thomas
--%>

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <%@include file="inc/headerscripts.jspf"  %>
    </head>
    <body><%@include file="inc/bodyheader.jspf" %>
        
    <div id="wrapper">

        <%  if (session.getAttribute("success") != null) {%><div class="alert alert-success" role="alert"> <%out.println(session.getAttribute("success"));
            session.removeAttribute("message");
            session.removeAttribute("success");%></div>
            <%}
            if (session.getAttribute("info") != null) {%><div class="alert alert-info" role="alert"><%out.println(session.getAttribute("info")); %><% session.removeAttribute("info");%></div>
        <%}
            if (session.getAttribute("warning") != null) {%><div class="alert alert-warning" role="alert"><%out.println(session.getAttribute("warning")); %><% session.removeAttribute("warning");%></div>
        <%}
            if (session.getAttribute("danger") != null) {%><div class="alert alert-danger" role="alert"><%out.println(session.getAttribute("danger")); %><% session.removeAttribute("danger");%></div>
        <% } %>
        <div id="lesswrap">
            <form method="post" action="AjaxServlet" id="mainwrap">
                <input hidden name="action" value="searchAnnonce"/>
                <div id="maincontainer" class="container">
                    <div  class="input-group">
                        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-search"></span>&nbsp;Rechercher</span>
                        <input id="mainsearch" name="searchtitre"  type="text" class="form-control" placeholder="Des écouteurs pas trop cassés, des chaussettes jamais portées.."aria-describedby="basic-addon1">
                    </div>

                    <div class="input-group"  style="z-index: 300">

                        <span class="input-group-addon" ><span class="glyphicon glyphicon glyphicon-globe"></span>&nbsp;Lieux</span>
                        <div  class="searchLieuxContainer"><span class="input-group-addon" >Etablissements</span> <select   name="etabSelectSearch"  id="etabSelectSearch" style="display:none" multiple="multiple"></select>
                            <span class="input-group-addon" >Villes </span><select   name="villeSelectSearch"  id="villeSelectSearch" style="display:none" multiple="multiple"></select>
                        </div><div class="searchLieuxContainer"><span class="input-group-addon" >Départements </span><select   name="deptSelectSearch"  id="deptSelectSearch" style="display:none" multiple="multiple"></select>
                            <span class="input-group-addon" >Régions</span><select   name="regionSelectSearch"  id="regionSelectSearch" style="display:none" multiple="multiple"></select>
                            </select></div></div>
                    <div  class="input-group prix">

                        <table><tr>

                                <td style="width: 370px">
                                    <span class="input-group-addon" id="basic-addon1" >
                                        <span class="glyphicon glyphicon glyphicon-euro"></span>
                                        &nbsp;Prix : &nbsp;<input type="text" id="amount" readonly><div class="pricebuttons">
                                            <button type="button" class=".slideminus btn btn-default" aria-label="Left Align">
                                                <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                            </button>
                                            <button type="button" class=".slideplus btn btn-default" aria-label="Left Align">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                            </button>
                                        </div></span>
                                <td style="padding-left: 50px">
                                    <div id="slider-range" class="sliderform"></div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div  class="input-group">
                        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;Catégories</span>
                        <select id="categSelect" style="display:none" multiple="multiple">
                        </select>
                    </div>
                    <div class="input-group" id="radioGroupSelecTypAnn" style="z-index: 100">
                        <span class="input-group-addon" id="basic-addon1" >Afficher :</span>
                        <input type="radio" id="radioSelecTypAnnVente" name="radioSelecTypAnn"><label for="radioSelecTypAnnVente">Les ventes</label>
                        <input type="radio" id="radioSelecTypAnnAll" name="radioSelecTypAnn" checked="checked"><label for="radioSelecTypAnnAll">Tous</label>
                        <input type="radio" id="radioSelecTypAnnDemande" name="radioSelecTypAnn"><label for="radioSelecTypAnnDemande">Les demandes</label>
                    </div>
                </div>

            </form>
            <div id="search"><div id="mainxjspreceiver" class="container"></div></div>
        </div>
    </div>
    <div id="map"></div>



    <%if (session.getAttribute("userlogged") == null) {%>

    <%} else {%>
    <!-- Modal -->
    <div class="modal fade" id="modalVente" tabindex="-1" role="dialog" aria-labelledby="modalVenteLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div id="mcvente" class="modal-content">
                <input hidden value="-1" name="idAnnonce" form="formVente" id="idVenteAnnonce"/>
                <form method="post" name="formVente" id="formVente" action="AjaxServlet">
                    <input required name="action" value="sendAnnonce" hidden/>
                    <input required name="typeAnnonce" value="vente" hidden/>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="modalVenteLabel">Vendre un objet :</h4>
                    </div>
                    <div class="modal-body">
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&nbsp;Titre</span>
                            <input required name="titre" type="text" class="form-control" placeholder="Table de chevet bien stylée, bouteille de vin cassée.." aria-describedby="basic-addon1">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span>&nbsp;Email</span>
                            <input  name="email" type="email" class="form-control" placeholder="email@example.com" aria-describedby="basic-addon1">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&nbsp;N°de téléphone</span>
                            <input name="telephone" type="text" class="form-control" placeholder="0682858320" aria-describedby="basic-addon1">
                        </div>
                        <div   class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&nbsp;Description</span>
                            <textarea  required  name="description" class="form-control" width="100%" height="300px" placeholder="Dimensions, couleur, état.." aria-describedby="basic-addon1"></textarea>
                        </div>


                        <div   class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&nbsp;Date de fin</span>
                            <input readonly required name="date-fin" class="datepicker" placeholder="31/12/2005" aria-describedby="basic-addon1">
                        </div>
                        <div    class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-globe"></span>&nbsp;Lieux</span>
                            <select  required  name="etabSelectAnnonce" id="etabSelectVente"  multiple="multiple">
                            </select></div>
                        <div  class="input-group prix">
                            <table><tr>
                                    <td style="width: 300px">
                                        <span class="input-group-addon" id="basic-addon1" >
                                            <span class="glyphicon glyphicon glyphicon-euro"></span>
                                            &nbsp;Prix : &nbsp;
                                            <input type="text" id="amount-annonce-vente" readonly>
                                            <input name="amountAnnonce" type="text" id="hidden-amount-annonce-vente" hidden>
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
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning effacerForm-Vente"><span class="glyphicon glyphicon-refresh"></span>&nbsp;Tout effacer</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>&nbsp;Close</button>
                        <input type="submit" class="btn btn-primary" id="sendAnnonce" value="Envoyer">
                    </div>
                </form>    
            </div>
        </div>
    </div>


    <div class="modal fade" id="modalDemande" tabindex="-1" role="dialog" aria-labelledby="modalDemandeLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div id="mcdemande" class="modal-content">
                <input hidden value="-1" name="idAnnonce" form="formDemande" id="idDemandeAnnonce"/>

                <form method="post" name="formDemande" id="formDemande" action="AjaxServlet">
                    <input required name="action" value="sendAnnonce" hidden/>
                    <input required name="typeAnnonce" value="demande" hidden/>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="modalDemandeLabel">Rechercher un objet, un service</h4>
                    </div>
                    <div class="modal-body">
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&nbsp;Titre</span>
                            <input required name="titre" type="text" class="form-control" placeholder="Table de chevet bien stylée, bouteille de vin cassée.." aria-describedby="basic-addon1">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"></span>&nbsp;Email</span>
                            <input  name="email" type="email" class="form-control" placeholder="email@example.com" aria-describedby="basic-addon1">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&nbsp;N°de téléphone</span>
                            <input name="telephone" type="text" class="form-control" placeholder="0682858320" aria-describedby="basic-addon1">
                        </div>
                        <div   class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&nbsp;Description</span>
                            <textarea  required  name="description" class="form-control" width="100%" height="300px" placeholder="Dimensions, couleur, état.." aria-describedby="basic-addon1"></textarea>
                        </div>


                        <div   class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&nbsp;Date de fin</span>
                            <input readonly required name="date-fin" class="datepicker" placeholder="31/12/2005" aria-describedby="basic-addon1">
                        </div>
                        <div    class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-globe"></span>&nbsp;Autour de :</span>
                            <select  required  name="etabSelectAnnonce" id="etabSelectDemande"  multiple="multiple">
                            </select></div>
                        <div  class="input-group prix">
                            <table><tr>
                                    <td style="width: 300px">
                                        <span class="input-group-addon" id="basic-addon1" >
                                            <span class="glyphicon glyphicon glyphicon-euro"></span>
                                            &nbsp;A moins de : &nbsp;
                                            <input type="text" id="amount-annonce-demande" readonly>
                                            <input name="amountAnnonce" type="text" id="hidden-amount-annonce-demande" hidden>
                                            <div class="pricebuttons">
                                                <button type="button" class=".slideminus btn btn-default" aria-label="Left Align">
                                                    <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                                </button>
                                                <button type="button" class=".slideplus btn btn-default" aria-label="Left Align">
                                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                                </button>
                                            </div></span>
                                    <td style="padding-left: 50px">
                                        <div id="slider-demande" class="sliderform"></div>
                                    </td>
                                </tr>
                            </table>

                        </div>
                        <div  class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;Catégorie(s) de l'objet</span>
                            <select required name="categSelect-annonce" id="categSelect-demande"  multiple="multiple">
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning effacerForm-Demande"><span class="glyphicon glyphicon-refresh"></span>&nbsp;Tout effacer</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>&nbsp;Close</button>
                        <input type="submit" class="btn btn-primary" id="sendAnnonce" value="Envoyer">
                    </div>
                </form>    
            </div>
        </div>
    </div>

    <%}%>
</body>
</html>
<%@include file="inc/footerscripts.jspf" %>