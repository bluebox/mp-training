import CommonDataGrid from "@medplus/react-common-components/DataGrid";
import DynamicForm, { CustomSpinners, Filter, ViewRecords, withFormHoc } from "@medplus/react-common-components/DynamicForm";
import dateFormat from 'dateformat';
import React, { useContext, useEffect, useRef, useState } from "react";
import { Button } from "react-bootstrap";
import { POPUP_ROLES, ROLE_MRK_APPROVE_POPUP_CONFIG_LABS } from "../../constants/MarketingRoles";
import Validate from "../../helpers/Validate";
import PopupService from "../../services/PopupService";
import { BodyComponent, FooterComponent, HeaderComponent, Wrapper } from "../common/CommonStructure";
import DataGridHelper from "../common/DataGridHelper";
import DynamicGridHeight from "../common/DynamicGridHeight";
import { AlertContext } from "../Contexts/UserContext";
import CustomDateTimePicker from "../common/CustomDateTimePicker";
import ResponseHandler from "../../helpers/ResponseHandler";
import { POPUP_URLS } from "../../constants/UrlConstants";
import cronstrue from 'cronstrue';
import RejectPopupModal from "./RejectPopUpModal";
import {rangeTypeMap } from "../../constants/MarketingConstant";

let obj = {
    "htmlElementType": "FORM",
    "id": "toDateForm",
    "label": null,
    "name": null,
    "value": null,
    "className": null,
    "readOnly": false,
    "disabled": false,
    "autofocus": false,
    "required": false,
    "style": null,
    "attributes": null,
    "message": null,
    "htmlActions": null,
    "elementSize": null,
    "defaultValue": null,
    "helperText": null,
    "labelClassName": null,
    "htmlGroups": [
        {
            "htmlElementType": "ELEMENTGROUP",
            "id": "dateRangeGrp",
            "label": null,
            "name": null,
            "value": null,
            "className": "row g-3",
            "readOnly": false,
            "disabled": false,
            "autofocus": false,
            "required": false,
            "style": null,
            "attributes": null,
            "message": null,
            "htmlActions": null,
            "elementSize": null,
            "defaultValue": null,
            "helperText": null,
            "labelClassName": null,
            "groups": null,
            "groupElements": []
        }
    ]
}


