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
String sql="insert into student values(?,?,?)";
PreparedStatement ps=con.prepareStatement(sql);

String name=request.getParameter("name");
String fname=request.getParameter("fname");
String mname=request.getParameter("mname");

ps.setString(1,name);
ps.setString(2,fname);
ps.setString(3,mname);

ps.execute();

out.println("user created");

%>



</body>
</html>