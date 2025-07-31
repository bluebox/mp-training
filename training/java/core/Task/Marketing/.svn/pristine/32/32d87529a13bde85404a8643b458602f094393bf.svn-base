import DocumentUpload from "@medplus/react-common-components/DocumentUpload";
import DynamicForm, { ALERT_TYPE, withFormHoc } from '@medplus/react-common-components/DynamicForm';
import React, { useContext, useEffect, useState } from "react";
import { PromotionStatus, PromotionType } from "../constants/PromotionConstants";
import Validate from '../helpers/Validate';
import { AlertContext } from './Contexts/UserContext';
import NextPrevBtn from './common/NextPrevButtons';
import { SplitBy } from "../formJsons/splitByRadioButton";

const ItemsUpload = (props) => {

    const isEdit = props?.actionPermissions?.isEdit;
    const setLoading = props.setLoading;

    const customerFlag=props.customerFlag
    const couponType = props?.couponType;
    const [documentTrigger, setDocumentTrigger] = useState(false);

    const showUpload = !(props?.actionPermissions?.isApprover || props?.actionPermissions?.isCloser) && (props?.actionPermissions?.isCreate || props?.actionPermissions?.isClone || (isEdit && props.globalPromotionStatus === PromotionStatus.inActive));
    const showRemove = !(props?.actionPermissions?.isApprover || props?.actionPermissions?.isCloser) && (isEdit && props.globalPromotionStatus === PromotionStatus.inActive);

    const {setAlertContent} = useContext(AlertContext)
    const providedExcelData = {};
    const onProductUpload = (files) => {
        providedExcelData['productUpload'] = files;
    }
    const onProductRemoveUpload = (files) => {
        providedExcelData['productRemove'] = files;
    }
    const onCustomerRemoveUpload = (files) => {
        providedExcelData['customerRemove'] = files;
    }
    const onCustomerUpload = (files) => {
        providedExcelData['customerUpload'] = files;
    } 

    {/* Complimentary Promotion */}
    const onComplimentaryProductUpload = (files) => {
        providedExcelData['complimentaryProductUpload'] = files;
    }
    const onComplimentaryProductRemove = (files) => {
        providedExcelData['complimentaryProductRemove'] = files;
    }
    const onOthersUpload = (files) => {
        providedExcelData['othersUpload'] = files;
    }

    const handlePrevBtnClick = () => {
        props.handleCampaignInfoChange(providedExcelData, props.tabDetails.currentTab.tabId, props.tabDetails.prevTab.tabId);
    }

    useEffect(()=>{
        if(documentTrigger){
            handleSubmit();
        }
    }, [providedExcelData])
    
    const handleSubmit =  ()=>{
        setDocumentTrigger(false);
        if(props?.actionPermissions?.isApprover) {
            props.handleCampaignInfoChange({}, props.tabDetails.currentTab.tabId, props.tabDetails.currentTab.tabId+1, false, false, false, true, undefined);
        } else{
            if(props.promotionType === PromotionType.complimentary && validateComplimentaryFiles()) {
                return false;
            } else if(props.promotionType !== PromotionType.complimentary && validateMultiItemOrCampaignFiles(providedExcelData)) {
                return false;
            }
            else{
                if(props?.actionPermissions?.isCreate){
                    props.handleCampaignInfoChange(providedExcelData, props.tabDetails.currentTab.tabId, props.tabDetails.currentTab.tabId+1, false, false, true, false, undefined);
                } else if(props?.actionPermissions?.isEdit){
                    props.handleCampaignInfoChange(providedExcelData, props.tabDetails.currentTab.tabId, props.tabDetails.currentTab.tabId+1, true, false, false, false, undefined);
                } else if(props?.actionPermissions?.isClone){
                    props.handleCampaignInfoChange(providedExcelData, props.tabDetails.currentTab.tabId, props.tabDetails.currentTab.tabId+1, false, true, false, false, undefined);
                }
            }
        }
    }

    const validateComplimentaryFiles = () => {
        const isCreateOrClone = (props?.actionPermissions?.isCreate || props?.actionPermissions?.isClone);
        if(isCreateOrClone && Validate().isEmpty(providedExcelData['complimentaryProductUpload'])) {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Please upload the Products File'});
            return true;
        } 
        if(isCreateOrClone && customerFlag === 'N' && Validate().isEmpty(providedExcelData['othersUpload'])) {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'References and Customers file is mandatory when all customers is not selected'});
            return true;
        } 
        return false;
    }

    const validateMultiItemOrCampaignFiles = () => {
        const isCreateOrClone = (props?.actionPermissions?.isCreate || props?.actionPermissions?.isClone);
        if(isCreateOrClone && couponType!='2' && Validate().isEmpty(providedExcelData['productUpload'])) {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Please upload the Products File'});
            return true;
        } else if (isCreateOrClone && customerFlag === 'N' && Validate().isEmpty(providedExcelData['customerUpload'])) {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Please upload the Customers File'});
            return true;
        } else if(props?.actionPermissions?.isEdit && props.existingCouponBased=='Y' && props.existingCouponType=='2' && couponType!='2' && Validate().isEmpty(providedExcelData['productUpload'])){
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Please upload the Products File'});
            return true;
        }
        return false;
    }

    const getComplimentaryUploads = () => {
        return (
            <>
            <div class="row" >
                {showUpload && <div class="col" id="complimentaryProductUpload" >
                        {RenderDocumentUpload('complimentaryProductUpload', "Complimentary Gift's Upload",onComplimentaryProductUpload, 'complimentary-product-upload')}
                    </div>
                }
                {showRemove &&  <div class="col" id="complimentaryProductRemove">
                        {RenderDocumentUpload('complimentaryProductRemove', "Complimentary Gift's Remove",onComplimentaryProductRemove, 'complimentary-product-remove')}
                    </div>
                }
            </div>
            <div class="row" >
                {showUpload &&  <div class="col" id="othersUpload" >
                        <label className="custom-fieldset">
                            {customerFlag === 'Y' ? ('References Upload' + (showRemove ? '/ Remove' : '')) 
                                : ('References and Customers Upload' + (showRemove ? '/ Remove' : ''))
                            }
                        </label>
                        {RenderDocumentUpload('othersUpload', "",onOthersUpload, 'others-upload')}
                    </div>
                }
            </div>
        </>);
    }

    const RenderDocumentUpload = (id, label, onSuccess, type, disableFileUpload = false) => {
        return (
            <div className="col" id={id}>
                <label class="font-12 my-2 text-secondary">{label}</label>
                <DocumentUpload
                    fileSelectOption={true}
                    documentScanOption={false}
                    buttonClassName={'scan-button'}
                    imageContainerClassName={'image-container'}
                    isAppendAllowed={false}
                    resetAddedDocuments={true}
                    uploadActionInParent={false}
                    getAddedDocuments={documentTrigger}
                    singleFileUpload={true}
                    disableFileUpload={disableFileUpload}
                    allowedFileFormats={".xls,.xlsx"}
                    onSuccessResponse={(files) => onSuccess(files)}
                    onErrorResponse={(message) => { setAlertContent({ message: message, show: true, alertType: "Error" }) }}
                    onDeleteResponse={() => { }}
                    includeLightBox={false}
                    imageTitle={type}
                />
            </div>
        );
    }

    const onSplitByChange = (value) => {
        props?.setSplitBy(value)
    }
    
    const oberserverMap = {
        'sliptBy': [['change', (payload) => onSplitByChange(payload[0].target.value)]],
    }

    return(
        <React.Fragment key={`${props.location.pathname}`}>
                <div>
                    <div>
                    {!(props?.actionPermissions?.isApprover || props?.actionPermissions?.isCloser) &&
                    <label  className="custom-fieldset" >Items</label>
                    }
                    {props.promotionType !== PromotionType.complimentary &&
                        <div class="row g-2" >
                            {showUpload &&
                                <div class="col" id="productUpload" >
                                    {RenderDocumentUpload('productUpload', "Items ID's Upload",onProductUpload, 'product-upload', couponType === '2')}
                                </div>
                            }
                            {showRemove &&
                                <div class="col" id="productRemove">
                                    {RenderDocumentUpload('productRemove', 'Items Remove', onProductRemoveUpload, 'product-remove', couponType === '2')}
                                </div>
                            }
                            {props?.actionPermissions?.isCreate && props?.isMultipleCampaigns && <DynamicForm  formJson ={SplitBy} helpers={props?.helpers} observers={oberserverMap} />}
                        </div>
                    }
                    
                    {props.promotionType == PromotionType.complimentary && getComplimentaryUploads()}

                        {props.promotionType !== PromotionType.complimentary && customerFlag !=="Y" &&
                        // {customerFlag !=="Y" &&
                        <div className="mt-3">
                            {!(props?.actionPermissions?.isApprover || props?.actionPermissions?.isCloser) &&
                            <label className="custom-fieldset">Customers</label>
                            }
                            <div class="row" >
                                {showUpload &&
                                    <div class="col" id="customerUpload">
                                        {RenderDocumentUpload('customerUpload', "Customer ID's Upload", onCustomerUpload, 'customer-upload')}
                                    </div>
                                }
                                {showRemove &&
                                    <div class="col" id="customerRemove">
                                        {RenderDocumentUpload('customerRemove', "Customer ID's Remove", onCustomerRemoveUpload, 'customer-remove')}
                                    </div>
                                }
                            </div>
                        </div>
                        }
                        
                        <NextPrevBtn isNoteRequired={true} noteItems= {props.uploadsNote} {...props} 
                        PrevTab={props.tabDetails.prevTab.title}
                        changeNxtBtn={true} 
                        handlePrevBtnClick={handlePrevBtnClick} 
                        handleNextBtnClick={() => setDocumentTrigger(true)} 
                        showNote={!(props?.actionPermissions?.isApprover || props?.actionPermissions?.isCloser)}
                        isApprover={props?.actionPermissions?.isApprover} isEdit={isEdit} />
                    </div>
                </div>
        </React.Fragment>
    )
}

export default withFormHoc(ItemsUpload);