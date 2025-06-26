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
margin-top : 30px;
margin-left : 425px;
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
	
	
	<div>
	<h1> Add Member Form</h1>
	
	<form action="AddMemberServlet" method="post">
		Name: <input type="text" name="name">
		<br> 
		<br> 
		Email: <input type="text" name="email">
		<br> 
		<br> 
		Mobile: <input type="text" name="mobile">
		<br> 
		<br> 
		Gender: 
		<select name="gender">
			<option value="M">Male</option>
			<option value="F">Female</option>
		</select>
		<br>
		<br>  
		Address: 
		<input type="text" name="address">
		<br>
		<br> 
		<input type="submit" value="Add Member">
	</form>

	</div>

</body>
</html>