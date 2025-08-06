<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Member</title>
<link rel="stylesheet" type="text/css" href="css/updatemember.css">
<link rel="stylesheet" href="css/addBook.css">

</head>
<body>
	<div class="form-container">
		<h1>UPDATE MEMBER</h1>

		<c:if test="${not empty errorMessage}">
			<span class="error-label">${errorMessage}</span>
		</c:if>
		<c:if test="${not empty successMessage}">
			<span class="success-label">${successMessage}</span>
		</c:if>

		<form action="MemberUpdateServlet" method="post">

			<label  >Select Member:</label> 
			
			<select name="memberId" class="combo-box" id="memberId"
				onchange="location.href='MemberUpdateServlet?memberId=' + this.value" required>
				<option value="">Select Member</option>
				<c:forEach var="m" items="${membersList}">
					<option value="${m.memberId}" ${m.memberId == memberIdSet ? 'selected' : ''}>
						${m.name} (${m.email})</option>
				</c:forEach>
				
			</select> 
			 <c:if test= "${not empty memberIdSet}">
			 
			<label  >MemberId:</label>
			<input type="text" value="${memberIdSet}" disabled  /> 
					
				<label>Name:</label> 
				<input type="text"
					name="name"  id="name" value="${name}" required>
	
				<label  >Email:</label> <input type="email"
					name="email"  id="email"    value="${email}" required>
	
				<label  >Mobile:</label> <input type="text"
					name="mobile"  value="${mobile}" id="mobile"   required>
	
				<label >Gender:</label> 
				 <select id="gender" name="gender" required>
	               	<option value="">Select Gender</option>
	               	<c:forEach var="gender" items="${genderList}">
	       				<option value="${gender}" ${gender == genderSelected ? 'selected' : ''}>${gender}</option>
	   				</c:forEach>
           		</select>
					
				</select> <label >Address:</label> <input type="text"
					name="address" value="${address}" id="address"   required>
			</c:if>
			<button type="submit" class="submit-button">UPDATE</button>
		</form>
	</div>

</body>
</html>
