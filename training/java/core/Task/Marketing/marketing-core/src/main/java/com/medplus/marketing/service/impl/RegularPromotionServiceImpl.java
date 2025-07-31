package com.medplus.marketing.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medplus.cache.MedplusCache;
import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.discounts.domain.Slab;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.constants.CampaignConstants;
import com.medplus.marketing.dao.RegularPromotionDao;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.PromotionCoupon;
import com.medplus.marketing.domain.RegularPromotion;
import com.medplus.marketing.domain.SlabGroup;
import com.medplus.marketing.helper.PosMasterDataHelper;
import com.medplus.marketing.helper.ProductStoreHelper;
import com.medplus.marketing.service.RegularPromotionService;
import com.medplus.marketing.util.CampaignUtil;
import com.medplus.pos.constant.PosMasterDataKey;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegularPromotionServiceImpl implements RegularPromotionService{

	@Autowired
	RegularPromotionDao regularPromotionDao;
	
	@Autowired
	ProductStoreHelper productStoreHelper;
	
	@Autowired
	private PosMasterDataHelper posMasterDataHelper;
	
	@Autowired
	MedplusCache eventPublisher;
	
	private static final String UNABLE_TO_FETCH_DATA = "Unable to Fetch Data";

	private static final Object STATUS = "Status";
	
	@Override
	public int checkSlabGroupAssignOrNot(Long slabGroupId) {
		try {
			return regularPromotionDao.checkSlabGroupIsAssignedToPromotionOrNot(slabGroupId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PromotionException(UNABLE_TO_FETCH_DATA, e);
		}
	}
	
	@Override
	public Map<String, String> getExisitingSlabGroups() {
		try {
			return regularPromotionDao.getExisitingSlabGroups();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new PromotionException(UNABLE_TO_FETCH_DATA, e);
		}
	}
	
	@Override
	public Map<String,String> getInvoiceCategoryTypes() {
		try {
			return regularPromotionDao.getInvoiceCategoryTypes();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new PromotionException(UNABLE_TO_FETCH_DATA, e);
		}
		}
	
	@Override
	public List<Slab> getSlabsInfoForSlabGroup(Long slabGroupId) {
		try {
			return regularPromotionDao.getSlabsInfoForSlabGroup(slabGroupId);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new PromotionException(UNABLE_TO_FETCH_DATA, e);
		}
	}

	@Override
	@Transactional("posTransactionManager")
	public SlabGroup insertSlabsAndSlabGroups(SlabGroup slabGroup) {
	
			log.info("regularPromotion.getSlabGroupModified() : {}", slabGroup.isSlabGroupModified());
			if (UtilValidate.isNotEmpty(slabGroup)) {
				if (UtilValidate.isNotEmpty(slabGroup.getSlabGroupId())) {
					if (slabGroup.isSlabGroupModified())
						regularPromotionDao.updateSlabGroup(slabGroup);
					else
						regularPromotionDao.findSlabIds(slabGroup);
				} else if (UtilValidate.isNotEmpty(slabGroup.getNewSlabGroupName())) {
					regularPromotionDao.insertSlabsAndSlabGroups(slabGroup);
				}
			}
			return slabGroup;
	}
	
	

	private void validateRegularPromotion(RegularPromotion regularPromotion, boolean isUpdate) {
		StringJoiner errorMessage = new StringJoiner(",");
		if(UtilValidate.isEmpty(regularPromotion))
			throw new PromotionException("Invalid Regular Promotion");
		if(UtilValidate.isEmpty(regularPromotion.getPromotionId()) && isUpdate)
			errorMessage.add("Invalid PromotionId");
		if(UtilValidate.isEmpty(regularPromotion.getPromotionName()))
			errorMessage.add("Promotion Name cannot be empty");
		if(UtilValidate.isEmpty(regularPromotion.getChannels()))
			errorMessage.add("Channels cannot be empty");
		if(UtilValidate.isEmpty(regularPromotion.getApplicableType()))
			errorMessage.add("ApplicableType cannot be empty");
		if(UtilValidate.isEmpty(regularPromotion.getPromotionLevel()))
			errorMessage.add("Promotion Level cannot be empty");
		else if(!(PromotionConstants.CATEGORY_PROMOTION_LEVEL.equalsIgnoreCase(regularPromotion.getPromotionLevel()) ||
				PromotionConstants.INVOICE_PROMOTION_LEVEL.equalsIgnoreCase(regularPromotion.getPromotionLevel())))
			errorMessage.add("Invalid Promotion Level");
		LocalDateTime fromDate = regularPromotion.getFromDate();
		LocalDateTime toDate = regularPromotion.getToDate();
		if(UtilValidate.isEmpty(fromDate))
			errorMessage.add("From Date for Promotion cannot be empty");
		if(UtilValidate.isNotEmpty(toDate) && (fromDate.isAfter(toDate)))
			errorMessage.add("ToDate can not be before FromDate!");
		if(UtilValidate.isNotEmpty(toDate) && toDate.isBefore(LocalDateTime.now()))
			errorMessage.add("ToDate can not be before the current timestamp!");
		if(UtilValidate.isEmpty(regularPromotion.getSlabGroupId()))
			errorMessage.add("Slab Group Id cannot be empty");
		if(UtilValidate.isEmpty(regularPromotion.getSlabs()))
			errorMessage.add("Slabs cannot be empty");
		else {
			Double prevToValue = null;
			for (Slab slab : regularPromotion.getSlabs()) {
				Double fromValue = slab.getFromValue();
				Double toValue = slab.getToValue();
				if(UtilValidate.isEmpty(fromValue) || UtilValidate.isEmpty(toValue) || toValue < 0 || toValue > 999999)
					throw new PromotionException("Invalid Slabs provided");
				if(fromValue < 0 || fromValue > 999999)
					errorMessage.add("From value for slabId : "+slab.getSlabId()+" should be between 0 and 999999");
				if(toValue < 0 || toValue > 999999)
					errorMessage.add("To value for slabId : "+slab.getSlabId()+" should be between 0 and 999999");
				if(fromValue > toValue)
					errorMessage.add("From value must be less than to value for slabId : "+slab.getSlabId());
				else if (prevToValue != null && !Objects.equals(fromValue, prevToValue)) {
			        errorMessage.add("From Value must be equal to previous To Value for slabId : "+slab.getSlabId());
			    }	
			    prevToValue = toValue;
			}
		}
		if(UtilValidate.isEmpty(regularPromotion.getLoyalty()))
			errorMessage.add("Loyalty for Regular Promotion cannot be empty");
		CampaignUtil.validatePromoRegions(regularPromotion.getRegions(), regularPromotion.getApplicableType(), productStoreHelper, isUpdate);
		if(UtilValidate.isEmpty(regularPromotion.getUserMetaData()))
			errorMessage.add("Meta Info is mandatory for Regular Promotion");
		else {
			UserMetaData metaData = regularPromotion.getUserMetaData();
			if(UtilValidate.isEmpty(metaData.getLongDescription()))
				errorMessage.add("Description cannot be empty");
			if(!isUpdate && UtilValidate.isEmpty(metaData.getCreatedBy()))
				errorMessage.add("CreatedBy can not be empty!");
			if(isUpdate && UtilValidate.isEmpty(metaData.getModifiedBy()))
				errorMessage.add("ModifiedBy can not be empty!");
		}
		if(regularPromotion.isCouponBased()) {
			CampaignUtil.validateCoupon(regularPromotion.getPromotionCoupon(), errorMessage, regularPromotion.getApplicableType());
			if(regularPromotion.getPromotionCoupon().getCouponDiscountType() != null && regularPromotion.getPromotionCoupon().getCouponDiscountType() != PromotionConstants.PRODUCTS_DISC_TYPE)
				errorMessage.add("Invalid Coupon Discount Type");
			if(!isUpdate && !regularPromotion.isAllCustomers() && UtilValidate.isEmpty(regularPromotion.getCustomerIds()))
				errorMessage.add("CustomerIds cannot be empty for Regular Promotion");
		}
		if(UtilValidate.isNotEmpty(errorMessage.toString()))
			throw new PromotionException(errorMessage.toString());
	}
	
	@Override
	public RegularPromotion insertRegularPromotion(RegularPromotion regularPromotion) {
		validateRegularPromotion(regularPromotion, false);
		return regularPromotionDao.insertPromotionDetailsAndDiscategorySlabMapping(regularPromotion);
	}
	
	@Override
	@Transactional("posTransactionManager")
	public boolean approveRegularPromotion(Long promotionId, LocalDateTime toDate, String approvedBy) {
		if(UtilValidate.isEmpty(promotionId))
			return false;
		Map<String, Object> headerDetails = regularPromotionDao.getRegularPromotionHeaderDetails(promotionId);
		String status = String.valueOf(headerDetails.get(STATUS));
		if(!PromotionConstants.INACTIVE.equals(status))
			throw new PromotionException("Can not approve promotion with status "+status);
		boolean isDateUpdated = CampaignUtil.validateToDate(headerDetails, "approve", Arrays.asList(PromotionConstants.APPLICABLE_TYPE_PHARMACY), toDate, null, "RP");
		try {
			regularPromotionDao.approveRegularPromotion(promotionId, toDate, approvedBy, isDateUpdated, headerDetails);
			pushToPosMaster(promotionId, headerDetails.get("CouponBased"));
			if(LocalDateTime.now().isAfter(CampaignUtil.getDateFromMap(headerDetails.get("ValidFrom")))) {
				CampaignUtil.clearPromotionCache(eventPublisher, true);
			}
			return true;
		} catch (Exception e) {
			log.error("Error approving the Promotion {} : {}",promotionId,e);
			throw new PromotionException("Unable to approve the Promotion!", e);
		}
	}
	
	private void pushToPosMaster(Long promotionId, Object couponBased) {
		if(regularPromotionDao.isPosChannel(promotionId) && (couponBased==null 
				|| CampaignConstants.NON_COUPON_BASED.equalsIgnoreCase((String) couponBased))) {
			posMasterDataHelper.pushToMasterDataUpdateInfoQueue(PosMasterDataKey.REGULAR_PROMOTION_DETAILS, promotionId.intValue(),new Date());
		}
	}

	@Override
	@Transactional("posTransactionManager")
	public boolean updatePromotionToDate(Long promotionId, LocalDateTime toDate, String modifiedBy, String status) {
		if(UtilValidate.isEmpty(promotionId))
			return false;
		Map<String, Object> headerDetails = regularPromotionDao.getRegularPromotionHeaderDetails(promotionId);
		String campaignStatus = String.valueOf(headerDetails.get(STATUS));
		if(!status.equals(campaignStatus)){
			throw new PromotionException("Can not update promotion ToDate with status "+campaignStatus );
		}
		boolean isToDateUpdated = CampaignUtil.validateToDate(headerDetails, "update", Arrays.asList(PromotionConstants.APPLICABLE_TYPE_PHARMACY), toDate, null, "RP");
		
		if(isToDateUpdated) {
			try {
				regularPromotionDao.updatePromtionToDate(promotionId, toDate, modifiedBy, headerDetails);
				if(PromotionConstants.ACTIVE.equals(campaignStatus)) {
					pushToPosMaster(promotionId,headerDetails.get("CouponBased"));
				}
				if(PromotionConstants.ACTIVE.equals(status) && Objects.nonNull(toDate) && LocalDate.now().equals(toDate.toLocalDate())) {
					CampaignUtil.clearPromotionCache(eventPublisher, true);
				}
				return true;
			} catch (Exception e) {
				log.error("Error updating ToDate for the Promotion {} : {}",promotionId,e);
				throw new PromotionException("Unable to Update ToDate for the Promotion!", e);
			}
		}
		return false;
	}

	@Override
	@Transactional("posTransactionManager")
	public void rejectRegularPromotion(Long promotionId, String rejectedBy, String remarks) {

		if(UtilValidate.isEmpty(promotionId))
			return ;
		Map<String, Object> headerDetails = regularPromotionDao.getRegularPromotionHeaderDetails(promotionId);
		int applicableType = Integer.parseInt(String.valueOf(headerDetails.get("ApplicableType")));
		if(!Arrays.asList(PromotionConstants.APPLICABLE_TYPE_PHARMACY).contains(applicableType)) {
			throw new PromotionException("User doesn't have the rights to reject Promotions of the applicable type!");
		}
		String status = String.valueOf(headerDetails.get(STATUS));
		if(!PromotionConstants.INACTIVE.equals(status))
			throw new PromotionException("Can not reject Promotions with status "+status);
		
		try {
			regularPromotionDao.rejectRegularPromotion(promotionId, rejectedBy, remarks, headerDetails);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new PromotionException("Unable to Reject Campaign");
		}
	}
	
	@Override
	@Transactional("posTransactionManager")
	public Map<String, Object> getRegularPromotions(CampaignSearchCriteria searchCriteria, List<Integer> applicableTypesByRole) {
		if(UtilValidate.isEmpty(searchCriteria) || (UtilValidate.isEmpty(searchCriteria.getCampaignId()) && UtilValidate.isEmpty(searchCriteria.getCampaignName()) && UtilValidate.isEmpty(searchCriteria.getApplicableTypes()) &&
				UtilValidate.isEmpty(searchCriteria.getChannels()) && UtilValidate.isEmpty(searchCriteria.getStatus())  && UtilValidate.isEmpty(searchCriteria.getToDate()) && 
				UtilValidate.isEmpty(searchCriteria.getFromDate()) && UtilValidate.isEmpty(searchCriteria.getCreatedBy()) && UtilValidate.isEmpty(searchCriteria.getPromotionLevel())
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
		
		try {
			return regularPromotionDao.getRegularPromotionList(searchCriteria);
		} catch (Exception e) {
			log.error("Error getting the campaign list : {}",e);
			return new HashMap<>();
		}
	}
	
	@Override
	@Transactional("posTransactionManager")
	public RegularPromotion getRegularPromotionByPromotionId(Long promotionId) {
		RegularPromotion regularPromotion = regularPromotionDao.getRegularPromotionByPromotionId(promotionId);
		if (UtilValidate.isNotEmpty(regularPromotion)) {
			Integer promotionType = 0;
			if (UtilValidate.isNotEmpty(regularPromotion.getDiscountType())) {
				if (regularPromotion.getDiscountType().size() == 2) {
					promotionType = PromotionConstants.SALE_TYPE_DISCOUNT_POINTS;
				} else if (regularPromotion.getDiscountType().contains(1)) {
					promotionType = PromotionConstants.SALE_TYPE_DISCOUNT;
				} else if (regularPromotion.getDiscountType().contains(2)) {
					promotionType = PromotionConstants.SALE_TYPE_POINTS;
				}
			}
			if(regularPromotion.isCouponBased()) {
				PromotionCoupon promotionCoupon = regularPromotionDao.getPromotionCoupons(promotionId, promotionType, null);
				if (UtilValidate.isNotEmpty(promotionCoupon)){
					regularPromotion.setPromotionCoupon(promotionCoupon);
					CampaignUtil.setCouponModes(promotionCoupon, regularPromotionDao.getCouponApplicableModes(promotionId, promotionType));
					if(!regularPromotion.isAllCustomers()) 
						regularPromotion.setCustomerIds(regularPromotionDao.getRegularPromotionCustomerIds(promotionId, promotionCoupon.getCouponCode()));
				}
			}
			List<Integer> channels = regularPromotionDao.getPromotionChannels(promotionId, regularPromotion.getApplicableType(), promotionType);
			if (UtilValidate.isNotEmpty(channels)) {
				regularPromotion.setChannels(channels);
			}
		}
		return regularPromotion;
	}
	
	@Override
	@Transactional("posTransactionManager")
	public RegularPromotion updateRegularPromotion(RegularPromotion regularPromotion) {
		
		validateRegularPromotion(regularPromotion, true);
		Map<String, Object> headerDetails = regularPromotionDao.getRegularPromotionHeaderDetails(regularPromotion.getPromotionId());
		regularPromotionDao.updateRegularPromotionDetails(regularPromotion, headerDetails);
		regularPromotionDao.updateDiscountCategorySlabs(regularPromotion);
		regularPromotionDao.updatePromotionCouponDetails(regularPromotion, headerDetails);
		return regularPromotion;
	}
	
	@Override
	public Set<Long> getRegularPromotionCustomerIds(Long promotionId, String couponCode) {
		if(UtilValidate.isEmpty(promotionId) || UtilValidate.isEmpty(couponCode))
			return new HashSet<>();
		try {
			return regularPromotionDao.getRegularPromotionCustomerIds(promotionId, couponCode);
		} catch (Exception e) {
			log.error("Error getting the campaign customers for id {} : {}",promotionId,e);
			return new HashSet<>();
		}
	}

	@Override
	public boolean isRegularPromotionNameAvailable(String promotionName) {
		if(UtilValidate.isEmpty(promotionName))
			return false;
		try {
			return regularPromotionDao.isRegularPromotionNameAvailable(promotionName);
		} catch (Exception e) {
			log.error("Error checking the regular promotion name availability for name {} : {}",promotionName,e);
			return false;
		}	
	}
	
	@Override
	public boolean isRegularPromotionCouponCodeAvailable(String couponCode) {
		if(UtilValidate.isEmpty(couponCode))
			return false;
		try {
			return regularPromotionDao.isRegularPromotionCouponCodeAvailable(couponCode);
		} catch (Exception e) {
			log.error("Error checking the regular promotion couponcode availability for couponcode {} : {}",couponCode,e);
			return false;
		}	
	}

	 @Override
	 @Transactional("posTransactionManager") 
	 public void autoRejectRegularPromotion() {
		 regularPromotionDao.autoRejectRegularPromotion(); 
	 }

	@Override
	public List<String> getStores(Long promotionId) {
		try {
			return regularPromotionDao.getStores(promotionId);
		} catch(Exception e) {
			log.error(e.getMessage(),e);
			return new ArrayList<>();
		}
	}
	 
}
