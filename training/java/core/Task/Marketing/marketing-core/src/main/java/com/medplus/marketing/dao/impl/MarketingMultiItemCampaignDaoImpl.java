package com.medplus.marketing.dao.impl;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.discounts.domain.MultiItemCampaign;
import com.medplus.discounts.domain.MultiItemCampaignMaster;
import com.medplus.discounts.domain.MultiItemSlab;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.dao.MarketingMultiItemCampaignDao;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.util.CampaignUtil;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @author abhijith
 *
 */


@Slf4j
@Repository
public class MarketingMultiItemCampaignDaoImpl implements MarketingMultiItemCampaignDao {

	@Autowired
	@Qualifier("posReadonlyNPJdbcTemplate") 
	NamedParameterJdbcTemplate posReadonlyNPJdbcTemplate;

	@Autowired
	@Qualifier("posNPJdbcTemplate") 
	NamedParameterJdbcTemplate posNPJdbcTemplate;
	
	private static final String GET_ALL_CAMPAIGN_ITEMS = "SELECT MI_CampaignID, ItemID, ItemStatus, CreatedBy, DateCreated, ModifiedBy, DateModified FROM tbl_multiitem_campaign_detail where MI_CampaignID=:campaignId";
	
	private static final String GET_ALL_CAMPAIGN_CUSTOMERS= "SELECT MI_CampaignID, CustomerID, CustomerStatus, CreatedBy, DateCreated, ModifiedBy, DateModified FROM tbl_multiitem_campaign_customers where MI_CampaignID=:campaignId";
	
	private static final String GET_ALL_CAMPAIGN_LOYALTIES = "SELECT MI_CampaignID, LoyaltyType, LoyaltyStatus, CreatedBy, DateCreated, ModifiedBy, DateModified FROM tbl_multiitem_campaign_loyalty where MI_CampaignID =:campaignId";
	
	private static final String GET_ALL_CAMPAIGN_REGIONS = "SELECT MI_CampaignID, Region, NotEligibleRegions, RegionStatus, CreatedBy, DateCreated, ModifiedBy, DateModified FROM tbl_multiitem_campaign_regions where MI_CampaignID=:campaignId";
	
	private static final String GET_ALL_CAMPAIGN_SLABS= "SELECT SlabID, MI_CampaignID, SlabStatus, NumberOfPacks, PriceOrNumberOfPacksForPrice, CreatedBy, DateCreated, ModifiedBy, DateModified FROM tbl_multiitem_campaign_slabs where MI_CampaignID=:campaignId";
	
	private static final String GET_ALL_CAMPAIGN_CHANNELS="SELECT PromotionId, ApplicableType, Channel, PromotionType, CreatedBy from tbl_promotion_channel_mapping where PromotionId=:campaignId and ApplicableType=:applicableType and PromotionType=:campaignType";
	
	private static final String GET_CAMPAIGN_HEADER = "select MI_CampaignID, Name, CampaignType, ApplicableType, AllCustomers, FromDate, ToDate, EffectiveDate, MI_Status, PriceConsideredForSlab, MI_CloneRefID, "
			+ "CreatedBy, DateCreated, ModifiedBy, DateModified, ApprovedBy, DateApproved, Remarks from tbl_multiitem_campaign_header where MI_CampaignID=:campaignId";
	
	private static final String GET_CAMPAIGN_METAINFO_FOR_LOG = "select ID, PromotionId, ApplicableType, PromotionType, Description, ImagePath, ImageServerName, "
			+ "PromotionVisible, Claimable, SpecialtyBased, CreatedBy, DateCreated from tbl_promotion_metainfo_mapping where PromotionId=:campaignId";
	
	private static final String GET_CUSTOMER_IDS_FOR_CAMPAIGN = "SELECT b.CustomerID from tbl_multiitem_campaign_header a "
			+ "inner join tbl_multiitem_campaign_customers b on a.MI_CampaignID = b.MI_CampaignID "
			+ "where a.MI_CampaignID =:campaignId and b.CustomerStatus='A'";
	
	private static final String GET_ITEM_IDS_FOR_CAMPAIGN = "SELECT b.ItemID from tbl_multiitem_campaign_header a "
			+ "inner join tbl_multiitem_campaign_detail b on a.MI_CampaignID = b.MI_CampaignID "
			+ "where a.MI_CampaignID =:campaignId and b.ItemStatus='A' ";
	
	private static final String CREATE_MULTI_ITEM_CAMPAIGN = "INSERT INTO `tbl_multiitem_campaign_header` "
			+ "(`Name`, `CampaignType`, `ApplicableType`, `AllCustomers`, `FromDate`, `ToDate`, `MI_Status`, `PriceConsideredForSlab`, `MI_CloneRefID`, `CreatedBy`, `DateCreated`) "
			+ "VALUES (:campaignName, :campaignType, :applicableType, :allCustomers, :fromDate, :toDate, :status, :priceConsidered, :cloneId, :createdBy, now())";
	
	private static final String INSERT_METAINFO = "INSERT INTO `tbl_promotion_metainfo_mapping` (`PromotionId`, `ApplicableType`, `PromotionType`, `Description`, `ImagePath`, `ImageServerName`, `PromotionVisible`, `Claimable`, `CreatedBy`, `DateCreated`) "
			+ "VALUES (:campaignId, :applicableType, :campaignType, :description, :imagePath, :imageServerName, :promotionVisible, :claimable, :createdBy, now())";
	
	private static final String UPDATE_MULTI_ITEM_CAMPAIGN = "UPDATE `tbl_multiitem_campaign_header` SET "
			+ "`AllCustomers` = :allCustomers, `ToDate` = :toDate, `PriceConsideredForSlab` = :priceConsidered, "
			+ "`ModifiedBy` = :modifiedBy, `DateModified` = now() WHERE (`MI_CampaignID` = :campaignId) and `MI_Status`='I'";
	
	private static final String UPDATE_METAINFO = "UPDATE `tbl_promotion_metainfo_mapping` SET `ApplicableType` = :applicableType, PromotionType = :campaignType, `Description` = :description, `ImagePath` = :imagePath, `ImageServerName` = :imageServerName, `PromotionVisible` = :promotionVisible, `Claimable` = :claimable WHERE (`PromotionId` = :campaignId)";
	
	private static final String INSERT_CAMPAIGN_DETAIL = "INSERT INTO `tbl_multiitem_campaign_detail` (`MI_CampaignID`, `ItemID`, `ItemStatus`, `CreatedBy`, `DateCreated`) "
			+ "VALUES (:campaignId, :itemId, :status, :createdBy, now())";
	
	private static final String INSERT_CAMPAIGN_LOYALTY = "INSERT INTO `tbl_multiitem_campaign_loyalty` (`MI_CampaignID`, `LoyaltyType`, `LoyaltyStatus`, `DateCreated`, `CreatedBy`) "
			+ "VALUES (:campaignId, :loyalty, :status, now(), :createdBy)";
	
	private static final String INSERT_CAMPAIGN_REGIONS = "INSERT INTO `tbl_multiitem_campaign_regions` (`MI_CampaignID`, `Region`, `NotEligibleRegions`, `RegionStatus`, `CreatedBy`, `DateCreated`) "
			+ "VALUES (:campaignId, :region, :notEligibleRegions, :status, :createdBy, now())";
	
	private static final String INSERT_CAMPAIGN_CHANNEL_MAPPING = "INSERT INTO `tbl_promotion_channel_mapping` (`PromotionId`, `ApplicableType`, `Channel`, `PromotionType`, `CreatedBy`) "
			+ "VALUES (:campaignId, :applicableType, :channel, :campaignType, :createdBy)";
	
