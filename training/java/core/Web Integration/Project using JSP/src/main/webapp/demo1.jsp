<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sum of Two Numbers</title>
</head>
<body>S
    <form action="" method="post">
        <div><h1>The sum of two numbers is:</h1></div>

        <label>The first number is:</label>
        <input type="number" name="num1" required /><br><br>

        <label>The second number is:</label>
        <input type="number" name="num2" required /><br><br>

        <input type="submit" value="Submit" />
    </form>
    <c:set var="x" value="${param.num1 }"/>
    <c:out value="${x }"/>
    <c:if test="${not empty param.num1 and not empty param.num2}">
        <c:set var="x" value="${param.num1}" />
        <c:set var="y" value="${param.num2}" />
        <c:set var="sum" value="${x + y}" />

        <h2>Sum of <c:out value="${x}" /> and <c:out value="${y}" /> = <c:out value="${sum}" /></h2>
    </c:if>
    <c:forEach var="i" begin="1" end="5" step="1">
    	<c:out value="${i}"/><br>
    </c:forEach>
    <c:forEach var="i" items="${'Ram','Raj','Raghu','Gopi','Madhav'}">
    	<c:out value="${i}"/><br>
    </c:forEach>
    <h1>Fabinocci Series</h1>
    <c:set var="x" value="0"/>
    <c:set var="y" value="1"/>
    <c:set var="stop" value="false"/>
    <c:forEach var="i" begin="0" end="10">
	    	<c:set var="temp" value="${y}"/>
	    	<c:set var="y" value="${x+y}"/>
	    	<c:set var="x" value="${temp}"/>
	    	<c:out value="${x}"/>
    </c:forEach>
</body>
</html>
