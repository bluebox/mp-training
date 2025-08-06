<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, com.LibraryManagement.models.Book"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Books</title>
<style>
body {
	font-family: sans-serif;
	margin: 0;
	padding: 20px;
	 background-image:
		url('${pageContext.request.contextPath}/resources/janko-ferlic-sfL_QOnmy00-unsplash.jpg');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: fixed;

}

.container {
	background-color: white;
	padding: 20px;
	border-radius: 8px;
	max-width: 1000px;
	margin: auto;
}

h2 {
	text-align: center;
	font-size: 24px;
	margin-bottom: 15px;
	color: #333;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 15px;
}

th, td {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: left;
	font-size: 14px;
	text-align: center;
}

th {
	background-color: #f0f0f0;
	color: #333;
}

.btn {
	padding: 6px 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	font-size: 13px;
	cursor: pointer;
	margin-right: 4px;
}

.back-form {
	margin-top: 20px;
	text-align: left;
}

.back-button {
	background-color: #007bff;
	color: white;
	padding: 10px 20px;
	font-size: 14px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.back-button:hover {
	background-color: #0056b3;
}

.btn:hover {
	background-color: #0056b3;
}

p {
	text-align: center;
	font-size: 16px;
	color: #555;
}
</style>
</head>
<body>
	<div class="container">
		<div>
			<form
				action="${pageContext.request.contextPath}/views/Books/books.jsp"
				method="get" class="back-form">
				<button type="submit" class="back-button">Back to Dashboard</button>
			</form>
		</div>

		<h2>All Books</h2>

		<%
		List<Book> bookList = (List<Book>) request.getAttribute("bookList");
		if (bookList == null || bookList.isEmpty()) {
		%>
		<p>No books found.</p>
		<%
		} else {
		%>
		<table>
			<thead>
				<tr>
					<th>Book ID</th>
					<th>Title</th>
					<th>Author</th>
					<th>Category</th>
					<th>Status</th>
					<th>Availability</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Book book : bookList) {
				%>
				<tr>
					<td><%=book.getBookId()%></td>
					<td><%=book.getTitle()%></td>
					<td><%=book.getAuthor()%></td>
					<td><%=book.getCategory()%></td>
					<td><%=book.getStatus()%></td>
					<td><%=book.getAvailability()%></td>
					<td>
						<form action="bookController" method="post"
							style="display: inline;">
							<input type="hidden" name="action" value="update" /> <input
								type="hidden" name="bookId" value="<%=book.getBookId()%>" /> <input
								type="submit" value="Update Book" class="btn" />
						</form>

						<form action="bookController" method="post"
							style="display: inline;">
							<input type="hidden" name="action" value="changeAvailability" />
							<input type="hidden" name="bookId" value="<%=book.getBookId()%>" />
							<input type="submit" value="Update Availability" class="btn" />
						</form>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%
		}
		%>
	</div>
</body>
</html>