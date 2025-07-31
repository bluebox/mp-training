import React from "react";
import { CAMPAIGN, COMPLIMENTARY_ROLES, MIC_ROLES, POPUP_ROLES, RP_ROLES } from "../constants/MarketingRoles";
import { PromotionStatus, PromotionType } from "../constants/PromotionConstants";
import { CAMPAIGN_URLS, COMPLIMENTARY_URLS, MIC_URLS, POPUP_URLS, RP_URLS } from "../constants/UrlConstants";
import Validate from "../helpers/Validate";

export const API_URL = '/marketing/';

export const POPUP = 'popup/';

export const REQUEST_TYPE = {
    GET: 'GET',
    POST: 'POST',
    PATCH: 'PATCH',
    PUT: 'PUT',
    DELETE: 'DELETE'
};


const CONTENT_TYPE = {
	JSON: 'application/json; charset=utf-8',
	MULTIPARTFILE: 'multipart/form-data',
};

export const getStatusSpanInfo = (status) => {
    switch(status) {
        case PromotionStatus.inActive :
            return <span className="badge badge-pending">Created</span>;
        case PromotionStatus.active :
            return <span className="badge badge-approved">Approved</span>;
        case PromotionStatus.rejected :
            return <span className="badge badge-rejected">Rejected</span>;
        default :
            return null; 
    }
}

export const getChanelsOnApplicableType = (applicableType, helpers) =>{
    let channelTypes = [];
    channelTypes.push(helpers.createOption("web","WEB","2","false"));
    if(applicableType == "PHARMACY" || applicableType === '5'){
        channelTypes.push(helpers.createOption("pos","POS","1","false"));
        channelTypes.push(helpers.createOption("crm","CRM","3","false"));
        channelTypes.push(helpers.createOption("mobile","Mobile","4","false"));
        channelTypes.push(helpers.createOption("posweb","POS WEB","5","false"));
    } else if(applicableType == "PATHLABS" || applicableType === '6'){
        channelTypes.push(helpers.createOption("crm","CRM","3","false"));
        channelTypes.push(helpers.createOption("mobile","Mobile","4","false"));
    }
    return channelTypes;
}

export const getCampaignTypeOptions = (applicableType, helpers, url, promotionType ) =>{

    let campaignTypes = [];
    if(promotionType === PromotionType.multiItem) {
        campaignTypes.push(helpers.createOption("multi-item-discount-fixed-sp","Multi Item discount - Fixed SP","8","false"));
        campaignTypes.push(helpers.createOption("multi-item-discount-buy-x-pay-for-y","Multi Item discount - Buy 'X' Pay for 'Y' (Y < X)","9","false"));
        campaignTypes.push(helpers.createOption("multi-item-discount-bundle","Multi Item discount - Bundle","10","false"));
    } else if(promotionType === PromotionType.regular){
        campaignTypes.push(helpers.createOption("invoice","Invoice","I","false"));
        campaignTypes.push(helpers.createOption("category","Category","C","false"));
    } else if(promotionType === PromotionType.campaign){
        campaignTypes.push(helpers.createOption("special-discount","Special Discount","3","false"));
        if(applicableType == "PHARMACY" || applicableType === '5'){
            campaignTypes.push(helpers.createOption("supplement-quantity","Supplement Quantity","5","false"));
            campaignTypes.push(helpers.createOption("supplement-item","Supplement Item","6","false"));
            campaignTypes.push(helpers.createOption("promotion-addOn","Promotion Addon","11","false"));
        }
    }
    return campaignTypes;
}

export const getApplicableTypeName = (applicableType) => {
    if(applicableType == '5') {
        return "PHARMACY";
    } else if(applicableType == '6') {
        return "PATHLABS";
    } else if(applicableType == '7') {
        return "LENS";
    }
}

export const isUrlCampaign = (url) => {
    return (url === CAMPAIGN_URLS.createCampaign || url === CAMPAIGN_URLS.listCamapign || url === CAMPAIGN_URLS.editCampaign || url === CAMPAIGN_URLS.cloneCampaign || url === CAMPAIGN_URLS.viewCampaign || url === CAMPAIGN_URLS.approveCampaign || url === CAMPAIGN_URLS.closeCampaign );
}

export const isPopupTemplateUrl = (url) => {
    return (url === POPUP_URLS.createTemplate || url === POPUP_URLS.viewTemplates || url === POPUP_URLS.editTemplate);
}

