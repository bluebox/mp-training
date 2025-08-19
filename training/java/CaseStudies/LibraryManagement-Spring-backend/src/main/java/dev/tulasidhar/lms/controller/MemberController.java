package dev.tulasidhar.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.tulasidhar.lms.Exceptions.ValidationException;
import dev.tulasidhar.lms.model.Member;
import dev.tulasidhar.lms.service.MemberService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping(value = "/getallmembers", produces = "application/json")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping(value = "/addmember")
    public int addMember(@RequestBody @Valid Member member) throws ValidationException {
        memberService.addNewMember(member);
        return 0;
    }

    @PutMapping("/updatemember")
    public int updateMember(@RequestBody @Valid Member member) throws ValidationException {
        memberService.updateMember(member);
        return 0;
    }
}