package com.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.dao.MembersDao;
import com.library.domain.Book;
import com.library.domain.Gender;
import com.library.domain.Member;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MembersDao membersDao;

    @GetMapping("/add")
    public String addMemberForm(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("genders", Gender.values());  // pass enum values to view
        return "add_member";
    }

    @PostMapping("/add")
    public String saveMember(@ModelAttribute Member member, RedirectAttributes ra) {
        boolean added = membersDao.addMember(member);
        ra.addFlashAttribute("message", added ? "Member added successfully." : "Failed to add member.");
        return "redirect:/members/list";
    }

    @GetMapping("/list")
    public String listMembers(Model model) {
        model.addAttribute("members", membersDao.getAllMembers());
        return "list_members";
    }

    @GetMapping("/edit/{id}")
    public String editMember(@PathVariable int id, Model model) {
        model.addAttribute("member", membersDao.getMemberById(id));
        model.addAttribute("genders", Gender.values());
        return "update_member";
    }
    
    @GetMapping("/search")
    public String showSearchPage() {
        return "search_member"; // name of Thymeleaf file (search_book.html)
    }

    // Handle form submission
    @PostMapping("/search")
    public String searchBookById(@RequestParam("memberId") int memberId, Model model) {
        Member member = membersDao.getMemberById(memberId);
        if (member != null) {
            return "redirect:/members/edit/" + memberId;
        } else {
            model.addAttribute("error", "Member not found with ID: " + memberId);
        }
        return "search_member";
    }

    @PostMapping("/update")
    public String updateMember(@ModelAttribute Member member, RedirectAttributes ra) {
        boolean updated = membersDao.updateMember(member);
        ra.addFlashAttribute("message", updated ? "Member updated." : "Update failed.");
        return "redirect:/members/list";
    }
}
