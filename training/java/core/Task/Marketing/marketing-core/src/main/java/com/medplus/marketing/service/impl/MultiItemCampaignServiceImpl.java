package com.medplus.marketing.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medplus.cache.MedplusCache;
import com.medplus.cache.MedplusCacheable;
import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.discounts.cache.MultiItemCampaignCacheEvent;
import com.medplus.discounts.cache.RemovedItemEvent;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.discounts.dao.MultiItemCampaignDao;
import com.medplus.discounts.domain.MultiItemCampaign;
import com.medplus.discounts.domain.MultiItemCampaignMaster;
import com.medplus.discounts.domain.MultiItemSlab;
import com.medplus.discounts.service.DiscountDetailsService;
import com.medplus.marketing.dao.MarketingMultiItemCampaignDao;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.helper.ProductStoreHelper;
import com.medplus.marketing.service.MultiItemCampaignService;
import com.medplus.marketing.util.CampaignUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author abhijith
 *
 */

@Slf4j
@Service
public class MultiItemCampaignServiceImpl implements MultiItemCampaignService{
	
	@Autowired
	MarketingMultiItemCampaignDao multiItemCampaignDao;
	
	@Autowired
	MultiItemCampaignDao discountsMultiItemCampaignDao;
	
	@Autowired
	DiscountDetailsService discountDetailsService; 
	
	@Autowired
	MedplusCache eventPublisher;
	
	@Autowired
	private ProductStoreHelper productStoreHelper;
	
	@Value("${com.medplus.multiitem.max.date.limit:185}")
	private int toDateMaxLimit;
	
	private static final String STATUS = "MI_Status";
	
	@Override
	@Transactional("posTransactionManager")
	public MultiItemCampaign createMultiItemCampaign(MultiItemCampaignMaster campaign){
		validateCampaign(campaign, false);
		modifyNotEligibleRegions(campaign.getRegions(), campaign.getChannels(), campaign.getApplicableType());
		String campaignName = campaign.getCampaignName();
		log.info("Campaign: {}", campaign);
		try {
			campaign = multiItemCampaignDao.createMultiItemCampaign(campaign);
			if(campaign==null) {
				log.error("Error creating the campaign {}",campaignName);
				throw new PromotionException("Unable to create campaign");
			}
		}catch (Exception e) {
			log.error("Error creating the campaign: {}", e);
			throw new PromotionException("Unable to create the campaign!", e);
		}
		return campaign;
	}

	@Override
	@Transactional("posTransactionManager")
	public MultiItemCampaignMaster updateMultiItemCampaign(MultiItemCampaignMaster campaign){
		validateCampaign(campaign, true);
		modifyNotEligibleRegions(campaign.getRegions(), campaign.getChannels(), campaign.getApplicableType());
		Map<String, Object> headerDetails = multiItemCampaignDao.getCampaignHeaderDetails(campaign.getCampaignId());
		String status = String.valueOf(headerDetails.get(STATUS));
		if("A".equals(status))
			throw new PromotionException("Unable to update the campaign!");
		boolean existingAllCustomers = String.valueOf(headerDetails.get("AllCustomers")).equals("Y");
		if(!campaign.isAllCustomers() && existingAllCustomers && UtilValidate.isEmpty(campaign.getCustomerIds())) {
			throw new PromotionException("CustomerUpload file can not be empty!");
		}
		log.info("Campaign: {}", campaign);
		try {
			multiItemCampaignDao.updateMultiItemCampaign(campaign, headerDetails);
		} catch (Exception e) {
			log.error("Error updating the campaign : {}" + campaign);
			throw new PromotionException("Unable to  update the campaign!", e);
		}
			  
		return campaign;
	}
	
	private void modifyNotEligibleRegions(Map<String, List<String>> regions, List<Integer> channels, Integer applicableType) {
		if(applicableType==5 && !(channels.size()==1 && channels.contains(1))) {
			List<String> invalidRegions = regions.keySet().stream().filter(region -> region.length()>7).collect(Collectors.toList());
			if(UtilValidate.isNotEmpty(invalidRegions)) {
				throw new PromotionException("Invalid regions : " + invalidRegions + ". Regions length cannot be greater than 7!");
			}
		}
		regions.keySet().forEach(region -> {
			List<String> ners = regions.get(region) == null ? new ArrayList<>() : regions.get(region);
			ners = ners.stream().map(String::toUpperCase).map(String::trim).collect(Collectors.toList());
			regions.replace(region, ners);
		});
	}
	
