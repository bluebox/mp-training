package com.library.dao;

import java.sql.SQLException;
import java.util.List;
import com.library.model.*;

public interface MemberDAO {
    void addMember(Member member) throws SQLException;
    void updateMember(Member member) throws SQLException;
    Member getMemberById(int memberId) throws SQLException;
    List<Member> getAllMembers() throws SQLException;
}

