import { ALERT_TYPE, RenderDataWrap, withFormHoc } from "@medplus/react-common-components/DynamicForm";

import React, { useContext, useEffect, useState } from 'react';
import { PromotionStatus } from "../constants/PromotionConstants";
import Validate from "../helpers/Validate";
import MarketingService from "../services/MarketingService";
import { AlertContext } from './Contexts/UserContext';
import RegionsDataGrid from './RegionsDataGrid';
import RegionsForm from './RegionsForm';
import NextPrevBtn from './common/NextPrevButtons';
const marketingService = MarketingService();
const RegionsWrapper = (props) => {

    const [regionsMap, setRegionsMap] = useState();
    const [disableFields, setDisableFields] = useState(false);
    const isCreateOrClone = (props?.actionPermissions?.isCreate || props?.actionPermissions?.isClone);
    const {setAlertContent} = useContext(AlertContext)
    useEffect(() => {
        if((Validate().isNotEmpty(props.globalPromotionStatus) && props.globalPromotionStatus === PromotionStatus.active) || props?.actionPermissions?.isApprover) {
          setDisableFields(true);
        }
        setRegionsMap({...props.regions})
    }, [props.regions])

    const handleRegionRemove = (region) => {
        delete regionsMap[region]
        setRegionsMap({...regionsMap});
        props?.handleRegionsChange({...regionsMap});
    }

    useEffect(() => {
        let existingRegions = props.regions;
        if(existingRegions && props?.showStoreLevel){
            getExistingStoreDetails(existingRegions);
        }
    },[props.regions]);
    
    const getExistingStoreDetails = async (existingRegions) => {
        let storesList = []
        let activeRegionsForClone = {}
        for (const [key, value] of Object.entries(existingRegions)) {
            if (key.length === 12 && (!value || !value.storeDetails || !value.storeDetails?.countryRegion || !value.storeDetails?.stateRegion || !value.storeDetails?.cityRegion)) {
              storesList.push(key);
            } else {
              activeRegionsForClone[key] = value;
            }
        }
        if (storesList && storesList.length > 0) {
            const storeDetails = await getStoreDetails(storesList);
            if (storeDetails && Object.keys(storeDetails).length > 0) {
                Object.keys(storeDetails).forEach(storeD => {
                    let region = existingRegions[storeD] ? existingRegions[storeD] : {};
                    region['storeDetails'] = storeDetails[storeD];
                    region['name'] = storeDetails[storeD]?.storeName;
                    existingRegions[storeD] = region;
                    activeRegionsForClone[storeD] = region;
                });
                const activeStoreRegionsLen = Object.keys(activeRegionsForClone).filter(key => key.length === 12).length;
                if(props?.actionPermissions?.isClone && storesList?.length != activeStoreRegionsLen) {
                  setAlertContent({
                    alertType: ALERT_TYPE.ERROR,
                    alertMessage: Math.abs(storesList?.length - activeStoreRegionsLen) + ' stores are removed from the configuration due to their Inactive status.'
                  });
                  props?.handleRegionsChange({...activeRegionsForClone});
                  return false;
                }
            } else if(props?.actionPermissions?.isClone) {
              storesList.forEach(key => delete existingRegions[key]);
              props.handleRegionsChange({ ...existingRegions});
              setAlertContent({
                alertType:ALERT_TYPE.ERROR, 
                alertMessage:'All stores of this configuration are in inactive status, kindly add alternate stores / region to proceed further.'
              });
              return false;
            }
        }
        setRegionsMap({ ...existingRegions });
    }

    const getStoreDetails = async (storesList) => {
      const data = await marketingService.getStoreNames({requestBody : storesList, requestParams : props?.applicableType ?  {'applicableType' : props.applicableType} : {}}).catch(error => {
		// setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error})
      });
      if(Validate().isNotEmpty(data) && data.statusCode === "SUCCESS" && data.responseData){
          return data.responseData;
      }
      return {};
        
  }

    const isMultiCampaignsAllowed = () => {
      let isMultipleCampaignsData = props.helpers.collectSpecificFieldValues('regionsForm',['isMultipleCampaigns']);
      return isMultipleCampaignsData?.isMultipleCampaigns?.[0];
    }

    const handlePrevBtnClick =()=>{
      if(isMultiCampaignsAllowed()) {
        props.handleCampaignInfoChange({isMultipleCampaigns : true} ,props.tabDetails?.currentTab?.tabId, props.tabDetails?.prevTab?.tabId);
      } else if(Validate().isEmpty(regionsMap) || validateRegionsMap()) {
            props.handleCampaignInfoChange(regionsMap, props.tabDetails.currentTab.tabId, props.tabDetails.prevTab.tabId);
      }
    }

    const handleNextBtnClick = ()=> {
        if(isMultiCampaignsAllowed()) {
          props.handleCampaignInfoChange({isMultipleCampaigns : true} ,props.tabDetails?.currentTab?.tabId, props.tabDetails?.nextTab?.tabId);
        } else if(Validate().isNotEmpty(regionsMap) && validateRegionsMap()){
          if(props.isUpdateBtnRequired && (props?.actionPermissions?.isCloser || props?.actionPermissions?.isApprover)) {
            props.handleCampaignInfoChange({}, props.tabDetails?.currentTab?.tabId, props.tabDetails?.currentTab?.tabId+1, false, false, false, true, undefined);
          } else {
            if(props?.applicableType=='5' && Validate().isNotEmpty(props.channel)  && (props.channel.length > 1 || !props.channel.includes('1'))) {
                let regionCodes = Object.keys(regionsMap)
                for(let index in regionCodes) {
                    if(regionCodes[index].length > 7) {
                        setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Store level regions are allowed only with POS promotions'});
                        return false;
                    }
                }
            } 
            props.handleCampaignInfoChange(regionsMap,props.tabDetails?.currentTab?.tabId, props.tabDetails?.nextTab?.tabId);
          }
        } else {
            setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:'Invalid region.'});
            return false;
        }   
    }
    const validateRegionsMap =() => {
        let validated = true;
        for( let key of Object.keys(regionsMap)) {
          if(!validateNER(key, regionsMap[key]['ner'])) {
            validated =  false;
          }
        }
        return validated;
        }

    const validateNER = (region,value) => {
        if (value && value !== "") {
            const ners = value.split(',').map(val=>val.trim());
            for(let i=0; i<ners.length;i++){
                const ner = ners[i];
                if (!(/^[A-Za-z]{2,7}(\d{5})?$/gi.test(ner)) || region.length >= ner.length || (ner.length > region.length && ner.toUpperCase().substring(0, region.length) != region) || ner.length > 12) {
                    if (ner.toUpperCase() == region || ner.length != 12 || (region.length == 12 && ner.length == 12) ) {
                        setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:'Invalid not eligible region '+ner+' for '+region})
                        return false;
                    }
                }
            }
        }
        return true;
    }
    const noteItems = [ ...(props.showDataInGrid ? ["Not Eligible Regions: Comma Separated list of Region Codes."] : []),
        ...(props.showStoreLevel ? ["Store Ids: Comma Separated list of Store ID's."] : []),
        ...(!props.showDataInGrid ? ["Filter Regions: Comma Separated list of search items which will be enabled if the count of selected regions is greater than 15."] : []),
        "Configured Stores with an Inactive status will be automatically removed during cloning."
    ];
                        
  return (
   <React.Fragment>
        {<div  className={props.ignoreHeight ? '' : props.showDataInGrid ? 'overflow-auto row' : 'row'} style={props.ignoreHeight ? {"height":"100%"} : {"height":"calc(100% - 63px)"}}>

        <RegionsForm {...props} validateNER={validateNER} disableFields={disableFields} setRegionsMap={setRegionsMap} regionsMap={regionsMap} isMultipleCampaigns={props?.isMultipleCampaigns}/>
        {regionsMap && Object.keys(regionsMap).length > 0 &&
                <>
                {props.showDataInGrid ? <RegionsDataGrid  disableFields={disableFields} {...props} validateNER={validateNER} handleRegionRemove={handleRegionRemove} showStoreLevel={props.showStoreLevel} isCreateOrClone={isCreateOrClone}/>
                    : <ListRegions disableFields={disableFields} regionsMap = {regionsMap} setRegionsMap={setRegionsMap} handleRegionRemove = {handleRegionRemove} showStoreLevel={props.showStoreLevel}  isCreateOrClone={isCreateOrClone}/>
                }
                </>
                        }
        </div>}
    {props.showNextPrev && props.tabDetails &&
         <NextPrevBtn isNoteRequired={true} noteItems= {noteItems} {...props} changeNxtBtn={props.isUpdateBtnRequired && (props?.actionPermissions?.isCloser || props?.actionPermissions?.isApprover)? true:false} 
         PrevTab={props.tabDetails.prevTab.title} nextTab={props.tabDetails.nextTab.title}
         handleNextBtnClick={handleNextBtnClick} handlePrevBtnClick={handlePrevBtnClick} isToDateChanged={props?.isToDateChanged}/>
    }     
    </React.Fragment>
  )
}



