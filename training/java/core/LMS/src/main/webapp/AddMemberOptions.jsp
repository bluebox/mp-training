<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Management System</title>
    <link rel="stylesheet" type="text/css" href="../../application/css/member.css">
    <style>
      
        .title-label {
            font-family: 'Arial Black', sans-serif;
            font-size: 20px;
            text-align: center;
        }

        .book-button, .book-buttons {
            padding: 10px 20px;
            margin-top: 15px;
            font-size: 16px;
            cursor: pointer;
        }

        .book-button {
           
        }

        .book-buttons {
           
        }

        .center-vbox {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 15px;
            padding-top: 50px;
        }
    </style>
</head>
<body>
    <div id="rootPane">
        <div class="center-vbox">
            <label class="title-label">Members Management</label>

            <form action="AddMemberOptionController" method="post">
            <input type="hidden" name="action" value="addmember">
                <button type="submit" class="book-button">Add a Member</button>
            </form>

            <form action="AddMemberOptionController" method="post">
            <input type="hidden" name="action" value="viewmembers">
                <button type="submit" class="book-button">View All Members</button>
            </form>

            <form action="MainPage.jsp" method="get">
                <button type="submit" class="book-buttons">Back</button>
            </form>
        </div>
    </div>
</body>
</html>
