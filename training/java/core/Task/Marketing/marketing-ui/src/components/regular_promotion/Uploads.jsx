import DocumentUpload from "@medplus/react-common-components/DocumentUpload";
import { ALERT_TYPE } from '@medplus/react-common-components/DynamicForm';
import React, { useContext, useEffect, useState } from 'react';
import DownloadIcon from "../../../src/images/download_icon.svg";
import { PromotionStatus } from "../../constants/PromotionConstants";
import Validate from '../../helpers/Validate';
import MarketingService from "../../services/MarketingService";
import { AlertContext } from '.././Contexts/UserContext';

const Uploads = (props) => {
  
    const documentTrigger = props.documentTrigger
    const customerFlag=props.customerFlag ? 'N' : 'Y';
    const {setAlertContent} = useContext(AlertContext)

    const providedExcelData = {};
    const onCustomerUpload = (files) => {
        providedExcelData['customerUpload'] = files;
    } 
    const onCustomerRemoveUpload = (files) => {
        providedExcelData['customerRemove'] = files;
    }

    useEffect(()=>{
        if(documentTrigger){
            handleSubmit();
        }
    }, [providedExcelData])
    
    const handleSubmit =  ()=>{
        const isCreateOrClone = (props?.actionPermissions?.isCreate || props?.actionPermissions?.isClone)
        if(isCreateOrClone && customerFlag==='N' && Validate().isEmpty(providedExcelData['customerUpload'])) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please upload the Customers File'});
            props.setDocumentTriggerFalse(false);
        }
        else{
            props.setExcelData(providedExcelData);
        }
        props.setDocumentTriggerFalse(false);
    }

    const noteItems = [
        "Customer: Excel file contains one column (CustomerId) to remove or upload.",
        "While cloning the campaign Items/Customer Excel file need to uploaded freshly."
    ]
 
    return(
        <React.Fragment key={`${props.location.pathname}`}>
                        {props?.fileOptions?.customerFile?.show  &&
                        <div>
                            <div class="row">
                                {!(props?.actionPermissions?.isApprover || props?.actionPermissions?.isCloser) && 
                                (props?.actionPermissions?.isCreate || props?.actionPermissions?.isClone || (props?.actionPermissions?.isEdit && props?.globalPromotionStatus === PromotionStatus.inActive)) &&
                                    <div class="col" id="customerUpload">
                                        <label  className="title text-muted" >Customer ID's Upload</label>
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
                                            allowedFileFormats={".xls,.xlsx"}
                                            onSuccessResponse={(files) => { onCustomerUpload(files) }}
                                            onErrorResponse={(message) => { setAlertContent({ message: message, show: true, alertType: "Error" }) }}
                                            onDeleteResponse={() => { }}
                                            includeLightBox={false}
                                            imageTitle={"customer-upload"}
                                        />
                                    </div>
                                }

                                {!(props?.actionPermissions?.isApprover || props?.actionPermissions?.isCloser) && (props?.actionPermissions?.isEdit && props.globalPromotionStatus === PromotionStatus.inActive) &&
                                    <div class="col" id="customerRemove">
                                        <label  className="title text-muted" >Customer ID's Remove</label>
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
                                            allowedFileFormats={".xls,.xlsx"}
                                            onSuccessResponse={(files) => { onCustomerRemoveUpload(files) }}
                                            onErrorResponse={(message) => { setAlertContent({ message: message, show: true, alertType: "Error" }) }}
                                            onDeleteResponse={() => { }}
                                            includeLightBox={false}
                                            imageTitle={"customer-remove"}
                                        />
                                    </div>
                                }
                                
                            </div>
                            </div>
                        }
        </React.Fragment>
    )

}

export default Uploads