package com.medplus.marketing.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.medplus.discounts.PromotionException;
import com.medplus.marketing.constants.CampaignConstants;
import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignProduct;
import com.medplus.marketing.exception.MarketingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelReaderContext {
	
	private static final String EXCEPTION_OCCURED = "Exception Occurred : ";

	private final CampaignExcelReader excelReader;
	public ExcelReaderContext(CampaignExcelReader excelReader) {
		this.excelReader = excelReader;
	}
	
	public Map<String, List<CampaignProduct>> read(Campaign campaign, MultipartFile productUploadExcel) {
		if (productUploadExcel == null || productUploadExcel.isEmpty())
			throw new PromotionException("Product upload file is empty");
		String fileName = productUploadExcel.getOriginalFilename();
		if (!(fileName.endsWith(".xls") || fileName.endsWith(".xlsx"))) {
			throw new MarketingException("Invalid " + fileName + " File! Please Upload .xls/.xlsx file");
		}
		try (Workbook workbook = fileName.endsWith(".xls") ? new HSSFWorkbook(productUploadExcel.getInputStream()) : new XSSFWorkbook(productUploadExcel.getInputStream())){
			Sheet sheet = workbook.getSheetAt(0);
			if(sheet.getPhysicalNumberOfRows() > 100001) {
				throw new PromotionException("Number of rows should not be more than 1 lakh");
			}
			return CampaignConstants.SPLIT_BY_PRODUCT.equalsIgnoreCase(campaign.getSplitBy()) ?
					excelReader.readAndGroupByProduct(sheet, campaign) :
					excelReader.readAndGroupByStore(sheet, campaign);
		} catch(PromotionException e){
			log.error(EXCEPTION_OCCURED, e);
			throw new PromotionException(e.getMessage());
		} catch(Exception e){
			log.error(EXCEPTION_OCCURED, e);
			throw new PromotionException("Invalid product upload file");
		}
	}
	
	
}
