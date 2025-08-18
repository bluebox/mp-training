package com.lms.LMS_Springboot.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.Member;




@Service 
@Component
public class Reports {
	@Autowired 
	private BookService bookservice;
	@Autowired 
	private MemberService memberservice;
	 public Map<String, Long> count_of_books_percategory() {
	        List<Book> list = bookservice.viewbooks();
	        return list.stream()
	                .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
//	        map.forEach((x, y) -> System.out.println(x + " " + y));
	       
	    }
	 
	 public List<Member> members_with_statusissue() {
         
        
        return  memberservice.viewjoinmembers();
        
        

        
 	
 }
 	public List<Book> overduebooks() {

 		return bookservice.viewjoinbooks();
 	}

}