export const isPopupConfigurationUrl = (url,params) => {
    return (url === POPUP_URLS.createConfiguration || url === POPUP_URLS.editConfiguration || url === POPUP_URLS.viewConfigurations || url === POPUP_URLS.cloneConfiguration || url === (POPUP_URLS.editConfiguration+'/'+params?.configId) || url === (`${POPUP_URLS.editConfiguration}/request/`+params?.reqId) || url === `${POPUP_URLS.viewConfiguration}/request/${params?.reqId}`);
}
export const isUrlMultiItemcampaign = (url) => {
    return (url === MIC_URLS.createCampaign || url === MIC_URLS.listCamapign || url === MIC_URLS.editCampaign || url === MIC_URLS.cloneCampaign || url === MIC_URLS.viewCampaign || url === MIC_URLS.closeCampaign || url === MIC_URLS.approveCampaign);
}

export const isUrlRegularPromotion = (url) => {
    return (url === RP_URLS.createCampaign || url === RP_URLS.listCamapign || url === RP_URLS.editCampaign || url === RP_URLS.cloneCampaign || url === RP_URLS.viewCampaign || url === RP_URLS.closeCampaign || url === RP_URLS.approveCampaign);
}

export const isUrlComplimentaryPromotion = (url) => {
    return (url === COMPLIMENTARY_URLS.createCampaign || url === COMPLIMENTARY_URLS.listCamapign || url === COMPLIMENTARY_URLS.editCampaign || url === COMPLIMENTARY_URLS.cloneCampaign || url === COMPLIMENTARY_URLS.viewCampaign || url === COMPLIMENTARY_URLS.closeCampaign || url === COMPLIMENTARY_URLS.approveCampaign);
}

export const isActiveMenu = (selectedMenu, itemUrl, params) => {
    if(isUrlMultiItemcampaign(selectedMenu)) {
        return ((selectedMenu === MIC_URLS.createCampaign && selectedMenu === itemUrl) || (selectedMenu != MIC_URLS.createCampaign && itemUrl === MIC_URLS.listCamapign))
    } else if (isUrlRegularPromotion(selectedMenu)) {
        return ((selectedMenu === RP_URLS.createCampaign && selectedMenu === itemUrl) || (selectedMenu != RP_URLS.createCampaign && itemUrl === RP_URLS.listCamapign));
    } else if (isUrlCampaign(selectedMenu)) {
        return ((selectedMenu === CAMPAIGN_URLS.createCampaign && selectedMenu === itemUrl) || (selectedMenu != CAMPAIGN_URLS.createCampaign && itemUrl === CAMPAIGN_URLS.listCamapign));
    } else if (isUrlComplimentaryPromotion(selectedMenu)) {
        return ((selectedMenu === COMPLIMENTARY_URLS.createCampaign && selectedMenu === itemUrl) || (selectedMenu != COMPLIMENTARY_URLS.createCampaign && itemUrl === COMPLIMENTARY_URLS.listCamapign));
    } else if (isPopupTemplateUrl(selectedMenu)) {
        return ((selectedMenu === POPUP_URLS.createTemplate && selectedMenu === itemUrl) || (POPUP_URLS.editTemplate === selectedMenu && itemUrl === POPUP_URLS.viewTemplateRequests) || (![POPUP_URLS.createTemplate,POPUP_URLS.editTemplate].includes(selectedMenu) && itemUrl === POPUP_URLS.viewTemplates));
    } else if (isPopupConfigurationUrl(selectedMenu,params)) {
        return (([POPUP_URLS.createConfiguration, POPUP_URLS.cloneConfiguration].includes(selectedMenu) && itemUrl === POPUP_URLS.createConfiguration) || ([POPUP_URLS.viewConfigurations, POPUP_URLS.editConfiguration].includes(selectedMenu) && POPUP_URLS.viewConfigurations === itemUrl) || (POPUP_URLS.editConfiguration+'/'+params?.configId === selectedMenu && POPUP_URLS.viewConfigurations === itemUrl ) || (`${POPUP_URLS.editConfiguration}/request/`+params?.reqId === selectedMenu && POPUP_URLS.viewConfigurationRequests === itemUrl) || (`${POPUP_URLS.viewConfiguration}/${params?.reqId}` == selectedMenu && POPUP_URLS.viewConfigurationRequests === itemUrl));
    }else {
        return (selectedMenu === itemUrl);
    }
}