	private static final String INSERT_CAMPAIGN_SLAB = "INSERT INTO `tbl_multiitem_campaign_slabs` (`MI_CampaignID`, `NumberOfPacks`, `PriceOrNumberOfPacksForPrice`, `SlabStatus`, `CreatedBy`, `DateCreated`) "
			+ "VALUES (:campaignId, :NumberOfPacks, :priceOrNumberOfPacks, :status, :createdBy, now()) ";
	
	private static final String INSERT_CAMPAIGN_CUSTOMERS = "INSERT INTO `tbl_multiitem_campaign_customers` (`MI_CampaignID`, `CustomerID`, `CustomerStatus`, `CreatedBy`, `DateCreated`) "
			+ "VALUES (:campaignId, :customerId, :status, :createdBy, now()) ";
	
	private static final String CAMPAIGN_HEADER_LOG_QUERY = "insert into tbl_multiitem_campaign_header_log (MI_CampaignID, Name, CampaignType, ApplicableType, AllCustomers, FromDate, ToDate, EffectiveDate, MI_Status, PriceConsideredForSlab, MI_CloneRefID, CreatedBy, DateCreated, ModifiedBy, DateModified, ApprovedBy, DateApproved, Remarks ) "
			+ "values (:MI_CampaignID, :Name, :CampaignType, :ApplicableType, :AllCustomers, :FromDate, :ToDate, :EffectiveDate, :MI_Status, :PriceConsideredForSlab, :MI_CloneRefID, :CreatedBy, :DateCreated, :ModifiedBy, :DateModified, :ApprovedBy, :DateApproved, :Remarks )";
	
	private static final String CAMPAIGN_METAINFO_LOG_QUERY = "insert into tbl_promotion_metainfo_mapping_log (ID, PromotionId, ApplicableType, PromotionType, Description, ImagePath, ImageServerName, PromotionVisible, Claimable, SpecialtyBased, CreatedBy, DateCreated) "
			+ "values (:ID, :PromotionId, :ApplicableType, :PromotionType, :Description, :ImagePath, :ImageServerName, :PromotionVisible, :Claimable, :SpecialtyBased, :CreatedBy, :DateCreated)";

	private static final String CAMPAIGN_LOYALTY_LOG_QUERY = "insert into tbl_multiitem_campaign_loyalty_log (`MI_CampaignID`, `LoyaltyType`, `LoyaltyStatus`, `DateCreated`, `CreatedBy`, `ModifiedBy`, `DateModified`) "
			+ "values (:MI_CampaignID, :LoyaltyType, :LoyaltyStatus, :DateCreated, :CreatedBy, :ModifiedBy, :DateModified) ";
	
	private static final String CAMPAIGN_REGION_LOG_QUERY = "insert into tbl_multiitem_campaign_regions_log (`MI_CampaignID`, `Region`, `NotEligibleRegions`, `RegionStatus`, `DateCreated`, `CreatedBy`, `ModifiedBy`, `DateModified`) "
			+ "values (:MI_CampaignID, :Region, :NotEligibleRegions, :RegionStatus, :DateCreated, :CreatedBy, :ModifiedBy, :DateModified)";
	
	private static final String CAMPAIGN_SLABS_LOG_QUERY = "insert into tbl_multiitem_campaign_slabs_log (`SlabID`, `MI_CampaignID`, `NumberOfPacks`, `PriceOrNumberOfPacksForPrice`, `SlabStatus`, `DateCreated`, `CreatedBy`, `ModifiedBy`, `DateModified`) "
			+ "values (:SlabID, :MI_CampaignID, :NumberOfPacks, :PriceOrNumberOfPacksForPrice, :SlabStatus, :DateCreated, :CreatedBy, :ModifiedBy, :DateModified)";
	
	private static final String CAMPAIGN_CUSTOMERS_LOG_QUERY = "insert into tbl_multiitem_campaign_customers_log (`CustomerID`, `MI_CampaignID`, `CustomerStatus`, `DateCreated`, `CreatedBy`, `ModifiedBy`, `DateModified`) "
			+ "values (:CustomerID, :MI_CampaignID, :CustomerStatus, :DateCreated, :CreatedBy, :ModifiedBy, :DateModified)";
	
	private static final String CAMPAIGN_DETAIL_LOG_QUERY = "insert into tbl_multiitem_campaign_detail_log (`ItemID`, `MI_CampaignID`, `ItemStatus`, `DateCreated`, `CreatedBy`, `ModifiedBy`, `DateModified`) "
			+ "values(:ItemID, :MI_CampaignID, :ItemStatus, :DateCreated, :CreatedBy, :ModifiedBy, :DateModified)";
	
	private static final String CAMPAIGN_CHANNEL_LOG_QUERY = "insert into tbl_promotion_channel_mapping_log (`PromotionId`, `ApplicableType`, `Channel`, `PromotionType`, `CreatedBy`) "
			+ "values (:PromotionId, :ApplicableType, :Channel, :PromotionType, :CreatedBy)";
	
	private static final String UPDATE_CAMPAIGN_CUSTOMER = "UPDATE `tbl_multiitem_campaign_customers` SET `CustomerStatus` = :CustomerStatus, `ModifiedBy` = :ModifiedBy, `DateModified` = now() WHERE (`MI_CampaignID` = :MI_CampaignID) and (`CustomerID` = :CustomerID) ";
	
	private static final String UPDATE_CAMPAIGN_DETAIL_STATUS = "UPDATE `tbl_multiitem_campaign_detail` SET `ItemStatus` = :ItemStatus, `ModifiedBy` = :ModifiedBy, `DateModified` = now() WHERE (`MI_CampaignID` = :MI_CampaignID) and (`ItemID` = :ItemID) ";
	
	private static final String UPDATE_CAMPAIGN_LOYALTY_STATUS = "UPDATE `tbl_multiitem_campaign_loyalty` SET `LoyaltyStatus` = :LoyaltyStatus, `ModifiedBy` = :ModifiedBy, `DateModified` = now() WHERE (`MI_CampaignID` = :MI_CampaignID) and (`LoyaltyType` = :LoyaltyType) ";
	
	private static final String UPDATE_CAMPAIGN_REGION = "UPDATE `tbl_multiitem_campaign_regions` SET `NotEligibleRegions` = :NotEligibleRegions, `RegionStatus`=:RegionStatus, `ModifiedBy` = :ModifiedBy, `DateModified` = now() WHERE (`MI_CampaignID` = :MI_CampaignID) and (`Region` = :Region) ";
	
	private static final String UPDATE_CAMPAIGN_SLAB = "UPDATE `tbl_multiitem_campaign_slabs` SET `PriceOrNumberOfPacksForPrice`=:PriceOrNumberOfPacksForPrice, `SlabStatus` = :SlabStatus, `ModifiedBy` = :ModifiedBy, `DateModified` = now() WHERE (`SlabID` = :SlabID) ";
	
	private static final String DELETE_CAMPAIGN_CHANNEL_MAPPING = "DELETE FROM `tbl_promotion_channel_mapping` WHERE (`PromotionId` = :PromotionId) and (`ApplicableType` = :ApplicableType) and (`Channel` = :Channel) and (`PromotionType` = :PromotionType)";
	
	private static final String GET_CAMPAIGNS_LIST = "SELECT count(a.MI_CampaignID) over() as TotalRowCount, a.MI_CampaignID, a.Name, a.ApplicableType, GROUP_CONCAT(b.Channel) as Channel , a.FromDate, a.ToDate, a.EffectiveDate, a.MI_Status, a.MI_CloneRefID, a.CreatedBy, a.DateCreated, a.ApprovedBy, a.DateApproved, a.Remarks FROM tbl_multiitem_campaign_header a "
			+ "inner join tbl_promotion_channel_mapping b on a.MI_CampaignID = b.PromotionId and a.ApplicableType=b.ApplicableType "
			+ "where (1=:campaignIdFlag or a.MI_CampaignID=:campaignId) and (1=:nameFlag or a.Name like :campaignName) and a.ApplicableType in (:applicableType) "
			+ "and (1=:channelFlag or b.Channel in (:channel)) and (1=:statusFlag or a.MI_Status=:status) and (1=:createdByFlag or a.CreatedBy=:createdBy) "
			+ "and (1=:dateFlag or a.DateCreated between :fromDate and :toDate) and (1=:closedFlag or a.ToDate < now())"
			+ "and b.PromotionType IN (:campaignTypes) "
			+ "group by MI_CampaignID order by MI_CampaignID desc limit :limit offset :offset ";
	
