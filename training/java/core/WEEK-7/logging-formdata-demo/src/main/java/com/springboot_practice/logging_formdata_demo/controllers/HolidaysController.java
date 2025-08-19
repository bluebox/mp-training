package com.springboot_practice.logging_formdata_demo.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot_practice.logging_formdata_demo.models.Holiday;

@Controller
public class HolidaysController {
	
	@RequestMapping("/holidays/{filter}")
	public String displayHolidays(@PathVariable String filter, Model model) {
		List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1 ","New Year's Day", Holiday.Type.FESTIVAL),
                new Holiday(" Oct 31 ","Halloween", Holiday.Type.FESTIVAL),
                new Holiday(" Nov 24 ","Thanksgiving Day", Holiday.Type.FESTIVAL),
                new Holiday(" Dec 25 ","Christmas", Holiday.Type.FESTIVAL),
                new Holiday(" Jan 17 ","Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
                new Holiday(" July 4 ","Independence Day", Holiday.Type.FEDERAL),
                new Holiday(" Sep 5 ","Labor Day", Holiday.Type.FEDERAL),
                new Holiday(" Nov 11 ","Veterans Day", Holiday.Type.FEDERAL)
        );
		
		List<Holiday> filteredHolidays=null;
		
		if (filter.equals("all")) {
			filteredHolidays=holidays;
			model.addAttribute("filteredHolidays", filteredHolidays);
		}else if(filter.equals("festival")) {
			filteredHolidays=holidays.stream().filter(holiday -> holiday.getType()==Holiday.Type.FESTIVAL).collect(Collectors.toList());
		}
		else if (filter.equals("federal")) {
			filteredHolidays=holidays.stream().filter(holiday -> holiday.getType()==Holiday.Type.FEDERAL).collect(Collectors.toList());
		}
		
		model.addAttribute("filteredHolidays",filteredHolidays);
		return "holidays";
		
	}
	
}