export const getPromotionTypeByUrl = (url) => {
    let promotionType = '';
    if(isUrlRegularPromotion(url)){
        promotionType =  PromotionType.regular
    } else if(isUrlMultiItemcampaign(url)) {
        promotionType = PromotionType.multiItem
    } else if(isUrlCampaign(url)) {
    	promotionType = PromotionType.campaign
    } else if(isUrlComplimentaryPromotion(url)) {
        promotionType = PromotionType.complimentary
    }
    return promotionType;
}

export const downloadExcelData =  async (excelDataItem, params, campaignId, setAlertContent, ALERT_TYPE, setLoading) => {
    console.log("excel data Item", excelDataItem, campaignId)
    let url = excelDataItem.url;    
    const fileName = excelDataItem.fileName;
    setLoading(true);
    if (Validate().isNotEmpty(url)) {
        downloadUrl(url, fileName, campaignId);
    } else {
        const response =  await excelDataItem.service(params).catch(error => {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error})
        });
        if (Validate().isNotEmpty(response) && response.status === 200) {
            const blob = new Blob([response.data], { type: 'application/force-download' });
            const blobUrl = window.URL.createObjectURL(blob);
            excelDataItem.url = blobUrl
            downloadUrl(blobUrl, fileName, campaignId);
        } else {
            setLoading(false);
            setAlertContent({ alertType: ALERT_TYPE.ERROR, alertMessage: "Unable to download the file" });
            return false;
        }
    }
    setLoading(false);
}

export const downloadUrl = (url, fileName, campaignId) => {
    const a = document.createElement('a');
    a.href = url;
    a.download = Validate().isEmpty(campaignId) ? `${fileName}.xls` : `${fileName}-${campaignId}.xlsx`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
}

export const downLoadTemplate = (templateData, campaignId) => { 
    downloadUrl(templateData.path, templateData.fileName, campaignId);
}

export const getRegionsName = (length) => {
    switch(length) {
      case "12":
        return "Stores";
      case "7":
        return "Cities";
      case "4":
        return "States";
      case "2":
        return "Countries";
      default:
        return "";
    }
};

export const regionFilterMapWithOutStores = { "2" : "Countries", "4" : "States", "7":"Cities"};
export const regionFilterMapWithStores = {...regionFilterMapWithOutStores, "12": "Stores"};

export const options = {
    weekday: 'short', 
    year: 'numeric', 
    month: 'short', 
    day: '2-digit', 
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
    timeZoneName: 'short'
  };

export const ERROR_MESSAGES = {
    UNABLE_TO_PROCESS: "Unable to process your request",
    EMPTY_DATE_RANGE: "FromDate and ToDate should not be Empty",
    NOT_VALID_DATE_RANGE: "FromDate should be less than to ToDate",
    SUPPORTING_FILES_REQUIRED: "Supporting files required",
    UNABLE_TO_DOWNLOAD_FILE: "Unable to download file"
  };

