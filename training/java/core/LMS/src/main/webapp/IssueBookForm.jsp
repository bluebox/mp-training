<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Issue Book</title>
    <style>
        .error {
            color: red;
        }
        .form-container {
            width: 50%;
            margin: 0 auto;
            padding-top: 20px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            font-size: 18px;
        }
        select, input[type="date"] {
            width: 100%;
            padding: 8px;
            font-size: 16px;
        }
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Issue Book</h2>
        
        <!-- Display status message if any -->
        <c:if test="${not empty statusMessage}">
            <p style="color:red;">${statusMessage}</p>
        </c:if>

        <form action="IssueBookController" method="POST">
            <div class="form-group">
                <label for="bookId">Select Book:</label>
                <select id="bookId" name="bookId">
                    <option value="" disabled selected>Select Book ID</option>
                    <c:forEach var="book" items="${bookList}">
                        <option value="${book.bookId}">${book.bookId} - ${book.title}</option>
                    </c:forEach>
                </select>
                <div class="error">${bookIdError}</div>
            </div>

            <div class="form-group">
                <label for="memberId">Select Member:</label>
                <select id="memberId" name="memberId">
                    <option value="" disabled selected>Select Member ID</option>
                    <c:forEach var="member" items="${memberList}">
                        <option value="${member.memberId}">${member.memberId} - ${member.name}</option>
                    </c:forEach>
                </select>
                <div class="error">${memberIdError}</div>
            </div>

            <div class="form-group">
                <label for="issueDate">Issue Date:</label>
                <input type="date" id="issueDate" name="issueDate">
                <div class="error">${issueDateError}</div>
            </div>

            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
