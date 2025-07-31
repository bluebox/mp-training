package com.medplus.marketing.helper;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.medplus.reactcomponents.core.domain.form.Response;
import com.medplus.reactcomponents.core.domain.form.Response.StatusCode;

public class MarketingResponseHelper {
	
	private MarketingResponseHelper() {}
	
	public static ResponseEntity<Response> sendErrorResponse(StatusCode status, String message,
			HttpStatus httpStatus) {
		return new ResponseEntity<>(new Response(status, message, null), httpStatus);
	}

}