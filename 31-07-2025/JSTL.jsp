<%@page import="java.util.Date" contentType="text/html"
	isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<center>
	<body bgcolor="pink">
		<c:out value="This is JSP" />

		<br />
		<c:set var="name" value="Prana" />
		My Name is
		<c:out value="${name}" />
		<br />
		<c:remove var="name" />
		My Name is
		<c:out value="${name}" />
		<br />
		<c:forEach var="i" begin="1" end="10" step="2">
			<c:out value="${i}" />
		</c:forEach>
		<br>
		<%
		int[] marks = { 10, 12, 15, 14, 9 };
		session.setAttribute("marks", marks);
		%>
		<c:forEach var="mark" items="${marks}">
			<c:out value="${mark}" />
		</c:forEach>
		<br>
		<c:import var="content" url="http://www.google.com/"/>
      <c:out value="${content}"/><br>
      <c:catch var="e">
            <%int x=2/0; %>
         </c:catch>
         <c:if test="${e!=null }">
            <c:out value="${e }"></c:out>
         </c:if><br>
         <c:set var="value" value="9"></c:set>
         <c:if test="${value >6}">
         </c:if>
         <c:out value="Qualified"/>
         <br>
         <c:set var="marks" value="80"/>
         <c:if test="${marks>33 }">
            Qualified!!
         </c:if><br>
	</body>
</center>

