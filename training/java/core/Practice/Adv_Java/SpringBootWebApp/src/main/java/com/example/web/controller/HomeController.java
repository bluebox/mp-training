package com.example.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

 import com.example.web.model.Student;
import com.example.web.service.StudentService;

import jakarta.validation.Valid;

@Controller
public class HomeController {
	@Autowired
	private StudentService studentService;
	private List<Student> students;
	
	@GetMapping("/home")
	public String home() {
		return "Home";
	}
	

	@PostMapping("/data")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String data(@Valid @ModelAttribute Student student,Model model) {
		studentService.saveStudent(student);
		students = studentService.getStudents();
		model.addAttribute("students",students);
		return "StudentData";
	}
	
	
	@PostMapping("/get/{id}")
	public ModelAndView updateStudentForm(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView();
		Student student = studentService.getStudentById(id);
		mv.addObject("student",student);
		mv.setViewName("UpdateStudent");
		return mv;
	}
	
	@PostMapping("/update/{id}")
	public String updateStudentDetails(@Valid @ModelAttribute Student student,@PathVariable("id") int id,Model model) {
		studentService.updateStudent(student,id);
		students = studentService.getStudents();
		model.addAttribute("students",students);
		return "StudentData";
	}
	
	
	  @PostMapping("/delete/{id}")
	  public String deleteStudent(@ModelAttribute Student student,@PathVariable("id") int id,Model model) { 
		  studentService.deleteStudent(student,id);
		  students = studentService.getStudents();
		  model.addAttribute("students",students);
		  return "StudentData"; 
	  }
	 
	  /*@PostMapping("/data") 
	  public ModelAndView datamodel(@Valid @RequestParam("fname")String fname,@Valid @RequestParam("lname") String lname,@Valid @RequestParam("email") String email,@Valid @RequestParam("dob") String dob,@Valid @RequestParam("gender") String gender) { 
		  ModelAndView mv = new ModelAndView();
		  student.setFname(fname);
		  student.setLname(lname);
		  student.setEmail(email);
		  student.setDob(dob);
		  student.setGender(gender);
		  studentService.saveStudent(student);
		  students = studentService.getStudents();
		  mv.addObject("students",students);
		  mv.setViewName("StudentData"); 
		  return mv; 
	  }*/
	 
}
