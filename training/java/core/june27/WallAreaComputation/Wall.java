package com.tulasidhar.june27.WallAreaComputation;

public class Wall {
	double width;
	double height;
	
	public Wall() {
		//Note: using this() and matching the certain constructor we can do constructor chaining
		this(0.0d,0.0d);
	}
	
	public Wall(double width,double height) {
		if(width < 0 ) 
			this.width = 0;
		else 
			this.width = width;

		if(height < 0) 
			this.height = 0;
		else
			this.height = height;
	}
	
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getArea() {
		return height*width;
	}
}