	private static final String UPDATE_AND_APPROVE_CAMPAIGN = "UPDATE `tbl_multiitem_campaign_header` SET `ToDate` = :toDate, `MI_Status` = :status, "
			+ "`ApprovedBy` = :approvedBy, `DateApproved` = :dateApproved, `EffectiveDate` = :effectiveDate, `ModifiedBy` = :modifiedBy, `DateModified` = now() WHERE (`MI_CampaignID` = :campaignId)";
	
	private static final String UPDATE_ACTIVE_CAMPAIGN = "UPDATE `tbl_multiitem_campaign_header` SET `ToDate` = :toDate, "
			+ "`ModifiedBy` = :modifiedBy, `DateModified` = now() WHERE (`MI_CampaignID` = :campaignId)";
	
	private static final String APPROVE_OR_REJECT_CAMPAIGN = "UPDATE `tbl_multiitem_campaign_header` SET `MI_Status` = :status, "
			+ "`ApprovedBy` = :approvedBy, `DateApproved` = :dateApproved, `EffectiveDate` = :effectiveDate, `Remarks` = :remarks WHERE (`MI_CampaignID` = :campaignId)";
	
	private static final String CAMPAIGN_NAME_AVAILABILITY_QUERY = "SELECT COUNT(MI_CampaignID) FROM tbl_multiitem_campaign_header WHERE Name=:campaignName";
	
	private static final String GET_CLOSED_CAMPAIGN_HEADER_QUERY = "select MI_CampaignID, Name, CampaignType, ApplicableType, AllCustomers, FromDate, ToDate, EffectiveDate, MI_Status, PriceConsideredForSlab, MI_CloneRefID, "
			+ "CreatedBy, DateCreated, ModifiedBy, DateModified, ApprovedBy, DateApproved, Remarks "
			+ "from tbl_multiitem_campaign_header where ToDate < :toDate and MI_Status='I'";
	
	private static final String GET_STORES_QUERY = "SELECT Region from `tbl_multiitem_campaign_regions` WHERE MI_CampaignID= :campaignId and LENGTH(`Region`) = 12 and RegionStatus='A'";

	private static final String CAMPAIGN_ID = "campaignId";
	
	private static final String CAMPAIGN_NAME = "campaignName";
	
	private static final String STATUS = "status";
	
	private static final String FROM_DATE = "fromDate";
	
	private static final String TO_DATE = "toDate";
	
	private static final String CREATED_BY = "createdBy";
	
	private static final String APPLICABLE_TYPE = "applicableType";
	
	private static final String CAMPAIGN_TYPE = "campaignType";
	
	private static final String CHANNEL = "channel";
	
	private static final String LOYALTY = "loyalty";
	
	private static final String MODIFIED_BY = "modifiedBy";
	
	private static final String NOT_ELIGIBLE_REGIONS = "notEligibleRegions";
	
	private static final String CHANNEL_FIELD = "Channel";
	
	private static final String NER = "NotEligibleRegions";
	
	private static final String MODIFIED_BY_FIELD = "ModifiedBy";
	
	private static final String LOYALTY_TYPE = "LoyaltyType";
	
	private static final String REGION = "Region";
	
	private static final String PONUP = "PriceOrNumberOfPacksForPrice";
	
	private static final String CUSTOMER_STATUS = "CustomerStatus";
	
	private static final String LOYALTY_STATUS = "LoyaltyStatus";
	
	private static final String REGION_STATUS = "RegionStatus";
	
	private static final String ITEM_STATUS = "ItemStatus";
	
	private static final String SLAB_STATUS = "SlabStatus";
	
	private static final String CUSTOMER_ID = "CustomerID";
	
	private static final String ITEM_ID = "ItemID";
	
	private static final String MI_CAMPAIGNID = "MI_CampaignID";
	
	private static final String REMARKS = "remarks";
	
	private static final String APPROVED_BY = "approvedBy";
	
	private static final String EFFECTIVE_DATE = "effectiveDate";
	
	private static final String DATE_APPROVED = "dateApproved";
	
	private static final String AUTO_REJECT_REMARKS = "Auto-rejected, not approved till end date";

	@Override
	public MultiItemCampaignMaster createMultiItemCampaign(MultiItemCampaignMaster campaign) {
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String priceConsidered = campaign.getPriceConsideredForSlab();
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue(CAMPAIGN_NAME,campaign.getCampaignName());
		source.addValue(CAMPAIGN_TYPE, campaign.getCampaignType());
		source.addValue(APPLICABLE_TYPE, campaign.getApplicableType());
		source.addValue("allCustomers", campaign.isAllCustomers() ? "Y" : "N");
		source.addValue(FROM_DATE, campaign.getFromDate());
		source.addValue(TO_DATE, campaign.getToDate());
		source.addValue(STATUS, campaign.getStatus());
		source.addValue("priceConsidered", UtilValidate.isEmpty(priceConsidered) ? PromotionConstants.DEFAULT_PRICE_CONSIDERED_FOR_SLAB_FOR_MULTI_ITEM_CAMPAIGN : priceConsidered);
		source.addValue("cloneId", campaign.getCloneReferenceId());
		source.addValue(CREATED_BY, campaign.getUserMetaData().getCreatedBy());
		
		posNPJdbcTemplate.update(CREATE_MULTI_ITEM_CAMPAIGN, source, generatedKeyHolder);
		if(generatedKeyHolder.getKey()==null)
			return null;
		int campaignId = generatedKeyHolder.getKey().intValue();	
		campaign.setCampaignId(campaignId);
		String createdBy = campaign.getUserMetaData().getCreatedBy();
		
		source = new MapSqlParameterSource();
		source.addValue(CAMPAIGN_ID, campaignId);
		source.addValue(APPLICABLE_TYPE, campaign.getApplicableType());
		source.addValue(CAMPAIGN_TYPE, Integer.valueOf(campaign.getCampaignType()));
		source.addValue("description", campaign.getUserMetaData().getLongDescription());
		source.addValue("imagePath", campaign.getUserMetaData().getImagePath());
		source.addValue("imageServerName", campaign.getUserMetaData().getImageServerName());
		source.addValue("promotionVisible", null);
		source.addValue("claimable", campaign.getUserMetaData().isClaimable() ? 1 : 0);
		source.addValue(CREATED_BY, campaign.getUserMetaData().getCreatedBy());
		
		posNPJdbcTemplate.update(INSERT_METAINFO, source);
		
		saveLoyalty(campaignId, campaign.getLoyalty(), createdBy);
		saveChannels(campaignId, campaign.getChannels(), campaign.getApplicableType(), campaign.getCampaignType(), createdBy);
		saveItems(campaignId, campaign.getItems(), createdBy);
		saveRegions(campaignId, campaign.getRegions(), createdBy);
		saveSlabs(campaignId, campaign.getMultiItemSlabs(), createdBy);
	    if(!campaign.isAllCustomers()) {
	    	saveCustomers(campaignId, campaign.getCustomerIds(), createdBy);
		}
	    return campaign;
	}
	
