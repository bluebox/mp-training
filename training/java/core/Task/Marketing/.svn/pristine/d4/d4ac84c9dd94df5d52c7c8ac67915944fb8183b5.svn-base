package com.medplus.marketing.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.Pair;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.medplus.cache.MedplusCache;
import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.discounts.cache.PromotionCacheEvent;
import com.medplus.discounts.cache.RemovedItemEvent;
import com.medplus.discounts.constants.CouponApplicableMode;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.discounts.constants.ServiceChargeMode;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.constants.CampaignConstants;
import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignProduct;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.PromotionCoupon;
import com.medplus.marketing.exception.MarketingException;
import com.medplus.marketing.helper.ProductStoreHelper;
import com.medplus.marketing.service.PathLabTestsService;
import com.medplus.nontrade.service.master.constants.StatusEnum;
import com.medplus.product.domain.Product;
import com.medplus.product.domain.ProductInfo;
import com.medplus.product.domain.ProductSearchCriteria;
import com.medplus.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CampaignUtil {

	private static final int CLINET_SIZE = 20000;

	private CampaignUtil() {}
	
	public static void validateCampaignInfo(StringJoiner errorMessage, Campaign campaign, int toDateMaxLimit, boolean isUpdate) {
		if(UtilValidate.isEmpty(campaign.getCampaignId()) && isUpdate) 
			errorMessage.add("Invalid Campaign Id!");
		if(UtilValidate.isEmpty(campaign.getCampaignName())) {
			errorMessage.add("CampaignName cannot be empty");
		} else if(campaign.getCampaignName().length() > CampaignConstants.MAX_CAMPAIGN_NAME_LEN) {
			errorMessage.add("CampaignName exceeds the maximum length of 50 characters");
		}
		int applicableType = campaign.getPromotionApplicableType();
		if(UtilValidate.isEmpty(applicableType))
			errorMessage.add("ApplicableType cannot be empty");
		else if(applicableType != PromotionConstants.APPLICABLE_TYPE_PHARMACY &&
				applicableType != PromotionConstants.APPLICABLE_TYPE_PATHLABS &&
				applicableType != PromotionConstants.APPLICABLE_TYPE_LENS) {
			errorMessage.add("Invalid ApplicableType");
		}
		if(UtilValidate.isEmpty(campaign.getChannels()))
			errorMessage.add("Channels cannot be empty");
		if(!isUpdate && !"Y".equals(campaign.getAllCustomers()) && UtilValidate.isEmpty(campaign.getCustomerIds()))
			errorMessage.add("CustomerIds cannot be empty for Campaign");
		Integer campaignType = campaign.getCampaignType();
		if(UtilValidate.isEmpty(campaignType)) {
			errorMessage.add("Campaign Type cannot be empty");
		} else {
			if (campaign.getPromotionApplicableType() != PromotionConstants.APPLICABLE_TYPE_PHARMACY
					&& Integer.parseInt(PromotionConstants.SPECIAL_DISCOUNT) != campaignType) {
				errorMessage.add("Invalid Campaign Level");
			}
			if (Integer.parseInt(PromotionConstants.PROMOTION_ADDON) == campaignType
					&& (Objects.isNull(campaign.getMinInvoiceValue()) || campaign.getMinInvoiceValue() < 1
							|| campaign.getMinInvoiceValue() > 99999)) {
				errorMessage.add("MinInvoice value should be in between 1 to 99999");
			}
		}
		LocalDateTime fromDate = campaign.getFromDate();
		LocalDateTime toDate = campaign.getToDate();
		if(UtilValidate.isEmpty(fromDate))
			errorMessage.add("From Date for Campaign cannot be empty");
		if(UtilValidate.isEmpty(toDate))
			errorMessage.add("To Date for Campaign cannot be empty");
		if(UtilValidate.isNotEmpty(toDate) && (fromDate.isAfter(toDate)))
			errorMessage.add("ToDate can not be before FromDate!");
		if(UtilValidate.isNotEmpty(toDate) && toDate.isBefore(LocalDateTime.now()))
			errorMessage.add("ToDate can not be before the current timestamp!");
		long daysBetween = ChronoUnit.DAYS.between(fromDate.toLocalDate(), toDate.toLocalDate());
		if(daysBetween > toDateMaxLimit)
			errorMessage.add("ToDate cannot be greater than " + toDateMaxLimit + " days from FromDate");
		if(UtilValidate.isEmpty(campaign.getLoyaltyTypes()))
			errorMessage.add("Loyalty can not be empty!");
		
		if(UtilValidate.isEmpty(campaign.getUserMetaData()))
			errorMessage.add("Meta Info is mandatory for Campaign ");
		else {
			UserMetaData metaData = campaign.getUserMetaData();
			if(UtilValidate.isEmpty(metaData.getLongDescription()))
				errorMessage.add("Description cannot be empty");
			if(!isUpdate && UtilValidate.isEmpty(metaData.getCreatedBy()))
				errorMessage.add("CreatedBy can not be empty!");
			if(isUpdate && UtilValidate.isEmpty(metaData.getModifiedBy()))
				errorMessage.add("ModifiedBy can not be empty!");
		}
	}
	
	public static void validateCouponInfo(StringJoiner errorMessage, Campaign campaign) {
		if(CampaignConstants.COUPON_BASED.equals(campaign.getCouponBased())) {
			validateCoupon(campaign.getPromotionCoupon(), errorMessage, campaign.getPromotionApplicableType());
			serviceChargeValidation(campaign);
		}
	}
	
	public static void validateCoupon(PromotionCoupon coupon, StringJoiner errorMessage, Integer applicableType) {
		if(UtilValidate.isEmpty(coupon) || UtilValidate.isEmpty(coupon.getCouponCode()))
			throw new PromotionException("Coupon cannot be empty");
		if(applicableType==6) {
			if(coupon.getCouponCode().length() < 4 || coupon.getCouponCode().length() > 12)
				errorMessage.add("Coupon code must be of 4-12 characters");		
		}else {
			if(coupon.getCouponCode().length() < 4 || coupon.getCouponCode().length() > 10)
				errorMessage.add("Coupon code must be of 4-10 characters");		
		}
		if(!StringUtils.isAlphanumeric(coupon.getCouponCode()))
			errorMessage.add("Invalid Coupon Code");
		if(UtilValidate.isNotEmpty(coupon.getNoOfDays()) && (coupon.getNoOfDays() <= 0 || coupon.getNoOfDays() > 999))
			errorMessage.add("Invalid Number of Days For Coupon");
		if(UtilValidate.isEmpty(coupon.getCustomerLimit()) || coupon.getCustomerLimit() <= 0 || coupon.getCustomerLimit() > 999)
			errorMessage.add("Invalid Customer Limit");
		if(UtilValidate.isEmpty(coupon.getTotalLimit()) || coupon.getTotalLimit() <= 0 || coupon.getTotalLimit() > 99999999)
			errorMessage.add("Invalid Total limit");
		if(coupon.getCustomerLimit() != null && coupon.getTotalLimit() != null && coupon.getCustomerLimit() > coupon.getTotalLimit())
			errorMessage.add("Total limit should be greater than customer limit");
		if(coupon.isAddOnCoupon())
			validateAddonCoupon(coupon, errorMessage, applicableType);
	}
	
	private static void validateAddonCoupon(PromotionCoupon coupon, StringJoiner errorMessage, Integer applicableType) {
		if(UtilValidate.isEmpty(coupon.getMinValue()))
			errorMessage.add("Min Invoice Value for Add on coupon cannot be empty");
		else if(coupon.getMinValue() <= 0 || coupon.getMinValue() > 9999.99)
			errorMessage.add("Invalid Min Invoice value for Add on coupon");			
		if(UtilValidate.isEmpty(coupon.getMaxDiscount()))
			errorMessage.add("Max Discount Value for Add on coupon cannot be empty");
		else if(coupon.getMaxDiscount() <= 0 || coupon.getMaxDiscount() > 9999.99)
			errorMessage.add("Invalid Max Discount value for Add on coupon");	
		if((UtilValidate.isEmpty(coupon.getMaxPoints()) || coupon.getMaxPoints() <= 0 || coupon.getMaxPoints() > 9999.99 ) && applicableType==PromotionConstants.APPLICABLE_TYPE_PHARMACY)
			errorMessage.add("Invalid Max Points value for Add on coupon");
	}
	
	private static void serviceChargeValidation(Campaign campaign) {
		if(CampaignConstants.COUPON_BASED.equalsIgnoreCase(campaign.getCouponBased())) {
			PromotionCoupon promotionCoupon = campaign.getPromotionCoupon();
			Integer couponDiscountType = promotionCoupon.getCouponDiscountType();
			if(UtilValidate.isNotEmpty(promotionCoupon.isAddOnCoupon()) && promotionCoupon.isAddOnCoupon() && UtilValidate.isNotEmpty(couponDiscountType) &&
					!Objects.equals(couponDiscountType, PromotionConstants.PRODUCTS_DISC_TYPE))
				throw new PromotionException("Cannot configure service charges for Addon Coupon");
			if(UtilValidate.isNotEmpty(couponDiscountType) 
					&& (Objects.equals(couponDiscountType, PromotionConstants.SERVICE_CHARGES_DISC_TYPE) 
							||Objects.equals(couponDiscountType, PromotionConstants.PRODUCTS_AND_SERVICE_CHARGES_DISC_TYPE))) {
				if(Objects.equals(campaign.getPromotionApplicableType(), PromotionConstants.APPLICABLE_TYPE_LENS) && !Objects.equals(PromotionConstants.PRODUCTS_DISC_TYPE, couponDiscountType)){
					throw new PromotionException("Cannot configure service charges for Applicable Type Lens");
				} else {
					validateServiceCharge(campaign.getPromotionApplicableType(), promotionCoupon);
				}
			}
		}
	}
	
	private static void validateServiceCharge(int applicableType, PromotionCoupon promotionCoupon) {
		Set<ServiceChargeMode> serviceChargeModes = ServiceChargeMode.getServiceChargesByApplicableType(applicableType);
		boolean hasValuesEmpty = true;
		Map<ServiceChargeMode, Double> serviceChargeDiscounts = promotionCoupon.getServiceChargeDiscounts();
		if (UtilValidate.isEmpty(serviceChargeDiscounts) || serviceChargeDiscounts.keySet().contains(null)) {
			throw new PromotionException("Invalid Service Charges found.");
		}
		for (ServiceChargeMode mode : ServiceChargeMode.values()) {
			Double charge = serviceChargeDiscounts.get(mode);
			if (!serviceChargeModes.contains(mode) && charge != null) {
				throw new PromotionException("Invalid Service Charge Discounts Provided.");
			}
			  
			if(UtilValidate.isEmpty(charge) || charge == 0) {
				continue;
			}
		    if (charge > 100 || charge < 0) {
		        throw new PromotionException("Invalid " + mode.name() + " Provided.");
		    }
		    hasValuesEmpty = false;
		}
		if(hasValuesEmpty) {
	        throw new PromotionException("At least 1 service charge discount is required for creating campaign");
		}
	}
	
	public static CampaignSearchCriteria setCampaignSearchCriteria(CampaignSearchCriteria searchCriteria, List<Integer> applicableTypesByRole) {
		if(UtilValidate.isEmpty(searchCriteria) || (UtilValidate.isEmpty(searchCriteria.getCampaignId()) 
				&& UtilValidate.isEmpty(searchCriteria.getCampaignName()) 
				&& UtilValidate.isEmpty(searchCriteria.getApplicableTypes()) 
				&& UtilValidate.isEmpty(searchCriteria.getChannels()) 
				&& UtilValidate.isEmpty(searchCriteria.getStatus())  
				&& UtilValidate.isEmpty(searchCriteria.getToDate()) 
				&& UtilValidate.isEmpty(searchCriteria.getFromDate()) 
				&& UtilValidate.isEmpty(searchCriteria.getCreatedBy()) 
				&& UtilValidate.isEmpty(searchCriteria.getPromotionLevel())
				&& UtilValidate.isEmpty(searchCriteria.getCouponCode()))) {
			searchCriteria.setFromDate(LocalDateTime.now());
			searchCriteria.setToDate(LocalDateTime.now());
		}
		List<Integer> applicableTypes = searchCriteria.getApplicableTypes();
		if(UtilValidate.isNotEmpty(applicableTypes)) {
			applicableTypes = applicableTypesByRole.containsAll(applicableTypes) ? applicableTypes : new ArrayList<>();
		}else {
			applicableTypes = applicableTypesByRole;
		}
		searchCriteria.setApplicableTypes(applicableTypes);
		return searchCriteria;
	}
	
	public static void validateUserMetaData(UserMetaData userMetaData, StringJoiner errorMessage, boolean isUpdate) {
		if(UtilValidate.isEmpty(userMetaData)) 
			errorMessage.add("MetaInfo can not be empty!");
		else if(!isUpdate && (userMetaData.getDateCreated() == null || UtilValidate.isEmpty(userMetaData.getCreatedBy())))
			errorMessage.add("MetaInfo for creation can not be empty!");
		else if(isUpdate && (userMetaData.getDateModified() == null || UtilValidate.isEmpty(userMetaData.getModifiedBy())))
			errorMessage.add("MetaInfo for updation can not be empty!");
	}
	
    public static void validateName(StringJoiner errorMessage, String headerName, String name, long minLength, long maxLength, Set<String> omittedCharacters, String regex) {
    	if (UtilValidate.isEmpty(name) || name.length() < minLength || name.length() > maxLength) {
            errorMessage.add(headerName + " must be in between " + minLength + " and " + maxLength + " characters");
        } else if (omittedCharacters.contains(String.valueOf(name.charAt(0)))) {
            errorMessage.add(headerName + " cannot start with any of the following characters: " + omittedCharacters.toString());
        } else if (!name.matches(regex)) {
            errorMessage.add("Please enter valid characters for " + headerName);
        }
    }
    
	public static void validateDates(StringJoiner errorMessage, LocalDateTime fromDate, LocalDateTime toDate,int toDateMaxLimit, boolean isUpdate) {
		if(UtilValidate.isEmpty(fromDate))
			errorMessage.add("From Date for Promotion cannot be empty");
		if(UtilValidate.isEmpty(toDate))
			errorMessage.add("To Date for Promotion cannot be empty");
		if(UtilValidate.isNotEmpty(toDate) && (!fromDate.isBefore(toDate)))
			errorMessage.add("ToDate can not be before FromDate!");
		if(UtilValidate.isNotEmpty(fromDate) && fromDate.isBefore(LocalDateTime.now()) && !isUpdate)
			errorMessage.add("FromDate can not be before the current timestamp!");
		if(UtilValidate.isNotEmpty(toDate) && UtilValidate.isNotEmpty(fromDate)) {
			long daysBetween = ChronoUnit.DAYS.between(fromDate.toLocalDate(), toDate.toLocalDate());
			if(daysBetween > toDateMaxLimit)
				errorMessage.add("ToDate cannot be greater than "+toDateMaxLimit+" days from FromDate");
		}
	}
	
	public static void validatePromoRegions(List<String> regions, int applicableType,
			ProductStoreHelper productStoreHelper, boolean isUpdate) {
		if (UtilValidate.isEmpty(regions)) {
			throw new PromotionException("Regions for Promotion cannot be empty");
		} else if(!isUpdate) {
			productStoreHelper.validateStores(
					regions.stream().filter(region -> region.length() == 12).collect(Collectors.toSet()),
					applicableType, "Region Store");
		}
	}
	
	public static int getCouponCodeMaxLen(int applicabelType) {
		return applicabelType == PromotionConstants.APPLICABLE_TYPE_PATHLABS ? 12 : 10;
	}
	
    public static LocalDateTime parseMillisToLocalDateTime(String millisString) {
		try {
			return Instant.ofEpochMilli(Long.parseLong(millisString)).atZone(ZoneId.systemDefault()).toLocalDateTime();
        } catch (NumberFormatException e) {
			throw new MarketingException("ToDate is not a valid date");
		}
    }

	public static ResponseEntity<ByteArrayResource> downloadExcel(Set<?> data, String header, String contentType, String attachmentFilename) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", contentType));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + attachmentFilename);
        if (data != null) {
            log.debug(attachmentFilename+" download Size: {}", data.size());
            byte[] excelData = ExcelUtil.writeExcel(data, header, null);
            return new ResponseEntity<>(new ByteArrayResource(excelData), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ByteArrayResource(new byte[0]), headers, HttpStatus.OK);
        }
    }
	
	public static boolean validateProductId(String productId, int applicableType) {
		return (UtilValidate.isEmpty(productId) ||  productId.contains("'") || productId.contains("\"") || productId.contains("“") || productId.contains("”")) 
				|| (applicableType != PromotionConstants.APPLICABLE_TYPE_PATHLABS && productId.length() != 8)
				|| (applicableType == PromotionConstants.APPLICABLE_TYPE_PATHLABS && productId.length() != 7);
	}
	
	public static void clearPromotionCache(MedplusCache eventPublisher, boolean firePromoCache) {
		try {
			log.info("Promotion Cache Clearing event trigerred at {}", new Date());
			eventPublisher.fireClearEvent(new RemovedItemEvent(""));
			if(firePromoCache) 
				eventPublisher.fireClearEvent(new PromotionCacheEvent(""));
		} catch (Exception e) {
			log.error("Error triggering promotion cache clearing event : {}", e.getMessage(), e);
		}	
	}
	
	public static Map<String,String> getProductMap(Set<String> productIds, String type, ProductService productService,boolean isActiveCheck) {
	    if(UtilValidate.isEmpty(productIds))
	    	return new HashMap<>();
		ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria();
		productSearchCriteria.setIsRunOnDb(false);
		if(isActiveCheck) {
			productSearchCriteria.setProductStatus(StatusEnum.ACTIVE);
		}
		List<String> productIdsList = new ArrayList<>(productIds);

	    int size = productIdsList.size();
	    Map<String,String> productInfoMap =  new HashMap<>();
	    for (int i = 0; i < size; i += CLINET_SIZE) {
			try {
	    	    Set<String> subProductIds = new HashSet<>(productIdsList.subList(i, Math.min(size, i+CLINET_SIZE)));
				log.info(i/CLINET_SIZE + " th call to get product Info , subProductIds size : {}", subProductIds.size());
	    	    productSearchCriteria.setProductIdList(subProductIds);

				ProductInfo prodInfo = productService.getProducts(productSearchCriteria);
				if (UtilValidate.isNotEmpty(prodInfo)) {
		            productInfoMap.putAll(prodInfo.getProductList().stream().collect(Collectors.toMap(Product::getProductId, Product::getName)));
		        }
			} catch (Exception e) {
				log.error("Exception caught while get the " + type + " from solr {}");
				throw new PromotionException("Unable to get the " + type + " !");
			}
	    }
		return productInfoMap;
	}
	
	public static <T> void validateIds(Set<T> currentIds, Set<T> validatedIds, String type) {
		Set<T> ids = new HashSet<>(currentIds);
		ids.removeAll(validatedIds);
		if(UtilValidate.isNotEmpty(ids)) {
			Iterator<T> idIterator = ids.iterator();
			String invalidId = "";
			if(idIterator.hasNext()) {
				invalidId = idIterator.next().toString();
			}
			StringBuilder sb=new StringBuilder();
			sb.append("The IDs "+ invalidId);
			if(ids.size() > 1) {
				sb.append(" and " + (ids.size()-1) + " Others");
			}
			throw new PromotionException(sb.toString() + " of type " + type + " are not available or in InActive status");
		}
	}
	
	public static Map<String, String> getPathLabTestMap(List<String> uplodedTests,PathLabTestsService pathLabTestsService, boolean isSpecialityBased, boolean isActiveCheck) {
		if (UtilValidate.isEmpty(uplodedTests))
			return new HashMap<>();
		Map<String, String> activePathTests = new HashMap<>();
		try {
			if (isSpecialityBased) {
				activePathTests = pathLabTestsService.getSpecialtyIds(uplodedTests, isActiveCheck);
			} else {
				activePathTests = pathLabTestsService.getPathTestByIdList(uplodedTests, isActiveCheck);
			}
		} catch (Exception ex) {
			log.error("Exception caught while getting the path lab test info : ", ex);
			throw new PromotionException("Unable to get the path lab tests !");
		}
		return activePathTests;
	}
	
	public static Map<String,String> getCampaignIDsInfo(Set<String> ids, int applicableType, boolean isSpecialityBased,PathLabTestsService pathLabTestsService, ProductService productService, boolean isActiveCheck) {
		if(UtilValidate.isEmpty(ids)) {
			return new HashMap<>();
		}
		if(PromotionConstants.APPLICABLE_TYPE_PATHLABS == applicableType){
			return getPathLabTestMap(new ArrayList<>(ids), pathLabTestsService, isSpecialityBased, isActiveCheck);
		}else{
			return getProductMap(ids, "Product/Optical IDs", productService, isActiveCheck);				
		}
	}
	
	public static String getProdIdFromCell(Cell cell) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
		return cell.getStringCellValue().toUpperCase().trim();
	}
	
	public static Set<String> getCampaignProducts(List<CampaignProduct> campaignProducts, int campaignType) {
		Set<String> campaignProductsSet = new HashSet<>();
		if (UtilValidate.isNotEmpty(campaignProducts)) {
			if (campaignType == 5 ) {
				for (CampaignProduct campaignProduct : campaignProducts) {
					campaignProductsSet.add(campaignProduct.getProductId());
					campaignProductsSet.add(campaignProduct.getToProductId());
				}
			}else if(campaignType == 6) {
				for (CampaignProduct campaignProduct : campaignProducts) {
					campaignProductsSet.add(campaignProduct.getProductId());
					List<String> toProductIds = Arrays.asList(campaignProduct.getToProductId().split(","));
					toProductIds.replaceAll(String::trim);
					campaignProduct.setToProductId(org.springframework.util.StringUtils.
							collectionToCommaDelimitedString(toProductIds));
					campaignProductsSet.addAll(toProductIds);
				}
			} else {
				for (CampaignProduct campaignProduct : campaignProducts) {
					campaignProductsSet.add(campaignProduct.getProductId());
				}
			}
		}
		return campaignProductsSet;
	}

	public static LocalDateTime getDateFromMap(Object dateObject) {
		if(dateObject==null)
			return null;
		String className = dateObject.getClass().getName();
		LocalDateTime date = null;
		if("java.sql.Timestamp".equals(className)) {
			date = ((java.sql.Timestamp) dateObject).toLocalDateTime();
		} else if("java.time.LocalDateTime".equals(className)) {
			date = (LocalDateTime) dateObject;
		}
		return date;
	}
	
	public static boolean validateToDate(Map<String, Object> headerDetails, String function, List<Integer> applicableTypes, LocalDateTime toDate, Integer toDateMaxLimit, String campaignType) {
		
		boolean isRegularProm = "RP".equalsIgnoreCase(campaignType);
		String campaign = isRegularProm || "COMPLIMENTARY".equalsIgnoreCase(campaignType) ? "promotion" : "campaign";
		int applicableType = Integer.parseInt(String.valueOf(headerDetails.get("ApplicableType")));
		if(UtilValidate.isEmpty(applicableTypes) || !applicableTypes.contains(applicableType)) {
			throw new PromotionException("User doesn't have the rights to " + function + " " + campaign + " of the applicable type!");
		}
		
		if(!isRegularProm && UtilValidate.isEmpty(toDate)) {
			throw new PromotionException("To Date can not be empty!");
		}
		Pair<String, String> dateFields = getDateFields(campaignType);
		String fromDateField = dateFields.getKey();   
		String toDateField = dateFields.getValue();   
		LocalDateTime fromDate = getDateFromMap(headerDetails.get(fromDateField));
		LocalDateTime existingToDate = getDateFromMap(headerDetails.get(toDateField));
		if((!isRegularProm && existingToDate==null) || fromDate==null) {
			throw new PromotionException("Could not " + function + " " + campaign + "!");
		}
		if(UtilValidate.isNotEmpty(existingToDate) && !LocalDateTime.now().isBefore(existingToDate)) {
			throw new PromotionException("ToDate cannot be updated for an expired "+ campaign + "!");
		}
		if(!isRegularProm || UtilValidate.isNotEmpty(toDate)) {
			if(!isRegularProm && (fromDate.plusDays(toDateMaxLimit).isBefore(toDate))){
				throw new PromotionException("ToDate cannot be greater than "+toDateMaxLimit+" days from FromDate!");
			}
			if(toDate.isBefore(fromDate)) {
				throw new PromotionException("ToDate cannot be before fromDate!");
			}
			if(toDate.isBefore(LocalDateTime.now())) {
				throw new PromotionException("ToDate cannot be before current timestamp!");
			}
			
		}
		return !Objects.equals(toDate, existingToDate);
		
	}
	
	public static Pair<String, String> getDateFields(String campaignType) {
		String fromDateField = "" ;
		String toDateField = "";
		switch(campaignType) {
			case "CAMPAIGN" : 
				fromDateField = "DateFrom";
				toDateField = "DateTo";
				break;
			case "MIC" : 
				fromDateField = "FromDate";
				toDateField = "ToDate";
				break;
			default : 
				fromDateField = "ValidFrom";
				toDateField = "ValidTo";
				break;
		}
        return  new Pair<>(fromDateField, toDateField);
	}
	
	
	public static void setCouponModes(PromotionCoupon promotionCoupon, Map<CouponApplicableMode, List<String>> couponApplicableModes){
		if (couponApplicableModes != null) {
			for(Entry<CouponApplicableMode, List<String>> mode : couponApplicableModes.entrySet()){
				switch(mode.getKey()){
				case DELIVERY_TYPE: promotionCoupon.setDeliveryType(mode.getValue());
				break;
				case E_PRESCRIPTION:promotionCoupon.setPrescription(mode.getValue());
				break;
				case PAYMENT_MODE:promotionCoupon.setPaymentType(mode.getValue());
				break;
				}
			}
		}
	}
	
	public static List<String> getAllValues(int existPromotiontype, int promotionType, List<String> deliveryType, List<String> prescription, List<String> paymentType){
		List<String> allValues = new ArrayList<>();
		if(promotionType == existPromotiontype) {
			if (UtilValidate.isNotEmpty(deliveryType)) {
				allValues.addAll(deliveryType);
			}
			if (UtilValidate.isNotEmpty(prescription)) {
				allValues.addAll(prescription);
			}
			if (UtilValidate.isNotEmpty(paymentType)) {
				allValues.addAll(paymentType);
			}
		}
		return allValues;
	}
	
	public static void checkConflictPriceSlabs(Map<String, Map<String, String>> grpProdSlabInfo,
			CampaignProduct campProd, boolean isGroupByStore) {
		String grpKey = isGroupByStore ? campProd.getStoreId() : campProd.getProductId();
		grpProdSlabInfo.computeIfAbsent(grpKey, k -> new HashMap<>()).merge(campProd.getProductId(),
				campProd.getPriceConsiderForSlab() == null ? "S" : campProd.getPriceConsiderForSlab(),
				(oldSlab, newSlab) -> {
					if (!oldSlab.equals(newSlab)) {
						throw new PromotionException(String.format(
								"Multiple PriceConsiderForSlab values found for productID %s against %s %s group",
								campProd.getProductId(), isGroupByStore ? "store" : "product", grpKey));
					}
					return oldSlab;
				});
	}
	
}

