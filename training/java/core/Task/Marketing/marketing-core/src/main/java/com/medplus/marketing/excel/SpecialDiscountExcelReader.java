package com.medplus.marketing.excel;

import java.util.ArrayList;
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

import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignProduct;
import com.medplus.marketing.helper.ProductStoreHelper;
import com.medplus.marketing.service.CampaignService;
import com.medplus.marketing.util.CampaignUtil;
import com.medplus.marketing.validators.CampaignColumnValidator;
import com.medplus.marketing.validators.CampaignRowValidator;
import com.medplus.marketing.validators.CampaignValidators;
import com.medplus.marketing.validators.DiscountTypeValidator;
import com.medplus.marketing.validators.DisplayMessageValidator;
import com.medplus.marketing.validators.PayBackPercentageValidator;
import com.medplus.marketing.validators.ProductIdValidator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Component
public class SpecialDiscountExcelReader implements CampaignExcelReader {
	
	private static final String PRODUCT_MISMATCH_MSG = "FromQuantity, DiscountType and DiscountValue and Other Columns must be the same for %s across all stores";
	
	@Autowired
	private CampaignService campaignService;
	
	@Autowired
	private ProductStoreHelper productStoreHelper;
	
	@Value("${com.medplus.marketing.promotions.paybackPercentage.max.allowed:0.0}")
	private double paybackPercentageMaxAllowed;
	
	@Getter
	@AllArgsConstructor
	@EqualsAndHashCode
	public static class ProductKey {
        private String storeId;
        private String productId;
        private int fromQuantity;
	}
	
	private List<CampaignColumnValidator> getColumnValidators(Campaign campaign) {
		Map<String, Integer> discountTypeMap = campaignService.findConditionTypes(campaign.getCampaignType());
		List<CampaignColumnValidator> colValidators = new ArrayList<>();
		colValidators.add(new ProductIdValidator(campaign.getUserMetaData().isSpecialtyBased()));
		colValidators.add(CampaignValidators.FROM_QUANTITY_VALIDATOR);
		colValidators.add(new DiscountTypeValidator(discountTypeMap));
		colValidators.add(CampaignValidators.DISCOUNT_VALUE_VALIDATOR);
		colValidators.add(CampaignValidators.MAX_QUANTITY_VALIDATOR);
		colValidators.add(new DisplayMessageValidator(campaign.getChannels(), campaign.getLoyaltyTypes()));
		if(campaign.getPromotionApplicableType() == PromotionConstants.APPLICABLE_TYPE_PHARMACY) {
			colValidators.add(new PayBackPercentageValidator(paybackPercentageMaxAllowed));
		}
		colValidators.add(CampaignValidators.PRICE_CONSIDERED_FOR_SLAB);
		colValidators.add(CampaignValidators.STORE_ID_VALIDATOR);
		return colValidators;
	}
	
	@Override
	public Map<String, List<CampaignProduct>> readAndGroupByStore(Sheet sheet, Campaign campaign) {
		
		Map<String, List<CampaignProduct>> storeProductInfo = new HashMap<>();
		List<CampaignColumnValidator> colValidators = getColumnValidators(campaign);
		Set<ProductKey> storeProdQuntkey = new HashSet<>();	
		Map<String, Map<String, String>> storeProdSlabInfo = new HashMap<>();
		for (Row row : sheet) {
			if (row.getRowNum() == 0)	
				continue;
			if(row.getPhysicalNumberOfCells() < 5) { 
				throw new PromotionException("Number of columns should be min 5 but found " + row.getPhysicalNumberOfCells()
								+ " in product upload excel file at row " + (row.getRowNum() + 1));
			}
			checkPayBackColumnPresent(row, campaign);
			CampaignRowValidator rowValidator = new CampaignRowValidator(colValidators, campaign.getPromotionApplicableType(), campaign.getCampaignType());
			rowValidator.validate(row);
			
			CampaignProduct campProd = rowValidator.getCampaignProduct();
			if(!storeProdQuntkey.add(new ProductKey(campProd.getStoreId(), campProd.getProductId(), campProd.getFromQuantity()))) {
				throw new PromotionException(String.format("Duplicate entry for %s with quantity %s found against store %s", 
						campProd.getProductId(), campProd.getFromQuantity(), campProd.getStoreId()));
			}
			CampaignUtil.checkConflictPriceSlabs(storeProdSlabInfo, campProd, true);
			storeProductInfo.computeIfAbsent(campProd.getStoreId(), k -> new ArrayList<>()).add(campProd);
		}
		validateGrpByStores(storeProductInfo, campaign.getPromotionApplicableType(),
				campaign.getUserMetaData().isSpecialtyBased());
		return storeProductInfo;
	}
	
