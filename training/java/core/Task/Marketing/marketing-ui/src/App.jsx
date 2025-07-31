import React, { useContext, useEffect, useState } from "react";
import Routes from './routes';
import '@medplus/react-common-components/CommonCss'
import MarketingService from "./services/MarketingService";
import { AlertContext, SidebarContext, UserContext } from "./components/Contexts/UserContext";
import Validate from "./helpers/Validate";
import { Wrapper } from "./components/common/CommonStructure";
import AccessDeniedPage from "./components/AccessDeniedPage";

import 'react-quill/dist/quill.snow.css';
import 'react-js-cron/dist/styles.css';
import { ALERT_TYPE } from "@medplus/react-common-components/DynamicForm";
import { TOKEN_OBJECT } from "./axios";

const App = () => {

    const [userSessionDetails,setUserSessionDetails] = useState({});
    const [isUserLoggedin, setUserLoggedin] = useState(false);
    const {setAlertContent} = useContext(AlertContext);
    const [isModuleRightsAvailable, setModuleRightsAvailable] = useState(false);
    const [sidebarCollapsedFlag,setSidebarCollapsedFlag] = useState(false);

    useEffect(()=>{
        MarketingService().getUserDetails().then(data => {
            if(Validate().isNotEmpty(data) && "SUCCESS" === data.statusCode && Validate().isNotEmpty(data.responseData)) {
                setUserSessionDetails(data.responseData['userDetails']);
                if(TOKEN_OBJECT.headerName==null && TOKEN_OBJECT.token==null) {
                    TOKEN_OBJECT.headerName=data.responseData?.tokenDetails?.headerName
                    TOKEN_OBJECT.token=data.responseData?.tokenDetails?.token
                }
                setUserLoggedin(true);
                if(Validate().isNotEmpty(data.responseData?.userDetails.roles) && data.responseData?.userDetails.moduleName==='marketing'){
                    setModuleRightsAvailable(true);
                }
            } else {
                setUserSessionDetails({});
            }
        })
        .catch(error => {
            setAlertContent({alertType: ALERT_TYPE.ERROR, alertMessage: error});
        })
    },[]);

    return (
            <React.Fragment>
                {isUserLoggedin ? <UserContext.Provider value={{ userSessionDetails, isUserLoggedin, isModuleRightsAvailable }}>
                <SidebarContext.Provider value={{ sidebarCollapsedFlag, setSidebarCollapsedFlag }}>
                    <Routes />
                </SidebarContext.Provider>
                </UserContext.Provider>
                    : <AccessDeniedPage/>
                }
            </React.Fragment>
        
    )
}

export default App;