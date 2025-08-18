package controller;

import jakarta.validation.Valid;
import model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.MemberRepository;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*") 
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    // Create a new member
    @PostMapping
    public ResponseEntity<?> addMember(@Valid @RequestBody Member member) {
        try {
            int rows = memberRepository.addMember(member);
            if (rows > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(member);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add member");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error occurred: " + e.getMessage());
        }
    }

    // Update a member
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable int id, @Valid @RequestBody Member member) {
        try {
            member.setId(id);
            Member updatedMember = memberRepository.updateMember(member);
            if (updatedMember != null) {
                return ResponseEntity.ok(updatedMember);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Member with ID " + id + " not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error occurred: " + e.getMessage());
        }
    }

    // Get all members
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberRepository.getAllMembers();
        return ResponseEntity.ok(members);
    }

    // Get member by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable int id) {
        Member member = memberRepository.getMemberById(id);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Member with ID " + id + " not found");
        }
    }

   
}
