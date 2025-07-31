import DynamicForm, { withFormHoc } from "@medplus/react-common-components/DynamicForm";
import { DEFAULT_END_DATE_TIME_FORMAT, DEFAULT_START_DATE_TIME_FORMAT } from "../../constants/PromotionConstants";
import { POPUP_FORM_URLS, POPUP_URLS } from "../../constants/UrlConstants";
import Validate from "../../helpers/Validate";
import dateFormat from 'dateformat';
import { REQUEST_TYPE } from "../../services/ServiceConstants";
import { POPUP_ROLES } from "../../constants/MarketingRoles";
import { AlertContext } from "../Contexts/UserContext";
import { useContext } from "react";
import { PATHLABS, PHARMACY } from "../../constants/MarketingConstant";

const TemplateRequestSearch = ({helpers, ...props}) => {
    const validate = Validate();
    const HAS_POP_UP_TEMPLATE_CREATE_ROLE = validate.validateRole(POPUP_ROLES.createTemplate);
    const { setStackedToastContent } = useContext(AlertContext);
    const HAS_POPUP_LABS_TEMPLATE_LIST = validate.validateRole(POPUP_ROLES.viewLabTemplates);
    const HAS_POPUP_PHARMACY_TEMPLATE_LIST = validate.validateRole(POPUP_ROLES.viewPharmaTemplates);

  const isValidSearchCriteria = (searchCriteria) => {
    for (let key in searchCriteria) {
      if (validate.isNotEmpty(searchCriteria[key]) && key != 'dateRange') {
        return true;
      }
    }
    setStackedToastContent({ toastMessage: 'Please give at least one search criteria for finding popup configurations.' })
    return false;
  }

    const onLoadingForm = () => {
      if(props.location && props.location.state && props.location.state.searchCriteria) {
        let searchCriteria = props.location.state.searchCriteria;
        if(validate.isNotEmpty(searchCriteria.applicableTypes)){
          helpers.updateValue(searchCriteria.applicableTypes[0],'applicableType')
        }
        if(validate.isNotEmpty(searchCriteria.requestId)){
          helpers.updateValue(searchCriteria.requestId[0],'requestId');
        }
        if(validate.isNotEmpty(searchCriteria.dateRange)){
          helpers.updateValue(searchCriteria.dateRange,'dateRange');
        } else {
          if(validate.isNotEmpty(searchCriteria.fromDateCreated) && validate.isNotEmpty(searchCriteria.toDateCreated)) {
            helpers.updateValue([searchCriteria.fromDateCreated,searchCriteria.toDateCreated],'dateRange');
          }
        }
        if (validate.isNotEmpty(searchCriteria.templateLikeName)) {
          helpers.updateValue(searchCriteria.templateLikeName, 'templateLikeName');
        }
        if (validate.isNotEmpty(searchCriteria.createdBy) && !HAS_POP_UP_TEMPLATE_CREATE_ROLE) {
          helpers.updateValue(searchCriteria.createdBy, 'createdBy');
        }
        if(validate.isNotEmpty(searchCriteria.requestStatus)) {
          helpers.updateValue(searchCriteria.requestStatus,'requestStatus');
        } else if(validate.isNotEmpty(searchCriteria.templateRequestStatus)){
          helpers.updateValue(searchCriteria.templateRequestStatus,'requestStatus');
        }
      }
      if(HAS_POP_UP_TEMPLATE_CREATE_ROLE){
        helpers.hideElement('createdBy')
      }
    }

    const onSearch = () => {
    const searchCriteria = helpers.validateAndCollectValuesForSubmit("templateRequestSearchForm");
    if(searchCriteria && searchCriteria.applicableType){
      searchCriteria['applicableTypes'] = [searchCriteria.applicableType];
    } else {
      if (HAS_POPUP_LABS_TEMPLATE_LIST && !HAS_POPUP_PHARMACY_TEMPLATE_LIST) {
        searchCriteria['applicableType'] = [PATHLABS];
      }
      else if (HAS_POPUP_PHARMACY_TEMPLATE_LIST && !HAS_POPUP_LABS_TEMPLATE_LIST) {
        searchCriteria['applicableType'] = [PHARMACY];
      }
      else {
        searchCriteria['applicableType'] = null;
      }
    }
    if(searchCriteria && searchCriteria.requestId){
      searchCriteria['requestId'] = [searchCriteria.requestId];
    } else {
      searchCriteria['requestId'] = null;
    }
    if (searchCriteria && searchCriteria.dateRange) {
      searchCriteria['fromDateCreated'] = searchCriteria.dateRange[0] ? dateFormat(searchCriteria.dateRange[0], DEFAULT_START_DATE_TIME_FORMAT) : null;
      searchCriteria['toDateCreated'] = searchCriteria.dateRange[1] ? dateFormat(searchCriteria.dateRange[1], DEFAULT_END_DATE_TIME_FORMAT) : null;
    }
    if(isValidSearchCriteria(searchCriteria)) {
      props.setShowModal(false);
      props.history.push({
        pathname: POPUP_URLS.viewTemplateRequests,
        state: { searchCriteria: searchCriteria },
      });
    }
  };

  const observers = {
    templateRequestSearchForm:[['load',onLoadingForm]],
    search: [['click', onSearch]],
    requestId: [['change',() => {}]],
    templateLikeName: [['change', () => {}]],
    dateRange: [['change', () => {}]],
    applicableType:[['click',() => {}]]
  };

  return (
    <>
      <DynamicForm
        requestMethod={REQUEST_TYPE.GET}
        requestUrl={POPUP_FORM_URLS.templateRequestSearchForm}
        helpers={helpers}
        observers={observers}
      />
    </>
  );
};
export default withFormHoc(TemplateRequestSearch);