	@Override
	public Map<String, Object> getMultiItemCampaignList(CampaignSearchCriteria searchCriteria, List<Integer> applicableTypesByRole){
		if(UtilValidate.isEmpty(searchCriteria) || (UtilValidate.isEmpty(searchCriteria.getCampaignId()) && UtilValidate.isEmpty(searchCriteria.getCampaignName()) && UtilValidate.isEmpty(searchCriteria.getCampaignType()) && UtilValidate.isEmpty(searchCriteria.getApplicableTypes()) &&
				UtilValidate.isEmpty(searchCriteria.getChannels()) && UtilValidate.isEmpty(searchCriteria.getStatus())  && UtilValidate.isEmpty(searchCriteria.getToDate()) && 
				UtilValidate.isEmpty(searchCriteria.getFromDate()) && (UtilValidate.isEmpty(searchCriteria.getCreatedBy())) )) {
				
			searchCriteria.setFromDate(LocalDate.now().atStartOfDay());
			searchCriteria.setToDate(LocalDate.now().plusDays(1).atStartOfDay());
		}
		List<Integer> applicableTypes = searchCriteria.getApplicableTypes();
		if(UtilValidate.isNotEmpty(applicableTypes)) {
			applicableTypes = applicableTypesByRole.containsAll(applicableTypes) ? applicableTypes : new ArrayList<>();
		}else {
			applicableTypes = applicableTypesByRole;
		}
		searchCriteria.setApplicableTypes(applicableTypes);
		
		try {
			return multiItemCampaignDao.getMultiItemCampaignList(searchCriteria);
		} catch (Exception e) {
			log.error("Error getting the campaign list : {}",e);
			return new HashMap<>();
		}
	}

	@Override
	public MultiItemCampaign getMultiItemCampaign(int campaignId) {
		if(UtilValidate.isEmpty(campaignId))
			return null;
		try {
			return discountsMultiItemCampaignDao.getMultiItemCampaign(campaignId, false);
		} catch (Exception e) {
			log.error("Error getting the campaign for id {} : {}",campaignId,e);
			return null;
		}
	}

	@Override
	public Set<Long> getCampaignCustomerIds(int campaignId) {
		if(UtilValidate.isEmpty(campaignId))
			return new HashSet<>();
		try {
			return multiItemCampaignDao.getCampaignCustomerIds(campaignId);
		} catch (Exception e) {
			log.error("Error getting the campaign customers for id {} : {}",campaignId,e);
			return new HashSet<>();
		}
	}

	@Override
	public Set<String> getCampaignItems(int campaignId) {
		if(UtilValidate.isEmpty(campaignId))
			return new HashSet<>();
		try {
			return multiItemCampaignDao.getCampaignItems(campaignId);
		} catch (Exception e) {
			log.error("Error getting the campaign items for id {} : {}",campaignId,e);
			return new HashSet<>();
		}
	}

	@Override
	@Transactional("posTransactionManager")
	public boolean approveCampaign(int campaignId, LocalDateTime toDate, List<Integer> applicableTypes, String approvedBy) {
		if(UtilValidate.isEmpty(campaignId))
			return false;
		Map<String, Object> headerDetails = multiItemCampaignDao.getCampaignHeaderDetails(campaignId);
		String status = String.valueOf(headerDetails.get(STATUS));
		if(!PromotionConstants.INACTIVE.equals(status))
			throw new PromotionException("Can not approve campaign with status "+status);
		boolean isDateUpdated = CampaignUtil.validateToDate(headerDetails, "approve", applicableTypes, toDate, toDateMaxLimit, "MIC");
		boolean approved=true;
		try {
			approved= multiItemCampaignDao.approveCampaign(campaignId, toDate, approvedBy, headerDetails, isDateUpdated);
			if(LocalDateTime.now().isAfter(CampaignUtil.getDateFromMap(headerDetails.get("FromDate")))) {
				clearMultiItemCache();
			}
		}catch (Exception e) {
			log.error("Error approving the campaign {} : {}",campaignId,e);
			throw new PromotionException("Unable to approve the campaign!", e);
		}
		return approved;
	}
	
