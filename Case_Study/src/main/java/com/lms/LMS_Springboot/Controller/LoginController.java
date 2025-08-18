package com.lms.LMS_Springboot.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController 
@RequestMapping("/Login")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class LoginController {
//	 @Value("${spring.security.user.name}")
//	    private String Username;
//	 @Value("${spring.security.user.password}")
//	    private String  Password;
//	 private final AuthenticationManager authManager ;
//	 public LoginController(AuthenticationManager authenticationManager) {
//	      
//			this.authManager = authenticationManager;
//	    }

	
	@RequestMapping(value="/csrftoken",method=RequestMethod.GET)
	public CsrfToken csrf(CsrfToken token) {
        return token; 
    }
	
	
//	@PostMapping
//	@ResponseBody
//	public ResponseEntity<String> loginpage(HttpServletRequest request,@RequestBody ObjectNode json) {
//		String username=json.get("username").asText();
//		String  password=json.get("password").asText();
//		if(username.equals(Username) && Password.equals(password)) {
//			Authentication auth = authManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(auth);
//
//            HttpSession session = request.getSession(true);
//            return ResponseEntity.ok("logged in");
//
//
//			
//		}
//		else {
//			return ResponseEntity.badRequest().body("not loged in");
//		}
//		
//	}
	

	
	
	
	

}
