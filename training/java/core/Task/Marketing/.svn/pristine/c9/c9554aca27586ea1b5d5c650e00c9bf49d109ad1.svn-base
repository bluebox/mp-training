package com.medplus.marketing.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.medplus.accounts.core.constants.StoreCategoryTypeEnum;
import com.medplus.accounts.service.impl.AccountStoreService;
import com.medplus.accounts.store.core.domain.StoreDetails;
import com.medplus.accounts.store.core.domain.StoreSearchCriteria;
import com.medplus.common.cas.model.UserDetails;
import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.marketing.service.PathLabTestsService;
import com.medplus.marketing.util.CampaignUtil;
import com.medplus.marketing.util.ExcelUtil;
import com.medplus.marketing.util.UserUtil;
import com.medplus.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductStoreHelper {
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private PathLabTestsService pathLabTestsService;
	
	@Autowired
	private AccountStoreService accountStoreService;
	
	public void validateProducts(Set<String> productIds, String productIdName, int applicableType, boolean isSpecialityBased) {
		if(UtilValidate.isEmpty(productIds)) 
			return;
		if(applicableType == PromotionConstants.APPLICABLE_TYPE_PATHLABS) {
			CampaignUtil.validateIds(productIds, CampaignUtil.getPathLabTestMap(new ArrayList<>(productIds), pathLabTestsService, isSpecialityBased, true).keySet(), "Path Lab Tests");
		} else {
			CampaignUtil.validateIds(productIds, CampaignUtil.getProductMap(productIds, productIdName ,productService, true).keySet(), productIdName);
		}
	}

	public List<StoreDetails> getStoreDetails(StoreSearchCriteria criteria, boolean isPathLabs, Optional<UserDetails> userDetails) {
		if(!userDetails.isPresent()) {
			log.info("User details not found");
			return Collections.emptyList();
		}
		if(isPathLabs) {
			criteria.setIgnorePathlabStores(false);
			criteria.setStoreCategoryTypes(Arrays.asList(StoreCategoryTypeEnum.PSC,StoreCategoryTypeEnum.CS,
					StoreCategoryTypeEnum.LAB, StoreCategoryTypeEnum.LAB_TO_CS, StoreCategoryTypeEnum.VIRTUAL_STORE,
					StoreCategoryTypeEnum.THIRD_PARTY_CS, StoreCategoryTypeEnum.THIRD_PARTY_LAB));
		} else {
			criteria.setCategoryId(1);
		}
		criteria.setStatus("A");
		criteria.setTenantIds(Collections.singletonList(UserUtil.getTenantId()));
		Set<String> teritaries = userDetails.get().getTeritaries();
		switch (userDetails.get().getTeritaryLevel()) {
			case 2:
				criteria.setStateCodeList(new ArrayList<>(teritaries));
				break;
			case 3:
				criteria.setCityCodeList(new ArrayList<>(teritaries));
				break;
			case 4:
				criteria.setStoreIds(new ArrayList<>(teritaries));
				break;
			default:
				break;
		}
		return accountStoreService.getStoresInfo(criteria);
	}
	
	public static Map<String, Map<String, String>> getStoresInfo(List<StoreDetails> stores) {		
		if(stores.isEmpty()) {
			return Collections.emptyMap();
		}
		return stores.stream().collect(Collectors.toMap(StoreDetails::getStoreId, storeDetail -> {
			HashMap<String, String> map = new HashMap<>();
			map.put("storeName", storeDetail.getName());
			map.put("countryRegion", storeDetail.getRegion1());
			map.put("stateRegion", storeDetail.getRegion1() + storeDetail.getRegion2());
			map.put("cityRegion", storeDetail.getRegion1() + storeDetail.getRegion2() + storeDetail.getRegion3());
			map.put("categoryType", String.valueOf(storeDetail.getStoreCategoryType().getId()));

			map.put("country", storeDetail.getCountry());
			map.put("state", storeDetail.getState());
			map.put("city", storeDetail.getCity());
			return map;
		}));
    }
	
	public void validateStoresAndProducts(Set<String> storeIds, Set<String> productIds, int applicableType, boolean isSpecialtyBased) {
		validateProducts(productIds, "Products", applicableType, isSpecialtyBased);
		validateStores(storeIds, applicableType, "Store");
	}
	
	public void validateStores(Set<String> storeIds, int applicableType, String type) {
		if(UtilValidate.isEmpty(storeIds)) {
			return;
		}
		StoreSearchCriteria criteria = new StoreSearchCriteria();
		criteria.setStoreIds(new ArrayList<>(storeIds));
		CampaignUtil.validateIds(storeIds,
				getStoreDetails(criteria, PromotionConstants.APPLICABLE_TYPE_PATHLABS == applicableType,
						UserUtil.getUserDetails()).stream().map(StoreDetails::getStoreId).collect(Collectors.toSet()),
				type);
	}

	public static ResponseEntity<ByteArrayResource> downloadStoresExcel(List<String> storeIds,AccountStoreService accountStoreService) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "force-download"));
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Stores.xlsx");
		if(storeIds != null) {
			List<StoreDetails> stores = new ArrayList<>();
			if(UtilValidate.isNotEmpty(storeIds)) {
				StoreSearchCriteria criteria = new StoreSearchCriteria();
				criteria.setStoreIds(storeIds);
				stores= accountStoreService.getStoresInfo(criteria);
			}
			if(stores != null) {
				byte[] excelData = ExcelUtil.getExcelForStores(getStoresInfo(stores));
				return new ResponseEntity<>(new ByteArrayResource(excelData), header, HttpStatus.OK);
			}
		}
	    return new ResponseEntity<>(new ByteArrayResource(new byte[0]), header, HttpStatus.OK);
	}
	
}
