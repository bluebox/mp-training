package com.example.lms.controller;

import com.example.lms.model.Member;
import com.example.lms.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public String addMember(@RequestBody Member member) {
        int rows = memberService.addMember(member);
        return rows > 0 ? "Member added successfully" : "Failed to add member";
    }

    @PutMapping("/{id}")
    public String updateMember(@PathVariable Long id, @RequestBody Member member) {
        member.setMemberId(id); 
        int rows = memberService.updateMember(member);
        return rows > 0 ? "Member updated successfully" : "Member not found";
    }

    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable Long id) {
        int rows = memberService.deleteMember(id);
        return rows > 0 ? "Member deleted successfully" : "Member not found";
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
}
