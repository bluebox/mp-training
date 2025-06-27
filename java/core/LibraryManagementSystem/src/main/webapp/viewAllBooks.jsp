<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="Pojo.Book" %>
<%@ page import="Service.LibraryService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>View All Books</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>All Books</h1>
    <%
    try {
        LibraryService lib = new LibraryService();
        List<Book> bookList = lib.viewAllBooks();
        request.setAttribute("booksList", bookList);
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
    %>
    <c:choose>
        <c:when test="${not empty bookList}">
            <table border="1">
                <tr>
                    <th>BookId</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Category</th>
                    <th>Status</th>
                    <th>Availability</th>
                </tr>
                <c:forEach var="book" items="${bookList}">
                    <tr>
                        <td>${book.getBookId()}</td>
                        <td>${book.getTitle()}</td>
                        <td>${book.getCategory()}</td>
                        <td>${book.getAuthor()}</td>
                        <td>${book.getStatus()}</td>
                        <td>${book.getAvailability()}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>There are no books.<c:out value="${bookList}"/></p>
        </c:otherwise>
    </c:choose>
</body>
</html>