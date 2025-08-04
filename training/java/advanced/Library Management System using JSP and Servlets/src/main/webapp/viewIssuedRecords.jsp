<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Issue Records</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h2 { text-align: center; color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .back-button-container { text-align: center; margin-top: 20px; }
        .back-button { padding: 10px 20px; background-color: #555; color: white; border: none; border-radius: 5px; cursor: pointer; }
    </style>
</head>
<body>
    <h2>All Issue Records</h2>

    <c:if test="${not empty issuedRecords}">
        <table>
            <thead>
                <tr>
                    <th>Issue ID</th>
                    <th>Book ID</th>
                    <th>Book Title</th>
                    <th>Member ID</th>
                    <th>Member Name</th>
                    <th>Status</th>
                    <th>Issue Date</th>
                    <th>Issued By</th>
                    <th>Return Date</th>
                    <th>Returned By</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="record" items="${issuedRecords}">
                    <tr>
                        <td><c:out value="${record.issueId}" /></td>
                        <td><c:out value="${record.bookId}" /></td>
                        <td>
                            <c:forEach var="book" items="${allBooks}">
                                <c:if test="${book.bookId == record.bookId}">
                                    <c:out value="${book.title}" />
                                </c:if>
                            </c:forEach>
                        </td>
                        <td><c:out value="${record.memberId}" /></td>
                        <td>
                            <c:forEach var="member" items="${allMembers}">
                                <c:if test="${member.memberID == record.memberId}">
                                    <c:out value="${member.name}" />
                                </c:if>
                            </c:forEach>
                        </td>
                        <td><c:out value="${record.status.name()}" /></td>
                        <td>
                            <c:out value="${record.issueDate}" />
                        </td>
                        <td><c:out value="${record.issuedBy}" /></td>
                        <td>
                            <c:out value="${record.returnDate}" />
                        </td>
                        <td><c:out value="${record.returnedBy}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty issuedRecords}">
        <p style="text-align:center;">No Issued records found in the library.</p>
    </c:if>
    
    <div class="back-button-container">
        <form action="viewIssuedRecords" method="get" style="display:inline-block;">
            <button type="submit" class="back-button">Refresh Records</button>
        </form>
        <form action="homeScreen.jsp" method="get" style="display:inline-block;">
            <button type="submit" class="back-button">Back to Main Menu</button>
        </form>
    </div>

</body>
</html>