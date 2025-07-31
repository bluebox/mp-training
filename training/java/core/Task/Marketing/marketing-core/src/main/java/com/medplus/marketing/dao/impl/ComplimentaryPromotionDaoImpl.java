package com.medplus.marketing.dao.impl;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.discounts.domain.ComplimentaryProduct;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.dao.ComplimentaryPromotionDao;
import com.medplus.marketing.dao.helper.ComplimentaryPromotionExtractor;
import com.medplus.marketing.dao.helper.PromotionDaoHelper;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.ComplimentaryPromotion;
import com.medplus.marketing.domain.ComplimentarySlab;
import com.medplus.marketing.util.CampaignUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ComplimentaryPromotionDaoImpl implements ComplimentaryPromotionDao {
	
	@Autowired
	@Qualifier("posReadonlyNPJdbcTemplate") 
	NamedParameterJdbcTemplate posReadonlyNPJdbcTemplate;

	@Autowired
	@Qualifier("posNPJdbcTemplate") 
	NamedParameterJdbcTemplate posNPJdbcTemplate;
	
	private static final String UNCHECKED = "unchecked";

    /*** Insert Queries */
	private static final String INSERT_COMPLIMENTARY_PROMO_QUERY = "INSERT INTO `tbl_complimentary_promotion` (`Name`, `ApplicableType`, `InvoiceValuePercentageForMessage`, `AllCustomers`, `ValidFrom`, `ValidTo`, `Status`, `CreatedBy`, `DateCreated`, `CloneReferenceId`, `ComplimentarySlabId`) VALUES (:Name, :ApplicableType, :InvoiceValuePercentageForMessage, :AllCustomers, :ValidFrom, :ValidTo, :Status, :CreatedBy, :DateCreated, :CloneReferenceId, :ComplimentarySlabId)";
	
	private static final String INSERT_SLAB_QUERY = "INSERT INTO `tbl_complimentary_slab` ( `Name`, `InvoiceAmount`, `Status`, `CreatedBy`, `DateCreated`) VALUES (:Name, :InvoiceAmount, :Status, :CreatedBy, :DateCreated)";

	private static final String INSERT_LOYALTY_QUERY = "INSERT INTO `tbl_complimentary_loyalty_mapping` (`ComplimentaryId`, `LoyaltyType`, `DateCreated`,`CreatedBy`) VALUES (:ComplimentaryId,:LoyaltyType, :DateCreated,:CreatedBy)";

	private static final String INSERT_CHANNEL_QUERY = "INSERT INTO `tbl_promotion_channel_mapping` (`PromotionId`,`ApplicableType`,`Channel`,`PromotionType`,`CreatedBy`) VALUES (:ComplimentaryId,:ApplicableType,:Channel, 7 ,:CreatedBy)";
	
	private static final String INSERT_REGIONS_QUERY = "INSERT INTO `tbl_complimentary_regions_Mapping` (`ComplimentaryId`, `ComplimentarySlabId`, `Region`, `Status`, `CreatedBy`, `DateCreated`) VALUES (:ComplimentaryId, :ComplimentarySlabId, :Region, :Status, :CreatedBy, :DateCreated)";

	private static final String INSERT_PRODUCTS_QUERY = "INSERT INTO `tbl_complimentary_products_Mapping` (`ComplimentaryId`, `ComplimentarySlabId`, `ProductId`, `Quantity`, `Discount`, `ProductAction`, `Status`, `CreatedBy`, `DateCreated`) VALUES (:ComplimentaryId, :ComplimentarySlabId, :ProductId, :Quantity, :Discount, :ProductAction, :Status, :CreatedBy, :DateCreated)";

	private static final String INSERT_REF_PRODUCTS_QUERY = "INSERT INTO tbl_complimentary_reference_products(ComplimentaryId,ProductId,Quantity,Status,CreatedBy,DateCreated) VALUES(:ComplimentaryId, :ProductId, :Quantity, :Status, :CreatedBy, :DateCreated)";

    private static final String INSERT_REF_COMPOSITIONS_QUERY = "INSERT INTO tbl_complimentary_reference_composition(ComplimentaryId,CompositionId,Quantity,Status,CreatedBy,DateCreated) VALUES(:ComplimentaryId, :CompositionId, :Quantity, :Status, :CreatedBy, :DateCreated)";

    private static final String INSERT_CUSTOMERS_QUERY = "INSERT INTO `tbl_complimentary_customer`(`ComplimentaryId`,`CustomerId`, `CreatedBy`,`DateCreated`) VALUES(:ComplimentaryId, :CustomerId, :CreatedBy, :DateCreated)";

    /*** Update Queries */
    private static final String UPDATE_COMPLIMENTARY_PROMO_QUERY = "update `tbl_complimentary_promotion`  SET  `InvoiceValuePercentageForMessage`=:InvoiceValuePercentageForMessage,`AllCustomers`=:AllCustomers,`ValidTo`= :ValidTo ,`ModifiedBy` = :ModifiedBy ,`DateModified` = :DateModified where `ComplimentaryId` = :ComplimentaryId and Status = 'I'" ;
	
    /*** GET Queries */
    private static final String GET_COMPLIMENTARY_HEADER = "SELECT `ComplimentaryId`, `Name`, `ApplicableType`, `InvoiceValuePercentageForMessage`, `AllCustomers`, `ValidFrom`, `ValidTo`, `EffectiveDate`, `Status`, `CreatedBy`, `DateCreated`, `CloneReferenceId`, `ComplimentarySlabId`, `ModifiedBy`, `DateModified`, `ApprovedBy`, `DateApproved`, `Remarks` from tbl_complimentary_promotion where ComplimentaryId= :ComplimentaryId";
        
	private static final String GET_LOYALTY_QUERY = "SELECT ComplimentaryId, LoyaltyType, DateCreated, CreatedBy  from tbl_complimentary_loyalty_mapping where ComplimentaryId=:ComplimentaryId";

	private static final String GET_CHANNEL_QUERY = "SELECT `PromotionId`,`ApplicableType`,`Channel`,`PromotionType`,`CreatedBy` from `tbl_promotion_channel_mapping` where PromotionId=:PromotionId and ApplicableType=:ApplicableType and PromotionType=7";

	private static final String GET_REGIONS_QUERY = "SELECT `ID`,`ComplimentaryId`, `ComplimentarySlabId`, `Region`, `Status`, `CreatedBy`, `DateCreated`, `ModifiedBy`, `DateModified` from `tbl_complimentary_regions_Mapping` where ComplimentaryId = :ComplimentaryId AND ComplimentarySlabId = :ComplimentarySlabId";

	private static final String GET_PRODUTS_QUERY = "SELECT ID, ComplimentaryId,ComplimentarySlabId,ProductId,Quantity,Discount,ProductAction,Status,CreatedBy,DateCreated,ModifiedBy,DateModified  FROM tbl_complimentary_products_Mapping WHERE ComplimentaryId=:ComplimentaryId and ComplimentarySlabId=:ComplimentarySlabId and Status='A'";

	private static final String GET_REFERENCE_PRODUTS_QUERY = "SELECT ID,ComplimentaryId,ProductId,Quantity,Status,CreatedBy,DateCreated,ModifiedBy,DateModified  FROM tbl_complimentary_reference_products WHERE ComplimentaryId= :ComplimentaryId and  Status='A'";

	private static final String GET_REFERENCE_COMPOSITIONS_QUERY = "SELECT ID, ComplimentaryId, CompositionId, Quantity, Status, CreatedBy, DateCreated, ModifiedBy, DateModified  FROM tbl_complimentary_reference_composition WHERE ComplimentaryId= :ComplimentaryId and  Status='A'";

    private static final String GET_CUSTOMERS_QUERY = "SELECT `ComplimentaryId`,`CustomerId`, `CreatedBy`,`DateCreated`from `tbl_complimentary_customer` where ComplimentaryId = :ComplimentaryId ";

    private static final String GET_CLOSED_COMPLIMENTARY_PROMOTIONS = "SELECT `ComplimentaryId`, `Name`, `ApplicableType`, `InvoiceValuePercentageForMessage`, `AllCustomers`, `ValidFrom`, `ValidTo`, `EffectiveDate`, `Status`, `CreatedBy`, `DateCreated`, `CloneReferenceId`, `ComplimentarySlabId`, `ModifiedBy`, `DateModified`, `ApprovedBy`, `DateApproved`, `Remarks` from tbl_complimentary_promotion  where ValidTo < :ValidTo and Status = 'I' ";
    
	private static final String GET_COMPIMENTARY_PRODUTS ="SELECT ProductId ,Quantity  ,Discount ,ProductAction FROM tbl_complimentary_products_Mapping WHERE ComplimentaryId= :ComplimentaryId and  ComplimentarySlabId = :ComplimentarySlabId and Status = 'A'";

	private static final String GET_COMPLIMENTARY_LIST = "SELECT count(a.ComplimentaryId) over() as TotalRowCount, a.ComplimentaryId, a.Name, a.ApplicableType, GROUP_CONCAT(b.Channel) as Channel , a.ValidFrom, a.ValidTo, a.EffectiveDate, a.Status, a.CloneReferenceId, a.CreatedBy, a.DateCreated, a.ApprovedBy, a.DateApproved FROM tbl_complimentary_promotion a "
			+ "inner join tbl_promotion_channel_mapping b on a.ComplimentaryId = b.PromotionId and a.ApplicableType=b.ApplicableType "
			+ "where (1=:complimentaryIdFlag or a.ComplimentaryId=:ComplimentaryId) and (1=:nameFlag or a.Name like :Name) and a.ApplicableType in (:ApplicableType) "
			+ "and (1=:channelFlag or b.Channel in (:Channel)) and (1=:statusFlag or a.Status=:Status) and (1=:createdByFlag or a.CreatedBy=:CreatedBy) "
			+ "and (1=:dateFlag or a.DateCreated between :ValidFrom and :ValidTo) and (1=:closedFlag or a.ValidTo < now()) and b.PromotionType = 7 "
			+ "group by ComplimentaryId order by ComplimentaryId desc limit :limit offset :offset ";
	
	private static final String GET_COMPLIMENTARY_DETAILS_BY_ID = "SELECT a.ComplimentaryId, a.Name, a.ApplicableType, a.AllCustomers, a.ValidFrom, a.EffectiveDate, a.ValidTo, a.InvoiceValuePercentageForMessage, "
			+ "a.Status, a.CloneReferenceId, a.CreatedBy, a.DateCreated, a.ModifiedBy, a.DateModified,  a.ApprovedBy, a.DateApproved, a.Remarks, "
			+ "c.ComplimentarySlabId, c.Name as SlabName, c.InvoiceAmount, d.LoyaltyType as LoyaltyType, e.Region as Region,f.Channel as Channel "
			+ "from tbl_complimentary_promotion a "
			+ "inner join tbl_complimentary_slab c on a.ComplimentarySlabId = c.ComplimentarySlabId "
			+ "inner join tbl_complimentary_loyalty_mapping d on a.ComplimentaryId = d.ComplimentaryId "
			+ "inner join tbl_complimentary_regions_Mapping e on a.ComplimentaryId = e.ComplimentaryId "
			+ "inner join tbl_promotion_channel_mapping f on a.ComplimentaryId = f.PromotionId and a.ApplicableType = f.ApplicableType "
			+ "where a.ComplimentaryId= :ComplimentaryId and c.Status = 'A' "
			+ "and e.Status ='A' and f.PromotionType = 7";
		
	private static final String GET_COMPLIMENTARY_REF_PRODUCTS_QRY = "SELECT ProductId FROM tbl_complimentary_reference_products WHERE ComplimentaryId= :ComplimentaryId and Status='A'";

	private static final String GET_COMPLIMENTARY_REF_COMPOSITIONS_QRY = "SELECT CompositionId FROM tbl_complimentary_reference_composition WHERE ComplimentaryId= :ComplimentaryId and Status='A'";

	private static final String GET_COMPLIMENTARY_CUSTOMERS_QRY = "SELECT CustomerId FROM tbl_complimentary_customer WHERE ComplimentaryId= :ComplimentaryId";
	
	private static final String GET_STORES_QUERY = "SELECT Region from `tbl_complimentary_regions_Mapping` WHERE ComplimentaryId= :ComplimentaryId and LENGTH(`Region`) = 12 and Status='A'";
	
	/*** Approve Queries */
	private static final String UPDATE_AND_APPROVE_COMPLIMENTARY = "UPDATE `tbl_complimentary_promotion` SET `ValidTo` = :ValidTo, `Status` = :Status, "
			+ "`ApprovedBy` = :ApprovedBy, `DateApproved` = :DateApproved, `EffectiveDate` = :EffectiveDate, `ModifiedBy` = :ModifiedBy, `DateModified` = now() WHERE (`ComplimentaryId` = :ComplimentaryId)";

	private static final String APPROVE_OR_REJECT_COMPLIMENTARY = "UPDATE `tbl_complimentary_promotion` SET `Status` = :Status, "
			+ "`ApprovedBy` = :ApprovedBy, `DateApproved` = :DateApproved, `EffectiveDate` = :EffectiveDate, `Remarks` = :Remarks WHERE (`ComplimentaryId` = :ComplimentaryId)";

	private static final String UPDATE_ACTIVE_COMPLIMENTARY_PROMO = "UPDATE `tbl_complimentary_promotion` SET `ValidTo` = :ValidTo, "
			+ "`ModifiedBy` = :ModifiedBy, `DateModified` = now() WHERE (`ComplimentaryId` = :ComplimentaryId)";
   
	/*** Delete Queries */
	private static final String DELETE_CHANNEL_QUERY = "DELETE FROM `tbl_promotion_channel_mapping` WHERE (`PromotionId` = :PromotionId) and (`ApplicableType` = :ApplicableType) and (`Channel` in (:Channel)) and (`PromotionType` = 7)";

	private static final String DELETE_REGION_QUERY = "DELETE FROM `tbl_complimentary_regions_Mapping` WHERE Region IN (:Region) and ComplimentaryId = :ComplimentaryId and ComplimentarySlabId = :ComplimentarySlabId";

	private static final String DELETE_LOYALTY_QUERY = "DELETE from tbl_complimentary_loyalty_mapping where LoyaltyType IN (:LoyaltyType) and ComplimentaryId = :ComplimentaryId";
    
	private static final String DELETE_CUSTOMER_QUERY = "DELETE from tbl_complimentary_customer where ComplimentaryId = :ComplimentaryId and CustomerId = :CustomerId ";

	private static final String DELETE_PRODUCTS = "DELETE FROM tbl_complimentary_products_Mapping WHERE  ComplimentaryId=:ComplimentaryId AND ComplimentarySlabId = :ComplimentarySlabId AND ProductId in (:ProductId)";

	private static final String DELETE_REFERENCE_PRODUCTS = "DELETE FROM tbl_complimentary_reference_products WHERE  ComplimentaryId=:ComplimentaryId AND ProductId in (:ProductId)";
	
	private static final String DELETE_REFERENCE_COMPOSITION = "DELETE FROM tbl_complimentary_reference_composition WHERE ComplimentaryId=:ComplimentaryId AND CompositionId in (:CompositionId)";

	/*** Insert Queries for Log table */
    private static final String INSERT_COMPLIMENTARY_PROMO_QUERY_LOG = "INSERT INTO `tbl_complimentary_promotion_log` (`ComplimentaryId`, `Name`, `ApplicableType`, `InvoiceValuePercentageForMessage`, `AllCustomers`, `ValidFrom`, `ValidTo`, `EffectiveDate`, `Status`, `CreatedBy`, `DateCreated`, `CloneReferenceId`, `ComplimentarySlabId`, `ModifiedBy`, `DateModified`, `ApprovedBy`, `DateApproved`, `Remarks` ) VALUES (:ComplimentaryId, :Name, :ApplicableType, :InvoiceValuePercentageForMessage, :AllCustomers, :ValidFrom, :ValidTo, :EffectiveDate, :Status, :CreatedBy, :DateCreated, :CloneReferenceId, :ComplimentarySlabId,"
    		+ " :ModifiedBy, :DateModified, :ApprovedBy, :DateApproved, :Remarks )";

	private static final String INSERT_LOYALTY_QUERY_LOG = "INSERT INTO tbl_complimentary_loyalty_mapping_log (ComplimentaryId, LoyaltyType, DateCreated,CreatedBy) VALUES (:ComplimentaryId,:LoyaltyType, :DateCreated,:CreatedBy)";

	private static final String INSERT_CHANNEL_QUERY_LOG = "INSERT INTO `tbl_promotion_channel_mapping_log` (`PromotionId`,`ApplicableType`,`Channel`,`PromotionType`,`CreatedBy`) VALUES (:PromotionId,:ApplicableType,:Channel,:PromotionType,:CreatedBy)";

	private static final String INSERT_REGIONS_QUERY_LOG = "INSERT INTO `tbl_complimentary_regions_Mapping_log` (`ID`,`ComplimentaryId`, `ComplimentarySlabId`, `Region`, `Status`, `CreatedBy`, `DateCreated`, `ModifiedBy`, `DateModified`) VALUES (:ID, :ComplimentaryId, :ComplimentarySlabId, :Region, :Status, :CreatedBy, :DateCreated, :ModifiedBy, :DateModified)";

	private static final String INSERT_PRODUCTS_QUERY_LOG = "INSERT INTO `tbl_complimentary_products_Mapping_log` (`ID`, `ComplimentaryId`, `ComplimentarySlabId`, `ProductId`, `Quantity`, `Discount`, `ProductAction`, `Status`, `CreatedBy`, `DateCreated`, `ModifiedBy`, `DateModified`) VALUES (:ID, :ComplimentaryId, :ComplimentarySlabId, :ProductId, :Quantity, :Discount, :ProductAction, :Status, :CreatedBy, :DateCreated, :ModifiedBy, :DateModified)";

	private static final String INSERT_REF_PRODUCTS_QUERY_LOG = "INSERT INTO tbl_complimentary_reference_products_log (ID,ComplimentaryId,ProductId,Quantity,Status,CreatedBy,DateCreated,ModifiedBy,DateModified) VALUES(:ID,:ComplimentaryId, :ProductId, :Quantity, :Status, :CreatedBy, :DateCreated, :ModifiedBy, :DateModified)";

    private static final String INSERT_REF_COMPOSITIONS_QUERY_LOG = "INSERT INTO tbl_complimentary_reference_composition_log (ID,ComplimentaryId,CompositionId,Quantity,Status,CreatedBy,DateCreated,ModifiedBy,DateModified) VALUES(:ID, :ComplimentaryId, :CompositionId, :Quantity, :Status, :CreatedBy, :DateCreated, :ModifiedBy, :DateModified)";

    private static final String INSERT_CUSTOMERS_QUERY_LOG = "INSERT INTO `tbl_complimentary_customer_log`(`ComplimentaryId`,`CustomerId`, `CreatedBy`,`DateCreated`) VALUES(:ComplimentaryId, :CustomerId, :CreatedBy, :DateCreated)";
    
    private static final String IS_COMPLIMENTARY_PROMO_NAME_AVAILABLE = "SELECT COUNT(ComplimentaryId) FROM tbl_complimentary_promotion WHERE Name=:PromotionName";

    private static final String IS_COMPLIMENTARY_SLAB_NAME_AVAILABLE = "SELECT COUNT(ComplimentarySlabId) FROM tbl_complimentary_slab WHERE Name=:slabName";

    private static final String GET_EXISTING_SLAB_DETAILS = "select ComplimentarySlabId, Name, InvoiceAmount from tbl_complimentary_slab order by Name";
    
	private static final String COMPLIMENTARY_ID = "ComplimentaryId";
	private static final String NAME = "Name";
	private static final String APPLICABLE_TYPE = "ApplicableType";
	private static final String COMPLIMENTARY_SLAB_ID = "ComplimentarySlabId";
	private static final String DATE_CREATED = "DateCreated";
	private static final String CREATED_BY = "CreatedBy";
	private static final String DATE_MODIFIED= "DateModified";
	private static final String MODIFIED_BY = "ModifiedBy";
	private static final String VALID_FROM = "ValidFrom";
	private static final String VALID_TO = "ValidTo";
	private static final String LOYALTY_TYPE = "LoyaltyType";
	private static final String CLONE_REFERENCE_ID = "CloneReferenceId";
	private static final String ALL_CUSTOMERS = "AllCustomers";
	private static final String QUANTITY = "Quantity";
	private static final String STATUS = "Status";
	private static final String REMARKS = "Remarks";
	private static final String EFFECTIVE_DATE = "EffectiveDate";
	private static final String REGION = "Region";
	private static final String CHANNEL = "Channel";
	private static final String INVOICE_VALUE_PERCENTAGE_FOR_MESSAGE = "InvoiceValuePercentageForMessage";
	private static final String DATE_APPROVED = "DateApproved";
	private static final String APPROVED_BY = "ApprovedBy";
	private static final String PRODUCT_ACTION = "ProductAction";
	private static final String DISCOUNT = "Discount";
	private static final String PRODUCT_ID = "ProductId";
	private static final String COMPOSITION_ID = "CompositionId";
	private static final String CUSTOMER_ID = "CustomerId";
	private static final String ID = "ID";
	private static final String PROMOTION_ID = "PromotionId";
	private static final String INVOICE_AMOUNT = "InvoiceAmount";

	@Override
	public ComplimentaryPromotion createComplimentaryPromotion(ComplimentaryPromotion complimentaryPromotion) {
		String createdBy=complimentaryPromotion.getUserMetaData().getCreatedBy();	
		LocalDateTime dateCreated = complimentaryPromotion.getUserMetaData().getDateCreated();
		
		if (UtilValidate.isEmpty(complimentaryPromotion.getComplimentarySlab().getComplimentarySlabId())) {
			long complimentarySlabId = insertSlab(complimentaryPromotion.getComplimentarySlab(), createdBy, dateCreated);
			complimentaryPromotion.getComplimentarySlab().setComplimentarySlabId(complimentarySlabId);
			log.info("New slab created with complimentarySlabId: {}",complimentarySlabId);
		}				
		
		long complimentaryId = insertPromotionInfo(complimentaryPromotion);
		complimentaryPromotion.setComplimentaryId(complimentaryId);
		insertChannels(complimentaryId, complimentaryPromotion.getChannels(), complimentaryPromotion.getApplicableType(), createdBy);
		insertLoyalties(complimentaryId, complimentaryPromotion.getLoyalties(), dateCreated, createdBy);
		insertRegions(complimentaryId, complimentaryPromotion.getComplimentarySlab().getComplimentarySlabId(), complimentaryPromotion.getRegions(), createdBy, dateCreated);
		insertProducts(complimentaryPromotion.getComplimentaryProducts(),complimentaryId ,complimentaryPromotion.getComplimentarySlab().getComplimentarySlabId(), createdBy, dateCreated);
		if (UtilValidate.isNotEmpty(complimentaryPromotion.getReferenceProductIds())) 
			insertReferenceProducts(complimentaryPromotion.getReferenceProductIds(), complimentaryId, createdBy, dateCreated);
		if (UtilValidate.isNotEmpty(complimentaryPromotion.getCompositionIds()))
			insertReferenceCompositions(complimentaryPromotion.getCompositionIds(), complimentaryId, createdBy, dateCreated);
		if(!complimentaryPromotion.isAllCustomers()) 
			insertCustomers(complimentaryId, complimentaryPromotion.getCustomerIds(), createdBy, dateCreated);
		return complimentaryPromotion;
	
	}
	
	private void insertChannels(long complimentaryId, List<Integer> channels, int applicableType, String createdBy) {
	    List<MapSqlParameterSource> paramsList = new ArrayList<>();
	    channels.forEach(channel -> {
	        MapSqlParameterSource params = new MapSqlParameterSource();
	        params.addValue(COMPLIMENTARY_ID, complimentaryId);
	        params.addValue(APPLICABLE_TYPE, applicableType);
	        params.addValue(CHANNEL, channel);
	        params.addValue(CREATED_BY, createdBy);
	        paramsList.add(params);
	    });
	   posNPJdbcTemplate.batchUpdate(INSERT_CHANNEL_QUERY, paramsList.toArray(new MapSqlParameterSource[0]));
	}
	
	private void insertLoyalties(long complimentaryId, List<Integer> loyalty, LocalDateTime dateCreated, String createdBy) {
	    List<MapSqlParameterSource> paramsList = new ArrayList<>();
	    loyalty.forEach(loyaltyType -> {
	        MapSqlParameterSource params = new MapSqlParameterSource();
	        params.addValue(COMPLIMENTARY_ID, complimentaryId);
	        params.addValue(LOYALTY_TYPE, loyaltyType);
	        params.addValue(DATE_CREATED, dateCreated);
	        params.addValue(CREATED_BY, createdBy);
	        paramsList.add(params);
	    });
	    posNPJdbcTemplate.batchUpdate(INSERT_LOYALTY_QUERY, paramsList.toArray(new MapSqlParameterSource[0]));
	}
	
	private long insertPromotionInfo(ComplimentaryPromotion complimentaryPromotion) {
		try{
			KeyHolder generatedKeyHolder = new GeneratedKeyHolder();			
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(NAME, complimentaryPromotion.getName());
			params.addValue(APPLICABLE_TYPE, complimentaryPromotion.getApplicableType());
			params.addValue(INVOICE_VALUE_PERCENTAGE_FOR_MESSAGE, complimentaryPromotion.getMessageDisplayPercentage());
			params.addValue(ALL_CUSTOMERS, complimentaryPromotion.isAllCustomers() ? 1 : 0); 
			params.addValue(VALID_FROM, complimentaryPromotion.getFromDate());
			params.addValue(VALID_TO, complimentaryPromotion.getToDate());
			params.addValue(STATUS, complimentaryPromotion.getStatus());
			params.addValue(CREATED_BY, complimentaryPromotion.getUserMetaData().getCreatedBy());
			params.addValue(DATE_CREATED, complimentaryPromotion.getUserMetaData().getDateCreated());
			params.addValue(CLONE_REFERENCE_ID, complimentaryPromotion.getCloneReferenceId());
			params.addValue(COMPLIMENTARY_SLAB_ID, complimentaryPromotion.getComplimentarySlab().getComplimentarySlabId());

			posNPJdbcTemplate.update(INSERT_COMPLIMENTARY_PROMO_QUERY, params, generatedKeyHolder);
			if(generatedKeyHolder.getKey() == null) {
				log.error("Error creating the complimentary promotion {}",complimentaryPromotion.getName());
				throw new PromotionException("Unable to create complimentary promotion");
			} else {
				return generatedKeyHolder.getKey().longValue();	
			}
		}catch(DuplicateKeyException e){
			throw new PromotionException("Complimentary promotion name already exists! Please change name.");
		}
	}

	private Long insertSlab(ComplimentarySlab complimentarySlab, String createdBy, LocalDateTime dateCreated) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(NAME, complimentarySlab.getName());
		params.addValue(INVOICE_AMOUNT, complimentarySlab.getInvoiceAmount());
		params.addValue(STATUS, PromotionConstants.ACTIVE);
		params.addValue(CREATED_BY, createdBy);
		params.addValue(DATE_CREATED, dateCreated);

	    try {
	        KeyHolder keyHolder = new GeneratedKeyHolder();
	        int recordInsertCount = posNPJdbcTemplate.update(INSERT_SLAB_QUERY, params, keyHolder);
	        if (recordInsertCount > 0) {
	            return keyHolder.getKey().longValue();
	        } else {
	            log.error("Error creating the complimentary slab with name {}", complimentarySlab.getName());
	            throw new PromotionException("Unable to create complimentary slab");
	        }
	    } catch (DuplicateKeyException e) {
	        throw new PromotionException("Complimentary slab name already exists! Please change name");
	    }
	}

	private void insertRegions(long complimentaryId, Long complimentarySlabId, List<String> regions, String createdBy, LocalDateTime dateCreated) {
	
	    List<MapSqlParameterSource> paramsList = new ArrayList<>();
		regions.forEach(region -> {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(COMPLIMENTARY_ID, complimentaryId);
			params.addValue(COMPLIMENTARY_SLAB_ID, complimentarySlabId);
			params.addValue(REGION, region.toUpperCase());
			params.addValue(STATUS, PromotionConstants.ACTIVE);
			params.addValue(CREATED_BY, createdBy);
			params.addValue(DATE_CREATED, dateCreated);
			paramsList.add(params);
		});

	    posNPJdbcTemplate.batchUpdate(INSERT_REGIONS_QUERY, paramsList.toArray(new MapSqlParameterSource[0]));
	}

	private void insertProducts(Set<ComplimentaryProduct> complimentaryProducts, Long complimentaryId, Long complimentarySlabId, String createdBy, LocalDateTime dateCreated) {
	    List<MapSqlParameterSource> paramsList = new ArrayList<>();
	    complimentaryProducts.forEach(product -> {
	        MapSqlParameterSource params = new MapSqlParameterSource();
	        params.addValue(COMPLIMENTARY_ID, complimentaryId);
	        params.addValue(COMPLIMENTARY_SLAB_ID, complimentarySlabId);
	        params.addValue(PRODUCT_ID, product.getProductId());
	        params.addValue(QUANTITY, product.getQuantity());
	        params.addValue(DISCOUNT, product.getDiscount());
	        params.addValue(PRODUCT_ACTION, product.isAutoAdd() ? "A" : "R");
	        params.addValue(STATUS, PromotionConstants.ACTIVE);
	        params.addValue(CREATED_BY, createdBy);
	        params.addValue(DATE_CREATED, dateCreated);
	        paramsList.add(params);
	    });
	    posNPJdbcTemplate.batchUpdate(INSERT_PRODUCTS_QUERY, paramsList.toArray(new MapSqlParameterSource[0]));
	}
	
	private void insertReferenceProducts(Set<String> referenceProductIds, Long complimentaryId, String createdBy, LocalDateTime dateCreated) {
	    List<MapSqlParameterSource> paramsList = new ArrayList<>();
	    referenceProductIds.forEach(productId -> {
	    	MapSqlParameterSource params = new MapSqlParameterSource();
	        params.addValue(COMPLIMENTARY_ID, complimentaryId);
	        params.addValue(PRODUCT_ID, productId);
	        params.addValue(QUANTITY, 1);
	        params.addValue(STATUS, PromotionConstants.ACTIVE);
	        params.addValue(CREATED_BY, createdBy);
	        params.addValue(DATE_CREATED, dateCreated);
	        paramsList.add(params);
	    });
	    posNPJdbcTemplate.batchUpdate(INSERT_REF_PRODUCTS_QUERY, paramsList.toArray(new MapSqlParameterSource[0]));
	}

	private void insertReferenceCompositions(Set<Integer> compositionIds, long complimentaryId, String createdBy,LocalDateTime dateCreated) {
	    List<MapSqlParameterSource> paramsList = new ArrayList<>();
	    compositionIds.forEach(compositionId -> {
	        MapSqlParameterSource params = new MapSqlParameterSource();
	        params.addValue(COMPLIMENTARY_ID, complimentaryId);
	        params.addValue(COMPOSITION_ID, compositionId);
	        params.addValue(QUANTITY, 1);
	        params.addValue(STATUS, PromotionConstants.ACTIVE);
	        params.addValue(CREATED_BY, createdBy);
	        params.addValue(DATE_CREATED, dateCreated);
	        paramsList.add(params);
	    });
	    posNPJdbcTemplate.batchUpdate(INSERT_REF_COMPOSITIONS_QUERY, paramsList.toArray(new MapSqlParameterSource[0]));
	}
	
	private void insertCustomers(long complimentaryId, Set<Long> customerIds, String createdBy, LocalDateTime dateCreated) {		
	    List<MapSqlParameterSource> paramsList = new ArrayList<>();
		customerIds.forEach(customerId -> {
			MapSqlParameterSource params = new MapSqlParameterSource();
	        params.addValue(COMPLIMENTARY_ID, complimentaryId);
	        params.addValue(CUSTOMER_ID, customerId);
	        params.addValue(STATUS, PromotionConstants.ACTIVE);
	        params.addValue(CREATED_BY, createdBy);
	        params.addValue(DATE_CREATED, dateCreated);
	        paramsList.add(params);
		});
        posNPJdbcTemplate.batchUpdate(INSERT_CUSTOMERS_QUERY, paramsList.toArray(new MapSqlParameterSource[0]));
	}
	
	@Override
	public Map<String, Object> getComplimentaryPromotionHeaderDetails(long complimentaryId) {
		return posNPJdbcTemplate.queryForMap(GET_COMPLIMENTARY_HEADER, new MapSqlParameterSource(COMPLIMENTARY_ID, complimentaryId));
	}
	
	private int getFlag(Object value, Integer complimentaryPromotionIdFlag) {
		return (complimentaryPromotionIdFlag==1 && value!=null) ? 0 : 1;
	}
	
	@Override
	public Map<String, Object> getComplimentaryPromotionList(CampaignSearchCriteria searchCriteria) {
		Integer complimentaryPromotionId = searchCriteria.getCampaignId();
		int complimentaryPromotionIdFlag= complimentaryPromotionId!=null ? 0 : 1;
		String name = searchCriteria.getCampaignName();
		if(UtilValidate.isNotEmpty(name)) 
			name="%"+name+"%";
		List<Integer> channels = searchCriteria.getChannels();
		List<Integer> applicableTypes = searchCriteria.getApplicableTypes();
		String status = searchCriteria.getStatus();
		int closedFlag=1;
		int statusFlag=1;
		if("C".equals(status)&&complimentaryPromotionIdFlag==1) {
			closedFlag=0;
			statusFlag=0;
			status="A";
		}else {
			statusFlag=getFlag(status,complimentaryPromotionIdFlag);
		}
		String createdBy = searchCriteria.getCreatedBy();
		LocalDateTime fromDate = searchCriteria.getFromDate();
		LocalDateTime toDate = searchCriteria.getToDate();
		int dateFlag = getFlag(fromDate, complimentaryPromotionIdFlag)==0 && getFlag(toDate, complimentaryPromotionIdFlag)==0 ? 0 : 1;
		if(dateFlag==0) {
			toDate=toDate.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
			fromDate=fromDate.withHour(0).withMinute(0).withSecond(0).withNano(0);
		}
		
		int offset = searchCriteria.getOffset();
		int limit = searchCriteria.getLimit();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(COMPLIMENTARY_ID, complimentaryPromotionId);
		params.addValue(NAME, name);
		params.addValue(APPLICABLE_TYPE, applicableTypes);
		params.addValue(CHANNEL, channels);
		params.addValue(STATUS, status);
		params.addValue(CREATED_BY, createdBy);
		params.addValue(VALID_FROM, fromDate);
		params.addValue(VALID_TO, toDate);
		params.addValue("offset", offset);
		params.addValue("limit", limit);
		params.addValue("complimentaryIdFlag", complimentaryPromotionIdFlag);
		params.addValue("nameFlag", getFlag(name, complimentaryPromotionIdFlag));
		params.addValue("channelFlag", getFlag(channels, complimentaryPromotionIdFlag));
		params.addValue("statusFlag", statusFlag);
		params.addValue("closedFlag", closedFlag);
		params.addValue("createdByFlag", getFlag(createdBy, complimentaryPromotionIdFlag));
		params.addValue("dateFlag", dateFlag);

		return posReadonlyNPJdbcTemplate.query(GET_COMPLIMENTARY_LIST, params, 
				(ResultSet rs) -> {
					List<ComplimentaryPromotion> complimentaryPromotionList = new ArrayList<>();
					Map<String, Object> result = new HashMap<>();
					int totalRowCount = 0;
					while(rs.next()){
						totalRowCount= rs.getInt("TotalRowCount");
						ComplimentaryPromotion complimentaryPromotion = new ComplimentaryPromotion();
						complimentaryPromotion.setComplimentaryId(rs.getInt(COMPLIMENTARY_ID));
						complimentaryPromotion.setName(rs.getString(NAME));
						complimentaryPromotion.setApplicableType(rs.getInt(APPLICABLE_TYPE));
						List<Integer> channelsForId = Stream.of(rs.getString(CHANNEL).split(","))
								  .map(String::trim)
								  .map(Integer::parseInt)
								  .collect(Collectors.toList());
						complimentaryPromotion.setChannels(channelsForId);
						complimentaryPromotion.setFromDate(rs.getObject(VALID_FROM, LocalDateTime.class));
						complimentaryPromotion.setToDate(rs.getObject(VALID_TO, LocalDateTime.class));
						complimentaryPromotion.setEffectiveDate(rs.getObject(EFFECTIVE_DATE, LocalDateTime.class));
						complimentaryPromotion.setStatus(rs.getString(STATUS));
						String cloneRefId = rs.getString(CLONE_REFERENCE_ID);
						if(UtilValidate.isNotEmpty(cloneRefId))
							complimentaryPromotion.setCloneReferenceId(Long.parseLong(cloneRefId));
						UserMetaData metaData = new UserMetaData();
						metaData.setCreatedBy(rs.getString(CREATED_BY));
						metaData.setDateCreated(rs.getObject(DATE_CREATED, LocalDateTime.class));
						metaData.setApprovedBy(rs.getString(APPROVED_BY));
						metaData.setDateApproved(rs.getObject(DATE_APPROVED, LocalDateTime.class));
						complimentaryPromotion.setUserMetaData(metaData);
						complimentaryPromotionList.add(complimentaryPromotion);
					}
					result.put("data", complimentaryPromotionList);
					result.put("rowCount", totalRowCount);
					return result;
				});
	}
	
	@Override
	public ComplimentaryPromotion getComplimentaryPromotionById(Long complimentaryId) {
		MapSqlParameterSource params = new MapSqlParameterSource(COMPLIMENTARY_ID, complimentaryId);
		return posReadonlyNPJdbcTemplate.query(GET_COMPLIMENTARY_DETAILS_BY_ID, params, new ComplimentaryPromotionExtractor());
	}
	
	@Override
	public boolean approveCampaign(Long complimentaryId, LocalDateTime toDate, String approvedBy, Map<String, Object> headerDetails, boolean isDateUpdated) {
		posNPJdbcTemplate.update(INSERT_COMPLIMENTARY_PROMO_QUERY_LOG, headerDetails);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(COMPLIMENTARY_ID, complimentaryId);
		params.addValue(VALID_TO, toDate);
		params.addValue(STATUS, PromotionConstants.ACTIVE);
		params.addValue(APPROVED_BY, approvedBy);
		params.addValue(MODIFIED_BY, approvedBy);
		params.addValue(REMARKS, null);
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime fromDate = CampaignUtil.getDateFromMap(headerDetails.get(VALID_FROM));
		LocalDateTime effectiveDate =  fromDate.isAfter(currentDate) ? fromDate : currentDate;
		params.addValue(EFFECTIVE_DATE, effectiveDate);
		params.addValue(DATE_APPROVED, currentDate);
		String query = isDateUpdated ? UPDATE_AND_APPROVE_COMPLIMENTARY : APPROVE_OR_REJECT_COMPLIMENTARY;
		return posNPJdbcTemplate.update(query, params) == 1;	
	}
	
	@Override
	public boolean isComplimentaryPromotionNameAvailable(String promotionName) {
		MapSqlParameterSource params = new MapSqlParameterSource("PromotionName", promotionName);
		Integer count = posReadonlyNPJdbcTemplate.queryForObject(IS_COMPLIMENTARY_PROMO_NAME_AVAILABLE, params, Integer.class);
		return (count!=null && count==0);
	}

	@Override
	public boolean updateComplimentaryToDate(Long complimentaryId, LocalDateTime toDate, String modifiedBy, Map<String, Object> headerDetails) {
		posNPJdbcTemplate.update(INSERT_COMPLIMENTARY_PROMO_QUERY_LOG, headerDetails);
		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue(COMPLIMENTARY_ID, complimentaryId)
			.addValue(VALID_TO, toDate)
			.addValue(MODIFIED_BY, modifiedBy);
		return posNPJdbcTemplate.update(UPDATE_ACTIVE_COMPLIMENTARY_PROMO, params) == 1;	
	}

	@Override
	public boolean rejectComplimentaryPromotion(Long complimentaryId, String rejectedBy, String remarks, Map<String, Object> headerDetails) {
		posNPJdbcTemplate.update(INSERT_COMPLIMENTARY_PROMO_QUERY_LOG, headerDetails);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(COMPLIMENTARY_ID, complimentaryId);
		params.addValue(STATUS, PromotionConstants.REJECT);
		params.addValue(APPROVED_BY, rejectedBy);
		params.addValue(REMARKS, remarks);
		params.addValue(EFFECTIVE_DATE, null);
		params.addValue(DATE_APPROVED, LocalDateTime.now());
		return posNPJdbcTemplate.update(APPROVE_OR_REJECT_COMPLIMENTARY, params) == 1;
	}

	@Override
	public Set<ComplimentaryProduct> getComplimentaryProducts(Long complimentaryId, Long complimentarySlabId) {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue(COMPLIMENTARY_ID, complimentaryId).addValue(COMPLIMENTARY_SLAB_ID, complimentarySlabId);
		return posReadonlyNPJdbcTemplate.query(GET_COMPIMENTARY_PRODUTS, params, (ResultSet rs) -> {
			Set<ComplimentaryProduct> complimentaryProducts = new HashSet<>();
			while (rs.next()) {
				ComplimentaryProduct complimentaryProduct = new ComplimentaryProduct();
				complimentaryProduct.setProductId(rs.getString(PRODUCT_ID));
				complimentaryProduct.setDiscount(rs.getDouble(DISCOUNT));
				complimentaryProduct.setQuantity(rs.getLong(QUANTITY));
				complimentaryProduct.setAutoAdd("A".equalsIgnoreCase(rs.getString(PRODUCT_ACTION)));
				complimentaryProducts.add(complimentaryProduct);
			}
			return complimentaryProducts;
		});
	}

	@Override
	public Set<String> getComplimentaryReferenceProducts(Long complimentaryId) {
		MapSqlParameterSource params = new MapSqlParameterSource(COMPLIMENTARY_ID,complimentaryId);
		List<String> refProducts = posReadonlyNPJdbcTemplate.queryForList(GET_COMPLIMENTARY_REF_PRODUCTS_QRY, params, String.class);
		return new HashSet<>(refProducts);
	}
	
	@Override
	public Set<Integer> getComplimentaryRefCompositons(Long complimentaryId) {
		MapSqlParameterSource params = new MapSqlParameterSource(COMPLIMENTARY_ID,complimentaryId);
		List<Integer> refCompositions = posReadonlyNPJdbcTemplate.queryForList(GET_COMPLIMENTARY_REF_COMPOSITIONS_QRY, params, Integer.class);
		return new HashSet<>(refCompositions);
	}
	
	@Override
	public Set<Long> getComplimentaryPromotionCustomers(Long complimentaryId) {
		MapSqlParameterSource params = new MapSqlParameterSource(COMPLIMENTARY_ID,complimentaryId);
		List<Long> customers = posReadonlyNPJdbcTemplate.queryForList(GET_COMPLIMENTARY_CUSTOMERS_QRY, params, Long.class);
		return new HashSet<>(customers);
	}

	@Override
	public void autoRejectClosedComplimentaryPromotions() {
		log.info("Running auto-reject cron for complimentary promotion at : {}", LocalDateTime.now());
		List<Map<String, Object>> closedPromoHeaderDetails = posNPJdbcTemplate.queryForList(GET_CLOSED_COMPLIMENTARY_PROMOTIONS, new MapSqlParameterSource(VALID_TO, LocalDate.now().atStartOfDay()));
		log.info("All auto-reject complimentary promotions : {}", closedPromoHeaderDetails.stream().map(t -> t.get(COMPLIMENTARY_ID)).collect(Collectors.toList()));
		posNPJdbcTemplate.batchUpdate(INSERT_COMPLIMENTARY_PROMO_QUERY_LOG, closedPromoHeaderDetails.toArray(new Map[closedPromoHeaderDetails.size()]));
		
		closedPromoHeaderDetails.stream().forEach(map -> {
			map.put(COMPLIMENTARY_ID, map.get(COMPLIMENTARY_ID));
			map.put(APPROVED_BY, "Auto-Reject Cron");
			map.put(STATUS, PromotionConstants.REJECT);
			map.put(REMARKS, "Auto-rejected, not approved till end date");
			map.put(EFFECTIVE_DATE, null);
			map.put(DATE_APPROVED, LocalDateTime.now());
		});
		posNPJdbcTemplate.batchUpdate(APPROVE_OR_REJECT_COMPLIMENTARY, closedPromoHeaderDetails.toArray(new Map[closedPromoHeaderDetails.size()]));
	}
	

	@Override
	public ComplimentaryPromotion updateComplimentaryPromotion(ComplimentaryPromotion complimentaryPromotion, Map<String, Object> headerDetails) {		
		posNPJdbcTemplate.update(INSERT_COMPLIMENTARY_PROMO_QUERY_LOG, headerDetails);
		MapSqlParameterSource params = new MapSqlParameterSource();
		long complimentaryPromotionId=complimentaryPromotion.getComplimentaryId();
		params.addValue(COMPLIMENTARY_ID, complimentaryPromotionId);
		params.addValue(INVOICE_VALUE_PERCENTAGE_FOR_MESSAGE, complimentaryPromotion.getMessageDisplayPercentage());
		params.addValue(ALL_CUSTOMERS, complimentaryPromotion.isAllCustomers() ? 1 : 0);
		params.addValue(VALID_TO, complimentaryPromotion.getToDate());
		params.addValue(MODIFIED_BY, complimentaryPromotion.getUserMetaData().getModifiedBy());
		params.addValue(DATE_MODIFIED, complimentaryPromotion.getUserMetaData().getDateModified());

		posNPJdbcTemplate.update(UPDATE_COMPLIMENTARY_PROMO_QUERY, params);
		updateRegions(complimentaryPromotion);
		if (UtilValidate.isNotEmpty(complimentaryPromotion.getComplimentaryProducts()) || UtilValidate.isNotEmpty(complimentaryPromotion.getRemoveComplimentaryProducts()))
			updateComplimentaryProducts(complimentaryPromotion);
		if (UtilValidate.isNotEmpty(complimentaryPromotion.getReferenceProductIds()) || UtilValidate.isNotEmpty(complimentaryPromotion.getRemoveReferenceProductIds()))
			updateReferenceProducts(complimentaryPromotion);
		if (UtilValidate.isNotEmpty(complimentaryPromotion.getCompositionIds()) || UtilValidate.isNotEmpty(complimentaryPromotion.getRemoveCompositionIds()))
			updateReferenceCompositions(complimentaryPromotion);
		updateLoyalities(complimentaryPromotion);
		updateChannels(complimentaryPromotion);
		boolean existingAllCustomers = (boolean)headerDetails.get(ALL_CUSTOMERS);
		if(complimentaryPromotion.isAllCustomers()) {
			if(!existingAllCustomers)
				deactivateCustomers(complimentaryPromotion);
		}else{
			if(UtilValidate.isNotEmpty(complimentaryPromotion.getCustomerIds()) || UtilValidate.isNotEmpty(complimentaryPromotion.getRemoveCustomerIds())) {
				updateCampaignCustomers(complimentaryPromotion, existingAllCustomers);
			}
		}
		return complimentaryPromotion;
	}
	
	@SuppressWarnings(UNCHECKED)
	private void updateRegions(ComplimentaryPromotion complimentaryPromotion) {
		List<String> updatedRegions = new ArrayList<>(complimentaryPromotion.getRegions());
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(COMPLIMENTARY_ID, complimentaryPromotion.getComplimentaryId());
		params.addValue(COMPLIMENTARY_SLAB_ID, complimentaryPromotion.getComplimentarySlab().getComplimentarySlabId());
		
		Map<String, Map<String, Object>> dbRegionsMap= posNPJdbcTemplate.query(GET_REGIONS_QUERY, params, (ResultSet rs) -> {
			Map<String, Map<String, Object>> result=new HashMap<>();
			while(rs.next()) {
				Map<String,Object> innerMap = new HashMap<>();
				innerMap.put(ID ,rs.getInt(ID));	
				innerMap.put(COMPLIMENTARY_ID ,rs.getInt(COMPLIMENTARY_ID));	
				innerMap.put(COMPLIMENTARY_SLAB_ID ,rs.getInt(COMPLIMENTARY_SLAB_ID));	
				innerMap.put(REGION ,rs.getString(REGION));	
				innerMap.put(STATUS ,rs.getString(STATUS));	
				innerMap.put(CREATED_BY ,rs.getString(CREATED_BY));	
				innerMap.put(DATE_CREATED ,rs.getObject(DATE_CREATED, LocalDateTime.class));
				innerMap.put(MODIFIED_BY ,rs.getString(MODIFIED_BY));	
				innerMap.put(DATE_MODIFIED ,rs.getObject(DATE_MODIFIED, LocalDateTime.class));
				result.put(rs.getString(REGION), innerMap);
			}
			return result;
		});
		List<String> toBeRemovedRegions = new ArrayList<>(dbRegionsMap.keySet());
		toBeRemovedRegions.removeAll(updatedRegions);
		updatedRegions.removeAll(dbRegionsMap.keySet());
		log.info("toBeRemoved regions: {}", toBeRemovedRegions);
		log.info("updated regions: {}", updatedRegions);
		insertRegions(complimentaryPromotion.getComplimentaryId(), complimentaryPromotion.getComplimentarySlab().getComplimentarySlabId(), updatedRegions, complimentaryPromotion.getUserMetaData().getModifiedBy(), LocalDateTime.now());

		if(UtilValidate.isNotEmpty(toBeRemovedRegions)) {
			List<Map<String, Object>> toBeRemoved=toBeRemovedRegions.stream().map(dbRegionsMap::get).collect(Collectors.toList());
			posNPJdbcTemplate.batchUpdate(INSERT_REGIONS_QUERY_LOG, toBeRemoved.toArray(new Map[toBeRemoved.size()]));
			params.addValue(REGION, toBeRemovedRegions);
			posNPJdbcTemplate.update(DELETE_REGION_QUERY, params);
		}
	}
	
	
	@SuppressWarnings(UNCHECKED)
	private void updateComplimentaryProducts(ComplimentaryPromotion complimentaryPromotion) {
		Set<ComplimentaryProduct> uploadedProducts= complimentaryPromotion.getComplimentaryProducts();
		Set<String> uploadedProductIds = uploadedProducts.stream().map(ComplimentaryProduct::getProductId).collect(Collectors.toSet()); 
		Set<String> removedProductIds = new HashSet<>(complimentaryPromotion.getRemoveComplimentaryProducts());
	   
		Set<String> commonProducts=new HashSet<>(uploadedProductIds.stream().filter(removedProductIds::contains).collect(Collectors.toSet()));
		uploadedProductIds.removeAll(commonProducts);
		removedProductIds.removeAll(commonProducts);
		
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue(COMPLIMENTARY_ID, complimentaryPromotion.getComplimentaryId());
		params.addValue(COMPLIMENTARY_SLAB_ID, complimentaryPromotion.getComplimentarySlab().getComplimentarySlabId());

		Map<String, Map<String, Object>> dbProductsMap= posNPJdbcTemplate.query(GET_PRODUTS_QUERY, params, (ResultSet rs) -> {
			Map<String, Map<String, Object>> result=new HashMap<>();
			while(rs.next()) {
				Map<String,Object> innerMap = new HashMap<>();
				innerMap.put(ID ,rs.getInt(ID));	
				innerMap.put(COMPLIMENTARY_ID ,rs.getInt(COMPLIMENTARY_ID));	
				innerMap.put(COMPLIMENTARY_SLAB_ID ,rs.getInt(COMPLIMENTARY_SLAB_ID));	
				innerMap.put(PRODUCT_ID ,rs.getString(PRODUCT_ID));	
				innerMap.put(QUANTITY ,rs.getInt(QUANTITY));	
				innerMap.put(DISCOUNT ,rs.getDouble(DISCOUNT));	
				innerMap.put(PRODUCT_ACTION ,rs.getString(PRODUCT_ACTION));	
				innerMap.put(STATUS ,rs.getObject(STATUS));	
				innerMap.put(CREATED_BY ,rs.getString(CREATED_BY));	
				innerMap.put(DATE_CREATED ,rs.getObject(DATE_CREATED, LocalDateTime.class));
				innerMap.put(MODIFIED_BY ,rs.getString(MODIFIED_BY));	
				innerMap.put(DATE_MODIFIED ,rs.getObject(DATE_MODIFIED, LocalDateTime.class));
				result.put(rs.getString(PRODUCT_ID), innerMap);
			}
			return result;
		});
		uploadedProductIds.removeAll(dbProductsMap.keySet());
		removedProductIds.retainAll(dbProductsMap.keySet());
		log.debug("Uploaded Complimentary Gift Products : {}", uploadedProductIds);
		log.debug("removed Complimentary Gift products : {}", removedProductIds);
		log.debug("removed Complimentary Gift products : {}", dbProductsMap);

		insertProducts(uploadedProducts.stream().filter(product -> uploadedProductIds.contains(product.getProductId())).collect(Collectors.toSet()),complimentaryPromotion.getComplimentaryId() ,complimentaryPromotion.getComplimentarySlab().getComplimentarySlabId(), complimentaryPromotion.getUserMetaData().getModifiedBy(), LocalDateTime.now());
		if(UtilValidate.isNotEmpty(removedProductIds)) {
			List<Map<String, Object>> toBeRemoved=removedProductIds.stream().map(dbProductsMap::get).collect(Collectors.toList());
			posNPJdbcTemplate.batchUpdate(INSERT_PRODUCTS_QUERY_LOG, toBeRemoved.toArray(new Map[toBeRemoved.size()]));
			params.addValue(PRODUCT_ID, removedProductIds);
			posNPJdbcTemplate.update(DELETE_PRODUCTS, params);
		}
	}

	@SuppressWarnings(UNCHECKED)
	private void updateReferenceProducts(ComplimentaryPromotion complimentaryPromotion) {
		Set<String> uploadedRefProducts = new HashSet<>(complimentaryPromotion.getReferenceProductIds());
		Set<String> removedRefProducts = new HashSet<>(complimentaryPromotion.getRemoveReferenceProductIds());
		
		Set<String> commonRefProducts=new HashSet<>(uploadedRefProducts.stream().filter(removedRefProducts::contains).collect(Collectors.toSet()));
		uploadedRefProducts.removeAll(commonRefProducts);
		removedRefProducts.removeAll(commonRefProducts);
		Map<String, Map<String, Object>> dbRefProductsMap= posNPJdbcTemplate.query(GET_REFERENCE_PRODUTS_QUERY, new MapSqlParameterSource(COMPLIMENTARY_ID, complimentaryPromotion.getComplimentaryId()), (ResultSet rs) -> {
			Map<String, Map<String, Object>> result=new HashMap<>();
			while(rs.next()) {
				Map<String,Object> innerMap = new HashMap<>();
				innerMap.put(ID ,rs.getInt(ID));	
				innerMap.put(COMPLIMENTARY_ID ,rs.getInt(COMPLIMENTARY_ID));	
				innerMap.put(PRODUCT_ID ,rs.getString(PRODUCT_ID));	
				innerMap.put(QUANTITY ,rs.getInt(QUANTITY));	
				innerMap.put(STATUS ,rs.getString(STATUS));	
				innerMap.put(CREATED_BY ,rs.getString(CREATED_BY));	
				innerMap.put(DATE_CREATED ,rs.getObject(DATE_CREATED, LocalDateTime.class));
				innerMap.put(MODIFIED_BY ,rs.getString(MODIFIED_BY));	
				innerMap.put(DATE_MODIFIED ,rs.getObject(DATE_MODIFIED, LocalDateTime.class));
				result.put(rs.getString(PRODUCT_ID), innerMap);
			}
			return result;
		});
		
		uploadedRefProducts.removeAll(dbRefProductsMap.keySet());
		removedRefProducts.retainAll(dbRefProductsMap.keySet());
		
		log.debug("Uploaded Reference Products : {}", uploadedRefProducts);
		log.debug("removed reference products : {}", removedRefProducts);
		
		insertReferenceProducts(uploadedRefProducts, complimentaryPromotion.getComplimentaryId(), complimentaryPromotion.getUserMetaData().getModifiedBy(), LocalDateTime.now());
		if(UtilValidate.isNotEmpty(removedRefProducts)) {
			List<Map<String, Object>> toBeRemoved=removedRefProducts.stream().map(dbRefProductsMap::get).collect(Collectors.toList());
			posNPJdbcTemplate.batchUpdate(INSERT_REF_PRODUCTS_QUERY_LOG, toBeRemoved.toArray(new Map[toBeRemoved.size()]));
			posNPJdbcTemplate.update(DELETE_REFERENCE_PRODUCTS, new MapSqlParameterSource()
					.addValue(COMPLIMENTARY_ID, complimentaryPromotion.getComplimentaryId())
					.addValue(PRODUCT_ID, removedRefProducts));
		}
	}
	
	@SuppressWarnings(UNCHECKED)
	private void updateReferenceCompositions(ComplimentaryPromotion complimentaryPromotion) {
		Set<Integer> uploadedRefCompositions = new HashSet<>(complimentaryPromotion.getCompositionIds());
		Set<Integer> removedRefCompostions = new HashSet<>(complimentaryPromotion.getRemoveCompositionIds());
		Set<Integer> commonRefCompostions = new HashSet<>(uploadedRefCompositions.stream().filter(removedRefCompostions::contains).collect(Collectors.toSet()));
		uploadedRefCompositions.removeAll(commonRefCompostions);
		removedRefCompostions.removeAll(commonRefCompostions);
		
		Map<Integer, Map<String, Object>> dbRefCompositionsMap= posNPJdbcTemplate.query(GET_REFERENCE_COMPOSITIONS_QUERY, new MapSqlParameterSource(COMPLIMENTARY_ID, complimentaryPromotion.getComplimentaryId()), (ResultSet rs) -> {
			Map<Integer, Map<String, Object>> result=new HashMap<>();
			while(rs.next()) {
				Map<String,Object> innerMap = new HashMap<>();
				innerMap.put(ID ,rs.getInt(ID));	
				innerMap.put(COMPLIMENTARY_ID ,rs.getInt(COMPLIMENTARY_ID));	
				innerMap.put(COMPOSITION_ID ,rs.getInt(COMPOSITION_ID));	
				innerMap.put(QUANTITY ,rs.getInt(QUANTITY));	
				innerMap.put(STATUS ,rs.getString(STATUS));	
				innerMap.put(CREATED_BY ,rs.getString(CREATED_BY));	
				innerMap.put(DATE_CREATED ,rs.getObject(DATE_CREATED, LocalDateTime.class));
				innerMap.put(MODIFIED_BY ,rs.getString(MODIFIED_BY));	
				innerMap.put(DATE_MODIFIED ,rs.getObject(DATE_MODIFIED, LocalDateTime.class));

				result.put(rs.getInt(COMPOSITION_ID), innerMap);
			}
			return result;
		});
		
		uploadedRefCompositions.removeAll(dbRefCompositionsMap.keySet());
		removedRefCompostions.retainAll(dbRefCompositionsMap.keySet());
		
		log.debug("uploaded reference composition ids : {}", uploadedRefCompositions);
		log.debug("removed reference composition ids : {}", removedRefCompostions);

		insertReferenceCompositions(uploadedRefCompositions, complimentaryPromotion.getComplimentaryId(), complimentaryPromotion.getUserMetaData().getModifiedBy(),LocalDateTime.now());
		if(UtilValidate.isNotEmpty(removedRefCompostions)) {
			List<Map<String, Object>> toBeRemoved=removedRefCompostions.stream().map(dbRefCompositionsMap::get).collect(Collectors.toList());
			posNPJdbcTemplate.batchUpdate(INSERT_REF_COMPOSITIONS_QUERY_LOG, toBeRemoved.toArray(new Map[toBeRemoved.size()]));
			posNPJdbcTemplate.update(DELETE_REFERENCE_COMPOSITION, new MapSqlParameterSource()
					.addValue(COMPLIMENTARY_ID, complimentaryPromotion.getComplimentaryId())
					.addValue(COMPOSITION_ID, removedRefCompostions));
		}
	}
	
	@SuppressWarnings(UNCHECKED)
	private void updateLoyalities(ComplimentaryPromotion complimentaryPromotion) {
		List<Integer> updatedLoyalties = new ArrayList<>(complimentaryPromotion.getLoyalties());
		Map<Integer, Map<String, Object>> dbLoyaltiesMap= posNPJdbcTemplate.query(GET_LOYALTY_QUERY, new MapSqlParameterSource(COMPLIMENTARY_ID, complimentaryPromotion.getComplimentaryId()), (ResultSet rs) -> {
			Map<Integer, Map<String, Object>> result=new HashMap<>();
			while(rs.next()) {
				Map<String,Object> innerMap = new HashMap<>();
				innerMap.put(COMPLIMENTARY_ID ,rs.getInt(COMPLIMENTARY_ID));	
				innerMap.put(LOYALTY_TYPE ,rs.getInt(LOYALTY_TYPE));	
				innerMap.put(CREATED_BY ,rs.getString(CREATED_BY));	
				innerMap.put(DATE_CREATED ,rs.getObject(DATE_CREATED, LocalDateTime.class));
				result.put(rs.getInt(LOYALTY_TYPE), innerMap);
			}
			return result;
		});
	
		List<Integer> toBeRemovedLoyalties = new ArrayList<>(dbLoyaltiesMap.keySet());
		toBeRemovedLoyalties.removeAll(updatedLoyalties);
		updatedLoyalties.removeAll(dbLoyaltiesMap.keySet());
		
		log.debug("toBeRemoved Loyalties: {}", toBeRemovedLoyalties);
		log.debug("toBeUpdated Loyalties: {}", updatedLoyalties);
		insertLoyalties(complimentaryPromotion.getComplimentaryId(), updatedLoyalties, LocalDateTime.now(), complimentaryPromotion.getUserMetaData().getModifiedBy());
		if(UtilValidate.isNotEmpty(toBeRemovedLoyalties)) {
			List<Map<String, Object>> toBeRemoved=toBeRemovedLoyalties.stream().map(dbLoyaltiesMap::get).collect(Collectors.toList());
			posNPJdbcTemplate.batchUpdate(INSERT_LOYALTY_QUERY_LOG, toBeRemoved.toArray(new Map[toBeRemoved.size()]));
			posNPJdbcTemplate.update(DELETE_LOYALTY_QUERY, new MapSqlParameterSource()
					.addValue(COMPLIMENTARY_ID, complimentaryPromotion.getComplimentaryId())
					.addValue(LOYALTY_TYPE, toBeRemovedLoyalties));
		}
	}

	@SuppressWarnings(UNCHECKED)
	private void updateChannels(ComplimentaryPromotion complimentaryPromotion) {
		List<Integer> updatedChannels = new ArrayList<>(complimentaryPromotion.getChannels());
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PROMOTION_ID, complimentaryPromotion.getComplimentaryId());
		params.addValue(APPLICABLE_TYPE, complimentaryPromotion.getApplicableType());
		Map<Integer, Map<String, Object>> dbPromotionChannelsMap= posNPJdbcTemplate.query(GET_CHANNEL_QUERY, params, (ResultSet rs) -> {
			Map<Integer, Map<String, Object>> result=new HashMap<>();
			while(rs.next()) {
				Map<String,Object> innerMap = new HashMap<>();
				innerMap.put(PROMOTION_ID ,rs.getInt(PROMOTION_ID));	
				innerMap.put(APPLICABLE_TYPE ,rs.getInt(APPLICABLE_TYPE));	
				innerMap.put(CHANNEL ,rs.getInt(CHANNEL));	
				innerMap.put("PromotionType" ,rs.getInt("PromotionType"));
				innerMap.put(CREATED_BY ,rs.getString(CREATED_BY));	

				result.put(rs.getInt(CHANNEL), innerMap);
			}
			return result;
		});
		List<Integer> toBeRemovedChannels = new ArrayList<>(dbPromotionChannelsMap.keySet());
		toBeRemovedChannels.removeAll(updatedChannels);
		
		updatedChannels.removeAll(dbPromotionChannelsMap.keySet());
		log.debug("toBeRemovedChannels: {}", toBeRemovedChannels);
		log.debug("updatedChannels: {}", updatedChannels);
		insertChannels(complimentaryPromotion.getComplimentaryId(), updatedChannels, complimentaryPromotion.getApplicableType(), complimentaryPromotion.getUserMetaData().getModifiedBy());
		if(UtilValidate.isNotEmpty(toBeRemovedChannels)) {
			List<Map<String, Object>> toBeRemoved=toBeRemovedChannels.stream().map(dbPromotionChannelsMap::get).collect(Collectors.toList());
			posNPJdbcTemplate.batchUpdate(INSERT_CHANNEL_QUERY_LOG, toBeRemoved.toArray(new Map[toBeRemoved.size()]));
			params.addValue(CHANNEL, toBeRemovedChannels);
			posNPJdbcTemplate.update(DELETE_CHANNEL_QUERY, params);
		}
	}
	
	private void updateCampaignCustomers(ComplimentaryPromotion complimentaryPromotion, boolean isNewCustomers) {
		Set<Long> uploadedCustomers = new HashSet<>(complimentaryPromotion.getCustomerIds());
		Set<Long> removedCustomers = new HashSet<>(complimentaryPromotion.getRemoveCustomerIds());
		Set<Long> commonCustomers = new HashSet<>(uploadedCustomers.stream().filter(removedCustomers::contains).collect(Collectors.toSet()));
		uploadedCustomers.removeAll(commonCustomers);
		Map<Long, Map<String, Object>> dbCustomerMap = null;
		if(!isNewCustomers) {
			removedCustomers.removeAll(commonCustomers);
			dbCustomerMap= posNPJdbcTemplate.query(GET_CUSTOMERS_QUERY, new MapSqlParameterSource(COMPLIMENTARY_ID, complimentaryPromotion.getComplimentaryId()), (ResultSet rs) -> {
				Map<Long, Map<String, Object>> result=new HashMap<>();
				while(rs.next()) {
					Map<String,Object> innerMap = new HashMap<>();
					innerMap.put(COMPLIMENTARY_ID ,rs.getInt(COMPLIMENTARY_ID));	
					innerMap.put(CUSTOMER_ID ,rs.getInt(CUSTOMER_ID));	
					innerMap.put(CREATED_BY ,rs.getString(CREATED_BY));	
					innerMap.put(DATE_CREATED ,rs.getObject(DATE_CREATED, LocalDateTime.class));
					result.put(rs.getLong(CUSTOMER_ID), innerMap);
				}
				return result;
			});
			uploadedCustomers.removeAll(dbCustomerMap.keySet());
			removedCustomers.retainAll(dbCustomerMap.keySet());
			log.info("uploadedCustomers : {}", uploadedCustomers);
			log.info("removedCustomers : {}", removedCustomers);
		}
		insertCustomers(complimentaryPromotion.getComplimentaryId(), uploadedCustomers, complimentaryPromotion.getUserMetaData().getModifiedBy(), LocalDateTime.now());
		if(!isNewCustomers && UtilValidate.isNotEmpty(removedCustomers)) {
			List<Map<String, Object>> toBeRemoved = removedCustomers.stream().map(dbCustomerMap::get).collect(Collectors.toList());
			innsertIntoLogAndDeleteCustomers(toBeRemoved);
		}
	}
	
	private void deactivateCustomers(ComplimentaryPromotion complimentaryPromotion) {
		List<Map<String, Object>> complimentaryPromotionCustomers=posNPJdbcTemplate.queryForList(GET_CUSTOMERS_QUERY, new MapSqlParameterSource(COMPLIMENTARY_ID, complimentaryPromotion.getComplimentaryId()));
		innsertIntoLogAndDeleteCustomers(complimentaryPromotionCustomers);
	}
	
	@SuppressWarnings(UNCHECKED)
	private void innsertIntoLogAndDeleteCustomers(List<Map<String, Object>> customerData) {
		posNPJdbcTemplate.batchUpdate(INSERT_CUSTOMERS_QUERY_LOG, customerData.toArray(new Map[customerData.size()]));
		posNPJdbcTemplate.batchUpdate(DELETE_CUSTOMER_QUERY, customerData.toArray(new Map[customerData.size()]));
	}

	@Override
	public Map<String, String> getExisitingSlabGroups() {
		return posReadonlyNPJdbcTemplate.query(GET_EXISTING_SLAB_DETAILS, rs -> {
			Map<String, String> slabs = new LinkedHashMap<>();
			while(rs.next()){
				slabs.put(rs.getString(COMPLIMENTARY_SLAB_ID) + "#" + rs.getString(INVOICE_AMOUNT) ,rs.getString(NAME));
			}
			return slabs;
		});		
	}

	@Override
	public boolean isSlabNameAvailable(String slabName) {
		MapSqlParameterSource params = new MapSqlParameterSource("slabName", slabName);
		Integer count = posReadonlyNPJdbcTemplate.queryForObject(IS_COMPLIMENTARY_SLAB_NAME_AVAILABLE, params, Integer.class);
		return (count!=null && count==0);
	}

	private static final Set<String> CAMPAIGN_TYPES = new HashSet<>();
	static {
		CAMPAIGN_TYPES.add(PromotionConstants.COMPLIMENTARY_ITEM);
	}
	@Override
	public boolean isPosChannel(Long complimentaryId) {
		return PromotionDaoHelper.isPosChannel(complimentaryId, CAMPAIGN_TYPES, posNPJdbcTemplate);
	}
	
	@Override
	public List<String> getStores(Long complimentaryId) {
		MapSqlParameterSource params = new MapSqlParameterSource(COMPLIMENTARY_ID,complimentaryId);
		return posReadonlyNPJdbcTemplate.queryForList(GET_STORES_QUERY, params, String.class);
	}
}
