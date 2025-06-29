package com.tulasidhar.june27.PointDistanceCalculator;

public class Point {
	int x,y;
	public Point() {
		this(0, 0);
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public double distance() {
		double distance = Math.sqrt( Math.pow((this.x-0),2) 
									+ Math.pow((this.y-0),2) 
									);
		return distance;
	}
	public double distance(int x , int y) {
		double distance = Math.sqrt( Math.pow((this.x-x),2) 
				+ Math.pow((this.y-y),2) 
				);
		return distance;
	}
	public double distance(Point p) {
		double distance = Math.sqrt( Math.pow((this.x-p.x),2) 
				+ Math.pow((this.y-p.y),2) 
				);
		return distance;
	}
}
