package com.medplus.marketing.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.medplus.common.utility.UtilValidate;
import com.medplus.marketing.constants.CssConstants;
import com.medplus.marketing.constants.MarketingConstants;
import com.medplus.marketing.util.UserUtil;
import com.medplus.popup.constants.ApplicableType;
import com.medplus.popup.constants.CombinationType;
import com.medplus.popup.constants.ConfigurationTriggerType;
import com.medplus.popup.constants.PopUpConfigStatus;
import com.medplus.popup.constants.RequestStatus;
import com.medplus.popup.domain.PopUpConfiguration;
import com.medplus.popup.domain.PopUpConfigurationRequest;
import com.medplus.popup.domain.PopUpTemplate;
import com.medplus.popup.domain.PopUpTemplateRequest;
import com.medplus.pos.util.PosUtil;
import com.medplus.reactcomponents.core.builder.ColumnBuilder;
import com.medplus.reactcomponents.core.domain.datagrid.Column;
import com.medplus.reactcomponents.core.domain.datagrid.DataGrid;
import com.medplus.reactcomponents.core.domain.datagrid.DownloadInfo;
import com.medplus.reactcomponents.core.domain.datagrid.EditorOptions;
import com.medplus.reactcomponents.core.domain.datagrid.Filters;
import com.medplus.reactcomponents.core.domain.datagrid.PaginationInfo;
import com.medplus.reactcomponents.core.domain.datagrid.SortDirection;
import com.medplus.reactcomponents.core.domain.datagrid.SortInfo;
import com.medplus.reactcomponents.core.domain.form.ElementGroup;
import com.medplus.reactcomponents.core.domain.form.Form;
import com.medplus.reactcomponents.core.domain.form.Option;
import com.medplus.reactcomponents.core.domain.htmlelements.ButtonElement;
import com.medplus.reactcomponents.core.domain.htmlelements.CheckBoxElement;
import com.medplus.reactcomponents.core.domain.htmlelements.DataListElement;
import com.medplus.reactcomponents.core.domain.htmlelements.DateRangeElement;
import com.medplus.reactcomponents.core.domain.htmlelements.HtmlElement;
import com.medplus.reactcomponents.core.domain.htmlelements.InputElement;
import com.medplus.reactcomponents.core.domain.htmlelements.RadioElement;
import com.medplus.reactcomponents.core.enums.datagrid.ColumnType;
import com.medplus.reactcomponents.core.enums.datagrid.DateFormatEnum;
import com.medplus.reactcomponents.core.enums.datagrid.DownloadFileType;
import com.medplus.reactcomponents.core.enums.datagrid.FilterOperators;
import com.medplus.reactcomponents.core.enums.datagrid.FrozenColumnPosition;
import com.medplus.reactcomponents.core.enums.datagrid.FunctionReturnType;
import com.medplus.reactcomponents.core.enums.form.InputRegexPattern;
import com.medplus.reactcomponents.core.enums.form.InputType;
import com.medplus.reactcomponents.core.helpers.DataGridHelper;
import com.medplus.reactcomponents.core.helpers.FormHelper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MarketingDataGridHelper {

	private static final String STATUS_LABEL = "Status";
	private static final String DATE_CREATED = "Date Created";
	private static final String APPLICABLE_TYPE = "Applicable Type";
	private static final String REQUEST_TYPE = "requestType";
	private static final String CREATED_BY = "Created By";
	private static final String PLACEHOLDER_TEXT = "placeholderText";
	private static final String DATE_RANGE_ID = "dateRange";
	private static final String DATE_RANGE = "Date Range";
	private static final String REQUEST_ID = "requestId";
	private static final String IMAGE_PATH = "imagePath";
	private static final String FOOTER = "footer";
	private static final String MESSAGE = "message";
	private static final String HEADER = "header";
	private static final String OFF = "off";
	private static final String AUTOCOMPLETE = "autocomplete";
	private static final String BOTH = "Both";
	private static final String ALL = "All";
	private static final String ACTIVE_ID = "active";
	private static final String IN_ACTIVE_ID = "inActive";
	private static final String PRODUCT_ID = "product";
	private static final String TIME_INTERVAL_ID = "timeInterval";
	private static final String CLEAR_ID = "clear";
	private static final String CLONE_REF_ID = "cloneRefId";
	private static final String SEARCH_ID = "search";
	private static final String TO_DATE_ID = "toDate";
	private static final String FROM_DATE_ID = "fromDate";
	private static final String TRIGGER_VALUE_ID = "triggerValue";
	private static final String TRIGGER_TYPE_ID = "triggerType";
	private static final String TEMPLATE_NAME_ID = "templateName";
	private static final String CREATED_BY_ID = "createdBy";
	private static final String DATE_CREATED_ID = "dateCreated";
	private static final String RENDER_ACTION_COLUMN = "renderActionColumn";
	private static final String ACTION = "action";
	private static final String ACTIONS = "Actions";
	private static final String REGIONS_SELECTION = "regionsSelection";
	private static final String PRODUCT_VALUE = "productValue";
	private static final String TRIGGER = "trigger";
	private static final String STATUS = "status";
	private static final String SEARCH = "Search";
	private static final String CLEAR = "Clear";
	private static final String GLOBAL_STATUS = "globalStatus";
	private static final String TEMPLATE_NAME = "Template Name";
	private static final String NAME_ID = "name";
	private static final String ID = "id";
	private static final String CHANNEL_MOBILE = "mobile";
	private static final String CHANNEL_WEB = "web";
	private static final String CHANNEL_CRM = "crm";
	private static final String CHANNEL_LAB_POS = "labPOS";
	private static final String IS_EXCEL_UPLOAD_FOR_REGIONS = "isExcelUploadForRegions";
	private static final String TEMPLATE_ID = "templateId";
	private static final String DEFINED_TRIGGER_ID = "definedTrigger";
	private static final String TRIGGER_TYPE_GRP = "triggerTypeGrp";
	private static final String FEW_CUSTOMERS_ID = "fewCustomers";
	private static final String ALL_CUSTOMERS_ID = "allCustomers";
	private static final String STATUS_GRP = "statusGrp";
	private static final String SELECT_STATUS = "Select Status";
	private static final String IN_ACTIVE = "In Active";
	private static final String ACTIVE = "Active";
	private static final String OTHERS_ID = "others";
	private static final String MALE_ID = "male";
	private static final String FEMALE_ID = "female";
	private static final String PATH_LABS = "Path Labs";
	private static final String PHARMACY = "Pharmacy";
	private static final String PATHLABS_ID = "pathlabs";
	private static final String PHARMACY_ID = "pharmacy";
	private static final String APPLICABLE_TYPE_ID = "applicableType";
	private static final String THIRTY_DAYS_IS_THE_MAXIMUM_RANGE = "30 Days is the Maximum Range";

	public DataGrid prepareRegionsGrid(boolean editable, boolean isStoreLevel) {

		List<Column> columnList = new ArrayList<>();

		Column column = new Column("Region", "region", ColumnType.STRING);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent("regionColumn"));
		column.setFilterEditor(DataGridHelper.addCustomComponent("selectFilter"));
		column.setFilterCellClassName("editor-cell custom-floating-select");

		columnList.add(column);

		column = new Column("Not Eligible Regions", "notEligibleRegions");
		column.setIsEditable(editable);
		column.setShowFilter(true);
		EditorOptions editorOptions = new EditorOptions();
		editorOptions.setEditOnFocus(true);
		column.setEditorOptions(editorOptions);
		column.setCellEditor(DataGridHelper.addCustomComponent("notEligibleCellEditor"));
		columnList.add(column);

		column = new Column(ACTIONS, ACTION);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent(RENDER_ACTION_COLUMN));
		columnList.add(column);

		DataGrid dataGrid = new DataGrid("rowIndex", columnList);
		Map<String, Filters> filterMap = new HashMap<>();
		filterMap.put("region",  getStringLengthFilter());
		filterMap.put("notEligibleRegions", getStringFilter());
		dataGrid.setInitialFilters(filterMap);
		
		if(isStoreLevel) {
			DownloadInfo downloadInfo = new DownloadInfo();
			downloadInfo.setFileName("Stores");
			downloadInfo.setIsRemoteDownload(true);
			downloadInfo.setFileType(DownloadFileType.XLSX);
			List<DownloadInfo> downloadInfoList = new ArrayList<>();
			downloadInfoList.add(downloadInfo);
			dataGrid.setDownloadInfoList(downloadInfoList);
		}
		return dataGrid;
	}

	public DataGrid prepareTemplateDataGrid(Long limitTo, Long totalRecords) {
		List<Column> columnsDataList = new ArrayList<>();
		List<Column> templateColList = new ArrayList<>();
		Column column = new Column("ID", ID, ColumnType.NUMBER);
		column.setShowFilter(true);
		column.setIsSortable(true);
		column.setResizable(true);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent("idActionColumn"));
		templateColList.add(column);

		column = new Column("Name", NAME_ID, ColumnType.STRING);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		templateColList.add(column);

		ColumnBuilder templateColumnBuilder = new ColumnBuilder("Template", "templateInfo", ColumnType.STRING).addChildColumns(templateColList).setHeaderCellClassName(CssConstants.TEXT_CENTER);
		Column templateGrpCol = templateColumnBuilder.buildColumn();
		templateGrpCol.setIsGroup(true);
		columnsDataList.add(templateGrpCol);
		
		column = new Column(APPLICABLE_TYPE, APPLICABLE_TYPE_ID,ColumnType.STRING);
		column.setShowFilter(true);
		columnsDataList.add(column);
		
		column = new Column(DATE_CREATED, DATE_CREATED_ID, ColumnType.DATE);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		column.setMinWidth(300d);
		column.setDateFormatStr(DateFormatEnum.NORMAL_DATE_WITH_24_TIME);
		columnsDataList.add(column);
		column = new Column(CREATED_BY, CREATED_BY_ID, ColumnType.STRING);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		columnsDataList.add(column);
		column = new Column(ACTIONS, ACTION);
		column.setResizable(true);
		column.setIsFrozen(true);
		column.setFrozenColumnPosition(FrozenColumnPosition.RIGHT);
		column.setCellClassName(CssConstants.ACTIONS_COLUMN);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent(RENDER_ACTION_COLUMN));
		columnsDataList.add(column);
		DataGrid dataGrid = new DataGrid(ID, columnsDataList);
		dataGrid.setRowClass(DataGridHelper.addCustomFunction(CssConstants.ROW_CLASS_NAME, FunctionReturnType.STRING));
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setLoadOnScroll(true);
		paginationInfo.setLimit(limitTo);
		dataGrid.setPaginationInfo(paginationInfo);
		dataGrid.setTotalRowsCount(totalRecords);

		Map<String, Filters> filterMap = new HashMap<>();
		filterMap.put(NAME_ID, getStringFilter());
		filterMap.put(CREATED_BY_ID, getStringFilter());
		filterMap.put(ID, getNumberFilter());
		filterMap.put(DATE_CREATED_ID, getDateFilter());
		filterMap.put(APPLICABLE_TYPE_ID, getStringFilter());
		dataGrid.setInitialFilters(filterMap);
		return dataGrid;
	}
	
	public DataGrid prepareTemplateRequestsDataGrid(Long limitTo, Long totalRecords) {
		List<Column> columnsDataList = new ArrayList<>();
		Set<String> userRoles = UserUtil.getModuleRoles();
		
		Column column = new Column("Request ID", REQUEST_ID, ColumnType.NUMBER);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent("requestIdActionColumn"));
		columnsDataList.add(column);

		column = new Column(TEMPLATE_NAME, NAME_ID, ColumnType.STRING);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		columnsDataList.add(column);
		
		column = new Column(APPLICABLE_TYPE, APPLICABLE_TYPE_ID,ColumnType.STRING);
		column.setShowFilter(true);
		columnsDataList.add(column);
		
		column = new Column(STATUS_LABEL,STATUS,ColumnType.STRING);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent("renderStatusColumn"));
		columnsDataList.add(column);
		
		column = new Column(DATE_CREATED, DATE_CREATED_ID, ColumnType.DATE);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		column.setMinWidth(300d);
		column.setDateFormatStr(DateFormatEnum.NORMAL_DATE_WITH_24_TIME);
		columnsDataList.add(column);
		column = new Column(CREATED_BY, CREATED_BY_ID, ColumnType.STRING);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		columnsDataList.add(column);
		column = new Column(ACTIONS, ACTION);
		column.setResizable(true);
		column.setIsFrozen(true);
		column.setFrozenColumnPosition(FrozenColumnPosition.RIGHT);
		column.setCellClassName(CssConstants.ACTIONS_COLUMN);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent(RENDER_ACTION_COLUMN));
		columnsDataList.add(column);
		DataGrid dataGrid = new DataGrid(ID, columnsDataList);
		dataGrid.setRowClass(DataGridHelper.addCustomFunction(CssConstants.ROW_CLASS_NAME, FunctionReturnType.STRING));
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setLoadOnScroll(true);
		paginationInfo.setLimit(limitTo);
		dataGrid.setPaginationInfo(paginationInfo);
		dataGrid.setTotalRowsCount(totalRecords);

		Map<String, Filters> filterMap = new HashMap<>();
		filterMap.put(NAME_ID, getStringFilter());
		filterMap.put(CREATED_BY_ID, getStringFilter());
		filterMap.put(REQUEST_ID, getNumberFilter());
		filterMap.put(APPLICABLE_TYPE_ID, getStringFilter());
		filterMap.put(DATE_CREATED_ID, getDateFilter());
		dataGrid.setInitialFilters(filterMap);
		SortInfo dateCreatedSortInfo = new SortInfo();
		dateCreatedSortInfo.setColumnKey(DATE_CREATED_ID);
		dateCreatedSortInfo.setDirection(SortDirection.ASC);
		if(UtilValidate.isNotEmpty(userRoles) && (userRoles.contains(MarketingConstants.LAB_TEMPLATE_CREATE_ROLE) || userRoles.contains(MarketingConstants.PHARMA_TEMPLATE_CREATE_ROLE))) {
			dateCreatedSortInfo.setDirection(SortDirection.DESC);
		}
		dataGrid.setSortInfo(Arrays.asList(dateCreatedSortInfo));
		return dataGrid;
	}

	private Filters getNumberFilter() {
		Filters numberFilter = new Filters(FilterOperators.EQUALS_NUMERIC);
		numberFilter.setOperators(FilterOperators.EQUALS_NUMERIC, FilterOperators.GREATER, FilterOperators.GREATER_EQUAL, FilterOperators.LESS_EQUAL, FilterOperators.LESS);
		return numberFilter;
	}

	private Filters getStringLengthFilter() {
		Filters lenFilter = new Filters(FilterOperators.EQUALS_LENGTH);
		lenFilter.setOperators(FilterOperators.CONTAINS_STRING);
		return lenFilter;
	}
	
	private Filters getStringFilter() {
		Filters strfilter = new Filters(FilterOperators.CONTAINS_STRING);
		strfilter.setOperators(FilterOperators.CONTAINS_STRING, FilterOperators.EQUALS_STRING);
		return strfilter;
	}

	private Filters getDateFilter() {
		Filters dateFilter = new Filters(FilterOperators.IN_RANGE_DATE);
		dateFilter.setOperators(FilterOperators.IN_RANGE_DATE, FilterOperators.NOT_IN_RANGE_DATE, FilterOperators.BEFORE_ON, FilterOperators.BEFORE, FilterOperators.AFTER, FilterOperators.AFTER_ON);
		return dateFilter;
	}

	public List<Map<String, Object>> prepareTemplateDataSet(List<PopUpTemplate> templateRequests) {
		List<Map<String, Object>> dataSetList = new ArrayList<>();
		if (UtilValidate.isEmpty(templateRequests)) {
			return dataSetList;
		}
		Map<String, Object> dataSet = null;
		for (PopUpTemplate popupTemplate : templateRequests) {
			dataSet = new HashMap<>();
			dataSet.put(ID, popupTemplate.getTemplateId());
			dataSet.put(NAME_ID, popupTemplate.getTemplateName());
			dataSet.put(APPLICABLE_TYPE_ID, getApplicableDisplayName(popupTemplate.getApplicableType()));
			dataSet.put(HEADER, popupTemplate.getHeader());
			dataSet.put(MESSAGE, popupTemplate.getMessage());
			dataSet.put(FOOTER, popupTemplate.getFooter());
			dataSet.put(IMAGE_PATH, popupTemplate.getImagePath());
			dataSet.put(CREATED_BY_ID, popupTemplate.getCreatedBy());
			dataSet.put(DATE_CREATED_ID, popupTemplate.getDateCreated());
			dataSetList.add(dataSet);
		}
		return dataSetList;
	}

	public String getApplicableDisplayName(ApplicableType applicableType) {
		switch (applicableType) {
		case PHARMACY:
			return PHARMACY;
		case PATHLABS:
			return PATH_LABS;
		}
		return "";
	}

	public List<Map<String, Object>> prepareTemplateRequestsDataSet(List<PopUpTemplateRequest> templateRequests) {
		List<Map<String, Object>> dataSetList = new ArrayList<>();
		if (UtilValidate.isEmpty(templateRequests)) {
			return dataSetList;
		}
		Map<String, Object> dataSet = null;
		for (PopUpTemplateRequest popupTemplateRequest : templateRequests) {
			dataSet = new HashMap<>();
			dataSet.put(REQUEST_ID, popupTemplateRequest.getRequestId());
			dataSet.put(NAME_ID, popupTemplateRequest.getTemplateName());
			dataSet.put(APPLICABLE_TYPE_ID, getApplicableDisplayName(popupTemplateRequest.getApplicableType()));
			dataSet.put(STATUS, popupTemplateRequest.getTemplateReqStatus());
			dataSet.put(CREATED_BY_ID, popupTemplateRequest.getCreatedBy());
			dataSet.put(DATE_CREATED_ID, popupTemplateRequest.getDateCreated());
			dataSet.put(IMAGE_PATH, popupTemplateRequest.getImagePath());
			dataSet.put(HEADER, popupTemplateRequest.getHeader());
			dataSet.put(FOOTER, popupTemplateRequest.getFooter());
			dataSet.put(MESSAGE, popupTemplateRequest.getMessage());
			dataSetList.add(dataSet);
		}
		return dataSetList;
	}

	public List<Map<String, Object>> preparePopupConfigurationDataSet(List<PopUpConfiguration> popupConfigurations) {
		List<Map<String, Object>> dataSetList = new ArrayList<>();
		if (UtilValidate.isEmpty(popupConfigurations)) {
			return dataSetList;
		}
		Map<String, Object> dataSet = null;
		for (PopUpConfiguration popupConfig : popupConfigurations) {
			dataSet = new HashMap<>();
			dataSet.put(ID, popupConfig.getConfigId());
			dataSet.put(NAME_ID, popupConfig.getPopUpConfigurationName());
			dataSet.put(TEMPLATE_ID, popupConfig.getTemplateId());
			dataSet.put(TEMPLATE_NAME_ID, popupConfig.getTemplateName());
			dataSet.put(TRIGGER_TYPE_ID, popupConfig.getTriggerType());
			dataSet.put(TRIGGER_VALUE_ID, popupConfig.getTriggerType() == ConfigurationTriggerType.DEFINED_TRIGGER ? CombinationType.getEnumFromValue(Integer.valueOf(popupConfig.getTriggerValue())) : popupConfig.getTriggerValue());
			dataSet.put(IS_EXCEL_UPLOAD_FOR_REGIONS, popupConfig.getIsExcelUploadForRegions());
			dataSet.put(STATUS, popupConfig.getStatus().getValue());
			dataSet.put(GLOBAL_STATUS, popupConfig.getStatus().getValue());
			dataSet.put(APPLICABLE_TYPE_ID, popupConfig.getApplicableType());
			dataSet.put(DATE_CREATED_ID, popupConfig.getDateCreated());
			dataSet.put(FROM_DATE_ID, popupConfig.getFromDate());
			dataSet.put(TO_DATE_ID, popupConfig.getToDate());
			dataSet.put(CLONE_REF_ID, PosUtil.isEmptyOrZero(popupConfig.getCloneRefId()) ? null : popupConfig.getCloneRefId());
			dataSet.put("productIds", popupConfig.getProductIds());
			dataSet.put("regions", popupConfig.getRegions());
			dataSetList.add(dataSet);
		}
		return dataSetList;
	}
	
	public List<Map<String, Object>> preparePopupConfigurationRequestDataSet(List<PopUpConfigurationRequest> popupConfigurationRequests) {
		List<Map<String, Object>> dataSetList = new ArrayList<>();
		if (UtilValidate.isEmpty(popupConfigurationRequests)) {
			return dataSetList;
		}
		Map<String, Object> dataSet = null;
		for (PopUpConfigurationRequest popupConfigRequest : popupConfigurationRequests) {
			dataSet = new HashMap<>();
			dataSet.put(REQUEST_ID, popupConfigRequest.getRequestId());
			dataSet.put(NAME_ID, popupConfigRequest.getPopUpConfigurationName());
			dataSet.put(APPLICABLE_TYPE_ID, popupConfigRequest.getApplicableType());
			dataSet.put(STATUS, popupConfigRequest.getConfigRequestStatus());
			dataSet.put(DATE_CREATED_ID, popupConfigRequest.getDateCreated());
			dataSet.put(CREATED_BY_ID, popupConfigRequest.getCreatedBy());
			dataSet.put(REQUEST_TYPE, popupConfigRequest.getRequestType());
			dataSetList.add(dataSet);
		}
		return dataSetList;
	}
	
	public DataGrid preparePopupConfigRequestDataGrid(int noOfRecords, Long totalRecords) {
		List<Column> columns = new ArrayList<>();
		Map<String, Filters> filterMap = new HashMap<>();
		Set<String> userRoles = UserUtil.getModuleRoles(); 
		
		Column column = new Column("Request ID",REQUEST_ID);
		column.setShowFilter(true);
		filterMap.put(REQUEST_ID, getNumberFilter());
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent("renderRequestIdColumn"));
		columns.add(column);
		
		column = new Column("Config Name", NAME_ID);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		filterMap.put(NAME_ID, getStringFilter());
		columns.add(column);
		
		column = new Column(APPLICABLE_TYPE, APPLICABLE_TYPE_ID,ColumnType.STRING);
		column.setShowFilter(true);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent("renderApplicableTypeColumn"));
		filterMap.put(APPLICABLE_TYPE_ID, getStringFilter());
		columns.add(column);
		
		column = new Column(STATUS_LABEL,STATUS,ColumnType.STRING);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent("renderStatusColumn"));
		columns.add(column);
		
		column = new Column(DATE_CREATED, DATE_CREATED_ID, ColumnType.DATE);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		column.setMinWidth(300d);
		column.setDateFormatStr(DateFormatEnum.NORMAL_DATE_WITH_24_TIME);
		filterMap.put(DATE_CREATED_ID, getDateFilter());
		columns.add(column);
		
		column = new Column(CREATED_BY, CREATED_BY_ID, ColumnType.STRING);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		filterMap.put(CREATED_BY_ID, getStringFilter());
		columns.add(column);
		
		column = new Column(ACTIONS, ACTION);
		column.setResizable(true);
		column.setCellClassName(CssConstants.ACTIONS_COLUMN);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent(RENDER_ACTION_COLUMN));
		column.setIsFrozen(true);
		column.setFrozenColumnPosition(FrozenColumnPosition.RIGHT);
		columns.add(column);
		
		DataGrid dataGrid = new DataGrid(ID, columns);
		dataGrid.setRowClass(DataGridHelper.addCustomFunction(CssConstants.ROW_CLASS_NAME, FunctionReturnType.STRING));
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setLoadOnScroll(true);
		paginationInfo.setLimit((long) noOfRecords);
		dataGrid.setPaginationInfo(paginationInfo);
		dataGrid.setTotalRowsCount(totalRecords);
		dataGrid.setInitialFilters(filterMap);
		SortInfo dateCreatedSortInfo = new SortInfo();
		dateCreatedSortInfo.setColumnKey(DATE_CREATED_ID);
		dateCreatedSortInfo.setDirection(SortDirection.ASC);
		if(UtilValidate.isNotEmpty(userRoles) && (userRoles.contains(MarketingConstants.LAB_TEMPLATE_CREATE_ROLE) || userRoles.contains(MarketingConstants.PHARMA_TEMPLATE_CREATE_ROLE))) {
			dateCreatedSortInfo.setDirection(SortDirection.DESC);
		}
		dataGrid.setSortInfo(Arrays.asList(dateCreatedSortInfo));
		return dataGrid;
	}

	public DataGrid preparePopupConfigDataGrid(int noOfRecords, Long totalRecords) {
		List<Column> columnsDataList = new ArrayList<>();

		List<Column> configurationList = new ArrayList<>();
		Column column = new Column("ID", ID, ColumnType.NUMBER);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent("configurationIdColumn"));
		column.setIsSortable(true);
		column.setResizable(true);
		column.setShowFilter(true);
		configurationList.add(column);
		column = new Column("Name", NAME_ID, ColumnType.STRING);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		configurationList.add(column);

		ColumnBuilder configColBuilder = new ColumnBuilder("Popup Configuration", "popupConfiguration", ColumnType.STRING).addChildColumns(configurationList)
				.setHeaderCellClassName(CssConstants.TEXT_CENTER);
		Column configGrpCol = configColBuilder.buildColumn();
		configGrpCol.setIsGroup(true);
		columnsDataList.add(configGrpCol);

		column = new Column(TEMPLATE_NAME, TEMPLATE_NAME_ID, ColumnType.STRING);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		columnsDataList.add(column);

		column = new Column(APPLICABLE_TYPE, APPLICABLE_TYPE_ID, ColumnType.STRING);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent("applicableTypeColumn"));
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		columnsDataList.add(column);

		List<Column> triggerColList = new ArrayList<>();

		column = new Column("Type", TRIGGER_TYPE_ID, ColumnType.STRING);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent("triggerTypeColumn"));
		column.setResizable(true);
		column.setIsSortable(true);
		triggerColList.add(column);
		column = new Column("Value", TRIGGER_VALUE_ID, ColumnType.STRING);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent("triggerValueColumn"));
		column.setResizable(true);
		triggerColList.add(column);
		ColumnBuilder triggerColBuilder = new ColumnBuilder("Trigger", TRIGGER, ColumnType.STRING).addChildColumns(triggerColList).setHeaderCellClassName(CssConstants.TEXT_CENTER);
		Column triggerGrpCol = triggerColBuilder.buildColumn();
		triggerGrpCol.setIsGroup(true);
		columnsDataList.add(triggerGrpCol);

		column = new Column(STATUS_LABEL, STATUS, ColumnType.STRING);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent("statusColumn"));
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		column.setMinWidth(155d);
		column.setFilterEditor(DataGridHelper.addCustomComponent("statusFilter"));
		column.setFilterCellClassName("custom-floating-select");
		columnsDataList.add(column);

		List<Column> dateColumnList = new ArrayList<>();

		column = new Column("Created", DATE_CREATED_ID, ColumnType.DATE);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		column.setMinWidth(300d);
		column.setDateFormatStr(DateFormatEnum.NORMAL_DATE_WITH_24_TIME);
		dateColumnList.add(column);
		column = new Column("Start", FROM_DATE_ID, ColumnType.DATE);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		column.setMinWidth(300d);
		column.setDateFormatStr(DateFormatEnum.NORMAL_DATE_WITH_24_TIME);
		dateColumnList.add(column);
		column = new Column("End", TO_DATE_ID, ColumnType.DATE);
		column.setResizable(true);
		column.setIsSortable(true);
		column.setShowFilter(true);
		column.setMinWidth(300d);
		column.setDateFormatStr(DateFormatEnum.NORMAL_DATE_WITH_24_TIME);
		dateColumnList.add(column);

		ColumnBuilder dateColBuilder = new ColumnBuilder("Date", "date", ColumnType.STRING).addChildColumns(dateColumnList).setHeaderCellClassName(CssConstants.TEXT_CENTER);
		Column dateGrpCol = dateColBuilder.buildColumn();
		dateGrpCol.setIsGroup(true);
		columnsDataList.add(dateGrpCol);

		column = new Column("Clone Reference ID", CLONE_REF_ID, ColumnType.NUMBER);
		column.setResizable(true);
		column.setShowFilter(true);
		column.setIsSortable(true);
		column.setDefaultColumnValue("-");
		columnsDataList.add(column);
		column = new Column(ACTIONS, ACTION);
		column.setResizable(true);
		column.setCellClassName(CssConstants.ACTIONS_COLUMN);
		column.setCustomRowRenderingFunction(DataGridHelper.addCustomComponent(RENDER_ACTION_COLUMN));
		column.setIsFrozen(true);
		column.setFrozenColumnPosition(FrozenColumnPosition.RIGHT);
		columnsDataList.add(column);
		DataGrid dataGrid = new DataGrid(ID, columnsDataList);
		dataGrid.setRowClass(DataGridHelper.addCustomFunction(CssConstants.ROW_CLASS_NAME, FunctionReturnType.STRING));
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setLoadOnScroll(true);
		paginationInfo.setLimit((long) noOfRecords);
		dataGrid.setPaginationInfo(paginationInfo);
		dataGrid.setTotalRowsCount(totalRecords);
		Map<String, Filters> filterMap = new HashMap<>();
		filterMap.put(ID, getNumberFilter());
		filterMap.put(NAME_ID, getStringFilter());
		filterMap.put(TEMPLATE_NAME_ID, getStringFilter());
		filterMap.put(TRIGGER_TYPE_ID, getStringFilter());
		filterMap.put(STATUS, new Filters(FilterOperators.IN_LIST_STRING));
		filterMap.put(DATE_CREATED_ID, getDateFilter());
		filterMap.put(FROM_DATE_ID, getDateFilter());
		filterMap.put(TO_DATE_ID, getDateFilter());
		filterMap.put(CLONE_REF_ID, getNumberFilter());
		dataGrid.setInitialFilters(filterMap);
		return dataGrid;
	}

	public Form getCreateTemplateForm() {
		Form createTemplateForm = new Form("", "createTemplateForm");
		
		Set<String> userRoles = UserUtil.getModuleRoles();

		InputElement templateName = new InputElement(InputType.TEXT, TEMPLATE_NAME, NAME_ID);
		templateName.setLabelClassName("col-4 text-secondary small");
		templateName.setRequired(true);
		templateName.setPlaceholder("Please enter template name");
		templateName.setRegex("^([a-zA-Z0-9\\s]+(?:\\[a-zA-Z0-9]+)*)$");
		templateName.setMaxLength(50);
		Map<String, String> attributesObj = new HashMap<>();
		attributesObj.put(AUTOCOMPLETE, OFF);
		templateName.setAttributes(attributesObj);
		
		ElementGroup templateNameGrp = FormHelper.createGroup("templateNameGrp", Arrays.asList(templateName));
		templateNameGrp.setClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.MB_4, CssConstants.ROW)));

		ElementGroup templateHeaderGrp = FormHelper.createGroup(HEADER, Arrays.asList());
		templateHeaderGrp.setLabel("Template Header");
		templateHeaderGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.MB_12, CssConstants.CUSTOM_FIELDSET)));
		templateHeaderGrp.setClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.MB_4)));

		ElementGroup templateMsgGrp = FormHelper.createGroup(MESSAGE, Arrays.asList());
		templateMsgGrp.setLabel("Template Message");
		templateMsgGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.CUSTOM_FIELDSET, CssConstants.MB_12)));
		templateMsgGrp.setClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.MB_4)));

		ElementGroup templateFooterGrp = FormHelper.createGroup(FOOTER, Arrays.asList());
		templateFooterGrp.setLabel("Template Footer");
		templateFooterGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.CUSTOM_FIELDSET, CssConstants.MB_12)));
		
		
		List<ElementGroup> groupElements = new ArrayList<>(Arrays.asList( templateNameGrp, templateHeaderGrp, templateMsgGrp, templateFooterGrp));
		
		if(UtilValidate.isNotEmpty(userRoles) && userRoles.contains(MarketingConstants.LAB_TEMPLATE_CREATE_ROLE) && userRoles.contains(MarketingConstants.PHARMA_TEMPLATE_CREATE_ROLE)) {
			RadioElement applicableType = new RadioElement("", APPLICABLE_TYPE_ID);
			applicableType.setName(APPLICABLE_TYPE_ID);
			Option pharmaOption = FormHelper.createOption(PHARMACY, PHARMACY_ID, ApplicableType.PHARMACY.toString(), PHARMACY_ID);
			Option pathlabsOption = FormHelper.createOption(PATH_LABS, PATHLABS_ID, ApplicableType.PATHLABS.toString(), PATHLABS_ID);
			applicableType.setValues(Arrays.asList(pharmaOption, pathlabsOption));
			applicableType.setRequired(true);
		
			
			ElementGroup applicableTypeGrp = FormHelper.createGroup("applicableTypeGrp", Arrays.asList(applicableType));
			applicableTypeGrp.setLabel(APPLICABLE_TYPE);
			applicableTypeGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.MB_12, CssConstants.CUSTOM_FIELDSET)));
			applicableTypeGrp.setClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.MB_4)));
			
			groupElements.add(0, applicableTypeGrp);
		}
		
		createTemplateForm.setHtmlGroups(groupElements);
		return createTemplateForm;
	}

	public Form getTemplateSearchForm() {
		Form templateSearchForm = new Form("", "templateSearchForm");
		
		Set<String> userRoles = UserUtil.getModuleRoles();
		
		InputElement templateName = new InputElement(InputType.TEXT, TEMPLATE_NAME, "templateLikeName");
		templateName.setLabelClassName(CssConstants.COL_6);
		templateName.setMinLength(3);
		templateName.setMaxLength(50);
		templateName.setRegex("^([a-zA-Z0-9\\s]+(?:\\[a-zA-Z0-9]+)*)$");
		Map<String, String> attributesObj = new HashMap<>();
		attributesObj.put(AUTOCOMPLETE, OFF);
		templateName.setAttributes(attributesObj);

		DateRangeElement dateRange = prepareDateRangeElement(new Date(),new Date(), null, new Date(), 29, THIRTY_DAYS_IS_THE_MAXIMUM_RANGE, null);
		dateRange.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList("col-6")));
		
		List<HtmlElement> groupElements = new ArrayList<>(Arrays.asList(templateName, dateRange));
		
		if(UtilValidate.isNotEmpty(userRoles) && userRoles.contains(MarketingConstants.LAB_TEMPLATE_VIEW_ROLE) && userRoles.contains(MarketingConstants.PHARMA_TEMPLATE_VIEW_ROLE)) {
			RadioElement applicableType = new RadioElement("", APPLICABLE_TYPE_ID);
			applicableType.setName(APPLICABLE_TYPE_ID);
			Option pharmaOption = FormHelper.createOption(PHARMACY, ApplicableType.PHARMACY.name(), ApplicableType.PHARMACY.toString(), Integer.toString(ApplicableType.PHARMACY.getType()));
			Option pathlabsOption = FormHelper.createOption(PATH_LABS, ApplicableType.PATHLABS.name(), ApplicableType.PATHLABS.toString(), Integer.toString(ApplicableType.PATHLABS.getType()));
			applicableType.setValues(Arrays.asList(pharmaOption, pathlabsOption));
			
			groupElements.add(0, applicableType);
		}
			
		ElementGroup templateNameGrp = FormHelper.createGroup("templateNameGrp", groupElements);
		templateNameGrp.setClassName("row g-3 mb-2 p-12");
		templateNameGrp.setLabel("Popup Template Search");
		templateNameGrp.setLabelClassName("custom-fieldset font-weight-bold");

		ButtonElement reset = new ButtonElement(CLEAR, CLEAR_ID);
		reset.setClassName("btn brand-secondary mx-3 px-4");
		reset.setReset(true);

		ButtonElement search = new ButtonElement(SEARCH, SEARCH_ID);
		search.setClassName("btn btn-dark px-4");
		search.setSubmit(true);

		ElementGroup buttonsGrp = FormHelper.createGroup("buttonsGrp", Arrays.asList(search, reset));
		buttonsGrp.setClassName("d-flex flex-row-reverse border-top p-2");

		templateSearchForm.setHtmlGroups(Arrays.asList(templateNameGrp, buttonsGrp));
		templateSearchForm.setLabelClassName("g-3 ");
		templateSearchForm.setAtleastOneFieldRequired(true);
		return templateSearchForm;
	}
	
	public Form getTemplateRequestSearchForm() {
		Form templateRequestSearchForm = new Form("","templateRequestSearchForm");
		
		Set<String> userRoles = UserUtil.getModuleRoles();
		
		InputElement templateName = new InputElement(InputType.TEXT, TEMPLATE_NAME, "templateLikeName");
		templateName.setLabelClassName(CssConstants.COL_6);
		templateName.setMinLength(3);
		templateName.setMaxLength(50);
		templateName.setRegex("^([a-zA-Z0-9\\s]+(?:\\[a-zA-Z0-9]+)*)$");
		Map<String, String> attributesObj = new HashMap<>();
		attributesObj.put(AUTOCOMPLETE, OFF);
		templateName.setAttributes(attributesObj);
		
		InputElement requestId = new InputElement(InputType.TEXT, "Request Id", REQUEST_ID);
		requestId.setLabelClassName(CssConstants.COL_6);
		requestId.setRegex(InputRegexPattern.NUMERIC.getRegex());
		requestId.setMaxLength(10);

		DateRangeElement dateRange = prepareDateRangeElement(new Date(),new Date(), null, new Date(), 29, THIRTY_DAYS_IS_THE_MAXIMUM_RANGE, null);
		dateRange.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList("col-6")));
		
		InputElement createdBy = new InputElement(InputType.TEXT, CREATED_BY, CREATED_BY_ID);
		createdBy.setRegex(InputRegexPattern.ALPHA_NUMERIC.getRegex());
		createdBy.setLabelClassName(CssConstants.COL_6);
		createdBy.setMaxLength(10);
		
		CheckBoxElement requestStatues = new CheckBoxElement(STATUS_LABEL, "requestStatus");
		requestStatues.setLabelClassName("d-block text-secondary font-14");
		requestStatues.setClassName("col-6");
		Option approved = FormHelper.createOption(RequestStatus.APPROVED.getLabel(), RequestStatus.APPROVED.getLabel(), RequestStatus.APPROVED.name(), RequestStatus.APPROVED.toString());
		Option pending = FormHelper.createOption(RequestStatus.PENDING.getLabel(), RequestStatus.PENDING.getLabel(), RequestStatus.PENDING.name(), RequestStatus.PENDING.toString());
		Option reject = FormHelper.createOption(RequestStatus.REJECTED.getLabel(), RequestStatus.REJECTED.getLabel(), RequestStatus.REJECTED.name(), RequestStatus.REJECTED.toString());
		requestStatues.setValues(Arrays.asList(approved,pending,reject));
		
		List<HtmlElement> groupElements = new ArrayList<>(Arrays.asList(requestStatues,requestId,templateName,createdBy, dateRange));
		
		if(UtilValidate.isNotEmpty(userRoles) && userRoles.contains(MarketingConstants.LAB_TEMPLATE_VIEW_ROLE) && userRoles.contains(MarketingConstants.PHARMA_TEMPLATE_VIEW_ROLE)) {
			RadioElement applicableType = new RadioElement("", APPLICABLE_TYPE_ID);
			applicableType.setLabel("Applicable Types");
			applicableType.setLabelClassName("d-block text-secondary font-14");
			applicableType.setClassName("col-6");
			Option pharmaOption = FormHelper.createOption(PHARMACY, ApplicableType.PHARMACY.name(), ApplicableType.PHARMACY.toString(), Integer.toString(ApplicableType.PHARMACY.getType()));
			Option pathlabsOption = FormHelper.createOption(PATH_LABS, ApplicableType.PATHLABS.name(), ApplicableType.PATHLABS.toString(), Integer.toString(ApplicableType.PATHLABS.getType()));
			applicableType.setValues(Arrays.asList(pharmaOption, pathlabsOption));
			
			groupElements.add(0, applicableType);
		}
		
		ElementGroup templateNameGrp = FormHelper.createGroup("templateGrp",groupElements);
		templateNameGrp.setClassName(CssConstants.ROW_G_3 + " " + CssConstants.MB_3 + " p-12");
		templateNameGrp.setLabel("Popup Template Request Search");
		templateNameGrp.setLabelClassName("custom-fieldset font-weight-bold");

		ButtonElement reset = new ButtonElement(CLEAR, CLEAR_ID);
		reset.setClassName("btn brand-secondary mx-3 px-4");
		reset.setReset(true);

		ButtonElement search = new ButtonElement(SEARCH, SEARCH_ID);
		search.setClassName("btn btn-dark px-4");
		search.setSubmit(true);

		ElementGroup buttonsGrp = FormHelper.createGroup("buttonsGrp", Arrays.asList(search, reset));
		buttonsGrp.setClassName("d-flex flex-row-reverse border-top p-2");

		templateRequestSearchForm.setHtmlGroups(Arrays.asList(templateNameGrp, buttonsGrp));
		templateRequestSearchForm.setLabelClassName("g-3 ");
		templateRequestSearchForm.setAtleastOneFieldRequired(true);
		return templateRequestSearchForm;
	}
	
	public Form getPathLabConfigurationForm(List<PopUpTemplate> popupTemplates, Map<String, String> patientageRange) {
	
		Form templateSelectionForm = new Form("", "pathLabConfigurationForm");
		
		/** pop-up configuration name **/
		InputElement popupConfigName = new InputElement(InputType.TEXT, "Popup Configuration Name ", NAME_ID);
		popupConfigName.setRequired(true);
		popupConfigName.setLabelClassName(CssConstants.COL_4);
		popupConfigName.setMaxLength(45);
		Map<String, String> attributesObj = new HashMap<>();
		attributesObj.put(AUTOCOMPLETE, OFF);
		popupConfigName.setAttributes(attributesObj);
		
		/** pop-up template name **/
		DataListElement templateNames = new DataListElement(TEMPLATE_NAME_ID);
		List<Option> tempValues = new ArrayList<>();
		if (UtilValidate.isNotEmpty(popupTemplates)) {
			for (PopUpTemplate popupTemplate : popupTemplates) {
				Option tempName = FormHelper.createOption(popupTemplate.getTemplateName(), popupTemplate.getTemplateName(), popupTemplate.getTemplateName().toLowerCase(), popupTemplate.getTemplateId().toString());
				tempValues.add(tempName);
			}
		}
		templateNames.setLabel(TEMPLATE_NAME);
		templateNames.setValues(tempValues);
		templateNames.setLabelClassName(CssConstants.W_100);
		templateNames.setDataListClassName(CssConstants.COL_4);

		/**pop-up template status **/
		RadioElement status = new RadioElement("", STATUS);
		status.setName(STATUS);
		status.setRequired(true);
		Option active = FormHelper.createOption(ACTIVE, STATUS, "A", ACTIVE_ID);
		active.setSelected(true);
		Option inActive = FormHelper.createOption(IN_ACTIVE, STATUS, "I", IN_ACTIVE_ID);
		status.setValues(Arrays.asList(active, inActive));
		status.setLabelClassName(CssConstants.W_100);
		status.setRequired(true);
		ElementGroup statusGrp = FormHelper.createGroup(STATUS_GRP, Arrays.asList(status));
		statusGrp.setLabel(SELECT_STATUS);
		statusGrp.setClassName(CssConstants.COL_4);
		statusGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.TEXT_SECONDARY, CssConstants.SMALL)));
		
		/**channel to be configured **/
		CheckBoxElement channel = new CheckBoxElement("", "channel");
		Option labPOS = FormHelper.createOption("Lab POS", CHANNEL_LAB_POS,CHANNEL_LAB_POS , CHANNEL_LAB_POS);
		Option crm = FormHelper.createOption("Crm", CHANNEL_CRM, CHANNEL_CRM, CHANNEL_CRM);
		Option web = FormHelper.createOption("Web", CHANNEL_WEB, CHANNEL_WEB, CHANNEL_WEB);
		Option mobile = FormHelper.createOption("Mobile",CHANNEL_MOBILE , CHANNEL_MOBILE, CHANNEL_MOBILE);
		channel.setValues(Arrays.asList(labPOS,crm,web,mobile));
		
		ElementGroup channelGrp = FormHelper.createGroup("channelGrp", Arrays.asList(channel));
		channelGrp.setLabel("Selection of Channel");
		channelGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.TEXT_SECONDARY, CssConstants.SMALL)));
		channelGrp.setRequired(true);
		
		/**date range of the configuration **/
		ElementGroup dateRangeGrp = FormHelper.createGroup("dateRangeGrp", Arrays.asList());
		dateRangeGrp.setClassName(CssConstants.COL_12);
		
		/** template information **/
		ElementGroup grp1 = FormHelper.createGroup("grp1", Arrays.asList(popupConfigName, templateNames));
		grp1.setGroups(Arrays.asList(statusGrp, dateRangeGrp,channelGrp));
		grp1.setLabel("Template Information");
		grp1.setClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.MB_4, CssConstants.ROW, CssConstants.G_3)));
		grp1.setLabelClassName(CssConstants.CUSTOM_FIELDSET);
		
		/** trigger type */
		RadioElement triggerType = new RadioElement("", TRIGGER_TYPE_ID);
		triggerType.setName(TRIGGER);
		Option definedTrigger = FormHelper.createOption("Defined Trigger", DEFINED_TRIGGER_ID,DEFINED_TRIGGER_ID , DEFINED_TRIGGER_ID);
		Option timeIntervalOption = FormHelper.createOption("Predefined time interval", TRIGGER, TIME_INTERVAL_ID, TIME_INTERVAL_ID);
		triggerType.setValues(Arrays.asList(definedTrigger, timeIntervalOption));
		triggerType.setRequired(true);
		
		ElementGroup triggerTypeGrp = FormHelper.createGroup(TRIGGER_TYPE_GRP, Arrays.asList(triggerType));
		triggerTypeGrp.setLabel("Trigger Type");
		triggerTypeGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.TEXT_SECONDARY, CssConstants.SMALL)));
		
		/** customer selection type for defined triggered */
		RadioElement triggerCustomerValue = new RadioElement("", "triggerCustomerValue");
		triggerCustomerValue.setName("customerValue");
		Option allCustomers = FormHelper.createOption("All", ALL_CUSTOMERS_ID,ALL_CUSTOMERS_ID , ALL_CUSTOMERS_ID);
		allCustomers.setSelected(true);
		Option fewCustomers = FormHelper.createOption("Few", FEW_CUSTOMERS_ID, FEW_CUSTOMERS_ID, FEW_CUSTOMERS_ID);
		triggerCustomerValue.setValues(Arrays.asList(allCustomers, fewCustomers));
		
		ElementGroup triggerCustomerGrp = FormHelper.createGroup("triggerCustomerGrp", Arrays.asList(triggerCustomerValue));
		triggerCustomerGrp.setLabel("Selection of a Customer");
		triggerCustomerGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.TEXT_SECONDARY, CssConstants.SMALL)));
		triggerCustomerGrp.setHidden(true);
		
		/** upload excel of customer id's when selection type is few **/
		ElementGroup uploadCustomerExcelGrp = FormHelper.createGroup("uploadCustomerExcelGrp", Arrays.asList());
		uploadCustomerExcelGrp.setLabel("Customer Upload");
		uploadCustomerExcelGrp.setLabelClassName(CssConstants.CUSTOM_FIELDSET);
		uploadCustomerExcelGrp.setHidden(true);
		
		/** patient gender for defined trigger*/
		CheckBoxElement patientGender = new CheckBoxElement("", "triggerPatientGender");
		Option male = FormHelper.createOption("Male", MALE_ID,MALE_ID , MALE_ID);
		male.setSelected(true);
		Option female = FormHelper.createOption("Female", FEMALE_ID, FEMALE_ID, FEMALE_ID);
		female.setSelected(true);
		Option others = FormHelper.createOption("Others", OTHERS_ID, OTHERS_ID, OTHERS_ID);
		others.setSelected(true);
		patientGender.setValues(Arrays.asList(male,female,others));
		
		ElementGroup triggerPatientGenderGrp = FormHelper.createGroup("triggerPatientGenderGrp", Arrays.asList(patientGender));
		triggerPatientGenderGrp.setLabel("Selection of Patient Gender");
		triggerPatientGenderGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.TEXT_SECONDARY, CssConstants.SMALL)));
		triggerPatientGenderGrp.setHidden(true);
		
		/** patient age for defined trigger */
		CheckBoxElement patientAge = new CheckBoxElement("", "patientAge");
		List<Option> ageRangeOptions = new ArrayList<>();
		Option defaultSelectedAll = FormHelper.createOption("All", "All", "All", "defaultAll");
		defaultSelectedAll.setSelected(true);
		ageRangeOptions.add(defaultSelectedAll);
		for (Entry<String, String> entry : patientageRange.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    Option optionObj = FormHelper.createOption(value, value, value, key);
		    optionObj.setDisabled(true);
		    optionObj.setSelected(true);
		    ageRangeOptions.add(optionObj);
		}
		patientAge.setValues(ageRangeOptions);
		
		ElementGroup triggerPatientAgeGrp = FormHelper.createGroup("triggerPatientAgeGrp", Arrays.asList(patientAge));
		triggerPatientAgeGrp.setLabel("Selection of Patient Age");
		triggerPatientAgeGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.TEXT_SECONDARY, CssConstants.SMALL)));
		triggerPatientAgeGrp.setHidden(true);
		
		/** trigger cron group */
		ElementGroup triggerCronGrp = FormHelper.createGroup("triggerCronGrp", Arrays.asList());
		triggerCronGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.TEXT_SECONDARY, CssConstants.SMALL)));
		triggerCronGrp.setHidden(true);
		
		/** defined trigger values */
		ElementGroup grp2 = FormHelper.createGroup("grp2", Arrays.asList());
		grp2.setGroups(Arrays.asList(triggerTypeGrp, triggerCustomerGrp,uploadCustomerExcelGrp,triggerPatientGenderGrp,triggerPatientAgeGrp, triggerCronGrp));
		grp2.setLabel("Trigger Information");
		grp2.setLabelClassName(CssConstants.CUSTOM_FIELDSET);
		grp2.setClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.ROW, CssConstants.G_3, CssConstants.MB_4)));
		
		/** selection of regions*/
		RadioElement region = new RadioElement("", IS_EXCEL_UPLOAD_FOR_REGIONS);
		region.setName(REGIONS_SELECTION);
		Option excelUploadRegionOption = FormHelper.createOption("Excel Upload", REGIONS_SELECTION, "Y", "excelUploadRegion");
		Option dropDownRegionOption = FormHelper.createOption("Manual Selection of Region", REGIONS_SELECTION, "N", "dropDownRegion");
		region.setValues(Arrays.asList(excelUploadRegionOption, dropDownRegionOption));
		region.setRequired(true);

		ElementGroup uploadRegionExcelGrp = FormHelper.createGroup("uploadRegionExcelGrp", Arrays.asList());
		uploadRegionExcelGrp.setHidden(true);

		ElementGroup regionsGrp = FormHelper.createGroup("regionsGrp", null);
		regionsGrp.setHidden(true);
		regionsGrp.setRequired(true);
		
		ElementGroup grp3 = FormHelper.createGroup("grp3", Arrays.asList(region));
		grp3.setGroups(Arrays.asList(uploadRegionExcelGrp, regionsGrp));
		grp3.setLabel("Regions");
		grp3.setLabelClassName(CssConstants.CUSTOM_FIELDSET);
		grp3.setClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.ROW, CssConstants.G_3, CssConstants.MB_4)));

		templateSelectionForm.setHtmlGroups(Arrays.asList(grp1, grp3, grp2));
		return templateSelectionForm;
	}
	

	public Form getConfigurationForm(List<PopUpTemplate> popupTemplates) {
		Form templateSelectionForm = new Form("", "configurationForm");

		InputElement popupConfigName = new InputElement(InputType.TEXT, "Popup Configuration Name ", NAME_ID);
		popupConfigName.setRequired(true);
		popupConfigName.setLabelClassName(CssConstants.COL_4);
		popupConfigName.setMaxLength(45);
		Map<String, String> attributesObj = new HashMap<>();
		attributesObj.put(AUTOCOMPLETE, OFF);
		popupConfigName.setAttributes(attributesObj);

		DataListElement templateNames = new DataListElement(TEMPLATE_NAME_ID);
		List<Option> tempValues = new ArrayList<>();
		if (UtilValidate.isNotEmpty(popupTemplates)) {
			for (PopUpTemplate popupTemplate : popupTemplates) {
				Option tempName = FormHelper.createOption(popupTemplate.getTemplateName(), popupTemplate.getTemplateName(), popupTemplate.getTemplateName().toLowerCase(), popupTemplate.getTemplateId().toString());
				tempValues.add(tempName);
			}
		}

		templateNames.setLabel(TEMPLATE_NAME);
		templateNames.setValues(tempValues);
		templateNames.setLabelClassName(CssConstants.W_100);
		templateNames.setDataListClassName(CssConstants.COL_4);

		RadioElement status = new RadioElement("", STATUS);
		status.setName(STATUS);
		status.setRequired(true);
		Option active = FormHelper.createOption(ACTIVE, STATUS, "A", ACTIVE_ID);
		Option inActive = FormHelper.createOption(IN_ACTIVE, STATUS, "I", IN_ACTIVE_ID);
		status.setValues(Arrays.asList(active, inActive));
		status.setLabelClassName(CssConstants.W_100);
		status.setRequired(true);

		ElementGroup statusGrp = FormHelper.createGroup(STATUS_GRP, Arrays.asList(status));
		statusGrp.setLabel(SELECT_STATUS);
		statusGrp.setClassName(CssConstants.COL_4);
		statusGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.TEXT_SECONDARY, CssConstants.SMALL)));

		ElementGroup dateRangeGrp = FormHelper.createGroup("dateRangeGrp", Arrays.asList());
		dateRangeGrp.setClassName(CssConstants.COL_12);

		ElementGroup grp1 = FormHelper.createGroup("grp1", Arrays.asList(popupConfigName, templateNames));
		grp1.setGroups(Arrays.asList(statusGrp, dateRangeGrp));
		grp1.setLabel("Template Information");
		grp1.setClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.MB_4, CssConstants.ROW, CssConstants.G_3)));
		grp1.setLabelClassName(CssConstants.CUSTOM_FIELDSET);

		RadioElement triggerType = new RadioElement("", TRIGGER_TYPE_ID);
		triggerType.setName(TRIGGER);
		Option productOption = FormHelper.createOption("Selection of a product", TRIGGER, PRODUCT_ID, PRODUCT_ID);
		Option timeIntervalOption = FormHelper.createOption("Predefined time interval", TRIGGER, TIME_INTERVAL_ID, TIME_INTERVAL_ID);
		triggerType.setValues(Arrays.asList(productOption, timeIntervalOption));
		triggerType.setRequired(true);

		ElementGroup triggerTypeGrp = FormHelper.createGroup(TRIGGER_TYPE_GRP, Arrays.asList(triggerType));
		triggerTypeGrp.setLabel("Trigger Type");
		triggerTypeGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.TEXT_SECONDARY, CssConstants.SMALL)));

		ElementGroup triggerCronGrp = FormHelper.createGroup("triggerCronGrp", Arrays.asList());
		triggerCronGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.TEXT_SECONDARY, CssConstants.SMALL)));
		triggerCronGrp.setHidden(true);

		RadioElement triggerProductValue = new RadioElement("", TRIGGER_VALUE_ID);
		triggerProductValue.setName(PRODUCT_VALUE);
		Option invoiceOption = FormHelper.createOption("On Invoice", PRODUCT_VALUE, "OnInvoice", "invoice");
		Option transferOption = FormHelper.createOption("On Transfer", PRODUCT_VALUE, "OnTransfer", "transfer");
		Option allOption = FormHelper.createOption(BOTH, PRODUCT_VALUE, ALL, "all");
		triggerProductValue.setValues(Arrays.asList(invoiceOption, transferOption, allOption));

		ElementGroup triggerProductGrp = FormHelper.createGroup("triggerProductGrp", Arrays.asList(triggerProductValue));
		triggerProductGrp.setLabel("Trigger Value");
		triggerProductGrp.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.TEXT_SECONDARY, CssConstants.SMALL)));
		triggerProductGrp.setHidden(true);

		ElementGroup grp2 = FormHelper.createGroup("grp2", Arrays.asList());
		grp2.setGroups(Arrays.asList(triggerTypeGrp, triggerProductGrp, triggerCronGrp));
		grp2.setLabel("Trigger Information");
		grp2.setLabelClassName(CssConstants.CUSTOM_FIELDSET);
		grp2.setClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.ROW, CssConstants.G_3, CssConstants.MB_4)));

		RadioElement region = new RadioElement("", IS_EXCEL_UPLOAD_FOR_REGIONS);
		region.setName(REGIONS_SELECTION);
		Option excelUploadRegionOption = FormHelper.createOption("Excel Upload", REGIONS_SELECTION, "Y", "excelUploadRegion");
		Option dropDownRegionOption = FormHelper.createOption("Manual Selection of Region", REGIONS_SELECTION, "N", "dropDownRegion");
		region.setValues(Arrays.asList(excelUploadRegionOption, dropDownRegionOption));
		region.setRequired(true);

		ElementGroup uploadRegionExcelGrp = FormHelper.createGroup("uploadRegionExcelGrp", Arrays.asList());
		uploadRegionExcelGrp.setHidden(true);

		ElementGroup regionsGrp = FormHelper.createGroup("regionsGrp", null);
		regionsGrp.setHidden(true);
		regionsGrp.setRequired(true);

		ElementGroup grp3 = FormHelper.createGroup("grp3", Arrays.asList(region));
		grp3.setGroups(Arrays.asList(uploadRegionExcelGrp, regionsGrp));
		grp3.setLabel("Regions");
		grp3.setLabelClassName(CssConstants.CUSTOM_FIELDSET);
		grp3.setClassName(MarketingCssHelper.createCssClass(Arrays.asList(CssConstants.ROW, CssConstants.G_3, CssConstants.MB_4)));

		ElementGroup uploadProductsExcelGrp = FormHelper.createGroup("uploadProductsExcelGrp", Arrays.asList());
		uploadProductsExcelGrp.setLabel("Products Upload");
		uploadProductsExcelGrp.setLabelClassName(CssConstants.CUSTOM_FIELDSET);
		uploadProductsExcelGrp.setHidden(true);

		templateSelectionForm.setHtmlGroups(Arrays.asList(grp1, grp3, grp2, uploadProductsExcelGrp));
		return templateSelectionForm;
	}

	public Form getConfigurationSearchForm() {
		Form popupConfigSearchForm = new Form("", "configurationSearchForm");
		
		Set<String> userRoles = UserUtil.getModuleRoles();
		
		InputElement name = new InputElement(InputType.TEXT, "Popup Configuration Name", "configurationLikeName");
		name.setMaxLength(45);
		name.setMinLength(3);
		name.setLabelClassName(CssConstants.COL_6);
		Map<String, String> attributesObj = new HashMap<>();
		attributesObj.put(AUTOCOMPLETE, OFF);
		name.setAttributes(attributesObj);

		InputElement storeId = new InputElement(InputType.TEXT, "Store ID", "storeId");
		storeId.setMaxLength(12);
		storeId.setMinLength(2);
		storeId.setLabelClassName(CssConstants.COL_6);
		attributesObj = new HashMap<>();
		attributesObj.put(AUTOCOMPLETE, OFF);
		storeId.setAttributes(attributesObj);

		InputElement productId = new InputElement(InputType.TEXT, "Product ID", "productId");
		productId.setMaxLength(8);
		productId.setMinLength(8);
		productId.setLabelClassName(CssConstants.COL_6);
		productId.setRegex("^[a-zA-Z]{4}\\d{4}$");
		attributesObj = new HashMap<>();
		attributesObj.put(AUTOCOMPLETE, OFF);
		productId.setAttributes(attributesObj);
		productId.setHidden(true);

		DateRangeElement dateRange = prepareDateRangeElement(new Date(),new Date(), null, new Date(), 29, THIRTY_DAYS_IS_THE_MAXIMUM_RANGE, null);
		dateRange.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList("col-6")));

		RadioElement triggerType = new RadioElement("", TRIGGER_TYPE_ID);
		triggerType.setName(TRIGGER);
		Option productOption = FormHelper.createOption("Product", TRIGGER, PRODUCT_ID, PRODUCT_ID);
		Option timeIntervalOption = FormHelper.createOption("Time Interval", TRIGGER, TIME_INTERVAL_ID, TIME_INTERVAL_ID);
		triggerType.setValues(Arrays.asList(productOption, timeIntervalOption));

		ElementGroup triggerTypeGrp = FormHelper.createGroup(TRIGGER_TYPE_GRP, Arrays.asList(triggerType));
		triggerTypeGrp.setLabel("Select Trigger Type");
		triggerTypeGrp.setClassName(CssConstants.COL_6);
		triggerTypeGrp.setLabelClassName("small text-secondary");
		triggerTypeGrp.setHidden(true);
		
		RadioElement status = new RadioElement("", STATUS);
		status.setName(STATUS);
		Option active = FormHelper.createOption(ACTIVE, STATUS, PopUpConfigStatus.ACTIVE.name(), ACTIVE_ID);
		Option inActive = FormHelper.createOption(IN_ACTIVE, STATUS, PopUpConfigStatus.INACTIVE.name(), IN_ACTIVE_ID);
		status.setValues(Arrays.asList(active, inActive));

		ElementGroup statusGrp = FormHelper.createGroup(STATUS_GRP, Arrays.asList(status));
		statusGrp.setLabel(SELECT_STATUS);
		statusGrp.setClassName(CssConstants.COL_6);
		statusGrp.setLabelClassName("small text-secondary");

		List<HtmlElement> groupElements = new ArrayList<>(Arrays.asList(name, storeId, productId, dateRange));
		
		if(UtilValidate.isNotEmpty(userRoles) && userRoles.contains(MarketingConstants.PHARMA_CONFIG_VIEW_ROLE) && userRoles.contains(MarketingConstants.LAB_CONFIG_VIEW_ROLE)) {
			RadioElement applicableType = new RadioElement("", APPLICABLE_TYPE_ID);
			applicableType.setName(APPLICABLE_TYPE_ID);
			Option pharmaOption = FormHelper.createOption(PHARMACY, ApplicableType.PHARMACY.name(), ApplicableType.PHARMACY.toString(), Integer.toString(ApplicableType.PHARMACY.getType()));
			Option pathlabsOption = FormHelper.createOption(PATH_LABS, ApplicableType.PATHLABS.name(), ApplicableType.PATHLABS.toString(), Integer.toString(ApplicableType.PATHLABS.getType()));
			applicableType.setValues(Arrays.asList(pharmaOption, pathlabsOption));
			
			groupElements.add(0, applicableType);
		}
		
		ElementGroup grp1 = FormHelper.createGroup("grp1", groupElements);
		grp1.setGroups(Arrays.asList(triggerTypeGrp,statusGrp));
		grp1.setLabel("Popup Configuration Search");
		grp1.setLabelClassName(CssConstants.CUSTOM_FIELDSET);
		grp1.setClassName("row g-3 mb-2 p-12");

		ButtonElement reset = new ButtonElement(CLEAR, CLEAR_ID);
		reset.setClassName("btn brand-secondary mx-3 px-4");
		reset.setReset(true);

		ButtonElement search = new ButtonElement(SEARCH, SEARCH_ID);
		search.setClassName("btn btn-dark px-4");
		search.setSubmit(true);

		ElementGroup buttonsGrp = FormHelper.createGroup("buttonsGrp", Arrays.asList(search, reset));
		buttonsGrp.setClassName("d-flex flex-row-reverse border-top p-2");

		popupConfigSearchForm.setHtmlGroups(Arrays.asList(grp1, buttonsGrp));
		popupConfigSearchForm.setAtleastOneFieldRequired(true);
		return popupConfigSearchForm;
	}
	
	public Form getConfigurationRequestSearchForm() {
		Form configRequestSearchForm = new Form("","configRequestSearchForm");
		
		Set<String> userRoles = UserUtil.getModuleRoles();
		
		InputElement name = new InputElement(InputType.TEXT, "Popup Configuration Name", "configurationLikeName");
		name.setMaxLength(45);
		name.setMinLength(3);
		name.setLabelClassName(CssConstants.COL_6);
		Map<String, String> attributesObj = new HashMap<>();
		attributesObj.put(AUTOCOMPLETE, OFF);
		name.setAttributes(attributesObj);
		
		InputElement requestId = new InputElement(InputType.TEXT, "Request Id", REQUEST_ID);
		requestId.setLabelClassName(CssConstants.COL_6);
		requestId.setRegex(InputRegexPattern.NUMERIC.getRegex());
		requestId.setMaxLength(10);

		DateRangeElement dateRange = prepareDateRangeElement(new Date(),new Date(), null, new Date(), 29, THIRTY_DAYS_IS_THE_MAXIMUM_RANGE, null);
		dateRange.setLabelClassName(MarketingCssHelper.createCssClass(Arrays.asList("col-6")));
	
		InputElement createdBy = new InputElement(InputType.TEXT, CREATED_BY, CREATED_BY_ID);
		createdBy.setRegex(InputRegexPattern.ALPHA_NUMERIC.getRegex());
		createdBy.setLabelClassName(CssConstants.COL_6);
		createdBy.setMaxLength(10);
		
		CheckBoxElement requestStatues = new CheckBoxElement(STATUS_LABEL, "requestStatus");
		requestStatues.setLabelClassName("d-block text-secondary font-14");
		requestStatues.setClassName("col-6");
		Option approved = FormHelper.createOption(RequestStatus.APPROVED.getLabel(), RequestStatus.APPROVED.getLabel(), RequestStatus.APPROVED.name(), RequestStatus.APPROVED.toString());
		Option pending = FormHelper.createOption(RequestStatus.PENDING.getLabel(), RequestStatus.PENDING.getLabel(), RequestStatus.PENDING.name(), RequestStatus.PENDING.toString());
		Option reject = FormHelper.createOption(RequestStatus.REJECTED.getLabel(), RequestStatus.REJECTED.getLabel(), RequestStatus.REJECTED.name(), RequestStatus.REJECTED.toString());
		requestStatues.setValues(Arrays.asList(approved,pending,reject));
		
		List<HtmlElement> groupElements = new ArrayList<>(Arrays.asList(requestStatues, requestId, name, createdBy, dateRange));
		
		if(UtilValidate.isNotEmpty(userRoles) && userRoles.contains(MarketingConstants.PHARMA_CONFIG_VIEW_ROLE) && userRoles.contains(MarketingConstants.LAB_CONFIG_VIEW_ROLE)) {
			RadioElement applicableType = new RadioElement("", APPLICABLE_TYPE_ID);
			applicableType.setLabel("Applicable Types");
			applicableType.setClassName("col-6");
			applicableType.setLabelClassName("d-block text-secondary font-14");
			Option pharmaOption = FormHelper.createOption(PHARMACY, ApplicableType.PHARMACY.name(), ApplicableType.PHARMACY.toString(), Integer.toString(ApplicableType.PHARMACY.getType()));
			Option pathlabsOption = FormHelper.createOption(PATH_LABS, ApplicableType.PATHLABS.name(), ApplicableType.PATHLABS.toString(), Integer.toString(ApplicableType.PATHLABS.getType()));
			applicableType.setValues(Arrays.asList(pharmaOption, pathlabsOption));
			
			groupElements.add(0, applicableType);
		}
		
		ElementGroup grp1 = FormHelper.createGroup("grp1", groupElements);
		grp1.setLabel("Popup Configuration Search");
		grp1.setLabelClassName(CssConstants.CUSTOM_FIELDSET);
		grp1.setClassName("row g-3 mb-2 p-12");

		ButtonElement reset = new ButtonElement(CLEAR, CLEAR_ID);
		reset.setClassName("btn brand-secondary mx-3 px-4");
		reset.setReset(true);

		ButtonElement search = new ButtonElement(SEARCH, SEARCH_ID);
		search.setClassName("btn btn-dark px-4");
		search.setSubmit(true);

		ElementGroup buttonsGrp = FormHelper.createGroup("buttonsGrp", Arrays.asList(search, reset));
		buttonsGrp.setClassName("d-flex flex-row-reverse border-top p-2");

		configRequestSearchForm.setHtmlGroups(Arrays.asList(grp1, buttonsGrp));
		configRequestSearchForm.setAtleastOneFieldRequired(true);
		
		return configRequestSearchForm;
	}
	
	private DateRangeElement prepareDateRangeElement(Date startDate, Date endDate,Date minRange,Date maxRange,int dateInterval, String helperText, String label){
		DateRangeElement dateRange = new DateRangeElement(DATE_RANGE, DATE_RANGE_ID);
		if(UtilValidate.isNotEmpty(startDate)) {
			dateRange.setStartDate(startDate);
		}
		if(UtilValidate.isNotEmpty(endDate)) {
			dateRange.setEndDate(endDate);
		}
		Map<String, String> attributes = new HashMap<>();
		attributes.put(PLACEHOLDER_TEXT, "Date Range (mmm dd,yyyy)");
		if(UtilValidate.isNotEmpty(dateInterval) && dateInterval > 0) {
			dateRange.setDateInterval(dateInterval);
		}
		if(UtilValidate.isNotEmpty(helperText)) {
			dateRange.setHelperText(helperText);
		}
		if(UtilValidate.isNotEmpty(minRange)) {
			dateRange.setMinRange(minRange);
		}
		if(UtilValidate.isNotEmpty(maxRange)) {
			dateRange.setMaxRange(maxRange);
		}
		attributes.put("showTodayButton", "true");
		if(UtilValidate.isNotEmpty(dateInterval) && dateInterval > 0 && dateInterval >= 6) {
			attributes.put("showLastWeekButton", "true");
		}
		if(UtilValidate.isNotEmpty(dateInterval) && dateInterval > 0 && dateInterval >= 14) {
			attributes.put("showLast15DaysButton", "true");
		}
		if(UtilValidate.isNotEmpty(dateInterval) && dateInterval > 0 && dateInterval >= 29) {
			attributes.put("showLast30DaysButton", "true");
		}
		if(UtilValidate.isNotEmpty(label)) {
			dateRange.setLabel(label);
		}
		dateRange.setAttributes(attributes);
		return dateRange;	
	}
}
