<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.ConnectionEvent"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Enter details</title>
	<%@ page errorPage="ErrorPage.jsp" %>
</head>
<body>
	<%-- int i = 10/0; --%>
	
	<form method='POST' action='HelloWorld.jsp'>
		<label>Enter your name :</label>
		<input type='text' name='username'/>
		<input type='email' name='email'/>
		
		<input type='submit'/>
	</form>
	
</body>
</html>