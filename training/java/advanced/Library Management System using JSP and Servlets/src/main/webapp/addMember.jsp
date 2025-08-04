<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Member</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f6;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .form-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        .error-message {
            color: red;
            font-weight: bold;
            margin-bottom: 15px;
        }
        .success-message {
            color: green;
            font-weight: bold;
            margin-bottom: 15px;
        }
        form label {
            display: block;
            text-align: left;
            margin-bottom: 5px;
            font-weight: bold;
        }
        form input[type="text"],
        form input[type="email"],
        form input[type="number"],
        form input[type="submit"],
        form select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        form input[type="submit"] {
            background-color: #3498db;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        form input[type="submit"]:hover {
            background-color: #2980b9;
        }
        .back-button-container {
            margin-top: 20px;
        }
        .back-button {
            width: 100%;
            padding: 10px;
            background-color: #7f8c8d;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            display: inline-block;
            box-sizing: border-box;
            transition: background-color 0.3s;
        }
        .back-button:hover {
            background-color: #555;
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>Add a New Member</h2>

        <c:if test="${not empty successMessage}">
            <p class="success-message"><c:out value="${successMessage}" /></p>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <p class="error-message"><c:out value="${errorMessage}" /></p>
        </c:if>

        <form action="addMember" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="phoneNumber">Phone Number:</label>
            <input type="number" id="phoneNumber" name="phoneNumber" required>

            <label for="gender">Gender:</label>
            <select id="gender" name="gender" required>
                <option value="">Select Gender</option>
                <option value="M">Male</option>
                <option value="F">Female</option>
                <option value="O">Other</option>
            </select>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>
            
            <input type="submit" value="Add Member">
        </form>

        <div class="back-button-container">
            <a href="homeScreen.jsp" class="back-button">Back to Main Menu</a>
        </div>
    </div>

</body>
</html>