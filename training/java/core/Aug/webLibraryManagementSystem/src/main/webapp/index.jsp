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
				<a href="books/addBook" target="contentFrame">➕ Add Book</a> 
				<a href="books/updateBook" target="contentFrame">✏️ Update Book</a>  
				<a href="books/viewBooks" target="contentFrame">📘 View All Books</a>
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
				<a href="issue/issueBook" target="contentFrame">📤 Issue Book</a> <a
					href="issue/returnBook" target="contentFrame">📥 Return Book</a>
			</div>
		</div>

		<div class="menu-item">
			<div class="menu-title" onclick="toggleSubMenu('reportsMenu', this)">
				<span class="arrow">▶</span> 📊 Reports
			</div>
			<div class="submenu" id="reportsMenu">
				<a href="reports/booksByCategory" target="contentFrame">📚 Books by
					Category</a> <a href="reports/activeMembers" target="contentFrame">✅
					Active Issue Books</a> <a href="reports/overdueBooks" target="contentFrame">⏰
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
