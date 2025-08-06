<%@page import="org.apache.jasper.compiler.Node.UseBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Beans Example</title>
</head>
<body>
<jsp:useBean id="student" class="com.vejas.examples.jsp.Student" scope="session"/>
<jsp:setProperty property="name" name="student" value="vejas"/>
<jsp:setProperty property="age" name="student" value="22"/>
<jsp:setProperty property="gender" name="student" value="male"/>

<p>Name : <%=student.getName() %> </p>
<p>Age : <%=student.getAge()%> </p>
<p>Gender : <%=student.getGender()%> </p>

</body>
</html>