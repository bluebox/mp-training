<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        
        h1 {
            font-size: 2.5em;
            color: #333;
        }
        
        h2 {
            font-size: 1.5em;
            color: #555;
            margin-bottom: 30px;
        }

        .button-row {
            display: flex;
            justify-content: center;
            gap: 15px; 
            margin-bottom: 15px;
        }
        
        .button-row form {
            display: inline-block;
        }

        button {
            width: 180px;
            height: 50px;
            background-color: #3498db; 
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }
        
        button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <h1>Library Management System</h1>
    <h2>Main Menu</h2>

    <div class="button-row">
        <form action="addBook" method="get">
            <button type="submit">Add Book</button>
        </form>
        <form action="viewBooks" method="get">
            <button type="submit">View Books</button>
        </form>
        <form action="addMember" method="get">
            <button type="submit">Add Member</button>
        </form>
        <form action="viewMembers" method="get">
            <button type="submit">View Members</button>
        </form>
    </div>

    <div class="button-row">
        <form action="issueBook" method="get">
            <button type="submit">Issue Book</button>
        </form>
        <form action="returnBook" method="get">
            <button type="submit">Return Book</button>
        </form>
        <form action="viewIssuedRecords" method="get">
            <button type="submit">View Issue Records</button>
        </form>
        <form action="reports" method="get">
            <button type="submit">Reports</button>
        </form>
    </div>

</body>
</html>