<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.LibraryManagement.models.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Book Details</title>
<style>
body {
	font-family: 'Segoe UI', sans-serif;
	margin: 0;
	padding: 40px;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	 background-image:
		url('${pageContext.request.contextPath}/resources/janko-ferlic-sfL_QOnmy00-unsplash.jpg');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: fixed;

}

.container {
	background-color: white;
	padding: 30px;
	border-radius: 10px;
	width: 400px;
	

h2 {
	text-align: center;
	margin-bottom: 20px;
	color: #333;
}

label {
	display: block;
	margin-top: 15px;
	font-size: 16px;
	color: #333;
}

input[type="text"], select {
	width: 100%;
	padding: 10px;
	font-size: 15px;
	margin-top: 5px;
	border: 1px solid #ccc;
	border-radius: 6px;
	box-sizing: border-box;
}

input[type="submit"] {
	margin-top: 20px;
	width: 100%;
	padding: 12px;
	font-size: 16px;
	background-color: #007BFF;
	color: white;
	border: none;
	border-radius: 6px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}

.error-message {
	color: red;
	font-size: 13px;
	margin-top: 5px;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Update Book</h2>

		<div class="message" style="color: ${requestScope.messageColor}">
				${requestScope.message}</div>
		<%
    Book book = (Book) request.getAttribute("Book");
%>

		<c:if test="${not empty book}">
			<form action="${pageContext.request.contextPath}/bookController"
				method="post">
				<input type="hidden" name="bookId" value="${book.bookId}" />
				 <label>Book ID:</label> 
				 <input type="text" value="${book.bookId}" disabled />
				  <label>Title:</label>
				<input type="text" name="title" value="${book.title}" required />
				
					<div class="error-message">${titleError}</div>
				
				<label>Author:</label>
				 <input type="text" name="author"
					value="${book.author}" required />
			
					<div class="error-message">${authorError}</div>
				

				<label>Category:</label> <select name="category">
					<option value="Category"
						<c:if test="${book.category eq 'Category'}">selected</c:if>>Category</option>
					<option value="Fiction"
						<c:if test="${book.category eq 'Fiction'}">selected</c:if>>Fiction</option>
					<option value="Mystery"
						<c:if test="${book.category eq 'Mystery'}">selected</c:if>>Mystery</option>
					<option value="Thriller"
						<c:if test="${book.category eq 'Thriller'}">selected</c:if>>Thriller</option>
					<option value="Story"
						<c:if test="${book.category eq 'Story'}">selected</c:if>>Story</option>
					<option value="Adventure"
						<c:if test="${book.category eq 'Adventure'}">selected</c:if>>Adventure</option>
				</select>
				
					<div class="error-message">${categoryError}</div>
				

				<label>Status:</label> <select name="status">
					<option value="A"
						<c:if test="${book.statusAsString eq 'A'}">selected</c:if>>Active</option>
					<option value="I"
						<c:if test="${book.statusAsString eq 'I'}">selected</c:if>>Inactive</option>
				</select>
				
					<div class="error-message">${statusError}</div>
		

				<input type="hidden" name="action" value="saveUpdate" /> <input
					type="submit" value="Update Book" />
			</form>
		</c:if>

		<form action="${pageContext.request.contextPath}/bookController"
			method="post">
			<input type="hidden" name="action" value="view" /> <input
				type="submit" value="Back to Book List" />
		</form>
	</div>
</body>
</html>
