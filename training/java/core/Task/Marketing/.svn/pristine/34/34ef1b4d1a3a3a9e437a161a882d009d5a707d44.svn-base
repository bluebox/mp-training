import DynamicForm, { REQUEST_TYPE, withFormHoc } from "@medplus/react-common-components/DynamicForm";
import dateFormat from 'dateformat';
import { useContext } from "react";
import { DEFAULT_END_DATE_TIME_FORMAT, DEFAULT_START_DATE_TIME_FORMAT } from "../../constants/PromotionConstants";
import { POPUP_FORM_URLS, POPUP_URLS } from "../../constants/UrlConstants";
import Validate from "../../helpers/Validate";
import { AlertContext } from "../Contexts/UserContext";
import { PATHLABS, PHARMACY } from "../../constants/MarketingConstant";
import { POPUP_ROLES } from "../../constants/MarketingRoles";

const ConfigurationSearch = ({ helpers, ...props }) => {
    const { setStackedToastContent } = useContext(AlertContext);
    const validate = Validate();
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

    const handleSearchAction = () => {
        const searchCriteria = helpers.validateAndCollectValuesForSubmit('configurationSearchForm');
        if (searchCriteria && searchCriteria.applicableType) {
            searchCriteria['applicableType'] = searchCriteria.applicableType;
        }
        if (validate.isNotEmpty(searchCriteria.dateRange) && searchCriteria.dateRange.length > 1 && validate.isNotEmpty(searchCriteria.dateRange[0]) && validate.isNotEmpty(searchCriteria.dateRange[1])) {
            searchCriteria.fromDateCreated = dateFormat(searchCriteria.dateRange[0], DEFAULT_START_DATE_TIME_FORMAT);
            searchCriteria.toDateCreated = dateFormat(searchCriteria.dateRange[1], DEFAULT_END_DATE_TIME_FORMAT);
        }
        if (isValidSearchCriteria(searchCriteria)) {
            if (helpers.getFormErrorConfigurations().includes(true)) {
                setStackedToastContent({ toastMessage: 'Please give valid search criteria.' });
                return;
            }
            props.setShowModal(false);
            props.history.push({
                pathname: POPUP_URLS.viewConfigurations,
                state: { searchCriteria: searchCriteria },
            });
        }
    }

    const showElementsBasedOnapplicableType = applicableType => {
        helpers.hideElement("productId");
        if("PHARMACY" == applicableType){
            helpers.showElement("triggerTypeGrp");
        }else{
            helpers.updateValue("","productId");
            helpers.updateValue("","triggerType");
            helpers.hideElement("triggerTypeGrp");
        }
    }
    const onLoadingForm = () => {
        if (props.location && props.location.state && props.location.state.searchCriteria) {
            let searchCriteria = props.location.state.searchCriteria;
            if (validate.isNotEmpty(searchCriteria.applicableType)) {
                helpers.updateValue(searchCriteria.applicableType, 'applicableType');
            if("PHARMACY" == searchCriteria.applicableType) {
                if(validate.isNotEmpty(searchCriteria.productId)) {
                    helpers.updateValue(searchCriteria.productId, 'productId');
                }
                if(validate.isNotEmpty(searchCriteria.triggerType)) {
                    helpers.updateValue(searchCriteria.triggerType, 'triggerType');
                }
            }
            }
            if (validate.isNotEmpty(searchCriteria.dateRange)) {
                helpers.updateValue(searchCriteria.dateRange, 'dateRange');
            } else {
                if(validate.isNotEmpty(searchCriteria.fromDateCreated) && validate.isNotEmpty(searchCriteria.toDateCreated)) {
                    helpers.updateValue([searchCriteria.fromDateCreated,searchCriteria.toDateCreated],'dateRange');
                }
            }
            if (validate.isNotEmpty(searchCriteria.configurationLikeName)) {
                helpers.updateValue(searchCriteria.configurationLikeName, 'configurationLikeName');
            }
            if (validate.isNotEmpty(searchCriteria.storeId)) {
                helpers.updateValue(searchCriteria.storeId, 'storeId');
            }
            if(validate.isNotEmpty(searchCriteria.status)) {
                helpers.updateValue(searchCriteria.status, 'status');
            }
            showElementsBasedOnapplicableType(searchCriteria.applicableType);
        }
    }

    const observerMap = {
        'configurationSearchForm': [['load',onLoadingForm]],
        'search': [['click', () => handleSearchAction()]],
        'storeId': [['change', (payload) => helpers.updateValue(payload[0].target.value.toUpperCase().trim(), 'storeId')]],
        'productId': [['change', (payload) => helpers.updateValue(payload[0].target.value.toUpperCase().trim(), 'productId')]],
        'applicableType' :[['click', (payload)=> {helpers.updateValue(payload[0].target.value, 'applicableType');showElementsBasedOnapplicableType(payload[0].target.value)}]],
    };

    return (
        <DynamicForm requestUrl={POPUP_FORM_URLS.configurationSearchForm} requestMethod={REQUEST_TYPE.GET} observers={observerMap} helpers={helpers} />
    )
}

export default withFormHoc(ConfigurationSearch);