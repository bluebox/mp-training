import { ALERT_TYPE } from "@medplus/react-common-components/DynamicForm";
import React, { useContext, useEffect, useRef, useState } from "react";
import { Button } from "react-bootstrap";
import { Nav, TabPane } from "reactstrap";
import { LENS_ITEM_NOTE, NON_SB_ITEM_NOTE, NON_SB_MULTIPLE_ITEM_NOTE, NON_SD_ITEM_NOTE, NON_SD_MULTIPLE_ITEM_NOTE, NO_ITEMS_NOTE, SB_ITEM_NOTE, SB_MULTIPLE_ITEM_NOTE, SD_ITEM_NOTE, SD_MULTIPLE_ITEM_NOTE } from "../../constants/ItemsNoteConstants";
import { CAMPAIGN, CAMPAIGN_CREATE_LAB_DOCTOR, CAMPAIGN_CREATE_LENS, CAMPAIGN_CREATE_PHARMACY } from "../../constants/MarketingRoles";
import { PromotionStatus, PromotionType, SERVICE_CHARGE_DETAIL } from "../../constants/PromotionConstants";
import { CAMPAIGN_TABS } from "../../constants/TabConstants";
import { CAMPAIGN_DOWNLOAD_TEMPLATES, CAMPAIGN_EXCEL_DATA, STORE_EXCEL_DATA } from "../../constants/TemplateConstants";
import { CAMPAIGN_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import UpdatedSuccessfullyIcon from '../../images/updated-successfully-icon.svg';
import CampaignService from "../../services/CampaignService";
import { downLoadTemplate, downloadExcelData, downloadUrl, getApplicableTypeName, getChanelsOnApplicableType } from "../../services/ServiceConstants";
import CampaignInfo from "../CampaignInfo";
import { AlertContext } from "../Contexts/UserContext";
import CouponInfo from "../CouponInfo";
import ItemsUpload from "../ItemsUpload";
import LoyaltyTypes from "../LoyaltyTypes";
import MetaInfo from "../MetaInfo";
import RegionsWrapper from "../RegionsWrapper";
import { BodyComponent, HeaderComponent, Wrapper } from "../common/CommonStructure";
import CustomNavItem from "../common/CustomNavItem";
import TemplateDropDown from "../common/TemplateDropDown";
import { getTabDetails } from "../util/TabUtils";

let excelData = CAMPAIGN_EXCEL_DATA;
let CAMPAIGN_TEMPLATES = []
let REGION_STORE_EXCEL = []
let isToDateChanged = false;
let toastMessage;
const CreateOldCampaign = (props) => {
    
    const [providedExcelData , setProvidedExcelData] = useState({})
    const [isFormLoaded, setFormLoaded] = useState(false);
    const [campaign, setCampaign] = useState({
        campaignInfo:{},
        couponInfo: {},
        metaInfo: {},
        loyalty :[],
        regions: {},
        uploads: {}
    });
    const [showChildren, setShowChildren] = useState(true);
    const [trigger, setTrigger] = useState()
    const [isMultipleCampaigns , setIsMultipleCampaigns] = useState(false);
    const [splitBy , setSplitBy] = useState("store");
    const {setAlertContent, setStackedToastContent} = useContext(AlertContext)
    const [globalPromotionStatus, setGlobalPromotionStatus] = useState(PromotionStatus.inActive);
    const [activeTabId, setActiveTabId] = useState(1);
    const [campaignId, setCampaignId] = useState(undefined);
    const [cloneCampaignId, setCloneCampaignId] = useState(undefined);
    const [existingCouponBased, setExistingCouponBased] = useState(undefined);
    const [existingCouponType, setExistingCouponType] = useState(undefined);
    const [couponId, setCouponId] = useState(undefined);
    const [existingAllCustomers, setExistingAllCustomers] = useState(undefined);
    const [templateId, setTemplateId] = useState(undefined);
    const [campaignType, setCampaignType] = useState(undefined);
    const [isSpecialityBased, setIsSpecialityBased] = useState(undefined);
    const [applicableType, setApplicableType] = useState(undefined);
    const [channel, setChannel] = useState(undefined);
    const [loading, setLoading] = useState(false);
    const [showUpdateToDate, setShowUpdateToDate] = useState(false);
    const [campaignNameValidated, setCampaignNameValidated] = useState(false);
    const [couponCodeValidated, setCouponCodeValidated] = useState(false);
    const [disableCouponCode, setDisableCouponCode] = useState(false);
    const [prevCouponCode, setPrevCouponCode] = useState(undefined);
    const headerRef = useRef(null);
    const [prevCampaignName, setPrevCampaignName] = useState(undefined);
    const [helpers,setHelpers] = useState(false);
    const [isApprover, setApprover] = useState(false);
    const [uploadsNote, setUploadsNote] = useState([]);

    const setIsToDateChanged = () => {
        isToDateChanged = true;
    }

    let fileData = {};

    const [actionPermissions, setActionPermissions] = useState({
        isCreate : false,
        isEdit   : false,
        isClone  : false,
        isApprover : false,
        isCloser : false
    })


    const getCouponDiscountType = (couponInfo) => {
        let couponDiscountType = 1;
        if(couponInfo && couponInfo.couponBased && couponInfo.couponBased=='Y' && couponInfo.couponType) {
            couponDiscountType = couponInfo.couponType;
        }
        return couponDiscountType;
    }
    const applicableTypeRoles = {
        pharmacy : CAMPAIGN_CREATE_PHARMACY,
        lab : CAMPAIGN_CREATE_LAB_DOCTOR,
        lens : CAMPAIGN_CREATE_LENS
    }
  
    useEffect(()=>{
        const campaignId = (Validate().isNotEmpty(props.location.state) && Validate().isNotEmpty(props.location.state.campaignId)) ? props.location.state.campaignId : undefined;
        setLoading(true);
        if(Validate().isNotEmpty(campaignId)) {
            setShowChildren(true);
            let edit = false;
            let clone = false;
            if (props.path === CAMPAIGN_URLS.editCampaign || props.path === CAMPAIGN_URLS.closeCampaign || props.path === CAMPAIGN_URLS.approveCampaign) {
                excelData.items.url = ""
                excelData.customers.url = ""
                REGION_STORE_EXCEL = []
                STORE_EXCEL_DATA.campaign.url = "";
                edit = true;
                actionPermissions["isEdit"] = true;
                if (props.path === CAMPAIGN_URLS.closeCampaign) {
                    actionPermissions["isCloser"] = true;
                }
            } else if(props.path === CAMPAIGN_URLS.cloneCampaign) {
                clone = true;
                actionPermissions["isClone"]= true
            }
            getCampaignByCampaignId(campaignId, edit, clone);
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

            setCampaign(
                {...campaign,
                    campaignInfo:{},
                    couponInfo:{},
                    metaInfo: {},
                    loyalty :[],
                    regions: [],
                    uploads: {}
                }
            )
            setCampaignId(undefined);
            if(props.match.path !== CAMPAIGN_URLS.createCampaign) {
                props.history.push(CAMPAIGN_URLS.createCampaign)
            }
        }
        setLoading(false);
    }, [props?.match?.params, actionPermissions?.isEdit, actionPermissions?.isClone])

    useEffect(() => {

        return () =>{
            setAlertContent({});
        }

    },[])

    const getCampaignByCampaignId = async (campaignId, isEdit=false, isClone=false) => {
        setShowUpdateToDate(false);
        isToDateChanged=false;
        const data = await CampaignService().getCampaignByCampaignId({'campaignId': campaignId}).catch(error=>{
            setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:error})
            return false;
        });
        // setGlobalPromotionStatus(data.responseData.globalPromotionStatus)
        if(Validate().isNotEmpty(data) && data.statusCode==="SUCCESS" && Validate().isNotEmpty(data.responseData)) {
            const wrappedObj = {
                campaignInfo:{},
                couponInfo: {},
                metaInfo: {
                    'stackedImages' : {}
                },
                loyalty :[],
                regions: [],
                uploads: {}
            };
            setCampaignId(data.responseData['campaignId']);
            wrappedObj['campaignInfo']['campaignName'] = data.responseData['campaignName'];
            if(isEdit){
                setGlobalPromotionStatus(data.responseData['status'])
                setExistingAllCustomers(data.responseData['allCustomers']);
                setExistingCouponBased(data.responseData['couponBased']);
                setExistingCouponType(data.responseData.promotionCoupon['couponDiscountType'])
            } else if(isClone) {
                setCloneCampaignId(data.responseData['campaignId']);
                wrappedObj['campaignInfo']['campaignName'] = undefined;
            }
            setCampaignType(data.responseData['campaignType']);
            setTemplateId(data.responseData['templateId']);
            setApplicableType(data.responseData['promotionApplicableType']);
            setIsSpecialityBased(data.responseData?.userMetaData?.specialtyBased ? true : false);

            setPrevCampaignName(wrappedObj['campaignInfo']['campaignName']);
            wrappedObj['campaignInfo']['applicableType'] = String(data.responseData['promotionApplicableType']);
            let approver = () => getApproverStatus(wrappedObj['campaignInfo']['applicableType'])
            setApprover(approver);
            wrappedObj['campaignInfo']['templateId'] = String(data.responseData['templateId']);
            wrappedObj['campaignInfo']['campaignType'] = String(data.responseData['campaignType']);
            wrappedObj['campaignInfo']['minInvoiceValue'] = data.responseData['minInvoiceValue'];
            const channels = data.responseData.channels;
            if (channels.length === 1) {
                setChannel(channels[0]);
            }
            wrappedObj['campaignInfo']['channel'] = channels.map(element => element.toString());
            wrappedObj['campaignInfo']['fromDate'] = data.responseData['fromDate'];
            wrappedObj['campaignInfo']['toDate'] = data.responseData['toDate'];
        	
            wrappedObj['campaignInfo']['status'] = isClone ? PromotionStatus.inActive : data.responseData['status'];
            wrappedObj['campaignInfo']['allCustomers'] = data.responseData['allCustomers'] === 'Y' ? 'Y' : null;
            wrappedObj['couponInfo']['couponBased'] = data.responseData['couponBased'] === 'Y' ? 'Y' : null;

            if(Validate().isNotEmpty(data.responseData['promotionCoupon'])) {
                const promotionCoupon = data.responseData['promotionCoupon'];
                wrappedObj['couponInfo']['couponCode'] = promotionCoupon['couponCode']
                if(isClone) {
                    wrappedObj['couponInfo']['couponCode'] = undefined
                    // wrappedObj['campaignInfo']['couponCode'] = Validate().isNotEmpty(cloneDataModalRedux.couponCode) ? cloneDataModalRedux.couponCode : undefined ;
                }
                setPrevCouponCode(wrappedObj['couponInfo']['couponCode']);
                if(isEdit && Validate().isNotEmpty(promotionCoupon.couponCode)) {
                    setDisableCouponCode(true);
                }
                //change key value for noofdays , minvalue , maxpoints
                wrappedObj['couponInfo']['noOfDays'] = promotionCoupon['noOfDays']
                wrappedObj['couponInfo']['totalLimit'] = promotionCoupon['totalLimit']
                wrappedObj['couponInfo']['customerLimit'] = promotionCoupon['customerLimit']
                setCouponId(promotionCoupon['couponId'])
                wrappedObj['couponInfo']['ePrescription'] = promotionCoupon['prescription']
                wrappedObj['couponInfo']['addOnCoupon'] = promotionCoupon['addOnCoupon'] ? 'Y' : null;
                wrappedObj['couponInfo']['minValue'] = promotionCoupon['minValue']
                wrappedObj['couponInfo']['maxDiscount'] = promotionCoupon['maxDiscount']
                wrappedObj['couponInfo']['maxPoints'] = promotionCoupon['maxPoints']
                wrappedObj['couponInfo']['couponType'] = promotionCoupon['couponDiscountType'] ? promotionCoupon['couponDiscountType'].toLocaleString() : '1';
                wrappedObj['couponInfo']['sampleCollection'] = promotionCoupon['deliveryType']
                
                let applicType = data.responseData['promotionApplicableType'];
                if(Validate().isNotEmpty(promotionCoupon['serviceChargeDiscounts']) && Validate().isNotEmpty(applicType) && applicType != '7'){
                    SERVICE_CHARGE_DETAIL[getApplicableTypeName(applicType)].forEach(({enumID}) => {
                        wrappedObj['couponInfo'][enumID] = promotionCoupon['serviceChargeDiscounts'][enumID];
                    });
                } 
            }
            if(!data.responseData.userMetaData) {
                setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to load data.'})
                return false;
            } else {
                wrappedObj['metaInfo']['longDescription'] = data.responseData.userMetaData['longDescription'];
                wrappedObj['metaInfo']['claimable'] = data.responseData.userMetaData['claimable'] ? ['claimable'] : null;
                wrappedObj['metaInfo']['specialtyBased'] = data.responseData.userMetaData['specialtyBased'] ? ['specialtyBased'] : null;
                wrappedObj['metaInfo']['promotionVisible'] = data.responseData.userMetaData['promotionVisible'] ? ['promotion'] : null;
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
            wrappedObj['loyalty'] = data.responseData['loyaltyTypes'].map((element) => element.toString());
            wrappedObj['regions'] = data.responseData['regionsMap'];
            if(Validate().isNotEmpty(data.responseData['regionsMap']));
            Object.keys(data.responseData['regionsMap']).map(key=>{
                wrappedObj['regions'][key]= {'ner':data.responseData['regionsMap'][key], name:""};
            });
            console.log("wrapped data : ", wrappedObj);
            setCampaign(wrappedObj);
            setGlobalPromotionStatus( ...wrappedObj['campaignInfo']['status'])
        } else {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:`${data.message}`, handleCallBack: failCallBackHandler, delayTime: 3000})
        }
        
    }

    useEffect(() => {
        setActionPermissions({...actionPermissions, isApprover:isApprover})
    }, [isApprover])
    

    const failCallBackHandler = () => {
        props.history.push(CAMPAIGN_URLS.listCamapign)
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
        if(props.path === CAMPAIGN_URLS.createCampaign ){
            if( !(Validate().validateRole(CAMPAIGN.pharmacyCreator) ||  Validate().validateRole(CAMPAIGN.lensCreator) ||  Validate().validateRole(CAMPAIGN.labsCreator))) {
                approver = true;
            }
        } else {
            approver = Validate().isApproverForApplicableType(applicableType, CAMPAIGN);
        } 
        return approver;
    }

    const checkCampaignNameExist = async (campaignName) => {
        const data = await CampaignService().validateCampaignName({'campaignName': campaignName});
        return data.responseData;
    }

    const checkCouponCodeExist = async (couponCode) => {
        const data = await CampaignService().validateCouponCode({'couponCode': couponCode});
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
                setCampaign({...campaign, campaignInfo:object});
                setPrevCampaignName(object.campaignName);
                if(object?.campaignType === '11' && CAMPAIGN_TABS.couponInfo.tabId == setTabId) {
                    setActiveTabId(CAMPAIGN_TABS.metaInfo.tabId)
                } else {
                    setActiveTabId(setTabId);
                }
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
                setCampaign({...campaign, couponInfo:object});
                setPrevCouponCode(object.couponCode);
                setActiveTabId(setTabId);
                break;
            case 3:
                setCampaign({...campaign, metaInfo:object});
                if(campaign?.campaignInfo?.campaignType === '11' && CAMPAIGN_TABS.couponInfo.tabId == setTabId) {
                    setActiveTabId(CAMPAIGN_TABS.campaignInfo.tabId)
                } else {
                    setActiveTabId(setTabId);
                }
                break;
            case 4:
                setCampaign({...campaign, loyalty:object.loyaltyType});
                setActiveTabId(setTabId);
                break;
            case 5:
                if((actionPermissions?.isApprover || actionPermissions?.isCloser) && setTabId === CAMPAIGN_TABS.region.tabId + 1) {
                    fileData = {}
                    prepareFinalObject();
                }
                else {
                    if(object?.isMultipleCampaigns) {
                        setIsMultipleCampaigns(true);
                        setCampaign({...campaign, regions:{}});
                    } else {
                        setIsMultipleCampaigns(false);
                        setCampaign({...campaign, regions:object});
                    }
                    setActiveTabId(setTabId)
                }
                break;
            case 6:
                fileData=object;
                if(setTabId === 7)
                    prepareFinalObject();
                else setActiveTabId(setTabId);
                break;
            case 7:
                prepareFinalObject(toDate);
                break;
            default:
                setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:"Invalid Tab"})
                break;
        }
    }

    const onCampaignTypeChange = (value) => {
        let channelTypes = [];
        if(value === '6'){
            channelTypes.push(helpers.createOption("pos","POS","1","false"));
        }else{
            channelTypes=getChanelsOnApplicableType(helpers.getHtmlElement("applicableType").value, helpers);
        }
        helpers.getHtmlElement("channel").value=[];
        helpers.getHtmlElement("channel").values=channelTypes;
        
        setCampaignType(value)
        if(value === '11') {
            setCampaign({...campaign, couponInfo:{}});
            helpers.showElement('minInvoiceValueGrp');
        } else {
            helpers.hideElement('minInvoiceValueGrp');
        }
    }

    const prepareFinalObject = async (toDate = undefined) => {
        setShowChildren(false);
        setLoading(true);
        let finalObj = {};
        let formData = new FormData();
        const campaignInfo = campaign.campaignInfo;

        if(actionPermissions.isApprover || actionPermissions.isCloser || (actionPermissions.isEdit && toDate)) {
            formData.append('campaignId', campaignId);
            formData.append('toDate', toDate ? toDate.getTime() :  campaignInfo.toDate.getTime());
            if(actionPermissions?.isCloser || actionPermissions?.isApprover) {
                (campaignInfo.channel.length === 1 && campaignInfo.channel[0] === '1') && formData.append('channel', campaignInfo.channel[0]);
                formData.append('couponBased', campaign?.couponInfo?.couponBased === 'Y' ? true : false);
            }
        } else {
            if(Validate().isNotEmpty(campaignId) && (actionPermissions?.isEdit)){
                finalObj['campaignId'] = campaignId;
            }
            if(Validate().isNotEmpty(existingAllCustomers) && (actionPermissions?.isEdit)){
                finalObj['existingAllCustomers'] = existingAllCustomers;
            }
            if(Validate().isNotEmpty(existingCouponBased) && (actionPermissions?.isEdit)){
                finalObj['existingCouponBased'] = existingCouponBased;
            }
            if(actionPermissions?.isClone && Validate().isNotEmpty(cloneCampaignId)) {
                finalObj['cloneReferenceId'] = cloneCampaignId;
            }
            if(actionPermissions?.isEdit && Validate().isNotEmpty(templateId)){
                finalObj['templateId'] = templateId;
            }
            finalObj['campaignName'] = campaignInfo.campaignName;
            finalObj['status'] = PromotionStatus.inActive;
            finalObj['fromDate'] = campaignInfo.fromDate.getTime();
            finalObj['toDate'] = campaignInfo.toDate.getTime();
            finalObj['channels'] = campaignInfo.channel;
            finalObj['campaignType'] = campaignInfo.campaignType;
            finalObj['minInvoiceValue'] = campaignInfo.minInvoiceValue;
            finalObj['promotionApplicableType'] = campaignInfo.applicableType;
            finalObj['allCustomers'] = campaignInfo.allCustomers == "Y" ? "Y" : "N";
            const couponInfo = campaign.couponInfo;
            const isCouponBased = couponInfo?.couponBased === 'Y' ? true : false;
            finalObj['couponBased'] = couponInfo?.couponBased
            const isAddOnCoupon = couponInfo?.addOnCoupon === 'Y' ? true : false;
            let couponInfoData = {
                couponId : isCouponBased  ? couponInfo.couponId : null,
                promotionId : isCouponBased  ? couponInfo.promotionId : null,
                couponCode: isCouponBased ? couponInfo.couponCode : null,
                noOfDays: isCouponBased && couponInfo.noOfDays ? couponInfo.noOfDays : null,
                totalLimit: isCouponBased ? couponInfo.totalLimit : null,
                customerLimit: isCouponBased ? couponInfo.customerLimit : null,
                prescription: isCouponBased ? couponInfo.ePrescription : null, 
                addOnCoupon: isAddOnCoupon,
                minValue: isAddOnCoupon ? couponInfo.minValue : null,
                maxDiscount: isAddOnCoupon ? couponInfo.maxDiscount : null,
                maxPoints: isAddOnCoupon ? couponInfo.maxPoints : null,
                couponDiscountType: couponInfo.couponType,
                deliveryType: couponInfo.sampleCollection,
                serviceChargeDiscounts: {}
            };

            if(Validate().isNotEmpty(campaignInfo.applicableType) && campaignInfo.applicableType != '7'){
                SERVICE_CHARGE_DETAIL[getApplicableTypeName(campaignInfo.applicableType)].forEach(({enumID}) => {
                    if(couponInfo[enumID] && Number(couponInfo[enumID]) > 0) {
                        couponInfoData['serviceChargeDiscounts'][enumID] = couponInfo[enumID];
                    }
                });
            } 
            if(isCouponBased && existingCouponBased && Validate().isNotEmpty(couponId) && (actionPermissions?.isEdit)){
                couponInfoData.couponId = couponId;
            }
            if(isCouponBased && existingCouponBased && Validate().isNotEmpty(campaignId) && (actionPermissions?.isEdit)){
                couponInfoData.promotionId = campaignId;
            }
            finalObj['promotionCoupon'] = couponInfoData;
            const metaInfo = campaign.metaInfo;
            let userMetaData =  {
                longDescription: metaInfo.longDescription,
                imagePath : metaInfo.imagePath,
                imageServerName: metaInfo.imageServerUrl,
                // promotionVisible : Validate().isNotEmpty(metaInfo.promotionVisible) ? true : false,
                claimable: Validate().isNotEmpty(metaInfo.claimable) ? true : false,
                specialtyBased: Validate().isNotEmpty(metaInfo.specialtyBased) ? true : false,
                promotionVisible: Validate().isNotEmpty(metaInfo.promotionVisible) ? true : false
            }
            finalObj['userMetaData'] = userMetaData;

            finalObj['loyaltyTypes'] = campaign.loyalty;

            const regions ={};
            let regionsMap=campaign.regions;
            Object.keys(regionsMap).map(region => {
                regions[region] = regionsMap[region]['ner'];
            });
            finalObj['regionsMap'] = regions;
            if(isMultipleCampaigns) {
                finalObj["splitBy"] = splitBy;
            }
            console.log('finalObj to submit: ', JSON.stringify(finalObj));
            formData.append('campaignInfo', JSON.stringify(finalObj));

            formData.append('productUpload', Validate().isNotEmpty(fileData.productUpload) ? fileData.productUpload[0]: null);
            formData.append('productRemove', Validate().isNotEmpty(fileData.productRemove) ? fileData.productRemove[0]: null);
            formData.append('customerUpload', Validate().isNotEmpty(fileData.customerUpload) ? fileData.customerUpload[0]: null);
            formData.append('customerRemove', Validate().isNotEmpty(fileData.customerRemove) ? fileData.customerRemove[0]: null);
        }
        let response;

        if(isMultipleCampaigns && actionPermissions?.isCreate) {
           downloadMultCampaignsInfo(formData);
           return;
        } else if(actionPermissions?.isCreate){
            response = await CampaignService().createCampaign(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions?.isEdit && !toDate && !(actionPermissions?.isApprover || actionPermissions?.isCloser)) {
            response = await CampaignService().editCampaign(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(globalPromotionStatus === PromotionStatus.inActive && actionPermissions?.isEdit && toDate) {
            response = await CampaignService().updateInActiveCampaignToDate(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions?.isClone) {
            response = await CampaignService().cloneCampaign(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } 
        else if(actionPermissions?.isCloser) {
            response = await CampaignService().updateCampaignTodate(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        } else if(actionPermissions?.isApprover) {
            response = await CampaignService().approveCampaign(formData).catch(error => {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            })
        }
        return ResponseHandler(setAlertContent).handleResponse(response,{'successAlert': true},(data) => {
            let campaignResponse;
            let currLoc = props.match.path;
            if(currLoc === CAMPAIGN_URLS.createCampaign || (currLoc === CAMPAIGN_URLS.editCampaign && !toDate) || currLoc === CAMPAIGN_URLS.cloneCampaign || currLoc === CAMPAIGN_URLS.approveCampaign || currLoc === CAMPAIGN_URLS.closeCampaign) {
                if(typeof data === 'string'){
                    campaignResponse = Validate().isNotEmpty(data) ? JSON.parse(data) : null;
                } else {
                    campaignResponse = data;
                }
            } 
            if(currLoc === CAMPAIGN_URLS.approveCampaign || currLoc === CAMPAIGN_URLS.closeCampaign || (currLoc === CAMPAIGN_URLS.editCampaign && toDate)) {
                campaignResponse = {
                    'campaignName': campaign.campaignInfo?.campaignName,
                    'campaignId' : data,    
                }
            }
        
            let note;
            if(currLoc === CAMPAIGN_URLS.createCampaign || currLoc === CAMPAIGN_URLS.cloneCampaign){
                note = "created"
            } else if(currLoc === CAMPAIGN_URLS.editCampaign || currLoc === CAMPAIGN_URLS.approveCampaign || currLoc === CAMPAIGN_URLS.closeCampaign)  {
                if(currLoc === CAMPAIGN_URLS.approveCampaign && globalPromotionStatus === PromotionStatus.inActive) {
                    note="approved"
                } else{
                    note="updated"
                }
            }            
            setShowChildren(true);
            setLoading(false);
            toastMessage = <div><p className="mb-0">Campaign  <b>{campaignResponse.campaignName} - {campaignResponse.campaignId} {note} successfully. </b></p>
                                <p className="mb-0">The system will automatically redirect you to the {actionPermissions?.isCreate ? "Create Tab" : "Dashboard"}</p>
                            </div>;
            setStackedToastContent({toastMessage: `Campaign  ${campaignResponse.campaignName} [${campaignResponse.campaignId}] ${note} successfully...`,
                onClose : () => {setStackedToastContent({})}
            }) 
            setActiveTabId(CAMPAIGN_TABS.default.tabId);
            successCallBackHandler(campaignResponse.campaignId);
        }, (error) => {
            setTimeout(() => {
                setShowChildren(true);
            }, activeTabId == CAMPAIGN_TABS.uploads?.tabId ? 2000 : 0);
            setActiveTabId(activeTabId);
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            setLoading(false);
        });
    }

    const downloadMultCampaignsInfo = async (formData) => {
        const response =  await CampaignService().createMultipleCampaigns(formData).catch(error => {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error})
        });        
        if (Validate().isNotEmpty(response) && response.headers["content-type"].includes("application/json")) {
            if (response.data instanceof Blob) {            
                const reader = new FileReader();
                reader.onloadend = () => {
                    const jsonData = JSON.parse(reader?.result); 
                    setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage: jsonData?.message});
                };
               reader.readAsText(response?.data);
            } 
        } else if (Validate().isNotEmpty(response) && response.status === 200) {
            const blob = new Blob([response.data], { type: 'application/force-download' });
            const blobUrl = window.URL.createObjectURL(blob);
            downloadUrl(blobUrl, "Multiple", "Campaigns");
            setStackedToastContent({toastMessage: `Multiple Campaigns created successfully...` ,
                onClose : () => {setStackedToastContent({})}
            }) 
            setActiveTabId(CAMPAIGN_TABS.default.tabId);
            successCallBackHandler();
        } else {    
            setAlertContent({ alertType: ALERT_TYPE.ERROR, alertMessage: "Server experiencing some problem" });
        }
        setTimeout(() => {
            setShowChildren(true);
        }, 2000);
        setLoading(false);
    }

    const updateToDate = (endDate) => {
        handleCampaignInfoChange({}, CAMPAIGN_TABS.uploads.tabId+1, CAMPAIGN_TABS.campaignInfo.tabId, false, false, false, false, endDate);
    }

    const successCallBackHandler = (campaignId) => {
        if(actionPermissions?.isCreate) {
            setActiveTabId(1);
            setCampaign({ 
                campaignInfo:{},
                couponInfo:{},
                metaInfo: {},
                loyalty :[],
                regions: {},
                uploads: {}
            });
            props.history.push(CAMPAIGN_URLS.createCampaign);
        } else {
            props.history.push({
                pathname: CAMPAIGN_URLS.listCamapign,
                campaignId: campaignId,
                isSuccessOperation : true
            });
        }
    }

    const handleRegionsChange =(regionsMap) => {
        setCampaign({...campaign, regions:regionsMap});
        Validate().isNotEmpty(regionsMap) && setIsMultipleCampaigns(false);
    }
    
    const setIsMultipleCampaignsInfo =(value) => {
        setCampaign({...campaign, regions:{}});
        setIsMultipleCampaigns(value);
    }
    
    useEffect(() => {
      if(activeTabId == CAMPAIGN_TABS.loyalty.tabId) {
        if(!(Validate().isNotEmpty(campaign.campaignInfo?.channel) && campaign.campaignInfo.channel.length == 1 && campaign.campaignInfo.channel[0] == '1'
         && getCouponDiscountType(campaign.couponInfo)) == '1') {
            setIsMultipleCampaigns(false);
         }
      }
    }, [activeTabId])
    
    const handleCouponNameChange = () => {
        setCouponCodeValidated(false);
    }

    const onApplicableTypeChange = (value) => {
        setCampaign({...campaign, regions:{}})
    }

    const uploadNoteAndTemplate = (note, template) => {
        setUploadsNote(note);
        pushTemplate(1, template.title, template);
    }

    useEffect(() => {
    const isMulCamps = actionPermissions?.isCreate && isMultipleCampaigns;
     if(activeTabId ===  isMulCamps? CAMPAIGN_TABS.uploads.tabId : CAMPAIGN_TABS.loyalty.tabId) {
        const campaignInfo = campaign.campaignInfo;
        const couponInfo = campaign.couponInfo;
        const metaInfo = campaign.metaInfo;
        CAMPAIGN_TEMPLATES = [{header: 'Downloads', data: []}, {header: 'Templates',data: []}];
        if(getCouponDiscountType(couponInfo) != '2'){
            pushExcelData(0, excelData.items.title, excelData.items);
            if(campaignInfo.campaignType === '5' || campaignInfo.campaignType === '6'){
                uploadNoteAndTemplate(isMulCamps ? NON_SD_MULTIPLE_ITEM_NOTE : NON_SD_ITEM_NOTE, 
                    isMulCamps ? CAMPAIGN_DOWNLOAD_TEMPLATES.multiplePharmaNonSDItems : CAMPAIGN_DOWNLOAD_TEMPLATES.pharmaNonSDItems);
            }
            else{
                if(campaignInfo.applicableType === '5'){
                    uploadNoteAndTemplate(isMulCamps ? SD_MULTIPLE_ITEM_NOTE : SD_ITEM_NOTE, 
                        isMulCamps ? CAMPAIGN_DOWNLOAD_TEMPLATES.multiplePharmaSDItems : CAMPAIGN_DOWNLOAD_TEMPLATES.pharmaSDItems);
                } else if(campaignInfo.applicableType === '6'){
                    if(Validate().isNotEmpty(metaInfo.specialtyBased)){
                        uploadNoteAndTemplate(isMulCamps ? SB_MULTIPLE_ITEM_NOTE : SB_ITEM_NOTE, 
                            isMulCamps ? CAMPAIGN_DOWNLOAD_TEMPLATES.multipleLabsSBItems : CAMPAIGN_DOWNLOAD_TEMPLATES.labsSBItems);
                    }else{
                        uploadNoteAndTemplate(isMulCamps ? NON_SB_MULTIPLE_ITEM_NOTE : NON_SB_ITEM_NOTE, 
                            isMulCamps ? CAMPAIGN_DOWNLOAD_TEMPLATES.multipleLabsNonSBItems : CAMPAIGN_DOWNLOAD_TEMPLATES.labsNonSBItems);
                    }
                }else{
                    uploadNoteAndTemplate(LENS_ITEM_NOTE, CAMPAIGN_DOWNLOAD_TEMPLATES.lensItems);
                }
            }
            if(actionPermissions['isEdit'])
                pushTemplate(1, CAMPAIGN_DOWNLOAD_TEMPLATES.itemsRemove.title, CAMPAIGN_DOWNLOAD_TEMPLATES.itemsRemove);
        }else{
            setUploadsNote(NO_ITEMS_NOTE);
        }
        
        if((actionPermissions.isApprover || actionPermissions.isCloser) && (channel === 1 || applicableType === 6)) {
            pushStoreExcelData(0, STORE_EXCEL_DATA.campaign.title, STORE_EXCEL_DATA.campaign);
        }

        if(campaignInfo.allCustomers != 'Y') {
            pushCustomerExcelData(0, excelData.customers.title, excelData.customers);
            pushTemplate(1,  CAMPAIGN_DOWNLOAD_TEMPLATES.customers.title, CAMPAIGN_DOWNLOAD_TEMPLATES.customers)
        }
     }
    }, [activeTabId])

    const pushTemplate = (index, title, templateData) => {
        if(!(actionPermissions.isApprover || actionPermissions.isCloser))
            CAMPAIGN_TEMPLATES[index].data.push({title: title, onClick: () => downLoadTemplate(templateData)});
    }

    const pushExcelData = (index, title, excelDataItem) => {
        if(actionPermissions.isEdit || actionPermissions.isApprover) {
            CAMPAIGN_TEMPLATES[index].data.push({title: title , onClick: () => 
                downloadExcelData(excelDataItem, {"templateId" : templateId, "campaignType" : campaignType, "applicableType" : applicableType, "isSpecialityBased" : isSpecialityBased}, props?.location?.state?.campaignId, setAlertContent, ALERT_TYPE, setLoading)});
        }
    }

    const pushCustomerExcelData = (index, title, excelDataItem) => {
        if(actionPermissions.isEdit || actionPermissions.isApprover) {
            CAMPAIGN_TEMPLATES[index].data.push({title: title , onClick: () => 
                downloadExcelData(excelDataItem,{'campaignId': props?.location?.state?.campaignId}, props?.location?.state?.campaignId, setAlertContent, ALERT_TYPE, setLoading)});
        }
    }

    useEffect (()=> {
        if(channel === 1 || applicableType === 6) {
            REGION_STORE_EXCEL = [{header: 'Downloads', data: []}, {header: 'Templates',data: []}];
            if(actionPermissions.isEdit || actionPermissions.isApprover) {
                REGION_STORE_EXCEL[0].data.push({
                    title: STORE_EXCEL_DATA.campaign.title , 
                    onClick: () => downloadExcelData(STORE_EXCEL_DATA.campaign, {'campaignId': props?.location?.state?.campaignId}, props?.location?.state?.campaignId, setAlertContent, ALERT_TYPE, setLoading)
                });
            }
        }
    }, [channel, applicableType])

    const pushStoreExcelData = (index, title, excelDataItem) => {
        if(actionPermissions.isEdit || actionPermissions.isApprover) {
            CAMPAIGN_TEMPLATES[index].data.push({title: title , onClick: () => 
                downloadExcelData(excelDataItem, {"campaignId" : props?.location?.state?.campaignId}, props?.location?.state?.campaignId, setAlertContent, ALERT_TYPE, setLoading)});
        }
    }

    useEffect(()=>{
        setActiveTabId(1)
    },[props.location.pathname])

    const noteItems = [
        "No. of Days: Will be used when system generates coupon automatically, to define the period from now to no of days for coupon validity.",
        "Add On Coupon: If you choose this, the discount given here will be applied on top of the existing discount.",
        "Service Charge: If you choose this, at least one discount must be configured."
    ]
    
    return(
        <React.Fragment key={`${props.location.pathname}`}>
            <Wrapper>
                <HeaderComponent className="custom-tabs-forms p-0" ref={headerRef}>
                {(actionPermissions?.isEdit) && <div className="d-flex border-bottom p-12 align-items-center">
                   <Button variant=" " className="btn-link icon-hover" onClick={()=>{props.history.push({pathname: CAMPAIGN_URLS.listCamapign, campaignId: campaignId})}}> 
                    <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                        <g id="leftarrow_black_icon_18px" transform="translate(-48.941 -316.765)">
                            <rect id="BG_Guide" data-name="BG Guide" width="18" height="18" transform="translate(48.941 316.765)" fill="none" />
                            <path id="Path_22927" data-name="Path 22927" d="M56.566,319.236a.686.686,0,0,0-.448.178l-6.977,6.53a.7.7,0,0,0,0,.984l6.977,6.44a.709.709,0,0,0,.984-.089.7.7,0,0,0,0-.984l-5.546-5.188H66.226a.716.716,0,0,0,0-1.431H51.557l5.635-5.188a.635.635,0,0,0,.269-.536,1.275,1.275,0,0,0-.179-.537A4.04,4.04,0,0,0,56.566,319.236Z" transform="translate(0 -0.471)" fill="#080808" />
                        </g>
                    </svg></Button>
                    <div className="mx-3 " title="CampaignID" > Campaign ID - {campaignId}</div>
                    <div className="me-3" title="Status">  {globalPromotionStatus === PromotionStatus.active ? <span className="badge  badge-approved">Approved</span> : globalPromotionStatus === PromotionStatus.inActive ? <span className="badge  badge-pending">Created</span>  :  <span className="badge  badge-rejected">Rejected</span>} </div></div>}
                    <Nav tabs className="sub-tabs">
                        <CustomNavItem  currentTabId={CAMPAIGN_TABS.campaignInfo.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={CAMPAIGN_TABS.campaignInfo.title}/>
                        <CustomNavItem  currentTabId={CAMPAIGN_TABS.couponInfo.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={CAMPAIGN_TABS.couponInfo.title}/>
                        <CustomNavItem  currentTabId={CAMPAIGN_TABS.metaInfo.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={CAMPAIGN_TABS.metaInfo.title}/>
                        <CustomNavItem  currentTabId={CAMPAIGN_TABS.loyalty.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={CAMPAIGN_TABS.loyalty.title}/>
                        <CustomNavItem  currentTabId={CAMPAIGN_TABS.region.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={CAMPAIGN_TABS.region.title}/>
                        {!(actionPermissions?.isApprover || actionPermissions?.isCloser) &&
                            <CustomNavItem  currentTabId={CAMPAIGN_TABS.uploads.tabId} activeTabId={activeTabId} setActiveTabId={setActiveTabId} tabTitle={CAMPAIGN_TABS.uploads.title}/>
                        }

                        {(actionPermissions?.isApprover || actionPermissions?.isCloser) && activeTabId == CAMPAIGN_TABS.region.tabId && Validate().isNotEmpty(CAMPAIGN_TEMPLATES) &&
                            <TemplateDropDown templates={CAMPAIGN_TEMPLATES} {...props}/>
                        } 
                        {actionPermissions?.isEdit && !(actionPermissions?.isApprover || actionPermissions?.isCloser) && activeTabId == CAMPAIGN_TABS.region.tabId && Validate().isNotEmpty(REGION_STORE_EXCEL) &&
                            <TemplateDropDown templates={REGION_STORE_EXCEL} {...props}/>
                        } 
                        {!(actionPermissions?.isApprover || actionPermissions?.isCloser) && activeTabId == CAMPAIGN_TABS.uploads.tabId && Validate().isNotEmpty(CAMPAIGN_TEMPLATES) &&
                            <TemplateDropDown templates={CAMPAIGN_TEMPLATES} {...props}/>
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
                        <CampaignInfo setHelpers={setHelpers} setLoading={setLoading} handleCampaignNameChange ={handleCampaignNameChange}  onCampaignTypeChange={onCampaignTypeChange} {...props} trigger = {trigger} campaignInfo={campaign.campaignInfo} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={globalPromotionStatus}
                        applicableTypeRoles={applicableTypeRoles} actionPermissions={actionPermissions} setIsToDateChanged={setIsToDateChanged} showUpdateToDate={showUpdateToDate} setShowUpdateToDate={setShowUpdateToDate} updateToDate={updateToDate}  onApplicableTypeChange={onApplicableTypeChange} tabDetails = {getTabDetails(CAMPAIGN_TABS.campaignInfo, campaignType=='11' ? CAMPAIGN_TABS.metaInfo : CAMPAIGN_TABS.couponInfo,'')} promotionType = {PromotionType.campaign}/>
                    </TabPane>}
                    {activeTabId == "2" && <TabPane className="h-100" tabId={2}>
                        <CouponInfo setHelpers={setHelpers} {...props} setLoading={setLoading}  handleCouponNameChange={handleCouponNameChange} couponInfo={campaign.couponInfo} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={ globalPromotionStatus}
                        actionPermissions={actionPermissions} note={noteItems} disableCouponCode={disableCouponCode} applicableType={campaign.campaignInfo.applicableType} campaignType={campaign.campaignInfo.campaignType} tabDetails = {getTabDetails(CAMPAIGN_TABS.couponInfo, CAMPAIGN_TABS.metaInfo, CAMPAIGN_TABS.campaignInfo)}/>
                    </TabPane>}
                    {activeTabId == "3" && <TabPane className="h-100" tabId={3}>
                        <MetaInfo {...props} setLoading={setLoading}   metaInfo={campaign.metaInfo} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={ globalPromotionStatus} 
                        actionPermissions={actionPermissions}  applicableType={campaign.campaignInfo.applicableType}  tabDetails = {getTabDetails(CAMPAIGN_TABS.metaInfo, CAMPAIGN_TABS.loyalty, campaignType=='11' ? CAMPAIGN_TABS.campaignInfo :  CAMPAIGN_TABS.couponInfo)}/>
                    </TabPane>}
                    {activeTabId == "4" && <TabPane className="h-100" tabId={4}>
                        <LoyaltyTypes {...props} setLoading={setLoading}  loyalty={campaign.loyalty} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={ globalPromotionStatus}
                        actionPermissions={actionPermissions}   tabDetails = {getTabDetails(CAMPAIGN_TABS.loyalty, CAMPAIGN_TABS.region, CAMPAIGN_TABS.metaInfo)}/>
                    </TabPane>}
                    {activeTabId == "5" && <TabPane className="h-100" tabId={5}>
                        <RegionsWrapper 
                         showStoreLevel ={(Validate().isNotEmpty(campaign.campaignInfo?.channel) && campaign.campaignInfo.channel.length == 1 && campaign.campaignInfo.channel[0] == '1') || (campaign.campaignInfo?.applicableType && campaign.campaignInfo?.applicableType==='6')}
                        applicableType = {campaign?.campaignInfo?.applicableType}  isMultipleCampaignsAllowed={actionPermissions.isCreate && getCouponDiscountType(campaign.couponInfo) == '1'} isMultipleCampaigns={isMultipleCampaigns} setIsMultipleCampaignsInfo = {setIsMultipleCampaignsInfo}
                         showNextPrev={true} showDataInGrid={true} handleRegionsChange ={handleRegionsChange}  {...props} setLoading={setLoading}  regions={campaign.regions} channel={campaign.campaignInfo?.channel} handleCampaignInfoChange={handleCampaignInfoChange} globalPromotionStatus={globalPromotionStatus} 
                        actionPermissions={actionPermissions}  isUpdateBtnRequired={true}  isNotEligibleRegionsRequired={true} isToDateChanged={isToDateChanged} tabDetails = {getTabDetails(CAMPAIGN_TABS.region, CAMPAIGN_TABS.uploads, CAMPAIGN_TABS.loyalty)}/>
                    </TabPane>}
                    {activeTabId == "6" && <TabPane className="h-100" tabId={6}>
                        <ItemsUpload {...props} setLoading={setLoading} uploads={campaign.uploads}  globalPromotionStatus={globalPromotionStatus} customerFlag={campaign.campaignInfo.allCustomers}
                            handleCampaignInfoChange={handleCampaignInfoChange} setProvidedExcelData={setProvidedExcelData} campaignName={campaign.campaignInfo?.campaignName} isMultipleCampaigns={isMultipleCampaigns} setSplitBy={setSplitBy}
                            actionPermissions={actionPermissions} couponType={getCouponDiscountType(campaign.couponInfo)} existingCouponType={existingCouponType} existingCouponBased={existingCouponBased} uploadsNote={uploadsNote} tabDetails = {getTabDetails(CAMPAIGN_TABS.uploads, '', CAMPAIGN_TABS.region)}/>
                    </TabPane>}
                </BodyComponent>
                {/* <FooterComponent ref={footerRef} className="footer px-3 py-2 d-flex flex-row-reverse justify-content-between">
                    <NextPrevBtn {...props} hidePrevBtn={activeTabId == 1 ? true : false } handleNextBtnClick={handleNextBtnClick} nextTab={headerTabs[activeTabId]}/>
                    </FooterComponent> */}
            </Wrapper>
        </React.Fragment>
    )
}

export default CreateOldCampaign;