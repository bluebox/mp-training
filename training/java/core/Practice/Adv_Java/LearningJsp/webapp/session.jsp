<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Linking</title>
</head>
<body>
<%
	String name=request.getParameter("name");
	String price=request.getParameter("price");
	session.setAttribute("name", name);
	session.setAttribute("price", price);
%>
<a href="useBean.jsp">Second Page</a>
</body>
</html>