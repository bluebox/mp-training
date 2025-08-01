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
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root",System.getenv("password"));
		String mail = request.getParameter("mail");
		String password = request.getParameter("pass");
		try {
//			Statement stmt = conn.createStatement();
//			int rows = stmt.executeUpdate("UPDATE user SET password = '" + password + "' WHERE email = '" + mail + "'");
			PreparedStatement pstmt = conn.prepareStatement("UPDATE user SET password = ? WHERE email = ?");
			pstmt.setString(1, password);
			pstmt.setString(2, mail);
			int rows = pstmt.executeUpdate();
		if (rows > 0) {
	%>
	<h2>User updated</h2>
	<%
		} else {
	%>
			<h2>Error in updating the user</h2>
	<%
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	%>
</body>
</html>