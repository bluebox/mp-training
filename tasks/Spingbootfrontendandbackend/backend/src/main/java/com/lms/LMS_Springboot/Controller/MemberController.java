package com.lms.LMS_Springboot.Controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMS_Springboot.DAO.MemberDAO;
import com.lms.LMS_Springboot.Model.Issue_records;
import com.lms.LMS_Springboot.Model.Member;
import com.lms.LMS_Springboot.Service.MemberService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("/members")

@RestController
public class MemberController {
	@Autowired
	MemberService memberservice;
	@GetMapping("/viewallmembers")
	public ResponseEntity<List<Member>> getAllMembers() throws SQLException {
		return ResponseEntity.ok(memberservice.getAllMembers());
		
	}
	@PostMapping("/addmember")

	public ResponseEntity<String> addmember(@Valid @RequestBody Member member){
	if(memberservice.addMember(member)) {
        return ResponseEntity.ok("member added successfully!");

	}
	else {
		return ResponseEntity.ok("member not addedd");
	}
		
	}
	
	@PostMapping("/updatemember/{memberid}")
	public ResponseEntity<String> UPDATEmember(@Valid @RequestBody Member member,@PathVariable int memberid) throws SQLException{
		if(memberservice.updateMember(member, memberid)) {
	        return ResponseEntity.ok("member updated!");

		}
		else {
			return ResponseEntity.ok("member failed to updated");
		}
			
		}
	@PostMapping("/memberbyid/{memberid}")
	public ResponseEntity<String> membergetbyid(@PathVariable int memberid) throws SQLException{
		if(memberservice.getMemberById(memberid) != null) {
	        return ResponseEntity.ok("member is available!");

		}
		else {
			return ResponseEntity.ok("member not avaialable");
		}
			
		}
	

}
