import DocumentUpload from "@medplus/react-common-components/DocumentUpload";
import DynamicForm, { ALERT_TYPE, StackedImages, withFormHoc } from '@medplus/react-common-components/DynamicForm';
import axios from 'axios';
import React, { useContext, useEffect, useState } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { PromotionStatus, PromotionType } from '../constants/PromotionConstants';
import ResponseHandler from '../helpers/ResponseHandler';
import Validate from '../helpers/Validate';
import { ADD_FORM, META_INFO_FORM } from '../redux/constants';
import FormJsonService from '../services/FormJsonService';
import { API_URL, isUrlCampaign } from "../services/ServiceConstants";
import { AlertContext } from './Contexts/UserContext';
import NextPrevBtn from './common/NextPrevButtons';

const MetaInfo = (props) => {

    const [metaInfo,setMetaInfo] = useState(props.metaInfo)
    let metaInfoFormData ={};
    const [documentsTrigger, setDocumentsTrigger] = useState(false);
    const {setAlertContent} = useContext(AlertContext);
    const [uploadedFile, setUploadedFile] = useState([]);
    const [isFormLoaded, setFormLoaded] = useState(false);
    const [disableFileUpload, setDisableFileUpload] = useState(false);
    const globalPromotionStatus = props.globalPromotionStatus;
    const [images, setImages] = useState([]);
    const dispatch = useDispatch();
    const metaInfoFormRedux = useSelector((state) => Validate().isNotEmpty(state.formJsonReducer) && Validate().isNotEmpty(state.formJsonReducer[META_INFO_FORM]) ? state.formJsonReducer[META_INFO_FORM] : [])
    const [metaInfoForm, setMetaInfoForm] = useState();
    
    useEffect(() => {
        const longDescriptionInput = document.getElementById("longDescription")
        if (longDescriptionInput) {
            longDescriptionInput.focus();
        }
    }, [document.getElementById("longDescription")]);

    const onDocumentsUpload = async (files) => {
        if(Validate().isNotEmpty(files)) {
            props.setLoading(true);
            setUploadedFile(files);
            
            const file=files[0];
            if(file != undefined){
               const img = new Image();
               img.src=URL.createObjectURL(file);
               checkImageDimensions(img)
               .then( async()=>{
                   await getServer(file);
                   props.setLoading(false);
               })
               .catch((errorMesaage) => {
                setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: errorMesaage});
                   setDocumentsTrigger(false);
                   props.setLoading(false);
               })
            }
            props.setLoading(false);
        } else {
            if(props?.actionPermissions?.isCreate){
                handleNextBtnClick(Validate().isNotEmpty(props.metaInfo.imagePath) ? props.metaInfo.imagePath:undefined, 
                        Validate().isNotEmpty(props.metaInfo.imageServerUrl) ? props.metaInfo.imageServerUrl:undefined);
            } else {
                handleNextBtnClick(Validate().isNotEmpty(props.metaInfo.imagePath) ? props.metaInfo.imagePath:undefined, 
                        Validate().isNotEmpty(props.metaInfo.imageServerUrl) ? props.metaInfo.imageServerUrl:props.metaInfo.stackedImages.imageServerUrl);
            }
        }
    }
    
    const checkImageDimensions = (image) => {
        return new Promise((resolve, reject) => {
          image.onload = () => {
            const width = image.width;
            const height = image.height;
            if (width > 1440 || height > 500) {
                reject('Maximum image width is 1440 and height is 500 allowed.');
            } else {
              resolve();
            }
          };
          image.onerror = () => {
            reject('Error loading image.');
          };
        });
      };

      const loadMetaInfoForm = async () => {
        props.setLoading(true);
        setFormLoaded(false);
        let metaInfoFormObj = metaInfoFormRedux;
        console.log('getting metaInfoFormObj from Redux ')
        if(Validate().isEmpty(metaInfoFormObj) || metaInfoFormObj.length <=0) {
            console.log('getting metaInfoFormObj from Server')
            const response =await FormJsonService().getMetaInfoForm().catch(error => {
                setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to load form'});
            });
        ResponseHandler(setAlertContent).handleResponse(response,{'responseType':'FORM'},(data) => {
            metaInfoFormObj = data;
            let formObj={};
            formObj[META_INFO_FORM] = data
            dispatch({type: ADD_FORM, payload : formObj})
        }, (error) => {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: error});
        });
        }
        setMetaInfoForm(metaInfoFormObj);
        console.log('getting metaInfoFormObj after ')
        setFormLoaded(true);
        props.setLoading(false);
      }

    useEffect(()=>{
        props.setLoading(true);
       // loadMetaInfoForm();
        if((globalPromotionStatus === PromotionStatus.active || props?.actionPermissions?.isApprover)) {
            setDisableFileUpload(true);
            props.helpers.disableElement("uploadToServerBtn");
            props.helpers.updateKeyValuesToAllFields("disabled", "true", "metaInfo");
        } 
        props.helpers.updateSpecificValues(metaInfo, "metaInfo")
        
        props.setLoading(false);
    },[props.metaInfo, isFormLoaded])

    const uploadFilesToServer = (url, filesFormData, requestConfig) => {
        return axios.post(url, filesFormData, requestConfig).then((response)=>{
            if(response.status !== 200){
                return new Promise((resolve) => {
                    return resolve({statusCode: 'CLIENT_ERROR', message: 'Something went wrong. Please try again!'});
                })
            }
            return response.data;
        })
    }
    const uploadFilesToImageServer = async(files, imageType) => {

        try {
            let response = await getImageServerDetails(imageType);

            let imageServerDetails = response.data;
            let filesFormData = new FormData();
            filesFormData.append('files', files , files.name);
            const url = imageServerDetails.imageServerUrl +"/upload?token="+ imageServerDetails.accessToken +"&clientId="+ imageServerDetails.clientId +"&imageType="+ imageType;
            let responseObject = await uploadFilesToServer(url, filesFormData);

            if (responseObject && responseObject.statusCode == "SUCCESS" && responseObject.response) {
                return Promise.resolve({...responseObject.response, imageServerUrl: imageServerDetails.imageServerUrl});
            } else {
                return Promise.reject(responseObject.message);
            }
        } catch (error) {
            console.log(error);
            return Promise.reject(error);
        }
    }

    const getImageServerDetails = async (imageType) => {
        try{
            return await axios({
                method: 'GET',
                url: API_URL + 'get-image-server-details',
                contentType: 'application/json; charset=utf-8',
                params: { imageType },
                headers: {'X-Requested-With':'XMLHttpRequest'}
            });
        }catch(error){
            console.log(error);
            return errorHandling(ERROR_MESSAGES.UNABLE_TO_PROCESS);
        }
    }

    const getServer = async (files) => {
        const data =  await uploadFilesToImageServer(files, "F");
        if(Validate().isNotEmpty(data) && Validate().isNotEmpty(data.imageServerUrl)){
            setMetaInfo({...metaInfo,imagePath: data[0].imagePath, imageServerUrl: data.imageServerUrl})
            setDisableFileUpload(true)
            handleNextBtnClick(data[0].imagePath, data.imageServerUrl)
        }
    }

    useEffect(()=>{
        if(Validate().isNotEmpty(props.metaInfo)){
            if(Validate().isNotEmpty(props.metaInfo.imageServerUrl) || Validate().isNotEmpty(props.metaInfo.imagePath)) {
                setImages({
                    "imagePath": `${props.metaInfo.imageServerUrl}/${props.metaInfo.imagePath}`,
                    "thumbnailPath": `${props.metaInfo.imageServerUrl}/${props.metaInfo.imagePath}`,
                    "altText": "Promotion Banner",
                });
            }
        }
    },[])

    const updateMetaInfo = () => {
        if(props.promotionType === PromotionType.regular){
            props.helpers.updateSingleKeyValueIntoField("label","Promotion Description", "longDescription", true);
        }
        // setFormLoaded(true);
        props.setLoading(true);
 		if(props?.promotionType === PromotionType.regular) {
            props.helpers.showElement('promotionVisible')
            props.helpers.hideElement('claimable')
        }
        if(isUrlCampaign(props.match.path)){
            props.helpers.showElement('promotionVisible')
        }
        if(isUrlCampaign(props.match.path) && props.applicableType === '6'){
            props.helpers.showElement('specialtyBased')
        }
        const prevMetaInfo = props.metaInfo;
        if(Validate().isNotEmpty(prevMetaInfo)){
            props.helpers.updateSpecificValues(prevMetaInfo, 'metaInfo');
            setUploadedFile(prevMetaInfo.file);
            if(Validate().isNotEmpty(props.metaInfo.stackedImages)){
                setImages(props.metaInfo.stackedImages); 
            }
            if(Validate().isNotEmpty(props.metaInfo.stackedImages.imageServerUrl))
            {
                setImages(props.metaInfo.stackedImages);
            }
            if((globalPromotionStatus === PromotionStatus.active || props?.actionPermissions?.isApprover)) {
                // if(!(props?.actionPermissions?.isClone)){
                    setDisableFileUpload(true);
                    props.helpers.disableElement("uploadToServerBtn");
                    props.helpers.updateKeyValuesToAllFields("disabled", "true", "metaInfo");
                // }
            } 
            if(isUrlCampaign(props.match.path) && !props?.actionPermissions?.isCreate){
                props.helpers.disableElement('specialtyBased')
            }
        }
        props.setLoading(false);
        return false;
    }

    const handleDescriptionCount = () =>{
        if(Validate().isNotEmpty(props.helpers.getHtmlElementValue('longDescription')) && props.helpers.getHtmlElementValue('longDescription').length>4000){
            props.helpers.updateErrorMessage("Max 4000 letters are allowed", "longDescription");
        }
        else{
            props.helpers.updateErrorMessage("","longDescription");
        }
        props.helpers.updateSingleKeyValueIntoField("helperText", Validate().isNotEmpty(props.helpers.getHtmlElementValue("longDescription")) ? props.helpers.getHtmlElementValue("longDescription").length +"/ 4000" : "0 / 4000","longDescription")
    }

    const oberserverMap = {
        'metaInfo' :[['load', () => updateMetaInfo()]],
        'longDescription':[['change', () =>handleDescriptionCount()]]
    }
    const handleNextBtnClick = (imagePath=undefined, imageServerUrl=undefined) => {
        props.setLoading(true);
         metaInfoFormData = props.helpers.validateAndCollectValuesForSubmit('metaInfo');
        if(metaInfoFormData?.longDescription?.length > 4000) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Campaign Description should be less than or equal to 4000 characters'});
            setDocumentsTrigger(false);
            props.setLoading(false);
            return false;
        }
        if(Validate().isNotEmpty(metaInfoFormData)){
            metaInfoFormData['imagePath'] = Validate().isNotEmpty(imagePath) ? imagePath : undefined;
            metaInfoFormData['imageServerUrl'] = Validate().isNotEmpty(imageServerUrl) ? imageServerUrl : undefined;
            metaInfoFormData['stackedImages'] = images;
            props.handleCampaignInfoChange(metaInfoFormData, props.tabDetails.currentTab.tabId, props.tabDetails.nextTab.tabId);
        } else {
            props.helpers.updateErrorMessage("is Required","metaInfo");
        }
        setDocumentsTrigger(false);
        props.setLoading(false);
        return false;

    }

    const noteItems = [
        "Campaign Banner: Maximum image width is 1440 and height is 500 allowed"
    ]

    const handlePrevBtnClick = ()=>{
        metaInfoFormData = props.helpers.collectValuesForSubmit('metaInfo');
        metaInfoFormData['imagePath'] = Validate().isNotEmpty(metaInfo.imagePath) ? metaInfo.imagePath : undefined;
        metaInfoFormData['imageServerUrl'] = Validate().isNotEmpty(metaInfo.imageServerUrl) ? metaInfo.imageServerUrl : undefined;
        metaInfoFormData['stackedImages'] = images;
        props.handleCampaignInfoChange(metaInfoFormData, props.tabDetails.currentTab.tabId, props.tabDetails.prevTab.tabId);
        
    }
    return(
        
            <React.Fragment key={`${props.location.pathname}`}>
              { <> <div style={{height:"calc(100% - 63px)"}}>
                        <DynamicForm 
                            requestUrl={`${API_URL}get-meta-info-form`} 
                            helpers={props.helpers} observers={oberserverMap} 
                            requestMethod={'GET'}
                            // formJson ={metaInfoForm}
                           // Loader={CustomLoader}
                            />
                        {!disableFileUpload && 
                        <div>
                        <label className='custom-fieldset mb-2'>Campaign Banner</label>
                        <DocumentUpload
                            fileSelectOption={true}
                            documentScanOption={false}
                            buttonClassName={'scan-button'}
                            disableFileUpload={disableFileUpload}
                            imageContainerClassName={'image-container'}
                            isAppendAllowed={false}
                            resetAddedDocuments={true}
                            uploadActionInParent={false}
                            getAddedDocuments={documentsTrigger}
                            singleFileUpload={true}
                            allowedFileFormats={".jpg,.jpeg,.png"}
                            onSuccessResponse={(files) => { onDocumentsUpload(files) }}
                            onErrorResponse={(message) => { setAlertContent({ message: message, show: true, alertType: "Error" }) }}
                            onDeleteResponse={() => { }}
                            includeLightBox={true}
                            imageTitle={"campaign-banner"}
                        /></div>}
                        {Validate().isNotEmpty(images.imagePath) ? <div className='d-inline-block'>
                                {  <StackedImages images={[images]} includeLightBox maxImages="1"/>}
                           </div>: null}
                    </div>
                    <NextPrevBtn PrevTab={props.tabDetails.prevTab.title}  isNoteRequired={true}  noteItems= {noteItems} nextTab={props.tabDetails.nextTab.title} {...props} handleNextBtnClick={() => {!disableFileUpload ? setDocumentsTrigger(true) : handleNextBtnClick()}} handlePrevBtnClick={handlePrevBtnClick} /> </> }
            </React.Fragment>
        
    )
}
export default withFormHoc(MetaInfo);