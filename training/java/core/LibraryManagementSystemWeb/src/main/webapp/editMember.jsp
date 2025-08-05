<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.library.model.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
Member member = (Member) request.getAttribute("member");
%>

<html>
<head>
<title>Edit Member</title>
<style>
.btn {
	margin: 5px;
	padding: 5px 10px;
}

.message {
	padding: 10px;
	width: 400px;
}

.success {
	background-color: #d4edda;
	color: #155724;
	border: 1px solid #c3e6cb;
}

.error {
	background-color: #f8d7da;
	color: #721c24;
	border: 1px solid #f5c6cb;
}
</style>
</head>
<body>

	<a href="viewMembers" class="btn">← Back to Members</a>
	<a href="index.jsp" class="btn">← Back to Dashboard</a>

	<h2>Edit Member</h2>

	<c:if test="${not empty successMsg}">
		<div class="message success">${successMsg}</div>
	</c:if>

	<c:if test="${not empty errorMsg}">
		<div class="message error">${errorMsg}</div>
	</c:if>

	<%
	if (member != null) {
	%>
	<form method="post" action="editMember">
		<input type="hidden" name="id" value="<%=member.getMemberId()%>" />
		
		 <label>Name:</label><br />
		<input type="text" name="name" value="<%=member.getName()%>" required /><br /><br />
		
		 <label>Email:</label><br /> <input type="email" name="email"
			value="<%=member.getEmail()%>" required /><br /> <br />
			
			 <label>Mobile:</label><br />
		<input type="text" name="mobile" value="<%=member.getMobile()%>" required /><br /> <br /> 
			
			<label>Gender:</label><br />
			 <select
			name="gender" required>
			<option value="M"
				<%="M".equals(member.getGender()) ? "selected" : ""%>>Male</option>
			<option value="F"
				<%="F".equals(member.getGender()) ? "selected" : ""%>>Female</option>
		</select><br /> <br /> <label>Address:</label><br />
		
		
		
		<textarea name="address" rows="4" cols="40" required><%=member.getAddress()%></textarea>
		
		<br /> <br /> <input type="submit" value="Update Member" />
	</form>
	<%
	} else {
	%>
	<p style="color: red;">No member data found. Please go back and try
		again.</p>
	<%
	}
	%>

</body>
</html>
