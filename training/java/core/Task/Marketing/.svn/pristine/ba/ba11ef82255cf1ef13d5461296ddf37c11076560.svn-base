package com.medplus.marketing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDetail {

	private Long campaignId;
	private Integer loyaltyType;
	private String region; 
	private String notEligibleRegion; 
	
	public int getConditionType(){
		int length = region.length();
		switch (length) {
		case 2:
			return 0;
		case 4:
			return 1;
		case 7:
			return 2;
		case 12:
			return 3;
		default:
			return 4;
		}
	}
}
