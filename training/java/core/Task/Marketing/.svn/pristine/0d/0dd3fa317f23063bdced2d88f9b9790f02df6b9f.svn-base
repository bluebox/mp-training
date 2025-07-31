package com.medplus.marketing.dao.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.constants.CouponApplicableMode;
import com.medplus.marketing.util.CampaignUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PromotionDaoHelper {
	
	private PromotionDaoHelper() {}
	
	private static final String INSERT_INTO_COUPON_APPLICABLE_MODE_MAPPING_QRY = "INSERT INTO `tbl_coupon_applicable_mode_mapping` (`PromotionId`, `PromotionType`, `Mode`, `Value`) VALUES (:PromotionId, :PromotionType, :Mode, :Value)";

	private static final String LOG_COUPON_APPLICABLE_MODE_MAPPING = "INSERT INTO `tbl_coupon_applicable_mode_mapping_log` (`PromotionId`, `PromotionType`, `Mode`, `Value`) VALUES (:PromotionId, :PromotionType, :Mode, :Value)";
	
	private static final String GET_COUPON_APPLICABLE_MODE_MAPPING = "SELECT `PromotionId`, `PromotionType`, `Mode`, `Value` from tbl_coupon_applicable_mode_mapping where `PromotionId`=:promotionId AND `PromotionType` IN (:promotionTypes) AND (1=:valueFlag or Value in (:Value))";
	
	private static final String DELETE_COUPON_APPLICABLE_MODE_MAPPING = "DELETE FROM `tbl_coupon_applicable_mode_mapping` where `PromotionId`=:promotionId AND `PromotionType` IN (:promotionTypes) AND Value in (:Value)";
	
	private static final String VALUE = "Value";
	private static final String PROMOTION_ID = "PromotionId";
	private static final String  PROMOTION_TYPE = "PromotionType";
	@SuppressWarnings("unchecked")
	public static void updateCouponApplicableModes(long promotionId, int promotionType, List<String> deliveryType, List<String> prescription, List<String> paymentType, NamedParameterJdbcTemplate posNPJdbcTemplate ) {
		
		Map<String,Object> couponApplicableModeValuesMap = getCouponApplicableModeValues(promotionId, promotionType, posNPJdbcTemplate);
		List<String> dbValues = (List<String>) couponApplicableModeValuesMap.get("couponApplicableModeValues");
		int existPromotiontype = (int) couponApplicableModeValuesMap.get("existPromotionType");
		List<String> allValues = CampaignUtil.getAllValues(existPromotiontype, promotionType, deliveryType, prescription, paymentType);
		
		if(UtilValidate.isNotEmpty(dbValues)){
			List<String> deleteValues = dbValues;
			if(UtilValidate.isNotEmpty(allValues)){
				deleteValues = deleteValues.stream()
		                .filter(value -> !allValues.contains(value))
		                .collect(Collectors.toList());
			}
			if (UtilValidate.isNotEmpty(deleteValues)) {
				MapSqlParameterSource params = new MapSqlParameterSource();
				params.addValue("promotionId", promotionId);
				params.addValue("promotionTypes", (promotionType > 2) ? promotionType : Arrays.asList(-1,1,2));
				params.addValue("valueFlag", 0);
				params.addValue(VALUE, deleteValues);
				List<Map<String, Object>> details = posNPJdbcTemplate.queryForList(GET_COUPON_APPLICABLE_MODE_MAPPING, params);
				posNPJdbcTemplate.batchUpdate(LOG_COUPON_APPLICABLE_MODE_MAPPING, details.toArray(new Map[details.size()]));
				posNPJdbcTemplate.update(DELETE_COUPON_APPLICABLE_MODE_MAPPING, params);
				
			}
			if(promotionType == existPromotiontype) {
				for (String dbValue : dbValues) {
					if (UtilValidate.isNotEmpty(deliveryType) && deliveryType.contains(dbValue)) {
						deliveryType.remove(dbValue);
					} else if (UtilValidate.isNotEmpty(prescription) && prescription.contains(dbValue)) {
						prescription.remove(dbValue);
					} else if (UtilValidate.isNotEmpty(paymentType) && paymentType.contains(dbValue)) {
						paymentType.remove(dbValue);
					}
				}
			}
		}

		if (UtilValidate.isNotEmpty(deliveryType)
				|| UtilValidate.isNotEmpty(prescription)
				|| UtilValidate.isNotEmpty(paymentType)) {
			EnumMap<CouponApplicableMode, List<String>> couponModes = new EnumMap<>(CouponApplicableMode.class);
			couponModes.put(CouponApplicableMode.DELIVERY_TYPE, deliveryType);
			couponModes.put(CouponApplicableMode.E_PRESCRIPTION, prescription);
			couponModes.put(CouponApplicableMode.PAYMENT_MODE, paymentType);

			saveCouponApplicableModeMapping(promotionId, promotionType, couponModes, posNPJdbcTemplate);
		}
	}
	
	private static Map<String,Object> getCouponApplicableModeValues(long promotionId, int promotionType, NamedParameterJdbcTemplate posReadonlyNPJdbcTemplate){
		Map<String,Object> couponApplicableModeValuesMap = new HashMap<>();
		int existPromotionType = 0;
		List<String> couponApplicableModeValues = null;
		try{
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("promotionId", promotionId);
			params.addValue("promotionTypes", (promotionType > 2) ? promotionType : Arrays.asList(-1,1,2));
			params.addValue("valueFlag", 1);
			params.addValue(VALUE, "");
			List<Map<String,Object>> couponApplicableModeValuesList = posReadonlyNPJdbcTemplate.queryForList(GET_COUPON_APPLICABLE_MODE_MAPPING, params);
			if(UtilValidate.isNotEmpty(couponApplicableModeValuesList)){
				couponApplicableModeValues = new ArrayList<>();
				for(Map<String,Object> eachRowData : couponApplicableModeValuesList){
					if(eachRowData != null && eachRowData.get(VALUE) != null) {
						couponApplicableModeValues.add((String)eachRowData.get(VALUE));
						existPromotionType = (int) eachRowData.get("PromotionType");
					}
					
				}
			}
		}catch(DataAccessException dae){
			log.error("Exception Occured :", dae);
		}
		couponApplicableModeValuesMap.put("couponApplicableModeValues", couponApplicableModeValues);
		couponApplicableModeValuesMap.put("existPromotionType", existPromotionType);
		return couponApplicableModeValuesMap;
	}
	
	public static int saveCouponApplicableModeMapping(long promotionId, int promotionType, Map<CouponApplicableMode, List<String>> couponModes, NamedParameterJdbcTemplate posNPJdbcTemplate){
		if(UtilValidate.isNotEmpty(couponModes)){
			List<Map<String, Object>> batchParams = new ArrayList<>();
			for (Entry<CouponApplicableMode, List<String>> cMode : couponModes.entrySet()) {
			    if (UtilValidate.isNotEmpty(cMode) && UtilValidate.isNotEmpty(cMode.getValue())) {
			        for (String modeValue : cMode.getValue()) {
			        	Map<String, Object> params = new HashMap<>();
			        	params.put(PROMOTION_ID, promotionId);
			        	params.put(PROMOTION_TYPE, promotionType);
			        	params.put("Mode", cMode.getKey().toString());
			        	params.put(VALUE, modeValue);
			            batchParams.add(params);
			        }
			    }
			}
			log.info("CouponModes : {}", batchParams);
			int[] updatedRows = posNPJdbcTemplate.batchUpdate(INSERT_INTO_COUPON_APPLICABLE_MODE_MAPPING_QRY, batchParams.toArray(new Map[batchParams.size()]));
			return Arrays.stream(updatedRows).sum();
		}
		return 0;
	}

	private static final String POS_CHANNEL_QUERY = "SELECT channel from tbl_promotion_channel_mapping where PromotionId=:PromotionId and PromotionType in (:PromotionType) and channel = 1";
	
	public static boolean isPosChannel(long campaignId, Set<String> campaignTypes, NamedParameterJdbcTemplate posNPJdbcTemplate) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PROMOTION_ID, campaignId);
		params.addValue(PROMOTION_TYPE, campaignTypes);
		return posNPJdbcTemplate.query(POS_CHANNEL_QUERY, params, (rs)-> {
			return rs.next();
		});
	}
}
