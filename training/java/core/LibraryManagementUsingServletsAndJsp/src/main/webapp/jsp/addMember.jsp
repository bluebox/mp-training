<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Member</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background:
                linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
                url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=1470&q=80');
            background-size: cover;
            background-position: center;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
        }

        .home-btn, .back-btn {
            position: absolute;
            top: 20px;
            padding: 6px 14px;
            font-size: 13px;
            font-weight: bold;
            border-radius: 8px;
            border: none;
            text-decoration: none;
        }

        .home-btn {
            right: 20px;
            background-color: #c8e6c9;
            color: #1b5e20;
        }

        .back-btn {
            left: 20px;
            background-color: #bbdefb;
            color: #0d47a1;
        }

        .container {
            background: rgba(255, 255, 255, 0.95);
            padding: 30px 40px;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
            width: 400px;
        }

        h1 {
            text-align: center;
            color: #0d47a1;
            font-size: 20px;
            margin-bottom: 15px;
            border-bottom: 2px solid #90caf9;
            display: inline-block;
            padding-bottom: 8px;
        }

        label {
            display: block;
            margin-top: 12px;
            font-size: 14px;
            font-weight: bold;
        }

        input[type="text"], select {
            width: 100%;
            padding: 8px;
            font-size: 13px;
            border-radius: 8px;
            border: 1px solid #ccc;
            margin-top: 5px;
        }

        .btn {
            margin-top: 20px;
            padding: 10px;
            width: 100%;
            font-size: 14px;
            border-radius: 8px;
            border: none;
            cursor: pointer;
            background-color: #64b5f6;
            color: white;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #42a5f5;
        }

        .popup-message {
            position: fixed;
            top: 30px;
            left: 50%;
            transform: translateX(-50%);
            background-color: #d0f0c0;
            color: #2e7d32;
            padding: 10px 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.2);
            font-weight: bold;
            font-size: 14px;
            z-index: 1000;
            animation: fadeOut 0.5s ease-in-out 2.5s forwards;
        }

        .popup-message.error {
            background-color: #ffcdd2;
            color: #c62828;
        }

        @keyframes fadeOut {
            to {
                opacity: 0;
                visibility: hidden;
                transform: translateX(-50%) translateY(-10px);
            }
        }
    </style>
</head>
<body>

<a href="${pageContext.request.contextPath}/jsp/index.jsp" class="home-btn">🏠 Home</a>
<a href="${pageContext.request.contextPath}/jsp/memberManagement.jsp" class="back-btn">← Back</a>

<c:if test="${not empty message}">
    <div class="popup-message ${success eq false ? 'error' : ''}">
        ${message}
    </div>
</c:if>

<div class="container">
    <h1>👤 Registration Form</h1>

    <form action="/dynamic-library-management/addMemberController" method="post">
        <label>Name:</label>
        <input type="text" name="name" placeholder="Enter Your Name" required />

        <label>Email Id:</label>
        <input type="text" name="email" placeholder="Enter Your Email Id" required />

        <label>Mobile No.:</label>
        <input type="text" name="mobile" placeholder="Enter Your Mobile Number" required />

        <label>Gender:</label>
        <select name="gender" required>
            <option value="">Select Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
        </select>

        <label>Address:</label>
        <input type="text" name="address" placeholder="Enter Your Address" required />

        <input type="submit" value="Register ➕" class="btn" />
    </form>
</div>

</body>
</html>
