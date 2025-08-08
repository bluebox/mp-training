package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.data.Member;
import com.example.demo.data.MemberService;

@Controller
public class MainController {

	@GetMapping("list")
	public String viewMembers(Model model) {
		List<Member> membersList = MemberService.getAllMembers();
		model.addAttribute("membersList", membersList);
		return "list.html";
	}
}
