package com.medplus.marketing.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.medplus.discounts.domain.ComplimentaryProduct;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.ComplimentaryPromotion;

public interface ComplimentaryPromotionService {

	ComplimentaryPromotion createComplimentaryPromotion(ComplimentaryPromotion complimentaryPromotion);
	Map<String, Object> getComplimentaryPromotionList(CampaignSearchCriteria searchCriteria,List<Integer> applicableTypesByRole);
	ComplimentaryPromotion getComplimentaryPromotionById(Long complimentaryId);
	boolean approveComplimentaryPromotion(Long complimentaryId, LocalDateTime toDate, List<Integer> applicableTypesByRole, String approvedBy);
	boolean updateComplimentaryToDate(Long complimentaryId, LocalDateTime toDate, List<Integer> applicableTypesByRole, String modifiedBy, String status);
	boolean rejectComplimentaryPromotion(Long complimentaryId, String rejectedBy, String remarks, List<Integer> applicableTypesByRole);
	Set<ComplimentaryProduct> getComplimentaryProducts(Long complimentaryId, Long complimentarySlabId);
	Set<String> getComplimentaryReferenceProducts(Long complimentaryId);
	Set<Integer> getComplimentaryRefCompositons(Long complimentaryId);
	Set<Long> getComplimentaryPromotionCustomers(Long complimentaryId);
	void autoRejectClosedComplimentaryPromotions();
	ComplimentaryPromotion updateComplimentaryPromotion(ComplimentaryPromotion complimentaryPromotion);
	Map<String, String> getExisitingSlabs();
	boolean isPromotionNameAvailable(String promotionName);
	boolean isSlabNameAvailable(String slabName);
	List<String> getStores(Long complimentaryId);
}
