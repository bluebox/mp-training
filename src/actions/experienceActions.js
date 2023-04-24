import {
    ADD_EXPERIENCE_REQUEST,
    ADD_EXPERIENCE_SUCCESS,
    ADD_EXPERIENCE_FAIL,
    ADD_EXPERIENCE_RESET,
    
    GET_EXPERIENCE_REQUEST,
    GET_EXPERIENCE_SUCCESS,
    GET_EXPERIENCE_FAIL,

    UPDATE_EXPERIENCE_REQUEST,
    UPDATE_EXPERIENCE_SUCCESS,
    UPDATE_EXPERIENCE_FAIL,
    UPDATE_EXPERIENCE_RESET,

    DELETE_EXPERIENCE_REQUEST,
    DELETE_EXPERIENCE_SUCCESS,
    DELETE_EXPERIENCE_FAIL,
} from '../constants/experienceConstants';
import axios from 'axios';

export const addNewExperience = (values) => async (dispatch, getState) => {
    try{
        dispatch({
            type: ADD_EXPERIENCE_REQUEST
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
            'http://127.0.0.1:8000/api/users/addExperience/',
            values,
            config
        )
        
        dispatch({
            type: ADD_EXPERIENCE_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: ADD_EXPERIENCE_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const resetAddedExperience = () => async (dispatch) => {

    dispatch({
        type: ADD_EXPERIENCE_RESET,
        payload: false
    })

}


export const getExperienceDetails = () => async (dispatch, getState) => {
    try{
        dispatch({
            type: GET_EXPERIENCE_REQUEST
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
            `http://127.0.0.1:8000/api/users/getExperience/`,
            config
        )

        console.log(data)
        
        dispatch({
            type: GET_EXPERIENCE_SUCCESS,
            payload: data
        })



    }catch (error) {
        dispatch({
            type: GET_EXPERIENCE_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const editExperience = (experience_id, values) => async (dispatch, getState) => {
    try{
        dispatch({
            type: UPDATE_EXPERIENCE_REQUEST
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
            `http://127.0.0.1:8000/api/users/editExperience/${experience_id}`,
            values,
            config
        )
        
        dispatch({
            type: UPDATE_EXPERIENCE_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: UPDATE_EXPERIENCE_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const deleteUserExperience = (experience_id) => async (dispatch, getState) => {
    try{
        dispatch({
            type: DELETE_EXPERIENCE_REQUEST
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
            `http://127.0.0.1:8000/api/users/deleteExperience/${experience_id}`,
            config
        )
        
        dispatch({
            type: DELETE_EXPERIENCE_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: DELETE_EXPERIENCE_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


