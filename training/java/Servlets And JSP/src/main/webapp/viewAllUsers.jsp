<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, th, td {
  border: 1px solid black;
}
</style>
</head>
<body>
	<%
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", System.getenv("password"));
	%>
	<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Password</th>
		</tr>
		<%
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user");
		while (rs.next()) {
		%>
		<tr>
			<td>
				<%
				out.print(rs.getString(1));
				%>
			</td>
			<td>
				<%
				out.print(rs.getString(2));
				%>
			</td>
			<td>
				<%
				out.print(rs.getString(3));
				%>
			</td>
			<td>
				<%
				out.print(rs.getString(4));
				%>
			</td>
		</tr>
		<%
		}
		%>

	</table>
	<%
	} catch (SQLException | ClassNotFoundException e) {
	e.printStackTrace();
	}
	%>
</body>
</html>