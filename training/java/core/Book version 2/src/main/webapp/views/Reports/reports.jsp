<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.LibraryManagement.controller.ReportsController.*"
	import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Reports</title>
<style>
body {
	font-family: 'Segoe UI', sans-serif;
	margin: 20px;
	background-image:
		url('${pageContext.request.contextPath}/resources/janko-ferlic-sfL_QOnmy00-unsplash.jpg');
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	background-attachment: fixed;
}

h2 {
	color: white;
}

.btn-group {
	margin-bottom: 20px;
}

button {
	padding: 10px 18px;
	margin-right: 10px;
	background-color: #007BFF;
	border: none;
	color: white;
	border-radius: 5px;
	cursor: pointer;
	font-size: 15px;
}

button:hover {
	background-color: #0056b3;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 10px;
	background-color: white;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #007BFF;
	color: white;
}

.hidden {
	display: none;
}
</style>

</head>
<body>
	
		<h2 style="text-align: center">Reports</h2>
		<div>
			<form action="/LibraryManagementSystem_2/views/main.jsp" method="post">
				<button type="submit" class="back-button">Back to Dashboard</button>
			</form>
		</div>
<form action="ReportsController" method="post">
		<h2>Overdue Books</h2>
		<table>
			<tr>
				<th>Title</th>
				<th>Member Name</th>
				<th>Issue Date</th>
			</tr>
			<%
			List<OverdueBook> overdueBooks = (List<OverdueBook>) request.getAttribute("overdueList");
			if (overdueBooks != null) {
				for (OverdueBook row : overdueBooks) {
			%>
			<tr>
				<td><%=row.getTitle()%></td>
				<td><%=row.getMemberName()%></td>
				<td><%=row.getIssueDate()%></td>
			</tr>
			<%
			}
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
			List<CategoryCount> categoryCounts = (List<CategoryCount>) request.getAttribute("categoryCountList");
			if (categoryCounts != null) {
				for (CategoryCount row : categoryCounts) {
			%>
			<tr>
				<td><%=row.getCategory()%></td>
				<td><%=row.getCount()%></td>
			</tr>
			<%
			}
			}
			%>
		</table>

		<h2>Members With Active Issues</h2>
		<table>
			<tr>
				<th>Member Name</th>
				<th>Books Issued</th>
			</tr>
			<%
			List<ActiveMember> activeMembers = (List<ActiveMember>) request.getAttribute("activeMembersList");
			if (activeMembers != null) {
				for (ActiveMember row : activeMembers) {
			%>
			<tr>
				<td><%=row.getName()%></td>
				<td><%=row.getBooksIssued()%></td>
			</tr>
			<%
			}
			}
			%>
		</table>
	</form>
</body>
</html>
