package com.LMS.LibMS.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.LibMS.model.Member;
import com.LMS.LibMS.model.enums.Gender;
import com.LMS.LibMS.service.interfaces.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {


	    private final MemberService memberService;

	    @Autowired
	    public MemberController(MemberService memberService) {
	        this.memberService = memberService;
	    }

	    @PostMapping("/add")
	    public void addMember(@Valid @RequestBody Member member) throws Exception {
	           memberService.addMember(member);
	    }

	    @GetMapping("/getmembers")
	    public List<Member> displayMembers() {
	        return memberService.getAllMembers();
	    }

	    @GetMapping("/getmember/{memberId}")
	    public Member getMemberById(@PathVariable Integer memberId) {
	        return memberService.getMemberById(memberId);
	    }

	    @PutMapping("/update/{memberId}")
	    public boolean updateMember(@PathVariable Integer memberId, @Valid @RequestBody Member member) throws Exception {
	           member.setMemberID(memberId);
	          return memberService.updateMember(member);

	    }

	    @DeleteMapping("/delete/{memberId}")
	    public boolean deleteMember(@PathVariable Integer memberId) throws Exception {
	           return memberService.deleteMembersById(List.of(memberId));
	    }

	    @PostMapping("/deleteBatch")
	    public boolean deleteSelectedMembers(@RequestBody List<Integer> memberIds) throws Exception {
	          return  memberService.deleteMembersById(memberIds);
	    }
	    

	    @GetMapping("/genders")
	    @ResponseBody
	    public List<Map<String, String>> getGenders() {
	        return Arrays.stream(Gender.values())
	                .map(gender -> Map.of("displayName", gender.name(), "code", gender.name()))//String.valueOf(gender.getCode())))
	                .collect(Collectors.toList());
	    }
}