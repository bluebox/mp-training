<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List, com.LibraryManagement.models.IssueRecords"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View All Issues</title>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        margin: 0;
        height: 100vh;
        background: #f4f6f8;
        display: flex;
        justify-content: center;
        align-items: center;
         background-image:
		url('${pageContext.request.contextPath}/resources/janko-ferlic-sfL_QOnmy00-unsplash.jpg');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
    .container {
        background-color: white;
        padding: 30px 40px;
        border-radius: 12px;
        width: 800px;
        max-width: 95vw;
        overflow-x: auto;
    }
    h2 {
        margin-bottom: 25px;
        color: #333;
        text-align: center;
        font-weight: 700;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 25px;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 12px 15px;
        text-align: center;
        font-size: 14px;
        color: #444;
        white-space: nowrap;
    }
    th {
        background-color: #007BFF;
        color: white;
        font-weight: 600;
    }
    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    tr:hover {
        background-color: #e6f0ff;
    }
    .btn {
        display: block;
        padding: 12px 25px;
        font-size: 16px;
        color: #fff;
        background-color: #007BFF;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        text-align: center;
        transition: background-color 0.3s ease;
        max-width: 150px;
        margin: 0 auto;
    }
    .btn:hover {
        background-color: #0056b3;
    }
    .message {
        text-align: center;
        font-size: 14px;
        margin-bottom: 15px;
    }
    .error {
        color: red;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>All Issued Books</h2>

        <c:if test="${not empty errorMessage}">
            <div class="message error"><c:out value="${errorMessage}" /></div>
        </c:if>
		
		
		
		<%
		List<IssueRecords> issues = (List<IssueRecords>) request.getAttribute("issueList");
		if (issues == null || issues.isEmpty()) {
		%>
		<p>No books found.</p>
		<%
		} else {
		%>
		<table>
			<thead>
				<tr>
					<th>Issue ID</th>
                    <th>Book ID</th>
                    <th>Member ID</th>
                    <th>Availability</th>
                    <th>Issue Date</th>
                    <th>Return Date</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (IssueRecords issue : issues) {
				%>
				<tr>
					<td><%=issue.getIssueId()%></td>
					<td><%=issue.getBookId()%></td>
					<td><%=issue.getMemberId()%></td>
					<%if(issue.getStatus()=='I'){%>
					<td>Issued</td>
					<%}else{%>
						<td>Returned</td><% }%>
					<td><%=issue.getIssueDate()%></td>
					<%if(issue.getReturnDate()==null){%>
					<td> </td>
					<%}else{%>
					<td><%=issue.getReturnDate()%></td><% }%>
					
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%
		}
		%>
		
        <button type="button" class="btn" onclick="window.location.href='${pageContext.request.contextPath}/views/IssueReturn/issueReturn.jsp'">Back</button>
    </div>
</body>
</html>
