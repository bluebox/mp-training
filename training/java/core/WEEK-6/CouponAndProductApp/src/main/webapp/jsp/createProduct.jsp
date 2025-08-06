<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Product</title>
<link rel="stylesheet" type="text/css" href="../styles/createProduct.css">
</head>
<body>
	<main id="main-container">
		<h1>Create Product</h1>
		<form action="createProductController" method="post" >
			<div class="input-container">
				<label>Name : </label>
				<input name="name" value="Watch" />
			</div>
			<div class="input-container">
				<label>Description : </label>
				<textarea name="description" rows=4 >This is very good product.</textarea>
			</div>
			<div class="input-container">
				<label>Expiry Date : </label>
				<input name="expDate" type="date" />
			</div>
		</form>
	</main>
</body>
<script type="text/javascript">
	function handleDiscountChange(){
		let rangeValue=document.querySelector("input[name='discount']").value;
		let rangeLabel=document.getElementById("range-label");
		rangeLabel.textContent=rangeValue;
	}
</script>
</html>