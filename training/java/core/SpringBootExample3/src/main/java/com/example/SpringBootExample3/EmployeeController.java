package com.example.SpringBootExample3;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.validation.Valid;

@Controller
public class EmployeeController implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/home")
	public String ShowForm(Employee employee) {
		return "form1.html";
	}

	@PostMapping("/home")
	public String CheckPersonData(@Valid Employee employee, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			return "form1.html";
		}
		return "redirect:/results";
	}
}
