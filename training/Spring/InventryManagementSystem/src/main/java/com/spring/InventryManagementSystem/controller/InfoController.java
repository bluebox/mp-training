package com.spring.InventryManagementSystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.InventryManagementSystem.domain.OrderDetails;
import com.spring.InventryManagementSystem.interfaces.service.InfoServiceInterface;

@RestController
@RequestMapping("info")
public class InfoController {

	private InfoServiceInterface infoService;

	public InfoController(InfoServiceInterface infoService) {
		this.infoService = infoService;
	}
	
	@PostMapping("pending-orders")
	public List<OrderDetails> pendingOrders() throws Exception{
		return infoService.pendingOrders();
	}
	
	@PostMapping("all-orders-admin")
	public List<OrderDetails> allOrdersAdmin() throws Exception{
		return infoService.allOrdersAdmin();
	}
	
	@PostMapping("all-orders")
	public List<OrderDetails> allOrders() throws Exception{
		return infoService.allOrders();
	}

}
