package com.lms.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeConrtoller {
	 @GetMapping("/")
	    public String home() {
	        return "home";
	    }
}
