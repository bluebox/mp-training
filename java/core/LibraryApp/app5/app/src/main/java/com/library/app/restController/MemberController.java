package com.library.app.restController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.library.app.model.Book;
import com.library.app.model.Member;
import com.library.app.model.Response;
import com.library.app.service.LibraryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RestController
@RequestMapping("/api/member")
//@CrossOrigin(origins="*")
@CrossOrigin(origins="http://localhost:3000/")
public class MemberController {
    

    @Autowired
    private LibraryService libraryService;

    
	@PostMapping(value="/addMember")
	public ResponseEntity<Response> addMemberPage(@RequestHeader("invocationFrom") String invocationFrom,@RequestBody Member member) {
	    
			Response response = new Response();
	        log.info(String.format("Header invocationFrom = %s", invocationFrom));
	        try {
				libraryService.addMember(member);
				response.setStatusCode("200");
				response.setStatusMsg("Member added successfully!");
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.header("isMemberAdded", "true")
						.body(response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        response.setStatusCode("400");
            response.setStatusMsg("Member not added!");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
	}


	@PutMapping(value="/updateMember")
	public ResponseEntity<Response> updateMemberPage(@RequestHeader("invocationFrom") String invocationFrom,@RequestBody Member member) {
	    
		Response response = new Response();
	        log.info(String.format("Header invocationFrom = %s", invocationFrom));
	        try {
	        	Member isPresentMember = libraryService.getMemberById(member.getMemberId());
	        	log.info("The Member ID is"+isPresentMember.getMemberId());
	        	if(isPresentMember !=null && isPresentMember.getMemberId() == member.getMemberId()) {
				libraryService.updateMember(member);
				response.setStatusCode("200");
				response.setStatusMsg("Member Details updated successfully!");
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.header("isMemberUpdated", "true")
						.body(response);
	        	}
			} 
	        catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        response.setStatusCode("400");
            response.setStatusMsg("No Member found on this Member ID!");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
		
	}
	

    @GetMapping(value="/members")
    public List<Member> viewAllMembersPage() throws Exception {
            List<Member> members = libraryService.viewAllMembers();
            log.info("All Members: " + members);
            return members;
    }

}