const ViewPathlabConfiguration = ({ helpers, ...props }) => {
    const popupService = PopupService();
    const validate = Validate();
    const [configurationInfo, setConfigurationInfo] = useState();
    const [isLoading, setLoading] = useState(false);
    const isApprover = validate.validateRole([ROLE_MRK_APPROVE_POPUP_CONFIG_LABS]);
    const [showApproveOrRejectButton, setShowApproveOrRejectButton] = useState(isApprover);
    const [customerIdsDefinedTrigger, setCustomerIdsDefinedTrigger] = useState();
    const [patientAgeDefinedTrigger, setPatientAgeDefinedTrigger] = useState();
    const [patientGenderDefinedTrigger, setPatientGenderDefinedTrigger] = useState();
    const [paramtersDefinedTrigger, setParametersDefinedTrigger] = useState();
    const [exParamtersDefinedTrigger, setExParametersDefinedTrigger] = useState();
    const [filterRegionText, setFilterRegionText] = useState([]);
    const [filteredRegions, setFilteredRegions] = useState([]);
    const [filterCustomerText, setFilterCustomerText] = useState([]);
    const [filterCustomers, setFilterCustomers] = useState([]);
    const [approveLoader, setApproveLoader] = useState(false);
    const [rejectLoader, setRejectLoader] = useState(false);
    const { setStackedToastContent } = useContext(AlertContext);
    const [toDate, setToDate] = useState(new Date());
    const [tempToDate, setTempToDate] = useState(new Date());
    const [parameterResult, setPatameterResult] = useState();
    const [excludeParameterResult, setExcludeParameterResult] = useState();
    const [openModal, setOpenModal] = useState(false);
    const requestId = props?.match?.params?.reqId;
    const configId = props?.match?.params?.configId;
    const isEdit = props?.location?.state?.isEdit;

    const parameterResultData = DataGridHelper().parameterResultData();
    const excludeParameterResultData = DataGridHelper().excludeParameterResultData();
    const patientAgeGroups = props?.location?.state?.ageGroups;

    const headerRef = useRef(null);
    const footerRef = useRef(null);

    useEffect(() => {
        if (validate.isNotEmpty(requestId)) {
            getConfigurationByRequestId(requestId);
        }
        if (validate.isNotEmpty(configId)) {
            getConfigurationByConfigId(configId);
        }

    }, [configId, requestId]);

    const getParameters = async (hasSelectedRadiology) => {
        const isRadiologyTests =   hasSelectedRadiology  ? 'Y' : 'N';
        const res = await popupService.getParameters({isRadiologyTests});
        if (validate.isNotEmpty(res) && validate.isNotEmpty(res.responseData) && "SUCCESS" == res.statusCode) {
            return res.responseData
        } else {
            return [];
        }
    };

    const getConfigurationByConfigId = (configId) => {
        popupService.getConfigRequestsByConfigId({ configId }).then(async res => {
            if (validate.isNotEmpty(res) && validate.isNotEmpty(res.responseData) && "SUCCESS" == res.message) {
                let popupConfiguration = res.responseData;
                let configRequest = {};
                configRequest['popupConfiguration'] = popupConfiguration;
                configRequest['configId'] = popupConfiguration.configId;
                configRequest['popUpConfigurationName'] = popupConfiguration.popUpConfigurationName;
                configRequest['applicableType'] = popupConfiguration.applicableType;
                setConfigurationInfo(configRequest);
                setToDate(new Date(popupConfiguration.toDate));
                setTempToDate(new Date(popupConfiguration.toDate));
                if (validate.isNotEmpty(res.responseData?.definedTrigger?.definedTriggerRules)) {
                    const definedTriggerRules = res.responseData.definedTrigger.definedTriggerRules;
                    setCustomerIdsDefinedTrigger(definedTriggerRules.find(eachRule => "CUSTOMER" == eachRule.definedTriggerType));
                    setPatientGenderDefinedTrigger(definedTriggerRules.find(eachRule => "PATIENT_GENDER" == eachRule.definedTriggerType));
                    setPatientAgeDefinedTrigger(definedTriggerRules.find(eachRule => "PATIENT_AGE" == eachRule.definedTriggerType));
                    if (validate.isNotEmpty(definedTriggerRules.find(eachRule => "PARAMETER_RESULT" == eachRule.definedTriggerType)) || validate.isNotEmpty(definedTriggerRules.find(eachRule => "EXCLUDE_PARAMETER" == eachRule.definedTriggerType))) {
                        const parametersTrigger = definedTriggerRules?.find(eachRule => "INCLUSIVE" == eachRule.parameterRule);
                        const exParamtersTrigger = definedTriggerRules?.find(eachRule => "EXCLUSIVE" == eachRule.parameterRule);
                        setParametersDefinedTrigger(parametersTrigger);
                        setExParametersDefinedTrigger(exParamtersTrigger);
                        let parameters = await getParameters();
                        if (validate.isNotEmpty(parametersTrigger)) {
                             prepareParametersInfo(parametersTrigger.parameterDetails,parameters);
                        }
                        if (validate.isNotEmpty(exParamtersTrigger)) {
                            if(checkIfExcludeParametersHasRadiologyTests(exParamtersTrigger.parameterDetails)){
                               let excludeParameters = await getParameters(true);
                                prepareExParametersInfo(exParamtersTrigger.parameterDetails,excludeParameters);
                            }
                            else{
                                prepareExParametersInfo(exParamtersTrigger.parameterDetails,parameters);
                            }
                        }
                    }
                }
            } else {
                setConfigurationInfo({});
                setToDate(new Date());
                setTempToDate(new Date());
                setCustomerIdsDefinedTrigger({});
                setPatientAgeDefinedTrigger({});
                setPatientGenderDefinedTrigger({});
                setParametersDefinedTrigger({});
                setExParametersDefinedTrigger({});
            }
        }).catch(err => {
            console.log('Error while fetching configuration request ', err);
            setConfigurationInfo({});
            setToDate(new Date());
            setTempToDate(new Date());
            setCustomerIdsDefinedTrigger({});
            setPatientAgeDefinedTrigger({});
            setPatientGenderDefinedTrigger({});
            setParametersDefinedTrigger({});
            setExParametersDefinedTrigger({});
        })
    }

    const getConfigurationByRequestId = (requestId) => {
        popupService.getConfigRequestsOnRequestId({ requestId }).then(async res => {
            if (validate.isNotEmpty(res) && validate.isNotEmpty(res.responseData) && "SUCCESS" == res.message) {
                setConfigurationInfo(res.responseData);
                if (validate.isNotEmpty(res.responseData?.popupConfiguration?.definedTrigger?.definedTriggerRules)) {
                    const definedTriggerRules = res.responseData.popupConfiguration.definedTrigger.definedTriggerRules;
                    setCustomerIdsDefinedTrigger(definedTriggerRules.find(eachRule => "CUSTOMER" == eachRule.definedTriggerType));
                    setPatientGenderDefinedTrigger(definedTriggerRules.find(eachRule => "PATIENT_GENDER" == eachRule.definedTriggerType));
                    setPatientAgeDefinedTrigger(definedTriggerRules.find(eachRule => "PATIENT_AGE" == eachRule.definedTriggerType));
                    if (validate.isNotEmpty(definedTriggerRules.find(eachRule => "PARAMETER_RESULT" == eachRule.definedTriggerType)) || validate.isNotEmpty(definedTriggerRules.find(eachRule => "EXCLUDE_PARAMETER" == eachRule.definedTriggerType))) {
                        const parametersTrigger = definedTriggerRules?.find(eachRule => "INCLUSIVE" == eachRule.parameterRule);
                        const exParamtersTrigger = definedTriggerRules?.find(eachRule => "EXCLUSIVE" == eachRule.parameterRule);
                        setParametersDefinedTrigger(parametersTrigger);
                        setExParametersDefinedTrigger(exParamtersTrigger);
                        let parameters = await getParameters();
                        if (validate.isNotEmpty(parametersTrigger)) {
                            prepareParametersInfo(parametersTrigger.parameterDetails, parameters);
                        }
                        if (validate.isNotEmpty(exParamtersTrigger)) {
                            if(checkIfExcludeParametersHasRadiologyTests(exParamtersTrigger.parameterDetails)){
                               let excludeParameters = await getParameters(true);
                                prepareExParametersInfo(exParamtersTrigger.parameterDetails,excludeParameters);
                            }
                            else{
                                prepareExParametersInfo(exParamtersTrigger.parameterDetails,parameters);
                            }
                        }
                    }
                }
                if ('PENDING' != res.responseData.configRequestStatus) {
                    setShowApproveOrRejectButton(false);
                }
            } else {
                setConfigurationInfo({});
                setCustomerIdsDefinedTrigger({});
                setPatientAgeDefinedTrigger({});
                setPatientGenderDefinedTrigger({});
                setParametersDefinedTrigger({});
                setExParametersDefinedTrigger({});
            }
        }).catch(err => {
            console.log('Error while fetching configuration request ', err);
            setConfigurationInfo({});
            setCustomerIdsDefinedTrigger({});
            setPatientAgeDefinedTrigger({});
            setPatientGenderDefinedTrigger({});
            setParametersDefinedTrigger({});
            setExParametersDefinedTrigger({});
        })
    }

    useEffect(() => {
        if (validate.isNotEmpty(configurationInfo?.popupConfiguration?.regions)) {
            const filteredKeys = configurationInfo.popupConfiguration.regions.filter(key => {
                return filterRegionText.some(text => key.toUpperCase().includes(text.toUpperCase()));
            });
            const filteredRegionsInfo = [];
            filteredKeys.forEach(key => {
                filteredRegionsInfo.push(key);
            });
            setFilteredRegions(filteredRegionsInfo)
        }
    }, [filterRegionText]);

    useEffect(() => {
        if (validate.isNotEmpty(configurationInfo?.popupConfiguration?.regions) && configurationInfo.popupConfiguration.regions.length <= 15) {
            setFilterRegionText([]);
        }
    }, [filteredRegions]);


    useEffect(() => {
        if (validate.isNotEmpty(customerIdsDefinedTrigger?.selectedCustomerIds)) {
            const str_customerIds = [];
            customerIdsDefinedTrigger?.selectedCustomerIds.map(each => {
                str_customerIds.push(String(each));
            })
            const filteredKeys = str_customerIds.filter(key => {
                return filterCustomerText.some(text => key.includes(text));
            });
            const filteredCustomersInfo = [];
            filteredKeys.forEach(key => {
                filteredCustomersInfo.push(key);
            });
            setFilterCustomers(filteredCustomersInfo);
        }

    }, [filterCustomerText]);

    useEffect(() => {
        if (validate.isNotEmpty(customerIdsDefinedTrigger?.selectedCustomerIds) && customerIdsDefinedTrigger.selectedCustomerIds.length <= 15) {
            setFilterCustomerText([]);
        }
    }, [filterCustomers]);


    const firstLetterCapitalize = text => {
        if (validate.isNotEmpty(text)) {
            return text.toLowerCase()[0].toUpperCase() + text.slice(1).toLowerCase();
        }
    }

    const approveConfiguration = (requestId) => {
        setApproveLoader(true);
        popupService.approveConfiguration({ requestId }).then(res => {
            if(validate.isNotEmpty(res)){
                setShowApproveOrRejectButton("SUCCESS" == res?.statusCode ? false : true);
                setStackedToastContent({toastMessage: validate.isNotEmpty(res.message) ? res.message : ""});
            }
            setApproveLoader(false);
        }).catch(err => {
            setShowApproveOrRejectButton(true);
            setApproveLoader(false);
            console.log(`Error while Approving Configuration ${requestId}`, err);
        })
    }

    const rejectConfiguration = (requestId, comments) => {
        setRejectLoader(true);
        popupService.rejectConfiguration({ requestId: requestId, comments: comments }).then(res => {
            if(validate.isNotEmpty(res)){
                setShowApproveOrRejectButton("SUCCESS" == res?.statusCode ? false : true);
                setStackedToastContent({toastMessage: validate.isNotEmpty(res.message) ? res.message : ""});
            }
            setRejectLoader(false);
            setOpenModal(false);
        }).catch(err => {
            setShowApproveOrRejectButton(true);
            setRejectLoader(false);
            setOpenModal(false);
            console.log(`Error while Rejecting Configuration ${requestId}`, err);
        })
    }

    const filterRegion = (event) => {
        let inputRegionTxt = event.target.value?.toUpperCase();
        event.target.value = inputRegionTxt;
        setFilterRegionText(inputRegionTxt.split(",").map(value => value.trim()).filter(key => key != ""))
    }

    const onClearRegionFilter = () => {
        setFilterRegionText([]);
        const regionInput = document.getElementById('StoreIds');
        if (regionInput) {
            regionInput.value = '';
        }
    }

    const filterCustomer = (event) => {
        let inputCustomerTxt = event.target.value;
        event.target.value = inputCustomerTxt;
        setFilterCustomerText(inputCustomerTxt.split(",").map(value => value.trim()).filter(key => key != ""))
    }

    const onClearCustomerFilter = () => {
        setFilterCustomerText([]);
        const customerInput = document.getElementById('customerIds');
        if (customerInput) {
            customerInput.value = '';
        }
    }

    const getMaxDate = () => {
        let date = new Date(toDate.getFullYear(), toDate.getMonth(), toDate.getDate());
        date.setHours(toDate.getHours(), toDate.getMinutes(), toDate.getSeconds(), toDate.getMilliseconds());
        return date;
    }

    const updateInitialValues = () => {
        if (validate.isNotEmpty(configurationInfo?.popupConfiguration) && validate.isNotEmpty(configurationInfo?.popupConfiguration?.toDate)) {
            helpers.updateValue(configurationInfo?.popupConfiguration?.toDate, 'dateRangeGrp');

        }
    }

    const RangeRegexType = (type) => {
        switch (type) {
          case "BETWEEN":
            return 1;
          case "NOT_BETWEEN":
            return 2;
          case "EQUAL_TO":
            return 3;
          case "NOT_EQUAL_TO":
            return 4;
          case "GREATER_THAN":
            return 5;
          case "LESS_THAN":
            return 6;
          case "GREATER_THAN_OR_EQUAL_TO":
            return 7;
          case "LESS_THAN_OR_EQUAL_TO":
            return 8;
        }
      }

    const ParameterType = (type) =>{
        switch(type){
            case "PARAMETER" :
                return "P";
            case "TEST" :
                return "T";
        }
    } 

    const prepareParametersInfo = (inclusiveParameters, parameters) => {
        if (validate.isEmpty(inclusiveParameters)) {
            return [];
        }
        else {
            const selectedParamters = [];
            inclusiveParameters.map(each => {
                const tempParamObj = {};
                tempParamObj['parameterId'] = each.parameterId;
                tempParamObj['parameterName'] = parameters[each.parameterId];
                tempParamObj['rangeType'] = RangeRegexType(each.rangeType);
                tempParamObj['minValue'] = each.minValue;
                tempParamObj['maxValue'] = each.maxValue;
                tempParamObj['absoluteValue'] = each.absoluteValue;
                tempParamObj['time'] = each.time;
                selectedParamters.push(tempParamObj);
            })
            setPatameterResult(selectedParamters);
        }
    }

    const checkIfExcludeParametersHasRadiologyTests = (excludeParameters)=> {
        return excludeParameters.some(param => "TEST" === param.parameterType);
    }

    const prepareExParametersInfo = (exParameters,parameters) => {
        if (validate.isEmpty(exParameters)) {
            return;
        }
        else {
            const selectedExParamters = [];
            exParameters.map(each => {
                const tempParamObj = {};
                tempParamObj['parameterId'] = each.parameterId;
                tempParamObj['parameterName'] = parameters[each.parameterId];
                tempParamObj['parameterType'] = ParameterType(each.parameterType)
                tempParamObj['time'] = each.time;
                selectedExParamters.push(tempParamObj);
            })
            setExcludeParameterResult(selectedExParamters);
        }
    }

    const handleUpdate = async (event) => {
        event.preventDefault();
        let formData = new FormData();
        let configInfo = {};
        let popupConfiguration = configurationInfo.popupConfiguration;
        configInfo['configId'] = popupConfiguration.configId;
        configInfo['toDate'] = tempToDate.getTime();
        formData.append('popupPathlabConfiguration', JSON.stringify(configInfo));
        const response = await popupService.updateConfiguration(formData);
        setLoading(true);
        ResponseHandler(setStackedToastContent).handleResponse(response, {}, () => {
            setLoading(false);
            setStackedToastContent({ toastMessage: 'Path Labs popup configuration updated successfully.' });
            props.history.push({ pathname: POPUP_URLS.viewConfigurations, state: {
                searchCriteria: props.location.state?.searchCriteria,
                id: configId
              } });
        }, (error) => {
            setLoading(false);
            setStackedToastContent({ toastMessage: error });
        })
    }

    const customHtmlMap = {

        'dateRangeGrp': [['INSERT_IN', () => {
            return (
                <div className="d-flex row g-3">
                    <CustomDateTimePicker handleDateChange={setTempToDate} title={'To Date'} defaultDateTime={toDate} pickerProps={{ readOnly: false }} minDate={new Date()} maxDate={getMaxDate()} />
                    {<div className="col-2">
                        <button disabled={isLoading} style={{ height: '50px' }} onClick={(event) => handleUpdate(event)} className="btn btn-primary mt-4">
                            {false ? <CustomSpinners spinnerText={'Update'} className={"spinner-position"} innerClass={"invisible"} /> : 'Update'}
                        </button></div>}
                </div>
            )
        }]]
    }

    const observersMap = {
        'toDateForm': [['load', updateInitialValues]],
    }

    const goBack = () => {
        props.history.push({
            pathname: validate.isNotEmpty(requestId) ? POPUP_URLS.viewConfigurationRequests : POPUP_URLS.viewConfigurations,
            state: {
                searchCriteria: props.location.state?.searchCriteria,
                id: configId,
                isBackClicked: true
            },
        });
    }

    const redirectToEditConfiguration = (applicableType, reqId) => {
        if (validate.isNotEmpty(applicableType) && validate.isNotEmpty(reqId)) {
            props.history.push({
                pathname: `${POPUP_URLS.editConfiguration}/request/${reqId}`,
                state: { ageGroups: patientAgeGroups, isEdit: true, applicableType: applicableType, searchCriteria: props.location.state?.searchCriteria },

            });
        }
    }

    const retrieveOverallParamsResultsCombinationType = (overallCondition) =>{
        if(validate.isEmpty(overallCondition)){
            return "";
        }
        if("OVERALL_AND_PARAMTER_AND" === overallCondition){
            return "AND";
        }
        if("OVERALL_AND_PARAMTER_OR" === overallCondition){
            return "OR";
        }
    }

    const getParameterResultWithTimeInMonth = () => {
        const updatedResults = parameterResult?.map(result => ({
          ...result, 
          time: result.time / 30 ,
          rangeType: getParamRangeById(result?.rangeType) 
        }));
        return updatedResults;
      };

      const getExclusionParameterResultwithParameterType = () => {
        const updatedResults = excludeParameterResult?.map(result => ({
          ...result, 
          parameterType: "P" === result?.parameterType ? "Pathology" : "Radiology"
        }));
        return updatedResults;
      };

      function getParamRangeById(rangeId) {
        const rangeValue = Object.keys(rangeTypeMap).find(key => rangeTypeMap[key] == rangeId);
        return rangeValue;
      }

    return (
        <>
            <HeaderComponent ref={headerRef} className="d-flex align-items-center px-1 py-2 border-bottom">
                <Button variant=" " className="btn-link icon-hover me-3" onClick={() => { goBack() }}> <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                    <g id="leftarrow_black_icon_18px" transform="translate(-48.941 -316.765)">
                        <rect id="BG_Guide" data-name="BG Guide" width="18" height="18" transform="translate(48.941 316.765)" fill="none" />
                        <path id="Path_22927" data-name="Path 22927" d="M56.566,319.236a.686.686,0,0,0-.448.178l-6.977,6.53a.7.7,0,0,0,0,.984l6.977,6.44a.709.709,0,0,0,.984-.089.7.7,0,0,0,0-.984l-5.546-5.188H66.226a.716.716,0,0,0,0-1.431H51.557l5.635-5.188a.635.635,0,0,0,.269-.536,1.275,1.275,0,0,0-.179-.537A4.04,4.04,0,0,0,56.566,319.236Z" transform="translate(0 -0.471)" fill="#080808" />
                    </g>
                </svg></Button>
                {requestId && <p className="mb-0">Configuration Request - <strong>{requestId}</strong></p>}
                {configId && <p className="mb-0">Configuration ID - <strong>{configId}</strong></p>}
                {/* {(validate.validateRole(POPUP_ROLES.createConfiguration) && validate.isNotEmpty(configurationInfo) && ['PENDING','REJECTED'].includes(configurationInfo.configRequestStatus)) && <div style={{ marginLeft: "auto" }}>
                    <button style={{ height: '50px' }} className={"btn btn-primary"} onClick={() => (redirectToEditConfiguration(configurationInfo.applicableType, requestId))}>
                        {false ? <CustomSpinners spinnerText={'Edit'} className={"spinner-position"} innerClass={"invisible"} /> : 'Edit'}
                    </button>
                </div>} */}
            </HeaderComponent>
            <BodyComponent className="body-height" allRefs={{ headerRef, footerRef }}  >
                {
                    validate.isNotEmpty(configurationInfo) &&
                    <>
                        {validate.isNotEmpty(configurationInfo.applicableType) && <div className="mb-4"><label className="mb-2 custom-fieldset">Applicable Type</label><p className="mb-0 "><mark className="px-2">{firstLetterCapitalize(configurationInfo.applicableType)}</mark></p></div>}

                        {validate.isNotEmpty(configurationInfo.popupConfiguration) && <label className="mb-2 custom-fieldset">Template Information</label>}
                        <div className="row g-3 mb-4">
                            {validate.isNotEmpty(configurationInfo.popUpConfigurationName) && <div className="d-flex flex-column col-3"><p className="mb-0 font-12 text-secondary">Popup Configuration Name</p> <p className="mb-0 font-14">{configurationInfo.popUpConfigurationName}</p></div>}
                            {validate.isNotEmpty(configurationInfo.popupConfiguration) &&
                                <React.Fragment>
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.templateId) && <div className="d-flex flex-column col-3"><label className=" font-12 text-secondary">Template ID</label> <p className="mb-0 font-14">{configurationInfo.popupConfiguration.templateId}</p></div>}
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.templateName) && <div className="d-flex flex-column col-3"><label className="font-12 text-secondary">Template Name</label> <p className="mb-0 font-14">{configurationInfo.popupConfiguration.templateName}</p></div>}
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.status) && <div className="d-flex flex-column col-3"><label className=" font-12 text-secondary">Status</label> <p className="mb-0 font-14">{firstLetterCapitalize(configurationInfo.popupConfiguration.status)}</p></div>}
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.fromDate) && <div className="d-flex flex-column col-3"><label className=" font-12 text-secondary">From Date</label> <p className="mb-0 font-14">{dateFormat(configurationInfo.popupConfiguration.fromDate, 'mmm d, yyyy HH:MM')}</p></div>}
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.toDate) && validate.isNotEmpty(requestId) && <div className="d-flex flex-column col-3"><label className=" font-12 text-secondary">To Date</label> <p className="mb-0 font-14">{dateFormat(configurationInfo.popupConfiguration.toDate, 'mmm d, yyyy HH:MM')}</p></div>}
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.toDate) && validate.isNotEmpty(configId) && !isEdit && <div className="d-flex flex-column col-3"><label className=" font-12 text-secondary">To Date</label> <p className="mb-0 font-14">{dateFormat(configurationInfo.popupConfiguration.toDate, 'mmm d, yyyy HH:MM')}</p></div>}
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.toDate) && validate.isNotEmpty(configId) && isEdit && <DynamicForm formJson={obj} helpers={helpers} customHtml={customHtmlMap} observers={observersMap} />}
                                </React.Fragment>
                            }

                        </div>

                        {validate.isNotEmpty(configurationInfo.popupConfiguration?.channels) && <>
                            <p className="mb-2 custom-fieldset">Selected Channels</p>
                            <div className="d-flex flex-wrap gap-3 mb-4" style={{ maxHeight: '200px', overflowY: 'auto' }}>
                                {configurationInfo.popupConfiguration.channels.map(channel => {
                                    return (
                                        <React.Fragment>
                                            {validate.isNotEmpty(channel) && <button className="btn btn-light btn-sm rounded-5">{channel.includes('_') ? firstLetterCapitalize(channel.replace('_', ' ')) : firstLetterCapitalize(channel)}</button>}
                                        </React.Fragment>
                                    )
                                })}
                            </div>
                        </>}

                        {validate.isNotEmpty(configurationInfo.popupConfiguration?.regions) &&
                            <>
                                {configurationInfo.popupConfiguration.regions.length > 15 && <Filter objCount={configurationInfo.popupConfiguration.regions.length} handleFilter={filterRegion} clearFilteredData={onClearRegionFilter} removeObj={{}} disableFields={true} filteredDataCount={filterRegion.length} filterText={filterRegionText} placeHolderText={"Filter Region"} id={'StoreIds'} {...props} />}
                                {filterRegionText?.length > 0
                                    ?
                                    <div className='mb-2'>
                                        <ViewRecords info={filteredRegions} props={props} displayText={"Regions Information"} />
                                    </div>
                                    :
                                    <div className='mb-2'>
                                        <ViewRecords info={configurationInfo.popupConfiguration?.regions} props={props} displayText={"Regions Information"} />
                                    </div>
                                }
                            </>
                        }
                        {
                            "TIME_INTERVAL" == configurationInfo?.popupConfiguration?.triggerType ? 
                                <>
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration?.triggerValue) &&
                                        <div className="mb-2">
                                            <p className="custom-fieldset mb-2">Selected Predefined Time Interval - {cronstrue.toString(configurationInfo.popupConfiguration.triggerValue)}</p>
                                        </div>
                                    }
                                </> :
                                <>
                                    {validate.isNotEmpty(customerIdsDefinedTrigger) &&
                                    <React.Fragment>
                                        {"FEW" == customerIdsDefinedTrigger?.customerSelection ?
                                            <>
                                                {customerIdsDefinedTrigger.selectedCustomerIds.length > 15 && <Filter objCount={customerIdsDefinedTrigger.selectedCustomerIds.length} handleFilter={filterCustomer} clearFilteredData={onClearCustomerFilter} removeObj={{}} disableFields={true} filteredDataCount={filterCustomer.length} filterText={filterCustomerText} placeHolderText={"Filter Customer"} id={'customerIds'} {...props} />}
                                                {filterCustomerText?.length > 0
                                                    ?
                                                    <div className='mb-2'>
                                                        <ViewRecords info={filterCustomers} props={props} displayText={"Customers Information"} />
                                                    </div>
                                                    :
                                                    <div className='mb-2'>
                                                        <ViewRecords info={customerIdsDefinedTrigger.selectedCustomerIds} props={props} displayText={"Customers Information"} />
                                                    </div>
                                                }
                                            </>
                                            : <><label className='custom-fieldset mb-2 col-2'>All Customers</label></>
                                        }
                                    </React.Fragment>
                                }
                                {validate.isNotEmpty(patientGenderDefinedTrigger) && validate.isNotEmpty(patientGenderDefinedTrigger.genders) && <div className="mb-4">
                                    <p className="custom-fieldset mb-2">Selected Patient Genders</p>
                                    <div className="d-flex flex-wrap gap-3" style={{ maxHeight: '200px', overflowY: 'auto' }}>
                                        {patientGenderDefinedTrigger.genders.map(gender => {
                                            return (
                                                <React.Fragment>
                                                    {validate.isNotEmpty(gender) && <button className="btn btn-light btn-sm rounded-5">{firstLetterCapitalize(gender)}</button>}
                                                </React.Fragment>
                                            )
                                        })}
                                    </div></div>
                                }

                                {validate.isNotEmpty(patientAgeDefinedTrigger) && validate.isNotEmpty(patientAgeDefinedTrigger.patientAgeRule) &&
                                    <React.Fragment>
                                        <p className="custom-fieldset mb-2">Selected Patient Age Groups</p>
                                        <div className="d-flex flex-wrap gap-3 mb-4" style={{ maxHeight: '200px', overflowY: 'auto' }}>
                                            {patientAgeDefinedTrigger.patientAgeRule.map(ageGroup => {
                                                return (
                                                    <React.Fragment>
                                                        <button className="btn btn-light btn-sm rounded-5">{patientAgeGroups?.[ageGroup]}</button>
                                                    </React.Fragment>
                                                )
                                            })}
                                        </div>
                                    </React.Fragment>
                                }

                                { validate.isNotEmpty(paramtersDefinedTrigger) && validate.isNotEmpty(paramtersDefinedTrigger.parameterDetails) && validate.isNotEmpty(getParameterResultWithTimeInMonth()) &&
                                    <React.Fragment>
                                        <p className="custom-fieldset mb-2">Selected Parameters</p>
                                        <div className="card mb-4">
                                            <DynamicGridHeight id="parameterResult" metaData={parameterResultData} dataSet={[...getParameterResultWithTimeInMonth()]}>
                                                <CommonDataGrid {...({ ...parameterResultData })} dataSet={[...getParameterResultWithTimeInMonth()]} callBackMap={{}} />
                                            </DynamicGridHeight>
                                            {validate.isNotEmpty(paramtersDefinedTrigger.combinationType) && <p className="mb-0 card-footer font-14 bg-white border-top-0">Selected Parameter Combination - <mark className="px-2"><strong>{paramtersDefinedTrigger.combinationType}</strong></mark></p>}
                                        </div>
                                    </React.Fragment>
                                }
                                {validate.isNotEmpty(exParamtersDefinedTrigger) && validate.isNotEmpty(exParamtersDefinedTrigger.parameterDetails) && validate.isNotEmpty(getExclusionParameterResultwithParameterType()) &&
                                    <React.Fragment>
                                        <p className="custom-fieldset mb-2">Selected Excluded Trigger </p>
                                        <div className="card mb-4">
                                            <DynamicGridHeight id="exParameterResult" metaData={excludeParameterResultData} dataSet={[...getExclusionParameterResultwithParameterType()]}>
                                                <CommonDataGrid {...({ ...excludeParameterResultData })} dataSet={[...getExclusionParameterResultwithParameterType()]} callBackMap={{}} />
                                            </DynamicGridHeight>
                                            {validate.isNotEmpty(exParamtersDefinedTrigger.combinationType) && <p className="mb-0 card-footer font-14 bg-white border-top-0">Selected Excluded Trigger Combination - <mark className="px-2"><strong>{exParamtersDefinedTrigger.combinationType}</strong></mark></p>}
                                        </div>
                                    </React.Fragment>
                                }
                                {validate.isNotEmpty(paramtersDefinedTrigger) && validate.isNotEmpty(exParamtersDefinedTrigger) && validate.isNotEmpty(configurationInfo?.popupConfiguration?.definedTrigger?.combinationType) &&
                                    <>
                                        <p className="mb-0 card-footer font-14 bg-white border-top-0">Overall Parameter Result Combination - <mark className="px-2"><strong>{retrieveOverallParamsResultsCombinationType(configurationInfo?.popupConfiguration?.definedTrigger?.combinationType)}</strong></mark></p>
                                    </>

                                }
                            </>
                        }
                        {validate.isNotEmpty(configurationInfo.comments) && <>
                            <p className="mb-2 custom-fieldset">Comment</p>
                            <div className="d-flex flex-wrap gap-3 mb-4" style={{ maxHeight: '200px', overflowY: 'auto' }}>
                                {configurationInfo.comments}
                            </div>
                        </>}
                    </>
                }
                {openModal && <RejectPopupModal openModal={openModal} setOpenModal={setOpenModal} ModalName={"Configuration"} requestId={requestId} templateName={configurationInfo?.popupConfiguration?.templateName} configurationName={configurationInfo?.popUpConfigurationName} reject={rejectConfiguration}/>}
            </BodyComponent>
            {showApproveOrRejectButton && validate.isNotEmpty(requestId) &&<FooterComponent ref={footerRef} className="d-flex justify-content-end border-top gap-3 p-2">
                
                    <React.Fragment>
                        {!rejectLoader ? <button className="btn btn-sm brand-secondary py-2 px-4" onClick={() => { setOpenModal(true) }}>Reject</button> : <CustomSpinners spinnerText={"Reject"} className={" spinner-position"} innerClass={"invisible"} />}
                        {!approveLoader ? <button className="btn btn-sm btn-success py-2 px-4" onClick={() => { approveConfiguration(configurationInfo.requestId) }}>Approve</button> : <CustomSpinners spinnerText={"Approve"} className={" spinner-position"} innerClass={"invisible"} />}
                    </React.Fragment>
            </FooterComponent>
                }
        </>
    )
}

export default withFormHoc(ViewPathlabConfiguration);