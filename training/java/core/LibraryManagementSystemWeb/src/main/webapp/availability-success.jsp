<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Availability Updated</title>
    <meta http-equiv="refresh" content="2; URL=${redirectURL}">
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 100px;
        }
        .message {
            color: green;
            font-size: 20px;
        }
    </style>
</head>
<body>
    <p class="message">${message}</p>
    <p>Redirecting to book list...</p>
</body>
</html>
