package updatedbookselling.bookcatalog.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import updatedbookselling.bookcatalog.daoimpl.UserRepository;
import updatedbookselling.bookcatalog.domain.Member;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Map<String, String> authenticate(String memberId, String password) {
    	System.out.println(memberId + " " + password);
    	
        Member member = userRepository.findByUserIdAndPassword(memberId, password);
        
        System.out.println(member);
        if (member != null) {
            Map<String, String> result = new HashMap<>();
            result.put("role", member.getRoles());
            System.out.println(result.get("role"));
            return result;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public boolean isAdmin(String memberId) {
        String role = userRepository.getRoleByUserId(memberId);
        return role != null && role.equalsIgnoreCase("ADMIN");
    }
}

