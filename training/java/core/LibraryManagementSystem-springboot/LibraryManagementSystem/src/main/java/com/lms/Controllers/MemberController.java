package com.lms.Controllers;



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

import com.lms.Models.Member;
import com.lms.Services.Implementation.MemberServiceImplementation;



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
    public ResponseEntity<Integer> newMember(@RequestBody Member member) {
        return new ResponseEntity<>(memberService.registerMember(member), HttpStatus.CREATED);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Member> one(@PathVariable int id) throws Exception {
        if( memberService.fetchMemberById(id)!=null)
        {
        	return new ResponseEntity<>(memberService.fetchMemberById(id), HttpStatus.OK);
        }
//            .map(member -> new ResponseEntity<>(member, HttpStatus.OK))
//            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@PutMapping("/{id}")
    public String updateMember(@RequestBody Member newMember, @PathVariable int id) throws Exception {
        if (memberService.fetchMemberById(id)!=null) {
            newMember.setMemberId(id);
             memberService.updateMember(newMember);
//            return new ResponseEntity<>( HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
             return  "succes";
        }
        else {
        	return "fail";
        }
    }
}
