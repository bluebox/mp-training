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

export const jobPostsListReducer = (state = {jobs: []}, action) => {
    switch (action.type) {
        case JOB_POST_LIST_REQUEST:
            return {loading: true, jobs:[]}
        
        case JOB_POST_LIST_SUCCESS:
            return {
                loading: false, 
                jobs:action.payload.jobPosts,
                page: action.payload.page,
                pages: action.payload.pages
            }

        case JOB_POST_LIST_FAIL:
            return {loading: false, error:action.payload}

        default:
            return state
    
    } 
}

export const jobPostDetailsReducer = (state = {job : {company:{}}}, action) => {
    switch (action.type) {
        case JOB_POST_DETAILS_REQUEST:
            return {loading: true, ...state}
        
        case JOB_POST_DETAILS_SUCCESS:
            return {loading: false, job:action.payload }

        case JOB_POST_DETAILS_FAIL:
            return {loading: false, error:action.payload}

        default:
            return state
    
    } 
}


export const addJobPost = (state = {}, action) => {
    switch (action.type) {
        case JOB_POST_ADD_REQUEST:
            return {loading: true}
        
        case JOB_POST_ADD_SUCCESS:
            return {loading: false, addedJobPost:action.payload }

        case JOB_POST_ADD_FAIL:
            return {loading: false, error:action.payload}

        case JOB_POST_ADD_RESET:
            return {loading: false, addedJobPost: action.payload}

        default:
            return state
    
    } 
}

export const editJobPost = (state = {}, action) => {
    switch (action.type) {
        case JOB_POST_EDIT_REQUEST:
            return {loading: true}
        
        case JOB_POST_EDIT_SUCCESS:
            return {loading: false, editedJobPost:action.payload }

        case JOB_POST_EDIT_FAIL:
            return {loading: false, error:action.payload}

        case JOB_POST_EDIT_RESET:
            return {loading: false, editedJobPost: action.payload}

        default:
            return state
    
    } 
}