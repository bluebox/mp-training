import { CustomFilterModal } from '@medplus/react-common-components/DynamicForm';
import React, { useContext, useEffect, useRef, useState } from "react";
import { Button, Dropdown } from "react-bootstrap";
import { Link } from "react-router-dom";
import MedplusLogo from "../../src/images/MedplusLogo.svg";
import { AlertContext, UserContext } from "../components/Contexts/UserContext";
import Validate from "../helpers/Validate";
import DeleteIcon from '../images/delete-black-icn.svg';
import MarketingService from "../services/MarketingService";
import { Wrapper } from '../components/common/CommonStructure';
import { API_URL, isPopupConfigurationUrl, isPopupTemplateUrl } from '../services/ServiceConstants';
import { useDispatch } from 'react-redux';
import {SearchContext} from '../components/Contexts/MarketingContexts'
import { CLEAR_ALL } from '../redux/reducer';
import Refresh from './Refresh';

const MarketingHeaders = (props) => {
    const inputEl = useRef(null);
    const [userInfo, setUserInfo] = useState(undefined);
    const validate = Validate();
    const [initialsBackground, setInitialsBackground] = useState(sessionStorage.getItem("userColorCode"));
    const [showModal, setShowModal] = useState(props.showModal ? props.showModal : false);
    const {userSessionDetails} = useContext(UserContext);
    const {campaignSearchCriteria, setCampaignSearchCriteria, loadData, setLoadData, selectedDateRange, setSelectedDateRange} = useContext(SearchContext)
    const dispatch = useDispatch();
    const [searchForm, setSearchForm] = useState(undefined);
    const [pathname, setPathname] = useState();
    const setSearchFormData = (searchForm)=> {
        setSearchForm(searchForm);
    }
    useEffect(() => {
        getInitialsBackground();
        setUserInfo(userSessionDetails);
    }, []);


    useEffect(()=>{
      if(Validate().isSearchCriteriaEmpty(campaignSearchCriteria, false) || !validate.isSamePromotionUrl(pathname, props.location.pathname)) {
            setShowModal(true && props.showModal);
            setLoadData(undefined)
            setSelectedDateRange(undefined)
            const searchCriteria = selectedDateRange;
            console.log('selectedDateRange', selectedDateRange);
            setCampaignSearchCriteria(undefined);
        } else{
            setShowModal(false)
        }
        setPathname(props.location.pathname)
    },[campaignSearchCriteria, props.location.pathname, props.location.search]) 

    const focusTextInput = () => {
        inputEl.current?.focus();
        inputEl.current?.classList.add("custom-form-width")
    }
    
    const handleClickOutside=(event) => {
        if(inputEl.current && inputEl.current.contains(event.target)){
            setShowModal(true)
            focusTextInput() 
        }
        else if(inputEl.current){
          setShowModal(false) 
          inputEl.current.classList.remove("custom-form-width")
        }
    }

    useEffect(() => {
        if (showModal) {
            focusTextInput();
        }
        document.addEventListener('mousedown', handleClickOutside);
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    },[showModal]);

    const getInitialsBackground = () => {
        if (sessionStorage.getItem("userColorCode") === null || sessionStorage.getItem("userColorCode") === "null") {
            const badgeBackgrounds = ["badge-approved", "badge-pending", "badge-created", "badge-rejected", "badge-Submitted", "badge-Decoded", "badge-Cancelled"];
            const arrayIndex = Math.floor(Math.random() * badgeBackgrounds.length);
            setInitialsBackground(badgeBackgrounds[arrayIndex]);
            sessionStorage.setItem("userColorCode", badgeBackgrounds[arrayIndex]);
        }
    }

    const handleOnSearchClick = () => {
        setShowModal(false);
        inputEl.current.classList.remove("custom-form-width")
    }

    const getInitials = (name) => {
        const dividedName = name.trim().split(" ");
        let initials = "";
        for (let i = 0; i < dividedName.length; i++) {
            let initial = dividedName[i].trim();
            if (validate.isEmpty(initial)) {
                continue;
            }
            initials = initials.concat(initial[0]);
            if (initials.length >= 2) {
                break;
            }
        }
        return (
            <React.Fragment>
                <span>
                    {initials}
                </span>
            </React.Fragment>
        );
    }

    const handleLogout = () => {
        sessionStorage.removeItem("userColorCode");
        dispatch({type:CLEAR_ALL});
    }

    const CampaignSearchForm = props.campaignSearchForm;
    return (
        <>
            <React.Fragment>
                <header className="px-3 py-2 bg-white d-flex justify-content-between shadow-sm position-sticky" style={{ 'top': 0, zIndex: 10 }}>
                    <div className="d-flex align-items-center">
                        <div>
                            <Link to={`${API_URL}ui/home`}><img src={MedplusLogo} alt="MedPlus Logo" /></Link>
                        </div>
                        <span className="mx-3 vertical-line"></span>
                        <div>
                            <p className="mb-0 font-14 text-brand line-height-sm">Marketing</p>
                            <p className="mb-0">{props.screenName}</p>
                        </div>
                    </div>
                    <div className="d-flex align-items-center gap-3">
                        {props.campaignSearchForm && <div ref={inputEl}> <CustomFilterModal className="custom-form-scroll"
                            bodyContent={() => { return (<CampaignSearchForm {...props} handleOnSearchClick={handleOnSearchClick} setShowModal={setShowModal} setSearchFormData={setSearchFormData} searchForm={searchForm} />) }}
                            isBigPopOverRequired={true}
                            headerContent={() => {
                                return (
                                    <div className="align-items-center d-flex justify-content-end" >
                                        {!showModal
                                            ? <Button variant="" className="btn btn-link p-2 icon-hover" title="Search" >
                                                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24">
                                                    <g id="Search_black_icon_24px" transform="translate(-48.941 -105.819)">
                                                        <rect id="Rectangle_3285" data-name="Rectangle 3285" width="24" height="24" transform="translate(48.941 105.819)" fill="none" />
                                                        <path id="Path_22916" data-name="Path 22916" d="M72.711,128.457l-7.162-7.132a9.455,9.455,0,1,0-1.1,1.1l7.164,7.133a.78.78,0,1,0,1.1-1.1ZM50.5,115.262a7.853,7.853,0,1,1,7.853,7.853A7.862,7.862,0,0,1,50.5,115.262Z" fill="#000000" />
                                                    </g>
                                                </svg>
                                            </Button> : null}
                                        {showModal
                                            ? <div>
                                                <Button onClick={() => handleOnSearchClick()} variant="light" className="btn btn-link p-1 icon-hover" >
                                                    <img src={DeleteIcon} alt={"Delete Icon"} height="18px" width="18px" title="Close Search Options" />
                                                </Button>
                                            </div>
                                            : null}
                                    </div>
                                )
                            }
                            }
                            showModal={showModal}
                        />
                        </div>
                        }

                        {!(isPopupConfigurationUrl(props.location.pathname) || isPopupTemplateUrl(props.location.pathname)) && <Refresh {...props} cronName={"Promotion Clear Cache"}/>}

                        <Dropdown>
                            <Dropdown.Toggle variant=" " id="dropdown-basic" className="custom-btn-dropdown p-0">
                                <div className="dropdown">
                                    <button type="button" id="dropdown-basic" aria-expanded="false" className="custom-btn-dropdown p-0 dropdown-toggle btn btn- ">
                                        <div className="d-flex align-items-center">
                                            <div className="text-end line-height-sm">
                                                <p className="mb-0 font-weight-bold font-12">{validate.isNotEmpty(userInfo) ? userInfo.name : ''}</p>
                                                <p className="mb-0 text-secondary font-10">Emp ID: {validate.isNotEmpty(userInfo) ? userInfo.employeeId : ''}</p>
                                                <p className="mb-0 text-secondary font-10">{validate.isNotEmpty(userInfo) ? userInfo.email : ''}</p>
                                            </div>
                                            {validate.isNotEmpty(userInfo) &&
                                                <div className={`username ms-2 ${initialsBackground}`}>
                                                    {getInitials(userInfo.name)}
                                                </div>
                                            }
                                        </div>
                                    </button>
                                </div>
                            </Dropdown.Toggle>
                            <Dropdown.Menu align="end" className="custom-dropdown custom-dropdown-menu">
                                <Dropdown.Item className="custom-dropdown-item" href={`${API_URL}logout`} onClick={handleLogout}>Logout</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
                    </div>
                </header>
            </React.Fragment>
        </>
    )
}

export default MarketingHeaders;