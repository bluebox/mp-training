package com.medplus.marketing.validators;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import com.medplus.discounts.PromotionException;
import com.medplus.marketing.domain.CampaignProduct;

import lombok.Data;

@Data
public class CampaignRowValidator {
	
	private List<CampaignColumnValidator> campaignColumnValidator;
	private final CampaignProduct campaignProduct;
	private int applicableType;
	private int campaignType;
	
	
	public CampaignRowValidator(List<CampaignColumnValidator> campaignColumnValidator, int applicableType, int campaignType) {
		this.campaignColumnValidator = campaignColumnValidator;
		this.applicableType = applicableType;
		this.campaignType = campaignType;
		this.campaignProduct = new CampaignProduct();
	}
	
    public void validate(Row row) {
        for (int i = 0; i < campaignColumnValidator.size(); i++) {
        	try {
        		campaignColumnValidator.get(i).validateAndSet(row.getCell(i), applicableType, campaignType, campaignProduct);
        	} catch (PromotionException e) {
        		throw new PromotionException(e.getMessage() + ". Please check at col " + (i+1) + " of row " + row.getRowNum());
        	}
        }
    }
   
}
