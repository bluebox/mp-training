<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Member</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }
        .container { width: 500px; margin: 0 auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        h2 { color: #333; }
        .form-row { margin-bottom: 15px; text-align: left; }
        .form-row label { display: inline-block; width: 150px; text-align: right; margin-right: 10px; }
        .form-row input[type="text"] { width: 250px; padding: 5px; }
        .gender-options { display: inline-block; }
        .gender-options input { margin-right: 5px; }
        .button-row { margin-top: 20px; }
        .button-row button { padding: 10px 20px; cursor: pointer; margin: 0 5px; }
        .status-label { margin-top: 15px; font-weight: bold; }
        .success { color: green; }
        .error { color: red; }
        
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Member Details</h2>

        <c:if test="${not empty requestScope.statusMessage}">
            <p class="status-label ${requestScope.statusType}"><c:out value="${requestScope.statusMessage}" /></p>
        </c:if>

        <form action="updateMember" method="post">
            <div class="form-row">
                <label>Member ID:</label>
                <input type="text" name="memberID" value="${member.memberID}" readonly>
            </div>
            <div class="form-row">
                <label>Name:</label>
                <input type="text" name="name" value="${member.name}" required>
            </div>
            <div class="form-row">
                <label>Email:</label>
                <input type="text" name="email" value="${member.email}" required>
            </div>
            <div class="form-row">
                <label>Phone Number:</label>
                <input type="text" name="phoneNumber" value="${member.phoneNumber}" required>
            </div>
            <div class="form-row">
                <label>Gender:</label>
                <div class="gender-options">
                    <input type="radio" name="gender" id="male" value="M" <c:if test="${member.gender eq 'MALE'}">checked</c:if>>
                    <label for="male">Male</label>
                    <input type="radio" name="gender" id="female" value="F" <c:if test="${member.gender eq 'FEMALE'}">checked</c:if>>
                    <label for="female">Female</label>
                    <input type="radio" name="gender" id="other" value="O" <c:if test="${member.gender eq 'OTHER'}">checked</c:if>>
                    <label for="other">Other</label>
                </div>
            </div>
            <div class="form-row">
                <label>Address:</label>
                <input type="text" name="address" value="${member.address}" required>
            </div>

            <div class="button-row">
                <button type="submit">Update</button>
                <button type="button" onclick="window.history.back()">Cancel</button>
            </div>
        </form>
    </div>
</body>
</html>