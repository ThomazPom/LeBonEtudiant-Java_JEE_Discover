<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <c:forEach var="u" items="${opt_ville}"> 
            <option value="${u.id}">${u.libelle}</option>

    </c:forEach>