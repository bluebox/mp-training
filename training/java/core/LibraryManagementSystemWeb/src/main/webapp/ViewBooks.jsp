<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*, com.library.model.Book"%>
<html>
<head>
<title>View Books</title>
<style>
table {
	border-collapse: collapse;
	margin: auto;
	width: 90%;
}

th, td {
	border: 1px solid #999;
	padding: 10px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}

.btn {
	padding: 5px 10px;
	font-size: 13px;
	margin: 2px;
	cursor: pointer;
	background-color: #4CAF50;
	border: none;
	color: white;
	border-radius: 3px;
}

.back-button {
	display: block;
	margin: 20px auto;
	width: fit-content;
	padding: 10px 20px;
	background-color: #4CAF50;
	color: white;
	text-decoration: none;
	font-weight: bold;
	border-radius: 4px;
}
</style>
</head>
<body>

	<a class="back-button" href="index.jsp">← Back to Dashboard</a>
	<h2 align="center">All Books</h2>

	<%
	List<Book> books = (List<Book>) request.getAttribute("books");
	String error = (String) request.getAttribute("error");
	if (error != null) {
	%>
	<p style="color: red; text-align: center;"><%=error%></p>
	<%
	} else if (books != null && !books.isEmpty()) {
	%>
	<table>
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Author</th>
			<th>Category</th>
			<th>Status</th>
			<th>Availability</th>
			<th>Actions</th>
		</tr>
		<%
		for (Book book : books) {
		%>
		<tr>
			<td><%=book.getBookId()%></td>
			<td><%=book.getTitle()%></td>
			<td><%=book.getAuthor()%></td>
			<td><%=book.getCategory()%></td>
			<td><%=book.getStatus() == 'A' ? "Active" : "Inactive"%></td>
			<td><%=book.getAvailability() == 'A' ? "Available" : "Issued"%></td>
			<td>
				<form action="UpdateBookServlet" method="get"
					style="display: inline;">
					<input type="hidden" name="bookId" value="<%=book.getBookId()%>">
					<button type="submit" class="btn">Update</button>
				</form>

				<form action="ToggleAvailabilityServlet" method="post">
					<input type="hidden" name="bookId" value="<%=book.getBookId()%>" />
					<input type="hidden" name="currentAvailability"
						value="<%=book.getAvailability()%>" />
					<button class="btn" type="submit"><%=book.getAvailability() == 'A' ? "Mark as Issued" : "Mark as Available"%></button>

				</form>


			</td>
		</tr>
		<%
		}
		%>
	</table>
	<%
	} else {
	%>
	<p style="text-align: center;">No books found.</p>
	<%
	}
	%>

</body>
</html>
