package com.medplus.marketing.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.stereotype.Component;

import com.medplus.marketing.constants.CampaignConstants;
import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignProduct;

@Component
public class CampaignsGroupByProduct implements MultipleCampaignsGenerator {
	
	@Override
	public List<Campaign> getCampaigns(Campaign campaign, Map<String, List<CampaignProduct>> storeProductInfo, Set<String> uniqueCouponCodes) {
		List<Campaign> campaigns = new ArrayList<>();
		boolean isCouponBased = CampaignConstants.COUPON_BASED.equalsIgnoreCase(campaign.getCouponBased());
		Iterator<String> couponItr = uniqueCouponCodes.iterator();
		
		storeProductInfo.forEach((productId, campaignProducts) -> {
			Campaign tempCampign = (Campaign) SerializationUtils.clone(campaign);
			
			tempCampign.setCampaignName(campaign.getCampaignName() + "_" + productId);
			tempCampign.setRegionsMap(campaignProducts.stream().
					collect(Collectors.toMap(CampaignProduct::getStoreId, p -> "", 
							(existing, replacement) -> existing)));
			Set<String> prodQuantKey = new HashSet<>();
			List<CampaignProduct> filteredProducts = campaignProducts.stream()
			    .filter(product -> prodQuantKey.add(product.getProductId() + "_" + product.getFromQuantity()))
			    .collect(Collectors.toList());
			tempCampign.setCampaignProducts(filteredProducts);
			if(isCouponBased && couponItr.hasNext()) {
				tempCampign.getPromotionCoupon().setCouponCode(couponItr.next());
			}
			campaigns.add(tempCampign);
		});
		return campaigns;
	}

}
