package com.library.DaoInterface;
import com.library.domain.Member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MemberDAOInterface {

    public abstract boolean updateMember(Connection conn, Member member) throws Exception;

    public abstract boolean addMember(Connection conn, Member member) throws Exception;
    
    public abstract void insertIntoMemberLog(Connection conn, ResultSet rs) throws Exception;

    public abstract ResultSet getMemberById(Connection conn, int memberId) throws SQLException;

    public abstract List<Member> fetchAllmembers() throws SQLException;
}
