<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ page isErrorPage="true"%>
		<meta charset="UTF-8">
		<title>Error occurred</title>
	</head>
	
	<body>
		<h1>Error occurred somewhere so you're seeing this</h1>
		<p>
			Error details:
			<%
				out.print(exception.getMessage());
			%>
		</p>
	</body>
</html>