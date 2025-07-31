package com.medplus.marketing.controller;

import static com.medplus.marketing.constants.CommonConstants.SUCCESS;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.medplus.common.utility.UtilValidate;
import com.medplus.marketing.LocalDateTimeDeserializer;
import com.medplus.marketing.constants.MarketingConstants;
import com.medplus.marketing.exception.MarketingException;
import com.medplus.marketing.helper.MarketingDataGridHelper;
import com.medplus.marketing.util.ExcelUtil;
import com.medplus.marketing.util.PopUpUtil;
import com.medplus.marketing.util.UserUtil;
import com.medplus.popup.constants.PopUpErrorConstants;
import com.medplus.popup.constants.RequestStatus;
import com.medplus.popup.constants.RequestType;
import com.medplus.popup.domain.PatientAgeDefinedTriggerRule;
import com.medplus.popup.domain.PopUpConfigRequestSearchCriteria;
import com.medplus.popup.domain.PopUpConfiguration;
import com.medplus.popup.domain.PopUpConfigurationDto;
import com.medplus.popup.domain.PopUpConfigurationRequest;
import com.medplus.popup.exception.PopUpException;
import com.medplus.popup.helper.PopUpConfigurationHelper;
import com.medplus.popup.service.PopUpConfigRequestService;
import com.medplus.popup.service.PopUpConfigService;
import com.medplus.reactcomponents.core.domain.form.Response;
import com.medplus.reactcomponents.core.domain.form.Response.StatusCode;
import com.medplus.reactcomponents.core.enums.form.DomType;
import com.medplus.reactcomponents.core.enums.form.ElementPosition;
import com.medplus.reactcomponents.core.helpers.FormHelper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PopupConfigurationRequestController {

	private static final String SOMETHING_WENT_WRONG = "Something went wrong!";
	private static final String ERROR = "Error : {}";
	private static final Gson GSON = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).create();

	private final PopUpConfigRequestService popupConfigRequestService;
	private final PopUpConfigService popupConfigService;

	public PopupConfigurationRequestController(PopUpConfigRequestService popupConfigRequestService,PopUpConfigService popupConfigService) {
		this.popupConfigRequestService = popupConfigRequestService;
		this.popupConfigService = popupConfigService;
	}

	@PreAuthorize(MarketingConstants.CONFIG_CREATE_RIGHTS)
	@PostMapping("create-popup-config-request")
	public Response createPopupCongigurationRequest(
			@RequestParam(value = "popupPathlabConfiguration") String popupConfigStr, @RequestParam(required = false) MultipartFile uploadCustomerIdsExcel,
			@RequestParam(required = false) MultipartFile removeCustomerIdsExcel, @RequestParam(required = false) MultipartFile uploadRegionsExcel,
			@RequestParam(required = false) MultipartFile removeRegionsExcel, @RequestParam(required = false) MultipartFile uploadProductIdsExcel,
			@RequestParam(required = false) MultipartFile removeProductIdsExcel) {
		log.info("uploadCustomerIdsExcel : {},removeCustomerIdsExcel :{}, uploadRegionsExcel : {} , removeRegionsExcel: {}, uploadProductIdsExcel: {}, removeProductIdsExcel:{}",
				uploadCustomerIdsExcel, removeCustomerIdsExcel, uploadRegionsExcel, removeRegionsExcel, uploadProductIdsExcel, removeProductIdsExcel);
		if (UtilValidate.isEmpty(popupConfigStr)) {
			return new Response(StatusCode.FAILURE, "Invalid data for popup configuration");
		}
		PopUpConfigurationDto popupConfigurationDto = GSON.fromJson(popupConfigStr, new TypeToken<PopUpConfigurationDto>() {}.getType());
		return createOrUpdateConfiguration(popupConfigurationDto, uploadCustomerIdsExcel, removeCustomerIdsExcel,
				uploadRegionsExcel, removeRegionsExcel, uploadProductIdsExcel, removeProductIdsExcel);
	}

	private Response createOrUpdateConfiguration(PopUpConfigurationDto popupConfigurationDto, MultipartFile uploadCustomerIdsExcel,MultipartFile removeCustomerIdsExcel, MultipartFile uploadRegionsExcel, MultipartFile removeRegionsExcel, MultipartFile uploadProductIdsExcel, MultipartFile removeProductIdsExcel) {
		try {
			if(UtilValidate.isEmpty(popupConfigurationDto)){
				return new Response(StatusCode.FAILURE, PopUpErrorConstants.POPUP_CONFIGURATION_IS_EMPTY);
			}
			Integer requestId = UtilValidate.isNotEmpty(popupConfigurationDto.getRequestId()) ? popupConfigurationDto.getRequestId() : null;
			
			PopUpConfigurationRequest popupConfigurationRequest = PopUpConfigurationHelper.getPopUpConfiguration(popupConfigurationDto);
			if(UtilValidate.isNotEmpty(requestId)) {
				popupConfigurationRequest.setRequestId(popupConfigurationDto.getRequestId());
				popupConfigurationRequest.setConfigRequestStatus(RequestStatus.PENDING);
				popupConfigurationRequest.setRequestType(RequestType.EDIT);
				popupConfigurationRequest.setModifiedBy(UserUtil.getUserId());
	        }else {
				popupConfigurationRequest.setCreatedBy(UserUtil.getUserId());
	        	popupConfigurationRequest.setConfigRequestStatus(RequestStatus.getEnumFromValue(popupConfigurationDto.getConfigRequestStatus()));
	        }
			PopUpConfiguration marketingPopupConfiguration = popupConfigurationRequest.getPopupConfiguration();
			if(UtilValidate.isNotEmpty(uploadRegionsExcel)) {
				marketingPopupConfiguration.setIsExcelUploadForRegions("Y");
			}
			ExcelUtil.prepareConfigurationInfoFromExcel(popupConfigurationRequest, uploadProductIdsExcel, removeProductIdsExcel, uploadRegionsExcel, removeRegionsExcel, uploadCustomerIdsExcel, removeCustomerIdsExcel);
			log.info("popupConfigurationRequest, {}", popupConfigurationRequest);
			boolean status = popupConfigRequestService.insertOrUpdateConfigRequest(popupConfigurationRequest);
			if (!status) {
				return new Response(StatusCode.FAILURE, UtilValidate.isEmpty(requestId) ? "Unable to create Popup Configuration Request." : "Unable to update Popup Configuration Request.");
			}
			return new Response(StatusCode.SUCCESS, UtilValidate.isEmpty(requestId) ? "Popup configuration request created successfully." : "Popup configuration request updated successfully.");
		}catch (PopUpException | MarketingException pe) {
			log.error(ERROR, pe.getMessage());
			return new Response(StatusCode.FAILURE, pe.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.CONFIG_VIEW_RIGHTS)
	@GetMapping("get-popup-configuration-on-requestId")
	public Response getPopupConfigurationonRequestId(@RequestParam String requestId) {
		try {
			if(UtilValidate.isEmpty(requestId)) {
				return new Response(StatusCode.FAILURE, "RequestId cannot be Empty");
			}
			PopUpConfigRequestSearchCriteria popupConfigRequestSearchCriteria = new PopUpConfigRequestSearchCriteria();
			popupConfigRequestSearchCriteria.setRequestIds(Arrays.asList(Integer.parseInt(requestId)));
			List<PopUpConfigurationRequest> configRequests = popupConfigRequestService.getPopupConfigRequests(popupConfigRequestSearchCriteria);
			if (UtilValidate.isEmpty(configRequests)) {
				return new Response(StatusCode.WARNING, "No Configuration Requests Found.");
			}
			return new Response(StatusCode.SUCCESS, "SUCCESS", configRequests.get(0));
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.CONFIG_VIEW_RIGHTS)
	@PostMapping("get-popup-configuration-requests")
	public Response getPopupConfigurationRequests(@RequestBody(required = false) PopUpConfigRequestSearchCriteria popupConfigRequestSearchCriteria) {
		try {
			if(UtilValidate.isEmpty(popupConfigRequestSearchCriteria)) {
				return new Response(StatusCode.FAILURE, "Empty Search Criteria");
			}
			log.info("popupConfigRequestSearchCriteria: {}",popupConfigRequestSearchCriteria);
			if(UtilValidate.isNotEmpty(popupConfigRequestSearchCriteria)) {
				popupConfigRequestSearchCriteria.setApplicableTypes(PopUpUtil.getApplicableTypesAgainstUserRolesForConfigView(popupConfigRequestSearchCriteria.getApplicableTypes()));
			}
			List<PopUpConfigurationRequest> configRequests = popupConfigRequestService.getPopupConfigRequests(popupConfigRequestSearchCriteria);
			if (UtilValidate.isEmpty(configRequests)) {
				return new Response(StatusCode.WARNING, "No Requests Found.");
			}
			Map<String, Object> responseMap = new HashMap<>();
			Long totalNoofConfigRequests = popupConfigRequestService.getTotalNoofPopupConfigRequest(popupConfigRequestSearchCriteria);
			responseMap.put("dataGrid", MarketingDataGridHelper.preparePopupConfigRequestDataGrid(configRequests.size(), totalNoofConfigRequests));
			responseMap.put("dataSet", MarketingDataGridHelper.preparePopupConfigurationRequestDataSet(configRequests));
			PatientAgeDefinedTriggerRule patientAgeDefinedTriggerRule = popupConfigService.getAvailablePatientAgeDefinedTriggerRules();
			LinkedHashMap<String, String> patientAgeRange = (LinkedHashMap<String, String>) PopUpConfigurationHelper.getAgeRangeMap(patientAgeDefinedTriggerRule);
			patientAgeRange.put("0", "All");
			responseMap.put("ageGroups", patientAgeRange);
			responseMap.put("totalRecords", totalNoofConfigRequests);
			return new Response(StatusCode.SUCCESS, "SUCCESS", responseMap);
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}
	
	@PreAuthorize(MarketingConstants.CONFIG_CREATE_RIGHTS)
	@PostMapping("update-popup-config-request")
	public Response updatePopupConfigurationRequest(
			@RequestParam(value = "popupPathlabConfiguration") String popupConfigStr, @RequestParam(required = false) MultipartFile uploadCustomerIdsExcel,
			@RequestParam(required = false) MultipartFile removeCustomerIdsExcel, @RequestParam(required = false) MultipartFile uploadRegionsExcel,
			@RequestParam(required = false) MultipartFile removeRegionsExcel, @RequestParam(required = false) MultipartFile uploadProductIdsExcel, @RequestParam(required = false) MultipartFile removeProductIdsExcel) {
		log.info("uploadCustomerIdsExcel : {}, removeCustomerIdsExcel : {}, uploadRegionsExcel : {} , removeRegionsExcel: {}, uploadProductIdsExcel: {}, removeProductIdsExcel: {}", 
				uploadCustomerIdsExcel, removeCustomerIdsExcel, uploadRegionsExcel, removeRegionsExcel, uploadProductIdsExcel, removeProductIdsExcel);
		if (UtilValidate.isEmpty(popupConfigStr)) {
			return new Response(StatusCode.FAILURE, "Invalid data for popup configuration");
		}
		PopUpConfigurationDto popupConfigurationDto = GSON.fromJson(popupConfigStr, new TypeToken<PopUpConfigurationDto>() {}.getType());
		return createOrUpdateConfiguration(popupConfigurationDto, uploadCustomerIdsExcel, removeCustomerIdsExcel,
				uploadRegionsExcel, removeRegionsExcel, uploadProductIdsExcel, removeProductIdsExcel);
	}

	@PreAuthorize(MarketingConstants.CONFIG_VIEW_RIGHTS)
	@GetMapping("get-configuration-request-search-form")
	public Response getConfigurationRequestSearchForm(){
		return new Response(FormHelper.createDomManipulationAction(DomType.FORM, MarketingDataGridHelper.getConfigurationRequestSearchForm(), ElementPosition.UPDATE, "INIT"), StatusCode.SUCCESS, SUCCESS);
	}

	@PreAuthorize(MarketingConstants.CONFIG_APPROVE_RIGHTS)
	@PostMapping("approve-popup-config-request")
	public Response approvePopupConfigurationRequest(@RequestBody(required = false) Map<String,String> requestObj) {
		return approveOrRejectConfiguration(requestObj.get("requestId"),true, null);
	}

	private Response approveOrRejectConfiguration(String requestId, boolean isApprove, String comments) {
		try {
			if(!UtilValidate.isNumeric(requestId)) {
				return new Response(StatusCode.FAILURE, "Request Id cannot be empty.");
			}
			RequestStatus requestStatus = isApprove ? RequestStatus.APPROVED : RequestStatus.REJECTED;
			boolean status = popupConfigRequestService.approveOrRejectConfigurationRequest(Integer.valueOf(requestId) ,UserUtil.getUserId(), requestStatus, comments);
			if (!status) {
				return new Response(StatusCode.FAILURE, isApprove ? "Unable to approve Popup Configuration Request." : "Unable to reject Popup Configuration Request.");
			}
			return new Response(StatusCode.SUCCESS, isApprove ? "Popup configuration request approved successfully." : "Popup configuration request rejected successfully.");
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.CONFIG_APPROVE_RIGHTS)
	@PostMapping("reject-popup-config-request")
	public Response rejectPopupConfigurationRequest(@RequestBody(required = false) Map<String,String> requestObj) {
		return approveOrRejectConfiguration(requestObj.get("requestId"), false, requestObj.get("comments"));
	}
}
