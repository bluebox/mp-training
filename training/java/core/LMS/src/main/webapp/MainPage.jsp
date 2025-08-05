<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Management System</title>

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            margin-top: 40px;
            color: #333;
        }

        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 20px;
            margin-top: 50px;
        }

        button {
            padding: 12px 25px;
            width: 200px;
            font-size: 16px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.2s;
        }

        button:hover {
            background-color: #0056b3;
            transform: scale(1.03);
        }

        form {
            margin: 0;
        }
    </style>
</head>
<body>
    <h1>Library Management System</h1>
    <div class="button-container">
        <form action="MainPageController" method="post">
            <input type="hidden" name="action" value="addbook">
            <button type="submit">Add Book</button>
        </form>

        <form action="MainPageController" method="post">
            <input type="hidden" name="action" value="addmember">
            <button type="submit">Add Member</button>
        </form>

        <form action="MainPageController" method="post">
        <input type="hidden" name="action" value="issueandreturn">
            <button type="submit">Issue And Return</button>
        </form>

        <form action="MainPageController" method="post">
         <input type="hidden" name="action" value="reports">
            <button type="submit">Reports</button>
        </form>
    </div>
</body>
</html>
