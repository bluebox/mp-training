package com.app.springDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.springDemo.model.Member;
import com.app.springDemo.service.MemberService;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Controller
public class MemberController {
	
	Logger log =LoggerFactory.getLogger(MemberController.class.getName());
	
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService=memberService;
	}

	@RequestMapping("/addMember")
	public String addMemberPage() {
		return "addMember";
	}
	
//	@RequestMapping(value = "/saveMsg",method = POST)
//    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String email,
//    		@RequestParam long mobile) {
//        log.info("Name : " + name);
//        log.info("Email Address : " + email);
//        log.info("Mobile Number : " + mobile);
//        return new ModelAndView("redirect:/addMember");
//    }
	
	@RequestMapping(value = "/saveMsg",method = POST)
    public ModelAndView saveMessage(Member member){
        memberService.saveMemberDetails(member);
//        log.info(member.getName());
        return new ModelAndView("redirect:/addMember");
    }
	
	
}
