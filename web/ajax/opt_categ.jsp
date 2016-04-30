<%@page import="model.Categorie"%>
<%@page import="java.util.List"%>
<%@page import="model.Annonce"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% Annonce anc_opt_categ = request.getAttribute("annonce") == null ? null : (Annonce) request.getAttribute("annonce");
    Boolean checked_anc_opt_categ = anc_opt_categ != null;
    List<Categorie> lst_categ= (List<Categorie>)request.getAttribute("opt_categ");
    for(Categorie categ :lst_categ)
    {
      boolean selected = checked_anc_opt_categ?util.validator.comparatorCateg.contains(anc_opt_categ.getCategories(),categ, new util.validator.comparatorCateg()):false;
       out.println("<option "+ (selected?"selected":"") +" value="+categ.getId() +">"  + categ.getLibelle() +"</option>");
    
    }
    
    
%>