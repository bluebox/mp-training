import { 
    COMPANY_LOGIN_REQUEST,
    COMPANY_LOGIN_SUCCESS,
    COMPANY_LOGIN_FAIL,

    COMPANY_LOGOUT,
    
    COMPANY_REGISTER_REQUEST,
    COMPANY_REGISTER_SUCCESS,
    COMPANY_REGISTER_FAIL,

    COMPANY_DETAILS_REQUEST,
    COMPANY_DETAILS_SUCCESS,
    COMPANY_DETAILS_FAIL,
    COMPANY_DETAILS_RESET,

    COMPANY_UPDATE_PROFILE_REQUEST,
    COMPANY_UPDATE_PROFILE_SUCCESS,
    COMPANY_UPDATE_PROFILE_FAIL,

    GET_COMPANY_DETAIL_PROFILE_REQUEST,
    GET_COMPANY_DETAIL_PROFILE_SUCCESS,
    GET_COMPANY_DETAIL_PROFILE_FAIL,
    GET_COMPANY_DETAIL_PROFILE_RESET,

    UPDATE_COMPANY_DETAIL_PROFILE_REQUEST,
    UPDATE_COMPANY_DETAIL_PROFILE_SUCCESS,
    UPDATE_COMPANY_DETAIL_PROFILE_FAIL,

    GET_COMPANIES_LIST_REQUEST,
    GET_COMPANIES_LIST_SUCCESS,
    GET_COMPANIES_LIST_FAIL,

    GET_COMPANIES_LIST_BY_FILTER_REQUEST,
    GET_COMPANIES_LIST_BY_FILTER_SUCCESS,
    GET_COMPANIES_LIST_BY_FILTER_FAIL,

    GET_COMPANIES_LIST_BY_FILTER_BY_LOCATION_REQUEST,
    GET_COMPANIES_LIST_BY_FILTER_BY_LOCATION_SUCCESS,
    GET_COMPANIES_LIST_BY_FILTER_BY_LOCATION_FAIL,

 } from '../constants/companyConstants'
import axios from 'axios'

