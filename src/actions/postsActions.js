import axios from "axios"

import {
    JOB_POST_LIST_REQUEST,
    JOB_POST_LIST_SUCCESS,
    JOB_POST_LIST_FAIL,

    JOB_POST_DETAILS_REQUEST,
    JOB_POST_DETAILS_SUCCESS,
    JOB_POST_DETAILS_FAIL,

    JOB_POST_ADD_REQUEST,
    JOB_POST_ADD_SUCCESS,
    JOB_POST_ADD_FAIL,
    JOB_POST_ADD_RESET,

    JOB_POST_EDIT_REQUEST,
    JOB_POST_EDIT_SUCCESS,
    JOB_POST_EDIT_FAIL,
    JOB_POST_EDIT_RESET,

} from '../constants/postsConstants'



export const listJobPosts = (keyword, currentPage) => async (dispatch) => {
    try {
        dispatch({type: JOB_POST_LIST_REQUEST})
        const {data} = await axios.get(`http://127.0.0.1:8000/api/posts/?keyword=${keyword}&page=${currentPage}`)
        console.log(data)
        dispatch({
            type: JOB_POST_LIST_SUCCESS,
            payload: data
        })
    } catch (error) {
        dispatch({
            type: JOB_POST_LIST_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }
}

export const listJobPostDetails = (id) => async (dispatch) => {
    try {
        dispatch({type: JOB_POST_DETAILS_REQUEST})
        const {data} = await axios.get(`http://127.0.0.1:8000/api/posts/${id}`)
        dispatch({
            type: JOB_POST_DETAILS_SUCCESS,
            payload: data
        })
    } catch (error) {
        dispatch({
            type: JOB_POST_DETAILS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }
}


export const addNewJobPostFromCompany = (values) => async (dispatch, getState) => {
    try{
        dispatch({
            type: JOB_POST_ADD_REQUEST
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

        const {data} = await axios.post(
            'http://127.0.0.1:8000/api/posts/addJobPost/',
            values,
            config
        )
        
        dispatch({
            type: JOB_POST_ADD_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: JOB_POST_ADD_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const resetAddedJobPost = () => async (dispatch) => {

    dispatch({
        type: JOB_POST_ADD_RESET,
        payload: false
    })

}


export const editJobPost = (job_post_id, values) => async (dispatch, getState) => {
    try{
        dispatch({
            type: JOB_POST_EDIT_REQUEST
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
            `http://127.0.0.1:8000/api/posts/editJobPost/${job_post_id}/`,
            values,
            config
        )
        
        dispatch({
            type: JOB_POST_EDIT_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: JOB_POST_EDIT_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const resetEditedJobPost = () => async (dispatch) => {

    dispatch({
        type: JOB_POST_EDIT_RESET,
        payload: false
    })

}