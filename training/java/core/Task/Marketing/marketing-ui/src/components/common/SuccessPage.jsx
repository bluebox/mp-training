import React, { useEffect, useState } from "react"
import Validate from "../../helpers/Validate"
import { API_URL } from "../../services/ServiceConstants";
import { MIC_URLS } from "../../constants/UrlConstants";

const SuccessComponent = (props) => {
    const [campaignId, setCampaignId] = useState(undefined);
    const [campaignName, setCampaignName] = useState(undefined);
    const [note, setNote] = useState(undefined);

    useEffect(()=>{
        if(Validate().isNotEmpty(props.location.state)) {
            setCampaignId(props.location.state['campaignId']);
            setCampaignName(props.location.state['campaignName']);
            setNote(props.location.state['note']);
        }
    }, [])
    const handleBtnClick = (campaignId) => {
        props.history.push(MIC_URLS.viewCampaign, {"campaignId":campaignId})
    }
    return(
        <React.Fragment>
            <div className={`grid-no-data-found w-50 ${props.grid ? "page-spinner-position":""}`}>
                <div className="text-center h3  shadow-none p-3 mb-5 bg-light border border-light rounded">
                    <span>Campaign <span className=""  > <span className="text-success small" >{campaignName} [{campaignId}]</span> </span> {note} Successfully.</span>
                </div>
            </div>
        </React.Fragment>
    )
}
export default SuccessComponent