export const loginCompany = (email, password) => async (dispatch) => {
    try{
        dispatch({
            type: COMPANY_LOGIN_REQUEST
        })

        const config = {
            headers:{
                'Content-Type': 'application/json'
            }
        }

        const {data} = await axios.post(
            'http://127.0.0.1:8000/api/users/login/',
            {'username': email, 'password': password},
            config
        )
        
        dispatch({
            type: COMPANY_LOGIN_SUCCESS,
            payload: data
        })
         
        localStorage.setItem('companyInfo', JSON.stringify(data))



    }catch (error) {
        dispatch({
            type: COMPANY_LOGIN_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const logoutCompany = () => (dispatch) => {
    localStorage.removeItem('companyInfo')
    dispatch({type: COMPANY_LOGOUT})
    dispatch({type: COMPANY_DETAILS_RESET})
    dispatch({type: GET_COMPANY_DETAIL_PROFILE_RESET})
}


export const registerCompany = (values) => async (dispatch) => {
    try{
        dispatch({
            type: COMPANY_REGISTER_REQUEST
        })

        const config = {
            headers:{
                'Content-Type': 'application/json'
            }
        }

        const {data} = await axios.post(
            'http://127.0.0.1:8000/api/company/register/',
            values,
            config
        )
        
        dispatch({
            type: COMPANY_REGISTER_SUCCESS,
            payload: data
        })

        dispatch({
            type: COMPANY_LOGIN_SUCCESS,
            payload: data
        })
         
        localStorage.setItem('companyInfo', JSON.stringify(data))



    }catch (error) {
        dispatch({
            type: COMPANY_REGISTER_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const getCompanyDetails = (keyword) => async (dispatch, getState) => {
    try{
        dispatch({
            type: COMPANY_DETAILS_REQUEST
        })

        const {
            companyLogin: {companyInfo},
        } = getState()

        const config = {
            headers:{
                'Content-Type': 'application/json',
                Authorization: `Bearer ${companyInfo.token}` 
            }
        }

        const {data} = await axios.get(
            `http://127.0.0.1:8000/api/company/${keyword}/`,
            config
        )

        console.log(data)
        
        dispatch({
            type: COMPANY_DETAILS_SUCCESS,
            payload: data
        })



    }catch (error) {
        dispatch({
            type: COMPANY_DETAILS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const updateCompanyProfile = (user) => async (dispatch, getState) => {
    try{
        dispatch({
            type: COMPANY_UPDATE_PROFILE_REQUEST
        })

        const {
            companyLogin: {companyInfo},
        } = getState()

        const config = {
            headers:{
                'Content-Type': 'application/json',
                Authorization: `Bearer ${companyInfo.token}` 
            }
        }

        const {data} = await axios.put(
            `http://127.0.0.1:8000/api/company/profile/update/`,
            user,
            config
        )

        
        dispatch({
            type: COMPANY_UPDATE_PROFILE_SUCCESS,
            payload: data
        })

        dispatch({
            type: COMPANY_LOGIN_SUCCESS,
            payload: data
        })

        localStorage.setItem('companyInfo', JSON.stringify(data))

    }catch (error) {
        dispatch({
            type: COMPANY_UPDATE_PROFILE_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const getCompanyProfileDetails = () => async (dispatch, getState) => {
    try{
        dispatch({
            type: GET_COMPANY_DETAIL_PROFILE_REQUEST
        })

        const {
            companyLogin: {companyInfo},
        } = getState()

        const config = {
            headers:{
                'Content-Type': 'application/json',
                Authorization: `Bearer ${companyInfo.token}` 
            }
        }

        const {data} = await axios.get(
            `http://127.0.0.1:8000/api/company/company-profile/`,
            config
        )

        console.log(data)
        
        dispatch({
            type: GET_COMPANY_DETAIL_PROFILE_SUCCESS,
            payload: data
        })



    }catch (error) {
        dispatch({
            type: GET_COMPANY_DETAIL_PROFILE_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const updateCompanyDetailProfile = (industryType, companyName, description, established, image) => async (dispatch, getState) => {
    try{
        dispatch({
            type: UPDATE_COMPANY_DETAIL_PROFILE_REQUEST
        })



        const {
            companyLogin: {companyInfo},
        } = getState()

        const config = {
            headers:{
                'Content-Type': 'multipart/form-data',
                Authorization: `Bearer ${companyInfo.token}` 
            }
        }
        console.log(image)

        const {data} = await axios.patch(
            `http://127.0.0.1:8000/api/company/company-profile/update`,
            {'industryType': industryType, 'companyName': companyName, 'description': description, 'established': established, 'image': image},
            config
        )

        
        dispatch({
            type: UPDATE_COMPANY_DETAIL_PROFILE_SUCCESS,
            payload: data
        })

        dispatch({
            type: COMPANY_LOGIN_SUCCESS,
            payload: {...companyInfo, 'name': data.company_name}
        })

        localStorage.setItem('companyInfo', JSON.stringify({...companyInfo, 'name': data.company_name}))

    }catch (error) {
        dispatch({
            type: UPDATE_COMPANY_DETAIL_PROFILE_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


}

export const getCompanyiesList = () => async (dispatch, getState) => {
    try{
        dispatch({
            type: GET_COMPANIES_LIST_REQUEST
        })

        const config = {
            headers:{
                'Content-Type': 'application/json',
            }
        }

        const {data} = await axios.get(
            `http://127.0.0.1:8000/api/company/companies-list/`,
            config
        )

        console.log(data)
        
        dispatch({
            type: GET_COMPANIES_LIST_SUCCESS,
            payload: data
        })



    }catch (error) {
        dispatch({
            type: GET_COMPANIES_LIST_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 

export const getCompaniesListByFiltering = (companyName) => async (dispatch, getState) => {
    try{
        dispatch({
            type: GET_COMPANIES_LIST_BY_FILTER_REQUEST
        })


        const config = {
            headers:{
                'Content-Type': 'application/json',
            }
        }

        const {data} = await axios.get(
            `http://127.0.0.1:8000/api/company/companies-list-filter/${companyName}`,
            
            config
        )

        console.log(data)
        
        dispatch({
            type: GET_COMPANIES_LIST_BY_FILTER_SUCCESS,
            payload: data
        })



    }catch (error) {
        dispatch({
            type: GET_COMPANIES_LIST_BY_FILTER_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 

export const getCompaniesListByFilteringByLocation = (location) => async (dispatch, getState) => {
    try{
        dispatch({
            type: GET_COMPANIES_LIST_BY_FILTER_BY_LOCATION_REQUEST
        })


        const config = {
            headers:{
                'Content-Type': 'application/json', 
            }
        }

        const {data} = await axios.get(
            `http://127.0.0.1:8000/api/company/companies-location-filter/${location}`,
            
            config
        )

        console.log(data)
        
        dispatch({
            type: GET_COMPANIES_LIST_BY_FILTER_BY_LOCATION_SUCCESS,
            payload: data
        })



    }catch (error) {
        dispatch({
            type: GET_COMPANIES_LIST_BY_FILTER_BY_LOCATION_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 