package com.medplus.marketing.controller;

import static com.medplus.marketing.constants.CommonConstants.ID;
import static com.medplus.marketing.constants.CommonConstants.SUCCESS;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.medplus.marketing.service.PathLabTestsService;
import com.medplus.marketing.util.ExcelUtil;
import com.medplus.marketing.util.PopUpUtil;
import com.medplus.marketing.util.UserUtil;
import com.medplus.popup.constants.ApplicableType;
import com.medplus.popup.constants.DefinedTriggerRuleEnum;
import com.medplus.popup.constants.PopUpErrorConstants;
import com.medplus.popup.constants.PopUpTemplateStatus;
import com.medplus.popup.domain.CustomerDefinedTriggerRule;
import com.medplus.popup.domain.DefinedTriggerRule;
import com.medplus.popup.domain.PatientAgeDefinedTriggerRule;
import com.medplus.popup.domain.PopUpConfigRequestSearchCriteria;
import com.medplus.popup.domain.PopUpConfigSearchCriteria;
import com.medplus.popup.domain.PopUpConfiguration;
import com.medplus.popup.domain.PopUpConfigurationDto;
import com.medplus.popup.domain.PopUpConfigurationRequest;
import com.medplus.popup.domain.PopUpTemplate;
import com.medplus.popup.domain.PopUpTemplateRequest;
import com.medplus.popup.domain.PopUpTemplateSearchCriteria;
import com.medplus.popup.exception.PopUpException;
import com.medplus.popup.helper.PopUpConfigurationHelper;
import com.medplus.popup.service.PopUpConfigRequestService;
import com.medplus.popup.service.PopUpConfigService;
import com.medplus.popup.service.PopUpTemplateRequestService;
import com.medplus.popup.service.PopUpTemplateService;
import com.medplus.pos.constant.CommonConstants;
import com.medplus.reactcomponents.core.domain.form.Form;
import com.medplus.reactcomponents.core.domain.form.Response;
import com.medplus.reactcomponents.core.domain.form.Response.StatusCode;
import com.medplus.reactcomponents.core.enums.form.DomType;
import com.medplus.reactcomponents.core.enums.form.ElementPosition;
import com.medplus.reactcomponents.core.helpers.FormHelper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("popup")
@Slf4j
public class PopupController {

