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
import java.util.Set;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medplus.cache.MedplusCache;
import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.discounts.domain.ComplimentaryProduct;
import com.medplus.marketing.dao.ComplimentaryPromotionDao;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.ComplimentaryPromotion;
import com.medplus.marketing.domain.ComplimentarySlab;
import com.medplus.marketing.helper.PosMasterDataHelper;
import com.medplus.marketing.helper.ProductStoreHelper;
import com.medplus.marketing.service.ComplimentaryPromotionService;
import com.medplus.marketing.util.CampaignUtil;
import com.medplus.pos.constant.PosMasterDataKey;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ComplimentaryPromotionServiceImpl implements ComplimentaryPromotionService{
	
	@Autowired
	ComplimentaryPromotionDao complimentaryPromotionDao;
	
	@Autowired
	ProductStoreHelper productStoreHelper;
	
	@Autowired
	private PosMasterDataHelper posMasterDataHelper;
	
	@Autowired
	MedplusCache eventPublisher;
	
	@Value("${com.medplus.complimentary.max.date.limit:95}")
	private int toDateMaxLimit;
	
	private static final String STATUS = "Status";

	private static final String APLHA_NUMERIC_REGEX = "^[a-zA-Z0-9 _%\\-]+$";

	private void validateComplimentaryPromotionInfo(ComplimentaryPromotion complimentaryPromotion, boolean isUpdate) {
		StringJoiner errorMessage = new StringJoiner(",");
		if(UtilValidate.isEmpty(complimentaryPromotion))
			throw new PromotionException("Invalid Complimentary Promotion");
		if(UtilValidate.isEmpty(complimentaryPromotion.getComplimentaryId()) && isUpdate)
			errorMessage.add("Invalid ComplimentaryId");
		CampaignUtil.validateName(errorMessage, "Complimentary Promotion Name", complimentaryPromotion.getName() , 4, 60, new HashSet<>(Arrays.asList("-", "_")),APLHA_NUMERIC_REGEX);
		if(UtilValidate.isEmpty(complimentaryPromotion.getChannels()))
			errorMessage.add("Channels cannot be empty");
		if(UtilValidate.isEmpty(complimentaryPromotion.getApplicableType()) || complimentaryPromotion.getApplicableType()!= PromotionConstants.APPLICABLE_TYPE_PHARMACY)
			errorMessage.add("Invalid ApplicableType");
		LocalDateTime fromDate = complimentaryPromotion.getFromDate();
		LocalDateTime toDate = complimentaryPromotion.getToDate();
		CampaignUtil.validateDates(errorMessage,fromDate,toDate,toDateMaxLimit,isUpdate);
		if(UtilValidate.isNotEmpty(errorMessage.toString()))
			throw new PromotionException(errorMessage.toString());
	}
	
	private void validateSlabs(ComplimentaryPromotion complimentaryPromotion, boolean isUpdate)  {
		StringJoiner errorMessage = new StringJoiner(",");
		if(complimentaryPromotion.getMessageDisplayPercentage() <= 0 || complimentaryPromotion.getMessageDisplayPercentage() >= 100)
			errorMessage.add("Message Display Percentage must be in between 0.0 to 100.00");
		ComplimentarySlab complimentarySlab = complimentaryPromotion.getComplimentarySlab();
		if(UtilValidate.isEmpty(complimentarySlab))
			errorMessage.add("Complimentary Slab cannot be empty");
		else if(UtilValidate.isEmpty(complimentarySlab.getComplimentarySlabId()) && isUpdate)
			errorMessage.add("Invalid Complimentary Slab Id");
		
		if(!isUpdate && UtilValidate.isNotEmpty(complimentarySlab) && UtilValidate.isEmpty(complimentarySlab.getComplimentarySlabId())) {
			CampaignUtil.validateName(errorMessage, "Complimentary Slab Name", complimentarySlab.getName(), 4, 60, new HashSet<>(Arrays.asList("-", "_")),APLHA_NUMERIC_REGEX);
			if(UtilValidate.isEmpty(complimentarySlab.getInvoiceAmount()))
				errorMessage.add("Invoice Amount is Null");
			else if(complimentarySlab.getInvoiceAmount() <= 0)
				errorMessage.add("Invalid Invoice Amount in Complimentary slab");
			else if (complimentarySlab.getInvoiceAmount() >= 10000000) 
		    	errorMessage.add("Invoice Amount should be below 8 digits");
		} 
		if(UtilValidate.isNotEmpty(errorMessage.toString()))
			throw new PromotionException(errorMessage.toString());
	}
	
	private void validateLoyalties(List<Integer> loyalties)  {
		if(UtilValidate.isEmpty(loyalties))
			throw new PromotionException("Loyalty for Complimentary Promotion cannot be empty");
	}
	
	private void validateUploads(ComplimentaryPromotion complimentaryPromotion, boolean isUpdate)  {
		StringJoiner errorMessage = new StringJoiner(",");
		if(!isUpdate && UtilValidate.isEmpty(complimentaryPromotion.getComplimentaryProducts()))
			errorMessage.add("Complimentary Gift Products can not be empty!");
		if(!isUpdate && !complimentaryPromotion.isAllCustomers() && UtilValidate.isEmpty(complimentaryPromotion.getCustomerIds()))
			errorMessage.add("CustomerID's are mandatory when all customers is not selected");
		if(UtilValidate.isNotEmpty(errorMessage.toString()))
			throw new PromotionException(errorMessage.toString());
	}
	
	private void validateComplimentaryPromotion(ComplimentaryPromotion complimentaryPromotion, boolean isUpdate) {
		StringJoiner errorMessage = new StringJoiner(", ");
		CampaignUtil.validateUserMetaData(complimentaryPromotion.getUserMetaData(), errorMessage, isUpdate);
		if(UtilValidate.isNotEmpty(errorMessage.toString()))
			throw new PromotionException(errorMessage.toString());
		validateComplimentaryPromotionInfo(complimentaryPromotion, isUpdate);
		validateSlabs(complimentaryPromotion, isUpdate);
		validateLoyalties(complimentaryPromotion.getLoyalties());
		CampaignUtil.validatePromoRegions(complimentaryPromotion.getRegions(), complimentaryPromotion.getApplicableType(), productStoreHelper, isUpdate);
		validateUploads(complimentaryPromotion, isUpdate);
	}
	 
	@Override
	@Transactional("posTransactionManager")
	public ComplimentaryPromotion createComplimentaryPromotion(ComplimentaryPromotion complimentaryPromotion) {
		validateComplimentaryPromotion(complimentaryPromotion, false);
		try {
			return complimentaryPromotionDao.createComplimentaryPromotion(complimentaryPromotion);
		} catch (PromotionException ex) {
			log.error(ex.getMessage(), ex);
			throw new PromotionException(ex.getMessage());
		} catch (Exception e) {
			log.error("Error creating the complimentary promotion: {}", e);
			throw new PromotionException("Unable to create the complimentary promotion!", e);
		}
	}

	@Override
	@Transactional("posTransactionManager")
	public ComplimentaryPromotion updateComplimentaryPromotion(ComplimentaryPromotion complimentaryPromotion) {
		validateComplimentaryPromotion(complimentaryPromotion, true);
		Map<String, Object> headerDetails = complimentaryPromotionDao.getComplimentaryPromotionHeaderDetails(complimentaryPromotion.getComplimentaryId());
		String status = String.valueOf(headerDetails.get(STATUS));
		if(!PromotionConstants.INACTIVE.equals(status))
			throw new PromotionException("Can not update the complimentary promotion with status "+status);
		boolean existingAllCustomers = (boolean) headerDetails.get("AllCustomers");
		if(!complimentaryPromotion.isAllCustomers() && existingAllCustomers && UtilValidate.isEmpty(complimentaryPromotion.getCustomerIds())) {
			throw new PromotionException("CustomerID's are mandatory when all customers is not selected");
		}
		log.info("complimentaryPromotion: {}", complimentaryPromotion);
		try {
			complimentaryPromotionDao.updateComplimentaryPromotion(complimentaryPromotion, headerDetails);
		} catch (Exception e) {
			log.error("Error updating the complimentaryPromotion : {}" + complimentaryPromotion);
			throw new PromotionException("Unable to  update the complimentaryPromotion!", e);
		}
		return complimentaryPromotion;
	}
	
	@Override
	public Map<String, Object> getComplimentaryPromotionList(CampaignSearchCriteria searchCriteria, List<Integer> applicableTypesByRole) {
		if(UtilValidate.isEmpty(applicableTypesByRole) || !applicableTypesByRole.contains(PromotionConstants.APPLICABLE_TYPE_PHARMACY)) 
			throw new PromotionException("User doesn't have the required rights to search!");
		
		searchCriteria = CampaignUtil.setCampaignSearchCriteria(searchCriteria, applicableTypesByRole);
		try {
			return complimentaryPromotionDao.getComplimentaryPromotionList(searchCriteria);
		} catch (Exception e) {
			log.error("Error getting the complimentary promotion list : {}", e);
			return new HashMap<>();
		}
	}
	
	@Override
	public ComplimentaryPromotion getComplimentaryPromotionById(Long complimentaryId) {
		if(UtilValidate.isEmpty(complimentaryId))
			return null;
		try {
			return complimentaryPromotionDao.getComplimentaryPromotionById(complimentaryId);
		} catch (Exception e) {
			log.error("Error getting the complimentary promotion for id {} : {}",complimentaryId,e);
		}		
		return null;
	}

	@Override
	@Transactional("posTransactionManager")
	public boolean approveComplimentaryPromotion(Long complimentaryId, LocalDateTime toDate, List<Integer> applicableTypes, String approvedBy) {
		if(UtilValidate.isEmpty(complimentaryId) || UtilValidate.isEmpty(approvedBy))
			return false;
		Map<String, Object> headerDetails = complimentaryPromotionDao.getComplimentaryPromotionHeaderDetails(complimentaryId);
		String status = String.valueOf(headerDetails.get(STATUS));
		if(!PromotionConstants.INACTIVE.equals(status))
			throw new PromotionException("Can not approve complimenatry promotion with status "+status);
		boolean isDateUpdated = CampaignUtil.validateToDate(headerDetails, "approve", applicableTypes, toDate, toDateMaxLimit, "COMPLIMENTARY");
		boolean approved=true;
		try {
			approved= complimentaryPromotionDao.approveCampaign(complimentaryId, toDate, approvedBy, headerDetails, isDateUpdated);
			pushToPosMaster(complimentaryId);
			if(LocalDateTime.now().isAfter(CampaignUtil.getDateFromMap(headerDetails.get("ValidFrom")))) {
				CampaignUtil.clearPromotionCache(eventPublisher, false);
			}
		}catch (Exception e) {
			log.error("Error approving the complimentary promotion {} : {}",complimentaryId,e);
			throw new PromotionException("Unable to approve the complimentary promotion!", e);
		}
		return approved;
	}

	@Override
	@Transactional("posTransactionManager")
	public boolean updateComplimentaryToDate(Long complimentaryId, LocalDateTime toDate,List<Integer> applicableTypesByRole, String modifiedBy, String status) {
		if(UtilValidate.isEmpty(complimentaryId) || UtilValidate.isEmpty(status) || UtilValidate.isEmpty(modifiedBy))
			return false;
		if(UtilValidate.isEmpty(toDate))
			throw new PromotionException("To Date can not be empty!");
		Map<String, Object> headerDetails = complimentaryPromotionDao.getComplimentaryPromotionHeaderDetails(complimentaryId);
		String promotionStatus = String.valueOf(headerDetails.get(STATUS));
		if(!status.equals(promotionStatus)) {
			throw new PromotionException("Can not update complimentary promotion ToDate with status "+ promotionStatus );
		}
		boolean isToDateUpdated = CampaignUtil.validateToDate(headerDetails, "update", applicableTypesByRole, toDate, toDateMaxLimit, "COMPLIMENTARY");
		boolean updated = true;
		if(isToDateUpdated) {
			try {
				updated= complimentaryPromotionDao.updateComplimentaryToDate(complimentaryId, toDate, modifiedBy, headerDetails);
				if(PromotionConstants.ACTIVE.equals(status)) {
					pushToPosMaster(complimentaryId);
					if(LocalDate.now().equals(toDate.toLocalDate())) {
						CampaignUtil.clearPromotionCache(eventPublisher, false);
					}
				}
			}catch (Exception e) {
				log.error("Error updating the complimentary promotion {} : {}",complimentaryId,e);
				throw new PromotionException("Unable to update the complimentary promotion!", e);
			}
		}
		return updated;
	}

	@Override
	@Transactional("posTransactionManager")
	public boolean rejectComplimentaryPromotion(Long complimentaryId, String rejectedBy, String remarks,List<Integer> applicableTypesByRole) {
		if(UtilValidate.isEmpty(complimentaryId) || UtilValidate.isEmpty(remarks) || UtilValidate.isEmpty(rejectedBy))
			return false;
		Map<String, Object> headerDetails = complimentaryPromotionDao.getComplimentaryPromotionHeaderDetails(complimentaryId);
		int applicableType = Integer.parseInt(String.valueOf(headerDetails.get("ApplicableType")));
		if(UtilValidate.isEmpty(applicableTypesByRole) || !applicableTypesByRole.contains(applicableType)) {
			throw new PromotionException("User doesn't have the rights to reject promotions of the applicable type!");
		}
		String status = String.valueOf(headerDetails.get(STATUS));
		if(!PromotionConstants.INACTIVE.equals(status))
			throw new PromotionException("Can not reject complimentary promotion with status "+status);
		try {
			return complimentaryPromotionDao.rejectComplimentaryPromotion(complimentaryId, rejectedBy, remarks, headerDetails);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new PromotionException("Unable to Reject Complimentary Promotion");
		}
	}
	
	@Override
	public Set<ComplimentaryProduct> getComplimentaryProducts(Long complimentaryId, Long complimentarySlabId) {
		if(UtilValidate.isEmpty(complimentaryId) || UtilValidate.isEmpty(complimentarySlabId))
			return new HashSet<>();
		try {
			return complimentaryPromotionDao.getComplimentaryProducts(complimentaryId, complimentarySlabId);
		} catch (Exception e) {
			log.error("Error getting the complimentary gift products for id {} : {}",complimentaryId,e);
			return new HashSet<>();
		}
	}
	
	@Override
	public Set<String> getComplimentaryReferenceProducts(Long complimentaryId) {
		if(UtilValidate.isEmpty(complimentaryId))
			return new HashSet<>();
		try {
			return complimentaryPromotionDao.getComplimentaryReferenceProducts(complimentaryId);
		} catch (Exception e) {
			log.error("Error getting the reference products for id {} : {}",complimentaryId,e);
			return new HashSet<>();
		}
	}
	
	@Override
	public Set<Integer> getComplimentaryRefCompositons(Long complimentaryId) {
		if(UtilValidate.isEmpty(complimentaryId))
			return new HashSet<>();
		try {
			return complimentaryPromotionDao.getComplimentaryRefCompositons(complimentaryId);
		} catch (Exception e) {
			log.error("Error getting the reference compositions for id {} : {}",complimentaryId,e);
			return new HashSet<>();
		}
	}
	
	@Override
	public Set<Long> getComplimentaryPromotionCustomers(Long complimentaryId) {
		if(UtilValidate.isEmpty(complimentaryId))
			return new HashSet<>();
		try {
			return complimentaryPromotionDao.getComplimentaryPromotionCustomers(complimentaryId);
		} catch (Exception e) {
			log.error("Error getting the complimentary promotion customers for id {} : {}",complimentaryId,e);
			return new HashSet<>();
		}
	}
	
	@Override
	@Transactional("posTransactionManager")
	public void autoRejectClosedComplimentaryPromotions() {
		complimentaryPromotionDao.autoRejectClosedComplimentaryPromotions();
	}

	@Override
	public Map<String, String> getExisitingSlabs() {
		try {
			return complimentaryPromotionDao.getExisitingSlabGroups();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new PromotionException("unable to fetch the existing slab details", e);
		}	
	}

	@Override
	public boolean isPromotionNameAvailable(String promotionName) {
		if(UtilValidate.isEmpty(promotionName))
			return false;
		try {
			return complimentaryPromotionDao.isComplimentaryPromotionNameAvailable(promotionName);
		} catch (Exception e) {
			log.error("Error checking the complimentary promotion name  {} : {}",promotionName,e);
			return false;
		}
	}
	
	@Override
	public boolean isSlabNameAvailable(String slabName) {
		if(UtilValidate.isEmpty(slabName))
			return false;
		try {
			return complimentaryPromotionDao.isSlabNameAvailable(slabName);
		} catch (Exception e) {
			log.error("Error checking the complimentary promotion slab name  {} : {}",slabName,e);
			return false;
		}
	}
	
	private void pushToPosMaster(Long complimentaryId) {
		if(complimentaryPromotionDao.isPosChannel(complimentaryId)) {
			posMasterDataHelper.pushToMasterDataUpdateInfoQueue(PosMasterDataKey.COMPLIMENTORY_PROMOTION_INFO, complimentaryId, new Date());
		}
	}

	@Override
	public List<String> getStores(Long complimentaryId) {
		try {
			return complimentaryPromotionDao.getStores(complimentaryId);
		} catch(Exception e) {
			log.error(e.getMessage(),e);
			return new ArrayList<>();
		}
	}

}
