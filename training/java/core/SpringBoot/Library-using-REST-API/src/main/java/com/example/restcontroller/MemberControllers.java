package com.example.restcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Member;
import com.example.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberControllers {
	@Autowired
	private MemberService mem;
	@GetMapping("/show")
	public ArrayList<Member> getAllMembers() {
		return mem.showMembers();
	}
	@PostMapping("/add")
	public String add(@RequestBody Member m) {
		return mem.addMember(m);
	}
	@PutMapping("/update")
	public String update(@RequestParam int memberId,@RequestParam String name,@RequestParam String email,@RequestParam long mobile,@RequestParam char gender,@RequestParam String address) {
		return mem.update(memberId, name, email, mobile, gender, address);
	}
	@DeleteMapping("/delete")
	public String delete(@RequestParam int memberId) {
		return mem.delete(memberId);
	}
}
