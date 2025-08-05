<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>View Books</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/viewbook.css" />
    <style>
        .book-buttons {
            padding: 8px 16px;
            font-weight: bold;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div style="max-width: 750px; margin: 0 auto; padding: 20px;">
        <table border="1" style="width:100%; border-collapse: collapse;">
            <thead>
                <tr>
                    <th style="width:45px;">Book ID</th>
                    <th style="width:80px;">Title</th>
                    <th style="width:100px;">Author</th>
                    <th style="width:80px;">Category</th>
                    <th style="width:80px;">Status</th>
                    <th style="width:70px;">Availability</th>
                    <th style="width:200px;">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${bookList}">
                    <tr>
                        <td>${book.bookId}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.category}</td>
                        <td>${book.status}</td>
                        <td>${book.availability}</td>
                        <td>
                          
                            <form action="EditBookController" method="get" style="display:inline;">
                                <input type="hidden" name="id" value="${book.bookId}" />
                                <button type="submit">Edit</button>
                            </form>
                            <form action="UpdateAvailabilityController" method="get" style="display:inline;">
                                    <input type="hidden" name="id" value="${book.bookId}" />
                                       <button type="submit">Update Availability</button>
                             </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br/>
        <form action="AddBookOptions.jsp" method="get">
            <button type="submit" class="book-buttons" style="color: red;">Back</button>
        </form>
    </div>
</body>
</html>
