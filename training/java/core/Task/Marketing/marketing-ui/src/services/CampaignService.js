import serverRequest, { downloadExcellServerRequest, downloadExcellServerRequestPost } from '../axios';
import CONFIG from "../services/ServiceConstants";

export default function CampaignService() {
    
    function createCampaign(data) {
        return serverRequest(CONFIG.API.CAMPAIGN.CREATE_CAMPAIGN, data);
    }
    function createMultipleCampaigns(data) {
        return downloadExcellServerRequestPost(CONFIG.API.CAMPAIGN.CREATE_MULTIPLE_CAMPAIGN, data);
    }
     function searchCampaign(data) {
        return serverRequest(CONFIG.API.CAMPAIGN.SEARCH_CAMPAIGN, data);
    }
    function getCampaignByCampaignId(data) {
        return serverRequest(CONFIG.API.CAMPAIGN.GET_CAMPAIGN_BY_CAMPAIGNID, data);
    }
    function editCampaign(data) {
        return serverRequest(CONFIG.API.CAMPAIGN.EDIT_CAMPAIGN, data);
    }
    function cloneCampaign(data) {
        return serverRequest(CONFIG.API.CAMPAIGN.CLONE_CAMPAIGN, data);
    }
    function approveCampaign(data) {
        return serverRequest(CONFIG.API.CAMPAIGN.APPROVE_CAMPAIGN, data);
    }
    function getCampaignProducts(data) {
        return downloadExcellServerRequest(CONFIG.API.CAMPAIGN.GET_PRODUCTIDS_FOR_CAMPAIGNID, data);
    }
    function getCampaignCustomerIds(data) {
        return downloadExcellServerRequest(CONFIG.API.CAMPAIGN.GET_CUSTOMERSIDS_FOR_CAMPAIGNID, data);
    }
    function validateCampaignName(data) {
        return serverRequest(CONFIG.API.CAMPAIGN.VALIDATE_CAMPAIGN_NAME, data);
    }
    function updateCampaignTodate(data) {
        return serverRequest(CONFIG.API.CAMPAIGN.UPDATE_TO_DATE, data);
    }
    function updateInActiveCampaignToDate(data) {
        return serverRequest(CONFIG.API.CAMPAIGN.UPDATE_INACTIVE_CAMPAIGN_TO_DATE, data);
    }
    function rejectCampaign(data) {
        return serverRequest(CONFIG.API.CAMPAIGN.REJECT_CAMPAIGN, data);
    }
    function validateCouponCode(data) {
        return serverRequest(CONFIG.API.CAMPAIGN.VALIDATE_COUPON_NAME, data);
    }
    function getStoresExcel(data) {
        return downloadExcellServerRequest(CONFIG.API.CAMPAIGN.GET_STORES_EXCEL,data);
    }

    return Object.freeze({
        createCampaign,
        createMultipleCampaigns,
        searchCampaign,
        editCampaign,
        cloneCampaign,
        getCampaignByCampaignId,
        approveCampaign,
        getCampaignProducts,
        getCampaignCustomerIds,
        validateCampaignName,
        updateCampaignTodate,
        updateInActiveCampaignToDate,
        rejectCampaign,
        validateCouponCode,
        getStoresExcel
    })
}