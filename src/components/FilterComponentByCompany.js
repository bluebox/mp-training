import React, {useEffect} from 'react'
import { Form } from 'react-bootstrap'
import { getCompanyiesList, getCompaniesListByFiltering } from '../actions/companyActions'
import { useDispatch, useSelector } from 'react-redux'
import Loader from './Loader'
import Message from './Message'
import { useNavigate } from 'react-router-dom'


function FilterComponentByCompany() {
    const dispatch = useDispatch()
    const navigate = useNavigate()
    const companyList = useSelector(state => state.companyList)
    const {error, loading, companies} = companyList
    useEffect(() => {
        dispatch(getCompanyiesList())
    }, [])

    function filterCompanyHandler(companyName){
        navigate(`/filter?company=${companyName}`)
        dispatch(getCompaniesListByFiltering(companyName))
    }

  return (
        <>
        <label style={{padding: '0.5rem 0rem'}}>Company Filter: </label>
        {
            loading ? <Loader /> : 
            error ? <Message variant='danger'>{error}</Message> :
            <Form.Select 
                size="sm"
                onChange={(e) => filterCompanyHandler(e.target.value)}
            >
                <option value='none'>-- Select Company --</option>
                {companies.map(company => (
                    <option value={company.company_name}>{company.company_name}</option>
                ))}
                
            </Form.Select>
        }
        </>

  )
}

export default FilterComponentByCompany


{/* <div className='d-flex'>
        <span >Company Filter</span>
        <div style={{width:'100%'}}>
        {
            loading ? <Loader /> : 
            error ? <Message variant='danger'>{error}</Message> :
            <Form.Select 
                size="sm"
                onChange={(e) => filterCompanyHandler(e.target.value)}
            >
                <option value='none'>-- Select Company --</option>
                {companies.map(company => (
                    <option value={company.company_name}>{company.company_name}</option>
                ))}
                
            </Form.Select>
        }
        </div>
    </div> */}