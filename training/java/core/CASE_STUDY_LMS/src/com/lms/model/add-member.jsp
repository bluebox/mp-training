<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Member</title>
</head>
<body style="background-color:#edf6f9; font-family:sans-serif;">
    <h2 style="text-align:center;">Add Member</h2>
    <form action="addMember" method="post" style="max-width: 500px; margin: auto;">
        <label>Name:</label><br/>
        <input type="text" name="name" required/><br/><br/>
        
        <label>Email:</label><br/>
        <input type="email" name="email" required/><br/><br/>

        <label>Mobile Number:</label><br/>
        <input type="text" name="mobile" required/><br/><br/>

        <label>Gender:</label><br/>
        <select name="gender" required>
            <option value="">Select</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
        </select><br/><br/>

        <label>Address:</label><br/>
        <textarea name="address" rows="4" required></textarea><br/><br/>

        <input type="submit" value="Add Member" />
    </form>

    <p style="color:red; text-align:center;">
        <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
    </p>
</body>
</html>