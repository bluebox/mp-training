<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books</title>
</head>
<body>
	<h1>All Books Record</h1>
	
	
	<c:if test="${not empty error && empty list}">
		<p style="color:red;">${error}</p>
	</c:if>

	<c:if test="${empty list && empty error}">
		<p style="color:red;">No Books found yet.</p>
	</c:if>
	
	
	<c:if test="${not empty list}">
		<table border="1" cellpadding="5" cellspacing="0">
			<tr>
				<th>Book_ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Status</th>
				<th>Availability</th>
			</tr>
			<c:forEach var="book" items="${list}">
				<tr>
					<td>${book.bookId}</td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.category}</td>
					<td>${book.status}</td>
					<td>${book.availability}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
    </table>

</body>
</html>