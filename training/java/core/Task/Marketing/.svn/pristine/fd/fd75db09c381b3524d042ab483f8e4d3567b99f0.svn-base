package com.medplus.marketing.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.medplus.discounts.domain.MultiItemCampaignMaster;
import com.medplus.marketing.domain.CampaignSearchCriteria;

public interface MarketingMultiItemCampaignDao {

	public MultiItemCampaignMaster createMultiItemCampaign(MultiItemCampaignMaster campaign);
	public MultiItemCampaignMaster updateMultiItemCampaign(MultiItemCampaignMaster campaign, Map<String, Object> headerDetails);
	public Map<String, Object> getMultiItemCampaignList(CampaignSearchCriteria searchCriteria);
	public Set<Long> getCampaignCustomerIds(int campaignId);
	public Set<String> getCampaignItems(int campaignId);
	public boolean approveCampaign(int campaignId, LocalDateTime toDate, String approvedBy, Map<String, Object> headerDetails, boolean isDateUpdated);
	public boolean isCampaignNameAvailable(String campaignName);
	public Map<String, Object> getCampaignHeaderDetails(int campaignId);
	public void rejectMultiItemCampaign(int campaignId, String rejectedBy, String remarks, Map<String, Object> headerDetails);
	public boolean updateActiveCampaignToDate(int campaignId, LocalDateTime toDate, String modifiedBy, Map<String, Object> headerDetails);
	public void autoRejectClosedCampaigns();
	public List<String> getStores(Integer campaignId);

}
