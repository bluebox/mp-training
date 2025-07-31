import React from "react";
import { Button } from "react-bootstrap";
import NoDataFoundIcon from '../../images/No-Data-Found.svg'

const NoDataFound = (props) => {

    const loadPage = () => {
        window.location.replace(window.location.pathname);
    }

    return (
        <React.Fragment>
            <div className={`d-flex justify-content-center align-items-center h-100 ${props.grid ? "page-spinner-position":""}`}>
                <div className="text-center">
                    <img src={NoDataFoundIcon} alt="No Data Found" />
                    <p className="mb-0">{props.text}</p>
                    {props.searchButton ? <Button variant="dark" className="mt-3" onClick={() => loadPage()}>Search Again</Button> : null }
                </div>
            </div>
        </React.Fragment>
    )
}
export default NoDataFound