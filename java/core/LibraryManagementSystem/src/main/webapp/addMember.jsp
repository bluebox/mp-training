<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="Pojo.Member" %>
<%@ page import="Service.LibraryService" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Member</title>
</head>
<body>

	<%
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		long mobile =Long.parseLong(request.getParameter("mobile"));
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
        try {
        	Member member = new Member();
    		member.setName(name);
    		member.setEmail(email);
    		member.setMobile(mobile);
    		member.setGender(gender.charAt(0));
    		member.setAddress(address);

            LibraryService lib = new LibraryService();
            lib.addMember(member);
	%>
            <h2>Member ADDED SUCCESSFULLY</h2>
	<%
       
        }catch (Exception e) {
            e.printStackTrace();
	%>
            <jsp:forward page="errorPage.html" />
	<%
    	}
	%>

</body>
</html>
