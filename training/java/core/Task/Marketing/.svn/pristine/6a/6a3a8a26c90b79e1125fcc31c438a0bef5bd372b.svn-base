import { ALERT_TYPE, withFormHoc } from '@medplus/react-common-components/DynamicForm';
import React, { useContext, useEffect, useState } from "react";
import { Button } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { UncontrolledTooltip } from "reactstrap";
import { PromotionStatus } from '../constants/PromotionConstants';
import ResponseHandler from '../helpers/ResponseHandler';
import Validate from '../helpers/Validate';
import RemoveIcon from "../images/Remove_icon.svg";
import { SET_LOYALTIES } from '../redux/constants';
import MarketingService from '../services/MarketingService';
import { AlertContext } from './Contexts/UserContext';
import NextPrevBtn from './common/NextPrevButtons';
const LoyaltyTypes = (props) => {
    const validate = Validate();
    const setLoading = props.setLoading;
    let loyaltyInfoData = {};
    const [disableLoyalties, setDisableLoyalities] = useState(false);
    const { setAlertContent } = useContext(AlertContext);
    const [selectedLoyalityTypes, setSelectedLoyalityTypes] = useState({})
    const [searchText, setSearchString] = useState(undefined);
    const [newLoyalityTypes, setNewLoyalityTypes] = useState({});
    const dispatch = useDispatch();
  const  loyaltyTypesRedux = useSelector((state) =>  validate.isNotEmpty(state.loyaltyReducer)? state.loyaltyReducer : undefined);
  const handleNextBtnClick = () => {
        loyaltyInfoData = Object.keys(selectedLoyalityTypes);
        if (Validate().isNotEmpty(loyaltyInfoData) && loyaltyInfoData.length > 0) {
          props.handleCampaignInfoChange({"loyaltyType":loyaltyInfoData}, props.tabDetails.currentTab.tabId, props.tabDetails.nextTab.tabId);

        } else {
          setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"Select atleast one loyalty"})
        }
        return false;
    }

    const handlePrevBtnClick = () => {
        loyaltyInfoData = Object.keys(selectedLoyalityTypes);
        props.handleCampaignInfoChange({"loyaltyType":loyaltyInfoData}, props.tabDetails.currentTab.tabId, props.tabDetails.prevTab.tabId);
    }
    

    const addOrRemoveCurrentProcess = (loyaltyKey, loyaltyValue) => {
        if(selectedLoyalityTypes[loyaltyKey] === loyaltyValue) {
            delete selectedLoyalityTypes[loyaltyKey];
        } else {
            
            selectedLoyalityTypes[loyaltyKey] = loyaltyValue 
        }

        setSelectedLoyalityTypes({...selectedLoyalityTypes});
        
      };

const [isChecked, setIsChecked] = useState(false);
const handleCheckboxChange = (event) => {
  setIsChecked(event.target.checked);
  if(event.target.checked){
      setSelectedLoyalityTypes({...newLoyalityTypes});
  }
  else{
      setSelectedLoyalityTypes([])
  }
};

const getLoyaltyTypes = async() => {

  let loyaltiesObj = loyaltyTypesRedux;
  console.log('getting loyaltys from redux: ')
  if(validate.isEmpty(loyaltiesObj) || Object.keys(loyaltiesObj).length <=0){
      console.log('getting loyalties from server');
      const response = await MarketingService().getLoyaltyTypes().catch(error => {
        setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to fetch loyalties'});
    });
    ResponseHandler(setAlertContent).handleResponse(response, {}, (data) => {
      loyaltiesObj = data;
      dispatch({type: SET_LOYALTIES, payload: data});
    }, (error) => {
      setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: error})
    });
  }
  setNewLoyalityTypes(loyaltiesObj);
  console.log('getting loyaltytypes: ')
    
    if(Validate().isNotEmpty(props.loyalty)) {
        let selectedLoyalitys = {}
        props.loyalty.forEach(element => {
            selectedLoyalitys[element] = loyaltiesObj[Number(element)]
        });
        (Object.keys(loyaltiesObj).length === Object.keys(selectedLoyalitys).length) ? setIsChecked(true) : setIsChecked(false);
        setSelectedLoyalityTypes({...selectedLoyalitys})
    }
}

useEffect(()=>{
    if(Object.keys(newLoyalityTypes).length !== Object.keys(selectedLoyalityTypes).length) {
        setIsChecked(false);
    }
},[newLoyalityTypes, selectedLoyalityTypes])

