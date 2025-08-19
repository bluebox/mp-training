package com.lms.serviceImpl;


import com.lms.dao.ReturnBookDao;
import com.lms.daoImpl.ReturnBookDaoImpl;
import com.lms.model.ReturnBook;
import com.lms.service.ReturnBookService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnBookServiceImpl implements ReturnBookService {

    private final ReturnBookDao dao;

    @Override
    public String getMemberNameByMobile(String mobile) {
        if (mobile == null || !mobile.matches("\\d{10}")) return null;
        return dao.fetchMemberName(mobile);
    }

    @Override
    public List<String> getIssuedBooksByMobile(String mobile) {
        if (mobile == null || !mobile.matches("\\d{10}")) return null;
        return dao.fetchIssuedBooks(mobile);
    }

    @Override
    public boolean returnBook(ReturnBook returnBook) {
        if (returnBook.getMobile() == null || returnBook.getBookName() == null || returnBook.getStatus() == null)
            return false;
        return dao.updateBookReturnStatus(returnBook);
    }
}
