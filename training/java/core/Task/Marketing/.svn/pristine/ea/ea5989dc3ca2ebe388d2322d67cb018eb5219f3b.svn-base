import { ALERT_TYPE } from "@medplus/react-common-components/DynamicForm";
import React, { useContext, useEffect, useRef, useState } from "react";
import { Button } from "react-bootstrap";
import { Nav, TabPane } from "reactstrap";
import { RP_CREATE, RP_ROLES } from "../../constants/MarketingRoles";
import { PromotionStatus, PromotionType } from "../../constants/PromotionConstants";
import { RP_TABS } from "../../constants/TabConstants";
import { RP_DOWNLOAD_TEMPLATES, RP_EXCEL_DATA, STORE_EXCEL_DATA } from "../../constants/TemplateConstants";
import { RP_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import UpdatedSuccessfullyIcon from '../../images/updated-successfully-icon.svg';
import MarketingService from "../../services/MarketingService";
import { downLoadTemplate, downloadExcelData } from "../../services/ServiceConstants";
import CampaignInfo from "../CampaignInfo";
import { AlertContext } from "../Contexts/UserContext";
import CouponInfo from "../CouponInfo";
import LoyaltyTypes from "../LoyaltyTypes";
import MetaInfo from "../MetaInfo";
import RegionsWrapper from "../RegionsWrapper";
import { BodyComponent, HeaderComponent, Wrapper } from "../common/CommonStructure";
import CustomNavItem from "../common/CustomNavItem";
import TemplateDropDown from "../common/TemplateDropDown";
import { getTabDetails } from "../util/TabUtils";
import Details from "./Details";
import ProductCategory from "./ProductCategory";
import PromotionSlabs from "./PromotionSlabs";
import ComplimentaryService from "../../services/ComplimentaryService";

let fileData = {};
let excelData = RP_EXCEL_DATA;
let RP_TEMPLATES = []
let REGION_STORE_EXCEL = []
let isToDateChanged = false;
let toastMessage;
const CreateRegularPromotion = (props) => {
    
    const [providedExcelData , setProvidedExcelData] = useState({})
    const [isFormLoaded, setFormLoaded] = useState(false);
    const [trigger, setTrigger] = useState()
    const {setAlertContent, setStackedToastContent} = useContext(AlertContext)
    const [globalPromotionStatus, setGlobalPromotionStatus] = useState('I');
    const [showChildren, setShowChildren] = useState(true);
    const [activeTabId, setActiveTabId] = useState(1);
    const [promotionId, setPromotionId] = useState(undefined);
    const [clonePromotionId, setClonePromotionId] = useState(undefined);
    const [showUpdateToDate, setShowUpdateToDate] = useState(false);
    const [loading, setLoading] = useState(false);
    const [campaignNameValidated, setCampaignNameValidated] = useState(false);
    const [couponCodeValidated, setCouponCodeValidated] = useState(false);
    const headerRef = useRef(null);
    const [prevCampaignName, setPrevCampaignName] = useState(undefined);
    const [prevCouponCode, setPrevCouponCode] = useState(undefined);
    const [helpers,setHelpers] = useState(false);
    const [prepareFinalObjectFlag, setPrepareFinalObjectFlag] = useState(false);
    const [disableCouponCode, setDisableCouponCode] = useState(false);
    const [isApprover, setApprover] = useState(false);
    const [channel, setChannel] = useState(undefined);

    const [actionPermissions, setActionPermissions] = useState({
        isCreate : false,
        isEdit   : false,
        isClone  : false,
        isApprover : false,
        isCloser : false
    })

    const applicableTypeRoles = {
        pharmacy : RP_CREATE,
    }
    const setIsToDateChanged = () => {
        isToDateChanged = true;
    }

    useEffect(()=>{
        if(activeTabId==9){
            prepareFinalObject(undefined, RP_TABS.details.tabId);
        }
    }, [prepareFinalObjectFlag])

    const [regularPromotion, setRegularPromotion] = useState({
        campaignInfo:{},
        couponInfo: {},
        metaInfo: {},
        slabInfo: {},
        category :[],
        loyalty :[],
        regions: {},
        details: {}, 
        uploads: {}

    }); 

    let fileOptions = {
        productFile: {show: false,title: "Products"},
        customerFile: {show: false,title: "Customers"}
    };

    useEffect(() => {
        return () =>{
            setAlertContent({});
        }
    },[])

    useEffect(()=>{
        const promotionId = (Validate().isNotEmpty(props.location.state) && Validate().isNotEmpty(props.location.state.promotionId)) ? props.location.state.promotionId : undefined;
        setLoading(true);
        if(Validate().isNotEmpty(promotionId)) {
            setShowChildren(true);
            let edit = false;
            let clone = false;
            if (props.path === RP_URLS.editCampaign || props.path === RP_URLS.closeCampaign || props.path === RP_URLS.approveCampaign) {
                excelData.customers.url = "";
                REGION_STORE_EXCEL = [];
                STORE_EXCEL_DATA.regular.url = "";
                edit = true;
                actionPermissions["isEdit"] = true;
                if (props.path === RP_URLS.closeCampaign) {
                    actionPermissions["isCloser"] = true;
                }
            } else if(props.path ===  RP_URLS.cloneCampaign) {
                clone = true;
                actionPermissions["isClone"]= true;
            }
            getRegularPromotionByPromotionId(promotionId, edit, clone);
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

            setRegularPromotion(
                {
                  ...regularPromotion,
                    campaignInfo:{},
                    couponInfo: {},
                    metaInfo: {},
                    slabInfo: {},
                    category :[],
                    loyalty :[],
                    regions: {},
                    details: [], 
                    uploads: {}
                }
            )
            setPromotionId(undefined);
            if(props.match.path !== RP_URLS.createCampaign ) {
                props.history.push(RP_URLS.createCampaign)
            }
        }
        setLoading(false);
    }, [props?.match?.params, actionPermissions?.isEdit, actionPermissions?.isClone])

    const handleCampaignNameChange  = () => {
        setCampaignNameValidated(false);
    }

    const handleCouponNameChange = () => {
        setCouponCodeValidated(false);
    }
    
    const onCampaignTypeChange = () => {
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
                setRegularPromotion({...regularPromotion, campaignInfo:object});
                setPrevCampaignName(object.campaignName);
                setActiveTabId(setTabId)
                break;
            case 2:
                if(object.couponCode && (object.couponCode)?.indexOf(" ") >= 0) {
                    helpers.updateErrorMessage("Invalid Coupon Code","couponCode");
                    break;
                }
                if(object.couponCode && !couponCodeValidated && !(actionPermissions.isEdit && disableCouponCode)) {
                    if(prevCouponCode === object.couponCode || await checkCouponCodeExist(object.couponCode) ) {
                        setPrevCouponCode(object.couponCode);
                        helpers.updateErrorMessage("Coupon Code already exists","couponCode");
                        break;
                    }
                    setCouponCodeValidated(true);
                }
                setRegularPromotion({...regularPromotion, couponInfo:object});
                setPrevCouponCode(object.couponCode)
                setActiveTabId(setTabId);
                break;
            case 3:
                setRegularPromotion({...regularPromotion, metaInfo:object});
                setActiveTabId(setTabId)
                break;
            case 4:
                setRegularPromotion({...regularPromotion, slabInfo:object});
                setActiveTabId(setTabId);
                break;
            case 5:
                setRegularPromotion({...regularPromotion, category:object.categoryType});
                setActiveTabId(setTabId);
                break; 
            case 6:
                setRegularPromotion({...regularPromotion, loyalty:object.loyaltyType});
                setActiveTabId(setTabId);
                break;         
            case 7:
                setRegularPromotion({...regularPromotion, regions:object});
                setActiveTabId(setTabId);
                break;  
            case 8:
                if((actionPermissions?.isApprover || actionPermissions?.isCloser) && setTabId === RP_TABS.details.tabId + 1) {
                    fileData = {}
                    prepareFinalObject(undefined, RP_TABS.details.tabId);
                } else {
                    fileData = object?.fileObj
                    setRegularPromotion({...regularPromotion, details:object});
                    setActiveTabId(setTabId); 
                    (setTabId == 9) ? setPrepareFinalObjectFlag(!prepareFinalObjectFlag) : setActiveTabId(setTabId);
                }
                break;
            case 9: 
                prepareFinalObject(toDate);
                setActiveTabId(setTabId);
                break;
            default:
                setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:"Invalid Tab"})
                break;
        }
    }

    const checkCampaignNameExist = async (campaignName) => {
        const data = await MarketingService().validateRegularPromotionName({'promotionName': campaignName});
        return data.responseData;
    }

    const checkCouponCodeExist = async (couponCode) => {
        const data = await MarketingService().validateCouponName({'couponCode': couponCode});
        return data.responseData;
    }

    const updateToDate = (endDate) => {
        handleCampaignInfoChange({}, RP_TABS.details.tabId+1, RP_TABS.promotionInfo.tabId, false, false, false, false, endDate);
    }

    const prepareFinalObject = async (toDate = undefined, currentTab=undefined) => {    
        setShowChildren(false);
        setLoading(true);
        let finalObj = {};
        let formData = new FormData();
        const campaignInfo = regularPromotion.campaignInfo;

        if(actionPermissions.isApprover || actionPermissions.isCloser || (actionPermissions.isEdit && (toDate === null || toDate))) {
            formData.append('promotionId', promotionId);
            (activeTabId === RP_TABS.promotionInfo.tabId) ?
                formData.append('toDate', toDate? toDate.getTime() : null) :
                formData.append('toDate', campaignInfo.toDate ? campaignInfo.toDate.getTime() : null);
            if(actionPermissions?.isCloser || actionPermissions?.isApprover) {
                (campaignInfo.channel.length === 1 && campaignInfo.channel[0] === '1') && formData.append('channel', campaignInfo.channel[0]);
                formData.append('couponBased', regularPromotion?.couponInfo?.couponBased === 'Y' ? true : false);
            }
        } else {
            if(Validate().isNotEmpty(promotionId) && (actionPermissions.isEdit)) {
                finalObj['promotionId'] = promotionId;
            }
            if(actionPermissions.isClone && Validate().isNotEmpty(clonePromotionId)) {
                finalObj['cloneReferenceId'] = clonePromotionId;
            }
            finalObj['promotionName'] = campaignInfo.campaignName;
            finalObj['status'] =  PromotionStatus.inActive;
            finalObj['fromDate'] = campaignInfo.fromDate.getTime();
            finalObj['toDate'] = campaignInfo.toDate ? campaignInfo.toDate.getTime() : null;
            finalObj['channels'] = campaignInfo.channel;
            finalObj['promotionLevel'] = campaignInfo.campaignType;
            finalObj['applicableType'] = campaignInfo.applicableType;

            const couponInfo = regularPromotion.couponInfo;
            finalObj['allCustomers'] = Validate().isNotEmpty(regularPromotion.couponInfo.couponBased) && Validate().isEmpty(regularPromotion.couponInfo.allCustomers) ? false : true;
            const isCouponBased = couponInfo?.couponBased === 'Y' ? true : false;
            finalObj['couponBased'] = isCouponBased
            const isAddOnCoupon = couponInfo?.addOnCoupon === 'Y' ? true : false;
            let couponInfoData = {
                couponCode: isCouponBased ? couponInfo.couponCode : null,
                noOfDays: isCouponBased  &&  couponInfo.noOfDays ? couponInfo.noOfDays : null,
                totalLimit: isCouponBased ? couponInfo.totalLimit : null,
                customerLimit: isCouponBased ? couponInfo.customerLimit : null,
                prescription: isCouponBased ? couponInfo.ePrescription : null, 
                addOnCoupon: isAddOnCoupon,
                minValue: isAddOnCoupon ? couponInfo.minValue : null,
                maxDiscount: isAddOnCoupon ? couponInfo.maxDiscount : null,
                maxPoints: isAddOnCoupon ? couponInfo.maxPoints : null
            };

            finalObj['promotionCoupon'] = couponInfoData;
    
            const metaInfo = regularPromotion.metaInfo;
            let userMetaData =  {
                longDescription: metaInfo.longDescription,
                imagePath : metaInfo.imagePath,
                imageServerName: metaInfo.imageServerUrl,
                promotionVisible : Validate().isNotEmpty(metaInfo.promotionVisible) ? true : false,
            }
            finalObj['userMetaData'] = userMetaData;
            finalObj['slabs'] = regularPromotion.slabInfo?.slabs;
            finalObj['slabGroupId'] = regularPromotion.slabInfo?.existingSlabGroups[0];
            finalObj['productCategoryIds'] = regularPromotion.category;
            finalObj['loyalty'] = regularPromotion.loyalty;
            finalObj['regions'] = Object.keys(regularPromotion.regions);
            finalObj['categoryDetail'] = regularPromotion.details.categoryDetail;
            
            console.log('finalObj to submit: ', JSON.stringify(finalObj));
            formData.append('regularPromotionInfo', JSON.stringify(finalObj));
            formData.append('customerUpload', Validate().isNotEmpty(fileData?.customerUpload) ? fileData.customerUpload[0]: null);
            formData.append('customerRemove', Validate().isNotEmpty(fileData?.customerRemove) ? fileData.customerRemove[0]: null);
        }    
        let response; 
        if(actionPermissions.isCreate){
            response = await MarketingService().createRegularPromotion(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions.isEdit && activeTabId>1 && !(actionPermissions.isApprover || actionPermissions.isCloser)) {
            response = await MarketingService().editRegularPromotion(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(globalPromotionStatus === PromotionStatus.inActive && actionPermissions?.isEdit && activeTabId == 1) {
            response = await MarketingService().updateInActiveRegularPromotionToDate(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions.isClone) {
            response = await MarketingService().cloneRegularPromotion(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions?.isCloser) {
            response = await MarketingService().updateRegularPromotionToDate(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions?.isApprover) {
            response = await MarketingService().approveRegularPromotion(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } 
        return ResponseHandler(setAlertContent).handleResponse(response,{'successAlert': true},(data) => {
            let campaign;
            let currLoc = props.match.path;
            if(currLoc === RP_URLS.createCampaign || (currLoc === RP_URLS.editCampaign && activeTabId > 1) || currLoc === RP_URLS.approveCampaign || currLoc === RP_URLS.closeCampaign || currLoc === RP_URLS.cloneCampaign) {
                if(typeof data === 'string'){
                    campaign = Validate().isNotEmpty(data) ? JSON.parse(data) : null;
                } else {
                    campaign = data;
                }
            } 
                if(currLoc === RP_URLS.approveCampaign || currLoc === RP_URLS.closeCampaign || (currLoc === RP_URLS.editCampaign && activeTabId == 1)) {
                campaign = {
                    'promotionName': regularPromotion.campaignInfo.campaignName,
                    'promotionId' : data,    
                }
            }
            let note;
            if(currLoc === RP_URLS.createCampaign || currLoc === RP_URLS.cloneCampaign){
                note = "created"
            } else if(currLoc === RP_URLS.editCampaign || currLoc === RP_URLS.closeCampaign || currLoc === RP_URLS.approveCampaign) {
                if(currLoc === RP_URLS.approveCampaign && globalPromotionStatus === PromotionStatus.inActive) {
                    note="approved"
                } else{
                    note="updated"
                }
            }
            setShowChildren(true);
            setLoading(false);
            toastMessage = <div><p className="mb-0">Promotion  <b>{campaign.promotionName} - {campaign.promotionId} {note} successfully. </b></p>
                                <p className="mb-0">The system will automatically redirect you to the {actionPermissions?.isCreate ? "Create Tab" : "Dashboard"}</p>
                            </div>;
            setStackedToastContent({toastMessage: `Promotion  ${campaign.promotionName} [${campaign.promotionId}] ${note} successfully...`,
                             onClose : () => {setStackedToastContent({})}
            });
            setActiveTabId(RP_TABS.default.tabId);
            successCallBackHandler(campaign.promotionId);
        }, (error) => {
            setShowChildren(true);
            setActiveTabId(currentTab?currentTab:activeTabId);
            setTimeout(() => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            },100)
            setLoading(false);
        });
    }

    const successCallBackHandler = (campaignId) => {
        if(actionPermissions?.isCreate) {
            setActiveTabId(1);
            setRegularPromotion({
                campaignInfo:{},
                couponInfo: {},
                metaInfo: {},
                slabInfo: {},
                category :[],
                loyalty :[],
                regions: {},
                details: {}, 
                uploads: {}
            });
            props.history.push(RP_URLS.createCampaign);    
        } else {
            props.history.push({
                pathname: RP_URLS.listCamapign,
                campaignId: campaignId,
                isSuccessOperation : true
            });
        }
    }

    const handleRegionsChange =(regionsMap) => {
        setRegularPromotion({...regularPromotion, regions:regionsMap});
    }
    const getApproverStatus = (applicableType) => {
        let approver = false;
        if(props.path === RP_URLS.createCampaign ){
            if( !(Validate().validateRole(RP_ROLES.pharmacyCreator)) ) {
                approver = true;
            }
        } 
        else {
            approver = Validate().isApproverForApplicableType(applicableType, RP_ROLES);
        } 
        return approver;
    }

    useEffect(() => {
        setActionPermissions({...actionPermissions, isApprover:isApprover})
    }, [isApprover])

    const failCallBackHandler = () => {
        props.history.push(RP_URLS.listCamapign)
    }

    // useEffect(() => {
    //     if(Validate().isNotEmpty(regularPromotion.couponInfo.addOnCoupon) && actionPermissions.isEdit && !actionPermissions.isApprover) {
    //         setRegularPromotion({...regularPromotion, couponInfo : {...regularPromotion.couponInfo, addOnCoupon : null}})
    //     }
    // }, [regularPromotion])
    
    const getRegularPromotionSlabs = async (slabGrpId,wrappedObj) => {
        console.log('getting slabs from server');
        const response = await MarketingService().getSlabGroupDetails({ 'slabGroupId': slabGrpId }).catch(error => {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to fetch slab group details'});
        });
        ResponseHandler(setAlertContent).handleResponse(response, {}, (data) => {
            let slabs =[]
            data.slabGroupDetails.forEach(slab => {
                slabs.push({"slabId" : slab.slabId, "fromValue" : slab.fromValue, "toValue" : slab.toValue});
            })
            wrappedObj['slabInfo']['slabs'] = slabs;
            // setSlabs(slabs);
        }, (error) => {
          setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: error})
        });
    }

    const getRegularPromotionByPromotionId = async (promotionId, isEdit=false, isClone=false) => {
        setShowUpdateToDate(false);
        isToDateChanged=false;
        const response = await MarketingService().getRegularPromotionByPromotionId({'promotionId': promotionId}).catch(error=>{
            setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:error})
            return false;
        });
        if(Validate().isNotEmpty(response) && response.statusCode==="SUCCESS" && Validate().isNotEmpty(response.responseData)) {
            const data=response.responseData
            const wrappedObj = {
                campaignInfo:{},
                couponInfo: {},
                metaInfo: {
                    'stackedImages' : {}
                },            
                slabInfo: {},
                category :[],
                loyalty :[],
                regions: {},
                details: {},  
                uploads: {}
            };
            setPromotionId(data['promotionId']);

            wrappedObj['campaignInfo']['campaignName'] = data['promotionName'];
            if(isEdit){
                setGlobalPromotionStatus(data['status'])
            } else if(isClone) {
                setClonePromotionId(data['promotionId']);
                wrappedObj['campaignInfo']['campaignName'] = undefined;
                // wrappedObj['campaignInfo']['campaignName'] = Validate().isNotEmpty(cloneDataModalRedux.promotionName) ? cloneDataModalRedux.promotionName : undefined ;
            }
           
            setPrevCampaignName(wrappedObj['campaignInfo']['campaignName']);
            wrappedObj['campaignInfo']['applicableType'] = String(data['applicableType']);
            const approver = () => getApproverStatus(wrappedObj['campaignInfo']['applicableType'])
            setApprover(approver)
        
            wrappedObj['campaignInfo']['campaignType'] = data['promotionLevel'];
            const channels = data['channels'];
            if (channels.length === 1) {
                setChannel(channels[0]);
            }
            wrappedObj['campaignInfo']['channel'] = channels.map(element => element.toString());
            wrappedObj['campaignInfo']['fromDate'] = data['fromDate'];
            wrappedObj['campaignInfo']['toDate'] = data['toDate'];
        
            wrappedObj['campaignInfo']['status'] = isClone ? PromotionStatus.inActive : data['status'];
            wrappedObj['couponInfo']['allCustomers'] = (data['allCustomers']) ? 'Y' : null;

            wrappedObj['couponInfo']['couponBased'] = data['couponBased'] ? 'Y' : null;
        if(Validate().isNotEmpty(data.promotionCoupon)) {
            wrappedObj['couponInfo']['couponCode'] = data.promotionCoupon['couponCode']
            if(isClone) {
                wrappedObj['couponInfo']['couponCode'] = undefined
                // wrappedObj['campaignInfo']['couponCode'] = Validate().isNotEmpty(cloneDataModalRedux.couponCode) ? cloneDataModalRedux.couponCode : undefined ;
            }
            setPrevCouponCode(wrappedObj['couponInfo']['couponCode']);
            if(isEdit && Validate().isNotEmpty(data.promotionCoupon.couponCode)) {
                setDisableCouponCode(true);
            }
            //change key value for noofdays , minvalue , maxpoints
            wrappedObj['couponInfo']['noOfDays'] = data.promotionCoupon['noOfDays']
            wrappedObj['couponInfo']['totalLimit'] = data.promotionCoupon['totalLimit']
            wrappedObj['couponInfo']['customerLimit'] = data.promotionCoupon['customerLimit']
            
            wrappedObj['couponInfo']['ePrescription'] = data.promotionCoupon['prescription']
            wrappedObj['couponInfo']['addOnCoupon'] = data.promotionCoupon['addOnCoupon'] ? 'Y' : null;
            wrappedObj['couponInfo']['minValue'] = data.promotionCoupon['minValue']
            wrappedObj['couponInfo']['maxDiscount'] = data.promotionCoupon['maxDiscount']
            wrappedObj['couponInfo']['maxPoints'] = data.promotionCoupon['maxPoints']
        }
            if(!data.userMetaData) {
                setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to load data.'})
                return false;
            } else {
                wrappedObj['metaInfo']['longDescription'] = data.userMetaData['longDescription'];
                wrappedObj['metaInfo']['promotionVisible'] = data.userMetaData['promotionVisible'] ? ['promotion'] : null;
                if( Validate().isNotEmpty(data.userMetaData['imageServerName']) && Validate().isNotEmpty(data.userMetaData['imagePath'])) {
                    let url = data.userMetaData['imageServerName'];
                    let path =  data.userMetaData['imagePath'];
                    wrappedObj['metaInfo']['imageServerName'] = url;
                    wrappedObj['metaInfo']['imagePath'] = path;
                    wrappedObj['metaInfo']['stackedImages']['imageServerUrl'] = url;
                    wrappedObj['metaInfo']['stackedImages']['imagePath'] = `${url}/${path}`;
                    wrappedObj['metaInfo']['stackedImages']['thumbnailPath'] = `${url}/${path}`;
                }
            }
            getRegularPromotionSlabs(data.slabGroupId,wrappedObj);
            wrappedObj['slabInfo']['existingSlabGroups'] = [data.slabGroupId];
            // wrappedObj['slabInfo']['slabs'] = data.regularPromotionSlabs.slabs;
            // wrappedObj['slabInfo']['slabIds'] = data.regularPromotionSlabs.slabIds;
            // wrappedObj['slabInfo']['existingSlabGroups'] = data.regularPromotionSlabs.existingSlabGroups; 
            // wrappedObj['slabInfo']['slabGroupName'] = data.regularPromotionSlabs.slabGroupName;
            wrappedObj['slabInfo']["isAddGrpHide"] = true;

            wrappedObj['category'] = data['productCategoryIds'].map((element) => element.toString());
            wrappedObj['loyalty'] = data['loyalty'].map((element) => element.toString());
            wrappedObj['regions'] = {};
            if(Validate().isNotEmpty(data['regions']));
            data['regions'].forEach(region => {
                wrappedObj['regions'][region]= {'ner':"", name:""};
            });

            wrappedObj['details']['categoryDetail'] = data['categoryDetail'];
            // wrappedObj['details']['allRegions'] = true;
            console.log("wrapped obj : ", wrappedObj)
            setRegularPromotion(wrappedObj);
            setGlobalPromotionStatus( ...wrappedObj['campaignInfo']['status'])
        } else {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:`${data.message}`, handleCallBack: failCallBackHandler, delayTime: 3000})
        }
    }

    useEffect(() => {
        if(activeTabId === RP_TABS.region.tabId) {
           RP_TEMPLATES = [{header: 'Downloads', data: []}, {header: 'Templates',data: []}];
           if(regularPromotion.couponInfo.allCustomers != 'Y') {
               pushExcelData(0, excelData.customers.title, excelData.customers);
               pushTemplate(1,  RP_DOWNLOAD_TEMPLATES.customers.title, RP_DOWNLOAD_TEMPLATES.customers)
           }
        }
    }, [activeTabId])
   
    const pushTemplate = (index, title, templateData) => {
        if(!(actionPermissions.isApprover || actionPermissions.isCloser))
            RP_TEMPLATES[index].data.push({title: title, onClick: () => downLoadTemplate(templateData)});
    }
   
    const pushExcelData = (index, title, excelDataItem) => {
        if(actionPermissions.isEdit || actionPermissions.isApprover) {
            const params = {'promotionId': props?.location?.state?.promotionId , 'couponCode' : regularPromotion?.couponInfo?.couponCode}
            RP_TEMPLATES[index].data.push({title: title , onClick: () => 
                downloadExcelData(excelDataItem, params, props?.location?.state?.promotionId, setAlertContent, ALERT_TYPE, setLoading)});
        }
    }

    useEffect (()=> {
        if(channel === 1) {
            REGION_STORE_EXCEL = [{header: 'Downloads', data: []}, {header: 'Templates',data: []}];
            if(actionPermissions.isEdit || actionPermissions.isApprover) {
                REGION_STORE_EXCEL[0].data.push({
                    title: STORE_EXCEL_DATA.regular.title , 
                    onClick: () => downloadExcelData(STORE_EXCEL_DATA.regular, {'campaignId': props?.location?.state?.promotionId}, props?.location?.state?.promotionId, setAlertContent, ALERT_TYPE, setLoading)
                });
            }
        }
    }, [channel])

    useEffect(()=>{
        setActiveTabId(1)
    },[props.location.pathname])

    const noteItems = [
        "No. of Days: Will be used when system generates coupon automatically, to define the period from now to no of days for coupon validity.",
        "AddOn Coupon: If you choose this, the discount given here will be applied on top of the existing discount.",
        "All Customers will be enabled if you select Coupon Based."
    ]

    return(
        <React.Fragment key={`${props.location.pathname}`}>
            <Wrapper>
                <HeaderComponent className="custom-tabs-forms p-0" ref={headerRef}>
                {(actionPermissions?.isEdit) && <div className="d-flex border-bottom p-12 align-items-center">
                   <Button variant=" " className="btn-link icon-hover" onClick={()=>{props.history.push({pathname: RP_URLS.listCamapign, campaignId: promotionId})}}> <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                        <g id="leftarrow_black_icon_18px" transform="translate(-48.941 -316.765)">
                            <rect id="BG_Guide" data-name="BG Guide" width="18" height="18" transform="translate(48.941 316.765)" fill="none" />
                            <path id="Path_22927" data-name="Path 22927" d="M56.566,319.236a.686.686,0,0,0-.448.178l-6.977,6.53a.7.7,0,0,0,0,.984l6.977,6.44a.709.709,0,0,0,.984-.089.7.7,0,0,0,0-.984l-5.546-5.188H66.226a.716.716,0,0,0,0-1.431H51.557l5.635-5.188a.635.635,0,0,0,.269-.536,1.275,1.275,0,0,0-.179-.537A4.04,4.04,0,0,0,56.566,319.236Z" transform="translate(0 -0.471)" fill="#080808" />
                        </g>
                    </svg></Button>
                    <div className="mx-3 " title="promotionID" > Promotion ID - {promotionId}</div>
                    <div className="text-white me-3" title="Status">  {globalPromotionStatus === 'A' ? <span className="badge  badge-approved">Approved</span> : globalPromotionStatus === 'I' ? <span className="badge  badge-pending">Created</span>  :  <span className="badge  badge-rejected">Rejected</span>} </div></div>}
                    
                <Nav tabs className="sub-tabs">
                    <CustomNavItem  currentTabId={RP_TABS.promotionInfo.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={RP_TABS.promotionInfo.title}/>
                    {/* {showCouponTab && */}
                    <CustomNavItem  currentTabId={RP_TABS.couponInfo.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={RP_TABS.couponInfo.title}/>
                    <CustomNavItem  currentTabId={RP_TABS.metaInfo.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={RP_TABS.metaInfo.title}/>
                    <CustomNavItem  currentTabId={RP_TABS.slabs.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={RP_TABS.slabs.title}/>
                    <CustomNavItem  currentTabId={RP_TABS.category.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={RP_TABS.category.title}/>
                    <CustomNavItem  currentTabId={RP_TABS.loyalty.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={RP_TABS.loyalty.title}/>
                    <CustomNavItem  currentTabId={RP_TABS.region.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={RP_TABS.region.title}/>
                    <CustomNavItem  currentTabId={RP_TABS.details.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={RP_TABS.details.title}/>
                    {(Validate().isNotEmpty(regularPromotion.couponInfo.couponBased) && Validate().isEmpty(regularPromotion.couponInfo.allCustomers))  && activeTabId === RP_TABS.details.tabId &&
                        <TemplateDropDown templates={RP_TEMPLATES} {...props}/>
                    } 
                    {(actionPermissions?.isEdit || actionPermissions?.isApprover || actionPermissions?.isCloser) && activeTabId == RP_TABS.region.tabId && Validate().isNotEmpty(REGION_STORE_EXCEL) &&
                            <TemplateDropDown templates={REGION_STORE_EXCEL} {...props}/>
                    } 

                </Nav>
                </HeaderComponent>

                <BodyComponent  className="body-height" loading={loading} showChildren={showChildren} allRefs={{ headerRef }} isFooterRequired={activeTabId == "8" && true}>
                    
                    {activeTabId == "0" && <TabPane  className="h-100 d-flex justify-content-center align-items-center" tabId={0}>
                    <div className="text-center">
                        <img src={UpdatedSuccessfullyIcon} alt="Updated Successfully" />
                        {toastMessage}
                    </div>
                    </TabPane>}
                    {activeTabId == "1" && <TabPane className="h-100" tabId={1}>
                        <CampaignInfo actionPermissions={actionPermissions} setHelpers={setHelpers} setLoading={setLoading} {...props} handleCampaignNameChange ={handleCampaignNameChange}  handleCampaignInfoChange={handleCampaignInfoChange}  onCampaignTypeChange={onCampaignTypeChange} trigger = {trigger} campaignInfo={regularPromotion.campaignInfo}  globalPromotionStatus={globalPromotionStatus}
                        applicableTypeRoles={applicableTypeRoles} showUpdateToDate={showUpdateToDate} setShowUpdateToDate={setShowUpdateToDate} updateToDate={updateToDate} tabDetails = {getTabDetails(RP_TABS.promotionInfo, RP_TABS.couponInfo,'')} setIsToDateChanged={setIsToDateChanged} promotionType = {PromotionType.regular}/>
                    </TabPane>}
                    {activeTabId == "2" && <TabPane className="h-100" tabId={2}>
                        <CouponInfo {...props} setLoading={setLoading} setHelpers={setHelpers} couponInfo={regularPromotion.couponInfo} handleCouponNameChange={handleCouponNameChange} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={globalPromotionStatus} 
                        actionPermissions={actionPermissions} note={noteItems} disableCouponCode={disableCouponCode} 
                        tabDetails = {getTabDetails(RP_TABS.couponInfo, RP_TABS.metaInfo,RP_TABS.promotionInfo)}/>
                    </TabPane>}
                    {activeTabId == "3" && <TabPane className="h-100" tabId={3}>
                        <MetaInfo {...props} setLoading={setLoading} metaInfo={regularPromotion.metaInfo} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={ globalPromotionStatus} 
                        actionPermissions={actionPermissions} tabDetails = {getTabDetails(RP_TABS.metaInfo, RP_TABS.slabs,RP_TABS.couponInfo)} promotionType = {PromotionType.regular}/>
                    </TabPane>}
                    {activeTabId == "4" && <TabPane className="h-100" tabId={4}>
                        <PromotionSlabs {...props} setLoading={setLoading} slabInfo={regularPromotion.slabInfo}  handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={ globalPromotionStatus} 
                        actionPermissions={actionPermissions} tabDetails = {getTabDetails(RP_TABS.slabs, RP_TABS.category,RP_TABS.metaInfo)}/>                    
                    </TabPane>}
                    {activeTabId == "5" && <TabPane className="h-100" tabId={5}>
                        <ProductCategory type="c" {...props} setLoading={setLoading} category={regularPromotion.category}  handleCampaignInfoChange={handleCampaignInfoChange}
                        actionPermissions={actionPermissions}  globalPromotionStatus={globalPromotionStatus} tabDetails = {getTabDetails(RP_TABS.category, RP_TABS.loyalty,RP_TABS.slabs)}/>
                    </TabPane>}
                    {activeTabId == "6" && <TabPane className="h-100" tabId={6}>
                        <LoyaltyTypes type="l" {...props} setLoading={setLoading}  loyalty={regularPromotion.loyalty} handleCampaignInfoChange={handleCampaignInfoChange}  globalPromotionStatus={ globalPromotionStatus} 
                        actionPermissions={actionPermissions} tabDetails = {getTabDetails(RP_TABS.loyalty, RP_TABS.region,RP_TABS.category)}/>
                    </TabPane>}
                    {activeTabId == "7" && <TabPane className="h-100" tabId={7}>
                        {/* <Regions {...props} setLoading={setLoading}  regions={regularPromotion.regions} campaignInfo={regularPromotion.campaignInfo} handleCampaignInfoChange={handleCampaignInfoChange}  globalPromotionStatus={globalPromotionStatus}
                        actionPermissions={actionPermissions} isNotEligibleRegionsRequired={false} tabDetails = {getTabDetails(RP_TABS.region, RP_TABS.details,RP_TABS.loyalty)}/>
                     */}
                    <div className='row'>                    
                    <RegionsWrapper 
                    applicableType={regularPromotion?.campaignInfo?.applicableType} 
                    showStoreLevel ={(Validate().isNotEmpty(regularPromotion.campaignInfo?.channel) && regularPromotion.campaignInfo.channel.length == 1 && regularPromotion.campaignInfo.channel[0] == '1')}
                    showNextPrev={true} showDataInGrid={false} handleRegionsChange ={handleRegionsChange}  {...props} setLoading={setLoading}  regions={regularPromotion.regions} channel={regularPromotion.campaignInfo?.channel} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={globalPromotionStatus} 
                    actionPermissions={actionPermissions} isUpdateBtnRequired={false} isNotEligibleRegionsRequired={false} tabDetails = {getTabDetails(RP_TABS.region, RP_TABS.details,RP_TABS.loyalty)}/>
                    </div>
                    </TabPane>}
                    {activeTabId == "8" && <TabPane className="h-100" tabId={8}>
                        <Details {...props} setLoading={setLoading}  details={regularPromotion.details} regObj={regularPromotion} campaignInfo={regularPromotion.campaignInfo} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={globalPromotionStatus}
                        actionPermissions={actionPermissions} 
                        tabDetails = {getTabDetails(RP_TABS.details,'',RP_TABS.region)}
                        fileOptions={{...fileOptions, customerFile:{ ...fileOptions.customerFile, show: ( regularPromotion.couponInfo.allCustomers === 'Y') ? false : true }}} 
                        uploads={regularPromotion.uploads} isToDateChanged={isToDateChanged}
                        setProvidedExcelData={setProvidedExcelData} 
                        customerFlag={Validate().isNotEmpty(regularPromotion.couponInfo.couponBased) && Validate().isEmpty(regularPromotion.couponInfo.allCustomers) ? true : false}
                        />
                    </TabPane>}
                    {/* {activeTabId == "9" && <TabPane className="h-100" tabId={9}>
                        <Uploads {...props} 
                            fileOptions={{...fileOptions, customerFile:{ ...fileOptions.customerFile, show: ( regularPromotion.campaignInfo.allCustomers === 'Y') ? false : true }}} 
                            actionPermissions={actionPermissions}  
                            setLoading={setLoading}  
                            uploads={regularPromotion.uploads} 
                            globalPromotionStatus={globalPromotionStatus}
                            handleCampaignInfoChange={handleCampaignInfoChange} setProvidedExcelData={setProvidedExcelData} 
                            tabDetails = {getTabDetails(RP_TABS.uploads,'',RP_TABS.details)}/>
                    </TabPane>} */}
                </BodyComponent>

            </Wrapper>
        </React.Fragment>
    )
}

export default CreateRegularPromotion;