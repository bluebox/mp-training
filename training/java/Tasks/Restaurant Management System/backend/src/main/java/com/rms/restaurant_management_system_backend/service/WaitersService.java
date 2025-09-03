package com.rms.restaurant_management_system_backend.service;

import java.util.List;

import com.rms.restaurant_management_system_backend.custom_classes.WaiterDetailsSelector;
import com.rms.restaurant_management_system_backend.domain.Waiters;

public interface WaitersService {
	boolean insertWaiter(int employeeId);

	boolean updateWaiterAvailability(int waiterId);
	
	List<WaiterDetailsSelector> selectAvailableWaiters();
	
	boolean deleteWaiterByEmpId(int employeeId);

	Waiters selectWaiterByEmpId(int employeeId);
}
