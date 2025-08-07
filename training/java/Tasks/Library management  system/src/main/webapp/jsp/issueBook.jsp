<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Issue Book</title>
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

    .home-btn, .back-btn {
        position: absolute;
        top: 20px;
        padding: 6px 12px;
        font-weight: bold;
        font-size: 12px;
        border-radius: 6px;
        border: 2px solid;
        text-decoration: none;
        cursor: pointer;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    .home-btn {
        right: 20px;
        background-color: white;
        color: #2e7d32;
        border-color: #2e7d32;
    }

    .home-btn:hover {
        background-color: #2e7d32;
        color: white;
    }

    .back-btn {
        left: 20px;
        background-color: white;
        color: #0d47a1;
        border-color: #0d47a1;
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
        width: 100%;
        max-width: 360px;
    }

    h1 {
        text-align: center;
        font-size: 18px;
        color: #0d47a1;
        margin-bottom: 20px;
        border-bottom: 1.5px solid #90caf9;
        padding-bottom: 6px;
    }

    label {
        display: block;
        margin-top: 12px;
        font-size: 13px;
        font-weight: bold;
        color: #333;
    }

    select {
        width: 100%;
        padding: 8px;
        font-size: 13px;
        border-radius: 6px;
        border: 1px solid #ccc;
        margin-top: 4px;
        font-family: inherit;
        outline: none;
        transition: border-color 0.3s ease;
    }

    select:focus {
        border-color: #64b5f6;
        box-shadow: 0 0 5px rgba(100, 181, 246, 0.5);
    }

    .btn {
        margin-top: 20px;
        padding: 10px;
        width: 100%;
        font-size: 13px;
        border-radius: 6px;
        border: none;
        cursor: pointer;
        background-color: #64b5f6;
        color: white;
        transition: background-color 0.3s ease;
        font-family: inherit;
        font-weight: bold;
    }

    .btn:hover {
        background-color: #42a5f5;
    }

    .popup {
        position: fixed;
        top: 30px;
        left: 50%;
        transform: translateX(-50%);
        padding: 12px 25px;
        font-size: 13px;
        font-weight: bold;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.2);
        z-index: 9999;
        display: none;
    }

    .popup.success {
        background-color: #c8e6c9;
        color: #2e7d32;
    }

    .popup.error {
        background-color: #ffcdd2;
        color: #c62828;
    }

</style>
</head>
<body>

<a href="${pageContext.request.contextPath}/jsp/index.jsp" class="home-btn">🏠 Home</a>
<a href="${pageContext.request.contextPath}/jsp/issueReturn.jsp" class="back-btn">← Back</a>

<!-- Show popup if message exists -->
<c:if test="${not empty message}">
    <div id="popupMessage" class="popup ${status}">
        ${message}
    </div>
</c:if>

<div class="container">
    <h1>📖 Issue Book</h1>
    <form action="issueRecord" method="post">
        <label>Select Member:</label>
        <select name="member" required>
            <option value="" disabled selected>Select Member</option>
            <c:forEach var="member" items="${members}">
                <option value="${member.memberId}">${member.memberId}. ${member.memberName}</option>
            </c:forEach>
        </select>

        <label>Select Book:</label>
        <select name="book" required>
            <option value="" disabled selected>Select Book</option>
            <c:forEach var="book" items="${books}">
                <option value="${book.bookId}">${book.bookId}. ${book.title}</option>
            </c:forEach>
        </select>

        <input type="submit" value="📘 Issue Book" class="btn" />
    </form>
</div>

<script>
    const popup = document.getElementById('popupMessage');
    if (popup) {
        popup.style.display = 'block';
        setTimeout(() => {
            popup.style.display = 'none';
        }, 3000);
    }
</script>

</body>
</html>
