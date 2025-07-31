import React, { useState } from 'react';
import { Switch } from 'react-router-dom';
import MarketingRoute from './commonRoute/MarketingRoute';
import CampaignSearch from './components/CampaignSearch.jsx';
import HomePage from './components/HomePage.jsx';
import ListCampaign from './components/ListCampaign';
import ConfigurationSearch from './components/Popup/ConfigurationSearch.jsx';
import CreateConfiguration from './components/Popup/CreateConfiguration.jsx';
import CreateTemplate from './components/Popup/CreateTemplate.jsx';
import ListConfiguration from './components/Popup/ListConfiguration.jsx';
import ListTemplates from './components/Popup/ListTemplates.jsx';
import TemplateSearch from './components/Popup/TemplateSearch.jsx';
import CreateOldCampaign from './components/campaign/CreateOldCampaign.jsx';
import ViewOldCampaign from './components/campaign/ViewOldCampaign.jsx';
import SuccessComponent from './components/common/SuccessPage';
import CreateComplimentaryPromotion from './components/complimentary_promotion/CreateComplimentaryPromotion';
import ViewComplimentaryPromotion from './components/complimentary_promotion/ViewComplimentaryPromotion';
import CreateCampaign from './components/multi_item_campaign/CreateCampaign';
import ViewCampaign from './components/multi_item_campaign/ViewCampaign.jsx';
import CreateRegularPromotion from './components/regular_promotion/CreateRegularPromotion';
import ViewRegularPromotion from './components/regular_promotion/ViewRegularPromotion';
import { CAMPAIGN, COMPLIMENTARY_ROLES, MIC_ROLES, POPUP_ROLES, RP_ROLES } from './constants/MarketingRoles';
import { LIST_TABS } from './constants/TabConstants';
import { CAMPAIGN_URLS, COMPLIMENTARY_URLS, MIC_URLS, POPUP_URLS, RP_URLS } from './constants/UrlConstants';
import Validate from './helpers/Validate';
import { API_URL } from './services/ServiceConstants.jsx';
import ListTemplateRequests from './components/Popup/ListTemplateRequests.jsx';
import TemplateRequestSearch from './components/Popup/TemplateRequestSearch.jsx';
import { ListConfigurationRequests } from './components/Popup/ListConfigurationRequests.jsx';
import ConfigurationRequestSearch from './components/Popup/ConfigurationRequestSearch.jsx';
import ViewConfiguration from './components/Popup/ViewConfiguration.jsx';
import EditTemplate from './components/Popup/EditTemplate.jsx';

const validate = Validate();

