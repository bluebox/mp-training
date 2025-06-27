package com.example.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.example.model.Student;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {
	public static int count=0;
	Logger log=LoggerFactory.getLogger(WebController.class.getName());
	@RequestMapping(value={"","/","/home"})
	@RequestScope
	public String webApp(Model m) {
		System.out.println(++count);
		m.addAttribute("student", new Student());
		m.addAttribute("val", false);
		return "web.html";
	}
	@RequestMapping("/hi")
	public String show(@Valid @ModelAttribute("student") Student s,BindingResult e,Model m,@RequestParam(required=false) String name,@RequestParam(required=false) int age,@RequestParam(required=false) String sType,@RequestParam(required=false) String sname,@RequestParam(required=false) String marks) {
		if(e.hasErrors()) {
			for(ObjectError i:e.getAllErrors()) {
				log.error(i.getDefaultMessage());
			}
			m.addAttribute("error",e);
			m.addAttribute("val", true);
			return "web.html";
		}
		m.addAttribute("name",name);
		m.addAttribute("age",age);
		m.addAttribute("stype", sType);
		m.addAttribute("sname", sname);
		m.addAttribute("marks", marks);
		return "thanks.html";
	}
}
