package com.tulasidhar.june27.CarpetCostCalc;

public class Carpet {
	double cost;
	
	public Carpet(double cost) {
		this.cost = cost<0 ? 0 : cost;
	}
	
	public double getCost() {
		return this.cost;
	}
}
