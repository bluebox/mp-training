package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.library.domain.Member;
import com.library.utilities.ConnectionMaker;

public class TestMemberDAO extends MemberDAO {

    @Override
    public boolean addMember(Member member) {
        int check = 0;
        try (Connection conn = ConnectionMaker.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO test_member(name, email, mobile, gender, address) VALUES(?,?,?,?,?)"
            );
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setLong(3, member.getMobile());
            ps.setString(4, String.valueOf(member.getGender()));
            ps.setString(5, member.getAddress());

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check == 1;
    }
}