const CONFIG ={
    API: {
        GET_CONFIGURATION_ON_REQUEST_ID : {
            PATH : API_URL  + 'get-popup-configuration-on-requestId',
            HEADER : {
                method: REQUEST_TYPE.GET
            } 
        },
        GET_CONFIGURATION_BY_CONFIG_ID : {
            PATH : API_URL + POPUP + 'get-configuration-by-configId',
            HEADER : {
                method : REQUEST_TYPE.GET
            }
        },
        APPROVE_CONFIGURATION: {
            PATH : API_URL  + 'approve-popup-config-request',
            HEADER : {
                method: REQUEST_TYPE.POST
            }   
        },
        REJECT_CONFIGURATION : {
            PATH : API_URL  + 'reject-popup-config-request',
            HEADER : {
                method: REQUEST_TYPE.POST
            }  
        },
        GET_LOGGED_IN_USER_DETAIL: {
            PATH: API_URL + 'user-login-details',
            HEADER: {
                method: REQUEST_TYPE.GET,
                contentType: CONTENT_TYPE.JSON,
            }
        },
        GET_COUNTRIES: {
            PATH: API_URL + 'get-countries',
            HEADER: {
                method: REQUEST_TYPE.GET,
            }
        },
        GET_STATES: {
            PATH: API_URL + 'get-states',
            HEADER: {
                method: REQUEST_TYPE.GET,
            }
        },
        GET_CITIES: {
            PATH: API_URL + 'get-cities',
            HEADER: {
                method: REQUEST_TYPE.GET,
            }
        },
        GET_STORES: {
            PATH: API_URL + 'get-stores',
            HEADER: {
                method: REQUEST_TYPE.GET,
            }
        },
        GET_PATHLAB_STORES: {
            PATH: API_URL + 'get-pathlab-stores',
            HEADER: {
                method: REQUEST_TYPE.GET,
            }
        },
        GET_STORE_NAMES: {
            PATH: API_URL + 'get-store-names',
            HEADER: {
                method: "POST_PARAMS",
            }
        },
        CREATE_CAMPAIGN: {
            PATH: API_URL + 'create-multi-item-campaign',
            HEADER: {
                method: REQUEST_TYPE.POST,
                contentType: CONTENT_TYPE.MULTIPARTFILE
            }
        },
        EDIT_CAMPAIGN: {
            PATH: API_URL + 'edit-multi-item-campaign',
            HEADER: {
                method: REQUEST_TYPE.POST,
                contentType: CONTENT_TYPE.MULTIPARTFILE
            }
        },
        CLONE_CAMPAIGN: {
            PATH: API_URL + 'create-multi-item-campaign',
            HEADER: {
                method: REQUEST_TYPE.POST,
                contentType: CONTENT_TYPE.MULTIPARTFILE
            }
        },
        GET_MULTI_STORES_EXCEL: {
            PATH: API_URL + 'get-multi-stores-download',                
        },
        APPROVE_CAMPAIGN: {
            PATH: API_URL + 'approve-multi-item-campaign',
            HEADER: {
                method: REQUEST_TYPE.POST
            }
        },
        SEARCH_MULTI_ITEM_CAMPAIGN: {
            PATH: API_URL + 'get-multiItem-campaign',
            HEADER: {
                method: REQUEST_TYPE.POST
            }
        },
        GET_MULTI_ITEM_CAMPAIGN_BY_CAMPAIGNID : {
            PATH: API_URL + 'get-multi-item-campaign-by-campaignid',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        GET_META_INFO_FORM : {
            PATH: API_URL + 'get-meta-info-form',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        GET_MI_SLAB_FORM : {
            PATH: API_URL + 'get-multiitem-slab-form',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        GET_REGIONS_FORM : {
            PATH: API_URL + 'get-regions-form',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        GET_PRODUCTIDS_FOR_CAMPAIGNID: {
            PATH: API_URL + 'get-product-excel-download',
        },
        GET_CUSTOMERSIDS_FOR_CAMPAIGNID: {
            PATH: API_URL + 'get-customer-excel-download',
        },
        GET_CUSTOMERIDS_FOR_REGULAR_PROMOTION: {
            PATH: API_URL + 'get-regular-promotion-customer-excel-download'
        },
        GET_LOYALTY_TYPES: {
            PATH: API_URL + 'get-loyalty-types',
            HEADER: {
                method:REQUEST_TYPE.GET
            }
        },
        GET_REGIONS_DATA_GRID: {
            PATH: API_URL + 'get-regions-data-grid',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        VALIDATE_CAMPAIGN_NAME: {
            PATH:API_URL + 'validate-campaign-name',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        VALIDATE_REGULAR_PROMOTION_NAME: {
            PATH:API_URL + 'validate-regular-promotion-name',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        GET_CAMPAIGN_INFO_FORM: {
            PATH:API_URL + 'get-campaign-info-form',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        UPDATE_TO_DATE: {
            PATH: API_URL + 'update-multi-item-campaign-to-date',
            HEADER: {
                method: REQUEST_TYPE.POST
            }
        },
        UPDATE_IN_ACTIVE_TO_DATE: {
            PATH: API_URL + 'update-inactive-multi-item-campaign-to-date',
            HEADER: {
                method: REQUEST_TYPE.POST
            }
        },
        REJECT_MULTI_ITEM_CAMPAIGN: {
            PATH: API_URL + 'reject-multi-item-campaign',
            HEADER: {
                method: REQUEST_TYPE.POST
            }
        },
        UPDATE_REGULAR_PROMOTION_TO_DATE: {
            PATH: API_URL + 'update-regular-promotion-to-date',
            HEADER: {
                method: REQUEST_TYPE.POST
            }
        },
        UPDATE_IN_ACTIVE_REGULAR_PROMOTION_TO_DATE: {
            PATH: API_URL + 'update-inactive-regular-promotion-to-date',
            HEADER: {
                method: REQUEST_TYPE.POST
            }
        },
        REJECT_REGULAR_PROMOTION: {
            PATH: API_URL + 'reject-regular-promotion' ,
            HEADER : {
                method: REQUEST_TYPE.POST
            }
        },
        POPUP: {
            CREATE_TEMPLATE : {
                PATH : API_URL + POPUP + 'create-template',
                HEADER : {
                    method: REQUEST_TYPE.POST
                }
            },
            CHECK_TEMPLATE_NAME : {
                PATH : API_URL + 'check-if-template-name-exists',
                HEADER : {
                    method: REQUEST_TYPE.GET
                }
            },
            GET_TEMPLATES : {
                PATH : API_URL + POPUP + 'get-templates',
                HEADER : {
                    method: REQUEST_TYPE.POST
                }
            },
            GET_TEMPLATE : {
                PATH : API_URL + POPUP + 'get-template',
                HEADER : {
                    method: REQUEST_TYPE.GET
                }
            },
            GET_TEMPLATE_REQUESTS : {
                PATH : API_URL + 'get-popup-template-requests',
                 HEADER : {
                    method: REQUEST_TYPE.POST
                }
            },
            GET_CONFIGURATION_REQUESTS : {
                PATH : API_URL + 'get-popup-configuration-requests',
                HEADER : {
                    method: REQUEST_TYPE.POST
                }
            },
            UPDATE_TEMPLATE: {
                PATH: API_URL + POPUP + 'update-popup-template',
                HEADER: {
                    method: REQUEST_TYPE.POST
                }
            },
            CREATE_CONFIGURATION: {
                PATH: API_URL  + POPUP + 'create-configuration',
                HEADER: {
                    method: REQUEST_TYPE.POST,
                    contentType: CONTENT_TYPE.MULTIPARTFILE,
                }
            },
            CHECK_CONFIGURATION_NAME: {
                PATH: API_URL + POPUP + 'check-configuration-name',
                HEADER: {
                    method: REQUEST_TYPE.GET
                }
            },
            GET_CONFIGURATIONS: {
                PATH: API_URL + POPUP + 'get-configurations',
                HEADER: {
                    method: REQUEST_TYPE.POST
                }
            },
            UPDATE_CONFIGURATIONS:{
                PATH: API_URL + POPUP + 'update-popup-configuration',
                HEADER: {
                    method: REQUEST_TYPE.POST,
                    contentType: CONTENT_TYPE.MULTIPARTFILE,
                }
            },
            UPDATE_CONFIGURATION:{
                PATH: API_URL + POPUP + 'update-configuration',
                HEADER: {
                    method: REQUEST_TYPE.POST,
                }
            },
            IS_TEMPLATE_ACTIVE : {
                PATH: API_URL + POPUP + 'is-template-active',
                HEADER: {
                    method: REQUEST_TYPE.GET,
                }
            },
            GET_REGIONS_EXCEL: {
                PATH: API_URL + POPUP + 'get-regions-excel-download',
                HEADER: {
                    method: REQUEST_TYPE.GET
                },
                RESPONSE_TYPE: 'blob'
            },
            GET_PRODUCT_IDS_EXCEL : {
                PATH: API_URL + POPUP + 'get-productIds-excel-download',
                HEADER: {
                    method: REQUEST_TYPE.GET
                },
                RESPONSE_TYPE: 'blob'
            },
            GET_PARAMETERS : {
                PATH : API_URL + POPUP + 'get-Parameters',
                HEADER : {
                    method: REQUEST_TYPE.GET
                }
            },
            CREATE_POPUP_TEMPLATE_REQUEST : {
                PATH : API_URL + 'create-popup-template-request',
                HEADER : {
                    method: REQUEST_TYPE.POST
                }
            },
            UPDATE_POPUP_TEMPLATE_REQUEST : {
                PATH : API_URL + 'update-popup-template-request',
                HEADER : {
                    method: REQUEST_TYPE.POST,
                }
            },
            APPROVE_POPUP_TEMPLATE_REQUEST : {
                PATH : API_URL + 'approve-popup-template-request',
                HEADER : {
                    method: REQUEST_TYPE.POST,
                    contentType: CONTENT_TYPE.MULTIPARTFILE
                } 
            },
            REJECT_POPUP_TEMPLATE_REQUEST : {
                PATH : API_URL + 'reject-popup-template-request',
                HEADER : {
                    method: REQUEST_TYPE.POST,
                    contentType: CONTENT_TYPE.MULTIPARTFILE
                } 
            },
            GET_TEMPLATE_REQUEST : {
                PATH : API_URL + 'get-template-request',
                HEADER : {
                    method: REQUEST_TYPE.GET,
                }
            },
            GET_CUSTOMERIDS_EXCEL : {
                PATH: API_URL + POPUP + 'get-customerIds-excel-download',
                HEADER: {
                    method: REQUEST_TYPE.GET
                },
                RESPONSE_TYPE: 'blob'
            },
        },
        CREATE_POPUP_CONFIG_REQUEST : {
            PATH : API_URL + 'create-popup-config-request',
            HEADER : {
                method: REQUEST_TYPE.POST
            }
        },
        UPDATE_POPUP_CONFIG_REQUEST:{
            PATH : API_URL + 'update-popup-config-request',
            HEADER : {
                method: REQUEST_TYPE.POST
            }
        },
        CAMPAIGN : {
            CREATE_CAMPAIGN: {
                PATH: API_URL + 'create-campaign',
                HEADER: {
                    method: REQUEST_TYPE.POST,
                    contentType: CONTENT_TYPE.MULTIPARTFILE
                }
            },
            CREATE_MULTIPLE_CAMPAIGN: {
                PATH: API_URL + 'create-multiple-campaigns  ',
                HEADER: {
                    method: REQUEST_TYPE.POST,
                    contentType: CONTENT_TYPE.MULTIPARTFILE
                }
            },
            EDIT_CAMPAIGN: {
                PATH: API_URL + 'edit-campaign',
                HEADER: {
                    method: REQUEST_TYPE.POST,
                    contentType: CONTENT_TYPE.MULTIPARTFILE
                }
            },
            CLONE_CAMPAIGN: {
                PATH: API_URL + 'create-campaign',
                HEADER: {
                    method: REQUEST_TYPE.POST,
                    contentType: CONTENT_TYPE.MULTIPARTFILE
                }
            },
            APPROVE_CAMPAIGN: {
                PATH: API_URL + 'approve-campaign',
                HEADER: {
                    method: REQUEST_TYPE.POST
                }
            },
            UPDATE_TO_DATE: {
                PATH: API_URL + 'update-campaign-to-date',
                HEADER: {
                    method: REQUEST_TYPE.POST
                }
            },
            UPDATE_INACTIVE_CAMPAIGN_TO_DATE: {
                PATH: API_URL + 'update-inactive-campaign-to-date',
                HEADER: {
                    method: REQUEST_TYPE.POST
                }
            },
            REJECT_CAMPAIGN: {
                PATH: API_URL + 'reject-campaign',
                HEADER: {
                    method: REQUEST_TYPE.POST
                }
            },
            SEARCH_CAMPAIGN: {
                PATH: API_URL + 'get-campaign-list',
                HEADER: {
                    method: REQUEST_TYPE.POST
                }
            },
            GET_CAMPAIGN_BY_CAMPAIGNID : {
                PATH: API_URL + 'get-campaign-by-campaignid',
                HEADER: {
                    method: REQUEST_TYPE.GET
                }
            },
            GET_PRODUCTIDS_FOR_CAMPAIGNID: {
                PATH: API_URL + 'get-product-excel-download-for-campaign',
            },
            GET_CUSTOMERSIDS_FOR_CAMPAIGNID: {
                PATH: API_URL + 'get-customer-excel-download-for-campaign',
            },
            VALIDATE_CAMPAIGN_NAME: {
                PATH:API_URL + 'is-campaign-name-available',
                HEADER: {
                    method: REQUEST_TYPE.GET
                }
            },
            VALIDATE_COUPON_NAME: {
                PATH:API_URL + 'validate-regular-promotion-couponcode',
                HEADER: {
                    method: REQUEST_TYPE.GET
                }
            },
            GET_STORES_EXCEL: {
                PATH: API_URL + 'get-campaign-stores-download',                
            }
        },
        CREATE_REGULAR_PROMOTION: {
            PATH: API_URL + 'create-regular-promotion',
            HEADER: {
                method: REQUEST_TYPE.POST,
                contentType: CONTENT_TYPE.MULTIPARTFILE
            }
        },
        EDIT_REGULAR_PROMOTION: {
            PATH: API_URL + 'edit-regular-promotion',
            HEADER: {
                method: REQUEST_TYPE.POST,
                contentType: CONTENT_TYPE.MULTIPARTFILE
            }
        },
        CLONE_REGULAR_PROMOTION: {
            PATH: API_URL + 'create-regular-promotion',
            HEADER: {
                method: REQUEST_TYPE.POST,
                contentType: CONTENT_TYPE.MULTIPARTFILE
            }
        },
        SEARCH_REGULAR_PROMOTION: {
            PATH: API_URL + 'get-regular-promotion',
            HEADER: {
                method: REQUEST_TYPE.POST
            }
        },
        GET_REGULAR_PROMOTION_BY_PROMOTIONID : {
            PATH: API_URL + 'get-regular-promotion-by-promotionid',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        APPROVE_REGULAR_PROMOTION : {
            PATH: API_URL + 'approve-regular-promotion',
            HEADER: {
                method: REQUEST_TYPE.POST
            }
        },
        GET_INVOICE_CATEGORY_TYPES: {
            PATH: API_URL + 'get-invoice-category-types',
            HEADER: {
                method:REQUEST_TYPE.GET
            }
        },
        GET_SLAB_GROUP_DETAILS: {
            PATH: API_URL + 'get-slab-group-details',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        CHECK_SLAB_GROUP_ASSIGN_OR_NOT: {
            PATH: API_URL + 'check-slab-group-assign-or-not',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        SAVE_SLAB_DETAILS: {
            PATH: API_URL + 'save-slab-details',
            HEADER: {
                method: REQUEST_TYPE.POST
            }
        },
        VALIDATE_COUPON_NAME: {
            PATH:API_URL + 'validate-regular-promotion-couponcode',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        GET_REGULAR_STORES_EXCEL: {
            PATH: API_URL + 'get-regular-stores-download',                
        },
        CLEAR_CACHE: {
            PATH: API_URL + 'update-promotions',
            HEADER: {
                method: REQUEST_TYPE.GET
            }
        },
        COMPLIMENTARY : {
            CREATE_PROMOTION : {
                PATH: API_URL + 'create-complimentary-gift-promotion',
                HEADER: {
                    method: REQUEST_TYPE.POST,
                    contentType: CONTENT_TYPE.MULTIPARTFILE
                }
            },
            EDIT_PROMOTION: {
                PATH: API_URL + 'edit-complimentary-gift-promotion',
                HEADER: {
                    method: REQUEST_TYPE.POST,
                    contentType: CONTENT_TYPE.MULTIPARTFILE
                }
            },
            CLONE_PROMOTION: {
                PATH: API_URL + 'create-complimentary-gift-promotion',
                HEADER: {
                    method: REQUEST_TYPE.POST,
                    contentType: CONTENT_TYPE.MULTIPARTFILE
                }
            },
            APPROVE_PROMOTION: {
                PATH: API_URL + 'approve-complimentary-promotion' ,
                HEADER: {
                    method: REQUEST_TYPE.POST
                }
            },
            UPDATE_TO_DATE: {
                PATH: API_URL + 'update-complimentary-promotion-to-date',
                HEADER: {
                    method: REQUEST_TYPE.POST
                }
            },
            UPDATE_INACTIVE_PROMOTION_TO_DATE: {
                PATH: API_URL + 'update-inactive-complimentary-promotion-to-date' ,
                HEADER: {
                    method: REQUEST_TYPE.POST
                }
            },
            REJECT_PROMOTION: {
                PATH: API_URL + 'reject-complimentary-promotion',
                HEADER: {
                    method: REQUEST_TYPE.POST
                }
            },
            SEARCH_PROMOTION: {
                PATH: API_URL + 'get-complimentary-promotion-list',
                HEADER: {
                    method: REQUEST_TYPE.POST
                }
            },
            GET_PROMOTION_BY_COMPLIMENTARY_ID : {
                PATH: API_URL + 'get-complimentary-promotion-by-complimentaryId',
                HEADER: {
                    method: REQUEST_TYPE.GET
                }
            },
            GET_COMPLIMENTARY_PRODUCTS: {
                PATH: API_URL + 'get-complimentary-gift-products',
            },
            GET_REFERENCE_CUSTOMERS: {
                PATH: API_URL + 'get-complimentary-reference-customer-download',                
            },
            VALIDATE_PROMOTION_NAME: {
                PATH:API_URL + 'validate-complimentary-promotion-name',
                HEADER: {
                    method: REQUEST_TYPE.GET
                }
            },
            VALIDATE_SLAB_GRP_NAME: {
                PATH:API_URL + 'validate-complimentary-slab-name',
                HEADER: {
                    method: REQUEST_TYPE.GET
                }
            },
            GET_STORES_EXCEL: {
                PATH: API_URL + 'get-complimentary-stores-download',                
            }
        }
    }
}

export default CONFIG;

export const MARKETING_NAVIGATION_MENU = [
    {
        displayName: "Complimentary Gift",
        id: "complimentaryPromotion",
        icon: <img src="" alt="campaign icon" />,
        role: COMPLIMENTARY_ROLES.allRoles,
        url:null,
        subLinks: [
            {
	            displayName: "Create",
                id: "create-complimentary-promotion",
                url: COMPLIMENTARY_URLS.createCampaign,
                subLinks:[],
                role: COMPLIMENTARY_ROLES.createRoles
            },
            {
	            displayName: "Dashboard",
                id: "list-complimentary-promotion",
                url: COMPLIMENTARY_URLS.listCamapign,
                subLinks:[],
                role: COMPLIMENTARY_ROLES.listRoles
            }
        ]
    },
    {
        displayName: "Multi Item Campaign",
        id: "multiItemCampaign",
        icon: <img src="" alt="campaign icon" />,
        role: MIC_ROLES.allRoles,
        url:null,
        subLinks: [
            {
                displayName: "Create",
                id: "create-campaign",
                url: MIC_URLS.createCampaign,
                subLinks:[],
                role: MIC_ROLES.createRoles
            },
            {
                displayName: "Dashboard",
                id: "list-campaign",
                url: MIC_URLS.listCamapign,
                subLinks:[],
                role: MIC_ROLES.listRoles
            }
        ]
    },
    {
        displayName: "Campaign",
        id: "campaign",
        icon: <img src="" alt="campaign icon" />,
        role: CAMPAIGN.allRoles,
        url:null,
        subLinks: [
            {
                displayName: "Create",
                id: "create-campaign-1",
                url: CAMPAIGN_URLS.createCampaign,
                subLinks:[],
                role: CAMPAIGN.createRoles
            },
            {
                displayName: "Dashboard",
                id: "list-campaign",
                url: CAMPAIGN_URLS.listCamapign,
                subLinks:[],
                role: CAMPAIGN.listRoles
            }
        ]
    },
    {
        displayName: "Regular Promotion",
        id: "campaignPromotion",
        icon: <img src="" alt="campaign icon" />,
        role: RP_ROLES.allRoles,
        url:null,
        subLinks: [
            {
                displayName: "Create",
                id: "create-promotion",
                url: RP_URLS.createCampaign ,
                subLinks:[],
                role: RP_ROLES.createRoles
            },
            {
                displayName: "Dashboard",
                id: "list-promotion",
                url: RP_URLS.listCamapign,
                subLinks:[],
                role: RP_ROLES.listRoles
            }
        ]
    },
    {
        displayName: "Popup Configuration",
        id: "popupConfiguration",
        icon: <img src="" alt="campaign icon" />,
        role: POPUP_ROLES['allRoles'],
        url:null,
        subLinks: [
            {
                displayName: "Template",
                id: "template",
                role: POPUP_ROLES['templateRoles'],
                url: null,
                subLinks:[
                    {
                        displayName: "Create",
                        id: "create-template",
                        url: POPUP_URLS.createTemplate,
                        role: POPUP_ROLES['createTemplate'],
                        subLinks: []
                    },
                    {
                        displayName: "Dashboard",
                        id: "template-dashboard",
                        url: POPUP_URLS.viewTemplates,
                        role: POPUP_ROLES['viewTemplates'],
                        subLinks: []
                    },
                    {
                        displayName: "Requests Dashboard",
                        id: "template-requests-dashboard",
                        url: POPUP_URLS.viewTemplateRequests,
                        role: POPUP_ROLES['viewTemplates'],
                        subLinks: []
                    }
                ]
            },
            {
                displayName: "Configuration",
                id: "popup-configuration",
                url: null,
                role: POPUP_ROLES['configRoles'],
                subLinks:[
                    {
                        displayName: "Create",
                        id: "create-configuration",
                        url: POPUP_URLS.createConfiguration,
                        role: POPUP_ROLES['createConfiguration'],
                        subLinks: []
                    },
                    {
                        displayName: "Dashboard ",
                        id: "popup-dashboard",
                        url: POPUP_URLS.viewConfigurations,
                        role: POPUP_ROLES['viewConfigurations'],
                        subLinks: []
                    },
                    {
                        displayName: "Requests Dashboard",
                        id: "configuration-requests-dashboard",
                        url: POPUP_URLS.viewConfigurationRequests,
                        role: POPUP_ROLES['viewConfigurations'],
                        subLinks: []
                    }
                ]
            }
        ]
    },
]