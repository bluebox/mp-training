<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.lms.model.*" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Book</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lms-style.css" />
    
</head>
<body>
<div class="container">
    <h2>Issue a Book</h2>

    <form action="IssueBookServlet" method="post">
         Mobile:
        <input type="text" name="mobile" value="${param.mobile}" />
        <input type="submit" name="action" value="Fetch Member" />
        <br/>
        <c:if test="${not empty member}">
            <p>Member: ${member.name}</p>
        </c:if>
        <c:if test="${not empty categories}">
            Category:
            <select name="category">
                <c:forEach var="cat" items="${categories}">
                    <option value="${cat}" ${cat == param.category ? "selected" : ""}>${cat}</option>
                </c:forEach>
            </select>
            <input type="submit" name="action" value="Search Books" />
        </c:if>

        <br/><br/>

        <c:if test="${not empty books}">
            Book:
            <select name="bookTitle">
                <c:forEach var="book" items="${books}">
                    <option value="${book}">${book}</option>
                </c:forEach>
            </select>
        </c:if>

        <br/><br/>
        Due Date:
        <input type="date" name="dueDate" min="${currentDate}" />
        <br/><br/>
        <input type="submit" name="action" value="Issue Book" />
    </form>

    <c:if test="${not empty message}">
        <p style="color:green">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
    </div>
</body>
</html>
