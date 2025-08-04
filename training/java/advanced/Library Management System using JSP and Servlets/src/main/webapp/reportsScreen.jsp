<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Reports</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin: 50px; background-color: #f4f7f6; }
        .container { max-width: 800px; margin: 0 auto; padding: 30px; background-color: #ffffff; box-shadow: 0 4px 8px rgba(0,0,0,0.1); border-radius: 10px; }
        h2 { color: #2c3e50; font-size: 24px; margin-bottom: 20px; }
        .button-row { display: flex; justify-content: center; gap: 10px; margin-bottom: 20px; }
        .button-row button, .back-button { padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; font-weight: bold; }
        .report-button { background-color: #3498db; color: white; }
        .report-button:hover { background-color: #2980b9; }
        .back-button { background-color: #95a5a6; color: white; }
        .back-button:hover { background-color: #7f8c8d; }
        textarea { width: 100%; height: 400px; padding: 15px; border: 1px solid #ccc; border-radius: 5px; resize: vertical; box-sizing: border-box; }
        .message-label { margin-top: 15px; font-weight: bold; color: red; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Library Reports</h2>
        <div class="button-row">
            <form action="reports" method="get">
                <input type="hidden" name="type" value="overdue">
                <button type="submit" class="report-button">Overdue Books</button>
            </form>
            <form action="reports" method="get">
                <input type="hidden" name="type" value="booksByCategory">
                <button type="submit" class="report-button">Books Per Category</button>
            </form>
            <form action="reports" method="get">
                <input type="hidden" name="type" value="membersWithActiveBooks">
                <button type="submit" class="report-button">Members with Active Books</button>
            </form>
        </div>
        <textarea id="reportDisplayArea" readonly>${reportContent}</textarea>
        <c:if test="${not empty errorMessage}">
            <p class="message-label">${errorMessage}</p>
        </c:if>
        <div style="margin-top:20px;">
            <a href="homeScreen.jsp"><button class="back-button">Back to Main Menu</button></a>
        </div>
    </div>
</body>
</html>