<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HEY mani</title>
</head>
<body>
	<h1>This is JSP Project</h1>
	<form action="" method="post">
		<input type="text"  placeholder="Num 1" name="num1">
		<input type="text"  placeholder="Num 1" name="num2">
		<input type="submit" type="button" value="Submit">
	</form>
	<%
		String num1=request.getParameter("num1");
	    String num2=(request.getParameter("num2"));
	    if(num1!=null && num2!=null)
	    {
	    	out.println(Integer.parseInt(num1)+Integer.parseInt(num2));
	    }
	    else{
	    	out.println("Invalid number");
	    }
			
			
	%>

</body>
</html>