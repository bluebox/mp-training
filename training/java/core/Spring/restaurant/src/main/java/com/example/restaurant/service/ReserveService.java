package com.example.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.model.Reserve;
import com.example.restaurant.repo.ReserveRepository;

@Service
public class ReserveService {
	
	@Autowired
	private ReserveRepository reserveRep;
	
	public void saveReserve(Reserve reserve) {
		reserveRep.saveReserve(reserve);
	}
}
