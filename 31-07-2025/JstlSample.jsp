<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Page</title>
</head>
<body bgcolor="beige">
<c:out value="<center><h1>Hello,My World!!!!</h1></center>" escapeXml="false"/>

<c:set var="mymsg" value="Never Give Up Hope and Alwaya Be Ready to face The World"></c:set>
<c:out value="<center><h2>${mymsg}</h2></center>" escapeXml="false"/>

<c:set var="remval" value="1567890"></c:set>
<% out.println("remval value before removing is: "); %>
<c:out value="${remval }"></c:out>
  
<c:remove var="remval"/>

<% out.println("      remval value after removing is: "); %>
<c:out value="${remval }"></c:out>
</body>
</html>