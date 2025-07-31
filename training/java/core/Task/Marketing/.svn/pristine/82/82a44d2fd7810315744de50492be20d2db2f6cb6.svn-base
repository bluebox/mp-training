import DynamicForm, { ALERT_TYPE, withFormHoc } from '@medplus/react-common-components/DynamicForm';
import React, { useContext, useEffect, useState } from "react";
import MarketingService from "../../services/MarketingService";
import { API_URL } from "../../services/ServiceConstants";
import NextPrevBtn from "../common/NextPrevButtons";
import Validate from "../../helpers/Validate";
import { AlertContext } from '../Contexts/UserContext';
import RemoveIconRed from '../../images/delete-icn-16.svg';
import RemoveIconBlack from '../../images/delete-icn-16-black.svg';
import Button from 'react-bootstrap/Button';
import ResponseHandler from '../../helpers/ResponseHandler';
import { PromotionStatus } from '../../constants/PromotionConstants';

let disableRemoveButton = false;
const PromotionSlabs = (props) => {

    const validate = Validate();
    const [slabInfo, setSlabInfo] = useState(props.slabInfo);
    const { setAlertContent } = useContext(AlertContext);
    const globalPromotionStatus = props.globalPromotionStatus;
    const setLoading = props.setLoading;
    const handleCreateNewSlabClick = ()=> {
        props.helpers.showElement('grp3');
        props.helpers.showElement('addGrp');
        props.helpers.resetForm('regPromotionSlabs');
        setSlabInfo(prevSlabInfo => ({ ...prevSlabInfo, slabs: [] }));
    }
    
    const handleAddNewSlabClick = ()=>{
        const slab = props.helpers.collectSpecificFieldValues('regPromotionSlabs',['fromValue','toValue'])
        //validate name also
        if(Validate().isEmpty(slab) || Validate().isEmpty(slab.fromValue) || Validate().isEmpty(slab.toValue)) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter From Value and To Value of the slab'});
            return false;
        }else if(slab.fromValue<0 || slab.fromValue>999999.9999){
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'From Value of the slab can not be greater than 999999.9999'});
            return false;
        }else if(slab.toValue<0 || slab.toValue>999999.9999){
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'To Value of the slab can not be greater than 999999.9999'});
            return false;
        }
        if(validateSlab(slab)) {
            // setDisableRemoveButton(false);
            disableRemoveButton = false;
        }
    }

    const validateSlab = (slab)=> {
        if(!slab) {                              
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter Slab details'})
        } else if (parseFloat(slab.fromValue) >= parseFloat(slab.toValue)) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'To value shoud be greater than From value'})
        }
        else if(Validate().isNotEmpty(slabInfo) && Validate().isNotEmpty(slabInfo.slabs) &&  slab.fromValue != slabInfo.slabs[slabInfo.slabs.length-1].toValue) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"From value must match with previous slab's To value."})
        }
        else {
            setSlabInfo(prevSlabInfo => ({ ...prevSlabInfo, slabs: [...prevSlabInfo.slabs, slab] }));
            props.helpers.updateValue(slab?.toValue,'fromValue',false);
            props.helpers.disableElement("fromValue");
            props.helpers.updateValue('','toValue',false);
            return true;
        }
        return false;
    }

    const onExistingSlabGroupChange = async (slabGrpId) => {
        props.helpers.hideElement('grp3');
        props.helpers.hideElement('addGrp');
        props.helpers.updateValue('','slabGroupName');
        props.helpers.updateValue('','fromValue');
        props.helpers.enableElement('fromValue')

        setSlabInfo(prevSlabInfo => ({ ...prevSlabInfo, slabs: [] }));

        if(Validate().isEmpty(slabGrpId)) {
            if(Validate().isNotEmpty(slabInfo))
                slabInfo.slabs.splice(0,slabInfo.slabs.length);
            return;
        }
        setLoading(true);
        const response = await MarketingService().getSlabGroupDetails({ 'slabGroupId': slabGrpId }).catch(error => {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to fetch Slab group details'});
        });
        ResponseHandler(setAlertContent).handleResponse(response, {}, (data) => {
            disableRemoveButton = true;
            setSlabInfo(prevSlabInfo => ({ ...prevSlabInfo, slabs: data.slabGroupDetails }));
        }, (error) => {
          setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: error})
        });
        setLoading(false);
    }

    const handleNextBtnClick = ()=>{
        if(!slabInfo.slabs || slabInfo.slabs.length <=0) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please select or Create slab group for promotion'})
        } else if(Validate().isEmpty(props.helpers.collectSpecificFieldValues('regPromotionSlabs',['existingSlabGroups']).existingSlabGroups) &&
            Validate().isEmpty(props.helpers.collectSpecificFieldValues('regPromotionSlabs',['slabGroupName']).slabGroupName)) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter Slab group name'})
        } else {
            const names = props.helpers.collectSpecificFieldValues('regPromotionSlabs',['existingSlabGroups','slabGroupName'])
            const existingSlabGroups = props.helpers.collectSpecificFieldValues('regPromotionSlabs',['existingSlabGroups'])
            const slabGroupName = props.helpers.collectSpecificFieldValues('regPromotionSlabs',['slabGroupName'])
            slabInfo["existingSlabGroups"] = names.existingSlabGroups;
            slabInfo["slabGroupName"] = names.slabGroupName;
            if(validate.isNotEmpty(slabInfo.slabGroupName)) 
                saveSlab(slabInfo)
            else
                props.handleCampaignInfoChange(slabInfo, props.tabDetails.currentTab.tabId, props.tabDetails.nextTab.tabId);
        }
        return false;
    }

    const saveSlab = async () => {
        let formData = new FormData();
        let slabGrpObj = {};

        slabGrpObj["slabGroupId"] = slabInfo["existingSlabGroups"] ? slabInfo["existingSlabGroups"][0] : null;
        slabGrpObj["newSlabGroupName"] = slabInfo["slabGroupName"];
        slabGrpObj["slabs"] = slabInfo["slabs"]
        slabGrpObj["slabGroupModified"]= false;
        formData.append('slabGroup', JSON.stringify(slabGrpObj));
        setLoading(true);        
        const response = await MarketingService().saveSlabDetails(formData).catch(error => {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage: 'Unable to Insert/Update Slab details'});
        });
        ResponseHandler(setAlertContent).handleResponse(response, {}, (data) => {
            const selectedOption=[]
            selectedOption.push(data.slabGroupId)
            let savedSlabInfo = {}
            savedSlabInfo["existingSlabGroups"] = selectedOption;
            savedSlabInfo["slabGroupName"] = null;
            savedSlabInfo['slabs']=[]
            data.slabs.forEach((slab,index) => {
                savedSlabInfo['slabs'][index] = {"slabId" :slab.slabId , "fromValue" : slab.fromValue, "toValue" : slab.toValue};
            });
            setSlabInfo(savedSlabInfo);
            props.handleCampaignInfoChange(savedSlabInfo, props.tabDetails.currentTab.tabId, props.tabDetails.nextTab.tabId);

        }, (error) => {
          setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: error})
        });
        setLoading(false);
    }

    const handlePrevBtnClick = ()=>{
        const names = props.helpers.collectSpecificFieldValues('regPromotionSlabs',['existingSlabGroups','slabGroupName'])
        slabInfo["existingSlabGroups"] = names.existingSlabGroups;
        slabInfo["slabGroupName"] = names.slabGroupName;
        props.handleCampaignInfoChange(slabInfo, props.tabDetails.currentTab.tabId, props.tabDetails.prevTab.tabId);
    }

    const updateRegularPromotionSlabForm = ()=> {
        props.setLoading(true);
        if(Validate().isNotEmpty(slabInfo)){
            if(Validate().isNotEmpty(slabInfo["slabGroupName"])) {
                props.helpers.showElement('grp3');
                props.helpers.showElement('addGrp');
                props.helpers.updateSpecificValues(slabInfo, 'regPromotionSlabs');
                const prevToValue = slabInfo?.slabs[slabInfo?.slabs?.length -1]?.toValue;
                if(Validate().isNotEmpty(prevToValue)) {
                    props.helpers.updateValue(prevToValue,'fromValue',false);
                    props.helpers.disableElement("fromValue");
                }
            } else {
                disableRemoveButton=true;
                props.helpers.updateSpecificValues(slabInfo, 'regPromotionSlabs');
            }
        }
        if(props.actionPermissions.isApprover ||  props.globalPromotionStatus === PromotionStatus.active) {
            props.helpers.updateKeyValuesToAllFields("disabled", "true", "regPromotionSlabs");
        }
        props.setLoading(false);
    }

    const oberserverMap = {
        'regPromotionSlabs' :[['load', () => updateRegularPromotionSlabForm()]],
        'createNewSlabGrpBtn' : [['click', ()=>(handleCreateNewSlabClick())]],
        'addNewSlab' : [['click', ()=>(handleAddNewSlabClick())]],
        'existingSlabGroups': [['select', (payload) => onExistingSlabGroupChange(payload[1].value[0])]]
    }

    const handleRemoveSlab = (index) => {
        slabInfo.slabs.splice(index,slabInfo.slabs.length-index);
        if(index==0) {
            props.helpers.enableElement("fromValue");
            props.helpers.updateValue(null,'fromValue',false);
        } else {
            props.helpers.disableElement("fromValue");
            props.helpers.updateValue(slabInfo?.slabs[slabInfo.slabs?.length-1]?.toValue,'fromValue',false);
        }
        setSlabInfo(prevSlabInfo => ({ ...prevSlabInfo}));
    }

    const customOrSeparator = () => {
        return(
            <div class="col-3">
                <div class="position-relative py-2">
                    <hr class="border-style-dashed" />
                    <span class="separator-or-badge">OR</span>
                </div>
            </div>
        );
    }

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
                            requestUrl={`${API_URL}get-reg-promotion-slabs`} 
                            requestMethod={'GET'} 
                            customHtml={injectHtml}
                        />
                        {slabInfo && slabInfo.slabs && slabInfo.slabs.length>0 &&  
                            <div>
                            <label className='custom-fieldset mb-2'>Slabs</label>
                            <div className="d-flex flex-wrap gap-3" style={{ maxHeight: '240px', overflowY: 'auto' }}>
                            { slabInfo.slabs.map( (slab, index) => (   
                                <Slab slab={slab}  key={index} index = {index} handleRemoveSlab={handleRemoveSlab} disableRemoveButton={disableRemoveButton} props={{...props}}/>
                                )) 
                            }
                            </div>
                        </div> }
                </div> 
                    <NextPrevBtn PrevTab={props.tabDetails.prevTab.title} nextTab={props.tabDetails.nextTab.title}  {...props} 
                     handleNextBtnClick = {handleNextBtnClick}
                     handlePrevBtnClick={handlePrevBtnClick} /> 
            </>}

            </React.Fragment>
        </>
    )
}

const  Slab = (props) => {
    const {slab,handleRemoveSlab, index} = props
    const campaignType = props.campaignType;
    // const [disableRemoveButton, setDisableRemoveButton] = useState(false);
    const [removeImg, setRemovImg] = useState(RemoveIconBlack);
    const changeImg = (imgColor) => {
        (imgColor === "red")? setRemovImg(RemoveIconRed):setRemovImg(RemoveIconBlack);
    }
    
  return (
    <>     
           <Button disabled={props.disableRemoveButton} variant="light" className={`${props.className} align-items-center btn btn-light d-flex rounded-5 mb-3 me-3 `} onMouseOver={() => changeImg("red")} onMouseOut={() => changeImg("black")} >
            <span>
                <span> From Value </span>
                <b>{slab.fromValue}</b>
                <span> To Value </span>
                <b>{slab.toValue}</b>
            </span>
              {!props.disableRemoveButton && <img  src={removeImg} onClick={(e) => handleRemoveSlab(index)} alt={"Remove"} className={"ms-2 align-top"} />}
          </Button>
    </>
  )
}
                                                                
export default withFormHoc(PromotionSlabs)