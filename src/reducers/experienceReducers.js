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

export const addExperience = (state = {}, action) => {
    switch (action.type) {
        case ADD_EXPERIENCE_REQUEST:
            return {loading: true}
        
        case ADD_EXPERIENCE_SUCCESS:
            return {loading: false, addedExperience:action.payload }

        case ADD_EXPERIENCE_FAIL:
            return {loading: false, error:action.payload}

        case ADD_EXPERIENCE_RESET:
            return {loading: false, addedExperience: action.payload}

        default:
            return state
    
    } 
}

export const getExperience = (state = {getExperiences: []}, action) => {
    switch (action.type) {
        case GET_EXPERIENCE_REQUEST:
            return {loading: true}
        
        case GET_EXPERIENCE_SUCCESS:
            return {loading: false, getExperiences:action.payload }

        case GET_EXPERIENCE_FAIL:
            return {loading: false, error:action.payload}

        default:
            return state
    
    } 
}


export const editExperience = (state = {}, action) => {
    switch (action.type) {
        case UPDATE_EXPERIENCE_REQUEST:
            return {loading: true}
        
        case UPDATE_EXPERIENCE_SUCCESS:
            return {loading: false, editExperience:action.payload }

        case UPDATE_EXPERIENCE_FAIL:
            return {loading: false, error:action.payload}

        case UPDATE_EXPERIENCE_RESET:
            return {loading: false, editExperience: action.payload}

        default:
            return state
    
    } 
}


export const deleteExperience = (state = {}, action) => {
    switch (action.type) {
        case DELETE_EXPERIENCE_REQUEST:
            return {deleting: true}
        
        case DELETE_EXPERIENCE_SUCCESS:
            return {deleting: false, deleteExperience:true}

        case DELETE_EXPERIENCE_FAIL:
            return {deleting: false, error:action.payload}

        default:
            return state
    
    } 
}