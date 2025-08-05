<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Options</title>

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(to right, #6a11cb, #2575fc);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: white;
            padding: 40px 30px;
            border-radius: 15px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            width: 350px;
            text-align: center;
        }

        h2 {
            margin-bottom: 30px;
            color: #333;
        }

        .btn {
            display: inline-block;
            width: 100%;
            padding: 12px 20px;
            margin-top: 15px;
            font-size: 16px;
            font-weight: 600;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            transition: all 0.3s ease;
        }

        .btn:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
        }

        .btn.secondary {
            background-color: #6c757d;
        }

        .btn.secondary:hover {
            background-color: #5a6268;
        }

        form {
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Book Options</h2>

        <form action="AddBookOptionController" method="post">
            <input type="hidden" name="action" value="addbook">
            <button type="submit" class="btn">Add Book</button>
        </form>

        <form action="AddBookOptionController" method="post">
            <input type="hidden" name="action" value="viewallBooks"> 
            <button type="submit" class="btn">View All Books</button>
        </form>

        
        <a href="MainPage.jsp" class="btn secondary">Go Back</a>
    </div>
</body>
</html>
