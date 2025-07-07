package react.casestudy.react.bookselling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import react.casestudy.react.bookselling.domain.Member;
import react.casestudy.react.bookselling.service.MemberService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public ResponseEntity<String> addMember(@RequestBody Member member) {
        int result = memberService.addMember(member);
        return result > 0 ? ResponseEntity.ok("Member added successfully")
                          : ResponseEntity.badRequest().body("Failed to add member");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable int id, @RequestBody Member member) {
        boolean updated = memberService.updateMember(id, member);
        return updated ? ResponseEntity.ok("Member updated successfully")
                       : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable int id) {
        try {
            Member member = memberService.getMemberById(id);
            return ResponseEntity.ok(member);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMembersByName(@RequestParam String name) {
        return ResponseEntity.ok(memberService.searchMembersByName(name));
    }
}

