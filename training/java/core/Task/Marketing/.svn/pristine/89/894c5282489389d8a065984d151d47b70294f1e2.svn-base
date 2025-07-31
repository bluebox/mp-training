package com.medplus.marketing.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.medplus.discounts.domain.ComplimentaryProduct;
import com.medplus.discounts.domain.UserMetaData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplimentaryPromotion implements Serializable{
	private static final long serialVersionUID = -2067611895196818422L;
	
	private long complimentaryId;
	private String name;
	private String status;
	private Long cloneReferenceId;
	private UserMetaData userMetaData;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private LocalDateTime effectiveDate;
	private int applicableType;
	private List<Integer> channels;
	ComplimentarySlab complimentarySlab;
	private double messageDisplayPercentage;
	private List<Integer> loyalties;
	private List<String> regions;
	private boolean allCustomers;
	private Set<Long> customerIds;
	private Set<ComplimentaryProduct> complimentaryProducts;
	private Set<String> referenceProductIds;
	private Set<Integer> compositionIds;
	
	private Set<String> removeComplimentaryProducts;

	private Set<String> removeReferenceProductIds;
	private Set<Integer> removeCompositionIds;
	private Set<Long> removeCustomerIds;
}
