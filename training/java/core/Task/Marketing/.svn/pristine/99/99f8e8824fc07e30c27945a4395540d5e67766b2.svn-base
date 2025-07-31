package com.medplus.marketing.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.medplus.accounts.service.impl.AccountStoreService;
import com.medplus.common.utility.UtilValidate;
import com.medplus.discounts.PromotionException;
import com.medplus.discounts.constants.PromotionConstants;
import com.medplus.discounts.constants.ServiceChargeMode;
import com.medplus.discounts.domain.Slab;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.LocalDateTimeDeserializer;
import com.medplus.marketing.constants.CssConstants;
import com.medplus.marketing.constants.MarketingConstants;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.RegularPromotion;
import com.medplus.marketing.domain.SlabGroup;
import com.medplus.marketing.exception.MarketingException;
import com.medplus.marketing.helper.ProductStoreHelper;
import com.medplus.marketing.role.RegularPromotionRoles;
import com.medplus.marketing.service.RegularPromotionService;
import com.medplus.marketing.util.CampaignUtil;
import com.medplus.marketing.util.ExcelUtil;
import com.medplus.marketing.util.UserUtil;
import com.medplus.reactcomponents.core.domain.actions.DomManipulation;
import com.medplus.reactcomponents.core.domain.form.ElementGroup;
import com.medplus.reactcomponents.core.domain.form.Form;
import com.medplus.reactcomponents.core.domain.form.Option;
import com.medplus.reactcomponents.core.domain.form.Response;
import com.medplus.reactcomponents.core.domain.form.Response.StatusCode;
import com.medplus.reactcomponents.core.domain.htmlelements.ButtonElement;
import com.medplus.reactcomponents.core.domain.htmlelements.CheckBoxElement;
import com.medplus.reactcomponents.core.domain.htmlelements.DataListElement;
import com.medplus.reactcomponents.core.domain.htmlelements.InputElement;
import com.medplus.reactcomponents.core.domain.htmlelements.RadioElement;
import com.medplus.reactcomponents.core.enums.form.DomType;
import com.medplus.reactcomponents.core.enums.form.ElementPosition;
import com.medplus.reactcomponents.core.enums.form.InputType;
import com.medplus.reactcomponents.core.helpers.FormHelper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class RegularPromotionCampaignController {

	private static final String SUCCESS = "success";
	private static final String CUSTOM_FIELD_SET = "custom-fieldset";
	private static final String ONLY_ALPHA_NUMERIC_REGEX ="^[a-zA-Z0-9]+$";
	private static final String COL_3_CLASS_NAME = "col-3";
	private static final String COL_4_CLASS_NAME = "col-4";
	private static final String COL_COL_MD_3 = "col col-md-3";
	private static final String NUMBER_REGEX = "^[0-9]+$";
	private static final String DOUBLE_REGEX = "^\\d+(\\.?(\\d?)+)$";
	private static final String SMALL_TEXT_SECONDARY = "small text-secondary";
	private static final String FAILURE_MESSAGE = "failed to get the data";
	private static final String NO_DATA_FOUND = "No Data Found";
	private static final String D_FLEX_CLASS = "d-flex mt-3 align-items-baseline";
	private static final String MB2_ME2_CLASS = " mb-2 me-2";
	
	private static final RegularPromotionRoles RP_ROLES = new RegularPromotionRoles();
	
	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
			.create();
	

	@Autowired
	private RegularPromotionService regularPromotionService;
	
	@Autowired
	private AccountStoreService accountStoreService;
	
	private static InputElement createServiceChargeElement(String label, String name) {
		InputElement input = new InputElement(InputType.TEXT, label, name);
		input.setLabelClassName(COL_3_CLASS_NAME + " mt-3");
		input.setMaxLength(5);
		input.setRegex(DOUBLE_REGEX);
		input.setAutofocus(true);
		return input;
	}
	
	public static List<ElementGroup> createServiceChargeElements() {
	    InputElement homeDelivery = createServiceChargeElement("Home Delivery Discount (%)", ServiceChargeMode.HOME_DELIVERY_CHARGES.name());
		InputElement handlingFees = createServiceChargeElement("Handling Fees Discount (%)", ServiceChargeMode.HANDLING_FEES.name());
		InputElement smallCartFees = createServiceChargeElement("Small Cart Fees Discount (%)", ServiceChargeMode.SMALL_CART_FEES.name());
		InputElement coldChainHandlingFees = createServiceChargeElement("Cold Chain Handling Fees Discount (%)",
				ServiceChargeMode.COLD_CHAIN_HANDLING_FEES.name());
		InputElement platformFees = createServiceChargeElement("Platform Fees Discount (%)", ServiceChargeMode.PLATFORM_FEES.name());
		InputElement packingFees = createServiceChargeElement("Packing Fees Discount (%)", ServiceChargeMode.PACKING_FEES.name());
		InputElement locationSurchargeFees = createServiceChargeElement("Location Surcharge Fees Discount (%)",
				ServiceChargeMode.LOCATION_SURCHARGE_FEES.name());
		ElementGroup pharmaServiceChargeGrp = FormHelper.createGroup("pharmaServiceChargeGrp", Arrays.asList(homeDelivery,
				handlingFees, smallCartFees, coldChainHandlingFees, platformFees, packingFees, locationSurchargeFees));
		pharmaServiceChargeGrp.setClassName("row");
		
		InputElement homeSampleCollection = createServiceChargeElement("Home Sample Collection Discount (%)",
				"HOME_SAMPLE_COLLECTION");
		InputElement reportDelivery = createServiceChargeElement("Report Delivery Discount (%)", "REPORT_DELIVERY_CHARGES");
		ElementGroup pathLabServiceChargeGrp  = FormHelper.createGroup("pathLabServiceChargeGrp", Arrays.asList(homeSampleCollection, reportDelivery));
		pathLabServiceChargeGrp.setClassName("row");
		
		ElementGroup serviceChargeGrp  = FormHelper.createGroup("serviceChargeGrp", Collections.emptyList());
		serviceChargeGrp.setGroups(Arrays.asList(pharmaServiceChargeGrp, pathLabServiceChargeGrp));
		serviceChargeGrp.setHidden(true);
		return Arrays.asList(serviceChargeGrp);
	}
	
	@GetMapping("get-coupon-form")
	public Response couponForm() {
		Form couponForm = new Form("", "couponForm");
		couponForm.setClassName(" row align-items-center justify-content-start mb-3 rounded");
		
		CheckBoxElement couponBased = new CheckBoxElement("Coupon Based", "couponBased");
		couponBased.setSwitchType(true);
		Option yes = FormHelper.createOption("Yes", "Y", "Y", "Y");
		Option no = FormHelper.createOption("No", "N", "N", "N");
		couponBased.setValues(Arrays.asList(yes));
		couponBased.setLabelClassName(CUSTOM_FIELD_SET + " mb-3");
		
		CheckBoxElement allCustomers = new CheckBoxElement("All Customers", "allCustomers");
		allCustomers.setSwitchType(true);
		allCustomers.setValues(Arrays.asList(FormHelper.createOption("Yes", "Y", "Y", true)));
		allCustomers.setLabelClassName(CUSTOM_FIELD_SET + " mt-3");
		allCustomers.setDisabled(true);
		
		ElementGroup allCustomersGrp = FormHelper.createGroup("allCustomersGrp", Arrays.asList(allCustomers));
		allCustomersGrp.setClassName( "col-12 mt-0 ");
		
		ElementGroup couponBasedGrp = FormHelper.createGroup("couponBasedGrp", Arrays.asList(couponBased));
		couponBasedGrp.setClassName( "col-12 mt-0 ");
		
		ElementGroup grp0 = FormHelper.createGroup("grp0", Arrays.asList(couponBased));

		InputElement couponCode = new InputElement(InputType.TEXT, "Coupon code", "couponCode");
		couponCode.setRegex(ONLY_ALPHA_NUMERIC_REGEX);
		couponCode.setMinLength(4);
		couponCode.setMaxLength(12);
		couponCode.setLabelClassName(COL_3_CLASS_NAME);
		
		InputElement numOfDays = new InputElement(InputType.TEXT, "No. of Days", "noOfDays");
		numOfDays.setLabelClassName(COL_3_CLASS_NAME);
		numOfDays.setMin("1");
		numOfDays.setMaxLength(3);
		numOfDays.setRegex(NUMBER_REGEX);
		numOfDays.setAutofocus(true);
		
		InputElement totalLimit = new InputElement(InputType.TEXT, "Total Limit", "totalLimit");
		totalLimit.setLabelClassName(COL_3_CLASS_NAME);
		totalLimit.setMin("1");
		totalLimit.setMaxLength(8);
		totalLimit.setRegex(NUMBER_REGEX);
		totalLimit.setAutofocus(true);
		
		InputElement customerLimit = new InputElement(InputType.TEXT, "Customer limit", "customerLimit");
		customerLimit.setLabelClassName(COL_3_CLASS_NAME);
		customerLimit.setMin("1");
		customerLimit.setMaxLength(3);
		customerLimit.setRegex(NUMBER_REGEX);
		customerLimit.setAutofocus(true);
		
		CheckBoxElement ePrescription = new CheckBoxElement("", "ePrescription");
		ePrescription.setValues(Arrays.asList(yes,no));
		ePrescription.setClassName("d-flex align-items-baseline");
		ePrescription.setLabelClassName(MB2_ME2_CLASS);

		CheckBoxElement addOnCoupon = new CheckBoxElement("Add On Coupon Discount", "addOnCoupon");
		addOnCoupon.setSwitchType(true);
		addOnCoupon.setValues(Arrays.asList(yes));
		addOnCoupon.setLabelClassName( "me-2");

		InputElement minInvoiceValue = new InputElement(InputType.TEXT, "Min Invoice Value", "minValue");
		minInvoiceValue.setLabelClassName(COL_4_CLASS_NAME);
		minInvoiceValue.setMaxLength(6);
		minInvoiceValue.setRegex(DOUBLE_REGEX);
		minInvoiceValue.setAutofocus(true);
		minInvoiceValue.setRequired(true);
		
		InputElement maxDiscount = new InputElement(InputType.TEXT, "Max Discount", "maxDiscount");
		maxDiscount.setLabelClassName(COL_4_CLASS_NAME);
		maxDiscount.setMaxLength(6);
		maxDiscount.setRegex(DOUBLE_REGEX);
		maxDiscount.setAutofocus(true);
		maxDiscount.setRequired(true);
		
		InputElement maxPaybackPoints = new InputElement(InputType.TEXT, "Max Payback Points", "maxPoints");
		maxPaybackPoints.setLabelClassName(COL_4_CLASS_NAME);
		maxPaybackPoints.setMaxLength(6);
		maxPaybackPoints.setRegex(DOUBLE_REGEX);
		maxPaybackPoints.setAutofocus(true);
		maxPaybackPoints.setRequired(true);
		
		ElementGroup grp1 = FormHelper.createGroup("grp1", Arrays.asList(couponCode,numOfDays,
				totalLimit,customerLimit));
		grp1.setClassName("row g-3 mb-3");
		grp1.setHidden(true);
						
		ElementGroup ePrescriptionGrp = FormHelper.createGroup("ePrescriptionGrp", Arrays.asList(ePrescription));
		ePrescriptionGrp.setLabel("E-Prescription");
		ePrescriptionGrp.setLabelClassName("small text-secondary MB-1");
		grp1.setGroups(Arrays.asList(ePrescriptionGrp));
		
		ElementGroup grp2 = FormHelper.createGroup("grp2", Arrays.asList(addOnCoupon));
		grp2.setHidden(true);
		
		RadioElement couponType = new RadioElement(" ", "couponType");
		Option products = FormHelper.createOption("Products/Tests", "products", "1", "products");
		Option service = FormHelper.createOption("Service Charge", "service", "2", "service");
		Option both = FormHelper.createOption("Both", "both", "3", "both");
		products.setSelected(true);
		couponType.setValues(Arrays.asList(products, service, both));
		couponType.setClassName("d-flex align-items-baseline");
		couponType.setLabelClassName(MB2_ME2_CLASS);
		couponType.setAutofocus(true);
		couponType.setRequired(true);
		
		CheckBoxElement sampleCollection = new CheckBoxElement("Sample Collection", "sampleCollection");
		Option homeSampleOption = FormHelper.createOption("Home Sample/Tele Consultation", "HomeSample", "H", "homeSample");
		Option walkInOption = FormHelper.createOption("Walk In", "WalkIn", "WI", "walkIn");
		sampleCollection.setValues(Arrays.asList(homeSampleOption,walkInOption));
		sampleCollection.setClassName(D_FLEX_CLASS);
		sampleCollection.setLabelClassName(MB2_ME2_CLASS);
		sampleCollection.setAutofocus(true);
		
		ElementGroup grp4 = FormHelper.createGroup("grp4", Arrays.asList(couponType));
		grp4.setLabel("Coupon Discount Type");
		grp4.setLabelClassName("small text-secondary mb-1");
		grp4.setClassName("mt-3");
		grp4.setHidden(true);
		
		ElementGroup grp5 = FormHelper.createGroup("grp5", Arrays.asList(sampleCollection));
		grp5.setHidden(true);
		
		grp4.setGroups(createServiceChargeElements());
		
		ElementGroup grp3 = FormHelper.createGroup("grp3", Arrays.asList(minInvoiceValue,maxDiscount,maxPaybackPoints));
		grp3.setClassName("row mt-2");
		grp3.setHidden(true);
		grp2.setGroups(Arrays.asList(grp3));
		grp0.setGroups(Arrays.asList(grp1,grp2));
		couponForm.setHtmlGroups(Arrays.asList(grp0,allCustomersGrp, grp4, grp5));
		DomManipulation domAction = FormHelper.createDomManipulationAction(DomType.FORM, couponForm,
				ElementPosition.UPDATE, "INIT");
		
		return new Response(domAction, StatusCode.SUCCESS, SUCCESS);
	}
	
	private List<Option> getExisitingSlabGroupOptions(){
		List<Option> existingSlabOpts=new ArrayList<>();
		Map<String,String> existingSlabs=regularPromotionService.getExisitingSlabGroups();
		if (UtilValidate.isNotEmpty(existingSlabs)) {
			existingSlabs.entrySet().stream()
		    	.map(entry -> FormHelper.createOption(entry.getValue(), entry.getKey(), entry.getKey(), entry.getKey()))
		    	.forEach(existingSlabOpts::add);
		}
		return existingSlabOpts;
	}
	
	@GetMapping("get-reg-promotion-slabs")
	public Response getRegPromotionSlabs() {

		Form slabForm = new Form("", "regPromotionSlabs");
		slabForm.setClassName(" row align-items-center justify-content-start mb-3 rounded");
				
		DataListElement existingSlabGroups = new DataListElement("existingSlabGroups");
		existingSlabGroups.setLabel("Existing Slab Groups ");
		existingSlabGroups.setLabelClassName(SMALL_TEXT_SECONDARY + " w-100");
		existingSlabGroups.setDataListClassName(CssConstants.COL_3);
		existingSlabGroups.setDefaultValue(null);
		existingSlabGroups.setValues(getExisitingSlabGroupOptions());	
		
		ElementGroup existingSlabGrp = FormHelper.createGroup("existingSlabGrp", Arrays.asList(existingSlabGroups));
		existingSlabGrp.setLabel("Existing Slab");
		existingSlabGrp.setLabelClassName(CUSTOM_FIELD_SET + " mb-2");

		ButtonElement createNewSlabGrpBtn = new ButtonElement("Create New Slab Group", "createNewSlabGrpBtn");
		createNewSlabGrpBtn.setClassName("btn btn-success shadow-sm p-12  rounded col-3");
		ElementGroup slabBtnGrp = FormHelper.createGroup("slabBtnGrp", Arrays.asList(createNewSlabGrpBtn));

		InputElement slabGroupName = new InputElement(InputType.TEXT, "Slab Group Name", "slabGroupName");
		slabGroupName.setRequired(true);
		ElementGroup grp3 = FormHelper.createGroup("grp3", Arrays.asList(slabGroupName));
		grp3.setLabel("Create Slab");
		grp3.setLabelClassName(CUSTOM_FIELD_SET + " mb-1");
		grp3.setClassName("col col-md-3 mt-3");
		grp3.setHidden(true);
		
		InputElement fromValue = new InputElement(InputType.TEXT, "From Value", "fromValue");
		fromValue.setMaxLength(9); 
		fromValue.setRegex(DOUBLE_REGEX);
		fromValue.setAutofocus(true);
		fromValue.setRequired(true);

		ElementGroup grp1 = FormHelper.createGroup("grp1", Arrays.asList(fromValue));
		grp1.setClassName(COL_COL_MD_3);

		InputElement toValue = new InputElement(InputType.TEXT, "To Value","toValue");
		toValue.setMaxLength(10); 
		toValue.setRegex(DOUBLE_REGEX);
		toValue.setAutofocus(true);
		toValue.setRequired(true);

		ElementGroup grp2 = FormHelper.createGroup("grp2",Arrays.asList(toValue));
		grp2.setClassName(COL_COL_MD_3);

		ButtonElement addSlabBtn = new ButtonElement("Add", "addNewSlab");
		addSlabBtn.setClassName("btn btn-success shadow-sm p-12 rounded col col-md-3");

		ElementGroup addSlabBtnGroup = FormHelper.createGroup("addSlabBtnGroup", Arrays.asList(addSlabBtn));
		addSlabBtnGroup.setClassName(COL_COL_MD_3);

		ElementGroup addGrp = FormHelper.createGroup("addGrp", Arrays.asList());
		addGrp.setGroups(Arrays.asList(grp1, grp2, addSlabBtnGroup));
		addGrp.setHidden(true);
		addGrp.setClassName("row mt-3");
		addGrp.setLabelClassName(CUSTOM_FIELD_SET + " mb-1");

		slabForm.setHtmlGroups(Arrays.asList(existingSlabGrp,slabBtnGrp,grp3,addGrp));
		DomManipulation domAction = FormHelper.createDomManipulationAction(DomType.FORM, slabForm,
				ElementPosition.UPDATE, "INIT");
		return new Response(domAction, StatusCode.SUCCESS, SUCCESS);
	}
	
	@GetMapping("get-invoice-category-types")
	public Response getInvoiceCategoryTypes(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> invoiceTypes = regularPromotionService.getInvoiceCategoryTypes();
		if (UtilValidate.isNotEmpty(invoiceTypes))
			return new Response(StatusCode.SUCCESS, SUCCESS, invoiceTypes);
		else
			return new Response(StatusCode.FAILURE, FAILURE_MESSAGE);
	}
	
	@PreAuthorize(MarketingConstants.RP_CREATE_RIGHTS)
	@PostMapping("create-regular-promotion")
	public Response createRegularPromotion(@RequestParam("regularPromotionInfo") String regularPromotionInfo, @RequestParam(value = "customerUpload", required = false) MultipartFile customerUpload) {
		log.info("regularPromotionInfo {}", regularPromotionInfo);
		RegularPromotion regularPromotion = GSON.fromJson(regularPromotionInfo, new TypeToken<RegularPromotion>() {}.getType());
		
		if(UtilValidate.isEmpty(regularPromotion))
			throw new MarketingException("Regular Promotion cannot be empty");
		if(!regularPromotion.isAllCustomers()) {
			if(UtilValidate.isNotEmpty(customerUpload))
				regularPromotion.setCustomerIds(ExcelUtil.readUploadExcel(customerUpload, Long.class, PromotionConstants.APPLICABLE_TYPE_PHARMACY, true));
			else	
				throw new MarketingException("Customer File is Empty");
		}
		if(UtilValidate.isEmpty(regularPromotion.getUserMetaData()))
			throw new MarketingException("User Meta Data is required");
		else {
			UserMetaData metaData = regularPromotion.getUserMetaData();
			metaData.setCreatedBy(UserUtil.getUserId());
			metaData.setDateCreated(LocalDateTime.now());
		}
		if(regularPromotion.isCouponBased() && UtilValidate.isEmpty(regularPromotion.getPromotionCoupon()))
			throw new MarketingException("Coupon Details are Required for Coupon Based Promotion");
		if(!regularPromotion.isAllCustomers() && UtilValidate.isEmpty(customerUpload))
			throw new MarketingException("Please provide customer excell file");
		try {
			RegularPromotion regPromotion = regularPromotionService.insertRegularPromotion(regularPromotion);
			log.debug("created regular Promotion : {}", regPromotion);
			return new Response(StatusCode.SUCCESS, SUCCESS, regPromotion);
		}catch (PromotionException e) {
			log.error(e.getMessage(), e);
			throw new PromotionException(e.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PromotionException("Unable to create Regular Promotion");
		}
	}
	
	@PreAuthorize(MarketingConstants.RP_EDIT_RIGHTS)
	@PostMapping("edit-regular-promotion")
	public Response editRegularPromotino(@RequestParam("regularPromotionInfo") String regularPromotionInfo, @RequestParam(value = "customerUpload", required = false) MultipartFile customerUpload, @RequestParam(value = "customerRemove", required = false) MultipartFile customerRemove) {
		log.info("regularPromotionInfo {}", regularPromotionInfo);
		RegularPromotion regularPromotion = GSON.fromJson(regularPromotionInfo, new TypeToken<RegularPromotion>() {}.getType());
		
		if(UtilValidate.isEmpty(regularPromotion))
			throw new MarketingException("Regular Promotion cannot be empty");
		if(UtilValidate.isNotEmpty(customerUpload))
			regularPromotion.setCustomerIds(ExcelUtil.readUploadExcel(customerUpload, Long.class, PromotionConstants.APPLICABLE_TYPE_PHARMACY, true));
		else
			regularPromotion.setCustomerIds(new HashSet<>());
		if(UtilValidate.isNotEmpty(customerRemove))
			regularPromotion.setRemovedCustomerIds(ExcelUtil.readUploadExcel(customerRemove, Long.class, PromotionConstants.APPLICABLE_TYPE_PHARMACY, true));
		else
			regularPromotion.setRemovedCustomerIds(new HashSet<>());
		try {
			String userId = UserUtil.getUserId();
			regularPromotion.getUserMetaData().setModifiedBy(userId);
			regularPromotion.getUserMetaData().setCreatedBy(userId);
			regularPromotion.getUserMetaData().setDateCreated(LocalDateTime.now());
			regularPromotion.getUserMetaData().setDateModified(LocalDateTime.now());
			RegularPromotion promomtion = regularPromotionService.updateRegularPromotion(regularPromotion);
			log.info("edited promtion : {}", promomtion);
			return new Response(StatusCode.SUCCESS, SUCCESS, promomtion);
		} catch (PromotionException e) {
			log.error(e.getMessage(), e);
			return new Response(StatusCode.FAILURE, e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Response(StatusCode.FAILURE, "Unable To Update Promotion");
		}
	}
	
	@PreAuthorize(MarketingConstants.RP_VIEW_RIGHTS)
	@PostMapping("get-regular-promotion")
	public Response listRegularPromotion(@RequestParam("campaignSearchCriteria") String promotion) {
		CampaignSearchCriteria searchCriteria = GSON.fromJson(promotion, new TypeToken<CampaignSearchCriteria>() {}.getType());
		if(UtilValidate.isEmpty(searchCriteria))
			searchCriteria = new CampaignSearchCriteria();
		log.info("CampaignSearchCriteria: {}",searchCriteria);
		List<Integer> applicableTypesByRole = RP_ROLES.getApplicableTypesByViewRoles();
		Map<String, Object> response = regularPromotionService.getRegularPromotions(searchCriteria, applicableTypesByRole);
		if(UtilValidate.isNotEmpty(response))
			return new Response(StatusCode.SUCCESS, SUCCESS, response);
		return new Response(StatusCode.SUCCESS, NO_DATA_FOUND, Collections.emptyMap());
	}
	
	@PreAuthorize(MarketingConstants.RP_VIEW_RIGHTS)
	@GetMapping("get-regular-promotion-by-promotionid")
	public Response getRegularPromotionById(@RequestParam("promotionId") Long promotionId) {
		try {
			return new Response(StatusCode.SUCCESS, SUCCESS, regularPromotionService.getRegularPromotionByPromotionId(promotionId));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Response(StatusCode.FAILURE, FAILURE_MESSAGE);
		}
	}
	
	/**
	 * @GetMapping("check-slab-group-assign-or-not") public Response
	 * checkSlabGroupAssignOrNot(@RequestParam("slabGroupId") Long slabGroupId) {
	 * try { return new Response(StatusCode.SUCCESS, SUCCESS,
	 * regularPromotionService.checkSlabGroupAssignOrNot(slabGroupId)); } catch
	 * (Exception e) { log.error(e.getMessage(), e); } return new
	 * Response(StatusCode.FAILURE, "Failed to fetch records"); }
	 */	

	@GetMapping("get-slab-group-details")
	public Response getSlabGroupDetails(@RequestParam("slabGroupId") Long slabGroupId) {
		try {
			List<Slab> slabGrpDetails = regularPromotionService.getSlabsInfoForSlabGroup(slabGroupId);
			log.info("slabGrpDetails for slabGroupId : {}, {}", slabGrpDetails, slabGroupId);
			/**
			 * int slabGroupAssignmentStatus=
			 * regularPromotionService.checkSlabGroupAssignOrNot(slabGroupId);
			 */			
			int slabGroupAssignmentStatus = 1;
			Map<String,Object> slabDetails=new HashMap<>();
			slabDetails.put("slabGroupDetails", slabGrpDetails);
			slabDetails.put("slabGroupAssignmentStatus", slabGroupAssignmentStatus);
	        return new Response(StatusCode.SUCCESS, SUCCESS, slabDetails);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return new Response(StatusCode.FAILURE, NO_DATA_FOUND, Collections.emptyMap());
	}
	
	@PostMapping("save-slab-details")
	public Response insertSlabsAndSlabGroups(@RequestParam("slabGroup") String slabGroup)
	{
		log.info("slab grp {}", slabGroup);
		SlabGroup slabGrp = GSON.fromJson(slabGroup, new TypeToken<SlabGroup>() {}.getType());
		slabGrp.setCreatedBy(UserUtil.getUserId());
		slabGrp.setDateCreated(LocalDateTime.now());
		try {
			SlabGroup res= regularPromotionService.insertSlabsAndSlabGroups(slabGrp);
	        return new Response(StatusCode.SUCCESS, SUCCESS, res);
		} catch (PromotionException e) {
			log.error(e.getMessage(), e);
			return new Response(StatusCode.FAILURE, e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PromotionException("Unable to Insert or Update the Slabs", e);
		}
	}
	
	@PreAuthorize(MarketingConstants.RP_APPROVE_RIGHTS)
	@PostMapping("approve-regular-promotion")
	public Response approveRegularPromotion(@RequestParam("promotionId") Long promotionId, @RequestParam("toDate") String date, @RequestParam(value = "channel", required = false) Integer channel, @RequestParam(value = "couponBased", required = false) Boolean isCouponBased) {
		log.info("RegularPromoiton Approve: {}, toDate: {}", promotionId, date);
		LocalDateTime toDate = null;
		try {
			if(UtilValidate.isNotEmpty(date))
				toDate = Instant.ofEpochMilli(Long.parseLong(date)).atZone(ZoneId.systemDefault()).toLocalDateTime();
		} catch (NumberFormatException e) {
			throw new MarketingException("toDate is not a valid date");
		}
		boolean approved = regularPromotionService.approveRegularPromotion(promotionId, toDate, UserUtil.getUserId());
		log.info("RegularPromoiton Approved: {}, Status: {}",promotionId, approved);
		if(approved) {
			return new Response(StatusCode.SUCCESS, SUCCESS, promotionId);
		}else {
			return new Response(StatusCode.FAILURE, "Unable to Approve Promotion");
		}
	}
	
	@PreAuthorize(MarketingConstants.RP_APPROVE_RIGHTS)
	@PostMapping("reject-regular-promotion")
	public Response rejectRegularPromotion(@RequestParam("campaignId") Long promotionId, @RequestParam("remarks") String remarks) {
		
		if(UtilValidate.isNotEmpty(promotionId) && UtilValidate.isNotEmpty(remarks)) {
			if(remarks.length()>255 || remarks.trim().length()<=0)
				return new Response(StatusCode.FAILURE, "Remarks length should be between 0 and 255");
			regularPromotionService.rejectRegularPromotion(promotionId, UserUtil.getUserId(), remarks);
			return new Response(StatusCode.SUCCESS, SUCCESS);
		}
		return new Response(StatusCode.FAILURE, "Unable to reject the Promotion");
	}

	@PreAuthorize(MarketingConstants.RP_CLOSE_RIGHTS)
	@PostMapping("update-regular-promotion-to-date")
	public Response updateToDateForRegularPromotion(@RequestParam("promotionId") Long promotionId, @RequestParam("toDate") String date, @RequestParam(value = "channel", required = false) Integer channel, @RequestParam(value = "couponBased", required = false) Boolean isCouponBased) {
		return updateToDateForPromotion(date, promotionId, PromotionConstants.ACTIVE, channel, isCouponBased);
	}
	
	@PreAuthorize(MarketingConstants.RP_EDIT_RIGHTS)
	@PostMapping("update-inactive-regular-promotion-to-date")
	public Response updateToDateForInactiveRegularPromotion(@RequestParam("promotionId") Long promotionId, @RequestParam("toDate") String date) {
		return updateToDateForPromotion(date, promotionId, PromotionConstants.INACTIVE, null, null);
	}
	
	private Response updateToDateForPromotion(String date, Long promotionId, String status, Integer channel, Boolean isCouponBased) {
		log.info("RegularPromotion ToDate Update: {}, toDate: {}", promotionId, date);
		LocalDateTime toDate = null;
		try {
			if(UtilValidate.isNotEmpty(date))
				toDate = Instant.ofEpochMilli(Long.parseLong(date)).atZone(ZoneId.systemDefault()).toLocalDateTime();
		} catch (NumberFormatException e) {
			throw new MarketingException("toDate is not a valid date");
		}
		try {
			regularPromotionService.updatePromotionToDate(promotionId, toDate, UserUtil.getUserId(), status);
			log.info("RegularPromotion Updated: {}",promotionId);
			return new Response(StatusCode.SUCCESS, SUCCESS, promotionId);
		}catch (PromotionException | MarketingException e) {
			log.error(e.getMessage(), e);
			return new Response(StatusCode.FAILURE, e.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			return new Response(StatusCode.FAILURE, "Unable to Update ToDate for Promotion");
		}
	}
	
	@PreAuthorize(MarketingConstants.RP_VIEW_RIGHTS)
	@GetMapping("get-regular-promotion-customer-excel-download")
	public ResponseEntity<ByteArrayResource> getCustomerExcelDownload(@RequestParam("promotionId") Long promotionId, @RequestParam("couponCode") String couponCode) {
		log.debug("RegularPromotion Customers Download: {} for CouponCode : {}", promotionId, couponCode);
		Set<Long> customerData = regularPromotionService.getRegularPromotionCustomerIds(promotionId, couponCode);
		return CampaignUtil.downloadExcel(customerData, "CustomerId", "force-download", "CustomerTemplate.xlsx");	
	}
	
	@GetMapping("validate-regular-promotion-name")
	public Response validateRegularPromotionName(@RequestParam("promotionName") String promotionName) {
		boolean isValidPromotionName = regularPromotionService.isRegularPromotionNameAvailable(promotionName);
		log.debug("RegularPromotion Name: {}, Exists: {}", promotionName, isValidPromotionName);
		return new Response(StatusCode.SUCCESS, SUCCESS, !isValidPromotionName);
	}
	
	@GetMapping("validate-regular-promotion-couponcode")
	public Response validateRegularPromotionCouponCode(@RequestParam("couponCode") String couponCode) {
		boolean isValidCouponCode = regularPromotionService.isRegularPromotionCouponCodeAvailable(couponCode);
		log.debug("RegularPromotion CouponCode: {}, Exists: {}", couponCode, isValidCouponCode);
		return new Response(StatusCode.SUCCESS, SUCCESS, !isValidCouponCode);
	}
	
	@PreAuthorize(MarketingConstants.RP_VIEW_RIGHTS)
	@GetMapping("get-regular-stores-download")
	public ResponseEntity<ByteArrayResource> getStoresExcelDownload(@RequestParam("campaignId") Long promotionId) {
		log.debug("Regular promotion Stores Download: {}", promotionId);
		List<String> storeIds = regularPromotionService.getStores(promotionId);
		return ProductStoreHelper.downloadStoresExcel(storeIds, accountStoreService);
	}
	
	@Scheduled(cron = "0 30 0 * * *")
	public void autoRejectClosedCampaigns() {
		try {
			regularPromotionService.autoRejectRegularPromotion();
		} catch (Exception e) {
			log.error("Error occured while running the auto-reject cron : ", e);
		}
	}
}