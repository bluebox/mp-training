package com.example.Backend.controllersImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend.domain.Member;
import com.example.Backend.serviceImplementation.MemberServiceImplementation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/members")

public class MemberController {

	@Autowired
	private MemberServiceImplementation memberService;

	@PostMapping
	public ResponseEntity<?> addMember(@Valid @RequestBody Member member) {
		memberService.addMember(member);
		return ResponseEntity.ok(Map.of("status", "success", "message", "member added successfully"));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateMember(@PathVariable int id, @Valid @RequestBody Member member) {
		memberService.updateMember(id, member);
		return ResponseEntity.ok(Map.of("status", "success", "message", "Member updated successfully"));
	}

	@GetMapping("/{id}")
	public Member getMemberById(@PathVariable int id) {
		return memberService.getMemberById(id);
	}

	@GetMapping
	public List<Member> getAllMembers() {
		return memberService.getAllMembers();
	}

}
