<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books</title>
<style>

.container {
	display: flex;
	flex-direction: column;
	align-items: center; 
	gap: 20px; /* spacing between forms */
}

form {
	width: 200px;
	text-align: center;
}

button {
	padding: 10px;
	font-size: 1rem;
	cursor: pointer;
}
</style>
</head>
<body>
	<h2 style="text-align: center">Books Page</h2>
	<div class="container">
		<form action="Add_book.jsp" method="post">
			<button type="submit">Add Book</button>
		</form>

		<form action="Update_book.jsp" method="post">
			<button type="submit">Update Book</button>
		</form>

		<form action="Controller" method="post">
			<input type="hidden" name="type" value="View All Books" />
			<button type="submit">View all books</button>
		</form>
	</div>
	<div style="text-align: center">
		<h4>Return to main menu</h4>
		<button onclick="window.location.href='MainMenu.jsp'"
			style="color: white; margin: auto; background-color: green;">
			Click Here</button>
	</div>
</body>
</html>


