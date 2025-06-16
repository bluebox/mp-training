package Library.src.main.java.com.LibraryManagement.dao;

import java.sql.SQLException;
import java.util.List;
import Library.src.main.java.com.LibraryManagement.model.*;

public interface MemberDAO {
    void addMember(Member member) throws SQLException;
    void updateMember(Member member) throws SQLException;
    Member getMemberById(int memberId) throws SQLException;
    List<Member> getAllMembers() throws SQLException;
}

