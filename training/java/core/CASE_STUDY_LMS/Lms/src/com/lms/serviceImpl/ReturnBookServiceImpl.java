package com.lms.serviceImpl;



import com.lms.dao.ReturnBookDaoInterface;
import com.lms.daoImpl.ReturnBookDaoImpl;
import com.lms.service.ReturnBookServiceInterface;

import java.util.List;

public class ReturnBookServiceImpl implements ReturnBookServiceInterface {

    private final ReturnBookDaoInterface dao = new ReturnBookDaoImpl();

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
    public boolean returnBook(String mobile, String bookName, String status) {
        if (mobile == null || bookName == null || status == null) return false;
        return dao.updateBookReturnStatus(mobile, bookName, status);
    }
}
