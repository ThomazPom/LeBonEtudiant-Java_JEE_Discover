<%-- 
    Document   : admin
    Created on : 11 avr. 2016, 11:57:40
    Author     : Benoit
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

        <!-- Librairy Dropzone -->
        <!-- Drag & Drop Upload Image -->
        <script src="./js/dropzone.js"></script>
        <link rel="stylesheet" href="./css/dropzone.css">
        
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
                <a class="navbar-brand" style="color:cyan" href="#">LeBonEtudiant<b style="color:white">.com ! Administration</b></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <%if (session.getAttribute("userlogged") == null) {%>
                

                <%} else {%>
                <c:set var="userlog" value='${session.getAttribute("userlogged")}'></c:set>
                    <ul class="nav navbar-nav navbar-right">

                        <li>  
                        <% String username = "John Smith ";%>
                        <% username = session.getAttribute("userlogged").toString();%>
                        <a class="navbar-brand" href="#">Bienvenue,  <%=username%> ! <span class="glyphicon glyphicon-cog"></a>
                    </li>
                    <li>  
                        <button class="btnav btn btn-primary" action="index.jsp">Accueil</button>
                    </li>

                    <li>  
                        <form method=post action="StaticServlet?action=disconnect">
                            <input type="submit" class="btnav btn btn-danger" value="Se déconnecter"/>
                        </form>
                    </li>
                    
                    <button type="button" class="btnav btn btn-info dropdown-toggle" data-toggle = "dropdown"><span class="glyphicon glyphicon-asterisk">&nbsp;</span><span class = "caret"></span></button>
                        <ul class = "dropdown-menu">
                            <li><a href = "admin.jsp">Annonces</a></li>
                            <li><a href = "admin.jsp">Membres</a></li>
                        </ul>
                    <!-- Button trigger modal -->
                </ul>
                <%}%>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
        
        
        <h3>Résultats de la recherche :</h3>
        <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Titre</th>
                    <th>Vendeur</th>
                    <th>Date</th>
                    <th>Prix</th>
                    <th>Région</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="a" items="${annonces}"> 
                    <tr>
                        <td>${a.getId()}</td>
                        <td>${a.getTitre()}</td>
                        <td>${a.getProprietaire().getPrenom()}&#160;${a.getProprietaire().getNom()}</td>
                        <td>${a.getDatePublication()}</td>
                        <td>${a.getPrix()}</td>
                        <td>${a.getEtablissements().get(0).getVille().getDepartement().getRegion().getLibelle()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
