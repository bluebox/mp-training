<!DOCTYPE html>
<html>
<head>
    <title>Return Book</title>
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
        input[type="submit"] {
            margin-top: 15px;
            padding: 8px 20px;
            font-size: 14px;
            background-color: #4CAF50; 
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        form {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <h2>Return a Book</h2>
    <form action="${pageContext.request.contextPath}/returnbook" method="post">
        <label for="bookId">Book ID:</label>
        <input type="text" id="bookId" name="bookid" required><br><br>

        <label for="memberId">Member ID:</label>
        <input type="text" id="memberId" name="memberid" required><br><br>

        <input type="submit" value="Return Book">
    </form>

    <form action="${pageContext.request.contextPath}/issuerecords.html" method="post">
        <input type="submit" value="Back to Issue Records" style="background-color:#4CAF50; color:white; border:none; padding:8px 20px; border-radius:4px; cursor:pointer;">
    </form>
</body>
</html>
