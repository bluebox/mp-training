import {
    COMPANY_JOB_POST_LIST_REQUEST,
    COMPANY_JOB_POST_LIST_SUCCESS,
    COMPANY_JOB_POST_LIST_FAIL,

    COMPANY_JOB_POST_DETAILS_REQUEST,
    COMPANY_JOB_POST_DETAILS_SUCCESS,
    COMPANY_JOB_POST_DETAILS_FAIL,

    APPLIED_CANDIDATE_DETAILS_REQUEST,
    APPLIED_CANDIDATE_DETAILS_SUCCESS,
    APPLIED_CANDIDATE_DETAILS_FAIL,

    COMPANY_JOB_POST_DELETE_REQUEST,
    COMPANY_JOB_POST_DELETE_SUCCESS,
    COMPANY_JOB_POST_DELETE_FAIL,

} from '../constants/companyJobPostsConstants'
import axios from 'axios'


export const listCompanyJobPosts = () => async (dispatch, getState) => {
    try {
        dispatch({type: COMPANY_JOB_POST_LIST_REQUEST})

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
            `http://127.0.0.1:8000/api/company/posts/`,
            config
        )

        dispatch({
            type: COMPANY_JOB_POST_LIST_SUCCESS,
            payload: data
        })
    } catch (error) {
        dispatch({
            type: COMPANY_JOB_POST_LIST_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }
}

export const listCompanyJobPostDetails = (id) => async (dispatch, getState) => {
    try {
        dispatch({type: COMPANY_JOB_POST_DETAILS_REQUEST})

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
            `http://127.0.0.1:8000/api/company/posts/${id}`,
            config
        )

        dispatch({
            type: COMPANY_JOB_POST_DETAILS_SUCCESS,
            payload: data
        })
    } catch (error) {
        dispatch({
            type: COMPANY_JOB_POST_DETAILS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }
}

export const appliedJobPostCandidatesDetails = (candidate_id) => async (dispatch, getState) => {
    try {
        dispatch({type: APPLIED_CANDIDATE_DETAILS_REQUEST})

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
            `http://127.0.0.1:8000/api/company/candidate-details/${candidate_id}`,
            config
        )
        console.log(data)

        dispatch({
            type: APPLIED_CANDIDATE_DETAILS_SUCCESS,
            payload: data
        })
    } catch (error) {
        dispatch({
            type: APPLIED_CANDIDATE_DETAILS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }
}


export const deleteCompanyJobPost = (id) => async (dispatch, getState) => {
    try {
        dispatch({type: COMPANY_JOB_POST_DELETE_REQUEST})

        const {
            companyLogin: {companyInfo},
        } = getState()

        const config = {
            headers:{
                'Content-Type': 'application/json',
                Authorization: `Bearer ${companyInfo.token}` 
            }
        }

        const {data} = await axios.delete(
            `http://127.0.0.1:8000/api/company/delete-post/${id}`,
            config
        )

        dispatch({
            type: COMPANY_JOB_POST_DELETE_SUCCESS,
            payload: data
        })
    } catch (error) {
        dispatch({
            type: COMPANY_JOB_POST_DELETE_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }
}