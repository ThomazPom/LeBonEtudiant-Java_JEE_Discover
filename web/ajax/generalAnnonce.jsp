<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <%  if (session.getAttribute("success") != null) {%><div class="alert alert-success" role="alert"> <%out.println(session.getAttribute("success"));
            session.removeAttribute("message");
            session.removeAttribute("success");%></div>
        <%}
                if (session.getAttribute("info") != null) {%><div class="alert alert-info" role="alert"><%out.println(session.getAttribute("info")); %><% session.removeAttribute("info");%></div>
        <%}
            if (session.getAttribute("warning") != null) {%><div class="alert alert-warning" role="alert"><%out.println(session.getAttribute("warning")); %><% session.removeAttribute("warning");%></div>
        <%}
            if (session.getAttribute("danger") != null) {%><div class="alert alert-danger" role="alert"><%out.println(session.getAttribute("danger")); %><% session.removeAttribute("danger");%></div>
        <%}
            if (session.getAttribute("html") != null) {%><%out.println(session.getAttribute("html"));  session.removeAttribute("html");}
            if (session.getAttribute("html2") != null) {%><%out.println(session.getAttribute("html2"));  session.removeAttribute("html2");}
            
            %>
        