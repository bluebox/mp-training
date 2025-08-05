<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Book</title>
<style>
.update-book-container {
	max-width: 600px;
	margin: 30px auto;
	padding: 30px;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

.update-book-title {
	color: #34495e;
	text-align: center;
	margin-bottom: 30px;
	font-size: 2em;
	font-weight: 600;
}

.update-book-form-group {
	margin-bottom: 20px;
}

.update-book-label {
	display: block;
	margin-bottom: 8px;
	color: #34495e;
	font-weight: 500;
	font-size: 0.95em;
}

.update-book-input, .update-book-select {
	width: 100%;
	padding: 12px;
	border: 2px solid #7f8c8d;
	border-radius: 6px;
	font-size: 1em;
	color: #34495e;
	box-sizing: border-box;
}

.update-book-input:focus, .update-book-select:focus {
	border-color: #34495e;
	outline: none;
	box-shadow: 0 0 5px rgba(52, 73, 94, 0.2);
}

.update-book-radio-group {
	display: flex;
	gap: 30px;
	align-items: center;
	margin-top: 5px;
}

.update-book-radio-label {
	font-weight: 500;
	color: #34495e;
	margin-right: 8px;
}

.update-book-submit, .update-book-cancel {
	width: 48%;
	padding: 14px;
	background-color: #34495e;
	color: white;
	border: none;
	border-radius: 6px;
	font-size: 1em;
	font-weight: 500;
	cursor: pointer;
	margin-top: 20px;
	margin-right: 2%;
}

.update-book-cancel {
	background-color: #7f8c8d;
	margin-right: 0;
}

.update-book-submit:hover {
	background-color: #7f8c8d;
}

.update-book-cancel:hover {
	background-color: #34495e;
}

@media ( max-width : 600px) {
	.update-book-container {
		padding: 15px;
	}
	.update-book-submit, .update-book-cancel {
		width: 100%;
		margin-right: 0;
		margin-bottom: 10px;
	}
}
</style>
</head>
<body>
	<div class="update-book-container">
		<h1 class="update-book-title">Update Book Details</h1>
		<form action="ViewBooksServlet" method="post" class="update-book-form">
			<input type="hidden" name="BookId" value="${book.book_Id}" />
			<div class="update-book-form-group">
				<label class="update-book-label" for="title">Title</label> 
				<input type="text" id="title" name="title" value="${book.book_Title}"
					placeholder="Enter Book Title" class="update-book-input" required />
			</div>
			<div class="update-book-form-group">
				<label class="update-book-label" for="author">Author</label> <input
					type="text" id="author" name="author" value="${book.book_Author}"
					placeholder="Enter Author Name" class="update-book-input" required />
			</div>
			<div class="update-book-form-group">
				<label class="update-book-label" for="category">Category</label> <select
					id="category" name="category" class="update-book-select" required>
					<option value="" disabled>Select Category</option>
					<c:forEach var="cat" items="${categories}">
						<option value="${cat}" ${cat == book.book_Category ? 'selected' : ''}>${cat}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="update-book-form-group">
			    <label class="update-book-label" for="status">Status</label>
			    <select id="status" name="status" class="update-book-select" required>
			        <option value="A" ${book.book_Status.compareTo("I".charAt(0)) != 0? 'selected' : ''}>Active (A)</option>
			        <option value="I" ${book.book_Status.compareTo("A".charAt(0)) != 0 ? 'selected' : ''}>Inactive (I)</option>
			    </select>
			</div>


			
			<input type="submit" name="action" value="UpdateBook" class="update-book-submit" />
			<input type="submit" name="action" value="Cancel" class="update-book-cancel" />
		</form>
	</div>
</body>
</html>
