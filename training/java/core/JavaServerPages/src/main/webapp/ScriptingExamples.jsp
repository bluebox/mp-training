<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Scripting</title>
</head>
<body>
	<%!int day = 6;%>
	<%
	if (day == 1 || day == 7 || day == 6) {
	%>
	<p>weekend</p>
	<%
	} else {
	%>
	<p>not weekend</p>
	<%
	}
	%>
</body>
</html>