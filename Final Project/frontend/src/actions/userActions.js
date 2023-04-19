import {
    USER_LOGIN_FAILURE, 
    USER_LOGIN_REQUEST, 
    USER_LOGIN_SUCCESS, 
    USER_LOGOUT,
    USER_DETAILS_FAILURE,
    USER_DETAILS_REQUEST,
    USER_DETAILS_SUCCESS,
    USER_DETAILS_RESET,
    USER_LIST_FAILURE,
    USER_LIST_REQUEST,
    USER_LIST_SUCCESS,
    USER_LIST_RESET,
} from '../constants/userConstants'
import axios from 'axios'

export const userLogin = (username, password) => async (dispatch) => {
    try{
        dispatch({
            type: USER_LOGIN_REQUEST
            
        })
        const config = {
            headers: {
                'Content-Type': 'application/json'
            }
        }
        const {data} = await axios.post(
            'http://127.0.0.1:8000/api/token/',
            {'username': username, 'password': password},
            config
        )
        dispatch({
            type: USER_LOGIN_SUCCESS,
            payload: data
        })
        localStorage.setItem('userInfo', JSON.stringify(data))

    }catch(error){
        dispatch({
            type: USER_LOGIN_FAILURE,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail: error.message,
        })
    }
}

export const logout = () => (dispatch) => {
    localStorage.removeItem('userInfo')
    dispatch({type: USER_LOGOUT})
    dispatch({type: USER_DETAILS_RESET})
    dispatch({type: USER_LIST_RESET})
}

export const getUserDetails = (id) => async (dispatch, getState) => {
    try{
        dispatch({
            type: USER_DETAILS_REQUEST
            
        })
        const {
            userLogin: {userInfo},
        } = getState()
        const config = {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        
        const {data} = await axios.get(
            `http://127.0.0.1:8000/api/user/${id}/`,
            config
        )
        dispatch({
            type: USER_DETAILS_SUCCESS,
            payload: data
        })


    }catch(error){
        dispatch({
            type: USER_DETAILS_FAILURE,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail: error.message,
        })
    }
}

export const listUser = () => async (dispatch, getState) => {
    try{
        dispatch({
            type: USER_LIST_REQUEST
            
        })
        const {
            userLogin: {userInfo},
        } = getState()
        const config = {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        
        const {data} = await axios.get(
            'http://127.0.0.1:8000/api/users/',
            config
        )
        dispatch({
            type: USER_LIST_SUCCESS,
            payload: data
        })

    }catch(error){
        dispatch({
            type: USER_LIST_FAILURE,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail: error.message,
        })
    }
}

