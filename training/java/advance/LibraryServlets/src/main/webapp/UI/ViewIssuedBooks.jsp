<%@page import="com.library.domain.IssueRecord"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/UI/styles.css">

</head>
<body>
	<h1>Issued Records</h1>
	<table>
		<tr>
			<th>BookId</th>
			<th>MemberId</th>
			<th>Issue Date</th>
			<th>Return Date</th>
		</tr>

		<%
			List<IssueRecord> issueRecords= (List<IssueRecord>)request.getAttribute("isuuedRecordsList");  
			for(IssueRecord record:issueRecords)
			{
		%>
		<tr>
			<td><%=record.getBookId() %></td>
			<td><%=record.getMemberId() %></td>
			<td><%=record.getIssueDate() %></td>
			<td><%=record.getReturnDate() %></td>

		</tr>

		<%
			}
		%>

	</table>

</body>
</html>