<%@ page isErrorPage="true" language="java" import="java.lang.Exception" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error handling</title>
</head>
<body>

<p>Error occured in division:
<%= exception.getMessage() %>
</p>
</body>
</html>