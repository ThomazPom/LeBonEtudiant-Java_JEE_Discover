
<%@page import="java.util.List"%>
<%@page import="model.Etablissement"%>
<%@page import="model.Annonce"%>
<%@page import="model.Utilisateur"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% Annonce anc_opt_etab = request.getAttribute("annonce") == null ? null : (Annonce) request.getAttribute("annonce");
    
    List<Etablissement> lst_external = null;
    Boolean checked_external = anc_opt_etab!= null;
    if(checked_external)
    {
        lst_external=anc_opt_etab.getEtablissements();
    }
    else{
        Utilisateur uti_opt_etab = request.getAttribute("userToView") == null ? null :(Utilisateur)request.getAttribute("userToView");
        lst_external= (checked_external = uti_opt_etab!= null)?uti_opt_etab.getEtabsUser():null;
    }
    List<Etablissement> lst_etab= (List<Etablissement>)request.getAttribute("opt_etab");
    
    for(Etablissement etab: lst_etab)
    {
       boolean selected =checked_external?util.validator.comparatorEtab.contains(lst_external,etab, new util.validator.comparatorEtab()):false;
       out.println("<option "+ (selected?"selected":"") +" value="+etab.getId() +">"  + etab.getNom() +"</option>");
    
    }
    
    
%>