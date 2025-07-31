import { ALERT_TYPE } from "@medplus/react-common-components/DynamicForm";
import { ERROR_MESSAGES } from "../services/ServiceConstants";
import Validate from "./Validate";


const validate = Validate();
const ResponseHandler = (setAlertContent) => {

    function handleResponse(response, optionals,successHandler, failureHandler)  {
        let success = false; 
        if(validate.isNotEmpty(response) && validate.isNotEmpty(response.statusCode)) {
            if(response.statusCode==='SUCCESS') {
                if(validate.isNotEmpty(optionals)) {
                    if(validate.isNotEmpty(optionals.responseType) && optionals.responseType === 'FORM') {
                        successHandler(response.action.domElements[0].element);
                    } else {
                        successHandler(response.responseData);
                    }
                    if(validate.isNotEmpty(optionals.successAlert)) {
                        // setAlertContent({alertType:ALERT_TYPE.SUCCESS, alertMessage:response.message}); 
                    }
                } else {
                    successHandler(response.responseData);
                }
                success = true;
            } else {
                setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:response.message ,toastMessage: response.message});
                failureHandler(response.message);
            }
        } else {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:ERROR_MESSAGES.UNABLE_TO_PROCESS, toastMessage: ERROR_MESSAGES.UNABLE_TO_PROCESS});
        }
        return success;
        
    }


    return Object.freeze({
        handleResponse,
    });
}
export default ResponseHandler;