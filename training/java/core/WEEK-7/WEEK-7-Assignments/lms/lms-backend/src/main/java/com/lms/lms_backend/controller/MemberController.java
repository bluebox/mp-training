package com.lms.lms_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lms_backend.model.Member;
import com.lms.lms_backend.service.MemberService;
import com.lms.lms_backend.utilities.SuccessResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Object> addMember(@Valid @RequestBody Member member) {
        memberService.addMember(member);
        return SuccessResponse.build(HttpStatus.CREATED, "Member added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMember(@PathVariable int id, @Valid @RequestBody Member member) {
        memberService.updateMemberDetails(id, member);
        return SuccessResponse.build(HttpStatus.OK, "Member updated successfully");
    }

    @GetMapping
    public ResponseEntity<Object> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return SuccessResponse.build(HttpStatus.OK, "Members fetched successfully", members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMemberById(@PathVariable int id) {
        Member member = memberService.getMemberById(id);
        return SuccessResponse.build(HttpStatus.OK, "Member fetched successfully", member);
    }

    @GetMapping("/genders")
    public ResponseEntity<Object> getAllGenders() {
        List<String> genders = memberService.getAllGenders();
        return SuccessResponse.build(HttpStatus.OK, "Genders fetched successfully", genders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable int id) {
        memberService.deleteMember(id);
        return SuccessResponse.build(HttpStatus.OK, "Member deleted successfully");
    }
}
