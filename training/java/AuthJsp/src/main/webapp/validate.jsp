<%@ page import="java.sql.*" %>
<%
    String jdbcURL = "jdbc:mysql://localhost:3306/login_db?useSSL=false&serverTimezone=UTC";
    String dbUser = "root"; // Replace with your DB user
    String dbPass = "root"; // Replace with your DB password

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    boolean isValid = false;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPass);

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            isValid = true;
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        out.println("<p>Error: " + e.getMessage() + "</p>");
    }

    if (isValid) {
%>
        <h2>Welcome, <%= username %>!</h2>
<%
    } else {
%>
        <h2>Login Failed</h2>
        <p>Invalid username or password.</p>
        <a href="login.jsp">Try Again</a>
<%
    }
%>
