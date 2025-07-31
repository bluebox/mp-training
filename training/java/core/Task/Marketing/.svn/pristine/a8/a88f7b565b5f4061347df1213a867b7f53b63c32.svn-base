import DynamicForm, { ALERT_TYPE, withFormHoc } from '@medplus/react-common-components/DynamicForm';
import React, { useContext, useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import { useDispatch, useSelector } from 'react-redux';
import { PromotionStatus } from '../../constants/PromotionConstants';
import ResponseHandler from '../../helpers/ResponseHandler';
import Validate from '../../helpers/Validate';
import RemoveIconBlack from '../../images/delete-icn-16-black.svg';
import RemoveIconRed from '../../images/delete-icn-16.svg';
import { ADD_FORM, MI_SLABS_FORM } from '../../redux/constants';
import FormJsonService from '../../services/FormJsonService';
import { API_URL } from "../../services/ServiceConstants";
import { AlertContext } from '../Contexts/UserContext';
import NextPrevBtn from '../common/NextPrevButtons';
import CurrencyFormatter from '../util/CurrencyFormatter';

const MultiItemSlabs = (props) => {
    const  {setAlertContent,setToastContent} = useContext(AlertContext);
    const [slabs, setSlabs] = useState(props.slabs)
    const [isFormLoaded, setFormLoaded] = useState(false);
    const setLoading = props.setLoading;
    const globalPromotionStatus = props.globalPromotionStatus;
    const dispatch = useDispatch();
    const miSlabsFormRedux = useSelector((state) => Validate().isNotEmpty(state.formJsonReducer) && Validate().isNotEmpty(state.formJsonReducer[MI_SLABS_FORM]) ? state.formJsonReducer[MI_SLABS_FORM] : [])
    const [miSlabsForm, setMiSlabsForm] = useState();

    useEffect(()=>{
        if(Validate().isEmpty(miSlabsForm)) {
            loadMISlabsForm();
        }
        setSlabs(props.slabs)
        setLoading(true);
        if(globalPromotionStatus === PromotionStatus.active || props?.actionPermissions?.isApprover) {
            props.helpers.updateKeyValuesToAllFields("disabled", "true", "multiItemCampaignSlab");
        }
        setLoading(false);
    },[props.slabs])

    const getExistingSlabs = () => {
        let existingSlabs = {};
        slabs.forEach(element => {
            existingSlabs[element.numberOfPacks] = element;
        });
        return existingSlabs;
    }

    const loadMISlabsForm = async () => {
        setLoading(true);
        setFormLoaded(false);
        let miSlabsFormObj = miSlabsFormRedux;
        console.log('getting miSlabsFormObj from Redux: ')
        if(Validate().isEmpty(miSlabsFormObj) || miSlabsFormObj.length <=0) {
            console.log('getting miSlabsFormObj from Server')
            const response =await FormJsonService().getMultiItemSlabsForm().catch(error => {
                setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to load form'});
            });
        ResponseHandler(setAlertContent).handleResponse(response,{'responseType':'FORM'},(data) => {
            miSlabsFormObj = data;
            let formObj={};
            formObj[MI_SLABS_FORM] = data
            dispatch({type: ADD_FORM, payload : formObj})
        }, (error) => {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: error});
        });
        }
        setMiSlabsForm(miSlabsFormObj);
        setFormLoaded(true);
        setLoading(false);
      }

    const handleBundleSlab = () => {
        const slab  = props.helpers.collectValuesForSubmit('multiItemCampaignSlab');     
        if(Validate().isEmpty(slab.sellingPriceOrNumberOfPacks)) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter Slab Details'});
            return false;
        } else if(Validate().isNotEmpty(slabs)){
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Only one slab is allowed for creating Multi Item Campaign - Bundle'})
            return false;
        }

        const roundedTotalPrice = Math.round(slab.sellingPriceOrNumberOfPacks * 100) / 100;
        if(roundedTotalPrice <= 0 || roundedTotalPrice > 999999.99){
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Bundle price should be greater than zero and less than 10 lakh'})
            return false;
        }
        setSlabs([{"numberOfPacks" : 1, "sellingPriceOrNumberOfPacks" : roundedTotalPrice}]);
        props.helpers.resetForm('multiItemCampaignSlab');
    }

    const handleAddNewSlabClick = () => {
        const slab  = props.helpers.validateAndCollectValuesForSubmit('multiItemCampaignSlab');        
        if(!slab) {
            // setToastContent({ toastMessage: 'Please Enter Slab Details', position: TOAST_POSITION.TOP_END });
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter Slab Details'})
            return false;
        } else if(slab && !slab.numberOfPacks) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter NumberOfPacks'})
            return false;
        } else if(slab && !slab.sellingPriceOrNumberOfPacks) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter TotalPrice or PayablePacks for Price'})
            return false;
        } 

        const roundedTotalPrice = Math.round(slab?.sellingPriceOrNumberOfPacks * 100) / 100;
        if(slab.numberOfPacks<=0 ) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'No Of Packs should be greater than zero'})
            return false;
        } else if (roundedTotalPrice <= 0 || roundedTotalPrice > 999999.99){
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Total Price or Payable Packs should be greater than zero and less than 10 lakh'})
            return false;
        } else if(slab && props.campaignType !== '8' && roundedTotalPrice > Number(slab.numberOfPacks)) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Payable Packs must be less than Number Of Packs'})
            return false;
        } else if(slab && props.campaignType !== '8' && !(Number.isInteger(Number(roundedTotalPrice)))) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Payable Packs must be an integer'})
            return false;
        } else if(getExistingSlabs()[slab.numberOfPacks]) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Slab already exists'})
            return false;
        }  else {
            setSlabs(prevSlabs  => [...prevSlabs, slab]);
            props.helpers.resetForm('multiItemCampaignSlab');
        }
        
    }
    const onFormLoad = () => {
        if(props?.actionPermissions?.isEdit) {
            if(globalPromotionStatus === PromotionStatus.active || props?.actionPermissions?.isApprover){
                props.helpers.updateKeyValuesToAllFields("disabled", "true", "multiItemCampaignSlab");
            }
        }
        if(props.campaignType === '10') {
            props.helpers.hideElement('numberOfUnitsGroup');
            props.helpers.updateSingleKeyValueIntoField("label","Total Price","sellingPriceOrNumberOfPacks",true);
        } else if(props.campaignType === '9') {
            props.helpers.updateSingleKeyValueIntoField("label","Payable Packs","sellingPriceOrNumberOfPacks",true);
        } else if(props.campaignType === '8') {
            props.helpers.updateSingleKeyValueIntoField("label","Total Price","sellingPriceOrNumberOfPacks",true);
        }
    }
    const oberserverMap = {
        'addNewSlab' : [['click', ()=>(props.campaignType !== '10' ? handleAddNewSlabClick() : handleBundleSlab())]],
        'multiItemCampaignSlab' : [['load', ()=>(onFormLoad())]]
      }
    const handleRemoveSlab = (index) => {
        slabs.splice(index,1);
        setSlabs( prevSlabs => [...prevSlabs])
    }

   const handleNextBtnClick = () => {
        if(!slabs || slabs.length <=0) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter aleast one slab to continue'})
        } else {
            props.handleCampaignInfoChange(slabs, props.tabDetails.currentTab.tabId, props.tabDetails.nextTab.tabId);
        }
        return false;
    }

    const handlePrevBtnClick = () => {
        props.handleCampaignInfoChange(slabs, props.tabDetails.currentTab.tabId, props.tabDetails.prevTab.tabId);
    }

    const noteItems = [
        "Total Price: The value will be rounded to 2 decimal place"
    ]

  return (
    <React.Fragment key={`${props.location.pathname}`}>
        {<div>   
        <div className='align-items-center justify-content-between overflow-auto row'  style={{height:"calc(100% - 63px)"}}>
                 <DynamicForm 
                 requestUrl={`${API_URL}get-multiitem-slab-form`} 
                 helpers = {props.helpers} observers={oberserverMap} 
                 requestMethod={'GET'}
                //  formJson = {miSlabsForm}
                 />
          { slabs && slabs.length>0 &&  <div>
               <label className='custom-fieldset mb-2'>Existing Slabs</label>
                  <div className="d-flex flex-wrap gap-3" style={{ maxHeight: '360px', overflowY: 'auto' }}>
                    { slabs.map( (slab, index) => (   
                        <Slab slab={slab}  key={index} index = {index} handleRemoveSlab={handleRemoveSlab} {...props}/>
                    )) }
               </div>
            </div> }
            </div>
            <NextPrevBtn {...props} PrevTab={props.tabDetails.prevTab.title} isNoteRequired={true} noteItems= {noteItems} nextTab={props.tabDetails.nextTab.title} handleNextBtnClick={handleNextBtnClick} handlePrevBtnClick={handlePrevBtnClick}/>
           </div> }
    </React.Fragment>
  )
}

