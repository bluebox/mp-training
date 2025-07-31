package com.medplus.marketing.validators;

import org.apache.poi.ss.usermodel.Cell;

import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.marketing.util.CampaignUtil;

public class CampaignValidators {

	/**
	 * provides validators for campaign related data
	 */
	private CampaignValidators() {}
	
	private static int validateAndGetQuantity(String fieldName, Cell cell) {

		if(cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			throw new PromotionException("Invalid Type of " + fieldName +" entered");
		double quantity = cell.getNumericCellValue();
		if (quantity <= 0 || quantity % 1 != 0) { 
	        throw new PromotionException(fieldName + " should be greater than 0 and should not be a decimal number");
	    }
		return (int) quantity;
	}
	
	public static final CampaignColumnValidator STORE_ID_VALIDATOR = (cell, applicableType, campaignType, campProduct) -> {
		if(cell == null || cell.getCellType() != Cell.CELL_TYPE_STRING) {
			throw new PromotionException("StoreId is invalid");
		}
		String storeID = cell.getStringCellValue().toUpperCase().trim();
		if (storeID.length() == 12) {
			campProduct.setStoreId(storeID);
		} else {
			throw new PromotionException("Invalid StoreId");
		}
	};
	
	private static void isValidToProductID(String fromProductId, String toProductId, int campaignType) {
		if(campaignType == 5 && !fromProductId.equals(toProductId)) {
			throw new PromotionException("FromProductId should be the same as ToProductId");
		}
		if(campaignType == 6 && fromProductId.equals(toProductId)) {
			throw new PromotionException("FromProductId should not be the same as ToProductId");
		}
	}

	public static final CampaignColumnValidator TO_PRODUCT_ID_VALIDATOR = (cell, applicableType, campaignType, campProduct) -> {
		if(cell == null) {
			throw new PromotionException("ToProductID is null");
		}
		String toProductID = CampaignUtil.getProdIdFromCell(cell);
		if (UtilValidate.isEmpty(toProductID) || toProductID.contains("'") || toProductID.contains("\"")) {
		    throw new PromotionException("Invalid ToProductID");
		}

		if(campaignType == 6) {
			String[] toProductIds = toProductID.split(",");
            for (int i = 0; i < toProductIds.length; i++) {
				String productId = toProductIds[i].trim();
				if (productId.length() < 8) {
					throw new PromotionException("Invalid ToProductID exist in the toProducts list for product : " + productId);
				}
				toProductIds[i] = productId;
            }
			toProductID = String.join(",", toProductIds).trim();
		}
		isValidToProductID(campProduct.getProductId(), toProductID, campaignType);
		campProduct.setToProductId(toProductID);
		
	};
	
	public static final CampaignColumnValidator FROM_QUANTITY_VALIDATOR = (cell, applicableType, campaignType, campProduct) -> {
		if(cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			throw new PromotionException("Invalid FromQuantity");

		double fromQuantity = cell.getNumericCellValue();
		if (applicableType != PromotionConstants.APPLICABLE_TYPE_PATHLABS) {
		    if (fromQuantity <= 0 || fromQuantity % 1 != 0) { 
		        throw new PromotionException("FromQuantity should be greater than 0 and should not be a decimal number");
		    }
		} else {
			if ((int)fromQuantity > 1) {
				throw new PromotionException("FromQuantity should be 1 for PathLabs");
			}
		}
		campProduct.setFromQuantity((int) fromQuantity);
	};

	public static final CampaignColumnValidator TO_QUANTITY_VALIDATOR = (cell, applicableType, campaignType, campProduct) -> {
		if(cell == null)
			throw new PromotionException("ToQuantity is empty");
		campProduct.setToQuantity(validateAndGetQuantity("ToQuantity", cell));

	};
	
	public static final CampaignColumnValidator MAX_QUANTITY_VALIDATOR = (cell, applicableType, campaignType, campProduct) -> {
		if(cell != null)
			campProduct.setToQuantity(validateAndGetQuantity("MaxQuantity", cell));
	};
	
	public static final CampaignColumnValidator PRICE_CONSIDERED_FOR_SLAB = (cell, applicableType, campaignType, campProduct) -> {
		if (UtilValidate.isNotEmpty(cell) ) {
			if(cell.getCellType()!=Cell.CELL_TYPE_STRING) {
				throw new PromotionException("Invalid Type of PriceConsiderForSlab found");
			}
			String priceConsiderForSlab = cell.getStringCellValue().trim().toUpperCase();
			if (UtilValidate.isNotEmpty(priceConsiderForSlab)) {
				if ("M".equals(priceConsiderForSlab) || "S".equals(priceConsiderForSlab))
					campProduct.setPriceConsiderForSlab(priceConsiderForSlab);
				else
					throw new PromotionException("Incorrect PriceConsiderForSlab value found");
			}
			
		}
	};
		
	public static final CampaignColumnValidator DISCOUNT_VALUE_VALIDATOR = (cell, applicableType, campaignType, campProduct) -> {
		
		if(cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			throw new PromotionException("Invalid Discount Quantity");
		
		double discountValue = cell.getNumericCellValue();
		if (campaignType == 3 && campProduct.getDiscountType() == 2 && discountValue > 100) 
			throw new PromotionException("Discountpercentage can not be greater than 100");
		campProduct.setDiscountValue(discountValue);
	};
	
}
