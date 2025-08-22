<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Issue Book</title>
</head>
<body>
    <h2 align="center">Issue a Book</h2>

    <form method="post" action="${pageContext.request.contextPath}/issueBook" align="center">
        <label for="memberId">Select Member:</label>
        <select name="memberId" required>
    <c:forEach var="member" items="${members}">
        <option value="${member.memberId}">
            ${member.name} (ID: ${member.memberId})
        </option>
    </c:forEach>
</select>

        <br><br>

        <label for="bookId">Select Book:</label>
        <select name="bookId" id="bookId" required>
            <c:forEach var="book" items="${books}">
                <option value="${book.bookId}">
                    ${book.title} (ID: ${book.bookId})
                </option>
            </c:forEach>
        </select>
        <br><br>

        <input type="submit" value="Issue Book" />
    </form>

    <div style="color:${statusColor}; text-align:center;">
        ${statusMessage}
    </div>

    <div style="text-align:center;">
        <a href="MainPage.html"><button>Back to Dashboard</button></a>
    </div>
</body>
</html>
