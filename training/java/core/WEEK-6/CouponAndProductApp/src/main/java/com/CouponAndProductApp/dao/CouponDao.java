package com.CouponAndProductApp.dao;

import java.util.List;

import com.CouponAndProductApp.domain.Coupon;

public interface CouponDao {
	boolean createCoupon(Coupon coupon);

	Coupon getCouponByCode(String code);

	List<Coupon> getAllCoupons();
}
