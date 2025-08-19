package com.lms.LMS_Springboot.Service;

import com.lms.LMS_Springboot.DAO.BookDao;
import com.lms.LMS_Springboot.DAO.Issue_RecordDAO;
import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportsService   {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private Issue_RecordDAO issueRecordDAO;

    public Map<String, Long> count_of_books_percategory() {
        List<Book> books = bookDao.viewallbooks();

        return books.stream()
                .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
    }

    
    public List<Member> members_with_statusissue() {
        return issueRecordDAO.viewMembersWithActiveIssues();
    }

    
    public List<Book> overduebooks() {
        return issueRecordDAO.viewOverdueBooks();
    }
}
