package com.medplus.marketing.interceptor;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.MDC;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;


public class RequestInterceptor implements WebRequestInterceptor{

	private static final AtomicLong ATOMIC_LONG = new AtomicLong();

	@Override
	public void preHandle(WebRequest request) throws Exception {
		String requestId = String.format("ReqId-%s", ATOMIC_LONG.getAndIncrement());
		MDC.put("REQUEST_ID", requestId);
	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		/* postHandle */
	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
		MDC.remove("REQUEST_ID");
	}
	
}

