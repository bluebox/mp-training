<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Member Result</title>
</head>
<body>


	<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
    <p style="color: red; text-align: center;"><%= error %></p>
<% }%><% else{ %>
<h3 align="center">Member updated successfully</h3>
<%} %>
</body>
</html>