import DynamicForm, { ALERT_TYPE, withFormHoc } from '@medplus/react-common-components/DynamicForm';
import React, { useContext, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { PromotionStatus, PromotionType } from '../constants/PromotionConstants';
import { CAMPAIGN_URLS, MIC_URLS } from '../constants/UrlConstants';
import ResponseHandler from '../helpers/ResponseHandler';
import Validate from '../helpers/Validate';
import { ADD_FORM, CAMPAIGN_INFO_FORM } from '../redux/constants';
import FormJsonService from '../services/FormJsonService';
import { API_URL, REQUEST_TYPE, getCampaignTypeOptions, getChanelsOnApplicableType, isUrlCampaign } from "../services/ServiceConstants";
import { AlertContext, UserContext } from './Contexts/UserContext';
import CustomDateTimePicker from './common/CustomDateTimePicker';
import NextPrevBtn from './common/NextPrevButtons';
import { addDays } from 'date-fns';

const CampaignInfo = (props) => {

    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(undefined);
    const [defaultStartDate, setDefaultStartDate] = useState(new Date(new Date().setSeconds(0,0)).getTime()+300000)
    const [defaultEndDate, setDefaultEndDate] = useState(new Date().setHours(23,59,59,0));
    const [campaignInfo, setCampaignInfo] = useState(props.campaignInfo)
    const [isFormLoaded, setFormLoaded] = useState(false);
    const [maxLimit, setMaxLimit] = useState(undefined);
    const [isToDateMandatory, setIsToDateMandatory] = useState(true);
    let campaignInfoData = {};
    const {setAlertContent} = useContext(AlertContext)
    const [disableStartDate, setDisableStartDate] = useState(true);
    const [disableEndDate, setDisableEndDate] = useState(false);
    const globalPromotionStatus = props.globalPromotionStatus;
    const campaignInfoFormRedux = useSelector((state) => Validate().isNotEmpty(state.formJsonReducer) && Validate().isNotEmpty(state.formJsonReducer[CAMPAIGN_INFO_FORM]) ? state.formJsonReducer[CAMPAIGN_INFO_FORM] : [])
    const [campaignInfoForm, setCampaignInfoForm] = useState();
    const dispatch = useDispatch();
    const {userSessionDetails} = useContext(UserContext);
    const validate = Validate();
    const marketingRoles = (validate.isNotEmpty(userSessionDetails) && validate.isNotEmpty(userSessionDetails.roles) && userSessionDetails.moduleName === 'marketing') ? userSessionDetails.roles : [];
    
    const hasRole=(userRole)=>{
        let isHasRole = false
        if (validate.isEmpty(marketingRoles) || validate.isEmpty(userRole)) {
            return isHasRole;
        }
        isHasRole = marketingRoles.includes(userRole);
        return isHasRole;
    }

    useEffect(() => {
        const campaignNameInput = document.getElementById("campaignName")
        if (campaignNameInput) {
            campaignNameInput.focus();
        }
    }, [document.getElementById("campaignName")]);


    useEffect(() => {
        if(globalPromotionStatus === PromotionStatus.active || props?.actionPermissions?.isApprover) {
            props.helpers.updateKeyValuesToAllFields("disabled", "true", "campaignInfo");
            setDisableEndDate(true);
            if(props?.actionPermissions?.isCloser || props?.actionPermissions?.isApprover){
                 setDisableEndDate(false);
            }
        }  
    }, [globalPromotionStatus, props?.actionPermissions?.isApprover, props?.actionPermissions?.isCloser])

    useEffect( () => {
        if(!endDate) {
            // let date = getDefaultToDate(new Date());
            // setEndDate(date);
            setEndDate(null);
            // date = getDefaultFromDate(new Date());
            setStartDate(null);

        }
    },[props.location]);
    
    useEffect(()=>{
        if(Validate().isNotEmpty(campaignInfo)) {
            updateFormValues();
        } else {
            setStartDate(null);
            setEndDate(null);
        }
    }, [props.campaignInfo])
    
    useEffect(()=> {
        startDate && setDisableEndDate(false);
    },[startDate])

    const isPromotionAddOn = () => {
        return props?.promotionType === PromotionType.campaign && (campaignInfo?.campaignType === '11' || props?.campaignInfo?.campaignType === '11');
    }

    const postFormLoading = () => {
        props.setLoading(true);
        if(props.actionPermissions.isCreate || props.actionPermissions.isClone) {
            setDisableStartDate(false);
        }
        if(Validate().isNotEmpty(props.campaignInfo)) {
            if(props?.actionPermissions?.isEdit && Validate().isNotEmpty(globalPromotionStatus)) {
                if(globalPromotionStatus === PromotionStatus.active || props?.actionPermissions?.isApprover) {
                    props.helpers.updateKeyValuesToAllFields("disabled", "true", "campaignInfo");
                    // setDisableStartDate(true);
                    setDisableEndDate(true);
                    if(props?.actionPermissions?.isCloser || props?.actionPermissions?.isApprover){
                         setDisableEndDate(false);
                    }
                } else if(globalPromotionStatus == PromotionStatus.inActive) {
                    props.helpers.disableElement('campaignName');
                    props.helpers.disableElement('applicableType');
                    if(props.match.path === MIC_URLS.editCampaign || props.match.path === CAMPAIGN_URLS.editCampaign) {
                        props.helpers.disableElement('campaignType')
                    }
                } 
            } else if(props?.actionPermissions?.isClone) {
                props.helpers.disableElement('applicableType');
                if(props?.promotionType === PromotionType.multiItem || props?.promotionType === PromotionType.campaign)
                    props.helpers.disableElement('campaignType');
                
            }
            onApplicableTypeChange(props.campaignInfo['applicableType']);
            props.helpers.updateSpecificValues(props.campaignInfo, "campaignInfo");
            if(props.campaignInfo.fromDate) {
                setStartDate(new Date(props.campaignInfo.fromDate));
                setDefaultStartDate(new Date(props.campaignInfo.fromDate));
            } else {
                setStartDate(null);
            }
            if(props.campaignInfo.toDate) {
                setEndDate(new Date(props.campaignInfo.toDate));
                setDefaultEndDate(new Date(props.campaignInfo.toDate));
            }
        } else {
            setDisableEndDate(true);
        }
        let maxLimitForPromotion = props.helpers.collectSpecificFieldValues("campaignInfo", ["maxDaysLimit"]);
		if(Validate().isNotEmpty(maxLimitForPromotion)) {
			setMaxLimit(maxLimitForPromotion.maxDaysLimit);
		}
        let isToDate = props.helpers.collectSpecificFieldValues("campaignInfo", ["isToDateMandatory"]);
        if(Validate().isNotEmpty(isToDateMandatory)) {
			setIsToDateMandatory(isToDate.isToDateMandatory === "1" ? true : false);
		}
        if(props?.promotionType === PromotionType.regular){
            props.helpers.hideElement('grp4');
            props.helpers.showElement('couponBasedGrp')
            props.helpers.updateSingleKeyValueIntoField("label","Promotion Name","campaignName",true);
            props.helpers.updateSingleKeyValueIntoField("label","Promotion Level","grp7",true);
        }
        if(props?.promotionType === PromotionType.complimentary){
            props.helpers.hideElement('grp9');
            props.helpers.hideElement('grp4');
            props.helpers.updateSingleKeyValueIntoField("label","Promotion Name","campaignName",true);
        }
        if(isPromotionAddOn()) {
            props.helpers.showElement('minInvoiceValueGrp');
        }
        props.setLoading(false);
    }
    const loadCampaignInfoForm = async () => {
        props.setLoading(true);
        setFormLoaded(false);
        let campaignInfoFormObj = campaignInfoFormRedux;
        console.log('getting campaignInfoForm from Redux: ')
        if(Validate().isEmpty(campaignInfoFormObj) || campaignInfoFormObj.length <=0) {
            console.log('getting campaignInfoForm from Server')
            const response =await FormJsonService().getCampaignInfoForm().catch(error => {
                setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to fetch data'});
            });
        ResponseHandler(setAlertContent).handleResponse(response,{'responseType':'FORM'},(data) => {
            campaignInfoFormObj = data;
            let formObj={};
            formObj[CAMPAIGN_INFO_FORM] = data
            dispatch({type: ADD_FORM, payload : formObj})
        }, (error) => {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: error});
        });
        }
        setCampaignInfoForm(campaignInfoFormObj);
        setFormLoaded(true);
        props.setLoading(false);
    }

    useEffect(()=>{
        props.setLoading(true);
        /* if(!isFormLoaded && Validate().isEmpty(campaignInfoForm)) {
            loadCampaignInfoForm();
        } */
        postFormLoading();
        props.setLoading(false);
    },[props?.actionPermissions?.isEdit, props?.campaignInfo, props?.actionPermissions?.isClone, props?.match?.path])


    const onApplicableTypeChange = (value, updateProps=false) => {
        props.helpers.updateValue([],'channel',false);
        props.helpers.showElement("grp3");
        props.helpers.showElement("grp7");
        const campaignTypes = getCampaignTypeOptions(value, props.helpers, props.match.path, props.promotionType) 
        props.helpers.updateValue(null,'campaignType', false);
        const options = getChanelsOnApplicableType(value, props.helpers);
        props.helpers.addOptions("campaignType", campaignTypes, true);
        props.helpers.addOptions("channel", options, true);
        props.helpers.showElement("channel");
        if(updateProps && props?.actionPermissions?.isCreate){
            props.onApplicableTypeChange && props?.onApplicableTypeChange(value)
        }
        
    }

    const addApplicableTypesOnRoles = () => {
        const applicableTypeRoles = props.applicableTypeRoles
        let isDefaultSelected = false;
        if(props?.actionPermissions?.isCreate || props?.actionPermissions?.isClone) {
            let applicableTypes = []
            if(applicableTypeRoles?.pharmacy && hasRole(applicableTypeRoles.pharmacy)) {
                applicableTypes.push(props.helpers.createOption("pharmacy","Pharmacy","5","false"))
                props.helpers.updateValue("5", "applicableType", true);
                onApplicableTypeChange("5");
                isDefaultSelected = true;
            } 
            if(applicableTypeRoles?.lab && hasRole(applicableTypeRoles.lab)) {
                applicableTypes.push(props.helpers.createOption("pathlabs","Pathlabs","6","false"))
                if(!isDefaultSelected) {
                    props.helpers.updateValue("6", "applicableType", true);
                    onApplicableTypeChange("6");
                    isDefaultSelected = true;
                }
            }
            if(applicableTypeRoles?.lens && hasRole(applicableTypeRoles.lens)) {
                applicableTypes.push(props.helpers.createOption("lens","Lens","7","false"))
                if(!isDefaultSelected) {
                    props.helpers.updateValue("7", "applicableType", true);
                    onApplicableTypeChange("7");
                    isDefaultSelected = true;
                }
            }
            props.helpers.addOptions("applicableType", applicableTypes, true);
        }
    }

    const updateFormValues = () => {
        props.helpers.updateSingleKeyValueIntoField("autocomplete","off","campaignName",false);
        props.helpers.updateSingleKeyValueIntoField("autofocus", true, "campaignName", true);
        addApplicableTypesOnRoles();
        if(props.promotionType === PromotionType.multiItem)
            props.helpers.updateValue("8", "campaignType", true);
        else if(props.promotionType === PromotionType.regular)
            props.helpers.updateValue("I", "campaignType", true);
        else if(props.promotionType === PromotionType.campaign)
            props.helpers.updateValue("3", "campaignType", true);
        props.setHelpers(props.helpers);
        props.setLoading(true);
        setCampaignInfo({...props.campaignInfo});
        postFormLoading();
        if(Validate().isNotEmpty(campaignInfo)){
            onApplicableTypeChange(campaignInfo['applicableType'])
            props.helpers.updateSpecificValues(campaignInfo, "campaignInfo");
        }
        if(props?.promotionType === PromotionType.regular) {
            props.helpers.hideElement('allCustomers')
        }
        if(isUrlCampaign(props.match.path)){
            props.helpers.hideElement('grp4');
        }
        props.setLoading(false);
    }

    const handleFromDateChange = (date) =>{
        setStartDate(date);
        if(date){
            setDefaultStartDate(date);
        }else{
            setDefaultStartDate(new Date(new Date().setSeconds(0,0)).getTime()+300000);
        }
    }
    const handleToDateChange = (date) =>{
        props.setIsToDateChanged();
        setEndDate(date);
        if(!(props.actionPermissions.isCreate || props.actionPermissions.isClone || (props.actionPermissions.isApprover && !props.actionPermissions.isCloser)))
            props.setShowUpdateToDate(true)
        if(date){
            setDefaultEndDate(date);
        }else{
            setDefaultEndDate(new Date().setHours(23,59,59,0))
        }
    }
    const getDefaultToDate = (dt) => {
        let date = dt;
        date.setDate(dt.getDate()+maxLimit);
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(58);
        return date;
    }
    const getDefaultFromDate = (dt) => {
        let date = dt;
        date.setMinutes(dt.getMinutes() + 5);
        return date;
    }
    
    const oberserverMap = {
        'applicableType' :[['change', (payload)=>onApplicableTypeChange(payload[0].target.value, true)]],
        'campaignInfo' :[['load', () => updateFormValues()]],
        'campaignType' : [['change', (payload)=>props.onCampaignTypeChange(payload[0].target.value)]],
        'campaignName' : [['change', (payload) => props.handleCampaignNameChange()]],
    }

     const handleUpdateToDate = () => {
        if(handleNextBtnClick()) {
            props.updateToDate(endDate);
        }
    }

    const handleNextBtnClick = () => {
        if(!startDate) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"From Date is mandatory"})
            return false;
        }
        if(!endDate && isToDateMandatory) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"To Date is mandatory"})
            return false;
        }
        let sDate = new Date(startDate);
        let eDate =endDate ? new Date(endDate) : undefined;
        campaignInfoData = props.helpers.validateAndCollectValuesForSubmit('campaignInfo', false);
        if(Validate().isNotEmpty(campaignInfoData)){
            campaignInfoData.allCustomers = (Validate().isNotEmpty(campaignInfoData.allCustomers) && campaignInfoData.allCustomers === 'Y') ? 'Y' : 'N';
            if(campaignInfoData !== null){
                    if(Validate().isEmpty(campaignInfoData.campaignName.trim())) {
                        props.helpers.updateErrorMessage("Please Enter Valid Campaign Name", "campaignName");
                        return false;
                    }
                    if(props?.promotionType === PromotionType.campaign && campaignInfoData?.campaignType === '11') {
                        if(Validate().isEmpty(campaignInfoData.minInvoiceValue)) {
                            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"Min Invoice Value is mandatory for Promotion Addon campaigns"});
                            return false;
                        }
                        const minInvoiceValue = parseFloat(campaignInfoData.minInvoiceValue);
                        if(isNaN(minInvoiceValue) || minInvoiceValue < 1 || minInvoiceValue > 99999) {
                            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"Min Invoice Value must be between 1 and 99,999"});
                            return false;
                        }
                    }
                    if(Validate().isEmpty(startDate)) {
                        setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"From Date is mandatory"})
                        return false;
                    }
                    if(isToDateMandatory && Validate().isEmpty(endDate)) {
                        setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"To Date is mandatory"})
                        return false;
                    }
                    if(new Date(startDate) < new Date() && (props.actionPermissions.isCreate || props.actionPermissions.isClone)) {
                        setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"From Date should be greater than current time"})
                        return false;
                    }
                    if(endDate && startDate >= endDate) {
                        setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"From Date should be less than To Date"})
                        return false;
                    }if(eDate && Validate().isNotEmpty(maxLimit) && (Math.abs(eDate -sDate)/(1000 * 60 * 60 * 24)) > maxLimit){
                        setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"From Date and To Date should be in between "+maxLimit + " days"})
                        return false;
                    }
                    if(endDate && endDate <= new Date()){
                        setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"To Date should be greater than current time"})
                        return false;
                    }
                campaignInfoData['fromDate'] = startDate;
                campaignInfoData['toDate'] = endDate;
                props.handleCampaignInfoChange(campaignInfoData, props.tabDetails.currentTab.tabId, props.tabDetails.nextTab.tabId);
                return true;
            }
            
        }
        return false;
    }

    const getNoteItems = () => {
        let notes = ["From date and To date selected is inclusive of the minute selected."];
        if(props?.promotionType === PromotionType.campaign) {
            notes.push("For Promotion Addon Campaigns, Min. Invoice value is mandatory.");
        }
        return notes;
    }

