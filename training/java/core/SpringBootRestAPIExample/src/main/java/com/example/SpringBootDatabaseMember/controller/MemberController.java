package com.example.SpringBootDatabaseMember.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootDatabaseMember.entity.Member;

@RestController
@RequestMapping("/users")
public class MemberController {

//	@Autowired
//	MemberRepo memberRepo;

//	@GetMapping("/api/members")
//	public List<Member> getAllMembers() {
//
//		List<Member> members = memberRepo.findAll();
//		return members;
//	}
//
//	@PostMapping("/api/members")
//	public Member addMember(@RequestBody Member member) {
//		return memberRepo.save(member);
//	}
//
//	@GetMapping("/api/members/{memberId}")
//	public Member getMemberById(@PathVariable int memberId) {
//		return memberRepo.findById(memberId).orElse(null);
//	}
//
//	@PutMapping("/api/members/{memberId}")
//	public Member updateMember(@PathVariable int memberId, @RequestBody Member member) {
//		Member newMember = memberRepo.findById(memberId).orElse(null);
//		if (newMember != null) {
//			newMember.setMemberName(member.getMemberName());
//			newMember.setMemberMail(member.getMemberMail());
//			newMember.setMemberMobileNo(member.getMemberMobileNo());
//			newMember.setMemberGender(member.getMemberGender());
//			newMember.setMemberAddress(member.getMemberAddress());
//			return memberRepo.save(newMember);
//
//		} else {
//			return null;
//		}
//
//	}
//
//	@DeleteMapping("/api/members/{memberId}")
//	public void deleteById(@PathVariable Integer memberId) {
//
//		memberRepo.deleteById(memberId);
//	}

	Map<Integer, Member> members = new HashMap<>();

	@GetMapping
	public Collection<Member> getMembers() {
		Collection<Member> list = members.values();
		return list;

	}

	@PostMapping
	public String postMethod(@RequestBody Member member) {
		Member member1 = new Member();
		member1.setMemberId(member.getMemberId());
		member1.setMemberName(member.getMemberName());
		member1.setMemberMail(member.getMemberMail());
		member1.setMemberMobileNo(member.getMemberMobileNo());
		member1.setMemberGender(member.getMemberGender());
		member1.setMemberAddress(member.getMemberAddress());
		members.put(member.getMemberId(), member1);
		System.out.println(members);
		return "member added";
	}

	@PutMapping("/{memberId}")
	public String updateMember(@RequestBody Member member, @PathVariable Integer memberId) {

		if (members.containsKey(memberId)) {

			Member member1 = new Member();
			member1.setMemberId(member.getMemberId());
			member1.setMemberName(member.getMemberName());
			member1.setMemberMail(member.getMemberMail());
			member1.setMemberMobileNo(member.getMemberMobileNo());
			member1.setMemberGender(member.getMemberGender());
			member1.setMemberAddress(member.getMemberAddress());
			members.put(member.getMemberId(), member1);
		}
		return "member updated";
	}

	@DeleteMapping("/{memberId}")
	public String deleteMember(@RequestBody Integer memberId) {
		if (members.containsKey(memberId)) {
			members.remove(memberId);
			return "member deleted";
		}

		return "No member found";
	}

}
