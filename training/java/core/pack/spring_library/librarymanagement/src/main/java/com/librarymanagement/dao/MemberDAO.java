package com.librarymanagement.dao;
import com.librarymanagement.model.*;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public interface MemberDAO {
    void addMember(Member member) throws SQLException;
    void updateMember(Member member) throws SQLException;
    Member getMemberById(int memberId) throws SQLException;
    List<Member> getAllMembers() throws SQLException;
}

