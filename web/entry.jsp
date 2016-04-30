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
            <% }
            %>
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
                            <div  class="searchLieuxContainer"><span class="input-group-addon" >Etablissements</span> <select   name="etabSelectSearch"  id="etabSelectSearch" style="display:none" multiple="multiple"><%@include file="ajax/opt_etab.jsp" %></select>
                                <span class="input-group-addon" >Villes </span><select   name="villeSelectSearch"  id="villeSelectSearch" style="display:none" multiple="multiple"><%@include file="ajax/opt_ville.jsp" %></select>
                            </div><div class="searchLieuxContainer"><span class="input-group-addon" >Départements </span><select   name="deptSelectSearch"  id="deptSelectSearch" style="display:none" multiple="multiple"> <%@include file="ajax/opt_ville.jsp" %> </select>
                                <span class="input-group-addon" >Régions</span><select   name="regionSelectSearch"  id="regionSelectSearch" style="display:none" multiple="multiple"><%@include file="ajax/opt_region.jsp" %> </select>
                            </div></div>
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
                                        <input hidden  readonly type="text" name="prixmin-search"/>
                                        <input hidden readonly type="text" name="prixmax-search"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div  class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;Catégories</span>
                            <select id="categSelect" name="categSelectSearch" style="display:none" multiple="multiple">
                                <%@include file="ajax/opt_categ.jsp" %>
                            </select>
                        </div>
                        <div class="input-group" id="radioGroupSelecTypAnn" style="z-index: 100">
                            <span class="input-group-addon" id="basic-addon1" >Afficher :</span>
                            <input type="radio" id="radioSelecTypAnnVente" value="vente" name="radioSelecTypAnn"><label for="radioSelecTypAnnVente">Les ventes</label>
                            <input type="radio" id="radioSelecTypAnnAll" value="all" name="radioSelecTypAnn" checked="checked"><label for="radioSelecTypAnnAll">Tous</label>
                            <input type="radio" id="radioSelecTypAnnDemande" value="demande" name="radioSelecTypAnn"><label for="radioSelecTypAnnDemande">Les demandes</label>
                        </div>
                    </div>

                </form>
                <div id="search"><div id="mainxjspreceiver" class="container-fluid"></div></div>
            </div>
        </div>
        <div id="map"></div>
        <form  method="post" action="AjaxServlet" class="mapInfoWindowContent" id="infoWindOrigin" name="infowindowsearch">
            <input hidden name="etabSelectSearch"/>
            <input hidden name="action" value="searchAnnonce"/>
            <div class="input-group">

                <span class="input-group-addon" >Rechercher: </span>
                <input name="searchtitre" type="text" class="form-control">
            </div>
            <div class="container-fluid infowindowResult"></div>
        </form>
        <%@include file="inc/modalswrapper.jsp" %>
    </body>
</html>
<%@include file="inc/footerscripts.jspf" %>