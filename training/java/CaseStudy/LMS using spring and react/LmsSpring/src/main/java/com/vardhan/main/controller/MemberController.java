package com.vardhan.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vardhan.main.model.Member;
import com.vardhan.main.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/members")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse<Member>> saveMember(@RequestBody Member member) {
        Member saved = memberService.saveMember(member);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(saved, "Member created"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Member>> getMember(@PathVariable Integer id) {
        Optional<Member> opt = memberService.findMemberById(id);
        return opt.map(m -> ResponseEntity.ok(ApiResponse.success(m, "Member found")))
                  .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                 .body(ApiResponse.error("Member not found", "")));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Member>>> getAllMembers() {
        List<Member> list = memberService.getAllMembers();
        return ResponseEntity.ok(ApiResponse.success(list, "All members"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Member>> updateMember(@PathVariable Integer id, @RequestBody Member member) {
        member.setMemberId(id);
        Member updated = memberService.updateMember(member);
        return ResponseEntity.ok(ApiResponse.success(updated, "Member updated"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMember(@PathVariable Integer id) {
        boolean ok = memberService.deleteMember(id);        
        return ok
                ? ResponseEntity.ok(ApiResponse.success(null, "Member deleted (soft)"))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(ApiResponse.error("Member not found", ""));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateMember(@PathVariable Integer id) {
        boolean ok = memberService.deactivateMember(id);
        return ok
                ? ResponseEntity.ok(ApiResponse.success(null, "Member deactivated"))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(ApiResponse.error("Member not found or already deactivated", ""));
    }

    @PutMapping("/{id}/reactivate")
    public ResponseEntity<ApiResponse<Void>> reactivateMember(@PathVariable Integer id) {
        boolean ok = memberService.reactivateMember(id);
        return ok
                ? ResponseEntity.ok(ApiResponse.success(null, "Member reactivated"))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(ApiResponse.error("Member not found or already active", ""));
    }


    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<Member>>> getActiveMembers() {
        List<Member> list = memberService.getActiveMembers();
        return ResponseEntity.ok(ApiResponse.success(list, "Active members"));
    }

    @GetMapping("/stats/count")
    public ResponseEntity<ApiResponse<Long>> totalCount() {
        long count = memberService.getTotalMembersCount();
        return ResponseEntity.ok(ApiResponse.success(count, "Total members (excluding deleted)"));
    }

    @GetMapping("/stats/active-count")
    public ResponseEntity<ApiResponse<Long>> activeCount() {
        long count = memberService.getActiveMembersCount();
        return ResponseEntity.ok(ApiResponse.success(count, "Active members count"));
    }
}