const ListRegions = ({regionsMap, setRegionsMap, handleRegionRemove, disableFields, showStoreLevel, isCreateOrClone}) => {
  const [filterText, setFilterText] = useState([])
  const [filteredRegionsMap, setFilteredRegionsMap] = useState([])
  const filteredRegionsCount = Object.keys(filteredRegionsMap)?.length;
  const regionsCount = Object.keys(regionsMap)?.length;
  const storesCount = showStoreLevel ? Object.keys(regionsMap).filter(region => region.length == 12).length : 0;

  useEffect(() => {
    const filteredKeys = Object.keys(regionsMap).filter(key => {
      return filterText.some(text => key.toUpperCase().includes(text.toUpperCase()));
    });
    const filteredRegions = {};
    filteredKeys.forEach(key => {
      filteredRegions[key] = regionsMap[key];
    });
    setFilteredRegionsMap(filteredRegions)
  }, [filterText])
  
  useEffect(() => {
      if(regionsCount <=15 && filterText?.length!=0) {
        setFilterText([]);
      }
  }, [filteredRegionsMap])

  const handleFilteredRegionRemove = (region) => {
    if(Object.keys(filteredRegionsMap).length > 0) {
      delete filteredRegionsMap[region]
      setFilteredRegionsMap({...filteredRegionsMap});
    }
    handleRegionRemove(region);
  }

  const filterRegion = (event) => {
    let inputRegionTxt = event.target.value?.toUpperCase();
    event.target.value = inputRegionTxt;
    setFilterText(inputRegionTxt.split(",").map(value => value.trim()).filter(key => key!="" ))
  } 

  const onClearRegionFilter = () => {
    setFilterText([]);
    const regionInput = document.getElementById('StoreIds');
    if (regionInput) {
      regionInput.value = '';
    }
  }

  const removeRegions = () => {
    if(filterText?.length > 0) {
      Object.keys(filteredRegionsMap)?.forEach(key => handleRegionRemove(key));
      setFilteredRegionsMap({});
    } else {
      Object.keys(regionsMap)?.forEach(key => handleRegionRemove(key));
      setRegionsMap({});    
    }
  }
    return (
      <div className="col pt-3">
        <div>
          <label className="custom-fieldset mb-2 col-3">
            Selected Regions - {regionsCount}
          </label>
          {regionsCount > 15 && (
            <div class="mb-3">
              <div class="d-flex align-items-center gap-3 ">
                <div class="form-floating w-50">
                  <input
                    type="text"
                    class="form-control"
                    id="StoreIds"
                    placeholder="Filter Region"
                    onChange={filterRegion}
                  />
                  <label for="StoreIds">Filter Regions</label>
                  <span
                    class="btn-close position-absolute"
                    onClick={onClearRegionFilter}
                    style={{ top: "calc(50% - 12px)", right: "2%" }}
                  ></span>
                </div>
                {!disableFields && (
                  <button
                    type="button"
                    className="btn btn-outline-brand"
                    onClick={removeRegions}
                    style={{ height: "50px" }}
                  >
                    Remove Selected -{" "}
                    {filterText?.length > 0
                      ? filteredRegionsCount
                      : regionsCount}
                  </button>
                )}
              </div>
            </div>
          )}

      {isCreateOrClone!==undefined && showStoreLevel &&
         <div className="mb-2">
          <span className="badge border border-secondary text-secondary rounded-5">
            Stores Count - {storesCount}
          </span>
          </div>
        }

          {filterText?.length > 0 && regionsCount > 15
            ? RenderDataWrap(
                filteredRegionsMap,
                disableFields,
                handleFilteredRegionRemove, "key"
              )
            : RenderDataWrap(regionsMap, disableFields, handleRegionRemove, "key")}
        </div>
      </div>
    );
}

export default withFormHoc(RegionsWrapper)