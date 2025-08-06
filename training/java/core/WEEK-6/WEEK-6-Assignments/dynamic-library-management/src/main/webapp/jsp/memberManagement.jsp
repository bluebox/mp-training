<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Member Management Options</title>
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

        .back-btn {
            position: absolute;
            top: 20px;
            left: 20px;
            padding: 6px 14px;
            font-size: 13px;
            background-color: rgba(255, 255, 255, 0.8);
            color: #0d47a1;
            font-weight: bold;
            border: none;
            border-radius: 8px;
            text-decoration: none;
            transition: background-color 0.3s ease;
            z-index: 10;
        }

        .back-btn:hover {
            background-color: #bbdefb;
        }

        .container {
            background: rgba(255, 255, 255, 0.95);
            padding: 30px 40px;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
            text-align: center;
            width: 380px;
        }

        h1 {
            font-size: 20px;
            color: #0d47a1;
            margin-bottom: 20px;
            border-bottom: 2px solid #90caf9;
            display: inline-block;
            padding-bottom: 8px;
        }

        .btn {
            display: block;
            margin: 12px auto;
            padding: 10px 24px;
            font-size: 14px;
            background-color: #64b5f6;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
            width: 100%;
        }

        .btn:hover {
            background-color: #42a5f5;
        }
    </style>
</head>
<body>

<a href="${pageContext.request.contextPath}/jsp/index.jsp" class="back-btn">← Back</a>

<div class="container">
    <h1>👤 Member Management</h1>

    <form action="addMember.jsp" method="get">
        <button type="submit" class="btn">➕ Register Member</button>
    </form>

    <form action="updateMember.jsp" method="get">
        <button type="submit" class="btn">📝 Update Member</button>
    </form>

    <form action="viewAllMembers.jsp" method="get">
        <button type="submit" class="btn">📋 View All Members</button>
    </form>
</div>

</body>
</html>
