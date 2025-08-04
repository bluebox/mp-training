<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Books</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            margin: 20px;
        }
        h2 { 
            text-align: center;
        }
        table { 
            width: 100%; 
            border-collapse: collapse; 
            margin-top: 20px; 
        }
        th, td { 
            border: 1px solid #ccc; 
            padding: 8px; 
            text-align: left; 
        }
        th { 
            background-color: #f2f2f2; 
        }
        .error {
            color: red;
            font-weight: bold;
        }
        .success {
            color: green;
            font-weight: bold;
        }
        .button-group form {
            display: inline;
        }
        button {
            padding: 5px 10px;
            cursor: pointer;
        }
        .bottom-buttons {
            display: flex;
            justify-content: center;
            gap: 10px; 
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2>All Books</h2>

    <c:if test="${not empty requestScope.statusMessage}">
        <p class="${requestScope.statusType}"><c:out value="${requestScope.statusMessage}" /></p>
    </c:if>

    <c:if test="${empty booksList}">
        <p>No books found in the library.</p>
    </c:if>

    <c:if test="${not empty booksList}">
        <table>
            <thead>
                <tr>
                    <th>Book ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Category</th>
                    <th>Status</th>
                    <th>Avail.</th>
                    <th>Created At</th>
                    <th>Created By</th>
                    <th>Updated At</th>
                    <th>Updated By</th>
                    <th>Actions</th>
                    <th>Avail. Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${booksList}">
                    <tr>
                        <td><c:out value="${book.bookId}" /></td>
                        <td><c:out value="${book.title}" /></td>
                        <td><c:out value="${book.author}" /></td>
                        <td><c:out value="${book.category.displayName}" /></td>
                        <td><c:out value="${book.status.name()}" /></td>
                        <td><c:out value="${book.availability.name()}" /></td>
                        <td><c:out value="${book.createdAt}" /></td>
                        <td><c:out value="${book.createdBy}" /></td>
                        <td><c:out value="${book.updatedAt}" /></td>
                        <td><c:out value="${book.updatedBy}" /></td>
                        <td class="button-group">
                            <form action="updateBook" method="get">
                                <input type="hidden" name="bookId" value="${book.bookId}">
                                <button type="submit">Update</button>
                            </form>
                            <form action="deleteBook" method="post" onsubmit="return confirm('Are you sure you want to delete this book?');">
                                <input type="hidden" name="bookId" value="${book.bookId}">
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                        <td class="button-group">
                            <form action="updateBookAvailability" method="post">
                                <input type="hidden" name="bookId" value="${book.bookId}">
                                <input type="hidden" name="newAvailability" value="${book.availability == 'AVAILABLE' ? 'I' : 'A'}">
                                <button type="submit">
                                    <c:if test="${book.availability == 'AVAILABLE'}">Mark Issued</c:if>
                                    <c:if test="${book.availability != 'AVAILABLE'}">Mark Available</c:if>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="bottom-buttons">
        <form action="viewBooks" method="get">
            <button type="submit">Refresh Books</button>
        </form>
        <form action="homeScreen.jsp" method="get">
            <button type="submit">Back to Main Menu</button>
        </form>
    </div>

</body>
</html>