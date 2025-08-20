package com.lms.springbootlms.controllers;


import com.lms.springbootlms.exception.InvalidInputException;
import com.lms.springbootlms.exception.ServiceException;
import com.lms.springbootlms.model.Member;
import com.lms.springbootlms.service.MemberService;
import com.lms.springbootlms.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<String> addMember(@RequestBody Member member) {
        try {
            memberService.addMember(member);
            return ResponseEntity.ok("Member added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(e.getMessage());
        }
    }

    @GetMapping("/mobile/{mobile}")
    public ResponseEntity<?> getMemberByMobile(@PathVariable String mobile) {
        try {
            Member member = memberService.getMemberByMobile(mobile);
            if (member == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Member not found with this mobile number.");
            }
            return ResponseEntity.ok(member);
        } catch (ServiceException | InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> updateMember(
            @PathVariable int memberId,
            @RequestBody Member member) {
        try {
            member.setMemberId(memberId);
            boolean updated = memberService.updateMember(member);

            if (updated) {
                return ResponseEntity.ok("Member updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Update failed. Member not found.");
            }
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllMembers() {
        try {
            List<Member> members = memberService.getAllMembers();
            return ResponseEntity.ok(members);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch members: " + e.getMessage());
        }
    }
}