	private void saveItems(int campaignId, Set<String> items, String createdBy) {
		List<MapSqlParameterSource> paramsList = new ArrayList<>();
	    for (String item : items) {
	    	MapSqlParameterSource params = new MapSqlParameterSource();
	    	params.addValue(CAMPAIGN_ID, campaignId);
	    	params.addValue("itemId", item);
	    	params.addValue(STATUS, PromotionConstants.ACTIVE);
	    	params.addValue(CREATED_BY, createdBy);
	      	paramsList.add(params);
	    }
	    posNPJdbcTemplate.batchUpdate(INSERT_CAMPAIGN_DETAIL, paramsList.toArray(new MapSqlParameterSource[paramsList.size()]));
	}
	
	private void saveLoyalty(int campaignId, List<Integer> loyalties, String createdBy) {
		List<MapSqlParameterSource> paramsList = new ArrayList<>();
		for(Integer loyalty : loyalties) {
	    	MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(CAMPAIGN_ID, campaignId);
			params.addValue(LOYALTY, loyalty);
			params.addValue(STATUS, PromotionConstants.ACTIVE);
			params.addValue(CREATED_BY, createdBy);
	    	paramsList.add(params);
	    }
	    posNPJdbcTemplate.batchUpdate(INSERT_CAMPAIGN_LOYALTY, paramsList.toArray(new MapSqlParameterSource[paramsList.size()]));
	}
	
	private void saveSlabs(int campaignId, List<MultiItemSlab> slabs, String createdBy) {
		List<MapSqlParameterSource> paramsList = new ArrayList<>();
		for(MultiItemSlab slab : slabs) {
	    	MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(STATUS, PromotionConstants.ACTIVE);
			params.addValue(CAMPAIGN_ID, campaignId);
			params.addValue("NumberOfPacks", slab.getNumberOfPacks());
			params.addValue("priceOrNumberOfPacks", slab.getSellingPriceOrNumberOfPacks());
			params.addValue(CREATED_BY, createdBy);
			paramsList.add(params);
	    }
	    posNPJdbcTemplate.batchUpdate(INSERT_CAMPAIGN_SLAB, paramsList.toArray(new MapSqlParameterSource[paramsList.size()]));
	}
	
	private void saveRegions(int campaignId, Map<String,List<String>> regions, String createdBy) {
		List<MapSqlParameterSource> paramsList = new ArrayList<>();
		regions.entrySet().forEach(entry -> {
	    	MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(CAMPAIGN_ID, campaignId);
			params.addValue("region", entry.getKey());
			params.addValue(STATUS, PromotionConstants.ACTIVE);
			params.addValue(CREATED_BY, createdBy);
	    	String ner = String.join(",", entry.getValue());
			params.addValue(NOT_ELIGIBLE_REGIONS, UtilValidate.isEmpty(ner) ? null : ner);
			paramsList.add(params);
	    });
	    posNPJdbcTemplate.batchUpdate(INSERT_CAMPAIGN_REGIONS, paramsList.toArray(new MapSqlParameterSource[paramsList.size()]));
	}
	
	private void saveChannels(int campaignId, List<Integer> channels, int applicableType, String campaignType, String createdBy) {
		List<MapSqlParameterSource> paramsList = new ArrayList<>();
		for(Integer channel : channels) {
	    	MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(CAMPAIGN_ID, campaignId);
	    	params.addValue(APPLICABLE_TYPE, applicableType);
	    	params.addValue(CHANNEL, channel);
	    	params.addValue(CAMPAIGN_TYPE, campaignType);
			params.addValue(CREATED_BY, createdBy);
	    	paramsList.add(params);
	    }
	    posNPJdbcTemplate.batchUpdate(INSERT_CAMPAIGN_CHANNEL_MAPPING, paramsList.toArray(new MapSqlParameterSource[paramsList.size()]));
	}
	
	private void saveCustomers(int campaignId, Set<Long> customers, String createdBy) {
		List<MapSqlParameterSource> paramsList = new ArrayList<>();
		for(Long customerId : customers) {
    		MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(CAMPAIGN_ID, campaignId);
			params.addValue("customerId", customerId);
			params.addValue(STATUS, PromotionConstants.ACTIVE);
			params.addValue(CREATED_BY, createdBy);
			paramsList.add(params);
    	}
	    posNPJdbcTemplate.batchUpdate(INSERT_CAMPAIGN_CUSTOMERS, paramsList.toArray(new MapSqlParameterSource[paramsList.size()]));
	}

	@Override
	public MultiItemCampaignMaster updateMultiItemCampaign(MultiItemCampaignMaster campaign, Map<String, Object> headerDetails) {
		
		posNPJdbcTemplate.update(CAMPAIGN_HEADER_LOG_QUERY, headerDetails);
		boolean existingAllCustomers = "Y".equals(String.valueOf(headerDetails.get("AllCustomers")));
		String priceConsidered = campaign.getPriceConsideredForSlab();
		MapSqlParameterSource params = new MapSqlParameterSource();
		int campaignId=campaign.getCampaignId();
		params.addValue(CAMPAIGN_ID, campaignId);
		params.addValue("allCustomers", campaign.isAllCustomers() ? "Y" : "N");
		params.addValue(TO_DATE, campaign.getToDate());
		params.addValue("priceConsidered", UtilValidate.isEmpty(priceConsidered) ? PromotionConstants.DEFAULT_PRICE_CONSIDERED_FOR_SLAB_FOR_MULTI_ITEM_CAMPAIGN : priceConsidered);
		params.addValue(MODIFIED_BY, campaign.getUserMetaData().getModifiedBy());
		posNPJdbcTemplate.update(UPDATE_MULTI_ITEM_CAMPAIGN, params);
		
		params = new MapSqlParameterSource();
		params.addValue(CAMPAIGN_ID, campaignId);
		
		Map<String,Object> metainfoDetails=posNPJdbcTemplate.queryForMap(GET_CAMPAIGN_METAINFO_FOR_LOG, params);
		posNPJdbcTemplate.update(CAMPAIGN_METAINFO_LOG_QUERY, metainfoDetails);
		
		params.addValue(APPLICABLE_TYPE, campaign.getApplicableType());
		params.addValue(CAMPAIGN_TYPE, Integer.valueOf(campaign.getCampaignType()));
		params.addValue("description", campaign.getUserMetaData().getLongDescription());
		params.addValue("imagePath", campaign.getUserMetaData().getImagePath());
		params.addValue("imageServerName", campaign.getUserMetaData().getImageServerName());
		params.addValue("promotionVisible", null);
		params.addValue("claimable", campaign.getUserMetaData().isClaimable() ? 1 : 0);
		posNPJdbcTemplate.update(UPDATE_METAINFO, params);
		
		updateCampaignItems(campaign);
		updateCampaignLoyalties(campaign);
		updateCampaignChannels(campaign);
		updateCampaignSlabs(campaign);
		updateCampaignRegions(campaign);
		if(campaign.isAllCustomers()) {
			if(!existingAllCustomers)
				deactivateCustomers(campaign);
		}else{
			updateCampaignCustomers(campaign);
		}
		return campaign;
	}
	
	private void deactivateCustomers(MultiItemCampaignMaster campaign) {
		List<Map<String, Object>> campaignCustomers=posNPJdbcTemplate.queryForList(GET_ALL_CAMPAIGN_CUSTOMERS, new MapSqlParameterSource(CAMPAIGN_ID, campaign.getCampaignId()));
		campaignCustomers=campaignCustomers.stream().filter(map -> map.get(CUSTOMER_STATUS).equals(PromotionConstants.ACTIVE)).collect(Collectors.toList());
		posNPJdbcTemplate.batchUpdate(CAMPAIGN_CUSTOMERS_LOG_QUERY, campaignCustomers.toArray(new Map[campaignCustomers.size()]));
		campaignCustomers.forEach(map -> {
			map.put(CUSTOMER_STATUS, PromotionConstants.INACTIVE);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});
		posNPJdbcTemplate.batchUpdate(UPDATE_CAMPAIGN_CUSTOMER, campaignCustomers.toArray(new Map[campaignCustomers.size()]));
	}
	
