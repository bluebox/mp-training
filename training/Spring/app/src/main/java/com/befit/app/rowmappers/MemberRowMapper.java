package com.befit.app.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.befit.app.beans.Member;

public class MemberRowMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = new Member();
        member.setId(rs.getInt("id"));
        member.setName(rs.getString("name"));
        member.setMobile(rs.getLong("mobile"));
        member.setAge(rs.getInt("age"));
        member.setWeight(rs.getFloat("weight"));
        member.setHeight(rs.getFloat("height"));
        member.setAddress(rs.getString("address"));
        member.setMemberShip(rs.getString("memberShip"));
        member.setJoinDate(rs.getDate("joinDate"));
        member.setExpiryDate(rs.getDate("expiryDate"));
        member.setStatus(rs.getString("status"));
        return member;
    }
}