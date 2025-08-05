<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Update Book</title>
    <style>
        input, select, button {
            margin: 5px;
            padding: 8px;
        }
    </style>
</head>
<body>
    <div align="center">
        <h3>Update Book Details</h3>
        
        <div>

        <form action="${pageContext.request.contextPath}/updateBookFormServlet" method="post">
            <input type="hidden" name="bookId" value="${book.bookId}" />
			<label>Title: </label>
            <input type="text" name="title" value="${book.title}" placeholder="Title" required /><br/>
            <label>Author: </label>
            <input type="text" name="author" value="${book.author}" placeholder="Author" required /><br/>
            <label>Category: </label>
            <input type="text" name="category" value="${book.category}" placeholder="Category" required /><br/>
            <label>Status: </label>
            <select name="status">
                    <option value="A" <c:if test="${{book.status}.equals('A')}">selected</c:if>>Active</option>
                    <option value="I" <c:if test="${{book.status}.equals('I')}">selected</c:if>>Inactive</option>
                </select>
            
			<br/>
			<button type="submit">Update</button>
            <a href="${pageContext.request.contextPath}/viewBooksServlet"><button type="button">Cancel</button></a>
        </form>

</div>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
    </div>
</body>
</html>
