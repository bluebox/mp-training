package updatedbookselling.bookcatalog.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
@CrossOrigin(origins = "http://localhost:3000" )
@RestController
public class SecurityController {
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest  request) {
		return (CsrfToken)request.getAttribute("_csrf");
	}
}
