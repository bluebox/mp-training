package com.vardhan.Practice4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserService {

    private final EmailService emailService;
    @Autowired 
    public UserService(EmailService emailService) {
        this.emailService = emailService;
        System.out.println("UserService bean is being initialized!");
    }

    public void registerUser(String username, String email) {
        System.out.println("Registering user: " + username);
        emailService.sendEmail(email, "Welcome!", "Thanks for registering!");
    }
}
