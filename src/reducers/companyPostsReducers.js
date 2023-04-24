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

} from '../constants/companyJobPostsConstants'

export const companyJobPostsListReducer = (state = {companyJobs: []}, action) => {
    switch (action.type) {
        case COMPANY_JOB_POST_LIST_REQUEST:
            return {loading: true, companyJobs:[]}
        
        case COMPANY_JOB_POST_LIST_SUCCESS:
            return {loading: false, companyJobs:action.payload }

        case COMPANY_JOB_POST_LIST_FAIL:
            return {loading: false, error:action.payload}

        default:
            return state
    
    } 
}

export const companyJobPostDetailsReducer = (state = {companyJob : {company:{}}}, action) => {
    switch (action.type) {
        case COMPANY_JOB_POST_DETAILS_REQUEST:
            return {loading: true, ...state}
        
        case COMPANY_JOB_POST_DETAILS_SUCCESS:
            return {loading: false, companyJob:action.payload }

        case COMPANY_JOB_POST_DETAILS_FAIL:
            return {loading: false, error:action.payload}

        default:
            return state
    
    } 
}


export const appliedCandaidatesDetails = (state = {}, action) => {
    switch (action.type) {
        case APPLIED_CANDIDATE_DETAILS_REQUEST:
            return {loading: true}
        
        case APPLIED_CANDIDATE_DETAILS_SUCCESS:
            return {loading: false, candidateDetails:action.payload }

        case APPLIED_CANDIDATE_DETAILS_FAIL:
            return {loading: false, error:action.payload}

        default:
            return state
    
    } 
}