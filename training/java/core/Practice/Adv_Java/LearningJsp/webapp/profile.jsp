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
		String query="SELECT * FROM products";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata","root","Ashok@99122");
		PreparedStatement stmt=conn.prepareStatement(query);
		ResultSet rs=stmt.executeQuery();
		rs.next();
	%>
	ProductId:<%=rs.getInt(1) %><br>
	ProductName: <%=rs.getString(2) %><br>
	Price: <%=rs.getDouble(3) %>
	
</body>
</html>