	private void validateCampaign(MultiItemCampaignMaster campaign, boolean isUpdate) {
		StringJoiner error = new StringJoiner(",");
		if(UtilValidate.isEmpty(campaign))
			throw new PromotionException("Invalid campaign!");
		
		LocalDateTime fromDate = campaign.getFromDate();
		LocalDateTime toDate = campaign.getToDate();
		Map<String, List<String>> regions = campaign.getRegions();
		List<MultiItemSlab> slabs = campaign.getMultiItemSlabs();
		String campaignType = campaign.getCampaignType();
		if(UtilValidate.isEmpty(campaign.getCampaignId()) && isUpdate) 
			error.add("Invalid Campaign Id!");
		if(UtilValidate.isEmpty(campaign.getCampaignName()) && !isUpdate)
			error.add("Campaign name can not be empty!");
		if(UtilValidate.isEmpty(campaign.getApplicableType()) && !isUpdate)
			error.add("Applicable type can not be empty!");
		if(UtilValidate.isEmpty(campaignType) && !isUpdate)
			error.add("Campaign type can not be empty!");
		if(UtilValidate.isEmpty(campaign.getChannels()))
			error.add("Channels can not be empty!");
		if(UtilValidate.isEmpty(fromDate))
			error.add("FromDate can not be empty!");
		if(UtilValidate.isEmpty(toDate))
			error.add("ToDate can not be empty!");
		if(fromDate.isAfter(toDate))
			error.add("ToDate can not be before FromDate!");
		if(toDate.isBefore(LocalDateTime.now()))
			error.add("ToDate can not be before the current timestamp!");
		long daysBetween = ChronoUnit.DAYS.between(fromDate.toLocalDate(), toDate.toLocalDate());
		if(daysBetween > toDateMaxLimit)
			error.add("ToDate cannot be greater than "+toDateMaxLimit+" days from FromDate");
		
		if(UtilValidate.isEmpty(campaign.getMultiItemSlabs())) {
			error.add("MultiItemSlabs can not be empty!");
		}else {
			if(campaignType == PromotionConstants.MI_BUNDLE_CAMPAIGN && slabs.size() != 1) {
				error.add("Only one Multi Item Campaign - Bundle slab is allowed");
			}
			slabs.forEach(slab -> {
				long numberOfPacks = slab.getNumberOfPacks();
				double sellingPriceOrNumberOfPacks = Math.round(slab.getSellingPriceOrNumberOfPacks() * 100D) / 100D;

				if(!campaignType.equals(PromotionConstants.MI_BUNDLE_CAMPAIGN) && (numberOfPacks<=0 || sellingPriceOrNumberOfPacks<=0)) {
					error.add("NumberOfPacks and TotalPrice / PayablePacks should be positive!");
				} else if(campaignType.equals(PromotionConstants.MI_BUNDLE_CAMPAIGN) && sellingPriceOrNumberOfPacks <= 0) {
					error.add("Multi Item Campaign - Bundle slab price should be greater than zero");
				} else if(campaignType.equals(PromotionConstants.BUY_X_PAY_FOR_Y_CAMPAIGN) ) {
					slab.setSellingPriceOrNumberOfPacks((long)sellingPriceOrNumberOfPacks);
					if(numberOfPacks < sellingPriceOrNumberOfPacks)
						error.add("Number Of Packs should be greater than Payable Packs!");
				} else if(sellingPriceOrNumberOfPacks > 999999.99) {
					error.add("Total Price Or Payable Packs should be less than 10 lakh");
				}
			});
			Set<Long> slabUnits = new HashSet<>();
			Set<Long> duplicates= slabs.stream().map(MultiItemSlab::getNumberOfPacks)
		      .filter(n -> !slabUnits.add(n))
		      .collect(Collectors.toSet());
			if(UtilValidate.isNotEmpty(duplicates))
				error.add("Slabs with No. of packs " + duplicates + " are duplicated. Please add slabs with unique no of packs");
		}
		
		if(UtilValidate.isEmpty(campaign.getLoyalty()))
			error.add("Loyalty can not be empty!");
		
		if(UtilValidate.isEmpty(regions)) {
			error.add("Regions can not be empty!");
		}else {
			Set<String> storeIds = new HashSet<>();
			regions.keySet().forEach(region -> {
				if(region.length() == 12) {
					storeIds.add(region);
				}
				List<String> ners = regions.get(region);
				if(UtilValidate.isNotEmpty(ners)) {
					ners.forEach(ner -> {
						ner = UtilValidate.isNotEmpty(ner) ? ner.trim().toUpperCase() : null;
						if (UtilValidate.isNotEmpty(ner) && 
								(region.length() >= ner.length() || 
									(ner.length() > region.length() && !region.equals(ner.substring(0, region.length())) || ner.length() > 12)
								) &&
							    (region.equals(ner)  || ner.length() != 12 || (region.length() == 12 && ner.length() == 12))) {
		                       
							error.add("Invalid Not eligible region " + ner + "for region "+region);
						}
					});
				}
			});
			if(!isUpdate) {
				productStoreHelper.validateStores(storeIds, campaign.getApplicableType(), "Region Store");
			}
		}
		
		if(!isUpdate && UtilValidate.isEmpty(campaign.getItems()))
			error.add("Items can not be empty!");
		if(!isUpdate && !campaign.isAllCustomers() && UtilValidate.isEmpty(campaign.getCustomerIds()))
			error.add("CustomerIds can not be empty!");
		if(UtilValidate.isEmpty(campaign.getUserMetaData())) {
			error.add("MetaInfo can not be empty!");
		}else {
			if(UtilValidate.isEmpty(campaign.getUserMetaData().getLongDescription()))
				error.add("Description can not be empty!");
			if(!isUpdate && UtilValidate.isEmpty(campaign.getUserMetaData().getCreatedBy()))
				error.add("CreatedBy can not be empty!");
			if(isUpdate && UtilValidate.isEmpty(campaign.getUserMetaData().getModifiedBy()))
				error.add("ModifiedBy can not be empty!");
		}
		
		if (!UtilValidate.isEmpty(error.toString()))
			throw new PromotionException(error.toString());
	}

