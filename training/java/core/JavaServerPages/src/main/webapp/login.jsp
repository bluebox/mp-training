<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
    <form action="j_security_check" method="POST">
        Username: <input type="text" name="j_username"/><br/>
        Password: <input type="password" name="j_password"/><br/>
        <input type="submit" value="Login"/>
    </form>
</body>
</html>