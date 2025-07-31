import React ,{ useContext, useState } from 'react';
import {  Route } from 'react-router-dom';
import { ProSidebarProvider } from "react-pro-sidebar";
import MarketingHeaders from '../Headers/MarketingHeaders';
import { AlertContext, UserContext } from '../components/Contexts/UserContext';
import Navigationab from '../Headers/NavigationTabs';
import { SearchContext } from '../components/Contexts/MarketingContexts';
import Validate from '../helpers/Validate';
import HomePage from '../components/HomePage';
const MarketingRoute = ({ component: Component, ...rest }) => {
    const [alertContent, setAlertContent] = useState({});
    const [toastContent, setToastContent] = useState([]);
    const [stackedToastContent, setStackedToastContent] = useState([]);
    const [campaignSearchCriteria, setCampaignSearchCriteria] = useState(undefined);
    const [loadData, setLoadData] = useState()
    const [selectedDateRange, setSelectedDateRange] = useState({})
    const usercontext = useContext(UserContext);

    return ( 
        <SearchContext.Provider value={{ campaignSearchCriteria, setCampaignSearchCriteria, loadData, setLoadData, selectedDateRange, setSelectedDateRange }}>
            <AlertContext.Provider value={{ alertContent, setAlertContent, toastContent, setToastContent, stackedToastContent, setStackedToastContent }}>
                <Route {...rest} render={(props) => (
                    <React.Fragment>
                        <MarketingHeaders {...props} {...rest} loadData={loadData} setLoadData={setLoadData} />
                       { Validate().isNotEmpty(usercontext) && usercontext.isModuleRightsAvailable  ? <section className="d-flex">
                            <ProSidebarProvider>
                                <Navigationab {...props} />
                            </ProSidebarProvider>
                            <div className='flex-grow-1'>
                                <Component {...props} {...rest} />
                            </div>
                        </section> :  <div className='flex-grow-1'>
                                <HomePage {...props} {...rest} />
                            </div> }
                    </React.Fragment>
                )} />
            </AlertContext.Provider>
        </SearchContext.Provider>

    )
}

export default MarketingRoute;