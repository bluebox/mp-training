<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="insert.jsp">
Enter Your Name:<input type="text" name="name">
Fathers name:<input type="text" name="fname">
Mothers name:<input type="text" name="mname">
<input type="submit">
</form>

<form action="dispay.jsp">
<input type="submit" value="getAllUsers">
</form>

<form action="delete.jsp">
<input type="text" name="name">
<input type="submit" name="deleteUser">

</form>

<%

/* Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp" ,"root", "Tarun@1728");
String query="select * from student";
PreparedStatement ps=con.prepareStatement(query);
ResultSet rs=ps.executeQuery();
while(rs.next())
{
	out.println(rs.getString(1)+" "+rs.getString(2)+ " "+rs.getInt(3)+"<br/>");
	
} */

%>



</body>
</html>