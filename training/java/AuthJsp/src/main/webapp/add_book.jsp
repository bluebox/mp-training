<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

.form-container{

width : 50%;
margin : auto;

}

h1{
text-align : center;
}

form{
margin-top : 50px;
 margin-left : 170px;
}
input{
width : 350px;
}

input[type = "submit"]{
 margin-left : 150px;
 width : auto;
 text-align : center;
 color : white;
 background-color : green;
 padding : 5px;
 border-radius : 5px;
}

</style>

</head>
<body>

	<div class="form-container">
	
	<h1>Add Book Form</h1>
	
	<form action="AddBookServlet" method="post">
		Title: 
		<input type="text" name="title">
		<br> 
		<br>
		Author: 
		<input type="text" name="author">
		<br> 
		<br>
		Category: 
		<input type="text" name="category">
		<br> 
		<br>
		
		Status: 
		<select name="status">
			<option value="A">Active</option>
			<option value="I">Inactive</option>
		</select>
		<br> 
		<br> 
		Availability: 
		<select name="availability">
			<option value="A">Available</option>
			<option value="I">Issued</option>
		</select>
		<br> 
		<br> 
		
		
		<input type="submit" value="Add Book">
		
	</form>
	
	</div>
	

</body>
</html>