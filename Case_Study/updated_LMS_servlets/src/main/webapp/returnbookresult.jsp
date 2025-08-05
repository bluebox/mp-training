<!DOCTYPE html>
<html>
<head>
    <title>Return Book Result</title>
</head>
<body>
    <h2>Return Book Result</h2>
    <p>${message}</p>

    
    <form action="${pageContext.request.contextPath}/issuerecords.html" method="get">
        <button type="submit">Back to Issue Records</button>
    </form>
</body>
</html>
