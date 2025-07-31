import React, { useContext } from "react";
import { Button } from "react-bootstrap";
import { UserContext } from "./Contexts/UserContext";
import Validate from "../helpers/Validate";
import MarketingBanner from "../images/marketingbanner.svg"
import NoRights from "./common/NoRights";

const HomePage = (props) => {
    const usercontext = useContext(UserContext);
    return (
        <React.Fragment>

            <div className='custom_gridContainer custom_gridContainer_fullwidth_forms'>
                <div className="forms">
                    {Validate().isEmpty(usercontext) || !usercontext.isModuleRightsAvailable  ? <NoRights/> :
                    <div className="row g-0 h-100">
                        <div className="col-5">
                            <div className="d-flex flex-column h-100 justify-content-between">
                                <div className="mt-5 ms-5">
                                    <p className="mb-0 text-secondary">Welcome to</p>
                                    <h3 className="mb-0 fw-normal">Marketing</h3>
                                    <h1 className="fw-bold display-4">Campaigns</h1>
                                    <p className="mb-0 text-secondary">Easy-to-use platform for all offers</p>
                                    
                                    {props.note}
                                </div>
                                <div className="d-flex align-items-center ms-5 mb-5">
                                    <p className="mb-0 text-secondary me-3">Let’s begin the journey</p>
                                    <Button variant="brand">Start Exploring</Button>{' '}
                                </div>
                            </div>
                        </div>
                        <div className="col-7 bg-light d-flex align-items-center justify-content-center">
                            <img src={MarketingBanner} alt="Marketing Banner" role="img" aria-label="Marketing Banner"/>
                        </div>
                    </div>}
                </div>
            </div>
        </React.Fragment>
    )
}
export default HomePage;