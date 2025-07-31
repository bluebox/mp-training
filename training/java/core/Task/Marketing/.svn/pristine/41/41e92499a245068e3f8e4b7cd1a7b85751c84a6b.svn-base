package com.medplus.marketing.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.medplus.discounts.constants.CouponApplicableMode;
import com.medplus.discounts.domain.Slab;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.PromotionCoupon;
import com.medplus.marketing.domain.RegularPromotion;
import com.medplus.marketing.domain.SlabGroup;

public interface RegularPromotionDao {


	public Map<String, String> getExisitingSlabGroups();
	public Map<String, String> getInvoiceCategoryTypes();
	public List<Slab> getSlabsInfoForSlabGroup(Long slabGroupId);
	public int checkSlabGroupIsAssignedToPromotionOrNot(Long slabGroupId);
	public SlabGroup insertSlabsAndSlabGroups(SlabGroup slabGroup);
	public SlabGroup updateSlabGroup(SlabGroup slabGroup);
	public void findSlabIds(SlabGroup slabGroup);
	public void savePromotionMetainfoDetails(long promotionId, int applicableType, Boolean specialtyBased, int promotionType, UserMetaData metaInfo);
	public void saveRegularPromotionCustomerDetails(RegularPromotion regularPromotion);
	public void savePromotionCouponDetails(PromotionCoupon promotionCoupon);
	public RegularPromotion insertPromotionDetailsAndDiscategorySlabMapping(RegularPromotion regularPromotion);
	public void updatePromotionMetainfoDetails(long promotionId, int promotionApplicableType, Boolean specialtyBased, int promotionType, UserMetaData metaInfo);
	public void approveRegularPromotion(Long promotionId, LocalDateTime toDate, String approvedBy, boolean isDateUpdated, Map<String, Object> headerDetails);
	public Map<String, Object> getRegularPromotionHeaderDetails(Long promotionId);
	public void updatePromtionToDate(Long promotionId, LocalDateTime toDate, String modifiedBy, Map<String, Object> headerDetails);
	public void rejectRegularPromotion(Long promotionId, String rejectedBy, String remarks,Map<String, Object> headerDetails);
	public Map<String, Object> getRegularPromotionList(CampaignSearchCriteria campaignSearchCriteria);
	public RegularPromotion getRegularPromotionByPromotionId(Long promotionId);
	public PromotionCoupon getPromotionCoupons(Long promotionId, int promotionType, String couponCode);
	public Map<CouponApplicableMode, List<String>> getCouponApplicableModes(Long promotionId, int promotionType);
	public List<Integer> getPromotionChannels(long promotionId, int applicableType, int promotionType);
	public void updateRegularPromotionDetails(RegularPromotion regularPromotion, Map<String, Object> headerDetails);
	public void updateDiscountCategorySlabs(RegularPromotion regularPromotion);
	public void updateRegularPromotionCustomerDetails(RegularPromotion regularPromotion, Map<String, Object> headerDetails);
	public void updatePromotionCouponDetails(RegularPromotion regularPromotion, Map<String, Object> headerDetails);
	public Set<Long> getRegularPromotionCustomerIds(Long promotionId, String couponCode);
	public boolean isRegularPromotionNameAvailable(String promotionName);
	public boolean isRegularPromotionCouponCodeAvailable(String couponCode);
	public void autoRejectRegularPromotion();
	boolean isPosChannel(long promotionId);
	public List<String> getStores(Long promotionId);
}
