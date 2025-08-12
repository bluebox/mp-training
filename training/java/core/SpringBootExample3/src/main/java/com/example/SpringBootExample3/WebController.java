package com.example.SpringBootExample3;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.validation.Valid;

@Controller
public class WebController implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/results").setViewName("results");
	}
	
	@GetMapping("/")
	public String ShowForm(PersonForm personForm)
	{
		return "form.html";
	}
	
	@PostMapping("/")
	public String CheckPersonData(@Valid PersonForm personForm,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			return "form.html";
		}
		return "redirect:/results";
		
	}

}	
