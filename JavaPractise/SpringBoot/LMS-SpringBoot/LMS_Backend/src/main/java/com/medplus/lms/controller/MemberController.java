package com.medplus.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.medplus.lms.domain.Member;
import com.medplus.lms.service.MemberService;
import com.medplus.lms.service.MemberServiceInterface;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/members")
@Validated
public class MemberController {

	private final MemberServiceInterface service;

	public MemberController(MemberService service) {
		this.service = service;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerMember(@Valid @RequestBody Member member) {
		String msg=service.registerMember(member);
		return ResponseEntity.status(HttpStatus.CREATED).body(msg);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateMember(@Valid @RequestBody Member member) {
		System.out.println(member);
		service.updateMember(member);
		return ResponseEntity.ok("Member updated successfully!");
	}

	@GetMapping("/all")
	public ResponseEntity<List<Member>> getAllMembers() {
		List<Member> members = service.getAllMembers();
		return ResponseEntity.ok(members);
	}

	@PutMapping("/deletemember/{memberId}")
	public ResponseEntity<String> deleteMember(@PathVariable int memberId) {
		service.deleteMember(memberId);
		return ResponseEntity.ok("Member inactivated successfully");
	}
	@GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable int id) {
        Member member = service.getMemberById(id);
        return ResponseEntity.ok(member);
    }
}
