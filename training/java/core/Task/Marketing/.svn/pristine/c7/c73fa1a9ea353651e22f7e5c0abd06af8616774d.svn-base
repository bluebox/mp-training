package com.medplus.marketing.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignProduct;
import com.medplus.marketing.domain.CampaignSearchCriteria;


public interface CampaignService {

	public Map<String,Object> getCampaignList(CampaignSearchCriteria searchCriteria, List<Integer> applicableTypesByRole);
	public void saveCampaign(Campaign campaign);
	public void saveMultipleCampaigns(List<Campaign> multipleCampaigns);
	public Campaign updateCampaign(Campaign campaign);
	public Campaign findCampaignForCampaignId(Long campaignId);
	public void setToDateForCampaign(Campaign campaign);
	public Map<String, Integer> findConditionTypes(Integer campaignType);
	void checkCampaignProducts(List<CampaignProduct> campaignProducts, int campaignType);
	public void checkRemoveExcelProducts(Campaign campaign);
	public List<Map<String, Object>> findCampaignProductsForTemplateId(Long templateId, Integer campaignType, Integer applicableType);
	public int checkCampaignNameAvailablity(String campaignName);
	public List<Long> getCampaignCustomers(Long campaignId);
	public boolean approveCampaign(long campaignId, LocalDateTime toDate, List<Integer> applicableTypes, String approvedBy);
	public void rejectCampaign(long campaignId, String rejectedBy, String remarks, List<Integer> applicableTypes);
	public boolean updateCampaignToDate(long campaignId, LocalDateTime toDate, List<Integer> applicableTypes,String modifiedBy, String status);
	public void autoRejectClosedCampaigns();
	public List<String> getStores(Long campaignId);
	public List<String> getCouponCodes(Set<String> uniqueCouponCodes);

}
