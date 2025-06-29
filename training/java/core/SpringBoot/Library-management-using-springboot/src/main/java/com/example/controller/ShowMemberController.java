package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Member;
import com.example.service.MemberService;

@Controller
public class ShowMemberController {
	@Autowired
	private MemberService ms;
	@RequestMapping("/showMember")
	public String showMember(Model m) {
		m.addAttribute("l", ms.showMembers());
		return "showMember.html";
	}
	@RequestMapping("/addMember")
	public String addMember() {
		return "addMember.html";
	}
	@RequestMapping("/addMemberData")
	public String addMemberData(Model m,@RequestParam(required = false) int memberId,@RequestParam(required = false) String name,@RequestParam(required = false) String email,@RequestParam(required = false) long mobile,@RequestParam(required = false) char gender,@RequestParam(required = false) String address) {
		String x = ms.addMember(new Member(memberId, name, email, mobile, gender, address));
		if(x.equals("inserted")) {
			return showMember(m);
		}
		else {
			return "addMember.html";
		}
	}
	@RequestMapping("/updateMember")
	public String updateMember(Model m,@RequestParam(required = false) int memberId) {
		m.addAttribute("memberId",memberId);
		return "updateMember.html";
	}
	@RequestMapping("/updateMemberData")
	public String updateData(Model m,@RequestParam(required = false) int memberId,@RequestParam(required = false) String name,@RequestParam(required = false) String email,@RequestParam(required = false) long mobile,@RequestParam(required = false) char gender,@RequestParam(required = false) String address) {
		if(ms.update(memberId, name, email, mobile, gender, address).equals("updated")) {
			System.out.println("updated");
			return showMember(m);
		}
		return "addmember.html";
	}
	@RequestMapping("/deleteMember")
	public String deleteMember(Model m,int memberId) {
		if(ms.delete(memberId).equals("deleted")) {
			return showMember(m);
		}
		else {
			return "showMember.html";
		}
	}
}
