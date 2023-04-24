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

export const addEducation = (state = {}, action) => {
    switch (action.type) {
        case ADD_EDUCATION_REQUEST:
            return {loading: true}
        
        case ADD_EDUCATION_SUCCESS:
            return {loading: false, addedEducation:action.payload }

        case ADD_EDUCATION_FAIL:
            return {loading: false, error:action.payload}

        case ADD_EDUCATION_RESET:
            return {loading: false, addedEducation: action.payload}

        default:
            return state
    
    } 
}

export const getEducation = (state = {getEducations: []}, action) => {
    switch (action.type) {
        case GET_EDUCATION_REQUEST:
            return {loading: true}
        
        case GET_EDUCATION_SUCCESS:
            return {loading: false, getEducations:action.payload }

        case GET_EDUCATION_FAIL:
            return {loading: false, error:action.payload}

        default:
            return state
    
    } 
}


export const editEducation = (state = {}, action) => {
    switch (action.type) {
        case UPDATE_EDUCATION_REQUEST:
            return {loading: true}
        
        case UPDATE_EDUCATION_SUCCESS:
            return {loading: false, editEducation:action.payload }

        case UPDATE_EDUCATION_FAIL:
            return {loading: false, error:action.payload}

        case UPDATE_EDUCATION_RESET:
            return {loading: false, editEducation: action.payload}

        default:
            return state
    
    } 
}


export const deleteEducation = (state = {}, action) => {
    switch (action.type) {
        case DELETE_EDUCATION_REQUEST:
            return {deleting: true}
        
        case DELETE_EDUCATION_SUCCESS:
            return {deleting: false, educationDeleted:true }

        case DELETE_EDUCATION_FAIL:
            return {deleting: false, error:action.payload}

        default:
            return state
    
    } 
}