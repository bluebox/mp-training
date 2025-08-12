package com.springboot_practice.logging_formdata_demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot_practice.logging_formdata_demo.models.Survey;

@Controller
public class SurveyController {
	
	@RequestMapping(value="/survey", method = RequestMethod.GET)
	public String handleSurveySubmit(ModelAndView model) {
		return "survey.html";
	}
	
	@RequestMapping(value="/saveSurveyDetails", method = RequestMethod.POST)
	public ModelAndView handleSurveySubmit(Survey survey) {
		System.out.println(survey);
		return new ModelAndView("redirect:/survey");
	}
}
