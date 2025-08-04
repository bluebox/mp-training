<%@ page import="java.util.List" %>
<%@ page import="com.lms.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    List<Book> bookList = (List<Book>) request.getAttribute("bookList");
    String error = (String) request.getAttribute("error");
%>

<html>
<head>
    <title>View Books</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lms-style.css" />
    
</head>
<body>
<div class="container">
    <h2>All Books</h2>

    <% if (error != null) { %>
        <p class="error"><%= error %></p>
    <% } else if (bookList != null && !bookList.isEmpty()) { %>
        <table>
            <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Category</th>
                <th>Status</th>
                <th>Availability</th>
            </tr>
            <% for (Book book : bookList) { %>
                <tr>
                    <td><%= book.getBookId() %></td>
                    <td><%= book.getBookTitle() %></td>
                    <td><%= book.getBookAuthor() %></td>
                    <td><%= book.getBookCategory() %></td>
                    <td><%= book.getStatus() %></td>
                    <td><%= book.getAvailability() %></td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No books found.</p>
    <% } %>
    </div>
</body>
</html>
