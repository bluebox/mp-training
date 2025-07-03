package com.spring.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.gym.beans.DateRangeRequest;
import com.spring.gym.beans.Member;
import com.spring.gym.impl.Impl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/members")
public class MemberConfig {

	private Impl impl;

	@Autowired
	public MemberConfig(Impl impl) {
		this.impl = impl;
	}

	@PostMapping(value = { "/add" })
	public ResponseEntity<?> addMember(@Valid @RequestBody Member member, Errors errors) {
		if (errors.hasErrors()) {
			Map<String, Object> errorsbody = new HashMap<>();
			errorsbody.put("Status", "Error");
			errorsbody.put("message", "Validation error");
			errorsbody.put("errors",
					errors.getAllErrors().stream().map(n -> n.getDefaultMessage()).collect(Collectors.toList()));
			return ResponseEntity.badRequest().body(errorsbody);
		}
		try {
			String str = impl.addMember(member);
			if ("success".equals(str)) {
				Map<String, String> successResponse = new HashMap<>();
				successResponse.put("status", "success");
				successResponse.put("message", "Member added successfully");
				return ResponseEntity.ok(successResponse);
			} else if ("Enter correct memberships".equals(str)){
				Map<String, String> errorResponse = new HashMap<>();
				errorResponse.put("status", "error");
				errorResponse.put("message", "Enter correct memberships");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
			} else if ("An activity is allowed only once".equals(str)){
				Map<String, String> errorResponse = new HashMap<>();
				errorResponse.put("status", "error");
				errorResponse.put("message", "An activity is allowed only once");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
			}else {
				Map<String, String> errorResponse = new HashMap<>();
				errorResponse.put("status", "error");
				errorResponse.put("message", "Something went wrong ");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
			}
		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Something went wrong: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}

	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody Member member) {
		try {
			String result = impl.updateMember(member);

			Map<String, String> response = new HashMap<>();

			if ("Success".equals(result)) {
				response.put("status", "success");
				response.put("message", "Member updated successfully");
				return ResponseEntity.ok(response);
			} else if ("Not Found".equals(result)) {
				response.put("status", "error");
				response.put("message", "Member not found");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			} else if ("Error Id".equals(result)) {
				Map<String, String> errorResponse = new HashMap<>();
				errorResponse.put("status", "error");
				errorResponse.put("message", "Enter id");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
			} else {
				response.put("status", "error");
				response.put("message", "Update failed");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Exception occurred: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Member member) {
		try {
			String result = impl.deleteMember(member);

			Map<String, String> response = new HashMap<>();

			if ("Success".equals(result)) {
				response.put("status", "success");
				response.put("message", "Member deleted successfully");
				return ResponseEntity.ok(response);
			} else if ("Not Found".equals(result)) {
				response.put("status", "error");
				response.put("message", "Member not found");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			} else if ("Error Id".equals(result)) {
				Map<String, String> errorResponse = new HashMap<>();
				errorResponse.put("status", "error");
				errorResponse.put("message", "Enter id");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
			} else {
				response.put("status", "error");
				response.put("message", "Update failed");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}

		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Exception occurred: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@PostMapping("/searchByJoinDate")
	public ResponseEntity<?> getMembersByJoinDate(@RequestBody DateRangeRequest request) {
	    try {
	        List<Member> members = impl.findMembersByJoinDateBetween(request.getStartDate(), request.getEndDate());

	        if (members.isEmpty()) {
	            Map<String, String> response = new HashMap<>();
	            response.put("status", "not found");
	            response.put("message", "No members found in the given date range");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }

	        return ResponseEntity.ok(members);
	    } catch (Exception e) {
	        Map<String, String> error = new HashMap<>();
	        error.put("status", "error");
	        error.put("message", "Exception occurred: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	    }
	}
	@GetMapping("/search/{id}")
	public ResponseEntity<?> searchMemberById(@PathVariable int id) {
	    try {
	        Member member = impl.findMemberById(id);
	        if (member != null) {
	            return ResponseEntity.ok(member);
	        } else {
	            Map<String, String> response = new HashMap<>();
	            response.put("status", "not found");
	            response.put("message", "No member found with ID: " + id);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    } catch (Exception e) {
	        Map<String, String> error = new HashMap<>();
	        error.put("status", "error");
	        error.put("message", "Exception occurred: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	    }
	}
	@GetMapping("/view")
	public ResponseEntity<?> viewAllMembers() {
	    try {
	        List<Member> members = impl.viewAllMembers();

	        if (members.isEmpty()) {
	            Map<String, String> response = new HashMap<>();
	            response.put("status", "not found");
	            response.put("message", "No members found");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }

	        return ResponseEntity.ok(members);
	    } catch (Exception e) {
	        Map<String, String> error = new HashMap<>();
	        error.put("status", "error");
	        error.put("message", "Exception occurred: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	    }
	}
	@GetMapping("/status")
	public ResponseEntity<?> getMembersByStatus(@RequestParam("status") String status) {
	    try {
	        List<Member> members = impl.findMembersByStatus(status);

	        if (members.isEmpty()) {
	            Map<String, String> response = new HashMap<>();
	            response.put("status", "not found");
	            response.put("message", "No members found with status: " + status);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }

	        return ResponseEntity.ok(members);
	    } catch (Exception e) {
	        Map<String, String> error = new HashMap<>();
	        error.put("status", "error");
	        error.put("message", "Exception occurred: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	    }
	}
	
	@GetMapping("/findByMembership")
	public ResponseEntity<?> findByMembership(@RequestParam("membership") String membership){
		try {
			List<Member> members = impl.findByMembership(membership);
			if(members.isEmpty()) {
				Map<String,String> response = new HashMap<>();
				response.put("status", "not found");
				response.put("message", "No members found with membership"+membership);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			return ResponseEntity.ok(members);
		}catch(Exception e) {
			Map<String,String> error = new HashMap<>();
			error.put("status", "error");
			error.put("message", "error Occured");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
		
	}


}
