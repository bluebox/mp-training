import {
    ADD_ADDRESS_REQUEST,
    ADD_ADDRESS_SUCCESS,
    ADD_ADDRESS_FAIL,
    ADD_ADDRESS_RESET,
    
    GET_ADDRESS_REQUEST,
    GET_ADDRESS_SUCCESS,
    GET_ADDRESS_FAIL,

    EDIT_ADDRESS_REQUEST,
    EDIT_ADDRESS_SUCCESS,
    EDIT_ADDRESS_FAIL,
    EDIT_ADDRESS_RESET,

    DELETE_ADDRESS_REQUEST,
    DELETE_ADDRESS_SUCCESS,
    DELETE_ADDRESS_FAIL,

} from '../constants/addressConstants';

export const addAddress = (state = {}, action) => {
    switch (action.type) {
        case ADD_ADDRESS_REQUEST:
            return {loading: true}
        
        case ADD_ADDRESS_SUCCESS:
            return {loading: false, addedAddress:action.payload }

        case ADD_ADDRESS_FAIL:
            return {loading: false, error:action.payload}

        case ADD_ADDRESS_RESET:
            return {loading: false, addedAddress: action.payload}

        default:
            return state
    
    } 
}

export const getAddress = (state = {getAddresses: []}, action) => {
    switch (action.type) {
        case GET_ADDRESS_REQUEST:
            return {loading: true}
        
        case GET_ADDRESS_SUCCESS:
            return {loading: false, getAddresses:action.payload }

        case GET_ADDRESS_FAIL:
            return {loading: false, error:action.payload}

        default:
            return state
    
    } 
}


export const editAddress = (state = {}, action) => {
    switch (action.type) {
        case EDIT_ADDRESS_REQUEST:
            return {loading: true}
        
        case EDIT_ADDRESS_SUCCESS:
            return {loading: false, editAddress:action.payload }

        case EDIT_ADDRESS_FAIL:
            return {loading: false, error:action.payload}

        case EDIT_ADDRESS_RESET:
            return {loading: false, editAddress: action.payload}

        default:
            return state
    
    } 
}


export const deleteAddress = (state = {}, action) => {
    switch (action.type) {
        case DELETE_ADDRESS_REQUEST:
            return {deleting: true}
        
        case DELETE_ADDRESS_SUCCESS:
            return {deleting: false, addressDeleted:true }

        case DELETE_ADDRESS_FAIL:
            return {deleting: false, error:action.payload}

        default:
            return state
    
    } 
}