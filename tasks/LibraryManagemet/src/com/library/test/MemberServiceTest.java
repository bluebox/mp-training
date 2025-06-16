package com.library.test;

import com.library.domain.Member;
import com.library.service.MemberService;
import com.library.dao.MemberDAO;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MemberServiceTest {

    class TestableMemberService extends MemberService {
        private final MemberDAO fakeDAO;

        public TestableMemberService(MemberDAO fakeDAO) {
            this.fakeDAO = fakeDAO;
        }

        @Override
        public MemberDAO getMemberDAO() {
            return fakeDAO;
        }
    }

    class FakeMemberDAO extends MemberDAO {
        private boolean insertResult;
        private List<Member> memberList;

        public FakeMemberDAO(boolean insertResult, List<Member> memberList) {
            this.insertResult = insertResult;
            this.memberList = memberList;
        }

        @Override
        public List<Member> getAllMembers() {
            return memberList;
        }
        
        @Override
        public boolean addMember(Member member) {
            return insertResult;
        }
    }

    @Test
    public void testViewAllMembers_returnsExpectedList() {
        List<Member> mockMembers = Arrays.asList(
            new Member(1, "Alice", "alice@example.com", 1234567890L, 'F', "Hyderabad"),
            new Member(2, "Bob", "bob@example.com", 9876543210L, 'M', "Chennai")
        );

        MemberService service = new TestableMemberService(new FakeMemberDAO(true, mockMembers));
        List<Member> members = service.viewAllMembers();

        assertEquals(2, members.size());
        assertEquals("Alice", members.get(0).getName());
        assertEquals("Bob", members.get(1).getName());
    }

    @Test
    public void testAddMember_returnsTrue_whenInsertSucceeds() {
        Member member = new Member("Charlie", "charlie@example.com", 1112223334L, 'M', "Mumbai");
        MemberService service = new TestableMemberService(new FakeMemberDAO(true, null));

        assertTrue(service.addMember(member));
    }

    @Test
    public void testAddMember_returnsFalse_whenInsertFails() {
        Member member = new Member("Daisy", "daisy@example.com", 9988776655L, 'F', "Delhi");
        MemberService service = new TestableMemberService(new FakeMemberDAO(false, null));

        assertFalse(service.addMember(member));
    }
}
