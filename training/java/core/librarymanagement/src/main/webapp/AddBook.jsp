<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
</head>
<body>
    <h2>Add New Book</h2>

    <form action="addBook" method="post">
        <label>Title:</label><br>
        <input type="text" name="title" required /><br><br>

        <label>Author:</label><br>
        <input type="text" name="author" required /><br><br>

        <label>Category:</label><br>
        <input type="text" name="category" required /><br><br>

        <label>Status:</label><br>
        <input type="radio" name="status" value="Active" checked /> Active<br>
        <input type="radio" name="status" value="Inactive" /> Inactive<br><br>

        <input type="submit" value="Add Book" />
    </form>

    <% 
        String msg = (String) request.getAttribute("message");
        if (msg != null) { 
    %>
        <p style="color:green;"><%= msg %></p>
    <% } %>
</body>
</html>
