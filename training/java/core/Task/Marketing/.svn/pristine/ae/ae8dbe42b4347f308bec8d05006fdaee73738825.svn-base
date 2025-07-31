package com.medplus.marketing.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.medplus.discounts.domain.Slab;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.RegularPromotion;
import com.medplus.marketing.domain.SlabGroup;

public interface RegularPromotionService {

	public int checkSlabGroupAssignOrNot(Long slabGroupId);
	public Map<String, String> getExisitingSlabGroups();
	Map<String, String> getInvoiceCategoryTypes();
	public List<Slab> getSlabsInfoForSlabGroup(Long slabGroupId);
	public SlabGroup insertSlabsAndSlabGroups(SlabGroup slabGroup);
	public RegularPromotion insertRegularPromotion(RegularPromotion regularPromotion);
	public boolean approveRegularPromotion(Long promotionId, LocalDateTime toDate, String approveBy);
	public boolean updatePromotionToDate(Long promotionId, LocalDateTime toDate, String modifiedBy, String status);
	public void rejectRegularPromotion(Long promotionId, String rejectedBy, String remarks);
	public Map<String, Object> getRegularPromotions(CampaignSearchCriteria searchCriteria, List<Integer> applicableTypesByRole);
	public RegularPromotion getRegularPromotionByPromotionId(Long promotionId);
	public RegularPromotion updateRegularPromotion(RegularPromotion regularPromotion);
	public Set<Long> getRegularPromotionCustomerIds(Long promotionId, String couponCode);
	public boolean isRegularPromotionNameAvailable(String promotionName);
	public boolean isRegularPromotionCouponCodeAvailable(String couponCode);
	public void autoRejectRegularPromotion();
	public List<String> getStores(Long promotionId);
}
