package com.library.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;

import com.library.app.model.Member;
import com.library.app.service.LibraryService;

@Controller
public class MemberController {
    
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private LibraryService libraryService;

    @Autowired
    public MemberController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @RequestMapping(value = "/addMember", method = RequestMethod.GET)
    public String addMemberPage(Model model) {
        model.addAttribute("member", new Member());
        return "addMember";
    }

    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public String addMemberPage(@Valid @ModelAttribute("member") Member member, Model model) {
        try {
            libraryService.addMember(member);
            model.addAttribute("success", "Member added successfully.");
            model.addAttribute("member", new Member());
        } catch (Exception e) {
            model.addAttribute("error", "Failed to add member: " + e.getMessage());
            log.error("Error adding member", e);
        }
        return "viewAllMembers";
    }

    @RequestMapping(value = "/updateMember", method = RequestMethod.GET)
    public String updateMemberPage(Model model) {
        model.addAttribute("member", new Member());
        return "updateMember";
    }

    @RequestMapping(value = "/updateMember", method = RequestMethod.POST)
    public String updateMemberPage(@Valid @ModelAttribute("member") Member member, Model model) {
        try {
            libraryService.updateMember(member);
            model.addAttribute("success", "Member updated successfully.");
            model.addAttribute("member", new Member());
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update member: " + e.getMessage());
            log.error("Error updating member", e);
        }
        return "viewAllMembers";
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String viewAllMembersPage(Model model) {
        try {
            List<Member> members = libraryService.viewAllMembers();
            log.info("All Members: " + members);
            model.addAttribute("members", members);
            model.addAttribute("member", new Member());
        } catch (Exception e) {
            log.error("Error fetching members", e);
            model.addAttribute("error", "No members found!");
        }
        return "viewAllMembers";
    }
}


