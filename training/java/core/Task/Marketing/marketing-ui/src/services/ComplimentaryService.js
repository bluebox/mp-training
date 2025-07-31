import serverRequest, { downloadExcellServerRequest } from '../axios';
import CONFIG from "./ServiceConstants";

export default function ComplimentaryService() {
    function createPromotion(data) {
        return serverRequest(CONFIG.API.COMPLIMENTARY.CREATE_PROMOTION, data);
    }
    function searchComplimentaryPromotion(data) {
        return serverRequest(CONFIG.API.COMPLIMENTARY.SEARCH_PROMOTION, data);
    }
    function getPromotionByComplimentaryId(data) {
        return serverRequest(CONFIG.API.COMPLIMENTARY.GET_PROMOTION_BY_COMPLIMENTARY_ID, data);
    }
    function editPromotion(data) {
        return serverRequest(CONFIG.API.COMPLIMENTARY.EDIT_PROMOTION, data);
    }
    function clonePromotion(data) {
        return serverRequest(CONFIG.API.COMPLIMENTARY.CLONE_PROMOTION, data);
    }
    function approvePromotion(data) {
        return serverRequest(CONFIG.API.COMPLIMENTARY.APPROVE_PROMOTION, data);
    }
    function updatePromotionTodate(data) {
        return serverRequest(CONFIG.API.COMPLIMENTARY.UPDATE_TO_DATE, data);
    }
    function updateInActivePromotionToDate(data) {
        return serverRequest(CONFIG.API.COMPLIMENTARY.UPDATE_INACTIVE_PROMOTION_TO_DATE, data);
    }
    function rejectPromotion(data) {
        return serverRequest(CONFIG.API.COMPLIMENTARY.REJECT_PROMOTION, data);
    }
    function getComplimentaryProducts(data) {
        return downloadExcellServerRequest(CONFIG.API.COMPLIMENTARY.GET_COMPLIMENTARY_PRODUCTS, data);
    }
    function getReferenceCustomers(data) {
        return downloadExcellServerRequest(CONFIG.API.COMPLIMENTARY.GET_REFERENCE_CUSTOMERS, data);
    }
    function validatePromotionName(data) {
        return serverRequest(CONFIG.API.COMPLIMENTARY.VALIDATE_PROMOTION_NAME, data);
    }
    function validateSlabGrpName(data) {
        return serverRequest(CONFIG.API.COMPLIMENTARY.VALIDATE_SLAB_GRP_NAME, data);
    }
    function getStoresExcel(data) {
        return downloadExcellServerRequest(CONFIG.API.COMPLIMENTARY.GET_STORES_EXCEL, data);
    }

    return Object.freeze({
        createPromotion,
        searchComplimentaryPromotion,
        editPromotion,
        clonePromotion,
        getPromotionByComplimentaryId,
        approvePromotion,
        updatePromotionTodate,
        updateInActivePromotionToDate,
        rejectPromotion,
        getComplimentaryProducts,
        getReferenceCustomers,
        validatePromotionName,
        validateSlabGrpName,
        getStoresExcel
    })
}