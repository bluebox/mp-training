package com.medplus.marketing.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.medplus.discounts.domain.ComplimentaryProduct;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.ComplimentaryPromotion;

public interface ComplimentaryPromotionDao {
	ComplimentaryPromotion createComplimentaryPromotion(ComplimentaryPromotion complimentaryPromotion);
	Map<String,Object> getComplimentaryPromotionHeaderDetails(long complimentaryId);
	Map<String, Object> getComplimentaryPromotionList(CampaignSearchCriteria searchCriteria);
	boolean approveCampaign(Long complimentaryId, LocalDateTime toDate, String approvedBy,Map<String, Object> headerDetails, boolean isDateUpdated);
	ComplimentaryPromotion getComplimentaryPromotionById(Long complimentaryId);
	boolean updateComplimentaryToDate(Long complimentaryId, LocalDateTime toDate, String modifiedBy,Map<String, Object> headerDetails);
	boolean rejectComplimentaryPromotion(Long complimentaryId, String rejectedBy, String remarks,Map<String, Object> headerDetails);
	Set<ComplimentaryProduct> getComplimentaryProducts(Long complimentaryId, Long complimentarySlabId);
	Set<String> getComplimentaryReferenceProducts(Long complimentaryId);
	Set<Integer> getComplimentaryRefCompositons(Long complimentaryId);
	Set<Long> getComplimentaryPromotionCustomers(Long complimentaryId);
	boolean isComplimentaryPromotionNameAvailable(String promotionName);
	void autoRejectClosedComplimentaryPromotions();
	ComplimentaryPromotion updateComplimentaryPromotion(ComplimentaryPromotion complimentaryPromotion,Map<String, Object> headerDetails);
	Map<String, String> getExisitingSlabGroups();
	boolean isSlabNameAvailable(String slabName);
	boolean isPosChannel(Long complimentaryId);
	List<String> getStores(Long complimentaryId);
}
