<%@taglib prefix="mytag" uri="/WEB-INF/custom.tld"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 
 

<jsp:useBean id="details"  class="com.JspIntro.model.UserDetails" scope="session"/>
 
 <jsp:setProperty name = "details" property="name" value="satheesh" />
 <jsp:setProperty name = "details" property="email" value="sathe@gmail.com" />
 <jsp:setProperty name = "details" property="phonenumber" value="17894561230" />
 
 <mytag:customTag UserDetails="${details}">
 </mytag:customTag>
</body>
</html>