package com.medplus.marketing.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.util.Pair;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.domain.CategoryDetail;
import com.medplus.marketing.domain.RegularPromotion;

public class RegularPromotionExtractor implements ResultSetExtractor<RegularPromotion>{

	@Override
	public RegularPromotion extractData(ResultSet rs) throws SQLException {
		RegularPromotion regularPromotion = new RegularPromotion();
		Set<Integer> productCategoryTypes = new HashSet<>();
		Set<Integer> loyaltyTypes= new HashSet<>();
		Set<Integer> discountTypes = new HashSet<>();
		Set<String> regions = new HashSet<>();
		Map<String, Pair<String,String>> existingDiscountCategorySlabMappingDetails = new HashMap<>();
		Set<CategoryDetail> categoryDetails = new HashSet<>();
		boolean isFirstRecord = true;
		while(rs.next()){
			if(isFirstRecord){
				regularPromotion.setPromotionId(rs.getLong("PromotionId"));
				regularPromotion.setPromotionName(rs.getString("Name"));
				regularPromotion.setStatus(rs.getString("Status"));
				regularPromotion.setFromDate(rs.getObject("ValidFrom", LocalDateTime.class));
				regularPromotion.setToDate(rs.getObject("ValidTo", LocalDateTime.class));
				regularPromotion.setEffectiveDate(rs.getObject("EffectiveDate", LocalDateTime.class));
				regularPromotion.setSlabGroupId(rs.getInt("SlabGroupId"));
				regularPromotion.setStatus(rs.getString("Status"));
				regularPromotion.setPromotionLevel(rs.getString("PromotionLevel"));
				regularPromotion.setApplicableType(rs.getInt("ApplicableType"));
				regularPromotion.setCouponBased("Y".equalsIgnoreCase(rs.getString("CouponBased")));
				regularPromotion.setAllCustomers("Y".equalsIgnoreCase(rs.getString("AllCustomers")));
				UserMetaData metaData = new UserMetaData();
				metaData.setLongDescription(rs.getString("Description"));
				metaData.setImagePath(UtilValidate.isNotEmpty(rs.getString("ImagePath"))?rs.getString("ImagePath"):"");
				metaData.setImageServerName(UtilValidate.isNotEmpty(rs.getString("ImageServerName"))?rs.getString("ImageServerName"):"");
				metaData.setCreatedBy(rs.getString("CreatedBy"));
				metaData.setDateCreated(rs.getObject("DateCreated", LocalDateTime.class));
				metaData.setModifiedBy(rs.getString("ModifiedBy"));
				metaData.setDateModified(rs.getObject("DateModified", LocalDateTime.class));
				metaData.setRemarks(rs.getString("Remarks"));
				String promotionVisble = rs.getString("PromotionVisible");
				if(UtilValidate.isNotEmpty(promotionVisble))
					metaData.setPromotionVisible("Y".equalsIgnoreCase(promotionVisble));
				regularPromotion.setUserMetaData(metaData);
				isFirstRecord=false;
			}
			productCategoryTypes.add(rs.getInt("ProductDiscountCategoryID"));
			loyaltyTypes.add(rs.getInt("LoyaltyType"));
			discountTypes.add(rs.getInt("DiscountType"));
			regions.add(rs.getString("Region"));
			existingDiscountCategorySlabMappingDetails.put(rs.getString("DiscCategoryID")+"@"+rs.getShort("SlabID"), Pair.create(rs.getString("DiscPercentage"),rs.getString("PaybackPercentage")));
			CategoryDetail categoryDetail = new CategoryDetail();
			categoryDetail.setDiscountCategoryId(rs.getLong("DiscCategoryID"));
			categoryDetail.setSlabId(rs.getLong("SlabID"));
			categoryDetail.setDiscountPercentage(rs.getDouble("DiscPercentage"));
			categoryDetail.setPaybackPercentage(rs.getDouble("PaybackPercentage"));
			categoryDetail.setProductDiscountCategoryId(Long.valueOf(rs.getString("ProductDiscountCategoryID")));
			categoryDetail.setLoyaltyId(Long.valueOf(rs.getString("LoyaltyType")));
			categoryDetail.setRegion(rs.getString("Region"));
			categoryDetails.add(categoryDetail);
		}
		regularPromotion.setProductCategoryIds(new ArrayList<>(productCategoryTypes));
		regularPromotion.setLoyalty(new ArrayList<>(loyaltyTypes));
		regularPromotion.setDiscountType(new ArrayList<>(discountTypes));
		regularPromotion.setRegions(new ArrayList<>(regions));
		regularPromotion.setCategoryDetail(new ArrayList<>(categoryDetails));
		return regularPromotion;
	}
	
}
