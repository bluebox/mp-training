package com.medplus.marketing.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.medplus.discounts.PromotionException;
import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignProduct;
import com.medplus.marketing.helper.ProductStoreHelper;
import com.medplus.marketing.util.CampaignUtil;
import com.medplus.marketing.validators.CampaignColumnValidator;
import com.medplus.marketing.validators.CampaignRowValidator;
import com.medplus.marketing.validators.CampaignValidators;
import com.medplus.marketing.validators.DisplayMessageValidator;
import com.medplus.marketing.validators.PayBackPercentageValidator;
import com.medplus.marketing.validators.ProductIdValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SupplementaryExcelReader implements CampaignExcelReader {
	
	@Autowired
	private ProductStoreHelper productStoreHelper;
	
	@Value("${com.medplus.marketing.promotions.paybackPercentage.max.allowed:0.0}")
	private double paybackPercentageMaxAllowed;
	
	private List<CampaignColumnValidator> getColumnValidators(Campaign campaign) {
		return new ArrayList<>(Arrays.asList(
				new ProductIdValidator(campaign.getUserMetaData().isSpecialtyBased()),
				CampaignValidators.FROM_QUANTITY_VALIDATOR,
				CampaignValidators.TO_PRODUCT_ID_VALIDATOR,
				CampaignValidators.TO_QUANTITY_VALIDATOR,
				new DisplayMessageValidator(campaign.getChannels(), campaign.getLoyaltyTypes()),
				new PayBackPercentageValidator(paybackPercentageMaxAllowed), 
				CampaignValidators.PRICE_CONSIDERED_FOR_SLAB,
				CampaignValidators.STORE_ID_VALIDATOR
			));
	}
	
	private static CampaignProduct validateAndGetCampaignProduct(Row row, List<CampaignColumnValidator> colValidators, int applicableType, int campType) {
		if(row.getPhysicalNumberOfCells() < 5) { 
			throw new PromotionException("Number of columns should be min 5 but found " + row.getPhysicalNumberOfCells()
							+ " in product upload excel file at row " + (row.getRowNum() + 1));
		}
		CampaignRowValidator rowValidator = new CampaignRowValidator(colValidators, applicableType, campType);
		rowValidator.validate(row);
		return rowValidator.getCampaignProduct();
	}
	
	@Override
	public Map<String, List<CampaignProduct>> readAndGroupByStore(Sheet sheet, Campaign campaign) {

		Map<String, List<CampaignProduct>> grpByStoresInfo = new HashMap<>();
		Set<String> storeProductkey = new HashSet<>();
		List<CampaignColumnValidator> colValidators = getColumnValidators(campaign);
		Map<String, Map<String, String>> storeProdSlabInfo = new HashMap<>();
		for (Row row : sheet) {
			if (row.getRowNum() == 0)
				continue;
			CampaignProduct campProd = validateAndGetCampaignProduct(row, colValidators, campaign.getPromotionApplicableType(), campaign.getCampaignType());
			if(!storeProductkey.add(campProd.getStoreId() + "_" + campProd.getProductId())) {
				throw new PromotionException("Only one record is allowed for " + campProd.getStoreId() + " with " + campProd.getProductId()+ " for supplementary");
			}
			CampaignUtil.checkConflictPriceSlabs(storeProdSlabInfo, campProd, true);
			grpByStoresInfo.computeIfAbsent(campProd.getStoreId(), k -> new ArrayList<>()).add(campProd);
		}
		validateGrpByStores(grpByStoresInfo, campaign.getPromotionApplicableType(),
				campaign.getUserMetaData().isSpecialtyBased(), campaign.getCampaignType());
		return grpByStoresInfo;
	}

	@Override
	public Map<String, List<CampaignProduct>> readAndGroupByProduct(Sheet sheet, Campaign campaign) {
		Map<String, List<CampaignProduct>> groupByProductInfo = new HashMap<>();
		Set<String> storeProductkey = new HashSet<>();
		Map<String, CampaignProduct> productQuantityMap = new HashMap<>();
		List<CampaignColumnValidator> colValidators = getColumnValidators(campaign);
		Map<String, Map<String, String>> itemProdSlabInfo = new HashMap<>();
		for (Row row : sheet) {
			if (row.getRowNum() == 0)
				continue;
			CampaignProduct campProd = validateAndGetCampaignProduct(row, colValidators, campaign.getPromotionApplicableType(), campaign.getCampaignType());
			if(!storeProductkey.add(campProd.getStoreId() + "_" + campProd.getProductId())) {
				throw new PromotionException("Only one record is allowed for " + campProd.getProductId() + " with " + campProd.getStoreId()+ " for supplementary");
			}
			CampaignProduct tempCampaignProduct = new CampaignProduct();
			BeanUtils.copyProperties(campProd, tempCampaignProduct);
			tempCampaignProduct.setStoreId(null);
			CampaignProduct existingCampaignProduct = productQuantityMap.putIfAbsent(campProd.getProductId(), tempCampaignProduct);
			if(existingCampaignProduct != null && !existingCampaignProduct.equals(tempCampaignProduct)) {
				throw new PromotionException("All columns must be same for product with id " + campProd.getProductId() + " across all stores");
			}
			CampaignUtil.checkConflictPriceSlabs(itemProdSlabInfo, campProd, false);
			groupByProductInfo.computeIfAbsent(campProd.getProductId(), k -> new ArrayList<>()).add(campProd);
		}
		validateGrpByProducts(groupByProductInfo, campaign.getPromotionApplicableType(),
				campaign.getUserMetaData().isSpecialtyBased());
		return groupByProductInfo;
	}

	private void validateGrpByStores(Map<String, List<CampaignProduct>> grpByStoresInfo, int applicableType, boolean isSpecialtyBased, int campaignType) {
		Set<String> storeIds = grpByStoresInfo.keySet();		
		Set<String> productIds = CampaignUtil.getCampaignProducts(grpByStoresInfo.values().stream().
				flatMap(List::stream).collect(Collectors.toList()), campaignType);
		productStoreHelper.validateStoresAndProducts(storeIds, productIds, applicableType, isSpecialtyBased);
	}
	
	private void validateGrpByProducts(Map<String, List<CampaignProduct>> groupByProductInfo, int applicableType, boolean isSpecialtyBased) {
		Set<String> productIds = new HashSet<>(groupByProductInfo.keySet());		
		Set<String> toProductIds = 	groupByProductInfo.values().stream().flatMap(List::stream)
				.map(CampaignProduct::getToProductId).flatMap(toProductId -> Arrays.stream(toProductId.split(","))).collect(Collectors.toSet());
		log.debug("toProductIds {} ", toProductIds);
		productIds.addAll(toProductIds);
		
		Set<String> storeIds = groupByProductInfo.values().stream().flatMap(List::stream)
				.map(CampaignProduct::getStoreId).collect(Collectors.toSet());
		productStoreHelper.validateStoresAndProducts(storeIds, productIds, applicableType, isSpecialtyBased);
	}
	
}
