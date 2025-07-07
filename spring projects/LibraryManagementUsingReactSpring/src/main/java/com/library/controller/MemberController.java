package com.library.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.domain.Member;
import com.library.service.MemberService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/add")
	public ResponseEntity<Map<String , String>> addMember(@Valid @RequestBody Member member){
		Map<String , String> response = new HashMap<>();
		
		if(memberService.addMember(member)) {
			response.put("status", "200");
			response.put("message", "member created successfully");
			return ResponseEntity.ok(response);
			
		}
		response.put("status", "404");
		response.put("message", "member not created");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> listMembers(@RequestParam(value="id" , required = false) Integer id){
		
		if(id!=null) {
			Member member = memberService.getMemberById(id);
			if(member!=null) {
				return ResponseEntity.ok(member);
			}
		}
		
		List<Member> members = memberService.getAllMembers();
		Map<String , String > response = new HashMap<>();
		
		if(members!=null && !members.isEmpty()) {
			response.put("status", "200");
			return ResponseEntity.ok(members);
		}
		
		return ResponseEntity.ok(new ArrayList<>());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateMmeberById(@PathVariable int id , @RequestBody Member member){
		member.setMemberId(id);
		Map<String , String> response = new HashMap<>();
		if(memberService.updateMember(member)) {
			response.put("status", "200");
			response.put("message", "member updated successfully");
			
			return ResponseEntity.ok(response);
		}
		response.put("status", "404");
		response.put("message", "member id not found or member updation failed");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}
