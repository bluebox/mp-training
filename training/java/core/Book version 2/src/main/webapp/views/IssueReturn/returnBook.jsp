<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Return Book</title>
<style>
    body {
        font-family: 'Segoe UI', sans-serif;
        margin: 0;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        background-image:
            url('${pageContext.request.contextPath}/resources/janko-ferlic-sfL_QOnmy00-unsplash.jpg');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
    .container {
        background-color: white;
        padding: 40px 30px;
        border-radius: 12px;
        text-align: center;
        width: 350px;
       
    }
    h2 {
        font-weight: bold;
        font-size: 26px;
        margin-bottom: 30px;
        color: #333;
    }
    label {
        font-size: 18px;
        margin-right: 10px;
        color: #333;
    }
    input[type="text"] {
        width: 150px;
        padding: 8px 10px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 6px;
        box-sizing: border-box;
        max-width: 150px;
    }
    .status {
        color: red;
        font-size: 13px;
        margin-top: 8px;
        min-height: 18px;
    }
    .button-group {
        margin-top: 25px;
        display: flex;
        justify-content: center;
        gap: 20px;
    }
    button {
        padding: 10px 25px;
        font-size: 16px;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        background-color: #007BFF;
        color: white;
        transition: background-color 0.3s ease;
    }
    button:hover {
        background-color: #0056b3;
    }
    button.back-btn {
        background-color: #6c757d;
    }
    button.back-btn:hover {
        background-color: #565e64;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Return Book</h2>
        <form action="${pageContext.request.contextPath}/IssueReturnController" method="post">
            <input type="hidden" name="action" value="return" />
            <div style="display: flex; justify-content: center; align-items: center; gap: 10px;">
                <label for="bookId">Enter Book ID:</label>
                <input type="text" id="bookId" name="bookId" placeholder="Book ID" required />
            </div>
            <div class="message" style="color: ${requestScope.messageColor}">
				${requestScope.message}</div>
            <div class="button-group">
                <button type="submit">Submit</button>
                <button type="button" class="back-btn" onclick="window.location.href='${pageContext.request.contextPath}/views/IssueReturn/issueReturn.jsp'">Back</button>
            </div>
        </form>
    </div>
</body>
</html>
