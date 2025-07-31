package com.medplus.marketing.handler;

import java.lang.reflect.Type;
import java.util.Optional;

import com.medplus.marketing.constants.CommonConstants;
import com.medplus.rest.core.domain.DatePatterns;
import com.medplus.rest.core.handler.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MarketingResponseHandler implements ResponseHandler {
	
	@Override
	public Object handleResponse(Optional<Object> genericResponse, Type type, DatePatterns datePatterns) {
		if(genericResponse.isPresent()) {
			String jsonResponse = CommonConstants.GSON.toJson(genericResponse.get());
			log.debug("data : {}, type: {}", jsonResponse, type);
			return CommonConstants.GSON.fromJson(jsonResponse, type);
		}
		return null;
	}
}
