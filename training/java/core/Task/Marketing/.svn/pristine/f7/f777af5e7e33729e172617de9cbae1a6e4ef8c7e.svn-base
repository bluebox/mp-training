import CommonDataGrid, { SelectFilter } from "@medplus/react-common-components/DataGrid";
import { ALERT_TYPE, CustomSpinners, StackedImages } from "@medplus/react-common-components/DynamicForm";
import dateFormat from 'dateformat';
import React, { useContext, useEffect, useRef, useState } from "react";
import { Button } from "react-bootstrap";
import { MIC_ROLES } from "../../constants/MarketingRoles";
import { PromotionStatus, PromotionType } from "../../constants/PromotionConstants";
import { LIST_TABS } from "../../constants/TabConstants";
import { MIC_EXCEL_DATA, STORE_EXCEL_DATA } from "../../constants/TemplateConstants";
import { MIC_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import MarketingService from "../../services/MarketingService";
import { downloadExcelData, getRegionsName, getStatusSpanInfo, regionFilterMapWithOutStores, regionFilterMapWithStores } from "../../services/ServiceConstants";
import { AlertContext } from "../Contexts/UserContext";
import RejectModal from "../RejectModal";
import { BodyComponent, FooterComponent, HeaderComponent, Wrapper } from '../common/CommonStructure';
import TemplateDropDown from "../common/TemplateDropDown";
import CurrencyFormatter from "../util/CurrencyFormatter";

let excelData = MIC_EXCEL_DATA;
let MIC_TEMPLATES = []
const ViewCampaign = (props) => {
	
    const [multiItemCampaign, setMultiItemCampaign] = useState({});
    const {setAlertContent, setToastContent} = useContext(AlertContext)
    const [loading, setLoading] = useState(false);
    const [btnLoading, setBtnLoading] = useState(false);
	const [newLoyalityTypes, setNewLoyalityTypes] = useState();
    const [selectedRegionFilter, setSelectedRegionFilter] = useState("")
	const [loyalties, setLoyalties] = useState([]);
	const [regions, setRegions] = useState({});
	const [slabs, setSlabs] = useState([]);
	const [metaData, setMetaData] = useState({});
    const [images, setImages] = useState([]);
    const [campaignId, setCampaignId] = useState(undefined);
    const [dataGridStructure, setDataGridStructure] = useState(undefined);
    const [dataGridData, setDataGridData] = useState([]);
	const [status, setStatus] = useState(undefined);
    const [rejectModalInfo , setRejectModalInfo] = useState({
        showRejectModal: false,
    })
    const [toDate, seToDate] = useState(null);
    const [applicableType, setApplicableType] = useState(undefined);
    const [isPosChannel, setIsPosChannel] = useState(undefined);
    const headerRef = useRef(undefined);
    const footerRef = useRef(undefined);

    const filterRegionsMap = Object.keys(regions)?.filter(region => region.length == 12)?.length == 0 ? regionFilterMapWithOutStores : regionFilterMapWithStores;
	
	useEffect(()=>{
		
        const campaignId = (Validate().isNotEmpty(props.location.state) && Validate().isNotEmpty(props.location.state.campaignId)) ? props.location.state.campaignId : undefined;
        setCampaignId(campaignId);
        setLoading(true);
        if(Validate().isNotEmpty(campaignId)) {
			getLoyaltyTypes();
            getMultiItemCampaignByCampaignId(campaignId);
	        setDataGrid()
        } else {
            if(props.match.path !== MIC_URLS.createCampaign) {
                props.history.push(MIC_URLS.createCampaign)
            }
        }
        setLoading(false);
    },[])
    
    useEffect(() => {
        let gridData = []
        if(regions && Object.keys(regions).length > 0) {
            Object.keys(regions).forEach(key => {
                let row ={};
                row['region'] = key;
                row['notEligibleRegions'] = regions[key] && Object.keys(regions[key]).length > 0 ? regions[key].toLocaleString() : '-' ;
                gridData.push(row);
            });
            
        }
        setDataGridData(gridData);
    },[regions]);
    
	const getLoyaltyTypes = async () => {
		
		let loyaltiesObj = {};
		console.log('getting loyalties from server');
		const response =  await MarketingService().getLoyaltyTypes().catch(error => {
	    	setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to fetch loyalties'});
	    });
	    loyaltiesObj=response.responseData;
    	setNewLoyalityTypes(loyaltiesObj);
	}
	
	const getMultiItemCampaignByCampaignId = async (campaignId) => {
       
        const data = await MarketingService().getMultiItemCampaignByCampaignId({'campaignId': campaignId}).catch(error=>{
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
		setStatus(data['status']);
        setExcel(!data['allCustomers'], data['applicableType'], data['channels'])
        setApplicableType(data['applicableType'])
		if(data['applicableType']===5){
			finalObj['applicableType'] = "Pharmacy";
		} else if(data['applicableType']===6){
			finalObj['applicableType'] = "Pathlabs";
		} else if(data['applicableType']===7){
			finalObj['applicableType'] = "Lens";
		}
		finalObj['priceConsideredForSlab']= data['priceConsideredForSlab']==="M" ? "MRP" : "Sale Price";
		finalObj['allCustomers']= data['allCustomers'] ? "Yes" : "No";
		finalObj['fromDate'] = dateFormat(data['fromDate'], "d mmm, yyyy hh:MM TT");
        seToDate(new Date(data['toDate']).getTime());
        finalObj['toDate'] = dateFormat(data['toDate'], "d mmm, yyyy hh:MM TT");
		finalObj['effectiveDate']= data['effectiveDate'] ? dateFormat(data['effectiveDate'], "d mmm, yyyy hh:MM TT") : finalObj['fromDate']
		let channels = [];
		data['channels'].forEach(element => {
			if(element===2){
				channels.push("WEB");
			} else if(element===1){
                setIsPosChannel(true);
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
		setRegions(data['regions']);
		
		let multiItemSlabs=[];

        data.multiItemSlabs.forEach(slab => {
            if (data.campaignType === "8") {
              multiItemSlabs.push(
                  <Button variant="light" className={`${props.className} align-items-center btn btn-light btn-sm d-flex rounded-5 mb-2`}>
                    <span>
                      Buy {slab.numberOfPacks} packs {" for "} 
                      <CurrencyFormatter data={slab.sellingPriceOrNumberOfPacks} />
                      {" at "}{finalObj.priceConsideredForSlab}
                    </span>
                  </Button>
              );
            } else if (data.campaignType === "9") {
              multiItemSlabs.push(
                  <Button variant="light" className={`${props.className} align-items-center btn btn-light btn-sm d-flex rounded-5 mb-2`}>
                    <span>
                      Buy {slab.numberOfPacks} packs, pay for {slab.sellingPriceOrNumberOfPacks}
                      {" packs "}
                    </span>
                  </Button>
              );
            } else {
                multiItemSlabs.push(
                    <Button variant="light" className={`${props.className} align-items-center btn btn-light btn-sm d-flex rounded-5 mb-2`}>
                      <span>
                        Buy this bundle at <CurrencyFormatter data={slab.sellingPriceOrNumberOfPacks} />
                      </span>
                    </Button>
                );
            }
          });
		setSlabs(multiItemSlabs);
		let campaignTypeName = ""
		if(data['campaignType'] === "8") {
			campaignTypeName = "Multi Item discount - Fixed SP";
		} else if(data['campaignType'] === "9") {
			campaignTypeName = "Multi Item discount - Buy 'X' Pay for 'Y' (Y < X)";
		} else {
			campaignTypeName = "Multi Item discount - Bundle";
		}
		finalObj['campaignType'] = campaignTypeName;
		setMetaData(data.userMetaData);
		if(Validate().isNotEmpty(data.userMetaData.imageServerName) || Validate().isNotEmpty(data.userMetaData.imagePath)) {
                setImages({
                    "imagePath": `${data.userMetaData.imageServerName}/${data.userMetaData.imagePath}`,
                    "thumbnailPath": `${data.userMetaData.imageServerName}/${data.userMetaData.imagePath}`,
                    "altText": "Promotion Banner",
                });
        }
        excelData.items.url="";
        excelData.customers.url="";
        STORE_EXCEL_DATA.multiItem.url="";
        setMultiItemCampaign(finalObj);
	}

    const setExcel = (showCustomers, applicableType, channels) => {
        MIC_TEMPLATES = [{header: 'Downloads', data: []}, {header: 'Templates',data: []}];
        pushExcelData(0, excelData.items.title, excelData.items, applicableType);
        if((channels?.length == 1 && channels[0] === 1) || applicableType === 6) {
            pushStoreExcelData(0, STORE_EXCEL_DATA.multiItem.title, STORE_EXCEL_DATA.multiItem)
        }
        if(showCustomers) {
            pushExcelData(0, excelData.customers.title, excelData.customers, applicableType);
        }
    }

    const pushExcelData = (index, title, excelDataItem, applicableType) => {
        MIC_TEMPLATES[index].data.push({title: title , onClick: () => 
            downloadExcelData(excelDataItem,{'campaignId': props?.location?.state?.campaignId, 'applicableType' : applicableType}, props?.location?.state?.campaignId, setAlertContent, ALERT_TYPE, setLoading)});
    }

    const pushStoreExcelData = (index, title, excelDataItem, applicableType) => {
        MIC_TEMPLATES[index].data.push({title: title , onClick: () => 
            downloadExcelData(excelDataItem,{'campaignId': props?.location?.state?.campaignId}, props?.location?.state?.campaignId, setAlertContent, ALERT_TYPE, setLoading)});
    }
	
	const setDataGrid = async () => {
        const data = await MarketingService().getRegionsDataGrid({'editableFeild': false, 'isStoreLevel' : false}).catch(error => {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: error});
        })
        let dataGridMetaInfo = data.responseData
        setDataGridStructure({...dataGridMetaInfo, columns: [...dataGridMetaInfo.columns.map(column => {
				if(column.rowDataKey === "action") {
					return {...column , isVisible: false}
				}
				return column;
			})] 
		})
    }
	
	const callBackMap =  {
        "selectFilter" : (props) => {
            const {handleFilterChange , ...rest} = props;
            const handleFilerChaner = (filters) => {
               setSelectedRegionFilter(filters.region.value)
               handleFilterChange(filters)
            }
            return <SelectFilter handleFilterChange={handleFilerChaner} {...rest}  tooltip={true} isClearable itemsMap={filterRegionsMap}/>
        }      
	};

    const getRegionsCount = (regionLength) => {
        return dataGridData.map(detail => detail.region).filter(region => region.length == regionLength).length;
    };

    const customText=()=>{
        return(
          <React.Fragment>
            {selectedRegionFilter!="" &&
                  <p className="mt-2 mb-0 d-flex align-items-center ">{getRegionsName(selectedRegionFilter)} - {getRegionsCount(selectedRegionFilter)}</p>
             }
          </React.Fragment>
        )
    }

    const approveCampaign = async () => {
        if(toDate < Date.now()) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:"ToDate cannot be before current time"});
            return;
        }
        let formData = new FormData();
        formData.append('campaignId', campaignId);
        formData.append('toDate', toDate);
        if(isPosChannel){
            formData.append('channel', 1);
        }
        setBtnLoading(true);
        const response = await MarketingService().approveCampaign(formData).catch(error => {
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
        setToastContent({toastMessage: `Campaign ${multiItemCampaign.campaignName} [${campaignId}] ${note} successfully....`, className:'w-100'});
        props.history.push({
            pathname: MIC_URLS.listCamapign,
            campaignId: campaignId,
            isSuccessOperation : true
        });
    }

    return (
        <div>
            <Wrapper>
                <HeaderComponent ref={headerRef} className="align-items-center border-bottom d-flex p-12">
                    <Button variant=" " className="btn-link icon-hover me-3" onClick={() => { props.history.push({ pathname: MIC_URLS.listCamapign, campaignId : campaignId}) }}> <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                        <g id="leftarrow_black_icon_18px" transform="translate(-48.941 -316.765)">
                            <rect id="BG_Guide" data-name="BG Guide" width="18" height="18" transform="translate(48.941 316.765)" fill="none" />
                            <path id="Path_22927" data-name="Path 22927" d="M56.566,319.236a.686.686,0,0,0-.448.178l-6.977,6.53a.7.7,0,0,0,0,.984l6.977,6.44a.709.709,0,0,0,.984-.089.7.7,0,0,0,0-.984l-5.546-5.188H66.226a.716.716,0,0,0,0-1.431H51.557l5.635-5.188a.635.635,0,0,0,.269-.536,1.275,1.275,0,0,0-.179-.537A4.04,4.04,0,0,0,56.566,319.236Z" transform="translate(0 -0.471)" fill="#080808" />
                        </g>
                    </svg></Button>
                    <p className='mb-0'>Campaign ID - <span className="fw-bold">{campaignId}</span></p>
                    <div className="text-white ms-3" title="Status"> {getStatusSpanInfo(status)} </div>
                    <TemplateDropDown templates={MIC_TEMPLATES} {...props}/>
                </HeaderComponent>
                <BodyComponent className="body-height" allRefs={{ "headerRef":headerRef ,"footerRef":   footerRef}}>
                    <div>
                        <div className='row mb-4 g-3'>
                            <label className='custom-fieldset'>Campaign Info</label>

                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Campaign Name</label>
                                <h6 className='mb-0 font-14'>{multiItemCampaign['campaignName']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Applicable Type</label>
                                <h6 className='mb-0 font-14'>{multiItemCampaign['applicableType']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Campaign Type</label>
                                <h6 className='mb-0 font-14'>{multiItemCampaign['campaignType']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Channel</label>
                                <h6 className='mb-0 font-14'>{multiItemCampaign['channels']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Price Considered For Slab</label>
                                <h6 className='mb-0 font-14'>{multiItemCampaign['priceConsideredForSlab']}</h6>
                            </div>

                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>All Customers</label>
                                <h6 className='mb-0 font-14'>{multiItemCampaign['allCustomers']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>From Date</label>
                                <h6 className='mb-0 font-14'>{multiItemCampaign['fromDate']}</h6>
                            </div>

                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>To Date</label>
                                <h6 className='mb-0 font-14'>{multiItemCampaign['toDate']}</h6>
                            </div>
							{status === PromotionStatus.active ? 
							<div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Effective Date</label>
                                <h6 className='mb-0 font-14'>{multiItemCampaign['effectiveDate']}</h6>
                            </div> : ""}

							{status === PromotionStatus.rejected ? 
							<div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Remarks</label>
                                <h6 className='mb-0 font-14'>{metaData['remarks'] ? metaData['remarks'] : " - "}</h6>
                            </div> : ""}
                        </div>
                    </div>
                    <div>

                        <div className='row mb-4'>
                            <label className='custom-fieldset mb-12'>Meta Info</label>
                            <div className='col-9'>
                                <label className='font-12 text-secondary mb-1'>Description</label>
                                <h6 className='mb-0 font-14'>{metaData['longDescription']}</h6>
                            </div>
                            <div className='col-3'>
                                <label className='font-12 text-secondary mb-1'>Is Climable</label>
                                <h6>{metaData['claimable']===true ? "Yes" : "No"}</h6>
                            </div>
                            {Validate().isNotEmpty(images.imagePath) ? <div className='d-inline-block'>
                                {  <StackedImages images={[images]} includeLightBox maxImages="1"/>}
                           	</div> : null}
                        </div>
                    </div>
                    <div className='row mb-4'>
                            <label className='custom-fieldset mb-12'>Slabs</label>
                            <div className="d-flex gap-3 flex-wrap" style={{ maxHeight: '200px', overflowY: 'auto' }}>
                            {slabs && slabs.map((slab, index) => {
								return (
										<div key={index}>
			                                {slab}
			                            </div>
								)
							})}
                            </div>
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
                    <div className='h-50'>
                    <label className='custom-fieldset mb-2 col-2'>Regions - {Object.keys(regions).length}</label>
                            {regions && Object.keys(regions).length > 0 &&
	                            <div className="h-75 overflow-auto card">
	                                <div className="h-100">
	                                {Validate().isNotEmpty(dataGridData) && <CommonDataGrid {...dataGridStructure} 
	                                dataSet={dataGridData} 
	                                callBackMap={callBackMap}
                                    customGridToolbar={{component:customText}}
	                                />}
	                                </div>
	                            </div>
                        	}
                    </div>
                </BodyComponent>

                <FooterComponent ref={footerRef} >
                    {Validate().isApproverForApplicableType(applicableType?.toString(), MIC_ROLES) && status == PromotionStatus.inActive &&
                    <div className="d-flex footer align-items-center justify-content-end gap-3 p-2">
                    {rejectModalInfo.showRejectModal && <RejectModal show={rejectModalInfo.showRejectModal} setRejectModalInfo={setRejectModalInfo} promotionType={PromotionType.multiItem} successCallBackHandler ={successCallBackHandler}
                            campaignName = {multiItemCampaign?.campaignName} campaignId = {campaignId} rejectService={LIST_TABS.multiItem.rejectService} {...props}/> }
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

export default ViewCampaign