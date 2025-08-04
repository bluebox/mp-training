<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="display:flex;flex-direction:column;align-items:center;">
<h1>Update Book Form</h1>
<form action="updatebook" method="post">
	<div>
	<label for="bookdi">Book Id:</label>
	<input type="number" name="bookid"/>
	</div><br/>
	<div><label for="title">Book title:</label>
	<input type="text" name="title"/></div><br/>
	<div><label for="author">Author:</label>
	<input type="text" name="author"/></div><br/>
	<div><label for="category">Category:</label>
	<input type="text" name="category"/></div><br/>
	<div>
	<label for="status">Status:</label><br>
	<input type="radio" name="status" value="Active">Active 
	<input type="radio" name="status" value="InActive">Inactive
	</div><br/>
	<div>
	<label for="availability">Availability:</label><br>
	<input type="radio" name="availability" value="Available">Available
	<input type="radio" name="availability" value="Issued">Issued
	</div><br/>
	<input type="submit" value="update book"/>
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