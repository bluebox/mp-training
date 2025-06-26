<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.casestudy.dao.IssueRecordDao"%>
<%@ page import="com.casestudy.domain.IssueRecord"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table{
margin : auto;
border-collapse : collapse;
}

table td , th{
padding : 5px;
text-align : center;
}
</style>
</head>
<body>
	<%
	IssueRecordDao dao = new IssueRecordDao();
	List<IssueRecord> records = dao.getAllIssuedRecords();
	%>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Book ID</th>
			<th>Member ID</th>
			<th>Status</th>
			<th>Issue Date</th>
			<th>Return Date</th>
		</tr>
		<%
		for (IssueRecord r : records) {
		%>
		<tr>
			<td><%=r.getIssueId()%></td>
			<td><%=r.getBookId()%></td>
			<td><%=r.getMemberId()%></td>
			<td><%=r.getStatus().name()%></td>
			<td><%=r.getIssueDate()%></td>
			<td><%=r.getReturnDate() != null ? r.getReturnDate() : "-"%></td>
		</tr>
		<%
		}
		%>
	</table>

</body>
</html>