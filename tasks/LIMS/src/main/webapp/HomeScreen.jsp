<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Library Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif; 
            text-align: center;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4; 
        }
        h1, h2 {
            color: #333;
        }
        .main-menu-section {
            background-color: #fff;
            margin: 20px auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            max-width: 800px; 
        }
        .button-row-container {
            display: flex;
            justify-content: center; 
            flex-wrap: wrap; 
            gap: 15px; 
            margin-top: 20px; 
            margin-bottom: 20px; 
        }
        .button-row-container form {
            margin: 0; 
        }
        .main-menu-button {
            padding: 12px 25px; 
            font-size: 1.1em; 
            cursor: pointer;
            border: none; 
            border-radius: 6px; 
            color: white; 
            transition: background-color 0.2s ease; 
            min-width: 160px; 
            box-sizing: border-box; 
        }
        .main-menu-button:hover {
            background-color: #0056b3; 
        }
        #statusLabel {
            color: #007bff;
            margin-top: 30px; 
            font-size: 1.05em;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="main-menu-section">
        <h1>Library Management System</h1>
        <h2>Main Menu</h2>

        <div class="button-row-container">
            <form action="addBook" method="get">
                <button type="submit" class="main-menu-button">Add Book</button>
            </form>
            <form action="viewBooks" method="get">
                <button type="submit" class="main-menu-button">View Books</button>
            </form>
            <form action="addMember" method="get">
                <button type="submit" class="main-menu-button">Add Member</button>
            </form>
            <form action="viewMembers" method="get">
                <button type="submit" class="main-menu-button">View Members</button>
            </form>
        </div>

        <div class="button-row-container">
            <form action="issueBook" method="get">
                <button type="submit" class="main-menu-button">Issue Book</button>
            </form>
            <form action="returnBook" method="get">
                <button type="submit" class="main-menu-button">Return Book</button>
            </form>
            <form action="issuedRecords" method="get">
                <button type="submit" class="main-menu-button">View Issue Records</button>
            </form>
            <form action="reports" method="get">
                <button type="submit" class="main-menu-button">Reports</button>
            </form>
        </div>
        
        <div>
            <p id="statusLabel">
                <%
                    String statusMessage = (String) request.getAttribute("statusMessage");
                    if (statusMessage != null) {
                        out.println(statusMessage);
                    }
                %>
            </p>
        </div>
    </div>
</body>
</html>