package  com.library.service;
import com.library.model.*;
import java.util.*;

public interface MemberService {
    void addMember(Member member) throws Exception;
    void updateMember(Member member) throws Exception;
    List<Member> getAllMembers() throws Exception;
    Member getMemberById(int memberId) throws Exception;
}
