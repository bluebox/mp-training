import CommonDataGrid, { ApproveIcon, Badges, DeleteIcon, EditIcon } from "@medplus/react-common-components/DataGrid";
import dateFormat from 'dateformat';
import React, { useContext, useEffect, useRef, useState } from "react";
import { UncontrolledTooltip } from "reactstrap";
import { PATHLABS, PHARMACY } from "../../constants/MarketingConstant";
import { POPUP_ROLES } from "../../constants/MarketingRoles";
import { DEFAULT_END_DATE_TIME_FORMAT, DEFAULT_START_DATE_TIME_FORMAT } from "../../constants/PromotionConstants";
import { POPUP_URLS } from "../../constants/UrlConstants";
import KeyboardShortcuts from "../../helpers/KeyboardShortcuts";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import PopupService from "../../services/PopupService";
import { BodyComponent, HeaderComponent, Wrapper } from "../common/CommonStructure";
import DataGridHelper from "../common/DataGridHelper";
import NoDataFound from "../common/NoDataFound";
import { AlertContext, UserContext } from "../Contexts/UserContext";
import PreviewTemplate from "./PreviewTemplate";
import RejectPopupModal from "./RejectPopUpModal";

export default function ListTemplateRequests(props) {

    const headerRef = useRef(null);
    const gridValueRef = useRef(null);
    const validate = Validate();
    const popupService = PopupService();
    const { setStackedToastContent } = useContext(AlertContext);
    const [isShowPreview, setShowPreview] = useState(false);
    const [modalValue, setModalValue] = useState({});
    const [isLoading, setLoading] = useState(true);
    const [dataSet, setDataSet] = useState(null);
    const [dataGrid, setDataGrid] = useState(null);
    const [searchCriteria, setSearchCriteria] = useState({});
    const [totalRecords, setTotalRecords] = useState(0);
    const [gridKey, setGridKey] = useState(0);
    const [selectedId, setSelectedId] = useState(props.location.state?.id);
    const [isBackClicked, setBackClicked] = useState(props.location.state?.isBackClicked);
    const {userSessionDetails} = useContext(UserContext);
    const HAS_POP_UP_TEMPLATE_CREATE_ROLE = validate.validateRole(POPUP_ROLES['createTemplate']);
    const [openModal, setOpenModal] = useState(false);
    const [requestId, setRequestId] = useState(null);
    const [templateName, setTemplateName] = useState("");
    const HAS_PHARMACY_VIEW_ROLE = validate.validateRole(POPUP_ROLES.viewPharmaTemplates);
    const HAS_PATHLABS_VIEW_ROLE = validate.validateRole(POPUP_ROLES.viewLabTemplates);

    const defaultCriteria = {
        noOfRecords: 50,
        limitFrom: 0,
        fromDateCreated: dateFormat(new Date(), DEFAULT_START_DATE_TIME_FORMAT),
        toDateCreated : dateFormat(new Date(), DEFAULT_END_DATE_TIME_FORMAT),
        createdBy : HAS_POP_UP_TEMPLATE_CREATE_ROLE ? userSessionDetails.userId : null
    };

    const toggle = (showPreview) => {
        setShowPreview(showPreview);
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
            let popupSearchCriteria = {};
            if(validate.isNotEmpty(paramSearchCriteria?.applicableType)){
                popupSearchCriteria['applicableTypes'] = paramSearchCriteria.applicableTypes;
            }else {
                if (HAS_PHARMACY_VIEW_ROLE && !HAS_PATHLABS_VIEW_ROLE) {
                popupSearchCriteria['applicableTypes'] = [PHARMACY]
                } else if (HAS_PATHLABS_VIEW_ROLE && !HAS_PHARMACY_VIEW_ROLE) {
                popupSearchCriteria['applicableTypes'] = [PATHLABS]
                }
              }
            popupSearchCriteria['templateName'] = paramSearchCriteria ? paramSearchCriteria.templateLikeName : searchCriteria.templateLikeName;
            popupSearchCriteria['fromDateCreated'] = validate.isEmpty(startDate) ? null : dateFormat(startDate, DEFAULT_START_DATE_TIME_FORMAT);
            popupSearchCriteria['toDateCreated'] = validate.isEmpty(endDate) ? null : dateFormat(endDate, DEFAULT_END_DATE_TIME_FORMAT);
            popupSearchCriteria['requestIds'] = paramSearchCriteria ? paramSearchCriteria.requestId : searchCriteria.requestId;
            let templateRequestStatus = paramSearchCriteria.requestStatus || paramSearchCriteria.templateRequestStatus;
            popupSearchCriteria['templateRequestStatus'] = templateRequestStatus ? templateRequestStatus : (searchCriteria.templateRequestStatus ? searchCriteria.templateRequestStatus : []);
            if(!HAS_POP_UP_TEMPLATE_CREATE_ROLE){
                popupSearchCriteria['createdBy'] = paramSearchCriteria ? paramSearchCriteria.createdBy : searchCriteria.createdBy;
            } else {
                popupSearchCriteria['createdBy'] = defaultCriteria.createdBy;
            }
            popupSearchCriteria['limitFrom'] = paramSearchCriteria.limitFrom || defaultCriteria.limitFrom;
            popupSearchCriteria['noOfRecords'] = paramSearchCriteria.noOfRecords || defaultCriteria.noOfRecords;
            setSearchCriteria(popupSearchCriteria);
            getTemplateRequestsList(popupSearchCriteria);
        }

    }, [props.location, props.showModal])

    const getTemplateRequestsList = async (finalObj) => {
        if (!props.showModal) {
            let response = await popupService.getTemplateRequests(finalObj).catch((err) => setStackedToastContent({ toastMessage: "Unable to get template requests, please try again" }));
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
            return response.responseData;
        }
    };

    const approveOrRejectRequest = async (requestId, isApprove, comments) => {
        if (!props.showModal) {
            let formdata = {};
            formdata['requestId'] = requestId;
            if(!isApprove){
                formdata['comments'] = comments;
            }
            let response = isApprove ? await popupService.approvePopupTemplateRequest(formdata).catch((err) => setStackedToastContent({ toastMessage: "Unable to approve template request, please try again" })) : await popupService.rejectPopupTemplateRequest(formdata).catch((err) => setStackedToastContent({ toastMessage: "Unable to reject template request, please try again" }));
            ResponseHandler(setStackedToastContent).handleResponse(
                response,
                { successAlert: true },
                () => {
                    setStackedToastContent({ toastMessage: isApprove ? `Popup Template Request ${requestId} approved` : `Popup Template Request ${requestId} rejected`});;
                },
                (error) => {
                    setStackedToastContent({ toastMessage: error });
                }
            );
        }
    };

    const handleEditAction = (value) => {
        props.history.push({
            pathname: POPUP_URLS.editTemplate,
            state: { templateInfo: { ...value, isFromApprove: true }, searchCriteria: getActualSearchCriteria(searchCriteria) },
        });
    };

    const approveTemplate = async(row) => {
        setSelectedId(row.requestId);
        await approveOrRejectRequest(row.requestId, true, null);
        await getTemplateRequestsList(searchCriteria);
    }

    const rejectTemplate = async(id, comments) => {
        setSelectedId(id);
        await approveOrRejectRequest(id, false, comments);
        await getTemplateRequestsList(searchCriteria);
        setOpenModal(false);
    }

    const handleRejectAction = (row) => {
        setRequestId(row.requestId);
        setTemplateName(row.name);
        setOpenModal(true);
    }

    const handlePreviewAction = (value) => {
        setSelectedId(value.requestId);
        setBackClicked(true);
        setModalValue(value);
        toggle(true);
    };

    const callBackMapping = {
        renderActionColumn: ({ row }) => {
            return <>
                {((validate.validateRole(POPUP_ROLES.approveLabTemplates) && row.applicableType == "Path Labs") || (validate.validateRole(POPUP_ROLES.approvePharmaTemplates) && row.applicableType == "Pharmacy")) && row.status == "PENDING" && <ApproveIcon handleOnClick={() => approveTemplate(row, true)} tooltip={'Approve'} />}
                {validate.validateRole(POPUP_ROLES.createTemplate) && ['PENDING', 'REJECTED'].includes(row.status) && <EditIcon handleOnClick={() => handleEditAction(row)} tooltip={'Edit'} />}
                {((validate.validateRole(POPUP_ROLES.approveLabTemplates) && row.applicableType == "Path Labs") || (validate.validateRole(POPUP_ROLES.approvePharmaTemplates) && row.applicableType == "Pharmacy")) && row.status == "PENDING" && <DeleteIcon handleOnClick={() => handleRejectAction(row, false)} tooltip={'Reject'} />}
            </>
        },
        requestIdActionColumn: ({row}) => {
            return (<>
                <a className="btn btn-sm btn-link" onClick={() => handlePreviewAction(row)} id={`template_id_${row.requestId}`}>{row.requestId}</a>
                <UncontrolledTooltip placement="bottom" target={`template_id_${row.requestId}`}>
                    {'View Template'}
                </UncontrolledTooltip>
            </>);
        },
        renderStatusColumn : (props) => {
            let statusText = props.row.status === 'APPROVED' ? 'Approved' : props.row.status == 'PENDING' ? 'Pending' : 'Rejected';
            return <Badges key={"status"} className={'badge rounded-pill badge-' + (props.row.status === 'APPROVED' ? 'approved' : props.row.status == 'PENDING' ? 'pending' : 'rejected')} text={statusText} />
        },
        rowClassName: (row) => {
            if (selectedId == row.requestId) {
                setTimeout(() => {
                    setSelectedId(undefined);
                }, 2000);
                return isBackClicked ? "row-popup-opened" : "row-edited-success";
            }
        }
    }

    const handleSuccessResponse = (responseData) => {
        if (validate.isNotEmpty(responseData)) {
            setDataGrid(validate.isEmpty(responseData.dataGrid) ? [] : responseData.dataGrid);
            setDataSet(validate.isEmpty(responseData.dataSet) ? [] : responseData.dataSet);
            setTotalRecords(validate.isEmpty(responseData.totalRecords) ? 0 : responseData.totalRecords);
        }
    };

    const remoteDataFunction = async ({ startIndex, limit, filters }) => {
        if (totalRecords <= startIndex) {
            return { dataSet: dataSet, totalRowsCount: totalRecords };
        }
        let popupSearchCriteria = { ...searchCriteria, 'limitFrom': startIndex, 'noOfRecords': limit };
        setSearchCriteria(popupSearchCriteria);
        const responseData = await getTemplateRequestsList(popupSearchCriteria);
        setDataSet(startIndex > 0 ? [...dataSet, ...responseData.dataSet] : responseData.dataSet);
        return { dataSet: responseData?.dataSet ? responseData.dataSet : [], totalRowsCount: responseData?.totalRecords ? responseData.totalRecords : 0, status: true, };
    };

    return (
        <React.Fragment>
            <Wrapper>
                <HeaderComponent ref={headerRef} className="d-flex flex-column">
                    {isShowPreview ?
                        <div className="align-items-center border-bottom d-flex justify-content-between px-2 px-lg-3 py-1">
                            <p className="d-flex align-items-center flex-wrap mb-0">
                                <span>Template Request ID - </span>
                                <span className="fw-bold text-truncate me-3 ms-1">{modalValue.requestId}</span>
                               {validate.isNotEmpty(modalValue.applicableType) && <span><div className={`${DataGridHelper().getBadgeIcon(modalValue.applicableType)} badge rounded-pill`}>{modalValue.applicableType}</div></span>}
                            </p>
                            <div className=" d-flex align-items-center">
                                <KeyboardShortcuts buttonId="template-close-button" handleEsc setOpenModal={() => toggle(false)}/>
                                <button type="button" id="template-close-button" onClick={() => toggle(false)} className="rounded-5 icon-hover btn-link btn ">
                                    <span class="custom-close-btn icon-hover"></span>
                                </button>
                                <UncontrolledTooltip placement="bottom" target="template-close-button">
                                    Close
                                </UncontrolledTooltip>
                            </div>
                        </div> :
                        <div className="align-items-center border-bottom d-flex justify-content-between px-2 px-lg-3 py-1">
                            <p className="d-flex align-items-center flex-wrap mb-0">
                                <span>List of Popup Template Requests {searchCriteria && searchCriteria.fromDateCreated && searchCriteria.toDateCreated && <strong>from {dateFormat(searchCriteria.fromDateCreated, 'mmm dd, yyyy')} To {dateFormat(searchCriteria.toDateCreated, 'mmm dd, yyyy')}</strong>}</span>
                            </p>
                        </div>
                    }
                </HeaderComponent>
                <BodyComponent allRefs={{ headerRef }} loading={isLoading} className="body-height">
                    {isShowPreview && <PreviewTemplate requestId={modalValue.requestId} setShowPreview={setShowPreview} setStackedToastContent={setStackedToastContent}/>}
                    {!isShowPreview && (validate.isNotEmpty(dataGrid) ? (!isLoading && <div className="card h-100">
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
                    </div>) : (!isLoading && <NoDataFound text="No Data Found" {...props} searchButton/>))}
                    {openModal && <RejectPopupModal openModal={openModal} setOpenModal={setOpenModal} ModalName={"Template"} requestId={requestId} templateName={templateName} reject={rejectTemplate}/>}
                </BodyComponent>
            </Wrapper>
        </React.Fragment>
    )
}