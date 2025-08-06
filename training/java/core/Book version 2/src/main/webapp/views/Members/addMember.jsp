<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a New Member</title>
<style>
body {
    font-family: 'Segoe UI', sans-serif;
    margin: 0;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: flex-start;
    padding-top: 40px;
     background-image:
		url('${pageContext.request.contextPath}/resources/janko-ferlic-sfL_QOnmy00-unsplash.jpg');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: fixed;

}


.container {
    background-color: white;
    padding: 40px;
    border-radius: 12px;
    width: 420px;
    
}

h3 {
    font-size: 26px;
    margin-bottom: 30px;
    color: #333;
    text-align: center;
}

.form-group {
    margin-bottom: 20px;
    text-align: left;
}

label {
    display: block;
    font-size: 16px;
    margin-bottom: 6px;
    color: #333;
}

input[type="text"], select {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 6px;
    box-sizing: border-box;
}

.error {
    color: red;
    font-size: 14px;
    margin-top: 4px;
}

input[type="submit"], .back-button {
    width: 100%;
    padding: 12px;
    font-size: 18px;
    background: #007BFF;
    color: #fff;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: 0.3s;
    margin-top: 10px;
}

input[type="submit"]:hover, .back-button:hover {
    background: #0056b3;
}

.message {
    font-size: 17px;
    margin-top: 20px;
    text-align: center;
}
</style>
</head>
<body>
    <div class="container">
        <h3>Add a Member</h3>
        <form action="${pageContext.request.contextPath}/memberController" method="post">
            <div class="form-group">
                <label>Enter Name:</label>
                <input type="text" name="name" value="${requestScope.name}" required/>
                <div class="error">${requestScope.nameError}</div>
            </div>

            <div class="form-group">
                <label>Enter Email:</label>
                <input type="text" name="email" value="${requestScope.email}" required/>
                <div class="error">${requestScope.emailError}</div>
            </div>

            <div class="form-group">
                <label>Select the Gender:</label>
                <select name="gender">
                    <option value="gender" ${requestScope.gender == 'option0' ? 'selected' : ''}>Gender</option>
                    <option value="Male" ${requestScope.gender == 'option1' ? 'selected' : ''}>Male</option>
                    <option value="Female" ${requestScope.gender == 'option2' ? 'selected' : ''}>Female</option>
                </select>
                <div class="error">${requestScope.genderError}</div>
            </div>

            <div class="form-group">
                <label>Enter Mobile No:</label>
                <input type="text" name="mobile" value="${requestScope.mobile}" required/>
                <div class="error">${requestScope.mobileError}</div>
            </div>

            <div class="form-group">
                <label>Enter Address:</label>
                <input type="text" name="address" value="${requestScope.address}" required/>
                <div class="error">${requestScope.addressError}</div>
            </div>

            <input type="hidden" name="action" value="add" />
            <input type="submit" value="Add Member" />

            <div class="message" style="color: ${requestScope.messageColor}">
                ${requestScope.message}
            </div>
        </form>

        <form action="${pageContext.request.contextPath}/views/Members/members.jsp" method="get">
            <button type="submit" class="back-button">Back to Dashboard</button>
        </form>
    </div>
</body>
</html>
