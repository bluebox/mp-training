import { CancelIcon } from "@medplus/react-common-components/DataGrid";
import DocumentUpload from "@medplus/react-common-components/DocumentUpload";
import DynamicForm, { CustomSpinners, withFormHoc } from "@medplus/react-common-components/DynamicForm";
import React, { useContext, useEffect, useRef, useState } from "react";
import { Button, Modal } from "react-bootstrap";
import Cron from "react-js-cron";
import { UncontrolledTooltip } from "reactstrap";
import { ACTIVE, CLOSED, DEFINED_TRIGGER, INACTIVE, STATUS, TIME_INTERVAL } from "../../constants/PromotionConstants";
import { POPUP_FORM_URLS, POPUP_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import PopupService from "../../services/PopupService";
import { REQUEST_TYPE } from "../../services/ServiceConstants";
import { AlertContext } from "../Contexts/UserContext";
import RegionsWrapper from "../RegionsWrapper";
import { BodyComponent, FooterComponent, HeaderComponent } from "../common/CommonStructure";
import CustomDateTimePicker from "../common/CustomDateTimePicker";
import InfoIcon from "../common/InfoIcon";
import ParametersForPathLabConfigurationForm from "./ParametersForPathLabConfigurationForm";
import PreviewTemplate from "./PreviewTemplate";
import ApplicableType from "./ApplicableType";
import ExcludeParameterForPathLabConfForm from "./ExcludeParameterForPathLabConfForm";
import BackButton from "../../images/leftarrow_black_icon_18px.svg";
import DownloadIcon from "../../images/download_cloud_icon.svg";
import KeyboardShortcuts from "../../helpers/KeyboardShortcuts";
import {StoreCategoryTypes } from "../../constants/MarketingConstant";

const CREATE_CONFIG_PATH = '/marketing/ui/create-configuration';

const CreatePathLabConfiguration = ({ helpers, ...props }) => {
  const headerRef = useRef(null);
  const footerRef = useRef(null);
  const [isLoading, setLoading] = useState(false);
  const [cronValue, setCronValue] = useState("");
  const { setStackedToastContent } = useContext(AlertContext);
  const [isDisablePreviewBtn, setDisablePreviewBtn] = useState(true);
  const [isNoteModalOpen, setNoteModalOpen] = useState(false);
  const validate = Validate();
  const [isShowPreview, setShowPreview] = useState(false);
  const [templateInfo, setTemplateInfo] = useState({});
  const popupService = PopupService();
  const [documentTrigger, setDocumentTrigger] = useState(false);
  const [regionsDownloadUrl, setRegionsDownloadUrl] = useState(undefined);
  const [customerIdsDownloadUrl, setCustomerIdsDownloadUrl] = useState(undefined);
  const [selectedCustomerType, setSelectedCustomerType] = useState('all');
  const excelData = useRef({
    'uploadRegions': '',
    'uploadCustomerIds': '',
    'removeRegions': '',
    'removeCustomerIds': '',
  })
  const [isEditForm, setEditForm] = useState(false);
  const [isCloneForm, setCloneForm] = useState(false);
  const [id, setId] = useState(0);
  const [regionsInfo, setRegionsInfo] = useState({});
  const [prevSelectedRegionType, setPrevSelectedRegionType] = useState("");
  const [modal, setModal] = useState(false);
  const [removeRegionsMap, setRemoveRegionsMap] = useState({});
  const [existingRegions, setExistingRegions] = useState([]);
  const [selectedPatientGender, setSelectedPatientGender] = useState([]);
  const [selectedChannel, setSelectedChannel] = useState([]);
  const [inclusiveExclusivecombination, setInclusiveExclusivecombinationCombination] = useState('');
  const [parameters, setParameters] = useState({});
  const [triggerType, setTriggerType] = useState(undefined)
  const [ageRanges, setAgeRanges] = useState([])
  const [parameterResult, setPatameterResult] = useState([])
  const [excludeParameterResult, setExcludeParameterResult] = useState([])
  const [parameterResultCombinationType, setParameterResultCombinationType] = useState(undefined)
  const [excludeParameterResultCombinationType, setExcludeParameterResultCombinationType] = useState(undefined)
  const [templateName, setTemplateName] = useState("");
  const [templateId,setTemplateId] = useState();
  const patientAgeGroups = props?.location?.state?.ageGroups;
  const [maximumInclusiveParametersSize, setMaximumInclusiveParametersSize] = useState(undefined)
  const [maximumExclusiveParametersSize, setMaximumExclusiveParametersSize] = useState(undefined)
  const [popupConfigurationInfo ,setPopupConfigurationInfo] = useState({});
  const [selectedCustomerIds, setSelectedCustomerIds] = useState(undefined);
  const [hasSelectedRadiology,setHasSelectedRadiologyTests] = useState(null);
  const [radiologyTests,setRadiologyTests] = useState({});

  useEffect(()=> {
    getParametersAndRadiologyTests();
  },[]);

  useEffect(() => {
    if(hasSelectedRadiology!=null){
      getParametersAndRadiologyTests();
    }
}, [hasSelectedRadiology]);

  const getParametersAndRadiologyTests = async () => {
    const isRadiologyTests =   hasSelectedRadiology  ? 'Y' : 'N';
    if(isRadiologyTests && validate.isNotEmpty(radiologyTests) || !isRadiologyTests && validate.isNotEmpty(parameters)){
      return;
    }
    const response = await popupService.getParameters({isRadiologyTests});
    ResponseHandler(setStackedToastContent).handleResponse(response, {}, (data) => hasSelectedRadiology ? setRadiologyTests(data) : setParameters(data), (error) => setStackedToastContent({ toastMessage: error }));
  };

  useEffect(() => {
    const timeInterval = ["timeInterval"];

    if (validate.isNotEmpty(selectedChannel)) {
      const onlyLabPosOrCrm = selectedChannel.every(
        channel => channel.toLowerCase() === 'labpos' || channel.toLowerCase() === 'crm'
      );

      if (onlyLabPosOrCrm) {
        helpers.showOptions("triggerType", timeInterval);

        if (validate.isNotEmpty(triggerType)) {
          if (triggerType === DEFINED_TRIGGER) {
            helpers.updateValue(DEFINED_TRIGGER, "triggerType");
            setTriggerType(DEFINED_TRIGGER);
          } else {
            helpers.updateValue(TIME_INTERVAL, "triggerType");
            setTriggerType(TIME_INTERVAL);
          }
        }
      } else {
        helpers.hideOptions("triggerType", timeInterval);
        helpers.updateValue(DEFINED_TRIGGER, "triggerType");
        setTriggerType(DEFINED_TRIGGER);
        onTriggerTypeChange(DEFINED_TRIGGER);
      }
    }
  }, [selectedChannel, triggerType]);

  

  const fetchConfigurationRequestBasedOnId = ()=> {
    if(validate.isEmpty(props.match.params.reqId)){
      return;
    }
    if("pathlabs" == props.applicableType){
      popupService.getConfigRequestsOnRequestId({ requestId: props?.match?.params?.reqId }).then(res => {
        if (validate.isNotEmpty(res) && validate.isNotEmpty(res.responseData) && "SUCCESS" == res.statusCode) {
          setPopupConfigurationInfo(res.responseData?.popupConfiguration);
          if(validate.isNotEmpty(props.applicableType)){
            props.setApplicableType(res.responseData?.applicableType?.toLowerCase());
            setEditForm(true);
            setConfigurationInfo(res.responseData?.popupConfiguration,true);
          }
        } else {
          setPopupConfigurationInfo({});
          if (validate.isNotEmpty(res.message)) {
            setStackedToastContent({ toastMessage: res.message });
          }
        }
      }).catch(error => {
        console.log(error);
        setPopupConfigurationInfo({});
      });
    }
  }


useEffect(()=>{
  if(parameterResult.length > 1){
    helpers.showElement("triggerParameters")
  }else{
    setParameterResultCombinationType(undefined)
    helpers.updateValue(null, "triggerParameters")
    helpers.hideElement("triggerParameters");
  }
},[parameterResult])

useEffect(() => {
  if(validate.isNotEmpty(parameterResult) && validate.isNotEmpty(excludeParameterResult)){
    helpers.showElement('combinationValue');
    helpers.showElement('combinationGrp');
  }else{
    helpers.hideElement('combinationValue');
    helpers.hideElement('combinationGrp');
  }
},[parameterResult?.length,excludeParameterResult?.length])

  const getDefaultFromDate = () => {
    let date = new Date();
    date.setMinutes(date.getMinutes() + 10);
    return date;
  }

  const getDefaultToDate = () => {
    let date = new Date();
    date.setDate(date.getDate() + 90);
    date.setHours(23);
    date.setMinutes(59);
    date.setSeconds(59);
    return date;
  }

  const [fromDate, setFromDate] = useState(getDefaultFromDate());
  const [toDate, setToDate] = useState(getDefaultToDate());

  const getMaxDate = () => {
    let date = new Date();
    date.setFullYear(2100);
    return date;
  }

  const reset = () => {
    helpers.hideElement('triggerCustomerGrp');
    helpers.hideElement('triggerCronGrp');
    helpers.hideElement('uploadCustomerExcelGrp');
    helpers.hideElement('uploadRegionExcelGrp');
    helpers.hideElement('regionsGrp');
    helpers.showElement('pathLabConfigurationForm');
    helpers.resetForm('pathLabConfigurationForm', false, true, true, true);
    setFromDate(getDefaultFromDate());
    setToDate(getDefaultToDate());
    setRegionsInfo({});
    setId(0);
    setCronValue("");
    setSelectedCustomerIds([]);
    setEditForm(false);
    setCloneForm(false);
    excelData.current['uploadRegions'] = ''
    excelData.current['uploadCustomerIds'] = ''
    excelData.current['removeRegions'] = ''
    excelData.current['removeCustomerIds'] = ''
    helpers.updateKeyValuesToAllFields('disabled', false, 'pathLabConfigurationForm');
    setShowPreview(false);
    handleSelectTemplateName(null);
    setPrevSelectedRegionType("");
    setModal(false);
    setRemoveRegionsMap({});
    setExistingRegions([]);
    setPatameterResult([])
    setExcludeParameterResult([])
    setParameterResultCombinationType(undefined)
    setExcludeParameterResultCombinationType(undefined)
    setInclusiveExclusivecombinationCombination('')
  }

  const handleSelectTemplateName = (value, valuesList) => {
    setTemplateName(value?.[0]);
    const selectedOption = valuesList?.find(item => item.value === value[0]);
    const id = selectedOption ? selectedOption.id : '';
    setTemplateId(id)
    setDisablePreviewBtn(validate.isEmpty(id));
  }

  const goBack = () => {
    props.history.push({
      pathname: isCloneForm ? POPUP_URLS.viewConfigurations : POPUP_URLS.viewConfigurationRequests,
      state: {
        searchCriteria: props?.location?.state?.searchCriteria,
        ageGroups: patientAgeGroups,
        applicableType : props.applicableType,
        id: id,
        isBackClicked: true
      },
    });
  }

  const toggle = (showPreview) => {
    showPreview ? helpers.hideElement('pathLabConfigurationForm') : helpers.showElement('pathLabConfigurationForm');
    if (showPreview && validate.isNotEmpty(helpers.getHtmlElementValue('templateName'))) {
      setTemplateInformation(templateId);
    }
    setShowPreview(showPreview);
  }

  const toggleModal = () => {
    helpers.updateValue('N', 'isExcelUploadForRegions');
    onRegionSelectionChange("N");
    setModal(!modal);
  }

  const setTemplateInformation = async (templateId) => {
    const response = await popupService.getTemplateInfo({ 'templateId': templateId });
    ResponseHandler(setStackedToastContent).handleResponse(response, {}, (data) => setTemplateInfo(data), (error) => setStackedToastContent({ toastMessage: error }));
  }

  const getNotesInfo = (height) => {
    return (
      <div class="customdropdown-position position-absolute text-secondary bottom " style={{ bottom: `${height}px` }}>
        <h6 class="text-dark font-14">Notes Information</h6>
        <ol>
          <li>Regions - Excel file contains one column with StoreID.</li>
          <li>Store Ids: Comma Separated list of Store ID's.</li>
          <li>Filter Regions: Comma Separated list of search items which will be enabled if the count of selected regions is greater than 15.</li>
          <li>Customers - Excel file contains one column with customerID.</li>
          <li>Maximum Inclusion parameters allowed per configuration is {maximumInclusiveParametersSize}, and Maximum Exclusion triggers allowed per configuration is {maximumExclusiveParametersSize}.</li>
        </ol>
      </div>
    )
  }

  const validateConfigurationForm = (configInfo) => {
    if (validate.isEmpty(configInfo)) {
      setStackedToastContent({ toastMessage: 'Please enter mandatory fields.' });
      setLoading(false)
      return true;
    }
    if (validate.isEmpty(configInfo.name)) {
      setStackedToastContent({ toastMessage: 'Please enter valid configuration name.' });
      setLoading(false)
      return true;
    }
    if (validate.isEmpty(configInfo.templateName)) {
      helpers.updateErrorMessage('Please select template from list.', 'templateId');
      setStackedToastContent({ toastMessage: 'Please select template from list.' })
      setLoading(false)
      return true;
    }
    if (TIME_INTERVAL === configInfo.triggerType && (validate.isEmpty(cronValue) || '* ' === cronValue.substring(0, 2) || '0-59' === cronValue.substring(0, 4))) {
      setStackedToastContent({
        toastMessage: 'Currently we are not allowing every minute as trigger Value. Please select proper cron value.',
      })
      setLoading(false)
      return true;
    }
    if (validate.isEmpty(fromDate) || validate.isEmpty(toDate)) {
      setStackedToastContent({ toastMessage: 'From Date / To Date is mandatory.', })
      setLoading(false)
      return true;
    }
    if (new Date(fromDate) < new Date()) {
      setStackedToastContent({ toastMessage: 'From Date should be greater than current time.' })
      setLoading(false)
      return true;
    }
    if (fromDate >= toDate) {
      setStackedToastContent({ toastMessage: 'From Date should be less than To Date.' })
      setLoading(false)
      return true;
    }
    if (toDate <= new Date()) {
      setStackedToastContent({ toastMessage: 'To Date should be more than current time.' })
      setLoading(false)
      return true;
    }
    if ('N' == configInfo.isExcelUploadForRegions && validate.isEmpty(regionsInfo)) {
      setStackedToastContent({ toastMessage: 'Please select regions.' });
      setLoading(false)
      return true;
    }
    if (!isCloneForm && configInfo.isExcelUploadForRegions == 'Y' && excelData.current?.uploadRegions === '' && (!isEditForm || prevSelectedRegionType !== configInfo.isExcelUploadForRegions)) {
      setStackedToastContent({ toastMessage: 'Please select excel file to upload regions.' })
      setLoading(false)
      return true;
    }
    if (isEditForm || isCloneForm) {
      return false;
    }
    return false;
  }

  const checkConfigurationName = async (name) => {
    if (validate.isNotEmpty(name)) {
      let response = await popupService.checkConfigurationName({ 'configurationName': name }).catch((err) =>
        setStackedToastContent({ toastMessage: 'Unable to check popup configuration name, please try again.' }));
      return ResponseHandler(setStackedToastContent).handleResponse(response, {}, () => { },
        (error) => {
          console.log(error);
          if (error.includes("already"))
            helpers.updateErrorMessage(error, 'name');
          setLoading(false)
          setStackedToastContent({ toastMessage: error });
        }
      );
    }
  }

  const prepareMapToList = (regionsInfo) => {
    let regions = [];
    Object.keys(regionsInfo).map(region => {
      regions.push(region);
    });
    return regions;
  }
  
  const pathaLabChannelConstant = {
    'labPOS': 'L',
    'crm': 'C',
    'web': 'W',
    'mobile': 'M'
};

  const GenderConstant = {
    "male" : "M",
    "female" : "F",
    "others" : "O"
  }

const processChannels = (configChannels) => {
    if (!configChannels || !Array.isArray(configChannels)) {
        return '';
    }
    const channels = configChannels.map(channel => pathaLabChannelConstant[channel] || channel);
    return channels.join(',');
};

const processGender = (configGender) => {
  if (!configGender || !Array.isArray(configGender)) {
      return '';
  }
  const genders = configGender.map(gender => GenderConstant[gender] || gender);
  return genders.join(', ');
};

  
const getPathLabConfigurationObj = (configInfo) => {
  let regions = 'Y' === configInfo?.isExcelUploadForRegions ? existingRegions : prepareMapToList(regionsInfo);
  regions = regions?.join(",");

  const triggeredInformation = {
    customerSelection: "allCustomers" === configInfo?.triggerCustomerValue ? "All" : "Few",
    gender: processGender(configInfo?.triggerPatientGender),
    patientAge: configInfo?.patientAge,
    includeParameterResult: getParameterResultWithCombination(),
    excludeParameterResult: getExclusionParameterResultWithCombination(),
    paramtersCombination: "or" === inclusiveExclusivecombination ? "1" : "0",
  };

    const pathlabConfig = {
      applicableType: "2",
      configRequestStatus: "Pending",
      popUpConfigurationName: configInfo?.name,
      templateId: templateId,
      templateName: templateName,
      channel:  processChannels(configInfo?.channel),
      regions: regions,
      triggerType: configInfo?.triggerType,
      triggerValue: TIME_INTERVAL === configInfo.triggerType? cronValue : configInfo?.combinationValue,
      configId: configInfo?.configId,
      removeRegions:  JSON.stringify(configInfo?.removeRegions),
      fromDate: fromDate.getTime(),
      toDate: toDate.getTime(),
    };
    if(props?.location?.state?.isEdit){
        pathlabConfig['requestId'] = props?.match?.params?.reqId;
    }else if (isCloneForm) {
      pathlabConfig['cloneRefId'] = id;
    }
    if(validate.isNotEmpty(selectedCustomerIds)){
      triggeredInformation['selectedCustomerIds'] = selectedCustomerIds.join(',')
    }
    if(DEFINED_TRIGGER == configInfo.triggerType){
      pathlabConfig['definedTriggerInformation'] = JSON.stringify(triggeredInformation);
    }
    return pathlabConfig;
  };



  const handleSubmitAction = async (event) => {
    event.preventDefault();
    setLoading(true);
    let configInfo = helpers.validateAndCollectValuesForSubmit('pathLabConfigurationForm');
    if (validate.isNotEmpty(configInfo) && validate.isEmpty(configInfo?.combinationValue)) {
      configInfo['combinationValue'] = 'and';
    }
    if (validateConfigurationForm(configInfo) || !(isEditForm || await checkConfigurationName(configInfo.name.trim()))) {
      return;
    }
    const patientAgeRange = helpers.getHtmlElement("patientAge")
    const patientAgeRule = patientAgeRange.values.filter(item =>
      patientAgeRange.value.includes(item.value) && item.id !== "defaultAll"
    ).map(item => parseInt(item.id, 10));

    const rules = {};
    patientAgeRange.values.forEach(item => {
      if (patientAgeRange.value.includes(item.value) && item?.id != "defaultAll") {
        const [minAge, maxAge] = item.value.split('-').map(Number);
        rules[item.id] = {
          AgeMinValue: minAge,
          AgeMaxValue: maxAge
        };
      }
    });
    const result = {
      patientAgeRule: patientAgeRule,
      rules: rules
    };
    configInfo['patientAge'] = JSON.stringify(result);
    configInfo['regionsInfo'] = 'Y' === configInfo.isExcelUploadForRegions ? existingRegions : prepareMapToList(regionsInfo);
    configInfo['removeRegions'] = prepareMapToList(removeRegionsMap);

    if (isEditForm) {
      configInfo['requestId'] = id;
    } else if (isCloneForm) {
      configInfo['cloneReferenceId'] = id;
    }
    const popupConfigurationRequest = getPathLabConfigurationObj(configInfo);
    
    if(validate.isNotEmpty(parameterResult) && parameterResult.length > 1 && validate.isEmpty(parameterResultCombinationType)){
      setLoading(false);
      setStackedToastContent({ toastMessage: 'Enter the combination for inclusive parameter results.' });
      return;
    }
    if(validate.isNotEmpty(excludeParameterResult) && excludeParameterResult.length > 1 && validate.isEmpty(excludeParameterResultCombinationType)){
      setLoading(false);
      setStackedToastContent({ toastMessage: 'Enter the combination for exclusive parameter results.' });
      return;
    }
    if(validate.isNotEmpty(excludeParameterResult) && validate.isNotEmpty(parameterResult) && validate.isEmpty(inclusiveExclusivecombination)){
      setLoading(false);
      setStackedToastContent({ toastMessage: 'Enter the combination for inclusive and exclusive parameter results.' });
      return;
    }

    let formData = new FormData();
    if ('Y' === configInfo.isExcelUploadForRegions) {
      formData.append('uploadRegionsExcel', excelData.current?.uploadRegions);
    }

    if (DEFINED_TRIGGER === configInfo.triggerType) {
      formData.append('uploadCustomerIdsExcel', excelData.current?.uploadCustomerIds);
      formData.append('removeCustomerIdsExcel', excelData.current?.removeCustomerIds);
    }

    if (validate.isNotEmpty(configInfo.requestId) || validate.isNotEmpty(configInfo.cloneReferenceId)) {
      formData.append('removeRegionsExcel', excelData.current?.removeRegions);
      formData.append('removeCustomerIdsExcel', excelData.current?.removeCustomerIds);
    }
    if(validate.isNotEmpty(templateId) && validate.isNotEmpty(templateName)){
      configInfo['templateId'] = templateId;
      configInfo['templateName'] = templateName;
    }
    formData.append('popupPathlabConfiguration', JSON.stringify(popupConfigurationRequest));
    const response = isEditForm ? await popupService.updatePopUpConfigRequest(formData) : await popupService.createPopupConfigRequest(formData);
    ResponseHandler(setStackedToastContent).handleResponse(response, {}, 
      (data) => {
      setLoading(false);
      setStackedToastContent({ toastMessage: `Path Labs Popup Configuration ${isEditForm ? 'Updated' : isCloneForm? 'Cloned' : 'Created'} Successfully.` });
      props.history.push({
        pathname: POPUP_URLS.viewConfigurationRequests,
        state: {
          searchCriteria: props.location.state?.searchCriteria,
          id: id
        },
      });
    }, (error) => {
      setLoading(false);
      setStackedToastContent({ toastMessage: error });
    })
  }
 
  const getChannels = channels => {
      const channelInfo = [];
      channels?.map(eachChannel=> {
        switch(eachChannel){
          case "WEB":
            channelInfo.push("web");
            break;
          case "MOBILE":
            channelInfo.push("mobile");
            break;
          case "CRM":
            channelInfo.push("crm");
            break;
          case "LAB_POS":
          channelInfo.push("labPOS");
          break;
          default:
          break;

        }
      })
      setSelectedChannel(channelInfo)
      return channelInfo;
  }

  const getGenders = genders => {
    const genderInfo = [];
    genders.map(eachGender => {
      switch (eachGender) {
        case "MALE":
          genderInfo.push("male");
          break;
        case "FEMALE":
          genderInfo.push("female");
          break;
        case "OTHERS":
          genderInfo.push("others");
          break;
        default:
          break;
      }
    })
    return genderInfo;
  }

  const rangeType = {
    "BETWEEN": 1,
    "NOT_BETWEEN": 2,
    "EQUAL_TO": 3,
    "NOT_EQUAL_TO": 4,
    "GREATER_THAN": 5,
    "LESS_THAN": 6,
    "GREATER_THAN_OR_EQUAL_TO": 7,
    "LESS_THAN_OR_EQUAL_TO": 8
  };

  const parameterType = {
    "PARAMETER" : "P",
    "TEST" : "T"
  }

  const getParametersMap = async (isRadiology) => {
    const isRadiologyTests =   isRadiology  ? 'Y' : 'N';
    const res = await popupService.getParameters({isRadiologyTests});
    if (validate.isNotEmpty(res) && validate.isNotEmpty(res.responseData) && "SUCCESS" == res.statusCode) {
        return res.responseData
    } else {
        return [];
    }
};

  const prepareParametersInfo = async(inclusiveParameters) => {
    if(validate.isEmpty(inclusiveParameters)){
      setPatameterResult([]);
    }
    else{
      const selectedParamters = [];
      let parameters = await getParametersMap();
      inclusiveParameters.map(each=> {
        const tempParamObj ={};
        tempParamObj['parameterName'] = props.location.state?.isClone ? parameters[each.parameterId] : each.parameterName;
        tempParamObj['absoluteValue'] = each.absoluteValue;
        tempParamObj['parameterId'] = each.parameterId;
        tempParamObj['rangeType'] =  parseInt(rangeType[each.rangeType]);
        tempParamObj['minValue'] = each.minValue;
        tempParamObj['maxValue'] = each.maxValue;
        tempParamObj['time'] = each.time;
        selectedParamters.push(tempParamObj);
      })
      setPatameterResult(selectedParamters);
    }
  }

  const prepareExParametersInfo = async(exParameters) => {
    if(validate.isEmpty(exParameters)){
      setExcludeParameterResult([]);
    }
    else{

      let selectedExParamters = [];
      let isRadiology = exParameters.some(param=>param.parameterType=='TEST');
      let parameters = await getParametersMap(isRadiology);
      exParameters.map(each=> {
        const tempParamObj ={};
        tempParamObj['parameterName'] = props.location.state?.isClone ? parameters[each.parameterId] : each.parameterName;
        tempParamObj['parameterId'] = each.parameterId;
        tempParamObj['parameterType'] = parameterType[each.parameterType]
        tempParamObj['time'] = each.time;
        selectedExParamters.push(tempParamObj);
      });
      setHasSelectedRadiologyTests(isRadiology);
      setExcludeParameterResult(selectedExParamters);
    }
  }

  const prepareDefinedTriggerType = (type) => {
    switch(type){
      case "DEFINED_TRIGGER":
          return "definedTrigger";
      case "TIME_INTERVAL":
          return "timeInterval";
      case "PRODUCT":
        return "product";
    }
  }
    
  const setConfigurationInfo = (configInfo) => {
    if(validate.isNotEmpty(configInfo) && CREATE_CONFIG_PATH != props.path){
      let formValues = {...configInfo, 
        'templateId': configInfo?.templateId,
        'templateName': [configInfo?.templateName],
        'name' : configInfo?.popUpConfigurationName?.toString(),
        'status': "ACTIVE" == configInfo.status ? "A" :"I",
        'isExcelUploadForRegions': validate.isNotEmpty(configInfo?.isExcelUploadForRegions)? "Y" :"N",
        'channel':getChannels(configInfo.channels),
        'triggerType' : prepareDefinedTriggerType(configInfo.triggerType),
        };
        if (props?.location?.state?.isEdit || props?.location?.state?.isClone ) {
          if (DEFINED_TRIGGER == formValues.triggerType) {
            const definedTriggerRules = configInfo?.definedTrigger?.definedTriggerRules;
            const customerTrigger = definedTriggerRules?.find(eachRule => "CUSTOMER" == eachRule.definedTriggerType);
            const patientGenderTrigger = definedTriggerRules?.find(eachRule => "PATIENT_GENDER" == eachRule.definedTriggerType);
            const patientAgeTrigger = definedTriggerRules?.find(eachRule => "PATIENT_AGE" == eachRule.definedTriggerType);
            const parametersTrigger = definedTriggerRules?.find(eachRule => "INCLUSIVE" == eachRule.parameterRule);
            const exParamtersTrigger = definedTriggerRules?.find(eachRule => "EXCLUSIVE" == eachRule.parameterRule);
            if(validate.isNotEmpty(parametersTrigger) && validate.isNotEmpty(exParamtersTrigger)){
              if("OVERALL_AND_PARAMTER_AND" == configInfo?.definedTrigger?.combinationType){
                setInclusiveExclusivecombinationCombination("and")
              }
              else if("OVERALL_AND_PARAMTER_OR" == configInfo?.definedTrigger?.combinationType){
                setInclusiveExclusivecombinationCombination("or")
              }else{
                setInclusiveExclusivecombinationCombination(undefined)
              }
            }
            if (validate.isNotEmpty(parametersTrigger)) {
              prepareParametersInfo(parametersTrigger.parameterDetails);
              setParameterResultCombinationType("AND" == parametersTrigger?.combinationType ? "and" : "or");
            }
            if (validate.isNotEmpty(exParamtersTrigger)) {
              prepareExParametersInfo(exParamtersTrigger.parameterDetails);
              setExcludeParameterResultCombinationType("AND" == exParamtersTrigger?.combinationType ? "and" : "or");
            }
            formValues['triggerCustomerValue'] = validate.isNotEmpty(customerTrigger?.customerSelection) ? "ALL" == customerTrigger.customerSelection ? "allCustomers" : "fewCustomers" : '';
            formValues['uploadCustomerIds'] = validate.isNotEmpty(customerTrigger?.selectedCustomerIds) ? customerTrigger?.selectedCustomerIds : '';
            setSelectedCustomerIds(customerTrigger?.selectedCustomerIds)
            formValues['triggerPatientGender'] = validate.isNotEmpty(patientGenderTrigger?.genders) ? getGenders(patientGenderTrigger.genders) : '';
            formValues['patientAge'] = validate.isNotEmpty(patientAgeTrigger?.patientAgeRule) ? patientAgeTrigger.patientAgeRule.map(eachAgeGrp => patientAgeGroups[eachAgeGrp]): '';
            setAgeRanges(formValues['patientAge']);
            const names = helpers.getHtmlElement("patientAge")?.values?.map(range => range.name);
            const allAgeRanges = names.filter(range => range !== "All")
            const selectedAgeRanges = patientAgeTrigger.patientAgeRule.map(eachAgeGrp => patientAgeGroups[eachAgeGrp]);
            const ifAll = selectedAgeRanges.length === allAgeRanges.length && selectedAgeRanges.every((value, index) => value === allAgeRanges[index]);
            if (ifAll) {
              formValues['patientAge'] = names;
              setAgeRanges(names)
              helpers.disableOptions("patientAge", allAgeRanges)
            }else{
              helpers.enableOptions("patientAge", names)
            }

            formValues['combinationValue'] = validate.isNotEmpty(configInfo?.definedTrigger?.combinationType) ? "OR" == configInfo?.definedTrigger?.combinationType ? "or" : "and" : 'and';
          } else {
            formValues['definedTrigger'] = '';
            formValues['triggerCustomerValue'] = '';
            formValues['uploadCustomerExcelGrp'] = '';
            formValues['triggerPatientGender'] = '';
            formValues['patientAge'] = '';
            formValues['combinationValue'] = '';
            formValues['patientAge'] = '';
            formValues['triggerPatientGender'] = [];
            formValues['triggerCustomerValue'] =''; 
            formValues['triggerCustomerGrp'] = '';
            formValues['patientAge'] = [];
            setSelectedCustomerType('');
          }
        }
    if(validate.isNotEmpty(formValues.uploadCustomerIds)){
      formValues['customerSelection'] ="Few";
    }

    setFromDate( new Date(formValues.fromDate) < new Date() ? getDefaultFromDate() : new Date(formValues.fromDate));
    setToDate(new Date(formValues.toDate) < new Date() ? getDefaultToDate() : new Date(formValues.toDate));
    if (TIME_INTERVAL === formValues.triggerType) {
      setCronValue(formValues.triggerValue)
      formValues.triggerValue = '';
    }
    helpers.updateSpecificValues(formValues, 'pathLabConfigurationForm');
    setTemplateId(configInfo?.templateId);
    setDisablePreviewBtn(validate.isEmpty(configInfo?.templateId));
    setTemplateName(configInfo?.templateName);
    if(props?.location?.state?.isEdit){
      formValues['requestId'] = props?.match?.params?.reqId;
    }
    onTriggerTypeChange(formValues.triggerType);
    onRegionSelectionChange(formValues.isExcelUploadForRegions);
    if(validate.isNotEmpty(configInfo.configId) && 0 != configInfo.configId){
      setId(configInfo.configId);
    }else{
      setId(props?.match?.params?.reqId);
    }
    onCustomerSelection(formValues.triggerCustomerValue)
    setSelectedCustomerIds(formValues.uploadCustomerIds);
    setEditForm(props.location.state?.isEdit);
    setCloneForm(props.location.state?.isClone);
    setExistingRegions(formValues.regions);
    if (validate.isNotEmpty(formValues.regions)) {
      let resultMap = formValues.regions.reduce((map, key) => {
        map[key] = "";
        return map;
      }, {});
      setRegionsInfo(resultMap);
    }
      disableFields(formValues);
    }
  }

  const disableFields = (formValues) => {
    if (props.location.state?.isEdit) {
      setPrevSelectedRegionType(formValues.isExcelUploadForRegions);
    } else if (props.location.state?.isClone) {
      helpers.updateValue('', 'name', false);
    }
  }

  const confirmationRegionModal = (value) => {
    if (isEditForm && 'N' === prevSelectedRegionType && 'Y' === helpers.getHtmlElementValue("isExcelUploadForRegions") && validate.isNotEmpty(regionsInfo)) {
      toggleModal();
      return;
    }
    onRegionSelectionChange(value);
  }

  const onRegionSelectionChange = (value) => {
    if ('Y' === value) {
      helpers.showElement('uploadRegionExcelGrp');
      helpers.hideElement('regionsGrp');
    } else {
      helpers.hideElement('uploadRegionExcelGrp');
      helpers.showElement('regionsGrp');
    }
  }

  const handleRegionConfirmation = () => {
    setModal(false);
    setRemoveRegionsMap(regionsInfo);
    setRegionsInfo({});
    onRegionSelectionChange("Y");
    helpers.updateValue('Y', 'isExcelUploadForRegions');
  }

  const onTriggerTypeChange = (value) => {
    setTriggerType(value)
    if (DEFINED_TRIGGER === value) {
      helpers.showElement('triggerCustomerGrp');
      helpers.hideElement('triggerCronGrp');
      helpers.showElement('triggerPatientGenderGrp');
      helpers.showElement('triggerPatientAgeGrp');
      helpers.updateSingleKeyValueIntoField('required', true, 'triggerCustomerValue');
      helpers.updateSingleKeyValueIntoField('required', true, 'combinationGrp')
      helpers.updateSingleKeyValueIntoField('required', true, 'triggerValue');
    } else {
      helpers.hideElement('triggerCustomerGrp');
      helpers.showElement('triggerCronGrp');
      helpers.hideElement('triggerPatientGenderGrp');
      helpers.hideElement('triggerPatientAgeGrp');
      helpers.updateSingleKeyValueIntoField('required', false, 'combinationGrp')
      helpers.updateSingleKeyValueIntoField('required', false, 'triggerCustomerValue');
      helpers.updateSingleKeyValueIntoField('required', false, 'triggerValue');
    }
  }

  const onCustomerSelection = (value) => {
    if ("fewCustomers" == value) {
      setSelectedCustomerType('few');
      helpers.showElement('uploadCustomerExcelGrp');
    } else {
      setSelectedCustomerType('all');
      helpers.hideElement('uploadCustomerExcelGrp');
    }
    helpers.updateSingleKeyValueIntoField('required', true, 'triggerValue');
  }

  const handlePatientAge = (payload) => {
    const value = payload[0]?.target.value;
    const checked = payload[0]?.target.checked;
    const patientAgeRanges = helpers.getHtmlElement("patientAge")?.values;
    const names = patientAgeRanges.map(range => range.name);
    const currentAgeRanges = ageRanges;
    if (validate.isNotEmpty(value) && "All" === value) {
      const allAgeRanges = names.filter(range => range !== "All")
        if (checked) {
            helpers.disableOptions("patientAge",allAgeRanges)
            setAgeRanges(names);
            helpers.updateValue(names, "patientAge");
        } else {
            helpers.enableOptions("patientAge", allAgeRanges)
            setAgeRanges([]);
            helpers.updateValue([], "patientAge", false);
        }
    } else {
        if (checked) {
            const updatedRanges = [...currentAgeRanges, value].filter(range => range !== "All");
            setAgeRanges(updatedRanges);
            helpers.updateValue(updatedRanges, "patientAge");
        } else {
            const updatedRanges = currentAgeRanges.filter(range => range !== value && range !== "All");
            setAgeRanges(updatedRanges);
            helpers.updateValue(updatedRanges, "patientAge");
        }
    }
};

  const onPatientGenderSelection = (isGenderSelected, genderVal) => {
    if (isGenderSelected) {
      setSelectedPatientGender([...selectedPatientGender, genderVal]);
    } else {
      const updatedGenders = selectedPatientGender.filter(eachGender => eachGender !== genderVal);
      setSelectedPatientGender(updatedGenders);
    }
  }

  const onChannelSelection = (isChannelSelected, channelVal) => {
    if (isChannelSelected) {
      setSelectedChannel([...selectedChannel, channelVal]);
    } else {
      const updatedChannels = selectedChannel.filter(eachChannel => eachChannel !== channelVal);
      setSelectedChannel(updatedChannels);

    }
  }

  const getParameterResultWithCombination = () => {
    if (validate.isEmpty(parameterResult)) {
      return;
    }
    const parameterResultObject = {};
    parameterResultObject.parameterResult = parameterResult;
    if (parameterResult.length > 1) {
      parameterResultObject.combinationType = "or" === parameterResultCombinationType ? "1" : "0";
    }
    return JSON.stringify(parameterResultObject);
  };
  
  const getExclusionParameterResultWithCombination = () => {
    const excludeParameterResultObject = {};
    if (validate.isEmpty(excludeParameterResult)) {
      return;
    }
    excludeParameterResultObject.excludeParameterResult = excludeParameterResult;
    if (excludeParameterResult.length > 1) {
      excludeParameterResultObject.combinationType = "or" === excludeParameterResultCombinationType ? "1" : "0";
    }
    return JSON.stringify(excludeParameterResultObject);
  };


  const validateMaximumParameterResult = (response) =>{
    if(validate.isEmpty(response) || validate.isEmpty(response?.data) || validate.isEmpty(response?.data?.responseData)){
      return;
    }
    setMaximumExclusiveParametersSize(response?.data?.responseData?.EXCLUSIVE_PARAMETERS_LENGTH)
    setMaximumInclusiveParametersSize(response?.data?.responseData?.INCLUSIVE_PARAMETERS_LENGTH)
  }
  

  const observerMap = {
    'channel': [['click', (payload) => onChannelSelection(payload[0].target.checked, payload[0].target.value)]],
    'triggerType': [['change', (payload) => onTriggerTypeChange(payload[0].target.value)]],
    'triggerCustomerValue': [['change', (payload) => onCustomerSelection(payload[0].target.value)]],
    'triggerPatientGender': [['click', (payload) => onPatientGenderSelection(payload[0].target.checked, payload[0].target.value)]],
    'patientAge': [['change', (payload) => handlePatientAge(payload)]],
    'isExcelUploadForRegions': [['change', (payload) => confirmationRegionModal(payload[0].target.value)]],
    'templateName': [['select', (payload) => handleSelectTemplateName(payload[0].target.value, payload[1]?.values)], ['change', (payload) => setDisablePreviewBtn(true)]],
    'name': [['blur', (payload) => checkConfigurationName(payload[0].target.value.trim())]],
    'pathLabConfigurationForm': [['load', () => props.location.state?.isClone ? setConfigurationInfo(props?.location?.state?.popupConfigurationInfo) : fetchConfigurationRequestBasedOnId()]],
    "RESPONSE" : [["RESPONSE", validateMaximumParameterResult]],
  };

  const onRegionsUpload = (files) => {
    if (validateFiles(files)) {
      excelData.current['uploadRegions'] = files[0];
    }
  }

  const onRegionsRemove = (files) => {
    if (validateFiles(files)) {
      excelData.current['removeRegions'] = files[0];
    }
  }

  const onCustomerIdsUpload = (files) => {
    if (validateFiles(files)) {
      excelData.current['uploadCustomerIds'] = files[0];
    }
  }

  const onCustomerIdsRemove = (files) => {
    if (validateFiles(files)) {
      excelData.current['removeCustomerIds'] = files[0];
    }
  }

  const validateFiles = (files) => {
    if (validate.isEmpty(files)) {
      setStackedToastContent({ toastMessage: 'Please attach a .xls, .xlsx file' });
      return false;
    }
    if (!(files[0].name.includes(".xls") || files[0].name.includes(".xlsx"))) {
      setStackedToastContent({ toastMessage: 'File type not supported. Only .xls, .xlsx types are allowed!!!' });
      return false;
    }
    return true;
  }

  const downloadUrl = (url, type) => {
    const a = document.createElement('a');
    a.href = url;
    a.setAttribute('download', `Popup-Configuration-${type}[${id}].xlsx`);
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
  }

  const downloadRegions = async (event) => {
    event.preventDefault();
    if (validate.isEmpty(regionsDownloadUrl)) {
      const response = await popupService.getRegionsExcel({ 'id': id }).catch((error) => {
        setStackedToastContent({ toastMessage: 'Unable to download regions excel, please try again' })
      })
      if (validate.isNotEmpty(response) && response.status === 200) {
        const url = window.URL.createObjectURL(new Blob([response.data], { type: 'application/force-download' }));
        setRegionsDownloadUrl(url);
        downloadUrl(url, 'regions');
      } else {
        setStackedToastContent({ toastMessage: 'Unable to download regions excel, please try again' });
      }
    } else {
      downloadUrl(regionsDownloadUrl, 'regions');
    }
  }

  const downloadCustomerIds = async (event) => {
    event.preventDefault();
    if (validate.isEmpty(customerIdsDownloadUrl)) {
      const response = await popupService.getCustomerIdsExcel({ 'id': id }).catch((error) => {
        setStackedToastContent({ toastMessage: 'Unable to download Customer IDs excel, please try again' })
      })
      if (validate.isNotEmpty(response) && response.status === 200) {
        const url = window.URL.createObjectURL(new Blob([response.data], { type: 'application/force-download' }));
        setCustomerIdsDownloadUrl(url);
        downloadUrl(url, 'customerIds');
      } else {
        setStackedToastContent({ toastMessage: 'Unable to download Customer IDs excel, please try again' });
      }
    } else {
      downloadUrl(customerIdsDownloadUrl, 'customerIds');
    }
  }

  const handleCombinationChange = (event) => {
    setInclusiveExclusivecombinationCombination(event.target.value);
  };


  const customHtmlMap = {
    'triggerCronGrp': [['INSERT_IN', () => {
      return (
        <>
          <div className='d-flex flex-wrap align-items-center my-1'>
            <Cron allowedDropdowns={['period', 'months', 'month-days', 'hours', 'minutes',]}
              allowedPeriods={['year', 'month', 'day', 'hour']}
              clearButtonAction={'empty'}
              defaultPeriod={'day'}
              value={cronValue} setValue={setCronValue} className={'my-project-cron something'} clearButtonProps={{ type: 'default' }} locale={{ clearButtonText: 'Reset' }}
            />
          </div>
        </>
      )
    }]],
    'uploadRegionExcelGrp': [['INSERT_IN', () => {
      return (
        <div className='d-flex px-3 py-0 col-8 justify-content-between'>
          <div>
          {((isEditForm || isCloneForm) && validate.isNotEmpty(regionsInfo)) && <label className='pb-2 small text-secondary'>Upload</label>}
            <DocumentUpload
              buttonClassName={'scan-button'}
              imageContainerClassName={'image-container'}
              includeLightBox={false}
              fileSelectOption={true}
              singleFileUpload={true}
              isAppendAllowed={false}
              loadingStatus={(isLoading) => { setDocumentTrigger(!isLoading) }}
              getAddedDocuments={documentTrigger}
              hidden={true}
              allowedFileFormats={'.xls,.xlsx'}
              onSuccessResponse={(files) => validate.isNotEmpty(files) && onRegionsUpload(files)}
              onErrorResponse={(message) => { setStackedToastContent({ toastMessage: message, }) }}
              removedFileResponse={(file) => { excelData.current['uploadRegions'] = '' }}
            />
          </div>
          {(isEditForm || isCloneForm) && validate.isNotEmpty(regionsInfo) &&
            <div>
              <label className='pb-2 small text-secondary'>Remove</label>
              <DocumentUpload
                buttonClassName={'scan-button'}
                imageContainerClassName={'image-container'}
                includeLightBox={false}
                fileSelectOption={true}
                singleFileUpload={true}
                isAppendAllowed={false}
                getAddedDocuments={documentTrigger}
                loadingStatus={(isLoading) => { setDocumentTrigger(!isLoading) }}
                allowedFileFormats={'.xls,.xlsx'}
                onSuccessResponse={(files) => validate.isNotEmpty(files) && onRegionsRemove(files)}
                onErrorResponse={(message) => { setStackedToastContent({ toastMessage: message }) }}
                removedFileResponse={(file) => { excelData.current['removeRegions'] = '' }}
              />
            </div>}
          {(isEditForm || isCloneForm) && validate.isNotEmpty(regionsInfo) && <div id='regionsDownload'>
            <p className='small text-secondary'>Existing Regions</p>
            <button className='btn btn-light' onClick={(event) => downloadRegions(event)}><img src={DownloadIcon} alt="Download Icon" /> Download Regions </button>
          </div>}
        </div>
      )
    }]],
    'uploadCustomerExcelGrp': [['INSERT_IN', () => {
      return (
        <div className='d-flex px-3 py-2 col-8 justify-content-between'>
          <div>
          {((isEditForm || isCloneForm) && validate.isNotEmpty(selectedCustomerIds)) && <label className='pb-2 small text-secondary'>Upload</label>}
            <DocumentUpload
              buttonClassName={'scan-button'}
              imageContainerClassName={'image-container'}
              includeLightBox={false}
              fileSelectOption={true}
              singleFileUpload={true}
              isAppendAllowed={false}
              loadingStatus={(isLoading) => { setDocumentTrigger(!isLoading) }}
              getAddedDocuments={documentTrigger}
              allowedFileFormats={".xls,.xlsx"}
              onSuccessResponse={(files) => validate.isNotEmpty(files) && onCustomerIdsUpload(files)}
              onErrorResponse={(message) => { setStackedToastContent({ toastMessage: message }) }}
              removedFileResponse={(file) => { excelData.current['uploadCustomerIds'] = '' }}
            />
          </div>
          {(isEditForm || isCloneForm) && validate.isNotEmpty(selectedCustomerIds) &&
            <div>
              <label className='pb-2 small text-secondary'>Remove</label>
              <DocumentUpload
                buttonClassName={'scan-button'}
                imageContainerClassName={'image-container'}
                includeLightBox={false}
                fileSelectOption={true}
                singleFileUpload={true}
                isAppendAllowed={false}
                getAddedDocuments={documentTrigger}
                loadingStatus={(isLoading) => { setDocumentTrigger(!isLoading) }}
                allowedFileFormats={'.xls,.xlsx'}
                onSuccessResponse={(files) => validate.isNotEmpty(files) && onCustomerIdsRemove(files)}
                onErrorResponse={(message) => { setStackedToastContent({ toastMessage: message }) }}
                removedFileResponse={(file) => { excelData.current['removeCustomerIds'] = '' }}
              />
            </div>}
          {(isEditForm || isCloneForm) && validate.isNotEmpty(selectedCustomerIds) && <div id='regionsDownload'>
            <p className='small text-secondary'>Download CustomerIds</p>
            <button className='btn btn-light' onClick={(event) => downloadCustomerIds(event)}><img src={DownloadIcon} alt="Download Icon" /> Download CustomerIds </button>
          </div>}
        </div>
      )
    }]],
    'regionsGrp': [['INSERT_IN', () => {
      return (<>
        <RegionsWrapper {...props} applicableType={"pathlabs" == props.applicableType ? '6' : '5'} isFromPathLabsConfig={true} ignoreHeight={true} showStoreLevel={ validate.isNotEmpty(selectedChannel) && selectedChannel.length === 1  && selectedChannel.includes("labPOS")} setLoading={() => { }} regions={regionsInfo} handleRegionsChange={(regionsMap) => setRegionsInfo(regionsMap)} isNotEligibleRegionsRequired={false} storeCategoryTypes={[StoreCategoryTypes.CS,StoreCategoryTypes.LAB_TO_CS]} />
      </>
      )
    }]],
    'dateRangeGrp': [['INSERT_IN', () => {
      return (
        <div className="d-flex row g-3">
          <CustomDateTimePicker handleDateChange={setFromDate} title={'From Date'} defaultDateTime={fromDate} pickerProps={{ readOnly: false }} minDate={new Date()} maxDate={getMaxDate()} />
          <CustomDateTimePicker handleDateChange={setToDate} title={'To Date'} defaultDateTime={toDate} pickerProps={{ readOnly: false }} minDate={new Date()} maxDate={getMaxDate()} />
        </div>
      )
    }]]
  }

  const handleChangeApplicableType = (value) => {
    props.setApplicableType(value);
    setDisablePreviewBtn(true);
    setTemplateId("");
    setTemplateName("");
  }

  return (
    <>
      <HeaderComponent className={`border-bottom py-2 d-flex align-items-center justify-content-between ${!isShowPreview && (isEditForm || isCloneForm) ? "px-1" : "px-3"}`} ref={headerRef}>
        {(isEditForm || isCloneForm) && !isShowPreview &&
          <>
            <Button id="back-button" variant="" className="btn-link icon-hover me-2" onClick={() => goBack()}>
              <img aria-label="Close" src={BackButton} alt="Close Icon" />
            </Button>
            <UncontrolledTooltip placement="bottom" target='back-button'>
              Back
            </UncontrolledTooltip>
          </>
        }
        {isShowPreview
          ? <>
            <div>Preview Template</div>
            <KeyboardShortcuts buttonId="template-close-button" handleEsc setOpenModal={() => toggle(false)}/>
            <Button id="template-close-button" color="link" variant=" " onClick={() => toggle(false)} className="rounded-5 btn-link icon-hover btn">
            <span class="custom-close-btn icon-hover"></span>
            </Button>
            <UncontrolledTooltip placement="bottom" target="template-close-button">
              Close
            </UncontrolledTooltip>
          </>
          : <div className="d-flex align-items-center justify-content-between w-100">
            <div> {isEditForm ? 'Edit ' : 'Create'} Path Labs Popup Configuration</div>
            <Button disabled={isDisablePreviewBtn} variant="light" className="px-4 brand-secondary btn-sm" onClick={() => validate.isNotEmpty(helpers.getHtmlElement('templateName')) && toggle(true)}>
              Preview
            </Button>
          </div>
        }
      </HeaderComponent>
      <BodyComponent className="body-height" allRefs={{ headerRef, footerRef }}>
        {!isShowPreview && <ApplicableType handleChange={handleChangeApplicableType} isEdit={isEditForm} applicableType={props.applicableType}/>}
        {isShowPreview && <PreviewTemplate templateId={templateInfo.templateId} setShowPreview={setShowPreview} setStackedToastContent={setStackedToastContent}/>}
        <DynamicForm
          key={props.match.params.reqId}
          observers={observerMap}
          requestMethod={REQUEST_TYPE.GET}
          helpers={helpers}
          requestUrl={POPUP_FORM_URLS.pathLabConfigurationCreateForm}
          customHtml={customHtmlMap}
        />
        {DEFINED_TRIGGER === triggerType && !isShowPreview &&
          <React.Fragment>
            <div className="card">
            <ParametersForPathLabConfigurationForm setParameters={setParameters} maximumInclusiveParametersSize = {maximumInclusiveParametersSize} setParameterResultCombinationType={setParameterResultCombinationType} parameterResultCombinationType={parameterResultCombinationType} parameterResult={parameterResult} setPatameterResult={setPatameterResult} parameters={parameters} /> 
           <ExcludeParameterForPathLabConfForm setParameters={hasSelectedRadiology ? setRadiologyTests :  setParameters} maximumExclusiveParametersSize = {maximumExclusiveParametersSize} setExcludeParameterResultCombinationType={setExcludeParameterResultCombinationType} excludeParameterResultCombinationType={excludeParameterResultCombinationType}  excludeParameterResult={excludeParameterResult} setExcludeParameterResult={setExcludeParameterResult} parameters={hasSelectedRadiology ?radiologyTests :  parameters} setHasSelectedRadiologyTests={setHasSelectedRadiologyTests} hasSelectedRadiology={hasSelectedRadiology}/>
              {validate.isNotEmpty(parameterResult) && validate.isNotEmpty(excludeParameterResult) &&
                <div className="card-footer bg-white">
                  <div className="combinationGrp" id="combinationGrp">
                    <label htmlFor="combinationGrp" className="text-secondary small">
                      Select the Combination
                    </label>
                    <div className="combinationValue" id="combinationValue" name="combinationValue">
                      <div className="form-check form-check-inline">
                        <input
                          name="inExParameterResultOrCombinationValue"
                          type="radio"
                          id="inExParameterResultOrCombinationId"
                          className="form-check-input"
                          value="or"
                          onChange={handleCombinationChange}
                          checked={inclusiveExclusivecombination === 'or'}
                        />
                        <label htmlFor="inExParameterResultOrCombinationId" className="form-check-label">OR</label>
                      </div>
                      <div className="form-check form-check-inline">
                        <input
                          name="inExParameterResultAndCombinationValue"
                          type="radio"
                          id="inExParameterResultAndCombinationId"
                          className="form-check-input"
                          value="and"
                          onChange={handleCombinationChange}
                          checked={inclusiveExclusivecombination === 'and'}
                        />
                        <label htmlFor="inExParameterResultAndCombinationId" className="form-check-label">AND</label>
                      </div>
                    </div>
                  </div>
                </div>
              }

            </div>
          </React.Fragment>
        }
        <Modal show={modal} backdrop="static" onHide={() => toggleModal()} aria-labelledby="contained-modal-title-vcenter" centered>
          <Modal.Header closeButton={() => toggleModal()}>
            <Modal.Title className="h6">
              Regions Confirmation
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            Do you want to remove existing configured regions?
          </Modal.Body>
          <Modal.Footer className="p-2 justify-content-center">
            <div>
              <Button variant="secondary" className="px-4 me-3 brand-secondary btn-sm btn" onClick={toggleModal}>Cancel</Button>
              <Button variant="dark" className="px-4 me-3 btn-sm" onClick={() => handleRegionConfirmation()}>Continue</Button>
            </div>
          </Modal.Footer>
        </Modal>
      </BodyComponent>
      <FooterComponent ref={footerRef} className='border-top p-2 d-flex justify-content-between'>
        {isNoteModalOpen && getNotesInfo(footerRef?.current?.offsetHeight)}
        <div  >
          <button tabIndex="-1" disabled={isShowPreview} type="button" className="btn btn-link link-dark font-12" onClick={() => { setNoteModalOpen(!isNoteModalOpen) }}>
            Note  <InfoIcon aria-hidden="true" modalOpen={!isNoteModalOpen} />
          </button>
        </div>
        <div className=" d-flex flex-row-reverse" >
          <Button disabled={isLoading || isShowPreview } variant=" " className={`btn px-4 me-2 btn-sm btn-${props?.location?.state?.isEdit ? 'primary' : 'brand'}`} onClick={(event) => handleSubmitAction(event)}>
            {isLoading ? <CustomSpinners spinnerText={props?.location?.state?.isEdit ? 'Update' : props?.location?.state?.isClone? 'Clone' : 'Submit'} className={"spinner-position"} innerClass={"invisible"} /> : (props?.location?.state?.isEdit ? 'Update' : props?.location?.state?.isClone? "Clone" : 'Submit')}
          </Button>
          {(!isShowPreview) && <button disabled={isShowPreview} variant=" " className='px-4 btn btn-sm brand-secondary me-2 ' onClick={() => (props?.location?.state?.isEdit || props?.location?.state?.isClone ? setConfigurationInfo(props?.location?.state?.isClone ? props?.location?.state?.popupConfigurationInfo : popupConfigurationInfo) : reset())}>
          {(props?.location?.state?.isEdit || props?.location?.state?.isClone) ? "Reset" : "Clear" }
          </button>}
        </div>
      </FooterComponent>
    </>
  )
}

export default withFormHoc(CreatePathLabConfiguration);