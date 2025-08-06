package com.CouponAndProductApp.domain;

import java.time.LocalDate;

public class Coupon {
	private int id;
	private String code;
	private double discount;
	private LocalDate expDate;
	public Coupon(int id, String code, double discount, LocalDate expDate) {
		this.id = id;
		this.code = code;
		this.discount = discount;
		this.expDate = expDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public LocalDate getExpDate() {
		return expDate;
	}
	public void setExpDate(LocalDate expDate) {
		this.expDate = expDate;
	}

}
