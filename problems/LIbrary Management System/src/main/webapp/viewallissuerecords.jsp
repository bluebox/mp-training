<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>view all issues
</title>
</head>
<body>
<h2>Issue Records</h2>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body style="display:flex;flex-direction:column;align-items:center;">
	<h2>view all issue Records</h2>
     
	<c:if test="${not empty error}">
		<p style="color:red;">${error}</p>
	</c:if>

	<c:if test="${empty List && empty error}">
		<p style="color:red;">No issue found yet.</p>
	</c:if>

	<c:if test="${not empty List}">
		<table border="1" cellpadding="5" cellspacing="0">
			<tr>
				<th><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body style="display:flex;flex-direction:column;align-items:center;">
	<h2>All issue Records</h2>
     
	<c:if test="${not empty error}">
		<p style="color:red;">${error}</p>
	</c:if>

	<c:if test="${empty List && empty error}">
		<p style="color:red;">No issue records found yet.</p>
	</c:if>

	<c:if test="${not empty List}">
		<table border="1" cellpadding="5" cellspacing="0">
			<tr>
				<th>Member_ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Mobile</th>
			    <th>Gender</th>
				<th>Address</th>
				<th>Return Date</th>
				
			</tr>
			<c:forEach var="member" items="${List}">
				<tr>
					<td>${issue.getId()}</td>
					<td>${issue.getName()}</td>
					<td>${issue.getEmail()}</td>
					<td>${issue.getMobile()}</td>
					<td>${issue.getGender()}</td>
					<td>${issue.getAddress()}</td>
					<td>${issue.getReturnDate()}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</th>
				<th>Name</th>
				<th>Email</th>
				<th>Mobile</th>
			    <th>Gender</th>
				<th>Address</th>
				<th>ReturnDate</th>
				
			</tr>
			<c:forEach var="member" items="${membersList}">
				<tr>
					<td>${issue.getId()}</td>
					<td>${issue. getName()}</td>
					<td>${issue. getEmail()}</td>
					<td>${issue.getMobile()}</td>
					<td>${issue.getGender()}</td>
					<td>${issue.getAddress()}</td>
						<td>${issue.getReturnDate()}</td>
					
				</tr>
			</c:forEach>
		</table>
	</c:if>
	








	

</body>
</html>



