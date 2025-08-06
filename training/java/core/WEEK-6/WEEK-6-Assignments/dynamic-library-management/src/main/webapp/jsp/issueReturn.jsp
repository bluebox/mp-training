<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Issue / Return Book</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: 
            linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)),
            url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=1470&q=80');
        background-size: cover;
        background-position: center;
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        position: relative;
    }

    .back-btn {
        position: absolute;
        top: 20px;
        left: 20px;
        padding: 6px 12px;
        background-color: rgba(255, 255, 255, 0.95);
        color: #0d47a1;
        font-weight: bold;
        font-size: 12px;
        border: 2px solid #0d47a1;
        border-radius: 6px;
        text-decoration: none;
        transition: background-color 0.3s ease, color 0.3s ease;
        z-index: 10;
    }

    .back-btn:hover {
        background-color: #0d47a1;
        color: white;
    }

    .container {
        background: rgba(255, 255, 255, 0.96);
        padding: 25px 30px;
        border-radius: 12px;
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.25);
        text-align: center;
        width: 100%;
        max-width: 340px;
    }

    h1 {
        font-size: 18px;
        color: #0d47a1;
        margin-bottom: 20px;
        border-bottom: 1.5px solid #90caf9;
        padding-bottom: 6px;
    }

    .btn {
        display: block;
        margin: 12px auto;
        padding: 10px 20px;
        font-size: 15px;
        background-color: #64b5f6;
        color: white;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        text-decoration: none;
        box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
        transition: background-color 0.3s ease;
    }

    .btn:hover {
        background-color: #42a5f5;
    }

   
</style>
</head>
<body>

    <a href="${pageContext.request.contextPath}/jsp/index.jsp" class="back-btn">← Back</a>

    <div class="container">
        <h1>📚 Issue / Return Book</h1>
        <a href="../issueRecord" class="btn">📖 Issue Book</a>
        <a href="../returnRecord" class="btn">🔄 Return Book</a>
        <a href="../viewIssues" class="btn">📋 View All Issues</a>
    </div>

</body>
</html>
