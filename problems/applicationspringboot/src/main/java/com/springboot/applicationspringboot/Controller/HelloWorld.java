package com.springboot.applicationspringboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.applicationspringboot.Model.Person;
import com.springboot.applicationspringboot.Model.Student;
import com.springboot.applicationspringboot.Model.Teacher;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.Errors;



@Slf4j
@Controller
public class HelloWorld {
	@Autowired
	public Person person;
	@Autowired
	public Teacher Teacher;
	
	// @RequestMapping(value={"","/","home"},method=RequestMethod.GET)  // like this we can map same function to various urls
	// example for get mapping and model (i,e: model is used for nothing but adding the objects and values to browser or frontend of the application)
	@GetMapping(value={"","/","home"})
	    public String displayHomePage(Model model) {
		    model.addAttribute("user", "prabhas");
		    model.addAttribute("list", List.of("a","e","i","o","u"));
		    model.addAttribute("type", true);
		    log.warn("Ensure the Student has no args constructor");
		    model.addAttribute("Student", new Student());
		    model.addAttribute("Teacher",Teacher);
		    model.addAttribute("Person",person.toString());
	        return "home.html";
	    }
	
	// example on postmapping with request param fields from input name and id matching
	@PostMapping(value= {"/add"})  
	public String addperson(@RequestParam(required=false ,defaultValue="son") String name,@RequestParam(required=false ,defaultValue="father") String last,Model model) {
		person.setName(name);
		  log.info("Name log: " + name);
		person.setLast(last);
		  log.info("last Name log: " + last);
		System.out.println(person.toString());
		model.addAttribute("Person", person);
		return "Person.html";
	}
	
	@GetMapping(value= {"/add"})  // request params in get mapping are seen in url itself
	public String getPerson(@RequestParam(required=false ,defaultValue="son get") String name,@RequestParam(required=false ,defaultValue="father get") String last,Model model) {
		person.setName(name);
		person.setLast(last);
		model.addAttribute("Person", person);
		return "Person.html";
		
	}
	
	// example on path params for model and view combined with redirect url feature
	@GetMapping("/{id}")
	public ModelAndView pathexample(@PathVariable(required =false) int id) {
		System.out.println("Hello i am called"+id);
		return new ModelAndView("redirect:/add");
	}
	
	@PostMapping("/addTeacher")
	public ModelAndView form(@Valid @ModelAttribute("Teacher") Teacher teacher,Errors errors,Model model) { // inorder to check validations we need to check the @Valid in function
	     	  if(errors.hasErrors()){
	              log.error("Teacher form validation failed due to : " + errors.toString());    //error handling in spring using the logger on console
	              model.addAttribute("error",errors.toString());
	              return new ModelAndView("redirect:/home");
	          }
		System.out.println(teacher.toString());
	     	 return new ModelAndView("redirect:/home");
	    // 	  return new ModelAndView("Person.html").addObject(teacher);
	}

	@PostMapping(value= {"","/form"})
	public ModelAndView formfill(@ModelAttribute("Student") Student stud) { // in caseof modelattribute first model need to contains that key
		System.out.println(stud.getName()+","+stud.getRollno()+","+stud.getStandard());
		// ModelAndView modelAndView = new ModelAndView("home.html");   // model + view both need to be configured here itself 
	       // modelAndView.addObject("studentobj", stud.toString());
		log.error("The error is occured");
		log.debug("Error while debug shown here");
	        return new ModelAndView("redirect:/home");
	}
}
