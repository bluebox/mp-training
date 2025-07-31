package com.medplus.marketing.util;

import org.apache.poi.ss.usermodel.Cell;

import com.medplus.discounts.PromotionException;
import com.medplus.discounts.constants.PromotionConstants;

public class Validators {
	
	private Validators () {}
	
	public static final ColumnValidator<String> REFERENCE_PRODUCT_ID_VALIDATOR = (cell, rowNum) -> {
		if(cell==null) {
			return null;
		}
		String productId = CampaignUtil.getProdIdFromCell(cell);
		if (!CampaignUtil.validateProductId(productId, PromotionConstants.APPLICABLE_TYPE_PHARMACY)) {
			return productId;
		} 
		throw new PromotionException("Invalid ProductId entered at row "+ rowNum);
	};

	public static final ColumnValidator<Integer> COMPOSITION_ID_VALIDATOR = (cell, rowNum) -> {
		if(cell==null) {
			return null;
		}
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && (int)cell.getNumericCellValue() >= 0) {
			return (int)cell.getNumericCellValue();
		} 
		throw new PromotionException("Invalid CompositionId entered at row "+ rowNum);
	};
	
	public static final ColumnValidator<Long> CUSTOMER_ID_VALIDATOR = (cell, rowNum) -> {
		if(cell==null) {
			return null;
		}
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			Long customerId = (long)cell.getNumericCellValue();
			if (customerId <= 0 || customerId.toString().length() > 9) {
				throw new PromotionException("CustomerId should be greater than 0 and length less than 10 at row "+ rowNum);
			}
			return customerId;
		} 
		throw new PromotionException("Invalid CustomerId entered at row "+ rowNum);
	};
		
}
