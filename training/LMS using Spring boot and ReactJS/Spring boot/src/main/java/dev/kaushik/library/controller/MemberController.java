package dev.kaushik.library.controller;

import dev.kaushik.library.model.Member;
import dev.kaushik.library.service.MemberService;
import dev.kaushik.library.validator.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/addMember")
    public ResponseEntity<Integer> addMember(@RequestBody Member member) {
    	MemberValidator.validate(member);
        Integer memberId = memberService.addMember(member);
        return new ResponseEntity<>(memberId, HttpStatus.CREATED);
    }

    @PutMapping("/updateMember")
    public ResponseEntity<Boolean> updateMember(@RequestBody Member member) {
    	MemberValidator.validate(member);
        boolean updated = memberService.updateMember(member);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMember/{memberId}")
    public ResponseEntity<Boolean> deleteMember(@PathVariable Integer memberId) {
        boolean deleted = memberService.deleteMember(memberId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @PostMapping("/batchDeleteMembers")
    public ResponseEntity<Integer> deleteMembersBatch(@RequestBody List<Integer> memberIds) {
        Integer deletedCount = memberService.deleteMembers(memberIds);
        return new ResponseEntity<>(deletedCount, HttpStatus.OK);
    }

    @GetMapping("/viewMembers")
    public ResponseEntity<List<Member>> viewMembers() {
        List<Member> members = memberService.findMembers(null);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }
    
    @PostMapping("/viewMembers")
    public ResponseEntity<List<Member>> viewMembersPost(@RequestBody(required = false) Member criteria) {
        List<Member> members = memberService.findMembers(criteria);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }
}