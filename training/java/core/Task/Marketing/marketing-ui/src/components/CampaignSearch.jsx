import DynamicForm, { withFormHoc } from "@medplus/react-common-components/DynamicForm";
import React, { useContext, useEffect } from "react";
import Validate from "../helpers/Validate";
import { API_URL, getChanelsOnApplicableType } from "../services/ServiceConstants";
import { SearchContext } from "./Contexts/MarketingContexts";
import { PromotionType } from "../constants/PromotionConstants";

const CampaignSearch = (props) => {
    const {campaignSearchCriteria, setCampaignSearchCriteria, loadData, setLoadData, selectedDateRange, setSelectedDateRange} = useContext(SearchContext)
    const validate = Validate();
    const handleSearchOnClick = () =>{
        const searchData = props.helpers.collectValuesForSubmit('multiItemCampaignSearchForm');
        const searchCriteria = {}
        searchCriteria['fromDate'] = validate.isNotEmpty(searchData.searchDateRange[0]) ? new Date(searchData.searchDateRange[0]).getTime() : null;
        searchCriteria['toDate'] = validate.isNotEmpty(searchData.searchDateRange[1]) ? new Date(searchData.searchDateRange[1]).getTime() : null;
        if(Validate().isNotEmpty(searchCriteria['fromDate']) && Validate().isEmpty(searchCriteria['toDate'])) {
            props.helpers.updateErrorMessage("From Date and To Date are Mandatory", 'searchDateRange');
            return false;
        }
        searchCriteria['campaignId'] = validate.isNotEmpty(searchData.campaignId) ? searchData.campaignId : null;
        searchCriteria['campaignName'] = validate.isNotEmpty(searchData.campaignName) ? searchData.campaignName : null;
        searchCriteria['campaignType'] = validate.isNotEmpty(searchData.campaignType) ? searchData.campaignType[0] : null;
        searchCriteria['status'] = validate.isNotEmpty(searchData.status) ? searchData.status : null;
        searchCriteria['createdBy'] = validate.isNotEmpty(searchData.createdBy) ? searchData.createdBy[0] : null;
        searchCriteria['applicableTypes'] = validate.isNotEmpty(searchData.applicableType) ? [searchData.applicableType] : null;
        searchCriteria['channels'] = validate.isNotEmpty(searchData.channel) ? searchData.channel : null;
        if(props?.promotionConfig?.promotionType === PromotionType.regular) {
            searchCriteria['promotionLevel'] = validate.isNotEmpty(searchData.promotionLevel) ? searchData.promotionLevel : null;
            searchCriteria['couponCode'] = validate.isNotEmpty(searchData.couponCode) ? searchData.couponCode : null;
        }else if(props?.promotionConfig?.promotionType === PromotionType.campaign) {
            searchCriteria['couponCode'] = validate.isNotEmpty(searchData.couponCode) ? searchData.couponCode : null;
        }
        
        if(validate.isSearchCriteriaEmpty(searchCriteria, false)){
            searchCriteria['fromDate']=new Date().getTime();
            searchCriteria['toDate'] = new Date().getTime();            
        }
        if(searchCriteria['fromDate'] && searchCriteria['toDate']){
            setSelectedDateRange({fromDate: searchCriteria['fromDate'], toDate:searchCriteria['toDate']})
        } else if(validate.isSearchCriteriaEmpty(searchCriteria)){
            setSelectedDateRange({fromDate: new Date().getTime(), toDate:new Date().getTime()})
        } else {
            setSelectedDateRange(undefined)
        }
        props.setSearchFormData(searchData);
        setCampaignSearchCriteria(searchCriteria)
        props.setShowModal(false);

    }

    const updateNames = () => {
        props.helpers.updateSingleKeyValueIntoField("label","Promotion Date Range","grp1",true);
        props.helpers.updateSingleKeyValueIntoField("label","Promotion Details","grp2",true);
        props.helpers.updateSingleKeyValueIntoField("label","Promotion ID","campaignId",true);
        props.helpers.updateSingleKeyValueIntoField("label","Promotion Name","campaignName",true);
    }

    const onFormLoad = () => {
        if(props?.promotionConfig?.promotionType === PromotionType.regular) {
            updateNames();
            props.helpers.showElement('promoLevelGrp') 
            props.helpers.showElement('couponCode');
        } else if(props?.promotionConfig?.promotionType === PromotionType.campaign) {
            props.helpers.hideElement('promoLevelGrp') 
            props.helpers.showElement('couponCode');
        } else if(props?.promotionConfig?.promotionType === PromotionType.complimentary) {
            updateNames();
            props.helpers.hideElement('couponCode');
        } else { 
            props.helpers.showElement('campaignType') 
            props.helpers.hideElement('promoLevelGrp');
            props.helpers.hideElement('couponCode');
        }
        props.helpers.hideGroup("grp6");
        if(Validate().isNotEmpty(props.searchForm)){
            props.helpers.updateSingleKeyValueIntoField("value",props.searchForm.searchDateRange,"searchDateRange");
        }
    }
    const onApplicableTypeChange = (value) => {
        props.helpers.updateValue([],'channel',false);
        props.helpers.showElement("grp6");
        const options = getChanelsOnApplicableType(value, props.helpers);
        props.helpers.addOptions("channel", options, true);
    }
    const oberserverMap = {
        'search' : [['click', ()=>handleSearchOnClick()]],
        'reset' : [['click', ()=>onFormLoad()]],
        'multiItemCampaignSearchForm' : [['load', ()=>onFormLoad()]],
        'applicableType' : [['change', (payload)=>onApplicableTypeChange(payload[0].target.value)]],
    }
    return (
        <>
           <React.Fragment>
                <DynamicForm requestUrl={`${API_URL}list-multi-item-campaign-form?type=${props?.promotionConfig?.promotionType}`} helpers={props.helpers} observers={oberserverMap} requestMethod={'GET'} />
           </React.Fragment>
        </>
    )
}

export default withFormHoc(CampaignSearch);