return(
    <React.Fragment key={`${props.location.pathname}`}>
          {<> 
            <div className='overflow-auto'  style={{height:"calc(100% - 63px)"}} key={`${props.location.pathname}`}>
                <DynamicForm 
                helpers={props.helpers} 
                observers={oberserverMap} 
                requestMethod={REQUEST_TYPE.GET}
                requestUrl={`${API_URL}get-campaign-info-form?type=${props.promotionType}`}
                // formJson ={campaignInfoForm}
                />
                <div>
                <div className='row m-0'>
                    <CustomDateTimePicker {...props} handleDateChange = {handleFromDateChange} title={'From Date'} defaultDateTime ={startDate} pickerProps={{readOnly: disableStartDate}} isClearable={props.actionPermissions.isCreate || props.actionPermissions.isClone}
                        minDate={new Date()} defaultTime={defaultStartDate}/>
                    <CustomDateTimePicker {...props}  handleDateChange = {handleToDateChange} title={'To Date'} defaultDateTime ={endDate} note = {isToDateMandatory ?'Max('+ maxLimit+') Days' : 'No max limit'} pickerProps={{readOnly: disableEndDate}} isClearable={true}
                        minDate={startDate ? startDate : new Date()} defaultTime={defaultEndDate} maxDate={isToDateMandatory && addDays(startDate ? startDate : new Date(),parseInt(maxLimit))}/>
                    {props.showUpdateToDate && <button className='btn btn-primary btn-sm col-2 mt-4' onClick={() => handleUpdateToDate()}>Update ToDate</button>}
                
                </div>
                
                </div>
            </div> 
            <NextPrevBtn nextTab={props.tabDetails.nextTab.title} {...props} isNoteRequired={true}  noteItems= {getNoteItems()} showNote={true} hidePrevBtn={true} handleNextBtnClick={handleNextBtnClick} /> 
            </>}
    </React.Fragment>
)
}

export default withFormHoc(CampaignInfo);