<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.LibraryManagement.models.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Member Details</title>
<style>
body {
    font-family: 'Segoe UI', sans-serif;
    margin: 0;
    padding: 40px;
    background-image:
        url('${pageContext.request.contextPath}/resources/janko-ferlic-sfL_QOnmy00-unsplash.jpg');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    background-attachment: fixed;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.container {
    background-color: white;
    padding: 30px;
    border-radius: 10px;
    width: 400px;
   
}

h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #333;
}

label {
    display: block;
    margin-top: 15px;
    font-size: 16px;
    color: #333;
}

input[type="text"], select {
    width: 100%;
    padding: 10px;
    font-size: 15px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 6px;
    box-sizing: border-box;
}

input[type="submit"] {
    margin-top: 20px;
    width: 100%;
    padding: 12px;
    font-size: 16px;
    background-color: #007BFF;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #0056b3;
}

.error-message {
    color: red;
    font-size: 13px;
    margin-top: 5px;
}
</style>
</head>
<body>
<div class="container">
    <h2>Update Member</h2>

   <div class="message" style="color: ${requestScope.messageColor}">
                ${requestScope.message}
            </div>
 <%
    Member member = (Member) request.getAttribute("member");
%>

    <c:if test="${not empty member}">
        <form action="${pageContext.request.contextPath}/memberController" method="post">
            <input type="hidden" name="memberId" value="${member.memberId}" />
            <label>Member ID:</label>
            <input type="text" value="${member.memberId}" disabled />

            <label>Name:</label>
            <input type="text" name="name" value="${member.name}" required />
            <c:if test="${not empty nameError}">
                <div class="error-message">${nameError}</div>
            </c:if>

            <label>Email:</label>
            <input type="text" name="email" value="${member.email}" required />
            <c:if test="${not empty emailError}">
                <div class="error-message">${emailError}</div>
            </c:if>

             <label>Gender:</label>
        <select name="gender" required>
            <option value="M" <%= "M".equals(member.getGenderAsString()) ? "selected" : "" %>>Male</option>
            <option value="F" <%= "F".equals(member.getGenderAsString()) ? "selected" : "" %>>Female</option>
        </select><br />
            <c:if test="${not empty genderError}">
                <div class="error-message">${genderError}</div>
            </c:if>

            <label>Mobile No:</label>
            <input type="text" name="mobile" value="${member.mobile}" required />
            <c:if test="${not empty mobileError}">
                <div class="error-message">${mobileError}</div>
            </c:if>

            <label>Address:</label>
            <input type="text" name="address" value="${member.address}" required />
            <c:if test="${not empty addressError}">
                <div class="error-message">${addressError}</div>
            </c:if>

            <input type="hidden" name="action" value="saveUpdate" />
            <input type="submit" value="Update Member" />
        </form>
    </c:if>

    <form action="${pageContext.request.contextPath}/memberController" method="get">
        <input type="hidden" name="action" value="view" />
        <input type="submit" value="Back to Member List" style="margin-top:15px;" />
    </form>
</div>
</body>
</html>