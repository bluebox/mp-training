<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Update Member</title>
    <style>
        input, select, button {
            margin: 5px;
            padding: 8px;
        }
    </style>
</head>
<body>
    <div align="center">
        <h3>Update Member Details</h3>
        
        <div>

        <form action="${pageContext.request.contextPath}/updateMemberServlet" method="post">
            <input type="hidden" name="memberId" value="${member.memberId}" />
			<label>name: </label>
            <input type="text" name="name" value="${member.name}" placeholder="Name" required /><br/>
            <label>Email: </label>
            <input type="text" name="Email" value="${member.email}" placeholder="email @ letters.com" required /><br/>
            <label>Mobile: </label>
            <input type="text" name="Mobile" value="${member.mobile}" placeholder="only numbers" required /><br/>
            <label>Gender: </label>
            <select name="gender">
                    <option value="M" <c:if test="${member.gender =='M'}">selected</c:if>>Male</option>
                    <option value="F" <c:if test="${member.gender =='F'}">selected</c:if>>Female</option>
                </select>
            
			<br/>
			<label>Address:</label>
			<input type="text" name="Address" value="${member.address}" placeholder="Home address" required /><br/>
			<button type="submit">Update</button>
            <a href="${pageContext.request.contextPath}/viewMembersServlet"><button type="button">Cancel</button></a>
        </form>

</div>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
    </div>
</body>
</html>
