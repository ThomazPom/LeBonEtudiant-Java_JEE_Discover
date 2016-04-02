<%-- 
    Document   : index
    Created on : 22 mars 2016, 20:42:46
    Author     : Thomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>LeBonEtudiant</title>

        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/appcss.css" rel="stylesheet">

        <link href="css/font-awesome.min.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
    <nav class="navbar navbar-fixed-top navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" style="color:cyan" href="#">LeBonEtudiant<b style="color:white">.com !</b></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <%if (session.getAttribute("userlogged") == null) {%>
                <form method="post" action="StaticServlet?action=registerUser" >

                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown" style="margin-right: 30px">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">S'inscrire&nbsp;&nbsp;<span class="glyphicon glyphicon glyphicon-plus"></span><span class="caret"></span></a>
                            <ul class="offmenu dropdown-menu">

                                <li>
                                    <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1">@Email</span>
                                        <input required type="email" name="email" class="form-control" placeholder="email@example.com" aria-describedby="basic-addon1">
                                    </div>
                                </li>
                                <li>
                                    <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1">Nom</span>
                                        <input required type="text" name="nom" class="form-control" placeholder="Nom" aria-describedby="basic-addon1">
                                    </div></li>
                                <li>
                                    <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1">Prénom</span>
                                        <input required type="text" name="prenom" class="form-control" placeholder="Prénom" aria-describedby="basic-addon1">
                                    </div>
                                </li>
                                <li>
                                    <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1">Mot de passe</span>
                                        <input required type="password" id="password" name="password" class="form-control" placeholder="*******" aria-describedby="basic-addon1">
                                    </div>
                                </li>
                                <li>
                                    <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1">Confirmation</span>
                                        <input required type="password"  id="confirm_password" class="form-control" placeholder="*******" aria-describedby="basic-addon1">
                                    </div>
                                </li>
                                <li>
                                    <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1">N°Tel</span>
                                        <input required type="tel" name="telephonne" class="form-control" placeholder="0620350511" aria-describedby="basic-addon1">
                                    </div>

                                </li>
                                <li>
                                    <div  class="input-group" style="z-index: 300">
                                        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-globe"></span>&nbsp;Lieux</span>
                                        <select name="registerRegionSelect" id="registerRegionSelect" style="display:none" multiple="multiple">
                                        </select></div>
                                </li>
                                <li> 
                                    <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-plus"></span></span>
                                        <input type="submit" class="form-control" value="enregistrement" aria-describedby="basic-addon1">
                                    </div>
                                </li>

                            </ul>
                        </li>
                    </ul>
                </form>
                <form method="post" action="StaticServlet?action=connect">

                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">


                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Se connecter&nbsp;&nbsp;<span class="glyphicon glyphicon-log-in"></span> <span class="caret"></span></a>
                            <ul class="offmenu dropdown-menu">

                                <li>
                                    <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1">@Email</span>
                                        <input required type="email" name="email" class="form-control" placeholder="email@example.com" aria-describedby="basic-addon1">
                                    </div>
                                </li>
                                <li>

                                    <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1">Mot de passe</span>
                                        <input required type="password" name="password" class="form-control" placeholder="*******" aria-describedby="basic-addon1">
                                    </div>

                                </li>
                                <li>

                                    <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-log-in"></span></span>
                                        <input type="submit" class="form-control" value="connexion" aria-describedby="basic-addon1">
                                    </div>

                                </li>
                            </ul>

                        </li>
                    </ul>
                </form>

                <%} else {%>
                <c:set var="userlog" value='${session.getAttribute("userlogged")}'></c:set>
                    <ul class="nav navbar-nav navbar-right">

                        <li>  
                        <% String username = "John Smith ";%>
                        <% username = session.getAttribute("userlogged").toString();%>
                        <a class="navbar-brand" href="#">Bienvenue,  <%=username%> ! <span class="glyphicon glyphicon-cog"></a>
                    </li>
                    <li>  
                        <button class="btnav btn btn-primary"  data-toggle="modal" data-target="#modalVente">Vendre un objet</button>

                    </li>

                    <li>  
                        <form method=post action="StaticServlet?action=disconnect">
                            <input type="submit" class="btnav btn btn-danger" value="Se déconnecter"/>
                        </form>
                    </li>
                    <!-- Button trigger modal -->


                </ul>
                <%}%>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
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
            <div id="mainwrap">
                <div id="maincontainer" class="container">
                    <div  class="input-group">
                        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-search"></span>&nbsp;Rechercher</span>
                        <input id="mainsearch" type="text" class="form-control" placeholder="Des écouteurs pas trop cassés, des chaussettes jamais portées.."aria-describedby="basic-addon1">
                    </div>

                    <div  class="input-group" style="z-index: 300">
                        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-globe"></span>&nbsp;Lieux</span>
                        <select id="regionSelect" style="display:none" multiple="multiple">
                        </select></div>
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
                        </select></div>

                </div>
            </div>
            <div id="search"><div id="mainxjspreceiver" class="container"></div></div>
        </div>
    </div>
    <div id="map"></div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/11.3_jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAOVh1mJ-DvhZOZUsb40ehjooUTUaCa3_M"></script>

    <!-- Include the plugin's CSS and JS: -->
    <script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="css/bootstrap-multiselect.css" type="text/css"/>
    <script src="js/appjvs.js"></script>


    <%if (session.getAttribute("userlogged") == null) {%>

    <%} else {%>
    <!-- Modal -->
    <div class="modal fade" id="modalVente" tabindex="-1" role="dialog" aria-labelledby="modalVenteLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div id="mcvente" class="modal-content">


                <input value="-1" name="idVenteAnnonce" form="formVente" id="idVenteAnnonce"/>

                <form method="post" name="formVente" id="formVente" action="AjaxServlet?action=sendVente">

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
                            <span class="input-group-addon" id="basic-addon1">@Email de contact</span>
                            <input  name="email" type="email" class="form-control" placeholder="email@example.com" aria-describedby="basic-addon1">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">N°de téléphone de contact</span>
                            <input name="telephone" type="text" class="form-control" placeholder="0682858320" aria-describedby="basic-addon1">
                        </div>

                        <div   class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-edit"></span>&nbsp;Description</span>
                            <textarea  required  name="description" class="form-control" width="100%" height="300px" placeholder="Dimensions, couleur, état.." aria-describedby="basic-addon1"></textarea>
                        </div>
                        <div    class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-globe"></span>&nbsp;Lieux</span>
                            <select  required  name="regionSelect-vente" id="regionSelect-vente"  multiple="multiple">
                            </select></div>
                        <div  class="input-group prix">

                            <table><tr>
                                    <td style="width: 300px">
                                        <span class="input-group-addon" id="basic-addon1" >
                                            <span class="glyphicon glyphicon glyphicon-euro"></span>
                                            &nbsp;Prix : &nbsp;
                                            <input type="text" id="amount-vente" readonly>
                                            <input name="amount-vente" type="text" id="hidden-amount-vente" hidden>
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
                            <select required name="categSelect-vente" id="categSelect-vente"  multiple="multiple">
                            </select></div>
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
    <%}%>
</body><link rel="stylesheet" href="js/jui/jquery-ui.min.css">
</body><link rel="stylesheet" href="js/jui/jquery-ui.theme.css">
<script src="js/jui/jquery-ui.min.js"></script>
</html>