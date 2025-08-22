<%@ page language="java" import="java.lang.Integer" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="AddNumbersjsp.html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adding numbers</title>
</head>
<body>
<% 
int x= Integer.parseInt(request.getParameter("number1"));
int y=Integer.parseInt(request.getParameter("number2"));
%>

<h2>result of two numbers 
<% 
out.println(x+" and "+y+" is: ");
%>

<%=x+y %>
</h2>

</body>
</html>