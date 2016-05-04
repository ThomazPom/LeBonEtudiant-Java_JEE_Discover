<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form method="post" name="formMajUtilisateur" id="formMajUser" action="AjaxServlet">

    <% model.Utilisateur userToView = (Utilisateur) request.getAttribute("userToView");
        Boolean userExist = userToView != null;%>
    <input hidden value="<%=userToView.getId()%>" name="idUtilisateur" id="idUtilisateur"/>

    <input required name="action" value="majUtilisateur" hidden/>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalMajUtilistateurLabel">Modifier le profil</h4>
    </div>
    <div class="modal-body">
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">@Email</span>
            <input value="<%=userExist ? userToView.getLogin() : ""%>" required type="email" name="email" class="form-control" placeholder="email@example.com" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Nom</span>
            <input value="<%=userExist ? userToView.getNom() : ""%>" required type="text" name="nom" class="form-control" placeholder="Nom" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Prénom</span>
            <input value="<%=userExist ? userToView.getPrenom() : ""%>" required type="text" name="prenom" class="form-control" placeholder="Prénom" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Nouveau mot de passe(Optionnel)</span>
            <input  type="password"  id="password" name="password" class="form-control" placeholder="*******" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Confirmation du nouveau mot de passe</span>
            <input  type="password"  id="confirm_password" name="confirm_password" class="form-control" placeholder="*******" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">Numéro de téléphone</span>
            <input value="<%=userExist ? userToView.getNumtel() : ""%>" required type="tel" name="telephonne" class="form-control" placeholder="0620350511" aria-describedby="basic-addon1">
        </div>

        <div  class="input-group" style="z-index: 300">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon glyphicon-globe"></span>&nbsp;Lieux</span>
            <select name="registerRegionSelect" id="registerRegionSelect" style="display:none" multiple="multiple">
                <%@include file="opt_etab.jsp" %>
            </select></div>
        

    </div>
            <div class="modal-footer">
            <button type="button" class="btn btn-warning effacerForm-Utilisateur"><span class="glyphicon glyphicon-refresh"></span>&nbsp;Réinitialiser</button>
            <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>&nbsp;Fermer</button>
            <input type="submit" class="btn btn-primary" value="Enregistrer" aria-describedby="basic-addon1">
        </div>
</form>