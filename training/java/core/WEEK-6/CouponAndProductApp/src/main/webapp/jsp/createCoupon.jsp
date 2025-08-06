<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Coupon</title>
<link rel="stylesheet" type="text/css" href="../styles/createCoupon.css">
</head>
<body>
	<main id="main-container">
		<h1>Create Coupon</h1>
		<form action="createCouponController" method="post" >
			<div class="input-container">
				<label>Code : </label>
				<input name="code" value="SUPERSALE" />
			</div>
			<div class="input-container">
				<label>Discount : </label>
				<input type="range" step="1" min="0" max="100" name="discount" oninput="handleDiscountChange()" />
				<label id="range-label">50</label>
			</div>
			<div class="input-container">
				<label>Expiry Date : </label>
				<input name="expDate" type="date" />
			</div>
			input
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