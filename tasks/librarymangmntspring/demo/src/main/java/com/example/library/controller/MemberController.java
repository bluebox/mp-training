package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.library.model.Member;
import com.example.library.service.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {
	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("")
	public String viewMembers(Model model) throws Exception {
		model.addAttribute("members", memberService.getAllMembers());
		return "members/viewMembers";
	}

	@GetMapping("/add")
	public String addMemberForm(Model model) {
		model.addAttribute("member", new Member(null, null, null, null, null, null));
		return "members/addMember";
	}

	@PostMapping("/add")
	public String addMember(@ModelAttribute Member member, Model model) throws Exception {
		memberService.addMember(member);
		return "redirect:/members";
	}

	@GetMapping("/update")
	public String updateMemberForm(@RequestParam(required = false) Integer memberId, Model model) throws Exception {
		if (memberId != null) {
			Member member = memberService.getAllMembers().stream().filter(m -> m.getMemberId().equals(memberId))
					.findFirst().orElse(null);
			model.addAttribute("member", member);
		} else {
			model.addAttribute("member", new Member(null, null, null, null, null, null));
		}
		return "members/updateMember";
	}

	@PostMapping("/update")
	public String updateMember(@ModelAttribute Member member, Model model) throws Exception {
		memberService.updateMember(member);
		return "redirect:/members";
	}

	@GetMapping("/members-with-books")
	public String membersWithBooks(Model model) throws Exception {
		model.addAttribute("membersWithBooks", memberService.getMembersWithActiveIssues());
		return "members/membersWithBooks";
	}
}