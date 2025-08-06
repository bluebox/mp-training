<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dynamic_library_management.domain.Member"%>
<%@ page
	import="com.dynamic_library_management.dao.implementation.MemberDaoImplementation"%>

<%
String success = (String) session.getAttribute("successMessage");
String error = (String) session.getAttribute("errorMessage");
session.removeAttribute("successMessage");
session.removeAttribute("errorMessage");

MemberDaoImplementation dao = new MemberDaoImplementation();
List<Member> members = dao.getAllMembers();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Members</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)),
		url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=1470&q=80');
	background-size: cover;
	background-position: center;
	min-height: 100vh;
}

.container {
	max-width: 1000px;
	margin: 50px auto;
	background: rgba(255, 255, 255, 0.96);
	padding: 30px 40px;
	border-radius: 15px;
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
}

h1 {
	text-align: center;
	font-size: 24px;
	color: #0d47a1;
	border-bottom: 2px solid #90caf9;
	padding-bottom: 10px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 25px;
	font-size: 14px;
}

th, td {
	padding: 10px 8px;
	border-bottom: 1px solid #ccc;
	text-align: left;
}

th {
	background-color: #00796b;
	color: white;
}

tr:hover {
	background-color: #f5f5f5;
}

.nav-links {
	text-align: center;
	margin-top: 30px;
}

.nav-links a {
	text-decoration: none;
	color: #0d47a1;
	font-size: 14px;
	font-weight: bold;
	margin: 0 10px;
	padding: 8px 16px;
	border: 2px solid #0d47a1;
	border-radius: 6px;
	transition: background-color 0.3s ease, color 0.3s ease;
}

.nav-links a:hover {
	background-color: #0d47a1;
	color: white;
}

.popup {
	position: fixed;
	top: 30px;
	left: 50%;
	transform: translateX(-50%);
	padding: 12px 25px;
	font-size: 14px;
	font-weight: bold;
	border-radius: 8px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
	z-index: 9999;
	display: none;
}

.popup.success {
	background-color: #c8e6c9;
	color: #2e7d32;
}

.popup.error {
	background-color: #ffcdd2;
	color: #c62828;
}

.btn-delete {
		background-color: #ef5350;
		color: white;
		border: none;
		padding: 6px 12px;
		font-size: 13px;
		border-radius: 6px;
		cursor: pointer;
		transition: background-color 0.3s ease;
	}
	.btn-delete:hover {
		background-color: #d32f2f;
	}


</style>
</head>
<body>

	<%
	if (success != null || error != null) {
	%>
	<div id="popupMessage"
		class="popup <%=(success != null) ? "success" : "error"%>">
		<%=(success != null) ? success : error%>
	</div>
	<%
	}
	%>

	<div class="container">
		<h1>👥 All Members</h1>

		<%
		if (members != null && !members.isEmpty()) {
		%>
		<table>
			<thead>
				<tr>
					<th>🆔 ID</th>
					<th>👤 Name</th>
					<th>📧 Email</th>
					<th>📱 Mobile</th>
					<th>⚧️ Gender</th>
					<th>🏠 Address</th>
					<th>🗑️ Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Member member : members) {
				%>
				<tr>
					<td><%=member.getMemberId()%></td>
					<td><%=member.getMemberName()%></td>
					<td><%=member.getMemberMail()%></td>
					<td><%=member.getMobileNo()%></td>
					<td><%=member.getGender()%></td>
					<td><%=member.getMemberAddress()%></td>
					<td>
						<form method="post" action="${pageContext.request.contextPath}/deleteMemberController"
							onsubmit="return confirm('Are you sure you want to delete this member?');"
							style="display: inline;">
							<input type="hidden" name="memberId"
								value="<%=member.getMemberId()%>" />
							<button type="submit" class="btn-delete">Delete</button>
						</form>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>

		</table>
		<%
		} else {
		%>
		<div class="popup error"
			style="position: static; display: block; margin-top: 20px; text-align: center;">
			No members found.</div>
		<%
		}
		%>

		<div class="nav-links">
			<a href="${pageContext.request.contextPath}/jsp/index.jsp">🏠
				Home</a> <a
				href="${pageContext.request.contextPath}/jsp/memberManagement.jsp">←
				Back</a>
		</div>
	</div>

	<script>
    const popup = document.getElementById('popupMessage');
    if (popup) {
        popup.style.display = 'block';
        setTimeout(() => {
            popup.style.display = 'none';
        }, 3000);
    }
</script>

</body>
</html>
