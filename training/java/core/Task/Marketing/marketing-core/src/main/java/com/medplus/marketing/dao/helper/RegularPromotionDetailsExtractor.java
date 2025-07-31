package com.medplus.marketing.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.domain.RegularPromotion;

public class RegularPromotionDetailsExtractor implements ResultSetExtractor<RegularPromotion>{

	@Override
	public RegularPromotion extractData(ResultSet rs) throws SQLException {
		RegularPromotion promotion = new RegularPromotion();
		promotion.setPromotionId(rs.getLong("PromotionId"));
		promotion.setPromotionName(rs.getString("Name"));
		promotion.setFromDate(rs.getObject("ValidFrom", LocalDateTime.class));
		promotion.setToDate(rs.getObject("ValidTo", LocalDateTime.class));
		promotion.setStatus(rs.getString("Status"));
		promotion.setPromotionLevel(rs.getString("PromotionLevel"));
		String couponBased = rs.getString("CouponBased");
		if(UtilValidate.isNotEmpty(couponBased))
			promotion.setCouponBased("Y".equalsIgnoreCase(couponBased));
		String allCustomers = rs.getString("AllCustomers");
		if(UtilValidate.isNotEmpty(allCustomers))
			promotion.setAllCustomers("Y".equalsIgnoreCase(allCustomers));
		UserMetaData metaData = new UserMetaData();
		metaData.setCreatedBy(rs.getString("CreatedBy"));
		metaData.setDateCreated(rs.getObject("DateCreated", LocalDateTime.class));
		metaData.setModifiedBy(rs.getString("ModifiedBy"));
		metaData.setDateModified(rs.getObject("DateModified", LocalDateTime.class));
		promotion.setUserMetaData(metaData);
		promotion.setSlabGroupId(rs.getInt("SlabGroupId"));
		promotion.setCloneReferenceId(rs.getLong("CloneReferenceId"));
		promotion.setApplicableType(rs.getInt("ApplicableType"));
		return promotion;
	}

}
