<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }
        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>An Error Occurred</h1>
    
    <c:if test="${not empty errorMessage}">
        <p class="error-message">
            <c:out value="${errorMessage}" />
        </p>
    </c:if>
    
    <c:if test="${empty errorMessage}">
        <p>An unexpected error occurred.</p>
    </c:if>
    
    <p>
        <a href="homeScreen.jsp">Back to Main Menu</a>
    </p>
</body>
</html>