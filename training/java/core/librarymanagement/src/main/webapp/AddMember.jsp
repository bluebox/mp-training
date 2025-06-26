<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form action="addMember" method="post">
 
 
 <label>Name</label><br>
 <input type="text" name="name"  required/><br><br>
 
 <label>Email</label><br>
 <input type="text" name="email" required/> <br><br> 
 
 <label>Mobile</label><br>
 <input type="number" name="mobile" required/> <br><br>
 
 <label>Gender</label><br>
 <input type="radio" name="gender" value="Male" checked/>Male<br>
 <input type="radio" name="gender" value="Female" />Female<br><br>
 
 <label>Address</label><br>
 <input type="textbox" name="address"/><br><br>
 
 <button type="submit">Submit</button>
 
  <% 
        String msg = (String) request.getAttribute("message");
        if (msg != null) { 
    %>
        <p style="color:green;"><%= msg %></p>
    <% } %>

 
 </form>
</body>
</html>