	private void updateCampaignCustomers(MultiItemCampaignMaster campaign) {
		Set<Long> uploadedCustomers = new HashSet<>(campaign.getCustomerIds());
		List<Map<String, Object>> campaignCustomers=posNPJdbcTemplate.queryForList(GET_ALL_CAMPAIGN_CUSTOMERS, new MapSqlParameterSource(CAMPAIGN_ID, campaign.getCampaignId()));
		Map<String, Map<Long, Map<String, Object>>> statusMap = campaignCustomers.stream()
                .collect(Collectors.groupingBy(
                        map -> (String)map.get(CUSTOMER_STATUS), 
                        Collectors.toMap(
                                map -> Long.valueOf(String.valueOf(map.get(CUSTOMER_ID))), 
                                map -> map,
                                (existing, replacement) -> existing 
                        )
                ));
		Map<Long, Map<String, Object>> activeMap = statusMap.getOrDefault(PromotionConstants.ACTIVE, new HashMap<>());
		Map<Long, Map<String, Object>> inactiveMap = statusMap.getOrDefault(PromotionConstants.INACTIVE, new HashMap<>());
		
		Set<Long> removedCustomers = new HashSet<>(campaign.getRemovedCustomerIds());
		
		Set<Long> commonCustomers = new HashSet<>(uploadedCustomers.stream().filter(removedCustomers::contains).collect(Collectors.toSet()));
		uploadedCustomers.removeAll(commonCustomers);
		removedCustomers.removeAll(commonCustomers);
		
		uploadedCustomers.removeAll(activeMap.keySet());
		
		Set<Long> toBeActiveCustomers = uploadedCustomers.stream().filter(inactiveMap::containsKey).collect(Collectors.toSet());
		uploadedCustomers.removeAll(toBeActiveCustomers);
		
		removedCustomers.retainAll(activeMap.keySet());
		
		log.debug("uploadedCustomers : {}", uploadedCustomers);
		log.debug("toBeActiveCustomers : {}", toBeActiveCustomers);
		log.debug("removedCustomers : {}", removedCustomers);
		saveCustomers(campaign.getCampaignId(), uploadedCustomers, campaign.getUserMetaData().getModifiedBy());
		
		List<Map<String, Object>> toBeActive = toBeActiveCustomers.stream().map(inactiveMap::get).collect(Collectors.toList());
		List<Map<String, Object>> toBeRemoved = removedCustomers.stream().map(activeMap::get).collect(Collectors.toList());
		List<Map<String, Object>> logList = new ArrayList<>(toBeActive);
		logList.addAll(toBeRemoved);
		
		posNPJdbcTemplate.batchUpdate(CAMPAIGN_CUSTOMERS_LOG_QUERY, logList.toArray(new Map[logList.size()]));
		toBeActive.forEach(map -> {
			map.put(CUSTOMER_STATUS, PromotionConstants.ACTIVE);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});
		toBeRemoved.forEach(map -> {
			map.put(CUSTOMER_STATUS, PromotionConstants.INACTIVE);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});
		toBeActive.addAll(toBeRemoved);
		posNPJdbcTemplate.batchUpdate(UPDATE_CAMPAIGN_CUSTOMER, toBeActive.toArray(new Map[toBeActive.size()]));
	}

	private void updateCampaignItems(MultiItemCampaignMaster campaign) {
		
		Set<String> uploadedItems = new HashSet<>(campaign.getItems());
		List<Map<String, Object>> campaignItems=posNPJdbcTemplate.queryForList(GET_ALL_CAMPAIGN_ITEMS, new MapSqlParameterSource(CAMPAIGN_ID, campaign.getCampaignId()));
		Map<String, Map<String, Map<String, Object>>> statusMap = campaignItems.stream()
                .collect(Collectors.groupingBy(
                        map -> (String)map.get(ITEM_STATUS),
                        Collectors.toMap(
                                map -> (String)map.get(ITEM_ID),
                                map -> map,
                                (existing, replacement) -> existing
                        )
                ));
		Map<String, Map<String, Object>> activeMap = statusMap.getOrDefault(PromotionConstants.ACTIVE, new HashMap<>());
		Map<String, Map<String, Object>> inactiveMap = statusMap.getOrDefault(PromotionConstants.INACTIVE, new HashMap<>());
		Set<String> removedItems = new HashSet<>(campaign.getRemovedItems());
		
		Set<String> commonItems=new HashSet<>(uploadedItems.stream().filter(removedItems::contains).collect(Collectors.toSet()));
		uploadedItems.removeAll(commonItems);
		removedItems.removeAll(commonItems);
		
		uploadedItems.removeAll(activeMap.keySet());
		
		Set<String> toBeActiveItems = uploadedItems.stream().filter(inactiveMap::containsKey).collect(Collectors.toSet());
		uploadedItems.removeAll(toBeActiveItems);

		removedItems.retainAll(activeMap.keySet());
		log.debug("Uploaded Items : {}", uploadedItems);
		log.debug("toBeActiveItems : {}", toBeActiveItems);
		log.debug("removedItems : {}", removedItems);
		saveItems(campaign.getCampaignId(), uploadedItems, campaign.getUserMetaData().getModifiedBy());
		
		List<Map<String, Object>> toBeActive = toBeActiveItems.stream().map(inactiveMap::get).collect(Collectors.toList());
		List<Map<String, Object>> toBeRemoved = removedItems.stream().map(activeMap::get).collect(Collectors.toList());
		List<Map<String, Object>> logList = new ArrayList<>(toBeActive);
		logList.addAll(toBeRemoved);
		posNPJdbcTemplate.batchUpdate(CAMPAIGN_DETAIL_LOG_QUERY, logList.toArray(new Map[logList.size()]));
		
		toBeActive.forEach(map -> {
			map.put(ITEM_STATUS, PromotionConstants.ACTIVE);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});
		
		toBeRemoved.forEach(map -> {
			map.put(ITEM_STATUS, PromotionConstants.INACTIVE);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});
		toBeActive.addAll(toBeRemoved);
		posNPJdbcTemplate.batchUpdate(UPDATE_CAMPAIGN_DETAIL_STATUS, toBeActive.toArray(new Map[toBeActive.size()]));
	}
	
