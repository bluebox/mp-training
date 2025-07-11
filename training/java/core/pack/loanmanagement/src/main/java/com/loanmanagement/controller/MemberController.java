package com.loanmanagement.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loanmanagement.Exceptions.InvalidIdException;
import com.loanmanagement.config.CreditScoreConfig;
import com.loanmanagement.model.LoanRequestDTO;
import com.loanmanagement.model.Member;
import com.loanmanagement.service.MemberService;
import com.loanmanagement.util.UtilityMethods;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private  MemberService memberServices;
	
	
	
	@PostMapping("/create-customer")
	public ResponseEntity<String> addMember(@RequestBody Member member)throws Exception {
		
		if (member.getName().trim().length()==0 ||member.getName().trim() == null || !Pattern.matches("^[A-Za-z\\s]+$", member.getName())) {
            return ResponseEntity.badRequest().body("Invalid name. It must contain only letters and spaces.");
        }

        if (member.getEmail() == null || !Pattern.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", member.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email format.");
        }

        if (member.getMobile() == null||member.getMobile().startsWith("0") || !Pattern.matches("^[0-9]{10}$", member.getMobile())) {
            return ResponseEntity.badRequest().body("Invalid mobile number. It must be exactly 10 digits.");
        }

        if (member.getAddress() == null || member.getAddress().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Address cannot be empty.");
        }
	    member.setCreditScore(UtilityMethods.getCreditScore());
		memberServices.addMember(member);
		return ResponseEntity.ok("New customer added");
	}
	
	@GetMapping("/{id}")
	public Member getMemberById(@PathVariable int id ) throws Exception{
		if(id<=0)
		throw new  InvalidIdException("Member id should not be 0 or negative");
		return memberServices.getMemberById(id);
		
	}
	@GetMapping
	public List<Member> getAllMembers() throws Exception{
		return memberServices.getAllMembers();
	}
	/*
	
//     @GetMapping("loans/{id}")
//		public LoanRequestDTO getLoanByMemberId(@PathVariable("id")int id){
//		return memberServices.getLoanByMemberId(id);*/
	
	}

