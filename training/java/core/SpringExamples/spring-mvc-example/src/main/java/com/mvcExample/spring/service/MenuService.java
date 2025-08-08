package com.mvcExample.spring.service;

import java.util.ArrayList;
import java.util.List;

import com.mvcExample.spring.model.Menu;


public class MenuService {
	
private static List<Menu> menu=new ArrayList<>();
	
	public static List<Menu> getMenu() {
		
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
