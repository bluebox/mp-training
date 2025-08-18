package com.library.Library.service;

import com.library.Library.model.IssueRecord;
import com.library.Library.model.Member;
import com.library.Library.repository.BookRepository;
import com.library.Library.repository.IssueRecordRepository;
import com.library.Library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportsService {

    private final IssueRecordRepository issueRecordRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public ReportsService(IssueRecordRepository issueRecordRepository,
                         BookRepository bookRepository,
                         MemberRepository memberRepository) {
        this.issueRecordRepository = issueRecordRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    
    public List<IssueRecord> getOverdueBooks() {
        LocalDate cutoffDate = LocalDate.now().minusDays(14);
        return issueRecordRepository.findByStatusAndIssueDateBefore('I', cutoffDate);
    }

    
    public Map<String, Long> getBooksPerCategory() {
        return bookRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(b -> b.getCategory(), Collectors.counting()));
    }

    
    public List<Member> getActiveMembers() {
        return memberRepository.findAll()
                .stream()
                .filter(m -> issueRecordRepository.existsByMemberId(m.getMemberId()))
                .collect(Collectors.toList());
    }
}
