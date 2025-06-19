package test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.library.domain.Member;
import com.library.service.impl.LibraryServiceImpl;

public class AddMemberTest {

    LibraryServiceImpl service = new LibraryServiceImpl();

    @Test
    void validMember() {
        Member member = new Member("Mani", "manirdy123@test.com", 9876543210L, 'M', "Hyderabad");
        boolean result = service.addMember(member);
        assertTrue(result);
    }

    @Test
    void invalidEmail() {
        Member member = new Member("Ravi", "invalid_email", 9876543210L, 'M', "Chennai");
        assertThrows(IllegalArgumentException.class, () -> {
            service.addMember(member);
        });
    }

    @Test
    void emptyName() {
        Member member = new Member("", "test@email.com", 9876543210L, 'M', "City");
        assertThrows(IllegalArgumentException.class, () -> {
            service.addMember(member);
        });
    }

    @Test
    void nullMember() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addMember(null);
        });
    }
    @Test
    void invalidMobile() {
        Member member = new Member("Ajay", "ajay@test.com", 123L, 'M', "Delhi");
        assertThrows(IllegalArgumentException.class, () -> {
            service.addMember(member);
        });
    }

    @Test
    void invalidGender() {
        Member member = new Member("Priya", "priya@test.com", 9876543210L, 'X', "Pune");
        assertThrows(IllegalArgumentException.class, () -> {
            service.addMember(member);
        });
    }

    @Test
    void emptyAddress() {
        Member member = new Member("Arjun", "arjun@test.com", 9876543210L, 'M', "");
        assertThrows(IllegalArgumentException.class, () -> {
            service.addMember(member);
        });
    }


    @Test
    void allFieldsEmpty() {
        Member member = new Member("", "", 0L, ' ', "");
        assertThrows(IllegalArgumentException.class, () -> {
            service.addMember(member);
        });
    }

}