	private static void checkPayBackColumnPresent(Row row, Campaign campaign) {
		if ((campaign.getPromotionApplicableType() == PromotionConstants.APPLICABLE_TYPE_PATHLABS
				|| campaign.getPromotionApplicableType() == PromotionConstants.APPLICABLE_TYPE_LENS) 
				&& (row.getPhysicalNumberOfCells() > 8 || UtilValidate.isNotEmpty(row.getCell(8)))) {
				throw new PromotionException(
						"paybackPercentage is  not allowed for ApplicableType PATHLABS/LENS at row : "
								+ (row.getRowNum() + 1)
								+ " ,Please check in product upload excel file");
		}
	}

	@Override
	public Map<String, List<CampaignProduct>> readAndGroupByProduct(Sheet sheet, Campaign campaign) {
		Map<String, List<CampaignProduct>> grpByProductInfo = new HashMap<>();
		List<CampaignColumnValidator> colValidators = getColumnValidators(campaign);
			
		Map<String, Map<Integer, Set<String>>> prodQuantStoresInfo = new HashMap<>();
		/** prodDiscountInfo - {key : product + quantity ; value : CampaignProduct } */
		Map<String, CampaignProduct> prodDiscountInfo = new HashMap<>();
		Map<String, Map<String, String>> itemProdSlabInfo = new HashMap<>();
		for (Row row : sheet) {
			if (row.getRowNum() == 0)
				continue;
			checkPayBackColumnPresent(row, campaign);
			if(row.getPhysicalNumberOfCells() < 5) { 
				throw new PromotionException("Number of columns should be min 5 but found " + row.getPhysicalNumberOfCells()
								+ " in product upload excel file at row " + (row.getRowNum() + 1));
			}
			CampaignRowValidator rowValidator = new CampaignRowValidator(colValidators, campaign.getPromotionApplicableType(), campaign.getCampaignType());
			rowValidator.validate(row);
			CampaignProduct campProd = rowValidator.getCampaignProduct();
			
			CampaignProduct tempCampaignProduct = new CampaignProduct();
			BeanUtils.copyProperties(campProd, tempCampaignProduct);
			tempCampaignProduct.setStoreId(null);
			CampaignProduct exiCampaignProduct = prodDiscountInfo.putIfAbsent(campProd.getProductId() + "_" + campProd.getFromQuantity(), 
					tempCampaignProduct);
			if (exiCampaignProduct != null && !exiCampaignProduct.equals(tempCampaignProduct)){
        		throw new PromotionException(String.format(PRODUCT_MISMATCH_MSG, campProd.getProductId()));
			}
			
			boolean isStoreAdded = prodQuantStoresInfo
					.computeIfAbsent(campProd.getProductId(), quantStores -> new HashMap<>())
					.computeIfAbsent(campProd.getFromQuantity(), stores -> new HashSet<>())
					.add(campProd.getStoreId());
			
			if(!isStoreAdded) {
				throw new PromotionException(String.format("Duplicate entry for %s with %s and quantity %s found at row %s",
						campProd.getStoreId(), campProd.getProductId(), campProd.getFromQuantity(), (row.getRowNum()+1)));
			}
			CampaignUtil.checkConflictPriceSlabs(itemProdSlabInfo, campProd, false);
			grpByProductInfo.computeIfAbsent(campProd.getProductId(), k -> new ArrayList<>()).add(campProd);
		}
		validateprodQuantStoresInfo(prodQuantStoresInfo);
		validateGrpByProducts(grpByProductInfo, campaign.getPromotionApplicableType(),	
				campaign.getUserMetaData().isSpecialtyBased());
		return grpByProductInfo;
	}
	

	private void validateprodQuantStoresInfo(Map<String, Map<Integer, Set<String>>> prodQuantStoresInfo) {
		prodQuantStoresInfo.forEach((productId, quantityStoresInfo) -> {
	        Set<String> configuredStores = null;  
			for(Set<String> store : quantityStoresInfo.values()) {
	        	if(configuredStores!=null && !configuredStores.equals(store)) {
	        		throw new PromotionException(String.format(PRODUCT_MISMATCH_MSG, productId));
	        	}
	        	configuredStores = store;
			}
	    });
	}

	private void validateGrpByStores(Map<String, List<CampaignProduct>> grpByStoresInfo, int applicableType, boolean isSpecialtyBased) {
		Set<String> productIds = grpByStoresInfo.values().stream().flatMap(List::stream)
				.map(CampaignProduct::getProductId).collect(Collectors.toSet()); 
		Set<String> storeIds = grpByStoresInfo.keySet();		
		productStoreHelper.validateStoresAndProducts(storeIds, productIds, applicableType, isSpecialtyBased);

	}

	private void validateGrpByProducts(Map<String, List<CampaignProduct>> grpByProductInfo, int applicableType, boolean isSpecialtyBased) {
		Set<String> productIds = grpByProductInfo.keySet();		
		Set<String> storeIds = grpByProductInfo.values().stream().flatMap(List::stream).map(CampaignProduct::getStoreId)
		        .collect(Collectors.toSet());
		productStoreHelper.validateStoresAndProducts(storeIds, productIds, applicableType, isSpecialtyBased);
	}
}
