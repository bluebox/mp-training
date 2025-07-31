import dateFormat from 'dateformat';
import React from "react";

const ViewMetaInfo = (metaData) => {
  return (
    <>
    <div className='col-3'>
        <label className='font-12 text-secondary mb-1'>Created By</label>
        <h6 className='mb-0 font-14'>{metaData['createdBy'] ? metaData['createdBy'] : " - "}</h6>
    </div> 
    <div className='col-3'>
        <label className='font-12 text-secondary mb-1'>Date Created</label>
        <h6 className='mb-0 font-14'>{metaData['dateCreated'] ? dateFormat(metaData['dateCreated'],"d mmm, yyyy hh:MM TT") : " - "}</h6>
    </div> 
    <div className='col-3'>
        <label className='font-12 text-secondary mb-1'>Approved/Rejected By</label>
        <h6 className='mb-0 font-14'>{metaData['approvedBy'] ? metaData['approvedBy'] : " - "}</h6>
    </div> 
    <div className='col-3'>
        <label className='font-12 text-secondary mb-1'>Date Approved/Rejected</label>
        <h6 className='mb-0 font-14'>{metaData['dateApproved'] ? dateFormat(metaData['dateApproved'],"d mmm, yyyy hh:MM TT") : " - "}</h6>
    </div>   
    </>
    )
}

export default ViewMetaInfo