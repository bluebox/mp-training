import {
    ADD_EDUCATION_REQUEST,
    ADD_EDUCATION_SUCCESS,
    ADD_EDUCATION_FAIL,
    ADD_EDUCATION_RESET,
    
    GET_EDUCATION_REQUEST,
    GET_EDUCATION_SUCCESS,
    GET_EDUCATION_FAIL,

    UPDATE_EDUCATION_REQUEST,
    UPDATE_EDUCATION_SUCCESS,
    UPDATE_EDUCATION_FAIL,
    UPDATE_EDUCATION_RESET,

    DELETE_EDUCATION_REQUEST,
    DELETE_EDUCATION_SUCCESS,
    DELETE_EDUCATION_FAIL,
} from '../constants/educationConstants';
import axios from 'axios';

export const addNewEducation = (values) => async (dispatch, getState) => {
    try{
        dispatch({
            type: ADD_EDUCATION_REQUEST
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

        const {data} = await axios.post(
            'http://127.0.0.1:8000/api/users/addEducation/',
            values,
            config
        )
        
        dispatch({
            type: ADD_EDUCATION_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: ADD_EDUCATION_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const resetAddedEducation = () => async (dispatch) => {

    dispatch({
        type: ADD_EDUCATION_RESET,
        payload: false
    })

}


export const getEducationDetails = () => async (dispatch, getState) => {
    try{
        dispatch({
            type: GET_EDUCATION_REQUEST
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
            `http://127.0.0.1:8000/api/users/getEducation/`,
            config
        )

        console.log(data)
        
        dispatch({
            type: GET_EDUCATION_SUCCESS,
            payload: data
        })



    }catch (error) {
        dispatch({
            type: GET_EDUCATION_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const editEducation = (education_id, values) => async (dispatch, getState) => {
    try{
        dispatch({
            type: UPDATE_EDUCATION_REQUEST
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
            `http://127.0.0.1:8000/api/users/editEducation/${education_id}`,
            values,
            config
        )
        
        dispatch({
            type: UPDATE_EDUCATION_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: UPDATE_EDUCATION_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 

export const deleteEducation = (education_id) => async (dispatch, getState) => {
    try{
        dispatch({
            type: DELETE_EDUCATION_REQUEST
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

        const {data} = await axios.delete(
            `http://127.0.0.1:8000/api/users/deleteEducation/${education_id}`,
            config
        )
        
        dispatch({
            type: DELETE_EDUCATION_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: DELETE_EDUCATION_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 

