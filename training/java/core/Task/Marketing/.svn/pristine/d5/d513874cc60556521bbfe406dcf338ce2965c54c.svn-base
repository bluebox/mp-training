package com.medplus.marketing.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.medplus.discounts.constants.CouponApplicableMode;
import com.medplus.discounts.constants.ServiceChargeMode;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignProduct;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.PromotionCoupon;

public interface CampaignDao {

	public void saveCampaignTemplate(Campaign campaign);
	public void saveCampaign(Campaign campaign);
	public void updateCampaignTemplate(Campaign campaign);
	public void updateCampaign(Campaign campaign);
	public Campaign findCampaignByCampaignId(Long campaignId);
	public List<CampaignProduct> findCampaignProductsByTemplateId(Long templateId, Integer campaignType);
	public void setToDateForCampaign(Campaign campaign);
	public int checkCampaignNameAvailablity(String campaignName);
	public List<String> checkCampaignProducts(Set<String> campaignProductsStr);
	public PromotionCoupon getPromotionCoupons(Long promotionId,int promotionType, String couponCode);
	public void saveCampaignCustomerDetails(Campaign campaign);
	public void updateCouponDetailsForCampaignCustomers(Long promotionId,String couponCode);
	public void updateCampaignCustomerDetails(Campaign campaign);
	public List<Long> getCampaignCustomers(Long campaignId);
	public List<Integer> getPromotionChannels(long promotionId, int applicableType, int promotionType);
	public Map<CouponApplicableMode, List<String>> getCouponApplicableModes(long promotionId, int promotionType);
	public Map<ServiceChargeMode, Double> getServiceChargePromotionDetails(Long promotionId, Integer promotionType);
	public void savePromotionMetainfoDetails(long promotionId, int promotionApplicableType, int promotionType, UserMetaData userMetaData);
	public void savePromotionCouponDetails(PromotionCoupon promotionCoupon);
	public void saveServiceChargePromotions(PromotionCoupon promotionCoupon);
	public void updatePromotionMetainfoDetails(long promotionId, int promotionApplicableType, int promotionType, UserMetaData userMetaData);
	public void updatePromotionCouponDetails(PromotionCoupon promotionCoupon);
	public void updateServiceChargePromotions(PromotionCoupon promotionCoupon);
	public void deleteCouponCodesForPromotion(Long campaignId, Integer campaignType);
	public void setToDateForPromotionCouponDetails(PromotionCoupon promotionCoupon);
	public Map<String, Integer> findConditionTypes(Integer campaignType);
	public List<Map<String, Object>> findCampaignProductsByTemplateIdForExcel(Long templateId, Integer campaignType, Integer applicableType);
	public Map<String, Object> getCampaignList(CampaignSearchCriteria searchCriteria);
	public boolean approveCampaign(long campaignId, long templateId, LocalDateTime toDate, String approvedBy, Map<String, Object> headerDetails, boolean isDateUpdated);
	public void rejectCampaign(long campaignId, long templateId, String rejectedBy, String remarks, Map<String, Object> headerDetails);
	public boolean updateCampaignToDate(long campaignId, LocalDateTime toDate, String modifiedBy, Map<String, Object> headerDetails);
	public void autoRejectClosedCampaigns();
	public Map<String, Object> getCampaignHeaderDetails(long campaignId);
	public PromotionCoupon getPromotionCouponForId(Long campaignId, Integer campaignType);
	boolean isPosChannel(long campaignId);
	public List<String> getStores(Long campaignId);
	public List<String> getCouponCodes(Set<String> uniqueCouponCodes);
	
}
