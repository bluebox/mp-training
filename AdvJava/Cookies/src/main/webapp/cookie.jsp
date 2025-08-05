
<%
Cookie fname= new Cookie("Fnames",request.getParameter("Fname"));
Cookie lname= new Cookie("Lnames",request.getParameter("Lname"));

fname.setMaxAge(60 * 60);
lname.setMaxAge(60 * 60);

response.addCookie(fname);
response.addCookie(lname);

%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookies</title>
</head>
<body>

First Name: <%=request.getParameter("Fnames")%><br> <br>
Last Name:  <%=request.getParameter("Lnames")%><br> <br>

<%
Cookie cookie =null;
Cookie[] cookies= null;

cookies = request.getCookies();
for(int i=0;i<cookies.length;i++){
	out.println(cookies[i].getName()+ ", ");
	out.println(cookies[i].getValue()+"<br><br>");
}

%>
</body>
</html>

