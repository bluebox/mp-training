package com.example.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.model.Member;
import com.example.web.service.MemberService;

@RestController
@CrossOrigin("*")
public class MemberController {
	@Autowired
	private MemberService memberService;
	private Member member;
	
	@PostMapping("/addmember")
	public ResponseEntity<String> addMember(@RequestBody Member member){
		System.out.println(member.toString());
		memberService.addNewMember(member);
		return ResponseEntity.ok("Member created successfully!");
	}
	
	@GetMapping("/getmembers")
	public ResponseEntity<List<Member>> getMembers(){
		List<Member> members=memberService.getAllMembers();
		return ResponseEntity.ok(members);
	}
	
	@PostMapping("/getmember/{id}")
	public ResponseEntity<Member> getMember(@PathVariable("id") int id) {
		member = memberService.getMemberById(id);
		return ResponseEntity.ok(member);
	}
	
	@PutMapping("/updatemember")
	public ResponseEntity<String> updateMember(@RequestBody Member member){
		memberService.updateMember(member);
		return ResponseEntity.ok("Member updated successfully!");
	}
}
