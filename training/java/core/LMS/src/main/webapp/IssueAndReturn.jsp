<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Issue Management</title>
    <link rel="stylesheet" type="text/css" href="../../application/css/member.css" />
    <style>
        .title-label {
            font-family: 'Arial', sans-serif;
            font-size: 20px;
            font-weight: bold;
            text-align: center;
            margin-top: 20px;
        }
        .button-container {
            text-align: center;
            margin-top: 50px;
        }
        .book-button {
            font-size: 16px;
            padding: 10px 20px;
            margin: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .book-button:hover {
            background-color: #45a049;
        }
        .book-buttons {
            font-size: 16px;
            padding: 10px 20px;
            margin: 10px;
            background-color: #f44336;
            color: white;
            border: none;
            cursor: pointer;
        }
        .book-buttons:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>
    <div class="title-label">
        <h1>Issue Management</h1>
    </div>

    <div class="button-container">
       
        <form action="IssueAndReturnController" method="post">
            <button type="submit" class="book-button" name="action" value="issueBook">Issue Book</button>
        </form>

     
        <form action="IssueAndReturnController" method="post">
            <button type="submit" class="book-button" name="action" value="returnBook">Return Book</button>
        </form>

      
        <form action="IssueAndReturnController" method="post">
            <button type="submit" class="book-button" name="action" value="viewIssues">View All Issues</button>
        </form>

     
        <form action="MainPage.jsp" method="get">
            <button type="submit" class="book-buttons" name="action" value="back" text-fill="RED">Back</button>
        </form>
    </div>

</body>
</html>
