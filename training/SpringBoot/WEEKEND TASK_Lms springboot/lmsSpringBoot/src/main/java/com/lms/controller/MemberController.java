package com.lms.controller;

import com.lms.model.Member;
import com.lms.serviceImpl.MemberServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor

public class MemberController {

    private final MemberServiceImpl memberService;
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() throws Exception {
        log.info("Fetching all members");
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/mobile/{mobile}")
    public ResponseEntity<?> getByMobile(@PathVariable String mobile) {
        log.info("Fetching member with mobile {}", mobile);
        try {
            Member member = memberService.getMemberByMobile(mobile);
            if (member == null) {
                return ResponseEntity.status(404)
                        .body("Member with mobile " + mobile + " not found.");
            }
            return ResponseEntity.ok(member);
        } catch (Exception e) {
            log.error("Error fetching member by mobile: {}", e.getMessage(), e);
            return ResponseEntity.status(500)
                    .body("Unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addMember(@Valid @RequestBody Member member, BindingResult result) {
        if (result.hasErrors()) {
            String firstError = result.getFieldErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(firstError);
        }

        try {
            log.info("Adding new member {}", member.getName());
            memberService.addMember(member);
            return ResponseEntity.ok("Member added successfully");
        } catch (Exception e) {
            log.error("Error adding member: {}", e.getMessage(), e);
            return ResponseEntity.status(500)
                    .body("Invalid Request:" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable int id, @Valid @RequestBody Member member,BindingResult result) {
        
    	 if (result.hasErrors()) {
             String firstError = result.getFieldErrors().get(0).getDefaultMessage();
             return ResponseEntity.badRequest().body(firstError);
         }

    	
    	try {
            member.setMemberId(id);
            log.info("Updating member with ID {}", id);
           memberService.updateMember(member);
            return ResponseEntity.ok("Member updated successfully!");
        }          catch (Exception e) {
            return ResponseEntity.status(500).body("Invalid request: " + e.getMessage());
        }
    }
}
