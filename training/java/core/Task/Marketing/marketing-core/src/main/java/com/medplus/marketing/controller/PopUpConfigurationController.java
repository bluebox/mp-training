package com.medplus.marketing.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.medplus.common.utility.UtilValidate;
import com.medplus.marketing.LocalDateTimeDeserializer;
import com.medplus.marketing.constants.MarketingConstants;
import com.medplus.marketing.util.UserUtil;
import com.medplus.popup.constants.PopUpErrorConstants;
import com.medplus.popup.domain.PopUpConfigSearchCriteria;
import com.medplus.popup.domain.PopUpConfiguration;
import com.medplus.popup.domain.PopUpConfigurationDto;
import com.medplus.popup.exception.PopUpException;
import com.medplus.popup.service.PopUpConfigService;
import com.medplus.reactcomponents.core.domain.form.Response;
import com.medplus.reactcomponents.core.domain.form.Response.StatusCode;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("popup")
@Slf4j
public class PopUpConfigurationController {

	private final PopUpConfigService popupConfigService;
	
	public PopUpConfigurationController(PopUpConfigService popupConfigService) {
		this.popupConfigService = popupConfigService;
	}
	
	private static final Gson GSON = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).create();
	
	@PreAuthorize(MarketingConstants.CONFIG_CREATE_RIGHTS)
	@PostMapping("update-configuration")
	public Response updateConfiguration(@RequestParam(value = "popupPathlabConfiguration") String popupConfigStr) {
		try {
			PopUpConfigurationDto popupConfigurationDto = GSON.fromJson(popupConfigStr,new TypeToken<PopUpConfigurationDto>() {}.getType());
			if(UtilValidate.isEmpty(popupConfigurationDto)){
				return new Response(StatusCode.FAILURE, PopUpErrorConstants.POPUP_CONFIGURATION_IS_EMPTY);
			}
			if(UtilValidate.isEmpty(popupConfigurationDto.getConfigId())) {
				return new Response(StatusCode.FAILURE, "ConfigId cannot be empty");
			}
			if(UtilValidate.isEmpty(popupConfigurationDto.getToDate())) {
				return new Response(StatusCode.FAILURE, "To Date cannot be empty");
			}
			PopUpConfiguration popupConfiguration = new PopUpConfiguration();
			popupConfiguration.setConfigId(Long.parseLong(popupConfigurationDto.getConfigId()));
			popupConfiguration.setToDate(Instant.ofEpochMilli(Long.valueOf(popupConfigurationDto.getToDate())).atZone(ZoneId.systemDefault()).toLocalDateTime());
			popupConfiguration.setCreatedBy(UserUtil.getUserId());
			popupConfigService.updateConfiguration(popupConfiguration);
			return new Response(StatusCode.SUCCESS, "Popup Configuration Updated Successfully.");
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error("Unable to update configuration", e);
			return new Response(StatusCode.FAILURE, PopUpErrorConstants.SOMETHING_WENT_WRONG);
		}
	}
	
	@PreAuthorize(MarketingConstants.CONFIG_VIEW_RIGHTS)
	@GetMapping("get-configuration-by-configId")
	public Response getConfigurationByConfigId(@RequestParam String configId) {
		try {
			if(!UtilValidate.isLong(configId)) {
				return new Response(StatusCode.FAILURE, "Invalid Configuration Id!");
			}
			
			PopUpConfigSearchCriteria popupSearchCriteria = new PopUpConfigSearchCriteria();
			popupSearchCriteria.setConfigIds(Arrays.asList(Long.parseLong(configId)));
			List<PopUpConfiguration> popupConfigurations = popupConfigService.getPopUpConfigurations(popupSearchCriteria);
			if (UtilValidate.isEmpty(popupConfigurations)) {
				return new Response(StatusCode.WARNING, "No Configuration Requests Found.");
			}
			return new Response(StatusCode.SUCCESS, "SUCCESS", popupConfigurations.get(0));
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error("Unable to get configuration", e);
			return new Response(StatusCode.FAILURE, PopUpErrorConstants.SOMETHING_WENT_WRONG);
		}
	}
	
}
