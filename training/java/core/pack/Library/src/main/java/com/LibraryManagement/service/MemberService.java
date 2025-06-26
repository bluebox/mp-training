package Library.src.main.java.com.LibraryManagement.service;
import Library.src.main.java.com.LibraryManagement.model.*;
import java.util.*;

public interface MemberService {
    void addMember(Member member) throws Exception;
    void updateMember(Member member) throws Exception;
    List<Member> getAllMembers() throws Exception;
    Member getMemberById(int memberId) throws Exception;
}
