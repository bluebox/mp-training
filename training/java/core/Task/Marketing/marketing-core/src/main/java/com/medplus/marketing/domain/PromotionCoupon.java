package com.medplus.marketing.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.discounts.constants.ServiceChargeMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionCoupon implements Serializable {

	private static final long serialVersionUID = 3292188887501885840L;	
	
	private Long couponId;
	private String couponCode;
	private Long promotionId;
	private Integer promotionType = PromotionConstants.SALE_TYPE_DISCOUNT;
	private Integer applicableType;
	private boolean allCustomers;
	private Long totalLimit;
	private Long customerLimit;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private String status;
	private Double minValue;
	private Double maxDiscount;
	private String createdBy;
	private LocalDateTime dateCreated;
	private boolean addOnCoupon;
	private Double maxPoints;
	
	private List<String> deliveryType = new LinkedList<>();
	private List<String> paymentType = new LinkedList<>();
	private List<String> prescription = new LinkedList<>();
	
	private Integer noOfDays;
	private Map<ServiceChargeMode, Double> serviceChargeDiscounts; 
	private Integer couponDiscountType = PromotionConstants.PRODUCTS_DISC_TYPE;

}
