package com.rms.restaurant_management_system_backend.dao;

import java.util.List;

import com.rms.restaurant_management_system_backend.custom_classes.WaiterDetailsSelector;
import com.rms.restaurant_management_system_backend.domain.Waiters;

public interface WaitersDao {
	int insertWaiter(int employeId);
	
	int updateWaiterAvailability(int waiterId, String availability);
	
	int insertWaiterLog(Waiters oldWaiter);
	
	int selectAssignedOrdersCount(int waiterId);
	
	Waiters selectWaiterById(int waiterId);
	
	List<WaiterDetailsSelector> selectAvailableWaiters();
	
	int deleteWaiterByEmpId(int employeeId);

	Waiters selectWaiterByEmpId(int employeeId);
}
