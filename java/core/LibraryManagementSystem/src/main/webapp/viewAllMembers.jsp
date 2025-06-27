<%@ page import="java.util.*" %>
<%@ page import="Pojo.Member" %>
<%@ page import="Service.LibraryService" %>
<%@ page import="java.io.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>View members</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>All Members</h1>
    <%
    try {
        LibraryService lib = new LibraryService();
        List<Member> memberList = lib.viewAllMembers();
        request.setAttribute("memberList", memberList);
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
    %>
    <c:choose>
        <c:when test="${not empty memberList}">
            <table border="1">
                <tr>
                    <th>Member ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Mobile</th>
                    <th>Gender</th>
                    <th>Address</th>
                </tr>
                <c:forEach var="member" items="${memberList}">
                    <tr>
                        <td>${member.getmemberId()}</td>
                        <td>${member.getName()}</td>
                        <td>${member.getEmail()}</td>
                        <td>${member.getMobile()}</td>
                        <td>${member.getGender()}</td>
                        <td>${member.getAddress()}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>There are no members.<c:out value="${memberList}"/></p>
        </c:otherwise>
    </c:choose>
</body>
</html>