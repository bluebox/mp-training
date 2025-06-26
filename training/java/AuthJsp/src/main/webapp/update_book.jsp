<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.casestudy.domain.Book, com.casestudy.domain.Status" %>
<%
    Book book = (Book) request.getAttribute("book");
    String message = (String) request.getAttribute("message");
%>

<!DOCTYPE html>
<html>
<head>
    
    <title>Update Book</title>
    <style>
        body { font-family: Arial; margin: 30px; }
        form { width: 500px; margin: auto; border: 1px solid #ccc; padding: 20px; border-radius: 8px; }
        label { display: block; margin-top: 10px; }
        input, select { width: 100%; padding: 8px; margin-top: 5px; }
        .btns { margin-top: 15px; display: flex; justify-content: space-between; }
        .msg { text-align: center; color: green; font-weight: bold; }
        .error { color: red; text-align: center; }
    </style>
</head>
<body>

<h2 style="text-align:center;">📖 Update Book Details</h2>

<% if (message != null) { %>
    <p class="<%= message.contains("failed") ? "error" : "msg" %>"><%= message %></p>
<% } %>

<form method="post" action="UpdateBookServlet">
    <label for="bookId">Book ID</label>
    <input type="text" name="bookId" id="bookId" value="<%= book != null ? book.getBookId() : "" %>" 
           <%= book != null ? "readonly" : "" %> required />

    <% if (book == null) { %>
        <div class="btns">
            <input type="submit" name="action" value="Fetch Book" />
        </div>
    <% } else { %>
        <label for="title">Title</label>
        <input type="text" name="title" id="title" maxlength="50" value="<%= book.getTitle() %>" required />

        <label for="author">Author</label>
        <input type="text" name="author" id="author" maxlength="50" value="<%= book.getAuthor() %>" required />

        <label for="category">Category</label>
        <input type="text" name="category" id="category" maxlength="50" value="<%= book.getCategory() %>" required />

        <label for="status">Status</label>
        <select name="status" id="status" required>
            <option value="A" <%= book.getStatus().getCode().equals("A") ? "selected" : "" %>>A - Active</option>
            <option value="I" <%= book.getStatus().getCode().equals("I") ? "selected" : "" %>>I - Inactive</option>
        </select>

        <div class="btns">
            <input type="submit" name="action" value="Update Book" />
            <a href="update_book.jsp">Reset</a>
        </div>
    <% } %>
</form>

</body>
</html>
