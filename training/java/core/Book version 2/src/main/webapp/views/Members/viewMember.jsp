<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.LibraryManagement.models.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Members</title>
<style>
body {
    font-family: sans-serif;
    margin: 0;
    padding: 20px;
    background-image: url('${pageContext.request.contextPath}/resources/janko-ferlic-sfL_QOnmy00-unsplash.jpg');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    background-attachment: fixed;
}

.container {
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    max-width: 1000px;
    margin: auto;
}

h2 {
    text-align: center;
    font-size: 24px;
    margin-bottom: 15px;
    color: #333;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 15px;
}

th, td {
    border: 1px solid #ccc;
    padding: 8px;
    text-align: left;
    font-size: 14px;
}

th {
    background-color: #f0f0f0;
    color: #333;
}

.btn {
    padding: 6px 10px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 13px;
    cursor: pointer;
    margin-right: 4px;
}

.btn:hover {
    background-color: #0056b3;
}

.back-form {
    margin-top: 20px;
    text-align: left;
}

.back-button {
    background-color: #007bff;
    color: white;
    padding: 10px 20px;
    font-size: 14px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.back-button:hover {
    background-color: #0056b3;
}

p {
    text-align: center;
    font-size: 16px;
    color: #555;
}
</style>
</head>
<body>
    <div class="container">

        <form action="${pageContext.request.contextPath}/views/Members/members.jsp" method="get" class="back-form">
            <button type="submit" class="back-button">Back to Dashboard</button>
        </form>

        <h2>All Members</h2>

        <c:choose>
            <c:when test="${empty memberList}">
                <p>No members found.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Member ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Mobile</th>
                            <th>Gender</th>
                            <th>Address</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="member" items="${memberList}">
                            <tr>
                                <td>${member.memberId}</td>
                                <td>${member.name}</td>
                                <td>${member.email}</td>
                                <td>${member.mobile}</td>
                                <td>${member.gender}</td>
                                <td>${member.address}</td>
                                <td>
                                    <form action="memberController" method="post" style="display:inline;">
                                        <input type="hidden" name="action" value="update" />
                                        <input type="hidden" name="memberId" value="${member.memberId}" />
                                        <input type="submit" value="Update Member" class="btn" />
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

    </div>
</body>
</html>
