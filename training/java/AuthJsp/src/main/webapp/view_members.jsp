<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.casestudy.dao.MembersDao"%>
<%@ page import="com.casestudy.domain.Member"%>
<%@ page import="com.casestudy.domain.Gender"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	margin: auto;
	border-collapse: collapse;
}

table td, th {
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body>
	<%
	MembersDao dao = new MembersDao();
	List<Member> members = dao.getAllMembers();
	%>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Mobile</th>
			<th>Gender</th>
			<th>Address</th>
		</tr>
		<%
		for (Member m : members) {
		%>
		<tr>
			<td><%=m.getMemberId()%></td>
			<td><%=m.getName()%></td>
			<td><%=m.getEmail()%></td>
			<td><%=m.getMobile()%></td>
			<td><%=m.getGender().name()%></td>
			<td><%=m.getAddress()%></td>
		</tr>
		<%
		}
		%>
	</table>


</body>
</html>