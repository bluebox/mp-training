<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Overdue Books</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        h2 { color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #aaa; padding: 10px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        .back-btn {
            margin-top: 30px;
            padding: 10px 20px;
            background-color: #ddd;
            border: none;
            cursor: pointer;
        }
        .back-btn:hover {
            background-color: #ccc;
        }
    </style>
</head>
<body>
    <h2>Overdue Books</h2>

    <c:choose>
        <c:when test="${not empty books}">
            <table>
                <tr>
                    <th>Book ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Category</th>
                </tr>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book.bookId}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.category}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>No overdue books found.</p>
        </c:otherwise>
    </c:choose>

    <!-- Back Button -->
    <button class="back-btn" onclick="history.back()">Back</button>
</body>
</html>

