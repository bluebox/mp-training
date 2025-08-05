<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Member</title>
    <style>
        body { font-family: Arial; margin: 20px; }
        .error { color: red; }
        .success { color: green; }
        label { display: block; margin-top: 10px; }
        input, select, textarea { width: 300px; padding: 5px; }
        textarea { height: 60px; }
        button { margin-top: 15px; padding: 8px 15px; }
    </style>
</head>
<body>

    <h2 align="center">Add New Member</h2>

    <c:if test="${not empty message}">
        <p class="${messageColor}">${message}</p>
    </c:if>

    <form method="post" action="add-member" align="center">
        <label>Full Name:</label>
        <input type="text" name="name" value="${name}" required />

        <label>Email Address:</label>
        <input type="email" name="email" value="${email}" required />

        <label>Mobile Number:</label>
        <input type="text" name="phone" value="${phone}" required />

        <label>Gender:</label>
        <select name="gender">
            <option value="">-- Select Gender --</option>
            <option value="Male" ${gender == 'Male' ? 'selected' : ''}>Male</option>
            <option value="Female" ${gender == 'Female' ? 'selected' : ''}>Female</option>
        </select>

        <label>Address:</label>
        <textarea name="address">${address}</textarea>

        <br>
        <button type="submit">Add Member</button>
        <a href="index.jsp"><button type="button">Back to Dashboard</button></a>
    </form>

</body>
</html>
