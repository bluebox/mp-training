<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Issue Book</title>
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
	background-color: white;
	padding: 40px;
	border-radius: 12px;
	width: 450px;
}

h2 {
	text-align: center;
	font-size: 24px;
	color: #333;
	margin-bottom: 30px;
}

label {
	display: block;
	margin-top: 15px;
	font-size: 16px;
	color: #333;
}

select, input[type="date"] {
	width: 100%;
	padding: 10px;
	font-size: 15px;
	margin-top: 5px;
	border: 1px solid #ccc;
	border-radius: 6px;
	box-sizing: border-box;
}

.button-group {
	display: flex;
	justify-content: space-between;
	margin-top: 30px;
}

input[type="submit"], .back-button {
	padding: 12px;
	width: 48%;
	font-size: 16px;
	background-color: #007BFF;
	color: white;
	border: none;
	border-radius: 6px;
	cursor: pointer;
}

input[type="submit"]:hover, .back-button:hover {
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
		<h2>Issue Book</h2>
		<div class="message" style="color: ${requestScope.messageColor}">
			${requestScope.message}</div>
		<form
			action="${pageContext.request.contextPath}/IssueReturnController"
			method="post">
			<label for="bookId">Book ID:</label> <select id="bookId"
				name="bookId" required>
				<option value="">Select Book ID</option>
				<c:forEach var="book" items="${bookList}">
					<option value="${book}"
						<c:if test="${param.bookId == book.toString()}">selected</c:if>>
						${book}</option>
				</c:forEach>
			</select>
			<c:if test="${not empty bookIdError}">
				<div class="error-message">${bookIdError}</div>
			</c:if>

			<label for="memberId">Member ID:</label> <select id="memberId"
				name="memberId" required>
				<option value="">Select Member ID</option>
				<c:forEach var="member" items="${memberList}">
					<option value="${member}"
						<c:if test="${param.memberId == member.toString()}">selected</c:if>>
						${member}</option>
				</c:forEach>
			</select>
			<c:if test="${not empty memberIdError}">
				<div class="error-message">${memberIdError}</div>
			</c:if>

			<label for="issueDate">Issue Date:</label> <input id="issueDate"
				type="date" name="issueDate" required value="${param.issueDate}" />
			<c:if test="${not empty issueDateError}">
				<div class="error-message">${issueDateError}</div>
			</c:if>

			<input type="hidden" name="action" value="issueBook" />

			<div class="button-group">
				<input type="submit" value="Issue Book" />
				<button type="button" class="back-button"
					onclick="window.location.href='${pageContext.request.contextPath}/views/IssueReturn/issueReturn.jsp'">
					Back</button>
			</div>
		</form>
	</div>
</body>
</html>
