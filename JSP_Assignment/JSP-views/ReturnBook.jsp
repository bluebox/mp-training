<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<html>
<head>
    <title>Return Book</title>
</head>
<body>
    <h2 align="center">Return Issued Book</h2>

    <form method="post" action="returnBook" style="text-align: center;">
        <label for="issueId">Select Issued Book:</label>
        <select name="issueId" id="issueId" required>
            <c:forEach var="record" items="${issuedRecords}">
                <option value="${record.issueId}">
                    Book ID: ${record.bookId} | Member ID: ${record.memberId}
                </option>
            </c:forEach>
        </select>
        <br><br>
        <input type="submit" value="Return Book" />
    </form>

    <div style="color:${statusColor}; text-align:center; margin-top:10px;">
        <strong>${statusMessage}</strong>
    </div>

    <div style="text-align: center; margin-top: 15px;">
        <a href="MainPage.html"><button>Back to Dashboard</button></a>
    </div>
</body>
</html>
