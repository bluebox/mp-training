package com.eazybytes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eazybytes.model.Student;
import com.eazybytes.serviceImpl.StudentImpl;

import java.util.List;

@Controller
public class MyController {
	
	@Autowired
	public StudentImpl studentService;
	
    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("username", "Greshma Ganta");
        model.addAttribute("role", "Software Developer");
        model.addAttribute("skills", List.of("Java", "Spring Boot", "Thymeleaf", "MySQL"));
        
        return "home1"; 
    }
    
    @RequestMapping(value={"/StudentForm","/student",""})
    		public String studentForm(Model m) {
    		m.addAttribute("student",new Student());
    	
    			return "StudentForm";
    		}
    
    @RequestMapping("/submit")
    public String submitPage(@ModelAttribute Student s) {
    	
    	Boolean sucess=studentService.addStudent(s);
    	
    //	System.out.println("Student name: "+s.getName()); 
    	
		if(sucess) {
			return "sucess";
		}
		else
		{
			return "failure";
		}
    }
    
    @RequestMapping("/view")
    public String viewStudents(Model m) {
    	
    	String msg=studentService.viewStudents();
    	m.addAttribute("msg",msg);
    	
    	
		return "viewPage";
    	
    }
   
    	
}
