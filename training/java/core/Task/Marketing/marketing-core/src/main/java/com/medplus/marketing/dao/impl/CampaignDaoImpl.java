package com.medplus.marketing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.discounts.constants.CouponApplicableMode;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.discounts.constants.ServiceChargeMode;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.constants.CampaignConstants;
import com.medplus.marketing.dao.CampaignDao;
import com.medplus.marketing.dao.helper.CampaignResultSetExtractor;
import com.medplus.marketing.dao.helper.PromotionCouponExtractor;
import com.medplus.marketing.dao.helper.PromotionDaoHelper;
import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignDetail;
import com.medplus.marketing.domain.CampaignProduct;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.PromotionCoupon;
import com.medplus.marketing.util.CampaignUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CampaignDaoImpl implements CampaignDao{

	@Autowired
	@Qualifier("posJdbcTemplate")
	JdbcTemplate posJdbcTemplate; 
	
	@Autowired
	@Qualifier("posReadonlyJdbcTemplate")
	JdbcTemplate posReadonlyJdbcTemplate; 
	
	@Autowired
	@Qualifier("posNPJdbcTemplate") 
	NamedParameterJdbcTemplate posNPJdbcTemplate;
	
	@Autowired
	@Qualifier("posReadonlyNPJdbcTemplate") 
	NamedParameterJdbcTemplate posReadonlyNPJdbcTemplate;
	
	private static final String INSERT_CAMPAIGN_TEMPLATE_QUERY = "INSERT INTO `tbl_campaign_template_header` (`Name`, `Status`, `CreatedBy`, `DateCreated`, `CampaignType`) VALUES (?, ?, ?, ?, ?)";
	
	private static final String INSERT_CAMPAIGN_TEMPLATE_DETAIL_QUERY = "INSERT INTO `tbl_campaign_template_detail` (`CampaignId`, `ProductId`, `DiscountType`, `DiscountAmount`,`PaybackPercentage` ,`CreatedBy`, `DateCreated`,"
			+ " `Condition`, `FromQuantity`, `ToQuantity`,`DisplayMessage`,`PriceToBeConsidered` ) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ? )";
	
	private static final String INSERT_CAMPAIGN_TEMPLATE_SUPPLEMENT_DETAIL_QUERY = "INSERT INTO `tbl_campaign_template_product_batch_detail` (`CampaignId`, `ProductId`, `FromQuantity`, `ToProductId`, `ToQuantity`, `CreatedBy`, `DateCreated`) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SAVE_CAMPAIGN_QUERY = "INSERT INTO `tbl_campaign_header` (`TemplateID`, `Name`, `DateFrom`, `DateTo`, `Status`, `CreatedBy`, `DateCreated`, `CloneReferenceId`, `ApplicableType`, `CouponBased`, `AllCustomers`, `MinInvoiceValue`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String INSERT_CAMPAIGN_DETAIL_QUERY = "INSERT INTO `tbl_campaign_detail` (`CampaignID`, `Loyality`, `ConditionType`, `Value`, `NotEligibleStore`, `CreatedBy`, `DateCreated`) VALUES (?, ?, ?, ?, ?, ?, ?) ";
	
	private static final String TEMPLATE_HEADER_LOG_QUERY = "INSERT INTO `tbl_campaign_template_header_log` (`ID`, `Name`, `Status`, `CreatedBy`, `DateCreated`, `ModifiedBy`,"
			+ " `DateModified`, `CampaignType`) values (:ID, :Name, :Status, :CreatedBy, :DateCreated, :ModifiedBy, :DateModified, :CampaignType)";
	
	private static final String TEMPLATE_DETAIL_LOG_QUERY = "INSERT INTO `tbl_campaign_template_detail_log`(`CampaignId`, `ProductId`, `DiscountType`, `DiscountAmount`,`PaybackPercentage` ,`CreatedBy`,"
			+ " `DateCreated`, `ModifiedBy`, `DateModified`, `Condition`, `FromQuantity`, `ToQuantity`, `DisplayMessage`, `PriceToBeConsidered`) SELECT `CampaignId`, `ProductId`, `DiscountType`, "
			+ "`DiscountAmount`,`PaybackPercentage` ,`CreatedBy`, `DateCreated`, `ModifiedBy`, `DateModified`, `Condition`, `FromQuantity`, `ToQuantity`,`DisplayMessage`,`PriceToBeConsidered` FROM `tbl_campaign_template_detail` "
			+ "WHERE `CampaignId` = :templateId AND `ProductId` IN (:removedProducts)";
	
	private static final String DELETE_TEMPLATE_DETAIL_QUERY = "DELETE FROM `tbl_campaign_template_detail` WHERE `CampaignId` = :templateId AND `ProductId` IN (:removedProducts)";
	
	private static final String SUPPLEMENT_DETAIL_TEMPLATE_LOG_QUERY = "INSERT INTO `tbl_campaign_template_product_batch_detail_log` (`CampaignId`, `ProductId`, `Batch`, `FromQuantity`, `ToBatch`,"
			+ "`ToProductId`, `ToQuantity`, `CreatedBy`, `DateCreated`, `ModifiedBy`, `DateModified`, `Id`) SELECT `CampaignId`, `ProductId`, `Batch`, `FromQuantity`, "
			+ "`ToBatch`, `ToProductId`, `ToQuantity`, `CreatedBy`, `DateCreated`, `ModifiedBy`, `DateModified`, `Id` FROM `tbl_campaign_template_product_batch_detail` "
			+ "WHERE `CampaignId` = :templateId AND `ProductId` IN (:removedProducts)";
	
	private static final String DELETE_SUPPLEMENT_DETAIL_QUERY = "DELETE FROM `tbl_campaign_template_product_batch_detail` WHERE `CampaignId` = :templateId AND `ProductId` IN (:removedProducts)";
	
	private static final String LOG_CAMPAIGN_HEADER_QUERY = "INSERT INTO `tbl_campaign_header_log` (`CampaignID`, `TemplateID`, `Name`, `CouponBased`,`AllCustomers`,`DateFrom`, `DateTo`, `Status`, `CreatedBy`, `DateCreated`, `ModifiedBy`, `DateModified` , `ApplicableType`, `MinInvoiceValue`) "
			+ " VALUES (:CampaignID, :TemplateID, :Name, :CouponBased, :AllCustomers, :DateFrom, :DateTo, :Status, :CreatedBy, :DateCreated, :ModifiedBy, :DateModified, :ApplicableType, :MinInvoiceValue )";
	
	private static final String GET_CAMPAIGN_HEADER_QUERY = "SELECT `CampaignID`, `TemplateID`, `Name`, `CouponBased`,`AllCustomers`, DateFrom, DateTo, `Status`, `CreatedBy`, "
			+ " `DateCreated`, `ModifiedBy`, `DateModified` , `ApplicableType`, `MinInvoiceValue` FROM `tbl_campaign_header` WHERE `CampaignID` = ? ";
	
	private static final String UPDATE_CAMPAIGN_HEADER_QUERY = "UPDATE `tbl_campaign_header` SET `CouponBased` = ?, `AllCustomers` = ?, `DateFrom` = ?, `DateTo` = ?, `Status` = ?, `ModifiedBy` = ?, `DateModified` = ? , `ApplicableType` = ?, `MinInvoiceValue` = ? WHERE `CampaignID` = ?";
	
	private static final String GET_CAMPAIGN_TEMPLATE_HEADER_QUERY = "SELECT `ID`, `Name`, `Status`, `CreatedBy`, `DateCreated`, `ModifiedBy`, `DateModified`, `CampaignType` "
			+ " FROM  `tbl_campaign_template_header` WHERE `ID` in (?)";
	
	private static final String UPDATE_CAMPAIGN_TEMPLATE_HEADER_QUERY = "UPDATE `tbl_campaign_template_header` SET  `Status` = :status, `ModifiedBy` = :modifiedBy, `DateModified` = now() WHERE `ID` in (:templateId) ";
	
	private static final String PROMOTION_CHANNEL_LOG_QUERY = "INSERT INTO `tbl_promotion_channel_mapping_log` (`PromotionId`, `ApplicableType`, `Channel`, `PromotionType`, `CreatedBy`) select `PromotionId`, `ApplicableType`, `Channel`, `PromotionType`, `CreatedBy` from tbl_promotion_channel_mapping where `PromotionId`=:promotionId AND `ApplicableType`=:applicableType AND `PromotionType` IN (:promotionType) AND Channel in (:channel)";
	
	private static final String DELETE_PROMOTION_CHANNEL_MAPPING_QUERY = "DELETE FROM `tbl_promotion_channel_mapping` where `PromotionId`=:promotionId AND `ApplicableType`=:applicableType AND `PromotionType` IN (:promotionType) AND Channel in (:channel)";
	
	private static final String INSERT_PROMOTION_CHANNEL_MAPPING_QUERY = "INSERT INTO `tbl_promotion_channel_mapping` (`PromotionId`, `ApplicableType`, `Channel`, `PromotionType`, `CreatedBy`) VALUES (?,?,?,?,?)";
	
	private static final String GET_PROMOTION_CHANNEL_MAPPING_QUERY = "select Channel,PromotionType from tbl_promotion_channel_mapping where PromotionId=:promotionId and ApplicableType=:applicableType and PromotionType IN (:promotionType)";
	
	private static final String CAMPAIGN_DETAIL_LOG_QUERY = "INSERT INTO `tbl_campaign_detail_log` (`ID`, `CampaignID`, `Loyality`, `ConditionType`, `Value`, `NotEligibleStore`,"
			+ "`CreatedBy`, `DateCreated`, `ModifiedBy`, `DateModified`) SELECT `ID`, `CampaignID`, `Loyality`, `ConditionType`, `Value`, `NotEligibleStore`,"
			+ "`CreatedBy`, `DateCreated`,`ModifiedBy`, `DateModified` FROM `tbl_campaign_detail` WHERE `CampaignID` = ? ";
	
	private static final String DELETE_CAMPAIGN_DETAIL_QUERY = "DELETE FROM `tbl_campaign_detail` WHERE `CampaignID` = ?";
	
	private static final String GET_CAMPAIGN_BY_ID_QUERY = "SELECT a.`Name` AS TemplateName, a.`Status` AS TemplateStatus, a.`CampaignType`, b.`CampaignID`, b.`TemplateID`, b.`Name`, DATE_FORMAT(b.DateFrom,'%Y-%m-%d %H:%i:%s') AS DateFrom , DATE_FORMAT(b.DateTo,'%Y-%m-%d %H:%i:%s') AS DateTo, b.EffectiveDate, b.`Status`, b.Remarks,  "
			+ "c.`Loyality`, c.`ConditionType`, c.`Value`, c.`NotEligibleStore` , b.`ApplicableType`, b.`CouponBased`, b.`AllCustomers`, b.`MinInvoiceValue`, ifnull(d.`Description`,'No data Available') as Description,d.`claimable`, d.`SpecialtyBased`, d.`ImagePath`, d.`ImageServerName`,d.`PromotionVisible` FROM `tbl_campaign_template_header` a INNER JOIN "
			+ "`tbl_campaign_header` b ON a.`ID` = b.`TemplateID` INNER JOIN `tbl_campaign_detail` c ON b.`CampaignID` = c.`CampaignID` LEFT JOIN `tbl_promotion_metainfo_mapping` d ON b.`CampaignID` = d.`PromotionId` WHERE b.`CampaignID` = ? ";

	private static final String GET_SUPPLEMENT_CAMPAIGN_PRODUCTS_QUERY = "SELECT a.`ProductId`, a.`FromQuantity`, b.`ToProductId`, a.`ToQuantity`, a.DiscountType, a.DiscountAmount,a.PaybackPercentage ,a.`DisplayMessage`,a.`PriceToBeConsidered` FROM  tbl_campaign_template_detail a LEFT JOIN `tbl_campaign_template_product_batch_detail` b ON a.CampaignId = b.CampaignId AND a.ProductId = b.ProductId WHERE a.`CampaignId` = ?";

	private static final String UPDATE_CAMPAIGN_TO_DATE_QUERY = "UPDATE `tbl_campaign_header` SET `DateTo`=?, ModifiedBy=?, DateModified=? WHERE `CampaignID`=?";

	private static final String IS_CAMPAIGN_NAME_AVAILABLE_QUERY = "SELECT COUNT(CampaignID) FROM tbl_campaign_header WHERE Name = ?";

	private static final String CHECK_CAMPAIGN_PRODUCTS_QUERY = "SELECT ProductId FROM tbl_product WHERE ProductId IN (:products)";

	private static final String GET_PROMOTION_DETAILS_QUERY = "SELECT Id,CouponCode,PromotionId,PromotionType,ApplicableType,AllCustomers,TotalLimit,CustomerLimit, FromDate,"
			+ " ToDate,Status,MinValue,AddonCoupon,MaxDiscount,MaxPoints,NoOfDays,CouponDiscountType,CreatedBy,DateCreated,ModifiedBy,DateModified FROM tbl_coupon_code"
			+ " WHERE PromotionId=:promotionId AND PromotionType=:promotionType AND (1=:couponCodeFlag OR CouponCode=:couponCode)";
	
	private static final String GET_PROMOTION_COUPON_QUERY = "SELECT Id,CouponCode,PromotionId,PromotionType,ApplicableType,AllCustomers,TotalLimit,CustomerLimit, FromDate,"
			+ " ToDate,Status,MinValue,AddonCoupon,MaxDiscount,MaxPoints,NoOfDays,CouponDiscountType,CreatedBy,DateCreated,ModifiedBy,DateModified FROM tbl_coupon_code"
			+ " WHERE PromotionId=:promotionId AND PromotionType=:PromotionType";

	private static final String INSERT_CAMPAIGN_CUSTOMERS_QUERY = "INSERT INTO `tbl_campaign_customer`(`CampaignId`,`CustomerId`,`CouponCode`,`CreatedBy`,`DateCreated`) VALUES(?,?,?,?,?)";

	private static final String UPDATE_COUPON_CODE_QUERY = "UPDATE `tbl_campaign_customer` SET `CouponCode`=? WHERE `CampaignId`=?";

	private static final String DELETE_CAMPAIGN_CUSTOMER_QUERY = "DELETE FROM `tbl_campaign_customer` WHERE `CampaignId`=:campaignId AND (1=:customerFlag OR `CustomerId` IN (:customerId))";

	private static final String GET_CAMPAIGN_CUSTOMER_COUNT_QUERY = "SELECT COUNT(*) FROM `tbl_campaign_customer` where CampaignId = ?";

	private static final String GET_CAMPAIGN_CUSTOMERS_QUERY = "SELECT CustomerId FROM tbl_campaign_customer WHERE CampaignId=?";
	
	private static final String GET_SERVICE_CHARGE_DETAILS= "SELECT ServiceChargeMode,ServiceChargeDiscount FROM tbl_service_charge_discounts WHERE PromotionId=? AND PromotionType=?";
	
	private static final String INSERT_SERVICE_CHARGE_DETAILS = "INSERT INTO tbl_service_charge_discounts (PromotionId, PromotionType, ServiceChargeMode, ServiceChargeDiscount, CreatedBy, DateCreated) VALUES "
																+ " (:promotionId, :promotionType, :serviceChargeMode, :serviceChargeDiscount, :createdBy, :dateCreated)";

	private static final String INSERT_META_INFO_DETAILS_QUERY = "INSERT INTO `tbl_promotion_metainfo_mapping` (`PromotionId`, `ApplicableType`, `PromotionType`,`Claimable`, `SpecialtyBased`, `Description`, `ImagePath`, `ImageServerName`, `PromotionVisible`, `CreatedBy`,`DateCreated`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,now())";

	private static final String INSERT_PROMOTION_COUPON_DETAILS = "INSERT INTO `tbl_coupon_code`(`CouponCode`,`PromotionId`,`PromotionType`,`ApplicableType`,`AllCustomers`,`TotalLimit`,CustomerLimit,`FromDate`,`ToDate`,`Status`,`MinValue`,`MaxDiscount`,`MaxPoints`,`AddonCoupon`, `NoOfDays`, `CouponDiscountType`, `CreatedBy`,`DateCreated`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String INSERT_TOTAL_COUPON_USED_QUERY = "INSERT INTO `tbl_total_coupon_used` (`CouponCode`, `TotalUsed`) VALUES (?, ?)";

	private static final String LOG_PROMOTION_METAINFO_QUERY = "INSERT INTO `tbl_promotion_metainfo_mapping_log` (`ID`,`PromotionId`, `ApplicableType`,`Claimable`, `SpecialtyBased`, `PromotionType`, `Description`, `ImagePath`, `ImageServerName`, `PromotionVisible`, `CreatedBy`,`DateCreated`) select `ID`, `PromotionId`, `ApplicableType`,`Claimable`, `SpecialtyBased`, `PromotionType`, `Description`, `ImagePath`, `ImageServerName`, `PromotionVisible`, `CreatedBy`,`DateCreated` from `tbl_promotion_metainfo_mapping` where `PromotionId`=? AND `ApplicableType`=? AND `PromotionType`=?";

	private static final String DELETE_PROMOTION_METAINFO_QUERY = "DELETE FROM `tbl_promotion_metainfo_mapping` where `PromotionId`=? AND `ApplicableType`=? AND `PromotionType`=?";

	private static final String LOG_COUPON_CODE_QUERY = "INSERT INTO `tbl_coupon_code_log`(`Id`,`CouponCode`,`PromotionId`,`PromotionType`,`ApplicableType`,`AllCustomers`,`TotalLimit`,CustomerLimit,`FromDate`,`ToDate`,`Status`,`MinValue`,`MaxDiscount`,`MaxPoints`,`AddonCoupon`,`NoOfDays`, `CouponDiscountType`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values (:Id, :CouponCode, :PromotionId, :PromotionType, :ApplicableType, :AllCustomers, :TotalLimit, :CustomerLimit, :FromDate, :ToDate, :Status, :MinValue, :MaxDiscount, :MaxPoints, :AddonCoupon, :NoOfDays, :CouponDiscountType, :CreatedBy, :DateCreated, :ModifiedBy, :DateModified)";

	private static final String ACTIVATE_OR_REJECT_COUPON_QUERY = "Update `tbl_coupon_code` SET Status=:status, ModifiedBy=:modifiedBy, DateModified=now() WHERE PromotionId=:campaignId";
	
	private static final String UPDATE_AND_ACTIVATE_COUPON_QUERY = "Update `tbl_coupon_code` SET Status=:status, ToDate=:toDate, ModifiedBy=:modifiedBy, DateModified=now() WHERE PromotionId=:campaignId";
	
	private static final String GET_COUPON_DETAILS_QUERY = "SELECT `Id`,`CouponCode`,`PromotionId`,`PromotionType`,`ApplicableType`,`AllCustomers`,`TotalLimit`,`CustomerLimit`,`FromDate`,`ToDate`,`Status`,`MinValue`,`MaxDiscount`,`MaxPoints`,`AddonCoupon`,`NoOfDays`, `CouponDiscountType`, `CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified` FROM tbl_coupon_code WHERE PromotionId=?";
	
	private static final String UPDATE_COUPON_DETAILS_QUERY = "UPDATE `tbl_coupon_code` SET `PromotionType`=?, `ApplicableType`=?, `AllCustomers`=?, `TotalLimit`=?, `CustomerLimit`=?, `FromDate`=?, `ToDate`=?, `Status`=?,`MinValue`=?,`MaxDiscount`=?,`MaxPoints`=?,`AddonCoupon`=?, `NoOfDays`=?, `CouponDiscountType`=?, `ModifiedBy`=?, `DateModified`=? WHERE CouponCode=?";

	private static final String INSERT_SERVICE_CHARGE_DETAILS_LOG = "INSERT INTO tbl_service_charge_discounts_log (PromotionId, PromotionType, ServiceChargeMode, ServiceChargeDiscount, CreatedBy, DateCreated, ModifiedBy, DateModified) select PromotionId, PromotionType, ServiceChargeMode, ServiceChargeDiscount, CreatedBy, DateCreated, ModifiedBy, DateModified FROM tbl_service_charge_discounts WHERE PromotionId= :promotionId AND PromotionType= :promotionType AND ServiceChargeMode IN (:serviceChargeModes)";

	
	private static final String UPDATE_SERVICE_CHARGER_DETAILS= "UPDATE tbl_service_charge_discounts SET ServiceChargeDiscount=:serviceChargeDiscount,ModifiedBy=:modifiedBy,DateModified=:dateModified WHERE PromotionId=:promotionId AND PromotionType=:promotionType AND ServiceChargeMode=:serviceChargeMode";

	private static final String LOG_COUPON_CODE_AGAINST_PROMOTION_ID = "INSERT INTO `tbl_coupon_code_log`(`Id`,`CouponCode`,`PromotionId`,`PromotionType`,`ApplicableType`,`AllCustomers`,`TotalLimit`,CustomerLimit,`FromDate`,`ToDate`,`Status`,`MinValue`,`MaxDiscount`,`MaxPoints`,`AddonCoupon`,`NoOfDays`,`CouponDiscountType`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) SELECT `Id`,`CouponCode`,`PromotionId`,`PromotionType`,`ApplicableType`,`AllCustomers`,`TotalLimit`,`CustomerLimit`,`FromDate`,`ToDate`,`Status`,`MinValue`,`MaxDiscount`,`MaxPoints`,`AddonCoupon`,`NoOfDays`,`CouponDiscountType`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified` FROM `tbl_coupon_code` WHERE `PromotionId`=? AND `PromotionType`=?";

	private static final String DELETE_TOTAL_COUPON_USED = "DELETE FROM tbl_total_coupon_used where CouponCode IN (SELECT CouponCode FROM `tbl_coupon_code` WHERE `PromotionId`=? AND `PromotionType`=?)";

	private static final String DELETE_COUPON_CODE = "DELETE FROM `tbl_coupon_code` WHERE `PromotionId`=? AND `PromotionType`=?";

	private static final String LOG_SERVICE_CHARGE_DISCOUNTS = "INSERT INTO tbl_service_charge_discounts_log(PromotionId, PromotionType, ServiceChargeMode, ServiceChargeDiscount, CreatedBy, DateCreated, ModifiedBy, DateModified) SELECT PromotionId, PromotionType, ServiceChargeMode, ServiceChargeDiscount, CreatedBy, DateCreated, ModifiedBy, DateModified FROM tbl_service_charge_discounts WHERE PromotionId=? AND PromotionType=?";

	private static final String DELETE_SERVICE_CHARGE_DISCOUNTS = "DELETE FROM tbl_service_charge_discounts WHERE PromotionId=? AND PromotionType=?";
	
	private static final String GET_COUPON_APPLICABLE_MODES_BY_PROMOTIONID = "SELECT Mode, Value FROM tbl_coupon_applicable_mode_mapping where PromotionId=:promotionId and PromotionType IN (:promotionType)";
	
	private static final String FIND_CONDITION_TYPES = "SELECT `ConditionTypeId`, `ConditionTypeName`, `DiscountTypeId`, `Status` FROM `tbl_condition_type`  WHERE `DiscountTypeId` = ?";

	private static final String GET_SUPPLEMENT_CAMPAIGN_PRODUCTS_WITH_MESSAGE_QUERY = "SELECT a.`ProductId` as ItemId, a.`FromQuantity`, a.`ToProductId`, a.`ToQuantity`,b.`DisplayMessage`,b.`PaybackPercentage`,b.`PriceToBeConsidered` AS PriceConsiderForSlab  FROM `tbl_campaign_template_product_batch_detail` a INNER JOIN tbl_campaign_template_detail b ON a.CampaignId = b.CampaignId AND a.ProductId = b.ProductId WHERE a.`CampaignId` = ?";

	private static final String GET_PHARMACY_CAMPAIGN_PRODUCTS_QUERY = "SELECT `ProductId` as ItemId, `FromQuantity`, b.`ConditionTypeName`, `DiscountAmount`,`ToQuantity` as MaxQuantity,`DisplayMessage`,`PaybackPercentage`, `PriceToBeConsidered` AS PriceConsiderForSlab "
			+ "FROM `tbl_campaign_template_detail` a INNER JOIN tbl_condition_type b ON a.DiscountType = ConditionTypeId "
			+ "WHERE `CampaignId` =  ? AND b.DiscountTypeId = ?";
	
	private static final String GET_STORES_QUERY = "SELECT Value from `tbl_campaign_detail` WHERE CampaignID= :campaignId and LENGTH(`Value`) = 12";
	
	private static final String GET_LABS_AND_LENS_CAMPAIGN_PRODUCTS_QUERY = "SELECT `ProductId` as ItemId, `FromQuantity`, b.`ConditionTypeName`, `DiscountAmount`,`ToQuantity` as MaxQuantity,`DisplayMessage`, `PriceToBeConsidered` AS PriceConsiderForSlab "
			+ "FROM `tbl_campaign_template_detail` a INNER JOIN tbl_condition_type b ON a.DiscountType = ConditionTypeId "
			+ "WHERE `CampaignId` =  ? AND b.DiscountTypeId = ?";
		
	private static final String GET_CAMPAIGNS_LIST = "SELECT count(a.CampaignID) over() as TotalRowCount, a.CampaignID,a.Name,a.CouponBased,a.AllCustomers, a.DateFrom, a.DateTo, a.EffectiveDate, "
			+ "a.Status,a.CreatedBy, a.DateCreated,a.ApprovedBy, a.DateApproved, a.CloneReferenceId, a.ApplicableType, group_concat(DISTINCT p.Channel) as Channels FROM tbl_campaign_header a "
			+ "INNER JOIN tbl_campaign_template_header b ON a.TemplateID=b.ID  "
			+ "LEFT JOIN tbl_promotion_channel_mapping p ON a.CampaignId = p.PromotionId  AND b.CampaignType = p.PromotionType "
			+ "LEFT JOIN tbl_coupon_code cc ON a.CampaignId = cc.PromotionId AND b.CampaignType = cc.PromotionType "
			+ "WHERE b.CampaignType IS NOT NULL AND (1=:campaignIdFlag OR a.CampaignID=:campaignId) AND (1=:statusFlag OR a.Status=:status) AND (1=:closedFlag or a.DateTo < now())"
			+ "AND a.ApplicableType in (:applicableTypes) AND (1=:nameFlag OR a.Name LIKE :campaignName) AND (1=:dateFlag OR a.DateCreated BETWEEN :fromDate and :toDate) "
			+ "AND (1=:channelFlag OR p.Channel in (:channel)) AND (1=:ccFlag OR cc.CouponCode=:couponCode) AND (1=:createdByFlag OR a.CreatedBy=:createdBy) "
			+ "GROUP BY a.CampaignId ORDER BY a.CampaignID DESC LIMIT :limit OFFSET :offset";

	private static final String GET_CLOSED_CAMPAIGNS_QUERY = "SELECT `CampaignID`, `TemplateID`, `Name`, `CouponBased`,`AllCustomers`, DateFrom, DateTo, `Status`, `CreatedBy`, "
			+ " `DateCreated`, `ModifiedBy`, `DateModified` , `ApplicableType`, `MinInvoiceValue` FROM `tbl_campaign_header` WHERE `DateTo` < :toDate  AND Status='I' ";

	private static final String APPROVE_OR_REJECT_CAMPAIGN_QUERY = "UPDATE `tbl_campaign_header` SET `Status` = :status, `ApprovedBy` = :approvedBy, "
			+ "`DateApproved` = :dateApproved, `EffectiveDate` = :effectiveDate, `Remarks` = :remarks WHERE (`CampaignID` = :campaignId)";
	
	private static final String UPDATE_AND_APPROVE_CAMPAIGN = "UPDATE `tbl_campaign_header` SET `DateTo` = :toDate, `Status` = :status, "
			+ "`ApprovedBy` = :approvedBy, `DateApproved` = :dateApproved, `EffectiveDate` = :effectiveDate, `ModifiedBy` = :modifiedBy, `DateModified` = now() WHERE (`CampaignID` = :campaignId)";
	
	private static final String UPDATE_ACTIVE_CAMPAIGN = "UPDATE `tbl_campaign_header` SET `DateTo` = :toDate, "
			+ "`ModifiedBy` = :modifiedBy, `DateModified` = now() WHERE (`CampaignID` = :campaignId)";

	private static final String UPDATE_COUPON_TO_DATE = "UPDATE `tbl_coupon_code` SET `ToDate`=?, `ModifiedBy`=?, `DateModified`=? WHERE CouponCode=?";
		
	private static final String GET_COUPON_CODES = "SELECT CouponCode FROM tbl_coupon_code WHERE CouponCode IN (:couponCodes)";

	private static final String CHANNEL = "Channel";
	
	private static final String PROMOTION_TYPE = "PromotionType";
	
	private static final String TYPES = "-1,1,2";
	
	private static final String EXCEPTION_MESSAGE = "Exception Occured : ";
	
	private static final String VALUE = "Value";

	private static final String PROMOTION_ID = "promotionId";

	private static final String PROMOTION_TYPE_PARAM = "promotionType";

	private static final String STATUS = "status";

	private static final String TO_DATE = "toDate";

	private static final String CAMPAIGN_ID = "campaignId";

	private static final String CAMPAIGN_ID_FIELD = "CampaignID";

	private static final String APPROVED_BY = "approvedBy";

	private static final String REMARKS = "remarks";

	private static final String EFFECTIVE_DATE = "effectiveDate";

	private static final String DATE_APPROVED = "dateApproved";
	
	private static final String MODIFIED_BY = "modifiedBy";

	private static final String TEMPLATE_ID = "templateId";

	private static final String COUPON_BASED = "CouponBased";

	private static final int DISCOUNT_TYPE_ID = 3;
	
	@Override
	public void saveCampaignTemplate(Campaign campaign) {

		int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER};
		PreparedStatementCreatorFactory psc = new PreparedStatementCreatorFactory(INSERT_CAMPAIGN_TEMPLATE_QUERY, types);
		psc.setReturnGeneratedKeys(true);
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		campaign.getUserMetaData().setDateCreated(LocalDateTime.now());
		try{
			posJdbcTemplate.update(psc.newPreparedStatementCreator(new Object[]{campaign.getCampaignName(), campaign.getStatus(), campaign.getUserMetaData().getCreatedBy(), campaign.getUserMetaData().getDateCreated(), campaign.getCampaignType() }), keyHolder);
		}catch(DuplicateKeyException e){
			throw new PromotionException("Campaign with Name " + campaign.getCampaignName() + " already exists! Please change name");
		}
		campaign.setTemplateId(keyHolder.getKey().longValue());
		saveCampaignTemplateDetail(campaign);
		log.info("campaignProducts saved: {}",campaign.getCampaignProducts());
	}

	private void saveCampaignTemplateDetail(Campaign campaign) {
		for (CampaignProduct campaignProduct : campaign.getCampaignProducts()) {
			try{
				posJdbcTemplate.update(INSERT_CAMPAIGN_TEMPLATE_DETAIL_QUERY, campaign.getTemplateId(), campaignProduct.getProductId(), campaignProduct.getDiscountType(), campaignProduct.getDiscountValue(), campaignProduct.getPaybackPercentage(),
						campaign.getUserMetaData().getCreatedBy(), campaign.getUserMetaData().getDateCreated(), 1, campaignProduct.getFromQuantity(), campaignProduct.getToQuantity(), campaignProduct.getDisplayMessage(), campaignProduct.getPriceConsiderForSlab());
				if(campaign.getCampaignType() == 5 || campaign.getCampaignType() == 6)
					posJdbcTemplate.update(INSERT_CAMPAIGN_TEMPLATE_SUPPLEMENT_DETAIL_QUERY, campaign.getTemplateId(), campaignProduct.getProductId(), campaignProduct.getFromQuantity(), campaignProduct.getToProductId(),
							campaignProduct.getToQuantity(), campaign.getUserMetaData().getCreatedBy(), campaign.getUserMetaData().getDateCreated());
			}catch(DuplicateKeyException e){
				throw new PromotionException(campaignProduct.getProductId() + " already exists! Please check the excel file");
			}
		}
	}
	
	@Override
	public void saveCampaign(Campaign campaign) {
		int[] types = {Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER, Types.INTEGER,Types.CHAR,Types.CHAR, Types.DOUBLE};
		PreparedStatementCreatorFactory psc = new PreparedStatementCreatorFactory(SAVE_CAMPAIGN_QUERY, types);
		psc.setReturnGeneratedKeys(true);
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		try{
			posJdbcTemplate.update(psc.newPreparedStatementCreator(new Object[]{campaign.getTemplateId(), campaign.getCampaignName(), campaign.getFromDate(),
					campaign.getToDate(), campaign.getStatus(), campaign.getUserMetaData().getCreatedBy(), campaign.getUserMetaData().getDateCreated(), campaign.getCloneReferenceId(),campaign.getPromotionApplicableType(),campaign.getCouponBased(),campaign.getAllCustomers(), campaign.getMinInvoiceValue()}), keyHolder);
		}catch(DuplicateKeyException e){
			throw new PromotionException("Promotion Name Alrady Exists! Please change name");
		}
		campaign.setCampaignId(keyHolder.getKey().longValue());
		saveCampaignDetails(campaign);
		savePromotionChannelMapping(campaign.getCampaignId(), campaign.getPromotionApplicableType(), campaign.getChannels(),campaign.getUserMetaData().getCreatedBy(), campaign.getCampaignType());
		if("Y".equals(campaign.getCouponBased()) && campaign.getPromotionCoupon()!=null && (UtilValidate.isNotEmpty(campaign.getPromotionCoupon().getDeliveryType()) || UtilValidate.isNotEmpty(campaign.getPromotionCoupon().getPrescription()) || UtilValidate.isNotEmpty(campaign.getPromotionCoupon().getPaymentType()))){
			EnumMap<CouponApplicableMode,List<String>> couponModes = new EnumMap<>(CouponApplicableMode.class);
			couponModes.put(CouponApplicableMode.DELIVERY_TYPE, campaign.getPromotionCoupon().getDeliveryType());
			couponModes.put(CouponApplicableMode.PAYMENT_MODE, campaign.getPromotionCoupon().getPaymentType());
			couponModes.put(CouponApplicableMode.E_PRESCRIPTION, campaign.getPromotionCoupon().getPrescription());
			PromotionDaoHelper.saveCouponApplicableModeMapping(campaign.getCampaignId(),campaign.getCampaignType(),couponModes, posNPJdbcTemplate);
		}
	}

	private void saveCampaignDetails(final Campaign campaign) {
		List<CampaignDetail> campaignDetails = new ArrayList<>();
		campaign.getLoyaltyTypes().forEach(loyaltyType -> 
			campaign.getRegionsMap().forEach((region, ner) -> {
				CampaignDetail campaignDetail = new CampaignDetail();
				campaignDetail.setCampaignId(campaign.getCampaignId());
				campaignDetail.setLoyaltyType(loyaltyType);
				campaignDetail.setRegion(region);
				campaignDetail.setNotEligibleRegion(ner);
				campaignDetails.add(campaignDetail);
			})
		);
		posJdbcTemplate.batchUpdate(INSERT_CAMPAIGN_DETAIL_QUERY, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				CampaignDetail campaignDetail = campaignDetails.get(i);
				ps.setLong(1, campaign.getCampaignId());
				ps.setInt(2, campaignDetail.getLoyaltyType());
				ps.setInt(3, campaignDetail.getConditionType());
				ps.setString(4, campaignDetail.getRegion());
				String notEligibleRegion=campaignDetail.getNotEligibleRegion();
				ps.setString(5,UtilValidate.isEmpty(notEligibleRegion) ? notEligibleRegion : notEligibleRegion.toUpperCase());
				ps.setString(6, campaign.getUserMetaData().getCreatedBy());
				ps.setObject(7, campaign.getUserMetaData().getDateCreated());
			}
			
			@Override
			public int getBatchSize() {
				return campaignDetails.size();
			}
		});
	}
	
	@Override
	public void updateCampaignTemplate(Campaign campaign) {
		Map<String, Object> headerDetails = posJdbcTemplate.queryForMap(GET_CAMPAIGN_TEMPLATE_HEADER_QUERY, campaign.getTemplateId());
 		posNPJdbcTemplate.update(TEMPLATE_HEADER_LOG_QUERY, headerDetails);
		if(campaign.getCampaignId() != null && UtilValidate.isNotEmpty(campaign.getRemovedProducts()))
			deleteCampaignTemplateDetail(campaign);
		if(UtilValidate.isNotEmpty(campaign.getCampaignProducts())) {
			/**
			 * List<CampaignProduct> existingCampaignProducts =
			 * findCampaignProductsByTemplateId(campaign.getTemplateId(),
			 * campaign.getCampaignType()); Map<String, String> existingMessages = new
			 * HashMap<>(); existingCampaignProducts.forEach(cp->
			 * existingMessages.put(cp.getProductId(), cp.getDisplayMessage()));
			 * campaign.getCampaignProducts().forEach(cp -> {
			 * if(existingMessages.containsKey(cp.getProductId()) &&
			 * !StringUtils.equals(existingMessages.get(cp.getProductId()),cp.
			 * getDisplayMessage())) throw new PromotionException(cp.getProductId() +
			 * " have different message in our system please correct and retry again!"); });
			 */
			saveCampaignTemplateDetail(campaign);
		}
	
	}
	
	public void deleteCampaignTemplateDetail(Campaign campaign){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TEMPLATE_ID, campaign.getTemplateId());
		params.addValue("removedProducts", campaign.getRemovedProducts());
		
		posNPJdbcTemplate.update(TEMPLATE_DETAIL_LOG_QUERY, params);
		posNPJdbcTemplate.update(DELETE_TEMPLATE_DETAIL_QUERY, params);
		if(campaign.getCampaignType() == 5 || campaign.getCampaignType() == 6){
			posNPJdbcTemplate.update(SUPPLEMENT_DETAIL_TEMPLATE_LOG_QUERY, params);
			posNPJdbcTemplate.update(DELETE_SUPPLEMENT_DETAIL_QUERY, params);
		}
	}

	@Override
	public void updateCampaign(Campaign campaign) {
		
		Map<String, Object> headerDetails=posJdbcTemplate.queryForMap(GET_CAMPAIGN_HEADER_QUERY, campaign.getCampaignId());
		posNPJdbcTemplate.update(LOG_CAMPAIGN_HEADER_QUERY, headerDetails);
		
		int campaignUpdate = posJdbcTemplate.update(UPDATE_CAMPAIGN_HEADER_QUERY, campaign.getCouponBased(), campaign.getAllCustomers(), campaign.getFromDate(), campaign.getToDate(), campaign.getStatus(), campaign.getUserMetaData().getCreatedBy(), campaign.getUserMetaData().getDateCreated(), campaign.getPromotionApplicableType(), campaign.getMinInvoiceValue(), campaign.getCampaignId());
		if(campaignUpdate <= 0)
			throw new PromotionException("Campaign Updation Failed!");
		if(!campaign.getLoyaltyTypes().isEmpty() && campaign.getRegionsMap().size() > 0){
			deleteCampaignDetail(campaign);
			saveCampaignDetails(campaign);
		}
		updatePromotionChannelMapping(campaign.getCampaignId(), campaign.getPromotionApplicableType(), campaign.getChannels(),campaign.getUserMetaData().getModifiedBy(), campaign.getCampaignType());
		if("Y".equals(campaign.getCouponBased()) && campaign.getPromotionCoupon()!=null ) {
			PromotionDaoHelper.updateCouponApplicableModes(campaign.getCampaignId(), campaign.getCampaignType(), campaign.getPromotionCoupon().getDeliveryType(), campaign.getPromotionCoupon().getPrescription(), campaign.getPromotionCoupon().getPaymentType(), posNPJdbcTemplate);
		}else{
			PromotionDaoHelper.updateCouponApplicableModes(campaign.getCampaignId(), campaign.getCampaignType(),  null,null,null, posNPJdbcTemplate);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void updatePromotionChannelMapping(long promotionId, int appllicableType, List<Integer> channels, String createdBy, int promotionType){
		Map<String, Object> map = getPromotionTypeAndChannels(promotionId, appllicableType, promotionType);
		
		List<Integer> dbChannels = (List<Integer>) map.get(CHANNEL);
		int existpromotionType =  (int) map.get(PROMOTION_TYPE);
		List<Integer> campaignChannels = new ArrayList<>(channels);
		List<Integer> deleteChannels = new ArrayList<>(dbChannels);
		log.info("DB channels : {}", dbChannels);
		List<Integer> insertChannels = new ArrayList<>(campaignChannels);
		log.info("New channels : {}", campaignChannels);
		if(promotionType == existpromotionType){
			deleteChannels.removeAll(campaignChannels);
			insertChannels.removeAll(dbChannels);
		}
		
		if(UtilValidate.isNotEmpty(deleteChannels)){
			
			MapSqlParameterSource source = new MapSqlParameterSource();
			source.addValue(PROMOTION_ID, promotionId);
			source.addValue("applicableType", appllicableType);
			if(promotionType > 2) 
				source.addValue(PROMOTION_TYPE_PARAM, promotionType) ;
			else	
				source.addValue(PROMOTION_TYPE_PARAM, TYPES);
			source.addValue("channel",deleteChannels);
			log.info("Delete channels : {}", deleteChannels);
			posNPJdbcTemplate.update(PROMOTION_CHANNEL_LOG_QUERY, source);
			posNPJdbcTemplate.update(DELETE_PROMOTION_CHANNEL_MAPPING_QUERY, source);
		}
		log.info("Insert channels : {}", insertChannels);
		if(UtilValidate.isNotEmpty(insertChannels)){
			savePromotionChannelMapping(promotionId,appllicableType,insertChannels,createdBy,promotionType);
		}
	}
	
	private void savePromotionChannelMapping(long promotionId, int appllicableType, List<Integer> channels, String createdBy, int type){
		
		posJdbcTemplate.batchUpdate(INSERT_PROMOTION_CHANNEL_MAPPING_QUERY, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, promotionId);
				ps.setInt(2, appllicableType);
				ps.setInt(3, channels.get(i));
				ps.setInt(4, type);
				ps.setString(5, createdBy);
			}
			
			@Override
			public int getBatchSize() {
				return channels.size();
			}
		});
	}
	
	private Map<String,Object>  getPromotionTypeAndChannels(long promotionId, int applicableType, int promotionType) {
		Map<String,Object> promotionChannelMap = new HashMap<>();
		List<Integer> channels = null;
		int existPromotionType = 0;
	
		try{
			MapSqlParameterSource source = new MapSqlParameterSource();
			source.addValue(PROMOTION_ID, promotionId);
			source.addValue("applicableType", applicableType);
			if(promotionType>2){
				source.addValue(PROMOTION_TYPE_PARAM, promotionType);
			} else {
				source.addValue(PROMOTION_TYPE_PARAM, TYPES);
			}
			List<Map<String,Object>> channelsList = posNPJdbcTemplate.queryForList(GET_PROMOTION_CHANNEL_MAPPING_QUERY, source);
			if(!channelsList.isEmpty()){
				channels = new ArrayList<>();
				for(Map<String,Object> eachRowData : channelsList){
					if(eachRowData != null && eachRowData.get(CHANNEL) != null) {
						channels.add((Integer)eachRowData.get(CHANNEL));
						existPromotionType = (int)eachRowData.get(PROMOTION_TYPE);
					}
				}
			}
		}catch(DataAccessException dae){
			log.error(EXCEPTION_MESSAGE, dae);
		}
		promotionChannelMap.put(CHANNEL,channels);
		promotionChannelMap.put(PROMOTION_TYPE,existPromotionType);
		return promotionChannelMap;
	}
	
	
	public void deleteCampaignDetail(Campaign campaign){
		posJdbcTemplate.update(CAMPAIGN_DETAIL_LOG_QUERY, campaign.getCampaignId());
		posJdbcTemplate.update(DELETE_CAMPAIGN_DETAIL_QUERY, campaign.getCampaignId());
	}
	
	@Override
	public Campaign findCampaignByCampaignId(Long campaignId) {
		log.info("At dao, campaignId: " + campaignId);
		try{
			return posJdbcTemplate.query(GET_CAMPAIGN_BY_ID_QUERY, new CampaignResultSetExtractor(), campaignId);
		}catch(DataAccessException dae){
			log.error(EXCEPTION_MESSAGE , dae);
		}
		return null;
	}

	@Override
	public List<CampaignProduct> findCampaignProductsByTemplateId(Long templateId, Integer campaignType) {

		return posJdbcTemplate.query(GET_SUPPLEMENT_CAMPAIGN_PRODUCTS_QUERY, new RowMapper<CampaignProduct>() {
			@Override
			public CampaignProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
				CampaignProduct campaignProduct = new CampaignProduct();
				campaignProduct.setProductId(rs.getString("ProductId"));
				campaignProduct.setFromQuantity(rs.getInt("FromQuantity"));
				int toQty = rs.getInt("ToQuantity");
				if(campaignType == 5 || campaignType == 6){
					campaignProduct.setToProductId(rs.getString("ToProductId"));
					campaignProduct.setToQuantity(toQty);
				}else{
					campaignProduct.setDiscountType(rs.getInt("DiscountType"));
					campaignProduct.setDiscountValue(rs.getDouble("DiscountAmount"));
					if(UtilValidate.isNotEmpty(rs.getObject("ToQuantity")))
						campaignProduct.setToQuantity(toQty);
				}
				if(UtilValidate.isNotEmpty(rs.getString("DisplayMessage"))) {
					campaignProduct.setDisplayMessage(rs.getString("DisplayMessage"));
				}
				if(UtilValidate.isNotEmpty(rs.getObject("PaybackPercentage"))) {
					campaignProduct.setPaybackPercentage(rs.getDouble("PaybackPercentage"));
				}
				if(UtilValidate.isNotEmpty(rs.getString("PriceToBeConsidered")))
					campaignProduct.setPriceConsiderForSlab(rs.getString("PriceToBeConsidered"));
				return campaignProduct;
			}
			
		}, templateId);
	}

	@Override
	public void setToDateForCampaign(Campaign campaign) {
		if(campaign != null){
			Map<String, Object> headerDetails = posReadonlyJdbcTemplate.queryForMap(GET_CAMPAIGN_HEADER_QUERY, campaign.getCampaignId());
			posNPJdbcTemplate.update(LOG_CAMPAIGN_HEADER_QUERY, headerDetails);
			posJdbcTemplate.update(UPDATE_CAMPAIGN_TO_DATE_QUERY, campaign.getToDate(),campaign.getUserMetaData().getCreatedBy(),campaign.getUserMetaData().getDateCreated(),campaign.getCampaignId());
		}
	}

	@Override
	public int checkCampaignNameAvailablity(String campaignName) {
		Integer count = posReadonlyJdbcTemplate.queryForObject(IS_CAMPAIGN_NAME_AVAILABLE_QUERY, Integer.class, campaignName);
		if(count==null)
			return 0;
		return count;
	}

	@Override
	public List<String> checkCampaignProducts(Set<String> campaignProducts) {
		return posReadonlyNPJdbcTemplate.query(CHECK_CAMPAIGN_PRODUCTS_QUERY, new MapSqlParameterSource("products", campaignProducts),new ResultSetExtractor<List<String>>(){
			@Override
			public List<String> extractData(ResultSet rs) throws SQLException {
				List<String> campaignProductsList = new ArrayList<>();
				while(rs.next()){
					campaignProductsList.add(rs.getString("ProductId"));
				}
				return campaignProductsList;
			}
		});
	}

	@Override
	public PromotionCoupon getPromotionCoupons(Long promotionId, int promotionType, String couponCode) {
		log.info("promotionId :{}",promotionId);
		if(UtilValidate.isEmpty(promotionId) || promotionId < 0)
			return null;
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue(PROMOTION_ID, promotionId);
		source.addValue(PROMOTION_TYPE_PARAM,promotionType);
		source.addValue("couponCode", couponCode);
		source.addValue("couponCodeFlag", UtilValidate.isNotEmpty(couponCode) ? 0 : 1);
		PromotionCoupon promotionCoupon = null;
		try{
			promotionCoupon = posReadonlyNPJdbcTemplate.query(GET_PROMOTION_DETAILS_QUERY, source, new PromotionCouponExtractor());
			if(promotionCoupon!= null) {
				Integer couponDiscountType = promotionCoupon.getCouponDiscountType();
				if(UtilValidate.isNotEmpty(couponDiscountType) && (Objects.equals(couponDiscountType, PromotionConstants.SERVICE_CHARGES_DISC_TYPE)
						||Objects.equals(couponDiscountType, PromotionConstants.PRODUCTS_AND_SERVICE_CHARGES_DISC_TYPE))) {
					promotionCoupon.setServiceChargeDiscounts(getServiceChargePromotionDetails(promotionCoupon.getPromotionId(), promotionCoupon.getPromotionType()));
				}
			}
			return promotionCoupon;
		}catch(DataAccessException dae){
			log.error(EXCEPTION_MESSAGE, dae);
		}
		return null;
	}

	@Override
	public void saveCampaignCustomerDetails(Campaign campaign) {
		if(campaign != null){
			String couponCode = campaign.getPromotionCoupon() != null ? campaign.getPromotionCoupon().getCouponCode().toUpperCase() : null;
			try {
				posJdbcTemplate.batchUpdate(INSERT_CAMPAIGN_CUSTOMERS_QUERY, new BatchPreparedStatementSetter() {
					List<Long> customerIds = new ArrayList<>(campaign.getCustomerIds());
					String createdBy = campaign.getCampaignId()==null ? campaign.getUserMetaData().getCreatedBy() : campaign.getUserMetaData().getModifiedBy();
					LocalDateTime dateCreated = campaign.getCampaignId()==null ? campaign.getUserMetaData().getDateCreated() : campaign.getUserMetaData().getDateModified();
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setLong(1, campaign.getCampaignId());
						ps.setLong(2, customerIds.get(i));
						ps.setString(3, couponCode);
						ps.setString(4, createdBy);
						ps.setObject(5, dateCreated);
					}
					@Override
					public int getBatchSize() {
						return campaign.getCustomerIds().size();
					}
				});
			} catch(DataAccessException e){
				log.error("Error updating the customer details for the campaign {}!",campaign.getCampaignName(), e);
				throw new PromotionException("Error saving customer details for the campaign {}!", campaign.getCampaignName());
			}
		}
	}

	@Override
	public void updateCouponDetailsForCampaignCustomers(Long campaignId, String couponCode) {
		if(campaignId >0){
			posJdbcTemplate.update(UPDATE_COUPON_CODE_QUERY, couponCode, campaignId);
		}
	}

	@Override
	public void updateCampaignCustomerDetails(Campaign campaign) {
		if(UtilValidate.isEmpty(campaign))
			throw new PromotionException("Campaign can not be empty!");
		int customerFlag = 1;
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("customerFlag", customerFlag);
		source.addValue(CAMPAIGN_ID, campaign.getCampaignId());
		if(CampaignConstants.FEW_CUSTOMERS.equalsIgnoreCase(campaign.getExistingAllCustomers()) && CampaignConstants.ALL_CUSTOMERS.equalsIgnoreCase(campaign.getAllCustomers())){
			source.addValue("customerId", null);
			posNPJdbcTemplate.update(DELETE_CAMPAIGN_CUSTOMER_QUERY,source);
		}else if(CampaignConstants.ALL_CUSTOMERS.equalsIgnoreCase(campaign.getExistingAllCustomers()) && CampaignConstants.FEW_CUSTOMERS.equalsIgnoreCase(campaign.getAllCustomers())){
			if(UtilValidate.isEmpty(campaign.getCustomerIds()))
				throw new PromotionException("Customer details could not be empty,Please refresh and try again");
			saveCampaignCustomerDetails(campaign);
			
		}else if(CampaignConstants.FEW_CUSTOMERS.equalsIgnoreCase(campaign.getAllCustomers())){
			if(UtilValidate.isNotEmpty(campaign.getCustomerIds())){
				saveCampaignCustomerDetails(campaign);
			}
			
			if(UtilValidate.isNotEmpty(campaign.getRemovedCustomerIds())){
				customerFlag=0;
				source.addValue("customerFlag", customerFlag);
				source.addValue("customerId", campaign.getRemovedCustomerIds());
				posNPJdbcTemplate.update(DELETE_CAMPAIGN_CUSTOMER_QUERY,source);
				Integer count = posJdbcTemplate.queryForObject(GET_CAMPAIGN_CUSTOMER_COUNT_QUERY, Integer.class, campaign.getCampaignId());
				if(count <1){
					throw new PromotionException("Please give atleast one customerId for few customer promotion");
				}
			}
		}
	}

	@Override
	public List<Long> getCampaignCustomers(Long campaignId) {
		return posReadonlyJdbcTemplate.queryForList(GET_CAMPAIGN_CUSTOMERS_QUERY, campaignId).stream().map(r -> ((Number)r.get("CustomerId")).longValue()).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getPromotionChannels(long promotionId, int applicableType, int promotionType) {
		Map<String, Object> map = getPromotionTypeAndChannels(promotionId, applicableType, promotionType);
		return (List<Integer>) map.get(CHANNEL);
	}

	@Override
	public Map<CouponApplicableMode, List<String>> getCouponApplicableModes(long promotionId, int promotionType) {
		Map<CouponApplicableMode, List<String>> couponApplicableModes = null;
		try{
			MapSqlParameterSource source = new MapSqlParameterSource();
			source.addValue(PROMOTION_ID, promotionId);
			if(promotionType>2){
				source.addValue(PROMOTION_TYPE_PARAM, promotionType);
			} else {
				source.addValue(PROMOTION_TYPE_PARAM, TYPES);
			}
			List<Map<String,Object>> couponApplicableModesList = posNPJdbcTemplate.queryForList(GET_COUPON_APPLICABLE_MODES_BY_PROMOTIONID, source);
			if(!couponApplicableModesList.isEmpty()){
				couponApplicableModes = new EnumMap<>(CouponApplicableMode.class);
				for(Map<String,Object> eachRowData : couponApplicableModesList){
					if(eachRowData != null && eachRowData.get("Mode") != null && eachRowData.get(VALUE) != null) {
						CouponApplicableMode mode = CouponApplicableMode.valueOf(eachRowData.get("Mode").toString());
						List<String> values = couponApplicableModes.get(mode);
						if(values==null){
							values = new ArrayList<>();
							couponApplicableModes.put(mode, values);
						}
						values.add(eachRowData.get(VALUE).toString());
					}
				}
			}
		}catch(DataAccessException dae){
			log.error(EXCEPTION_MESSAGE, dae);
		}
		return couponApplicableModes;
	}
	
	@Override
	public Map<ServiceChargeMode, Double> getServiceChargePromotionDetails(Long promotionId, Integer promotionType) {
	    return posReadonlyJdbcTemplate.query(GET_SERVICE_CHARGE_DETAILS, new ResultSetExtractor<Map<ServiceChargeMode, Double>>() {
	        @Override
	        public Map<ServiceChargeMode, Double> extractData(ResultSet rs) throws SQLException {
	            
	        	Map<ServiceChargeMode, Double> serviceChargeDetails = new HashMap<>();
	            while(rs.next()) {
	                ServiceChargeMode serviceChargeMode = ServiceChargeMode.getServiceChargeMode(rs.getInt("ServiceChargeMode"));
	                if (serviceChargeMode != null) {
	                    serviceChargeDetails.put(serviceChargeMode, rs.getDouble("ServiceChargeDiscount"));
	                }    
	            }
	            return serviceChargeDetails;
	        }
	    }, promotionId, promotionType);
	}

	
	@Override
	public void savePromotionMetainfoDetails(long promotionId, int promotionApplicableType, int promotionType, UserMetaData userMetaData){
		int[] types = {Types.INTEGER, Types.INTEGER, Types.INTEGER,Types.BOOLEAN, Types.BOOLEAN, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.CHAR, Types.VARCHAR};
		PreparedStatementCreatorFactory psc = new PreparedStatementCreatorFactory(INSERT_META_INFO_DETAILS_QUERY, types);
		try{
			posJdbcTemplate.update(psc.newPreparedStatementCreator(new Object[]{promotionId, promotionApplicableType, promotionType,userMetaData.isClaimable(), userMetaData.isSpecialtyBased(), userMetaData.getLongDescription(), userMetaData.getImagePath(), userMetaData.getImageServerName(), userMetaData.isPromotionVisible()?'Y':'N', userMetaData.getCreatedBy()}));
		}catch(DuplicateKeyException e){
			throw new PromotionException("Promotion Mapping Already Exists!");
		}catch(Exception e){
			log.error("Exception in Dao ", e);
			throw new PromotionException(e.getMessage());
		}
	}
	
	@Override
	public void savePromotionCouponDetails(PromotionCoupon promotionCoupon){
		log.info("promotionCoupon : {}",promotionCoupon);
		if(UtilValidate.isNotEmpty(promotionCoupon)){
			try{
				posJdbcTemplate.update(INSERT_PROMOTION_COUPON_DETAILS,promotionCoupon.getCouponCode().toUpperCase(),promotionCoupon.getPromotionId(),promotionCoupon.getPromotionType(),promotionCoupon.getApplicableType(),promotionCoupon.isAllCustomers()?"Y":"N",promotionCoupon.getTotalLimit(),promotionCoupon.getCustomerLimit(),promotionCoupon.getFromDate(),promotionCoupon.getToDate(),promotionCoupon.getStatus(),promotionCoupon.getMinValue(),promotionCoupon.getMaxDiscount(),promotionCoupon.getMaxPoints(),promotionCoupon.isAddOnCoupon(),promotionCoupon.getNoOfDays(), promotionCoupon.getCouponDiscountType() , promotionCoupon.getCreatedBy(),promotionCoupon.getDateCreated());
			}catch(DuplicateKeyException e){
				throw new PromotionException("Couponcode Already Exists! Please Change Name");
			}
			posJdbcTemplate.update(INSERT_TOTAL_COUPON_USED_QUERY, promotionCoupon.getCouponCode().toUpperCase(), 0);
		}
	}
	
	private static List<MapSqlParameterSource> prepareServiceChargeParams(
			Map<ServiceChargeMode, Double> serviceChargeDiscounts, PromotionCoupon promotionCoupon, boolean isCreate) {
		List<MapSqlParameterSource> paramsList = new ArrayList<>();
		serviceChargeDiscounts.forEach((serviceCharge, discount) -> {
			if (discount != null) {
				MapSqlParameterSource params = new MapSqlParameterSource();
				params.addValue("promotionId", promotionCoupon.getPromotionId());
				params.addValue("promotionType", promotionCoupon.getPromotionType());
				params.addValue("serviceChargeMode", serviceCharge.getMode());
				params.addValue("serviceChargeDiscount", discount);
				params.addValue(isCreate ? "createdBy" : "modifiedBy", promotionCoupon.getCreatedBy());
				params.addValue(isCreate ? "dateCreated" : "dateModified", promotionCoupon.getDateCreated());
				paramsList.add(params);
			}
		});
		return paramsList;
	}
	
	@Override
	public void saveServiceChargePromotions(PromotionCoupon promotionCoupon) {
		saveServiceCharges(promotionCoupon.getServiceChargeDiscounts(), promotionCoupon);
	}
	
	private void saveServiceCharges(Map<ServiceChargeMode, Double> serviceChargeDiscounts, PromotionCoupon promotionCoupon) {
		try {
			List<MapSqlParameterSource> paramsList = prepareServiceChargeParams(serviceChargeDiscounts, promotionCoupon, true);
			if (!paramsList.isEmpty()) {
				posNPJdbcTemplate.batchUpdate(INSERT_SERVICE_CHARGE_DETAILS, paramsList.toArray(new MapSqlParameterSource[0]));
			} else {
				log.info("No valid service charge discounts to process for promotion {}", promotionCoupon.getPromotionId());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
	        throw new PromotionException("Failed to save service charge promotions.");
		} 
	}
	
	@Override
	public void updatePromotionMetainfoDetails(long promotionId, int promotionApplicableType, int promotionType, UserMetaData userMetaData){
		try{
			if(UtilValidate.isNotEmpty(promotionId)){
				posJdbcTemplate.update(LOG_PROMOTION_METAINFO_QUERY, promotionId,promotionApplicableType,promotionType);
				posJdbcTemplate.update(DELETE_PROMOTION_METAINFO_QUERY, promotionId,promotionApplicableType,promotionType);
				savePromotionMetainfoDetails(promotionId, promotionApplicableType, promotionType, userMetaData);
			}
		}catch(Exception e){
			log.error("Exception in Dao ",e);
			throw new PromotionException(e.getMessage());
		}
	}
	
	@Override
	public void updatePromotionCouponDetails(PromotionCoupon promotionCoupon){
		log.info("promotionCoupon: {} ",promotionCoupon);
		if(promotionCoupon != null){
			Map<String, Object> headerDetails = posJdbcTemplate.queryForMap(GET_COUPON_DETAILS_QUERY, promotionCoupon.getPromotionId());
			posNPJdbcTemplate.update(LOG_COUPON_CODE_QUERY, headerDetails);
			int couponUpdate = posJdbcTemplate.update(UPDATE_COUPON_DETAILS_QUERY, promotionCoupon.getPromotionType(),promotionCoupon.getApplicableType(),promotionCoupon.isAllCustomers()?"Y":"N",promotionCoupon.getTotalLimit(),promotionCoupon.getCustomerLimit(),promotionCoupon.getFromDate(),promotionCoupon.getToDate(),promotionCoupon.getStatus(),promotionCoupon.getMinValue(),promotionCoupon.getMaxDiscount(),promotionCoupon.getMaxPoints(),promotionCoupon.isAddOnCoupon(), promotionCoupon.getNoOfDays(), promotionCoupon.getCouponDiscountType(), promotionCoupon.getCreatedBy(),promotionCoupon.getDateCreated(),promotionCoupon.getCouponCode());
			if(couponUpdate <= 0) 
				throw new PromotionException("Coupon Updation Failed!");
		}
	}
	
	@Override
	public void updateServiceChargePromotions(PromotionCoupon promotionCoupon) {		
		Map<ServiceChargeMode, Double> existingServiceCharges = getServiceChargePromotionDetails(promotionCoupon.getPromotionId(), promotionCoupon.getPromotionType());
		Map<ServiceChargeMode, Double> toBeUpdatedServiceCharges = new HashMap<>();
		Map<ServiceChargeMode, Double> toBeCreatedServiceCharges = new HashMap<>();
		
		promotionCoupon.getServiceChargeDiscounts().forEach((serviceChargeMode, currentDiscount) -> {
		    Double existingDiscount = existingServiceCharges.get(serviceChargeMode);
		    if (existingDiscount == null) {
		        toBeCreatedServiceCharges.put(serviceChargeMode, currentDiscount);
		    } else if (!currentDiscount.equals(existingDiscount)) {
		        toBeUpdatedServiceCharges.put(serviceChargeMode, currentDiscount);
		    }
		    existingServiceCharges.remove(serviceChargeMode);
		});
		
		existingServiceCharges.forEach((serviceChargeMode, existingDiscount) -> {
			if(existingDiscount != 0) {
			    toBeUpdatedServiceCharges.put(serviceChargeMode, 0.0);
			}
		});
		
		log.info("toBeCreatedServiceCharges {}",toBeCreatedServiceCharges);
		log.info("toBeUpdatedServiceCharges {}",toBeUpdatedServiceCharges);

		try {
			if(UtilValidate.isNotEmpty(toBeUpdatedServiceCharges)) {
				MapSqlParameterSource params = new MapSqlParameterSource();
				params.addValue("promotionId", promotionCoupon.getPromotionId());
				params.addValue("promotionType", promotionCoupon.getPromotionType());
				params.addValue("serviceChargeModes", 
						toBeUpdatedServiceCharges.keySet().stream().map(servCharge -> servCharge.getMode()).collect(Collectors.toSet()));
				posNPJdbcTemplate.update(INSERT_SERVICE_CHARGE_DETAILS_LOG, params);
			}
			saveServiceCharges(toBeCreatedServiceCharges, promotionCoupon);
			List<MapSqlParameterSource> paramsList = prepareServiceChargeParams(toBeUpdatedServiceCharges, promotionCoupon, false);
			posNPJdbcTemplate.batchUpdate(UPDATE_SERVICE_CHARGER_DETAILS, paramsList.toArray(new MapSqlParameterSource[0]));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PromotionException("Unable to Update Service Charges");
		}
	}

	@Override
	public void deleteCouponCodesForPromotion(Long promotionId,Integer promotionType){
		if(promotionId >0){
			posJdbcTemplate.update(LOG_COUPON_CODE_AGAINST_PROMOTION_ID,promotionId,promotionType);
			posJdbcTemplate.update(DELETE_TOTAL_COUPON_USED, promotionId,promotionType);
			posJdbcTemplate.update(DELETE_COUPON_CODE,promotionId,promotionType);
			posJdbcTemplate.update(LOG_SERVICE_CHARGE_DISCOUNTS, promotionId, promotionType);
			posJdbcTemplate.update(DELETE_SERVICE_CHARGE_DISCOUNTS, promotionId, promotionType);
		}
	}
	
	@Override
	public void setToDateForPromotionCouponDetails(PromotionCoupon promotionCoupon) {
		if(promotionCoupon != null){
			Map<String, Object> headerDetails = posReadonlyJdbcTemplate.queryForMap(GET_COUPON_DETAILS_QUERY, promotionCoupon.getPromotionId());
			posNPJdbcTemplate.update(LOG_COUPON_CODE_QUERY, headerDetails);
			posJdbcTemplate.update(UPDATE_COUPON_TO_DATE, promotionCoupon.getToDate(),promotionCoupon.getCreatedBy(),promotionCoupon.getDateCreated(),promotionCoupon.getCouponCode());
		}
	}
	
	@Override
	public Map<String, Integer> findConditionTypes(Integer campaignType){
		return posReadonlyJdbcTemplate.query(FIND_CONDITION_TYPES, new ResultSetExtractor<Map<String, Integer>>(){
			@Override
			public Map<String, Integer> extractData(ResultSet rs)
					throws SQLException {
				Map<String, Integer> campaignTypes = new HashMap<>();
 				while(rs.next()){
 					campaignTypes.put(rs.getString("ConditionTypeName"), rs.getInt("ConditionTypeId"));
				}
				return campaignTypes;
			}
		}, campaignType);
	}
	
	@Override
	public List<Map<String, Object>> findCampaignProductsByTemplateIdForExcel(Long templateId, Integer campaignType, Integer applicableType) {
		log.info("templateId for excel download is : {} and campaign type is : {}",templateId,campaignType);
		if(campaignType == 5 || campaignType == 6) {
			return posReadonlyJdbcTemplate.queryForList(GET_SUPPLEMENT_CAMPAIGN_PRODUCTS_WITH_MESSAGE_QUERY, templateId);
		}else if(applicableType==5){
			return posReadonlyJdbcTemplate.queryForList(GET_PHARMACY_CAMPAIGN_PRODUCTS_QUERY, templateId, DISCOUNT_TYPE_ID);
		}else {
			return posReadonlyJdbcTemplate.queryForList(GET_LABS_AND_LENS_CAMPAIGN_PRODUCTS_QUERY, templateId, campaignType);
		}
	}
	
	@Override
	public Map<String, Object> getCampaignList(CampaignSearchCriteria searchCriteria) {
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
		String couponCode = searchCriteria.getCouponCode();
		int offset = searchCriteria.getOffset();
		int limit = searchCriteria.getLimit();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CAMPAIGN_ID, campaignId);
		params.addValue("campaignName", name);
		params.addValue("applicableTypes", applicableTypes);
		params.addValue("channel", channels);
		params.addValue(STATUS, status);
		params.addValue("createdBy", createdBy);
		params.addValue("fromDate", fromDate);
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
		params.addValue("couponCode", couponCode);
		params.addValue("ccFlag", getFlag(couponCode, campaignIdFlag));
		return posReadonlyNPJdbcTemplate.query(GET_CAMPAIGNS_LIST, params, 
				(ResultSet rs) -> {
					List<Campaign> campaignList = new ArrayList<>();
					Map<String, Object> result = new HashMap<>();
					int totalRowCount = 0;
					while(rs.next()){
						totalRowCount= rs.getInt("TotalRowCount");
						Campaign campaign = new Campaign();
						campaign.setCampaignId(rs.getLong(CAMPAIGN_ID_FIELD));
						campaign.setCampaignName(rs.getString("Name"));
						campaign.setCouponBased(rs.getString(COUPON_BASED));
						campaign.setAllCustomers(rs.getString("AllCustomers"));
						campaign.setFromDate(rs.getObject("DateFrom", LocalDateTime.class));
						campaign.setToDate(rs.getObject("DateTo", LocalDateTime.class));
						campaign.setEffectiveDate(rs.getObject("EffectiveDate", LocalDateTime.class));
						campaign.setStatus(rs.getString("Status"));
						UserMetaData metaData = new UserMetaData();
						metaData.setCreatedBy(rs.getString("CreatedBy"));
						metaData.setDateCreated(rs.getObject("DateCreated", LocalDateTime.class));
						metaData.setApprovedBy(rs.getString("ApprovedBy"));
						metaData.setDateApproved(rs.getObject("DateApproved", LocalDateTime.class));
						campaign.setUserMetaData(metaData);
						String cloneRefId = rs.getString("CloneReferenceId");
						if(UtilValidate.isNotEmpty(cloneRefId))
							campaign.setCloneReferenceId(Long.parseLong(cloneRefId));
						campaign.setPromotionApplicableType(rs.getInt("ApplicableType"));
						List<Integer> channelsForId = Stream.of(rs.getString("Channels").split(","))
								  .map(String::trim)
								  .map(Integer::parseInt)
								  .collect(Collectors.toList());
						campaign.setChannels(channelsForId);
						campaignList.add(campaign);
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
	public boolean approveCampaign(long campaignId, long templateId, LocalDateTime toDate, String approvedBy, Map<String, Object> headerDetails, boolean isDateUpdated) {
		
		Map<String, Object> templateHeaderDetails = posReadonlyJdbcTemplate.queryForMap(GET_CAMPAIGN_TEMPLATE_HEADER_QUERY, templateId);
		posNPJdbcTemplate.update(TEMPLATE_HEADER_LOG_QUERY, templateHeaderDetails);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(STATUS, "A");
		params.addValue(MODIFIED_BY, approvedBy);
		params.addValue(TEMPLATE_ID, templateId);
		posNPJdbcTemplate.update(UPDATE_CAMPAIGN_TEMPLATE_HEADER_QUERY, params);
		
		if(CampaignConstants.COUPON_BASED.equals(headerDetails.get(COUPON_BASED))) {
			Map<String, Object> couponHeaderDetails = posReadonlyJdbcTemplate.queryForMap(GET_COUPON_DETAILS_QUERY, campaignId);
			posNPJdbcTemplate.update(LOG_COUPON_CODE_QUERY, couponHeaderDetails);
			params = new MapSqlParameterSource();
			params.addValue(STATUS, "A");
			params.addValue(MODIFIED_BY, approvedBy);
			params.addValue(CAMPAIGN_ID, campaignId);
			params.addValue(TO_DATE, toDate);
			String couponQuery = isDateUpdated ? UPDATE_AND_ACTIVATE_COUPON_QUERY : ACTIVATE_OR_REJECT_COUPON_QUERY;
			posNPJdbcTemplate.update(couponQuery, params);
		}
		
		posNPJdbcTemplate.update(LOG_CAMPAIGN_HEADER_QUERY, headerDetails);
		params = new MapSqlParameterSource();
		params.addValue(CAMPAIGN_ID, campaignId);
		params.addValue(TO_DATE, toDate);
		params.addValue(STATUS, PromotionConstants.ACTIVE);
		params.addValue(APPROVED_BY, approvedBy);
		params.addValue(MODIFIED_BY, approvedBy);
		params.addValue(REMARKS, null);
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime fromDate = CampaignUtil.getDateFromMap(headerDetails.get("DateFrom"));
		LocalDateTime effectiveDate =  fromDate.isAfter(currentDate) ? fromDate : currentDate;
		params.addValue(EFFECTIVE_DATE, effectiveDate);
		params.addValue(DATE_APPROVED, currentDate);
		String query = isDateUpdated ? UPDATE_AND_APPROVE_CAMPAIGN : APPROVE_OR_REJECT_CAMPAIGN_QUERY;
		
		int rows =posNPJdbcTemplate.update(query, params);
		return rows==1;
	}

	@Override
	public void rejectCampaign(long campaignId, long templateId, String rejectedBy, String remarks, Map<String, Object> headerDetails) {
		Map<String, Object> templateHeaderDetails = posReadonlyJdbcTemplate.queryForMap(GET_CAMPAIGN_TEMPLATE_HEADER_QUERY, templateId);
		posNPJdbcTemplate.update(TEMPLATE_HEADER_LOG_QUERY, templateHeaderDetails);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(STATUS, "R");
		params.addValue(MODIFIED_BY, rejectedBy);
		params.addValue(TEMPLATE_ID, templateId);
		posNPJdbcTemplate.update(UPDATE_CAMPAIGN_TEMPLATE_HEADER_QUERY, params);
		if(CampaignConstants.COUPON_BASED.equals(headerDetails.get(COUPON_BASED))) {
			Map<String, Object> couponHeaderDetails = posReadonlyJdbcTemplate.queryForMap(GET_COUPON_DETAILS_QUERY, campaignId);
			posNPJdbcTemplate.update(LOG_COUPON_CODE_QUERY, couponHeaderDetails);
			params = new MapSqlParameterSource();
			params.addValue(STATUS, "R");
			params.addValue(MODIFIED_BY, rejectedBy);
			params.addValue(CAMPAIGN_ID, campaignId);
			posNPJdbcTemplate.update(ACTIVATE_OR_REJECT_COUPON_QUERY, params);
		}
		posNPJdbcTemplate.update(LOG_CAMPAIGN_HEADER_QUERY, headerDetails);
		params = new MapSqlParameterSource();
		params.addValue(CAMPAIGN_ID, campaignId);
		params.addValue(STATUS, "R");
		params.addValue(APPROVED_BY, rejectedBy);
		params.addValue(REMARKS, remarks);
		params.addValue(EFFECTIVE_DATE, null);
		params.addValue(DATE_APPROVED, LocalDateTime.now());
		posNPJdbcTemplate.update(APPROVE_OR_REJECT_CAMPAIGN_QUERY, params);
	}

	@Override
	public boolean updateCampaignToDate(long campaignId, LocalDateTime toDate, 
			String modifiedBy, Map<String, Object> headerDetails) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		if(CampaignConstants.COUPON_BASED.equals(headerDetails.get(COUPON_BASED))) {
			Map<String, Object> couponHeaderDetails = posReadonlyJdbcTemplate.queryForMap(GET_COUPON_DETAILS_QUERY, campaignId);
			posNPJdbcTemplate.update(LOG_COUPON_CODE_QUERY, couponHeaderDetails);
			params.addValue(TO_DATE, toDate);
			params.addValue(STATUS, couponHeaderDetails.get("Status"));
			params.addValue(MODIFIED_BY, modifiedBy);
			params.addValue(CAMPAIGN_ID, campaignId);
			posNPJdbcTemplate.update(UPDATE_AND_ACTIVATE_COUPON_QUERY, params);
		}
		posNPJdbcTemplate.update(LOG_CAMPAIGN_HEADER_QUERY, headerDetails);
		params = new MapSqlParameterSource();
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
		List<Map<String, Object>> headerDetails = posNPJdbcTemplate.queryForList(GET_CLOSED_CAMPAIGNS_QUERY, new MapSqlParameterSource(TO_DATE, toDate));
		if(UtilValidate.isEmpty(headerDetails))
			return;
		List<Long> templateIds = headerDetails.stream().map(map -> ((Number)(map.get("TemplateID"))).longValue()).collect(Collectors.toList());
		List<Map<String, Object>> templateHeaderDetails=posReadonlyJdbcTemplate.queryForList(GET_CAMPAIGN_TEMPLATE_HEADER_QUERY, templateIds);
		posNPJdbcTemplate.batchUpdate(TEMPLATE_HEADER_LOG_QUERY, templateHeaderDetails.toArray(new Map[templateHeaderDetails.size()]));
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(STATUS, "R");
		params.addValue(MODIFIED_BY, "Auto-Reject Cron");
		params.addValue(TEMPLATE_ID, templateIds);
		posNPJdbcTemplate.update(UPDATE_CAMPAIGN_TEMPLATE_HEADER_QUERY, params);
		log.info("All auto-reject campaigns : {}", headerDetails.stream().map(t -> t.get(CAMPAIGN_ID_FIELD)).collect(Collectors.toList()));
		posNPJdbcTemplate.batchUpdate(LOG_CAMPAIGN_HEADER_QUERY, headerDetails.toArray(new Map[headerDetails.size()]));
		headerDetails.stream().forEach(map -> {
			map.put(CAMPAIGN_ID, map.get(CAMPAIGN_ID_FIELD));
			map.put(APPROVED_BY, "Auto-Reject Cron");
			map.put(STATUS, "R");
			map.put(REMARKS, "Auto-rejected, not approved till end date");
			map.put(EFFECTIVE_DATE, null);
			map.put(DATE_APPROVED, LocalDateTime.now());
		});
		posNPJdbcTemplate.batchUpdate(APPROVE_OR_REJECT_CAMPAIGN_QUERY, headerDetails.toArray(new Map[headerDetails.size()]));
	}

	@Override
	public Map<String, Object> getCampaignHeaderDetails(long campaignId) {
		return posJdbcTemplate.queryForMap(GET_CAMPAIGN_HEADER_QUERY, campaignId);
	}

	@Override
	public PromotionCoupon getPromotionCouponForId(Long campaignId, Integer campaignType) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PROMOTION_ID, campaignId);
		params.addValue(PROMOTION_TYPE, campaignType);
		return posNPJdbcTemplate.query(GET_PROMOTION_COUPON_QUERY, params, new PromotionCouponExtractor());
	}
	
	private static final Set<String> CAMPAIGN_TYPES = new HashSet<>();
	static {
		CAMPAIGN_TYPES.add(PromotionConstants.SPECIAL_DISCOUNT);
		CAMPAIGN_TYPES.add(PromotionConstants.SUPPLIMENT_QUANTITY);
		CAMPAIGN_TYPES.add(PromotionConstants.SUPPLEMENT_ITEM);
		CAMPAIGN_TYPES.add(PromotionConstants.SPECIAL_POINTS);
	}
	@Override
	public boolean isPosChannel(long campaignId) {
	
		return PromotionDaoHelper.isPosChannel(campaignId, CAMPAIGN_TYPES, posNPJdbcTemplate);
	}

	@Override
	public List<String> getStores(Long campaignId) {
		MapSqlParameterSource params = new MapSqlParameterSource(CAMPAIGN_ID,campaignId);
		return posReadonlyNPJdbcTemplate.queryForList(GET_STORES_QUERY, params, String.class);
	}

	@Override
	public List<String> getCouponCodes(Set<String> uniqueCouponCodes) {
		Map<String, Object> params = new HashMap<>();
		params.put("couponCodes", uniqueCouponCodes);

		return posNPJdbcTemplate.queryForList(GET_COUPON_CODES, params).stream()
				.map(row -> (String) row.get("CouponCode")).collect(Collectors.toList());
	}
	
}
