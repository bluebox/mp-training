import DocumentUpload from "@medplus/react-common-components/DocumentUpload";
import DynamicForm, { CustomSpinners, REQUEST_TYPE, withFormHoc } from "@medplus/react-common-components/DynamicForm";
import dateFormat from 'dateformat';
import React, { useContext, useEffect, useRef, useState } from "react";
import { Button, Modal } from "react-bootstrap";
import Cron from "react-js-cron";
import { UncontrolledTooltip } from "reactstrap";
import { ACTIVE, CLOSED, DEFAULT_DATE_TIME_FORMAT, INACTIVE, PRODUCT, STATUS, TIME_INTERVAL } from "../../constants/PromotionConstants";
import { POPUP_FORM_URLS, POPUP_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import CancelIcon from "../../images/cross.svg";
import DownloadIcon from "../../images/download_cloud_icon.svg";
import BackButton from "../../images/leftarrow_black_icon_18px.svg";
import PopupService from "../../services/PopupService";
import { AlertContext } from "../Contexts/UserContext";
import RegionsWrapper from "../RegionsWrapper";
import { BodyComponent, FooterComponent, HeaderComponent, Wrapper } from "../common/CommonStructure";
import CustomDateTimePicker from "../common/CustomDateTimePicker";
import InfoIcon from "../common/InfoIcon";
import PreviewTemplate from "./PreviewTemplate";
import CreatePathLabConfiguration from "./CreatePathLabConfiguration";
import ApplicableType from "./ApplicableType";
import KeyboardShortcuts from "../../helpers/KeyboardShortcuts";
import { POPUP_ROLES } from "../../constants/MarketingRoles";
import { StoreCategoryTypes } from "../../constants/MarketingConstant";

const CREATE_CONFIG_PATH = '/marketing/ui/create-configuration';

const CreateConfiguration = ({ helpers, ...props }) => {
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
  const [productDownloadUrl, setProductDownloadUrl] = useState(undefined);
  const excelData = useRef({
    'uploadRegions': '',
    'uploadProductIds': '',
    'removeRegions': '',
    'removeProductIds': '',
  })
  const [isEditForm, setEditForm] = useState(false);
  const [isCloneForm, setCloneForm] = useState(false);
  const [globalPromotionStatus, setGlobalPromotionStatus] = useState('');
  const [id, setId] = useState(0);
  const [regionsInfo, setRegionsInfo] = useState({});
  const [productIds, setProductIds] = useState([]);
  const [wrapperKey, setWrapperKey] = useState(1);
  const [prevSelectedRegionType, setPrevSelectedRegionType] = useState("");
  const [modal, setModal] = useState(false);
  const [removeRegionsMap, setRemoveRegionsMap] = useState({});
  const [existingRegions, setExistingRegions] = useState([]);
  const [applicableType, setApplicableType] = useState(props.location.state?.applicableType?.toLowerCase());
  const [popupConfigurationInfo ,setPopupConfigurationInfo] = useState({});
  const homeHeadRef = useRef(null);
  const [templateName, setTemplateName] = useState("");
  const [templateId,setTemplateId] = useState();

  useEffect(() => {
    setWrapperKey(wrapperKey + 1);
  }, [props.path]);

  useEffect(() => {
    if(validate.isEmpty(props.match.params.reqId)){
      return;
    }
    if("pharmacy" == props?.location?.state?.applicableType?.toLowerCase()){
      popupService.getConfigRequestsOnRequestId({ requestId: props.match.params.reqId }).then(res => {
        if (validate.isNotEmpty(res) && validate.isNotEmpty(res.responseData) && "SUCCESS" == res.statusCode) {
          setPopupConfigurationInfo(res.responseData?.popupConfiguration);
          if(validate.isEmpty(applicableType)){
            setApplicableType(res.responseData?.applicableType?.toLowerCase());
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
  }, [props.match.params.reqId]);

  const getDefaultToDate = () => {
    let date = new Date();
    date.setDate(date.getDate() + 90);
    date.setHours(23);
    date.setMinutes(59);
    date.setSeconds(59);
    return date;
  }
  const getDefaultFromDate = () => {
    let date = new Date();
    date.setMinutes(date.getMinutes() + 10);
    return date;
  }

  const [fromDate, setFromDate] = useState(getDefaultFromDate());
  const [toDate, setToDate] = useState(getDefaultToDate());

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

  const onProductIdsUpload = (files) => {
    if (validateFiles(files)) {
      excelData.current['uploadProductIds'] = files[0];
    }
  }

  const onProductIdsRemove = (files) => {
    if (validateFiles(files)) {
      excelData.current['removeProductIds'] = files[0];
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


  const onTriggerTypeChange = (value) => {
    if (PRODUCT === value) {
      helpers.showElement('triggerProductGrp');
      helpers.hideElement('triggerCronGrp');
      helpers.showElement('uploadProductsExcelGrp');
      helpers.updateSingleKeyValueIntoField('required', true, 'triggerValue');
    } else {
      helpers.hideElement('triggerProductGrp');
      helpers.showElement('triggerCronGrp');
      helpers.hideElement('uploadProductsExcelGrp');
      helpers.updateSingleKeyValueIntoField('required', false, 'triggerValue');
    }
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

  const setTemplateInformation = async (templateId) => {
    const response = await popupService.getTemplateInfo({ 'templateId': templateId });
    ResponseHandler(setStackedToastContent).handleResponse(response, {}, (data) => setTemplateInfo(data), (error) => setStackedToastContent({ toastMessage: error }));
  }

  const handleSelectTemplateName = (value, valuesList) => {
    if(validate.isEmpty(value) || validate.isEmpty(valuesList)){
      return;
    }
    setTemplateName(value[0]);
    const selectedOption = valuesList?.find(item => item.value === value[0]);
    const id = selectedOption ? selectedOption.id : '';
    setTemplateId(id)
    setDisablePreviewBtn(validate.isEmpty(id));
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
    if (validate.isEmpty(configInfo) || CREATE_CONFIG_PATH == props.path) {
      reset();
      return;
    }
    let formValues = {
      ...configInfo,
      'templateId': configInfo?.templateId,
      'templateName': [configInfo?.templateName],
      'name' : configInfo?.popUpConfigurationName?.toString(),
      'status': "ACTIVE" == configInfo.status ? "A" :"I",
      'isExcelUploadForRegions': validate.isNotEmpty(configInfo?.isExcelUploadForRegions)? "Y" :"N",
      'triggerType' : prepareDefinedTriggerType(configInfo.triggerType),
    };
    setFromDate(new Date(formValues.fromDate));
    setToDate(new Date(formValues.toDate));
    if (TIME_INTERVAL === formValues.triggerType) {
      setCronValue(formValues.triggerValue)
      formValues.triggerValue = '';
    }
    helpers.updateSpecificValues(formValues, 'configurationForm');
    setTemplateId(configInfo?.templateId);
    setDisablePreviewBtn(validate.isEmpty(configInfo?.templateId));
    setTemplateName(configInfo?.templateName);
    if(props?.location?.state?.isEdit){
      formValues['requestId'] = props.match.params.reqId;
    }
    onTriggerTypeChange(formValues.triggerType);
    onRegionSelectionChange(formValues.isExcelUploadForRegions);
    if(validate.isNotEmpty(configInfo.configId) && 0 != configInfo.configId){
      setId(configInfo.configId);
    }else{
      setId(props.match.params.reqId);
    }
    setEditForm(props.location.state?.isEdit);
    setCloneForm(props.location.state?.isClone);
    if(props.location.state?.isClone){
      setTemplateId(configInfo.templateId);
      setTemplateName(configInfo.templateName);
    }
    setProductIds(formValues.productIds);
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

  const disableFields = (formValues) => {
    if(props.location.state?.isEdit){
      setPrevSelectedRegionType(formValues.isExcelUploadForRegions);
    }
    if (props.location.state?.isClone) {
      helpers.updateValue('', 'name', false);
    }
  }

  const confirmationRegionModal = (value) => {
    if (isEditForm && 'I' === globalPromotionStatus && 'N' === prevSelectedRegionType && 'Y' === helpers.getHtmlElementValue("isExcelUploadForRegions") && validate.isNotEmpty(regionsInfo)) {
      toggleModal();
      return;
    }
    onRegionSelectionChange(value);
  }

  const observerMap = {
    'triggerType': [['change', (payload) => onTriggerTypeChange(payload[0].target.value)]],
    'isExcelUploadForRegions': [['change', (payload) => confirmationRegionModal(payload[0].target.value)]],
    'templateName': [['select', (payload) => handleSelectTemplateName(payload[0].target.value, payload[1]?.values)], ['change', (payload) => setDisablePreviewBtn(true)]],
    'name': [['blur', (payload) => checkConfigurationName(payload[0].target.value.trim())]],
    'configurationForm': [['load', () => setConfigurationInfo(props.location.state?.isClone ? props.location.state.popupConfigurationInfo : popupConfigurationInfo)]],
  };

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
        setStackedToastContent({ toastMessage: 'Unable to download regions excel, please try again.' })
      })
      if (validate.isNotEmpty(response) && response.status === 200) {
        const url = window.URL.createObjectURL(new Blob([response.data], { type: 'application/force-download' }));
        setRegionsDownloadUrl(url);
        downloadUrl(url, 'regions');
      } else {
        setStackedToastContent({ toastMessage: 'Unable to download regions excel, please try again.' });
      }
    } else {
      downloadUrl(regionsDownloadUrl, 'regions');
    }
  }

  const downloadProducts = async (event) => {
    event.preventDefault();
    if (validate.isEmpty(productDownloadUrl)) {
      const response = await popupService.getProductIdsExcel({ 'id': id }).catch((error) =>
        setStackedToastContent({ toastMessage: 'Unable to download products excel, please try again.' })
      )
      if (validate.isNotEmpty(response) && response.status === 200) {
        const url = window.URL.createObjectURL(new Blob([response.data], { type: 'application/force-download' }));
        setProductDownloadUrl(url);
        downloadUrl(url, 'products');
      } else {
        setStackedToastContent({ toastMessage: 'Unable to download products excel, please try again.' });
      }
    } else {
      downloadUrl(productDownloadUrl, 'products');
    }
  }

  const getMaxDate = () => {
    let date = new Date();
    date.setFullYear(2100);
    return date;
  }

  const customHtmlMap = {
    'triggerCronGrp': [['INSERT_IN', () => {
      return (
        <>
          <div className='d-flex flex-wrap align-items-center my-1'>
            <Cron allowedDropdowns={['period', 'months', 'month-days', 'hours', 'minutes',]}
              allowedPeriods={['year', 'month', 'day', 'hour']}
              clearButtonAction={'empty'}
              defaultPeriod={'day'}
              value={cronValue} setValue={setCronValue} disabled={isEditForm && ACTIVE === globalPromotionStatus} className={'my-project-cron something'} clearButtonProps={{ type: 'default' }} locale={{ clearButtonText: 'Reset' }}
            />
          </div>
        </>
      )
    }]],
    'uploadRegionExcelGrp': [['INSERT_IN', () => {
      return (
        <div className='d-flex px-3 py-0 col-8 justify-content-between'>
          <div>
            {((isEditForm || isCloneForm) && validate.isNotEmpty(regionsInfo)) &&<label className='pb-2 small text-secondary'>Upload</label>}
            <DocumentUpload
              buttonClassName={'scan-button'}
              imageContainerClassName={'image-container'}
              includeLightBox={false}
              fileSelectOption={true}
              singleFileUpload={true}
              isAppendAllowed={false}
              loadingStatus={(isLoading) => { setDocumentTrigger(!isLoading) }}
              getAddedDocuments={documentTrigger}
              disableFileUpload={validate.isNotEmpty(globalPromotionStatus) && INACTIVE !== globalPromotionStatus}
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
                disableFileUpload={validate.isNotEmpty(globalPromotionStatus) && INACTIVE !== globalPromotionStatus}
                allowedFileFormats={'.xls,.xlsx'}
                onSuccessResponse={(files) => validate.isNotEmpty(files) && onRegionsRemove(files)}
                onErrorResponse={(message) => { setStackedToastContent({ toastMessage: message }) }}
                removedFileResponse={(file) => { excelData.current['removeRegions'] = '' }}
              />
            </div>}
          {(isEditForm || isCloneForm) && validate.isNotEmpty(regionsInfo) &&<div id='productDownload'>
            <p className='small text-secondary'>Download Regions</p>
            <button className='btn btn-link icon-hover' onClick={(event) => downloadRegions(event)}><img src={DownloadIcon} alt="Download Icon" /></button>
          </div>}
        </div>
      )
    }]],
    'uploadProductsExcelGrp': [['INSERT_IN', () => {
      return (
        <div className='d-flex px-3 py-2 col-8 justify-content-between align-items-center'>
          <div>
            {(isEditForm || isCloneForm) && <label className='pb-2 small text-secondary'>Upload</label>}
            <DocumentUpload
              buttonName={'product upload'}
              buttonClassName={'scan-button'}
              imageContainerClassName={'image-container'}
              includeLightBox={false}
              fileSelectOption={true}
              singleFileUpload={true}
              isAppendAllowed={false}
              loadingStatus={(isLoading) => { setDocumentTrigger(!isLoading) }}
              getAddedDocuments={documentTrigger}
              disableFileUpload={validate.isNotEmpty(globalPromotionStatus) && INACTIVE !== globalPromotionStatus}
              allowedFileFormats={".xls,.xlsx"}
              onSuccessResponse={(files) => validate.isNotEmpty(files) && onProductIdsUpload(files)}
              onErrorResponse={(message) => { setStackedToastContent({ toastMessage: message }) }}
              removedFileResponse={(file) => { excelData.current['uploadProductIds'] = '' }}
            />
          </div>
          {(isEditForm || isCloneForm) && <div>
            <label className="pb-2 small text-secondary">Remove</label>
            <DocumentUpload
              buttonName={'product remove'}
              buttonClassName={'scan-button'}
              imageContainerClassName={'image-container'}
              includeLightBox={false}
              fileSelectOption={true}
              singleFileUpload={true}
              isAppendAllowed={false}
              loadingStatus={(isLoading) => { setDocumentTrigger(!isLoading) }}
              getAddedDocuments={documentTrigger}
              disableFileUpload={validate.isNotEmpty(globalPromotionStatus) && INACTIVE !== globalPromotionStatus}
              allowedFileFormats={".xls,.xlsx"}
              onSuccessResponse={(files) => validate.isNotEmpty(files) && onProductIdsRemove(files)}
              onErrorResponse={(message) => { setStackedToastContent({ toastMessage: message }) }}
              removedFileResponse={(file) => { excelData.current['removeProductIds'] = '' }}
            />
          </div>}
          {(isEditForm || isCloneForm) && <div id="productDownload">
            <button className='btn btn-light' onClick={(event) => downloadProducts(event)}><img src={DownloadIcon} alt="Download Icon" /> Download Items </button>
          </div>}
        </div>
      )
    }]],
    'regionsGrp': [['INSERT_IN', () => {
      return (
        <RegionsWrapper ignoreHeight={true} {...props} showStoreLevel={true} setLoading={() => { }} regions={regionsInfo} handleRegionsChange={(regionsMap) => setRegionsInfo(regionsMap)} isNotEligibleRegionsRequired={false} storeCategoryTypes={[StoreCategoryTypes.STORE]} />
      )
    }]],
    'dateRangeGrp': [['INSERT_IN', () => {
      return (
        <div className="d-flex row g-3">
          <CustomDateTimePicker handleDateChange={setFromDate} title={'From Date'} defaultDateTime={fromDate} pickerProps={{ readOnly: (isEditForm && globalPromotionStatus === ACTIVE) }} minDate={new Date()} maxDate={getMaxDate()} />
          <CustomDateTimePicker handleDateChange={setToDate} title={'To Date'} defaultDateTime={toDate} pickerProps={{ readOnly: false }} minDate={new Date()} maxDate={getMaxDate()} />
        </div>
      )
    }]]
  }

  const reset = () => {
    helpers.hideElement('triggerProductGrp');
    helpers.hideElement('triggerCronGrp');
    helpers.hideElement('uploadProductsExcelGrp');
    helpers.hideElement('uploadRegionExcelGrp');
    helpers.hideElement('regionsGrp');
    helpers.showElement('configurationForm');
    helpers.resetForm('configurationForm', false, true, true, true);
    setFromDate(getDefaultFromDate());
    setToDate(getDefaultToDate());
    setRegionsInfo({});
    setId(0);
    setCronValue("");
    setProductIds([]);
    setEditForm(false);
    setCloneForm(false);
    excelData.current['uploadRegions'] = ''
    excelData.current['uploadProductIds'] = ''
    excelData.current['removeRegions'] = ''
    excelData.current['removeProductIds'] = ''
    setGlobalPromotionStatus("");
    helpers.updateKeyValuesToAllFields('disabled', false, 'configurationForm');
    setShowPreview(false);
    handleSelectTemplateName(null);
    setPrevSelectedRegionType("");
    setModal(false);
    setRemoveRegionsMap({});
    setExistingRegions([]);
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

    if (ACTIVE !== globalPromotionStatus && new Date(fromDate) < new Date()) {
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
      setStackedToastContent({ toastMessage: 'Please select any region.' });
      setLoading(false)
      return true;
    }

    if (PRODUCT === configInfo.triggerType && validate.isEmpty(helpers.getHtmlElementValue('triggerValue'))) {
      setStackedToastContent({ toastMessage: 'Please select trigger value.' });
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

    if (PRODUCT === configInfo.triggerType && excelData.current?.uploadProductIds === '') {
      setStackedToastContent({ toastMessage: 'Please select excel file to upload products.' })
      setLoading(false)
      return true;
    }

    return false;
  }

  const updateConfiguration = async (formData) => {
    return await popupService.updatePopUpConfigRequest(formData).catch((error) =>
      setStackedToastContent({ toastMessage: "Unable to update popup configuration, please try again" })
    );
  }

  const createConfiguration = async (formData) => {
    return await popupService.createPopupConfigRequest(formData).catch((error) =>
      setStackedToastContent({ toastMessage: "Unable to create popup configuration, please try again" })
    );
  }

  const prepareMapToList = (regionsInfo) => {
    let regions = [];
    Object.keys(regionsInfo).map(region => {
      regions.push(region);
    });
    return regions.join(', ');
  }

  const handleSubmitAction = async (event) => {
    event.preventDefault();
    setLoading(true);
    let configInfo = helpers.validateAndCollectValuesForSubmit('configurationForm');
    if (validateConfigurationForm(configInfo) || !(isEditForm || await checkConfigurationName(configInfo.name.trim()))) {
      return;
    }
    configInfo.templateId = configInfo.templateId;
    configInfo.templateName = helpers.getDisplayValueForOptionValue('templateName', configInfo.templateId)
    if (TIME_INTERVAL == configInfo.triggerType) {
      configInfo.triggerValue = cronValue;
    }
    configInfo['popUpConfigurationName'] = configInfo.name;
    configInfo['applicableType'] = '1';
    configInfo['fromDate'] = fromDate.getTime();
    configInfo['toDate'] = toDate.getTime();
    configInfo['regions'] = 'Y' === configInfo.isExcelUploadForRegions ? existingRegions.join(',') : prepareMapToList(regionsInfo);
    if(validate.isNotEmpty(removeRegionsMap)){
      configInfo['removeRegions'] = prepareMapToList(removeRegionsMap);
    }
    if (isEditForm) {
      configInfo['requestId'] = id;
    } else if (isCloneForm) {
      configInfo['cloneRefId'] = id;
    }
    let formData = new FormData();
    if ('Y' === configInfo.isExcelUploadForRegions) {
      formData.append('uploadRegionsExcel', excelData.current?.uploadRegions);
    }
    if (PRODUCT === configInfo.triggerType) {
      formData.append('uploadProductIdsExcel', excelData.current?.uploadProductIds);
      configInfo['productIds'] = productIds.join(',');
    }
    if (validate.isNotEmpty(configInfo.requestId) || validate.isNotEmpty(configInfo.cloneRefId)) {
      formData.append('removeProductIdsExcel', excelData.current?.removeProductIds);
      formData.append('removeRegionsExcel', excelData.current?.removeRegions);
    }
    if(validate.isNotEmpty(templateId) && validate.isNotEmpty(templateName)){
      configInfo['templateId'] = templateId;
      configInfo['templateName'] = templateName;
    }
    formData.append('popupPathlabConfiguration', JSON.stringify(configInfo));
    const response = await (isEditForm ? updateConfiguration(formData) : createConfiguration(formData));
    ResponseHandler(setStackedToastContent).handleResponse(response, {}, (data) => {
      setLoading(false);
      setStackedToastContent({ toastMessage: `Popup Configuration ${isEditForm? 'Updated' : 'Created'} Successfully.` });
      props.history.push({
        pathname: POPUP_URLS.viewConfigurationRequests,
        state: {
          searchCriteria: isCloneForm ? {} : props.location.state?.searchCriteria,
          id: id
        },
      });
    }, (error) => {
      setLoading(false);
      setStackedToastContent({ toastMessage: error });
    })
  }

  const toggle = (showPreview) => {
    showPreview ? helpers.hideElement('configurationForm') : helpers.showElement('configurationForm');
    if (showPreview && validate.isNotEmpty(helpers.getHtmlElementValue('templateName'))) {
      setTemplateInformation(templateId);
    }
    setShowPreview(showPreview);
  }

  const goBack = () => {
    props.history.push({
      pathname:props.location.state?.isClone ? POPUP_URLS.viewConfigurations : POPUP_URLS.viewConfigurationRequests,
      state: {
        searchCriteria: props.location.state?.searchCriteria,
        id: id,
        isBackClicked: true
      },
    });
  }
  const getNotesInfo = (height) => {
    return (
      <div class="customdropdown-position position-absolute text-secondary bottom " style={{ bottom: `${height}px` }}>
        <h6 class="text-dark font-14">Notes Information</h6>
        <ol>
          <li>Regions - Excel file contains one column with StoreID.</li>
          <li>Store Ids: Comma Separated list of Store ID's.</li>
          <li>Filter Regions: Comma Separated list of search items which will be enabled if the count of selected regions is greater than 15.</li>
          <li>Products - Excel file contains one column with ProductID.</li>
          <li>Cron - Double click on a dropdown option to automatically select / unselect a periodicity.</li>
        </ol>
      </div>
    )
  }

  const toggleModal = () => {
    helpers.updateValue('N', 'isExcelUploadForRegions');
    onRegionSelectionChange("N");
    setModal(!modal);
  }

  const handleRegionConfirmation = () => {
    setModal(false);
    setRemoveRegionsMap(regionsInfo);
    setRegionsInfo({});
    onRegionSelectionChange("Y");
    helpers.updateValue('Y', 'isExcelUploadForRegions');
  }

  const handleChangeApplicableType = (value) => {
    setApplicableType(value);
    setDisablePreviewBtn(true);
    setTemplateId("");
    setTemplateName("");
  }


  const renderHeader = (isEditForm, isClone, isShowPreview, toggle, goBack, BackButton) => (
    <HeaderComponent className={`border-bottom py-2 d-flex align-items-center justify-content-between ${!isShowPreview && (isEditForm || isCloneForm) ? "px-1" : "px-3"}`} ref={headerRef}>
      {(isEditForm || isClone) && !isShowPreview && (
        <>
          <Button id="back-button" variant="" className="btn-link icon-hover me-2" onClick={goBack}>
            <img aria-label="Close" src={BackButton} alt="Close Icon" />
          </Button>
          <UncontrolledTooltip placement="bottom" target="back-button">Back</UncontrolledTooltip>
        </>
      )}
      {isShowPreview ? (
        <>
          <div>Preview Template</div>
          <KeyboardShortcuts buttonId="template-close-button" handleEsc setOpenModal={() => toggle(false)} />
          <Button id="template-close-button" color="link" variant=" " onClick={() => toggle(false)} className="rounded-5 btn-link icon-hover btn">
            <span className="custom-close-btn icon-hover"></span>
          </Button>
          <UncontrolledTooltip placement="bottom" target="template-close-button">Close</UncontrolledTooltip>
        </>
      ) : (
        <div className="d-flex align-items-center justify-content-between w-100">
          <div>{isEditForm ? 'Edit ' : 'Create'} Pharmacy Popup Configuration</div>
          <Button disabled={isDisablePreviewBtn} variant="light" className="px-4 brand-secondary btn-sm" onClick={() => validate.isNotEmpty(helpers.getHtmlElement('templateName')) && toggle(true)}>
            Preview
          </Button>
        </div>
      )}
    </HeaderComponent>
  );
  
  const renderFooter = (isEditForm, isShowPreview, isLoading, globalPromotionStatus, reset, handleSubmitAction) => (
    <FooterComponent ref={footerRef} className="border-top p-2 d-flex justify-content-between">
      {isNoteModalOpen && getNotesInfo(footerRef?.current?.offsetHeight)}
      <div>
        <button disabled={isShowPreview} type="button" className="btn btn-link link-dark font-12" onClick={() => setNoteModalOpen(!isNoteModalOpen)}>
          Note <InfoIcon aria-hidden="true" modalOpen={!isNoteModalOpen} />
        </button>
      </div>
      <div>
        {!isEditForm && !isShowPreview && (
          <button disabled={isShowPreview} className="px-4 btn brand-secondary me-2 btn-sm" onClick={reset}>
            Clear
          </button>
        )}
        {ACTIVE !== globalPromotionStatus && (
          <Button disabled={isLoading || isShowPreview || ACTIVE === globalPromotionStatus} className={`btn btn-sm px-4 me-2 btn-${isEditForm ? 'primary' : 'brand'}`} onClick={handleSubmitAction}>
            {isLoading ? <CustomSpinners spinnerText={isEditForm ? 'Update' : 'Submit'} className="spinner-position" innerClass="invisible" /> : isEditForm ? 'Update' : 'Submit'}
          </Button>
        )}
      </div>
    </FooterComponent>
  );
  
  const renderModal = (modal, toggleModal, handleRegionConfirmation) => (
    <Modal show={modal} backdrop="static" onHide={toggleModal} aria-labelledby="contained-modal-title-vcenter" centered>
      <Modal.Header closeButton={toggleModal}>
        <Modal.Title className="h6">Regions Confirmation</Modal.Title>
      </Modal.Header>
      <Modal.Body>Do you want to remove existing configured regions?</Modal.Body>
      <Modal.Footer className="p-2 justify-content-center">
        <div>
          <Button variant="secondary" className="px-4 me-3 brand-secondary btn-sm btn" onClick={toggleModal}>Cancel</Button>
          <Button variant="dark" className="px-4 me-3 btn-sm" onClick={handleRegionConfirmation}>Continue</Button>
        </div>
      </Modal.Footer>
    </Modal>
  );
  
  return (
    <>

      {
        validate.isEmpty(applicableType) ?
          <>
            {validate.validateRole(POPUP_ROLES.createLabConfiguration) && validate.validateRole(POPUP_ROLES.createPharmaConfiguration) &&
              <Wrapper>
                <HeaderComponent ref={homeHeadRef} className="px-3 py-2 border-bottom">
                  Popup Configuration
                </HeaderComponent>
                <BodyComponent className="body-height" allRefs={{ "headerRef": homeHeadRef }}>
                  <ApplicableType handleChange={handleChangeApplicableType} isEdit={isEditForm} applicableType={applicableType} />
                </BodyComponent>
              </Wrapper>
            }
            <Wrapper key={wrapperKey}>
              {validate.validateRole(POPUP_ROLES.createPharmaConfiguration) && !validate.validateRole(POPUP_ROLES.createLabConfiguration) &&
                <>
                  {renderHeader(isEditForm, props.location.state?.isClone, isShowPreview, toggle, goBack, BackButton)}
                  <BodyComponent className="body-height" allRefs={{ "headerRef": headerRef, "footerRef": footerRef }}>
                    {!isShowPreview && <ApplicableType handleChange={handleChangeApplicableType} isEdit={isEditForm} applicableType={applicableType} />}
                    {isShowPreview && <PreviewTemplate templateId={templateInfo.templateId} setShowPreview={setShowPreview} setStackedToastContent={setStackedToastContent}/>}
                    <DynamicForm observers={observerMap} requestMethod={REQUEST_TYPE.GET} helpers={helpers} requestUrl={POPUP_FORM_URLS.configurationCreateForm} customHtml={customHtmlMap} />
                    {renderModal(modal, toggleModal, handleRegionConfirmation)}
                  </BodyComponent>
                  {renderFooter(isEditForm, isShowPreview, isLoading, globalPromotionStatus, reset, handleSubmitAction)}
                </>
              }
              {validate.validateRole(POPUP_ROLES.createLabConfiguration) && !validate.validateRole(POPUP_ROLES.createPharmaConfiguration) && 
                <CreatePathLabConfiguration applicableType={'pathlabs'} setApplicableType={setApplicableType} history={props.history} {...props} />
              }
            </Wrapper>
          </>
          :
          <>
            <Wrapper key={wrapperKey}>
              <>
                {applicableType === 'pathlabs' ?
                  <CreatePathLabConfiguration applicableType={applicableType} setApplicableType={setApplicableType} history={props.history} {...props} />
                  :
                  <>
                    {renderHeader(isEditForm, props.location.state?.isClone, isShowPreview, toggle, goBack, BackButton)}
                    <BodyComponent className="body-height" allRefs={{ "headerRef": headerRef, "footerRef": footerRef }}>
                      {!isShowPreview &&<ApplicableType handleChange={handleChangeApplicableType} isEdit={isEditForm} applicableType={applicableType} />}
                      {isShowPreview && <PreviewTemplate templateId={templateInfo.templateId} setShowPreview={setShowPreview} setStackedToastContent={setStackedToastContent}/>}
                      <DynamicForm observers={observerMap} requestMethod={REQUEST_TYPE.GET} helpers={helpers} requestUrl={POPUP_FORM_URLS.configurationCreateForm} customHtml={customHtmlMap} />
                      {renderModal(modal, toggleModal, handleRegionConfirmation)}
                    </BodyComponent>
                    {renderFooter(isEditForm, isShowPreview, isLoading, globalPromotionStatus, reset, handleSubmitAction)}
                  </>
                }
              </>
            </Wrapper>
          </>
      }
    </>
  );
  
};
export default withFormHoc(CreateConfiguration);