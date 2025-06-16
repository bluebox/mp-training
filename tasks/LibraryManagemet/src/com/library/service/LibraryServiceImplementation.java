package com.library.service;

import java.util.List;

import com.library.dao.BookDAO;
import com.library.dao.MemberDAO;
import com.library.domain.Book;
import com.library.domain.Member;
import com.library.utilities.MemberValidation;

public class LibraryServiceImplementation extends SQLQuery implements LibraryService {
	
	
    public boolean addMember(Member member) {
    	MemberDAO memberDAO=new MemberDAO();
        if (!MemberValidation.isValidMember(member)) {
            throw new IllegalArgumentException("Invalid member data");
        }
        return memberDAO.addMember(member,insertMember);
    }
    
    public List<Book> fetchAllBooks() {
    	BookDAO bookDAO =new BookDAO();
        return bookDAO.getAllBooks(selectAllBooks);
    }
    

}
