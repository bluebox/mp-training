<%@ page language="java" import="java.util.Scanner" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.util.Arrays" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%! 
		int k=123; 
	%>
	<%= "My fav number is"+ k %>

	<% 
		int i=Integer.parseInt(request.getParameter("num1"));
		int j=Integer.parseInt(request.getParameter("num2"));
		out.println("<h2> Sum of two numbers is: "+(i+j)+"</h2>");
		int error=9/0;
	%>
</body>
</html>
<%@ include file="header.jsp" %>