<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<title>All Members view</title>
	<link rel="stylesheet" href="viewbooks.css"/>
</head>
<body style="display:flex;flex-direction:column;align-items:center;">
	<h2>All Members Records</h2>
     
	<c:if test="${not empty error && empty membersList}">
		<p style="color:red;">${error}</p>
	</c:if>

	<c:if test="${empty membersList && empty error}">
		<p style="color:red;">No Registered Members found yet.</p>
	</c:if>

	<c:if test="${not empty membersList}">
		<table border="1" cellpadding="5" cellspacing="0">
			<tr>
				<th>Member_ID</th>
				<th>Name</th>
				<th>Mobile</th>
				<th>Email</th>
				<th>Address</th>
				<th>Gender</th>
			</tr>
			<c:forEach var="member" items="${membersList}">
				<tr>
					<td>${member.getMemberid()}</td>
					<td>${member.getName()}</td>
					<td>${member.getMobile()}</td>
					<td>${member.getEmail()}</td>
					<td>${member.getAddress()}</td>
					<td>${member.getGender()}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
</body>
</html>

