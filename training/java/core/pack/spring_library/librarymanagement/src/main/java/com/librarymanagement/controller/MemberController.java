package com.librarymanagement.controller;

import com.librarymanagement.aspects.Duration;
import com.librarymanagement.exceptions.GeneralException;
import com.librarymanagement.exceptions.NoMemberException;
import com.librarymanagement.model.Member;
import com.librarymanagement.service.MemberService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

  
    @PostMapping
    @Duration
    public String addMember( @Valid @RequestBody Member member,Errors er) throws Exception {
    	if(er.hasErrors())
    		throw new  GeneralException("invalid data");
        memberService.addMember(member);
        return "Member added successfully.";
    }
   // @Duration
    @PutMapping("/{memberId}")
    public String updateMember(
            @PathVariable int memberId,
            @RequestBody Member memberRequest) throws Exception {

        // Fetch existing member
        Member existingMember = memberService.getMemberById(memberId);
        if (existingMember == null) {
            throw new NoMemberException("No member found");
        }

        // Update fields
        existingMember.setName(memberRequest.getName());
        existingMember.setEmail(memberRequest.getEmail());
        existingMember.setMobile(memberRequest.getMobile());
        existingMember.setGender(memberRequest.getGender());
        existingMember.setAddress(memberRequest.getAddress());

        // Save
        memberService.updateMember(existingMember);

        return "Member updated successfully";
    }


   
    @GetMapping
    public List<Member> getAllMembers() throws Exception {
        return memberService.getAllMembers();
    }

   
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable("id") int memberId) throws Exception {
        return memberService.getMemberById(memberId);
    }
}
