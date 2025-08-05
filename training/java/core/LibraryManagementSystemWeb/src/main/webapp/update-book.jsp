<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.library.model.Book"%>
<%
Book book = (Book) request.getAttribute("book");
String message = (String) request.getAttribute("message");
String error = (String) request.getAttribute("error");
%>
<html>
<head>
<title>Update Book</title>
<style>
body {
	font-family: Arial;
	padding: 20px;
}

.form-group {
	margin-bottom: 10px;
}

label {
	display: block;
	margin-bottom: 5px;
}

input[type="text"], select {
	width: 100%;
	padding: 8px;
}

.btn {
	background-color: #4CAF50;
	color: white;
	padding: 10px 20px;
	border: none;
	cursor: pointer;
}

.message {
	color: green;
	text-align: center;
}

.error {
	color: red;
	text-align: center;
}
</style>
<script>
	setTimeout(function() {
		var msg = document.getElementById("feedback");
		if (msg)
			msg.style.display = "none";
	}, 2000);
</script>
</head>
<body>

<a class="back-button" href="ViewBooks.jsp"><button>← Back to View Books</button></a>
<a class="back-button" href="ViewBooks.jsp"><button>← Back to DashBoard</button></a>
	<h2>Update Book Details</h2>


	<%
	if (error != null) {
	%>
	<p id="feedback" class="error"><%=error%></p>
	<%
	}
	%>
	<% if (message != null) { %>
    <p id="feedback" class="message"><%=message%></p>
    <script>
        setTimeout(function () {
            window.location.href = 'ViewBooksServlet';
        }, 2000);
    </script>
<% } %>


	<form method="post" action="UpdateBookServlet">
		<input type="hidden" name="bookId" value="<%=book.getBookId()%>" />
		<div class="form-group">
			<label>Title:</label> <input type="text" name="title"
				value="<%=book.getTitle()%>" required />
		</div>
		<div class="form-group">
			<label>Author:</label> <input type="text" name="author"
				value="<%=book.getAuthor()%>" required />
		</div>
		<div class="form-group">
			<label>Category:</label> <input type="text" name="category"
				value="<%=book.getCategory()%>" required />
		</div>
		<div class="form-group">
			<label>Status:</label> <select name="status">
				<option value="A" <%=book.getStatus() == 'A' ? "selected" : ""%>>Active</option>
				<option value="I" <%=book.getStatus() == 'I' ? "selected" : ""%>>Inactive</option>
			</select>
		</div>
		<button type="submit" class="btn">Update Book</button>
	</form>
</body>
</html>