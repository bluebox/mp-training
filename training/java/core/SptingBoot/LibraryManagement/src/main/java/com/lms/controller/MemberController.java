package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Member;
import com.lms.service.interfaces.implementation.MemberServiceImplementation;

@RestController
@RequestMapping("/members")
@CrossOrigin("http://localhost:5173/")
public class MemberController {
	
	private final MemberServiceImplementation memberService;
	
	@Autowired
	public MemberController(MemberServiceImplementation memberService) {
		this.memberService=memberService;
	}
	
	@GetMapping
    public ResponseEntity<List<Member>> all() {
        return new ResponseEntity<>(memberService.getAllMembers(), HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<Member> newMember(@RequestBody Member member) {
        return new ResponseEntity<>(memberService.registerMember(member), HttpStatus.CREATED);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Member> one(@PathVariable int id) {
        return memberService.fetchMemberById(id)
            .map(member -> new ResponseEntity<>(member, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@RequestBody Member newMember, @PathVariable int id) {
        if (memberService.fetchMemberById(id).isPresent()) {
            newMember.setMemberId(id);
            Member updatedMember = memberService.updateMember(newMember);
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }
}
