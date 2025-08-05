<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Member</title>
<style>
	body {
  margin: 0;
  height: 100vh; 
  display: flex;
  justify-content: center; 
  align-items: center;     
  background-color: #f5f5f5; 
}
form{
	display:flex;
	flex-direction:column;
	align-items:center;
	  justify-content: center; 
	gap:20px;
	background-color:lightblue;
	padding:20px;
	margin:20px;
	border:solid 2px;
	border-radius:8px;

}


</style>
</head>
<body style="display:flex;flex-direction:column;align-items:center;">

<form action="AddMemberController" method="post" >
<div><h1>Add Member Form</h1></div>
<div><label for="name">Name: </label>
	<input type="text" name="name" required><br><br></div>
<div>
<label>Mobile: </label>
<input type="text" name="mobile" required><br><br>
</div>
	<div>
	<label>Email: </label>
	<input type="email" name="email" required><br><br>
	</div>
	<div>
	<label>Gender:</label>
	<select name="gender" required>
		<option value="">--Select Gender--</option>
		<option value="Male">Male</option>
		<option value="Female">Female</option>
	</select><br><br>
	</div>

	<div>
	<label>Address: </label>
	<input type="text" name="address" required><br><br>
	</div>
	
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