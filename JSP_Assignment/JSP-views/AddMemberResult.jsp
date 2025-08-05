<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Member Result</title>
</head>
<body style="background: #f4f4f4;">
    <h2 align="center">
        <% if (request.getAttribute("message") != null) { %>
            <span style="color: green;"><%= request.getAttribute("message") %></span>
        <% } else if (request.getAttribute("error") != null) { %>
            <span style="color: red;"><%= request.getAttribute("error") %></span>
        <% } %>
    </h2>
    <div align="center">
        <a href="AddMember.jsp">Back to Add Member</a>
    </div>
</body>
</html>
