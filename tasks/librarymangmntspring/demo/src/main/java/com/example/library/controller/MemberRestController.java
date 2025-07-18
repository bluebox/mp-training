package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.ApiResponse;
import com.example.library.model.Member;
import com.example.library.model.MemberIssueDTO;
import com.example.library.service.MemberService;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(
	    origins = "http://localhost:5173",
	    allowCredentials = "true"
	)
public class MemberRestController {

	private final MemberService memberService;

	@Autowired
	public MemberRestController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("")
	public ResponseEntity<ApiResponse<List<Member>>> viewMembers() {
		try {
			List<Member> members = memberService.getAllMembers();
			return ResponseEntity.ok(new ApiResponse<>(true, "Members fetched successfully", members));
		} catch (Exception e) {
			return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(false, "Failed to fetch members: " + e.getMessage(), null));
		}
	}

	@PostMapping("/add")
	public ResponseEntity<ApiResponse<Member>> addMember(@RequestBody Member member) {
		try {
			memberService.addMember(member);
			return ResponseEntity.ok(new ApiResponse<>(true, "Member added successfully", member));
		} catch (Exception e) {
			return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(false, "Failed to add member: " + e.getMessage(), null));
		}
	}

	@PostMapping("/update")
	public ResponseEntity<ApiResponse<Member>> updateMember(@RequestBody Member member) {
		try {
			memberService.updateMember(member);
			return ResponseEntity.ok(new ApiResponse<>(true, "Member updated successfully", member));
		} catch (Exception e) {
			return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(false, "Failed to update member: " + e.getMessage(), null));
		}
	}

	@GetMapping("/with-active-books")
	public ResponseEntity<ApiResponse<List<MemberIssueDTO>>> membersWithBooks() {
		try {
			List<MemberIssueDTO> members = memberService.getMembersWithActiveIssues();
			return ResponseEntity.ok(new ApiResponse<>(true, "Members with active books fetched", members));
		} catch (Exception e) {
			return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR).body(
					new ApiResponse<>(false, "Failed to fetch members with active books: " + e.getMessage(), null));
		}
	}
}
