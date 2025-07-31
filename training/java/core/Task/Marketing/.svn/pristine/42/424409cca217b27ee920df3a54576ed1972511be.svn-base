package com.medplus.marketing.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.medplus.marketing.domain.PromotionCoupon;

public class PromotionCouponExtractor implements ResultSetExtractor<PromotionCoupon>{

	@Override
	public PromotionCoupon extractData(ResultSet rs) throws SQLException {
		PromotionCoupon promotionCoupon = new PromotionCoupon();
		while(rs.next()){
			promotionCoupon.setCouponId(rs.getLong("Id"));
			promotionCoupon.setCouponCode(rs.getString("CouponCode"));
			promotionCoupon.setPromotionId(rs.getLong("PromotionId"));
			promotionCoupon.setPromotionType(rs.getInt("PromotionType"));
			promotionCoupon.setApplicableType(rs.getInt("ApplicableType"));
			promotionCoupon.setAllCustomers("Y".equalsIgnoreCase(rs.getString("AllCustomers")));
			promotionCoupon.setTotalLimit(rs.getLong("TotalLimit"));
			promotionCoupon.setCustomerLimit(rs.getLong("CustomerLimit"));
			promotionCoupon.setFromDate(rs.getObject("FromDate", LocalDateTime.class));
			promotionCoupon.setToDate(rs.getObject("ToDate", LocalDateTime.class));
			promotionCoupon.setStatus(rs.getString("Status"));
			promotionCoupon.setMinValue(rs.getDouble("MinValue"));
			promotionCoupon.setAddOnCoupon(rs.getBoolean("AddonCoupon"));
			promotionCoupon.setMaxDiscount(rs.getDouble("MaxDiscount"));
			promotionCoupon.setMaxPoints(rs.getDouble("MaxPoints"));
			promotionCoupon.setNoOfDays(rs.getObject("NoOfDays")!= null? rs.getInt("NoOfDays"): null);
			promotionCoupon.setCouponDiscountType(rs.getInt("CouponDiscountType"));
			promotionCoupon.setCreatedBy(rs.getString("CreatedBy"));
			promotionCoupon.setDateCreated(rs.getObject("DateCreated", LocalDateTime.class));
		}
		return promotionCoupon;
	}

}
