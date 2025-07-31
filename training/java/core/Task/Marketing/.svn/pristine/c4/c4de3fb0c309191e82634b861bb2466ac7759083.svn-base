package com.medplus.marketing.service;

import com.medplus.marketing.exception.MarketingException;
import com.medplus.marketing.handler.MarketingResponseHandler;
import com.medplus.rest.core.annotation.ClientRequest;
import com.medplus.rest.core.annotation.CommonClientRequest;
import com.medplus.rest.core.annotation.ServiceImpl;
import com.medplus.rest.core.constants.HttpMethod;

@ServiceImpl
@CommonClientRequest(name = "medpluslims",responseType = String.class, responseHandler = MarketingResponseHandler.class, exceptionClass = MarketingException.class)
public interface HealthConditionService {
	
	@ClientRequest(requestUri = "lab-test/get-parameters.pos" , httpMethod= HttpMethod.GET)
	String getParametersList();
	
	@ClientRequest(requestUri = "lab-test/get-radiology-tests.pos" , httpMethod= HttpMethod.GET)
	String getRadiologyTests();
}
