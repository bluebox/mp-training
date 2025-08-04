<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Library Members</title>
<style>
    body {
        font-family: Arial, sans-serif;
    }
    table {
        width: 100%;
        border-collapse: collapse; 
        margin-top: 20px;
        border: 1px solid #ccc; 
    }
    th, td {
        border: 1px solid #ccc; 
        padding: 8px;
        text-align: left;
    }
    .action-buttons button {
        border: none;
        padding: 5px 10px;
        border-radius: 3px;
        color: white;
        margin-right: 5px;
        cursor: pointer;
        font-size: 14px;
    }
    .action-buttons button.update {
        background-color: #4CAF50; /* Green */
    }
    .action-buttons button.delete {
        background-color: #f44336; /* Red */
    }
    .action-buttons button:hover {
        opacity: 0.8;
    }
    .success-message {
        color: green;
        font-weight: bold;
        background-color: #d4edda;
        border: 1px solid #c3e6cb;
        padding: 10px;
        margin-bottom: 20px;
        border-radius: 5px;
    }
    .error-message {
        color: red;
        font-weight: bold;
        background-color: #f8d7da;
        border: 1px solid #f5c6cb;
        padding: 10px;
        margin-bottom: 20px;
        border-radius: 5px;
    }
</style>
</head>
<body>

    <h2>Library Members</h2>
    
    <c:if test="${not empty requestScope.successMessage}">
        <p class="success-message"><c:out value="${requestScope.successMessage}" /></p>
    </c:if>
    <c:if test="${not empty requestScope.errorMessage}">
        <p class="error-message"><c:out value="${requestScope.errorMessage}" /></p>
    </c:if>

    <c:if test="${empty membersList}">
        <p>No members found.</p>
    </c:if>

    <c:if test="${not empty membersList}">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Gender</th>
                    <th>Address</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="member" items="${membersList}">
                    <tr>
                        <td><c:out value="${member.memberID}" /></td>
                        <td><c:out value="${member.name}" /></td>
                        <td><c:out value="${member.email}" /></td>
                        <td><c:out value="${member.phoneNumber}" /></td>
                        <td><c:out value="${member.gender.name()}" /></td>
                        <td><c:out value="${member.address}" /></td>
                        <td class="action-buttons">
                            <form action="updateMember" method="get" style="display:inline;">
                                <input type="hidden" name="memberID" value="${member.memberID}">
                                <button type="submit" class="update">Update</button>
                            </form>
                            <form action="deleteMember" method="get" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this member?');">
                                <input type="hidden" name="memberID" value="${member.memberID}">
                                <button type="submit" class="delete">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="back-button-container">
        <form action="homeScreen.jsp" method="get">
            <button type="submit">Go Back</button>
        </form>
    </div>

</body>
</html>