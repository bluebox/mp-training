package main.com.library.dao;


import main.com.library.domain.Member;
import main.com.library.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MemberDAO {

    public boolean updateMember(Member member) {
        String sql = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setLong(3, member.getMobile());
            stmt.setString(4, String.valueOf(member.getGender()));
            stmt.setString(5, member.getAddress());
            stmt.setInt(6, member.getMemberId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}