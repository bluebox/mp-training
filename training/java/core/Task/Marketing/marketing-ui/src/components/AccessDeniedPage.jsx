import React from 'react'
import { API_URL } from "../services/ServiceConstants"
const AccessDeniedPage = () => {
  return (
    <React.Fragment>
        <div className=' grid-no-data-found shadow-none p-3 mb-5 bg-light rounded'>
            Access Denied or logged out. Please <span> <a href={`${API_URL}logout`}>  <button
            className='btn btn-danger'
            //  onClick={() => {
            //    window.location.href = `${API_URL}`
            // }}
            >Login</button> </a> again</span> 
        </div>
    </React.Fragment>
  )
}

export default AccessDeniedPage