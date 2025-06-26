<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
#container {
	width: 50%;
	margin: auto;
}

form {
	margin-left: 150px;
}

h1 {
	text-align: center;
}

input {
	width: 350px;
}

input[type="submit"] {
	margin-left: 150px;
	width: auto;
	text-align: center;
	color: white;
	background-color: green;
	padding: 5px;
	border-radius: 5px;
}
</style>
</head>
<body>
	<div id="container">
		<h1>Return Book Form</h1>
		<form action="ReturnBookServlet" method="post">
			Member ID: <input type="text" name="memberId">
			<br> 
			<br> 
			
			Book ID: <input type="text" name="bookId">
			<br> 
			<br> 

			<input type="submit" value="Return Book">
		</form>
	</div>

</body>
</html>