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
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.medplus.cache.MedplusCache;
import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.marketing.constants.CampaignConstants;
import com.medplus.marketing.dao.CampaignDao;
import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignProduct;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.PromotionCoupon;
import com.medplus.marketing.helper.PosMasterDataHelper;
import com.medplus.marketing.helper.ProductStoreHelper;
import com.medplus.marketing.service.CampaignService;
import com.medplus.marketing.service.PathLabTestsService;
import com.medplus.marketing.util.CampaignUtil;
import com.medplus.pos.constant.PosMasterDataKey;
import com.medplus.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CampaignServiceImpl implements CampaignService{
	
	private static final String AT_LINE_MSG = "At Line ";

	private static final String NOT_AVAILABLE_MSG = " is in Inactive status or not available";

	private static final String FROM_PRODUCTID_MSG = " FromProductId ";

	private static final Object STATUS = "Status";

	@Autowired
	private PosMasterDataHelper posMasterDataHelper;
	
	@Autowired
	CampaignDao campaignDao;
	
	@Autowired
	ProductService productService;
	
	@Autowired 
	PathLabTestsService pathLabTestsService;
	
	@Autowired
	private ProductStoreHelper productStoreHelper;
	
	@Autowired
	MedplusCache eventPublisher;
	
	@Value("${com.medplus.campaign.max.date.limit:540}")
	private int toDateMaxLimit;
	
	@Override
	public Map<String, Object> getCampaignList(CampaignSearchCriteria searchCriteria, List<Integer> applicableTypesByRole) {
		if(UtilValidate.isEmpty(searchCriteria) || (UtilValidate.isEmpty(searchCriteria.getCampaignId()) && UtilValidate.isEmpty(searchCriteria.getCampaignName()) && UtilValidate.isEmpty(searchCriteria.getApplicableTypes()) &&
				UtilValidate.isEmpty(searchCriteria.getChannels()) && UtilValidate.isEmpty(searchCriteria.getStatus())  && UtilValidate.isEmpty(searchCriteria.getToDate()) && 
				UtilValidate.isEmpty(searchCriteria.getFromDate()) && (UtilValidate.isEmpty(searchCriteria.getCreatedBy())) && (UtilValidate.isEmpty(searchCriteria.getCouponCode())))) {
				
			searchCriteria.setFromDate(LocalDate.now().atStartOfDay());
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
			return campaignDao.getCampaignList(searchCriteria);
		} catch (Exception e) {
			log.error("Error getting the campaign list : {}",e);
			return new HashMap<>();
		}
	}
	
	@Override
	@Transactional("posTransactionManager")
	public void saveMultipleCampaigns(List<Campaign> multipleCampaigns) {
		multipleCampaigns.forEach(this::saveCampaign);
	}
	
	@Override
	@Transactional("posTransactionManager")
	public void saveCampaign(Campaign campaign) {
		validateCampaign(campaign, false);
		campaignDao.saveCampaignTemplate(campaign);
		campaignDao.saveCampaign(campaign);
		log.info("campaign : {}",campaign);
		campaignDao.savePromotionMetainfoDetails(campaign.getCampaignId(),campaign.getPromotionApplicableType(), campaign.getCampaignType(),campaign.getUserMetaData());

		if (CampaignConstants.FEW_CUSTOMERS.equalsIgnoreCase(campaign.getAllCustomers())) {
			if (UtilValidate.isNotEmpty(campaign.getCustomerIds())) {
				campaignDao.saveCampaignCustomerDetails(campaign);
			} else {
				throw new PromotionException("Customer details can not be empty,Please check and try again");
			}
		}
		if (CampaignConstants.COUPON_BASED.equalsIgnoreCase(campaign.getCouponBased())) {
			if (campaign.getPromotionCoupon() != null) {
				PromotionCoupon promotionCoupon = campaign.getPromotionCoupon();
				promotionCoupon.setPromotionId(campaign.getCampaignId());
				campaignDao.savePromotionCouponDetails(promotionCoupon);
				Integer couponDiscountType = promotionCoupon.getCouponDiscountType();
				if(UtilValidate.isNotEmpty(couponDiscountType) && Objects.equals(couponDiscountType, PromotionConstants.SERVICE_CHARGES_DISC_TYPE) ||
						Objects.equals(couponDiscountType, PromotionConstants.PRODUCTS_AND_SERVICE_CHARGES_DISC_TYPE))
					campaignDao.saveServiceChargePromotions(promotionCoupon);
			} else {
				throw new PromotionException(
						"Coupon details can not be empty,Please refresh and try again");
			}
		}
	}
	
	@Override
	@Transactional("posTransactionManager")
	public Campaign updateCampaign(Campaign campaign) {
		validateCampaign(campaign, true);
		campaignDao.updateCampaignTemplate(campaign);
		campaignDao.updateCampaign(campaign);
		campaignDao.updatePromotionMetainfoDetails(campaign.getCampaignId(),campaign.getPromotionApplicableType(),campaign.getCampaignType(),campaign.getUserMetaData());
		campaignDao.updateCampaignCustomerDetails(campaign);
		if (CampaignConstants.COUPON_BASED.equalsIgnoreCase(campaign.getExistingCouponBased())) {
			PromotionCoupon existingPromotionCoupon = campaignDao.getPromotionCouponForId(campaign.getCampaignId(), campaign.getCampaignType());
			if (CampaignConstants.COUPON_BASED.equalsIgnoreCase(campaign.getCouponBased())) {
				PromotionCoupon promotionCoupon = campaign.getPromotionCoupon();
				if(UtilValidate.isNotEmpty(promotionCoupon)) {
					campaignDao.updatePromotionCouponDetails(promotionCoupon);
					Integer couponDiscountType = promotionCoupon.getCouponDiscountType();
					if(UtilValidate.isNotEmpty(couponDiscountType) && Objects.equals(couponDiscountType, PromotionConstants.SERVICE_CHARGES_DISC_TYPE) ||
							Objects.equals(couponDiscountType, PromotionConstants.PRODUCTS_AND_SERVICE_CHARGES_DISC_TYPE)) {
						campaignDao.updateServiceChargePromotions(promotionCoupon);
					}
				}
				
			} else {
				if(existingPromotionCoupon!=null && existingPromotionCoupon.getCouponDiscountType()==2 && UtilValidate.isEmpty(campaign.getCampaignProducts())) {
					throw new PromotionException("Campaign products can not be empty");
				}
				campaignDao.deleteCouponCodesForPromotion(campaign.getCampaignId(),campaign.getCampaignType());
				campaignDao.updateCouponDetailsForCampaignCustomers(campaign.getCampaignId(), null);
			}
		} else if (!CampaignConstants.COUPON_BASED.equalsIgnoreCase(campaign.getExistingCouponBased()) && CampaignConstants.COUPON_BASED.equalsIgnoreCase(campaign.getCouponBased())) {
			if (campaign.getPromotionCoupon() != null) {
				PromotionCoupon promotionCoupon = campaign.getPromotionCoupon();
				promotionCoupon.setPromotionId(campaign.getCampaignId());
				campaignDao.savePromotionCouponDetails(promotionCoupon);
				Integer couponDiscountType = promotionCoupon.getCouponDiscountType();
				if(UtilValidate.isNotEmpty(couponDiscountType) && Objects.equals(couponDiscountType, PromotionConstants.SERVICE_CHARGES_DISC_TYPE) ||
						Objects.equals(couponDiscountType, PromotionConstants.PRODUCTS_AND_SERVICE_CHARGES_DISC_TYPE))
					campaignDao.saveServiceChargePromotions(promotionCoupon);
				campaignDao.updateCouponDetailsForCampaignCustomers(campaign.getCampaignId(), campaign.getPromotionCoupon().getCouponCode().toUpperCase());
			}else {
					 throw new PromotionException("Coupon details can not be empty,Please refresh and try again");
			}
		}
		return campaign;
		
	}
	
	private void validateCampaignRegions (StringJoiner errorMessage,Map<String, String> regions, Integer applicableType, boolean isUpdate) {
		if(UtilValidate.isEmpty(regions)) {
			errorMessage.add("Regions can not be empty!");
		}else {
			Set<String> storeIds = new HashSet<>();
			regions.keySet().forEach(region -> {
				if(region.length() == 12) {
					storeIds.add(region);
				}
				String[] ners = regions.get(region).split(",");
				if(UtilValidate.isNotEmpty(ners)) {
					for(int i = 0; i < ners.length; i++) {
						String ner=ners[i];
						ner = UtilValidate.isNotEmpty(ner) ? ner.trim().toUpperCase() : null;
						if (UtilValidate.isNotEmpty(ner) && 
								(region.length() >= ner.length() || 
									(ner.length() > region.length() && !region.equals(ner.substring(0, region.length())) || ner.length() > 12)
								) &&
							    (region.equals(ner)  || ner.length() != 12 || (region.length() == 12 && ner.length() == 12))) {
		                       
							errorMessage.add("Invalid Not eligible region " + ner + "for region "+region);
						}
					}
				}
			});
			if(!isUpdate) {
				productStoreHelper.validateStores(storeIds, applicableType, "Region Store");
			}
		}
	}
	
	private void validateCampaign(Campaign campaign, boolean isUpdate) {
		if(UtilValidate.isEmpty(campaign))
			throw new PromotionException("Invalid Campaign");
		StringJoiner errorMessage = new StringJoiner(",");
		CampaignUtil.validateCampaignInfo(errorMessage, campaign, toDateMaxLimit, isUpdate);
		CampaignUtil.validateCouponInfo(errorMessage, campaign);
		validateCampaignRegions(errorMessage, campaign.getRegionsMap(), campaign.getPromotionApplicableType(), isUpdate);
		if(!isUpdate && (campaign.getPromotionCoupon()==null || campaign.getPromotionCoupon().getCouponDiscountType()!=PromotionConstants.SERVICE_CHARGES_DISC_TYPE) && UtilValidate.isEmpty(campaign.getCampaignProducts()))
			errorMessage.add("Campaign products can not be empty!");
		if(UtilValidate.isNotEmpty(errorMessage.toString()))
			throw new PromotionException(errorMessage.toString());
	}

	@Override
	public Campaign findCampaignForCampaignId(Long campaignId) {
		log.info("At service, campaignId: " + campaignId);
		Campaign campaign = campaignDao.findCampaignByCampaignId(campaignId);
		if (campaign != null) {
			PromotionCoupon promotionCoupon = campaignDao.getPromotionCoupons(campaignId, campaign.getCampaignType(), null);
			if (promotionCoupon != null) {
				campaign.setPromotionCoupon(promotionCoupon);
				CampaignUtil.setCouponModes(promotionCoupon, campaignDao.getCouponApplicableModes(campaignId, campaign.getCampaignType()));
			}
			List<Integer> channels = campaignDao.getPromotionChannels(campaignId, campaign.getPromotionApplicableType(),campaign.getCampaignType());
			if (channels != null) {
				campaign.setChannels(channels);
			}
		}
		return campaign;
	}
	
	@Override
	@Transactional("posTransactionManager")
	public void setToDateForCampaign(Campaign campaign) {
		campaignDao.setToDateForCampaign(campaign);
		if (campaign != null && CampaignConstants.COUPON_BASED.equalsIgnoreCase(campaign.getCouponBased()) && campaign.getPromotionCoupon() != null) {
			campaignDao.setToDateForPromotionCouponDetails(campaign.getPromotionCoupon());
		}
	}
	
	@Override
	public Map<String, Integer> findConditionTypes(Integer campaignType) {
		return campaignDao.findConditionTypes(campaignType == Integer.parseInt(PromotionConstants.SPECIAL_DISCOUNT)
				|| campaignType == Integer.parseInt(PromotionConstants.PROMOTION_ADDON) ? 3 : 0);
	}
	
	@Override
	public void checkCampaignProducts(List<CampaignProduct> campaignProducts, int campaignType) {
		Set<String> products = CampaignUtil.getCampaignProducts(campaignProducts, campaignType);
		log.debug("campaignProductsStr : {}", products);
		if (UtilValidate.isNotEmpty(products)) {
			compareCampaignProductsWithProductMaster(campaignProducts, campaignType, CampaignUtil.getProductMap(products, "Product/Optical IDs", productService, true).keySet().stream().collect(Collectors.toList()));
		}
	}
	
	@Override
	public void checkRemoveExcelProducts(Campaign campaign) {
		Set<String> remProducts = campaign.getRemovedProducts();
		if (UtilValidate.isNotEmpty(remProducts)) {
			log.debug("Campaign RemoveProductStr : {}", remProducts);
			if(campaign.getPromotionApplicableType() == PromotionConstants.APPLICABLE_TYPE_PATHLABS){
				Set<String> activePathTests = CampaignUtil.getPathLabTestMap(new ArrayList<>(remProducts), pathLabTestsService, campaign.getUserMetaData().isSpecialtyBased(), true).keySet();
				compareRemoveProductsWithProductMaster(campaign, activePathTests);	
			}else{
				if (UtilValidate.isNotEmpty(remProducts)) {
					compareRemoveProductsWithProductMaster(campaign, CampaignUtil.getProductMap(remProducts, "Remove Product/Optical IDs", productService, true).keySet());
				}						
			}
		}
	}	
	
	@Override
	public List<Map<String, Object>> findCampaignProductsForTemplateId(Long templateId, Integer campaignType, Integer applicableType) {
		return campaignDao.findCampaignProductsByTemplateIdForExcel(templateId, campaignType, applicableType);
	}
	
	@Override
	public int checkCampaignNameAvailablity(String campaignName) {
		return campaignDao.checkCampaignNameAvailablity(campaignName);
	}

	@Override
	public List<Long> getCampaignCustomers(Long campaignId) {
		return campaignDao.getCampaignCustomers(campaignId);
	}
	
	private void compareRemoveProductsWithProductMaster(Campaign campaign, Set<String> campaignProductsList) {
		campaign.getRemovedProducts().forEach(product ->  {
			if (!(campaignProductsList.contains(product
					.replace("'", "").toUpperCase()))) {
				throw new PromotionException(product + NOT_AVAILABLE_MSG);
			}
		});
	}
	
	private void compareCampaignProductsWithProductMaster(List<CampaignProduct> campaignProducts, int campaignType, List<String> campaignProductsList) {
		if (campaignType == 5) {
			for (CampaignProduct campaignProduct : campaignProducts) {
				if (campaignProductsList.contains(campaignProduct.getProductId().toUpperCase())) {
					if (!(campaignProductsList.contains(campaignProduct.getToProductId().toUpperCase()))) {
						throw new PromotionException(AT_LINE_MSG
								+ (campaignProducts.indexOf(campaignProduct) + 2) + " ToProductId "
								+ campaignProduct.getToProductId()
								+ NOT_AVAILABLE_MSG);
					}
				} else {
					throw new PromotionException(AT_LINE_MSG
							+ (campaignProducts.indexOf(campaignProduct) + 2) + FROM_PRODUCTID_MSG
							+ campaignProduct.getProductId()
							+ NOT_AVAILABLE_MSG);
				}
			}
		} else if(campaignType == 6) {
			for (CampaignProduct campaignProduct : campaignProducts) {
				if (campaignProductsList.contains(campaignProduct.getProductId().toUpperCase())) {
					 	List<String> productIds = Arrays.stream(campaignProduct.getToProductId().split(",")).map(String::toUpperCase).collect(Collectors.toList());
						productIds.replaceAll(String::trim);
						if (!(campaignProductsList.containsAll(productIds))) {
							productIds.removeAll(campaignProductsList);
							throw new  PromotionException(AT_LINE_MSG
									+ (campaignProducts.indexOf(
											campaignProduct) + 2) + " ToProductId "
									+ StringUtils.collectionToCommaDelimitedString(productIds)
									+ " is/are in Inactive status or not available");
						}} else {
					throw new PromotionException(AT_LINE_MSG
							+ (campaignProducts.indexOf(
									campaignProduct) + 2) + FROM_PRODUCTID_MSG
							+ campaignProduct.getProductId()
							+ NOT_AVAILABLE_MSG);
				}
		}}
		else {
			for (CampaignProduct campaignProduct : campaignProducts) {
				if (!(campaignProductsList.contains(campaignProduct.getProductId().toUpperCase()))) {
					throw new PromotionException(AT_LINE_MSG 
							+ (campaignProducts.indexOf(campaignProduct) + 2) 
							+ FROM_PRODUCTID_MSG
							+ campaignProduct.getProductId()
							+ NOT_AVAILABLE_MSG);
				}
			}
		}
	}

	@Override
	@Transactional("posTransactionManager")
	public boolean approveCampaign(long campaignId, LocalDateTime toDate, List<Integer> applicableTypes, String approvedBy) {
		if(UtilValidate.isEmpty(campaignId))
			return false;
		Map<String, Object> headerDetails = campaignDao.getCampaignHeaderDetails(campaignId);
		String status = String.valueOf(headerDetails.get(STATUS));
		if(!PromotionConstants.INACTIVE.equals(status))
			throw new PromotionException("Can not approve campaign with status "+status);
		
		boolean isDateUpdated = CampaignUtil.validateToDate(headerDetails, "approve", applicableTypes, toDate, toDateMaxLimit, "CAMPAIGN");
		boolean updated=true;
		try {
			updated= campaignDao.approveCampaign(campaignId, ((Number)headerDetails.get("TemplateID")).longValue(), toDate, approvedBy, headerDetails, isDateUpdated);
			pushToPosMaster(campaignId, headerDetails.get("CouponBased"));
			if(LocalDateTime.now().isAfter(CampaignUtil.getDateFromMap(headerDetails.get("DateFrom")))) {
				CampaignUtil.clearPromotionCache(eventPublisher, true);
			}
		}catch (Exception e) {
			log.error("Error approving the campaign {} : {}",campaignId,e);
			throw new PromotionException("Unable to approve the campaign!", e);
		}
		return updated;
	}
	
	private void pushToPosMaster(long campaignId, Object couponBased) {
		if(campaignDao.isPosChannel(campaignId) && (couponBased==null 
				|| CampaignConstants.NON_COUPON_BASED.equalsIgnoreCase((String)couponBased))) {
			posMasterDataHelper.pushToMasterDataUpdateInfoQueue(PosMasterDataKey.SPECIAL_PROMOTIONS, campaignId, new Date());
		}
	}


	@Override
	@Transactional("posTransactionManager")
	public void rejectCampaign(long campaignId, String rejectedBy, String remarks, List<Integer> applicableTypes) {
		if(UtilValidate.isEmpty(campaignId) || UtilValidate.isEmpty(remarks))
			return ;
		Map<String, Object> headerDetails = campaignDao.getCampaignHeaderDetails(campaignId);
		int applicableType = Integer.parseInt(String.valueOf(headerDetails.get("ApplicableType")));
		if(!applicableTypes.contains(applicableType)) {
			throw new PromotionException("User doesn't have the rights to reject campaigns of the applicable type!");
		}
		String status = String.valueOf(headerDetails.get(STATUS));
		if(!PromotionConstants.INACTIVE.equals(status))
			throw new PromotionException("Can not reject campaign with status "+status);
		
		try {
			campaignDao.rejectCampaign(campaignId, ((Number)headerDetails.get("TemplateID")).longValue(), rejectedBy, remarks, headerDetails);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new PromotionException("Unable to Reject Campaign");
		}
	}

	@Override
	@Transactional("posTransactionManager")
	public boolean updateCampaignToDate(long campaignId, LocalDateTime toDate, List<Integer> applicableTypes, String modifiedBy, String status) {
		if(UtilValidate.isEmpty(campaignId))
			return false;
		Map<String, Object> headerDetails = campaignDao.getCampaignHeaderDetails(campaignId);
		String campaignStatus = String.valueOf(headerDetails.get(STATUS));
		if(!status.equals(campaignStatus)) {
			throw new PromotionException("Can not update campaign ToDate with status "+campaignStatus );
		}
		boolean isToDateUpdated =  CampaignUtil.validateToDate(headerDetails, "update", applicableTypes, toDate, toDateMaxLimit, "CAMPAIGN");
		boolean updated = true;
		if(isToDateUpdated) {
			try {
				updated= campaignDao.updateCampaignToDate(campaignId, toDate, modifiedBy, headerDetails);
				if(PromotionConstants.ACTIVE.equals(campaignStatus)) {
					pushToPosMaster(campaignId, headerDetails.get("CouponBased"));
				}
				if(PromotionConstants.ACTIVE.equals(status) && LocalDate.now().equals(toDate.toLocalDate())) {
					CampaignUtil.clearPromotionCache(eventPublisher, true);
				}
			}catch (Exception e) {
				log.error("Error updating the campaign {} : {}",campaignId,e);
				throw new PromotionException("Unable to update the campaign!", e);
			}
		}
		return updated;
	}
	
	@Override
	@Transactional("posTransactionManager")
	public void autoRejectClosedCampaigns() {
		campaignDao.autoRejectClosedCampaigns();
	}


	@Override
	public List<String> getStores(Long campaignId) {
		try {
			return campaignDao.getStores(campaignId);
		} catch(Exception e) {
			log.error(e.getMessage(),e);
			return new ArrayList<>();
		}
	}

	@Override
	public List<String> getCouponCodes(Set<String> uniqueCouponCodes) {
		return campaignDao.getCouponCodes(uniqueCouponCodes);
	}
	
}

