<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management System</title>
<style>
    body {
        font-family: 'Segoe UI', sans-serif;
        margin: 0;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        background-image: url('../resources/janko-ferlic-sfL_QOnmy00-unsplash.jpg');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }

    .container {
        background-color: white; 
        padding: 40px;
        border-radius: 12px;
        text-align: center;
        width: 400px;
    }

    h3 {
        font-size: 26px;
        margin-bottom: 30px;
        color: #333;
    }

    form {
        margin-bottom: 20px;
    }

    input[type="submit"] {
        width: 100%;
        padding: 12px;
        font-size: 18px;
        background: #007BFF;
        color: #fff;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        transition: 0.3s;
    }

    input[type="submit"]:hover {
        background: #0056b3;
    }
</style>
</head>
<body>
    <div class="container">
        <h3>Welcome to Library Management System</h3>
        <form action="../views/Books/books.jsp">
            <input type="submit" value="Books" />
        </form>
        <form action="../views/Members/members.jsp">
            <input type="submit" value="Members" />
        </form>
        <form action="../views/IssueReturn/issueReturn.jsp">
            <input type="submit" value="Issue and Return" />
        </form>
        <form action="${pageContext.request.contextPath}/ReportsController">
            <input type="submit" value="Reports" />
        </form>
    </div>
</body>
</html>