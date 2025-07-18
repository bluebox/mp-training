<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>404 Not Found</title>
</head>
<body>
    <h1>Oops! Page Not Found (404)</h1>
    <p>please check the route again</p>
    <p>The requested URL <strong><%= request.getRequestURI() %></strong> was not found on this server.</p>
</body>
</html>
