<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String mail = request.getParameter("mail");
	try {
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root",System.getenv("password"));
		PreparedStatement pstmt = conn.prepareStatement("DELETE FROM user WHERE email = ?");
		pstmt.setString(1, mail);
		int rows = pstmt.executeUpdate();
		if (rows > 0) {
	%>
	<h2>User deleted</h2>
	<%
		} else {
	%>
			<h2>Error in deleting the user</h2>
	<%
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	%>
</body>
</html>