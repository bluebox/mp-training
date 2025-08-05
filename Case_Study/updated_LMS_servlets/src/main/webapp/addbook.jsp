<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addbook</title>
<style>
	body {
  margin: 0;
  height: 100vh; 
  display: flex;
  justify-content: center; 
  align-items: center;     
  background-color: #f5f5f5; 
}
form{
	display:flex;
	flex-direction:column;
	align-items:center;
	  justify-content: center; 
	gap:20px;
	background-color:lightblue;
	padding:20px;
	margin:20px;
	border:solid 2px;
	border-radius:8px;

}


</style>
</head>
<body>

	<form action="addbook" method="post">
		<div><h2>Add Book</h2></div>
	<div><label for="title">Book title:</label>
	<input type="text" name="title"/></div>
	<div><label for="author">Author:</label>
	<input type="text" name="author"/></div>
	<div><label for="category">Category:</label>
	<input type="text" name="category"/></div>
	<div>
	<label for="status">Status:</label><br>
	<input type="radio" name="status" value="Active">Active 
	<input type="radio" name="status" value="InActive">Inactive
	</div>
	<div>
	<label for="availability">Availability:</label><br>
	<input type="radio" name="availability" value="Available">Available
	<input type="radio" name="availability" value="Issued">Issued
	</div>
	<input type="submit" value="addbook"/>
	</form>


</body>
</html>