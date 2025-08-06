<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Members Section</title>
<style>
    body {
        font-family: 'Segoe UI', sans-serif;
        margin: 0;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
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
        text-align: center;
        width: 400px;
    }

    h3 {
        font-size: 26px;
        margin-bottom: 30px;
        color: #333;
    }

    form {
        margin-bottom: 20px;
    }

    input[type="submit"] {
        width: 100%;
        padding: 12px;
        font-size: 18px;
        background: #007BFF;
        color: #fff;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        transition: 0.3s;
    }

    input[type="submit"]:hover {
        background: #0056b3;
    }
</style>
</head>
<body>
    <div class="container">
        <h3>Members Section</h3>

        <form action="addMember.jsp">
            <input type="submit" value="Add a Member"/>
        </form>

        <form action="${pageContext.request.contextPath}/memberController" method="post">
            <input type="hidden" name="action" value="view" />
            <input type="submit" value="View Members"/>
        </form>

        <form action="../main.jsp" method="post">
            <input type="submit" value="Back to Main Menu" />
        </form>
    </div>
</body>
</html>
