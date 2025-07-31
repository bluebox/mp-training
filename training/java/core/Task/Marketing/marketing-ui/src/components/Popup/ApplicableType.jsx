import React from 'react'
import Validate from '../../helpers/Validate'
import { POPUP_ROLES } from '../../constants/MarketingRoles'

const ApplicableType=(props)=> {
  const validate = Validate()
  return (
    <>
      {
        validate.validateRole(POPUP_ROLES.createLabConfiguration) && validate.validateRole(POPUP_ROLES.createPharmaConfiguration) &&
        <div className="mb-4">
          <p className="custom-fieldset mb-2" >Applicable Type</p>
          <div className="form-check form-check-inline">
          <input type="radio" name="pharmacy" disabled={props.isEdit} className="form-check-input" id="pharmacy" value="pharmacy" onChange={(e) => props.handleChange(e.target.value)} checked={'pharmacy' == props.applicableType} />
          <label for="pharmacy" >
              Pharmacy
          </label>
          </div>
          <div className="form-check form-check-inline">
          <input type="radio" name="pathlabs" disabled={props.isEdit} className="form-check-input" id="pathlabs" value="pathlabs" onChange={(e) => props.handleChange(e.target.value)} checked={'pathlabs' == props.applicableType} />
          <label for="pathlabs" >
              Path Labs
          </label>
          </div>
        </div>
      }
    </>
  )
}

export default ApplicableType