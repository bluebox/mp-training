import DynamicForm, { ALERT_TYPE, CustomAlert, withFormHoc } from "@medplus/react-common-components/DynamicForm";
import React, { useEffect, useState } from "react";
import { Button, Modal } from 'react-bootstrap';
import { PromotionType } from "../constants/PromotionConstants";
import { RejectTextArea } from "../formJsons/rejectTextArea";
import Validate from "../helpers/Validate";
import { API_URL } from "../services/ServiceConstants";

function RejectModal(props) {
  const  [ alertContent,setAlertContent ] = useState()
  const [modal, setModal] = useState(false);
  const [showToast, setShowToast] = useState(false);
  const [remarks, setRemarks] = useState();
  const [loading, setLoading] = useState(false);
  const headerName = ( props?.promotionType === PromotionType.campaign || props.promotionType === PromotionType.multiItem ) ? 'Campaign' : 'Promotion';
  useEffect(() => {
    setModal(props.show);
    setShowToast(true)
  }, []);
  const toggle = () => {
    props.setRejectModalInfo({showRejectModal:false})
    onAlertClose()
    setModal(false);
  }
  const handleRejectCampaign = async (e) => {
    setLoading(true)
    setShowToast(true);
    const campaignId = props.campaignId;
    if(!(remarks && remarks.length > 0 && remarks.trim().length >0)){
      setAlertContent({
        alertType: ALERT_TYPE.ERROR,
        alertMessage: 'Remarks are mandatory',
      });
      if(remarks && remarks.length > 255){
		 setAlertContent({
        alertType: ALERT_TYPE.ERROR,
        alertMessage: 'Maximum remarks length is 255 chars',
      }); 
	  }
      setLoading(false)
      return false;
    }

    if (Validate().isNotEmpty(campaignId)) {
      let formData = new FormData();
      formData.append("campaignId", campaignId);
      formData.append("remarks", remarks);
      const response = await props.rejectService(formData);
      if (Validate().isNotEmpty(response)) {
        if (response.statusCode === "SUCCESS") {
          setLoading(true)
          // setAlertContent({
            // alertType: ALERT_TYPE.SUCCESS,
            // alertMessage: `campaignID [${campaignId}] Rejected successfully.. Rediecting to Home`,
            // handleCallBack: props.handleCallBack ? props.handleCallBack:successCallBackHandler,
            // delayTime: 3000,
          // });
          setModal(false); 
          props.setRejectModalInfo({showRejectModal:false})
          props.successCallBackHandler();
          // setModal(!modal);
        } else {
          setAlertContent({
            alertType: ALERT_TYPE.ERROR,
            alertMessage: response.message,
          });
          setLoading(false)
        }
      }
    } else {
      setAlertContent({
        alertType: ALERT_TYPE.ERROR,
        alertMessage: "Unable to Reject Campagin",
      });
      setLoading(false)
    }
    
  };
  const successCallBackHandler = () => {
    window.location.href=`${API_URL}ui/list-multi-item-campaign`
  };

  const onAlertClose =() => {
    setShowToast(false);
    // setLoading(false)
    
    if(alertContent.handleCallBack) {
      alertContent?.handleCallBack();
      setModal(!modal);
      props.setRejectModalInfo({showRejectModal:false})
    }
    
  }

  const handleCancel =() => {
    setModal(false);
    props.setRejectModalInfo({showRejectModal:false})
  }
  useEffect(() => {
    return () =>{
        setAlertContent({});
    }

},[])

const handleDescriptionCount = () =>{
  if(Validate().isNotEmpty(props.helpers.getHtmlElementValue('remarks')) && props.helpers.getHtmlElementValue('remarks').length>255){
      props.helpers.updateErrorMessage("Max 255 letters are allowed", "remarks");
  }
  else{
      props.helpers.updateErrorMessage("","remarks");
  }
  props.helpers.updateSingleKeyValueIntoField("helperText", Validate().isNotEmpty(props.helpers.getHtmlElementValue("remarks")) ? props.helpers.getHtmlElementValue("remarks").length +"/ 255" : "0 / 255","remarks")
  setRemarks(props.helpers.getHtmlElementValue("remarks"))
}

const updateRejectForm = () => {
  props.helpers.updateValue([], "remarks", false);
}

const oberserverMap = {
  'remarks':[['change', () =>handleDescriptionCount()]],
  'rejectForm' : [['load', () => updateRejectForm()]]
}

  return (
    <React.Fragment>
    <div className='custom-border-bottom-dashed'>
        <Modal isOpen={modal} toggle={toggle} {...props}  backdrop={"static"} size="lg" aria-labelledby="contained-modal-title-vcenter" centered >
        <Modal.Header toggle={toggle} isOpen={modal}  {...props} closeButton onHide={toggle}>
          <Modal.Title className='h6'>Do you want to reject this {headerName}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {showToast && alertContent && alertContent.alertMessage && <CustomAlert
                          alertType={alertContent.alertType ? alertContent.alertType : ALERT_TYPE.ERROR}
                          alertMessage={() => alertContent.alertMessage ? alertContent.alertMessage : ""}
                          isShow={showToast}
                          // isDismissibleRequired={true}
                          isAutohide={true}
                          delayTime={3000}
                          isTransition={true}
                          onClose={alertContent.onClose ? alertContent.onClose : onAlertClose}
          />}
          
          <div className="row">
               <div className='form-floating'>
                 <input aria-label="text input" type="text" id="campaignDetails" class="form-control-plaintext" value={`${props.campaignName} - ${props.campaignId}`}/>
                 <label for="campaignId">{headerName} Details</label>
               </div>
           </div>

      <DynamicForm  formJson ={RejectTextArea} helpers={props.helpers} observers={oberserverMap} />

        </Modal.Body>

        <Modal.Footer className="p-2 justify-content-center">
          <div>           
            <button type="button" tabindex="3" class="px-5 me-3 brand-secondary btn-sm btn" disabled={loading} onClick={handleCancel}>Close</button>
            <Button variant="brand" disabled={!(remarks && remarks.length > 0 && (remarks.trim()).length >0 && (remarks.length <=255))} className="btn-sm px-3" onClick={handleRejectCampaign}>Yes, Reject this {headerName}</Button>
          </div>
        </Modal.Footer> 
      </Modal>
      </div>
    </React.Fragment>
    );
}

export default withFormHoc(RejectModal);