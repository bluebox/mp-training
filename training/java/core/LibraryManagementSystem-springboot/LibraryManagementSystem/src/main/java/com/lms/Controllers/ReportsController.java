package com.lms.Controllers;



import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.Models.Book;
import com.lms.Models.IssueRecords;
import com.lms.Models.Member;
import com.lms.Services.Implementation.BookServiceImplementation;
import com.lms.Services.Implementation.IssueRecordServiceImplementation;
import com.lms.Services.Implementation.MemberServiceImplementation;



@RequestMapping("/reports")
@RestController
@CrossOrigin("http://localhost:5173/")
public class ReportsController {

	private final IssueRecordServiceImplementation issueService;
	private final BookServiceImplementation bookService;
	private final MemberServiceImplementation memberService;

	@Autowired
	public ReportsController(IssueRecordServiceImplementation issueService, BookServiceImplementation bookService,
			MemberServiceImplementation memberService) {
		this.bookService = bookService;
		this.issueService = issueService;
		this.memberService = memberService;
	}

	@GetMapping("/overdue")
	public ResponseEntity<List<OverdueBook>> dueBooks() {
		List<IssueRecords> allIssues = issueService.getAllIssues();
		List<OverdueBook> overdueList = allIssues.stream().filter(issue -> issue.getReturnDate() == null)
				.filter(issue -> issue.getIssueDate().isBefore(LocalDate.now().minusDays(10))).map(issue -> {
					Book book = bookService.getBookById(issue.getBookId());
					Member member = memberService.fetchMemberById(issue.getMemberId());
					return new OverdueBook(book.getTitle(), member.getName(), issue.getIssueDate());
				}).collect(Collectors.toList());
		return overdueList.size() > 0 ? new ResponseEntity<>(overdueList, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/count")
	public ResponseEntity<List<CategoryCount>> count() {
	    List<Book> allBooks = bookService.getAllBooks();

	    Map<String, Long> countByCategory = allBooks.stream()
	            .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));

	    List<CategoryCount> categoryCountList = countByCategory.entrySet().stream()
	            .map(e -> new CategoryCount(e.getKey(), e.getValue()))
	            .sorted(Comparator.comparingLong(CategoryCount::getCount).reversed()) 
	            .collect(Collectors.toList());

	    return categoryCountList.isEmpty()? new ResponseEntity<>(HttpStatus.NO_CONTENT): new ResponseEntity<>(categoryCountList, HttpStatus.OK);
	}

	@GetMapping("/members")
	public ResponseEntity<List<ActiveMember>> members() throws Exception {
		List<IssueRecords> allIssues = issueService.getAllIssues();
		Map<Integer, Long> memberIssueCount = allIssues.stream().filter(issue -> issue.getReturnDate() == null)
				.collect(Collectors.groupingBy(IssueRecords::getMemberId, Collectors.counting()));
		List<ActiveMember> activeMembersList = memberIssueCount.entrySet().stream().map(e -> {
			Member member = memberService.fetchMemberById(e.getKey());
			return new ActiveMember(member.getName(), e.getValue().intValue());
		}).collect(Collectors.toList());
		return activeMembersList.size() > 0 ? new ResponseEntity<>(activeMembersList, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	class OverdueBook {
		private final String title;
		private final String memberName;
		private final LocalDate issueDate;

		public OverdueBook(String title, String memberName, LocalDate issueDate) {
			this.title = title;
			this.memberName = memberName;
			this.issueDate = issueDate;
		}

		public String getTitle() {
			return title;
		}

		public String getMemberName() {
			return memberName;
		}

		public LocalDate getIssueDate() {
			return issueDate;
		}
	}

	
	class CategoryCount {
		private final String category;
		private final Long count;

		public CategoryCount(String category, Long count) {
			this.category = category;
			this.count = count;
		}

		public String getCategory() {
			return category;
		}

		public Long getCount() {
			return count;
		}
	}

	class ActiveMember {
		private final String name;
		private final int booksIssued;

		public ActiveMember(String name, int booksIssued) {
			this.name = name;
			this.booksIssued = booksIssued;
		}

		public String getName() {
			return name;
		}

		public int getBooksIssued() {
			return booksIssued;
		}
	}
}
