<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reports</title>
    <style>
        body { font-family: Arial; padding: 20px; text-align: center; }
        button {
            margin: 10px;
            padding: 15px 25px;
            font-size: 16px;
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Library Reports</h2>

    <form action="overduebooks" method="get">
        <button type="submit">Overdue Books</button>
    </form>

    <form action="bookspercategory" method="get">
        <button type="submit">Books Per Category</button>
    </form>

    <form action="memberswithissued" method="get">
        <button type="submit">Members with Issued Books</button>
    </form>
</body>
</html>
