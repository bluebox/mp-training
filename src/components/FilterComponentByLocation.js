import React, {useEffect} from 'react'
import { Form } from 'react-bootstrap'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { getCompaniesListByFilteringByLocation } from '../actions/companyActions'


function FilterComponentByLocation() {
    const dispatch = useDispatch()
    const navigate = useNavigate()

    function filterCompanyHandler(location){
        navigate(`/filtering?location=${location}`)
        dispatch(getCompaniesListByFilteringByLocation(location))
    }

  return (
    <> 
        <label style={{padding: '0.5rem 0rem'}}>Location Filter: </label>
        <Form.Select 
            size="sm"
            onChange={(e) => filterCompanyHandler(e.target.value)}
        >
            <option value='bangalore'>Banglore</option>
            <option value='hyderabad'>Hyderabad</option>
            <option value='pune'>Pune</option>
            <option value='kerala'>Kerala</option>
            
            
        </Form.Select>

    </>

  )
}

export default FilterComponentByLocation

{/* <div className='d-flex'>
<span >Location Filter</span>
<div style={{width:'100%'}}>

<Form.Select 
    size="sm"
    onChange={(e) => filterCompanyHandler(e.target.value)}
>
    <option value='bangalore'>Banglore</option>
    <option value='hyderabad'>Hyderabad</option>
    <option value='pune'>Pune</option>
    <option value='kerala'>Kerala</option>
    
    
</Form.Select>

</div>
</div> */}