import { ALERT_TYPE } from "@medplus/react-common-components/DynamicForm";
import React, { useContext, useEffect, useRef, useState } from "react";
import { Button } from "react-bootstrap";
import { Nav, TabPane } from "reactstrap";
import { MIC_ITEM_NOTE } from "../../constants/ItemsNoteConstants";
import { MIC_CREATE_LAB_DOCTOR, MIC_CREATE_LENS, MIC_CREATE_PHARMACY, MIC_ROLES } from "../../constants/MarketingRoles";
import { PromotionStatus, PromotionType } from "../../constants/PromotionConstants";
import { MIC_TABS } from "../../constants/TabConstants";
import { MIC_DOWNLOAD_TEMPLATES, MIC_EXCEL_DATA, STORE_EXCEL_DATA } from "../../constants/TemplateConstants";
import { MIC_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import UpdatedSuccessfullyIcon from '../../images/updated-successfully-icon.svg';
import MarketingService from "../../services/MarketingService";
import {downLoadTemplate, downloadExcelData } from "../../services/ServiceConstants";
import CampaignInfo from "../CampaignInfo";
import { AlertContext } from "../Contexts/UserContext";
import ItemsUpload from "../ItemsUpload";
import LoyaltyTypes from "../LoyaltyTypes";
import MetaInfo from "../MetaInfo";
import RegionsWrapper from "../RegionsWrapper";
import { BodyComponent, HeaderComponent, Wrapper } from "../common/CommonStructure";
import CustomNavItem from "../common/CustomNavItem";
import TemplateDropDown from "../common/TemplateDropDown";
import { getTabDetails } from "../util/TabUtils";
import MultiItemSlabs from "./MultiItemSlabs";

let excelData = MIC_EXCEL_DATA;
let MIC_TEMPLATES = []
let REGION_STORE_EXCEL = []
let isToDateChanged = false;
let toastMessage;
const CreateCampaign = (props) => {
    
    const [providedExcelData , setProvidedExcelData] = useState({})
    const [multiItemCampaign, setMultiItemCampaign] = useState({
        campaignInfo:{},
        metaInfo: {},
        slabs : [],
        loyalty :[],
        regions: {},
        uploads: {}
    });
    const [trigger, setTrigger] = useState()
    const {setAlertContent, setStackedToastContent} = useContext(AlertContext)
    const [globalPromotionStatus, setGlobalPromotionStatus] = useState(PromotionStatus.inActive);
    const [activeTabId, setActiveTabId] = useState(1);
    const [showChildren, setShowChildren] = useState(true);
    const [campaignId, setCampaignId] = useState(undefined);
    const [cloneCampaignId, setCloneCampaignId] = useState(undefined);
    const [loading, setLoading] = useState(false);
    const [showUpdateToDate, setShowUpdateToDate] = useState(false);
    const [campaignNameValidated, setCampaignNameValidated] = useState(false);
    const headerRef = useRef(null);
    const [prevCampaignName, setPrevCampaignName] = useState(undefined);
    const [helpers,setHelpers] = useState(false);
    const [isApprover, setApprover] = useState(false);
    const [applicableType, setApplicableType] = useState(undefined);
    const [channel, setChannel] = useState(undefined);
    const setIsToDateChanged = () => {
        isToDateChanged = true;
    }

    let fileData = {};

    const [actionPermissions, setActionPermissions] = useState({
        isCreate : false,
        isEdit   : false,
        isClone  : false,
        isApprover : false,
        isCloser : false,
    })

    const applicableTypeRoles = {
        pharmacy : MIC_CREATE_PHARMACY,
        lab : MIC_CREATE_LAB_DOCTOR,
        lens : MIC_CREATE_LENS
    }
  
    const onApplicableTypeChange = (value) => {
        setMultiItemCampaign({...multiItemCampaign, regions:{}})
    }
    useEffect(()=>{
        const campaignId = (Validate().isNotEmpty(props.location.state) && Validate().isNotEmpty(props.location.state.campaignId)) ? props.location.state.campaignId : undefined;
        setLoading(true);
        if(Validate().isNotEmpty(campaignId)) {
            setShowChildren(true);
            let edit = false;
            let clone = false;
            if (props.path === MIC_URLS.editCampaign || props.path === MIC_URLS.closeCampaign || props.path === MIC_URLS.approveCampaign) {
                excelData.items.url = "";
                excelData.customers.url = "";
                REGION_STORE_EXCEL = []
                STORE_EXCEL_DATA.multiItem.url = "";
                edit = true;
                actionPermissions["isEdit"] = true;
                if (props.path === MIC_URLS.closeCampaign) {
                    actionPermissions["isCloser"] = true;
                }
            } else if(props.path === MIC_URLS.cloneCampaign) {
                clone = true;
                actionPermissions["isClone"]= true
            } 
            getMultiItemCampaignByCampaignId(campaignId, edit, clone);
        } else {
            setShowChildren(true);
            setActiveTabId(1);
            setGlobalPromotionStatus(PromotionStatus.inActive)
            actionPermissions["isCreate"]=true
            actionPermissions["isEdit"]=false
            actionPermissions["isClone"]=false
            actionPermissions["isApprover"]=false
            actionPermissions["isCloser"]=false
            setShowUpdateToDate(false);

            setMultiItemCampaign(
                {...multiItemCampaign,
                    campaignInfo:{},
                    metaInfo: {},
                    slabs : [],
                    loyalty :[],
                    regions: [],
                    uploads: {}
                }
            )
            setCampaignId(undefined);
            if(props.match.path !== MIC_URLS.createCampaign) {
                props.history.push(MIC_URLS.createCampaign)
            }
        }
        setLoading(false);
    }, [props?.match?.params, actionPermissions?.isEdit, actionPermissions?.isClone])

    useEffect(() => {

        return () =>{
            setAlertContent({});
        }

    },[])

    const getMultiItemCampaignByCampaignId = async (campaignId, isEdit=false, isClone=false) => {
        setShowUpdateToDate(false);
        isToDateChanged=false;
        const data = await MarketingService().getMultiItemCampaignByCampaignId({'campaignId': campaignId}).catch(error=>{
            setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:error})
            return false;
        });
        // setGlobalPromotionStatus(data.responseData.globalPromotionStatus)
        if(Validate().isNotEmpty(data) && data.statusCode==="SUCCESS" && Validate().isNotEmpty(data.responseData)) {
            const wrappedObj = {
                campaignInfo:{},
                metaInfo: {
                    'stackedImages' : {}
                },
                slabs : [],
                loyalty :[],
                regions: [],
                uploads: {}
            };
            setCampaignId(data.responseData['campaignId']);
            wrappedObj['campaignInfo']['campaignName'] = data.responseData['campaignName'];
            if(isEdit){
                setGlobalPromotionStatus(data.responseData['status'])
            } else if(isClone) {
                setCloneCampaignId(data.responseData['campaignId']);
                wrappedObj['campaignInfo']['campaignName'] = undefined;
            }
           
            setPrevCampaignName(wrappedObj['campaignInfo']['campaignName']);
            setApplicableType(data.responseData['applicableType']);
            wrappedObj['campaignInfo']['applicableType'] = String(data.responseData['applicableType']);
            let approver = () => getApproverStatus(wrappedObj['campaignInfo']['applicableType'])
            setApprover(approver);

            wrappedObj['campaignInfo']['campaignType'] = data.responseData['campaignType'];
            const channels = data.responseData.channels;
            if (channels.length === 1) {
                setChannel(channels[0]);
            }
            wrappedObj['campaignInfo']['channel'] = channels.map(element => element.toString());
            wrappedObj['campaignInfo']['fromDate'] = data.responseData['fromDate'];
            wrappedObj['campaignInfo']['toDate'] = data.responseData['toDate'];
        
            wrappedObj['campaignInfo']['status'] = isClone ? PromotionStatus.inActive : data.responseData['status'];
            wrappedObj['campaignInfo']['allCustomers'] = (data.responseData['allCustomers']===true) ? 'Y' : 'N';
            wrappedObj['campaignInfo']['priceConsideredForSlab'] = data.responseData['priceConsideredForSlab'];
            if(!data.responseData.userMetaData) {
                setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to load data.'})
                return false;
            } else {
                wrappedObj['metaInfo']['longDescription'] = data.responseData.userMetaData['longDescription'];
                // wrappedObj['metaInfo']['promotionVisible'] = data.responseData.userMetaData['promotionVisible'] ? ['promotion'] : null;
                wrappedObj['metaInfo']['claimable'] = data.responseData.userMetaData['claimable'] ? ['claimable'] : null;
                if( Validate().isNotEmpty(data.responseData.userMetaData['imageServerName']) && Validate().isNotEmpty(data.responseData.userMetaData['imagePath'])) {
                    let url = data.responseData.userMetaData['imageServerName'];
                    let path =  data.responseData.userMetaData['imagePath'];
                    wrappedObj['metaInfo']['imageServerName'] = url;
                    wrappedObj['metaInfo']['imagePath'] = path;
                    wrappedObj['metaInfo']['stackedImages']['imageServerUrl'] = url;
                    wrappedObj['metaInfo']['stackedImages']['imagePath'] = `${url}/${path}`;
                    wrappedObj['metaInfo']['stackedImages']['thumbnailPath'] = `${url}/${path}`;
                }
            }
            wrappedObj['slabs'] = data.responseData['multiItemSlabs'];
            wrappedObj['loyalty'] = data.responseData['loyalty'].map((element) => element.toString());

            wrappedObj['regions'] = data.responseData['regions'];
            if(Validate().isNotEmpty(data.responseData['regions']));
            Object.keys(data.responseData['regions']).map(key=>{
                wrappedObj['regions'][key]= {'ner':data.responseData['regions'][key].join(','), name:""};
            });
            console.log("wrapped data : ", wrappedObj);
            setMultiItemCampaign(wrappedObj);
            setGlobalPromotionStatus( ...wrappedObj['campaignInfo']['status'])
        } else {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:`${data.message}`, handleCallBack: failCallBackHandler, delayTime: 3000})
        }
        
    }

    useEffect(() => {
        setActionPermissions({...actionPermissions, isApprover:isApprover})
    }, [isApprover])

    const failCallBackHandler = () => {
        props.history.push(MIC_URLS.listCamapign)
    }

    const getClassNameForTab = (tabId) => {
        if(tabId < activeTabId) {
            return "completed";
        }
        return tabId ===activeTabId ? "active" : "unCompleted"
    }


    const handleCampaignNameChange  = () => {
        setCampaignNameValidated(false);
    }

    const getApproverStatus = (applicableType) => {
        let approver = false;
        if(props.path === MIC_URLS.createCampaign ){
            if( !(Validate().validateRole(MIC_ROLES.pharmacyCreator) ||  Validate().validateRole(MIC_ROLES.lensCreator) ||  Validate().validateRole(MIC_ROLES.labsCreator))) {
                approver = true;
            }
        } else {
            approver = Validate().isApproverForApplicableType(applicableType, MIC_ROLES);
        } 
        return approver;
    }

    const checkCampaignNameExist = async (campaignName) => {
        const data = await MarketingService().validateCampaignName({'campaignName': campaignName});
        return data.responseData;
    }
    const handleCampaignInfoChange = async (object,tabId, setTabId, isEdit=false, isClone=false, isCreate=false, toApprove=false, toDate=undefined) => {
        switch(tabId) {
            case 0:
                break;
            case 1:
                if(!campaignNameValidated && !(actionPermissions?.isEdit)) {
                    if(prevCampaignName === object.campaignName || await checkCampaignNameExist(object.campaignName) ) {
                        setPrevCampaignName(object.campaignName);
                        helpers.updateErrorMessage("CampaignName already exists","campaignName");
                        break;
                    }
                    setCampaignNameValidated(true);
                    
                }
                setMultiItemCampaign({...multiItemCampaign, campaignInfo:object});
                setPrevCampaignName(object.campaignName);
                setActiveTabId(setTabId)
                break;
            case 2:
                setMultiItemCampaign({...multiItemCampaign, metaInfo:object});
                setActiveTabId(setTabId);
                break;
            case 3:
                setMultiItemCampaign({...multiItemCampaign, slabs:object});
                setActiveTabId(setTabId);
                break;
            case 4:
                setMultiItemCampaign({...multiItemCampaign, loyalty:object.loyaltyType});
                setActiveTabId(setTabId);
                break;
            case 5:
                if((actionPermissions?.isApprover || actionPermissions?.isCloser) && setTabId === MIC_TABS.region.tabId + 1) {
                    fileData = {}
                    prepareFinalObject(undefined,isEdit, isClone, isCreate, toApprove);
                }
                else {
                    setMultiItemCampaign({...multiItemCampaign, regions:object});
                    setActiveTabId(setTabId)
                };
                break;
            case 6:
                fileData=object;
                if(setTabId === 7)
                    prepareFinalObject(undefined,isEdit, isClone, isCreate, toApprove);
                else setActiveTabId(setTabId);
                break;
            case 7:
                prepareFinalObject(toDate, isEdit, isClone, isCreate, toApprove);
                // setActiveTabId(setTabId);
                break;
            default:
                setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:"Invalid Tab"})
                break;
        }
    }

    const onCampaignTypeChange = () => {
        setMultiItemCampaign({...multiItemCampaign, slabs:[]})
    }
    
    const prepareFinalObject = async (toDate = undefined, isEdit=false, isClone=false, isCreate=false, toApprove=false) => {
        setShowChildren(false);
        setLoading(true);
        let finalObj = {};
        let formData = new FormData();
        const campaignInfo = multiItemCampaign.campaignInfo;
        if(actionPermissions.isApprover || actionPermissions.isCloser || (actionPermissions.isEdit && toDate)) {
            formData.append('campaignId', campaignId);
            formData.append('toDate', toDate ? toDate.getTime() :  campaignInfo.toDate.getTime());
            if((actionPermissions.isApprover || actionPermissions.isCloser) && campaignInfo.channel.length >0 && campaignInfo.channel.indexOf("1")!=-1){
                formData.append('channel', 1);
            }

        } else {
            if(Validate().isNotEmpty(campaignId) && (isEdit)){
                finalObj['campaignId'] = campaignId;
            }
            
            if(isClone && Validate().isNotEmpty(cloneCampaignId)) {
                finalObj['cloneReferenceId'] = cloneCampaignId;
            }
            finalObj['campaignName'] = campaignInfo.campaignName;
            finalObj['status'] = PromotionStatus.inActive;
            finalObj['fromDate'] = campaignInfo.fromDate.getTime();
            finalObj['toDate'] = campaignInfo.toDate.getTime();
            finalObj['channels'] = campaignInfo.channel;
            finalObj['campaignType'] = campaignInfo.campaignType;
            finalObj['applicableType'] = campaignInfo.applicableType;
            finalObj['allCustomers'] = campaignInfo.allCustomers == "Y" ? true : false;
            finalObj['priceConsideredForSlab'] =  Validate().isEmpty(campaignInfo.priceConsideredForSlab) ? 'S' : campaignInfo.priceConsideredForSlab;        
            const metaInfo = multiItemCampaign.metaInfo;
            let userMetaData =  {
                longDescription: metaInfo.longDescription,
                imagePath : metaInfo.imagePath,
                imageServerName: metaInfo.imageServerUrl,
                // promotionVisible : Validate().isNotEmpty(metaInfo.promotionVisible) ? true : false,
                claimable: Validate().isNotEmpty(metaInfo.claimable) ? true : false
            }
            finalObj['userMetaData'] = userMetaData;
            finalObj['multiItemSlabs'] = multiItemCampaign.slabs;

            finalObj['loyalty'] = multiItemCampaign.loyalty;

            const regions ={};
            let regionsMap=multiItemCampaign.regions;
            Object.keys(regionsMap).map(region => {
                regions[region] = regionsMap[region]['ner'].split(',');
            });
            finalObj['regions'] = regions;

            console.log('finalObj to submit: ', JSON.stringify(finalObj));
            formData.append('campaignInfo', JSON.stringify(finalObj));

            formData.append('productUpload', Validate().isNotEmpty(fileData.productUpload) ? fileData.productUpload[0]: null);
            formData.append('productRemove', Validate().isNotEmpty(fileData.productRemove) ? fileData.productRemove[0]: null);
            formData.append('customerUpload', Validate().isNotEmpty(fileData.customerUpload) ? fileData.customerUpload[0]: null);
            formData.append('customerRemove', Validate().isNotEmpty(fileData.customerRemove) ? fileData.customerRemove[0]: null);
        }
        let response;
        if(actionPermissions?.isCreate){
            response = await MarketingService().createCampaign(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions?.isEdit && !toDate && !(actionPermissions?.isApprover || actionPermissions?.isCloser)) {
            response = await MarketingService().editCampaign(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(globalPromotionStatus === PromotionStatus.inActive && actionPermissions?.isEdit && toDate) {
            response = await MarketingService().updateInActiveTodate(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            }) 
        } else if(actionPermissions?.isClone) {
            response = await MarketingService().cloneCampaign(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions?.isCloser) {
            response = await MarketingService().updateTodate(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions?.isApprover) {
            response = await MarketingService().approveCampaign(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        }
        return ResponseHandler(setAlertContent).handleResponse(response,{'successAlert': true},(data) => {
            let campaign;
            let currLoc = props.match.path;
            if(currLoc === MIC_URLS.createCampaign || (currLoc === MIC_URLS.editCampaign && !toDate) || currLoc === MIC_URLS.cloneCampaign || currLoc === MIC_URLS.approveCampaign || currLoc === MIC_URLS.closeCampaign) {
                if(typeof data === 'string'){
                    campaign = Validate().isNotEmpty(data) ? JSON.parse(data) : null;
                } else {
                    campaign = data;
                }

            } 
            if(toApprove || currLoc === MIC_URLS.closeCampaign || currLoc === MIC_URLS.editCampaign && toDate) {
                campaign = {
                    'campaignName': multiItemCampaign.campaignInfo.campaignName,
                    'campaignId' : data,    
                }
            }
            let note;
            if(currLoc === MIC_URLS.createCampaign || currLoc === MIC_URLS.cloneCampaign){
                note = "created"
            } else if(currLoc === MIC_URLS.editCampaign || currLoc === MIC_URLS.approveCampaign || currLoc === MIC_URLS.closeCampaign) {
                if(toApprove && globalPromotionStatus === PromotionStatus.inActive) {
                    note="approved"
                } else{
                    note="updated"
                }
            }
            setShowChildren(true);
            setLoading(false);
            toastMessage = <div><p className="mb-0">Campaign  <b>{campaign.campaignName} - {campaign.campaignId} {note} successfully. </b></p>
                                <p className="mb-0">The system will automatically redirect you to the {actionPermissions?.isCreate ? "Create Tab" : "Dashboard"}</p>
                            </div>;
            setStackedToastContent({toastMessage: `Campaign  ${campaign.campaignName} [${campaign.campaignId}] ${note} successfully...`,
                                    onClose : () => {setStackedToastContent({})}
                                });
            setActiveTabId(MIC_TABS.default.tabId);
            successCallBackHandler(campaign.campaignId);
        }, (error) => {
            setTimeout(() => {
                setShowChildren(true);
            }, activeTabId == MIC_TABS.uploads?.tabId ? 2000 : 0);
            setActiveTabId(activeTabId);
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            setLoading(false);
        });
    }

    const updateToDate = (endDate) => {
        handleCampaignInfoChange({}, MIC_TABS.uploads.tabId+1, MIC_TABS.campaignInfo.tabId, false, false, false, false, endDate);
        // setActiveTabId(MIC_TABS.campaignInfo.tabId);
    }

    const successCallBackHandler = (campaignId) => {
        if(actionPermissions?.isCreate) {
            setActiveTabId(1);
            setMultiItemCampaign({
                campaignInfo:{},
                metaInfo: {},
                slabs : [],
                loyalty :[],
                regions: {},
                uploads: {}
            });
            props.history.push(MIC_URLS.createCampaign);
        } else {
            props.history.push({
                pathname: MIC_URLS.listCamapign,
                campaignId: campaignId,
                isSuccessOperation : true
            });
        }
    }

    const handleRegionsChange =(regionsMap) => {
        setMultiItemCampaign({...multiItemCampaign, regions:regionsMap});
    }

    useEffect(() => {
     if(activeTabId === MIC_TABS.loyalty.tabId) {
        MIC_TEMPLATES = [{header: 'Downloads', data: []}, {header: 'Templates',data: []}];

        pushExcelData(0, excelData.items.title, excelData.items);
        pushTemplate(1, MIC_DOWNLOAD_TEMPLATES.items.title, MIC_DOWNLOAD_TEMPLATES.items);
        if((actionPermissions.isApprover || actionPermissions.isCloser) && (channel === 1 || applicableType === 6)) {
            pushExcelData(0, STORE_EXCEL_DATA.multiItem.title, STORE_EXCEL_DATA.multiItem);
        }
        if(multiItemCampaign.campaignInfo.allCustomers != 'Y') {
            pushExcelData(0, excelData.customers.title, excelData.customers);
            pushTemplate(1,  MIC_DOWNLOAD_TEMPLATES.customers.title, MIC_DOWNLOAD_TEMPLATES.customers)
        }
     }  
    }, [activeTabId])

    useEffect (()=> {
        if(channel === 1 || applicableType === 6) {
            REGION_STORE_EXCEL = [{header: 'Downloads', data: []}, {header: 'Templates',data: []}];
            if(actionPermissions.isEdit || actionPermissions.isApprover) {
                REGION_STORE_EXCEL[0].data.push({
                    title: STORE_EXCEL_DATA.multiItem.title , 
                    onClick: () => downloadExcelData(STORE_EXCEL_DATA.multiItem, {'campaignId': props?.location?.state?.campaignId}, props?.location?.state?.campaignId, setAlertContent, ALERT_TYPE, setLoading)
                });
            }
        }
    }, [channel, applicableType])

    const pushTemplate = (index, title, templateData) => {
        if(!(actionPermissions.isApprover || actionPermissions.isCloser))
            MIC_TEMPLATES[index].data.push({title: title, onClick: () => downLoadTemplate(templateData)});
    }

    const pushExcelData = (index, title, excelDataItem) => {
        if(actionPermissions.isEdit || actionPermissions.isApprover) {
            MIC_TEMPLATES[index].data.push({title: title , onClick: () => 
                downloadExcelData(excelDataItem,{'campaignId': props?.location?.state?.campaignId, 'applicableType': multiItemCampaign?.campaignInfo?.applicableType}, props?.location?.state?.campaignId, setAlertContent, ALERT_TYPE, setLoading)});
        }
    }

    useEffect(()=>{
        setActiveTabId(1)
    },[props.location.pathname])

    
    return(
        <React.Fragment key={`${props.location.pathname}`}>
            <Wrapper>
                <HeaderComponent className="custom-tabs-forms p-0" ref={headerRef}>
                {(actionPermissions?.isEdit) && <div className="d-flex border-bottom p-12 align-items-center">
                   <Button variant=" " className="btn-link icon-hover" onClick={()=>{props.history.push({pathname: MIC_URLS.listCamapign, campaignId: campaignId})}}> 
                    <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                        <g id="leftarrow_black_icon_18px" transform="translate(-48.941 -316.765)">
                            <rect id="BG_Guide" data-name="BG Guide" width="18" height="18" transform="translate(48.941 316.765)" fill="none" />
                            <path id="Path_22927" data-name="Path 22927" d="M56.566,319.236a.686.686,0,0,0-.448.178l-6.977,6.53a.7.7,0,0,0,0,.984l6.977,6.44a.709.709,0,0,0,.984-.089.7.7,0,0,0,0-.984l-5.546-5.188H66.226a.716.716,0,0,0,0-1.431H51.557l5.635-5.188a.635.635,0,0,0,.269-.536,1.275,1.275,0,0,0-.179-.537A4.04,4.04,0,0,0,56.566,319.236Z" transform="translate(0 -0.471)" fill="#080808" />
                        </g>
                    </svg></Button>
                    <div className="mx-3 " title="CampaignID" > Campaign ID - {campaignId}</div>
                    <div className="me-3" title="Status">  {globalPromotionStatus === PromotionStatus.active ? <span className="badge  badge-approved">Approved</span> : globalPromotionStatus === PromotionStatus.inActive ? <span className="badge  badge-pending">Created</span>  :  <span className="badge  badge-rejected">Rejected</span>} </div></div>}
                    <Nav tabs className="sub-tabs">
                        <CustomNavItem  currentTabId={MIC_TABS.campaignInfo.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={MIC_TABS.campaignInfo.title}/>
                        <CustomNavItem  currentTabId={MIC_TABS.metaInfo.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={MIC_TABS.metaInfo.title}/>
                        <CustomNavItem  currentTabId={MIC_TABS.slabs.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={MIC_TABS.slabs.title}/>
                        <CustomNavItem  currentTabId={MIC_TABS.loyalty.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={MIC_TABS.loyalty.title}/>
                        <CustomNavItem  currentTabId={MIC_TABS.region.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={MIC_TABS.region.title}/>
                        {!(actionPermissions?.isApprover || actionPermissions?.isCloser) &&
                            <CustomNavItem  currentTabId={MIC_TABS.uploads.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={MIC_TABS.uploads.title}/>
                        }
                        {(actionPermissions?.isApprover || actionPermissions?.isCloser) && activeTabId == MIC_TABS.region.tabId &&
                            <TemplateDropDown templates={MIC_TEMPLATES} {...props}/>
                        } 
                        {actionPermissions?.isEdit && !(actionPermissions?.isApprover || actionPermissions?.isCloser) && activeTabId == MIC_TABS.region.tabId && Validate().isNotEmpty(REGION_STORE_EXCEL) &&
                            <TemplateDropDown templates={REGION_STORE_EXCEL} {...props}/>
                        } 
                        {!(actionPermissions?.isApprover || actionPermissions?.isCloser) && activeTabId == MIC_TABS.uploads.tabId &&
                            <TemplateDropDown templates={MIC_TEMPLATES} {...props}/>
                        } 
                    </Nav>
                </HeaderComponent>

                <BodyComponent  className="body-height" loading={loading} showChildren={showChildren} allRefs={{ headerRef }}>
                    {activeTabId == "0" && <TabPane  className="h-100 d-flex justify-content-center align-items-center" tabId={0}>
                        <div className="text-center">
                        <img src={UpdatedSuccessfullyIcon} alt="Updated Successfully" />
                            {toastMessage}
                        </div>  
                    </TabPane>}
                    {activeTabId == "1" && <TabPane className="h-100" tabId={1}>
                        <CampaignInfo setHelpers={setHelpers} setLoading={setLoading} handleCampaignNameChange ={handleCampaignNameChange}  onCampaignTypeChange={onCampaignTypeChange} {...props} trigger = {trigger} campaignInfo={multiItemCampaign.campaignInfo} slabs={multiItemCampaign.slabs} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={globalPromotionStatus}
                         onApplicableTypeChange={onApplicableTypeChange} showUpdateToDate={showUpdateToDate} setShowUpdateToDate={setShowUpdateToDate} applicableTypeRoles={applicableTypeRoles} updateToDate={updateToDate} actionPermissions={actionPermissions} setIsToDateChanged={setIsToDateChanged} tabDetails = {getTabDetails(MIC_TABS.campaignInfo, MIC_TABS.metaInfo,'')} promotionType = {PromotionType.multiItem}/>
                    </TabPane>}
                    {activeTabId == "2" && <TabPane className="h-100" tabId={2}>
                        <MetaInfo {...props} setLoading={setLoading}   metaInfo={multiItemCampaign.metaInfo} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={ globalPromotionStatus}  
                        actionPermissions={actionPermissions}    tabDetails = {getTabDetails(MIC_TABS.metaInfo, MIC_TABS.slabs, MIC_TABS.campaignInfo)}/>
                    </TabPane>}
                    {activeTabId == "3" && <TabPane className="h-100" tabId={3}>
                        <MultiItemSlabs {...props} setLoading={setLoading}  slabs={multiItemCampaign.slabs} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={ globalPromotionStatus} campaignType={multiItemCampaign.campaignInfo.campaignType}
                        actionPermissions={actionPermissions}    tabDetails = {getTabDetails(MIC_TABS.slabs, MIC_TABS.loyalty, MIC_TABS.metaInfo)}/>
                    </TabPane>}
                    {activeTabId == "4" && <TabPane className="h-100" tabId={4}>
                        <LoyaltyTypes {...props} setLoading={setLoading}  loyalty={multiItemCampaign.loyalty} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={ globalPromotionStatus}
                        actionPermissions={actionPermissions}    tabDetails = {getTabDetails(MIC_TABS.loyalty, MIC_TABS.region, MIC_TABS.slabs)}/>
                    </TabPane>}
                    {activeTabId == "5" && <TabPane className="h-100" tabId={5}>
                        <RegionsWrapper 
                         showStoreLevel ={(Validate().isNotEmpty(multiItemCampaign.campaignInfo?.channel) && multiItemCampaign.campaignInfo.channel.length == 1 && multiItemCampaign.campaignInfo.channel[0] == '1') || (multiItemCampaign.campaignInfo?.applicableType && multiItemCampaign.campaignInfo?.applicableType==='6')}
                        applicableType = {multiItemCampaign?.campaignInfo?.applicableType}
                         showNextPrev={true} showDataInGrid={true} handleRegionsChange ={handleRegionsChange}  {...props} setLoading={setLoading}  regions={multiItemCampaign.regions} channel={multiItemCampaign.campaignInfo?.channel} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={globalPromotionStatus} 
                        actionPermissions={actionPermissions} isUpdateBtnRequired={true} isNotEligibleRegionsRequired={true} isToDateChanged={isToDateChanged} tabDetails = {getTabDetails(MIC_TABS.region, MIC_TABS.uploads, MIC_TABS.loyalty)}/>
                    </TabPane>}
                    {activeTabId == "6" && <TabPane className="h-100" tabId={6}>
                        <ItemsUpload {...props} setLoading={setLoading}  uploads={multiItemCampaign.uploads}  globalPromotionStatus={globalPromotionStatus} customerFlag={multiItemCampaign.campaignInfo.allCustomers}
                            handleCampaignInfoChange={handleCampaignInfoChange} setProvidedExcelData={setProvidedExcelData} campaignName={multiItemCampaign.campaignInfo?.campaignName}
                            actionPermissions={actionPermissions} uploadsNote={MIC_ITEM_NOTE}  tabDetails = {getTabDetails(MIC_TABS.uploads, '', MIC_TABS.region)}/>
                    </TabPane>}
                </BodyComponent>
                {/* <FooterComponent ref={footerRef} className="footer px-3 py-2 d-flex flex-row-reverse justify-content-between">
                    <NextPrevBtn {...props} hidePrevBtn={activeTabId == 1 ? true : false } handleNextBtnClick={handleNextBtnClick} nextTab={headerTabs[activeTabId]}/>
                    </FooterComponent> */}
            </Wrapper>
        </React.Fragment>
    )
}

export default CreateCampaign;