<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Server Error</title>
</head>
<body>
    <h1>Something Went Wrong (500)</h1>
    <p>An internal server error occurred.</p>
    <p><b>Error:</b> <%= exception.getMessage() %></p>
</body>
</html>
