<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Book List</title>
    <style>
        table, th, td { border: 1px solid black; padding: 8px; border-collapse: collapse; }
        th { background-color: #ddd; }
    </style>
</head>
<body>
    <div align="center">
        <h2>All Books</h2>

        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>ID</th><th>Title</th><th>Author</th><th>Category</th><th>Status</th><th>Availability</th><th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book.bookId}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.category}</td>
                        <td>${book.status}</td>
                        <td>${book.availability}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/updateBookFormServlet?bookId=${book.bookId}">
                                <button>Update</button>
                            </a>
                      
                            <a href="${pageContext.request.contextPath}/updateAvailabilityServlet?bookId=${book.bookId}">
                                <button>Availability</button>
                            </a>
                        </td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
