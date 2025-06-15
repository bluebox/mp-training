package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.library.domain.Member;
import com.library.utilities.ConnectionMaker;

public class UpdateMemberDetailsDAO {

    public boolean updateMember(Member member) {
        String query = "UPDATE members SET name=?, email=?, mobile=?, gender=?, address=? WHERE id=?";
        try (Connection conn = ConnectionMaker.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setLong(3, member.getMobile());
            ps.setString(4, String.valueOf(member.getGender()));
            ps.setString(5, member.getAddress());
            ps.setInt(6, member.getId());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
