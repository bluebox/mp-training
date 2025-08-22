<%@ page language="java"  import="java.util.Scanner" contentType="charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ExpressionEvaluate</title>
</head>
<body>

<%!
int x;
int y;%>
<% 
Scanner sc=new Scanner(System.in);
x=sc.nextInt();
y=sc.nextInt();
%>
<%=x+y %>
</body>
</html>