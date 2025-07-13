package com.spring.InventryManagementSystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	
	Integer ItemId;
	Integer OrderId;
	String item;
	Integer itemQuantity;
	Float itemCost;

}
