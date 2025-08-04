<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Return Book</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin: 50px; background-color: #f4f7f6; }
        .container { max-width: 800px; margin: 0 auto; padding: 30px; background-color: #ffffff; box-shadow: 0 4px 8px rgba(0,0,0,0.1); border-radius: 10px; display: flex; gap: 20px; }
        .input-section { flex: 1; text-align: left; }
        .display-section { flex: 1; text-align: left; }
        h2 { color: #2c3e50; font-size: 24px; margin-bottom: 20px; text-align: center; }
        .form-row { margin-bottom: 15px; display: flex; align-items: center; }
        .form-row label { flex: 1; padding-right: 10px; font-weight: bold; }
        .form-row input { flex: 2; padding: 8px; border: 1px solid #ccc; border-radius: 5px; }
        .button-row { display: flex; justify-content: center; gap: 10px; margin-top: 20px; }
        .button-row button { padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; font-weight: bold; }
        .return-button { background-color: #3498db; color: white; }
        .return-button:hover { background-color: #2980b9; }
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
    <h2>Return Book</h2>

    <c:if test="${not empty requestScope.statusMessage}">
        <p class="status-message ${requestScope.statusType}"><c:out value="${requestScope.statusMessage}" /></p>
    </c:if>

    <div class="container">
        <div class="input-section">
            <form action="returnBook" method="post">
                <div class="form-row">
                    <label for="bookIdField">Book ID:</label>
                    <input type="text" id="bookIdField" name="bookId" placeholder="Enter Book ID to Return" required>
                </div>
                <div class="button-row">
                    <button type="submit" class="return-button">Return Book</button>
                </div>
            </form>
            <div class="button-row">
                 <button onclick="window.location.href='homeScreen.jsp'" class="back-button">Back to Main Menu</button>
            </div>
        </div>
        
        <div class="display-section">
            <div class="list-header">Currently Issued Books</div>
            <textarea id="displayArea" readonly>
<c:if test="${not empty issuedRecords}">
    <c:forEach var="record" items="${issuedRecords}">
        <c:if test="${record.status.name() == 'ISSUED' && empty record.returnDate}">
- Book ID: ${record.bookId}
  Title: ${booksMap[record.bookId].title}
  Issued To: ${membersMap[record.memberId].name}
  Issue Date: ${issueDatesMap[record.issueId]}
        </c:if>
    </c:forEach>
</c:if>
<c:if test="${empty issuedRecords}">
No books currently issued.
</c:if>
            </textarea>
        </div>
    </div>
</body>
</html>