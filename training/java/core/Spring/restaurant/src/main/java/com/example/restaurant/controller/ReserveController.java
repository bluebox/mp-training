package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReserveController {
	
	@RequestMapping(value="reserve")
	public String reserve() {
		return "ReserveSeat";
	}
}
