import DynamicForm, { withFormHoc } from "@medplus/react-common-components/DynamicForm";
import dateFormat from 'dateformat';
import { POPUP_FORM_URLS, POPUP_URLS } from "../../constants/UrlConstants";
import Validate from "../../helpers/Validate";
import { REQUEST_TYPE } from "../../services/ServiceConstants";
import { DEFAULT_END_DATE_TIME_FORMAT, DEFAULT_START_DATE_TIME_FORMAT } from "../../constants/PromotionConstants";
import { useContext } from "react";
import { AlertContext } from "../Contexts/UserContext";
import { PATHLABS, PHARMACY } from "../../constants/MarketingConstant";
import { POPUP_ROLES } from "../../constants/MarketingRoles";

const TemplateSearch = ({ helpers, ...props }) => {
  const validate = Validate();
  const {setStackedToastContent} = useContext(AlertContext);
  const HAS_POPUP_LABS_TEMPLATE_LIST = validate.validateRole(POPUP_ROLES.viewLabTemplates);
  const HAS_POPUP_PHARMACY_TEMPLATE_LIST = validate.validateRole(POPUP_ROLES.viewPharmaTemplates);

  const onLoadingForm = () => {
    if(props.location && props.location.state && props.location.state.searchCriteria) {
      let searchCriteria = props.location.state.searchCriteria;
      if(validate.isNotEmpty(searchCriteria.applicableType)){
        helpers.updateValue(searchCriteria.applicableType,'applicableType')
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
    }
  }
  const isValidSearchCriteria = (searchCriteria) => {
    for (let key in searchCriteria) {
        if (validate.isNotEmpty(searchCriteria[key]) && key != 'dateRange') {
            return true;
        }
    }
    setStackedToastContent({ toastMessage: 'Please give at least one search criteria for finding popup configurations.' })
    return false;
}

  const onSearch = () => {
    const searchCriteria = helpers.validateAndCollectValuesForSubmit("templateSearchForm");
    if(searchCriteria && searchCriteria.applicableType){
      searchCriteria['applicableType'] = searchCriteria.applicableType;
    }else {
      if(HAS_POPUP_LABS_TEMPLATE_LIST && !HAS_POPUP_PHARMACY_TEMPLATE_LIST){
          searchCriteria['applicableType'] = PATHLABS ;
      }
      else if(HAS_POPUP_PHARMACY_TEMPLATE_LIST && !HAS_POPUP_LABS_TEMPLATE_LIST){
          searchCriteria['applicableType'] = PHARMACY;
      }
      else{
          searchCriteria['applicableType'] = null;
      }
  }
    if (searchCriteria && searchCriteria.dateRange) {
      searchCriteria['fromDateCreated'] = searchCriteria.dateRange[0] ? dateFormat(searchCriteria.dateRange[0], DEFAULT_START_DATE_TIME_FORMAT) : null;
      searchCriteria['toDateCreated'] = searchCriteria.dateRange[1] ? dateFormat(searchCriteria.dateRange[1], DEFAULT_END_DATE_TIME_FORMAT) : null;
    }
    if(isValidSearchCriteria(searchCriteria)) {
      props.setShowModal(false);
      props.history.push({
        pathname: POPUP_URLS.viewTemplates,
        state: { searchCriteria: searchCriteria },
      });
    }
  };

  const observers = {
    templateSearchForm:[['load',onLoadingForm]],
    search: [['click', onSearch]]
  };

  return (
    <>
      <DynamicForm
        requestMethod={REQUEST_TYPE.GET}
        requestUrl={POPUP_FORM_URLS.templateSearchForm}
        helpers={helpers}
        observers={observers}
      />
    </>
  );
};
export default withFormHoc(TemplateSearch);
