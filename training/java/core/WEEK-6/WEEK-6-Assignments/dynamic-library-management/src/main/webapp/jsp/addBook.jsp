<%@page import="com.dynamic_library_management.domain.BookCategory"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	List<BookCategory> categoryList = List.of(BookCategory.values());
	request.setAttribute("categoryList", categoryList);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
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

        .home-btn, .back-btn {
            position: absolute;
            top: 15px;
            padding: 6px 12px;
            font-size: 14px;
            font-weight: bold;
            border-radius: 6px;
            border: none;
            text-decoration: none;
        }

        .home-btn {
            right: 15px;
            background-color: #c8e6c9;
            color: #1b5e20;
        }

        .back-btn {
            left: 15px;
            background-color: #bbdefb;
            color: #0d47a1;
        }

        .container {
            background: rgba(255, 255, 255, 0.9);
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.25);
            width: 360px;
        }

        h1 {
            text-align: center;
            font-size: 22px;
            color: #0d47a1;
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-top: 10px;
            font-size: 14px;
            font-weight: bold;
        }

        input[type="text"], select {
            width: 100%;
            padding: 8px;
            font-size: 13px;
            border-radius: 6px;
            border: 1px solid #ccc;
            margin-top: 4px;
        }

        .btn {
            margin-top: 20px;
            padding: 10px;
            width: 100%;
            font-size: 14px;
            border-radius: 6px;
            border: none;
            cursor: pointer;
            background-color: #64b5f6;
            color: white;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #42a5f5;
        }

        .popup {
            position: absolute;
            top: 10%;
            left: 50%;
            transform: translateX(-50%);
            background-color: #f0f0f0;
            color: #000;
            padding: 10px 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            z-index: 999;
            font-size: 14px;
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

    <a href="${pageContext.request.contextPath}/jsp/index.jsp" class="home-btn">Home</a>
    <a href="${pageContext.request.contextPath}/jsp/bookOptions.jsp" class="back-btn">Back</a>

    <c:if test="${not empty message}">
        <div id="popupMessage" class="popup ${success ? 'success' : 'error'}">${message}</div>
    </c:if>

    <div class="container">
        <h1>Add Book</h1>

        <form action="/dynamic-library-management/addBookController" method="post">
            <label>Title:</label>
            <input type="text" name="title" placeholder="Enter Book Title" required />

            <label>Author:</label>
            <input type="text" name="author" placeholder="Enter Author Name" required />

            <label>Category:</label>
            <select name="category" required>
                <option value="">Select Category</option>
                <c:forEach items="${categoryList}" var="category">
                    <option value="${category.stringValue}">${category.toString()}</option>
                </c:forEach>
            </select>

            <input type="submit" value="➕ Add Book" class="btn" />
        </form>
    </div>

    <script>
        const popup = document.getElementById('popupMessage');
        if (popup) {
            popup.style.display = 'block';
            setTimeout(() => {
                popup.style.display = 'none';
            }, 5000);
        }
    </script>

</body>
</html>
