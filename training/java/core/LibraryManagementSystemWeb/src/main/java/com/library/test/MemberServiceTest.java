package com.library.test;
import com.library.model.Member;
import com.library.service.impl.MemberServiceImplementation;
import com.library.service.interfaces.MemberService;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class MemberServiceTest {

    MemberService service = new MemberServiceImplementation();

    private String generateRandomEmail(String baseName) {
        return baseName + System.currentTimeMillis() + "@gmail.com";
    }

    private long generateRandomMobile() {
        return 9000000000L + (long) (Math.random() * 100000);
    }

    @Test
    public void testRegisterMember() throws Exception {
        Member m = new Member("John", generateRandomEmail("john"), generateRandomMobile(), "M", "Hyderabad");
        boolean result = service.registerMember(m);
        assertTrue("Member should be registered successfully", result);
    }
    
    @Test
    public void testFetchMemberById() throws Exception {
        String email = generateRandomEmail("jane");
        long mobile = generateRandomMobile();
        Member m = new Member("Jane", email, mobile, "F", "Delhi");
        service.registerMember(m);

        Member fetched = service.fetchAllMembers().stream()
                .filter(x -> x.getEmail().equals(email))
                .findFirst().orElse(null);

        assertNotNull("Fetched member should not be null", fetched);
        assertEquals("Jane", fetched.getName());
    }

    @Test
    public void testModifyMember() throws Exception {
        String email = generateRandomEmail("sam");
        long mobile = generateRandomMobile();
        Member m = new Member("Sam", email, mobile, "M", "Chennai");
        service.registerMember(m);

        Member saved = service.fetchAllMembers().stream()
                .filter(x -> x.getEmail().equals(email))
                .findFirst().orElse(null);

        assertNotNull("Saved member should not be null", saved);
        saved.setName("Samuel");

        boolean updated = service.modifyMember(saved);
        assertTrue("Member should be updated successfully", updated);
    }
    
    @Test
    public void testGetAllMembers() throws Exception {
        String email1 = generateRandomEmail("user1");
        String email2 = generateRandomEmail("user2");

        Member m1 = new Member("Alice", email1, generateRandomMobile(), "F", "Mumbai");
        Member m2 = new Member("Bob", email2, generateRandomMobile(), "M", "Patna");

        service.registerMember(m1);
        service.registerMember(m2);

        List<Member> members = service.fetchAllMembers();

        assertNotNull("Member list should not be null", members);
        assertTrue("Member list should contain at least 2 members", members.size() >= 2);

        boolean containsAlice = members.stream().anyMatch(m -> m.getEmail().equals(email1));
        boolean containsBob = members.stream().anyMatch(m -> m.getEmail().equals(email2));

        assertTrue("list should have alice", containsAlice);
        assertTrue("list should have bob", containsBob);
    }
}
