<%@ page import="com.lms.model.BookCategory" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    BookCategory[] categories = BookCategory.values();
    String message = (String) request.getAttribute("message");
    String error = (String) request.getAttribute("error");
%>
<html>
<head>
    <title>Add Book</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lms-style.css" />
    
</head>
<body>
<div class="container">
    <h2>Add New Book</h2>

    <% if (message != null) { %>
        <p style="color:green;"><%= message %></p>
    <% } %>
    <% if (error != null) { %>
        <p style="color:red;"><%= error %></p>
    <% } %>

    <form method="post" action="addBook">
        <label>Title:</label>
        <input type="text" name="title" required/><br/><br/>

        <label>Author:</label>
        <input type="text" name="author" required/><br/><br/>

        <label>Category:</label>
        <select name="category" required>
            <option value="">--Select Category--</option>
            <% for (BookCategory cat : categories) { %>
                <option value="<%= cat.name() %>"><%= cat.name() %></option>
            <% } %>
        </select><br/><br/>

        <button type="submit">Add Book</button>
        <button type="reset">Clear</button>
    </form>
    </div>
</body>
</html>