export default (props) => {
    const [note, setNote] = useState('');

    return(
    <Switch>
        <MarketingRoute exact path={`${API_URL}ui/home`} screenName={'Home'} component={HomePage} {...props} />
        
        {/* Multi Item Camapign */}
        {validate.validateRole(MIC_ROLES.createRoles)  && <MarketingRoute exact path={MIC_URLS.createCampaign} screenName={"Multi Item Campaign / Create"} component={CreateCampaign} {...props} /> }
        {validate.validateRole(MIC_ROLES.listRoles) && <MarketingRoute exact path={MIC_URLS.viewCampaign} screenName={"Multi Item Campaign / Dashboard / View"} component={ViewCampaign} {...props}/>}
        {validate.validateRole(MIC_ROLES.editRoles) && <MarketingRoute exact path={MIC_URLS.editCampaign} screenName={"Multi Item Campaign / Dashboard / Edit"} component={CreateCampaign} {...props}/>}
        {validate.validateRole(MIC_ROLES.cloneRoles) && <MarketingRoute exact path={MIC_URLS.cloneCampaign} screenName={"Multi Item Campaign / Dashboard / Clone"} component={CreateCampaign} {...props}/>}
        {validate.validateRole(MIC_ROLES.listRoles) && <MarketingRoute exact path={MIC_URLS.listCamapign} screenName={"Multi Item Campagin / Dashboard"} component={ListCampaign} {...props}  showModal = {true} campaignSearchForm={CampaignSearch} promotionConfig={LIST_TABS.multiItem}/>}
        {validate.validateRole(MIC_ROLES.approveRoles) && <MarketingRoute exact path={MIC_URLS.approveCampaign} screenName={"Multi Item Campaign / Dashboard / Approve"} component={CreateCampaign} {...props}/>}
        {validate.validateRole(MIC_ROLES.closeRoles) && <MarketingRoute exact path={MIC_URLS.closeCampaign} screenName={"Multi Item Campaign / Dashboard / Update"} component={CreateCampaign} {...props}/>} 
        
        {/* Popup */}
        {validate.validateRole(POPUP_ROLES['createTemplate']) && <MarketingRoute exact path={POPUP_URLS.createTemplate} screenName={"Popup Configuration / Template / Create"} component={CreateTemplate} {...props} />}
        {validate.validateRole(POPUP_ROLES['viewTemplates']) && <MarketingRoute exact path={POPUP_URLS.viewTemplates} screenName={"Popup Configuration / Template / Dashboard"} component={ListTemplates} {...props} showModal={false} campaignSearchForm={TemplateSearch} />}
        {validate.validateRole(POPUP_ROLES['createTemplate']) && <MarketingRoute exact path={POPUP_URLS.editTemplate} screenName={"Popup Configuration / Template / Edit"} component={EditTemplate} {...props} />}
        {validate.validateRole(POPUP_ROLES['viewTemplates']) && <MarketingRoute exact path={POPUP_URLS.viewTemplateRequests} screenName={'Popup Configuration / Template / Request Dashboard'} component={ListTemplateRequests} {...props} showModal={false} campaignSearchForm={TemplateRequestSearch} />}
        
        {validate.validateRole(POPUP_ROLES['createConfiguration']) && <MarketingRoute exact path={POPUP_URLS.createConfiguration} screenName={"Popup Configuration / Configuration / Create"} component={CreateConfiguration} {...props} />}
        {validate.validateRole(POPUP_ROLES['createConfiguration']) && <MarketingRoute exact path={POPUP_URLS.editConfiguration} screenName={"Popup Configuration / Configuration / Edit"} component={CreateConfiguration} {...props} />}
        {validate.validateRole(POPUP_ROLES['createConfiguration']) && <MarketingRoute exact path={`${POPUP_URLS.editConfiguration}/request/:reqId`} screenName={"Popup Configuration / Configuration / Edit"} component={CreateConfiguration} {...props} />}
        {validate.validateRole(POPUP_ROLES['viewConfigurations']) && <MarketingRoute exact path={`${POPUP_URLS.editConfiguration}/:configId`} screenName={"Popup Configuration / Configuration / Edit"} component={ViewConfiguration} {...props} />}
        {validate.validateRole(POPUP_ROLES['viewConfigurations']) && <MarketingRoute exact path={`${POPUP_URLS.viewConfiguration}/request/:reqId`} screenName={'Popup Configuration / Configuration / View'} component={ViewConfiguration} {...props} />}
        {validate.validateRole(POPUP_ROLES['viewConfigurations']) && <MarketingRoute exact path={`${POPUP_URLS.viewConfiguration}/:configId`} screenName={'Popup Configuration / Configuration / View'} component={ViewConfiguration} {...props} />}
        {validate.validateRole(POPUP_ROLES['viewConfigurations']) && <MarketingRoute exact path={POPUP_URLS.viewConfigurations} screenName={'Popup Configuration / Configuration / Dashboard'} component={ListConfiguration} {...props} showModal={false} campaignSearchForm={ConfigurationSearch} />}
        {validate.validateRole(POPUP_ROLES['viewConfigurations']) && <MarketingRoute exact path={POPUP_URLS.viewConfigurationRequests} screenName={'Popup Configuration / Configuration / Request Dashboard'} component={ListConfigurationRequests} {...props} showModal={false} campaignSearchForm={ConfigurationRequestSearch}/>}
        {validate.validateRole(POPUP_ROLES['createConfiguration']) && <MarketingRoute exact path={POPUP_URLS.cloneConfiguration} screenName={"Popup Configuration / Configuration / Clone"} component={CreateConfiguration} {...props} />}


        {/* Regualar Promotion */}
        {validate.validateRole(RP_ROLES.createRoles)  && <MarketingRoute exact path={RP_URLS.createCampaign} screenName={"Regular Promotion / Create"} component={CreateRegularPromotion} {...props} /> } 
        {validate.validateRole(RP_ROLES.listRoles) && <MarketingRoute exact path={RP_URLS.viewCampaign} screenName={"Regular Promotion / Dashboard / View"} component={ViewRegularPromotion} {...props}/>}
        {validate.validateRole(RP_ROLES.editRoles) && <MarketingRoute exact path={RP_URLS.editCampaign} screenName={"Regular Promotion / Dashboard / Edit"} component={CreateRegularPromotion} {...props}/>}
        {validate.validateRole(RP_ROLES.cloneRoles) && <MarketingRoute exact path={RP_URLS.cloneCampaign} screenName={"Regular Promotion / Dashboard / Clone"} component={CreateRegularPromotion} {...props}/>}
        {validate.validateRole(RP_ROLES.listRoles) && <MarketingRoute exact path={RP_URLS.listCamapign} screenName={"Regular Promotion / Dashboard"} component={ListCampaign} {...props}  showModal = {true} campaignSearchForm={CampaignSearch} promotionConfig={LIST_TABS.regular}/>} 
        {validate.validateRole(RP_ROLES.approveRoles) && <MarketingRoute exact path={RP_URLS.approveCampaign} screenName={"Regular Promotion / Dashboard / Approve"} component={CreateRegularPromotion} {...props}/>}
        {validate.validateRole(RP_ROLES.closeRoles) && <MarketingRoute exact path={RP_URLS.closeCampaign} screenName={"Regular Promotion / Dashboard / Update"} component={CreateRegularPromotion} {...props}/>} 

        {/* Camapign */}
        {validate.validateRole(CAMPAIGN.createRoles)  && <MarketingRoute exact path={CAMPAIGN_URLS.createCampaign} screenName={"Campaign / Create"} component={CreateOldCampaign} {...props} /> }
        {validate.validateRole(CAMPAIGN.listRoles) && <MarketingRoute exact path={CAMPAIGN_URLS.viewCampaign} screenName={"Campaign / Dashboard / View"} component={ViewOldCampaign} {...props}/>}
        {validate.validateRole(CAMPAIGN.editRoles) && <MarketingRoute exact path={CAMPAIGN_URLS.editCampaign} screenName={"Campaign / Dashboard / Edit"} component={CreateOldCampaign} {...props}/>}
        {validate.validateRole(CAMPAIGN.cloneRoles) && <MarketingRoute exact path={CAMPAIGN_URLS.cloneCampaign} screenName={"Campaign / Dashboard / Clone"} component={CreateOldCampaign} {...props}/>}
        {validate.validateRole(CAMPAIGN.listRoles) && <MarketingRoute exact path={CAMPAIGN_URLS.listCamapign} screenName={"Campaign / Dashboard"} component={ListCampaign} {...props}  showModal = {true}  campaignSearchForm={CampaignSearch} promotionConfig={LIST_TABS.campaign}/>}
        {validate.validateRole(CAMPAIGN.approveRoles) && <MarketingRoute exact path={CAMPAIGN_URLS.approveCampaign} screenName={"Campaign / Dashboard / Approve"} component={CreateOldCampaign} {...props}/>}
        {validate.validateRole(CAMPAIGN.closeRoles) && <MarketingRoute exact path={CAMPAIGN_URLS.closeCampaign} screenName={"Campaign / Dashboard / Update"} component={CreateOldCampaign} {...props}/>} 

        {/* Complimentary Gift */}
        {validate.validateRole(COMPLIMENTARY_ROLES.createRoles)  && <MarketingRoute exact path={COMPLIMENTARY_URLS.createCampaign} screenName={"Complimentary Gift / Create"} component={CreateComplimentaryPromotion} {...props} /> } 
        {validate.validateRole(COMPLIMENTARY_ROLES.listRoles) && <MarketingRoute exact path={COMPLIMENTARY_URLS.viewCampaign} screenName={"Complimentary Gift / Dashboard / View"} component={ViewComplimentaryPromotion} {...props}/>}
        {validate.validateRole(COMPLIMENTARY_ROLES.editRoles) && <MarketingRoute exact path={COMPLIMENTARY_URLS.editCampaign} screenName={"Complimentary Gift / Dashboard / Edit"} component={CreateComplimentaryPromotion} {...props}/>}
        {validate.validateRole(COMPLIMENTARY_ROLES.cloneRoles) && <MarketingRoute exact path={COMPLIMENTARY_URLS.cloneCampaign} screenName={"Complimentary Gift / Dashboard / Clone"} component={CreateComplimentaryPromotion} {...props}/>}
        {validate.validateRole(COMPLIMENTARY_ROLES.listRoles) && <MarketingRoute exact path={COMPLIMENTARY_URLS.listCamapign} screenName={"Complimentary Gift / Dashboard"} component={ListCampaign} {...props}  showModal = {true} campaignSearchForm={CampaignSearch} promotionConfig={LIST_TABS.complimentary}/>} 
        {validate.validateRole(COMPLIMENTARY_ROLES.approveRoles) && <MarketingRoute exact path={COMPLIMENTARY_URLS.approveCampaign} screenName={"Complimentary Gift / Dashboard / Approve"} component={CreateComplimentaryPromotion} {...props}/>}
        {validate.validateRole(COMPLIMENTARY_ROLES.closeRoles) && <MarketingRoute exact path={COMPLIMENTARY_URLS.closeCampaign} screenName={"Complimentary Gift / Dashboard / Update"} component={CreateComplimentaryPromotion} {...props}/>} 

        <MarketingRoute exact path={`${API_URL}ui/success-campaign`} screenName={"Success"} component={SuccessComponent} {...props} />
        <MarketingRoute component={HomePage} screenName={'Home'} {...props} note={note}/>
    </Switch>
    )
    };