package com.medplus.marketing.service;

import java.util.List;
import java.util.Map;

public interface PathLabTestsService {

	Map<String,String> getPathTestByIdList(List<String> labTestIdsList,boolean isActiveCheck);
	Map<String, String> getSpecialtyIds(List<String> specialtyIds,boolean isActiveCheck);
	Map<String, String> getParametersList();
	Map<String, String> getRadiologyTests();
}
