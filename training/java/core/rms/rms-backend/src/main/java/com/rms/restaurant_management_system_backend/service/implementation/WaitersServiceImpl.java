package com.rms.restaurant_management_system_backend.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rms.restaurant_management_system_backend.constant.WaiterAvailability;
import com.rms.restaurant_management_system_backend.custom_classes.WaiterDetailsSelector;
import com.rms.restaurant_management_system_backend.dao.EmployeeDao;
import com.rms.restaurant_management_system_backend.dao.WaitersDao;
import com.rms.restaurant_management_system_backend.domain.Employees;
import com.rms.restaurant_management_system_backend.domain.Waiters;
import com.rms.restaurant_management_system_backend.exception.RestaurantOperationException;
import com.rms.restaurant_management_system_backend.service.WaitersService;

@Service
public class WaitersServiceImpl implements WaitersService {

	private final WaitersDao waitersDao;

	private final EmployeeDao employeeDao;

	public WaitersServiceImpl(WaitersDao waitersDao, EmployeeDao employeeDao) {
		this.waitersDao = waitersDao;
		this.employeeDao = employeeDao;
	}

	@Override
	public boolean insertWaiter(int employeeId) {
		Employees employee = employeeDao.getEmpById(employeeId);
		if (employee == null) {
			throw new RestaurantOperationException("Employee not found");
		}

		int count = waitersDao.insertWaiter(employeeId);

		if (count != 1) {
			throw new RestaurantOperationException("Waiter not inserted correctly");
		}
		return true;
	}

	@Override
	public boolean updateWaiterAvailability(int waiterId) {
		Waiters oldWaiter = waitersDao.selectWaiterById(waiterId);
		if (oldWaiter == null) {
			throw new RestaurantOperationException("Waiter with given id not found");
		}

		int logCount = waitersDao.insertWaiterLog(oldWaiter);
		if (logCount != 1) {
			throw new RestaurantOperationException("Waiter log not inserted correctly");
		}

		int ordersCount = waitersDao.selectAssignedOrdersCount(oldWaiter.getWaiterId());
		System.out.println(ordersCount);
		if (ordersCount >= 3) {
			waitersDao.updateWaiterAvailability(waiterId, WaiterAvailability.BUSY.getDbName());
		} else if (ordersCount < 3) {
			waitersDao.updateWaiterAvailability(waiterId, WaiterAvailability.AVAILABLE.getDbName());
		}
		return true;
	}

	@Override
	public List<WaiterDetailsSelector> selectAvailableWaiters() {
		List<WaiterDetailsSelector> availableWaiters = waitersDao.selectAvailableWaiters();
		return availableWaiters;
	}

	@Override
	public boolean deleteWaiterByEmpId(int employeeId) {
		Employees employee = employeeDao.getEmpById(employeeId);
		if (employee == null) {
			throw new RestaurantOperationException("Employee not found");
		}
		Waiters oldWaiter = selectWaiterByEmpId(employeeId);
		waitersDao.insertWaiterLog(oldWaiter);
		int count = waitersDao.deleteWaiterByEmpId(employeeId);
		if (count != 1) {
			throw new RestaurantOperationException("Failed to delete waiter by employee id");
		}
		return true;
	}

	@Override
	public Waiters selectWaiterByEmpId(int employeeId) {
		return waitersDao.selectWaiterByEmpId(employeeId);
	}

}
