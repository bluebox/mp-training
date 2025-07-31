import customersUploadRemoveTemplate from '../templates/CUSTOMERS_UPLOAD_REMOVE.xls'
import itemsUploadRemoveTemplate from '../templates/ITEMS_UPLOAD_REMOVE.xls'

import lensItemsUploadTemplate from '../templates/LENS_PRODUCT_UPLOAD_TEMPLATE.xls'
import pharmacyNonSDItemsUploadTemplate from '../templates/NON_SD_PRODUCT_UPLOAD_TEMPLATE.xls'
import pathlabsNonSpecialityBasedItemsUploadTemplate from '../templates/NON_SPECIALITY_BASED_PRODUCT_UPLOAD_TEMPLATE.xls'
import pharmacySDItemsUploadTemplate from '../templates/SD_PRODUCT_UPLOAD_TEMPLATE.xls'
import pathlabsSpecialityBasedItemsUploadTemplate from '../templates/SPECIALITY_BASED_PRODUCT_UPLOAD_TEMPLATE.xls'

import multPharmacySDItemsUploadTemplate from '../templates/MULT_SD_PRODUCT_UPLOAD_TEMPLATE.xls'
import multPathlabsSpecialityBasedItemsUploadTemplate from '../templates/MULT_SPECIALITY_BASED_PRODUCT_UPLOAD_TEMPLATE.xls'
import multPharmacyNonSDItemsUploadTemplate from '../templates/MULT_NON_SD_PRODUCT_UPLOAD_TEMPLATE.xls'
import multPathlabsNonSpecialityBasedItemsUploadTemplate from '../templates/MULT_NON_SPECIALITY_BASED_PRODUCT_UPLOAD_TEMPLATE.xls'

import giftProductsTemplate from '../templates/CP_PRODUCTS.xls'
import giftProductsRemoveTemplate from '../templates/CP_REMOVE_PRODUCTS.xls'

import referenceEdit from '../templates/CP_REF_EDIT.xls'
import referenceWithCustomerEdit from '../templates/CP_REF_WITH_CUST_EDIT.xls'

import CampaignService from '../services/CampaignService'
import ComplimentaryService from '../services/ComplimentaryService'
import MarketingService from '../services/MarketingService'

export const STORE_EXCEL_DATA = {
   multiItem :  { title: "Existing Stores", url: "", service: MarketingService().getMultiItemStoresExcel, fileName : "Multi-Item-Campaign-Stores" },
   regular :  { title: "Existing Stores", url: "", service: MarketingService().getRegularPromotionStoresExcel, fileName : "Regular-Promotion-Stores" },
   campaign :   { title: "Existing Stores", url: "", service: CampaignService().getStoresExcel, fileName : "Campaign-Stores" },
   complimentary :  { title: "Existing Stores", url: "", service: ComplimentaryService().getStoresExcel, fileName : "Complimentary-Promotion-Stores" },
}

/*  Multi Item Campaign */
export const MIC_DOWNLOAD_TEMPLATES = {
   items : { title : "Items Upload/Remove", path : itemsUploadRemoveTemplate, fileName : "Multi-Item-Campaign-Items-Template"},
   customers : { title : "Customers Upload/Remove", path : customersUploadRemoveTemplate, fileName : "Multi-Item-Campaign-Customers-Template"},
}

export const MIC_EXCEL_DATA = {
   items: { title: "Existing Items", url: "", service: MarketingService().getProductIds, fileName : "Multi-Item-Campaign-Items" },
   customers: { title: "Existing Customers", url: "" , service: MarketingService().getCustomerIds, fileName : "Multi-Item-Campaign-Customers" }
};

/* Regular Promotion */
export const RP_DOWNLOAD_TEMPLATES = {
   customers : { title : "Customers", path : customersUploadRemoveTemplate, fileName : "Regular-Promotion-Customers-Template"}
}
export const RP_EXCEL_DATA = {
   customers: { title: "Existing Customer Details", url: "" , service: MarketingService().getRegularPromotionCustomerIds, fileName : "Regular-Promotion-Customers" }
};

