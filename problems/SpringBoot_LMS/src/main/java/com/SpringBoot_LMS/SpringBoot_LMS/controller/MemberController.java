package com.SpringBoot_LMS.SpringBoot_LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Book;
import com.SpringBoot_LMS.SpringBoot_LMS.model.Member;
import com.SpringBoot_LMS.SpringBoot_LMS.service.BookService;
import com.SpringBoot_LMS.SpringBoot_LMS.service.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/Member")
public class MemberController {

	@Autowired
	private MemberService service;
	

	@GetMapping("/viewMembers")
	public ResponseEntity<List<Member>> viewBooks() throws Exception {
		    List<Member> members=service.getMembers();
		    if(members==null) {
		    	return ResponseEntity.badRequest().body(members);
		    }
	        return ResponseEntity.ok().body(members);
	}
	
	@GetMapping("/getMember/{id}")
	public ResponseEntity<String> viewBook(@PathVariable int id) throws Exception {
         Member member=service.getMemberById(id);
         System.out.println(member.toString());
         if(member!=null) {
        	 return ResponseEntity.ok().body(member.toString());
         }
         return ResponseEntity.badRequest().body("Error while fetching the Member");
}
	
	
	@RequestMapping(value="/addMember",method=RequestMethod.POST)
	public ResponseEntity<String> addBook(@Valid @RequestBody Member member) throws Exception{
		System.out.println(member);
		int value=service.AddMember(member);
		if(value>0) {
			return ResponseEntity.ok().body("member created and added to DB successfully");	
		}
		return ResponseEntity.badRequest().body("Error while adding the member");
	}
	
	@RequestMapping(value="/updateMember",method=RequestMethod.POST)
	public ResponseEntity<String> updateBook(@Valid @RequestBody Member member) throws Exception{
		System.out.println(member);
		Member value=service.updateMember(member);
		if(value!=null) {
			return ResponseEntity.ok().body("member updated to DB successfully"+value);	
		}
		return ResponseEntity.badRequest().body("Error while updating the member");
	}
	
	
	
}
