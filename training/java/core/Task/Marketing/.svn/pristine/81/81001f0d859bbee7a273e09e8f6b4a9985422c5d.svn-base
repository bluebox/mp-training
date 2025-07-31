package com.medplus.marketing.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.medplus.discounts.domain.UserMetaData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campaign implements Serializable {

	private static final long serialVersionUID = -7715481136415640065L;
	
	private Long campaignId;
	private Long templateId;
	private Long cloneReferenceId;
	private String campaignName;
	private Integer campaignType;
	private Integer promotionApplicableType;
	private String status;
	private String globalCampaignStatus;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private LocalDateTime effectiveDate;
	private PromotionCoupon promotionCoupon;
	private String allCustomers;
	private String existingAllCustomers;
	private String couponBased;
	private String existingCouponBased;
	private Character flag = 'N';
	private Double minInvoiceValue; 
	private List<CampaignProduct> campaignProducts = new LinkedList<>();
	private List<Integer> channels = new LinkedList<>();
	private List<Integer> loyaltyTypes = new LinkedList<>();
	private Set<Long> customerIds = new HashSet<>();
	private Map<String, String> regionsMap = new HashMap<>();
	private Set<String> removedProducts;
	private Set<Long> removedCustomerIds;
	private UserMetaData userMetaData ;
	private String splitBy;
}
