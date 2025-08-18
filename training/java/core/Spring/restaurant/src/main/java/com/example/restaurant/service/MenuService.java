package com.example.restaurant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.model.Menu;
import com.example.restaurant.repo.MenuRepository;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRep;

	public List<Menu> getMenu() {
		return menuRep.getMenu();
	}
}
