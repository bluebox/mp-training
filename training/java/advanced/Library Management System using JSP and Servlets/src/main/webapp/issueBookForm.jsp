<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Issue Book</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; background-color: #f4f7f6; }
        .container { max-width: 800px; margin: 0 auto; padding: 30px; background-color: #ffffff; box-shadow: 0 4px 8px rgba(0,0,0,0.1); border-radius: 10px; display: flex; gap: 20px; }
        .input-section { flex: 1; text-align: left; }
        .display-section { flex: 1; text-align: left; }
        h2 { color: #2c3e50; font-size: 24px; margin-bottom: 20px; text-align: center; }
        .form-row { margin-bottom: 15px; display: flex; align-items: center; }
        .form-row label { flex: 1; padding-right: 10px; font-weight: bold; }
        .form-row input { flex: 2; padding: 8px; border: 1px solid #ccc; border-radius: 5px; }
        .button-row { display: flex; justify-content: center; gap: 10px; margin-top: 20px; }
        .button-row button { padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; font-weight: bold; }
        .issue-button { background-color: #3498db; color: white; }
        .issue-button:hover { background-color: #2980b9; }
        .back-button { background-color: #95a5a6; color: white; }
        .back-button:hover { background-color: #7f8c8d; }
        .status-message { margin-top: 15px; font-weight: bold; }
        .success { color: green; }
        .error { color: red; }
        textarea { width: 100%; height: 250px; padding: 10px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 5px; resize: vertical; white-space: pre; }
        .list-header { font-weight: bold; font-size: 16px; text-align: center; margin-bottom: 10px; }
    </style>
</head>
<body>
    <h2>Issue Book</h2>

    <c:if test="${not empty requestScope.statusMessage}">
        <p class="status-message ${requestScope.statusType}"><c:out value="${requestScope.statusMessage}" /></p>
    </c:if>

    <div class="container">
        <div class="input-section">
            <form action="issueBook" method="post">
                <div class="form-row">
                    <label for="bookId">Book ID:</label>
                    <input type="text" id="bookId" name="bookId" placeholder="Enter Book ID" required>
                </div>
                <div class="form-row">
                    <label for="memberId">Member ID:</label>
                    <input type="text" id="memberId" name="memberId" placeholder="Enter Member ID" required>
                </div>
                <div class="button-row">
                    <button type="submit" class="issue-button">Issue Book</button>
                </div>
            </form>
            <div class="button-row">
                 <button onclick="window.location.href='homeScreen.jsp'" class="back-button">Back to Main Menu</button>
            </div>
        </div>
        
        <div class="display-section">
            <div class="list-header">Available Books & Members</div>
            <textarea id="displayArea" readonly>
Available Books:
<c:if test="${not empty booksList}">
    <c:forEach var="book" items="${booksList}">
    - ID: ${book.bookId}
      Title: ${book.title}
      Author: ${book.author}
      Avail: ${book.availability.name()}, Status: ${book.status.name()}
    </c:forEach>
</c:if>
<c:if test="${empty booksList}">
    No books in the library.
</c:if>
--------------------------------------------------
Registered Members:
<c:if test="${not empty membersList}">
    <c:forEach var="member" items="${membersList}">
    - ID: ${member.memberID}, Name: ${member.name}
    </c:forEach>
</c:if>
<c:if test="${empty membersList}">
    No members registered.
</c:if>
            </textarea>
        </div>
    </div>
</body>
</html>