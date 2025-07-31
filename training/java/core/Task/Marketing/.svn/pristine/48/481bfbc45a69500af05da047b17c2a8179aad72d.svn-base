import DynamicForm, { ALERT_TYPE, withFormHoc } from "@medplus/react-common-components/DynamicForm";
import React, { useContext, useEffect, useRef, useState } from "react";
import { PromotionStatus, PromotionType, SERVICE_CHARGE_DETAIL } from "./../constants/PromotionConstants";
import Validate from "./../helpers/Validate";
import { API_URL, isUrlCampaign, isUrlRegularPromotion } from "./../services/ServiceConstants";
import { AlertContext } from "./Contexts/UserContext";
import NextPrevBtn from "./common/NextPrevButtons";

const CouponInfo = (props) => {

    let couponData ={};
    const { setAlertContent } = useContext(AlertContext);
    const setLoading = props.setLoading;
    const [selectedCampaignType, setSelectedCampaignType] = useState(undefined);

    useEffect(()=>{
        setLoading(true);
        if(Validate().isNotEmpty(props.couponInfo)) {
            updateFormValues();
        }       
        setLoading(false);
    }, [props.couponInfo])

    const onCouponCodeChange = (value) => {
        props.helpers.updateValue(value?.toUpperCase()?.trim(),'couponCode');
        props.handleCouponNameChange();
    }

    const updateFormValues = () => {
        props.helpers.updateSpecificValues(props.couponInfo, "couponForm");
        // props.helpers.disableElement('couponCode');
    }
    const onCouponBasedChange = (value) => {
        
        (props?.promotionType === PromotionType.regular && Validate().isNotEmpty(value)) ? props.helpers.showElement('allCustomersGrp') : props.helpers.hideElement('allCustomersGrp');
        updateCouponBased(Validate().isEmpty(value) ? 'N' : 'Y');
    }

    const updateCouponBased = (value) => {
        if(value === 'N') {
            props.helpers.hideElement('allCustomersGrp');
            props.helpers.updateValue('Y', 'allCustomers', true);
            props.helpers.disableElement('allCustomers') 
            props.helpers.hideElement('grp1');
            props.helpers.hideElement('grp2');
            props.helpers.hideElement('grp4');
            props.helpers.hideElement('grp5');
            updateRequiredFieldsOfCouponBased(false);
            updateRequiredFieldsOfAddOnCoupon(false);
        } else {
            if(isUrlRegularPromotion(props.match.path)){
                props.helpers.showElement('allCustomersGrp');
                props.helpers.enableElement('allCustomers');
            }
            props.helpers.showElement('grp1');
            
            updateRequiredFieldsOfCouponBased(true);
            if((isUrlCampaign(props.match.path) && props.campaignType === '3') 
                || isUrlRegularPromotion(props.match.path)){
                    props.helpers.showElement('grp2');
                    let addOnCouponData = props.helpers.collectSpecificFieldValues('couponForm',['addOnCoupon']);
                    addOnCouponData = Validate().isEmpty(addOnCouponData.addOnCoupon) ? false : true;
                     if(addOnCouponData) {
                        props.helpers.showElement('grp3');
                        updateRequiredFieldsOfAddOnCoupon(true);
                    } else {
                        props.helpers.hideElement('grp3');
                        updateRequiredFieldsOfAddOnCoupon(false);
                    } 

                    if(props.actionPermissions.isEdit){
                        props.helpers.disableElement('addOnCoupon');
                        props.helpers.disableElement('couponType')
                    }
                    onCouponDiscountTypeChange(props.couponInfo.couponType)

            }else if(isUrlCampaign(props.match.path) && props.campaignType != '3'){
                if(props.actionPermissions.isEdit){
                    props.helpers.disableElement('couponType')
                }
                onCouponDiscountTypeChange(props.couponInfo.couponType)
            }
            updateRequiredFieldsOfCouponBased(true);
            
            if(props.applicableType== "PHARMACY" || props.applicableType === '5'){
                props.helpers.showElement('grp4');
            }
            else if(props.applicableType== "PATHLABS" || props.applicableType === '6'){
                props.helpers.showElement('grp4');
                props.helpers.showElement('grp5');
            }

            if(isUrlCampaign(props.match.path) && selectedCampaignType === '1' ) {
                onCouponDiscountTypeChange(selectedCampaignType);
            } else if(isUrlCampaign(props.match.path) && selectedCampaignType === '2' || selectedCampaignType === '3') {
                onCouponDiscountTypeChange(selectedCampaignType);
            }
        }
    }

    const updateRequiredFieldsOfCouponBased = (value) => {
        props.helpers.updateSingleKeyValueIntoField("required", value, "couponCode");
        // props.helpers.updateSingleKeyValueIntoField("required", value, "noOfDays");
        props.helpers.updateSingleKeyValueIntoField("required", value, "totalLimit");
        props.helpers.updateSingleKeyValueIntoField("required", value, "customerLimit");
        // if(!value) updateRequiredFieldsOfAddOnCoupon(false);
    }

    const onAddOnCouponChange = (value) => {
        updateAddOnCoupon(Validate().isEmpty(value) ? 'N' : 'Y');
    }

    const updateAddOnCoupon = (value) =>{ 
        if(value === 'Y') {
            props.helpers.showElement('grp3');
            if(isUrlCampaign(props.match.path) && (props.applicableType === '6' || props.applicableType === '7')) {  
                props.helpers.hideElement('maxPoints');
            }
            props.helpers.updateValue("1", "couponType", true);
            props.helpers.disableElement('couponType');
            props.helpers.hideElement('grp6');
            updateRequiredFieldsOfAddOnCoupon(true);
        } else {
            props.helpers.hideElement('grp3');
            props.helpers.enableElement('couponType');
            if(isUrlRegularPromotion(props.match.path) || (isUrlCampaign(props.match.path) && props.applicableType === '7')){
                props.helpers.hideElement('couponType');
            }
            updateRequiredFieldsOfAddOnCoupon(false)
        }
    }

    const onCouponDiscountTypeChange = (value) =>{
        setSelectedCampaignType(value);
        if(value === '2' || value === '3') {
            props.helpers.showElement('serviceChargeGrp');
            if(props.applicableType === '6'){
                props.helpers.hideElement('pharmaServiceChargeGrp');
                props.helpers.showElement('pathLabServiceChargeGrp');
            } else if(props.applicableType === '5'){
                props.helpers.showElement('pharmaServiceChargeGrp');
                props.helpers.hideElement('pathLabServiceChargeGrp');
            } else{
                props.helpers.hideElement('serviceChargeGrp');
            }
            if(props.actionPermissions.isCreate || props.actionPermissions.isClone){
                props.helpers.hideElement('grp3');
                props.helpers.disableElement('addOnCoupon')
            }
        } else{
            if(props.actionPermissions.isCreate || props.actionPermissions.isClone){
                props.helpers.hideElement('serviceChargeGrp');
                props.helpers.enableElement('addOnCoupon')
            }
        }
    }    

    const updateRequiredFieldsOfAddOnCoupon = (value) => {
        props.helpers.updateSingleKeyValueIntoField("required", value, "minValue");
        props.helpers.updateSingleKeyValueIntoField("required", value, "maxDiscount");
        if(isUrlRegularPromotion(props.match.path) || (isUrlCampaign(props.match.path) && props.applicableType === '5') )
            props.helpers.updateSingleKeyValueIntoField("required", value, "maxPoints");
    }
    
    const updateCouponInfo = ()=>{
        props.setHelpers(props.helpers);
        let isCouponBased = props.helpers.collectSpecificFieldValues("couponForm", ["couponBased"]);
        isUrlCampaign(props.match.path) || ((Validate().isEmpty(isCouponBased.couponBased)) && isUrlRegularPromotion(props.match.path))? props.helpers.hideElement('allCustomersGrp') : props.helpers.showElement('allCustomersGrp');
        if(isUrlRegularPromotion(props.match.path))
            props.helpers.hideElement('couponType');
        props.setLoading(true);
        const prevCouponInfo = props.couponInfo;
        if(Validate().isNotEmpty(prevCouponInfo)){
            props.helpers.updateSpecificValues(prevCouponInfo, 'couponInfo');
            if(Validate().isNotEmpty(prevCouponInfo.couponBased)) {
                updateCouponBased('Y');
                (Validate().isNotEmpty(prevCouponInfo.addOnCoupon)) ? updateAddOnCoupon('Y') : updateAddOnCoupon('N');
                if(Validate().isNotEmpty(prevCouponInfo.couponType) ){
                    if(props.actionPermissions.isEdit){
                        props.helpers.disableElement('couponType');
                    }
                    onCouponDiscountTypeChange(prevCouponInfo.couponType)

                }
            } else {
                updateCouponBased('N');
            }
        }
        if(props.actionPermissions.isApprover || props?.globalPromotionStatus === PromotionStatus.active || (isUrlCampaign(props.match.path) && props?.campaignType === '11')) {
            props.helpers.updateKeyValuesToAllFields("disabled", "true", "couponForm");
        } else if(props.actionPermissions.isEdit) {   
            if(props.disableCouponCode) {
                props.helpers.disableElement('couponCode');
            }
            props.helpers.disableElement('addOnCoupon')
        } 
        if(isUrlCampaign(props.match.path)){
            props.helpers.hideElement('allCustomersGrp');
        }
        props.setLoading(false);
        props.setLoading(false);
    }

    const validateForm = (formData)=> {
        if(formData && formData?.couponBased === 'Y') {
            if(Validate().isEmpty(formData?.totalLimit) || Validate().isEmpty(formData?.customerLimit)) {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter coupon details'})
                return false;
            }
            var regex = /^[a-zA-Z0-9]+$/;
            if(!regex.test(formData?.couponCode)){
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter valid coupon code'})
                return false;
            }
            if(Validate().isEmpty(formData?.couponCode)) {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter coupon details'})
                return false;
            }
            let couponCode = String(formData?.couponCode);
            if((props.applicableType=='6' && (couponCode.length>12 || couponCode.length<4))){
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Coupon code should be of length between 4 to 12 '})
                return false;
            }
            if((props.applicableType!='6' && (couponCode.length>10 || couponCode.length<4))){
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Coupon code should be of length between 4 to 10 '})
                return false;
            }
            
        }
        if(formData && (Number(formData.totalLimit) < Number(formData.customerLimit) )) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Total limit should be greater than Customer limit'})
            return false;
        }
        if(formData && formData?.addOnCoupon === 'Y') {
            if(Validate().isEmpty(formData?.minValue) || Validate().isEmpty(formData?.maxDiscount) ) {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter AddOn coupon details'});
                return false;
            } else if((isUrlRegularPromotion(props.match.path) || (isUrlCampaign(props.match.path)) && props.applicableType==='5') && (Validate().isEmpty(formData?.maxPoints) || formData.maxPoints <= 0 || formData.maxPoints > 9999.99)){
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Max PayBackPoints should be between 0 and 10000'});
                props.helpers.customRef('maxPoints').focus();
                return false;
            } else if(formData.minValue <= 0 || formData.minValue > 9999.99){
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Min Invoice Value should be strictly between 0 and 10000'});
                props.helpers.customRef('minValue').focus();
                return false;
            } else if(formData.maxDiscount <= 0 || formData.maxDiscount > 9999.99){
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Max Discount should be strictly between 0 and 10000'});
                props.helpers.customRef('maxDiscount').focus();
                return false;
            }
        }
        if(formData && (formData.couponType === '2' || formData.couponType === '3') && !validateServiceCharges(formData)) {
            return false;
        }
        return true;
    }

    const validateServiceCharges = (formData) => {
        let isAllEmpty = true;
        let applicableTypeName = "";
        if(props.applicableType == '5') {
            applicableTypeName = "PHARMACY";
        } else if(props.applicableType == '6') {
            applicableTypeName = "PATHLABS";
        }
        if(props.applicableType)
        for(const { enumID, name } of SERVICE_CHARGE_DETAIL[applicableTypeName]) {
            if (formData && formData[enumID] && Number(formData[enumID]) > 100) {
                setAlertContent({ alertType: ALERT_TYPE.ERROR, alertMessage: `${name} discount percentage should be less than 100`});
                return false;
            } 
            if (formData && formData[enumID] && Number(formData[enumID]) > 0) {
                isAllEmpty = false;
            }
        }
        if(isAllEmpty) {
            setAlertContent({ alertType: ALERT_TYPE.ERROR, alertMessage: `At least 1 service charge discount is required for creating campaign`});
            return false;
        }
        return true;
    }

    const handleNextBtnClick = () => {

        if(props.actionPermissions?.isEdit)
            couponData = props.helpers.collectValuesForSubmit('couponForm');  
        else
            couponData = props.helpers.validateAndCollectValuesForSubmit('couponForm', false, true, true);        
 
        if(couponData == null)
            return false;
        if(Validate().isEmpty(couponData.couponBased) || validateForm(couponData)) {
            props.handleCampaignInfoChange(couponData, props.tabDetails.currentTab.tabId, props.tabDetails.nextTab.tabId);
            return true;
        }
        return false;
    }

    const handlePrevBtnClick = ()=>{
        couponData = props.helpers.collectValuesForSubmit('couponForm');
        props.handleCampaignInfoChange(couponData, props.tabDetails.currentTab.tabId, props.tabDetails.prevTab.tabId);

    }

    const oberserverMap = {
        'couponForm' : [['load', () => updateCouponInfo()]],
        'couponBased' : [['change', (payload) => onCouponBasedChange(payload[1].value)]],
        'addOnCoupon' : [['change', (payload) => onAddOnCouponChange(payload[1].value)]],
        'couponCode' : [['change', (payload) => onCouponCodeChange(payload[0].target.value)]],
        'couponType' : [['change', (payload) => onCouponDiscountTypeChange(payload[0].target.value)]]
    }

    return (
        <>
            <React.Fragment  key={`${props.location.pathname}`}> 
            { <> <div>
                        <DynamicForm 
                            helpers={props.helpers} 
                            observers={oberserverMap}
                            requestUrl={`${API_URL}get-coupon-form`} 
                            requestMethod={'GET'} 
                        />
                </div> 
                
                    <NextPrevBtn PrevTab={props.tabDetails.prevTab.title} nextTab={props.tabDetails.nextTab.title} {...props} 
                     isNoteRequired={true}  noteItems= {props.note} showNote={!(props?.actionPermissions?.isApprover || props?.actionPermissions?.isCloser)}
                     handleNextBtnClick = {handleNextBtnClick}
                     handlePrevBtnClick={handlePrevBtnClick} /> 
                </> 
            }
            </React.Fragment>
        </>
    )
}

export default withFormHoc(CouponInfo)