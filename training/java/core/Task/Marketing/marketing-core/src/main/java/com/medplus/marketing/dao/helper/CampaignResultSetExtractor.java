/**
 * 
 */
package com.medplus.marketing.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.domain.Campaign;


public class CampaignResultSetExtractor implements ResultSetExtractor<Campaign> {

	private static final String PROMO_VISIBLE = "PromotionVisible";

	@Override
	public Campaign extractData(ResultSet rs) throws SQLException{
		Campaign campaign = new Campaign();
		Set<Integer> loyaltyTypes = new HashSet<>();
		Map<String, String> regionsMap = new HashMap<>();
		int i = 0;
		while(rs.next()){
			if(i++ == 0){
				campaign.setCampaignId(rs.getLong("CampaignID"));
				campaign.setTemplateId(rs.getLong("TemplateID"));
				campaign.setCampaignName(rs.getString("Name"));
				campaign.setCampaignType(rs.getInt("CampaignType"));
				campaign.setFromDate(rs.getObject("DateFrom", LocalDateTime.class));
				campaign.setToDate(rs.getObject("DateTo", LocalDateTime.class));
				campaign.setEffectiveDate(rs.getObject("EffectiveDate", LocalDateTime.class));
				campaign.setStatus(rs.getString("Status"));	
				campaign.setPromotionApplicableType(rs.getInt("ApplicableType"));
				campaign.setCouponBased(rs.getString("CouponBased"));
				campaign.setExistingCouponBased(rs.getString("CouponBased"));
				campaign.setAllCustomers(rs.getString("AllCustomers"));
				campaign.setExistingAllCustomers(rs.getString("AllCustomers"));
				UserMetaData userMetaData = new UserMetaData();
				userMetaData.setSpecialtyBased(rs.getBoolean("SpecialtyBased"));
				userMetaData.setClaimable(rs.getBoolean("claimable"));
				userMetaData.setLongDescription(rs.getString("Description"));
				String imagePath = rs.getString("ImagePath");
				userMetaData.setImagePath(UtilValidate.isNotEmpty(imagePath)? imagePath :"");
				String imageServerName = rs.getString("ImageServerName");
				userMetaData.setImageServerName(UtilValidate.isNotEmpty(imageServerName) ? imageServerName : "");
				userMetaData.setRemarks(rs.getString("Remarks"));
				userMetaData.setPromotionVisible("Y".equalsIgnoreCase(rs.getString(PROMO_VISIBLE)));
				campaign.setUserMetaData(userMetaData);
				campaign.setMinInvoiceValue(rs.getDouble("MinInvoiceValue"));
				campaign.setFlag(UtilValidate.isNotEmpty(rs.getString(PROMO_VISIBLE))?rs.getString(PROMO_VISIBLE).charAt(0):'N');
			}
			loyaltyTypes.add(rs.getInt("Loyality"));
			String notEligibleStore = rs.getString("NotEligibleStore");
			regionsMap.put(rs.getString("Value"), UtilValidate.isEmpty(notEligibleStore) ? notEligibleStore : notEligibleStore.toUpperCase());
		}
		campaign.setLoyaltyTypes(new ArrayList<>(loyaltyTypes));
		campaign.setRegionsMap(regionsMap);
		return campaign;
	}

}
