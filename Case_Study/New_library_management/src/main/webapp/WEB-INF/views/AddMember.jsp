<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Member</title>
</head>
<body style="display:flex;flex-direction:column;align-items:center;">
<h1>Add Member Form</h1>
<form action="AddMemberController" method="post" >
	Name: <input type="text" name="name" required><br><br>
	Mobile: <input type="text" name="mobile" required><br><br>
	Email: <input type="email" name="email" required><br><br>
Gender:
	<select name="gender" required>
		<option value="">--Select Gender--</option>
		<option value="Male">Male</option>
		<option value="Female">Female</option>
	</select><br><br>
	Address: <input type="text" name="address" required><br><br>
	<input type="submit" value="Add Member">
</form>

<% if (request.getAttribute("message") != null) { %>
	<p style="color:green;"><%= request.getAttribute("message") %></p>
	<a href="Home.html">Home Page</a>
<% } %>
<% if (request.getAttribute("error") != null) { %>
	<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

</body>
</html>