	@Override
	public boolean isCampaignNameAvailable(String campaignName) {
		if(UtilValidate.isEmpty(campaignName))
			return false;
		try {
			return multiItemCampaignDao.isCampaignNameAvailable(campaignName);
		} catch (Exception e) {
			log.error("Error checking the campaign name availability for name {} : {}",campaignName,e);
			return false;
		}	
	}

	@Override
	@Transactional("posTransactionManager")
	public void rejectMultiItemCampaign(int campaignId, String rejectedBy, String remarks, List<Integer> applicableTypes) {
		if(UtilValidate.isEmpty(campaignId) || UtilValidate.isEmpty(remarks))
			return ;
		Map<String, Object> headerDetails = multiItemCampaignDao.getCampaignHeaderDetails(campaignId);
		int applicableType = Integer.parseInt(String.valueOf(headerDetails.get("ApplicableType")));
		if(!applicableTypes.contains(applicableType)) {
			throw new PromotionException("User doesn't have the rights to reject campaigns of the applicable type!");
		}
		String status = String.valueOf(headerDetails.get(STATUS));
		if(!PromotionConstants.INACTIVE.equals(status))
			throw new PromotionException("Can not reject campaign with status "+status);
		
		try {
			multiItemCampaignDao.rejectMultiItemCampaign(campaignId, rejectedBy, remarks, headerDetails);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new PromotionException("Unable to Reject Campaign");
		}
	}

	@Override
	@Transactional("posTransactionManager")
	public boolean updateActiveCampaignToDate(int campaignId, LocalDateTime toDate, List<Integer> applicableTypes,
			String modifiedBy, String status) {
		if(UtilValidate.isEmpty(campaignId))
			return false;
		if(UtilValidate.isEmpty(toDate))
			throw new PromotionException("To Date can not be empty!");
		Map<String, Object> headerDetails = multiItemCampaignDao.getCampaignHeaderDetails(campaignId);
		String campaignStatus = String.valueOf(headerDetails.get(STATUS));
		if(!status.equals(campaignStatus)) {
			throw new PromotionException("Can not update campaign ToDate with status "+campaignStatus );
		}
		boolean isToDateUpdated = CampaignUtil.validateToDate(headerDetails, "update", applicableTypes, toDate, toDateMaxLimit, "MIC");
		boolean updated = true;
		if(isToDateUpdated) {
			try {
				updated= multiItemCampaignDao.updateActiveCampaignToDate(campaignId, toDate, modifiedBy, headerDetails);
				if(PromotionConstants.ACTIVE.equals(status) && LocalDate.now().equals(toDate.toLocalDate())) {
					clearMultiItemCache();
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
		multiItemCampaignDao.autoRejectClosedCampaigns();
	}
	
	private void clearMultiItemCache() {
		try {
			log.info("MultiItem Cache Clearing event trigerred at {}", new Date());
			eventPublisher.fireClearEvent(new MultiItemCampaignCacheEvent(""));
		} catch (Exception e) {
			log.error("Error triggering multiitem cache clearing event : {}", e.getMessage(), e);
		}
	}

	@Override
	public List<String> getStores(Integer campaignId) {
		try {
			return multiItemCampaignDao.getStores(campaignId);
		} catch(Exception e) {
			log.error(e.getMessage(),e);
			return new ArrayList<>();
		}
	}

	@Override
	@MedplusCacheable(expiresInSec = 10800, cacheClearingEvent = RemovedItemEvent.class)
	public Map<Integer, String> getLoyaltyTypes() {
		return discountDetailsService.getLoyaltyTypes();
	}
	
}
