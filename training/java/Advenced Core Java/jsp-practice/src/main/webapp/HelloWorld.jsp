<%@page import="org.apache.jasper.compiler.Node.UseBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page import="dev.tulasidhar.beans.UserBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello world</title>
</head>
<body>
	<h1>Hello world this is my first JSP project</h1>
	
	<jsp:useBean id="firstUser" class="dev.tulasidhar.beans.UserBean">
		<jsp:setProperty  name="firstUser" property="*"/>	
		
	</jsp:useBean>
	
	<h2>your name is <jsp:getProperty  name="firstUser" property="username"></jsp:getProperty></h2>
	<c:out value="|Random thing printed with JSTL"></c:out>
	<c:set var="age" value="21"></c:set>
	<c:out value=" | my age is age ${age}"></c:out>
</body>
</html>