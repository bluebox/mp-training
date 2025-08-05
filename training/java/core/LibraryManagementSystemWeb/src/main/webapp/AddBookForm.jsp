<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Add Book</title>
    <style>
        .message {
            font-weight: bold;
            margin: 10px auto;
            width: fit-content;
            padding: 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h2 align="center">Add Book</h2>

    <form method="post" action="AddBookServlet" style="text-align: center;">
        <label>Title:</label>
        <input type="text" name="title" value="${requestScope.title}" /><br/><br/>

        <label>Author:</label>
        <input type="text" name="author" value="${requestScope.author}" /><br/><br/>

        <label>Category:</label>
        <input type="text" name="category" value="${requestScope.category}" /><br/><br/>

        <input type="submit" value="Add Book" />
    </form>

    <div id="message" class="message" style="color: ${messageColor}; display: ${message != null ? 'block' : 'none'};">
        ${message}
    </div>

    <div style="text-align: center;">
        <a href="index.jsp"><button>Back to Dashboard</button></a>
    </div>

    <script>
        setTimeout(() => {
            const msg = document.getElementById('message');
            if (msg) msg.style.display = 'none';
        }, 2000);
    </script>
</body>
</html>
