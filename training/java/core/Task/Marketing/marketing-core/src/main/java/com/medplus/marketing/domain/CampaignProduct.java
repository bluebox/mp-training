package com.medplus.marketing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignProduct {

	private String storeId;
	private Long templateId;
	private String productId;
	private Integer discountType;
	private Double discountValue;
	private Integer fromQuantity;
	private Integer toQuantity;
	private String toProductId;
	private String displayMessage;
	private Double paybackPercentage;
	private String priceConsiderForSlab;
	
}
