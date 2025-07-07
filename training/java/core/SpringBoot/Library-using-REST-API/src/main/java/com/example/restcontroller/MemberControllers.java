package com.example.restcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins="http://localhost:3000")
public class MemberControllers {
	@Autowired
	private MemberService mem;
	@GetMapping("/show")
	public ArrayList<Member> getAllMembers() {
		System.out.println("Data is fetching");
		return mem.showMembers();
	}
	@PostMapping("/add")
	public String add(@RequestBody Member m) {
		return mem.addMember(m);
	}
	@PutMapping("/update")
	public String update(@RequestBody Member m) {
		return mem.update(m.getMemberId(), m.getName(), m.getEmail(), m.getMobile(), m.getGender(), m.getAddress());
	}
	@DeleteMapping("/delete")
	public String delete(@RequestParam int memberId) {
		return mem.delete(memberId);
	}
}