	private static final String APPLICATION = "application";
	private static final String FORCE_DOWNLOAD = "force-download";
	private static final String SOMETHING_WENT_WRONG = "Something went wrong!";
	private static final String ERROR = "Error : {}";
	private static final Gson GSON = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).create();
	private static final Integer MAX_INCLUSIVE_PARAMETERS_LENGTH = 5;
	private static final Integer MAX_EXCLUSIVE_PARAMETERS_LENGTH = 2;
	private static final int MAX_NO_OF_RECORDS = 500;
	

	private PathLabTestsService pathLabTestsService;
	private PopUpConfigService popupConfigService;
	private PopUpTemplateRequestService popupTemplateRequestService;
	private PopUpConfigRequestService popupConfigRequestService;
	private PopUpTemplateService popupTemplateService;

	@Autowired
	public PopupController(PathLabTestsService pathLabTestsService,
			PopUpConfigService popupConfigService, PopUpTemplateRequestService popupTemplateRequestService,
			PopUpConfigRequestService popupConfigRequestService, PopUpTemplateService popupTemplateService) {
		this.pathLabTestsService = pathLabTestsService;
		this.popupConfigService = popupConfigService;
		this.popupTemplateRequestService = popupTemplateRequestService;
		this.popupConfigRequestService = popupConfigRequestService;
		this.popupTemplateService = popupTemplateService;
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_CREATE_RIGHTS)
	@GetMapping("get-create-template-form")
	public Response getCreateTemplateForm() {
		return new Response(FormHelper.createDomManipulationAction(DomType.FORM, MarketingDataGridHelper.getCreateTemplateForm(), ElementPosition.UPDATE, "INIT"), StatusCode.SUCCESS, SUCCESS);
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_VIEW_RIGHTS)
	@GetMapping("get-template-search-form")
	public Response getTemplateSearchForm() {
		return new Response(FormHelper.createDomManipulationAction(DomType.FORM, MarketingDataGridHelper.getTemplateSearchForm(), ElementPosition.UPDATE, "INIT"), StatusCode.SUCCESS, SUCCESS);
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_VIEW_RIGHTS)
	@PostMapping("get-templates")
	public Response getTemplates(@RequestParam(value = "searchCriteria") String searchCriteriaStr) {
		try {
			log.info("popupSeachCriteriaStr {} ", searchCriteriaStr);
			PopUpTemplateSearchCriteria popupTemplateSearchCriteria = GSON.fromJson(searchCriteriaStr, new TypeToken<PopUpTemplateSearchCriteria>() {}.getType());
			if(UtilValidate.isNotEmpty(searchCriteriaStr) && UtilValidate.isNotEmpty(popupTemplateSearchCriteria)) {
				popupTemplateSearchCriteria.setApplicableTypes(PopUpUtil.getApplicableTypesAgainstUserRolesForTemplateView(popupTemplateSearchCriteria.getApplicableTypes()));
			}
			Map<String, Object> responseMap = new HashMap<>();
			List<PopUpTemplate> popupTemplates = popupTemplateService.getPopupTemplates(popupTemplateSearchCriteria);
			responseMap.put("dataSet", MarketingDataGridHelper.prepareTemplateDataSet(popupTemplates));
			responseMap.put("totalRecords", popupTemplates.size());
			responseMap.put("dataGrid", MarketingDataGridHelper.prepareTemplateDataGrid((long) popupTemplateSearchCriteria.getNoOfRecords(), (long) popupTemplates.size()));
			return new Response(StatusCode.SUCCESS, SUCCESS, responseMap);
		}catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_CREATE_RIGHTS)
	@PostMapping("create-template")
	public Response createTemplate(@RequestParam(value = "templateInfo") String templateInfoStr) {
		try {
			log.info("templateInfoStr : {}", templateInfoStr);
			PopUpTemplateRequest popupTemplate = GSON.fromJson(templateInfoStr, new TypeToken<PopUpTemplateRequest>() {}.getType());
			popupTemplate.setCreatedBy(UserUtil.getUserId());
			popupTemplateRequestService.insertOrUpdatePopupTemplateRequest(popupTemplate);
			return new Response(StatusCode.SUCCESS, SUCCESS, popupTemplate);
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.CONFIG_VIEW_RIGHTS)
	@GetMapping("get-pathlab-create-configuration-form")
	public Response getPathLabConfigurationForm() {
		try {
			PatientAgeDefinedTriggerRule patientAgeDefinedTriggerRule = popupConfigService.getAvailablePatientAgeDefinedTriggerRules();
			LinkedHashMap<String, String> patientAgeRange = (LinkedHashMap<String, String>) PopUpConfigurationHelper.getAgeRangeMap(patientAgeDefinedTriggerRule);
			Form templateSelectionForm = MarketingDataGridHelper.getPathLabConfigurationForm(getTemplates(ApplicableType.PATHLABS), patientAgeRange);
			Map<String, Integer> parameterResultLengths = new HashMap<>();
			parameterResultLengths.put("INCLUSIVE_PARAMETERS_LENGTH", MAX_INCLUSIVE_PARAMETERS_LENGTH);
			parameterResultLengths.put("EXCLUSIVE_PARAMETERS_LENGTH", MAX_EXCLUSIVE_PARAMETERS_LENGTH);
			return new Response(FormHelper.createDomManipulationAction(DomType.FORM, templateSelectionForm, ElementPosition.UPDATE, "INIT"), StatusCode.SUCCESS, SUCCESS, parameterResultLengths);
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}
	
	@GetMapping("get-create-configuration-form")
	public Response getConfigurationForm() {
		try {
			Form templateSelectionForm = MarketingDataGridHelper.getConfigurationForm(getTemplates(ApplicableType.PHARMACY));
			return new Response(FormHelper.createDomManipulationAction(DomType.FORM, templateSelectionForm,ElementPosition.UPDATE, "INIT"), StatusCode.SUCCESS, SUCCESS);
		} catch (PopUpException me) {
			log.error(ERROR, me.getMessage());
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.CONFIG_CREATE_RIGHTS)
	@PostMapping("create-configuration")
	public Response createConfiguration(@RequestParam(value = "popupConfigurationRequest") String popupConfigStr, @RequestParam(required = false) MultipartFile uploadProductIdsExcel,
			@RequestParam(required = false) MultipartFile uploadRegionsExcel, @RequestParam(required = false) MultipartFile removeProductIdsExcel,
			@RequestParam(required = false) MultipartFile removeRegionsExcel) {
		try {
			
			log.info("popupConfigStr : {} , uploadProductIdsExcel : {}, uploadRegionsExcel : {} , removeProductIdsExcel : {} , removeRegionsExcel: {}", popupConfigStr, uploadProductIdsExcel,
					uploadRegionsExcel, removeProductIdsExcel, removeRegionsExcel);
			PopUpConfigurationRequest popupConfiguration = GSON.fromJson(popupConfigStr, new TypeToken<PopUpConfigurationRequest>() {}.getType());
			ExcelUtil.prepareConfigurationInfoFromExcel(popupConfiguration, uploadProductIdsExcel, removeProductIdsExcel, uploadRegionsExcel, removeRegionsExcel, null,null);
			popupConfiguration.setCreatedBy(UserUtil.getUserId());
			boolean status = popupConfigRequestService.insertOrUpdateConfigRequest(popupConfiguration);
			if(!status) {
				return new Response(StatusCode.FAILURE, "Unable to create Popup Configuration Request.");
			}
			log.debug(" popupConfiguration : {}", popupConfiguration);
			return new Response(StatusCode.SUCCESS, SUCCESS, "Popup configuration request created successfully.");
		} catch (PopUpException | MarketingException pe) {
			return new Response(StatusCode.FAILURE, pe.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.CONFIG_VIEW_RIGHTS)
	@GetMapping("get-configuration-search-form")
	public Response getConfigurationSearchForm() {
		return new Response(FormHelper.createDomManipulationAction(DomType.FORM, MarketingDataGridHelper.getConfigurationSearchForm(), ElementPosition.UPDATE, "INIT"), StatusCode.SUCCESS, SUCCESS);
	}

	@PreAuthorize(MarketingConstants.CONFIG_VIEW_RIGHTS)
	@PostMapping("get-configurations")
	public Response getConfigurations(@RequestParam(value = "searchCriteria") String searchCriteriaStr) {
		try {
			log.info("searchCriteriaStr {} ", searchCriteriaStr);
			PopUpConfigSearchCriteria popupSearchCriteria = GSON.fromJson(searchCriteriaStr, new TypeToken<PopUpConfigSearchCriteria>() {}.getType());
			if(UtilValidate.isNotEmpty(popupSearchCriteria)) {
				popupSearchCriteria.setApplicableTypes(PopUpUtil.getApplicableTypesAgainstUserRolesForConfigView(popupSearchCriteria.getApplicableTypes()));
			}
			Map<String, Object> responseMap = new HashMap<>();
			List<PopUpConfiguration> popupConfigurations = popupConfigService.getPopUpConfigurationHeader(popupSearchCriteria);
			responseMap.put("dataSet", MarketingDataGridHelper.preparePopupConfigurationDataSet(popupConfigurations));
			responseMap.put("totalRecords", popupConfigurations.size());
			responseMap.put("dataGrid", MarketingDataGridHelper.preparePopupConfigDataGrid(popupSearchCriteria.getNoOfRecords(), (long)popupConfigurations.size()));
			PatientAgeDefinedTriggerRule patientAgeDefinedTriggerRule = popupConfigService.getAvailablePatientAgeDefinedTriggerRules();
			LinkedHashMap<String, String> patientAgeRange = (LinkedHashMap<String, String>) PopUpConfigurationHelper.getAgeRangeMap(patientAgeDefinedTriggerRule);
			patientAgeRange.put("0", "All");
			responseMap.put("ageGroups", patientAgeRange);
			return new Response(StatusCode.SUCCESS, SUCCESS, responseMap);
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.CONFIG_VIEW_RIGHTS)
	@GetMapping("get-productIds-excel-download")
	public ResponseEntity<ByteArrayResource> getProductExcelDownload(@RequestParam(ID) Integer id) {
		log.info("Popup Configuration ProductIds Download for Id : {}", id);
		Set<String> productIds = null;
		PopUpConfigRequestSearchCriteria popupConfigRequestSearchCriteria = new PopUpConfigRequestSearchCriteria();
		popupConfigRequestSearchCriteria.setRequestIds(Arrays.asList(id));
		try {
			List<PopUpConfigurationRequest> configRequests = popupConfigRequestService.getPopupConfigRequests(popupConfigRequestSearchCriteria);
			if (UtilValidate.isNotEmpty(configRequests)) {
				productIds = new HashSet<>(configRequests.get(0).getPopupConfiguration().getProductIds());
			}
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType(APPLICATION, FORCE_DOWNLOAD));
			header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductTemplate.xlsx");
			log.debug("Products Downloaded: {}", productIds);
			return new ResponseEntity<>(new ByteArrayResource(ExcelUtil.writeExcel(productIds, "ItemId", null)), header, HttpStatus.OK);
		} catch (PopUpException me) {
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error(ERROR, e);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PreAuthorize(MarketingConstants.CONFIG_VIEW_RIGHTS)
	@GetMapping("get-regions-excel-download")
	public ResponseEntity<ByteArrayResource> getRegionExcelDownload(@RequestParam(ID) Integer id) {
		log.info("Popup Configuration Regions Download for Id : {}", id);
		Set<String> regions = null;
		PopUpConfigRequestSearchCriteria popupConfigRequestSearchCriteria = new PopUpConfigRequestSearchCriteria();
		popupConfigRequestSearchCriteria.setRequestIds(Arrays.asList(id));
		try {
			List<PopUpConfigurationRequest> configRequests = popupConfigRequestService.getPopupConfigRequests(popupConfigRequestSearchCriteria);
			if (UtilValidate.isNotEmpty(configRequests)) {
				regions = new HashSet<>(configRequests.get(0).getPopupConfiguration().getRegions());
			}
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType(APPLICATION, FORCE_DOWNLOAD));
			header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=RegionTemplate.xlsx");
			log.debug("Regions Downloaded: {}", regions);
			return new ResponseEntity<>(new ByteArrayResource(ExcelUtil.writeExcel(regions, "Region", null)), header, HttpStatus.OK);
		} catch (PopUpException me) {
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error(ERROR, e);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PreAuthorize(MarketingConstants.CONFIG_VIEW_RIGHTS)
	@GetMapping("get-customerIds-excel-download")
	public ResponseEntity<ByteArrayResource> getCustomerExcelDownload(@RequestParam(ID) Integer id) {
		log.info("Popup Configuration CustomerIds Download for Id : {}", id);
		Set<Long> customerIds = null;
		PopUpConfigRequestSearchCriteria popupConfigRequestSearchCriteria = new PopUpConfigRequestSearchCriteria();
		popupConfigRequestSearchCriteria.setRequestIds(Arrays.asList(id));
		try {
			List<PopUpConfigurationRequest> configRequests = popupConfigRequestService.getPopupConfigRequests(popupConfigRequestSearchCriteria);
			if (UtilValidate.isNotEmpty(configRequests)) {
				List<DefinedTriggerRule> definedTriggerRules = configRequests.get(0).getPopupConfiguration()
						.getDefinedTrigger().getDefinedTriggerRules();
				if (UtilValidate.isNotEmpty(definedTriggerRules)) {
					for (DefinedTriggerRule each : definedTriggerRules) {
						if (each.getDefinedTriggerType() == DefinedTriggerRuleEnum.CUSTOMER) {
							customerIds = new HashSet<>(((CustomerDefinedTriggerRule) each).getSelectedCustomerIds());
						}
					}
				}
			}
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType(APPLICATION, FORCE_DOWNLOAD));
			header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=CustomerTemplate.xlsx");
			log.debug("Customers Downloaded Size: {}", customerIds);
			return new ResponseEntity<>(new ByteArrayResource(ExcelUtil.writeExcel(customerIds, "CustomerId", null)), header, HttpStatus.OK);
		} catch (PopUpException me) {
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error(ERROR, e);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PreAuthorize(MarketingConstants.CONFIG_VIEW_RIGHTS)
	@GetMapping("get-Parameters")
	public Response getParameters(HttpServletRequest request) {
		boolean  isRadiologyTests = false;
		try {
			isRadiologyTests = "Y".equals(request.getParameter("isRadiologyTests"));
			Map<String, String> parametersList = isRadiologyTests ? pathLabTestsService.getRadiologyTests() : pathLabTestsService.getParametersList();
			return new Response(Response.StatusCode.SUCCESS, (isRadiologyTests ? "Radiology Tests" : "Parameters") + " retrieved successfully", parametersList);
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(Response.StatusCode.FAILURE, "Failed to retrieve " + (isRadiologyTests ? "Radiology Tests" : "Parameters"));
		}
	}

	private List<PopUpTemplate> getTemplates(ApplicableType applicableType) throws PopUpException {
		PopUpTemplateSearchCriteria searchCriteria = new PopUpTemplateSearchCriteria();
		searchCriteria.setLimitFrom(CommonConstants.ZERO);
		searchCriteria.setNoOfRecords(MAX_NO_OF_RECORDS);
		searchCriteria.setApplicableTypes(Arrays.asList(applicableType));
		searchCriteria.setPopUpTemplateStatuses(Arrays.asList(PopUpTemplateStatus.ACTIVE));
		return popupTemplateService.getPopupTemplates(searchCriteria);
	}

	@GetMapping("check-template-name")
	public Response isTemplateNameExist(@RequestParam String templateName) {
		try {
			log.info("templateName : {}", templateName);
			PopUpTemplateRequest request = new PopUpTemplateRequest();
			request.setTemplateName(templateName);
			if (popupTemplateRequestService.checkIfTemplateNameExists(request)) {
				return new Response(StatusCode.FAILURE, "Template name already exist. Please enter another name.");
			}
			return new Response(StatusCode.SUCCESS, SUCCESS);
		} catch (PopUpException pe) {
			return new Response(StatusCode.FAILURE, pe.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@GetMapping("check-configuration-name")
	public Response isConfigurationNameExist(@RequestParam String configurationName) {
		try {
			log.info("configurationName : {}", configurationName);
			PopUpConfigurationRequest request = new PopUpConfigurationRequest();
			request.setPopUpConfigurationName(configurationName);
			if (popupConfigService.checkIsConfigNameExitst(request)) {
				return new Response(StatusCode.FAILURE, "Popup Configuration Name already exist. Please enter another name");
			}
			return new Response(StatusCode.SUCCESS, SUCCESS);
		} catch (PopUpException pe) {
			return new Response(StatusCode.FAILURE, pe.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_CREATE_RIGHTS)
	@PostMapping("update-popup-template")
	public Response updatePopUpTemplate(@RequestParam(value = "templateInfo") String templateInfoStr) {
		try {
			log.info("popupTemplateInfoStr : {}", templateInfoStr);
			PopUpTemplate popupTemplate = GSON.fromJson(templateInfoStr, new TypeToken<PopUpTemplate>() {}.getType());
			popupTemplate.setModifiedBy(UserUtil.getUserId());
			boolean status = popupTemplateService.createOrUpdateTemplate(popupTemplate);
			if (!status) {
				return new Response(StatusCode.FAILURE, "Unable to Update Popup Template.");
			}
			return new Response(StatusCode.SUCCESS, "Popup Template Updated Successfully.");
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.CONFIG_CREATE_RIGHTS)
	@PostMapping("update-popup-configuration")
	public Response updateConfiguration(@RequestParam(value = "popupPathlabConfiguration") String popupConfigStr, @RequestParam(required = false) MultipartFile uploadProductIdsExcel,
			@RequestParam(required = false) MultipartFile uploadRegionsExcel, @RequestParam(required = false) MultipartFile removeProductIdsExcel,
			@RequestParam(required = false) MultipartFile removeRegionsExcel) {
		try {
			log.info("popupTemplateInfoStr : {} , uploadProductIdsExcel : {}, uploadRegionsExcel : {} , removeProductIdsExcel : {} , removeRegionsExcel: {}", popupConfigStr, uploadProductIdsExcel,
					uploadRegionsExcel, removeProductIdsExcel, removeRegionsExcel);
			PopUpConfigurationDto popupConfigurationDto = GSON.fromJson(popupConfigStr,new TypeToken<PopUpConfigurationDto>() {}.getType());
			if(UtilValidate.isEmpty(popupConfigurationDto)){
				return new Response(StatusCode.FAILURE, PopUpErrorConstants.POPUP_CONFIGURATION_IS_EMPTY);
			}
			PopUpConfigurationRequest popupConfigurationRequest = PopUpConfigurationHelper.getPopUpConfiguration(popupConfigurationDto);
			PopUpConfiguration popupConfiguration = popupConfigurationRequest.getPopupConfiguration();
			popupConfiguration.setModifiedBy(UserUtil.getUserId());
			//ExcelUtil.prepareConfigurationInfoFromExcel(popupConfiguration, uploadProductIdsExcel, removeProductIdsExcel, uploadRegionsExcel, removeRegionsExcel);
			popupConfigService.createOrUpdateConfigration(popupConfiguration);
			return new Response(StatusCode.SUCCESS, "Popup Configuration Updated Successfully.");
		} catch (PopUpException | MarketingException pe) {
			return new Response(StatusCode.FAILURE, pe.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_VIEW_RIGHTS)
	@GetMapping("get-template")
	public Response getTemplate(@RequestParam(required = false) String templateId) {
		try {
			if(!UtilValidate.isNumeric(templateId)) {
				return new Response(StatusCode.FAILURE, "Template Id is Empty");
			}
			PopUpTemplateSearchCriteria popUpTemplateSearchCriteria = new PopUpTemplateSearchCriteria();
			if(UtilValidate.isNotEmpty(popUpTemplateSearchCriteria)) {
				popUpTemplateSearchCriteria.setApplicableTypes(PopUpUtil.getApplicableTypesAgainstUserRolesForTemplateView(popUpTemplateSearchCriteria.getApplicableTypes()));
			}
			popUpTemplateSearchCriteria.setTemplateIds(Arrays.asList(Integer.valueOf(templateId)));
			List<PopUpTemplate> popUpTemplates = popupTemplateService.getPopupTemplates(popUpTemplateSearchCriteria);
			if(UtilValidate.isEmpty(popUpTemplates)) {
				return new Response(StatusCode.FAILURE, "Template Not Found");
			}
			return new Response(StatusCode.SUCCESS, SUCCESS, popUpTemplates.get(0));
		}catch(PopUpException pe) {
			return new Response(StatusCode.FAILURE, pe.getMessage());
		}catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}


}
