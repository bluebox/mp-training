package com.example.vehicle.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vehicle.model.Vehicle;
import com.example.vehicle.service.VehicleService;

@RestController
@CrossOrigin("*")
@RequestMapping("/vehicle")
public class VehicleController {
	private final VehicleService vehicleService;
	@Autowired
	public VehicleController(VehicleService vehicleService) {
		this.vehicleService=vehicleService;
	}
	@PostMapping("/add")
	public String addVehicle(@RequestBody Vehicle v) throws Exception {
		return vehicleService.addVehicle(v);
	}
	@PutMapping("/update")
	public String updateVehicle(@RequestParam int regNum,@RequestParam String updatedBy,@RequestParam int vehicleId) throws Exception {
		return vehicleService.updateVehicleNumber(regNum, updatedBy, LocalDateTime.now(), vehicleId);
	}
	@PutMapping("/delete")
	public String deleteVehicleById(@RequestParam int vehicleId) throws Exception {
		return vehicleService.deleteVehicleById(vehicleId);
	}
	@GetMapping("/show")
	public Vehicle showVehicleById(@RequestParam int vehicleId) throws Exception {
		return vehicleService.getVehicleById(vehicleId);
	}
	@GetMapping("/showAll")
	public List<Vehicle> showAllVehicles() throws SQLException {
		return vehicleService.getAllVehicles();
	}
}
