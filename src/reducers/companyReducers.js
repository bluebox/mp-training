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
    COMPANY_UPDATE_PROFILE_RESET,

    GET_COMPANY_DETAIL_PROFILE_REQUEST,
    GET_COMPANY_DETAIL_PROFILE_SUCCESS,
    GET_COMPANY_DETAIL_PROFILE_FAIL,
    GET_COMPANY_DETAIL_PROFILE_RESET,

    UPDATE_COMPANY_DETAIL_PROFILE_REQUEST,
    UPDATE_COMPANY_DETAIL_PROFILE_SUCCESS,
    UPDATE_COMPANY_DETAIL_PROFILE_FAIL,
    UPDATE_COMPANY_DETAIL_PROFILE_RESET,

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


export const companyLoginReducer = (state = {}, action) => {
    switch (action.type) {
        case COMPANY_LOGIN_REQUEST:
            return {loading: true}
        
        case COMPANY_LOGIN_SUCCESS:
            return {loading: false, companyInfo:action.payload }

        case COMPANY_LOGIN_FAIL:
            return {loading: false, error:action.payload}

        case COMPANY_LOGOUT:
            return {}

        default:
            return state
    
    } 
}


export const companyRegister = (state = {}, action) => {
    switch (action.type) {
        case COMPANY_REGISTER_REQUEST:
            return {loading: true}
        
        case COMPANY_REGISTER_SUCCESS:
            return {loading: false, companyInfo:action.payload }

        case COMPANY_REGISTER_FAIL:
            return {loading: false, error:action.payload}

        case COMPANY_LOGOUT:
            return {}

        default:
            return state
    
    } 
}


export const companyDetailsReducer = (state = {company: {}}, action) => {
    switch (action.type) {
        case COMPANY_DETAILS_REQUEST:
            return {...state, loading: true}
        
        case COMPANY_DETAILS_SUCCESS:
            return {loading: false, company:action.payload }

        case COMPANY_DETAILS_FAIL:
            return {loading: false, error:action.payload}

        case COMPANY_DETAILS_RESET:
            return {user: {}}

        default:
            return state
    
    } 
}

export const companyUpdateProfileReducer = (state = {}, action) => {
    switch (action.type) {
        case COMPANY_UPDATE_PROFILE_REQUEST:
            return {loading: true}
        
        case COMPANY_UPDATE_PROFILE_SUCCESS:
            return {loading: false, success: true, companyInfo:action.payload }

        case COMPANY_UPDATE_PROFILE_FAIL:
            return {loading: false, error:action.payload}

        case COMPANY_UPDATE_PROFILE_RESET:
            return {}

        default:
            return state
    
    } 
}


export const companyDetailProfileReducer = (state = {}, action) => {
    switch (action.type) {
        case GET_COMPANY_DETAIL_PROFILE_REQUEST:
            return {...state, loading: true}
        
        case GET_COMPANY_DETAIL_PROFILE_SUCCESS:
            return {loading: false, company:action.payload }

        case GET_COMPANY_DETAIL_PROFILE_FAIL:
            return {loading: false, error:action.payload}

        case GET_COMPANY_DETAIL_PROFILE_RESET:
            return {}

        default:
            return state
    
    } 
}

export const companyProfileUpdateReducer = (state = {}, action) => {
    switch (action.type) {
        case UPDATE_COMPANY_DETAIL_PROFILE_REQUEST:
            return {loading: true}
        
        case UPDATE_COMPANY_DETAIL_PROFILE_SUCCESS:
            return {loading: false, success: true, companyInfo:action.payload }

        case UPDATE_COMPANY_DETAIL_PROFILE_FAIL:
            return {loading: false, error:action.payload}

        case UPDATE_COMPANY_DETAIL_PROFILE_RESET:
            return {}

        default:
            return state
    
    } 
}

export const companyListReducer = (state = {companies:[], loading:true}, action) => {
    switch (action.type) {
        case GET_COMPANIES_LIST_REQUEST:
            return {loading: true}
        
        case GET_COMPANIES_LIST_SUCCESS:
            return {loading: false, companies:action.payload }

        case GET_COMPANIES_LIST_FAIL:
            return {loading: false, error:action.payload}

        default:
            return state
    
    } 
}


export const companiesListByFilter = (state = {companiesList:[], listLoading:true}, action) => {
    switch (action.type) {
        case GET_COMPANIES_LIST_BY_FILTER_REQUEST:
            return {listLoading: true}
        
        case GET_COMPANIES_LIST_BY_FILTER_SUCCESS:
            return {listLoading: false, companiesList:action.payload }

        case GET_COMPANIES_LIST_BY_FILTER_FAIL:
            return {listLoading: false, error:action.payload}

        default:
            return state
    
    } 
}

export const companiesListByFilterByCompany = (state = {companiesList:[], listLoading:true}, action) => {
    switch (action.type) {
        case GET_COMPANIES_LIST_BY_FILTER_BY_LOCATION_REQUEST:
            return {listLoading: true}
        
        case GET_COMPANIES_LIST_BY_FILTER_BY_LOCATION_SUCCESS:
            return {listLoading: false, companiesList:action.payload }

        case GET_COMPANIES_LIST_BY_FILTER_BY_LOCATION_FAIL:
            return {listLoading: false, error:action.payload}

        default:
            return state
    
    } 
}