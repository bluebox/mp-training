import DynamicForm, { ALERT_TYPE, withFormHoc } from "@medplus/react-common-components/DynamicForm";
import React, { useContext, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import Validate from "../helpers/Validate";
import { ADD_CITIES, ADD_PATHLAB_STORES, ADD_STATES, ADD_STORES, SET_COUNTRIES } from "../redux/constants";
import MarketingService from "../services/MarketingService";
import CONFIG, { API_URL } from "../services/ServiceConstants";
import { AlertContext } from "./Contexts/UserContext";
const marketingService = MarketingService();
const validate = Validate();
const RegionsForm = (props) => {
    
    const {setAlertContent} = useContext(AlertContext)
    const regionsMap= props.regionsMap;
    const setRegionsMap= props.setRegionsMap;
    const setLoading = props.setLoading;
    const globalPromotionStatus = props.globalPromotionStatus;
    const storeCategoryTypes = props.storeCategoryTypes;
    const countries = useSelector((state) => state.countriesReducer.countries);
    const states = useSelector((state) => state.statesReducer);
    const cities = useSelector((state) => state.citiesReducer);
    const stores = useSelector((state) => state.storesReducer);
    const pathlabStores = useSelector((state) => state.pathlabStoresReducer);
    const dispatch = useDispatch();
    const [regionType, setRegionType] = useState(undefined);


    useEffect(()=>{
        if(props.isFromPathLabsConfig){
            generateRegionType()
            if(!props.showStoreLevel){
                setRegionsMap({})
            }
        }
    },[props.showStoreLevel])


    const generateRegionType = () => {
        props.helpers.updateValue("Country Level", "regionType", true);
        onRegionTypeChange("Country Level");
        const regionList = [];
        regionList.push(props.helpers.createOption("Country Level", "Country Level", "Country Level", "false"));
        regionList.push(props.helpers.createOption("State Level", "State Level", "State Level", "false"));
        regionList.push(props.helpers.createOption("City Level", "City Level", "City Level", "false"));
        if (props.showStoreLevel) {
            regionList.push(props.helpers.createOption("Store Level", "Store Level", "Store Level", "false"));
            if(props?.isMultipleCampaignsAllowed) {
                props.helpers.showElement("isMultipleCampaigns");
                if(props?.isMultipleCampaigns) {
                    props.helpers.updateValue("Store Level", "regionType", false);
                    props.helpers.enableElement("isMultipleCampaigns");
                    props.helpers.updateValue(["true"], "isMultipleCampaigns", false);
                    onMultiCampignsChange(true);
                    props.helpers.disableElement('country')
                    props.helpers.disableElement('addRegion')
                }
            } 
        } 
        regionList.map(region =>{ return({...region,name :"region"})});
        props.helpers.addOptions("regionType", regionList, true);
        if(props.disableFields) {
            props.helpers.updateKeyValuesToAllFields("disabled", "true", "regionsForm");
        }
    }

    const getCountries = async () => {
        setLoading(true);
        let dataMap = Validate().isNotEmpty(countries) ? { "India": countries["India"] } : {};
        console.log('countries from redux: ');
        if(Validate().isEmpty(dataMap) || Object.keys(dataMap).length <=0) {
            const data = await marketingService.getCountries(CONFIG.API.GET_COUNTRIES).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error})
            });
            if(validate.isNotEmpty(data) && data.statusCode === "SUCCESS" && data.responseData && Object.keys(data.responseData).length >0){
              console.log('getting countries from service: ')
                dataMap =  { "India": data?.responseData["India"] };
                dispatch({type: SET_COUNTRIES, payload: data.responseData})
            } else {
                setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to load countries'})
            }
        }
        let countryList=[];
        let sortedDataMapList = Object.entries(dataMap).sort((a, b) => {
            if (a[0] < b[0]) return -1;
            if (a[0] > b[0]) return 1;
            return 0;
        });
        for (const [key, value] of sortedDataMapList) {
            countryList.push(props.helpers.createOption(key, key, value, "India" == key ? "true" : "false"));
        }
        countryList.map(country =>{ return({...country,name :"country"})});

        props.helpers.addOptions("country", countryList, true);
        setLoading(false);
    }

    const getStates = async (value) => {
        setLoading(true);
        let dataMap = Validate().isNotEmpty(states) && Validate().isNotEmpty(states[value]) ? states[value] : {};
        if(Validate().isEmpty(dataMap) || Object.keys(dataMap).length <=0) {
            const data = await marketingService.getStates({ 'countrycode': value }).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error})
            });
            if(validate.isNotEmpty(data) && data.statusCode === "SUCCESS" && data.responseData){
              console.log('getting states from service: ')
                let statesObj = {};
                statesObj[value] = data.responseData;
                dataMap = data.responseData;
                dispatch({type: ADD_STATES, payload: statesObj})
            }
        }
        let stateList=[]
        for (const [key, stateCode] of Object.entries(dataMap)) {
            stateList.push(props.helpers.createOption(key, `${stateCode} - ${key}`, stateCode, "false"));
        }
        
        props.helpers.addOptions("state", stateList, true);
        setLoading(false);
    }
    const getCities = async (value) => {
        setLoading(true);
        let cityData = Validate().isNotEmpty(cities) && Validate().isNotEmpty(cities[value]) ? cities[value] : {};
        console.log('getting cities from redux: ')
        if(Validate().isEmpty(cityData) || Object.keys(cityData).length <=0) {
            const data = await marketingService.getCities({ 'statecode': value }).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error})
            });
            if(validate.isNotEmpty(data) && data.statusCode === "SUCCESS" && data.responseData){
              console.log('getting cities from service: ')
                let cityObj = {};
                cityObj[value] = data.responseData;
                cityData = data.responseData;
                dispatch({type: ADD_CITIES, payload: cityObj})
            }
        }
        let citiesList=[]
        for (const [key, cityCode] of Object.entries(cityData)) {
            citiesList.push(props.helpers.createOption(key, `${cityCode} - ${key}`, cityCode, "false"));
        }
        
        props.helpers.addOptions("city", citiesList, true);
        setLoading(false);
    }

    const getStores = async (value) => {
        setLoading(true);
        let storeData = Validate().isNotEmpty(stores) && Validate().isNotEmpty(stores[value]) ? stores[value] : {};
        if(Validate().isEmpty(storeData) || Object.keys(storeData).length <=0) {
            const data = await marketingService.getStores({ 'citycode': value }).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error})
            });
            if(validate.isNotEmpty(data) && data.statusCode === "SUCCESS" && data.responseData){
              console.log('getting stores from service: ')
                let storeObj = {};
                storeObj[value] = data.responseData;
                storeData = data.responseData;
                dispatch({type: ADD_STORES, payload: storeObj})
            }
        }
        let storeList=[]
        for (const [key, storeDetails] of Object.entries(storeData)) {
            if(validate.isNotEmpty(storeCategoryTypes)) {
                if(storeCategoryTypes.includes(storeDetails.categoryType)){
                    storeList.push(props.helpers.createOption(key, `${key} - ${storeDetails?.storeName}`, key+"$"+storeDetails?.storeName , "false"));
                }
            } else {
                storeList.push(props.helpers.createOption(key, `${key} - ${storeDetails?.storeName}`, key+"$"+storeDetails?.storeName , "false"));
            }
        }
        props.helpers.addOptions("store", storeList, true);
        setLoading(false);
    }
    const getPathlabStores = async (value) => {
        setLoading(true);
        let storeData = Validate().isNotEmpty(pathlabStores) && Validate().isNotEmpty(pathlabStores[value]) ? pathlabStores[value] : {};
        if(Validate().isEmpty(storeData) || Object.keys(storeData).length <=0) {
            const data = await marketingService.getPathlabStores({ 'citycode': value }).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error})
            });
            if(validate.isNotEmpty(data) && data.statusCode === "SUCCESS" && data.responseData){
              console.log('getting stores from service: ')
                let storeObj = {};
                storeObj[value] = data.responseData;
                storeData = data.responseData;
                dispatch({type: ADD_PATHLAB_STORES, payload: storeObj})
            }
        }
        let storeList=[]
        for (const [key, storeDetails] of Object.entries(storeData)) {
            if(validate.isNotEmpty(storeCategoryTypes)) {
                if(storeCategoryTypes.includes(storeDetails.categoryType)){
                    storeList.push(props.helpers.createOption(key, `${key} - ${storeDetails?.storeName}`, key+"$"+storeDetails?.storeName , "false"));
                }
            } else {
                storeList.push(props.helpers.createOption(key, `${key} - ${storeDetails?.storeName}`, key+"$"+storeDetails?.storeName , "false"));
            }
        }
        props.helpers.addOptions("store", storeList, true);
        setLoading(false);
    }

    const onStoreIdsChange = (value)=> {
        props.helpers.updateValue(value.toUpperCase(),'storeIds');
        
        if(validate.isEmpty(value)) {
            props.helpers.updateSingleKeyValueIntoField("required", true, "state");
            props.helpers.updateSingleKeyValueIntoField("required", true, "city");
            props.helpers.updateSingleKeyValueIntoField("required", true, "store");
        } else {
            props.helpers.updateValue([],'store',false);
            props.helpers.resetForm('store')
            props.helpers.updateSingleKeyValueIntoField("required", false, "state");
            props.helpers.updateSingleKeyValueIntoField("required", false, "city");
            props.helpers.updateSingleKeyValueIntoField("required", false, "store");
        }
    }
    const onStoreChange = (value) => {
        props.helpers.updateValue(null,'storeIds',false);
        if(regionType === 'Store Level') {
            props.helpers.updateValue("",'notEligibleRegions',false);
            props.helpers.hideElement('notEligibleRegions');
        }
    }
    const onCountryChange = (value) => {
        props.helpers.updateValue([],'state',false);
        props.helpers.resetForm('state')
        props.helpers.updateValue([],'city',false);
        props.helpers.resetForm('city')
        props.helpers.updateValue([],'store',false);
        props.helpers.resetForm('store')
        if(regionType === 'State Level' || regionType === 'City Level' || regionType === 'Store Level') {
            props.helpers.showElement('state')
            if(Validate().isNotEmpty(value)) {
                getStates(value);
            }
        }
        if(regionType === 'Country Level') {
            props.helpers.showElement('notEligibleRegions');
        }
        (!props.isNotEligibleRegionsRequired) && hideNotEligibleRegion();
    }
    const onStateChange = (value) => {
        props.helpers.updateValue([],'city',false);
        props.helpers.resetForm('city')
        props.helpers.updateValue([],'store',false);
        props.helpers.resetForm('store')
        if(regionType === 'City Level' || regionType === 'Store Level') {
            props.helpers.showElement('city')
            if(Validate().isNotEmpty(value)) {
                getCities(value);
            }
        }
        if(regionType === 'State Level') {
            props.helpers.showElement('notEligibleRegions');
        }
        (!props.isNotEligibleRegionsRequired) && hideNotEligibleRegion();
    }
    const onCityChange = (value) => {
        props.helpers.updateValue(null,'store',false);
        props.helpers.resetForm('store')
        if(regionType === 'Store Level') {
            props.helpers.showElement('store')
            if(Validate().isNotEmpty(value)) {
                if(props?.applicableType && props.applicableType==='6'){
                    getPathlabStores(value);
                }
                else {
                    getStores(value);
                }
            }
        }
        if(regionType === 'City Level') {
            props.helpers.showElement('notEligibleRegions');
        }
        (!props.isNotEligibleRegionsRequired) && hideNotEligibleRegion();

    }
    const onRegionTypeChange = (value, disableMultipleCampaigns = true) => {
        setRegionType(value);
        props.helpers.showElement('grp4');
     //    props.helpers.resetForm('regionsForm')
     //    props.helpers.updateValue(value, "regionType", false);
         props.helpers.hideElement("addRegion");
         let showAddRegionBtn = true;
         if(validate.isNotEmpty(value)) {
             if(showAddRegionBtn){
                 props.helpers.showElement("addRegion");
             }
         }
         props.helpers.enableElement('country')
         props.helpers.enableElement('addRegion')
         if(disableMultipleCampaigns) {
            props.helpers.disableElement('isMultipleCampaigns')
            props.helpers.updateValue(null, "isMultipleCampaigns", false);
         }
         if(value === 'Country Level') {
             props.helpers.updateValue([], "country", false);
             hideElement();
             props.helpers.updateValue("",'notEligibleRegions',false);
             props.helpers.hideElement('grp9');
             props.helpers.updateValue('','storeIds');
 
         } else if(value === 'State Level') {
             props.helpers.updateValue([], "country", false);
             props.helpers.updateValue(null, "state", false);
             hideElement();
             props.helpers.updateValue("",'notEligibleRegions',false);
             props.helpers.hideElement('grp9');
             props.helpers.updateSingleKeyValueIntoField("required", true, "state");
             props.helpers.updateValue('','storeIds');
         } else if(value === 'City Level') {
             props.helpers.updateValue([],'country',false);
             props.helpers.updateValue(null, "state", false);
             props.helpers.updateValue(null, "city", false);
             hideElement();
             props.helpers.updateValue("",'notEligibleRegions',false);
             props.helpers.hideElement('grp9');
             props.helpers.updateSingleKeyValueIntoField("required", true, "state");
             props.helpers.updateSingleKeyValueIntoField("required", true, "city");
             props.helpers.updateValue('','storeIds');
         } else if(value === 'Store Level') {
             props.helpers.updateValue([], "country", false);
             props.helpers.updateValue(null, "state", false);
             props.helpers.updateValue(null, "city", false);
             props.helpers.updateValue(null, "store", false);
             hideElement();
             props.helpers.updateValue("",'notEligibleRegions',false);
             props.helpers.showElement('grp9');
             props.helpers.updateValue('','storeIds');  
             props.helpers.enableElement('isMultipleCampaigns')    
         } 
         getCountries();
     }
    const hideElement = () => {
        props.helpers.hideElement('state');
        props.helpers.hideElement("city");
        props.helpers.hideElement("store");
        props.helpers.hideElement("notEligibleRegions");
    }

    const hideNotEligibleRegion = () => {
        props.helpers.updateValue("",'notEligibleRegions',false);
        props.helpers.hideElement('notEligibleRegions');
    }
    const addRegion = async () => {
        let region = "";
        let storeName = "";
        let storeDetails = {};
        const regionMap = props.helpers.validateAndCollectValuesForSubmit('regionsForm', false, false, false);
        if(validate.isEmpty(regionMap)) {
            return false;
        }

        if (validate.isNotEmpty(regionMap.country) && regionMap.country.length > 0) {
            region = region + regionMap.country;
            storeDetails['countryRegion']=region;
        } else if((regionType === 'Store Level' && validate.isEmpty(regionMap.storeIds)) || (regionType != 'Store Level')){
            setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:'Please select atleast one country.'});
            return false;
        }

        if (validate.isNotEmpty(regionMap.state) && regionMap.state.length > 0) {
            region = region + regionMap.state[0];
            storeDetails['stateRegion']=region;
        }
        if (validate.isNotEmpty(regionMap.city) && regionMap.city.length > 0) {
            region = region + regionMap.city[0];
            storeDetails['cityRegion']=region;
        }
        if (validate.isNotEmpty(regionMap.store) && regionMap.store.length > 0) {
            let storeIdName = regionMap.store[0].split("$");
            region = storeIdName[0];
            storeName = storeIdName[1];
            storeDetails['storeName'] = storeName;
            let storeDetailsMap = await getActiveStores([region]);
            if(Validate().isEmpty(storeDetailsMap)) {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"Invalid or Inactive store ID "+ region.toString() +' found in list'});
                return false;
            }
        }

        const storeIdsValue = regionMap.storeIds

        if(validate.isEmpty(regionMap.store) && validate.isNotEmpty(storeIdsValue) && validate.isNotEmpty(storeIdsValue.trim())) {
            const storeIdsSet = new Set(storeIdsValue.split(',').map(val=>val.trim()));
            validateStoreIds(Array.from(storeIdsSet),regionMap);
        } else if(regionMap?.regionType === 'Store Level' && validate.isEmpty(regionMap?.store) && validate.isEmpty(regionMap?.storeIds)) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please select atleast one store'});
        } else {
            if(region.length == 0) {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please select atleast one region.'});
                return false;
            } /* else if(region.length == 12){
                console.log('region: ',region, 'storeDetails: ', storeDetails)
                if(!checkConflictingRegion(region, storeDetails)){
                    return false;
                }
            } */
            processRegion(region,regionMap,storeDetails)
        }
    }

    const validateStoreIds = async (storeIds,regionMap) => {
        let invalidStoreIds=[];
        let validStoreIds=[];

        storeIds.forEach((storeId) => {
            if(storeId.length != 0) {
                if(storeId.length!=12) {
                    invalidStoreIds.push(storeId);
                } else {
                    validStoreIds.push(storeId);
                }
            }
        });
        if(validate.isNotEmpty(invalidStoreIds)){
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"Invalid store IDs "+ invalidStoreIds.toString() +' in list'});
            return;
        }
        invalidStoreIds=[]
        setLoading(true);
        if(validate.isNotEmpty(validStoreIds)) {
            let storeDetailsMap = await getActiveStores(validStoreIds);
            const storeIdsFromService = Object.keys(storeDetailsMap);
            validStoreIds.forEach(storeId => {
                if (!storeIdsFromService.includes(storeId)) {
                    invalidStoreIds.push(storeId)
                } else {
                    /*   if(checkConflictingRegion(storeId, storeDetailsMap[storeId])){
                    } */
                    processRegion(storeId, regionMap, storeDetailsMap[storeId]);
                }
            });
        }        
        if(validate.isNotEmpty(invalidStoreIds))
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"Invalid or Inactive store IDs "+ invalidStoreIds.toString() +' found in list'});
        props.helpers.updateValue(invalidStoreIds.toString(),'storeIds');
        setLoading(false);
    }

    const getActiveStores = async (validStoreIds) => {
        const data = await marketingService.getStoreNames({requestBody : validStoreIds, requestParams : props?.applicableType ?  {'applicableType' : props.applicableType} : {}}).catch(error => {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error})
        });
        if(validate.isNotEmpty(data) && data.statusCode === "SUCCESS" && data.responseData){
            return data.responseData;
        }
        return {};
    }

    const processRegion = (region,regionMap,storeDetails)=> {
        let regionAvailable = false;
        let conflictingRegion = '';
        
        if(regionsMap && Object.keys(regionsMap).length >0) {
            Object.keys(regionsMap).forEach(key => {
                let countryRegion ='';
                let stateRegion = '';
                let cityRegion = '';
                if(region.length === 12){
                    countryRegion = storeDetails?.countryRegion;
                    stateRegion = storeDetails?.stateRegion;
                    cityRegion = storeDetails?.cityRegion;
                } else if(key.length === 12) {
                    const strDetails = regionsMap[key]?.storeDetails;
                    countryRegion = strDetails?.countryRegion;
                    stateRegion = strDetails?.stateRegion;
                    cityRegion = strDetails?.cityRegion;
                }

                if (key.startsWith(region) || region.startsWith(key)) {
                    if(key.length===12) {
                        if(region === countryRegion || region=== stateRegion || region===cityRegion || region===key){
                            conflictingRegion =  key;
                            regionAvailable = true;
                            return;
                        }
                    } else {
                        regionAvailable = true;
                        conflictingRegion =  key;
                        return;
                    }
                    
                } else if(key.length===12) {
                    if(region === countryRegion || region=== stateRegion || region===cityRegion || region===key){
                        regionAvailable = true;
                        conflictingRegion =  key;
                        return;
                    }
                } if(region.length===12 && storeDetails) {
                    if(key === countryRegion || key=== stateRegion || key===cityRegion || region===key){
                        regionAvailable = true;
                        conflictingRegion =  key;
                        return;
                    }
                }
            });
        }
        if(regionAvailable) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:`${region} conflicting with ${conflictingRegion}`});
            return false;
        }
        if(props?.validateNER(region, regionMap.notEligibleRegions)) {
            regionsMap[region] = {'ner': Validate().isNotEmpty(regionMap.notEligibleRegions) ? modifyNER(regionMap.notEligibleRegions) : '', 
                                      'name':storeDetails?.storeName,'storeDetails':storeDetails };
            setRegionsMap({...regionsMap});
            if(props.handleRegionsChange) {
                props.handleRegionsChange(regionsMap);
            }
            props.helpers.updateValue('','notEligibleRegions', false)
            if(regionType === 'State Level') {
                props.helpers.updateValue('','state');
            } else if (regionType === 'City Level') {
                props.helpers.updateValue('','city');
            } else if (regionType === 'Store Level') {
                props.helpers.updateValue('','store');
            } else if (regionType === 'Country Level') {
                props.helpers.updateValue([], "country", false);
            }
            
        }
    }

    const onNERChange = (value) => {
        props.helpers.updateValue(value?.toUpperCase()?.trim(),'notEligibleRegions');
    }
  
    const modifyNER = (value) => {
        if (value && value !== "") {
            const ners = value.split(',').map(val=>val.trim());
            if(ners)
                return ners.join(',');
        }
        return value;
    }

    const onMultiCampignsChange = (value) => {
        if(value) {
           setRegionsMap({});
           props.setIsMultipleCampaignsInfo(true);
           //props.handleRegionsChange({});
           onRegionTypeChange('Country Level', false)
           props.helpers.disableElement('country')
           props.helpers.disableElement('addRegion')
        } else {
            onRegionTypeChange('Store Level', false)
        }
    }

    const oberserverMap = {
        'regionType': [['change', (payload) => onRegionTypeChange(payload[0].target.value)]],
        'country': [['change', (payload) => onCountryChange(payload[0].target.value)]],
        'state': [['select', (payload) => onStateChange(payload[1].value[0])]],
        'city': [['select', (payload) => onCityChange(payload[1].value[0])]],
        'storeIds' : [['change', (payload) => onStoreIdsChange(payload[0].target.value)]],
        'store': [['select', (payload) => onStoreChange(payload[1].value[0])]],
        'notEligibleRegions' : [['change', (payload) => onNERChange(payload[0].target.value)]],
        'isMultipleCampaigns' : [['change', (payload) => onMultiCampignsChange(payload[1].value[0])]],
        'regionsForm': [['load', generateRegionType]],
        'addRegion': [['click', async () => addRegion()]]
    }
    
    const customOrSeparator = () => {
        return(
            <div class="mb-3">
                <div class="position-relative py-2">
                    <hr class="border-style-dashed" />
                    <span class="separator-or-badge">OR</span>
                </div>
            </div>
        );
    }

    const injectHtml = {
        "storeIds" : [['INSERT_BEFORE', customOrSeparator]]
    }

  return (
    <React.Fragment>
      <DynamicForm
        requestUrl={`${API_URL}get-regions-form${props?.isMultipleCampaignsAllowed ? "?isMultipleCampaigns=true" : ""}`}
        helpers={props.helpers}
        observers={oberserverMap}
        requestMethod={"GET"}
        // formJson ={regionsForm}
        customHtml={injectHtml}
      />
    </React.Fragment>
  );
};

export default withFormHoc(RegionsForm);