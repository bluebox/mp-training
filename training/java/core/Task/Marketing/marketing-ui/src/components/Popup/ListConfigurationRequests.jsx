import CommonDataGrid, { ApproveIcon, Badges, CancelIcon, EditIcon } from "@medplus/react-common-components/DataGrid";
import dateFormat from 'dateformat';
import React, { useContext, useEffect, useRef, useState } from "react";
import { UncontrolledTooltip } from "reactstrap";
import { PATHLABS, PHARMACY } from "../../constants/MarketingConstant";
import { POPUP_ROLES, ROLE_MRK_APPROVE_POPUP_CONFIGURATION_PHARMA } from "../../constants/MarketingRoles";
import { DEFAULT_END_DATE_TIME_FORMAT, DEFAULT_START_DATE_TIME_FORMAT } from "../../constants/PromotionConstants";
import { POPUP_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import PopupService from "../../services/PopupService";
import { BodyComponent, HeaderComponent, Wrapper } from "../common/CommonStructure";
import NoDataFound from "../common/NoDataFound";
import { AlertContext, UserContext } from "../Contexts/UserContext";
import RejectPopupModal from "./RejectPopUpModal";

export const ListConfigurationRequests = (props) => {
    const validate = Validate();
    const popupService = PopupService();

    const headerRef = useRef(null);
    const gridValueRef = useRef(null);
    const { setStackedToastContent } = useContext(AlertContext);
    const [isLoading, setLoading] = useState(true);
    const [dataSet, setDataSet] = useState([]);
    const [dataGrid, setDataGrid] = useState({});
    const [gridKey, setGridKey] = useState(0);
    const [totalRecords, setTotalRecords] = useState(0);
    const [searchCriteria, setSearchCriteria] = useState({});
    const [selectedId,setSelectedId] = useState(props.location.state?.id);
    const [isBackClicked, setBackClicked] = useState(props.location.state?.isBackClicked);
    const [ageGroups,setAgeGroups] = useState({});
    const [openModal, setOpenModal] = useState(false);
    const [selectedConfiguration, setSelectedConfiguration] = useState({});
    const {userSessionDetails} = useContext(UserContext);
    const HAS_POP_UP_CONFIG_CREATE_ROLE = validate.validateRole(POPUP_ROLES['createConfiguration']);
    const HAS_PHARMACY_VIEW_ROLE = validate.validateRole(POPUP_ROLES.viewPharmaConfigurations);
    const HAS_PATHLABS_VIEW_ROLE = validate.validateRole(POPUP_ROLES.viewLabConfigurations);

    const defaultCriteria = {
        noOfRecords: 50,
        limitFrom: 0,
        fromDateCreated: dateFormat(new Date(), DEFAULT_START_DATE_TIME_FORMAT),
        toDateCreated : dateFormat(new Date(), DEFAULT_END_DATE_TIME_FORMAT),
        createdBy : HAS_POP_UP_CONFIG_CREATE_ROLE ? userSessionDetails.userId : null
    };

    const getActualSearchCriteria = (criteria) => {
        const { noOfRecords, limitFrom, ...rest } = criteria;
        return rest;
    }

    useEffect(() => {
        let paramSearchCriteria = props.location.state?.searchCriteria || {};
        let startDate = validate.isNotEmpty(paramSearchCriteria) ? paramSearchCriteria.fromDateCreated : ((validate.isNotEmpty(searchCriteria)&& validate.isNotEmpty(searchCriteria.fromDateCreated)) ? searchCriteria.fromDateCreated : defaultCriteria.fromDateCreated);
        let endDate = validate.isNotEmpty(paramSearchCriteria) ? paramSearchCriteria.toDateCreated : ((validate.isNotEmpty(searchCriteria)&& validate.isNotEmpty(searchCriteria.toDateCreated)) ? searchCriteria.toDateCreated : defaultCriteria.toDateCreated);
		if (validate.isEmpty(paramSearchCriteria) || !validate.isEqualObject(getActualSearchCriteria(paramSearchCriteria), getActualSearchCriteria(searchCriteria))) {
            setGridKey(gridKey + 1);
            let popupRequestSearchCriteria = {};
            if(validate.isNotEmpty(paramSearchCriteria?.applicableTypes)){
                popupRequestSearchCriteria['applicableTypes'] = paramSearchCriteria.applicableTypes;
            }else{
                if(HAS_PHARMACY_VIEW_ROLE && !HAS_PATHLABS_VIEW_ROLE){
                    popupRequestSearchCriteria['applicableTypes'] = [PHARMACY]
                }else if(HAS_PATHLABS_VIEW_ROLE && !HAS_PHARMACY_VIEW_ROLE){
                    popupRequestSearchCriteria['applicableTypes'] = [PATHLABS]
                }
            }
            popupRequestSearchCriteria['requestIds'] = paramSearchCriteria ? paramSearchCriteria.requestId : searchCriteria.requestId;
            popupRequestSearchCriteria['fromDateCreated'] = validate.isEmpty(startDate) ? null : dateFormat(startDate, DEFAULT_START_DATE_TIME_FORMAT);
            popupRequestSearchCriteria['toDateCreated'] = validate.isEmpty(endDate) ? null : dateFormat(endDate, DEFAULT_END_DATE_TIME_FORMAT);
            let configRequestStatus = paramSearchCriteria.requestStatus || paramSearchCriteria.configRequestStatus;
            popupRequestSearchCriteria['configRequestStatus'] = configRequestStatus ? configRequestStatus : (searchCriteria.configRequestStatus ? searchCriteria.configRequestStatus : []);
            popupRequestSearchCriteria['popupConfigRequestName'] = paramSearchCriteria ? paramSearchCriteria.configurationLikeName : searchCriteria.configurationLikeName;
            if(!HAS_POP_UP_CONFIG_CREATE_ROLE){
                popupRequestSearchCriteria['createdBy'] = paramSearchCriteria ? paramSearchCriteria.createdBy : searchCriteria.createdBy;
            } else {
                popupRequestSearchCriteria['createdBy'] = defaultCriteria.createdBy;
            }
			popupRequestSearchCriteria['limitFrom'] = paramSearchCriteria.limitFrom || defaultCriteria.limitFrom;
            popupRequestSearchCriteria['noOfRecords'] = paramSearchCriteria.noOfRecords || defaultCriteria.noOfRecords;
            setSearchCriteria(popupRequestSearchCriteria);
            getConfigRequestsList(popupRequestSearchCriteria);
        }
    }, [props.location, props.showModal])

    const getConfigRequestsList = async (finalObj) => {
        if (!props.showModal) {
            let response = await popupService.getConfigRequests(finalObj).catch((err) => setStackedToastContent({ toastMessage: "Unable to get configuration requests, please try again." }));
            ResponseHandler(setStackedToastContent).handleResponse(
                response,
                { successAlert: true },
                handleSuccessResponse,
                (error) => {
                    setDataSet([]);
                    setDataGrid({});
                    setStackedToastContent({ toastMessage: error });
                }
            );
            setLoading(false);
            setAgeGroups(validate.isNotEmpty(response.responseData?.ageGroups) ? response?.responseData?.ageGroups : {});
            return response.responseData;
        }
    };

    const handleSuccessResponse = (responseData) => {
        if (validate.isNotEmpty(responseData)) {
            setDataGrid(validate.isEmpty(responseData.dataGrid) ? [] : responseData.dataGrid);
            setDataSet(validate.isEmpty(responseData.dataSet) ? [] : responseData.dataSet);
            setTotalRecords(validate.isEmpty(responseData.totalRecords) ? 0 : responseData.totalRecords);
        }
    };

    const redirectToEditConfiguration = (applicableType, reqId) => {
        if (validate.isNotEmpty(applicableType) && validate.isNotEmpty(reqId)) {
            props.history.push({
                pathname: `./edit-configuration/request/${reqId}`,
                state:{ageGroups:ageGroups, isEdit:true, applicableType: applicableType,searchCriteria:searchCriteria},
                
            });
        }
    }

    const updateDataSetOnApprove = (requestId) => {
        setDataSet(dataSet.filter(item => item.requestId !== requestId)) ;
    }
    
    const handleRejectConfiguration = (row) => {
        if(validate.isEmpty(row)){
            return;
        }
        let configInfo = {};
        configInfo['requestId'] = row.requestId;
        configInfo['name'] = row.name;
        setSelectedConfiguration(configInfo);
        setOpenModal(true);
    }

    const callBackMapping = {
        renderActionColumn: (props) => {
            return (
                <>
                    {props?.row?.applicableType && props?.row?.requestId && validate.validateRole(POPUP_ROLES['approveConfigurations']) && props.row.status == 'PENDING' && <ApproveIcon handleOnClick={() => approveConfiguration(props?.row?.requestId)} tooltip={'Approve'} />}
                    {props?.row?.applicableType && props?.row?.requestId && validate.validateRole(POPUP_ROLES['createConfiguration'])&& ['PENDING','REJECTED'].includes(props.row.status) &&  <EditIcon tooltip={"Edit"} handleOnClick={() => (redirectToEditConfiguration(props.row.applicableType,props.row.requestId))} id={props.row.requestId} />}
                    {props?.row?.applicableType && props?.row?.requestId && validate.validateRole(POPUP_ROLES['approveConfigurations']) && props.row.status == 'PENDING' && <CancelIcon handleOnClick={() => handleRejectConfiguration(props?.row)} tooltip={'Reject'} />}
                </>
            );
        },
        "renderRequestIdColumn" : (rowObject) => {
            return <>
                <a className="pointer btn btn-sm btn-link" onClick={() => { viewConfiguration(rowObject.row.requestId,rowObject.row.applicableType) }} id={"requestId"}>{rowObject.row.requestId}</a>
                <UncontrolledTooltip placement="bottom" target={"requestId"}>
                    {'View Configuration'}
                </UncontrolledTooltip>
            </>
        },
        "renderApplicableTypeColumn" : (rowObject) => {
           return rowObject?.row?.applicableType == "PATHLABS" ? "Path Labs" : "Pharmacy";
        },
        "renderStatusColumn" : (props) => {
            let statusText = props.row.status === 'APPROVED' ? 'Approved' : props.row.status == 'PENDING' ? 'Pending' : 'Rejected';
            return <Badges key={"status"} className={'badge rounded-pill badge-' + (props.row.status === 'APPROVED' ? 'approved' : props.row.status == 'PENDING' ? 'pending' : 'rejected')} text={statusText} />
        },
        "rowClassName": (row) => {
            if (selectedId == row.requestId) {
                setTimeout(() => {
                    setSelectedId(undefined);
                }, 2000);
                return isBackClicked ? "row-popup-opened" : "row-edited-success";
            }
        }
    };

    const remoteDataFunction = async ({ startIndex, limit, filters }) => {
        if (totalRecords <= startIndex) {
            return { dataSet: dataSet, totalRowsCount: totalRecords };
        }
        let popupSearchCriteria = { ...searchCriteria, 'limitFrom': startIndex, 'noOfRecords': limit };
        setSearchCriteria(popupSearchCriteria);
        const responseData = await getConfigRequestsList(popupSearchCriteria);
        setDataSet(startIndex > 0 ? [...dataSet, ...responseData.dataSet] : responseData.dataSet);
        return { dataSet: responseData?.dataSet ? responseData.dataSet : [], totalRowsCount: responseData?.totalRecords ? responseData.totalRecords : 0, status: true };
    };

    const approveConfiguration = (requestId) => {
        if(validate.isNotEmpty(requestId)){
            popupService.approveConfiguration({requestId}).then(res=> {
                if(validate.isNotEmpty(res) && validate.isNotEmpty(res.statusCode) && "SUCCESS" == res.statusCode){
                    setStackedToastContent({toastMessage:`${requestId} Approved successfully`});
                    updateDataSetOnApprove(requestId)
                } else {
                    setStackedToastContent({toastMessage: validate.isNotEmpty(res.message) ? res.message : `Unable to approve the configuration request ${requestId}, please try later.`});
                }
            }).catch(err => {
                console.log("Error while Approving the configuration ", err);
                setStackedToastContent({toastMessage:`Unable to approve the configuration request ${requestId}, please try later.`});
            });
        }

    }

    const rejectConfiguration = (requestId, comments) => {
        if(validate.isNotEmpty(requestId)){
           popupService.rejectConfiguration({requestId: requestId, comments: comments}).then(res=> {
            if(validate.isNotEmpty(res) && validate.isNotEmpty(res.statusCode) && "SUCCESS" == res.statusCode){
                setStackedToastContent({toastMessage:`${requestId} rejected successfully`});
                updatePopupConfigData(requestId);
            }else{
                setStackedToastContent({toastMessage:`Unable to reject the configuration request ${requestId}, please try later.`});
            }
            setOpenModal(false);
           }).catch((err) => {
            console.log("Error while Rejecting the configuration ", err);
            setOpenModal(false);
        });
        }
    }

    const handleDataSet = (responseData) => {
        if (validate.isNotEmpty(responseData) && validate.isNotEmpty(responseData.dataSet) && Array.isArray(responseData.dataSet)) {
            let [requestedDataSet] = responseData.dataSet;
            if(validate.isNotEmpty(requestedDataSet)){
                setDataSet(dataSet => dataSet.map((each) => {
                    if(each.requestId == requestedDataSet.requestId){
                        return requestedDataSet;
                    }
                    return each;
                }))
            }
        }
    }
    
    const updatePopupConfigData = async (requestId) => {
        let response = await popupService.getConfigRequests({ 'requestIds': [requestId] }).catch((err) => setStackedToastContent({ toastMessage: "Unable to get configuration, please try again." }));
        ResponseHandler(setStackedToastContent).handleResponse(
            response,
            { successAlert: true },
            handleDataSet,
            (error) => {
                setStackedToastContent({ toastMessage: error });
            }
        );
    }

    const viewConfiguration = (reqId,applicableType) => {
        if (Validate().isNotEmpty(reqId)) {
            props.history.push({
                pathname: `${POPUP_URLS.viewConfiguration}/request/${reqId}`,
                state:{ageGroups:ageGroups,searchCriteria:searchCriteria,applicableType:applicableType, isEdit:false}
            });
        }
    }

    return (
        <React.Fragment>
            <Wrapper>
                <HeaderComponent ref={headerRef} className="d-flex flex-column">
                    <div className="align-items-center border-bottom d-flex justify-content-between px-2 px-lg-3 py-1">
                        <p className="d-flex align-items-center flex-wrap mb-0">
                            <span>List of Popup Configuration Requests {searchCriteria && searchCriteria.fromDateCreated && searchCriteria.toDateCreated && <strong>from {dateFormat(searchCriteria.fromDateCreated, 'mmm dd, yyyy')} To {dateFormat(searchCriteria.toDateCreated, 'mmm dd, yyyy')}</strong>}</span>
                        </p>
                    </div>
                </HeaderComponent>
                <BodyComponent allRefs={{ headerRef }} loading={isLoading} className="body-height">
                    {validate.isNotEmpty(dataSet) && validate.isNotEmpty(dataGrid) && !isLoading ? <div className="card h-100">
                        <div className="card-body p-0">
                            <CommonDataGrid
                                key={gridKey}
                                ref={gridValueRef}
                                {...dataGrid}
                                dataSet={dataSet}
                                callBackMap={callBackMapping}
                                remoteDataFunction={remoteDataFunction}
                                noRowsFallback={<NoDataFound text="No Data Found" {...props} grid />}
                            />
                        </div>
                    </div> : !isLoading && <NoDataFound text="No Data Found" {...props} searchButton/>}
                    {openModal && <RejectPopupModal openModal={openModal} setOpenModal={setOpenModal} ModalName={"Configuration"} requestId={selectedConfiguration?.requestId} configurationName={selectedConfiguration?.name} reject={rejectConfiguration}/>}
                </BodyComponent>
            </Wrapper>
        </React.Fragment>
    )

}