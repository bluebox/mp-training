package com.medplus.marketing.validators;

import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.marketing.domain.CampaignProduct;

public class DisplayMessageValidator implements CampaignColumnValidator {
	private List<Integer> channels = new LinkedList<>();
	private List<Integer> loyaltyTypes = new LinkedList<>();

	public DisplayMessageValidator(List<Integer> channels, List<Integer> loyaltyTypes) {
		this.channels = channels;
		this.loyaltyTypes = loyaltyTypes;
	}
	
	private static void validateDisplayMessage(int campaignType, List<Integer> loyaltyTypes, int fromQuantity) {
		if (campaignType == 5 || campaignType == 6) {
			throw new PromotionException("Display Message is needed when channel is mart or mobile");
		}
		if ((loyaltyTypes.size() > 1 || (!loyaltyTypes.isEmpty() && loyaltyTypes.get(0) != 1))) {
			throw new PromotionException("Display Message is needed when channel is mart or mobile and the loyalty type is other than regular");
		}
		if (!loyaltyTypes.isEmpty() && fromQuantity > 1) {
			throw new PromotionException("Display Message is needed when channel is mart or mobile, the loyalty type is regular and FromQuantity is greater than 1");
		}
	}
	
	@Override
	public void validateAndSet(Cell cell, int applicableType, int campaignType, CampaignProduct campProduct) {
		if (UtilValidate.isEmpty(cell) || UtilValidate.isEmpty(cell.getStringCellValue().trim())) {
			if ((channels.contains(2) || channels.contains(4))) {
				validateDisplayMessage(campaignType, loyaltyTypes, campProduct.getFromQuantity());
			}
		} else {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String displayMessage = cell.getStringCellValue().trim();
			if (!UtilValidate.validateStringWithLength(displayMessage, 255))
				throw new PromotionException("Display Message should be maximum of 255 charactors.");
			campProduct.setDisplayMessage(cell.getStringCellValue().trim());
		}
	}
}
