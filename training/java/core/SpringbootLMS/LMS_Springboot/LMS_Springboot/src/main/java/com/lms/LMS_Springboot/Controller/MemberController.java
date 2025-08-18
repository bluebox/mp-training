package com.lms.LMS_Springboot.Controller;

import com.lms.LMS_Springboot.Model.Member;
import com.lms.LMS_Springboot.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable int id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    public String addMember(@RequestBody Member member) {
        int rows = memberService.addMember(member);
        return rows > 0 ? "Member added successfully" : "Failed to add member";
    }

    @PutMapping("/{id}")
    public String updateMember(@PathVariable int id, @RequestBody Member member) {
        int rows = memberService.updateMember(id, member);
        return rows > 0 ? "Member updated successfully" : "Failed to update member";
    }
}
