package com.VIMS.VIMSBackend.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.VIMS.VIMSBackend.Config.AuthResponse;
import com.VIMS.VIMSBackend.Config.JWTProvider;
import com.VIMS.VIMSBackend.Model.Role;
import com.VIMS.VIMSBackend.Model.UserModel;
import com.VIMS.VIMSBackend.Repo.UserRepo;
import com.VIMS.VIMSBackend.Service.UserService;
import com.VIMS.VIMSBackend.Service.UserServiceInterface;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/UserAuth")
public class UserController {
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	 
    @Autowired
    private UserService UserServiceImplementation;
    
    @Autowired
    private UserServiceInterface userServiceInterface;
    
    @Autowired 
    private UserRepo userRepo;
	 
	 
	 @PostMapping("/signup")
	    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody UserModel user) throws Exception  {
	        int UserId =user.getUserId();
	        String password = user.getUserPassword();
	        String Email=user.getUserEmail();
	        String FirstName = user.getUserFirstname();
	        String LastName = user.getUserLastname();
	         String phone=user.getUserPhone();
	        String Address=user.getUserAddress();
	        Date registrationdate=Date.valueOf(LocalDate.now());
	        Role role = user.getRole();

	        UserModel userexist=UserServiceImplementation.getUserByEmail(Email);
	        UserModel isUserExists=userexist;
	        if (isUserExists != null) {
	            throw new Exception("Email Is Already Used With Another Account");
	        }
	        UserModel createdUser = new UserModel();
	        createdUser.setUserId(UserId);
	        createdUser.setUserFirstname(FirstName);
	        createdUser.setUserLastname(LastName);
	        createdUser.setRole(role);
	        createdUser.setUserRegistationDate(registrationdate);
	        createdUser.setUserEmail(Email);
	        createdUser.setUserAddress(Address);
	        createdUser.setUserPhone(phone);
	        createdUser.setUserPassword(passwordEncoder.encode(password));
	        
	        int savedUser = UserServiceImplementation.addUser(createdUser);
	        //  userRepository.save(savedUser);
	  
	        Authentication authentication = new UsernamePasswordAuthenticationToken(UserId,password);
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String token = JWTProvider.generateToken(authentication);


	        AuthResponse authResponse = new AuthResponse();
	        authResponse.setJwt(token);
	        authResponse.setMessage("Register Success");
	        authResponse.setStatus(true);
	        authResponse.setUser(userRepo.getUserByEmail(Email));
	        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	    }
	 
	 
	 
	    @PostMapping("/signin")
	    public ResponseEntity<AuthResponse> signin(@RequestBody ObjectNode credentials ) {
	    	int UserId=credentials.get("UserId").asInt();
	    	String password=credentials.get("password").asText();
	    	System.out.println(UserId+"-------"+password);

	        Authentication authentication = authenticate(UserId,password);
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String token = JWTProvider.generateToken(authentication);
	        AuthResponse authResponse = new AuthResponse();
             
	        authResponse.setMessage("Login Success");
	        authResponse.setUser(userRepo.getUserById(UserId));
	        authResponse.setJwt(token);
	        authResponse.setStatus(true);
	        return new ResponseEntity<>(authResponse,HttpStatus.OK);
	    }


	    @PostMapping("/review/{id}")
	    public ResponseEntity<String> addReview(@PathVariable("id") int userId, @RequestBody String review) {
	        String result = UserServiceImplementation.addReview(userId, review);
	        return ResponseEntity.ok(result);
	        
	    }
	    
	    // Get All Reviews
	    @GetMapping("/reviews")
	    public ResponseEntity<List<String>> getAllReviews() {
	        List<String> list = UserServiceImplementation.getAllReviews();
	      return ResponseEntity.ok().body(list.subList(0, 5));}
	 
	    
	 // Get only Admins directly from all users
	    @GetMapping("/admins")
	    public ResponseEntity<List<UserModel>> getAllAdmins() {
	        List<UserModel> allUsers = userServiceInterface.getAllUsers();

	        // filter admins only
	        List<UserModel> admins = allUsers.stream()
	                .filter(user -> "A".equalsIgnoreCase(user.getRole().getType()))
	                .toList();

	        return ResponseEntity.ok(admins);
	    }
	    
	    
	   
	 
	 private Authentication authenticate(int UserId, String password) {

	        System.out.println(UserId+"---++----"+password);

	       // UserDetails userDetails = customUserDetails.loadUserByUsername(UserId);
	        UserDetails userDetails=UserServiceImplementation.loadUserByUsername(String.valueOf(UserId));

	        System.out.println("Signin user details"+ userDetails);

	        if(userDetails == null) {
	            System.out.println("Sign in details - null" + userDetails);

	            throw new BadCredentialsException("Invalid username and password");
	        }
	        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
	            System.out.println("Sign in userDetails - password mismatch"+userDetails);

	            throw new BadCredentialsException("Invalid password");

	        }
	        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

	    }
	 
	 
}




