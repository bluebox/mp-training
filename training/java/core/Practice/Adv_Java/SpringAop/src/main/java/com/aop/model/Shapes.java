package com.aop.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Shapes {
	@Autowired()
	private Circle circle;
	@Autowired()
	private Triangle triangle;
	public Circle getCircle() {
		return circle;
	}
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	public Triangle getTriangle() {
		return triangle;
	}
	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}
}
