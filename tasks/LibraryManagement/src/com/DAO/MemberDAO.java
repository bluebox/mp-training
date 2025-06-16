package com.DAO;

import com.models.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MemberDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "pavan";
    private static final String PASSWORD = "Pavan@02";
    
    public boolean save(Member member) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO members(name, email, mobile, gender, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setInt(3, (int) member.getMobile());
            stmt.setString(4, String.valueOf(member.getGender()));
            stmt.setString(5, member.getAddress());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
