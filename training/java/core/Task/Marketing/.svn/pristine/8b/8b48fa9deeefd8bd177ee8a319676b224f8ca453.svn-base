import CONFIG from "../services/ServiceConstants"
import serverRequest from '../axios'
export default function FormJsonService() {
    async function  getCampaignInfoForm() {
        return serverRequest(CONFIG.API.GET_CAMPAIGN_INFO_FORM);
    }
    async function  getMetaInfoForm() {
        return serverRequest(CONFIG.API.GET_META_INFO_FORM);
    }
    async function getMultiItemSlabsForm() {
        return serverRequest(CONFIG.API.GET_MI_SLAB_FORM);
    }
    async function getRegionsForm() {
        return serverRequest(CONFIG.API.GET_REGIONS_FORM);
    }
    return Object.freeze({
        getCampaignInfoForm,
        getMetaInfoForm,
        getMultiItemSlabsForm,
        getRegionsForm,
    });
}