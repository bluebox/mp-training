<!DOCTYPE html>
<html>
<head>
    <title>Return Book Result</title>
    <style>
        body {
            background-color: #f9f9f9; 
            font-family: sans-serif;
            margin: 0;
            padding: 20px;
            text-align: center;
        }
        button {
            margin-top: 20px;
            padding: 8px 20px;
            font-size: 14px;
            background-color: #4CAF50; 
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
    <script>
        window.onload = function() {
            const msg = "<%= request.getAttribute("message") != null ? request.getAttribute("message").toString().replaceAll("\"", "\\\"") : "" %>";
            if (msg) {
                alert(msg);
            }
        }
    </script>
</head>
<body>
    <form action="${pageContext.request.contextPath}/issuerecords.html" method="get">
        <button type="submit">Back to Issue Records</button>
    </form>
</body>
</html>
