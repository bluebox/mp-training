import CommonDataGrid, { CellTextEditor } from "@medplus/react-common-components/DataGrid";
import DynamicForm, { ALERT_TYPE, withFormHoc } from "@medplus/react-common-components/DynamicForm";
import React, { useContext, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { UncontrolledTooltip } from "reactstrap";
import { PromotionStatus } from "../constants/PromotionConstants";
import ResponseHandler from "../helpers/ResponseHandler";
import Validate from "../helpers/Validate";
import RemoveIconRed from '../images/delete-icn-16.svg';
import { ADD_CITIES, ADD_FORM, ADD_STATES, ADD_STORES, REGIONS_FORM, SET_COUNTRIES } from "../redux/constants";
import FormJsonService from "../services/FormJsonService";
import MarketingService from "../services/MarketingService";
import CONFIG, { API_URL } from "../services/ServiceConstants";
import { AlertContext } from "./Contexts/UserContext";
import NextPrevBtn from "./common/NextPrevButtons";
const marketingService = MarketingService();

const validate = Validate();

const Regions = (props) => {

    const [selectedRegions, setSelectedRegions] = useState([]);
    const {setAlertContent} = useContext(AlertContext)
    const [regionsMap, setRegionsMap] = useState(props.regions)
    const [isFormLoaded, setFormLoaded] = useState(true);
    const setLoading = props.setLoading;
    const globalPromotionStatus = props.globalPromotionStatus;
    const countries = useSelector((state) => state.countriesReducer.countries);
    const states = useSelector((state) => state.statesReducer);
    const cities = useSelector((state) => state.citiesReducer);
    const stores = useSelector((state) => state.storesReducer);
    const [disableFields, setDisableFields] = useState(false);
    const regionsFormRedux = useSelector((state) => Validate().isNotEmpty(state.formJsonReducer) && Validate().isNotEmpty(state.formJsonReducer[REGIONS_FORM]) ? state.formJsonReducer[REGIONS_FORM] : [])
    const [regionsForm, setRegionsForm] = useState()
    const dispatch = useDispatch();
    const [dataGridStructure, setDataGridStructure] = useState(undefined);
    const [dataGridData, setDataGridData] = useState([]);
    const [regionType, setRegionType] = useState(undefined);

    const setDataGrid = async () => {
        let disableNER = false;
        if((globalPromotionStatus === PromotionStatus.active || props?.actionPermissions?.isApprover)){
            disableNER = true;
        }
        const data = await marketingService.getRegionsDataGrid({'editableFeild': !disableNER}).catch(error => {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: error});
        })

        if(props.isNotEligibleRegionsRequired) {
            setDataGridStructure(data.responseData)
        } else {
            const updatedData = data.responseData;
            const columnIndexToRemove = updatedData.columns.findIndex(column => column.columnName === "Not Eligible Regions");
            if (columnIndexToRemove !== -1) {
                updatedData.columns.splice(columnIndexToRemove, 1);
            }
            setDataGridStructure(updatedData)
        }
    }

    useEffect(()=>{
        
        if(Validate().isEmpty(dataGridStructure)) {
            setDataGrid()
        }
        updateValues();
    }, [])

    useEffect(() => {
        let gridData = []
        if(regionsMap && Object.keys(regionsMap).length > 0) {
            Object.keys(regionsMap).forEach(key => {
                let row ={};
                row['region'] = key;
                row['notEligibleRegions'] = regionsMap[key] ? regionsMap[key]['ner'] : '';
                gridData.push(row);
            });
            
        }

        setDataGridData(gridData);
    },[regionsMap]);


    const updateValues = () => {
        let regions = props.regions
        if(validate.isNotEmpty(regions)){
            let sRegions = [];
            Object.keys(regions).map(key => {
                sRegions.push(key);
            })
        }
    }

    const generateRegionType = () => {
        const regionList = [];
        regionList.push(props.helpers.createOption("Country Level", "Country Level", "Country Level", "false"));
        regionList.push(props.helpers.createOption("State Level", "State Level", "State Level", "false"));
        regionList.push(props.helpers.createOption("City Level", "City Level", "City Level", "false"));
        if (validate.isNotEmpty(props.campaignInfo.channel) && props.campaignInfo.channel.length == 1 && props.campaignInfo.channel[0] == '1') {
            regionList.push(props.helpers.createOption("Store Level", "Store Level", "Store Level", "false"));
        }
        props.helpers.addOptions("regionType", regionList, true);
        if(globalPromotionStatus === PromotionStatus.active || props?.actionPermissions?.isApprover) {
            props.helpers.updateKeyValuesToAllFields("disabled", "true", "regionsForm");
            setDisableFields(true);
        }
    }

    const onRegionTypeChange = (value) => {
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
                getStores(value);
            }
        }
        if(regionType === 'City Level') {
            props.helpers.showElement('notEligibleRegions');
        }
        (!props.isNotEligibleRegionsRequired) && hideNotEligibleRegion();

    }

    const onStoreChange = (value) => {
        if(regionType === 'Store Level') {
            props.helpers.updateValue("",'notEligibleRegions',false);
            props.helpers.hideElement('notEligibleRegions');
        }
    }

    const loadRegionsForm = async () => {
        props.setLoading(true);
        setFormLoaded(false);
        let regionsFormObj = regionsFormRedux;
        console.log('getting regionsFormObj from Redux: ')
        if(Validate().isEmpty(regionsFormObj) || regionsFormObj.length <=0) {
            console.log('getting campaignInfoForm from Server')
            const response =await FormJsonService().getRegionsForm().catch(error => {
                setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to load form'});
            });
        ResponseHandler(setAlertContent).handleResponse(response,{'responseType':'FORM'},(data) => {
            regionsFormObj = data;
            let formObj={};
            formObj[REGIONS_FORM] = data
            dispatch({type: ADD_FORM, payload : formObj})
        }, (error) => {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: error});
        });
        }
        setRegionsForm(regionsFormObj);
        setFormLoaded(true);
        props.setLoading(false);
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
                dataMap = data.responseData;
                dispatch({type: SET_COUNTRIES, payload: data.responseData})
            } else {
                setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to load countries'})
            }
        }
        let countryList=[];
        for (const [key, value] of Object.entries(dataMap)) {
            countryList.push(props.helpers.createOption(key, key, value, "false"));
        }
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
            stateList.push(props.helpers.createOption(key, `${key} [${stateCode}]`, stateCode, "false"));
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
            citiesList.push(props.helpers.createOption(key, `${key} [${cityCode}]`, cityCode, "false"));
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
        for (const [key, name] of Object.entries(storeData)) {
            storeList.push(props.helpers.createOption(key, `${name} [${key}]`, key+"$"+name , "false"));
        }
        props.helpers.addOptions("store", storeList, true);
        setLoading(false);
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
            let storeIdNameMap={};
            const data = await marketingService.getStoreNames(validStoreIds).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error})
            });
            if(validate.isNotEmpty(data) && data.statusCode === "SUCCESS" && data.responseData){
                storeIdNameMap=data.responseData;
                validStoreIds.forEach(storeId => {
                    if (!Object.keys(storeIdNameMap).includes(storeId)) {
                        invalidStoreIds.push(storeId)
                    } else {
                        processRegion(storeId, regionMap, storeIdNameMap[storeId] );
                    }
                });
            }
        }        
        if(validate.isNotEmpty(invalidStoreIds))
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"Invalid or Inactive store IDs "+ invalidStoreIds.toString() +' in list'});
        props.helpers.updateValue(invalidStoreIds.toString(),'storeIds');
        setLoading(false);
    }


    const addRegion = () => {
        let region = "";
        let storeName = "";
        const regionMap = props.helpers.validateAndCollectValuesForSubmit('regionsForm', false, false, false);
        if(validate.isEmpty(regionMap)) {
            return false;
        }

        if (validate.isNotEmpty(regionMap.country) && regionMap.country.length > 0) {
            region = region + regionMap.country;
        } else if((regionType === 'Store Level' && validate.isEmpty(regionMap.storeIds)) || (regionType != 'Store Level')){
            setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:'Please select atleast one country.'});
            return false;
        }

        if (validate.isNotEmpty(regionMap.state) && regionMap.state.length > 0) {
            region = region + regionMap.state[0];
        }
        if (validate.isNotEmpty(regionMap.city) && regionMap.city.length > 0) {
            region = region + regionMap.city[0];
        }
        if (validate.isNotEmpty(regionMap.store) && regionMap.store.length > 0) {
            let storeIdName = regionMap.store[0].split("$");
            region = storeIdName[0];
            storeName = storeIdName[1];
        }

        const storeIdsValue = regionMap.storeIds

        if(validate.isEmpty(regionMap.store) && validate.isNotEmpty(storeIdsValue) && validate.isNotEmpty(storeIdsValue.trim())) {
            const storeIdsSet = new Set(storeIdsValue.split(',').map(val=>val.trim()));
            validateStoreIds(Array.from(storeIdsSet),regionMap);
        } else {
            if(region.length == 0) {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please select atleast one region.'});
                return false;
            }
            processRegion(region,regionMap,storeName)
        }
    }

    const processRegion = (region,regionMap,storeName)=> {
        let regionAvailable = false;

        if(regionsMap && Object.keys(regionsMap).length >0) {
            Object.keys(regionsMap).forEach(key => {
                if (key.startsWith(region) || region.startsWith(key)) {
                    regionAvailable = true;
                    return;
                }
            });
        }
        if(regionAvailable) {
            setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:'Invalid region or Region already exists!'});
            return false;
        }
        if(validateNER(region, regionMap.notEligibleRegions)) {
            regionsMap[region] = {'ner': Validate().isNotEmpty(regionMap.notEligibleRegions) ? regionMap.notEligibleRegions : '', 
                                      'name':storeName };
            setRegionsMap({...regionsMap});
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

    const handleNextBtnClick = ()=> {
        if(Validate().isNotEmpty(regionsMap) && validateRegionsMap()){
            if(Validate().isNotEmpty(props.campaignInfo) && Validate().isNotEmpty(props.campaignInfo.channel) && (props.campaignInfo.channel.length > 1 || !props.campaignInfo.channel.includes('1'))) {
                let regionCodes = Object.keys(regionsMap)
                for(let index in regionCodes) {
                    if(regionCodes[index].length > 7) {
                        setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Store level regions are allowed only with POS promotions'});
                        return false;
                    }
                }
            } 
            props.handleCampaignInfoChange(regionsMap,props.tabDetails?.currentTab?.tabId, props.tabDetails?.nextTab?.tabId);

        } else {
            setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:'Invalid region.'});
            return false;
        }       
    }

  const validateRegionsMap =() => {
    let validated = true;
    Object.keys(regionsMap).map(key=>{
        if(!validateNER(key, regionsMap[key]['ner'])) {
            validated =  false;
            return;
        }
    });
    return validated;
    }

    const handlePrevBtnClick =()=>{
        if(Validate().isEmpty(regionsMap) || validateRegionsMap()) {
            props.handleCampaignInfoChange(regionsMap, props.tabDetails.currentTab.tabId, props.tabDetails.prevTab.tabId);
        }
    }

    const onStoreIdsChange = (value)=> {
        props.helpers.updateValue(value.toUpperCase(),'storeIds');
        
        if(validate.isEmpty(value)) {
            props.helpers.updateSingleKeyValueIntoField("required", true, "state");
            props.helpers.updateSingleKeyValueIntoField("required", true, "city");
            props.helpers.updateSingleKeyValueIntoField("required", true, "store");
        } else {
            props.helpers.updateSingleKeyValueIntoField("required", false, "state");
            props.helpers.updateSingleKeyValueIntoField("required", false, "city");
            props.helpers.updateSingleKeyValueIntoField("required", false, "store");
        }
    }

    const oberserverMap = {
        'regionType': [['change', (payload) => onRegionTypeChange(payload[0].target.value)]],
        'country': [['change', (payload) => onCountryChange(payload[0].target.value)]],
        'state': [['select', (payload) => onStateChange(payload[1].value[0])]],
        'city': [['select', (payload) => onCityChange(payload[1].value[0])]],
        'storeIds' : [['change', (payload) => onStoreIdsChange(payload[0].target.value)]],
        'store': [['select', (payload) => onStoreChange(payload[1].value[0])]],
        'regionsForm': [['load', generateRegionType]],
        'addRegion': [['click', addRegion]]
    }

    const handleRegionRemove = (region) => {
        delete regionsMap[region]
        let d=selectedRegions.filter((element) => element !== region);
        setSelectedRegions(d);
        setRegionsMap({...regionsMap});
    }

    const handleRemoveNER = (region) => {
        regionsMap[region]['ner'] = '';
        setRegionsMap({...regionsMap})
    }
    const handleNERChange = (region,value) => {
        regionsMap[region]['ner']= value;
        setRegionsMap({...regionsMap});
    }

    const deleteRegion = (row) => {
        handleRegionRemove(row.region);
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

    const callBackMapping = {
        "renderActionColumn" : (rowObject) => {
            return <React.Fragment>
                <button disabled={disableFields} className="btn btn-light p-0" onClick={(event)=>{event.preventDefault(); deleteRegion(rowObject.row)}}><img  src={RemoveIconRed}  alt={"Remove"} className={" "} /></button>
            </React.Fragment>
        },
        "notEligibleCellEditor" : ({row , column , onRowChange , onClose}) => {
            if(row['region'] && row['region'].length == 12) {
                return <React.Fragment></React.Fragment>
            }
            return <CellTextEditor selectedValue = {row[column.key]} onChange = {(e) => onRowChange({...row,[column.key] : e.target.value})} />
        },
        "regionColumn" : ({row , column , onRowChange , onClose}) => {
            return  <>
            <span  id={`tooltip_${row[column.key]}_${column.idx}`}>
                {row['region']}
                {   validate.isNotEmpty(regionsMap) &&
                    validate.isNotEmpty(regionsMap[row['region']]) &&
                    validate.isNotEmpty(regionsMap[row['region']]['name']) &&
                    row['region'].length === 12 &&
                <UncontrolledTooltip placement="bottom" target={`tooltip_${row[column.key]}_${column.idx}`}>
                    {regionsMap[row['region']]['name']}
                </UncontrolledTooltip>
                }
            </span>
            </>
        }
        
    }

    const handleNEREdit = (obj) => {
        let row = obj.updatedRows[obj.updatedRowIndex];
        if(handleNERBlur(row.region, row.notEligibleRegions)) {
            handleNERChange(row.region, row.notEligibleRegions);
        }
        return {}
    }

    const handleNERBlur = (region,value) => {
        return validateNER(region, value);

    }
    const noteItems = [
        "Not Eligible Regions: Comma Seperated list of Region Codes.",
        "Store Ids: Comma Seperated list of Store ID's."
    ]

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
        <>
            <React.Fragment key={`${props.location.pathname}`}>
               {<div  className='overflow-auto row' style={{height:"calc(100% - 63px)"}}>
                        <DynamicForm 
                            requestUrl={`${API_URL}get-regions-form`} 
                            helpers={props.helpers} observers={oberserverMap} 
                            requestMethod={'GET'} 
                            // formJson ={regionsForm}
                            customHtml={injectHtml}
                            />
                        {regionsMap && Object.keys(regionsMap).length > 0 &&
                            <div className="h-50">
                               <label className="custom-fieldset">Selected Regions</label>
                                <div className="card w-100 h-100">
                                {Validate().isNotEmpty(dataGridStructure) && <CommonDataGrid {...dataGridStructure} 
                                dataSet={dataGridData} callBackMap={callBackMapping} 
                                onEdit = {handleNEREdit}
                                />}
                                </div>
                            </div>
                        }
                    </div> 
                }
                <NextPrevBtn isNoteRequired={true} noteItems= {noteItems} {...props} 
                PrevTab={props.tabDetails.prevTab.title} nextTab={props.tabDetails.nextTab.title}
                handleNextBtnClick={handleNextBtnClick} handlePrevBtnClick={handlePrevBtnClick} />
            </React.Fragment>
        </>
    )
}

export default withFormHoc(Regions)