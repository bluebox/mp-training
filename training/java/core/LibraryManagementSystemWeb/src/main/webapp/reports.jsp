<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*, com.library.model.*"%>
<%@ page import="javax.servlet.*, javax.servlet.http.*"%>
<%@ page import="com.library.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Reports</title>
<style>
table {
	width: 90%;
	border-collapse: collapse;
	margin: 20px auto;
}

th, td {
	border: 1px solid #000;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}

h2 {
	text-align: center;
	margin-top: 40px;
}
</style>
</head>
<body>

	<h1 align="center">Library Reports</h1>
	<div style="text-align: center;">
		<a href="index.jsp"><button>← Back to Dashboard</button></a>
	</div>

	<h2>Overdue Books</h2>
	<table>
		<tr>
			<th>Book ID</th>
			<th>Title</th>
			<th>Member ID</th>
			<th>Issue Date</th>
		</tr>
		<%
		List<IssueRecordRow> overdueBooks = (List<IssueRecordRow>) request.getAttribute("overdueBooks");
		for (IssueRecordRow row : overdueBooks) {
		%>
		<tr>
			<td><%=row.getBookId()%></td>
			<td><%=row.getTitle()%></td>
			<td><%=row.getMemberId()%></td>
			<td><%=row.getIssueDate()%></td>
		</tr>
		<%
		}
		%>
	</table>

	<h2>Books Per Category</h2>
	<table>
		<tr>
			<th>Category</th>
			<th>Count</th>
		</tr>
		<%
		List<CategoryCountRow> categoryCounts = (List<CategoryCountRow>) request.getAttribute("categoryCounts");
		for (CategoryCountRow row : categoryCounts) {
		%>
		<tr>
			<td><%=row.getCategory()%></td>
			<td><%=row.getCount()%></td>
		</tr>
		<%
		}
		%>
	</table>

	<h2>Members With Active Issues</h2>
	<table>
		<tr>
			<th>Member ID</th>
			<th>Name</th>
			<th>Email</th>
		</tr>
		<%
		List<MemberRow> activeMembers = (List<MemberRow>) request.getAttribute("activeMembers");
		for (MemberRow row : activeMembers) {
		%>
		<tr>
			<td><%=row.getMemberId()%></td>
			<td><%=row.getName()%></td>
			<td><%=row.getEmail()%></td>
		</tr>
		<%
		}
		%>
	</table>

</body>
</html>