	private void updateCampaignRegions(MultiItemCampaignMaster campaign) {
		Map<String, List<String>> updatedRegions = new HashMap<>(campaign.getRegions());
		List<Map<String,Object>> campaignRegions = posNPJdbcTemplate.queryForList(GET_ALL_CAMPAIGN_REGIONS, new MapSqlParameterSource(CAMPAIGN_ID, campaign.getCampaignId()));
		Map<String, Map<String, Map<String, Object>>> statusMap = campaignRegions.stream()
                .collect(Collectors.groupingBy(
                        map -> (String)map.get(REGION_STATUS), 
                        Collectors.toMap(
                                map -> (String)map.get(REGION), 
                                map -> map, 
                                (existing, replacement) -> existing 
                        )
                ));
		Map<String, Map<String, Object>> activeMap = statusMap.getOrDefault(PromotionConstants.ACTIVE, new HashMap<>());
		Map<String, Map<String, Object>> inactiveMap = statusMap.getOrDefault(PromotionConstants.INACTIVE, new HashMap<>());
		
		List<String> toBeRemoved = new ArrayList<>(activeMap.keySet());
		toBeRemoved.removeAll(updatedRegions.keySet());
		
		updatedRegions.entrySet().removeIf(entry -> activeMap.containsKey(entry.getKey()) && 
				( (UtilValidate.isEmpty(activeMap.get(entry.getKey()).get(NER)) && UtilValidate.isEmpty(String.join(",", entry.getValue()))) || Objects.equals(activeMap.get(entry.getKey()).get(NER) ,String.join(",", entry.getValue()) ) )
				);
		
		Map<String, List<String>> inactiveRegions= new HashMap<>(updatedRegions.entrySet().stream().filter(entry -> inactiveMap.containsKey(entry.getKey())).collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
		
		Map<String, List<String>> activeDifferentNERs = new HashMap<>(updatedRegions.entrySet().stream()
				.filter(entry -> activeMap.containsKey(entry.getKey()) && !( Objects.equals(activeMap.get(entry.getKey()).get(NER),String.join(",", entry.getValue())) || ( UtilValidate.isEmpty(activeMap.get(entry.getKey()).get(NER)) && UtilValidate.isEmpty(String.join(",", entry.getValue())) ) ))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
		
		updatedRegions.entrySet().removeAll(inactiveRegions.entrySet());
		updatedRegions.entrySet().removeAll(activeDifferentNERs.entrySet());
		
		log.debug("Updated Regions : {}", updatedRegions);
		log.debug("toBeActiveItems : {}", inactiveRegions);
		log.debug("activeDifferentNERregions : {}", activeDifferentNERs);
		log.debug("removed regions : {}", toBeRemoved);
		saveRegions(campaign.getCampaignId(), updatedRegions, campaign.getUserMetaData().getModifiedBy());
		
		List<Map<String, Object>> inactiveRegionsList = inactiveRegions.keySet().stream().map(inactiveMap::get).collect(Collectors.toList());
		List<Map<String, Object>> updatedList = activeDifferentNERs.keySet().stream().map(activeMap::get).collect(Collectors.toList());
		List<Map<String, Object>> toBeRemovedList = toBeRemoved.stream().map(activeMap::get).collect(Collectors.toList());
		
		List<Map<String, Object>> logList = new ArrayList<>(updatedList);
		logList.addAll(inactiveRegionsList);
		logList.addAll(toBeRemovedList);
		
		posNPJdbcTemplate.batchUpdate(CAMPAIGN_REGION_LOG_QUERY, logList.toArray(new Map[logList.size()]));

		inactiveRegionsList.forEach(map -> {
			String ners = String.join(",",inactiveRegions.get(map.get(REGION))); 
			map.put(REGION_STATUS, PromotionConstants.ACTIVE);
			map.put(NER, UtilValidate.isEmpty(ners) ? null : ners);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});
		
		updatedList.forEach(map -> {
			String ners = String.join(",",activeDifferentNERs.get(map.get(REGION)));
			map.put(REGION_STATUS, PromotionConstants.ACTIVE);
			map.put(NER, UtilValidate.isEmpty(ners) ? null : ners);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});
		
		toBeRemovedList.forEach(map -> {
			map.put(REGION_STATUS, PromotionConstants.INACTIVE);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});

		updatedList.addAll(inactiveRegionsList);
		updatedList.addAll(toBeRemovedList);
		posNPJdbcTemplate.batchUpdate(UPDATE_CAMPAIGN_REGION, updatedList.toArray(new Map[updatedList.size()]));

	}

	private void updateCampaignSlabs(MultiItemCampaignMaster campaign) {
		List<MultiItemSlab> updatedSlabs = new ArrayList<>(campaign.getMultiItemSlabs());
		List<Map<String,Object>> campaignSlabs = posNPJdbcTemplate.queryForList(GET_ALL_CAMPAIGN_SLABS, new MapSqlParameterSource(CAMPAIGN_ID, campaign.getCampaignId()));
		Map<String, Map<Long, Map<String, Object>>> statusMap = campaignSlabs.stream()
                .collect(Collectors.groupingBy(
                        map -> (String)map.get(SLAB_STATUS),
                        Collectors.toMap(
                                map -> Long.parseLong(String.valueOf(map.get("NumberOfPacks"))),
                                map -> map, 
                                (existing, replacement) -> existing 
                        )
                ));
		Map<Long, Map<String, Object>> activeMap = statusMap.getOrDefault(PromotionConstants.ACTIVE, new HashMap<>());
		Map<Long, Map<String, Object>> inactiveMap = statusMap.getOrDefault(PromotionConstants.INACTIVE, new HashMap<>());
		
		List<MultiItemSlab> toBeRemovedSlabs = new ArrayList<>(activeMap.keySet().stream().map(unit -> new MultiItemSlab((Integer)activeMap.get(unit).get("SlabID"), unit, Double.valueOf(String.valueOf(activeMap.get(unit).get(PONUP))))).collect(Collectors.toList()));
		toBeRemovedSlabs.removeIf(removedSlab -> updatedSlabs.stream().map(MultiItemSlab::getNumberOfPacks).collect(Collectors.toSet()).contains(removedSlab.getNumberOfPacks()));
		
		updatedSlabs.removeIf(slab -> activeMap.containsKey(slab.getNumberOfPacks()) && Objects.equals(Double.valueOf(String.valueOf(activeMap.get(slab.getNumberOfPacks()).get(PONUP))), slab.getSellingPriceOrNumberOfPacks()));
		
		List<MultiItemSlab> differentPriceActiveSlabs= new ArrayList<>(updatedSlabs.stream().filter(slab -> activeMap.containsKey(slab.getNumberOfPacks())).collect(Collectors.toList()));
		updatedSlabs.removeAll(differentPriceActiveSlabs);
		
		List<MultiItemSlab> toBeActiveSlabs = updatedSlabs.stream().filter(slab -> inactiveMap.containsKey(slab.getNumberOfPacks())).collect(Collectors.toList());
		updatedSlabs.removeAll(toBeActiveSlabs);
		
		log.debug("Updated Slabs : {}", updatedSlabs);
		log.debug("toBeActiveSlabs : {}", toBeActiveSlabs);
		log.debug("removedSlabs : {}", toBeRemovedSlabs);
		saveSlabs(campaign.getCampaignId(), updatedSlabs, campaign.getUserMetaData().getModifiedBy());

		List<Map<String, Object>> updateList = toBeActiveSlabs.stream().map(slab -> inactiveMap.get(slab.getNumberOfPacks())).collect(Collectors.toList());
		List<Map<String, Object>> activeSlabsList = differentPriceActiveSlabs.stream().map(slab -> activeMap.get(slab.getNumberOfPacks())).collect(Collectors.toList());
		List<Map<String, Object>> toBeRemoved=toBeRemovedSlabs.stream().map(slab -> activeMap.get(slab.getNumberOfPacks())).collect(Collectors.toList());
		
		List<Map<String, Object>> logList = new ArrayList<>(updateList);
		logList.addAll(activeSlabsList);
		logList.addAll(toBeRemoved);
		
		posNPJdbcTemplate.batchUpdate(CAMPAIGN_SLABS_LOG_QUERY, logList.toArray(new Map[logList.size()]));
		
		
		toBeActiveSlabs.stream().forEach(slab -> inactiveMap.get(slab.getNumberOfPacks()).put(PONUP, slab.getSellingPriceOrNumberOfPacks()));
		differentPriceActiveSlabs.stream().forEach(slab -> activeMap.get(slab.getNumberOfPacks()).put(PONUP, slab.getSellingPriceOrNumberOfPacks()));

		updateList = toBeActiveSlabs.stream().map(slab -> inactiveMap.get(slab.getNumberOfPacks())).collect(Collectors.toList());
		activeSlabsList = differentPriceActiveSlabs.stream().map(slab -> activeMap.get(slab.getNumberOfPacks())).collect(Collectors.toList());

		updateList.forEach(map -> {
			map.put(SLAB_STATUS, PromotionConstants.ACTIVE);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});
		
		activeSlabsList.forEach(map -> {
			map.put(SLAB_STATUS, PromotionConstants.ACTIVE);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});
		
		toBeRemoved.forEach(map -> {
			map.put(SLAB_STATUS, PromotionConstants.INACTIVE);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});
		
		updateList.addAll(activeSlabsList);
		updateList.addAll(toBeRemoved);
		posNPJdbcTemplate.batchUpdate(UPDATE_CAMPAIGN_SLAB, updateList.toArray(new Map[updateList.size()]));

	}

	private void updateCampaignChannels(MultiItemCampaignMaster campaign) {
		List<Integer> updatedChannels = new ArrayList<>(campaign.getChannels());
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CAMPAIGN_ID, campaign.getCampaignId());
		params.addValue(APPLICABLE_TYPE, campaign.getApplicableType());
		params.addValue(CAMPAIGN_TYPE, campaign.getCampaignType());
		List<Map<String,Object>> campaignChannels = posNPJdbcTemplate.queryForList(GET_ALL_CAMPAIGN_CHANNELS, params);
		Map<Integer, Map<String, Object>> activeMap = campaignChannels.stream().collect(Collectors.toMap(
                    map -> (Integer)map.get(CHANNEL_FIELD),
                    map -> map, 
                    (existing, replacement) -> existing 
                ));
		
		List<Integer> toBeRemovedChannels = new ArrayList<>(activeMap.keySet());
		toBeRemovedChannels.removeAll(updatedChannels);
		
		updatedChannels.removeAll(activeMap.keySet());
		log.debug("toBeRemovedChannels: {}", toBeRemovedChannels);
		log.debug("updatedChannels: {}", updatedChannels);
		saveChannels(campaign.getCampaignId(), updatedChannels, campaign.getApplicableType(), campaign.getCampaignType(), campaign.getUserMetaData().getModifiedBy());
		
		List<Map<String, Object>> toBeRemoved=toBeRemovedChannels.stream().map(activeMap::get).collect(Collectors.toList());
		posNPJdbcTemplate.batchUpdate(CAMPAIGN_CHANNEL_LOG_QUERY, toBeRemoved.toArray(new Map[toBeRemoved.size()]));
		posNPJdbcTemplate.batchUpdate(DELETE_CAMPAIGN_CHANNEL_MAPPING, toBeRemoved.toArray(new Map[toBeRemoved.size()]));

	}

	private void updateCampaignLoyalties(MultiItemCampaignMaster campaign) {
		List<Integer> updatedLoyalties = new ArrayList<>(campaign.getLoyalty());
		List<Map<String,Object>> campaignLoyalties = posNPJdbcTemplate.queryForList(GET_ALL_CAMPAIGN_LOYALTIES, new MapSqlParameterSource(CAMPAIGN_ID, campaign.getCampaignId()));
		Map<String, Map<Integer, Map<String, Object>>> statusMap = campaignLoyalties.stream()
                .collect(Collectors.groupingBy(
                        map -> (String)map.get(LOYALTY_STATUS),
                        Collectors.toMap(
                                map -> (Integer)map.get(LOYALTY_TYPE),
                                map -> map, 
                                (existing, replacement) -> existing 
                        )
                ));
		Map<Integer, Map<String, Object>> activeMap = statusMap.getOrDefault(PromotionConstants.ACTIVE, new HashMap<>());
		Map<Integer, Map<String, Object>> inactiveMap = statusMap.getOrDefault(PromotionConstants.INACTIVE, new HashMap<>());

		List<Integer> toBeRemovedLoyalties = new ArrayList<>(activeMap.keySet());
		toBeRemovedLoyalties.removeAll(updatedLoyalties);
		
		updatedLoyalties.removeAll(activeMap.keySet());
		
		List<Integer> toBeActiveLoyalties = updatedLoyalties.stream().filter(inactiveMap::containsKey).collect(Collectors.toList());
		updatedLoyalties.removeAll(toBeActiveLoyalties);
		
		log.debug("Updated Loyalties : {}", updatedLoyalties);
		log.debug("toBeActiveLoyalties : {}", toBeActiveLoyalties);
		log.debug("removedLoyalties : {}", toBeRemovedLoyalties);
		saveLoyalty(campaign.getCampaignId(), updatedLoyalties, campaign.getUserMetaData().getModifiedBy());
		
		List<Map<String, Object>> toBeActive=toBeActiveLoyalties.stream().map(inactiveMap::get).collect(Collectors.toList());
		List<Map<String, Object>> toBeRemoved=toBeRemovedLoyalties.stream().map(activeMap::get).collect(Collectors.toList());
		List<Map<String, Object>> logList = new ArrayList<>(toBeActive);
		logList.addAll(toBeRemoved);
		posNPJdbcTemplate.batchUpdate(CAMPAIGN_LOYALTY_LOG_QUERY, logList.toArray(new Map[logList.size()]));
		
		toBeActive.forEach(map -> {
			map.put(LOYALTY_STATUS, PromotionConstants.ACTIVE);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});
		
		toBeRemoved.forEach(map -> {
			map.put(LOYALTY_STATUS, PromotionConstants.INACTIVE);
			map.put(MODIFIED_BY_FIELD, campaign.getUserMetaData().getModifiedBy());
		});
		
		toBeActive.addAll(toBeRemoved);
		posNPJdbcTemplate.batchUpdate(UPDATE_CAMPAIGN_LOYALTY_STATUS, toBeActive.toArray(new Map[toBeActive.size()]));
		
	}

	@Override
	public Map<String, Object> getMultiItemCampaignList(CampaignSearchCriteria searchCriteria) {
		Integer campaignId = searchCriteria.getCampaignId();
		int campaignIdFlag= campaignId!=null ? 0 : 1;
		String name = searchCriteria.getCampaignName();
		if(UtilValidate.isNotEmpty(name)) 
			name="%"+name+"%";
		List<Integer> channels = searchCriteria.getChannels();
		List<Integer> applicableTypes = searchCriteria.getApplicableTypes();
		String status = searchCriteria.getStatus();
		int closedFlag=1;
		int statusFlag=1;
		if("C".equals(status)&&campaignIdFlag==1) {
			closedFlag=0;
			statusFlag=0;
			status="A";
		}else {
			statusFlag=getFlag(status,campaignIdFlag);
		}
		String createdBy = searchCriteria.getCreatedBy();
		LocalDateTime fromDate = searchCriteria.getFromDate();
		LocalDateTime toDate = searchCriteria.getToDate();
		int dateFlag = getFlag(fromDate, campaignIdFlag)==0 && getFlag(toDate, campaignIdFlag)==0 ? 0 : 1;
		if(dateFlag==0) {
			toDate=toDate.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
			fromDate=fromDate.withHour(0).withMinute(0).withSecond(0).withNano(0);
		}		
		int offset = searchCriteria.getOffset();
		int limit = searchCriteria.getLimit();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CAMPAIGN_ID, campaignId);
		params.addValue(CAMPAIGN_NAME, name);
		params.addValue(APPLICABLE_TYPE, applicableTypes);
		params.addValue(CHANNEL, channels);
		params.addValue(STATUS, status);
		params.addValue(CREATED_BY, createdBy);
		params.addValue(FROM_DATE, fromDate);
		params.addValue(TO_DATE, toDate);
		params.addValue("offset", offset);
		params.addValue("limit", limit);
		params.addValue("campaignIdFlag", campaignIdFlag);
		params.addValue("nameFlag", getFlag(name, campaignIdFlag));
		params.addValue("channelFlag", getFlag(channels, campaignIdFlag));
		params.addValue("statusFlag", statusFlag);
		params.addValue("closedFlag", closedFlag);
		params.addValue("createdByFlag", getFlag(createdBy, campaignIdFlag));
		params.addValue("dateFlag", dateFlag);
		params.addValue("campaignTypes",
				UtilValidate.isEmpty(searchCriteria.getCampaignType())
						? Arrays.asList(PromotionConstants.FIXED_SP_CAMPAIGN,
								PromotionConstants.BUY_X_PAY_FOR_Y_CAMPAIGN, 
								PromotionConstants.MI_BUNDLE_CAMPAIGN)
						: Arrays.asList(searchCriteria.getCampaignType()));
		return posReadonlyNPJdbcTemplate.query(GET_CAMPAIGNS_LIST, params, 
				(ResultSet rs) -> {
					List<MultiItemCampaign> campaignList = new ArrayList<>();
					Map<String, Object> result = new HashMap<>();
					int totalRowCount = 0;
					while(rs.next()){
						totalRowCount= rs.getInt("TotalRowCount");
						MultiItemCampaign multiItemCampaign = new MultiItemCampaign();
						multiItemCampaign = new MultiItemCampaign();
						multiItemCampaign.setCampaignId(rs.getInt(MI_CAMPAIGNID));
						multiItemCampaign.setCampaignName(rs.getString("Name"));
						multiItemCampaign.setApplicableType(rs.getInt("ApplicableType"));
						List<Integer> channelsForId = Stream.of(rs.getString(CHANNEL_FIELD).split(","))
								  .map(String::trim)
								  .map(Integer::parseInt)
								  .collect(Collectors.toList());
						multiItemCampaign.setChannels(channelsForId);
						multiItemCampaign.setFromDate(rs.getObject("FromDate", LocalDateTime.class));
						multiItemCampaign.setToDate(rs.getObject("ToDate", LocalDateTime.class));
						multiItemCampaign.setEffectiveDate(rs.getObject("EffectiveDate", LocalDateTime.class));
						multiItemCampaign.setStatus(rs.getString("MI_Status"));
						String cloneRefId = rs.getString("MI_CloneRefID");
						if(UtilValidate.isNotEmpty(cloneRefId))
							multiItemCampaign.setCloneReferenceId(Integer.parseInt(cloneRefId));
						UserMetaData metaData = new UserMetaData();
						metaData.setCreatedBy(rs.getString("CreatedBy"));
						metaData.setDateCreated(rs.getObject("DateCreated", LocalDateTime.class));
						metaData.setApprovedBy(rs.getString("ApprovedBy"));
						metaData.setDateApproved(rs.getObject("DateApproved", LocalDateTime.class));
						metaData.setRemarks(rs.getString(REMARKS));
						multiItemCampaign.setUserMetaData(metaData);
						campaignList.add(multiItemCampaign);
					}
					result.put("data", campaignList);
					result.put("rowCount", totalRowCount);
					return result;
				});
	}
	
