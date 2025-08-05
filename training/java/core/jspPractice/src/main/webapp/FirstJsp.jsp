<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
	<%
	Date date=new Date();
	%>
	<%= date %>
	</h1>
	<c:forEach var="i" begin="1" end="10">
	<p> <c:out value="${i}"/>
	</p>
	</c:forEach>
</body>
</html>