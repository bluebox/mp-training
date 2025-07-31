import DataGridHelper from "../components/common/DataGridHelper";
import CampaignService from "../services/CampaignService";
import ComplimentaryService from "../services/ComplimentaryService";
import MarketingService from "../services/MarketingService";
import { CAMPAIGN, COMPLIMENTARY_ROLES, MIC_ROLES, RP_ROLES } from "./MarketingRoles";
import { PromotionType } from "./PromotionConstants";
import { CAMPAIGN_URLS, COMPLIMENTARY_URLS, MIC_URLS, RP_URLS } from "./UrlConstants";

export const MIC_TABS = {
    default: {title: "Default", tabId: 0},
    campaignInfo: { title: "Campaign Info", tabId: 1 },
    metaInfo: { title: "Meta Info", tabId: 2 },
    slabs: { title: "Slabs", tabId: 3 },
    loyalty: { title: "Loyalty Type", tabId: 4 },
    region: { title: "Region", tabId: 5 },
    uploads: { title: "Uploads & Downloads", tabId: 6 },
};

export const RP_TABS = {
    default: {title: "Default", tabId: 0},
    promotionInfo: { title: "Promotion Info", tabId: 1 },
    couponInfo: { title: "Coupon Info", tabId: 2 },
    metaInfo: { title: "Meta Info", tabId: 3 },
    slabs: { title: "Slabs", tabId: 4 },
    category: { title: "Category", tabId: 5 },
    loyalty: { title: "Loyalty", tabId: 6 },
    region: { title: "Region", tabId: 7 },
    details: { title: "Details", tabId: 8 },
    uploads: { title: "Uploads", tabId: 9 }
};

export const CAMPAIGN_TABS = {
    default: {title: "Default", tabId: 0},
    campaignInfo: { title: "Campaign Info", tabId: 1 },
    couponInfo: { title: "Coupon Info", tabId: 2 },
    metaInfo: { title: "Meta Info", tabId: 3 },
    loyalty: { title: "Loyalty Type", tabId: 4 },
    region: { title: "Region", tabId: 5 },
    uploads: { title: "Uploads & Downloads", tabId: 6 },
};

export const COMPLIMENTARY_TABS = {
    default: {title: "Default", tabId: 0},
    campaignInfo: { title: "Promotion Info", tabId: 1 },
    slabs: { title: "Slabs", tabId: 2 },
    loyalty: { title: "Loyalty Type", tabId: 3},
    region: { title: "Region", tabId: 4 },
    uploads: { title: "Uploads & Downloads", tabId: 5 },
};

export const LIST_TABS = {
    multiItem : {roles : MIC_ROLES , gridData: DataGridHelper().multiItemCampaignData("Campaign") , service: MarketingService().searchMultiItemCampaign, url : MIC_URLS, id : "campaignId", applicableType:"applicableType", name : "campaignName", rejectService : MarketingService().rejectMultiItemCampaign, promotionType : PromotionType.multiItem, viewName: "Multi Item Campaigns"},
    regular : {roles : RP_ROLES , gridData: DataGridHelper().regularPromotionData("Promotion") , service: MarketingService().searchRegularPromotion, url : RP_URLS, id : "promotionId", applicableType:"applicableType", name : "promotionName", rejectService: MarketingService().rejectRegularPromotion, promotionType : PromotionType.regular, viewName: "Promotions"},
    complimentary : {roles : COMPLIMENTARY_ROLES , gridData: DataGridHelper().multiItemCampaignData("Promotion") , service: ComplimentaryService().searchComplimentaryPromotion, url : COMPLIMENTARY_URLS, id : "complimentaryId", applicableType:"applicableType", name : "name", rejectService: ComplimentaryService().rejectPromotion, promotionType : PromotionType.complimentary, viewName: "Promotions"},
    campaign : {roles : CAMPAIGN , gridData: DataGridHelper().multiItemCampaignData("Campaign") , service: CampaignService().searchCampaign, url : CAMPAIGN_URLS, id : "campaignId", applicableType:"promotionApplicableType", name : "campaignName", rejectService : CampaignService().rejectCampaign, promotionType : PromotionType.campaign, viewName: "Campaigns"}
}
