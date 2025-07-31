package com.medplus.marketing.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignProduct;

public interface CampaignExcelReader {
	Map<String, List<CampaignProduct>> readAndGroupByStore(Sheet sheet, Campaign campaign);
	Map<String, List<CampaignProduct>> readAndGroupByProduct(Sheet sheet, Campaign campaign);
}
