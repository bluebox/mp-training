import { 
    USER_LOGIN_REQUEST,
    USER_LOGIN_SUCCESS,

    USER_LOGIN_FAIL,

    USER_LOGOUT,

    USER_REGISTER_REQUEST,
    USER_REGISTER_SUCCESS,
    USER_REGISTER_FAIL,

    USER_DETAILS_REQUEST,
    USER_DETAILS_SUCCESS,
    USER_DETAILS_FAIL,
    USER_DETAILS_RESET,

    USER_UPDATE_PROFILE_REQUEST,
    USER_UPDATE_PROFILE_SUCCESS,
    USER_UPDATE_PROFILE_FAIL,
    
    APPLIED_COMPANY_USER_DETAILS_REQUEST,
    APPLIED_COMPANY_USER_DETAILS_SUCCESS,
    APPLIED_COMPANY_USER_DETAILS_FAIL,
    APPLIED_COMPANY_USER_DETAILS_RESET,


 } from '../constants/userConstants'

import {
    COMPANY_LOGIN_SUCCESS
} from '../constants/companyConstants'

import axios from 'axios'

export const login = (values) => async (dispatch) => {
    try{
        dispatch({
            type: USER_LOGIN_REQUEST
        })

        const config = {
            headers:{
                'Content-Type': 'application/json'
            }
        }

        const {data} = await axios.post(
            'http://127.0.0.1:8000/api/users/login/',
            values,
            config
        )

        if (!data.isAdmin){
            dispatch({
                type: USER_LOGIN_SUCCESS,
                payload: data
            })
             
            localStorage.setItem('userInfo', JSON.stringify(data))
        }else {
            dispatch({
                type: COMPANY_LOGIN_SUCCESS,
                payload: data
            })
            localStorage.setItem('companyInfo', JSON.stringify(data))
        }
        
        



    }catch (error) {
        dispatch({
            type: USER_LOGIN_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const logout = () => (dispatch) => {
    localStorage.removeItem('userInfo')
    dispatch({type: USER_LOGOUT})
    dispatch({type: USER_DETAILS_RESET})
}


export const register = (values) => async (dispatch) => {
    try{
        dispatch({
            type: USER_REGISTER_REQUEST
        })

        const config = {
            headers:{
                'Content-Type': 'application/json'
            }
        }

        const {data} = await axios.post(
            'http://127.0.0.1:8000/api/users/register/',
            values,
            config
        )
        
        dispatch({
            type: USER_REGISTER_SUCCESS,
            payload: data
        })

        dispatch({
            type: USER_LOGIN_SUCCESS,
            payload: data
        })
         
        localStorage.setItem('userInfo', JSON.stringify(data))



    }catch (error) {
        dispatch({
            type: USER_REGISTER_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const getUserDetails = (keyword) => async (dispatch, getState) => {
    try{
        dispatch({
            type: USER_DETAILS_REQUEST
        })

        const {
            userLogin: {userInfo},
        } = getState()

        const config = {
            headers:{
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}` 
            }
        }

        const {data} = await axios.get(
            `http://127.0.0.1:8000/api/users/${keyword}/`,
            config
        )

        console.log(data)
        
        dispatch({
            type: USER_DETAILS_SUCCESS,
            payload: data
        })



    }catch (error) {
        dispatch({
            type: USER_DETAILS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const updateUserProfile = (user) => async (dispatch, getState) => {
    try{
        dispatch({
            type: USER_UPDATE_PROFILE_REQUEST
        })

        const {
            userLogin: {userInfo},
        } = getState()

        const config = {
            headers:{
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}` 
            }
        }

        const {data} = await axios.put(
            `http://127.0.0.1:8000/api/users/profile/update/`,
            user,
            config
        )

        
        dispatch({
            type: USER_UPDATE_PROFILE_SUCCESS,
            payload: data
        })

        dispatch({
            type: USER_LOGIN_SUCCESS,
            payload: data
        })

        localStorage.setItem('userInfo', JSON.stringify(data))

    }catch (error) {
        dispatch({
            type: USER_UPDATE_PROFILE_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 



export const viewAppliedCandidates = (id) => async (dispatch, getState) => {
    try{
        dispatch({
            type: APPLIED_COMPANY_USER_DETAILS_REQUEST
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
            `http://127.0.0.1:8000/api/company/posts/${id}/getAppliedCandidates/`,
            config
        )
        console.log(data)
        
        dispatch({
            type: APPLIED_COMPANY_USER_DETAILS_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: APPLIED_COMPANY_USER_DETAILS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const resetAddedCandidatesForAPost = () => async (dispatch) => {

    dispatch({
        type: APPLIED_COMPANY_USER_DETAILS_RESET,
        payload: false
    })

}