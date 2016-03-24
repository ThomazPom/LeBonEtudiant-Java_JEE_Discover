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
                    <li class="dropdown" style="width:300px;">
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
        <div class="container">
                 <div  class="input-group">
                                    <span class="input-group-addon" id="basic-addon1">Rechercher</span>
                                    <input id="mainsearch" type="text" class="form-control" placeholder="Des écouteurs pas trop cassés, des chaussettes jamais portées.."aria-describedby="basic-addon1">
                                </div>
                           
        </div>
        
    </div>
    <div id="map"></div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAOVh1mJ-DvhZOZUsb40ehjooUTUaCa3_M"></script>

    <script src="js/appjvs.js"></script>
</body>
</html>