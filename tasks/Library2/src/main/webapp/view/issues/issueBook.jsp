<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Issue Book</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 40px; background: #f9fafb; }
        h2 { margin-bottom: 20px; }
        form { background: #fff; padding: 24px; border-radius: 8px; box-shadow: 0 0 8px rgba(0,0,0,0.08); max-width: 400px; margin-bottom: 24px; }
        label { display: block; margin-bottom: 8px; }
        input[type="text"], input[type="date"] { width: 100%; padding: 8px; margin-bottom: 16px; border: 1px solid #ddd; border-radius: 4px; }
        button { padding: 10px 20px; background: #2563eb; color: #fff; border: none; border-radius: 4px; cursor: pointer; }
        .success { color: green; margin-bottom: 20px; }
        .error { color: red; margin-bottom: 20px; }
    </style>
</head>
<body>
<h2>Issue Book</h2>
<% String success = (String) request.getAttribute("success"); %>
<% String error = (String) request.getAttribute("error"); %>
<% if (success != null) { %>
    <div class="success"><%= success %></div>
<% } %>
<% if (error != null) { %>
    <div class="error"><%= error %></div>
<% } %>
<form method="post" action="/issuebook">
    <label for="bookId">Book ID:</label>
    <input type="text" id="bookId" name="bookId" required />
    <label for="memberId">Member ID:</label>
    <input type="text" id="memberId" name="memberId" required />
    <label for="issueDate">Issue Date:</label>
    <input type="date" id="issueDate" name="issueDate" required />
    <button type="submit">Issue Book</button>
</form>
</body>
</html>