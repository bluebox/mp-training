<!DOCTYPE html>
<html>
<head>
    <title>Return Book</title>
    
<body style="display:flex;flex-direction:column;align-items:center;">
    <h2>Return a Book</h2>
    <form action="${pageContext.request.contextPath}/returnbook" method="post">
        <label for="bookId">Book ID:</label>
        <input type="text" id="bookId" name="bookid" required><br><br>

        <label for="memberId">Member ID:</label>
        <input type="text" id="memberId" name="memberid" required><br><br>

        <input type="submit" value="Return Book">
    </form>

   
</body>
</html>
