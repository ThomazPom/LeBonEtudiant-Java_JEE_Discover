
<%@page import="java.util.List"%>
<%@page import="model.Etablissement"%>
<%@page import="model.Annonce"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% Annonce anc_opt_etab = request.getAttribute("annonce") == null ? null : (Annonce) request.getAttribute("annonce");
    Boolean checked_anc_opt_etab = anc_opt_etab!= null;
    List<Etablissement> lst_etab= (List<Etablissement>)request.getAttribute("opt_etab");
    for(Etablissement etab: lst_etab)
    {
       boolean selected =checked_anc_opt_etab?util.validator.comparatorEtab.contains(anc_opt_etab.getEtablissements(),etab, new util.validator.comparatorEtab()):false;
       out.println("<option "+ (selected?"selected":"") +" value="+etab.getId() +">"  + etab.getNom() +"</option>");
    
    }
    
    
%>