package com.EventManagement.EMS_Backend.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EventManagement.EMS_Backend.Model.ModelUser;
import com.EventManagement.EMS_Backend.Service.RegistrationService;
import com.EventManagement.EMS_Backend.Service.StudentService;
import com.EventManagement.EMS_Backend.Service.UserServiceImplementation;
import com.EventManagement.EMS_Backend.config.AuthResponse;
import com.EventManagement.EMS_Backend.config.JwtProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private RegistrationService userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StudentService studentservice;

   
    @Autowired
    private UserServiceImplementation customUserDetails;
    




    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody ModelUser user)   {
       

//        ModelUser isEmailExist = userRepository.findbyemail(user.getEmail());
//        if (isEmailExist != null) {
//            //throw new Exception("Email Is Already Used With Another Account");
//        	return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
//       }
       
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        userRepository.userRegister(user);
        UserDetails userDetails = customUserDetails.loadUserByUsername(user.getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtProvider.generateToken(authentication);


        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Register Success");
        authResponse.setStatus(true);
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

    }





    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody ObjectNode json) {
        String username =json.get("username").asText();
        String password = json.get("password").asText();
        System.out.println(username+"-------"+password);

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login success");
        authResponse.setJwt(token);
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }



    
    private Authentication authenticate(String username, String password) {

        System.out.println(username+"---++----"+password);
       
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("Sig in in user details"+ userDetails);

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