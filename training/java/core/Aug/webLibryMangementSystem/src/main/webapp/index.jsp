<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library</title>
<link rel="stylesheet" href="css/dashboard.css">
</head>
<body>

	<div class="sidebar">
		<h2>📚 Library </h2>

		<div class="menu-item">
			<div class="menu-title" onclick="toggleSubMenu('bookMenu', this)">
				<span class="arrow">▶</span> 📖 Book
			</div>
			<div class="submenu" id="bookMenu">
				<a href="addBook" target="contentFrame">➕ Add Book</a> 
				<a href="updateBook" target="contentFrame">✏️ Update Book</a> 
				<a href="updateAvailability" target="contentFrame">📦 Update
					Availability</a> 
				<a href="viewBooks" target="contentFrame">📘 View All Books</a>
			</div>
		</div>

		<div class="menu-item">
			<div class="menu-title" onclick="toggleSubMenu('memberMenu', this)">
				<span class="arrow">▶</span> 👤 Member
			</div>
			<div class="submenu" id="memberMenu">
				<a href="RegisterMemberServlet" target="contentFrame">📝 Register
					Member</a> <a href="MemberUpdateServlet" target="contentFrame">✏️
					Update Member</a> <a href="MembersViewAllServlet" target="contentFrame">👥
					View All Members</a>
			</div>
		</div>

		<div class="menu-item">
			<div class="menu-title"
				onclick="toggleSubMenu('issueReturnMenu', this)">
				<span class="arrow">▶</span> 🔁Issue and Return
			</div>
			<div class="submenu" id="issueReturnMenu">
				<a href="IssueBookServlet" target="contentFrame">📤 Issue Book</a> <a
					href="ReturnBookServlet" target="contentFrame">📥 Return Book</a>
			</div>
		</div>

		<div class="menu-item">
			<div class="menu-title" onclick="toggleSubMenu('reportsMenu', this)">
				<span class="arrow">▶</span> 📊 Reports
			</div>
			<div class="submenu" id="reportsMenu">
				<a href="booksByCategory" target="contentFrame">📚 Books by
					Category</a> <a href="activeMembers" target="contentFrame">✅
					Active Members</a> <a href="overdueBooks" target="contentFrame">⏰
					Overdue Books</a>
			</div>
		</div>
	</div>

	<div class="content">
		<iframe name="contentFrame" src="defaultPage.jsp"></iframe>
	</div>

	<script src="scripts/dashboard.js">
		
	</script>

</body>
</html>