const  Slab = (props) => {
    const {slab,handleRemoveSlab, index} = props
    const [disableRemoveButton, setDisableRemoveButton] = useState(false);
    const campaignType = props.campaignType;
    const [removeImg, setRemovImg] = useState(RemoveIconBlack);

    const changeImg = (imgColor) => {
        if(imgColor === "red"){
            setRemovImg(RemoveIconRed);
        }
        else{
            setRemovImg(RemoveIconBlack)
        }
    }
    
    useEffect(()=>{
        if(props.globalPromotionStatus === PromotionStatus.active || props?.actionPermissions?.isApprover){
            setDisableRemoveButton(true);
        }
    }, [])
  return (
    <>
           <Button disabled={disableRemoveButton} variant="light" className={`${props.className} align-items-center btn btn-light d-flex rounded-5 mb-3 me-3`} onMouseOver={() => changeImg("red")} onMouseOut={() => changeImg("black")} >

           {campaignType !='10' &&
              <span>Buy <b>{slab.numberOfPacks}</b> 
                {campaignType==='8' &&  <span> Packs For <b> <CurrencyFormatter data={slab.sellingPriceOrNumberOfPacks}/> </b> </span>}
                {campaignType==='9' && <span> Pack(s), Pay For <b>{slab.sellingPriceOrNumberOfPacks}</b> Packs at MRP</span>}
              </span> 
            }
            
            {campaignType==='10' && <span> Buy this bundle at <b> <CurrencyFormatter data={slab.sellingPriceOrNumberOfPacks}/> </b></span>}

            {!disableRemoveButton && <img  src={removeImg} onClick={(e) => handleRemoveSlab(index)} alt={"Remove"} className={"ms-2 align-top"} />}
          </Button>
    </>
  )
}
export default withFormHoc(MultiItemSlabs); 