package com.example.demo.service;

import com.example.demo.models.FoodItem;
import com.example.demo.repo.ItemRepo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Slf4j
@Service
public class FoodService {
	@Autowired
	private ItemRepo repo;
   

    public void addFoodItem(FoodItem item) {
        log.info("Adding food item: {}", item);
        repo.additem(item);
    }

    public List<FoodItem> getByCategory(String category) {
        log.info("Fetching items by category: {}", category);
         return repo.allItems(category);
         
    }

    
}
