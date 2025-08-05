<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Book Result</title>
</head>
<body>
    <h2>Issue Book Result</h2>
    <p>${message}</p>

    

    <form action="${pageContext.request.contextPath}/issuerecords.html" method="get">
        <button type="submit">Back to issuerecords</button>
    </form>
</body>
</html>
