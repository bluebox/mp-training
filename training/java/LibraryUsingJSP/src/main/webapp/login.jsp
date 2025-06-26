

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2>Login Form</h2>

    <%-- Show error message if login failed --%>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p style="color:red;"><%= error %></p>
    <%
        }
    %>

    <form action="loginProcess.jsp" method="post">
        Username: <input type="text" name="username" required /><br/><br/>
        Password: <input type="password" name="password" required /><br/><br/>
        <input type="submit" value="Login" />
    </form>
</body>
</html>
