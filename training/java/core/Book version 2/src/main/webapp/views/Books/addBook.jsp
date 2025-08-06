<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adding Book</title>
<style>
body {
	font-family: 'Segoe UI', sans-serif;
	margin: 0;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	 background-image:
		url('${pageContext.request.contextPath}/resources/janko-ferlic-sfL_QOnmy00-unsplash.jpg');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: fixed;

}

.container {
	padding: 40px;
	border-radius: 12px;
	width: 420px;
	background-color: white; 
}

h3 {
	font-size: 26px;
	margin-bottom: 30px;
	color: #333;
	text-align: center;
}

.form-group {
	margin-bottom: 20px;
	text-align: left;
}

label {
	display: block;
	font-size: 16px;
	margin-bottom: 6px;
	color: #333;
}

input[type="text"], select {
	width: 100%;
	padding: 10px;
	font-size: 16px;
	
	border-radius: 6px;
	box-sizing: border-box;
}

.error {
	color: red;
	font-size: 14px;
	margin-top: 4px;
}

input[type="submit"] {
	width: 100%;
	padding: 12px;
	font-size: 18px;
	background: #007BFF;
	color: #fff;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	transition: 0.3s;
	margin-top: 10px;
}

input[type="submit"]:hover {
	background: #0056b3;
}

.back-button {
	width: 100%;
	padding: 12px;
	font-size: 18px;
	background: #007BFF;
	color: #fff;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	transition: 0.3s;
	margin-top: 10px;
}

.back-button:hover {
	background-color: #0056b3;
}
.message {
	font-size: 17px;
	margin-top: 20px;
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h3>Add a Book</h3>
		<form action="${pageContext.request.contextPath}/bookController"
			method="post">
			<div class="form-group">
				<label>Enter Book Title:</label> <input type="text" name="title"
					value="${requestScope.title}" required/>
				<div class="error">${requestScope.titleError}</div>
			</div>

			<div class="form-group">
				<label>Enter Book Author:</label> <input type="text" name="author"
					value="${requestScope.author}" required/>
				<div class="error">${requestScope.authorError}</div>
			</div>

			<div class="form-group">
				<label>Select the Category:</label> <select name="category">
					<option value="Category"
						${requestScope.category == 'Category' ? 'selected' : ''}>Category</option>
					<option value="Fiction"
						${requestScope.category == 'Fiction' ? 'selected' : ''}>Fiction</option>
					<option value="Mystery"
						${requestScope.category == 'Mystery' ? 'selected' : ''}>Mystery</option>
					<option value="Thriller"
						${requestScope.category == 'Thriller' ? 'selected' : ''}>Thriller</option>
					<option value="Story"
						${requestScope.category == 'Story' ? 'selected' : ''}>Story</option>
					<option value="Adventure"
						${requestScope.category == 'Adventure' ? 'selected' : ''}>Adventure</option>
				</select>
				<div class="error">${requestScope.categoryError}</div>
			</div>

			<input type="hidden" value="add" name="action" /> <input
				type="submit" value="Add" />

			<div class="message" style="color: ${requestScope.messageColor}">
				${requestScope.message}</div>
		</form>

		<form
			action="${pageContext.request.contextPath}/views/Books/books.jsp"
			method="get" class="back-form">
			<button type="submit" class="back-button">Back to Dashboard</button>
		</form>
	</div>
</body>
</html>