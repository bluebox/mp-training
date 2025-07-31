package com.medplus.marketing.dao.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.domain.ComplimentaryPromotion;
import com.medplus.marketing.domain.ComplimentarySlab;

public class ComplimentaryPromotionExtractor implements ResultSetExtractor<ComplimentaryPromotion> {

	@Override
	public ComplimentaryPromotion extractData(ResultSet rs) throws SQLException {
		ComplimentaryPromotion promotion = null;
		Set<Integer> loyaltyTypes= new HashSet<>();
		Set<String> regions = new HashSet<>();
		Set<Integer> channels = new HashSet<>();

		boolean isFirstRecord = true;
		while(rs.next()){
			if(isFirstRecord) {
				promotion = new ComplimentaryPromotion();
				promotion.setComplimentaryId(rs.getInt("ComplimentaryId"));
				promotion.setName(rs.getString("Name"));
				promotion.setApplicableType(rs.getInt("ApplicableType"));
				promotion.setFromDate(rs.getObject("ValidFrom", LocalDateTime.class));
				promotion.setToDate(rs.getObject("ValidTo", LocalDateTime.class));
				promotion.setEffectiveDate(rs.getObject("EffectiveDate", LocalDateTime.class));
				promotion.setStatus(rs.getString("Status"));
				promotion.setAllCustomers(rs.getInt("AllCustomers") == 1);
				promotion.setMessageDisplayPercentage(rs.getDouble("InvoiceValuePercentageForMessage"));
				String cloneRefId = rs.getString("CloneReferenceId");
				if(UtilValidate.isNotEmpty(cloneRefId))
					promotion.setCloneReferenceId(Long.parseLong(cloneRefId));
				UserMetaData metaData = new UserMetaData();
				metaData.setRemarks(rs.getString("Remarks"));
				metaData.setCreatedBy(rs.getString("CreatedBy"));
				metaData.setDateCreated(rs.getObject("DateCreated", LocalDateTime.class));
				metaData.setModifiedBy(rs.getString("ModifiedBy"));
				metaData.setDateModified(rs.getObject("DateModified", LocalDateTime.class));
				metaData.setApprovedBy(rs.getString("ApprovedBy"));
				metaData.setDateApproved(rs.getObject("DateApproved", LocalDateTime.class));
				promotion.setUserMetaData(metaData);
				ComplimentarySlab complimentarySlab = new ComplimentarySlab();
				complimentarySlab.setComplimentarySlabId(rs.getLong("ComplimentarySlabId"));
				complimentarySlab.setName(rs.getString("SlabName"));
				complimentarySlab.setInvoiceAmount(rs.getDouble("InvoiceAmount"));
				promotion.setComplimentarySlab(complimentarySlab);
				isFirstRecord = false;
			}
			loyaltyTypes.add(rs.getInt("LoyaltyType"));
			regions.add(rs.getString("Region"));
			channels.add(rs.getInt("Channel"));
		}
		if(!isFirstRecord) {
			promotion.setLoyalties(new ArrayList<>(loyaltyTypes));
			promotion.setRegions(new ArrayList<>(regions));
			promotion.setChannels(new ArrayList<>(channels));
		}
		return promotion;
	
	}
	

}
