package dev.tulasidhar.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tulasidhar.lms.Exceptions.IdNotExistException;
import dev.tulasidhar.lms.model.Book;
import dev.tulasidhar.lms.model.Issue_Records;
import dev.tulasidhar.lms.model.Member;
import dev.tulasidhar.lms.model.OverDueList;
import dev.tulasidhar.lms.model.ReportMember;
import dev.tulasidhar.lms.service.BookService;
import dev.tulasidhar.lms.service.IssueLogService;
import dev.tulasidhar.lms.service.MemberService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class IssueReturnController {
    @Autowired
    private BookService bookService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private IssueLogService issueLogService;
    
    

    @GetMapping("/searchmember")
    public Member getMemberByEmail(@RequestParam String email) throws IdNotExistException {
    	Member member = memberService.getMemberByEmail(email);
    	if(member == null) {
    		throw new IdNotExistException("Member does not exist");
    	}
        return member;
    }

    @PostMapping("/issuebook")
    public Map<String, String> issueBook(@RequestBody Issue_Records record) throws IdNotExistException {
        issueLogService.addIssueRecord(record);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Issued successfully");
        return response;
    }
    
    @GetMapping("/issued-records")
    public List<Issue_Records> getAllIssuedRecords() {
        return issueLogService.getAllIssuedRecords();
    }
    
    @PostMapping("/returnbook")
    public Map<String, String> returnBook(@RequestParam int issueId,@RequestParam int bookId) {
        issueLogService.returnIssuedBook(issueId, true);
        bookService.updateBookAvailability(bookId,true);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Book returned successfully");
        return response;
    }
    
    
    
    @GetMapping("/membersbooks")
    public List<Book> membersBooks(@RequestParam int memberid){
    	List<Book> books;
    	books = issueLogService.booksOfMember(memberid);
    	return books;
    }
    
    @GetMapping("/booksmembers")
    public List<Member> booksMembers(@RequestParam int bookid){
    	List<Member> members;
    	members = issueLogService.getAllIssuedRecords().stream().filter(record -> record.getBookId()==bookid)
				 .map(record -> memberService.getMemberById(record.getMemberId())).distinct().collect(Collectors.toList());
       	return members;
    }
    
    @GetMapping("/overdue-books")
    public List<OverDueList> getOverdueBooks() {
        return issueLogService.getOverDueBooks();
    }

    @GetMapping("/category-stats")
    public Map<String, Long> getCategoryStats() {
        return bookService.getBooksByCategory();
    }

    @GetMapping("/member-stats")
    public List<ReportMember> getMemberStats() {
        return issueLogService.booksOfMemberReport();
    }
    
    
    
    
    
}