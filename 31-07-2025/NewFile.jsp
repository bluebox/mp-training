<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Hello! The time is now <%= new java.util.Date() %>
<% System.out.print("Welcome to JSP World "); %>
<%! int x=9,y=1,z ;%>
<%= z=x+y  %>
<% out.print("Addition of "+x+" and "+y+" is :"+z); %>
</body>
</html>