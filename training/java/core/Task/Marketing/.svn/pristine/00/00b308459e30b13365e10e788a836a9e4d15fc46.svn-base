package com.medplus.marketing.validators;

import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;

import com.medplus.discounts.PromotionException;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.marketing.domain.CampaignProduct;

public class DiscountTypeValidator implements CampaignColumnValidator{

	private Map<String, Integer> discountTypeMap;
	public DiscountTypeValidator(Map<String, Integer> discountTypeMap) {
		this.discountTypeMap = discountTypeMap;
	}
	
	@Override
	public void validateAndSet(Cell cell, int applicableType, int campaignType, CampaignProduct campProduct) {
		if(cell == null || cell.getCellType() != Cell.CELL_TYPE_STRING) {
			throw new PromotionException("Invalid DiscountType");
		}
		
		Integer discountTypeId = discountTypeMap.get(cell.getStringCellValue().trim());
		if (discountTypeId == null) {
			throw new PromotionException("Incorrect value of Discount Type found");
		} 
		if(Integer.parseInt(PromotionConstants.PROMOTION_ADDON) == campaignType &&
				discountTypeId != PromotionConstants.SPECIAL_DISCOUNT_TYPE_REDUCTION_PERCENTAGE) {
			throw new PromotionException("Promotion Addon must be configured as a percentage discount only");
		}
		campProduct.setDiscountType(discountTypeId);

	}

}
