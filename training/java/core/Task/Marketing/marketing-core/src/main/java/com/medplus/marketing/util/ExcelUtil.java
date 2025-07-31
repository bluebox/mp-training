package com.medplus.marketing.util;

import static com.medplus.marketing.constants.CommonConstants.Y;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.discounts.domain.ComplimentaryProduct;
import com.medplus.marketing.domain.Campaign;
import com.medplus.marketing.domain.CampaignProduct;
import com.medplus.marketing.domain.ComplimentaryPromotion;
import com.medplus.marketing.exception.MarketingException;
import com.medplus.popup.constants.ApplicableType;
import com.medplus.popup.constants.ConfigurationTriggerType;
import com.medplus.popup.constants.CustomerSelection;
import com.medplus.popup.domain.CustomerDefinedTriggerRule;
import com.medplus.popup.domain.DefinedTriggerRule;
import com.medplus.popup.domain.PopUpConfiguration;
import com.medplus.popup.domain.PopUpConfigurationRequest;
import com.medplus.popup.exception.PopUpException;
import com.medplus.popup.helper.PopUpConfigurationHelper;
import com.medplus.pos.util.PosUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUtil {

	private static final String XLSX = ".xlsx";
	private static final String ENTERED_IN = " entered in ";
	private static final String PLEASE_CHECK_THE_ROW_NUMBER = " file! please check in the row number ";
	private static final String EXCEPTION_OCCURED = "Exception Occurred : ";
	private static final String FILE_UPLOAD_ERROR_MESSAGE = " File! Please Upload .xls/.xlsx file";

	private ExcelUtil() {
	}
	
	public static <T> Set<T> readUploadExcel(MultipartFile uploadFile, Class<T> clazz, int applicableType, boolean isCustomerUpload) {
		return readExcelWithSpeciality(uploadFile, clazz, applicableType, false, isCustomerUpload);
	}

	public static <T> Set<T> readExcelWithSpeciality(MultipartFile uploadFile, Class<T> clazz, int applicableType, boolean isSpecialityBased, boolean isCustomersUpload) {

		Set<T> values = new HashSet<>();
		String fileType = isCustomersUpload ? "Customers" : "Products";

		if (UtilValidate.isEmpty(uploadFile)) {
			throw new MarketingException(fileType + " file is Empty");
		}
		String fileName = uploadFile.getOriginalFilename();
		if (!(fileName.endsWith(".xls") || fileName.endsWith(XLSX))) {
			throw new MarketingException(" Invalid " + fileName + " file ! Please Upload .xls /.xlsx file");
		}
		try (Workbook wb = fileName.endsWith(".xls") ? new HSSFWorkbook(uploadFile.getInputStream()) : new XSSFWorkbook(uploadFile.getInputStream())) {
			Sheet sheet = wb.getSheetAt(0);
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
				if (row.getLastCellNum() == -1) {
					throw new MarketingException("Atleast One column required in " + fileName + " at row " + (row.getRowNum() + 1));
				}
				Cell cell = row.getCell(0);
				if (cell == null) {
					throw new MarketingException("Null value at row " + (row.getRowNum() + 1));
				}
				if (!isCustomersUpload && !isSpecialityBased) {
					String productId = CampaignUtil.getProdIdFromCell(cell);
					if(CampaignUtil.validateProductId(productId, applicableType)) {
						throw new MarketingException("Invalid product " + productId + ENTERED_IN + fileName + " file!please check in the row number " + (row.getRowNum() + 1));
					}
					values.add(clazz.cast(productId));
				} else if (isCustomersUpload && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					long val = (long) cell.getNumericCellValue();
					if(val < 0) {
						throw new MarketingException("Customer Id must be a positive value ! please check in the file "+ fileName + " at row number "+ (row.getRowNum()+1));
					}
					values.add(clazz.cast(val));
				} else if(isSpecialityBased) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					T product = clazz.cast(cell.getStringCellValue().toUpperCase());
					if(UtilValidate.isEmpty(product)) {
						throw new MarketingException("Invalid product " + product + ENTERED_IN + fileName + " file! please check in row number " + (row.getRowNum() + 1));
						}
					values.add(product);
				} 
				else {
					throw new MarketingException("Invalid type entered in " + fileName + " please check in the row number " + (row.getRowNum() + 1));
				}
			}
		} catch (MarketingException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MarketingException("Unable to read "+ fileName + " file." , e);
		}
		return values;
	}
	
	public static <T> byte[] writeExcel(Set<T> data, String header, Map<T, String> itemMap) {

		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Excel Sheet");
			CellStyle boldStyle = getBoldCellStyle(workbook);

			Row headerRow = sheet.createRow(0);
			addCell(headerRow, 0, boldStyle, header);
			sheet.setColumnWidth(0, 3000);

			if (itemMap != null) {
				addCell(headerRow, 1, boldStyle, "ItemName");
				sheet.setColumnWidth(1, 8000);
			}
			
			int rowNum = 1;
			for (T item : data) {
				Row row = sheet.createRow(rowNum++);
				Cell cell = row.createCell(0);

				if (item instanceof String) {
					cell.setCellValue((String) item);
					if (itemMap != null) {
						cell = row.createCell(1);
						cell.setCellValue(itemMap.get(item));
					}
				} else if (item instanceof Long) {
					cell.setCellValue((Long) item);
				} else if (item instanceof Integer) {
					cell.setCellValue((Integer) item);
				}
			}
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			return outputStream.toByteArray();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return new byte[0];
	}
	
	private static boolean shouldUpdateDefinedTrigger(PopUpConfigurationRequest popupConfigurationRequest, MultipartFile uploadCustomerIdsExcel) {
		PopUpConfiguration popupConfiguration = popupConfigurationRequest.getPopupConfiguration();
        boolean hasCustomerDefinedTriggerRule = false;
        boolean customerSelectionNotAll = false;

        if (popupConfiguration.getDefinedTrigger() != null &&
        		popupConfiguration.getDefinedTrigger().getDefinedTriggerRules() != null) {
            
            for (DefinedTriggerRule rule : popupConfiguration.getDefinedTrigger().getDefinedTriggerRules()) {
                if (rule instanceof CustomerDefinedTriggerRule) {
                    hasCustomerDefinedTriggerRule = true;
                    CustomerDefinedTriggerRule customerRule = (CustomerDefinedTriggerRule) rule;
                    if (CustomerSelection.FEW == customerRule.getCustomerSelection() && UtilValidate.isEmpty(customerRule.getSelectedCustomerIds())) {
                        customerSelectionNotAll = true;
                    }
                }
            }
        }

        return (hasCustomerDefinedTriggerRule && customerSelectionNotAll) &&
               (PosUtil.isEmptyOrZero(popupConfiguration.getCloneRefId()) &&
            	PosUtil.isEmptyOrZero(popupConfigurationRequest.getRequestId()) &&
                PosUtil.isEmptyOrZero(popupConfiguration.getConfigId())) ||
               UtilValidate.isNotEmpty(uploadCustomerIdsExcel);
    }

	public static void prepareConfigurationInfoFromExcel(PopUpConfigurationRequest popupConfigurationRequest, MultipartFile uploadProductIdsExcel, MultipartFile removeProductIdsExcel,
			MultipartFile uploadRegionsExcel, MultipartFile removeRegionsExcel, MultipartFile uploadCustomerIdsExcel, MultipartFile removeCustomerIdsExcel) throws PopUpException {
		
		if(UtilValidate.isEmpty(popupConfigurationRequest.getPopupConfiguration())) {
			return;
		}
		PopUpConfiguration popupConfiguration = popupConfigurationRequest.getPopupConfiguration();
		if (Y.equalsIgnoreCase(popupConfiguration.getIsExcelUploadForRegions())
				&& ((PosUtil.isEmptyOrZero(popupConfiguration.getCloneRefId()) && PosUtil.isEmptyOrZero(popupConfiguration.getConfigId())) || UtilValidate.isNotEmpty(uploadRegionsExcel))) {
			popupConfiguration.setRegions(getDataFromExcel(uploadRegionsExcel, false,false, "Uploaded Regions "));
		}
		if (ConfigurationTriggerType.PRODUCT == popupConfiguration.getTriggerType()
				&& ((PosUtil.isEmptyOrZero(popupConfigurationRequest.getRequestId()) && PosUtil.isEmptyOrZero(popupConfiguration.getCloneRefId()) && PosUtil.isEmptyOrZero(popupConfiguration.getConfigId())) || UtilValidate.isNotEmpty(uploadProductIdsExcel))) {
			popupConfiguration.setProductIds(getDataFromExcel(uploadProductIdsExcel, true,false, "Uploaded ProductIds "));
		}
		if (ConfigurationTriggerType.DEFINED_TRIGGER == popupConfiguration.getTriggerType()) {
		    if (shouldUpdateDefinedTrigger(popupConfigurationRequest, uploadCustomerIdsExcel)) {
		    	PopUpConfigurationHelper.getCustomerDefinedTriggerIfFew(popupConfiguration, new ArrayList<>(readUploadExcel(uploadCustomerIdsExcel, Long.class, ApplicableType.PATHLABS.getType(), true)));
		    }
		}


		if (PosUtil.isEmptyOrZero(popupConfiguration.getConfigId()) && PosUtil.isEmptyOrZero(popupConfigurationRequest.getRequestId())  && PosUtil.isEmptyOrZero(popupConfiguration.getCloneRefId())) {
			return;
		}
		

		// In edit case, when user changes region selection from manual to excel upload
		if (UtilValidate.isNotEmpty(popupConfiguration.getRemoveRegions()) && !PosUtil.isEmptyOrZero(popupConfiguration.getConfigId())) {
			popupConfiguration.getRemoveRegions().removeAll(popupConfiguration.getRegions());
		}

		if (UtilValidate.isNotEmpty(removeRegionsExcel)) {
		    List<String> regionsToRemove = Optional.ofNullable(getDataFromExcel(removeRegionsExcel, false, false, "Removed Regions"))
		                                           .orElse(Collections.emptyList())
		                                           .stream().map(String::trim).collect(Collectors.toList());
		    popupConfiguration.setRemoveRegions(regionsToRemove);
		    if (PosUtil.isEmptyOrZero(popupConfiguration.getConfigId()) &&
		        (!PosUtil.isEmptyOrZero(popupConfigurationRequest.getRequestId()) || !PosUtil.isEmptyOrZero(popupConfiguration.getCloneRefId()))) {

		        List<String> currentRegions = Optional.ofNullable(popupConfiguration.getRegions())
		                                              .orElse(Collections.emptyList())
		                                              .stream().map(String::trim).collect(Collectors.toList());
		        if (currentRegions.containsAll(regionsToRemove) && regionsToRemove.containsAll(currentRegions)) {
		            throw new PopUpException("Selected store IDs cannot be empty.");
		        }
		        currentRegions.removeAll(regionsToRemove);
		        popupConfiguration.setRegions(currentRegions);
		    }
		}

		if (UtilValidate.isNotEmpty(removeProductIdsExcel)) {
		    List<String> productIdsToRemove = Optional.ofNullable(getDataFromExcel(removeProductIdsExcel, true, false, "Removed ProductIds"))
		                                              .orElse(Collections.emptyList())
		                                              .stream().map(String::trim).collect(Collectors.toList());
		    popupConfiguration.setRemoveProductIds(productIdsToRemove);
		    if (PosUtil.isEmptyOrZero(popupConfiguration.getConfigId()) &&
		        (!PosUtil.isEmptyOrZero(popupConfigurationRequest.getRequestId()) || !PosUtil.isEmptyOrZero(popupConfiguration.getCloneRefId()))) {

		        List<String> currentProductIds = Optional.ofNullable(popupConfiguration.getProductIds())
		                                                 .orElse(Collections.emptyList())
		                                                 .stream().map(String::trim).collect(Collectors.toList());
		        if (currentProductIds.containsAll(productIdsToRemove) && productIdsToRemove.containsAll(currentProductIds)) {
		            throw new PopUpException("Selected product IDs cannot be empty.");
		        }
		        currentProductIds.removeAll(productIdsToRemove);
		        popupConfiguration.setProductIds(currentProductIds);
		    }
		}
		
		if (UtilValidate.isNotEmpty(removeCustomerIdsExcel)) {
			PopUpConfigurationHelper.getCustomerDefinedTriggerIfFew(popupConfiguration, new ArrayList<>(readUploadExcel(removeCustomerIdsExcel,Long.class, ApplicableType.PATHLABS.getType(), true)));
		}
		validateCommonElements(popupConfiguration, uploadProductIdsExcel, removeProductIdsExcel, uploadRegionsExcel, removeRegionsExcel);
	}

	private static void validateCommonElements(PopUpConfiguration popupConfiguration, MultipartFile uploadProductIdsExcel, MultipartFile removeProductIdsExcel, MultipartFile uploadRegionsExcel,
			MultipartFile removeRegionsExcel) {
		Set<String> commonElements = new HashSet<>(popupConfiguration.getRegions());
		commonElements.retainAll(popupConfiguration.getRemoveRegions());
		if (UtilValidate.isNotEmpty(uploadRegionsExcel) && UtilValidate.isNotEmpty(removeRegionsExcel) && UtilValidate.isNotEmpty(commonElements)) {
			throw new MarketingException("Please remove common storeIDs from upload and remove regions excel.");
		}
		
		if (!ApplicableType.PATHLABS.equals(popupConfiguration.getApplicableType())) {
			commonElements = new HashSet<>(popupConfiguration.getProductIds());
			commonElements.retainAll(popupConfiguration.getRemoveProductIds());
			if (UtilValidate.isNotEmpty(uploadProductIdsExcel) && UtilValidate.isNotEmpty(removeProductIdsExcel)
					&& UtilValidate.isNotEmpty(commonElements)) {
				throw new MarketingException("Please remove common productIDs from upload and remove products excel.");
			}
		}
	}

	private static List<String> getDataFromExcel(MultipartFile excelFile, boolean isProductIdFile, boolean isCustomerIdFile, String fileType) throws PopUpException {
		if (UtilValidate.isEmpty(excelFile)) {
			throw new MarketingException(fileType + " file is empty");
		}
		String fileName = excelFile.getOriginalFilename();
		if (!(fileName.endsWith(".xls") || fileName.endsWith(XLSX))) {
			throw new PopUpException("Invalid " + fileName + FILE_UPLOAD_ERROR_MESSAGE);
		}

		List<String> values = new ArrayList<>();
		try (Workbook wb = fileName.endsWith(".xls") ? new HSSFWorkbook(excelFile.getInputStream()) : new XSSFWorkbook(excelFile.getInputStream())) {
			Sheet sheet = wb.getSheetAt(0);
			if (sheet.getPhysicalNumberOfRows() == 1) {
				throw new PopUpException("Atleast One row is required in " + fileName);
			}
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
				if (row.getLastCellNum() == -1) {
					throw new PopUpException("Atleast One column required in " + fileName + " at row " + (row.getRowNum() + 1));
				}
				Cell cell = row.getCell(0);
				if (cell == null) {
					throw new PopUpException("Null value at row " + (row.getRowNum() + 1) + " in " + fileName);
				}
				if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
					throw new PopUpException("Invalid type entered in " + fileName + " please check in the row number " + (row.getRowNum() + 1));
				}
				if (row.getPhysicalNumberOfCells() != 1) {
					throw new PopUpException("Number of columns should be 1 but found " + row.getPhysicalNumberOfCells() + " in " + fileName);
				}
				String value = cell.getStringCellValue().toUpperCase();
				if (isProductIdFile && !isCustomerIdFile && (value.length() != 8 || value.contains("'") || value.contains("\""))) {
					throw new PopUpException("Invalid ProductID : " + value + ENTERED_IN + fileName + PLEASE_CHECK_THE_ROW_NUMBER + (row.getRowNum() + 1));
				} else if (!isProductIdFile && !isCustomerIdFile && (value.length() != 12 || value.contains("'") || value.contains("\""))) {
					throw new PopUpException("Invalid StoreId : " + value + ENTERED_IN + fileName + PLEASE_CHECK_THE_ROW_NUMBER + (row.getRowNum() + 1));
				}else if(!isProductIdFile && isCustomerIdFile && !UtilValidate.validateCustomerId(value)) {
					throw new PopUpException("Invalid CustomerId : " + value + ENTERED_IN + fileName + PLEASE_CHECK_THE_ROW_NUMBER + (row.getRowNum() + 1));
				}
				if (!values.contains(value)) {
					values.add(value);
				}
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new PopUpException(e.getMessage());
		}
		return values;
	}
	
	public static void readProductsUploadExcel(Campaign campaign, Map<String, Integer> map,
			double paybackPercentageMaxAllowed, MultipartFile productUploadExcel) {
		if (productUploadExcel == null || productUploadExcel.isEmpty())
			return;

		String fileName = productUploadExcel.getOriginalFilename();
		if (!(fileName.endsWith(".xls") || fileName.endsWith(XLSX))) {
			throw new MarketingException("Invalid " + fileName + FILE_UPLOAD_ERROR_MESSAGE);
		}
		try (Workbook workbook = fileName.endsWith(".xls") ? new HSSFWorkbook(productUploadExcel.getInputStream()) : new XSSFWorkbook(productUploadExcel.getInputStream())){

			Sheet sheet = workbook.getSheetAt(0);
			CampaignProduct campaignProduct = null;
			campaign.getCampaignProducts().clear();
			Set<String> prodQuanSet = new HashSet<>();
			List<String> supplementProducts = new ArrayList<>();
			Map<String, String> productPriceSlabMap = new HashMap<>();
			int promotionAddOn = Integer.parseInt(PromotionConstants.PROMOTION_ADDON);
			for (Row row : sheet) {
				if (row.getRowNum() == 0)
					continue;
				campaignProduct = new CampaignProduct();
				if (row.getPhysicalNumberOfCells() >= 4) {
					try {  
						if (campaign.getUserMetaData().isSpecialtyBased())
							row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						String productId = CampaignUtil.getProdIdFromCell(row.getCell(0));
						if ((campaign.getCampaignType() == 5 || campaign.getCampaignType() == 6)
								&& supplementProducts.contains(productId))
							throw new PromotionException("Multiple promotions are not allowed one campaign! product : "
									+ productId);
						supplementProducts.add(productId);
						campaignProduct.setProductId(productId);
						if ((campaign.getPromotionApplicableType() == PromotionConstants.APPLICABLE_TYPE_PATHLABS
								|| campaign.getPromotionApplicableType() == PromotionConstants.APPLICABLE_TYPE_LENS) 
								&& (row.getPhysicalNumberOfCells() > 7 || UtilValidate.isNotEmpty(row.getCell(7)))) {
								throw new PromotionException("PaybackPercentage is  not allowed for ApplicableType PATHLABS/LENS");
						}
						
						if (campaign.getPromotionApplicableType() != PromotionConstants.APPLICABLE_TYPE_PATHLABS) {
							if (verifyAndGetNumericCellValue(row, 1) <= 0) {
								throw new PromotionException("From Quantity should be greater than 0");
							}
							if (verifyAndGetNumericCellValue(row, 1) !=(int) verifyAndGetNumericCellValue(row, 1)) {
								throw new PromotionException("From Quantity should not be a decimal number");
							}
							campaignProduct.setFromQuantity((int) verifyAndGetNumericCellValue(row, 1));
						} else {
							int fromQuantity = (int) verifyAndGetNumericCellValue(row, 1);
							if (fromQuantity > 1) {
								throw new PromotionException("Invalid FromQuantity entered");
							}
							campaignProduct.setFromQuantity((int) verifyAndGetNumericCellValue(row, 1));
						}

						if (campaign.getCampaignType() == 5 || campaign.getCampaignType() == 6) {
							campaignProduct.setToProductId(CampaignUtil.getProdIdFromCell(row.getCell(2)));
							if (verifyAndGetNumericCellValue(row, 3) <= 0) {
								throw new PromotionException("ToQuantity should be greater than 0");
							}
							if (verifyAndGetNumericCellValue(row, 3) != (int) verifyAndGetNumericCellValue(row, 3)) {
								throw new PromotionException("ToQuantity should not be a decimal number");
							}
							campaignProduct
									.setPaybackPercentage(getPaybackPercentage(row, 5, paybackPercentageMaxAllowed));
							campaignProduct.setToQuantity((int) verifyAndGetNumericCellValue(row, 3));
						} else {
							Integer discountTypeId = map.get(row.getCell(2).getStringCellValue().trim());
							if (discountTypeId != null) {
								if(promotionAddOn == campaign.getCampaignType()
										&& discountTypeId != PromotionConstants.SPECIAL_DISCOUNT_TYPE_REDUCTION_PERCENTAGE) {
									throw new PromotionException("Promotion Addon must be configured as a percentage discount only");
								}
								campaignProduct.setDiscountType(discountTypeId);
							} else
								throw new PromotionException("Invalid Discount Type Entered in product upload excel file");
							campaignProduct.setDiscountValue(verifyAndGetNumericCellValue(row, 3));
							if ((campaign.getCampaignType() == Integer.parseInt(PromotionConstants.SPECIAL_DISCOUNT)
									|| campaign.getCampaignType() == promotionAddOn)
									&& UtilValidate.isNotEmpty(row.getCell(4))
									&& row.getCell(4).toString().trim().length() > 0
									&& UtilValidate.isNotEmpty(verifyAndGetNumericCellValue(row, 4))) {
								if (verifyAndGetNumericCellValue(row, 4) <= 0) {
									throw new PromotionException("Max Quantity Value should be greater than 0");
								}
								if (verifyAndGetNumericCellValue(row, 4) != (int) verifyAndGetNumericCellValue(row, 4)) {
									throw new PromotionException("Max Quantity Value should not be a decimal number");
								}
								campaignProduct.setToQuantity((int) verifyAndGetNumericCellValue(row, 4));
							}
							if (campaign.getPromotionApplicableType() != PromotionConstants.APPLICABLE_TYPE_PATHLABS
									&& campaign.getPromotionApplicableType() != PromotionConstants.APPLICABLE_TYPE_LENS)
								campaignProduct.setPaybackPercentage(
										getPaybackPercentage(row, 6, paybackPercentageMaxAllowed));
						}
						if (campaign.getCampaignType() == 5 || campaign.getCampaignType() == 6) {
							setDisplayMessage(campaign, campaignProduct, row, 4);
						} else {
							setDisplayMessage(campaign, campaignProduct, row, 5);
						}
						if ((campaign.getCampaignType() == 3 || campaign.getCampaignType() == 5
								|| campaign.getCampaignType() == 6 || promotionAddOn == campaign.getCampaignType())) {
							int colNumber = ((campaign.getCampaignType() == 3 || promotionAddOn == campaign.getCampaignType()) && campaign
									.getPromotionApplicableType() == PromotionConstants.APPLICABLE_TYPE_PHARMACY) ? 7
											: 6;
							boolean isPriceSlabNotEmpty = UtilValidate.isNotEmpty(row.getCell(colNumber));
							String priceConsiderForSlab = isPriceSlabNotEmpty
									? row.getCell(colNumber).getStringCellValue().trim().toUpperCase()
									: "S";
							if ("M".equals(priceConsiderForSlab) || "S".equals(priceConsiderForSlab)) {
								String existingSlab = productPriceSlabMap
										.putIfAbsent(campaignProduct.getProductId(), priceConsiderForSlab);
								if (existingSlab != null && !existingSlab.equals(priceConsiderForSlab)) {
									throw new PromotionException("Multiple PriceConsiderForSlab values found for product "
											+ campaignProduct.getProductId() + " at column " + colNumber);
								}
								if(isPriceSlabNotEmpty) {
									campaignProduct.setPriceConsiderForSlab(priceConsiderForSlab);
								}
							} else {
								throw new PromotionException(
										"Invalid value found at column number " + colNumber);
							}
						}
						log.debug("campaignProduct : {}", campaignProduct);
						checkDuplicateEntry(campaignProduct, prodQuanSet);
						
					} catch (PromotionException ex) {
						throw new PromotionException(ex.getMessage()
								+ ", Please check in product upload excel file at row " + (row.getRowNum() + 1));
					}
				} else {
					throw new PromotionException(
							"Number of columns should be 4 but found " + row.getPhysicalNumberOfCells()
									+ " in product upload excel file at row " + (row.getRowNum() + 1));
				}
				validateCampaignProduct(campaign.getCampaignType(), campaignProduct,campaign.getPromotionApplicableType(), campaign, row);
				campaign.getCampaignProducts().add(campaignProduct);
			}
			/* validateCampaignProductsMessage(campaign); */
			log.info("CampaignProducts: {}", campaign.getCampaignProducts().size());
		}catch(PromotionException e){
			log.error(EXCEPTION_OCCURED, e);
			throw new PromotionException(e.getMessage());
		}catch(Exception e){
			log.error(EXCEPTION_OCCURED, e);
			throw new PromotionException("Invalid product upload file");
		}
	}

	private static void checkDuplicateEntry(CampaignProduct cp, Set<String> prodQuanSet) {
		String prodKey = cp.getProductId().toUpperCase()+"#"+cp.getFromQuantity();
		
		if(!prodQuanSet.add(prodKey)) {
			throw new PromotionException(String.format("Duplicate entry for %s with quantity %s", cp.getProductId(), cp.getFromQuantity()));
		}
	}

	private static double verifyAndGetNumericCellValue(Row row, int columnNumber) {
		try {
			return row.getCell(columnNumber).getNumericCellValue();
		} catch (Exception e) {
			throw new PromotionException("The value at row " + (row.getRowNum() + 1) + " column " + (columnNumber + 1)
					+ " should be a number");
		}
	}

	private static Double getPaybackPercentage(Row row, int i, double paybackPercentageMaxAllowed)
			throws PromotionException {
		if (UtilValidate.isNotEmpty(row.getCell(i)) && row.getCell(i).toString().trim().length() > 0) {
			Double paybackPercentage = verifyAndGetNumericCellValue(row, i);
			if (paybackPercentage < 0) {
				throw new PromotionException(
						"Payback Percentage Value should be greater than or equal to 0.check at row "
								+ (row.getRowNum() + 1));
			}
			if (paybackPercentageMaxAllowed > 0 && paybackPercentage > paybackPercentageMaxAllowed) {
				throw new PromotionException(
						"Payback Percentage Value should be less than or equal to Maximum Allowed : "
								+ paybackPercentageMaxAllowed + ".check at row " + (row.getRowNum() + 1));
			}
			return paybackPercentage;
		}
		return null;
	}

	private static void setDisplayMessage(Campaign campaign, CampaignProduct campaignProduct, Row row,
			int columnNumber) {
		// if display message is not provided
		if (UtilValidate.isEmpty(row.getCell(columnNumber))
				|| UtilValidate.isEmpty(row.getCell(columnNumber).getStringCellValue().trim())) {
			// and if channel is mart or mobile
			if ((campaign.getChannels().contains(2) || campaign.getChannels().contains(4))) {
				if (campaign.getCampaignType() == 5 || campaign.getCampaignType() == 6) {
					throw new PromotionException("Display Message is needed when channel is mart or mobile");
				}

				// and loyalty type contains other than regular loyalty type
				if ((campaign.getLoyaltyTypes().size() > 1
						|| (campaign.getLoyaltyTypes().size() == 1 && campaign.getLoyaltyTypes().get(0) != 1))) {
					throw new PromotionException(
							"Display Message is needed when channel is mart or mobile and the loyalty type is other than regular");
				}
				// or loyalty type is regular and TOQuantity is specified
				if (campaign.getLoyaltyTypes().size() == 1 && campaign.getLoyaltyTypes().get(0) == 1
						&& campaignProduct.getFromQuantity() > 1) {
					throw new PromotionException(
							"Display Message is needed when channel is mart or mobile, the loyalty type is regular and FromQuantity is greater than 1");
				}
			}
		} else {
			String displayMessage = row.getCell(columnNumber).getStringCellValue().trim();
			if (!UtilValidate.validateStringWithLength(displayMessage, 255))
				throw new PromotionException("Display Message should be maximum of 255 charactors.");
			campaignProduct.setDisplayMessage(row.getCell(columnNumber).getStringCellValue().trim());
		}
	}

	private static void validateCampaignProductsMessage(Campaign campaign) {
		List<CampaignProduct> campaignProducts = campaign.getCampaignProducts();
		Map<String, String> productMessageMap = new HashMap<>();
		campaignProducts.forEach(cp -> {
			if (productMessageMap.containsKey(cp.getProductId())
					&& !StringUtils.equals(productMessageMap.get(cp.getProductId()), cp.getDisplayMessage())) {
				throw new PromotionException("Message should be same for all entries for product " + cp.getProductId()
						+ " Please check in excel sheet");
			}
			productMessageMap.putIfAbsent(cp.getProductId(), cp.getDisplayMessage());
		});
	}

	public static void validateCampaignProduct(int campaignType, CampaignProduct campaignProduct, int applicableType,
			Campaign campaign, Row row)  {
			String message = " in row number " + (row.getRowNum()+1);
			if (applicableType == PromotionConstants.APPLICABLE_TYPE_PATHLABS) {
				if ((!campaign.getUserMetaData().isSpecialtyBased() && campaignProduct.getProductId().length() != 7)
						|| campaignProduct.getProductId().contains("'") || campaignProduct.getProductId().contains("\"")) {
					throw new PromotionException("Invalid ProductId "+message);
				}
					
			} else {
				if (campaignProduct.getProductId().length() != 8 || campaignProduct.getProductId().contains("'")
						|| campaignProduct.getProductId().contains("\"")) {
					throw new PromotionException("Invalid ProductId "+message);
				}
			}
			switch (campaignType) {
			case 3:
				if (campaignProduct.getDiscountType() == 2 && campaignProduct.getDiscountValue() > 100) 
					throw new PromotionException("Discount percentage can not be greater than 100 "+message);
				break;
			case 5:
				if (!campaignProduct.getProductId().equals(campaignProduct.getToProductId()))
					throw new PromotionException("FromProductId should be the same as ToProductId "+message);
				break;
			case 6:
				if (campaignProduct.getProductId().equals(campaignProduct.getToProductId()))
					throw new PromotionException("FromProductId should not be the same as ToProductId "+message);
				if(campaignProduct.getToProductId().contains("'")
						|| campaignProduct.getToProductId().contains("\""))
					throw new PromotionException("Invalid ToProductId "+message);
				if (campaignProduct.getToProductId() != null && !campaignProduct.getToProductId().isEmpty()) {
					String[] toProductIds = campaignProduct.getToProductId().split(",");
					for (String productId : toProductIds) {
						if (productId.trim().length() < 8)
							throw new PromotionException(
									"Invalid Product id exist in the toProducts list for product :" + productId + message);
					}
				}
				break;
			default:
				break;
			}
	}
	
	public static byte[] generateExcelSheet(List<Map<String,Object>> listOfMaps, Map<String,String> campaignIdsInfo){
		try(Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = null;
			Row row =null;
			Cell cell=null;
			if(UtilValidate.isNotEmpty(listOfMaps)){
				int noOfSheets = (int) Math.ceil(Double.valueOf(listOfMaps.size())/65535);
				for (int i = 0; i < noOfSheets; i++) {				
					int limit = ((i+1)*65535)<listOfMaps.size()?((i+1)*65535):listOfMaps.size();
					int j = i*65535;			
					int rownum = 0;
					int colnum =0;
					sheet = workbook.createSheet("Excel Sheet "+i);
					row = sheet.createRow(rownum++);
					Map<String, Object> map = listOfMaps.get(0);						
					for (String key : map.keySet()) {
						cell = row.createCell(colnum++);
						cell.setCellValue(key);
						cell = null;
						if("ToProductId".equalsIgnoreCase(key)) {
							cell = row.createCell(colnum++);
							cell.setCellValue("ToProductIdName");
							cell = null;
						}else if("ItemId".equalsIgnoreCase(key)) {
							cell = row.createCell(colnum++);
							cell.setCellValue("ItemName");
							cell = null;
						}
					}
					
					for(;j<limit;j++){
						map = listOfMaps.get(j);
						log.debug("Products : {}", map);
						row = sheet.createRow(rownum++);
						colnum = 0;
						
						String itemId = "";
						for (Entry<String, Object> entry : map.entrySet()) {
							cell = row.createCell(colnum++);
							if(entry.getValue()!=null) {
								if("FromQuantity".equalsIgnoreCase(entry.getKey()) || "ToQuantity".equalsIgnoreCase(entry.getKey()) || "MaxQuantity".equalsIgnoreCase(entry.getKey())){
									cell.setCellValue((Integer) entry.getValue());
								}else if("DiscountAmount".equalsIgnoreCase(entry.getKey()) || "PaybackPercentage".equalsIgnoreCase(entry.getKey())){
									cell.setCellValue(Double.parseDouble(entry.getValue().toString()));
								}else if("ItemId".equalsIgnoreCase(entry.getKey())) {
									itemId = entry.getValue() != null ? entry.getValue().toString():"";
									cell.setCellValue(itemId);
									
									cell = row.createCell(colnum++);
									cell.setCellValue(campaignIdsInfo.get(itemId) != null ? campaignIdsInfo.get(itemId) : "");
								}else if("ToProductId".equalsIgnoreCase(entry.getKey())) {
									itemId = entry.getValue() != null ? entry.getValue().toString():"";
									cell.setCellValue(itemId);
									String [] items = itemId.split(",");
									StringJoiner itemsName = new StringJoiner(",");
									for (String item : items) {
										String campaignName = campaignIdsInfo.get(item.trim()); 
										if (campaignName != null) {
											itemsName.add(campaignName);
										}
									}
									cell = row.createCell(colnum++);
									cell.setCellValue(itemsName.toString());
								}else{
									cell.setCellValue(entry.getValue() != null ? entry.getValue().toString():"");
								}
							}else {
								cell.setCellValue("");
							}
						}
						row = null;
						map = null;
					}	
					sheet = null;
				}
			}
			ByteArrayOutputStream fout = new ByteArrayOutputStream();
			workbook.write(fout);
			return fout.toByteArray();
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException Occurred : ",e);
		} catch (IOException e) {
			log.error("Exception Occurred : ",e);
		}
		return new byte[0];
	}

	public static Set<ComplimentaryProduct> readComplimentaryProductsExcel(MultipartFile productUpload, boolean isRefProdExcel) {
		String fileName = productUpload.getOriginalFilename();
		try (Workbook wb = validateAndCreateWorkbook(productUpload, "Complimentary Gift Excel")) {
			Sheet sheet = wb.getSheetAt(0);
			validateSheet(sheet,"Complimentary Excel Sheet", 1);
			Set<String> productSet = new HashSet<>();
			Set<ComplimentaryProduct> complimentaryProducts = new HashSet<>();
			for (Row row : sheet) {
				if (row.getRowNum() == 0)
					continue;
				validateRowColumns(row, "Complimentary Gift Excel", 4);
				processRow(row, productSet, complimentaryProducts, isRefProdExcel);
			}
			return complimentaryProducts;	
		} catch (PromotionException e) {
			log.error(e.getMessage(), e);
			throw new PromotionException(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PromotionException("Unable to read the "+ fileName + " file.", e);
		}
	}

	public static Workbook validateAndCreateWorkbook(MultipartFile file, String mssg) throws IOException {
		if (UtilValidate.isEmpty(file)) {
			throw new PromotionException(mssg + " file is empty");
		}
		String fileName = file.getOriginalFilename();
		
		if (UtilValidate.isEmpty(fileName)) {
			throw new PromotionException("File name is empty !");
		}
		if (!(fileName.endsWith(".xls") || fileName.endsWith(""))) {
			throw new PromotionException("Invalid " + fileName + FILE_UPLOAD_ERROR_MESSAGE);
		}
		return fileName.endsWith(".xls") ? new HSSFWorkbook(file.getInputStream()) : new XSSFWorkbook(file.getInputStream());
	}

	private static void validateSheet(Sheet sheet, String fileName, int noOfRows) {
		if (sheet.getPhysicalNumberOfRows() <= noOfRows) {
			throw new PromotionException(fileName+" must have "+ noOfRows+" or more products");
		}
	}

	private static void validateRowColumns(Row row, String fileName, int noOfCells) {
		if (row.getPhysicalNumberOfCells() != noOfCells) {
			throw new PromotionException("Number of columns should be " + noOfCells + " but found " + row.getPhysicalNumberOfCells()
					+ " in the "+ fileName + " file at row " + (row.getRowNum() + 1));
		}
	}

	private static void processRow(Row row, Set<String> productSet, Set<ComplimentaryProduct> complimentaryProducts, boolean isRefProdExcel) {
		try {
			ComplimentaryProduct complimentaryProduct = new ComplimentaryProduct();
			String productId = getProdIdFromCell(row, 0);
			Double quantity = verifyAndGetNumericCellValue(row, 1);
			Double discount = verifyAndGetNumericCellValue(row, 2);
			String productAction = getCellValueAsString(row, 3);
			if (UtilValidate.isEmpty(productId)) {
				throw new PromotionException("ProductId should not be empty");
			}
			if (quantity <= 0) {
				throw new PromotionException("Quantity should be entered as a positive number");
			}
			if (discount < 0 || discount > 100) {
				throw new PromotionException("Complimentary Product discount should be in between 0 and 100");
			}
			if (!productAction.equalsIgnoreCase("A") && !productAction.equalsIgnoreCase("R")) {
				throw new PromotionException("Complimentary Product action should be either A(Auto-Add) or R(Recommended)");
			}
			if (productSet.contains(productId.toUpperCase())) {
				throw new PromotionException("Same product configured multiple times productId : " + productId);
			}
			if(CampaignUtil.validateProductId(productId, PromotionConstants.APPLICABLE_TYPE_PHARMACY)) {
				throw new PromotionException("Invalid productId : "+ productId);
			}
			if (!isRefProdExcel && quantity >= 100) {
				throw new PromotionException("Quantity should not be more than 2 digits");
			}
			complimentaryProduct.setProductId(productId.toUpperCase());
			complimentaryProduct.setQuantity(quantity.longValue());
			complimentaryProduct.setDiscount(discount);
			complimentaryProduct.setAutoAdd(productAction.equalsIgnoreCase("A"));
	
			complimentaryProducts.add(complimentaryProduct);
			productSet.add(productId.toUpperCase());
		} catch (PromotionException e) {
			throw new PromotionException(e.getMessage() + " , Please check in gift product-upload excel file at row "+ (row.getRowNum() + 1));
		} catch (Exception e) {
			throw new PromotionException("Invalid data , Please check in complimentary gift product-upload excel file at row "+ (row.getRowNum() + 1));
		}
	}

	private static String getProdIdFromCell(Row row, int cellIndex) {
		Cell cell = row.getCell(cellIndex);
		return CampaignUtil.getProdIdFromCell(cell);
	}
	
	private static String getCellValueAsString(Row row, int cellIndex) {
		return row.getCell(cellIndex).getStringCellValue().trim();
	}
	
	public static void readExcelUploadFile(MultipartFile othersUpload,Map<Integer, ColumnData<?>> columnDataMap) {
		String fileName = othersUpload.getOriginalFilename();
		try (Workbook wb = validateAndCreateWorkbook(othersUpload, "Others Upload Excel")) {
			Sheet sheet = wb.getSheetAt(0);
			if (sheet.getPhysicalNumberOfRows() <= 2) {
				throw new PromotionException("Excel should have 1 or more records");
			}
			for (Row row : sheet) {		
				if (row.getRowNum() == 0 || row.getRowNum() == 1)
					continue;
				columnDataMap.keySet().forEach( index -> {
					ColumnData<?> columnData = columnDataMap.get(index);
					Object data = columnData.getValidator().validateAndGet(row.getCell(index-1), row.getRowNum()+1);
					if(data != null) {
						columnData.addRecord(data);
					}
				});
			}
		}
		catch (PromotionException e) {
			throw new PromotionException(e.getMessage() + ". Please check in " + fileName +" file", e);
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PromotionException("Unable to read the "+ fileName + " file", e);
		}
	}
	
	private static final int REF_PRODUCT_IDX = 1;
	private static final int REM_REF_PRODUCT_IDX = 2;
	private static final int COMP_IDX = 3;
	private static final int REM_COMP_IDX = 4;	
	private static final int CUST_CREATE_IDX = 5;
	private static final int REM_CUST_IDX = 6;

	@SuppressWarnings("unchecked")
	public static void readAndSetComplimentaryPromotion(MultipartFile othersUpload, ComplimentaryPromotion complimentaryPromotion, boolean isEdit) {
	    Map<Integer, ColumnData<?>> columnDataMap = new HashMap<>();
	    columnDataMap.put(REF_PRODUCT_IDX, new ColumnData<>(REF_PRODUCT_IDX, Validators.REFERENCE_PRODUCT_ID_VALIDATOR));
	    columnDataMap.put(COMP_IDX, new ColumnData<>(COMP_IDX, Validators.COMPOSITION_ID_VALIDATOR));
	    if (!complimentaryPromotion.isAllCustomers()) {
	        columnDataMap.put(CUST_CREATE_IDX, new ColumnData<>(CUST_CREATE_IDX, Validators.CUSTOMER_ID_VALIDATOR));
	    }
	    if (isEdit) {
	    	columnDataMap.put(REM_REF_PRODUCT_IDX, new ColumnData<>(REM_REF_PRODUCT_IDX, Validators.REFERENCE_PRODUCT_ID_VALIDATOR));
	        columnDataMap.put(REM_COMP_IDX, new ColumnData<>(REM_COMP_IDX, Validators.COMPOSITION_ID_VALIDATOR));
		    if (!complimentaryPromotion.isAllCustomers()) {
	            columnDataMap.put(REM_CUST_IDX, new ColumnData<>(REM_CUST_IDX, Validators.CUSTOMER_ID_VALIDATOR));
		    }
	    }
	    readExcelUploadFile(othersUpload, columnDataMap);
	    complimentaryPromotion.setReferenceProductIds((Set<String>) columnDataMap.get(REF_PRODUCT_IDX).getData());
	    complimentaryPromotion.setCompositionIds((Set<Integer>) columnDataMap.get(COMP_IDX).getData());
	    if (!complimentaryPromotion.isAllCustomers()) {
	        complimentaryPromotion.setCustomerIds((Set<Long>) columnDataMap.get(CUST_CREATE_IDX).getData());
	    }
	    if (isEdit) {
	        complimentaryPromotion.setRemoveReferenceProductIds((Set<String>) columnDataMap.get(REM_REF_PRODUCT_IDX).getData());
	        complimentaryPromotion.setRemoveCompositionIds((Set<Integer>) columnDataMap.get(REM_COMP_IDX).getData());
		    if (!complimentaryPromotion.isAllCustomers()) {
	            complimentaryPromotion.setRemoveCustomerIds((Set<Long>) columnDataMap.get(REM_CUST_IDX).getData());
		    }
	    	validateCommonItems(complimentaryPromotion);
	    }
	}
		
	private static void validateCommonItems(ComplimentaryPromotion complimentaryPromotion) {
		StringJoiner joiner = new StringJoiner(", ");
		if(checkCommonItems(complimentaryPromotion.getReferenceProductIds(), complimentaryPromotion.getRemoveReferenceProductIds()))
			joiner.add("Reference Products");
		if(checkCommonItems(complimentaryPromotion.getCompositionIds(), complimentaryPromotion.getRemoveCompositionIds()))
			joiner.add("Reference Compositions");
		if(!complimentaryPromotion.isAllCustomers() && checkCommonItems(complimentaryPromotion.getCustomerIds(), complimentaryPromotion.getRemoveCustomerIds())) 
			joiner.add("Customers");
		
		if(!joiner.toString().isEmpty()) {
			throw new PromotionException(joiner.toString()+" contain duplicates in both upload & remove columns.");
		}
	}
	
	private static <T> boolean checkCommonItems(Set<T> uploads, Set<T> removals) {
		for(T key : removals) {
			if(uploads.contains(key)) {
				return true;
			}
		}
		return false;
	}

	public static byte[] generateExcelSheetForComplimentaryGift(Set<ComplimentaryProduct> giftProducts, Map<String,String> productMap) {
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Complimentary Gift");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ProductId");
			headerRow.createCell(1).setCellValue("ProductName");
			headerRow.createCell(2).setCellValue("ComplimentaryQuantity");
			headerRow.createCell(3).setCellValue("ComplimentaryDiscount");
			headerRow.createCell(4).setCellValue("ProductAction");
			int rowIdx = 1;
			for (ComplimentaryProduct product : giftProducts) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(product.getProductId());
				row.createCell(1).setCellValue(productMap.get(product.getProductId()));
				row.createCell(2).setCellValue(product.getQuantity());
				row.createCell(3).setCellValue(product.getDiscount());
				row.createCell(4).setCellValue(product.isAutoAdd() ? "A" : "R");
			}
			ByteArrayOutputStream fout = new ByteArrayOutputStream();
			workbook.write(fout);
			return fout.toByteArray();
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException Occurred :", e);
		} catch (IOException e) {
			log.error("Exception Occurred :", e);
		}
		return new byte[0];
	}
	
	private static CellStyle getBoldCellStyle(Workbook workbook) {
		CellStyle boldStyle = workbook.createCellStyle();
		boldStyle.setAlignment(CellStyle.ALIGN_CENTER);
		Font font = workbook.createFont();
		font.setBold(true);
		boldStyle.setFont(font);
		return boldStyle;
	}
	
	public static byte[] getExcelForReferences(Set<String> refProductId,Set<Integer> compositionId,Set<Long> customerId, Map<String,String> productMap, Map<Integer,String> compositionMap) {
		Iterator<String> prodItr = refProductId.iterator();
		Iterator<Integer> compItr = compositionId.iterator();
		Iterator<Long> custItr = customerId.iterator();

		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Complimentary Gift");
			CellStyle boldStyle = getBoldCellStyle(workbook);

			Row headerRow = sheet.createRow(0);
			addCell(headerRow, 0, boldStyle, "Reference Products");
			addCell(headerRow, 2, boldStyle, "Reference Compositions");
			addCell(headerRow, 4, boldStyle, "Customers" );
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			
			Row subHeaderRow = sheet.createRow(1);
			addCell(subHeaderRow, 0, boldStyle, "ProductID Upload");
			addCell(subHeaderRow, 1, boldStyle, "Product Name");
			
			addCell(subHeaderRow, 2, boldStyle, "CompositionID Upload");
			addCell(subHeaderRow, 3, boldStyle, "Composition Name");
			
			addCell(subHeaderRow, 4, boldStyle, "CustomerID Upload");
			for(int i=0; i<subHeaderRow.getLastCellNum(); i++) {
				sheet.autoSizeColumn(i);
			}
			boolean prodFlag = true;
			boolean compFlag = true;
			boolean custFlag = true;
			int rowIdx = 2;
			while (prodFlag || compFlag || custFlag) {
				Row row = sheet.createRow(rowIdx++);
				prodFlag = prodItr.hasNext(); 
				if (prodFlag) {
					Object prodId = prodItr.next();
					addCell(row, 0, null, prodId);
					Cell cell = row.createCell(1);
			    	cell.setCellValue(productMap.get(prodId));
				}
				compFlag = compItr.hasNext();
				if (compFlag) {
					Object compId = compItr.next();
					addCell(row, 2, null, compId);
					Cell cell = row.createCell(3);
			    	cell.setCellValue(compositionMap.get(compId));
				}
				custFlag = custItr.hasNext() && addCell(row, 4, null, custItr.next());
			}
			
			ByteArrayOutputStream fout = new ByteArrayOutputStream();
			workbook.write(fout);
			return fout.toByteArray();
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException Occurred :", e);
		} catch (IOException e) {
			log.error("Exception Occurred :", e);
		}
		return new byte[0];
	}
	
	private static boolean addCell(Row row, int columnIndex, CellStyle cellStyle,Object value) {
		Cell cell = row.createCell(columnIndex);
	    if(value instanceof String) {
	    	cell.setCellValue((String) value);
	    } else if(value instanceof Integer) {
	    	cell.setCellValue((Integer) value);
	    } else if(value instanceof Long) {
	    	cell.setCellValue((Long) value );
	    }
	    if(cellStyle != null) 
	    	cell.setCellStyle(cellStyle);
	    return true;
	}
	
	public static byte[] getExcelForStores(Map<String, Map<String, String>> storesInfo) {
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Stores");
			CellStyle boldStyle = getBoldCellStyle(workbook);
			Row headerRow = sheet.createRow(0);
			addCell(headerRow, 0, boldStyle, "Store Id");
			addCell(headerRow, 1, boldStyle, "Store Name");
			addCell(headerRow, 2, boldStyle, "City");
			addCell(headerRow, 3, boldStyle, "State");
            int rowIndex = 1;
			for (Map.Entry<String, Map<String, String>> entry : storesInfo.entrySet()) {
				Row row = sheet.createRow(rowIndex++);
				Map<String, String> storeDetails = entry.getValue();
				addCell(row, 0, null, entry.getKey());
				addCell(row, 1, null, storeDetails.get("storeName"));
				addCell(row, 2, null, storeDetails.get("city"));
				addCell(row, 3, null, storeDetails.get("state"));
			}
			ByteArrayOutputStream fout = new ByteArrayOutputStream();
			workbook.write(fout);
			return fout.toByteArray();
		} catch (FileNotFoundException e) {
			log.error("File NotFoundException Occurred :", e);
		} catch (IOException e) {
			log.error("Exception Occurred:", e);
		}
		return new byte[0];
	}
	
	public static byte[] generateExcelForMultipleCampaigns(List<Campaign> multipleCampaigns, boolean isCouponBased) {
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Multiple Campaigns");
			CellStyle boldStyle = getBoldCellStyle(workbook);
			Row headerRow = sheet.createRow(0);
			addCell(headerRow, 0, boldStyle, "CampaignId");
			addCell(headerRow, 1, boldStyle, "CampaignName");
			addCell(headerRow, 2, boldStyle, "CouponCode");
			for(int i=0; i < 3; i++) {
				sheet.autoSizeColumn(i);
			}
			int rowIdx = 1;
			for (Campaign campaign : multipleCampaigns) {
				Row row = sheet.createRow(rowIdx++);
				addCell(row, 0, null, campaign.getCampaignId());
				addCell(row, 1, null, campaign.getCampaignName());
				if(isCouponBased) {
					addCell(row, 2, null, campaign.getPromotionCoupon().getCouponCode());
				} 
			}
			ByteArrayOutputStream fout = new ByteArrayOutputStream();
			workbook.write(fout);
			return fout.toByteArray();
		}  catch (IOException e) {
			log.error("Exception Occurred :", e);
		}
		return new byte[0];
	}
	
}
