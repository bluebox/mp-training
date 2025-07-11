package com.example.vehicle.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vehicle.dao.VehicleDao;
import com.example.vehicle.model.Vehicle;

@Service
@Transactional
public class VehicleService {
	@Autowired
	private VehicleDao vehicleDao;

	public String addVehicle(Vehicle vehicle) throws Exception {
		String result;
		try {
			result = vehicleDao.addVehicle(vehicle);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return result;
	}

//	public String updateVehicle(Vehicle vehicle) throws Exception {
//		String result;
//		try {
//			result=vehicleDao.updateVehicle(vehicle);
//		}
//		catch(Exception e) {
//			throw new Exception(e.getMessage());
//		}
//		return result;
//	}
	public String updateVehicleNumber(String regNum, String updatedBy, LocalDateTime updatedOn, int vehicleId)
			throws Exception {
		String result;
		try {
			result = vehicleDao.updateVehicleNumber(regNum, updatedBy, updatedOn, vehicleId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return result;
	}

	public Vehicle getVehicleById(int vehicleId) throws Exception {
		Vehicle vehicle = vehicleDao.getVehicleById(vehicleId);
		return vehicle;
	}

	
	public Vehicle getVehicleByRegNum(String regNum) throws Exception {
		Vehicle vehicle = vehicleDao.getVehicleByRegNum(regNum);
		return vehicle;
	}

	public String deleteVehicleById(int vehicleId) throws Exception {
		String result;
		try {
			result = vehicleDao.deleteVehicleById(vehicleId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return result;
	}
	
	public List<Vehicle> getAllVehicles(){
		return vehicleDao.getAllVehicles();
	}

}
