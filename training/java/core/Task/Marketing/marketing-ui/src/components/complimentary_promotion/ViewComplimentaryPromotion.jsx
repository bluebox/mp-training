import { ALERT_TYPE, CustomSpinners, ViewRecords } from "@medplus/react-common-components/DynamicForm";
import dateFormat from 'dateformat';
import React, { useContext, useEffect, useRef, useState } from "react";
import { Button } from "react-bootstrap";
import { COMPLIMENTARY_ROLES } from "../../constants/MarketingRoles";
import { PromotionStatus, PromotionType } from "../../constants/PromotionConstants";
import { LIST_TABS } from "../../constants/TabConstants";
import { COMPLIMENTARY_EXCEL_DATA, STORE_EXCEL_DATA } from "../../constants/TemplateConstants";
import { COMPLIMENTARY_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import ComplimentaryService from "../../services/ComplimentaryService";
import MarketingService from "../../services/MarketingService";
import { downloadExcelData, getStatusSpanInfo } from "../../services/ServiceConstants";
import { AlertContext } from "../Contexts/UserContext";
import RejectModal from "../RejectModal";
import { BodyComponent, FooterComponent, HeaderComponent, Wrapper } from '../common/CommonStructure';
import TemplateDropDown from "../common/TemplateDropDown";
import ViewMetaInfo from "../common/ViewMetaInfo";

let excelData = COMPLIMENTARY_EXCEL_DATA;
let COMPLIMENTARY_TEMPLATES = []
const ViewComplimentaryPromotion = (props) => {
 
    const [complimentaryPromotion, setComplimentaryPromotion] = useState({});
    const {setAlertContent, setToastContent} = useContext(AlertContext)
    const [loading, setLoading] = useState(false);
	const [newLoyalityTypes, setNewLoyalityTypes] = useState();
	const [loyalties, setLoyalties] = useState([]);
    const [btnLoading, setBtnLoading] = useState(false);
	const [regions, setRegions] = useState([]);
    const [metaData, setMetaData] = useState({});
	const [slab, setSlab] = useState([]);
    const [complimentaryId, setComplimentaryId] = useState(undefined);
	const [status, setStatus] = useState(undefined);
    const [rejectModalInfo , setRejectModalInfo] = useState({
        showRejectModal: false,
    })
    const [toDate, setToDate] = useState(null);
    const [applicableType, setApplicableType] = useState(undefined);
    const headerRef = useRef(undefined);
    const footerRef = useRef(undefined);

	useEffect(()=>{
        const complimentaryId = (Validate().isNotEmpty(props.location.state) && Validate().isNotEmpty(props.location.state.complimentaryId)) ? props.location.state.complimentaryId : undefined;
        setComplimentaryId(complimentaryId);
        setLoading(true);
        if(Validate().isNotEmpty(complimentaryId)) {
			getLoyaltyTypes();
            getcomplimentaryPromotionByCampaignId(complimentaryId);
        } else {
            if(props.match.path !== COMPLIMENTARY_URLS.createCampaign) {
                props.history.push(COMPLIMENTARY_URLS.createCampaign)
            }
        }
        setLoading(false);
    },[])
    
	const getLoyaltyTypes = async () => {
		let loyaltiesObj = {};
		console.log('getting loyalties from server');
		const response =  await MarketingService().getLoyaltyTypes().catch(error => {
	    	setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to fetch loyalties'});
	    });
	    loyaltiesObj=response.responseData;
    	setNewLoyalityTypes(loyaltiesObj);
	}
	
	const getcomplimentaryPromotionByCampaignId = async (complimentaryId) => {
        const data = await ComplimentaryService().getPromotionByComplimentaryId({'complimentaryId': complimentaryId}).catch(error=>{
            setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:error})
            return false;
        });
        if(Validate().isNotEmpty(data) && data.statusCode==="SUCCESS" && Validate().isNotEmpty(data.responseData)) {
            setFinalObject(data.responseData);
        } else {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:`${data.message}`, handleCallBack: failCallBackHandler, delayTime: 3000})
        }
    }
    
    const failCallBackHandler = () => {
        props.history.push(COMPLIMENTARY_URLS.listCamapign)
    }
	
	const setFinalObject = (data) => {
		const finalObj = data;
        finalObj['name'] = data['name'];
		setStatus(data['status']);
        setApplicableType(data['applicableType']);
		if(data['applicableType']===5){
			finalObj['applicableType'] = "Pharmacy";
		} 
        setExcel(!data['allCustomers'], data.complimentarySlab.complimentarySlabId, data['channels'])
		finalObj['allCustomers']= data['allCustomers'] ? "Yes" : "No";
		finalObj['fromDate'] = dateFormat(data['fromDate'], "d mmm, yyyy hh:MM TT");
        setToDate(new Date(data['toDate']).getTime());
        finalObj['toDate'] = dateFormat(data['toDate'], "d mmm, yyyy hh:MM TT");
		finalObj['effectiveDate']= data['effectiveDate'] ? dateFormat(data['effectiveDate'], "d mmm, yyyy hh:MM TT") : finalObj['fromDate']
		let channels = [];
		data['channels'].forEach(element => {
			if(element===2){
				channels.push(" WEB");
			} else if(element===1){     
				channels.push(" POS");
			} else if(element===3){
				channels.push(" CRM");
			} else if(element===4){
				channels.push(" Mobile");
			} else if(element===5){
				channels.push(" POS WEB");
			}
		});
		finalObj['channels']=channels.toLocaleString();
        finalObj['messageDisplayPercentage'] = data['messageDisplayPercentage'];
		setLoyalties(finalObj['loyalties']); 
        finalObj['regions'] = data.regions
        setRegions(data.regions)
        setMetaData(data.userMetaData);
        finalObj['complimentarySlab'] = data.complimentarySlab
        setSlab(data.complimentarySlab);
        excelData.products.url="";
        excelData.referenceCustomers.url="";
        STORE_EXCEL_DATA.complimentary.url="";
        setComplimentaryPromotion(finalObj);
	}

    const setExcel = (showCustomers, complimentarySlabId, channels) => {
           COMPLIMENTARY_TEMPLATES = [{header: 'Downloads', data: []}, {header: 'Templates',data: []}];
           pushExcelData(0, excelData.products.title, excelData.products, {'complimentaryId': props?.location?.state?.complimentaryId, 'complimentarySlabId' : complimentarySlabId});
           let params = {'complimentaryId': props?.location?.state?.complimentaryId};
           let title = excelData.referenceCustomers.title;
           if(showCustomers)
                title += ' and Customers'
           pushExcelData(0, title, excelData.referenceCustomers, params);
           if(channels?.length == 1 && channels[0] === 1) {
              pushStoreExcelData(0, STORE_EXCEL_DATA.complimentary.title, STORE_EXCEL_DATA.complimentary)
           }
    }
   
    const pushExcelData = (index, title, excelDataItem, params) => {
            COMPLIMENTARY_TEMPLATES[index].data.push({title: title , onClick: () => 
                downloadExcelData(excelDataItem, params, props?.location?.state?.complimentaryId, setAlertContent, ALERT_TYPE, setLoading)});
    }

    const pushStoreExcelData = (index, title, excelDataItem) => {
        COMPLIMENTARY_TEMPLATES[index].data.push({title: title , onClick: () => 
            downloadExcelData(excelDataItem,{'campaignId': props?.location?.state?.complimentaryId}, props?.location?.state?.complimentaryId, setAlertContent, ALERT_TYPE, setLoading)});
    }

    const approveCampaign = async () => {
        if(toDate < Date.now()) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"ToDate cannot be before current time"});
            return;
        }
        let formData = new FormData();
        formData.append('complimentaryId', complimentaryId);
        formData.append('toDate', toDate);
        setBtnLoading(true);
        const response = await ComplimentaryService().approvePromotion(formData).catch(error => {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
        })
        return ResponseHandler(setAlertContent).handleResponse(response,{'successAlert': true},(data) => {
            successCallBackHandler("Approved");
            setBtnLoading(false);
        }, (error) => {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:error});
            setBtnLoading(false);
        });
    }

    const rejectCampaign = () => {
        setRejectModalInfo({
            showRejectModal: true,
        });
    }

    const successCallBackHandler = (note = "Rejected") => {
        setToastContent({toastMessage: `Promotion ${complimentaryPromotion.name} [${complimentaryId}] ${note} successfully....`, className:'w-100'});
        props.history.push({
            pathname: COMPLIMENTARY_URLS.listCamapign,
            campaignId: complimentaryId,
            isSuccessOperation : true
        });
    }

	
    
    return (
        <div>
            <Wrapper>
                <HeaderComponent ref={headerRef} className="align-items-center border-bottom d-flex p-12">
                    <Button variant=" " className="btn-link icon-hover me-3" onClick={() => { props.history.push({ pathname: COMPLIMENTARY_URLS.listCamapign, campaignId : complimentaryId}) }}> <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                        <g id="leftarrow_black_icon_18px" transform="translate(-48.941 -316.765)">
                            <rect id="BG_Guide" data-name="BG Guide" width="18" height="18" transform="translate(48.941 316.765)" fill="none" />
                            <path id="Path_22927" data-name="Path 22927" d="M56.566,319.236a.686.686,0,0,0-.448.178l-6.977,6.53a.7.7,0,0,0,0,.984l6.977,6.44a.709.709,0,0,0,.984-.089.7.7,0,0,0,0-.984l-5.546-5.188H66.226a.716.716,0,0,0,0-1.431H51.557l5.635-5.188a.635.635,0,0,0,.269-.536,1.275,1.275,0,0,0-.179-.537A4.04,4.04,0,0,0,56.566,319.236Z" transform="translate(0 -0.471)" fill="#080808" />
                        </g>
                    </svg></Button>
                    <p className='mb-0'>Promotion ID - <span className="fw-bold">{complimentaryId}</span></p>
                    <div className="text-white ms-3" title="Status"> {getStatusSpanInfo(status)} </div>
                    <TemplateDropDown templates={COMPLIMENTARY_TEMPLATES} {...props}/>
                </HeaderComponent>
                <BodyComponent className="body-height" allRefs={{ "headerRef":headerRef ,"footerRef":   footerRef}}>
                    <div>
                        <div className='row mb-4 g-3'>
                            <label className='custom-fieldset'>Promotion Info</label>

                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Promotion Name</label>
                                <h6 className='mb-0 font-14'>{complimentaryPromotion['name']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Applicable Type</label>
                                <h6 className='mb-0 font-14'>{complimentaryPromotion['applicableType']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Channel</label>
                                <h6 className='mb-0 font-14'>{complimentaryPromotion['channels']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>All Customers</label>
                                <h6 className='mb-0 font-14'>{complimentaryPromotion['allCustomers']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>From Date</label>
                                <h6 className='mb-0 font-14'>{complimentaryPromotion['fromDate']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>To Date</label>
                                <h6 className='mb-0 font-14'>{complimentaryPromotion['toDate']}</h6>
                            </div>
							{status === PromotionStatus.active ? 
							<div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Effective Date</label>
                                <h6 className='mb-0 font-14'>{complimentaryPromotion['effectiveDate']}</h6>
                            </div> : ""}
							{status === PromotionStatus.rejected ? 
							<div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Remarks</label>
                                <h6 className='mb-0 font-14'>{metaData['remarks'] ? metaData['remarks'] : " - "}</h6>
                            </div> : ""}
                        </div>
                    </div>
                    <div>
                    </div>
                    
                    <div className='row mb-4'>
                        <label className='custom-fieldset mb-12'>Meta Info</label>
                        {ViewMetaInfo(metaData)}
                    </div>

                    <div>
                        <div className='row mb-4'>
                            <label className='custom-fieldset mb-12'>Loyalty Types</label>
                            <div className="d-flex gap-3 flex-wrap">
                            {newLoyalityTypes && loyalties && loyalties.map((value, index) => {
								return (
									
										<div key={index}>
			                                <Button
			                                variant="light" className={`${props.className}  align-items-center btn btn-light btn-sm d-flex rounded-5 mb-2`}>
			                                    <span>
			                                        {newLoyalityTypes[value]}
			                                    </span>
			                                </Button>
			                            </div>
								)
							})}
                            </div>
                        </div>
                    </div>  
                    <div className='row mb-3'>
                            <label className='custom-fieldset mb-12'>Invoice Slab</label>
                           <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Name</label>
                                <h6 className='mb-0 font-14'>{slab.name}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Min Invoice Amount</label>
                                <h6 className='mb-0 font-14'>{slab.invoiceAmount}</h6>
                            </div> 
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Message Display Percentage</label>
                                <h6 className='mb-0 font-14'>{complimentaryPromotion['messageDisplayPercentage']}</h6>
                            </div> 
                    </div>
                    <div className='row'>
                        <ViewRecords info={regions} props={props} displayText={"Regions"} />
                    </div>
                </BodyComponent>

                <FooterComponent ref={footerRef} >
                {Validate().isApproverForApplicableType(applicableType?.toString(), COMPLIMENTARY_ROLES) && status == PromotionStatus.inActive &&
                    <div className="d-flex footer align-items-center justify-content-end gap-3 p-2">
                    {rejectModalInfo.showRejectModal && <RejectModal show={rejectModalInfo.showRejectModal} setRejectModalInfo={setRejectModalInfo} promotionType={PromotionType.complimentary} successCallBackHandler ={successCallBackHandler}
                            campaignName = {complimentaryPromotion?.name} campaignId = {complimentaryId} rejectService={LIST_TABS.complimentary.rejectService} {...props}/> }
                        <Button variant=" " className="btn px-3 brand-secondary shadow-sm rounded" onClick={rejectCampaign} >
                            {"Reject"}
                        </Button>
                        <Button variant=" " className="btn px-3 btn-success shadow-sm rounded align-right" onClick={approveCampaign} disabled={btnLoading}>
                            {!btnLoading && <> Approve </>}
                            {btnLoading && (<div className="d-flex justify-content-center align-items-center" style={{ width: "100%" }}>
                                <CustomSpinners spinnerText={"Approve"} className={" spinner-position"} innerClass={"invisible"} /></div>)}
                        </Button>
                    </div>}   
                </FooterComponent>
            </Wrapper>

        </div>
    )
}
export default ViewComplimentaryPromotion