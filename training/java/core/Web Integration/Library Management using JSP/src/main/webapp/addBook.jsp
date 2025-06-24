<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<form action="/Library_Management_using_JSP/AddBookControllers" method="post">
		<label for="bookId">Book ID : </label>
		<input type="number" name="bookId" id="bookId"/><br>
		<label for="title">Title : </label>
		<input type="text" name="title" id="title"/><br>
		<label for="author">Author : </label>
		<input type="text" name="author" id="author"/><br>
		<label for="category">Category : </label>
		<input type="text" name="category" id="category"/><br>
		<c:if test="${param.mode=='update'}">
			<label for "status">Status</label>
			<select name="status">
				<option style="visibility:hidden">Select your choice</option>
				<option id="A" value="A">Active</option>
				<option id="I" value="I">Inactive</option>
			</select><br>
		</c:if>
		<input type="submit">
	</form>
</body>
</html>