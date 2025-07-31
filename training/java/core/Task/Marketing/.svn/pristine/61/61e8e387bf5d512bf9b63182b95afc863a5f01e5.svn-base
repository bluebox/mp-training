package com.medplus.marketing.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.discounts.domain.MultiItemCampaign;
import com.medplus.discounts.domain.MultiItemCampaignMaster;

public interface MultiItemCampaignService {

	public MultiItemCampaign createMultiItemCampaign(MultiItemCampaignMaster campaign);
	public MultiItemCampaignMaster updateMultiItemCampaign(MultiItemCampaignMaster campaign);
	public Map<String, Object> getMultiItemCampaignList(CampaignSearchCriteria searchCriteria, List<Integer> applicableTypesByRole);
	public MultiItemCampaign getMultiItemCampaign(int campaignId);
	public Set<Long> getCampaignCustomerIds(int campaignId);
	public Set<String> getCampaignItems(int campaignId);
	public boolean approveCampaign(int campaignId, LocalDateTime toDate, List<Integer> applicableTypes, String approvedBy);
	public boolean isCampaignNameAvailable(String campaignName);
	public void rejectMultiItemCampaign(int campaignId, String rejectedBy, String remarks, List<Integer> applicableTypes);
	public boolean updateActiveCampaignToDate(int campaignId, LocalDateTime toDate, List<Integer> applicableTypes, String modifiedBy, String status);
	void autoRejectClosedCampaigns();
	public List<String> getStores(Integer campaignId);
	public Map<Integer,String> getLoyaltyTypes();

}
