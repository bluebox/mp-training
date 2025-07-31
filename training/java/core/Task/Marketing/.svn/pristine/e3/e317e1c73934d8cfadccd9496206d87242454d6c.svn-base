package com.medplus.marketing.controller;

import static com.medplus.marketing.constants.CommonConstants.SUCCESS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.common.utility.UtilValidate;
import com.medplus.marketing.constants.MarketingConstants;
import com.medplus.marketing.helper.MarketingDataGridHelper;
import com.medplus.marketing.util.PopUpUtil;
import com.medplus.marketing.util.UserUtil;
import com.medplus.popup.constants.PopUpErrorConstants;
import com.medplus.popup.constants.RequestStatus;
import com.medplus.popup.domain.PopUpTemplateRequest;
import com.medplus.popup.domain.PopUpTemplateRequestSearchCriteria;
import com.medplus.popup.exception.PopUpException;
import com.medplus.popup.service.PopUpTemplateRequestService;
import com.medplus.reactcomponents.core.domain.form.Response;
import com.medplus.reactcomponents.core.domain.form.Response.StatusCode;
import com.medplus.reactcomponents.core.enums.form.DomType;
import com.medplus.reactcomponents.core.enums.form.ElementPosition;
import com.medplus.reactcomponents.core.helpers.FormHelper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PopupTemplateRequestController {

	private static final String SOMETHING_WENT_WRONG = "Something went wrong!";
	private static final String ERROR = "Error : ";

	private final PopUpTemplateRequestService popupTemplateRequestService;

	@Autowired
	public PopupTemplateRequestController(PopUpTemplateRequestService popupTemplateRequestService) {
		this.popupTemplateRequestService = popupTemplateRequestService;
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_CREATE_RIGHTS)
	@PostMapping("create-popup-template-request")
	public Response createPopupTemplateRequest(@RequestBody(required = false) PopUpTemplateRequest popupTemplateRequest) {
		return createOrUpdateTemplate(popupTemplateRequest, true);
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_VIEW_RIGHTS)
	@GetMapping("check-if-template-name-exists")
	public Response checkIfTemplateNameExists(@RequestParam(required = false) String templateName) {
		try {
			if (UtilValidate.isEmpty(templateName)) {
				return new Response(StatusCode.FAILURE, PopUpErrorConstants.TEMPLATE_NAME_CANNOT_BE_EMPTY);
			}
			PopUpTemplateRequest request = new PopUpTemplateRequest();
			request.setTemplateName(templateName);
			if (popupTemplateRequestService.checkIfTemplateNameExists(request)) {
				return new Response(StatusCode.FAILURE, "Template with name "+ templateName + " already exists");
			}
			return new Response(StatusCode.SUCCESS, "No template found with name "+ templateName);
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	private Response createOrUpdateTemplate(PopUpTemplateRequest popupTemplateRequest, boolean isFromCreate) {
		try {
			if (UtilValidate.isEmpty(popupTemplateRequest)) {
				return new Response(StatusCode.FAILURE, PopUpErrorConstants.POPUP_TEMPLATE_ID_IS_EMPTY);
			}
			if (isFromCreate) {
				popupTemplateRequest.setCreatedBy(UserUtil.getUserId());
			} else {
				popupTemplateRequest.setModifiedBy(UserUtil.getUserId());
			}
			boolean status = popupTemplateRequestService.insertOrUpdatePopupTemplateRequest(popupTemplateRequest);
			if (!status) {
				return new Response(StatusCode.FAILURE, "Unable to " + (isFromCreate ? "Save" : "Update") + " Popup Template Request.");
			}
			return new Response(StatusCode.SUCCESS, "Popup Template Request " + (isFromCreate ? "Saved" : "Updated") + " Successfully.");
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_VIEW_RIGHTS)
	@GetMapping("get-template-request")
	public Response getPopupTemplateRequestById(@RequestParam(required = false) String requestId) {
		try {
			if(!UtilValidate.isNumeric(requestId)) {
				return new Response(StatusCode.FAILURE, "RequestId is Empty");
			}
			PopUpTemplateRequestSearchCriteria searchCriteria = new PopUpTemplateRequestSearchCriteria();
			searchCriteria.setRequestIds(Arrays.asList(Integer.valueOf(requestId)));
			List<PopUpTemplateRequest> templateRequests = fetchPopupTemplateRequests(searchCriteria);
			return new Response(StatusCode.SUCCESS, SUCCESS, templateRequests.get(0));
		}catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_VIEW_RIGHTS)
	@PostMapping("get-popup-template-requests")
	public Response getPopupTemplateRequests(@RequestBody(required = false) PopUpTemplateRequestSearchCriteria popupTemplateRequestSearchCriteria) {
		try {
			if(UtilValidate.isEmpty(popupTemplateRequestSearchCriteria)) {
				return new Response(StatusCode.FAILURE, "Empty Search Criteria");
			}
			log.info("PopUpTemplateRequestSearchCriteria : {}",popupTemplateRequestSearchCriteria);
			if(UtilValidate.isNotEmpty(popupTemplateRequestSearchCriteria)) {
				popupTemplateRequestSearchCriteria.setApplicableTypes(PopUpUtil.getApplicableTypesAgainstUserRolesForTemplateView(popupTemplateRequestSearchCriteria.getApplicableTypes()));
			}
			List<PopUpTemplateRequest> templateRequests = fetchPopupTemplateRequests(popupTemplateRequestSearchCriteria);
			Long totalNoOfPopRequests = popupTemplateRequestService.getTotalNoOfRequests(popupTemplateRequestSearchCriteria);
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("dataSet", MarketingDataGridHelper.prepareTemplateRequestsDataSet(templateRequests));
			responseMap.put("dataGrid", MarketingDataGridHelper.prepareTemplateRequestsDataGrid((long) templateRequests.size(), totalNoOfPopRequests));
			responseMap.put("totalRecords", totalNoOfPopRequests);
			return new Response(StatusCode.SUCCESS, "SUCCESS", responseMap);
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	private List<PopUpTemplateRequest> fetchPopupTemplateRequests(PopUpTemplateRequestSearchCriteria popupTemplateRequestSearchCriteria) throws PopUpException {
		List<PopUpTemplateRequest> templateRequests = popupTemplateRequestService.getPopupTemplateRequests(popupTemplateRequestSearchCriteria);
		if (UtilValidate.isEmpty(templateRequests)) {
			throw new PopUpException("No Requests Found.");
		}
		return templateRequests;
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_CREATE_RIGHTS)
	@PostMapping("update-popup-template-request")
	public Response updatePopupTemplateRequest(@RequestBody(required = false) PopUpTemplateRequest popupTemplateRequest) {
		return createOrUpdateTemplate(popupTemplateRequest, false);
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_VIEW_RIGHTS)
	@GetMapping("get-template-request-search-form")
	public Response getTemplateRequestSearchForm() {
		return new Response(FormHelper.createDomManipulationAction(DomType.FORM, MarketingDataGridHelper.getTemplateRequestSearchForm(), ElementPosition.UPDATE, "INIT"), StatusCode.SUCCESS, SUCCESS);
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_APPROVE_RIGHTS)
	@PostMapping("approve-popup-template-request")
	public Response approvePopupTemplateRequest(@RequestParam(required = false) String requestId) {
		return approveOrRejectTemplate(requestId, true, null);
	}

	private Response approveOrRejectTemplate(String requestId, boolean isApprove, String comments) {
		try {
			if(!UtilValidate.isNumeric(requestId)) {
				return new Response(StatusCode.FAILURE, "Request Id cannot be empty.");
			}
			RequestStatus requestStatus = isApprove ? RequestStatus.APPROVED : RequestStatus.REJECTED;
			boolean status = popupTemplateRequestService.approveOrRejectPopupTemplateRequest(Integer.parseInt(requestId), UserUtil.getUserId(), requestStatus, comments);
			if (!status) {
				return new Response(StatusCode.FAILURE, "Unable to " + (isApprove ? "Approve" : "Reject") + " Popup Template Request.");
			}
			return new Response(StatusCode.SUCCESS, "Popup Template Request " + (isApprove ? "Approved" : "Rejected") + " Successfully.");
		} catch (PopUpException me) {
			return new Response(StatusCode.FAILURE, me.getMessage());
		} catch (Exception e) {
			log.error(ERROR, e);
			return new Response(StatusCode.FAILURE, SOMETHING_WENT_WRONG);
		}
	}

	@PreAuthorize(MarketingConstants.TEMPLATE_APPROVE_RIGHTS)
	@PostMapping("reject-popup-template-request")
	public Response rejectPopupTemplateRequest(@RequestParam(required = false) String requestId, @RequestParam(required = false) String comments) {
		return approveOrRejectTemplate(requestId, false, comments);
	}
}
