<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Return Book</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)),
                    url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=1470&q=80');
        background-size: cover;
        background-position: center;
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        position: relative;
    }

    .home-btn, .back-btn {
        position: absolute;
        top: 20px;
        padding: 6px 12px;
        font-weight: bold;
        font-size: 12px;
        border-radius: 6px;
        border: 2px solid;
        text-decoration: none;
        cursor: pointer;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    .home-btn {
        right: 20px;
        background-color: white;
        color: #2e7d32;
        border-color: #2e7d32;
    }

    .home-btn:hover {
        background-color: #2e7d32;
        color: white;
    }

    .back-btn {
        left: 20px;
        background-color: white;
        color: #0d47a1;
        border-color: #0d47a1;
    }

    .back-btn:hover {
        background-color: #0d47a1;
        color: white;
    }

    .container {
        background: rgba(255, 255, 255, 0.96);
        padding: 25px 30px;
        border-radius: 12px;
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.25);
        width: 100%;
        max-width: 360px;
    }

    h1 {
        text-align: center;
        font-size: 18px;
        color: #0d47a1;
        margin-bottom: 20px;
        border-bottom: 1.5px solid #90caf9;
        padding-bottom: 6px;
    }

    label {
        display: block;
        margin-top: 12px;
        font-size: 13px;
        font-weight: bold;
        color: #333;
    }

    select {
        width: 100%;
        padding: 8px;
        font-size: 13px;
        border-radius: 6px;
        border: 1px solid #ccc;
        margin-top: 4px;
        font-family: inherit;
        outline: none;
        transition: border-color 0.3s ease;
    }

    select:focus {
        border-color: #64b5f6;
        box-shadow: 0 0 5px rgba(100, 181, 246, 0.5);
    }

    .btn {
        margin-top: 20px;
        padding: 10px;
        width: 100%;
        font-size: 13px;
        border-radius: 6px;
        border: none;
        cursor: pointer;
        background-color: #64b5f6;
        color: white;
        transition: background-color 0.3s ease;
        font-family: inherit;
        font-weight: bold;
    }

    .message {
        font-size: 13px;
        margin-top: 12px;
        font-weight: bold;
        text-align: center;
    }

.popup {
            position: absolute;
            top: 10%;
            left: 50%;
            transform: translateX(-50%);
            background-color: #f0f0f0;
            color: #000;
            padding: 10px 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            z-index: 999;
            font-size: 14px;
            display: none;
        }

        .popup.success {
            background-color: #c8e6c9;
            color: #2e7d32;
        }

        .popup.error {
            background-color: #ffcdd2;
            color: #c62828;
        }
</style>
</head>
<body>

<a href="${pageContext.request.contextPath}/jsp/index.jsp" class="home-btn">🏠 Home</a>
<a href="${pageContext.request.contextPath}/jsp/issueReturn.jsp" class="back-btn">← Back</a>

<c:if test="${not empty message}">
	<div id="popupMessage" class="popup ${status}">${message}</div>
</c:if>

<div class="container">
    <h1>🔄 Return Book</h1>
    <form action="returnRecord" method="post">

			<label>Select Member:</label> <select name="memberId" required>
				<option value="">Select Member</option>
				<c:forEach var="m" items="${members}">
					<option value="${m.memberId}"
						${selectedMemberId == m.memberId ? "selected" : ""}>${m.memberName}</option>
				</c:forEach>
			</select>

			<c:if test="${step == 'book'}">
				<label>Select Book:</label>
				<select name="bookId" required>
					<option value="">Select Book</option>
					<c:forEach var="b" items="${books}">
						<option value="${b.bookId}">${b.title}</option>
					</c:forEach>
				</select>
			</c:if>

			<c:choose>
				<c:when test="${step == 'book'}">
					<input type="submit" class="btn" formaction="finalReturn"
						value="Return Book" />
				</c:when>
				<c:otherwise>
					<input type="submit" class="btn" value="Next" />
				</c:otherwise>
			</c:choose>

		</form>
	</div>
	
	<script>
        const popup = document.getElementById('popupMessage');
        if (popup) {
            popup.style.display = 'block';
            setTimeout(() => {
                popup.style.display = 'none';
            }, 5000);
        }
    </script>

</body>
</html>