	private int getFlag(Object value, Integer campaignIdFlag) {
		return (campaignIdFlag==1 && value!=null) ? 0 : 1;
	}

	@Override
	public Set<Long> getCampaignCustomerIds(int campaignId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CAMPAIGN_ID, campaignId);
		List<Long> customerIds = posReadonlyNPJdbcTemplate.queryForList(GET_CUSTOMER_IDS_FOR_CAMPAIGN, params, Long.class);
		return new HashSet<>(customerIds);
	}

	@Override
	public Set<String> getCampaignItems(int campaignId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CAMPAIGN_ID, campaignId);
		List<String> items = posReadonlyNPJdbcTemplate.queryForList(GET_ITEM_IDS_FOR_CAMPAIGN, params, String.class);
		return new HashSet<>(items);
	}

	@Override
	public boolean approveCampaign(int campaignId, LocalDateTime toDate, String approvedBy, Map<String, Object> headerDetails, boolean isDateUpdated) {
		
		posNPJdbcTemplate.update(CAMPAIGN_HEADER_LOG_QUERY, headerDetails);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CAMPAIGN_ID, campaignId);
		params.addValue(TO_DATE, toDate);
		params.addValue(STATUS, PromotionConstants.ACTIVE);
		params.addValue(APPROVED_BY, approvedBy);
		params.addValue(MODIFIED_BY, approvedBy);
		params.addValue(REMARKS, null);
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime fromDate = CampaignUtil.getDateFromMap(headerDetails.get("FromDate"));
		LocalDateTime effectiveDate =  fromDate.isAfter(currentDate) ? fromDate : currentDate;
		params.addValue(EFFECTIVE_DATE, effectiveDate);
		params.addValue(DATE_APPROVED, currentDate);
		String query = isDateUpdated ? UPDATE_AND_APPROVE_CAMPAIGN : APPROVE_OR_REJECT_CAMPAIGN;
		
		int rows =posNPJdbcTemplate.update(query, params);
		return rows==1;
	}

	@Override
	public Map<String, Object> getCampaignHeaderDetails(int campaignId) {
		return posNPJdbcTemplate.queryForMap(GET_CAMPAIGN_HEADER, new MapSqlParameterSource(CAMPAIGN_ID, campaignId));
	}
	
	@Override
	public boolean isCampaignNameAvailable(String campaignName){
		MapSqlParameterSource params = new MapSqlParameterSource(CAMPAIGN_NAME, campaignName);
		Integer count = posReadonlyNPJdbcTemplate.queryForObject(CAMPAIGN_NAME_AVAILABILITY_QUERY, params, Integer.class);
		return count==0;
	}

	@Override
	public void rejectMultiItemCampaign(int campaignId, String rejectedBy, String remarks, Map<String, Object> headerDetails) {
		posNPJdbcTemplate.update(CAMPAIGN_HEADER_LOG_QUERY, headerDetails);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CAMPAIGN_ID, campaignId);
		params.addValue(STATUS, "R");
		params.addValue(APPROVED_BY, rejectedBy);
		params.addValue(REMARKS, remarks);
		params.addValue(EFFECTIVE_DATE, null);
		params.addValue(DATE_APPROVED, LocalDateTime.now());
		posNPJdbcTemplate.update(APPROVE_OR_REJECT_CAMPAIGN, params);
	}

	@Override
	public boolean updateActiveCampaignToDate(int campaignId, LocalDateTime toDate, 
			String modifiedBy, Map<String, Object> headerDetails) {
		posNPJdbcTemplate.update(CAMPAIGN_HEADER_LOG_QUERY, headerDetails);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CAMPAIGN_ID, campaignId);
		params.addValue(TO_DATE, toDate);
		params.addValue(MODIFIED_BY, modifiedBy);
		
		int rows =posNPJdbcTemplate.update(UPDATE_ACTIVE_CAMPAIGN, params);
		return rows==1;
	}

	@Override
	public void autoRejectClosedCampaigns() {
		LocalDateTime toDate = LocalDate.now().atStartOfDay();
		log.info("Running auto-reject cron at : {}", LocalDateTime.now());
		List<Map<String, Object>> headerDetails = posNPJdbcTemplate.queryForList(GET_CLOSED_CAMPAIGN_HEADER_QUERY, new MapSqlParameterSource(TO_DATE, toDate));
		log.info("All auto-reject campaigns : {}", headerDetails.stream().map(t -> t.get(MI_CAMPAIGNID)).collect(Collectors.toList()));
		posNPJdbcTemplate.batchUpdate(CAMPAIGN_HEADER_LOG_QUERY, headerDetails.toArray(new Map[headerDetails.size()]));
		headerDetails.stream().forEach(map -> {
			map.put(CAMPAIGN_ID, map.get(MI_CAMPAIGNID));
			map.put(APPROVED_BY, "Auto-Reject Cron");
			map.put(STATUS, PromotionConstants.REJECT);
			map.put(REMARKS, AUTO_REJECT_REMARKS);
			map.put(EFFECTIVE_DATE, null);
			map.put(DATE_APPROVED, LocalDateTime.now());
		});
		posNPJdbcTemplate.batchUpdate(APPROVE_OR_REJECT_CAMPAIGN, headerDetails.toArray(new Map[headerDetails.size()]));
	}

	@Override
	public List<String> getStores(Integer campaignId) {
		MapSqlParameterSource params = new MapSqlParameterSource(CAMPAIGN_ID,campaignId);
		return posReadonlyNPJdbcTemplate.queryForList(GET_STORES_QUERY, params, String.class);
	}

}
