
<%@ page import="com.lms.model.Book" %>
<%@ page import="com.lms.model.BookCategory" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Book book = (Book) request.getAttribute("book");
    String error = (String) request.getAttribute("error");
    String message = (String) request.getAttribute("message");
    BookCategory[] categories = BookCategory.values();
%>

<html>
<head>
    <title>Update Book</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lms-style.css" />
    
</head>
<body>
<div class="container">
    <h2>Update Book</h2>

    <% if (message != null) { %>
        <p style="color:green;"><%= message %></p>
    <% } %>
    <% if (error != null) { %>
        <p style="color:red;"><%= error %></p>
    <% } %>

    <form method="post" action="updateBook">
        <label>Book ID:</label>
        <input type="text" name="bookId" value="<%= (book != null) ? book.getBookId() : "" %>"/>
        <button type="submit" name="action" value="fetch">Fetch</button>
        <br/><br/>

        <label>Title:</label>
        <input type="text" name="title" value="<%= (book != null) ? book.getBookTitle() : "" %>"/><br/><br/>

        <label>Author:</label>
        <input type="text" name="author" value="<%= (book != null) ? book.getBookAuthor() : "" %>"/><br/><br/>

        <label>Category:</label>
        <select name="category">
            <% for (BookCategory cat : BookCategory.values()) { %>
                <option value="<%= cat.name() %>"
                    <%= (book != null && book.getBookCategory() == cat) ? "selected" : "" %>>
                    <%= cat.name() %>
                </option>
            <% } %>
        </select><br/><br/>

        <label>Status:</label>
        <input type="radio" name="status" value="A" <%= (book != null && book.getStatus() == 'A') ? "checked" : "" %>> Active
        <input type="radio" name="status" value="I" <%= (book != null && book.getStatus() == 'I') ? "checked" : "" %>> Inactive
        <br/><br/>

        <label>Availability:</label>
        <input type="radio" name="availability" value="A" <%= (book != null && book.getAvailability() == 'A') ? "checked" : "" %>> Available
        <input type="radio" name="availability" value="U" <%= (book != null && book.getAvailability() == 'U') ? "checked" : "" %>> Unavailable
        <br/><br/>

        <button type="submit" name="action" value="update">Update</button>
        <button type="reset">Clear</button>
    </form>
    </div>
</body>
</html>
