<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp" ,"root", "Tarun@1728");
String sql="delete from student where name=?";
PreparedStatement ps=con.prepareStatement(sql);
String name=request.getParameter("name");
ps.setString(1,"name");
int rs=ps.executeUpdate();
if(rs>1)
{
	out.println("user deleter succcessfully");
}
else
	out.println("user not deleted");
%>

</body>
</html>