package com.library.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	@RequestMapping("/addMember")
	public String addMemberPage() {
		return "addMember";
	}
	
	@RequestMapping("/updateMember")
	public String updateMemberPage() {
		return "updateMember";
	}
	
	
	@RequestMapping("/members")
	public String viewAllMembersPage() {
		return "viewAllMembers";
	}
}
