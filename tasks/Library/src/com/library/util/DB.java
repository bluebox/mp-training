package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "Ramsai";
    private static final String PASSWORD = "Rathod@777";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
if (member.getName() == null || member.getName().trim().isEmpty()) {
    throw new IllegalArgumentException("Name cannot be empty.");
}

if (member.getEmail() == null || !member.getEmail().matches("^\\S+@\\S+\\.\\S+$")) {
    throw new IllegalArgumentException("Invalid email address.");
}

if (String.valueOf(member.getMobile()).length() != 10) {
    throw new IllegalArgumentException("Mobile number must be 10 digits.");
}

String gender = String.valueOf(member.getGender()).toUpperCase();
if (!gender.equals("M") && !gender.equals("F")) {
    throw new IllegalArgumentException("Gender must be 'M' or 'F'.");
}

if (member.getAddress() == null || member.getAddress().trim().isEmpty()) {
    throw new IllegalArgumentException("Address cannot be empty.");
}

if (member.getMemberId() <= 0) {
    throw new IllegalArgumentException("Invalid member ID.");
}
