<%-- 
    Document   : needToConnect.jsp
    Created on : 1 mai 2016, 12:56:26
    Author     : Thomas
--%>
<div class="modal-body">
    <h1>Oops !</h1>
    <h2>  Cette fonctionalité n'est disponible qu'aux membres inscrits!</h2>


    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingOne">
                <h4 class="panel-title">
                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        <b>Merci de vous connecter <span class="caret"></span> </b>
                    </a>
                </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                <div class="panel-body">
                    <form method="post" action="StaticServlet?action=connect">



                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">@Email</span>
                            <input required type="email" name="email" class="form-control" placeholder="email@example.com" aria-describedby="basic-addon1">
                        </div>



                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">Mot de passe</span>
                            <input required type="password" name="password" class="form-control" placeholder="*******" aria-describedby="basic-addon1">
                        </div>




                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-log-in"></span></span>
                            <input type="submit" class="form-control" value="connexion" aria-describedby="basic-addon1">
                        </div>




                    </form>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingTwo">
                <h4 class="panel-title">
                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        <b>Ou de vous enregistrer <span class="caret"></span> </b>

                    </a>
                </h4>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                <div class="panel-body">


                    <form name="registerModal" method="post" action="StaticServlet?action=registerUser" >


                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">@Email</span>
                            <input required type="email" name="email" class="form-control" placeholder="email@example.com" aria-describedby="basic-addon1">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">Nom</span>
                            <input required type="text" name="nom" class="form-control" placeholder="Nom" aria-describedby="basic-addon1">
                        </div>

                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">Prénom</span>
                            <input required type="text" name="prenom" class="form-control" placeholder="Prénom" aria-describedby="basic-addon1">
                        </div>


                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">Mot de passe</span>
                            <input required type="password" id="password"  name="password" class="form-control" placeholder="*******" aria-describedby="basic-addon1">
                        </div>


                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">Confirmation</span>
                            <input required type="password" name="confirm_password"  id="confirm_password" class="form-control" placeholder="*******" aria-describedby="basic-addon1">
                        </div>


                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">N°Tel</span>
                            <input required type="tel" name="telephonne" class="form-control" placeholder="0620350511" aria-describedby="basic-addon1">
                        </div>



                        <div  class="input-group" style="z-index: 300">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-globe"></span>&nbsp;Lieux</span>
                            <select name="registerRegionSelect" id="registerRegionSelect" style="display:none" multiple="multiple">
                                <%@include file="/ajax/opt_etab.jsp" %>
                            </select></div>


                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-plus"></span></span>
                            <input type="submit" class="form-control" value="enregistrement" aria-describedby="basic-addon1">
                        </div>



                    </form>
                </div>
            </div>
        </div>
    </div>



</div>