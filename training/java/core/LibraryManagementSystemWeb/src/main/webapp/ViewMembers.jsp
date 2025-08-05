<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>View Members</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
	margin-top: 20px;
}

th, td {
	padding: 8px 12px;
	border: 1px solid #ccc;
	text-align: center;
}

.btn {
	padding: 5px 10px;
	font-size: 14px;
}
</style>
</head>
<body>

	<a href="index.jsp" class="btn"><button>Back to Dashboard</button></a>
	<h2>All Members</h2>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Mobile</th>
				<th>Gender</th>
				<th>Address</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${memberList}">
				<tr>
					<td>${member.memberId}</td>
					<td>${member.name}</td>
					<td>${member.email}</td>
					<td>${member.mobile}</td>
					<td><c:choose>
							<c:when test="${member.gender eq 'M'}">Male</c:when>
							<c:when test="${member.gender eq 'F'}">Female</c:when>
							<c:otherwise>Other</c:otherwise>
						</c:choose></td>
					<td>${member.address}</td>
					<td>
						<form action="editMember" method="get">
							<input type="hidden" name="id" value="${member.memberId}" />
							<button class="btn" type="submit">Edit</button>
						</form>
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
