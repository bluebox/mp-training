package com.eazybytes.controller;


import com.eazybytes.model.Member;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    // Show form
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("member", new Member());
        return "member-form";
    }

    // Handle form submit
    @PostMapping("/form")
    public String submitForm(
            @Valid @ModelAttribute("member") Member member,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "member-form";         }

        model.addAttribute("message", " Member is valid and saved!");
        return "member-success";
    }
}
