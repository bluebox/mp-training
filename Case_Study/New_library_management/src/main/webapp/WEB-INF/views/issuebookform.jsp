<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Book</title>
    <style>
        body {
            background-color: #f9f9f9;
            font-family: sans-serif;
            margin: 40px;
        }
        label {
            display: inline-block;
            width: 100px;
            font-weight: bold;
        }
        input[type="text"] {
            padding: 6px;
            width: 200px;
        }
        input[type="submit"], button {
            margin-top: 15px;
            padding: 8px 20px;
            font-size: 14px;
            background-color: #4CAF50; /* green */
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover, button:hover {
            background-color: #45a049;
        }
        form {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <h2>Issue a Book</h2>
    <form action="${pageContext.request.contextPath}/issuebook" method="post">
        <label for="bookId">Book ID:</label>
        <input type="text" id="bookId" name="bookid" required><br><br>

        <label for="memberId">Member ID:</label>
        <input type="text" id="memberId" name="memberid" required><br><br>

        <input type="submit" value="Issue Book">
    </form>

    <form action="${pageContext.request.contextPath}/issuerecords.html" method="get">
        <button type="submit">Back to Issue Records</button>
    </form>
</body>
</html>
