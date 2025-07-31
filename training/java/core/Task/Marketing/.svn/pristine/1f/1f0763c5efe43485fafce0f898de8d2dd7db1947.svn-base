package com.medplus.marketing.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.marketing.constants.CampaignConstants;
import com.medplus.marketing.exception.MarketingException;
import com.medplus.marketing.util.CampaignsGroupByProduct;
import com.medplus.marketing.util.CampaignsGroupByStore;
import com.medplus.marketing.util.MultipleCampaignsGenerator;

@Component
public class ExcelBeanFactory {

	private final ApplicationContext context;
	
	@Autowired
	public ExcelBeanFactory(ApplicationContext context) {
		this.context = context;
	}
	
	public CampaignExcelReader getBeansByType(int campaignType) {
		Class<? extends CampaignExcelReader> excelReader = null;
		if(campaignType == Integer.parseInt(PromotionConstants.SUPPLIMENT_QUANTITY) 
				|| campaignType == Integer.parseInt(PromotionConstants.SUPPLEMENT_ITEM)) {
			excelReader = SupplementaryExcelReader.class;
		} else if(campaignType == Integer.parseInt(PromotionConstants.SPECIAL_DISCOUNT) 
				|| campaignType == Integer.parseInt(PromotionConstants.SPECIAL_POINTS) 
				|| campaignType == Integer.parseInt(PromotionConstants.PROMOTION_ADDON) ) {
			excelReader = SpecialDiscountExcelReader.class;
		}
		if(excelReader!=null) {
			return context.getBean(excelReader);
		} else {
			throw new MarketingException("Invalid Campaign Type");
		}
	}
	
	public MultipleCampaignsGenerator getSplitByBeans(String splitBy) {
		Class<? extends MultipleCampaignsGenerator> campaignGenerator = null;

		if(CampaignConstants.SPLIT_BY_STORE.equalsIgnoreCase(splitBy)) {
			campaignGenerator = CampaignsGroupByStore.class;
		} else if(CampaignConstants.SPLIT_BY_PRODUCT.equalsIgnoreCase(splitBy)) {
			campaignGenerator = CampaignsGroupByProduct.class;
		} 
		
		if(campaignGenerator!=null) {
			return context.getBean(campaignGenerator);
		} else {
			throw new MarketingException("Invalid SplitBy Type");
		}
		
	}
	
}
