<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3 style="color:#cc0000;text-align: center">${annonces.size()} r�sultats de la recherche <span class="caret"></span> </h3>
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
    <div  class="panel-primary">

        <div class="collapseResult panel-collapse collapse in" role="tabpanel">
            <div>

                <table class="table tableResultAnnonce">
                    <thead>
                        <tr>
                            <th class="nosmall">#</th>
                            <th>Titre</th>
                            <th>Vendeur</th>
                            <th></th>
                            <th class="nosmall">Date</th>
                            <th>Prix</th>
                            <th class="nosmall">R�gion</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="a" items="${annonces}"> 
                            <tr>
                                <td class="idAnnonce nosmall">${a.getId()}</td>
                                <td>${a.getTitre()}</td>
                                <td>${a.getProprietaire().getPrenom()}&#160;${a.getProprietaire().getNom()}</td>
                                <td><button class="btn  btn-sm btn-default noBig"><span class="glyphicon glyphicon-eye-open"></span></button></td>
                                <td class="nosmall">${a.getDatePublication()}</td>
                                <td>${a.getPrix()}</td>
                                <td class="nosmall">${a.getEtablissements().get(0).getVille().getDepartement().getRegion().getLibelle()}</td>
                                <td class="hidden infoetabs">
                                    <c:forEach var="ae" items="${a.getEtablissements()}"> 
                                        <ul class="infoetab">

                                            <li class="idEtab">${ae.getId()}</li>
                                            <li class="nomEtab">${ae.getNom()}</li>
                                            <li class="lonEtab">${ae.getLongitudeX()}</li>
                                            <li class="latEtab">${ae.getLatitudeY()}</li>

                                        </ul>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>

        </div>

    </div>
</div>

