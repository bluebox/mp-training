<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Scanner"%>
<%@ page errorPage="Error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="blue">
	<%!//this is decleration
	%>
	<h1>Hello world</h1>
	<%
	//this is scriptlet
	int num1=Integer.parseInt(request.getParameter("num1"));
	String msg="this is the msg i stored in string and using <%= to print directly in th html page";
	int num2=Integer.parseInt(request.getParameter("num2"));
	out.println("<h3>Result:</h3>");
	out.println(num1/num2);
	Scanner sc=new Scanner(System.in);
	%>
<h2>this is string is printed directly</h2>	
<h3><%=msg %></h3>

</body>
</html>