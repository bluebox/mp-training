package com.lms.LMS_Springboot.Controller;

import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.Member;
import com.lms.LMS_Springboot.Service.ReportsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 

@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping("/bookspercategory")
    public Map<String, Long> getBooksPerCategory() {
        return reportsService.count_of_books_percategory();
    }

    @GetMapping("/activemembers")
    public List<Member> getActiveIssuedMembers() {
        return reportsService.members_with_statusissue();
    }

    @GetMapping("/overduebooks")
    public List<Book> getOverdueBooks() {
        return reportsService.overduebooks();
    }
}
