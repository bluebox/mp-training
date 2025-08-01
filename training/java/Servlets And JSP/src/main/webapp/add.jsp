<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body bgcolor="cyan">
	<%
		int num1 = 	Integer.parseInt(request.getParameter("number1")); 
		int num2 = 	Integer.parseInt(request.getParameter("number2")); 
		//out.print("Sum = " + (num1+num2));
	%>
	Sum = <%=(num1+num2) %>
	</body>
</html>