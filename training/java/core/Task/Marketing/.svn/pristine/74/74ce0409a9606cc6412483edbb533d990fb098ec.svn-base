package com.medplus.marketing.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.discounts.domain.Slab;
import com.medplus.discounts.domain.UserMetaData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegularPromotion {

	private Long promotionId;
	private Long cloneReferenceId;
	private String promotionName;
	private String status;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private LocalDateTime effectiveDate;
	private List<Integer> discountType = Arrays.asList(PromotionConstants.SALE_TYPE_DISCOUNT);
	private List<Integer> channels;
	private String promotionLevel;
	private Integer applicableType;
	private boolean allCustomers;
	private boolean couponBased;
	private List<Integer> productCategoryIds;
	private List<Integer> loyalty;
	private List<String> regions;
	private PromotionCoupon promotionCoupon;
	private UserMetaData userMetaData;
	private List<Slab> slabs;
	private Integer slabGroupId;
	private List<CategoryDetail> categoryDetail;
	private Set<Long> customerIds = new HashSet<>();
	private Set<Long> removedCustomerIds = new HashSet<>();
}
