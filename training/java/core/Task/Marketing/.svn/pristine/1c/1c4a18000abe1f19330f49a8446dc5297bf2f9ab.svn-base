package com.medplus.marketing.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.medplus.cache.MedplusCacheable;
import com.medplus.common.utility.UtilValidate;
import com.medplus.marketing.service.HealthConditionService;
import com.medplus.marketing.service.PathLabTestsService;
import com.medplus.popup.cache.PopUpConfigParameterCacheEvent;
import com.medplus.popup.cache.PopUpConfigRadiologyTestsCacheEvent;
import com.medplus.pos.constant.CommonConstants;
import com.medplus.solr.core.impl.DoctorsCoreHelper;
import com.medplus.solr.core.impl.PathLabsTestsCoreHelper;
import com.medplus.solr.core.pojo.LabServiceSearchCriteria;
import com.medplus.solr.core.pojo.SpecialitySearchCriteria;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PathLabTestsServiceImpl implements PathLabTestsService {
	
	private static final DoctorsCoreHelper DOCTOR_CORE_HELPER = new DoctorsCoreHelper();

	private final HealthConditionService healthConditionService;

	private static final String DEFAULT_PARAMETER_ID = "830";

	@Autowired
	public PathLabTestsServiceImpl (HealthConditionService healthConditionService) {
		this.healthConditionService = healthConditionService;
	}
	
	@Override
	public Map<String,String> getPathTestByIdList(List<String> labTestIdsList,boolean isActiveCheck) {
		LabServiceSearchCriteria searchCriteria = new LabServiceSearchCriteria();
		searchCriteria.setTestCodes(labTestIdsList);
		if(isActiveCheck) {
			searchCriteria.setStatus("A");
		}
		return convertSolrDocumentListToLabTestIdsMap(PathLabsTestsCoreHelper.getPathLabServices(searchCriteria));
	}

	private Map<String,String> convertSolrDocumentListToLabTestIdsMap(SolrDocumentList solrDocumentList) {
		Map<String,String> testIdsMap = new HashMap<>();
		if(UtilValidate.isNotEmpty(solrDocumentList)){
			for(SolrDocument eachDoc : solrDocumentList){
				testIdsMap.put(eachDoc.get("code_s") != null ? eachDoc.get("code_s").toString() : "", 
						eachDoc.get("name_s") != null ? eachDoc.get("name_s").toString() : "");
			}
		}
		return testIdsMap;
	}

	@Override
	public Map<String,String> getSpecialtyIds(List<String> specialtyIds,boolean isActiveCheck) {
		if(Objects.isNull(specialtyIds))
			return Collections.emptyMap();
		List<Long> spIds = specialtyIds.stream().map(Long::parseLong).collect(Collectors.toList());
		SpecialitySearchCriteria specialitySearchCriteria = new SpecialitySearchCriteria();
		specialitySearchCriteria.setSpecialityIds(spIds);
		if(isActiveCheck) {
			specialitySearchCriteria.setStatus("A");
		}
		SolrDocumentList solrDocumentList = DOCTOR_CORE_HELPER.getSpecialities(specialitySearchCriteria);
		if(UtilValidate.isEmpty(solrDocumentList)) {
			return Collections.emptyMap();
		}
		Map<String,String> availableSpecialityIds = new HashMap<>();
		solrDocumentList.forEach(solrSpeciality -> {
			if(UtilValidate.isNotEmpty(solrSpeciality)) {
				availableSpecialityIds.put(getStringValue(solrSpeciality.get("specialityId_s")),
						getStringValue(solrSpeciality.get("documentName")));
			}
		});
		return availableSpecialityIds;
	}
	
	private String getStringValue(Object obj) {
		if(UtilValidate.isNotEmpty(obj)) {
			return obj.toString();
		}
		return null;
	}

	@Override
	@MedplusCacheable(expiresInSec = 864000, cacheClearingEvent = PopUpConfigParameterCacheEvent.class)
	public Map<String, String> getParametersList() {
		try {
			String parametersListResponse = healthConditionService.getParametersList();
			Map<String, String> parametersList = UtilValidate.isNotEmpty(parametersListResponse)
					? CommonConstants.GSON.fromJson(parametersListResponse, new TypeToken<Map<String, String>>() {}.getType())
					: Collections.emptyMap();
			parametersList.remove(DEFAULT_PARAMETER_ID);
			log.info("Parameters list retrieved: {}", parametersList);
			return parametersList;
		} catch (Exception e) {
			log.error("Exception occurred while getting parameters list: ", e);
			return Collections.emptyMap();
		}
	}
	
	@Override
	@MedplusCacheable(cacheClearingEvent = PopUpConfigRadiologyTestsCacheEvent.class)
	public Map<String, String> getRadiologyTests() {
		try {
			String radiologyTestsListResponse = healthConditionService.getRadiologyTests();
			return UtilValidate.isNotEmpty(radiologyTestsListResponse)
					? CommonConstants.GSON.fromJson(radiologyTestsListResponse, new TypeToken<Map<String, String>>() {}.getType())
					: Collections.emptyMap();
		}
		catch(Exception e) {
			log.error("Exception occurred while getting radiology tests:{} ", e);
			return Collections.emptyMap();
		}
	}

}
