package com.example.dao;

import java.sql.*;
import com.example.model.User;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/login_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root"; // replace with your MySQL password

    public boolean validate(User user) {
        boolean status = false;

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
        	System.out.println(user.getUsername());
        	System.out.println(user.getPassword());
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
