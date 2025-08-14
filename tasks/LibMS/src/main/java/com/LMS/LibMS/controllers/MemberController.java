package com.LMS.LibMS.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.LMS.LibMS.model.Member;
import com.LMS.LibMS.service.interfaces.MemberService;

@Controller
class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService= memberService;
	}
	
	@GetMapping("/members")
	@ResponseBody
	public List<Member> displayMembers(){
		return memberService.findMembers();
	}

}
