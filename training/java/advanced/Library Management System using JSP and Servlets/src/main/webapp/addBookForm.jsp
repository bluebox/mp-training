<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Book</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; background-color: #f4f7f6; }
        .container { max-width: 500px; margin: 0 auto; padding: 30px; background-color: #ffffff; box-shadow: 0 4px 8px rgba(0,0,0,0.1); border-radius: 10px; }
        h2 { color: #2c3e50; font-size: 24px; margin-bottom: 20px; }
        .form-row { margin-bottom: 15px; text-align: left; display: flex; align-items: center; }
        .form-row label { flex: 1; padding-right: 10px; font-weight: bold; }
        .form-row input, .form-row select { flex: 2; padding: 8px; border: 1px solid #ccc; border-radius: 5px; }
        .message-label { margin-top: 15px; font-weight: bold; }
        .success { color: green; }
        .error { color: red; }
        .button-row { display: flex; justify-content: center; gap: 10px; margin-top: 20px; }
        .button-row button { padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; font-weight: bold; }
        .add-button { background-color: #3498db; color: white; }
        .add-button:hover { background-color: #2980b9; }
        .back-button { background-color: #95a5a6; color: white; }
        .back-button:hover { background-color: #7f8c8d; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add New Book</h2>

        <c:if test="${not empty requestScope.statusMessage}">
            <p class="message-label ${requestScope.statusType}"><c:out value="${requestScope.statusMessage}" /></p>
        </c:if>

        <form action="addBook" method="post">
            <div class="form-row">
                <label for="titleField">Title:</label>
                <input type="text" id="titleField" name="title" placeholder="Enter book title" required>
            </div>
            <div class="form-row">
                <label for="authorField">Author:</label>
                <input type="text" id="authorField" name="author" placeholder="Enter author name" required>
            </div>
            <div class="form-row">
                <label for="categoryComboBox">Category:</label>
                <select id="categoryComboBox" name="category" required>
                    <option value="" disabled selected>Select category</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category}">${category.displayName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-row">
                <label for="statusComboBox">Status:</label>
                <select id="statusComboBox" name="status" required>
                    <option value="" disabled selected>Select status</option>
                    <c:forEach var="status" items="${statuses}">
                        <option value="${status}">${status.name()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-row">
                <label for="availabilityComboBox">Availability:</label>
                <select id="availabilityComboBox" name="availability" required>
                    <option value="" disabled selected>Select availability</option>
                    <c:forEach var="availability" items="${availabilities}">
                        <option value="${availability}">${availability.name()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="button-row">
                <button type="submit" class="add-button">Add Book</button>
                <button type="button" onclick="window.location.href='homeScreen.jsp'" class="back-button">Back to Main Menu</button>
            </div>
        </form>
    </div>
</body>
</html>