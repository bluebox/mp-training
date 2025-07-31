package com.medplus.marketing.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.cache.MedplusCache;
import com.medplus.discounts.cache.MultiItemCampaignCacheEvent;
import com.medplus.discounts.cache.PromotionCacheEvent;
import com.medplus.discounts.cache.RemovedItemEvent;
import com.medplus.discounts.cache.RemovedItemEventNoCacheEmptyResult;
import com.medplus.popup.cache.PopUpConfigAgeRulesCacheEvent;
import com.medplus.popup.cache.PopUpConfigParameterCacheEvent;
import com.medplus.popup.cache.PopUpConfigRadiologyTestsCacheEvent;
import com.medplus.reactcomponents.core.domain.form.Response;
import com.medplus.reactcomponents.core.domain.form.Response.StatusCode;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CacheController {

	@Autowired
	MedplusCache eventPublisher;

	@GetMapping("update-promotions")
	public Response updatePromotions() {
		boolean cacheCleared = false;
		try {
			log.info("Discount Cache Clearing event trigerred at {}", new Date());
			eventPublisher.fireClearEvent(new RemovedItemEvent(""));
			eventPublisher.fireClearEvent(new PromotionCacheEvent(""));
			eventPublisher.fireClearEvent(new RemovedItemEventNoCacheEmptyResult(""));
			eventPublisher.fireClearEvent(new MultiItemCampaignCacheEvent(""));
			cacheCleared = true;
		} catch (Exception e) {
			log.error("Unable to clear Discount cache : {}", e.getMessage(), e);
		}

		return cacheCleared ? new Response(StatusCode.SUCCESS, "Discount Cache Cleared Successfully.")
				: new Response(StatusCode.FAILURE, "Unable to clear Discount Cache.");

	}

	@GetMapping("clear-parametersList")
	public Response clearParametersList() {
		boolean cacheCleared = false;
		try {
			log.info("Paramerers List Cache Clearing event trigerred at {}", new Date());
			eventPublisher.fireClearEvent(new PopUpConfigParameterCacheEvent(""));
			cacheCleared = true;
		} catch (Exception e) {
			log.error("Error : {}", e.getMessage(), e);
		}
		return cacheCleared ? new Response(StatusCode.SUCCESS, "Parameters list cleared from Cache Successfully.")
				: new Response(StatusCode.FAILURE, "Unable to clear Parameters list from Cache.");
	}

	@GetMapping("clear-age-mapping")
	public Response clearAgeMapping() {
		boolean cacheCleared = false;
		try {
			log.info("Age mapping Cache Clearing event trigerred at {}", new Date());
			eventPublisher.fireClearEvent(new PopUpConfigAgeRulesCacheEvent(""));
			cacheCleared = true;
		} catch (Exception e) {
			log.error("Error : ", e);
		}
		return cacheCleared ? new Response(StatusCode.SUCCESS, "Parameters list cleared from Cache Successfully.")
				: new Response(StatusCode.FAILURE, "Unable to clear Parameters list from Cache.");
	}

	@GetMapping("clear-radiology-tests")
	public Response clearRadiologyTests() {
		boolean cacheCleared = false;
		try {
			log.info("Radiology Test Cache Clearing event trigerred at {}", new Date());
			eventPublisher.fireClearEvent(new PopUpConfigRadiologyTestsCacheEvent(""));
			cacheCleared = true;
		} catch (Exception e) {
			log.error("Error : ", e);
		}
		return cacheCleared ? new Response(StatusCode.SUCCESS, "Radiology list cleared from Cache Successfully.")
				: new Response(StatusCode.FAILURE, "Unable to clear Radiology list from Cache.");
	}
}
