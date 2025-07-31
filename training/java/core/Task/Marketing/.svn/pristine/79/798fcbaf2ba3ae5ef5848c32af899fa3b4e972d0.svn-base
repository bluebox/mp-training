import DynamicForm, { ALERT_TYPE, withFormHoc } from '@medplus/react-common-components/DynamicForm';
import React, { useContext, useEffect, useState } from "react";
import { PromotionStatus } from '../../constants/PromotionConstants';
import Validate from "../../helpers/Validate";
import { API_URL } from "../../services/ServiceConstants";
import { AlertContext } from '../Contexts/UserContext';
import NextPrevBtn from "../common/NextPrevButtons";

const ComplimentaryPromotionSlab = (props) => {
 
  const validate = Validate();
  const [slabInfo, setSlabInfo] = useState(props.slabs);
  const { setAlertContent } = useContext(AlertContext);
  const globalPromotionStatus = props.globalPromotionStatus;

  useEffect(() => {
    const msgDisplayInput = document.getElementById("messageDisplayPercentage")
    if (msgDisplayInput) {
      msgDisplayInput.focus();
    }
  }, [document.getElementById("messageDisplayPercentage")]);

  const handleCreateNewSlabClick = ()=> {
      props.helpers.updateSingleKeyValueIntoField("value", "", "invoiceAmount", true);
      props.helpers.updateSingleKeyValueIntoField("value", "", "slabGroupName", true);
      props.helpers.showElement('invoiceAmountGrp');
      props.helpers.showElement('slabGroupNameGrp');
      props.helpers.updateSingleKeyValueIntoField("value", "", "invoiceBtn", true);
      props.helpers.hideElement('invoiceBtn');
      props.helpers.updateValue([],'existingSlabGroups',false);
      props.helpers.resetForm('existingSlabGroups')
      setSlabInfo({});
  }

  const onExistingSlabGroupChange = (slabIdwithAmount) => {
     if(Validate().isEmpty(slabIdwithAmount)) {
        props.helpers.updateSingleKeyValueIntoField("value", "", "invoiceBtn", true);
        props.helpers.hideElement('invoiceBtn');
        setSlabInfo({});
     } else {
      props.helpers.updateSingleKeyValueIntoField("value", "", "invoiceAmount", true);
      props.helpers.updateSingleKeyValueIntoField("value", "", "slabGroupName", true);
      props.helpers.hideElement('slabGroupNameGrp');
      props.helpers.hideElement('invoiceAmountGrp');
      props.helpers.showElement('invoiceBtn');
      const complimentarySlabId = slabIdwithAmount.split("#")[0];
      const invoiceAmount = slabIdwithAmount.split("#")[1]
      props.helpers.updateSingleKeyValueIntoField("value", invoiceAmount, "invoiceBtn", true);
      const existingSlabItem = props.helpers.collectSpecificFieldValues('complimentaryPromotionSlabs',['existingSlabGroups']);
      setSlabInfo({...existingSlabItem, complimentarySlabId : complimentarySlabId, invoiceAmount: invoiceAmount});
     }
  }

  const handleNextBtnClick = ()=>{
      const details = props.helpers.validateAndCollectValuesForSubmit('complimentaryPromotionSlabs',false);
      if(Validate().isEmpty(details))
        return false;
      if(Validate().isEmpty(details.messageDisplayPercentage) || parseFloat(details.messageDisplayPercentage)<=0 || parseFloat(details.messageDisplayPercentage)<=0 || parseFloat(details.messageDisplayPercentage) >= 100) {
        setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Message Display Percentage must be in between 0.0 to 100.00'})
      } else if(Validate().isEmpty(details.existingSlabGroups) && (Validate().isEmpty(details.slabGroupName) || Validate().isEmpty(details.invoiceAmount))) {
        setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please select or Create slab for promotion'})
      } else if (Validate().isEmpty(details.existingSlabGroups) && (details.slabGroupName?.length < 4 || details.slabGroupName?.length > 60 || details.slabGroupName.startsWith("-") || details.slabGroupName.startsWith("_"))) {
        setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Slab group name length should be in between 4 and 60 and cannot start with -, _ '});
      } else if(Validate().isEmpty(details.existingSlabGroups) && (parseFloat(details.invoiceAmount) <= 0) || (parseFloat(details.invoiceAmount) >= 10000000)) {        
        setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Invoice Amount should be below 8 digits'})
      } 
      else {
          props.handleCampaignInfoChange(
            Validate().isNotEmpty(slabInfo.existingSlabGroups) ? {...slabInfo, messageDisplayPercentage: details.messageDisplayPercentage}  : {slabGroupName : details.slabGroupName, invoiceAmount : details.invoiceAmount, messageDisplayPercentage : details.messageDisplayPercentage},
            props.tabDetails.currentTab.tabId, props.tabDetails.nextTab.tabId);
      }
      return false;
  }

  const handlePrevBtnClick = ()=>{
      const details = props.helpers.collectSpecificFieldValues('complimentaryPromotionSlabs',['slabGroupName', 'invoiceAmount', 'messageDisplayPercentage'])
        if(Validate().isEmpty(slabInfo?.existingSlabGroups)) {
          slabInfo['slabGroupName'] = details.slabGroupName;
          slabInfo['invoiceAmount'] = details.invoiceAmount;
        }
        slabInfo['messageDisplayPercentage'] = details.messageDisplayPercentage;
      props.handleCampaignInfoChange(slabInfo, props.tabDetails.currentTab.tabId, props.tabDetails.prevTab.tabId);
  }

  const updateComplimentaryPromotionSlabForm = ()=> {
      props.setHelpers(props.helpers);
      props.setLoading(true);
      if(Validate().isNotEmpty(slabInfo)){
        if(Validate().isEmpty(slabInfo.existingSlabGroups)) {
           if(Validate().isNotEmpty(slabInfo?.slabGroupName) || Validate().isNotEmpty(slabInfo?.invoiceAmount)) {
              props.helpers.showElement('invoiceAmountGrp');
              props.helpers.showElement('slabGroupNameGrp');
           }
        } else {
            props.helpers.showElement('invoiceBtn');
            props.helpers.updateSingleKeyValueIntoField("value", slabInfo.invoiceAmount, "invoiceBtn", true);
        }
        props.helpers.updateSpecificValues(slabInfo, 'complimentaryPromotionSlabs');
      }
      if(props.actionPermissions.isApprover ||  props.globalPromotionStatus === PromotionStatus.active) {
          props.helpers.updateKeyValuesToAllFields("disabled", "true", "complimentaryPromotionSlabs");
          props.helpers.hideElement("slabBtnGrp");
      } else if(props.actionPermissions.isEdit) {
        props.helpers.disableElement("existingSlabGroups");
        props.helpers.hideElement("slabBtnGrp");
      }
      props.setLoading(false);
  }

  const oberserverMap = {
      'complimentaryPromotionSlabs' :[['load', () => updateComplimentaryPromotionSlabForm()]],
      'createNewSlabGrpBtn' : [['click', ()=>(handleCreateNewSlabClick())]],
      'existingSlabGroups': [['select', (payload) => onExistingSlabGroupChange(payload[1].value[0])]],
      'slabGroupName' : [['change', (payload) => props.handleSlabGrpNameChange()]]
  }


    const customOrSeparator = () => {
        return (
        <div class="col-3" id="or">
            <div class="position-relative py-2 mt-3 mb-3">
            <hr class="border-style-dashed" />
            <span class="separator-or-badge">OR</span>
            </div>
        </div>
        );
    };

    const injectHtml = {
        "createNewSlabGrpBtn" : [['INSERT_BEFORE', customOrSeparator]]
    }

  return (
      <>
          <React.Fragment  key={`${props.location.pathname}`}> 
          {<> 
              <div>
                      <DynamicForm 
                          helpers={props.helpers} 
                          observers={oberserverMap}
                          requestUrl={`${API_URL}get-complimentary-promotion-slab-form`} 
                          requestMethod={'GET'} 
                          customHtml={injectHtml}
                      />
              </div> 
                  <NextPrevBtn PrevTab={props.tabDetails.prevTab.title} nextTab={props.tabDetails.nextTab.title}  {...props} 
                   handleNextBtnClick = {handleNextBtnClick}
                   handlePrevBtnClick={handlePrevBtnClick} /> 
          </>}

          </React.Fragment>
      </>
  )

}

export default withFormHoc(ComplimentaryPromotionSlab)