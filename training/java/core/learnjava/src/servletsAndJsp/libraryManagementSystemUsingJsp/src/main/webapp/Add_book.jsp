<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Book</title>
<%
String message = (String) request.getAttribute("alertMessage");
%>
</head>
<body>

	<% if (message != null) { %>
	<script>
	    alert("<%= message.replace("\"", "\\\"") %>");
	</script>
	<% } %>

	<form action="Controller" method="post">
		<input type="hidden" name="type" value="Add Book" />
		<table style="margin: auto">
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" style="margin: auto"
					placeholder="Enter Book Name" minlength="2" maxlength="50" required /></td>
			</tr>
			<tr>
			<tr>
				<td>Author</td>
				<td><input type="text" name="Author" style="margin: auto"
					placeholder="Enter Author Name" minlength="2" maxlength="50"
					required /></td>
			</tr>
			<tr>
			<tr>
				<td>Category</td>
				<td><input type="text" name="Category" style="margin: auto"
					placeholder="Category" required /></td>
			</tr>
			<tr>
				<td>Status</td>
				<td><label> <input type="radio" name="Status"
						value="Active" required /> ACTIVE
				</label> <label> <input type="radio" name="Status" value="INACTIVE" />
						INACTIVE
				</label></td>
			</tr>
			<tr>
				<td>Availability</td>
				<td><label> <input type="radio" name="Availability"
						value="Available" required /> Available
				</label> <label> <input type="radio" name="Availability"
						value="Issued" /> "Issued"
				</label></td>
			</tr>
			<tr>
				<td></td>
				<td><button type="submit">Add Book</button></td>
			</tr>
		</table>
	</form>

	<div style="text-align: center">
		<h4>Return to main menu</h4>
		<button onclick="window.location.href='MainMenu.jsp'"
			style="color: white; margin: auto; background-color: green;">
			Click Here</button>
	</div>

</body>
</html>