package com.medplus.marketing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.discounts.constants.CouponApplicableMode;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.discounts.domain.Slab;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.dao.RegularPromotionDao;
import com.medplus.marketing.dao.helper.PromotionCouponExtractor;
import com.medplus.marketing.dao.helper.PromotionDaoHelper;
import com.medplus.marketing.dao.helper.RegularPromotionExtractor;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.CategoryDetail;
import com.medplus.marketing.domain.PromotionCoupon;
import com.medplus.marketing.domain.RegularPromotion;
import com.medplus.marketing.domain.SlabGroup;
import com.medplus.marketing.util.CampaignUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class RegularPromotionDaoImpl implements RegularPromotionDao {

	@Autowired
	@Qualifier("posJdbcTemplate")
	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("posReadonlyNPJdbcTemplate") 
	NamedParameterJdbcTemplate posReadonlyNPJdbcTemplate;

	@Autowired
	@Qualifier("posNPJdbcTemplate") 
	NamedParameterJdbcTemplate posNPJdbcTemplate;

	private static final String CHANNEL = "Channel";
	private static final String PROMOTION_ID = "promotionId";
	private static final String APPLICABLE_TYPE = "applicableType";
	private static final String PROMOTION_TYPE = "PromotionType";
	private static final String DISCOUNT_CATEGORY_ID= "DiscCategoryID";
	private static final String TO_DATE = "toDate";
	private static final String SLAB_GROUP_ID = "slabGroupId";
	private static final String STATUS_FEILD = "status";
	private static final String ALL_CUSTOMERS = "allCustomers";
	private static final String MODIFIED_BY = "modifiedBy";
	private static final String PROMOTION_TYPES = "promotionTypes";
	private static final String VALUE = "Value";
	private static final String FROM_DATE = "fromDate";
	private static final String COUPON_BASED = "CouponBased";
	private static final String COUPON_CODE_FEILD = "couponCode";
	private static final String STATUS = "Status";
	private static final String ALL_CUSTOMERS_FIELD = "AllCustomers";
	private static final String VALUE_FLAG = "valueFlag";
	private static final String EFFECTIVE_DATE = "effectiveDate";
	private static final String VALID_FROM = "ValidFrom";
	private static final String VALID_TO = "ValidTo";
	private static final String DATE_APPROVED = "dateApproved";
	private static final String REMARKS = "remarks";
	private static final String AUTO_REJECT_REMARKS = "Auto-rejected, not approved till end date";
	private static final String APPROVED_BY = "approvedBy";
	private static final String REGULAR_PROMOTION_ID = "RegularPromotionId";
	private static final String CUSTOMER_ID = "CustomerId";
	private static final String COUPON_CODE_FLAG = "couponCodeFlag";

	private static final String CHECK_SLABGROUPID_IS_ASSIGNED_QRY = "SELECT COUNT(*) FROM `tbl_regular_promotion` WHERE `SlabGroupId`=:slabGroupId";
	
	private static final String GET_EXISTING_SLAB_GROUPS = "SELECT `SlabGroupId`,`Name` FROM `tbl_slab_group` ORDER BY `Name`";
	
	private static final String GET_INVOICE_CATEGORY_TYPES = "SELECT InvoiceCategoryTypeId,Name FROM tbl_product_invoice_category_type ORDER BY Name";
	
	private static final String GET_SLAB_INFO_FOR_SLABGROUPID = "SELECT a.`SlabID`,a.`FromValue`,a.`ToValue` FROM `tbl_discount_slab` a INNER JOIN tbl_slab_group_items b ON a.SlabID=b.SlabID WHERE b.`SlabGroupId` = :slabGroupId ORDER BY a.`FromValue`";
	
	private static final String UPDATE_SLAB_GROUP_QRY = "UPDATE `tbl_slab_group` SET `ModifiedBy`= ?, `DateModified`= ? WHERE `SlabGroupId`=?";
	
	private static final String GET_SLAB_ID_QRY = "SELECT `SlabID` FROM `tbl_discount_slab` WHERE `FromValue`=? AND `ToValue`=?";
	
	private static final String INSERT_SLAB_GROUP_ITEMS_QRY = "INSERT INTO `tbl_slab_group_items`(`SlabGroupId`,`SlabId`) VALUES(?,?)";
	
	private static final String INSERT_SLAB_RANGE_QRY = "INSERT INTO `tbl_discount_slab`(`Name`,`FromValue`,`ToValue`,`Status`,`CreatedBy`,`DateCreated`) VALUES(?,?,?,?,?,?)";
	
	private static final String LOG_SLAB_GRP_ITEM_QREY = "INSERT INTO `tbl_slab_group_items_log` (`SlabGroupId`, `SlabId`) SELECT `SlabGroupId`, `SlabId` FROM `tbl_slab_group_items` WHERE `SlabGroupId` = ?";
	
	private static final String DELETE_SLAB_GRP_ITEM_QRY = "DELETE FROM `tbl_slab_group_items` WHERE `SlabGroupId` = ?";
	
	private static final String INSERT_SLAB_GRP_QRY = "INSERT INTO `tbl_slab_group`(`Name`,`CreatedBy`,`DateCreated`) VALUES(?,?,?)";
	
	private static final String INSERT_REGULAR_PROMO_DETAILS_QRY = "INSERT INTO `tbl_regular_promotion`(`Name`,`ValidFrom`,`ValidTo`,`Status`,`PromotionLevel`,ApplicableType,`CouponBased`,`AllCustomers`,`CreatedBy`,`DateCreated`,`SlabGroupId`,`CloneReferenceId`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String INSERT_INTO_PROMO_CHANNEL_MAPPING_QRY = "INSERT INTO `tbl_promotion_channel_mapping` (`PromotionId`, `ApplicableType`, `Channel`, `PromotionType`, `CreatedBy`) VALUES (?,?,?,?,?)";
	
	private static final String INSERT_INTO_PROMO_META_INFO_MAPPING_QRY = "INSERT INTO `tbl_promotion_metainfo_mapping` (`PromotionId`, `ApplicableType`, `PromotionType`,`Claimable`, `SpecialtyBased`, `Description`, `ImagePath`, `ImageServerName`, `PromotionVisible`, `CreatedBy`,`DateCreated`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,now())";
	
	private static final String INSERT_INTO_DISC_SLAB_CATEGORY_MAPPING_QRY = "INSERT INTO `tbl_discount_slab_category_mapping`(`PromotionId`,`SlabID`,`DiscCategoryID`,`PaybackPercentage`,`DiscPercentage`,`ValidFrom`,`ValidTo`,`Status`,`CouponBased`,`AllCustomers`,`CreatedBy`,`DateCreated`) VALUES(?,?,?,?,?,?,?,?,?,?,?,now())";
	
	private static final String INSERT_REGULAR_PROMO_CUSTOMERS_QRY = "INSERT INTO `tbl_regular_promotion_customer`(`RegularPromotionId`,`CustomerId`,`CouponCode`,`CreatedBy`,`DateCreated`) VALUES(?,?,?,?,now())";
	
	private static final String INSERT_PROMO_COUPON_DETAILS_QRY = "INSERT INTO `tbl_coupon_code`(`CouponCode`,`PromotionId`,`PromotionType`,`ApplicableType`,`AllCustomers`,`TotalLimit`,CustomerLimit,`FromDate`,`ToDate`,`Status`,`MinValue`,`MaxDiscount`,`MaxPoints`,`AddonCoupon`, `NoOfDays`, `CouponDiscountType`, `CreatedBy`,`DateCreated`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
	
	private static final String INSERT_INTO_TOTAL_COUPON_USED_QRY = "INSERT INTO `tbl_total_coupon_used` (`CouponCode`, `TotalUsed`) VALUES (?, ?)";
	
	private static final String GET_REG_PROMO_DETAILS = "SELECT `PromotionId`,`Name`,`ValidFrom`,`ValidTo`,`Status`,`PromotionLevel`,`CouponBased`,`AllCustomers`,`CreatedBy`,`DateCreated`,`SlabGroupId`,`CloneReferenceId`,`ModifiedBy`,`DateModified`,`ApplicableType`,`ApprovedBy`,`DateApproved`,`Remarks`,`EffectiveDate` FROM `tbl_regular_promotion` WHERE `PromotionId`=:promotionId";
	
	private static final String REGULAR_PROMO_DETAILS_LOG_QRY = "INSERT INTO `tbl_regular_promotion_log`(`PromotionId`,`Name`,`ValidFrom`,`ValidTo`,`Status`,`PromotionLevel`,`CouponBased`,`AllCustomers`,`CreatedBy`,`DateCreated`,`SlabGroupId`,`CloneReferenceId`,`ModifiedBy`,`DateModified`,`ApplicableType`,`ApprovedBy`,`DateApproved`,`Remarks`,`EffectiveDate`) "
			+ " VALUES (:PromotionId,:Name,:ValidFrom,:ValidTo,:Status,:PromotionLevel,:CouponBased,:AllCustomers,:CreatedBy,:DateCreated,:SlabGroupId,:CloneReferenceId,:ModifiedBy,:DateModified,:ApplicableType,:ApprovedBy,:DateApproved,:Remarks,:EffectiveDate)";
	
	private static final String UPDATE_REGULAR_PROMO_DETAILS_QRY = "UPDATE `tbl_regular_promotion` SET `ValidTo`=:toDate, `PromotionLevel`=:promotionLevel,`CouponBased`=:couponBased, `AllCustomers`=:allCustomers,`SlabGroupId`=:slabGroupId,`ModifiedBy`=:modifiedBy,`DateModified`=now() where `PromotionId`=:promotionId";
	
	private static final String LOG_PROMO_CHANNEL_MAP_QRY = "INSERT INTO `tbl_promotion_channel_mapping_log` (`PromotionId`, `ApplicableType`, `Channel`, `PromotionType`, `CreatedBy`) VALUES (:PromotionId,:ApplicableType, :Channel, :PromotionType, CreatedBy)";
	
	private static final String GET_PROMO_CHANNEL_MAP_QRY = "SELECT `PromotionId`, `ApplicableType`, `Channel`, `PromotionType`, `CreatedBy` from tbl_promotion_channel_mapping where `PromotionId`=:promotionId AND `ApplicableType`=:applicableType AND `PromotionType` IN (:promotionTypes) AND Channel in (:channels)";
	
	private static final String DELETE_PROMO_CHANNEL_MAP_QRY = "DELETE FROM `tbl_promotion_channel_mapping` where `PromotionId`=:promotionId AND `ApplicableType`=:applicableType AND `PromotionType` IN (:promotionTypes)  AND Channel in (:channels)";
	
	private static final String LOG_PROMO_METAINFO_MAP_QRY = "INSERT INTO `tbl_promotion_metainfo_mapping_log` (`ID`,`PromotionId`, `ApplicableType`,`Claimable`, `SpecialtyBased`, `PromotionType`, `Description`, `ImagePath`, `PromotionVisible`, `CreatedBy`,`DateCreated`) VALUES "
			+ " (:ID, :PromotionId, :ApplicableType, :Claimable, :SpecialtyBased, :PromotionType, :Description, :ImagePath, :PromotionVisible, :CreatedBy, :DateCreated)";
	
	private static final String GET_PROMO_METAINFO_QRY = " SELECT `ID`, `PromotionId`, `ApplicableType`,`Claimable`, `SpecialtyBased`, `PromotionType`, `Description`, `ImagePath`, `PromotionVisible`, `CreatedBy`,`DateCreated` from `tbl_promotion_metainfo_mapping` where `PromotionId`=:promotionId AND `ApplicableType`=:applicableType AND `PromotionType`=:PromotionType";
	
	private static final String DELETE_PROMO_METAINFO_MAP_QRY = "DELETE FROM `tbl_promotion_metainfo_mapping` where `PromotionId`=:promotionId AND `ApplicableType`=:applicableType AND `PromotionType`=:PromotionType";
	
	private static final String UPDATE_AND_APPROVE_REGULAR_PROMO_QRY = "UPDATE tbl_regular_promotion SET Status=:Status, EffectiveDate = :effectiveDate, ValidTo=:toDate, ApprovedBy=:approvedBy, DateApproved=:dateApproved,ModifiedBy=:approvedBy,DateModified=now() WHERE PromotionId in (:promotionId)";
	
	private static final String APPROVE_REJECT_REGULAR_PROMO_QRY = "UPDATE tbl_regular_promotion SET Status=:Status, EffectiveDate = :effectiveDate, Remarks=:remarks, ApprovedBy=:approvedBy, DateApproved=:dateApproved WHERE PromotionId in (:promotionId)";
	
	private static final String UPDATE_ACTIVE_PROMO = "UPDATE tbl_regular_promotion SET ValidTo = :toDate, ModifiedBy = :modifiedBy, DateModified = now() WHERE PromotionId=:promotionId";
	
	private static final String INSERT_DISCOUNT_CATEGORY_QRY = "INSERT INTO `tbl_discount_category`(`ProductDiscountCategoryID`,`LoyaltyType`,`DiscountType`,`Region`,`ApplicableType`,`PromotionLevel`,`Status`,`CreatedBy`,`DateCreated`) VALUES(?,?,?,?,?,?,?,?,?)";
	
	private static final String GET_DISCOUNT_CATEGORY_ID_QRY = "SELECT `DiscCategoryID` FROM `tbl_discount_category` WHERE `ProductDiscountCategoryID`=? AND `LoyaltyType`=? AND `DiscountType`=? AND `Region`=? AND `PromotionLevel`=? AND ApplicableType=?";
	
	private static final String GET_REGULAR_PROMO_LIST = "SELECT SQL_CALC_FOUND_ROWS count(a.PromotionId) over() as TotalRowCount, a.PromotionId,a.Name,DATE_FORMAT(a.ValidFrom,'%Y-%m-%d %H:%i:%s') AS ValidFrom,"
			+ " DATE_FORMAT(a.ValidTo,'%Y-%m-%d %H:%i:%s') AS ValidTo, DATE_FORMAT(a.EffectiveDate,'%Y-%m-%d %H:%i:%s') AS EffectiveDate, a.Status,a.PromotionLevel,a.CouponBased,a.AllCustomers,a.CreatedBy,"
			+ " DATE_FORMAT(a.DateCreated,'%Y-%m-%d %H:%i:%s') AS DateCreated,a.ApprovedBy, DATE_FORMAT(a.DateApproved,'%Y-%m-%d %H:%i:%s') AS DateApproved,a.CloneReferenceId,a.ApplicableType, group_concat(DISTINCT p.Channel) as Channels"
			+ " FROM tbl_regular_promotion a LEFT JOIN tbl_promotion_channel_mapping p ON a.PromotionId = p.PromotionId AND p.PromotionType in (-1,1,2)"
			+ " left join tbl_coupon_code cc on cc.PromotionId=a.PromotionId and cc.PromotionType=p.PromotionType WHERE"
			+ " (1=:promotionIdFlag or a.PromotionId=:promotionId) and (1=:nameFlag or a.Name like :promotionName) and a.ApplicableType in (:applicableType) "
			+ " and (1=:channelFlag or p.Channel in (:channel)) and (1=:statusFlag or a.Status=:status) and (1=:createdByFlag or a.CreatedBy=:createdBy) "
			+ " and (1=:dateFlag or a.DateCreated between :fromDate and :toDate) and (1=:closedFlag or a.ValidTo < now()) and p.PromotionType in (-1,1,2) "
			+ " and (1=:couponCodeFlag or cc.CouponCode=:couponCode) and (1=:promotionLevelFlag or a.PromotionLevel=:promotionLevel) group by PromotionId order by PromotionId desc limit :limit offset :offset";

	private static final String GET_REG_PROMO_DETAILS_BY_PROMO_IDQRY = "SELECT a.PromotionId,a.Name,DATE_FORMAT(a.ValidFrom,'%Y-%m-%d %H:%i:%s') AS ValidFrom, DATE_FORMAT(a.EffectiveDate,'%Y-%m-%d %H:%i:%s') AS EffectiveDate,"
			+ " DATE_FORMAT(a.ValidTo,'%Y-%m-%d %H:%i:%s') AS ValidTo,a.Status,a.CouponBased,a.AllCustomers,a.CreatedBy,a.DateCreated,a.ModifiedBy,a.DateModified,a.PromotionLevel,a.ApplicableType,a.Remarks,"
			+ " a.SlabGroupId,b.SlabID,b.DiscCategoryID,b.PaybackPercentage,b.DiscPercentage,c.ProductDiscountCategoryID,"
			+ " c.LoyaltyType,c.DiscountType,c.Region,ifnull(d.Description,'No data Available') as Description,"
			+ " d.ImagePath, d.ImageServerName,d.PromotionVisible FROM tbl_regular_promotion a INNER JOIN tbl_discount_slab_category_mapping b ON "
			+ " a.PromotionId=b.PromotionId INNER JOIN tbl_discount_category c ON b.DiscCategoryID=c.DiscCategoryID "
			+ " LEFT JOIN tbl_promotion_metainfo_mapping d ON a.PromotionId = d.PromotionId WHERE a.PromotionId=:promotionId";

	private static final String GET_PROMOTION_DETAILS_QRY = "SELECT Id,CouponCode,PromotionId,PromotionType,ApplicableType,"
			+ " AllCustomers,TotalLimit,CustomerLimit,DATE_FORMAT(FromDate,'%Y-%m-%d %H:%i:%s') AS FromDate,DATE_FORMAT(ToDate,'%Y-%m-%d %H:%i:%s') AS ToDate,"
			+ " Status,MinValue,AddonCoupon,MaxDiscount,MaxPoints,NoOfDays,CouponDiscountType,CreatedBy,DateCreated,ModifiedBy,DateModified "
			+ " FROM tbl_coupon_code WHERE PromotionId=:promotionId AND PromotionType=:promotionType AND (1=:couponCodeFlag or CouponCode=:couponCode)";
	
	private static final String GET_PROMOTION_TYPE_AND_CHANNELS = "select Channel,PromotionType from tbl_promotion_channel_mapping where PromotionId=:promotionId and ApplicableType=:applicableType and PromotionType IN (:promotionTypes)";
	
	private static final String GET_COUPON_APPLICABLE_MODE_MAPPING = "SELECT `PromotionId`, `PromotionType`, `Mode`, `Value` from tbl_coupon_applicable_mode_mapping where `PromotionId`=:promotionId AND `PromotionType` IN (:promotionTypes) AND (1=:valueFlag or Value in (:Value))";
	
	private static final String DELETE_DISCOUNT_SLAB_CATEGORY_MAPPING_QRY = "DELETE FROM `tbl_discount_slab_category_mapping` WHERE `PromotionId`=:promotionId";
	
	private static final String INSERT_DISCOUNT_CATEGOR_MAPPING_LOG_QRY = "INSERT INTO `tbl_discount_slab_category_mapping_log`(`ID`,`PromotionId`,`SlabID`,`DiscCategoryID`,`PaybackPercentage`,"
			+ "`DiscPercentage`,`ValidFrom`,`ValidTo`,`Status`,`CouponBased`,`AllCustomers`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) SELECT `ID`,`PromotionId`,`SlabID`,`DiscCategoryID`,"
			+ "`paybackPercentage`,`DiscPercentage`,`ValidFrom`,`ValidTo`,`Status`,`CouponBased`,`AllCustomers`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified` FROM `tbl_discount_slab_category_mapping`"
			+ " WHERE `PromotionId`=:promotionId";
	
	private static final String GET_COUPON_DETAILS = "SELECT `Id`,`CouponCode`,`PromotionId`,`PromotionType`,`ApplicableType`,`AllCustomers`,`TotalLimit`,`CustomerLimit`,`FromDate`,`ToDate`,`Status`,`MinValue`,`MaxDiscount`,`MaxPoints`,`AddonCoupon`,`NoOfDays`, `CouponDiscountType`, `CreatedBy`,`DateCreated`,`ModifiedBy`,"
			+ " `DateModified` FROM tbl_coupon_code WHERE PromotionId=:promotionId AND PromotionType=:PromotionType";
	
	private static final String INSERT_COUPON_DETAILS_LOG_QRY = "INSERT INTO `tbl_coupon_code_log`(`Id`,`CouponCode`,`PromotionId`,`PromotionType`,`ApplicableType`,`AllCustomers`,`TotalLimit`,CustomerLimit,`FromDate`,`ToDate`,`Status`,`MinValue`,`MaxDiscount`,`MaxPoints`,`AddonCoupon`,`NoOfDays`, `CouponDiscountType`,"
			+ " `CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) VALUES (:Id, :CouponCode, :PromotionId, :PromotionType, :ApplicableType, :AllCustomers, :TotalLimit, :CustomerLimit, :FromDate, :ToDate, :Status, :MinValue, :MaxDiscount, :MaxPoints, :AddonCoupon, :NoOfDays, :CouponDiscountType, :CreatedBy, :DateCreated, :ModifiedBy, :DateModified)";
	
	private static final String UPDATE_COUPON_DETAILS = "UPDATE tbl_coupon_code SET PromotionType=:promotionType, ApplicableType=:applicableType, AllCustomers=:allCustomers, TotalLimit=:totalLimit, CustomerLimit=:customerLimit, FromDate=:fromDate, ToDate=:toDate, "
			+ " Status=:status, MinValue=:minValue, MaxDiscount=:maxDiscount, MaxPoints=:maxPoints, AddonCoupon=:addonCoupon, NoOfDays=:noOfDays, CouponDiscountType=:couponDiscountType, ModifiedBy=:modifiedBy, DateModified=now() WHERE PromotionId=:promotionId AND CouponCode=:couponCode";
	
	private static final String DELETE_COUPON_DETAILS = "DELETE FROM `tbl_coupon_code` WHERE `PromotionId`=:promotionId AND `PromotionType`=:promotionTypes";
	private static final String DELETE_TOTAL_COUPON_USED = "DELETE FROM tbl_total_coupon_used where CouponCode IN(SELECT CouponCode FROM `tbl_coupon_code` WHERE `PromotionId`=:promotionId AND `PromotionType`=:promotionTypes)";
	
	private static final String GET_ALL_REGULAR_PROMOTION_CUSTOMERS = "SELECT RegularPromotionId, CustomerId, CouponCode, CreatedBy, DateCreated FROM tbl_regular_promotion_customer WHERE RegularPromotionId=:RegularPromotionId AND (1=:couponCodeFlag or CouponCode=:couponCode)";
	
	private static final String REGULAR_PROMOTION_CUSTOMER_LOG_QRY = "insert into tbl_regular_promotion_customer_log (`RegularPromotionId`, `CustomerId`, `CouponCode`, `DateCreated`, `CreatedBy`) values (:RegularPromotionId, :CustomerId, :CouponCode, :DateCreated, :CreatedBy)";
	
	private static final String GET_CUSTOMER_IDS_FOR_REGULAR_PROMOTION = "SELECT CustomerId FROM tbl_regular_promotion_customer WHERE RegularPromotionId=:promotionId AND CouponCode=:couponCode";
	
	private static final String UPDATE_TO_DATE_IN_DISCOUNT_CATEGORY_MAPPING = "UPDATE `tbl_discount_slab_category_mapping` SET ValidTo=:toDate, Status=:Status, ModifiedBy=:modifiedBy, DateModified=now() WHERE `PromotionId` in (:promotionId)";
	
	private static final String UPDATE_TO_DATE_IN_PROMOTION_COUPON = "UPDATE `tbl_coupon_code` SET ToDate=:toDate, Status=:Status, `ModifiedBy`=:modifiedBy, `DateModified`=now() WHERE PromotionId in (:promotionId) AND PromotionType=:PromotionType";
	
	private static final String REGULAR_PROMOTINO_NAME_AVAILABILITY_QUERY = "SELECT COUNT(PromotionId) FROM tbl_regular_promotion WHERE Name=:PromotionName";
	
	private static final String REGULAR_PROMOTINO_COUPO_CODE_AVAILABILITY_QUERY = "SELECT COUNT(PromotionId) FROM tbl_coupon_code WHERE CouponCode=:couponCode";
	
	private static final String GET_AND_LOG_DISC_SLAB_CATEGORY_MAPPING = "INSERT INTO `tbl_discount_slab_category_mapping_log`(`ID`,`PromotionId`,`SlabID`,`DiscCategoryID`, "
			+ "`PaybackPercentage`,`DiscPercentage`,`ValidFrom`,`ValidTo`,`Status`,`CouponBased`,`AllCustomers`,`CreatedBy`, "
			+ "`DateCreated`,`ModifiedBy`,`DateModified`) SELECT `ID`,`PromotionId`,`SlabID`,`DiscCategoryID`,`PaybackPercentage`, "
			+ "`DiscPercentage`,`ValidFrom`,`ValidTo`,`Status`,`CouponBased`,`AllCustomers`,`CreatedBy`,`DateCreated`,`ModifiedBy`, "
			+ "`DateModified` FROM `tbl_discount_slab_category_mapping` WHERE `PromotionId`in (:promotionId)";
	
	private static final String GET_AND_LOG_PROMOTION_COUPON_DETAILS = "INSERT INTO `tbl_coupon_code_log`(`Id`,`CouponCode`,`PromotionId`, "
			+ "`PromotionType`,`ApplicableType`,`AllCustomers`,`TotalLimit`,CustomerLimit, "
			+ "`FromDate`,`ToDate`,`Status`,`MinValue`,`MaxDiscount`,`MaxPoints`,`AddonCoupon`, "
			+ "`NoOfDays`, `CouponDiscountType`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) "
			+ "SELECT `Id`,`CouponCode`,`PromotionId`,`PromotionType`,`ApplicableType`,`AllCustomers`, "
			+ "`TotalLimit`,`CustomerLimit`,`FromDate`,`ToDate`,`Status`,`MinValue`,`MaxDiscount`, "
			+ "`MaxPoints`,`AddonCoupon`,`NoOfDays`, `CouponDiscountType`, `CreatedBy`,`DateCreated`, "
			+ "`ModifiedBy`,`DateModified` FROM tbl_coupon_code WHERE PromotionId in (:promotionId) AND PromotionType=:PromotionType";
	
	private static final String GET_CLOSED_REGULAR_PROMOTION_HEADER_QUERY = "SELECT `PromotionId`,`Name`,`ValidFrom`,`ValidTo`,`Status`,`PromotionLevel`,"
			+ "`CouponBased`,`AllCustomers`,`CreatedBy`,`DateCreated`,`SlabGroupId`,`CloneReferenceId`,`ModifiedBy`,`DateModified`,`ApplicableType`,"
			+ "`ApprovedBy`,`DateApproved`,`Remarks`,`EffectiveDate` FROM `tbl_regular_promotion` WHERE ValidTo < :toDate and Status='I'";
	
	private static final String DELETE_CUSTOMERS_FOR_REGULAR_PROMOTION = "DELETE FROM tbl_regular_promotion_customer WHERE RegularPromotionId=:RegularPromotionId AND CustomerId in (:CustomerId)";
	
	private static final String GET_AND_LOG_REGULAR_PROMOTION_CUSTOMERIDS = "INSERT INTO tbl_regular_promotion_customer_log (RegularPromotionId, CustomerId, CouponCode, CreatedBy, "
			+ " DateCreated) SELECT RegularPromotionId, CustomerId, CouponCode, CreatedBy, DateCreated FROM tbl_regular_promotion_customer"
			+ " WHERE RegularPromotionId=:RegularPromotionId AND (1=:couponCodeFlag OR CouponCode=:couponCode) AND CustomerId in (:CustomerId)";
	
	private static final String GET_STORES_QUERY = "SELECT c.Region FROM tbl_regular_promotion a "
			+ "INNER JOIN tbl_discount_slab_category_mapping b ON a.PromotionId = b.PromotionId "
			+ "INNER JOIN tbl_discount_category c ON b.DiscCategoryID = c.DiscCategoryID WHERE a.PromotionId = :promotionId and LENGTH(c.Region) = 12 and c.Status = 'A'";

	@Override
	public int checkSlabGroupIsAssignedToPromotionOrNot(Long slabGroupId) {
		if(UtilValidate.isNotEmpty(slabGroupId)) {
			MapSqlParameterSource params = new MapSqlParameterSource(SLAB_GROUP_ID, slabGroupId);
			Integer res = posReadonlyNPJdbcTemplate.queryForObject(CHECK_SLABGROUPID_IS_ASSIGNED_QRY, params, Integer.class);
			return UtilValidate.isNotEmpty(res) ? res : 0;
		}
		return 0;
	}

	@Override
	public Map<String,String> getExisitingSlabGroups() {
		return posReadonlyNPJdbcTemplate.query(GET_EXISTING_SLAB_GROUPS, rs -> {
			Map<String, String> existingSlabGroups = new LinkedHashMap<>();
			while(rs.next()){
				existingSlabGroups.put(rs.getString(SLAB_GROUP_ID), rs.getString("Name"));
			}
			return existingSlabGroups;
		});
	}

	@Override
	public Map<String,String> getInvoiceCategoryTypes() {
		return posReadonlyNPJdbcTemplate.query(GET_INVOICE_CATEGORY_TYPES, rs -> {
			Map<String, String> invoiceCategoryTypes = new LinkedHashMap<>();
			while(rs.next()){
				invoiceCategoryTypes.put(rs.getString("InvoiceCategoryTypeId"), rs.getString("Name"));
			}
			return invoiceCategoryTypes;
		});
	}

	@Override
	public List<Slab> getSlabsInfoForSlabGroup(Long slabGroupId){
		if(UtilValidate.isNotEmpty(slabGroupId) && slabGroupId > 0){
			MapSqlParameterSource params = new MapSqlParameterSource(SLAB_GROUP_ID, slabGroupId);
			return posReadonlyNPJdbcTemplate.query(GET_SLAB_INFO_FOR_SLABGROUPID, params, new RowMapper<Slab>() {

				@Override
				public Slab mapRow(ResultSet rs, int rowNum) throws SQLException {
					Slab slab = new Slab();
					slab.setFromValue(rs.getDouble("FromValue"));
					slab.setToValue(rs.getDouble("ToValue"));
					slab.setSlabId(rs.getLong("SlabID"));
					return slab;
				}

			});
		}
		return Collections.emptyList();
	}

	@Override
	public SlabGroup insertSlabsAndSlabGroups(SlabGroup slabGroup) {
		if(UtilValidate.isNotEmpty(slabGroup) && UtilValidate.isNotEmpty(slabGroup.getNewSlabGroupName()) && slabGroup.getSlabGroupId() == null){
			int[] createTypes = {Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP};
			PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(INSERT_SLAB_GRP_QRY,createTypes);
			pscf.setReturnGeneratedKeys(true);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			try{
				jdbcTemplate.update(pscf.newPreparedStatementCreator(new Object[]{slabGroup.getNewSlabGroupName(),slabGroup.getCreatedBy(), LocalDateTime.now()}),keyHolder);
			}catch(DuplicateKeyException e){
				throw new PromotionException("Slab Group Name is Already Exists: "+ slabGroup.getNewSlabGroupName());
			}
			slabGroup.setSlabGroupId(keyHolder.getKey().longValue());
			saveSlabgroupItems(slabGroup);
		}
		return slabGroup;
	}

	@Override
	@Transactional("posTransactionManager")
	public SlabGroup updateSlabGroup(SlabGroup slabGroup) {

		if(checkSlabGroupIsAssignedToPromotionOrNot(slabGroup.getSlabGroupId()) > 0)
			throw new PromotionException("Edit is not possible for this Slab Group : "+slabGroup.getSlabGroupId()+", this already assigned to promotion!");
		if(UtilValidate.isNotEmpty(slabGroup.getSlabGroupId()) && UtilValidate.isNotEmpty(slabGroup.getSlabs()) && 
				(jdbcTemplate.update(UPDATE_SLAB_GROUP_QRY, slabGroup.getCreatedBy(), slabGroup.getDateCreated(), slabGroup.getSlabGroupId()) > 0)){
			logAndDeleteSlabGroupItems(slabGroup);
			saveSlabgroupItems(slabGroup);
		}
		return slabGroup;
	}

	private void logAndDeleteSlabGroupItems(SlabGroup slabGroup) {

		jdbcTemplate.update(LOG_SLAB_GRP_ITEM_QREY, slabGroup.getSlabGroupId());
		jdbcTemplate.update(DELETE_SLAB_GRP_ITEM_QRY, slabGroup.getSlabGroupId());
	}

	private void saveSlabgroupItems(SlabGroup slabGroup){

		if(UtilValidate.isNotEmpty(slabGroup.getSlabs())){
			for(Slab slab : slabGroup.getSlabs()){
				try{
					Long slabId = jdbcTemplate.queryForObject(GET_SLAB_ID_QRY, Long.class,slab.getFromValue(),slab.getToValue());
					slab.setSlabId(UtilValidate.isNotEmpty(slabId) ? slabId: 0L);
				}catch(DataAccessException e){
					int[] createTypes = {Types.VARCHAR,Types.DECIMAL,Types.DECIMAL,Types.CHAR,Types.VARCHAR,Types.TIMESTAMP};
					PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(INSERT_SLAB_RANGE_QRY,createTypes);
					pscf.setReturnGeneratedKeys(true);
					KeyHolder keyHolder = new GeneratedKeyHolder();
					jdbcTemplate.update(pscf.newPreparedStatementCreator(new Object[]{"Slab from "+slab.getFromValue() +" to "+slab.getToValue(),slab.getFromValue(),slab.getToValue(),'A',slabGroup.getCreatedBy(), LocalDateTime.now()}),keyHolder);
					slab.setSlabId(keyHolder.getKey().longValue());
				}

				if(UtilValidate.isEmpty(slab.getSlabId()))
					throw new PromotionException("Slab Creation Faild for the slab From value : "+slab.getFromValue() + " To Value : "+slab.getToValue());
				try{
					jdbcTemplate.update(INSERT_SLAB_GROUP_ITEMS_QRY, slabGroup.getSlabGroupId(),slab.getSlabId());
				}catch(DuplicateKeyException dke){
					log.info("Already current slabgroupid and slabid inserted");
				}
			}
		}
	}


	@Override
	public void findSlabIds(SlabGroup slabGroup) {
		if(UtilValidate.isNotEmpty(slabGroup) && UtilValidate.isNotEmpty(slabGroup.getSlabs())){
			for(Slab slab : slabGroup.getSlabs()){
				try{
					Long slabId = jdbcTemplate.queryForObject(GET_SLAB_ID_QRY,Long.class,slab.getFromValue(),slab.getToValue());
					slab.setSlabId(UtilValidate.isNotEmpty(slabId) ? slabId : 0L);
				}catch(DataAccessException e){
					throw new PromotionException("Slab not found for From Value : "+slab.getFromValue()+" To Value : "+slab.getToValue());
				}
			}
		}
	}

	private int savePromotionChannelMapping(long promotionId, int appllicableType, List<Integer> channels, String createdBy, int type){
		int insertedRows = 0;
		if(UtilValidate.isNotEmpty(channels)){
			for(Integer eachChannel : channels){
				insertedRows = insertedRows + jdbcTemplate.update(INSERT_INTO_PROMO_CHANNEL_MAPPING_QRY, promotionId, appllicableType, eachChannel, type, createdBy);
			}
		}
		return insertedRows;
	}

	public void insertDiscountCategorySlabMapping(RegularPromotion regularPromotion){
		if(UtilValidate.isNotEmpty(regularPromotion)) {
			UserMetaData metaData = regularPromotion.getUserMetaData();
			if(UtilValidate.isNotEmpty(regularPromotion.getCategoryDetail()) && UtilValidate.isNotEmpty(metaData)){
				for(CategoryDetail categoryDetail : regularPromotion.getCategoryDetail()){
					jdbcTemplate.update(INSERT_INTO_DISC_SLAB_CATEGORY_MAPPING_QRY , regularPromotion.getPromotionId(),
							categoryDetail.getSlabId(), categoryDetail.getDiscountCategoryId(), categoryDetail.getPaybackPercentage(),
							categoryDetail.getDiscountPercentage(), regularPromotion.getFromDate(), regularPromotion.getToDate(),
							regularPromotion.getStatus(), regularPromotion.isCouponBased() ? "Y" : "N", regularPromotion.isAllCustomers() ? "Y" : "N",
							metaData.getCreatedBy());
				}
			}
		}
	}

	@Override
	public void savePromotionMetainfoDetails(long promotionId, int applicableType, Boolean specialtyBased, int promotionType, UserMetaData metaInfo){
		int[] types = {Types.INTEGER, Types.INTEGER, Types.INTEGER,Types.BOOLEAN, Types.BOOLEAN, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.CHAR, Types.VARCHAR};
		PreparedStatementCreatorFactory psc = new PreparedStatementCreatorFactory(INSERT_INTO_PROMO_META_INFO_MAPPING_QRY, types);
		try{
			jdbcTemplate.update(psc.newPreparedStatementCreator(new Object[]{promotionId, applicableType, promotionType, metaInfo.isClaimable(), specialtyBased, metaInfo.getLongDescription(), metaInfo.getImagePath(), metaInfo.getImageServerName(), metaInfo.isPromotionVisible() ? 'Y':'N', metaInfo.getCreatedBy()}));
		}catch(DuplicateKeyException e){
			throw new PromotionException("Promotion Mapping Alrady Exists!");
		}catch(Exception e){
			log.error("Exception in Dao "+e.getMessage());
			throw new PromotionException("Unable to Save meta data for the promotion");
		}
	}

	@Override
	public void saveRegularPromotionCustomerDetails(RegularPromotion regularPromotion){
		if(UtilValidate.isNotEmpty(regularPromotion) && UtilValidate.isNotEmpty(regularPromotion.getUserMetaData())){
			String couponCode = regularPromotion.getPromotionCoupon() != null ? regularPromotion.getPromotionCoupon().getCouponCode().toUpperCase() : null;
			for(Long customerId:regularPromotion.getCustomerIds()){
				try{
					jdbcTemplate.update(INSERT_REGULAR_PROMO_CUSTOMERS_QRY, regularPromotion.getPromotionId(),customerId,couponCode,regularPromotion.getUserMetaData().getCreatedBy());
				}catch(DuplicateKeyException e){
					log.info("Duplicate customerID: {}",customerId);
				}
			}
		}
	}

	@Override
	public void savePromotionCouponDetails(PromotionCoupon promotionCoupon){
		log.info("promotionCoupon : {}",promotionCoupon);
		if(UtilValidate.isNotEmpty(promotionCoupon)){
			log.info("promotionCoupon PromotionId : {}",promotionCoupon.getPromotionId());
			try{
				jdbcTemplate.update(INSERT_PROMO_COUPON_DETAILS_QRY, promotionCoupon.getCouponCode().toUpperCase(),
						promotionCoupon.getPromotionId(),promotionCoupon.getPromotionType(),promotionCoupon.getApplicableType(),
						promotionCoupon.isAllCustomers() ? "Y" : "N",promotionCoupon.getTotalLimit(),promotionCoupon.getCustomerLimit(),
						promotionCoupon.getFromDate(),promotionCoupon.getToDate(), PromotionConstants.INACTIVE,
						promotionCoupon.getMinValue(),promotionCoupon.getMaxDiscount(),promotionCoupon.getMaxPoints(),
						promotionCoupon.isAddOnCoupon(),promotionCoupon.getNoOfDays(), promotionCoupon.getCouponDiscountType() , 
						promotionCoupon.getCreatedBy());
			}catch(DuplicateKeyException e){
				throw new PromotionException("Couponcode Already Exists! Please Change Name");
			}
			jdbcTemplate.update(INSERT_INTO_TOTAL_COUPON_USED_QRY , promotionCoupon.getCouponCode().toUpperCase(), 0);
		}
	}

	private void insertRegularPromotionDetails(RegularPromotion regularPromotion){
		if(UtilValidate.isNotEmpty(regularPromotion)) {
			UserMetaData metaData = regularPromotion.getUserMetaData();
			if(UtilValidate.isNotEmpty(metaData)) {
				int[] createTypes = {Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP,Types.CHAR,Types.CHAR,Types.INTEGER,Types.CHAR,Types.CHAR,Types.VARCHAR,Types.TIMESTAMP,Types.INTEGER,Types.INTEGER};
				PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(INSERT_REGULAR_PROMO_DETAILS_QRY,createTypes);
				pscf.setReturnGeneratedKeys(true);
				KeyHolder keyHolder = new GeneratedKeyHolder();
				try{
					jdbcTemplate.update(pscf.newPreparedStatementCreator(new Object[]{regularPromotion.getPromotionName(),regularPromotion.getFromDate(),regularPromotion.getToDate(),PromotionConstants.INACTIVE,regularPromotion.getPromotionLevel(),regularPromotion.getApplicableType(),regularPromotion.isCouponBased() ? "Y" : "N",regularPromotion.isAllCustomers() ? "Y":"N",metaData.getCreatedBy(), LocalDateTime.now(), regularPromotion.getSlabGroupId(),regularPromotion.getCloneReferenceId()}),keyHolder);
				}catch(DuplicateKeyException e){
					throw new PromotionException("Promotion Name Already Exists! Please Change Name");
				}
				regularPromotion.setPromotionId(keyHolder.getKey().longValue());
				log.info("The generated promotion id is : {}",regularPromotion.getPromotionId());
			}
		}
	}

	@Override
	@Transactional("posTransactionManager")
	public RegularPromotion insertPromotionDetailsAndDiscategorySlabMapping(RegularPromotion regularPromotion){
		if(UtilValidate.isNotEmpty(regularPromotion)){
			UserMetaData metaData = regularPromotion.getUserMetaData();
			if(UtilValidate.isNotEmpty(metaData)) {
				insertRegularPromotionDetails(regularPromotion);
				savePromotionMetainfoDetails(regularPromotion.getPromotionId(),regularPromotion.getApplicableType(),null, PromotionConstants.SALE_TYPE_DISCOUNT, regularPromotion.getUserMetaData());
				savePromotionChannelMapping(regularPromotion.getPromotionId(), regularPromotion.getApplicableType(), regularPromotion.getChannels(), metaData.getCreatedBy(), PromotionConstants.SALE_TYPE_DISCOUNT);
				saveDiscountCategories(regularPromotion);
				insertDiscountCategorySlabMapping(regularPromotion);
				if(regularPromotion.isCouponBased())
					saveCouponDetails(regularPromotion);
			}
		}
		return regularPromotion;
	}
	
	private void saveCouponDetails(RegularPromotion regularPromotion) {
		if(UtilValidate.isNotEmpty(regularPromotion.getPromotionCoupon())){
			PromotionCoupon promotionCoupon = regularPromotion.getPromotionCoupon();
			promotionCoupon.setPromotionId(regularPromotion.getPromotionId());
			promotionCoupon.setApplicableType(regularPromotion.getApplicableType());
			promotionCoupon.setAllCustomers(regularPromotion.isAllCustomers());
			promotionCoupon.setFromDate(regularPromotion.getFromDate());
			promotionCoupon.setToDate(regularPromotion.getToDate());
			promotionCoupon.setStatus(regularPromotion.getStatus());
			promotionCoupon.setCreatedBy(regularPromotion.getUserMetaData().getCreatedBy());
			savePromotionCouponDetails(promotionCoupon);
			if(regularPromotion.isCouponBased() && regularPromotion.getPromotionCoupon()!=null && (UtilValidate.isNotEmpty(regularPromotion.getPromotionCoupon().getDeliveryType()) || UtilValidate.isNotEmpty(regularPromotion.getPromotionCoupon().getPrescription()) || UtilValidate.isNotEmpty(regularPromotion.getPromotionCoupon().getPaymentType()))){
				EnumMap<CouponApplicableMode,List<String>> couponModes = new EnumMap<>(CouponApplicableMode.class);
				couponModes.put(CouponApplicableMode.DELIVERY_TYPE, regularPromotion.getPromotionCoupon().getDeliveryType());
				couponModes.put(CouponApplicableMode.PAYMENT_MODE, regularPromotion.getPromotionCoupon().getPaymentType());
				couponModes.put(CouponApplicableMode.E_PRESCRIPTION, regularPromotion.getPromotionCoupon().getPrescription());
				PromotionDaoHelper.saveCouponApplicableModeMapping(regularPromotion.getPromotionId(), PromotionConstants.SALE_TYPE_DISCOUNT,couponModes, posNPJdbcTemplate);
			}
			if(!regularPromotion.isAllCustomers()){
				if(UtilValidate.isNotEmpty(regularPromotion.getCustomerIds()))
					saveAndUpdateCustomers(regularPromotion, false);
				else
					throw new PromotionException("Customer details could not be empty,Please refresh and try again");
			}
		}else{
			throw new PromotionException("Coupon details could not be empty,Please refresh and try again");
		}
	}

	private Integer getRegularPromotionType(RegularPromotion regularPromotion) {
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
		return promotionType;
	}
	
	@Override
	public void updateRegularPromotionDetails(RegularPromotion regularPromotion, Map<String, Object> headerDetails) {
		
		posNPJdbcTemplate.update(REGULAR_PROMO_DETAILS_LOG_QRY, headerDetails);
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TO_DATE, regularPromotion.getToDate());
		params.addValue("promotionLevel", regularPromotion.getPromotionLevel());
		params.addValue("couponBased", regularPromotion.isCouponBased() ? "Y" : "N");
		params.addValue(ALL_CUSTOMERS, regularPromotion.isAllCustomers() ? "Y" : "N");
		params.addValue(SLAB_GROUP_ID, regularPromotion.getSlabGroupId());
		params.addValue(MODIFIED_BY, regularPromotion.getUserMetaData().getModifiedBy());
		params.addValue(PROMOTION_ID, regularPromotion.getPromotionId());
		
		posNPJdbcTemplate.update(UPDATE_REGULAR_PROMO_DETAILS_QRY, params);
		
		UserMetaData metaData = regularPromotion.getUserMetaData();
		
		updatePromotionChannelMapping(regularPromotion.getPromotionId(), regularPromotion.getApplicableType(), regularPromotion.getChannels(), metaData.getCreatedBy(), getRegularPromotionType(regularPromotion));
		
		updatePromotionMetainfoDetails(regularPromotion.getPromotionId(),regularPromotion.getApplicableType(), null, getRegularPromotionType(regularPromotion), metaData);
		
		if(regularPromotion.isCouponBased() && regularPromotion.getPromotionCoupon()!=null ) {
			PromotionCoupon coupon = regularPromotion.getPromotionCoupon();
			PromotionDaoHelper.updateCouponApplicableModes(regularPromotion.getPromotionId(), getRegularPromotionType(regularPromotion), coupon.getDeliveryType(), coupon.getPrescription(), coupon.getPaymentType(), posNPJdbcTemplate);
		}else{
			PromotionDaoHelper.updateCouponApplicableModes(regularPromotion.getPromotionId(), getRegularPromotionType(regularPromotion), null,null,null, posNPJdbcTemplate);
		}
		
	}
	
	private Map<String,Object>  getPromotionTypeAndChannels(long promotionId, int applicableType, int promotionType) {
		Map<String,Object> promotionChannelMap = new HashMap<>();
		List<Integer> channels = new ArrayList<>();
		int existPromotionType = 0;
		MapSqlParameterSource params = new MapSqlParameterSource();
		try{
			params.addValue(PROMOTION_ID, promotionId);
			params.addValue(APPLICABLE_TYPE, applicableType);
			params.addValue(PROMOTION_TYPES, (promotionType > 2) ? promotionType : Arrays.asList(-1,1,2));
			List<Map<String,Object>> channelsList = posReadonlyNPJdbcTemplate.queryForList(GET_PROMOTION_TYPE_AND_CHANNELS, params);
			if(UtilValidate.isNotEmpty(channelsList)){
				channels = new ArrayList<>();
				for(Map<String,Object> eachRowData : channelsList){
					if(eachRowData != null && eachRowData.get(CHANNEL) != null) {
						channels.add((Integer)eachRowData.get(CHANNEL));
						existPromotionType = (int)eachRowData.get(PROMOTION_TYPE);
					}
				}
			}
		}catch(DataAccessException dae){
			log.error("Exception Occured :", dae);
		}
		promotionChannelMap.put(CHANNEL,channels);
		promotionChannelMap.put(PROMOTION_TYPE,existPromotionType);
		return promotionChannelMap;
	}

	@SuppressWarnings("unchecked")
	private void updatePromotionChannelMapping(long promotionId, int applicableType, List<Integer> channels, String createdBy, int promotionType){
		Map<String, Object> map = getPromotionTypeAndChannels(promotionId, applicableType, promotionType);

		List<Integer> dbChannels = (List<Integer>) map.get(CHANNEL);
		int existpromotionType =  (int) map.get(PROMOTION_TYPE);
		List<Integer> campaignChannels = channels;
		List<Integer> deleteChannels = dbChannels;
		List<Integer> insertChannels = campaignChannels;
		if(promotionType == existpromotionType){
			deleteChannels = deleteChannels.stream()
	                .filter(channel -> !campaignChannels.contains(channel))
	                .collect(Collectors.toList());
			insertChannels =  campaignChannels.stream()
	                .filter(channel -> !dbChannels.contains(channel))
	                .collect(Collectors.toList());
		}
		if(UtilValidate.isNotEmpty(deleteChannels)){
			List<Integer> channel = new ArrayList<>();
			channel.addAll(deleteChannels);

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PROMOTION_ID, promotionId);
			params.addValue(APPLICABLE_TYPE, applicableType);
			params.addValue(PROMOTION_TYPES, (promotionType > 2) ? promotionType : Arrays.asList(-1,1,2));

			params.addValue("channels", channel);		
			List<Map<String, Object>> promotionChannelMapingDetails = posReadonlyNPJdbcTemplate.queryForList(GET_PROMO_CHANNEL_MAP_QRY, params);
			posNPJdbcTemplate.batchUpdate(LOG_PROMO_CHANNEL_MAP_QRY, promotionChannelMapingDetails.toArray(new Map[promotionChannelMapingDetails.size()]));
			posNPJdbcTemplate.update(DELETE_PROMO_CHANNEL_MAP_QRY, params);
		}
		if(UtilValidate.isNotEmpty(insertChannels)){
			savePromotionChannelMapping(promotionId,applicableType,insertChannels,createdBy,promotionType);
		}
	}

	@Override
	public void updatePromotionMetainfoDetails(long promotionId, int applicableType, Boolean specialtyBased, int promotionType, UserMetaData metaInfo){
		try{
			if(UtilValidate.isNotEmpty(promotionId)){
				MapSqlParameterSource params = new MapSqlParameterSource();
				params.addValue(PROMOTION_ID, promotionId);
				params.addValue(APPLICABLE_TYPE, applicableType);
				params.addValue(PROMOTION_TYPE, promotionType);
				Map<String, Object> promotionMetaInfoMappingDetails = posReadonlyNPJdbcTemplate.queryForMap(GET_PROMO_METAINFO_QRY, params);
				posNPJdbcTemplate.update(LOG_PROMO_METAINFO_MAP_QRY, promotionMetaInfoMappingDetails);
				posNPJdbcTemplate.update(DELETE_PROMO_METAINFO_MAP_QRY, params);

				savePromotionMetainfoDetails(promotionId, applicableType, specialtyBased, promotionType, metaInfo);
			}
		}catch(Exception e){
			log.error("Exception in Dao "+e.getMessage());
			throw new PromotionException("Unable to Update MetaInfo Data");
		}
	}

	@Override
	public void approveRegularPromotion(Long promotionId, LocalDateTime toDate, String approvedBy, boolean isDateUpdated, Map<String, Object> headerDetails) {
		
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PROMOTION_ID, promotionId);
			posNPJdbcTemplate.update(REGULAR_PROMO_DETAILS_LOG_QRY, headerDetails);
			params.addValue(TO_DATE, toDate);
			params.addValue(APPROVED_BY, approvedBy);
			params.addValue(STATUS, PromotionConstants.ACTIVE);
			params.addValue(REMARKS, null);
			LocalDateTime currentDate = LocalDateTime.now();
			LocalDateTime fromDate = CampaignUtil.getDateFromMap(headerDetails.get(VALID_FROM));
			LocalDateTime effectiveDate =  fromDate.isAfter(currentDate) ? fromDate : currentDate;
			params.addValue(EFFECTIVE_DATE, effectiveDate);
			params.addValue(DATE_APPROVED, currentDate);
			String query = isDateUpdated ? UPDATE_AND_APPROVE_REGULAR_PROMO_QRY : APPROVE_REJECT_REGULAR_PROMO_QRY;
			posNPJdbcTemplate.update(query, params);
			setToDateForDiscountSlabMapping(promotionId, toDate, approvedBy, PromotionConstants.ACTIVE);
			if("Y".equalsIgnoreCase((String)headerDetails.get(COUPON_BASED))) 
				setToDateForCouponBasedPromotions(promotionId, toDate, approvedBy, PromotionConstants.ACTIVE);
		
	}

	@Override
	public Map<String, Object> getRegularPromotionHeaderDetails(Long promotionId) {
		return posNPJdbcTemplate.queryForMap(GET_REG_PROMO_DETAILS, new MapSqlParameterSource(PROMOTION_ID, promotionId));
	}

	@Override
	public void updatePromtionToDate(Long promotionId, LocalDateTime toDate, String modifiedBy, Map<String, Object> headerDetails) {

		if(UtilValidate.isEmpty(toDate) || toDate.isAfter(LocalDateTime.now())) {
			setToDateForActiveRegularPromotion(promotionId, toDate, modifiedBy, headerDetails);
			setToDateForDiscountSlabMapping(promotionId, toDate, modifiedBy, String.valueOf(headerDetails.get(STATUS)));
			if("Y".equalsIgnoreCase((String)headerDetails.get(COUPON_BASED))) 
				setToDateForCouponBasedPromotions(promotionId, toDate, modifiedBy, String.valueOf(headerDetails.get(STATUS)));
		} else {
			throw new PromotionException("Closed Promotion's To date cannot be Updated.");
		}
	}
	
	private void setToDateForCouponBasedPromotions(Long promotionId, LocalDateTime toDate, String modifiedBy, String status) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PROMOTION_ID, promotionId);
		params.addValue(PROMOTION_TYPE, PromotionConstants.SALE_TYPE_DISCOUNT);
		posNPJdbcTemplate.update(GET_AND_LOG_PROMOTION_COUPON_DETAILS, params);
		params.addValue(TO_DATE, toDate);
		params.addValue(MODIFIED_BY, modifiedBy);
		params.addValue(STATUS, status);
		posNPJdbcTemplate.update(UPDATE_TO_DATE_IN_PROMOTION_COUPON, params);
	}
	
	private void setToDateForDiscountSlabMapping(Long promotionId, LocalDateTime toDate, String modifiedBy, String status) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PROMOTION_ID, promotionId);
		posNPJdbcTemplate.update(GET_AND_LOG_DISC_SLAB_CATEGORY_MAPPING,params);
		params.addValue(TO_DATE, toDate);
		params.addValue(MODIFIED_BY, modifiedBy);
		params.addValue(STATUS, status);
		posNPJdbcTemplate.update(UPDATE_TO_DATE_IN_DISCOUNT_CATEGORY_MAPPING, params);
	}
	
	private void setToDateForActiveRegularPromotion(Long promotionId, LocalDateTime toDate, String modifiedBy, Map<String, Object> headerDetails) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PROMOTION_ID, promotionId);
		posNPJdbcTemplate.update(REGULAR_PROMO_DETAILS_LOG_QRY, headerDetails);
		params.addValue(MODIFIED_BY, modifiedBy);
		params.addValue(TO_DATE, toDate);
		posNPJdbcTemplate.update(UPDATE_ACTIVE_PROMO, params);
	}

	@Override
	public void rejectRegularPromotion(Long promotionId, String rejectedBy, String remarks, Map<String, Object> headerDetails) {

		if(PromotionConstants.INACTIVE.equalsIgnoreCase((String)headerDetails.get(STATUS))) {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PROMOTION_ID, promotionId);
			posNPJdbcTemplate.update(REGULAR_PROMO_DETAILS_LOG_QRY, headerDetails);
			params.addValue(STATUS, PromotionConstants.REJECT);
			params.addValue(APPROVED_BY, rejectedBy);
			params.addValue(REMARKS, remarks);
			params.addValue(EFFECTIVE_DATE, null);
			params.addValue(DATE_APPROVED, LocalDateTime.now());
			posNPJdbcTemplate.update(APPROVE_REJECT_REGULAR_PROMO_QRY, params);
			LocalDateTime toDate = CampaignUtil.getDateFromMap(headerDetails.get(VALID_TO));
			setToDateForDiscountSlabMapping(promotionId, toDate, rejectedBy, PromotionConstants.REJECT);
			if("Y".equalsIgnoreCase((String)headerDetails.get(COUPON_BASED))) 
				setToDateForCouponBasedPromotions(promotionId, toDate, rejectedBy, PromotionConstants.REJECT);
		} else {
			throw new PromotionException("Active Promotion cannot be Rejected");
		}
	}

	private void saveDiscountCategories(RegularPromotion regularPromotion){
		log.info("regularPromotion : {}",new Gson().toJson(regularPromotion));
		if(UtilValidate.isNotEmpty(regularPromotion) && regularPromotion.getProductCategoryIds() != null && regularPromotion.getLoyalty() != null && regularPromotion.getRegions() != null){
			int[] createTypes = {Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.CHAR,Types.CHAR,Types.VARCHAR,Types.TIMESTAMP};
			PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(INSERT_DISCOUNT_CATEGORY_QRY,createTypes);
			pscf.setReturnGeneratedKeys(true);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			Map<CategoryDetail, Long> savedCategoryDetails = new HashMap<>();
			regularPromotion.getCategoryDetail().forEach(categoryDetail -> {
				CategoryDetail details = new CategoryDetail(categoryDetail.getProductDiscountCategoryId(), categoryDetail.getLoyaltyId(), categoryDetail.getRegion());
				Long savedId = savedCategoryDetails.get(details);
				if(UtilValidate.isNotEmpty(savedId)) {
					categoryDetail.setDiscountCategoryId(savedId);
				} else {
					List<Integer> discountCategoryId = jdbcTemplate.query(GET_DISCOUNT_CATEGORY_ID_QRY, (rs, rowNum) -> rs.getInt(DISCOUNT_CATEGORY_ID), categoryDetail.getProductDiscountCategoryId(), categoryDetail.getLoyaltyId(),
							PromotionConstants.SALE_TYPE_DISCOUNT, categoryDetail.getRegion(), regularPromotion.getPromotionLevel(), regularPromotion.getApplicableType());
					if(UtilValidate.isNotEmpty(discountCategoryId))
						categoryDetail.setDiscountCategoryId(Long.valueOf(discountCategoryId.get(0)));
					else {
						jdbcTemplate.update(pscf.newPreparedStatementCreator(new Object[]{categoryDetail.getProductDiscountCategoryId(),categoryDetail.getLoyaltyId(),PromotionConstants.SALE_TYPE_DISCOUNT,categoryDetail.getRegion(),regularPromotion.getApplicableType(),regularPromotion.getPromotionLevel(),"A",regularPromotion.getUserMetaData().getCreatedBy(), LocalDateTime.now()}),keyHolder);
						categoryDetail.setDiscountCategoryId(keyHolder.getKey().longValue());
					}
					savedCategoryDetails.put(details, categoryDetail.getDiscountCategoryId());
				}
			});

		}
	}
	
	private int getFlag(Object value, Integer promotionIdFlag) {
		return (promotionIdFlag==1 && value!=null) ? 0 : 1;
	}
	
	@Override
	public Map<String, Object> getRegularPromotionList(CampaignSearchCriteria searchCriteria) {
		Integer promotionId = searchCriteria.getCampaignId();
		int promotionIdFlag= promotionId!=null ? 0 : 1;
		String name = searchCriteria.getCampaignName();
		if(UtilValidate.isNotEmpty(name)) 
			name="%"+name+"%";
		List<Integer> channels = searchCriteria.getChannels();
		List<Integer> applicableTypes = searchCriteria.getApplicableTypes();
		String status = searchCriteria.getStatus();
		int closedFlag=1;
		int statusFlag=1;
		if("C".equals(status)&&promotionIdFlag==1) {
			closedFlag=0;
			statusFlag=0;
			status="A";
		}else {
			statusFlag=getFlag(status,promotionIdFlag);
		}
		String promotionLevel = searchCriteria.getPromotionLevel();
		String couponCode = searchCriteria.getCouponCode();
		String createdBy = searchCriteria.getCreatedBy();
		LocalDateTime fromDate = searchCriteria.getFromDate();
		LocalDateTime toDate = searchCriteria.getToDate();
		int dateFlag = getFlag(fromDate, promotionIdFlag)==0 && getFlag(toDate, promotionIdFlag)==0 ? 0 : 1;
		if(dateFlag==0) {
			toDate=toDate.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
			fromDate=fromDate.withHour(0).withMinute(0).withSecond(0).withNano(0);
		}
		
		int offset = searchCriteria.getOffset();
		int limit = searchCriteria.getLimit();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PROMOTION_ID, promotionId);
		params.addValue("promotionName", name);
		params.addValue(APPLICABLE_TYPE, applicableTypes);
		params.addValue("channel", channels);
		params.addValue(STATUS_FEILD, status);
		params.addValue("createdBy", createdBy);
		params.addValue(FROM_DATE, fromDate);
		params.addValue("promotionLevel", promotionLevel);
		params.addValue(COUPON_CODE_FEILD, couponCode);
		params.addValue(TO_DATE, toDate);
		params.addValue("offset", offset);
		params.addValue("limit", limit);
		params.addValue("promotionIdFlag", promotionIdFlag);
		params.addValue("nameFlag", getFlag(name, promotionIdFlag));
		params.addValue("channelFlag", getFlag(channels, promotionIdFlag));
		params.addValue("statusFlag", statusFlag);
		params.addValue("closedFlag", closedFlag);
		params.addValue("createdByFlag", getFlag(createdBy, promotionIdFlag));
		params.addValue("dateFlag", dateFlag);
		params.addValue("promotionLevelFlag", getFlag(promotionLevel, promotionIdFlag));
		params.addValue(COUPON_CODE_FLAG, getFlag(couponCode, promotionIdFlag));
		
		return posReadonlyNPJdbcTemplate.query(GET_REGULAR_PROMO_LIST, params,
				(ResultSet rs) ->{
					List<RegularPromotion> promotionList = new ArrayList<>();
					Map<String, Object> result = new HashMap<>();
					int totalRowCount = 0;
					while(rs.next()){
						totalRowCount= rs.getInt("TotalRowCount");
						RegularPromotion promotion = new RegularPromotion();
						promotion.setPromotionId(rs.getLong(PROMOTION_ID));
						promotion.setPromotionName(rs.getString("Name"));
						promotion.setFromDate(rs.getObject(VALID_FROM, LocalDateTime.class));
						promotion.setToDate(rs.getObject(VALID_TO, LocalDateTime.class));
						promotion.setEffectiveDate(rs.getObject("EffectiveDate", LocalDateTime.class));
						promotion.setStatus(rs.getString(STATUS));
						promotion.setPromotionLevel(rs.getString("PromotionLevel"));
						promotion.setCouponBased("Y".equalsIgnoreCase(rs.getString(COUPON_BASED)));
						promotion.setAllCustomers("Y".equalsIgnoreCase(rs.getString(ALL_CUSTOMERS_FIELD)));
						UserMetaData metaData = new UserMetaData();
						metaData.setCreatedBy(rs.getString("CreatedBy"));
						metaData.setDateCreated(rs.getObject("DateCreated", LocalDateTime.class));
						metaData.setApprovedBy(rs.getString("ApprovedBy"));
						metaData.setDateApproved(rs.getObject("DateApproved", LocalDateTime.class));
						promotion.setUserMetaData(metaData);
						promotion.setCloneReferenceId(rs.getLong("CloneReferenceId"));
						promotion.setApplicableType(rs.getInt("ApplicableType"));
						List<Integer> channelsList = Stream.of(rs.getString("Channels").split(","))
								  .map(String::trim)
								  .map(Integer::parseInt)
								  .collect(Collectors.toList());
						promotion.setChannels(channelsList);
						promotionList.add(promotion);
					}
					result.put("data", promotionList);
					result.put("rowCount", totalRowCount);
					return result;
				});
	}
	
	@Override
	public RegularPromotion getRegularPromotionByPromotionId(Long promotionId) {
		log.info("promotionId :{}",promotionId);
		MapSqlParameterSource params = new MapSqlParameterSource(PROMOTION_ID, promotionId);
		try{
			return posReadonlyNPJdbcTemplate.query(GET_REG_PROMO_DETAILS_BY_PROMO_IDQRY, params, new RegularPromotionExtractor());
		}catch(DataAccessException dae){
			log.error("Exception Occured : ", dae);
		}
		return null;
	}
	
	@Override
	public PromotionCoupon getPromotionCoupons(Long promotionId,int promotionType,String couponCode){
		int couponCodeFlag = UtilValidate.isNotEmpty(couponCode) ? 0 : 1;
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(COUPON_CODE_FLAG, couponCodeFlag);
		params.addValue(COUPON_CODE_FEILD, couponCode);
		params.addValue("promotionType", promotionType);
		params.addValue(PROMOTION_ID, promotionId);
		return posReadonlyNPJdbcTemplate.query(GET_PROMOTION_DETAILS_QRY, params, new PromotionCouponExtractor());
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getPromotionChannels(long promotionId, int applicableType, int promotionType) {
		Map<String, Object> map = getPromotionTypeAndChannels(promotionId, applicableType, promotionType);
		return (List<Integer>) map.get(CHANNEL);
	}
	
	@Override
	public Map<CouponApplicableMode, List<String>> getCouponApplicableModes(Long promotionId, int promotionType) {
		EnumMap<CouponApplicableMode, List<String>> couponApplicableModes = null;
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PROMOTION_ID, promotionId);
		params.addValue(PROMOTION_TYPES, (promotionType > 2) ? promotionType : Arrays.asList(-1,1,2));
		params.addValue(VALUE_FLAG, 1);
		params.addValue(VALUE, null);
		List<Map<String,Object>> couponApplicableModesList = posReadonlyNPJdbcTemplate.queryForList(GET_COUPON_APPLICABLE_MODE_MAPPING, params);
		if(UtilValidate.isNotEmpty(couponApplicableModesList)){
			couponApplicableModes = new EnumMap<>(CouponApplicableMode.class);
			for(Map<String,Object> eachRowData : couponApplicableModesList){
				if(eachRowData != null && eachRowData.get("Mode") != null && eachRowData.get(VALUE) != null) {
					CouponApplicableMode mode = CouponApplicableMode.valueOf(eachRowData.get("Mode").toString());
					couponApplicableModes.computeIfAbsent(mode, v-> new ArrayList<>()).add(eachRowData.get(VALUE).toString());
				}
			}
		}
		return couponApplicableModes;
	}
	
	private void updateDiscountCategoryMappingDetails(Long promotionId) {
		MapSqlParameterSource params = new MapSqlParameterSource(PROMOTION_ID, promotionId);
		posNPJdbcTemplate.update(INSERT_DISCOUNT_CATEGOR_MAPPING_LOG_QRY, params);
		posNPJdbcTemplate.update(DELETE_DISCOUNT_SLAB_CATEGORY_MAPPING_QRY, params);
	}
	
	@Override
	public void updateDiscountCategorySlabs(RegularPromotion regularPromotion) {
		saveDiscountCategories(regularPromotion);
		if(UtilValidate.isNotEmpty(regularPromotion.getCategoryDetail())) {
			updateDiscountCategoryMappingDetails(regularPromotion.getPromotionId());
			insertDiscountCategorySlabMapping(regularPromotion);
		}
	}

	@Override
	public void updateRegularPromotionCustomerDetails(RegularPromotion regularPromotion, Map<String, Object> headerDetails) {
		boolean existingAllCustomers = "Y".equals(String.valueOf(headerDetails.get(ALL_CUSTOMERS_FIELD)));
		saveAndUpdateCustomers(regularPromotion, existingAllCustomers);
	}
	
	private void saveAndUpdateCustomers(RegularPromotion regularPromotion, boolean isExistingAllCustomers) {
		if(!isExistingAllCustomers && regularPromotion.isAllCustomers()) {
			deactivateCustomers(regularPromotion.getPromotionId());
		} else if(isExistingAllCustomers && !regularPromotion.isAllCustomers()) {
			if(UtilValidate.isEmpty(regularPromotion.getCustomerIds()))
				throw new PromotionException("CustomerId's cannot be empty.");
			Set<Long> customersToUpload = new HashSet<>(regularPromotion.getCustomerIds());
			if(UtilValidate.isNotEmpty(regularPromotion.getRemovedCustomerIds())) 
				customersToUpload.removeAll(regularPromotion.getRemovedCustomerIds());
			saveCustomers(regularPromotion.getPromotionId(), regularPromotion.getPromotionCoupon().getCouponCode(), customersToUpload, regularPromotion.getUserMetaData().getModifiedBy());
		} else if(!isExistingAllCustomers && !regularPromotion.isAllCustomers()) {
			Set<Long> customersToUpload = regularPromotion.getCustomerIds();
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(REGULAR_PROMOTION_ID, regularPromotion.getPromotionId());
			params.addValue(COUPON_CODE_FLAG, 0);
			params.addValue(COUPON_CODE_FEILD, regularPromotion.getPromotionCoupon().getCouponCode());
			List<Map<String, Object>> existingCustomers=posNPJdbcTemplate.queryForList(GET_ALL_REGULAR_PROMOTION_CUSTOMERS, params);
			log.info("existingCustomers : {}",existingCustomers);
			List<Long> existingCustomerIds = existingCustomers.stream()
		                .map(map -> ((Number) map.get(CUSTOMER_ID)).longValue())
		                .filter(Objects::nonNull)
		                .collect(Collectors.toList());
			Set<Long> customersToRemove = new HashSet<>(regularPromotion.getRemovedCustomerIds());
			
			Set<Long> commonCustomers = new HashSet<>(customersToUpload.stream().filter(customersToRemove::contains).collect(Collectors.toSet()));
			customersToUpload.removeAll(commonCustomers);
			customersToRemove.removeAll(commonCustomers);
			
			customersToUpload.removeAll(existingCustomerIds);
			customersToRemove.retainAll(existingCustomerIds);
			
			if(UtilValidate.isNotEmpty(customersToUpload))
				saveCustomers(regularPromotion.getPromotionId(), regularPromotion.getPromotionCoupon().getCouponCode(), customersToUpload, regularPromotion.getUserMetaData().getModifiedBy());
			if(UtilValidate.isNotEmpty(customersToRemove))
				deleteCustomersForRegularPromotion(regularPromotion.getPromotionId(), regularPromotion.getPromotionCoupon().getCouponCode(), customersToRemove);
		}
	}
	
	private void deleteCustomersForRegularPromotion(Long promotionId, String couponCode, Set<Long> customersToRemove) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(REGULAR_PROMOTION_ID, promotionId);
		params.addValue(COUPON_CODE_FEILD, couponCode);
		params.addValue(CUSTOMER_ID, customersToRemove);
		params.addValue(COUPON_CODE_FLAG, 0);
		params.addValue(COUPON_CODE_FEILD, couponCode);
		posNPJdbcTemplate.update(GET_AND_LOG_REGULAR_PROMOTION_CUSTOMERIDS, params);
		posNPJdbcTemplate.update(DELETE_CUSTOMERS_FOR_REGULAR_PROMOTION, params);
	}

	
	private void saveCustomers(Long promotionId, String couponCode, Set<Long> customers, String createdBy) {
		for(Long customerId : customers) {
			try {
				jdbcTemplate.update(INSERT_REGULAR_PROMO_CUSTOMERS_QRY, promotionId,customerId,couponCode.toUpperCase(),createdBy);
	    	} catch(DuplicateKeyException e){
				log.info("Duplicate customerID: {}",customerId);
			}
		}
	}
	
	private void deactivateCustomers(Long promotionId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(REGULAR_PROMOTION_ID, promotionId);
		params.addValue(COUPON_CODE_FLAG, 1);
		params.addValue(COUPON_CODE_FEILD, null);
		List<Map<String, Object>> customers= posNPJdbcTemplate.queryForList(GET_ALL_REGULAR_PROMOTION_CUSTOMERS, params);
		posNPJdbcTemplate.batchUpdate(REGULAR_PROMOTION_CUSTOMER_LOG_QRY, customers.toArray(new Map[customers.size()]));
		List<Object> customerIds = customers.stream()
	        .map(map -> map.get(CUSTOMER_ID))
	        .filter(Objects::nonNull)
	        .collect(Collectors.toList());
		if(UtilValidate.isNotEmpty(customerIds)) {
			params.addValue(CUSTOMER_ID, customerIds);
			posNPJdbcTemplate.update(DELETE_CUSTOMERS_FOR_REGULAR_PROMOTION, params);
		}

	}

	private void deleteCouponDetals(PromotionCoupon coupon, Map<String, Object> couponDetails) {
		if(UtilValidate.isNotEmpty(coupon)) {
			posNPJdbcTemplate.update(INSERT_COUPON_DETAILS_LOG_QRY, couponDetails);
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PROMOTION_ID, coupon.getPromotionId());
			params.addValue(PROMOTION_TYPES, coupon.getPromotionType());
			posNPJdbcTemplate.update(DELETE_TOTAL_COUPON_USED, params);
			posNPJdbcTemplate.update(DELETE_COUPON_DETAILS, params);
		}
	}
	
	private void updateCoupon(PromotionCoupon coupon, String modifiedBy) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PROMOTION_ID, coupon.getPromotionId());
		params.addValue("promotionType", coupon.getPromotionType());
		params.addValue(APPLICABLE_TYPE, coupon.getApplicableType());
		params.addValue(ALL_CUSTOMERS, coupon.isAllCustomers() ? "Y" : "N");
		params.addValue("totalLimit", coupon.getTotalLimit());
		params.addValue("customerLimit", coupon.getCustomerLimit());
		params.addValue(FROM_DATE, coupon.getFromDate());
		params.addValue(TO_DATE, coupon.getToDate());
		params.addValue(STATUS_FEILD, coupon.getStatus());
		params.addValue("minValue", coupon.getMinValue());
		params.addValue("maxDiscount", coupon.getMaxDiscount());
		params.addValue("maxPoints", coupon.getMaxPoints());
		params.addValue("addonCoupon", coupon.isAddOnCoupon());
		params.addValue("noOfDays", coupon.getNoOfDays());
		params.addValue("couponDiscountType", coupon.getCouponDiscountType());
		params.addValue(MODIFIED_BY, modifiedBy);
		params.addValue(COUPON_CODE_FEILD, coupon.getCouponCode().toUpperCase());
		posNPJdbcTemplate.update(UPDATE_COUPON_DETAILS, params);
	}
	
	@Override
	@Transactional("posTransactionManager")
	public void updatePromotionCouponDetails(RegularPromotion promotion, Map<String, Object> headerDetails) {

		boolean existingIsCouponBased = "Y".equalsIgnoreCase((String) headerDetails.get(COUPON_BASED));
		boolean existingIsAllCustomers = "Y".equalsIgnoreCase((String) headerDetails.get(ALL_CUSTOMERS_FIELD));
		PromotionCoupon coupon = promotion.getPromotionCoupon();
		coupon.setPromotionId(promotion.getPromotionId());
		coupon.setFromDate(promotion.getFromDate());
		coupon.setToDate(promotion.getToDate());
		coupon.setApplicableType(promotion.getApplicableType());
		coupon.setCreatedBy(promotion.getUserMetaData().getCreatedBy());
		if(!existingIsCouponBased && promotion.isCouponBased()) {
			savePromotionCouponDetails(coupon);
			saveAndUpdateCustomers(promotion, existingIsAllCustomers);
		}
		else if(existingIsCouponBased){
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PROMOTION_ID, promotion.getPromotionId());
			params.addValue(PROMOTION_TYPE, promotion.getPromotionCoupon().getPromotionType());
			Map<String, Object> couponDetails = posReadonlyNPJdbcTemplate.queryForMap(GET_COUPON_DETAILS, params);
			if(!promotion.isCouponBased()) {
				deleteCouponDetals(promotion.getPromotionCoupon(), couponDetails);
				deactivateCustomers(promotion.getPromotionId());
			} else if(promotion.isCouponBased()){
				Map<CouponApplicableMode, List<String>> couponApplicableModes = getCouponApplicableModes(UtilValidate.isNotEmpty(couponDetails.get(PROMOTION_ID)) ? Long.valueOf(couponDetails.get(PROMOTION_ID).toString()) : null, (Integer) couponDetails.get(PROMOTION_TYPE));
				PromotionCoupon existingCoupon = new PromotionCoupon();
				existingCoupon.setPromotionType((Integer) couponDetails.get(PROMOTION_TYPE));
				existingCoupon.setApplicableType((Integer) couponDetails.get("ApplicableType"));
				existingCoupon.setAllCustomers("Y".equalsIgnoreCase((String)couponDetails.get(ALL_CUSTOMERS_FIELD)));
				existingCoupon.setTotalLimit(UtilValidate.isNotEmpty(couponDetails.get("TotalLimit")) ? Long.valueOf(couponDetails.get("TotalLimit").toString()) : null);
				existingCoupon.setCustomerLimit(UtilValidate.isNotEmpty(couponDetails.get("CustomerLimit")) ? Long.valueOf(couponDetails.get("CustomerLimit").toString()) : null);
				existingCoupon.setFromDate(CampaignUtil.getDateFromMap(couponDetails.get("FromDate")));
				existingCoupon.setToDate((CampaignUtil.getDateFromMap(couponDetails.get("ToDate"))));
				existingCoupon.setStatus((String) couponDetails.get(STATUS));
				existingCoupon.setMinValue(UtilValidate.isNotEmpty(couponDetails.get("MinValue")) ?  Double.valueOf(couponDetails.get("MinValue").toString()) : null );
				existingCoupon.setMaxDiscount(UtilValidate.isNotEmpty(couponDetails.get("MaxDiscount")) ? Double.valueOf(couponDetails.get("MaxDiscount").toString()) : null);
				existingCoupon.setMaxPoints(UtilValidate.isNotEmpty(couponDetails.get("MaxPoints")) ? Double.valueOf(couponDetails.get("MaxPoints").toString()) : null);
				existingCoupon.setAddOnCoupon((Boolean) couponDetails.get("AddonCoupon"));
				if(UtilValidate.isNotEmpty(couponApplicableModes)) {
					existingCoupon.setDeliveryType(couponApplicableModes.get(CouponApplicableMode.DELIVERY_TYPE));
					existingCoupon.setPaymentType(couponApplicableModes.get(CouponApplicableMode.PAYMENT_MODE));
					existingCoupon.setPrescription(couponApplicableModes.get(CouponApplicableMode.E_PRESCRIPTION));
				}
				existingCoupon.setNoOfDays((Integer) couponDetails.get("NoOfDays"));
				existingCoupon.setCouponDiscountType((Integer) couponDetails.get("CouponDiscountType"));
				if(!Objects.equals(existingCoupon, coupon)) {
					posNPJdbcTemplate.update(INSERT_COUPON_DETAILS_LOG_QRY, couponDetails);
					updateCoupon(coupon, promotion.getUserMetaData().getModifiedBy());
					saveAndUpdateCustomers(promotion, existingCoupon.isAllCustomers());
				}
			}
		}
	}

	@Override
	public Set<Long> getRegularPromotionCustomerIds(Long promotionId, String couponCode) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PROMOTION_ID, promotionId);
		params.addValue(COUPON_CODE_FEILD, couponCode);
		List<Long> customers =  posReadonlyNPJdbcTemplate.queryForList(GET_CUSTOMER_IDS_FOR_REGULAR_PROMOTION, params, Long.class);
		return new HashSet<>(customers);
	}

	@Override
	public boolean isRegularPromotionNameAvailable(String promotionName) {
		MapSqlParameterSource params = new MapSqlParameterSource("PromotionName", promotionName);
		Integer count = posReadonlyNPJdbcTemplate.queryForObject(REGULAR_PROMOTINO_NAME_AVAILABILITY_QUERY, params, Integer.class);
		return (count!=null && count==0);
	}
	
	@Override
	public boolean isRegularPromotionCouponCodeAvailable(String couponCode) {
		MapSqlParameterSource params = new MapSqlParameterSource(COUPON_CODE_FEILD, couponCode);
		Integer count = posReadonlyNPJdbcTemplate.queryForObject(REGULAR_PROMOTINO_COUPO_CODE_AVAILABILITY_QUERY, params, Integer.class);
		return (count!=null && count==0);
	}
	
	@Override 
	public void autoRejectRegularPromotion() { 
		LocalDateTime toDate = LocalDate.now().atStartOfDay();
		log.info("Running auto-reject cron for RegularPromotions at : {}", LocalDateTime.now()); 
		List<Map<String, Object>> headerDetails = posNPJdbcTemplate.queryForList(GET_CLOSED_REGULAR_PROMOTION_HEADER_QUERY, new MapSqlParameterSource(TO_DATE, toDate));
		List<Object> promotionIdsToReject = headerDetails.stream().map(t ->t.get(PROMOTION_ID)).collect(Collectors.toList());
		log.info("All auto-reject campaigns : {}", promotionIdsToReject);
		if(UtilValidate.isEmpty(promotionIdsToReject))
			return;
		posNPJdbcTemplate.batchUpdate(REGULAR_PROMO_DETAILS_LOG_QRY, headerDetails.toArray(new Map[headerDetails.size()]));
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(STATUS, PromotionConstants.REJECT);
		params.addValue(PROMOTION_ID, promotionIdsToReject);
		params.addValue(REMARKS, AUTO_REJECT_REMARKS);
		params.addValue(EFFECTIVE_DATE, null);
		params.addValue(APPROVED_BY, "Auto-Reject Cron");
		params.addValue(DATE_APPROVED, LocalDateTime.now());
		params.addValue(TO_DATE, headerDetails.get(0).get(VALID_TO));
		params.addValue(MODIFIED_BY, "Auto-Reject Cron");
		params.addValue(PROMOTION_TYPE, PromotionConstants.SALE_TYPE_DISCOUNT);
		posNPJdbcTemplate.update(APPROVE_REJECT_REGULAR_PROMO_QRY, params);
		posNPJdbcTemplate.update(GET_AND_LOG_DISC_SLAB_CATEGORY_MAPPING,params);
		posNPJdbcTemplate.update(UPDATE_TO_DATE_IN_DISCOUNT_CATEGORY_MAPPING, params);
		posNPJdbcTemplate.update(GET_AND_LOG_PROMOTION_COUPON_DETAILS, params);
		posNPJdbcTemplate.update(UPDATE_TO_DATE_IN_PROMOTION_COUPON, params);

	}

	private static final Set<String> CAMPAIGN_TYPES = new HashSet<>();
	static {
		CAMPAIGN_TYPES.add(PromotionConstants.SLAB_BASED_DISCOUNT);
		CAMPAIGN_TYPES.add(PromotionConstants.SLAB_BASED_POINTS);
		CAMPAIGN_TYPES.add("-1");
	}
	@Override
	public boolean isPosChannel(long promotionId) {
		return PromotionDaoHelper.isPosChannel(promotionId, CAMPAIGN_TYPES, posNPJdbcTemplate);
	}

	@Override
	public List<String> getStores(Long promotionId) {
		MapSqlParameterSource params = new MapSqlParameterSource(PROMOTION_ID, promotionId);
		return posReadonlyNPJdbcTemplate.queryForList(GET_STORES_QUERY, params, String.class);
	}
	
}
