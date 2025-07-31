package com.medplus.marketing.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignProduct;

public class MultipleCampaignsContext {

	private final MultipleCampaignsGenerator multipleCampaignsGenerator;
	public MultipleCampaignsContext(MultipleCampaignsGenerator multipleCampaignsGenerator) {
		this.multipleCampaignsGenerator = multipleCampaignsGenerator;
	}
		
	public List<Campaign> generateMultipleCampaigns(Campaign campaign, Map<String, List<CampaignProduct>> storeProductInfo, Set<String> uniqueCouponCodes) {
		return multipleCampaignsGenerator.getCampaigns(campaign, storeProductInfo, uniqueCouponCodes);
	}

}
