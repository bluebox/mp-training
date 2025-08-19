package com.library.library_management_system.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library_management_system.domain.Member;
import com.library.library_management_system.response.CustomResponse;
import com.library.library_management_system.service.MemberService;
import com.library.library_management_system.utils.MemberGender;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/members")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping(value = "/addMember")
	public ResponseEntity<CustomResponse<Member>> addMember(@Valid @RequestBody Member member) {

		memberService.addMember(member);
		CustomResponse<Member> response = new CustomResponse<>(true, "Member Added successfully!", member);
		return ResponseEntity.ok(response);

	}

	@PostMapping(value = "/updateMember")
	public ResponseEntity<CustomResponse<Member>> updateMember(@Valid @RequestBody Member member) {

		Member oldMember = memberService.getMemberById(member.getId());

		Member newMember = memberService.updateMember(member, oldMember);

		CustomResponse<Member> response = new CustomResponse<>(true, "Member Updated successfully!", newMember);
		return ResponseEntity.ok(response);

	}

	@DeleteMapping("/deleteMember/{memberId}")
	public ResponseEntity<CustomResponse<Member>> updateDeleteBook(@PathVariable int memberId) {

		Member member = memberService.getMemberById(memberId);
		memberService.deleteMember(member);

		CustomResponse<Member> response = new CustomResponse<>(true, "Member deleted successfully!", member);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/getMembers")
	public ResponseEntity<CustomResponse<List<Member>>> getAllMembers() {

		List<Member> members = memberService.getMembers();
		CustomResponse<List<Member>> response = new CustomResponse<>(true, "Members Retrived successfully!", members);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/genderList")
	public ResponseEntity<CustomResponse<List<String>>> getCategories() {
		List<String> categories = Stream.of(MemberGender.values()).map(gender -> gender.getDisplayName()).toList();
		CustomResponse<List<String>> response = new CustomResponse<>(true, "Member Gender List Retrived successfully!",
				categories);
		return ResponseEntity.ok(response);

	}

}
