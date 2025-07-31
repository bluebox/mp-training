package com.medplus.marketing.validators;

import org.apache.poi.ss.usermodel.Cell;

import com.medplus.discounts.PromotionException;
import com.medplus.marketing.domain.CampaignProduct;
import com.medplus.marketing.util.CampaignUtil;

public class ProductIdValidator implements CampaignColumnValidator {
	
	private boolean isSpecialityBased;
	public ProductIdValidator(boolean isSpecialityBased) {
		this.isSpecialityBased = isSpecialityBased;
	}
	
	@Override
	public void validateAndSet(Cell cell, int applicableType, int campaignType, CampaignProduct campProduct) {
		if(cell == null) {
			throw new PromotionException("ID is empty");
		}
		String id = CampaignUtil.getProdIdFromCell(cell);
		if (!isSpecialityBased && CampaignUtil.validateProductId(id, applicableType)) {
			throw new PromotionException("Invalid ID entered");
		}
		campProduct.setProductId(id);
	}
}
