<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Book Availability</title>
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
	width: 400px;
}

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
	<h2>Update Availability</h2>

	<div class="message" style="color: ${requestScope.messageColor}">
				${requestScope.message}</div>

	<c:if test="${not empty book}">
		<form action="${pageContext.request.contextPath}/bookController" method="post">
			<input type="hidden" name="bookId" value="${book.bookId}" />

			<label>Book ID:</label>
			<input type="text" value="${book.bookId}" disabled />

			<label>Title:</label>
			<input type="text" value="${book.title}" disabled />

			<label>Author:</label>
			<input type="text" value="${book.author}" disabled />

			<label>Category:</label>
			<input type="text" value="${book.category}" disabled />

			<label>Status:</label>
			<input type="text" value="<c:out value='${book.statusAsString eq "A" ? "Active" : "Inactive"}' />" disabled />

			<label>Availability:</label>
			<select name="availability">
				<option value="A" <c:if test="${book.availabilityAsString eq 'A'}">selected</c:if>>Available</option>
				<option value="I" <c:if test="${book.availabilityAsString eq 'I'}">selected</c:if>>Issued</option>
			</select>
			<c:if test="${not empty availabilityError}">
				<div class="error-message">${availabilityError}</div>
			</c:if>

			<input type="hidden" name="action" value="updateAvail" />
			<input type="submit" value="Update Availability" />
		</form>
	</c:if>

	<form action="${pageContext.request.contextPath}/bookController" method="post">
		<input type="hidden" name="action" value="view" />
		<input type="submit" value="Back to Book List" />
	</form>
</div>
</body>
</html>