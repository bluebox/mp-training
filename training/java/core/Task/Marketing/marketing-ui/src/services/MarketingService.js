import serverRequest, { downloadExcellServerRequest } from '../axios';
import CONFIG from "../services/ServiceConstants";

export default function MarketingService() {
    
    function getUserDetails() {
        return serverRequest(CONFIG.API.GET_LOGGED_IN_USER_DETAIL);
    }
    function getCountries() {
        return serverRequest(CONFIG.API.GET_COUNTRIES);
    }
    function getStates(data) {
        return serverRequest(CONFIG.API.GET_STATES, data);
    }
    function getCities(data) {
        return serverRequest(CONFIG.API.GET_CITIES, data);
    }
    function getStores(data) {
        return serverRequest(CONFIG.API.GET_STORES, data);
    }
    function getPathlabStores(data) {
        return serverRequest(CONFIG.API.GET_PATHLAB_STORES, data);
    }
    function getStoreNames(data) {
        return serverRequest(CONFIG.API.GET_STORE_NAMES, data);
    }
    function createCampaign(data) {
        return serverRequest(CONFIG.API.CREATE_CAMPAIGN, data);
    }
    function createRegularPromotion(data) {
        return serverRequest(CONFIG.API.CREATE_REGULAR_PROMOTION, data);
    }
    function searchMultiItemCampaign(data) {
        return serverRequest(CONFIG.API.SEARCH_MULTI_ITEM_CAMPAIGN, data);
    }
    function searchRegularPromotion(data) {
        return serverRequest(CONFIG.API.SEARCH_REGULAR_PROMOTION, data)
    }
    function getMultiItemCampaignByCampaignId(data) {
        return serverRequest(CONFIG.API.GET_MULTI_ITEM_CAMPAIGN_BY_CAMPAIGNID, data);
    }
    function getRegularPromotionByPromotionId(data) {
        return serverRequest(CONFIG.API.GET_REGULAR_PROMOTION_BY_PROMOTIONID, data);
    }
    function editCampaign(data) {
        return serverRequest(CONFIG.API.EDIT_CAMPAIGN, data);
    }
    function editRegularPromotion(data) {
        return serverRequest(CONFIG.API.EDIT_REGULAR_PROMOTION, data);
    }
    function cloneCampaign(data) {
        return serverRequest(CONFIG.API.CLONE_CAMPAIGN, data);
    }
    function cloneRegularPromotion(data) {
        return serverRequest(CONFIG.API.CLONE_REGULAR_PROMOTION, data);
    }
    function approveCampaign(data) {
        return serverRequest(CONFIG.API.APPROVE_CAMPAIGN, data);
    }
    function approveRegularPromotion(data) {
        return serverRequest(CONFIG.API.APPROVE_REGULAR_PROMOTION, data);
    }
    function getProductIds(data) {
        return downloadExcellServerRequest(CONFIG.API.GET_PRODUCTIDS_FOR_CAMPAIGNID, data);
    }
    function getCustomerIds(data) {
        return downloadExcellServerRequest(CONFIG.API.GET_CUSTOMERSIDS_FOR_CAMPAIGNID, data);
    }
    function getRegularPromotionCustomerIds(data) {
        return downloadExcellServerRequest(CONFIG.API.GET_CUSTOMERIDS_FOR_REGULAR_PROMOTION, data);
    }
    function getLoyaltyTypes() {
        return serverRequest(CONFIG.API.GET_LOYALTY_TYPES);
    }
    function getInvoiceCategoryTypes() {
        return serverRequest(CONFIG.API.GET_INVOICE_CATEGORY_TYPES);
    }
    function getRegionsDataGrid(data) {
        return serverRequest(CONFIG.API.GET_REGIONS_DATA_GRID, data)
    }
    function validateCampaignName(data) {
        return serverRequest(CONFIG.API.VALIDATE_CAMPAIGN_NAME, data);
    }
    function validateRegularPromotionName(data) {
        return serverRequest(CONFIG.API.VALIDATE_REGULAR_PROMOTION_NAME, data);
    }
    function getCampaignInfoForm() {
        return serverRequest(CONFIG.API.GET_CAMPAIGN_INFO_FORM);
    }
    function updateTodate(data) {
        return serverRequest(CONFIG.API.UPDATE_TO_DATE, data);
    }
    function updateInActiveTodate(data) {
        return serverRequest(CONFIG.API.UPDATE_IN_ACTIVE_TO_DATE, data);
    }
    function rejectMultiItemCampaign(data) {
        return serverRequest(CONFIG.API.REJECT_MULTI_ITEM_CAMPAIGN, data);
    }
    function updateRegularPromotionToDate(data) {
        return serverRequest(CONFIG.API.UPDATE_REGULAR_PROMOTION_TO_DATE, data);
    }
    function updateInActiveRegularPromotionToDate(data) {
        return serverRequest(CONFIG.API.UPDATE_IN_ACTIVE_REGULAR_PROMOTION_TO_DATE, data);
    }    
    function rejectRegularPromotion(data) {
        return serverRequest(CONFIG.API.REJECT_REGULAR_PROMOTION, data);
    }
    function getSlabGroupDetails(data) {
        return serverRequest(CONFIG.API.GET_SLAB_GROUP_DETAILS, data);
    }
    function checkSlabGroupAssignOrNot(data) {
        return serverRequest(CONFIG.API.CHECK_SLAB_GROUP_ASSIGN_OR_NOT, data);
    }
    function saveSlabDetails(data) {
        return serverRequest(CONFIG.API.SAVE_SLAB_DETAILS, data);
    }
    function validateCouponName(data) {
        return serverRequest(CONFIG.API.VALIDATE_COUPON_NAME, data);
    }
    function clearCache() {
        return serverRequest(CONFIG.API.CLEAR_CACHE);
    }
    function getMultiItemStoresExcel(data) {
        return downloadExcellServerRequest(CONFIG.API.GET_MULTI_STORES_EXCEL,data);
    }
    function getRegularPromotionStoresExcel(data) {
        return downloadExcellServerRequest(CONFIG.API.GET_REGULAR_STORES_EXCEL,data);
    }

    return Object.freeze({
        getUserDetails,
        getCountries,
        getStates,
        getCities,
        getStores,
        getStoreNames,
        createCampaign,
        createRegularPromotion,
        editCampaign,
        editRegularPromotion,
        cloneCampaign,
        cloneRegularPromotion,
        searchMultiItemCampaign,
        searchRegularPromotion,
        getMultiItemCampaignByCampaignId,
        getRegularPromotionByPromotionId,
        approveCampaign,
        approveRegularPromotion,
        getProductIds,
        getCustomerIds,
        getRegularPromotionCustomerIds,
        getLoyaltyTypes,
        getInvoiceCategoryTypes,
        getRegionsDataGrid,
        validateCampaignName,
        validateRegularPromotionName,
        getCampaignInfoForm,
        updateInActiveTodate,
        updateTodate,
        rejectMultiItemCampaign,
        updateRegularPromotionToDate,
        updateInActiveRegularPromotionToDate,
        rejectRegularPromotion,
        getSlabGroupDetails,
        checkSlabGroupAssignOrNot,
        saveSlabDetails,
        validateCouponName,
        clearCache,
        getPathlabStores,
        getMultiItemStoresExcel,
        getRegularPromotionStoresExcel
    })
}