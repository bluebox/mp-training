import {
    ADD_SKILL_REQUEST,
    ADD_SKILL_SUCCESS,
    ADD_SKILL_FAIL,
    ADD_SKILL_RESET,
    
    GET_SKILL_REQUEST,
    GET_SKILL_SUCCESS,
    GET_SKILL_FAIL,

    DELETE_SKILL_REQUEST,
    DELETE_SKILL_SUCCESS,
    DELETE_SKILL_FAIL,

    GET_ALL_SKILLS_REQUEST,
    GET_ALL_SKILLS_SUCCESS,
    GET_ALL_SKILLS_FAIL,


} from '../constants/skillsConstants';
import axios from 'axios';

export const addNewSkill = (skill_id) => async (dispatch, getState) => {
    try{
        dispatch({
            type: ADD_SKILL_REQUEST
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
            `http://127.0.0.1:8000/api/users/addSkill/`,
            {'skill_id': skill_id},
            config
        )
        
        dispatch({
            type: ADD_SKILL_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: ADD_SKILL_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const resetAddedSkill = () => async (dispatch) => {

    dispatch({
        type: ADD_SKILL_RESET,
        payload: false
    })

}


export const getSkillSet = () => async (dispatch, getState) => {
    try{
        dispatch({
            type: GET_SKILL_REQUEST
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
            `http://127.0.0.1:8000/api/users/getSkills/`,
            config
        )

        console.log(data)
        
        dispatch({
            type: GET_SKILL_SUCCESS,
            payload: data
        })



    }catch (error) {
        dispatch({
            type: GET_SKILL_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const getSkillsList = () => async (dispatch, getState) => {
    try{
        dispatch({
            type: GET_ALL_SKILLS_REQUEST
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
            `http://127.0.0.1:8000/api/users/getAllSkills/`,
            config
        )

        console.log(data)
        
        dispatch({
            type: GET_ALL_SKILLS_SUCCESS,
            payload: data
        })



    }catch (error) {
        dispatch({
            type: GET_ALL_SKILLS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 

export const deleteAddedSkill = (skill_id) => async (dispatch, getState) => {

    try{
        dispatch({
            type: DELETE_SKILL_REQUEST
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
            `http://127.0.0.1:8000/api/users/deleteSkill/${skill_id}`,
            config
        )

        
        dispatch({
            type: DELETE_SKILL_SUCCESS,
            payload: data
        })



    }catch (error) {
        dispatch({
            type: GET_ALL_SKILLS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }

}