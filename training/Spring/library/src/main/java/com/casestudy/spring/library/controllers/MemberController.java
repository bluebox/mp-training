package com.casestudy.spring.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.casestudy.spring.library.beans.Member;
import com.casestudy.spring.library.impl.Implementation;

import jakarta.validation.Valid;

@Controller
public class MemberController {

	Implementation impl;

	@Autowired
	public MemberController(Implementation impl) {
		this.impl = impl;
	}

	@GetMapping("/AddMember")
	public String showMemberForm(Model model) {
		model.addAttribute("member", new Member());
		return "Member/AddMember";
	}

	@PostMapping("/SaveMember")
	public String addMember(@Valid @ModelAttribute Member member, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("member", member);
			return "Member/AddMember";
		}
		boolean flag = impl.addMemberService(member);
		if (flag == true) {
			model.addAttribute("message", "Member added successfully!");
			model.addAttribute("targetUrl", "/AddMember");
			model.addAttribute("buttonLabel", "Go to AddMember");
			return "Response";
		} else {
			model.addAttribute("message", "Error Occured!");
			model.addAttribute("targetUrl", "/MainMenu");
			model.addAttribute("buttonLabel", "Go to Main Menu");
			return "Response";
		}

	}

	@GetMapping("/UpdateMember")
	public String showEmptyUpdateForm(Model model) {
		return "Member/SearchMember";
	}

	@GetMapping("/UpdateMemberPopulate")
	public String showMemberUpdateForm(@RequestParam("id") int id, Model model) {
		Member member = impl.getMemberById(id);
		if (member != null) {
			model.addAttribute("member", member);
			return "Member/UpdateMember";
		} else {
			model.addAttribute("message", "Member not found!");
			model.addAttribute("targetUrl", "/UpdateMember");
			model.addAttribute("buttonLabel", "Try Again");
			return "Response";
		}
	}

	@PostMapping("/UpdateSaveMember")
	public String updateBook(@Valid @ModelAttribute("member") Member member, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("member", member);
			return "Member/UpdateMember";
		}
		boolean flag = impl.updateMemberService(member);
		if (flag == true) {
			model.addAttribute("message", "Member Updated successfully!");
			model.addAttribute("targetUrl", "/Members");
			model.addAttribute("buttonLabel", "Go to Members");
			return "Response";
		} else {
			model.addAttribute("message", "Error Occured!");
			model.addAttribute("targetUrl", "/MainMenu");
			model.addAttribute("buttonLabel", "Go to Main Menu");
			return "Response";
		}
	}

	@GetMapping("/ViewAllMembers")
	public String showAllBooks(Model model) {
		model.addAttribute("members", impl.getAllMembersService());
		return "Member/ViewAllMembers";
	}
}
