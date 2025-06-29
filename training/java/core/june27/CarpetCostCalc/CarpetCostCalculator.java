package com.tulasidhar.june27.CarpetCostCalc;

public class CarpetCostCalculator {
	Floor floor;
	Carpet carpet;
	
	public CarpetCostCalculator(Floor floor, Carpet carpet) {
		this.floor = floor;
		this.carpet = carpet;
	}
	
	public double getTotalCost() {
		return floor.getArea() * carpet.getCost();
	}
	
	
}
