package com.medplus.marketing.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignProduct;

public interface MultipleCampaignsGenerator {
	List<Campaign> getCampaigns(Campaign campaign, Map<String, List<CampaignProduct>> storeProductInfo,
			Set<String> uniqueCouponCodes);	
}
