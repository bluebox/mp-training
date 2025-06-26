<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style>
h1 ,h3{
	text-align: center;
}
ol{
padding-inline-start : 0px;
}

#container{
 display:flex;
 justify-content : center;
 gap : 50px;
}

li {
	list-style-type : none;
	margin-bottom: 10px;
	padding: 5px;
	margin : 15px auto;
	border-radius: 5px;
	background-color: green;
}

a {
	text-decoration: none;
	color: white;
}
</style>
</head>
<body>

	<h1>Library Management System</h1>

	<nav>

		<div id="container">

			<div id="book">

				<h3>Books</h3>

				<ol>
					<li><a href="add_book.jsp">Add Book</a></li>
					<li><a href="update_book.jsp">Update Book</a></li>
					<li><a href="view_books.jsp">View All Books</a></li>
				</ol>

			</div>

			<div id="member">

				<h3>Member</h3>

				<ol>
					<li><a href="add_member.jsp">Add Member</a></li>
					<li><a href="update_member.jsp">Update Member</a></li>
					<li><a href="view_members.jsp">View all members</a></li>		
				</ol>

			</div>



			<div id="records">

				<h3>Records</h3>

				<ol>
					<li><a href="issue_book.jsp">Issue Book</a></li>
					<li><a href="return_book.jsp">Return Book</a></li>
					<li><a href="view_issued_records.jsp">View Issue Records</a></li>
				</ol>

			</div>

			<div id="reports">
			
				<h3>Reports</h3>

				<ol>
					<li><a href="active_issued_members.jsp">Active issued
							members</a></li>
					<li><a href="books_count_per_category.jsp">Books count per Category</a></li>
					<li><a href="overdue_books.jsp">Overdue Books</a></li>
				</ol>

			</div>


		</div>








	</nav>

</body>
</html>