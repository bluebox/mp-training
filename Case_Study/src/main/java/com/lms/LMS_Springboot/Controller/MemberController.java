package com.lms.LMS_Springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.Member;
import com.lms.LMS_Springboot.Service.BookService;
import com.lms.LMS_Springboot.Service.MemberService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/Member")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class MemberController {
	@Autowired 
	MemberService memberservice;
	@RequestMapping(value="/addmember",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addmembercontroller(@Valid @RequestBody Member member) {
		
		System.out.println("member"+member);
		if(memberservice.addmember(member)!=0) {
			return ResponseEntity.ok("Member added successfully");
		}
		else {
			//return new ResponseEntity<>("book not added",HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.badRequest().body("Member not added");
		}
	}
	
	@RequestMapping(value="/updatemember",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> updatemembercontroller(@Valid @RequestBody Member member) {
		
		System.out.println("member"+member);
		if(memberservice.updatemember(member)!=0) {
			return ResponseEntity.ok("Member updated Successfully");
		}
		else {
			//return new ResponseEntity<>("book not added",HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.badRequest().body("Member not updated");
		}
	}
	
	@RequestMapping(value="/viewmembers",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Member>> viewallmembercontroller() {
		return  ResponseEntity.ok(memberservice.viewallmembers());
		//return ""+bookdao.viewallBooks();
	}
	
	@RequestMapping(value="/viewmembers/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> viewmemberwithidcontroller(@Valid @PathVariable int id) {
		
		
		Member member=memberservice.getbyid(id);
		if(member!=null) {
			return ResponseEntity.ok().body(member.toString());
		}
		else {
			//return new ResponseEntity<>("book not added",HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.badRequest().body("member not fetched");
		}
		//return ""+bookdao.viewallBooks();
	}
	
}
