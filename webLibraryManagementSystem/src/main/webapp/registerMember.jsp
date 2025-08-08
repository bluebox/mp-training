<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Registration</title>
<link rel="stylesheet" type="text/css"
	href="css/addBook.css">

</head>
<body>

	<div class="form-container">

		
		<h1>Member Registration</h1>

		<c:if test="${not empty errorMessage}">
			<span class="error-label">${errorMessage}</span>
		</c:if>
		<c:if test="${not empty successMessage}">
			<span class="success-label">${successMessage}</span>
		</c:if>

		<form action="RegisterMemberServlet" method="post">
		 
				<label for="name">Name:</label> <input type="text" name="name"
					id="name" required  value="${name}"/>
			 
				<label for="email">Email:</label> 
				<input type="email" name="email" id="email" required value="${email}" />
			 
				<label for="mobile">Mobile:</label> <input type="text" name="mobile"
					id="mobile" required value="${mobile}" />

				<label for="gender">Gender:</label> 
				<select id="gender" name="gender" required>
                	<option value="">Select Gender</option>
                	<c:forEach var="gender" items="${genderList}">
        				<option value="${gender}" ${gender == genderSelected ? 'selected' : ''}>${gender}</option>
    				</c:forEach>
           		</select>

				<label for="address">Address:</label> <input type="text"
					name="address" id="address" required value="${address}"/>

				<button type="submit" class="submit-button">Submit</button>
		</form>

	</div>

</body>
</html>
