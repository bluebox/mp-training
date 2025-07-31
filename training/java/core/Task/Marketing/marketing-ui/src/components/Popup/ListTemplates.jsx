import CommonDataGrid, { ChangeType, CloneIcon, EditIcon } from "@medplus/react-common-components/DataGrid";
import dateFormat from 'dateformat';
import { useContext, useEffect, useRef, useState } from "react";
import { UncontrolledTooltip } from "reactstrap";
import { POPUP_ROLES } from "../../constants/MarketingRoles";
import { DEFAULT_END_DATE_TIME_FORMAT, DEFAULT_START_DATE_TIME_FORMAT } from "../../constants/PromotionConstants";
import { POPUP_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import PopupService from "../../services/PopupService";
import { AlertContext } from "../Contexts/UserContext";
import { BodyComponent, HeaderComponent, Wrapper, } from "../common/CommonStructure";
import NoDataFound from "../common/NoDataFound";
import PreviewTemplate from "./PreviewTemplate";
import DataGridHelper from "../common/DataGridHelper";
import KeyboardShortcuts from "../../helpers/KeyboardShortcuts";
import { PATHLABS, PHARMACY } from "../../constants/MarketingConstant";

export default function ListTemplates(props) {
  const headerRef = useRef(null);
  const popupService = PopupService();
  const { setStackedToastContent } = useContext(AlertContext);
  const validate = Validate();
  const [dataGrid, setDataGrid] = useState(undefined);
  const [dataSet, setDataSet] = useState([]);
  const [isShowPreview, setShowPreview] = useState(false);
  const [modalValue, setModalValue] = useState({});
  const [isLoading, setLoading] = useState(true);
  const [searchCriteria, setSearchCriteria] = useState({});
  const gridValueRef = useRef(null);
  const [gridKey, setGridKey] = useState(0);
  const [selectedId, setSelectedId] = useState(props.location.state?.id);
  const [totalRecords, setTotalRecords] = useState(0);
  const [isBackClicked, setBackClicked] = useState(props.location.state?.isBackClicked);
  const HAS_PHARMACY_VIEW_ROLE = validate.validateRole(POPUP_ROLES.viewPharmaTemplates);
  const HAS_PATHLABS_VIEW_ROLE = validate.validateRole(POPUP_ROLES.viewLabTemplates);

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
    let paramSearchCriteria = props.location.state?.searchCriteria || {};
    let startDate = validate.isNotEmpty(paramSearchCriteria) ? paramSearchCriteria.fromDateCreated : ((validate.isNotEmpty(searchCriteria)&& validate.isNotEmpty(searchCriteria.fromDateCreated)) ? searchCriteria.fromDateCreated : defaultCriteria.fromDateCreated);
    let endDate = validate.isNotEmpty(paramSearchCriteria) ? paramSearchCriteria.toDateCreated : ((validate.isNotEmpty(searchCriteria)&& validate.isNotEmpty(searchCriteria.toDateCreated)) ? searchCriteria.toDateCreated : defaultCriteria.toDateCreated);
	if (validate.isEmpty(paramSearchCriteria) || !validate.isEqualObject(getActualSearchCriteria(paramSearchCriteria), getActualSearchCriteria(searchCriteria))) {
      setGridKey(gridKey + 1);
      let popupSearchCriteria = {};
      popupSearchCriteria['templateName'] = paramSearchCriteria ? paramSearchCriteria.templateLikeName : searchCriteria.templateLikeName;
      popupSearchCriteria['fromDateCreated'] = validate.isEmpty(startDate) ? null : dateFormat(startDate, DEFAULT_START_DATE_TIME_FORMAT);
      popupSearchCriteria['toDateCreated'] = validate.isEmpty(endDate) ? null : dateFormat(endDate, DEFAULT_END_DATE_TIME_FORMAT);
      popupSearchCriteria['limitFrom'] = paramSearchCriteria.limitFrom || defaultCriteria.limitFrom;
      popupSearchCriteria['noOfRecords'] = paramSearchCriteria.noOfRecords || defaultCriteria.noOfRecords;
      popupSearchCriteria['runOnMaster'] = validate.isNotEmpty(selectedId);
      if(validate.isNotEmpty(paramSearchCriteria.applicableType)){
        popupSearchCriteria['applicableTypes'] = [paramSearchCriteria.applicableType];
      }else {
        if (HAS_PHARMACY_VIEW_ROLE && !HAS_PATHLABS_VIEW_ROLE) {
          popupSearchCriteria['applicableTypes'] = [PHARMACY]
        } else if (HAS_PATHLABS_VIEW_ROLE && !HAS_PHARMACY_VIEW_ROLE) {
          popupSearchCriteria['applicableTypes'] = [PATHLABS]
        }
      }
      setSearchCriteria(popupSearchCriteria);
      let formData = new FormData();
      formData.append("searchCriteria", JSON.stringify(popupSearchCriteria));
      getTemplateList(formData);
    }
  }, [props.location, props.showModal]);

  useEffect(() => {
    if (validate.isNotEmpty(gridValueRef) && validate.isNotEmpty(gridValueRef.current) && selectedId) {
      gridValueRef.current.scrollToRow(dataSet.findIndex(data => (data.id == selectedId)));
    }
  }, [isShowPreview, dataSet])


  const handleSuccessResponse = (responseData) => {
    if (validate.isNotEmpty(responseData)) {
      setDataGrid(validate.isEmpty(responseData.dataGrid) ? [] : responseData.dataGrid);
      setDataSet(validate.isEmpty(responseData.dataSet) ? [] : responseData.dataSet);
      setTotalRecords(validate.isEmpty(responseData.totalRecords) ? 0 : responseData.totalRecords);
    }
  };

  const getTemplateList = async (finalObj) => {
    if (!props.showModal) {
      let response = await popupService.getTemplates(finalObj).catch((err) => setStackedToastContent({ toastMessage: "Unable to get templates, please try again" }));
      ResponseHandler(setStackedToastContent).handleResponse(
        response,
        { successAlert: true },
        handleSuccessResponse,
        (error) => {
          setStackedToastContent({ toastMessage: error });
        }
      );
      setLoading(false);
      return response.responseData;
    }
  };

  const handleEditAction = (value) => {
    props.history.push({
      pathname: POPUP_URLS.createTemplate,
      state: { templateInfo: value, searchCriteria: getActualSearchCriteria(searchCriteria), isCloneTemplate:true },
    });
  };

  const handlePreviewAction = (value) => {
    setSelectedId(value.id);
    setBackClicked(true);
    setModalValue(value);
    toggle(true);
  };

  const callBackMapping = {
    renderActionColumn: (props) => {
      return (
        <>
          {validate.validateRole(POPUP_ROLES.createTemplate) && <CloneIcon tooltip={"Clone"} handleOnClick={() => handleEditAction(props.row)} id={props.row.id} />}
        </>
      );
    },
    idActionColumn: (props) => {
      return (<>
        <a className="text-primary pointer btn btn-link btn-sm" onClick={() => handlePreviewAction(props.row)} id={`template_id_${props.row.id}`}>{props.row.id}</a>
        <UncontrolledTooltip placement="bottom" target={`template_id_${props.row.id}`}>
          {'View Template'}
        </UncontrolledTooltip>
      </>);
    },
    rowClassName: (row) => {
      if (selectedId == row.id) {
        setTimeout(() => {
          setSelectedId(undefined);
        }, 2000);
        return isBackClicked ? "row-popup-opened" : "row-edited-success";
      }
    }
  };

  const toggle = (showPreview) => {
    setShowPreview(showPreview);
  };

  const remoteDataFunction = async ({ startIndex, limit, filters }) => {
    console.log(filters);
    if (totalRecords <= startIndex) {
      return { dataSet: dataSet, totalRowsCount: totalRecords };
    }
    let popupSearchCriteria = { ...searchCriteria,templateName:templateName, 'limitFrom': startIndex, 'noOfRecords': limit };
    setSearchCriteria(popupSearchCriteria);
    let formData = new FormData();
    formData.append("searchCriteria", JSON.stringify(popupSearchCriteria));
    const responseData = await getTemplateList(formData);
    setDataSet(startIndex > 0 ? [...dataSet, ...responseData.dataSet] : responseData.dataSet);
    return { dataSet: responseData?.dataSet ? responseData.dataSet : [], totalRowsCount: responseData?.totalRecords ? responseData.totalRecords : 0, status: true, };
  };

  return (
    <>
      <Wrapper>
        <HeaderComponent ref={headerRef} className="d-flex flex-column">
          {isShowPreview ?
            <div className="align-items-center border-bottom d-flex justify-content-between px-2 px-lg-3 py-1">
              <p className="d-flex align-items-center flex-wrap mb-0">
                <span>Template ID - </span>
                <span className="fw-bold text-truncate me-3 ms-1">{modalValue.id}</span>
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
                <span>List of Popup Templates {searchCriteria && searchCriteria.fromDateCreated && searchCriteria.toDateCreated && <strong>from {dateFormat(searchCriteria.fromDateCreated, 'mmm dd, yyyy')} To {dateFormat(searchCriteria.toDateCreated, 'mmm dd, yyyy')}</strong>}</span>
              </p>
            </div>
          }
        </HeaderComponent>
        <BodyComponent allRefs={{ headerRef }} loading={isLoading} className="body-height">
          {isShowPreview && <PreviewTemplate templateId={modalValue.id} setShowPreview={setShowPreview} setStackedToastContent={setStackedToastContent}/>}
          {!isShowPreview && (validate.isNotEmpty(dataGrid) && validate.isNotEmpty(dataSet) && !isLoading ? (
            <div className="card h-100">
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
            </div>
          ) :
            !isLoading && <NoDataFound text="No Data Found" {...props} searchButton />
          )}
        </BodyComponent>
      </Wrapper>
    </>
  );
}
