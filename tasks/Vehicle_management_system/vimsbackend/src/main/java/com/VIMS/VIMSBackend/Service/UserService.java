package com.VIMS.VIMSBackend.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.VIMS.VIMSBackend.Model.UserModel;
import com.VIMS.VIMSBackend.Repo.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface,UserDetailsService{

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @Override
    public UserDetails loadUserByUsername(String UserId) throws UsernameNotFoundException {
        UserModel user = userRepo.getUserById(Integer.parseInt(UserId));
        System.out.println(user);
       
        if(user==null) {
            throw new UsernameNotFoundException("User not found with this email"+UserId);

        }

        
        System.out.println("Loaded user: " + user.getUserId() + ", Role: " + user.getRole());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+String.valueOf(user.getRole())));
       System.out.println(authorities);
        return new org.springframework.security.core.userdetails.User(
                String.valueOf(user.getUserId()),
                user.getUserPassword(),
                authorities);
    }

    // Add User
    public int addUser(UserModel user) {
        return userRepo.addUser(user);
    }

    // Update User
    public int updateUser(UserModel user) {
        return userRepo.updateUser(user);
    }

    // Get User by Id
    public UserModel getUserById(int userId) {
        return userRepo.getUserById(userId);
    }

    // Get all Users
    public List<UserModel> getAllUsers() {
        return userRepo.getAllUsers();
    }
    
    @Override
    public String addReview(int userId, String review) {
        return userRepo.addReview(userId, review);
    }

    @Override
    public List<String> getAllReviews() {
        return userRepo.getAllReviews();
    }

    
    public UserModel getUserByEmail(String Email) {
		return userRepo.getUserByEmail(Email);
	}
    
}

