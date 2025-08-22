<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Member</title>
</head>
<body style="background: beige;">
	<h1 ALIGN="CENTER">ADD MEMBER</h1>
	<div align="center">
<form action=" ${pageContext.request.contextPath}/addMemberServlet" method="post" autocomplete="off">
    <pre>
<label>Full Name:</label> <input type="text" name="fullName" required />

<label>Email Address:</label> <input type="email" name="email" required />

<label>Mobile Number:</label> <input type="tel" name="mobile" required />

<label>Gender:</label>
<select name="gender" required>
  <option value="M">Male</option>
  <option value="F">Female</option>
</select>

<label>Address:</label>
<textarea rows="3" cols="20" name="address" required></textarea>

<input style="background: grey; border: none; border-radius: 4px;" type="submit" value="Submit" />
</pre>
</form>

	</div>
</body>
</html>