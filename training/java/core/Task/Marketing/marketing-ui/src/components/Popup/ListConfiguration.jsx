import CommonDataGrid, { Badges, ChangeType, CloneIcon, EditIcon, SelectFilter } from "@medplus/react-common-components/DataGrid";
import { withFormHoc } from "@medplus/react-common-components/DynamicForm";
import cronstrue from 'cronstrue';
import dateFormat from 'dateformat';
import { useContext, useEffect, useRef, useState } from "react";
import { UncontrolledTooltip } from "reactstrap";
import { PATHLABS, PHARMACY } from "../../constants/MarketingConstant";
import { POPUP_ROLES } from "../../constants/MarketingRoles";
import { DEFAULT_END_DATE_TIME_FORMAT, DEFAULT_START_DATE_TIME_FORMAT, PromotionStatus } from "../../constants/PromotionConstants";
import { POPUP_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import PopupService from "../../services/PopupService";
import { BodyComponent, HeaderComponent, Wrapper } from "../common/CommonStructure";
import NoDataFound from "../common/NoDataFound";
import { AlertContext } from "../Contexts/UserContext";

const ListConfiguration = (props) => {
    const validate = Validate();
    const headerRef = useRef(null);
    const [isLoading, setLoading] = useState(true);
    const gridValueRef = useRef(null);
    const [dataGrid, setDataGrid] = useState([]);
    const [dataSet, setDataSet] = useState([]);
    const [searchCriteria, setSearchCriteria] = useState({});
    const popupService = PopupService();
    const { setStackedToastContent } = useContext(AlertContext);
    const [key, setKey] = useState(0);
    const [selectedId, setSelectedId] = useState(props.location.state?.id);
    const [totalRecords, setTotalRecords] = useState(0);
    const [ageGroups,setAgeGroups] = useState({});
    const HAS_PHARMACY_VIEW_ROLE = validate.validateRole(POPUP_ROLES.viewPharmaConfigurations);
    const HAS_PATHLABS_VIEW_ROLE = validate.validateRole(POPUP_ROLES.viewLabConfigurations);

    const defaultCriteria = {
        noOfRecords: 50,
        limitFrom: 0,
        fromDateCreated: dateFormat(new Date(), DEFAULT_START_DATE_TIME_FORMAT),
        toDateCreated : dateFormat(new Date(), DEFAULT_END_DATE_TIME_FORMAT)
    };

    const getActualSearchCriteria = (criteria) => {
        const { noOfRecords, limitFrom, ...rest } = criteria;
        return rest;
    }

    useEffect(() => {
        if (validate.isNotEmpty(gridValueRef) && validate.isNotEmpty(gridValueRef.current) && selectedId) {
            gridValueRef.current.scrollToRow(dataSet.findIndex(data => (data.id == selectedId)));
        }
    }, [dataSet])

    useEffect(() => {
        let paramSearchCriteria = props.location.state?.searchCriteria || {};
        if (validate.isEmpty(paramSearchCriteria) || !validate.isEqualObject(getActualSearchCriteria(paramSearchCriteria), getActualSearchCriteria(searchCriteria))) {
            setKey(key + 1);
            let configureSearchCriteria = {};
            let startDate = validate.isNotEmpty(paramSearchCriteria) ? paramSearchCriteria.fromDateCreated : ((validate.isNotEmpty(searchCriteria)&& validate.isNotEmpty(searchCriteria.fromDateCreated)) ? searchCriteria.fromDateCreated : defaultCriteria.fromDateCreated);
            let endDate = validate.isNotEmpty(paramSearchCriteria) ? paramSearchCriteria.toDateCreated : ((validate.isNotEmpty(searchCriteria)&& validate.isNotEmpty(searchCriteria.toDateCreated)) ? searchCriteria.toDateCreated : defaultCriteria.toDateCreated);
            configureSearchCriteria['fromDateCreated'] = validate.isEmpty(startDate) ? null : dateFormat(startDate, DEFAULT_START_DATE_TIME_FORMAT);
            configureSearchCriteria['toDateCreated'] = validate.isEmpty(startDate) ? null : dateFormat(endDate, DEFAULT_END_DATE_TIME_FORMAT);
            configureSearchCriteria['limitFrom'] = paramSearchCriteria.limitFrom || defaultCriteria.limitFrom;
            configureSearchCriteria['noOfRecords'] = paramSearchCriteria.noOfRecords || defaultCriteria.noOfRecords;
            configureSearchCriteria['runOnMaster'] = validate.isNotEmpty(selectedId);
            if(validate.isNotEmpty(paramSearchCriteria.applicableType)){
                configureSearchCriteria['applicableTypes'] = [paramSearchCriteria.applicableType];
            }else{
                if(HAS_PHARMACY_VIEW_ROLE && !HAS_PATHLABS_VIEW_ROLE){
                    configureSearchCriteria['applicableTypes'] = [PHARMACY]
                }else if(HAS_PATHLABS_VIEW_ROLE && !HAS_PHARMACY_VIEW_ROLE){
                    configureSearchCriteria['applicableTypes'] = [PATHLABS]
                }
            }
            if(validate.isNotEmpty(paramSearchCriteria.triggerType)){
                configureSearchCriteria['triggerTypes'] = "product" == paramSearchCriteria.triggerType ? ["PRODUCT"] : ["TIME_INTERVAL"];
            }
            if(validate.isNotEmpty(paramSearchCriteria.status)){
                configureSearchCriteria['popUpConfigStatuses'] = [paramSearchCriteria.status];
                configureSearchCriteria.status = paramSearchCriteria.status;
            }
            configureSearchCriteria['configName'] = paramSearchCriteria ? paramSearchCriteria.configurationLikeName : searchCriteria.configurationLikeName;
            setSearchCriteria(configureSearchCriteria);
            let formData = new FormData();
            formData.append("searchCriteria", JSON.stringify(configureSearchCriteria));
            getConfigurations(formData);
        }
    }, [props.location, props.showModal]);

    const getConfigurations = async (formData) => {
        let response = await popupService.getConfigurations(formData).catch((error) =>
            setStackedToastContent({ toastMessage: "Something went wrong, please try again" }));
        ResponseHandler(setStackedToastContent).handleResponse(response, {}, (response) => {
            setDataGrid(response.dataGrid);
            setDataSet(response.dataSet);
            setTotalRecords(response.totalRecords);
            setAgeGroups(validate.isNotEmpty(response?.ageGroups) ? response?.ageGroups : {})
        }, (error) => {
            setStackedToastContent({ toastMessage: error })
        })
        setLoading(false);
        return response.responseData;
    }

    const redirectToEditConfiguration = (configId, applicableType) => {
        props.history.push({ pathname: POPUP_URLS.editConfiguration + `/${configId}`, state: { isEdit: true, ageGroups: ageGroups, searchCriteria: searchCriteria, applicableType: applicableType}});
    }

    const redirectToCloneConfiguration = (configId, applicableType) => {
        if (validate.isNotEmpty(configId)) {
            popupService.getConfigRequestsByConfigId({ configId }).then(res => {
                props.history.push({ pathname: POPUP_URLS.cloneConfiguration, state: { popupConfigurationInfo: res.responseData, isClone: true, ageGroups:ageGroups, searchCriteria:searchCriteria, applicableType: applicableType } });
            })
        }
    }

    const handleClick = (configId, applicableType) => {
        props.history.push({ pathname: POPUP_URLS.viewConfiguration + `/${configId}`, state: { isEdit: false, ageGroups:ageGroups, searchCriteria:searchCriteria, applicableType: applicableType } });
    }

    const callBackMapping = {
        renderActionColumn: (props) => {
            return (
                <>
                    {validate.validateRole(POPUP_ROLES['createConfiguration']) && ![PromotionStatus.inActive,PromotionStatus.closed].includes(props.row.status) && <EditIcon tooltip={"Edit"} handleOnClick={() => redirectToEditConfiguration(props.row?.id, props.row?.applicableType)} id={props.row.id}/>}
                    {validate.validateRole(POPUP_ROLES['createConfiguration']) && <CloneIcon tooltip={"Clone"} handleOnClick={() => redirectToCloneConfiguration(props.row?.id, props.row?.applicableType)} id={props.row.id} />}
                </>
            );
        },
        rowClassName: (row) => {
            if (selectedId == row.id) {
                setTimeout(() => {
                    setSelectedId(undefined);
                }, 2000);
                return props.location.state?.isBackClicked ? "row-popup-opened" : "row-edited-success";
            }
        },
        statusColumn: (props) => {
            let status = props.row.status;
            let statusText = status === PromotionStatus.active ? 'Active' : status == PromotionStatus.inActive ? 'In Active' : 'Closed'
            return <Badges key={"status"} className={'badge rounded-pill badge-' + (status === PromotionStatus.active ? 'approved' : status == PromotionStatus.inActive ? 'rejected' : 'Cancelled')} text={statusText} />
        },
        statusFilter: (props) => {
            let itemsMap = {
                'A': 'Active',
                'I': 'In Active',
                'C': 'Closed'
            }
            return (
                <SelectFilter id={props.column.key} tooltip={true} isClearable itemsMap={itemsMap} {...props} />
            )
        },
        triggerTypeColumn: (props) => {
            return prepareDefinedTriggerType(props.row.triggerType);
        },
        triggerValueColumn: (props) => {
            return props.row.triggerType == "DEFINED_TRIGGER" ? toCamelCase(props.row.triggerValue) : "TIME_INTERVAL" === props.row.triggerType ? cronstrue.toString(props.row.triggerValue) : toCamelCase(props.row.triggerValue);
        },
        applicableTypeColumn: (props) => {
            return props.row.applicableType == "PATHLABS" ? "Path Labs" : "Pharmacy";
        },
        "configurationIdColumn" : (props) => {
            return <>
            {props.row.applicableType == "PATHLABS" ? <>
                <a className="btn btn-link btn-sm" onClick={() => { handleClick(props.row.id, props.row.applicableType) }} id={"configId"}>{props.row.id}</a>
                <UncontrolledTooltip placement="bottom" target={"configId"}>
                    {'View Configuration'}
                </UncontrolledTooltip> </>:<p className="px-2"> {props.row.id}</p>}
            </>
        },
    };

    const toCamelCase = (word) => {
        if (validate.isEmpty(word)) {
            return "";
        }
        return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();


    }

    const prepareDefinedTriggerType = (type) => {
        switch(type) {
            case "TIME_INTERVAL" : 
                return "Time Interval";
            case "PRODUCT" : 
                return "Product";
            case "DEFINED_TRIGGER" : 
                return "Defined Trigger";
            default:
                return undefined;
        }
    }

    const remoteDataFunction = async ({ startIndex, limit }) => {
        if (totalRecords <= startIndex) {
            return { dataSet: dataSet, totalRowsCount: totalRecords };
        }
        let popupSearchCriteria = { ...searchCriteria, 'limitFrom': startIndex, 'noOfRecords': limit };
        setSearchCriteria(popupSearchCriteria);
        let formData = new FormData();
        formData.append("searchCriteria", JSON.stringify(popupSearchCriteria));
        const responseData = await getConfigurations(formData);
        setDataSet(startIndex > 0 ? [...dataSet, ...responseData.dataSet] : responseData.dataSet);
        return { dataSet: responseData?.dataSet ? responseData.dataSet : [], totalRowsCount: responseData?.totalRecords ? responseData.totalRecords : 0, status: true };
    }

    const handleOnDataChangeCallBack = ({ changeType }) => {
        if (changeType === ChangeType.SORT_INFO) {
            gridValueRef?.current?.scrollToRow(0);
        }
    }

    return (
        <Wrapper>
            <HeaderComponent ref={headerRef} className="border-bottom py-2 px-2 ">
                List of Popup Configurations {searchCriteria && searchCriteria.fromDateCreated && searchCriteria.toDateCreated && <strong>from {dateFormat(searchCriteria.fromDateCreated, 'mmm dd, yyyy')} To {dateFormat(searchCriteria.toDateCreated, 'mmm dd, yyyy')}</strong>}
            </HeaderComponent>
            <BodyComponent allRefs={{ headerRef }} loading={isLoading} className="body-height">
                {(validate.isNotEmpty(dataGrid) && validate.isNotEmpty(dataSet) && !isLoading)
                    ? (
                        <div className="card h-100">
                            <div className="card-body p-0">
                                <CommonDataGrid key={key}
                                    ref={gridValueRef}
                                    {...dataGrid}
                                    dataSet={dataSet}
                                    callBackMap={callBackMapping}
                                    remoteDataFunction={remoteDataFunction}
                                    noRowsFallback={<NoDataFound text="No Data Found" {...props} grid />}
                                    onDataChangeCallBack={handleOnDataChangeCallBack}
                                />
                            </div>
                        </div>
                    )
                    : !isLoading && (<NoDataFound text="No Data Found" {...props} searchButton />)}
            </BodyComponent>
        </Wrapper>
    );
}
export default withFormHoc(ListConfiguration);