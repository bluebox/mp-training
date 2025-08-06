package com.CouponAndProductApp.services;

import com.CouponAndProductApp.domain.Coupon;

public interface CouponService {
	boolean validateCreateCoupon(Coupon coupon);

	Coupon validateGetCouponByCode(String code);
}
