<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main menu</title>

</head>
<body>
	<h2 style="text-align: center; color: green">Library Management
		System</h2>
	<br>
	<br>
	<table style="margin: auto">
		<tr>
			<th><button onclick="window.location.href='MainMenu.jsp'"
					style="color: white; margin: auto; background-color: green;">Main
					menu</button></th>
			<th><button onclick="window.location.href='Books.jsp'"
					style="color: white; margin: auto; background-color: green;">Books</button></th>
			<th><button onclick="window.location.href='Members.jsp'"
					style="color: white; margin: auto; background-color: green;">Members</button></th>
			<th><button onclick="window.location.href='IssueBook.jsp'"
					style="color: white; margin: auto; background-color: green;">IssueBook</button></th>
		</tr>
	</table>
	<br>
	<br>
	<h3 style="margin: auto; text-align: center">Search book by name</h3>
	<form action="Controller" method="Post">
		<input type="hidden" name="type" value="Search Book By Name" />
		<table style="margin: auto">
			<tr>
				<td>Name</td>
				<td><input type="text" name="book" style="margin: auto"
					placeholder="Enter Book Name" minlength="2" maxlength="50"
					oninput="searchBook()" required /></td>
			</tr>
			<tr>
				<td></td>
				<td><button type="submit">Search Book</button></td>
			</tr>
		</table>
	</form>
</body>
</html>