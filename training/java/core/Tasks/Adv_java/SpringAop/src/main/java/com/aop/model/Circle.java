package com.aop.model;

import com.aop.aspect.Loggable;

public class Circle {
	private String shapeName;
	
	@Loggable
	public String getShapename() {
		return shapeName;
	}

	public void setShapename(String shapename) {
		shapeName = shapename;
	}
}
