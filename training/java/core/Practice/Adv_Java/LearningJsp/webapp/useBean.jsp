
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.jstl.examples.Products" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
</head>
<body>
<%
String name=session.getAttribute("name").toString();
String price=session.getAttribute("price").toString();
out.println(name);
out.println(price);
%>
<jsp:useBean id="myBean" class="com.jstl.examples.Products">
<%-- <jsp:setProperty property="name" name="myBean"/>
<jsp:setProperty property="price" name="myBean"/> --%>
</jsp:useBean>
<%myBean.setName(name);
myBean.setPrice(Double.parseDouble(price));%>
Given Record:<br>
<jsp:getProperty property="name" name="myBean"/><br>
<jsp:getProperty property="price" name="myBean"/><br>
</body>
</html>