<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Tags</title>
</head>
<body>
<c:set var="message" value="Hello JSP"/>
<c:out value="${message}"/><br/>
<c:out value="${fn:toLowerCase(message)}"/><br/>
<c:out value="${fn:toUpperCase(message)}"/><br/>
<c:remove var="message"/>
<c:out value="${message}"/><br/>
<c:out value="Hi message is empty above"/><br/>
<c:set value="Hi Jsp" var="message"/>
<c:out value="${message}"/><br/>
<c:out value="message is reassigned with a new value"/>

<c:set var="Date" value="<%= new Date() %>"/>
<fmt:formatDate value="${Date}" pattern="dd-MM-yyyy"/>
<c:out value="${Date}" />

<c:import var="mybooks" url="Book.xml"/>
<x:parse xml="${mybooks}" var="output"/>
<p>First Book Name: <x:out select="$output/books/book[1]/book-name"/></p>
<p>First Book Price: <x:out select="$output/books/book[1]/book-price"/></p>
<p>Second Book Name: <x:out select="$output/books/book[2]/book-name"/></p>
<p>Second Book Price: <x:out select="$output/books/book[2]/book-price"/></p>
</body>
</html>