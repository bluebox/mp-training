package com.example.spring.controller;


import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

//    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
//    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
//                                   @RequestParam(value = "logout", required = false) String logout, Model model) {
//        String errorMessge = null;
//        if (error != null) {
//            errorMessge = "Username or Password is incorrect !!";
//        }
//        if (logout != null) {
//            errorMessge = "You have been successfully logged out !!";
//        }
//        model.addAttribute("errorMessge", errorMessge);
//        return "login.html";
//    }

}