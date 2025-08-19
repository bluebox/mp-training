
package com.lms.service;

import java.util.List;

public interface ReturnBookServiceInterface {
    String getMemberNameByMobile(String mobile);
    List<String> getIssuedBooksByMobile(String mobile);
    boolean returnBook(String mobile, String bookName, String status);
}
