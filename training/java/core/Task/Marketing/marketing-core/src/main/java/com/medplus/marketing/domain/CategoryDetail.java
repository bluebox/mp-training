package com.medplus.marketing.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDetail {

	private Long discountCategoryId;
	private Long slabId;
	private Long loyaltyId;
	private Long productDiscountCategoryId;
	private String region;
	private Double discountPercentage;
	private Double paybackPercentage;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private String status;
	private boolean couponBased;
	private boolean allCustomers;
	
	
	
	public CategoryDetail(Long productDiscountCategoryId, Long loyaltyId, String region) {
		super();
		this.productDiscountCategoryId = productDiscountCategoryId;
		this.loyaltyId = loyaltyId;
		this.region = region;
	}
	
	public CategoryDetail(Long discountCategoryId, Long slabId, Double discountPercentage, Double paybackPercentage,
			String status, boolean couponBased, boolean allCustomers) {
		super();
		this.discountCategoryId = discountCategoryId;
		this.slabId = slabId;
		this.discountPercentage = discountPercentage;
		this.paybackPercentage = paybackPercentage;
		this.status = status;
		this.couponBased = couponBased;
		this.allCustomers = allCustomers;
	}
	
}
