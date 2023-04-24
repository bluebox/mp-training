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

export const addSkill = (state = {}, action) => {
    switch (action.type) {
        case ADD_SKILL_REQUEST:
            return {loading: true}
        
        case ADD_SKILL_SUCCESS:
            return {loading: false, addSkill:action.payload }

        case ADD_SKILL_FAIL:
            return {loading: false, error:action.payload}

        case ADD_SKILL_RESET:
            return {loading: false, addSkill: action.payload}

        default:
            return state
    
    } 
}

export const getSkills = (state = {getSkills: []}, action) => {
    switch (action.type) {
        case GET_SKILL_REQUEST:
            return {loading: true, ...state}
        
        case GET_SKILL_SUCCESS:
            return {loading: false, getSkills:action.payload.skills }

        case GET_SKILL_FAIL:
            return {loading: false, error:action.payload}

        default:
            return state
    
    } 
}


export const deleteSkill = (state = {}, action) => {
    switch (action.type) {
        case DELETE_SKILL_REQUEST:
            return {loading: true}
        
        case DELETE_SKILL_SUCCESS:
            return {loading: false, deleteSkill:action.payload }

        case DELETE_SKILL_FAIL:
            return {loading: false, error:action.payload}

        default:
            return state
    
    } 
}

export const getSkillsList = (state = {totalSkills: []}, action) => {
    switch (action.type) {
        case GET_ALL_SKILLS_REQUEST:
            return {loading: true, ...state}
        
        case GET_ALL_SKILLS_SUCCESS:
            return {loading: false, totalSkills:action.payload.skillsList }

        case GET_ALL_SKILLS_FAIL:
            return {loading: false, error:action.payload}

        default:
            return state
    
    } 
}
