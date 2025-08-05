package com.library.dao.interfaces;

import com.library.model.Member;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface MemberDao {
    boolean addMember(Member member, Connection conn) throws SQLException;
    boolean updateMember(Member member, Connection conn) throws SQLException;
    List<Member> getAllMembers();
    Member getMemberById(int id);
}

