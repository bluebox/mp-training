<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <form action="issuebook" method="post">
 <label>BookId: </label><br>
 <input type="text" name="bookid" required/><br><br>
 
 <label>MemberId</label><br>
 <input type="text" name="memberid" required/><br><br>
 
 <button type="submit">Submit</button>
 
 </form>
 
</body>
</html>