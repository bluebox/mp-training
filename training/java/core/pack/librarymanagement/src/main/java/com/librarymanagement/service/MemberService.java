package com.librarymanagement.service;
import com.librarymanagement.model.*;
import java.util.*;

import org.springframework.stereotype.Component;
@Component
public interface MemberService {
    void addMember(Member member) throws Exception;
    void updateMember(Member member) throws Exception;
    List<Member> getAllMembers() throws Exception;
    Member getMemberById(int memberId) throws Exception;
}
