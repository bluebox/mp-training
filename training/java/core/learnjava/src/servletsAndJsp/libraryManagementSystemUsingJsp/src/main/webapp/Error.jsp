<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
	<h2 style="color: red; text-align: center">${msg}</h2>
	<h3 style="text-align: center">${what}</h3>
	<div style="text-align: center">
		<button onclick="window.location.href='MainMenu.jsp'"
			style="color: white; margin: auto; background-color: green;">
			Click Here</button>
	</div>

</body>
</html>