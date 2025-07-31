<%@page import= "java.util.Date"  contentType = "text/html"  isELIgnored = "true"%>

<%Date d = new Date();%>
<%=d%>
<%="<br/>"%>
<%= "This is JSP" %>
<%="<br/>"%>
<%= getServletInfo()%>
<%="<br/>"%>
<%= "LOCAL CONTENT"%>
<%@include file = "a.html"%>
<%= "LOCAL CONTENT" %>