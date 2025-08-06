<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management System</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background: 
            linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
            url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=1470&q=80'); 
        background-size: cover;
        background-position: center;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        position: relative;
    }

    .back-btn {
        position: absolute;
        top: 15px;
        left: 15px;
        padding: 6px 12px;
        background-color: rgba(255, 255, 255, 0.8);
        color: #0d47a1;
        font-size: 14px;
        font-weight: bold;
        border: none;
        border-radius: 6px;
        text-decoration: none;
        transition: background-color 0.3s ease;
        z-index: 10;
    }

    .back-btn:hover {
        background-color: #bbdefb;
    }

    .container {
        background: rgba(255, 255, 255, 0.95);
        padding: 25px 40px;
        border-radius: 12px;
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
        text-align: center;
        width: 360px;
    }

    h1 {
        font-size: 22px;
        color: #0d47a1;
        margin-bottom: 20px;
        border-bottom: 2px solid #90caf9;
        display: inline-block;
        padding-bottom: 8px;
    }

    .btn {
        display: block;
        margin: 10px auto;
        padding: 10px 24px;
        font-size: 14px;
        background-color: #64b5f6;
        color: white;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        text-decoration: none;
        transition: background-color 0.3s ease;
    }

    .btn:hover {
        background-color: #42a5f5;
    }
</style>
</head>
<body>

    <div class="container">
        <h1>Library Management System</h1>

        <a href="bookOptions.jsp" class="btn">📚 Book Management</a>
        <a href="memberManagement.jsp" class="btn">👤 Member Management</a>
        <a href="issueReturn.jsp" class="btn">🔄 Issue & Return Books</a>
        <a href="reports.jsp" class="btn">📊 Reports</a>
    </div>

</body>
</html>
