import DynamicForm, { CustomSpinners, Filter, ViewRecords, withFormHoc } from "@medplus/react-common-components/DynamicForm";
import cronstrue from 'cronstrue';
import dateFormat from 'dateformat';
import React, { useContext, useEffect, useRef, useState } from "react";
import { Button } from "react-bootstrap";
import { POPUP_ROLES, ROLE_MRK_APPROVE_POPUP_CONFIGURATION_PHARMA } from "../../constants/MarketingRoles";
import { POPUP_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import PopupService from "../../services/PopupService";
import { BodyComponent, FooterComponent, HeaderComponent, Wrapper } from "../common/CommonStructure";
import CustomDateTimePicker from "../common/CustomDateTimePicker";
import { AlertContext } from "../Contexts/UserContext";
import RejectPopupModal from "./RejectPopUpModal";
import ViewPathlabConfiguration from "./ViewPathlabConfiguration";

let obj = {
    "htmlElementType": "FORM",
    "id": "toDateForm",
    "label": null,
    "name": null,
    "value": null,
    "className": null,
    "readOnly": false,
    "disabled": false,
    "autofocus": false,
    "required": false,
    "style": null,
    "attributes": null,
    "message": null,
    "htmlActions": null,
    "elementSize": null,
    "defaultValue": null,
    "helperText": null,
    "labelClassName": null,
    "htmlGroups": [
        {
            "htmlElementType": "ELEMENTGROUP",
            "id": "dateRangeGrp",
            "label": null,
            "name": null,
            "value": null,
            "className": "row g-3",
            "readOnly": false,
            "disabled": false,
            "autofocus": false,
            "required": false,
            "style": null,
            "attributes": null,
            "message": null,
            "htmlActions": null,
            "elementSize": null,
            "defaultValue": null,
            "helperText": null,
            "labelClassName": null,
            "groups": null,
            "groupElements": []
        }
    ]
}

const ViewConfiguration = ({ helpers, ...props }) => {
    const popupService = PopupService();
    const validate = Validate();
    const [configurationInfo, setConfigurationInfo] = useState();
    const [isLoading, setLoading] = useState(false);
    const isApprover = validate.validateRole([ROLE_MRK_APPROVE_POPUP_CONFIGURATION_PHARMA]);
    const [showApproveOrRejectButton, setShowApproveOrRejectButton] = useState(isApprover);
    const [filterRegionText, setFilterRegionText] = useState([]);
    const [filteredRegions, setFilteredRegions] = useState([]);
    const [approveLoader, setApproveLoader] = useState(false);
    const [rejectLoader, setRejectLoader] = useState(false);
    const { setStackedToastContent } = useContext(AlertContext);
    const [toDate, setToDate] = useState(new Date());
    const [tempToDate, setTempToDate] = useState(new Date());
    const [openModal, setOpenModal] = useState(false);
    const [productIds,setProductIds] = useState([]);
    const [selectedProductTriggerValue,setSelectedProductTriggerValue] = useState('');
    const [filterProductText, setFilterProductText] = useState([]);
    const [filteredProducts, setFilteredProducts] = useState([]);
    
    const requestId = props?.match?.params?.reqId;
    const configId = props?.match?.params?.configId;
    const isEdit = validate.validateRole(POPUP_ROLES['createConfiguration']) ? props?.location?.state?.isEdit : false;

    const headerRef = useRef(null);
    const footerRef = useRef(null);

    useEffect(() => {
        if (validate.isNotEmpty(requestId)) {
            getConfigurationByRequestId(requestId);
        }
        if (validate.isNotEmpty(configId)) {
            getConfigurationByConfigId(configId);
        }
    }, [configId, requestId]);

    useEffect(() => {
        if (validate.isNotEmpty(productIds)) {
            const filteredKeys = productIds.filter(key => {
                return filterProductText.some(text => key.toUpperCase().includes(text.toUpperCase()));
            });
            const filteredProductsInfo = [];
            filteredKeys.forEach(key => {
                filteredProductsInfo.push(key);
            });
            setFilteredProducts(filteredProductsInfo);
        }
    }, [filterProductText]);

    useEffect(() => {
        if (validate.isNotEmpty(productIds) && productIds.length <= 15) {
            setFilterProductText([]);
        }
    }, [filteredProducts]);

    useEffect(() => {
        if (validate.isNotEmpty(configurationInfo?.popupConfiguration?.regions)) {
            const filteredKeys = configurationInfo.popupConfiguration.regions.filter(key => {
                return filterRegionText.some(text => key.toUpperCase().includes(text.toUpperCase()));
            });
            const filteredRegionsInfo = [];
            filteredKeys.forEach(key => {
                filteredRegionsInfo.push(key);
            });
            setFilteredRegions(filteredRegionsInfo);
        }
    }, [filterRegionText]);

    useEffect(() => {
        if (validate.isNotEmpty(configurationInfo?.popupConfiguration?.regions) && configurationInfo.popupConfiguration.regions.length <= 15) {
            setFilterRegionText([]);
        }
    }, [filteredRegions]);

    const getConfigurationByConfigId = (configId) => {
        popupService.getConfigRequestsByConfigId({ configId }).then(async res => {
            if (validate.isNotEmpty(res) && validate.isNotEmpty(res.responseData) && "SUCCESS" == res.message) {
                let popupConfiguration = res.responseData;
                let configRequest = {};
                configRequest['popupConfiguration'] = popupConfiguration;
                configRequest['configId'] = popupConfiguration.configId;
                configRequest['popUpConfigurationName'] = popupConfiguration.popUpConfigurationName;
                configRequest['applicableType'] = popupConfiguration.applicableType;
                setConfigurationInfo(configRequest);
                setToDate(new Date(popupConfiguration.toDate));
                setTempToDate(new Date(popupConfiguration.toDate));
                setSelectedProductTriggerValue("PRODUCT" == res?.responseData?.triggerType && res.responseData?.triggerValue ? res.responseData.triggerValue : '');
                if (validate.isNotEmpty(res.responseData?.productIds)) {
                    setProductIds(res.responseData.productIds);
                }
            } else {
                setConfigurationInfo({});
                setProductIds([]);
                setSelectedProductTriggerValue('');
                setToDate(new Date());
                setTempToDate(new Date());
            }
        }).catch(err => {
            console.log('Error while fetching configuration request ', err);
            setConfigurationInfo({});
            setToDate(new Date());
            setTempToDate(new Date());
        })
    }

    const getConfigurationByRequestId = (requestId) => {
        popupService.getConfigRequestsOnRequestId({ requestId }).then(async res => {
            if (validate.isNotEmpty(res) && validate.isNotEmpty(res.responseData) && "SUCCESS" == res.message) {
                setConfigurationInfo(res.responseData);
                setSelectedProductTriggerValue("PRODUCT" == res?.responseData?.popupConfiguration?.triggerType && res.responseData?.popupConfiguration?.triggerValue ? res.responseData.popupConfiguration.triggerValue : '');
                if (validate.isNotEmpty(res.responseData?.popupConfiguration?.productIds)) {
                    setProductIds(res.responseData.popupConfiguration.productIds);
                } if ('PENDING' != res.responseData.configRequestStatus) {
                    setShowApproveOrRejectButton(false);
                }
            } else {
                setConfigurationInfo({});
                setProductIds([]);
                setSelectedProductTriggerValue('');
            }
        }).catch(err => {
            console.log('Error while fetching configuration request ', err);
            setConfigurationInfo({});
            setProductIds([]);
        })
    }

    const firstLetterCapitalize = text => {
        if (validate.isNotEmpty(text)) {
            return text.toLowerCase()[0].toUpperCase() + text.slice(1).toLowerCase();
        }
    }

    const approveConfiguration = (requestId) => {
        setApproveLoader(true);
        popupService.approveConfiguration({ requestId }).then(res => {
            if(validate.isNotEmpty(res)){
                setShowApproveOrRejectButton("SUCCESS" == res?.statusCode ? false : true);
                setStackedToastContent({toastMessage: validate.isNotEmpty(res.message) ? res.message : ""});
            }
            setApproveLoader(false);
        }).catch(err => {
            setShowApproveOrRejectButton(true);
            setApproveLoader(false);
            console.log(`Error while Approving Configuration ${requestId}`, err);
        })
    }

    const rejectConfiguration = (requestId, comments) => {
        setRejectLoader(true);
        popupService.rejectConfiguration({ requestId: requestId, comments: comments }).then(res => {
            if(validate.isNotEmpty(res)){
                setShowApproveOrRejectButton("SUCCESS" == res?.statusCode ? false : true);
                setStackedToastContent({toastMessage: validate.isNotEmpty(res.message) ? res.message : ""});
            }
            setRejectLoader(false);
            setOpenModal(false);
        }).catch(err => {
            setShowApproveOrRejectButton(true);
            setRejectLoader(false);
            setOpenModal(false);
            console.log(`Error while Rejecting Configuration ${requestId}`, err);
        })
    }

    const filterRegion = (event) => {
        let inputRegionTxt = event.target.value?.toUpperCase();
        event.target.value = inputRegionTxt;
        setFilterRegionText(inputRegionTxt.split(",").map(value => value.trim()).filter(key => key != ""))
    }

    const onClearRegionFilter = () => {
        setFilterRegionText([]);
        const regionInput = document.getElementById('StoreIds');
        if (regionInput) {
            regionInput.value = '';
        }
    }

    const filterProduct = (event) => {
        let inputProductTxt = event.target.value?.toUpperCase();
        event.target.value = inputProductTxt;
        setFilterProductText(inputProductTxt.split(",").map(value => value.trim()).filter(key => key != ""));
    }

    const onClearProductFilter = () => {
        setFilterProductText([]);
        const productInput = document.getElementById('productIds');
        if (productInput) {
            productInput.value = '';
        }
    }

    const getMaxDate = () => {
        let date = new Date(toDate.getFullYear(), toDate.getMonth(), toDate.getDate());
        date.setHours(toDate.getHours(), toDate.getMinutes(), toDate.getSeconds(), toDate.getMilliseconds());
        return date;
    }

    const updateInitialValues = () => {
        if (validate.isNotEmpty(configurationInfo?.popupConfiguration) && validate.isNotEmpty(configurationInfo?.popupConfiguration?.toDate)) {
            helpers.updateValue(configurationInfo?.popupConfiguration?.toDate, 'dateRangeGrp');
        }
    }

    const handleUpdate = async (event) => {
        event.preventDefault();
        let formData = new FormData();
        let configInfo = {};
        let popupConfiguration = configurationInfo.popupConfiguration;
        configInfo['configId'] = popupConfiguration.configId;
        configInfo['toDate'] = tempToDate.getTime();
        formData.append('popupPathlabConfiguration', JSON.stringify(configInfo));
        const response = await popupService.updateConfiguration(formData);
        setLoading(true);
        ResponseHandler(setStackedToastContent).handleResponse(response, {}, () => {
            setLoading(false);
            setStackedToastContent({ toastMessage: 'Popup Configuration update request generated successfully.' });
            props.history.push({ pathname: POPUP_URLS.viewConfigurations, state: {
                searchCriteria: props.location.state?.searchCriteria,
                id: configId
              } });
        }, (error) => {
            setLoading(false);
            setStackedToastContent({ toastMessage: error });
        })
    }

    const customHtmlMap = {
        'dateRangeGrp': [['INSERT_IN', () => {
            return (
                <div className="d-flex row g-3">
                    <CustomDateTimePicker handleDateChange={setTempToDate} title={'To Date'} defaultDateTime={toDate} pickerProps={{ readOnly: false }} minDate={new Date()} maxDate={getMaxDate()} />
                    {<div className="col-2">
                        <button disabled={isLoading} style={{ height: '50px' }} onClick={(event) => handleUpdate(event)} className="btn btn-primary mt-4">
                            {false ? <CustomSpinners spinnerText={'Update'} className={"spinner-position"} innerClass={"invisible"} /> : 'Update'}
                        </button></div>}
                </div>
            )
        }]]
    }

    const observersMap = {
        'toDateForm': [['load', updateInitialValues]],
    }

    const goBack = () => {
        props.history.push({
            pathname: validate.isNotEmpty(requestId) ? POPUP_URLS.viewConfigurationRequests : POPUP_URLS.viewConfigurations,
            state: {
                searchCriteria: props.location.state?.searchCriteria,
                id: configId,
                isBackClicked: true
            },
        });
    }

    const redirectToEditConfiguration = (applicableType, reqId) => {
        if (validate.isNotEmpty(applicableType) && validate.isNotEmpty(reqId)) {
            props.history.push({
                pathname: `${POPUP_URLS.editConfiguration}/request/${reqId}`,
                state:{isEdit:true, applicableType: applicableType, searchCriteria: props.location.state?.searchCriteria},
            });
        }
    }

    return (
        <Wrapper>
            {"pathlabs" == props?.location?.state?.applicableType?.toLowerCase() ? <ViewPathlabConfiguration {...props} /> :
            <React.Fragment>
                <HeaderComponent ref={headerRef} className="d-flex align-items-center px-1 py-2 border-bottom">
                <Button variant=" " className="btn-link icon-hover me-3" onClick={() => { goBack() }}> <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                    <g id="leftarrow_black_icon_18px" transform="translate(-48.941 -316.765)">
                        <rect id="BG_Guide" data-name="BG Guide" width="18" height="18" transform="translate(48.941 316.765)" fill="none" />
                        <path id="Path_22927" data-name="Path 22927" d="M56.566,319.236a.686.686,0,0,0-.448.178l-6.977,6.53a.7.7,0,0,0,0,.984l6.977,6.44a.709.709,0,0,0,.984-.089.7.7,0,0,0,0-.984l-5.546-5.188H66.226a.716.716,0,0,0,0-1.431H51.557l5.635-5.188a.635.635,0,0,0,.269-.536,1.275,1.275,0,0,0-.179-.537A4.04,4.04,0,0,0,56.566,319.236Z" transform="translate(0 -0.471)" fill="#080808" />
                    </g>
                </svg></Button>
                {requestId && <p className="mb-0">Configuration Request - <strong>{requestId}</strong></p>}
                {configId && <p className="mb-0">Configuration ID - <strong>{configId}</strong></p>}
                {/* {(validate.validateRole(POPUP_ROLES.createPharmaConfiguration) && !validate.validateRole(POPUP_ROLES.approvePharmaConfigurations) && validate.isNotEmpty(configurationInfo) && ['PENDING','REJECTED'].includes(configurationInfo.configRequestStatus)) && <div style={{ marginLeft: "auto" }}>
                    <button style={{ height: '50px' }} className={"btn btn-primary"} onClick={() => (redirectToEditConfiguration(configurationInfo.applicableType, requestId))}>
                        {false ? <CustomSpinners spinnerText={'Edit'} className={"spinner-position"} innerClass={"invisible"} /> : 'Edit'}
                    </button>
                </div>} */}
            </HeaderComponent>
            <BodyComponent className="body-height" allRefs={{ headerRef, footerRef }}  >
                {
                    validate.isNotEmpty(configurationInfo) &&
                    <>
                        {validate.isNotEmpty(configurationInfo.applicableType) && <div className="mb-4"><label className="mb-2 custom-fieldset">Applicable Type</label><p className="mb-0 "><mark className="px-2">{firstLetterCapitalize(configurationInfo.applicableType)}</mark></p></div>}

                        {validate.isNotEmpty(configurationInfo.popupConfiguration) && <label className="mb-2 custom-fieldset">Template Information</label>}
                        <div className="row g-3 mb-4">
                            {validate.isNotEmpty(configurationInfo.popUpConfigurationName) && <div className="d-flex flex-column col-3"><p className="mb-0 font-12 text-secondary">Popup Configuration Name</p> <p className="mb-0 font-14">{configurationInfo.popUpConfigurationName}</p></div>}
                            {validate.isNotEmpty(configurationInfo.popupConfiguration) &&
                                <React.Fragment>
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.templateId) && <div className="d-flex flex-column col-3"><label className=" font-12 text-secondary">Template ID</label> <p className="mb-0 font-14">{configurationInfo.popupConfiguration.templateId}</p></div>}
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.templateName) && <div className="d-flex flex-column col-3"><label className="font-12 text-secondary">Template Name</label> <p className="mb-0 font-14">{configurationInfo.popupConfiguration.templateName}</p></div>}
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.status) && <div className="d-flex flex-column col-3"><label className=" font-12 text-secondary">Status</label> <p className="mb-0 font-14">{firstLetterCapitalize(configurationInfo.popupConfiguration.status)}</p></div>}
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.fromDate) && <div className="d-flex flex-column col-3"><label className=" font-12 text-secondary">From Date</label> <p className="mb-0 font-14">{dateFormat(configurationInfo.popupConfiguration.fromDate, 'mmm d, yyyy HH:MM')}</p></div>}
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.toDate) && validate.isNotEmpty(requestId) && <div className="d-flex flex-column col-3"><label className=" font-12 text-secondary">To Date</label> <p className="mb-0 font-14">{dateFormat(configurationInfo.popupConfiguration.toDate, 'mmm d, yyyy HH:MM')}</p></div>}
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.toDate) && validate.isNotEmpty(configId) && !isEdit && <div className="d-flex flex-column col-3"><label className=" font-12 text-secondary">To Date</label> <p className="mb-0 font-14">{dateFormat(configurationInfo.popupConfiguration.toDate, 'mmm d, yyyy HH:MM')}</p></div>}
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration.toDate) && validate.isNotEmpty(configId) && isEdit && <DynamicForm formJson={obj} helpers={helpers} customHtml={customHtmlMap} observers={observersMap} />}
                                </React.Fragment>
                            }
                        </div>
                        {validate.isNotEmpty(configurationInfo.popupConfiguration?.regions) &&
                            <>
                                {configurationInfo.popupConfiguration.regions.length > 15 && <Filter objCount={configurationInfo.popupConfiguration.regions.length} handleFilter={filterRegion} clearFilteredData={onClearRegionFilter} removeObj={{}} disableFields={true} filteredDataCount={filterRegion.length} filterText={filterRegionText} placeHolderText={"Filter Region"} id={'StoreIds'} {...props} />}
                                {filterRegionText?.length > 0
                                    ?
                                    <div className='mb-2'>
                                        <ViewRecords info={filteredRegions} props={props} displayText={"Regions Information"} />
                                    </div>
                                    :
                                    <div className='mb-2'>
                                        <ViewRecords info={configurationInfo.popupConfiguration?.regions} props={props} displayText={"Regions Information"} />
                                    </div>
                                }
                            </>
                        }
                        {
                            "TIME_INTERVAL" == configurationInfo?.popupConfiguration?.triggerType ? 
                                <>
                                    {validate.isNotEmpty(configurationInfo.popupConfiguration?.triggerValue) &&
                                        <div className="mb-2">
                                            <p className="custom-fieldset mb-2">Selected Predefined Time Interval - {cronstrue.toString(configurationInfo.popupConfiguration.triggerValue)}</p>
                                        </div>
                                    }
                                </> :
                                <>
                                  {validate.isNotEmpty(productIds) &&
                                    <React.Fragment>
                                        <label className='custom-fieldset mb-2 col-2'>Selected Product Trigger Type</label><p className="mb-0 font-14">{selectedProductTriggerValue}</p>
                                            <>
                                                {productIds.length > 15 && <Filter objCount={productIds.length} handleFilter={filterProduct} clearFilteredData={onClearProductFilter} removeObj={{}} disableFields={true} filteredDataCount={filterProduct.length} filterText={filterProductText} placeHolderText={"Filter Product"} id={'productIds'} {...props} />}
                                                {filterProductText?.length > 0
                                                    ?
                                                    <div className='mb-2'>
                                                        <ViewRecords info={filteredProducts} props={props} displayText={"Products Information"} />
                                                    </div>
                                                    :
                                                    <div className='mb-2'>
                                                        <ViewRecords info={productIds} props={props} displayText={"Products Information"} />
                                                    </div>
                                                }
                                            </>
                                    </React.Fragment>
                                }  
                                </>
                        }
                         {validate.isNotEmpty(configurationInfo.comments) && <>
                            <p className="mb-2 custom-fieldset">Comment</p>
                            <div className="d-flex flex-wrap gap-3 mb-4" style={{ maxHeight: '200px', overflowY: 'auto' }}>
                                {configurationInfo.comments}
                            </div>
                        </>}
                    </>
                }
                {openModal && <RejectPopupModal openModal={openModal} setOpenModal={setOpenModal} ModalName={"Configuration"} requestId={requestId} templateName={configurationInfo?.popupConfiguration?.templateName} configurationName={configurationInfo?.popUpConfigurationName} reject={rejectConfiguration}/>}
            </BodyComponent>
            {showApproveOrRejectButton && !validate.validateRole(POPUP_ROLES.createPharmaConfiguration) && validate.isNotEmpty(requestId) && <FooterComponent ref={footerRef} className="d-flex justify-content-end border-top gap-3 p-2">
                    <React.Fragment>
                        {!rejectLoader ? <button className="btn btn-sm brand-secondary py-2 px-4" onClick={() => {  setOpenModal(true) }}>Reject</button> : <CustomSpinners spinnerText={"Reject"} className={" spinner-position"} innerClass={"invisible"} />}
                        {!approveLoader ? <button className="btn btn-sm btn-success py-2 px-4" onClick={() => { approveConfiguration(configurationInfo.requestId) }}>Approve</button> : <CustomSpinners spinnerText={"Approve"} className={" spinner-position"} innerClass={"invisible"} />}
                    </React.Fragment>
            </FooterComponent>
                }
            </React.Fragment>
            }
        </Wrapper>
    )
}

export default withFormHoc(ViewConfiguration);