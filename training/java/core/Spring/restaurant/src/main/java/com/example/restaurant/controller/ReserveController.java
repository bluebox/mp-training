package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.restaurant.model.Reserve;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReserveController {

    @RequestMapping("/reserve")
    public String reserve(Model model) {
        model.addAttribute("reserve", new Reserve());
        return "ReserveSeat";
    }

    @PostMapping("/saveReserve")
    public String saveReserve(@Valid @ModelAttribute Reserve reserve, Errors errors) {
        if (errors.hasErrors()) {
            return "ReserveSeat";
        }

        String redirectUrl = String.format(
            "redirect:/reserve/details?name=%s&date=%s&time=%s",
            reserve.getName(),
            reserve.getDate(),
            reserve.getTime()
        );

        return redirectUrl;
    }
    
    @GetMapping("/reserve/details")
    public String showDetails(
            @RequestParam("name") String name,
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            Model model) {

        Reserve reserve = new Reserve();
        reserve.setName(name);
        reserve.setDate(date);
        reserve.setTime(time);

        model.addAttribute("reserve", reserve);
        return "ReserveDetails";
    }
}
