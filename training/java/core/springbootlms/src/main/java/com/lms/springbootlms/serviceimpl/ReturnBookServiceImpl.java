package com.lms.springbootlms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.springbootlms.dao.ReturnBookDao;
import com.lms.springbootlms.exception.DaoException;
import com.lms.springbootlms.exception.ServiceException;
import com.lms.springbootlms.service.ReturnBookServiceInterface;

@Service
public class ReturnBookServiceImpl implements ReturnBookServiceInterface {

    private final ReturnBookDao dao;

    @Autowired
    public ReturnBookServiceImpl(ReturnBookDao dao) {
        this.dao = dao;
    }

    @Override
    public String getMemberNameByMobile(String mobile) throws ServiceException {
        try {
            if (mobile == null || !mobile.matches("\\d{10}")) {
                throw new ServiceException("Invalid mobile number format. Must be 10 digits.");
            }
            return dao.fetchMemberName(mobile);
        } catch (DaoException e) {
            throw new ServiceException("Error fetching member name for mobile: " + mobile, e);
        }
    }

    @Override
    public List<String> getIssuedBooksByMobile(String mobile) throws ServiceException {
        try {
            if (mobile == null || !mobile.matches("\\d{10}")) {
                throw new ServiceException("Invalid mobile number format. Must be 10 digits.");
            }
            return dao.fetchIssuedBooks(mobile);
        } catch (DaoException e) {
            throw new ServiceException("Error fetching issued books for mobile: " + mobile, e);
        }
    }

    @Override
    public boolean returnBook(String mobile, String bookName, String status) throws ServiceException {
        try {
            if (mobile == null || !mobile.matches("\\d{10}")) {
                throw new ServiceException("Invalid mobile number format. Must be 10 digits.");
            }
            if (bookName == null || bookName.isBlank()) {
                throw new ServiceException("Book name cannot be null or empty.");
            }
            if (status == null || (!status.equalsIgnoreCase("active") && !status.equalsIgnoreCase("inactive"))) {
                throw new ServiceException("Invalid status. Allowed values: active/inactive.");
            }
            return dao.updateBookReturnStatus(mobile, bookName, status);
        } catch (DaoException e) {
            throw new ServiceException("Error while returning book: " + bookName + " for mobile: " + mobile, e);
        }
    }
}
