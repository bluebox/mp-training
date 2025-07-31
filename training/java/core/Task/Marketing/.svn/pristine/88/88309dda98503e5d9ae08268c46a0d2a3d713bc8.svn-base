package com.medplus.marketing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import com.medplus.marketing.interceptor.RequestInterceptor;

@EnableAsync
@Configuration
public class MarketingConfigurer implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new WebRequestHandlerInterceptorAdapter(new RequestInterceptor()));
	}
	
}