useEffect( ()=>{
  setLoading(true);
    if(Validate().isEmpty(newLoyalityTypes) || Validate().isEmpty(searchText)){
        getLoyaltyTypes();
    }
    if(props?.actionPermissions?.isApprover || props?.globalPromotionStatus === PromotionStatus.active) {
        setDisableLoyalities(true);
    }
    setLoading(false);
},[props.loyalty])

const handleSerachLoyalityTypes = (e) =>{
   const inputValue = e.target.value;
   setSearchString(inputValue);
   if(Validate().isEmpty(e.target.value)){
    setNewLoyalityTypes(LoyaltyTypes);
   }
   else{
       const filteredLoyalityTypes =  LoyaltyTypes.filter((eachType)=>{
           return eachType.toUpperCase().includes(inputValue.toUpperCase())
       })
       setNewLoyalityTypes(filteredLoyalityTypes)
   }
}
    return (
      <>
        <React.Fragment key={`${props.location.pathname}`}>
     { Object.keys(newLoyalityTypes).length >0 && <>
            <div className='overflow-scroll row' style={{height:"calc(100% - 63px)"}}>
              <label className='custom-fieldset mb-2'>Select Loyalty Types</label>
              <div className="d-flex align-items-baseline mb-3">
                <div class="form-check ps-0 me-3" >
                  <input disabled={disableLoyalties}
                    class="form-check-input ms-0 me-2 pointer"
                    type="checkbox"
                    checked={isChecked}
                    name="flexRadioDefault1"
                    id={"flexRadioDefault1"}
                    onClick={(event) => {handleCheckboxChange(event)}}
                  />
                  <label class="form-check-label small" for="flexRadioDefault1" >
                    Select all the Loyalty Types
                  </label>
                </div>
              </div>
              <div className="row">
                <div className="d-grid gap-2 col">
                {newLoyalityTypes &&
                    Object.entries(newLoyalityTypes).map(
                    ([loyaltyKey, loyaltyValue]) => {
                    return (
                    <>
                        <Button disabled={disableLoyalties} variant="light" id={`partner_process_config_processtype_button_${loyaltyKey}`} key={loyaltyKey} className="rounded-5 mb-2" value={loyaltyKey} onClick={() => { addOrRemoveCurrentProcess(Number(loyaltyKey),loyaltyValue) }} >
                            <div className="d-flex justify-content-between align-items-center">
                                <span className="text-secondary font-14" value={loyaltyKey}>{loyaltyValue}</span>
                                <svg aria-hidden="true" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" value={loyaltyKey} >
                                    <path id="Icon_material-check-circle" data-name="Icon material-check-circle" d="M15,3A12,12,0,1,0,27,15,12,12,0,0,0,15,3ZM12.6,21l-6-6,1.692-1.692L12.6,17.6,21.708,8.5,23.4,10.2Z" transform="translate(-3 -3)" fill={ Validate().isNotEmpty(selectedLoyalityTypes[loyaltyKey]) ? "#11B094" : "#ced4da"} />
                                </svg>
                            </div>
                        </Button>
                    </>
                    )
                })}
                </div>
                <div className="col">
                  {Validate().isNotEmpty(selectedLoyalityTypes) &&
                     (
                      <div>
                        <p className="font-14 font-weight-bold title">
                          Current Selected Loyalty Types
                        </p>
                        <div className="d-flex flex-wrap gap-3">
                        {Object.keys(selectedLoyalityTypes).map((index, idx) => {
                            let item=selectedLoyalityTypes[index]
                            return (
                              <React.Fragment>
                                <Button disabled={disableLoyalties}
                                  variant="light"
                                  className="rounded-5"
                                >
                                  <span
                                    aria-hidden="true"
                                    className="text-muted font-14"
                                  >
                                    {item}
                                  </span>
                                  <img
                                    src={RemoveIcon}
                                    onClick={() => {
                                      addOrRemoveCurrentProcess(index, item);
                                    }}
                                    id={`current_process_config_${idx}`}
                                    alt={"Remove " + item}
                                    className={"ms-2 align-top"}
                                  />
                                  <UncontrolledTooltip
                                    placement="bottom"
                                    target={`current_process_config_${idx}`}
                                  >
                                    {"Remove " + item}
                                  </UncontrolledTooltip>
                                </Button>
                              </React.Fragment>
                            );
                          })}
                        </div>
                      </div>
                    )}
                </div>
              </div>
            </div>
            <NextPrevBtn
              {...props}
              nextTab={props.tabDetails.nextTab.title}
              handlePrevBtnClick={handlePrevBtnClick}
              handleNextBtnClick={handleNextBtnClick}
              PrevTab={props.tabDetails.prevTab.title}
            />
          </> }
        </React.Fragment>
      </>
    );
}

export default withFormHoc(LoyaltyTypes);