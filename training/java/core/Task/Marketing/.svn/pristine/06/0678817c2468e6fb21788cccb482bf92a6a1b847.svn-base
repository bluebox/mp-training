import DynamicForm, { REQUEST_TYPE, withFormHoc } from "@medplus/react-common-components/DynamicForm";
import dateFormat from 'dateformat';
import { useContext } from "react";
import { DEFAULT_END_DATE_TIME_FORMAT, DEFAULT_START_DATE_TIME_FORMAT } from "../../constants/PromotionConstants";
import { POPUP_FORM_URLS, POPUP_URLS } from "../../constants/UrlConstants";
import Validate from "../../helpers/Validate";
import { AlertContext } from "../Contexts/UserContext";
import { POPUP_ROLES } from "../../constants/MarketingRoles";
import { PATHLABS, PHARMACY } from "../../constants/MarketingConstant";

const ConfigurationRequestSearch = ({ helpers, ...props }) => {
    const { setStackedToastContent } = useContext(AlertContext);
    const validate = Validate();
    const HAS_POP_UP_CONFIG_PHARMACY_CREATE_ROLE = validate.validateRole(POPUP_ROLES.createPharmaConfiguration);
    const HAS_POP_UP_CONFIG_PATHLABS_CREATE_ROLE = validate.validateRole(POPUP_ROLES.createLabConfiguration)
    const HAS_POPUP_CONFIG_LABS_REQUESTS_LIST = validate.validateRole(POPUP_ROLES.viewLabConfigurations);
    const HAS_POPUP_CONFIG_PHARMACY_REQUESTS_LIST = validate.validateRole(POPUP_ROLES.viewPharmaConfigurations);
 
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
        if (props.location && props.location.state && props.location.state.searchCriteria) {
            let searchCriteria = props.location.state.searchCriteria;
            if (validate.isNotEmpty(searchCriteria.applicableTypes) && validate.isNotEmpty(searchCriteria.applicableTypes[0])) {
                helpers.updateValue(searchCriteria.applicableTypes[0], 'applicableType')
            }
            if (validate.isNotEmpty(searchCriteria.requestId)) {
                helpers.updateValue(searchCriteria.requestId[0], 'requestId');
            }
            if (validate.isNotEmpty(searchCriteria.dateRange)) {
                helpers.updateValue(searchCriteria.dateRange, 'dateRange');
            }else {
                if( validate.isNotEmpty(searchCriteria.fromDateCreated) && validate.isNotEmpty(searchCriteria.toDateCreated)) {
                    helpers.updateValue([searchCriteria.fromDateCreated,searchCriteria.toDateCreated],'dateRange');
                }
            }
            if(validate.isNotEmpty(searchCriteria.configurationLikeName)) {
                helpers.updateValue(searchCriteria.configurationLikeName, 'configurationLikeName');
            }
            if(validate.isNotEmpty(searchCriteria.createdBy) && !(HAS_POP_UP_CONFIG_PHARMACY_CREATE_ROLE || HAS_POP_UP_CONFIG_PATHLABS_CREATE_ROLE)) {
                helpers.updateValue(searchCriteria.createdBy, 'createdBy');
            }
            if (validate.isNotEmpty(searchCriteria.requestStatus)) {
                helpers.updateValue(searchCriteria.requestStatus, 'requestStatus');
            } else if (validate.isNotEmpty(searchCriteria.configRequestStatus)) {
                helpers.updateValue(searchCriteria.configRequestStatus, 'requestStatus');
            }
        }
        if((HAS_POP_UP_CONFIG_PHARMACY_CREATE_ROLE || HAS_POP_UP_CONFIG_PATHLABS_CREATE_ROLE)){
            helpers.hideElement('createdBy');
        }
    }

    const handleSearchAction = () => {
        const searchCriteria = helpers.validateAndCollectValuesForSubmit("configRequestSearchForm");
        if (searchCriteria && searchCriteria.applicableType) {
            searchCriteria['applicableTypes'] = [searchCriteria.applicableType];
        } else {
            if(HAS_POPUP_CONFIG_LABS_REQUESTS_LIST && !HAS_POPUP_CONFIG_PHARMACY_REQUESTS_LIST){
                searchCriteria['applicableTypes'] = [PATHLABS] ;
            }
            else if(HAS_POPUP_CONFIG_PHARMACY_REQUESTS_LIST && !HAS_POPUP_CONFIG_LABS_REQUESTS_LIST){
                searchCriteria['applicableTypes'] = [PHARMACY];
            }
            else{
                searchCriteria['applicableTypes'] = null;
            }
        }
        if (searchCriteria && searchCriteria.requestId) {
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
                pathname: POPUP_URLS.viewConfigurationRequests,
                state: { searchCriteria: searchCriteria },
            });
        }
    }

    const observerMap = {
        configRequestSearchForm: [['load', onLoadingForm]],
        'search': [['click', () => handleSearchAction()]]
    };

    return (
        <>
            <DynamicForm
                requestMethod={REQUEST_TYPE.GET}
                requestUrl={POPUP_FORM_URLS.configurationRequestSearchForm}
                helpers={helpers}
                observers={observerMap}
            />
        </>
    )
}

export default withFormHoc(ConfigurationRequestSearch);