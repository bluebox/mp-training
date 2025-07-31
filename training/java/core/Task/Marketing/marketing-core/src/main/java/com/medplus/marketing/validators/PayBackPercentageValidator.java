package com.medplus.marketing.validators;

import org.apache.poi.ss.usermodel.Cell;

import com.medplus.discounts.PromotionException;
import com.medplus.marketing.domain.CampaignProduct;

public class PayBackPercentageValidator implements CampaignColumnValidator {

	private double paybackPercentageMaxAllowed;
	public PayBackPercentageValidator(double paybackPercentageMaxAllowed) {
		this.paybackPercentageMaxAllowed = paybackPercentageMaxAllowed;
	}

	@Override
	public void validateAndSet(Cell cell, int applicableType, int campaignType, CampaignProduct campProduct) {
		if(cell == null)
			return;
		if(cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			throw new PromotionException("Invalid Payback Percentage found");
		Double paybackPercentage = cell.getNumericCellValue();
		if (paybackPercentage < 0) {
			throw new PromotionException("Payback Percentage Value should be greater than or equal to 0");
		}
		if (paybackPercentageMaxAllowed > 0 && paybackPercentage > paybackPercentageMaxAllowed) {
			throw new PromotionException(
					"Payback Percentage Value should be less than or equal to Maximum Allowed : "
							+ paybackPercentageMaxAllowed);
		}
		campProduct.setPaybackPercentage(paybackPercentage);
	
	}


}
