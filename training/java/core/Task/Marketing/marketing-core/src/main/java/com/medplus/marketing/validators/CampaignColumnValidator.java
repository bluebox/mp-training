package com.medplus.marketing.validators;

import org.apache.poi.ss.usermodel.Cell;

import com.medplus.marketing.domain.CampaignProduct;

@FunctionalInterface
public interface CampaignColumnValidator {
	void validateAndSet(Cell cell, int applicableType, int campaignType, CampaignProduct campProduct);
}
