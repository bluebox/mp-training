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
	String frstName = request.getParameter("frstName");
	String lastName = request.getParameter("lastName");
	String mail = request.getParameter("mail");
	String password = request.getParameter("pass");
	try {
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root",System.getenv("password"));
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user VALUES (?,?,?,?)");
		pstmt.setString(1, frstName);
		pstmt.setString(2, lastName);
		pstmt.setString(3, mail);
		pstmt.setString(4, password);
		int rows = pstmt.executeUpdate();
		if (rows > 0) {
	%>
	<h2>User created</h2>
	<%
		} else {
	%>
			<h2>Error in creating the user</h2>
	<%
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	%>
</body>
</html>