/*  Campaign */
export const CAMPAIGN_DOWNLOAD_TEMPLATES = {
   customers : { title : "Customers Upload/Remove", path : customersUploadRemoveTemplate, fileName : "Campaign-Customers-Template"},
   
   pharmaSDItems : { title : "Upload Campaign Details", path : pharmacySDItemsUploadTemplate, fileName : "Campaign-Detail-Upload-Template"},
   multiplePharmaSDItems : { title : "Upload Multiple Campaign Details", path : multPharmacySDItemsUploadTemplate, fileName : "Multiple-Campaign-Detail-Upload-Template"},

   pharmaNonSDItems : { title : "Upload Campaign Details", path : pharmacyNonSDItemsUploadTemplate, fileName : "Campaign-Detail-Upload-Template"},
   multiplePharmaNonSDItems : { title : "Upload Multiple Campaign Details", path : multPharmacyNonSDItemsUploadTemplate, fileName : "Multiple-Campaign-Detail-Upload-Template"},

   labsSBItems : { title : "Upload Campaign Details", path : pathlabsSpecialityBasedItemsUploadTemplate, fileName : "Campaign-Detail-Upload-Template"},
   multipleLabsSBItems : { title : "Upload Multiple Campaign Details", path : multPathlabsSpecialityBasedItemsUploadTemplate, fileName : "Multiple-Campaign-Detail-Upload-Template"},

   labsNonSBItems : { title : "Upload Campaign Details", path : pathlabsNonSpecialityBasedItemsUploadTemplate, fileName : "Campaign-Detail-Upload-Template"},
   multipleLabsNonSBItems : { title : "Upload Multiple Campaign Details", path : multPathlabsNonSpecialityBasedItemsUploadTemplate, fileName : "Multiple-Campaign-Detail-Upload-Template"},

   lensItems : { title : "Upload Campaign Details", path : lensItemsUploadTemplate, fileName : "Campaign-Detail-Upload-Template"},

   itemsRemove : { title : "Remove Campaign Details", path : itemsUploadRemoveTemplate, fileName : "Campaign-Detail-Remove-Template"},
}

export const CAMPAIGN_EXCEL_DATA = {
   items: { title: "Existing Campaign Details", url: "", service: CampaignService().getCampaignProducts, fileName : "Campaign-Details" },
   customers: { title: "Existing Customers Details", url: "" , service: CampaignService().getCampaignCustomerIds, fileName : "Campaign-Customers" }
}; 


/*  Complimentary Promotion */
export const COMPLIMENTARY_DWNLOAD_TEMPLATES = {
   productsUpload : { title : "Complimentary Gift's Upload", path : giftProductsTemplate, fileName : "Complimentary-Promotion-Gift-Products-Template"},
   productsRemove : { title : "Complimentary Gift's Remove", path : giftProductsRemoveTemplate, fileName : "Complimentary-Promotion-Gift-Products-Remove-Template"},
   create : {  withCustomers : { title : "References and Customers Upload", path : referenceWithCustomerEdit, fileName : "References-And-Customer-Upload-Remove-Template"},
               withOutCustomers : { title : "References Upload", path : referenceEdit, fileName : "References-Upload-Remove-Template"}},
   edit : { withCustomers : { title : "References and Customers Upload/ Remove", path : referenceWithCustomerEdit, fileName : "References-And-Customers-Upload-Remove-Template"},
            withOutCustomers : { title : "References Upload/ Remove", path : referenceEdit, fileName : "References-Upload-Remove-Template"}}, 
  };

export const COMPLIMENTARY_EXCEL_DATA = {
   products: { title: "Existing Complimentary Gift's", url: "", service: ComplimentaryService().getComplimentaryProducts, fileName : "Complimentary-Promotion-Gift-Products" },
   referenceCustomers: { title: "Existing References", url: "", service: ComplimentaryService().getReferenceCustomers, fileName : "Complimentary-Promotion-References-And-Customers" },
};
