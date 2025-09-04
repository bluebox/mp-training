package com.medplus.exptracker.Service;

import java.util.List;

import com.medplus.exptracker.DTO.ManagerExpenseDTO;
import com.medplus.exptracker.DTO.ManagerForAdminDTO;
import com.medplus.exptracker.Exceptions.MonthlyLimitException;

public interface ManagerService {

    List<ManagerExpenseDTO> getExpensesByManagerId(Integer managerId);

    void approveExpense(Integer id, String remarks) throws MonthlyLimitException;

    void rejectExpense(Integer id, String remarks);
    
    List<ManagerForAdminDTO> getAllManager();
}
