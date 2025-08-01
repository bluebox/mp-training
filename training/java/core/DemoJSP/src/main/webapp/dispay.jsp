<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp" ,"root", "Tarun@1728");
String sql="select * from student";
PreparedStatement ps=con.prepareStatement(sql);
ResultSet rs=ps.executeQuery();
while(rs.next())
{
	String name=rs.getString("name");
	String fname=rs.getString("fname");
	String mname=rs.getString("mname");
	out.println("<h1>"+name+" "+fname+" "+mname+"</h1>");
	
}
%>



</body>
</html>