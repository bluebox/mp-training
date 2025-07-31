import serverRequest, { downloadExcellServerRequest, getImageServerDetailsUploadImage } from "../axios";
import CONFIG from "./ServiceConstants";

export default function PopupService() {
  function createTemplate(data) {
    return serverRequest(CONFIG.API.POPUP.CREATE_TEMPLATE, data);
  }

  function checkTemplateName(data) {
    return serverRequest(CONFIG.API.POPUP.CHECK_TEMPLATE_NAME, data);
  }

  function getTemplates(data) {
    return serverRequest(CONFIG.API.POPUP.GET_TEMPLATES, data);
  }

  function getTemplateInfo(data) {
    return serverRequest(CONFIG.API.POPUP.GET_TEMPLATE, data);
  }

  function getParameters(data) {
    return serverRequest(CONFIG.API.POPUP.GET_PARAMETERS,data);
  }

  function updateTemplate(data) {
    return serverRequest(CONFIG.API.POPUP.UPDATE_TEMPLATE, data);
  }

  function createConfiguration(data) {
    return serverRequest(CONFIG.API.POPUP.CREATE_CONFIGURATION, data);
  }

  function checkConfigurationName(data) {
    return serverRequest(CONFIG.API.POPUP.CHECK_CONFIGURATION_NAME, data);
  }

  function getConfigurations(data) {
    return serverRequest(CONFIG.API.POPUP.GET_CONFIGURATIONS, data);
  }

  function updateConfigurations(data) {
    return serverRequest(CONFIG.API.POPUP.UPDATE_CONFIGURATIONS, data);
  }

  function updateConfiguration(data) {
    return serverRequest(CONFIG.API.POPUP.UPDATE_CONFIGURATION, data);
  }

  function isTemplateActive(data) {
    return serverRequest(CONFIG.API.POPUP.IS_TEMPLATE_ACTIVE, data);
  }

  function getRegionsExcel(data) {
    return downloadExcellServerRequest(CONFIG.API.POPUP.GET_REGIONS_EXCEL, data);
  }

  function getProductIdsExcel(data) {
    return downloadExcellServerRequest(CONFIG.API.POPUP.GET_PRODUCT_IDS_EXCEL, data);
  }

  function uploadImage(image, imageType) {
    return getImageServerDetailsUploadImage(image, imageType);
  }

  function getTemplateRequests(data) {
    return serverRequest(CONFIG.API.POPUP.GET_TEMPLATE_REQUESTS, data);
  }

  function getConfigRequests(data) {
    return serverRequest(CONFIG.API.POPUP.GET_CONFIGURATION_REQUESTS, data);
  }

  function getConfigRequestsOnRequestId(obj){
    return serverRequest(CONFIG.API.GET_CONFIGURATION_ON_REQUEST_ID,obj);
  }

  function approveConfiguration(obj){
    return serverRequest(CONFIG.API.APPROVE_CONFIGURATION,obj);
  }

  function rejectConfiguration(obj){
    return serverRequest(CONFIG.API.REJECT_CONFIGURATION, obj);
  }


  function createPopupConfigRequest(data) {
    return serverRequest(CONFIG.API.CREATE_POPUP_CONFIG_REQUEST, data);
  }

  function updatePopUpConfigRequest(data){
    return serverRequest(CONFIG.API.UPDATE_POPUP_CONFIG_REQUEST, data); 
  }

  function approvePopupTemplateRequest(data) {
    return serverRequest(CONFIG.API.POPUP.APPROVE_POPUP_TEMPLATE_REQUEST, data);
  }

  function rejectPopupTemplateRequest(data) {
    return serverRequest(CONFIG.API.POPUP.REJECT_POPUP_TEMPLATE_REQUEST, data);
  }

  function createPopupTemplateRequest(data) {
    return serverRequest(CONFIG.API.POPUP.CREATE_POPUP_TEMPLATE_REQUEST, data);
  }

  function updatePopupTemplateRequest(data) {
    return serverRequest(CONFIG.API.POPUP.UPDATE_POPUP_TEMPLATE_REQUEST, data);
  }

  function getTemplateRequest(data) {
    return serverRequest(CONFIG.API.POPUP.GET_TEMPLATE_REQUEST, data);
  }

  function getCustomerIdsExcel(data) {
    return downloadExcellServerRequest(CONFIG.API.POPUP.GET_CUSTOMERIDS_EXCEL, data);
  }

  function getConfigRequestsByConfigId(obj){
    return serverRequest(CONFIG.API.GET_CONFIGURATION_BY_CONFIG_ID,obj);
  }


  return Object.freeze({
    createTemplate,
    checkTemplateName,
    getTemplates,
    getTemplateInfo,
    updateTemplate,
    createConfiguration,
    checkConfigurationName,
    getConfigurations,
    updateConfigurations,
    updateConfiguration,
    isTemplateActive,
    getRegionsExcel,
    getProductIdsExcel,
    uploadImage,
    getTemplateRequests,
    getConfigRequests,
    getParameters,
    getConfigRequestsOnRequestId,
    approveConfiguration,
    rejectConfiguration,
    createPopupConfigRequest,
    updatePopUpConfigRequest,
    approvePopupTemplateRequest,
    rejectPopupTemplateRequest,
    createPopupTemplateRequest,
    updatePopupTemplateRequest,
    getTemplateRequest,
    getCustomerIdsExcel,
    getConfigRequestsByConfigId
  });
}
