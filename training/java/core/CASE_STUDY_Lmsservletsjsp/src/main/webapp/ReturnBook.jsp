<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import="java.util.*" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
<title>Return Book</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lms-style.css" />

</head>
<body>
<div class="container">
    <h2>Return a Book</h2>

    <form action="ReturnBookServlet" method="post">
        Mobile: <input type="text" name="mobile" value="${param.mobile}" />
        <input type="submit" name="action" value="Fetch Books" /><br/>

        <c:if test="${not empty memberName}">
            <p>Member: ${memberName}</p>
        </c:if>

        <c:if test="${not empty books}">
            Book:
            <select name="bookName">
                <c:forEach var="book" items="${books}">
                    <option value="${book}">${book}</option>
                </c:forEach>
            </select><br/>
        </c:if>

        Status:<br/>
        <input type="radio" name="status" value="Active" /> Active<br/>
        <input type="radio" name="status" value="Inactive" /> Inactive<br/>

        <input type="submit" name="action" value="Return Book" />
    </form>

    <c:if test="${not empty message}">
        <p style="color: green">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
    </c:if>
    </div>
</body>
</html>