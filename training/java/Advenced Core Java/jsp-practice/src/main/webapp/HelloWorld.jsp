<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>

<%@page import="org.apache.jasper.compiler.Node.UseBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%-- page import="dev.tulasidhar.beans.UserBean" --%>


<%@ page errorPage="ErrorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Hello world</title>
	<%
		Context initialContext = new InitialContext();   
    	
		Context environmentContext = (Context) initialContext
            .lookup("java:comp/env");

   	 	String dataResourceName = "jdbc/servletsdb";
    	DataSource dataSource = (DataSource) environmentContext
            .lookup(dataResourceName);

    	Connection conn = dataSource.getConnection();
    	
    	PreparedStatement ps = conn.prepareStatement("INSERT INTO simple_users VALUES (?,?)");
    	ps.setString(1,request.getParameter("username"));
    	ps.setString(2,request.getParameter("email"));
    	
    	ps.addBatch();
    	
    	ps.setString(1,"Default User");
    	ps.setString(2,"DefaultEmail@gmail.com");
    	
    	ps.addBatch();
    	int result[] = ps.executeBatch();
    	
	%>
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
	<br/>
	<p>Users inserted : <% out.print(result[0] + " " + result[1]); %></p>
</body>
</html>