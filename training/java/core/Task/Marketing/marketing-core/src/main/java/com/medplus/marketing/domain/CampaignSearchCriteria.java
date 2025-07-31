package com.medplus.marketing.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CampaignSearchCriteria {

	private Integer campaignId;
	private String campaignName;
	private String campaignType;
	private List<Integer> applicableTypes;
	private List<Integer> channels;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private String status;
	private String createdBy;
	private String couponCode;
	private String promotionLevel;
	private Integer offset=0;
	private Integer limit=100;
	
}
