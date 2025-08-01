<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exception</title>
</head>
<body>
	<%
	try {
		int k = 9 / 0;
	} catch (Exception e) {
		out.println("Exception : " + e.getMessage());
	}
	%>
</body>
</html>