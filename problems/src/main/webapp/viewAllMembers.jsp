<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View All Members</title>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>All Members view</title>
</head>
<body style="display:flex;flex-direction:column;align-items:center;">
	<h2>All Members Records</h2>
     
	<c:if test="${not empty error}">
		<p style="color:red;">${error}</p>
	</c:if>

	<c:if test="${not empty membersList && empty error}">
		<p style="color:red;">No Registered Members found yet.</p>
	</c:if>

	<c:if test="${not empty membersList}">
		<table border="1" cellpadding="5" cellspacing="0">
			<tr>
				<th>Member_ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Mobile</th>
			    <th>Gender</th>
				<th>Address</th>
			</tr>
			<c:forEach var="member" items="${membersList}">
				<tr>
					<td>${member.MemberId}</td>
					<td>${member.Name}</td>
					<td>${member.Email}</td>
					<td>${member.Mobile}</td>
					<td>${member.gender}</td>
					<td>${member.Address}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	
</body>
</html>

</body>
</html>