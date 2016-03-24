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

                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Se connecter&nbsp;&nbsp;<span class="glyphicon glyphicon-log-in"></span> <span class="caret"></span></a>
                        <ul class="dropdown-menu" style="width:300px">

                            <li>
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon1">@Email</span>
                                    <input type="text" class="form-control" placeholder="email@example.com" aria-describedby="basic-addon1">
                                </div>
                            </li>
                            <li>

                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon1">Mot de passe</span>
                                    <input type="password" class="form-control" placeholder="*******" aria-describedby="basic-addon1">
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
                    <li class="dropdown" style="width:300px;margin-right: 30px">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">S'inscrire&nbsp;&nbsp;<span class="glyphicon glyphicon glyphicon-plus"></span><span class="caret"></span></a>
                        <ul class="dropdown-menu">

                            <li>
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon1">@Email</span>
                                    <input type="text" class="form-control" placeholder="email@example.com" aria-describedby="basic-addon1">
                                </div>
                            </li>
                            <li>
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon1">Nom</span>
                                    <input type="text" class="form-control" placeholder="Nom" aria-describedby="basic-addon1">
                                </div></li>
                            <li>
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon1">Prénom</span>
                                    <input type="text" class="form-control" placeholder="Prénom" aria-describedby="basic-addon1">
                                </div>
                            </li>
                            <li>
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon1">Mot de passe</span>
                                    <input type="password" class="form-control" placeholder="*******" aria-describedby="basic-addon1">
                                </div>
                            </li>
                            <li>
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon1">Confirmation</span>
                                    <input type="password" class="form-control" placeholder="*******" aria-describedby="basic-addon1">
                                </div>

                            </li>
                            <li>             <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-plus"></span></span>
                                    <input type="submit" class="form-control" value="enregistrement" aria-describedby="basic-addon1">
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <div id="search">
        <div id="maincontainer" class="container">
            <div  class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-search"></span>&nbsp;Rechercher</span>
                <input id="mainsearch" type="text" class="form-control" placeholder="Des écouteurs pas trop cassés, des chaussettes jamais portées.."aria-describedby="basic-addon1">
            </div>

            <div  class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-globe"></span>&nbsp;Région</span>
                <select id="regionSelect" style="display:none" multiple="multiple">
                    <option value="06">Alpes maritimes</option>
                    <option value="29">Bretagne</option>
                    <option value="75">Ile de france</option>
                    <option value="83">Var</option>
                    <option value="13">Marseille</option>
                    <option value="AA">Exemple</option>
                </select></div>
<div  class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-euro"></span>&nbsp;Prix : &nbsp;<input type="text" id="amount" readonly style="border:0; color:red;background-color: transparent; font-weight:bold;"></span>
                <div id="slider-range" class="sliderform"></div>
            </div>
            <div  class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;Catégories</span>
                <select id="categSelect" style="display:none" multiple="multiple">
                    <option value="06">Vêtements</option>
                    <option value="29">Informatique</option>
                    <option value="75">Automobile</option>
                    <option value="83">Multimédia</option>
                    <option value="13">Meubles</option>
                    <option value="AA">Mobile</option>
                </select></div>
            <div id="mainxjspreceiver"></div>
            
            
        </div>

    </div>
    <div id="map"></div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAOVh1mJ-DvhZOZUsb40ehjooUTUaCa3_M"></script>

    <!-- Include the plugin's CSS and JS: -->
    <script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="css/bootstrap-multiselect.css" type="text/css"/>
    <script src="js/appjvs.js"></script>
</body><link rel="stylesheet" href="js/jui/jquery-ui.min.css">
</body><link rel="stylesheet" href="js/jui/jquery-ui.theme.css">
<script src="js/jui/jquery-ui.min.js"></script>
</html>