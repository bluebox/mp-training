import { ALERT_TYPE } from "@medplus/react-common-components/DynamicForm";
import React, { useContext, useEffect, useRef, useState } from "react";
import { Button } from "react-bootstrap";
import { Nav, TabPane } from "reactstrap";
import { COMP_CREATE_REF_CUST_NOTE, COMP_CREATE_REF_NOTE, COMP_EDIT_REF_CUST_NOTE, COMP_EDIT_REF_NOTE } from "../../constants/ItemsNoteConstants";
import { COMPLIMENTARY_CREATE, COMPLIMENTARY_ROLES } from "../../constants/MarketingRoles";
import { PromotionStatus, PromotionType } from "../../constants/PromotionConstants";
import { COMPLIMENTARY_TABS } from "../../constants/TabConstants";
import { COMPLIMENTARY_DWNLOAD_TEMPLATES, COMPLIMENTARY_EXCEL_DATA, STORE_EXCEL_DATA } from "../../constants/TemplateConstants";
import { COMPLIMENTARY_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import ComplimentaryService from "../../services/ComplimentaryService";
import { downLoadTemplate, downloadExcelData } from "../../services/ServiceConstants";
import CampaignInfo from "../CampaignInfo";
import { AlertContext } from "../Contexts/UserContext";
import ItemsUpload from "../ItemsUpload";
import LoyaltyTypes from "../LoyaltyTypes";
import RegionsWrapper from "../RegionsWrapper";
import { BodyComponent, HeaderComponent, Wrapper } from "../common/CommonStructure";
import CustomNavItem from "../common/CustomNavItem";
import TemplateDropDown from "../common/TemplateDropDown";
import { getTabDetails } from "../util/TabUtils";
import ComplimentaryPromotionSlab from "./ComplimentaryPromotionSlab";
import ConfirmSubmitModal from "./ConfirmSubmitModal";
import UpdatedSuccessfullyIcon from '../../images/updated-successfully-icon.svg'

let excelData = COMPLIMENTARY_EXCEL_DATA;
let COMPLIMENTARY_TEMPLATES = []
let REGION_STORE_EXCEL = []
let isToDateChanged = false;
let fileData = {};
let toastMessage;
const CreateComplimentaryPromotion = (props) => {

    const [providedExcelData , setProvidedExcelData] = useState({})
    const [complimentaryPromotion, setComplimentaryPromotion] = useState({
        campaignInfo:{},
        slabs : {},
        loyalty :[],
        regions: {},
        uploads: {}
    });
    const [trigger, setTrigger] = useState()
    const {setAlertContent, setStackedToastContent} = useContext(AlertContext)
    const [globalPromotionStatus, setGlobalPromotionStatus] = useState(PromotionStatus.inActive);
    const [activeTabId, setActiveTabId] = useState(1);
    const [showChildren, setShowChildren] = useState(true);
    const [complimentaryId, setComplimentaryId] = useState(undefined);
    const [cloneCampaignId, setCloneCampaignId] = useState(undefined);
    const [loading, setLoading] = useState(false);
    const [campaignNameValidated, setCampaignNameValidated] = useState(false);
    const [slabGroupNameValidate, setSlabGroupNameValidate] = useState(false);
    const headerRef = useRef(null);
    const [prevCampaignName, setPrevCampaignName] = useState(undefined);
    const [prevSlabGroupName, setPrevSlabGroupName] = useState(undefined);
    const [showUpdateToDate, setShowUpdateToDate] = useState(false);
    const [uploadsNote, setUploadsNote] = useState([]);
    const [helpers,setHelpers] = useState(false);
    const [isApprover, setApprover] = useState(false);
    const [channel, setChannel] = useState(undefined);
    const setIsToDateChanged = () => {
        isToDateChanged = true;
    }

    const [actionPermissions, setActionPermissions] = useState({
        isCreate : false,
        isEdit   : false,
        isClone  : false,
        isApprover : false,
        isCloser : false,
    })

    const applicableTypeRoles = {
        pharmacy : COMPLIMENTARY_CREATE,
    }
  
    const onApplicableTypeChange = (value) => {
        setComplimentaryPromotion({...complimentaryPromotion, regions:{}})
    }
    useEffect(()=>{
        const complimentaryId = (Validate().isNotEmpty(props.location.state) && Validate().isNotEmpty(props.location.state.complimentaryId)) ? props.location.state.complimentaryId : undefined;
        setLoading(true);
        if(Validate().isNotEmpty(complimentaryId)) {
            setShowChildren(true);
            let edit = false;
            let clone = false;
            if (props.path === COMPLIMENTARY_URLS.editCampaign || props.path === COMPLIMENTARY_URLS.closeCampaign || props.path === COMPLIMENTARY_URLS.approveCampaign) {
                excelData.products.url="";
                excelData.referenceCustomers.url="";
                REGION_STORE_EXCEL = [];
                STORE_EXCEL_DATA.complimentary.url = "";
                edit = true;
                actionPermissions["isEdit"] = true;
                if (props.path === COMPLIMENTARY_URLS.closeCampaign) {
                    actionPermissions["isCloser"] = true;
                }
            } else if(props.path === COMPLIMENTARY_URLS.cloneCampaign) {
                clone = true;
                actionPermissions["isClone"]= true
            } 
            getcomplimentaryPromotionByCampaignId(complimentaryId, edit, clone);
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

            setComplimentaryPromotion(
                {...complimentaryPromotion,
                    campaignInfo:{},
                    slabs : {},
                    loyalty :[],
                    regions: [],
                    uploads: {}
                }
            )
            setComplimentaryId(undefined);
            if(props.match.path !== COMPLIMENTARY_URLS.createCampaign) {
                props.history.push(COMPLIMENTARY_URLS.createCampaign)
            }
        }
        setLoading(false);
    }, [props?.match?.params, actionPermissions?.isEdit, actionPermissions?.isClone])

    useEffect(() => {

        return () =>{
            setAlertContent({});
        }

    },[])

    const getcomplimentaryPromotionByCampaignId = async (complimentaryId, isEdit=false, isClone=false) => {
        setShowUpdateToDate(false);
        isToDateChanged=false;
        fileData = {}
        const data = await ComplimentaryService().getPromotionByComplimentaryId({'complimentaryId': complimentaryId}).catch(error=>{
            setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:error})
            return false;
        });

        // setGlobalPromotionStatus(data.responseData.globalPromotionStatus)
        if(Validate().isNotEmpty(data) && data.statusCode==="SUCCESS" && Validate().isNotEmpty(data.responseData)) {
            const wrappedObj = {
                campaignInfo:{},
                slabs : {},
                loyalty :[],
                regions: [],
                uploads: {}
            };
            setComplimentaryId(data.responseData['complimentaryId']);
            wrappedObj['campaignInfo']['campaignName'] = data.responseData['name'];
            if(isEdit){
                setGlobalPromotionStatus(data.responseData['status'])
            } else if(isClone) {
                setCloneCampaignId(data.responseData['complimentaryId']);
                wrappedObj['campaignInfo']['campaignName'] = undefined;
            }
            setPrevCampaignName(wrappedObj['campaignInfo']['campaignName']);
            wrappedObj['campaignInfo']['applicableType'] = String(data.responseData['applicableType']);
            let approver = () => getApproverStatus(wrappedObj['campaignInfo']['applicableType'])
            setApprover(approver);

            const channels = data.responseData.channels;
            if (channels.length === 1) {
                setChannel(channels[0]);
            }
            wrappedObj['campaignInfo']['channel'] = channels.map(element => element.toString());
            wrappedObj['campaignInfo']['fromDate'] = data.responseData['fromDate'];
            wrappedObj['campaignInfo']['toDate'] = data.responseData['toDate'];
        
            wrappedObj['campaignInfo']['status'] = isClone ? PromotionStatus.inActive : data.responseData['status'];
            wrappedObj['campaignInfo']['allCustomers'] = (data.responseData['allCustomers']===true) ? 'Y' : 'N';
            
            wrappedObj["slabs"]["messageDisplayPercentage"] = data.responseData?.messageDisplayPercentage;
            const slab = data.responseData['complimentarySlab'];
            let invoiceAmount = slab.invoiceAmount.toString();
            if(!invoiceAmount.includes('.')) 
                invoiceAmount += '.0';
            wrappedObj["slabs"]["existingSlabGroups"] =  [slab.complimentarySlabId + "#"+ invoiceAmount];
            wrappedObj["slabs"]["complimentarySlabId"] = slab.complimentarySlabId;
            wrappedObj["slabs"]["invoiceAmount"] = invoiceAmount;
            
            wrappedObj['loyalty'] = data.responseData['loyalties'].map((element) => element.toString());
            wrappedObj['regions'] = {};
            if(Validate().isNotEmpty(data['regions']));
            data.responseData['regions'].forEach(region => {
                wrappedObj['regions'][region]= {'ner':"", name:""};
            });
            setComplimentaryPromotion(wrappedObj);
            setGlobalPromotionStatus( ...wrappedObj['campaignInfo']['status'])
        } else {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:`${data.message}`, handleCallBack: failCallBackHandler, delayTime: 3000})
        }
    }

    useEffect(() => {
        setActionPermissions({...actionPermissions, isApprover:isApprover})
    }, [isApprover])

    const failCallBackHandler = () => {
        props.history.push(COMPLIMENTARY_URLS.listCamapign)
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

    const handleSlabGrpNameChange  = () => {
        setSlabGroupNameValidate(false);
    }

    const getApproverStatus = (applicableType) => {
        let approver = false;
        if(props.path === COMPLIMENTARY_URLS.createCampaign ){
            if( !(Validate().validateRole(COMPLIMENTARY_ROLES.pharmacyCreator))) {
                approver = true;
            }
        } else {
            approver = Validate().isApproverForApplicableType(applicableType, COMPLIMENTARY_ROLES);
        } 
        return approver;
    }

    const checkCampaignNameExist = async (campaignName) => {
        const data = await ComplimentaryService().validatePromotionName({'promotionName': campaignName});
        console.log("response : {}", data);
        return data.responseData;
    }

    const checkSlabGrpNameExist = async (slabGrpName) => {
        const data = await ComplimentaryService().validateSlabGrpName({'slabName': slabGrpName});
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
                        helpers.updateErrorMessage("Promotion Name already exists","campaignName");
                        break;
                    }
                    setCampaignNameValidated(true);
                }
                setComplimentaryPromotion({...complimentaryPromotion, campaignInfo:object});
                setPrevCampaignName(object.campaignName);
                setActiveTabId(setTabId)
                break;
            case 2:
                if(Validate().isEmpty(object.existingSlabGroups) && object.slabGroupName && !slabGroupNameValidate && !(actionPermissions.isEdit)) {
                    if(prevSlabGroupName === object.slabGroupName || await checkSlabGrpNameExist(object.slabGroupName) ) {
                        setPrevSlabGroupName(object.slabGroupName);
                        helpers.updateErrorMessage("Slab Group Name Already Exists! Please Enter Another Name","slabGroupName");
                        break;
                    }
                    setSlabGroupNameValidate(true);
                }
                setComplimentaryPromotion({...complimentaryPromotion, slabs:object});
                setPrevSlabGroupName(object.slabGroupName);
                setActiveTabId(setTabId);
                break;
            case 3:
                setComplimentaryPromotion({...complimentaryPromotion, loyalty:object.loyaltyType});
                setActiveTabId(setTabId);
                break;
            case 4:
                if((actionPermissions?.isApprover || actionPermissions?.isCloser) && setTabId === COMPLIMENTARY_TABS.region.tabId + 1) {
                    fileData = {}
                    prepareFinalObject(undefined);
                }
                else {
                    setComplimentaryPromotion({...complimentaryPromotion, regions:object});
                    setActiveTabId(setTabId)
                };
                break;
            case 5:
                fileData=object;
                if(setTabId === 6) {
                    if((actionPermissions.isCreate || actionPermissions.isClone) && Validate().isEmpty(fileData.othersUpload))
                        setModalShow(true);
                    else 
                        prepareFinalObject(undefined);
                }
                else setActiveTabId(setTabId);
                break;
            case 6:
                prepareFinalObject(toDate);
                break;
            default:
                setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:"Invalid Tab"})
                break;
        }
    }

    const onCampaignTypeChange = () => {
        setComplimentaryPromotion({...complimentaryPromotion, slabs:[]})
    }

    const [modalShow, setModalShow] = useState(false);
    const handleClose = () => setModalShow(false);
    const handleConfirm = () => {
        setModalShow(false);
        prepareFinalObject(undefined);
    };
    
    const prepareFinalObject = async (toDate = undefined) => {
        setShowChildren(false);
        setLoading(true);
        let finalObj = {};
        let formData = new FormData();
        const campaignInfo = complimentaryPromotion.campaignInfo;
        if(actionPermissions.isApprover || actionPermissions.isCloser || (actionPermissions.isEdit && toDate)) {
            formData.append('complimentaryId', complimentaryId);
            formData.append('toDate', toDate ? toDate.getTime() :  campaignInfo.toDate.getTime());
        } else {
            if(Validate().isNotEmpty(complimentaryId) && (actionPermissions.isEdit)){
                finalObj['complimentaryId'] = complimentaryId;
            }
            
            if(actionPermissions.isClone && Validate().isNotEmpty(cloneCampaignId)) {
                finalObj['cloneReferenceId'] = cloneCampaignId;
            }
            finalObj['name'] = campaignInfo.campaignName;
            finalObj['status'] = PromotionStatus.inActive;
            finalObj['fromDate'] = campaignInfo.fromDate.getTime();
            finalObj['toDate'] = campaignInfo.toDate.getTime();
            finalObj['channels'] = campaignInfo.channel;
            finalObj['applicableType'] = campaignInfo.applicableType;
            finalObj['allCustomers'] = campaignInfo.allCustomers == "Y" ? true : false;
            if(Validate().isNotEmpty(complimentaryPromotion.slabs.complimentarySlabId)) {
                finalObj['complimentarySlab'] = complimentaryPromotion.slabs;
            } else {
                finalObj['complimentarySlab'] = {
                    "name" : complimentaryPromotion.slabs.slabGroupName,
                    "invoiceAmount" : complimentaryPromotion.slabs.invoiceAmount,
                }
            }
            finalObj['messageDisplayPercentage']=complimentaryPromotion?.slabs?.messageDisplayPercentage;
            finalObj['loyalties'] = complimentaryPromotion.loyalty;
            finalObj['regions'] = Object.keys(complimentaryPromotion.regions);

            console.log('finalObj to submit: ', JSON.stringify(finalObj));
            formData.append('complimentaryPromotionInfo', JSON.stringify(finalObj));

            formData.append('productUpload', Validate().isNotEmpty(fileData.complimentaryProductUpload) ? fileData.complimentaryProductUpload[0]: null);
            formData.append('productRemove', Validate().isNotEmpty(fileData.complimentaryProductRemove) ? fileData.complimentaryProductRemove[0]: null);
            formData.append('othersUpload', Validate().isNotEmpty(fileData.othersUpload) ? fileData.othersUpload[0]: null);
        }
        let response;
        if(actionPermissions?.isCreate){
            response = await ComplimentaryService().createPromotion(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions?.isEdit && !toDate && !(actionPermissions?.isApprover || actionPermissions?.isCloser)) {
            response = await ComplimentaryService().editPromotion(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(globalPromotionStatus === PromotionStatus.inActive && actionPermissions?.isEdit && toDate) {
            response = await ComplimentaryService().updateInActivePromotionToDate(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            }) 
        } else if(actionPermissions?.isClone) {
            response = await ComplimentaryService().clonePromotion(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions?.isCloser) {
            response = await ComplimentaryService().updatePromotionTodate(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions?.isApprover) {
            response = await ComplimentaryService().approvePromotion(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        }
        return ResponseHandler(setAlertContent).handleResponse(response,{'successAlert': true},(data) => {
            let campaign;
            let currLoc = props.match.path;
            if(currLoc === COMPLIMENTARY_URLS.createCampaign || (currLoc === COMPLIMENTARY_URLS.editCampaign && !toDate) || currLoc === COMPLIMENTARY_URLS.cloneCampaign || currLoc === COMPLIMENTARY_URLS.approveCampaign || currLoc === COMPLIMENTARY_URLS.closeCampaign) {
                if(typeof data === 'string'){
                    campaign = Validate().isNotEmpty(data) ? JSON.parse(data) : null;
                } else {
                    campaign = data;
                }

            } 
            if(currLoc === COMPLIMENTARY_URLS.approveCampaign || currLoc === COMPLIMENTARY_URLS.closeCampaign || currLoc === COMPLIMENTARY_URLS.editCampaign && toDate) {
                campaign = {
                    'name': complimentaryPromotion.campaignInfo.campaignName,
                    'complimentaryId' : data,    
                }
            }
            let note;
            if(currLoc === COMPLIMENTARY_URLS.createCampaign || currLoc === COMPLIMENTARY_URLS.cloneCampaign){
                note = "created"
            } else if(currLoc === COMPLIMENTARY_URLS.editCampaign || currLoc === COMPLIMENTARY_URLS.approveCampaign || currLoc === COMPLIMENTARY_URLS.closeCampaign) {
                if(currLoc === COMPLIMENTARY_URLS.approveCampaign && globalPromotionStatus === PromotionStatus.inActive) {
                    note="approved"
                } else{
                    note="updated"
                }
            }
            toastMessage = <div><p className="mb-0">Promotion  <b>{campaign.name} - {campaign.complimentaryId} {note} successfully. </b></p>
                                <p className="mb-0">The system will automatically redirect you to the {actionPermissions?.isCreate ? "Create Tab" : "Dashboard"}</p>
                            </div>;
            setStackedToastContent({toastMessage: `Promotion  ${campaign.name} [${campaign.complimentaryId}] ${note} successfully..`,
                                    onClose : () => {setStackedToastContent({})}
                                });
            setActiveTabId(COMPLIMENTARY_TABS.default.tabId);
            setShowChildren(true);
            setLoading(false);
            successCallBackHandler(campaign.complimentaryId);
        }, (error) => {
            setTimeout(() => {
                setShowChildren(true);
            }, activeTabId == COMPLIMENTARY_TABS.uploads?.tabId ? 2000 : 0);
            setActiveTabId(activeTabId);
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            setLoading(false);
        });
    }

    const updateToDate = (endDate) => {
        handleCampaignInfoChange({}, COMPLIMENTARY_TABS.uploads.tabId+1, COMPLIMENTARY_TABS.campaignInfo.tabId, false, false, false, false, endDate);
        // setActiveTabId(COMPLIMENTARY_TABS.campaignInfo.tabId);
    }

    const successCallBackHandler = (campaignId) => {
        if(actionPermissions?.isCreate) {
            setActiveTabId(1);
            setComplimentaryPromotion({
                campaignInfo:{},
                slabs : {},
                loyalty :[],
                regions: {},
                uploads: {}
            });
            props.history.push(COMPLIMENTARY_URLS.createCampaign);
        } else {
            props.history.push({
                pathname: COMPLIMENTARY_URLS.listCamapign,
                campaignId: campaignId,
                isSuccessOperation : true
            });
        }
    }

    const handleRegionsChange =(regionsMap) => {
        setComplimentaryPromotion({...complimentaryPromotion, regions:regionsMap});
    }

    useEffect(() => {
     if(activeTabId === COMPLIMENTARY_TABS.loyalty.tabId) {
        COMPLIMENTARY_TEMPLATES = [{header: 'Downloads', data: []}, {header: 'Templates',data: []}];
        pushExcelData(0, excelData.products.title, excelData.products, {'complimentaryId': props?.location?.state?.complimentaryId, 'complimentarySlabId' : complimentaryPromotion?.slabs?.complimentarySlabId});
        pushTemplate(1, COMPLIMENTARY_DWNLOAD_TEMPLATES.productsUpload.title, COMPLIMENTARY_DWNLOAD_TEMPLATES.productsUpload);
        let params = {'complimentaryId': props?.location?.state?.complimentaryId};
        pushExcelData(0, excelData.referenceCustomers.title+' / Customers', excelData.referenceCustomers, params);
        if((actionPermissions.isApprover || actionPermissions.isCloser) && (channel === 1)) {
            pushExcelData(0, STORE_EXCEL_DATA.complimentary.title, STORE_EXCEL_DATA.complimentary, {'campaignId': props?.location?.state?.complimentaryId});
        }
        const isAllCustomers = complimentaryPromotion.campaignInfo.allCustomers === 'Y';
        if(!(actionPermissions.isApprover || actionPermissions.isCloser)) {
            if(actionPermissions.isCreate || actionPermissions.isClone) {
                if(isAllCustomers) {
                    setUploadsNote(COMP_CREATE_REF_NOTE)
                    pushTemplate(1, COMPLIMENTARY_DWNLOAD_TEMPLATES.create.withOutCustomers.title, COMPLIMENTARY_DWNLOAD_TEMPLATES.create.withOutCustomers);
                } else {
                    setUploadsNote(COMP_CREATE_REF_CUST_NOTE)
                    pushTemplate(1, COMPLIMENTARY_DWNLOAD_TEMPLATES.create.withCustomers.title, COMPLIMENTARY_DWNLOAD_TEMPLATES.create.withCustomers);
                }
            } else if(actionPermissions.isEdit) {
                pushTemplate(1, COMPLIMENTARY_DWNLOAD_TEMPLATES.productsRemove.title, COMPLIMENTARY_DWNLOAD_TEMPLATES.productsRemove);
                if(isAllCustomers) {
                    setUploadsNote(COMP_EDIT_REF_NOTE)
                    pushTemplate(1, COMPLIMENTARY_DWNLOAD_TEMPLATES.edit.withOutCustomers.title, COMPLIMENTARY_DWNLOAD_TEMPLATES.edit.withOutCustomers);
                } else {
                    setUploadsNote(COMP_EDIT_REF_CUST_NOTE)
                    pushTemplate(1, COMPLIMENTARY_DWNLOAD_TEMPLATES.edit.withCustomers.title, COMPLIMENTARY_DWNLOAD_TEMPLATES.edit.withCustomers);
                }
            }
        }
    }
    }, [activeTabId])
    
    useEffect (()=> {
        if(channel === 1) {
            REGION_STORE_EXCEL = [{header: 'Downloads', data: []}, {header: 'Templates',data: []}];
            if(actionPermissions.isEdit || actionPermissions.isApprover) {
                REGION_STORE_EXCEL[0].data.push({
                    title: STORE_EXCEL_DATA.complimentary.title , 
                    onClick: () => downloadExcelData(STORE_EXCEL_DATA.complimentary, {'campaignId': props?.location?.state?.complimentaryId}, props?.location?.state?.complimentaryId, setAlertContent, ALERT_TYPE, setLoading)
                });
            }
        }
    }, [channel])

    const pushTemplate = (index, title, templateData) => {
        if(!(actionPermissions.isApprover || actionPermissions.isCloser))
            COMPLIMENTARY_TEMPLATES[index].data.push({title: title, onClick: () => downLoadTemplate(templateData)});
    }

    const pushExcelData = (index, title, excelDataItem, params) => {
        if(actionPermissions.isEdit || actionPermissions.isApprover) {
            COMPLIMENTARY_TEMPLATES[index].data.push({title: title , onClick: () => 
                downloadExcelData(excelDataItem, params, props?.location?.state?.complimentaryId, setAlertContent, ALERT_TYPE, setLoading)});
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
                   <Button variant=" " className="btn-link icon-hover" onClick={()=>{props.history.push({pathname: COMPLIMENTARY_URLS.listCamapign, campaignId: complimentaryId})}}> 
                    <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                        <g id="leftarrow_black_icon_18px" transform="translate(-48.941 -316.765)">
                            <rect id="BG_Guide" data-name="BG Guide" width="18" height="18" transform="translate(48.941 316.765)" fill="none" />
                            <path id="Path_22927" data-name="Path 22927" d="M56.566,319.236a.686.686,0,0,0-.448.178l-6.977,6.53a.7.7,0,0,0,0,.984l6.977,6.44a.709.709,0,0,0,.984-.089.7.7,0,0,0,0-.984l-5.546-5.188H66.226a.716.716,0,0,0,0-1.431H51.557l5.635-5.188a.635.635,0,0,0,.269-.536,1.275,1.275,0,0,0-.179-.537A4.04,4.04,0,0,0,56.566,319.236Z" transform="translate(0 -0.471)" fill="#080808" />
                        </g>
                    </svg></Button>
                    <div className="mx-3 " title="CampaignID" > Promotion ID - {complimentaryId}</div>
                    <div className="me-3" title="Status">  {globalPromotionStatus === PromotionStatus.active ? <span className="badge  badge-approved">Approved</span> : globalPromotionStatus === PromotionStatus.inActive ? <span className="badge  badge-pending">Created</span>  :  <span className="badge  badge-rejected">Rejected</span>} </div></div>}
                    <Nav tabs className="sub-tabs">
                        <CustomNavItem  currentTabId={COMPLIMENTARY_TABS.campaignInfo.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={COMPLIMENTARY_TABS.campaignInfo.title}/>
                        <CustomNavItem  currentTabId={COMPLIMENTARY_TABS.slabs.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={COMPLIMENTARY_TABS.slabs.title}/>
                        <CustomNavItem  currentTabId={COMPLIMENTARY_TABS.loyalty.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={COMPLIMENTARY_TABS.loyalty.title}/>
                        <CustomNavItem  currentTabId={COMPLIMENTARY_TABS.region.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={COMPLIMENTARY_TABS.region.title}/>
                        {!(actionPermissions?.isApprover || actionPermissions?.isCloser) &&
                            <CustomNavItem  currentTabId={COMPLIMENTARY_TABS.uploads.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={COMPLIMENTARY_TABS.uploads.title}/>
                        }
                        {(actionPermissions?.isApprover || actionPermissions?.isCloser) && activeTabId == COMPLIMENTARY_TABS.region.tabId &&
                            <TemplateDropDown templates={COMPLIMENTARY_TEMPLATES} {...props}/>
                        } 
                        {actionPermissions?.isEdit && !(actionPermissions?.isApprover || actionPermissions?.isCloser) && activeTabId == COMPLIMENTARY_TABS.region.tabId && Validate().isNotEmpty(REGION_STORE_EXCEL) &&
                            <TemplateDropDown templates={REGION_STORE_EXCEL} {...props}/>
                        } 
                        {!(actionPermissions?.isApprover || actionPermissions?.isCloser) && activeTabId == COMPLIMENTARY_TABS.uploads.tabId &&
                            <TemplateDropDown templates={COMPLIMENTARY_TEMPLATES} {...props}/>
                        } 
                    </Nav>
                </HeaderComponent>

                <BodyComponent loading={loading} showChildren={showChildren} className="body-height"  allRefs={{ headerRef }}>
                    {activeTabId == "0" && <TabPane  className="h-100 d-flex justify-content-center align-items-center" tabId={0}>
                    <div className="text-center">
                        <img src={UpdatedSuccessfullyIcon} alt="Updated Successfully" />
                        {toastMessage}
                    </div>
                    </TabPane>}
                    {activeTabId == "1" && <TabPane className="h-100" tabId={1}>
                        <CampaignInfo actionPermissions={actionPermissions} setHelpers={setHelpers} setLoading={setLoading} {...props} handleCampaignNameChange ={handleCampaignNameChange}  handleCampaignInfoChange={handleCampaignInfoChange}  onCampaignTypeChange={onCampaignTypeChange} trigger = {trigger} campaignInfo={complimentaryPromotion.campaignInfo}  globalPromotionStatus={globalPromotionStatus}
                         applicableTypeRoles={applicableTypeRoles} updateToDate={updateToDate} setShowUpdateToDate={setShowUpdateToDate} showUpdateToDate={showUpdateToDate} tabDetails = {getTabDetails(COMPLIMENTARY_TABS.campaignInfo, COMPLIMENTARY_TABS.slabs,'')} setIsToDateChanged={setIsToDateChanged} promotionType = {PromotionType.complimentary}/>
                    </TabPane>}
                    {activeTabId == "2" && <TabPane className="h-100" tabId={2}>
                        <ComplimentaryPromotionSlab {...props} setLoading={setLoading}  slabs={complimentaryPromotion.slabs} handleCampaignInfoChange={handleCampaignInfoChange} handleSlabGrpNameChange ={handleSlabGrpNameChange} globalPromotionStatus={ globalPromotionStatus} campaignType={complimentaryPromotion.campaignInfo.campaignType}
                        actionPermissions={actionPermissions} setHelpers={setHelpers}  tabDetails = {getTabDetails(COMPLIMENTARY_TABS.slabs, COMPLIMENTARY_TABS.loyalty, COMPLIMENTARY_TABS.campaignInfo)}/>
                    </TabPane>}
                    {activeTabId == "3" && <TabPane className="h-100" tabId={3}>
                        <LoyaltyTypes {...props} setLoading={setLoading}  loyalty={complimentaryPromotion.loyalty} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={ globalPromotionStatus}
                        actionPermissions={actionPermissions}    tabDetails = {getTabDetails(COMPLIMENTARY_TABS.loyalty, COMPLIMENTARY_TABS.region, COMPLIMENTARY_TABS.slabs)}/>
                    </TabPane>}
                    {activeTabId == "4" && <TabPane className="h-100" tabId={4}>
                        <div className='row'>                    
                        <RegionsWrapper 
                        applicableType={complimentaryPromotion?.campaignInfo?.applicableType}
                        showStoreLevel ={(Validate().isNotEmpty(complimentaryPromotion.campaignInfo?.channel) && complimentaryPromotion.campaignInfo.channel.length == 1 && complimentaryPromotion.campaignInfo.channel[0] == '1')}
                        showNextPrev={true} showDataInGrid={false} handleRegionsChange ={handleRegionsChange}  {...props} setLoading={setLoading}  regions={complimentaryPromotion.regions} channel={complimentaryPromotion.campaignInfo?.channel} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={globalPromotionStatus} 
                        actionPermissions={actionPermissions} isUpdateBtnRequired={true} isNotEligibleRegionsRequired={false} isToDateChanged={isToDateChanged} tabDetails = {getTabDetails(COMPLIMENTARY_TABS.region, COMPLIMENTARY_TABS.uploads,COMPLIMENTARY_TABS.loyalty)}/>
                        </div>
                    </TabPane>}
                    {activeTabId == "5" && <TabPane className="h-100" tabId={5}>
                        <ItemsUpload {...props} setLoading={setLoading}  uploads={complimentaryPromotion.uploads}  globalPromotionStatus={globalPromotionStatus} customerFlag={complimentaryPromotion.campaignInfo.allCustomers}
                            handleCampaignInfoChange={handleCampaignInfoChange} setProvidedExcelData={setProvidedExcelData} campaignName={complimentaryPromotion.campaignInfo?.campaignName}
                            actionPermissions={actionPermissions} uploadsNote={uploadsNote}  tabDetails = {getTabDetails(COMPLIMENTARY_TABS.uploads, '', COMPLIMENTARY_TABS.region)} promotionType = {PromotionType.complimentary}/>
                    </TabPane>}
                        {modalShow && <ConfirmSubmitModal show={modalShow} handleClose={handleClose}  handleConfirm={handleConfirm}/>}   
                </BodyComponent>
            </Wrapper>
        </React.Fragment>
    )
}

export default CreateComplimentaryPromotion;