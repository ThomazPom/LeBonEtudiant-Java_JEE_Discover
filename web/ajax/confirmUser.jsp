<%@page import="model.Utilisateur"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@include file="generalAnnonce.jsp" %>

<%    model.Utilisateur userToView = (Utilisateur) request.getAttribute("userToView");
   //             request.getAttribute("isUserTheLoggedOne", canEdit);

%>
<input hidden value="<%=userToView.getLogin() %>" name="idUtilisateur" >
<div class="modal-body form-group">
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&#160;Email </span>
        <p>
            <%=userToView.getLogin()%>
        </p>
    </div>
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&#160;Nom</span>
        <p>
            <%=userToView.getNom()%>
        </p>
    </div>
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&#160;Prénom</span>
        <p>
            <%=userToView.getPrenom()%>
        </p>
    </div>
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&#160;Numéro de téléphonne</span>
        <p>
            <%=userToView.getNumtel()%>
        </p>
    </div>
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&#160;Role</span>
        <p>
            <%=userToView.getRole()%>
        </p>
    </div>
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-time"></span>&#160;Annonces publiées</span>
        <select multiple="multiple" readonly style="background: #FAFAFA">
            <c:forEach var="u" items="${userToView.getAnnonces()}"> 
                <option value="${u.id}">${u.getTitre()}</option>
            </c:forEach>
        </select>
    </div>
        
   
</div>
        <div class="modal-footer">
            <c:if test="${isUserTheLoggedOne}">
                
            <button class="btn btn-warning editUser">Editer cet utilisateur</button>
            </c:if>
            <button data-dismiss="modal" class="btn btn-success">Fermer cette page</button>
        </div>