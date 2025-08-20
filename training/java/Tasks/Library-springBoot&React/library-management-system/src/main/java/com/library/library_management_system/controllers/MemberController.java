package com.library.library_management_system.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.library_management_system.domain.Member;
import com.library.library_management_system.services.MemberServiceInterface;

@RestController
@RequestMapping("/library/members")
public class MemberController {

	private final MemberServiceInterface memberService;

	public MemberController(MemberServiceInterface memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/add")
	public ResponseEntity<Member> addMember(@Valid @RequestBody Member memberInput) {
		Member member = new Member(memberInput.getMemberName(), memberInput.getMemberMail(), memberInput.getMobileNo(),
				memberInput.getGender(), memberInput.getMemberAddress());
		return ResponseEntity.ok(memberService.addMember(member));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable int id, @RequestBody Member memberInput) {
		Member updatedMember = new Member(memberInput.getMemberName(), memberInput.getMemberMail(),
				memberInput.getMobileNo(), memberInput.getGender(), memberInput.getMemberAddress());
		return ResponseEntity.ok(memberService.updateMember(id, updatedMember));
	}

	@GetMapping("/allMembers")
	public ResponseEntity<List<Member>> getAllMembers() {
		List<Member> members = memberService.getAllMembers();
		return ResponseEntity.ok(members);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable int id) {
		Member members = memberService.getMemberById(id);
		return ResponseEntity.ok(members);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable int id) {
		memberService.deleteMember(id);
		return ResponseEntity.ok("Member with ID " + id + " deleted successfully");
	}
}
