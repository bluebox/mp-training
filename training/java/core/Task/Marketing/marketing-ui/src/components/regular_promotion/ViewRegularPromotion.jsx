import CommonDataGrid from "@medplus/react-common-components/DataGrid";
import { ALERT_TYPE, CustomSpinners, StackedImages, ViewRecords } from "@medplus/react-common-components/DynamicForm";
import dateFormat from 'dateformat';
import React, { useContext, useEffect, useRef, useState } from "react";
import { Button } from "react-bootstrap";
import { RP_ROLES } from "../../constants/MarketingRoles";
import { PromotionStatus, PromotionType } from "../../constants/PromotionConstants";
import { LIST_TABS } from "../../constants/TabConstants";
import { RP_EXCEL_DATA, STORE_EXCEL_DATA } from "../../constants/TemplateConstants";
import { MIC_URLS, RP_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import MarketingService from "../../services/MarketingService";
import { downloadExcelData, getStatusSpanInfo } from '../../services/ServiceConstants';
import { AlertContext } from "../Contexts/UserContext";
import RejectModal from "../RejectModal";
import { BodyComponent, FooterComponent, HeaderComponent, Wrapper } from '../common/CommonStructure';
import DataGridHelper from "../common/DataGridHelper";
import TemplateDropDown from "../common/TemplateDropDown";

let categoryDetail=[]
let slabInfo = {}
let newCategoryTypes = {}
let newLoyalityTypes = {}
let excelData = RP_EXCEL_DATA;
let RP_TEMPLATES = []
const ViewRegularPromotion = (props) => {
	
    const [regularPromotion, setRegularPromotion] = useState({
    });
    const {setAlertContent, setToastContent} = useContext(AlertContext)
    const [loading, setLoading] = useState(false);
    const [btnLoading, setBtnLoading] = useState(false);
	const [loyalties, setLoyalties] = useState([]);
    const [categories, setCategories] = useState([]);

	const [regions, setRegions] = useState([]);
	const [slabs, setSlabs] = useState([]);
	const [metaData, setMetaData] = useState({});
    const [customerDownloadUrl,setCustomerDownloadUrl] = useState(undefined);
    const [disableCustomerDownloadBtn, setDisableCustomerDownloadBtn] = useState(false);
    const [storeLevel, setStoreLevel] = useState(false);
    const [images, setImages] = useState([]);
    const [promotionId, setPromotionId] = useState(undefined);
    const [dataGridStructure,setDataGridStructure] = useState(DataGridHelper().regularPromotionDetails());

    const [dataGridData, setDataGridData] = useState([]);
	const [status, setStatus] = useState(undefined);
    const [rejectModalInfo , setRejectModalInfo] = useState({
        showRejectModal: false,
    })
    const [toDate, setToDate] = useState(null);
    const [applicableType, setApplicableType] = useState(undefined);
    const headerRef = useRef(undefined);
    const footerRef = useRef(undefined);
	
	useEffect(()=>{
        const promotionId = (Validate().isNotEmpty(props.location.state) && Validate().isNotEmpty(props.location.state.promotionId)) ? props.location.state.promotionId : undefined;
        setPromotionId(promotionId);
        setLoading(true);
        if(Validate().isNotEmpty(promotionId)) {
			getLoyaltyTypes();
            getCategoryTypes();
            getRegularPromotionByPromotionId(promotionId);
	        setDataGrid()
        } else {
            if(props.match.path !== RP_URLS.createCampaign) {
                props.history.push(RP_URLS.createCampaign)
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
        newLoyalityTypes=loyaltiesObj;
	}

    const getCategoryTypes = async () => {
        let categoriesObj = {};
		console.log('getting categories from server');
		const response =  await MarketingService().getInvoiceCategoryTypes().catch(error => {
	    	setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to fetch loyalties'});
	    });
	    categoriesObj=response.responseData;
        newCategoryTypes=categoriesObj;
    }

    const getRegularPromotionSlabs = async (slabGrpId) => {
        console.log('getting slabs from server');
        const response = await MarketingService().getSlabGroupDetails({ 'slabGroupId': slabGrpId }).catch(error => {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to fetch Slab group details'});
        });
        ResponseHandler(setAlertContent).handleResponse(response, {}, (data) => {
            let slabs =[]
            data.slabGroupDetails.forEach(slab => {
                slabInfo[slab.slabId]={"fromValue": slab.fromValue, "toValue": slab.toValue};
                slabs.push(slab.fromValue+ ' - ' + slab.toValue);
            })
            setSlabs(slabs);
        }, (error) => {
          setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: error})
        });
    }
	
    const getRegularPromotionByPromotionId = async (promotionId) => {
        const data = await MarketingService().getRegularPromotionByPromotionId({'promotionId': promotionId}).catch(error=>{
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
        props.history.push(MIC_URLS.listCamapign)
    }
	
	const setFinalObject = (data) => {
		const finalObj = data;
        finalObj['promotionName'] = data.promotionName;
        finalObj['promotionLevel'] = data.promotionLevel;
        setStoreLevel((data['channels']?.length == 1 && data['channels'][0] === 1));
        if(data['promotionLevel']=== 'C'){
			finalObj['promotionLevel'] = "Category";
		} else if(data['promotionLevel']=== 'I'){
			finalObj['promotionLevel'] = "Invoice";
        }
		setStatus(data['status']);
        setApplicableType(data['applicableType']);
		if(data['applicableType']===5){
			finalObj['applicableType'] = "Pharmacy";
		} else if(data['applicableType']===6){
			finalObj['applicableType'] = "Pathlabs";
		} else if(data['applicableType']===7){
			finalObj['applicableType'] = "Lens";
		}
        setDisableCustomerDownloadBtn(!data['allCustomers']);
		finalObj['allCustomers']= data['allCustomers'] ? "Yes" : "No";
        finalObj['fromDate'] = dateFormat(data['fromDate'], "d mmm, yyyy hh:MM TT");
		if(data['toDate']) {
            setToDate(new Date(data['toDate'])?.getTime());
            finalObj['toDate']= dateFormat(data['toDate'], "d mmm, yyyy hh:MM TT");
        } else {
            finalObj['toDate']='-'
        }
		let channels = [];
		data['channels'].forEach(element => {
			if(element===2){
				channels.push("WEB");
			} else if(element===1){
				channels.push("POS");
			} else if(element===3){
				channels.push("CRM");
			} else if(element===4){
				channels.push("Mobile");
			} else if(element===5){
				channels.push("POS WEB");
			}
		});
		finalObj['channels']=channels.toLocaleString();
		setLoyalties(finalObj['loyalty']); 
		setCategories(finalObj['productCategoryIds']);

        finalObj['couponBased']= data['couponBased'] ? 'Yes' : 'No';
        finalObj['promotionCoupon'] = data.promotionCoupon
        // finalObj['prescription'] = couponInfo['prescription'].toLocaleString();
		setMetaData(data.userMetaData);
		if(Validate().isNotEmpty(data.userMetaData.imageServerName) || Validate().isNotEmpty(data.userMetaData.imagePath)) {
                setImages({
                    "imagePath": `${data.userMetaData.imageServerName}/${data.userMetaData.imagePath}`,
                    "thumbnailPath": `${data.userMetaData.imageServerName}/${data.userMetaData.imagePath}`,
                    "altText": "Promotion Banner",
                });
        }
        // finalObj['existingSlabGroups'] = data.regularPromotionSlabs.existingSlabGroups
        categoryDetail= data.categoryDetail
        finalObj['regions'] = data.regions
        setRegions(data.regions)
        setRegularPromotion(finalObj)
        excelData.customers.url="";
        STORE_EXCEL_DATA.regular.url="";
        getRegularPromotionSlabs(data['slabGroupId']);
	}
	
    useEffect(() => {
        let details = []
        let index=0;
        if(Validate().isNotEmpty(newCategoryTypes) && Validate().isNotEmpty(newLoyalityTypes)) {
            categoryDetail.forEach(detail => {
                details.push({
                    rowIndex : index++,
                    productCategory: newCategoryTypes[detail.productDiscountCategoryId],
                    loyaltyType: newLoyalityTypes[detail.loyaltyId],
                    discountType: "SlabBasedDiscount",
                    region: detail.region,
                    fromValue: slabInfo[detail.slabId]?.fromValue,
                    toValue: slabInfo[detail.slabId]?.toValue,
                    discountPercentage: Validate().isNotEmpty(detail.discountPercentage) && parseFloat(detail.discountPercentage).toFixed(2),
                    paybackPercentage: Validate().isNotEmpty(detail.paybackPercentage) && parseFloat(detail.paybackPercentage).toFixed(2)
                });
            });
        }
        if(Validate().isNotEmpty(details)){
           details = sortDetails(details);
            setDataGridData(details);
        }
    }, [slabs])
	const sortDetails = (details) => {
        const keys = ['productCategory', 'loyaltyType', 'region', 'fromValue'];
        return details.sort((a, b) => {
            for (let key of keys) {
              if (a[key] < b[key]) return -1;
              if (a[key] > b[key]) return 1;
            }
            return 0;
          });
    }

    useEffect(() => {
        RP_TEMPLATES = [{header: 'Downloads', data: []}, {header: 'Templates',data: []}];
        if(storeLevel) {
            pushStoreExcelData(0, STORE_EXCEL_DATA.regular.title, STORE_EXCEL_DATA.regular)
        }
        if(disableCustomerDownloadBtn) {
            pushExcelData(0, excelData.customers.title, excelData.customers);
        }
    }, [disableCustomerDownloadBtn, regularPromotion.promotionCoupon?.couponCode, storeLevel])

    const pushExcelData = (index, title, excelDataItem) => {
        const params = {'promotionId': props?.location?.state?.promotionId, 'couponCode': regularPromotion.promotionCoupon?.couponCode}
        RP_TEMPLATES[index].data.push({title: title , onClick: () => 
            downloadExcelData(excelDataItem, params,  props?.location?.state?.promotionId, setAlertContent, ALERT_TYPE, setLoading)});
    }

    const pushStoreExcelData = (index, title, excelDataItem) => {
        RP_TEMPLATES[index].data.push({title: title , onClick: () => 
            downloadExcelData(excelDataItem,{'campaignId': props?.location?.state?.promotionId}, props?.location?.state?.promotionId, setAlertContent, ALERT_TYPE, setLoading)});
    }

	const setDataGrid =  () => {
        setDataGridStructure({...dataGridStructure, columns: [...dataGridStructure.columns.map(column => {
            if(column.rowDataKey === "action") {
                return {...column , isVisible: false}
            }
            return column;
        })] 
    })
    }
	
	const callBackMap = () => {
		console.log("error");
	};

    const approveCampaign = async () => {
        if(toDate && toDate < Date.now()) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"ToDate cannot be before current time"});
            return;
        }
        let formData = new FormData();
        formData.append('promotionId', promotionId);
        formData.append('toDate', toDate ? toDate : null);

        setBtnLoading(true);
        const response = await MarketingService().approveRegularPromotion(formData).catch(error => {
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
        setToastContent({toastMessage: `Promotion ${regularPromotion.promotionName} [${promotionId}] ${note} successfully....`, className:'w-100'});
        props.history.push({
            pathname: RP_URLS.listCamapign,
            campaignId: promotionId,
            isSuccessOperation : true
        });
    }
	
    return (
        <div>
            <Wrapper>
            <HeaderComponent ref={headerRef} className="align-items-center border-bottom d-flex p-12">
                <Button variant=" " className="btn-link icon-hover me-3" onClick={() => { props.history.push({ pathname: RP_URLS.listCamapign, campaignId: promotionId }) }}> <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                    <g id="leftarrow_black_icon_18px" transform="translate(-48.941 -316.765)">
                        <rect id="BG_Guide" data-name="BG Guide" width="18" height="18" transform="translate(48.941 316.765)" fill="none" />
                        <path id="Path_22927" data-name="Path 22927" d="M56.566,319.236a.686.686,0,0,0-.448.178l-6.977,6.53a.7.7,0,0,0,0,.984l6.977,6.44a.709.709,0,0,0,.984-.089.7.7,0,0,0,0-.984l-5.546-5.188H66.226a.716.716,0,0,0,0-1.431H51.557l5.635-5.188a.635.635,0,0,0,.269-.536,1.275,1.275,0,0,0-.179-.537A4.04,4.04,0,0,0,56.566,319.236Z" transform="translate(0 -0.471)" fill="#080808" />
                    </g>
                </svg></Button>
                <p className='mb-0'>Promotion ID - <span className="fw-bold">{promotionId}</span></p>
                <div className="text-white ms-3" title="Status">{getStatusSpanInfo(status)}</div>
                {(disableCustomerDownloadBtn || storeLevel) && <TemplateDropDown templates={RP_TEMPLATES} {...props}/>}
            </HeaderComponent>
                <BodyComponent className="body-height" allRefs={{ "headerRef":headerRef ,"footerRef":   footerRef}}>
                    <div>
                        <div className='row mb-4 g-3'>
                            <label className='custom-fieldset'>Regular Promotion Info</label>

                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Promotion Name</label>
                                <h6 className='mb-0 font-14'>{regularPromotion['promotionName']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Applicable Type</label>
                                <h6 className='mb-0 font-14'>{regularPromotion['applicableType']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Promotion Level</label>
                                <h6 className='mb-0 font-14'>{regularPromotion['promotionLevel']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Channel</label>
                                <h6 className='mb-0 font-14'>{regularPromotion['channels']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>All Customers</label>
                                <h6 className='mb-0 font-14'>{regularPromotion['allCustomers']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>From Date</label>
                                <h6 className='mb-0 font-14'>{regularPromotion['fromDate']}</h6>
                            </div>

                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>To Date</label>
                                <h6 className='mb-0 font-14'>{regularPromotion['toDate']}</h6>
                            </div>

                            {status === PromotionStatus.active ? 
							<div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Effective Date</label>
                                <h6 className='mb-0 font-14'>{regularPromotion['effectiveDate']}</h6>
                            </div> : ""}

							{status === PromotionStatus.rejected ? 
							<div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Remarks</label>
                                <h6 className='mb-0 font-14'>{metaData['remarks'] ? metaData['remarks'] : " - "}</h6>
                            </div> : ""}
                        </div>
                    </div>
               
                    <div>
                    {regularPromotion['couponBased'] === 'Yes' &&
                        <div className='row mb-4'>
                            <label className='custom-fieldset mb-12'>Coupon Info</label>
                                <div className="row">
                                    <div className='col-3'>
                                        <label className='font-12 text-secondary mb-1'>Coupon Code</label>
                                        <h6 className='mb-0 font-14'>{regularPromotion.promotionCoupon['couponCode']}</h6>
                                    </div>
                                    <div className='col-3 mb-3'>
                                        <label className='font-12 text-secondary mb-1'>Num Of Days</label>
                                        <h6 className='mb-0 font-14'>{regularPromotion.promotionCoupon['noOfDays']}</h6>
                                    </div>
                                    <div className='col-3'>
                                        <label className='font-12 text-secondary mb-1'>Total Limit</label>
                                        <h6 className='mb-0 font-14'>{regularPromotion.promotionCoupon['totalLimit']}</h6>
                                    </div>
                                    <div className='col-3'>
                                        <label className='font-12 text-secondary mb-1'>Customer Limit</label>
                                        <h6 className='mb-0 font-14'>{regularPromotion.promotionCoupon['customerLimit']}</h6>
                                    </div>
                                    {Validate().isNotEmpty(regularPromotion.promotionCoupon['prescription']) &&
                                        <div className='col-3 mb-3'>
                                            <label className='font-12 text-secondary mb-1'>Prescription</label>
                                            <h6 className='mb-0 font-14'>{regularPromotion.promotionCoupon['prescription'].map(value => value.toUpperCase()=== 'Y' ? 'Yes' : 'No')?.join(', ')}</h6>
                                        </div>
                                    }
                                </div>
                            
                        {regularPromotion.promotionCoupon['addOnCoupon'] &&
                        <div className='row'>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1 font-weight-bold'>Add on Coupon</label>
                                {/* <h6 className='mb-0 font-14'>{regularPromotion['addOnCoupon']}</h6> */}
                            </div>
                                <div className='row m-auto'>
                                    <div className='col-3'>
                                        <label className='font-12 text-secondary mb-1'>Min Invoice Value</label>
                                        <h6 className='mb-0 font-14'>{regularPromotion.promotionCoupon['minValue']}</h6>
                                    </div>

                                    <div className='col-3'>
                                        <label className='font-12 text-secondary mb-1'>Max Discount</label>
                                        <h6 className='mb-0 font-14'>{regularPromotion.promotionCoupon['maxDiscount']}</h6>
                                    </div>
                                    <div className='col-3'>
                                        <label className='font-12 text-secondary mb-1'>Max Payback Points</label>
                                        <h6 className='mb-0 font-14'>{regularPromotion.promotionCoupon['maxPoints']}</h6>
                                    </div>
                                </div>
                                
                        </div> 
                        }
                    </div>
                    } 
                    </div>

                    <div>
                        <div className='row mb-4 g-3'>
                            <label className='custom-fieldset'>Meta Info</label>
                            <div className='col-9'>
                                <label className='font-12 text-secondary mb-1'>Description</label>
                                <h6 className='mb-0 font-14'>{metaData['longDescription']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Promotion Visible</label>
                                <h6>{metaData['promotionVisible']===true ? "Yes" : "No"}</h6>
                            </div>
                            {Validate().isNotEmpty(images.imagePath) ? <div className='d-inline-block'>
                                {  <StackedImages images={[images]} includeLightBox maxImages="1"/>}
                           	</div> : null}
                        </div>
                    </div>
                    
                    <div className='mb-3'>
                        <div className='my-3 '>
                            <label className='custom-fieldset mb-12'>Slabs</label>
                            <div className="d-flex gap-3 flex-wrap" style={{ maxHeight: '160px', overflowY: 'auto' }}>
                            {slabs && slabs.map((slab, index) => {
								return (
										<div key={index}>
			                                <Button variant="light" className={`${props.className}  align-items-center btn btn-light btn-sm d-flex rounded-5 mb-2`}>
			                                    <span>{slab}</span>
			                                </Button>
			                            </div>
								)
							})}
                            </div>
                        </div>
                    </div>
                    <div className='mb-3'>
                        <div className='my-3 '>
                            <label className='custom-fieldset mb-12'>Product Category Types</label>
                            <div className="d-flex gap-3 flex-wrap">
                            {newCategoryTypes && categories && categories.map((value, index) => {
								return (
										<div key={index}>
			                                <Button variant="light" className={`${props.className}  align-items-center btn btn-light btn-sm d-flex rounded-5 mb-2`}>
			                                    <span>{newCategoryTypes[value]}</span>
			                                </Button>
			                            </div>
								)
							})}
                        </div>
                    </div>
                    <div className='mb-3'>
                        <div className='my-3 '>
                            <label className='custom-fieldset mb-12'>Loyalty Types</label>
                            <div className="d-flex gap-3 flex-wrap">
                            {newLoyalityTypes && loyalties && loyalties.map((value, index) => {
								return (
										<div key={index}>
			                                <Button variant="light" className={`${props.className}  align-items-center btn btn-light btn-sm d-flex rounded-5 mb-2`}>
			                                    <span>{newLoyalityTypes[value]}</span>
			                                </Button>
			                            </div>
								)
							})}
                            </div>
                        </div>
                    </div>
                    <div className='mb-3'>
                        <ViewRecords info={regions} props={props} displayText={"Regions"}/>
                    </div>
                    </div>
                    <div className='h-50'>
                    <label className='custom-fieldset mb-1'>Details</label>
                        <div className="h-75 overflow-auto card">
                            <div className="h-100">
                            {Validate().isNotEmpty(dataGridData) && <CommonDataGrid {...dataGridStructure} 
                            dataSet={dataGridData} 
                            callBackMap={callBackMap}
                            />}
                            </div>
                        </div>
                    </div>   
                </BodyComponent>
                <FooterComponent ref={footerRef} >
                {Validate().isApproverForApplicableType(applicableType?.toString(), RP_ROLES) && status == PromotionStatus.inActive &&
                    <div className="d-flex footer align-items-center justify-content-end gap-3 p-2">
                        {rejectModalInfo.showRejectModal && <RejectModal show={rejectModalInfo.showRejectModal} setRejectModalInfo={setRejectModalInfo} promotionType={PromotionType.regular} successCallBackHandler ={successCallBackHandler}
                            campaignName = {regularPromotion?.promotionName} campaignId = {promotionId} rejectService={LIST_TABS.regular.rejectService} {...props}/> }
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

export default ViewRegularPromotion