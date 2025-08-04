<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-image: url('images/download.jpg');
        background-size: cover;
        background-repeat: no-repeat;
        background-position: center;
        margin: 0;
        padding: 0;
    }

    .content {
        background-color: rgba(255, 255, 255, 0.5);
        width: 70%;
        max-width: 700px;
        margin: 40px auto;
        padding: 20px 30px;
        border-radius: 8px;
        text-align: center;
    }

    h1 {
        color: #2c3e50;
        margin-bottom: 20px;
    }

    table {
        width: 90%;
        border-collapse: collapse;
        margin: 15px auto;
    }

    th, td {
        padding: 8px 12px;
        border: 1px solid #444;
        font-size: 14px;
        text-align: left;
    }

    th {
        background-color: #4CAF50;
        color: white;
    }

    tr:hover {
        background-color: rgba(76, 175, 80, 0.1);
    }

    .back-btn {
        margin-top: 25px;
    }
    .back-btn button {
        padding: 10px 20px;
        font-size: 15px;
        background-color: #4CAF50;
        border: none;
        color: white;
        border-radius: 6px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    .back-btn button:hover {
        background-color: #388E3C;
    }
</style>
</head>
<body>
    <div class="content">
        <h1>Hi, welcome to the Books Section</h1>
        <table>
            <thead>
                <tr>
                    <th>Bookid</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Category</th>
                    <th>Status</th>
                    <th>Availability</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="item">
                    <tr>
                        <td>${item.bookid}</td>
                        <td>${item.title}</td>
                        <td>${item.author}</td>
                        <td>${item.category}</td>
                        <td>${item.status}</td>
                        <td>${item.availability}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="back-btn">
            <form action="books.html" method="get">
                <button type="submit">Back to Books</button>
            </form>
        </div>
    </div>
</body>
</html>
