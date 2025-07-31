import CommonDataGrid, { ApproveIcon, CancelIcon, CloneIcon, EditIcon } from "@medplus/react-common-components/DataGrid";
import { ALERT_TYPE, CustomSpinners, TOAST_POSITION } from "@medplus/react-common-components/DynamicForm";
import dateFormat from 'dateformat';
import React, { useCallback, useContext, useEffect, useRef, useState } from "react";
import { UncontrolledTooltip } from "reactstrap";
import { PromotionStatus, PromotionType } from "../constants/PromotionConstants";
import { RP_URLS } from "../constants/UrlConstants";
import Validate from "../helpers/Validate";
import { SearchContext } from "./Contexts/MarketingContexts";
import { AlertContext, SidebarContext } from "./Contexts/UserContext";
import RejectModal from "./RejectModal";
import { BodyComponent, HeaderComponent, Wrapper } from "./common/CommonStructure";
import NoDataFound from "./common/NoDataFound";

const ListCampaign = (props) => {

    const validate =  Validate();
    const promotionConfig = props?.promotionConfig
    const {campaignSearchCriteria, loadData, setLoadData, selectedDateRange, setSelectedDateRange} = useContext(SearchContext);
    const [selectedCampaignId, setSelectedCampaignId] = useState(props.location?.campaignId);
    const isSuccessOperation = props.location?.isSuccessOperation;
    const [dataSet, setDataSet] = useState([]);
    const [loading, setLoading] = useState(false);
    const [gridData,setGridData] = useState(promotionConfig?.gridData);
    const { setToastContent } = useContext(AlertContext);
    const {setAlertContent} = useContext(AlertContext);
    const [campaignCount, setCampaignCount] = useState(100);
    const { sidebarCollapsedFlag } = useContext(SidebarContext);
    const [rejectModalInfo , setRejectModalInfo] = useState({
        showRejectModal: false,
        rejectModalData  : undefined
    })
    const [selectedDate, setSelectedDate] = useState({
        fromDate :undefined,
        toDate:undefined
    })
    const headerRef = useRef(null);
    const options = {
        year: 'numeric',
        month: 'short',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
      };
    const formateSelectedDateRange = () => {
        const fromDate = selectedDateRange?.fromDate ?  dateFormat(selectedDateRange?.fromDate, "d mmm, yyyy") : undefined;
        const toDate = selectedDateRange?.toDate ? dateFormat(selectedDateRange?.toDate, "d mmm, yyyy") : undefined;
        setSelectedDate({fromDate:fromDate, toDate:toDate})
    }
    
    useEffect(() => {
        setSelectedDate({fromDate:null, toDate:null});
        if(!validate.isSearchCriteriaEmpty(campaignSearchCriteria, false)) {
            getCampaigns({});
        }
        formateSelectedDateRange();
    }, [campaignSearchCriteria]);

    useEffect(() => {
        setGridData({...gridData , totalRowsCount : campaignCount})
    },[campaignCount])
    useEffect(() => {
        setDataSet({});
        setGridData(promotionConfig.gridData);
    },[props.path])
    useEffect(() => {
        formateSelectedDateRange();
    },[selectedDateRange])
    

    const validateEditRoles = (rowObject) => {
        let roles=[];
        if(validate.isNotEmpty(rowObject) && validate.isNotEmpty(rowObject.row) && rowObject.row.status === 'Approved') {
            roles = promotionConfig?.roles?.closeRoles;
        } else {
            roles = promotionConfig?.roles?.editIconRoles;
        }
        return validate.validateRole(roles);
    }

    const validateCloneRoles = (rowObject) => {
        return validate.validateRole(promotionConfig?.roles?.cloneRoles);
    }

    const validateViewRoles  = (rowObject) => {
        return validate.validateRole(promotionConfig?.roles?.listRoles);
    }

    const getCampaigns  = async ({ startIndex, limit, totalRecords }) => {
        setLoading(true);
        const dataList = [];
        const formData = new FormData();
        campaignSearchCriteria['offset'] = Validate().isEmpty(startIndex) ? 0 : startIndex,
        campaignSearchCriteria['limit'] = limit,
        formData.append('campaignSearchCriteria', JSON.stringify(campaignSearchCriteria));

        const response = await promotionConfig?.service(formData).catch(error => {
            setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:error})
        });
        if (validate.isNotEmpty(response) && response.statusCode === 'SUCCESS') {
            setCampaignCount(response.responseData.rowCount);
            for (const obj of response.responseData['data']) {
                const dataObj = {};
                dataObj['rowIndex'] = obj[promotionConfig?.id];
                dataObj['campaignId'] = obj[promotionConfig?.id];
                dataObj['campaignName'] = obj[promotionConfig?.name];
                dataObj['status'] = obj['status']== PromotionStatus.active ? "Approved" : (obj['status']==PromotionStatus.inActive ? "Created" : "Rejected");
            
                if(props.path ===  RP_URLS.listCamapign) {
                    const promotionLevel = obj['promotionLevel'];
                    switch (promotionLevel) {
                        case 'C':
                            dataObj['promotionLevel']='Category';
                            break;
                        case 'I':
                            dataObj['promotionLevel']='Invoice';
                            break;
                        default:
                            break;
                    }
                }
                const fromDateObj = new Date(obj['fromDate']);
                dataObj['fromDate'] = fromDateObj.toLocaleString('en-US', options);
                if(obj['effectiveDate']){
                    const effectiveDateObj = new Date(obj['effectiveDate']);
					dataObj['effectiveDate'] = effectiveDateObj.toLocaleString('en-US', options);
				} else {
                    dataObj['effectiveDate'] = "-";
                }
                if(obj['toDate']) {
                    const toDateObj = new Date(obj['toDate']);
                    dataObj['toDate'] = toDateObj.toLocaleString('en-US', options);
                } else {
                    dataObj['toDate'] = "-";
                }
                dataObj['createdBy'] = obj.userMetaData.createdBy;
                const dateCreatedObject = new Date(obj.userMetaData.dateCreated);
                dataObj['dateCreated'] = dateCreatedObject.toLocaleString('en-US', options);
               
                dataObj['approvedBy'] = obj.userMetaData.approvedBy ? obj.userMetaData.approvedBy : "-";
                dataObj['dateApproved'] = obj.userMetaData.dateApproved ? new Date(obj.userMetaData.dateApproved)?.toLocaleString('en-US', options) : "-";
               
                dataObj['cloneReferenceId'] =  (obj['cloneReferenceId'] != 0 && validate.isNotEmpty(obj['cloneReferenceId'])) ? obj['cloneReferenceId'] : '-';
                const applicableType = obj[promotionConfig?.applicableType];
                switch (applicableType) {
                	case 5:
                    	dataObj['applicableType']='Pharmacy';
                        break;
                    case 6:
                        dataObj['applicableType']='Pathlabs';
                        break;
                    case 7:
                        dataObj['applicableType']='Lens';
                        break;
                    default:
                        break;
                }
                let channelList = []
                for (const channel of obj['channels']) {
                    switch (channel) {
                        case 1:
                            channelList.push('POS');
                            break;
                        case 2:
                            channelList.push('WEB');
                            break;
                        case 3:
                            channelList.push('CRM');
                            break;
                        case 4:
                            channelList.push('Mobile');
                            break;
                        case 5:
                            channelList.push('POS WEB');
                            break;
                        default:
                            break;
                    }
                }
                dataObj['channels'] = channelList.join(', ')
                dataList.push(dataObj);
            }
            if(startIndex > 0) {
                setDataSet([...dataSet,...dataList]);
                setLoadData([...dataSet, ...dataList])
            } else {
                setDataSet(dataList);
                setLoadData(dataList)
            }
            setLoading(false);
            return { dataSet:dataList, totalRowsCount:totalRecords, status: true } 
        } else {
            setAlertContent({alertTyp:ALERT_TYPE.ERROR, alertMessage:"Unable to fetch data"});
            setDataSet({});
            setLoadData(undefined);
            setLoading(false);
            return {status: false};
        }
    }

    const cloneCampaign = async (campaignId) => {
        if(validate.isNotEmpty(campaignId)) {
            props.history.push(promotionConfig?.url?.cloneCampaign, {[promotionConfig?.id]: campaignId});
        }
    }

    const editCampaign = (campaignId) => {
        if(validate.isNotEmpty(campaignId)) {
            props.history.push(promotionConfig?.url?.editCampaign, {[promotionConfig?.id]: campaignId});
        }
    }

    const approveCampaign = (campaignId) => {
        if(validate.isNotEmpty(campaignId)) {
            props.history.push(promotionConfig?.url?.approveCampaign, {[promotionConfig?.id]: campaignId});
        }
    }

    const updateCampaign = (campaignId) => {
        if(validate.isNotEmpty(campaignId)) {
            props.history.push(promotionConfig?.url?.closeCampaign, {[promotionConfig?.id]: campaignId});
        }
    }

    const viewCampaign = (campaignId) => {
        setSelectedCampaignId(campaignId)
        if(Validate().isNotEmpty(campaignId)) {
            props.history.push(promotionConfig?.url?.viewCampaign, {[promotionConfig?.id]: campaignId});
        }
    }

    const rowClassFunction = useCallback((rowObject) => {
        let rowClassName = '';
        if (rowObject.row.campaignId == selectedCampaignId) {
            rowClassName = "row-popup-opened";
        }
        return rowClassName;

    }, [selectedCampaignId])

    const getClassName = (status) => {
        if(status == 'Approved') {
            return 'badge-approved'
        } else if(status == 'Created') {
            return 'badge-pending'
        } else if(status == 'Rejected') {
            return 'badge-rejected'
        }
    }

    setTimeout(()=>{
        setSelectedCampaignId(undefined)
    },4000)

    const successCallBackHandler = () => {
        setToastContent({toastMessage: `campaignID [${rejectModalInfo?.rejectModalData?.campaignId}] Rejected successfully....`, className:'w-100'});
        getCampaigns({});
    }

    const getApplicableTypeId = (type) => {
        if(type === 'Pharmacy'){
            return '5';
        } else if( type=== 'Pathlabs'){
            return '6';
        } else if(type === 'Lens') {
            return '7';
        }
    }


    const isCreatorForApplicableType = (row) => {
        return Validate().isCreatorForApplicableType(getApplicableTypeId(row.applicableType), promotionConfig?.roles);
    } 
    const isApproverForApplicableType = (row) => {
        return Validate().isApproverForApplicableType(getApplicableTypeId(row.applicableType), promotionConfig?.roles);
    } 
    const isCloserForApplicableType = (row) => {
        return Validate().isCloserForApplicableType(getApplicableTypeId(row.applicableType), promotionConfig?.roles);
    }
    const isEditorForApplicableType = (row) => {
        return Validate().isEditorForApplicableType(getApplicableTypeId(row.applicableType), promotionConfig?.roles);
    } 
    
    const handleRejectClick =(row) => {
        setRejectModalInfo({
            showRejectModal: true,
            rejectModalData: row
        });
    }

    const checkToDateExpired = (toDate) => {
        if(promotionConfig?.promotionType === PromotionType.regular && !toDate) {
            return false;
        }
        return toDate && new Date(toDate).getTime() <= Date.now();
    }
    
    const callBackMapping = {
        "renderActionColumn" : (rowObject) => {
            return <React.Fragment> 
            <div className="align-items-center d-flex">
                {isEditorForApplicableType(rowObject.row) && rowObject?.row?.status === 'Created'  && <EditIcon handleOnClick={()=> editCampaign(rowObject?.row?.campaignId)} tooltip={'Update'} isDisabled={checkToDateExpired(rowObject?.row?.toDate)}/>}
                {isCloserForApplicableType(rowObject.row) && rowObject.row.status === 'Approved' && <EditIcon handleOnClick={() => updateCampaign(rowObject?.row?.campaignId)} tooltip={'Update'} isDisabled={checkToDateExpired(rowObject?.row?.toDate)}/>}  
                {isCreatorForApplicableType(rowObject.row) && rowObject.row.status !== 'Rejected' && <CloneIcon handleOnClick={() => cloneCampaign(rowObject.row.campaignId)} tooltip={'Clone'} />}              
                {isApproverForApplicableType(rowObject.row) && rowObject.row.status === 'Created' && <ApproveIcon handleOnClick={() => approveCampaign(rowObject?.row?.campaignId)} tooltip={'Approve'} />} 
                {isApproverForApplicableType(rowObject.row) && rowObject.row.status === 'Created' && <CancelIcon handleOnClick={() => handleRejectClick(rowObject.row)} tooltip={'Reject'} />}
            </div>
            </React.Fragment>
        },
        "status" : (rowObject) => {
            return <>
            <div className={`${getClassName(rowObject.row.status)} badge rounded-pill`}>{rowObject.row.status}</div>
            </>
        },
        "campaignId" : (rowObject) => {
            return <>
                 <a class="btn btn-sm btn-link w-100" id={rowObject?.row?.campaignId} href="javascript:void(0)" rel="noopener" 
                 onClick={() => {viewCampaign(rowObject.row.campaignId)}}
                 aria-label={rowObject?.row?.campaignId} role="link" title="View Campaign">{rowObject?.row?.campaignId}</a>
            </>
        },
        "rowClass" : (row) => {
            let rowClassName = '';
            if ((row?.campaignId == selectedCampaignId) && validate.isNotEmpty(row?.campaignId)) {
                rowClassName = isSuccessOperation ? "row-edited-success" : "row-popup-opened";
            }
            return rowClassName;
        }
    }

    return(
        <>
            <React.Fragment>
                <Wrapper>
            <HeaderComponent ref={headerRef}>
            <p className="p-12 mb-0 border-bottom">List of {promotionConfig?.viewName} {selectedDate.fromDate && selectedDate.toDate&& <span className="fw-bold">From {selectedDate.fromDate} To {selectedDate.toDate}</span>}</p>
            </HeaderComponent>
                    <BodyComponent className='p-12' allRefs = {{headerRef}}>
                        {loading && <CustomSpinners outerClassName={"align-items-center d-flex custom-spinner flex-column"} innerClass={"custom-spinner-text-width"} animation="border" variant="brand" spinnerText='Please be patient while we prepare your data for loading!'/>}
                        {!loading && <>
                        {validate.isNotEmpty(dataSet) ? <div className={`h-100 ${sidebarCollapsedFlag ? 'nav-close-grid-width' :'nav-open-grid-width'}`}> <div className="h-100 card"><CommonDataGrid
                            {...gridData}
                            dataSet={dataSet}
                            callBackMap={callBackMapping}
                            remoteDataFunction={getCampaigns}
                            noRowsFallback={<NoDataFound text="No Data Found" {...props} grid/>}
                        /></div> </div> : <NoDataFound text="No Data Found" {...props} searchButton/>}
                        </>}
                    </BodyComponent>
                    {rejectModalInfo.showRejectModal && <RejectModal show={rejectModalInfo.showRejectModal} setRejectModalInfo={setRejectModalInfo} promotionType={promotionConfig.promotionType} successCallBackHandler ={successCallBackHandler}
                    campaignName = {rejectModalInfo?.rejectModalData?.campaignName} campaignId = {rejectModalInfo?.rejectModalData?.campaignId} rejectService={promotionConfig?.rejectService} {...props}/> }
                    </Wrapper>
            </React.Fragment>
        </>
    )

    
}

export default ListCampaign;