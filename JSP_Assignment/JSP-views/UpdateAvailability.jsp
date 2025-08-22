<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Update Availability</title>
    <style>
        input, select, button {
            margin: 5px;
            padding: 8px;
        }
    </style>
</head>
<body>
    <div align="center">
        <h3>Update Availability Details</h3>
        
        <div>

        <form action="${pageContext.request.contextPath}/updateAvailabilityServlet" method="post">
            <input type="hidden" name="bookId" value="${book.bookId}" />
			<label>Title: </label>
            <input type="text" name="title" value="${book.title}" placeholder="Title" readonly /><br/>
            <label>Author: </label>
            <input type="text" name="author" value="${book.author}" placeholder="Author" readonly /><br/>
            <label>Category: </label>
            <input type="text" name="category" value="${book.category}" placeholder="Category" readonly /><br/>
            <label>Availability: </label>
            <select name="availability">
                    <option value="A" <c:if test="${{book.availability}.equals('A')}">selected</c:if>>Available</option>
                    <option value="I" <c:if test="${{book.availability}.equals('I')}">selected</c:if>>Issued</option>
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
