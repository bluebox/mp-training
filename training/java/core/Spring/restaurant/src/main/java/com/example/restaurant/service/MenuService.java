package com.example.restaurant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.restaurant.model.Menu;

@Service
public class MenuService {

	private List<Menu> menu=new ArrayList<>();
	
	public List<Menu> getMenu() {
		menu.clear();
		menu.add(new Menu("Biryani", 125.0));
		menu.add(new Menu("Thali", 180.0));
		menu.add(new Menu("Dosa", 50.0));
		menu.add(new Menu("Puri", 50.0));
		menu.add(new Menu("Idli", 40.0));
		menu.add(new Menu("Ghee Idli", 50.0));
		
		return menu;
	}
}
