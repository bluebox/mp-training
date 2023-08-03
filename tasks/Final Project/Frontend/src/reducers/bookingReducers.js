import { BOOKING_CREATE_REQUEST,
        BOOKING_CREATE_SUCCESS,
        BOOKING_CREATE_FAIL,
    
        BOOKING_CREATE_RESET,

        BOOKING_DETAILS_REQUEST,
        BOOKING_DETAILS_SUCCESS,
        BOOKING_DETAILS_FAIL,

        BOOKING_PAY_REQUEST,
        BOOKING_PAY_SUCCESS,
        BOOKING_PAY_FAIL,
        BOOKING_PAY_RESET,
    } from '../constants/bookingConstants'


export const bookingCreateReducer = (state={}, action) =>{
    switch(action.type){
        case BOOKING_CREATE_REQUEST:
            return {
                loading:true
            }

        case BOOKING_CREATE_SUCCESS:
            return {
                loading: false,
                success: true,
                booking: action.payload
            }

        case BOOKING_CREATE_FAIL:
            return {
                loading:false,
                error: action.payload
            }

        case BOOKING_CREATE_RESET:
            return {}
        
        default:
            return state
    }
}

export const bookingDetailsReducer = (state={loading:true, bookingItems:[], bookingAddress:{}}, action) =>{
    switch(action.type){
        case BOOKING_DETAILS_REQUEST:
            return {
                ...state,
                loading:true
            }

        case BOOKING_DETAILS_SUCCESS:
            return {
                loading: false,
                booking: action.payload
            }

        case BOOKING_DETAILS_FAIL:
            return {
                loading:false,
                error: action.payload
            }
        
        default:
            return state
    }
}

export const bookingOrderPayReducer = (state={}, action) =>{
    switch(action.type){
        case BOOKING_PAY_REQUEST:
            return {
                loading:true
            }

        case BOOKING_PAY_SUCCESS:
            return {
                loading: false,
                success: true,
            }

        case BOOKING_PAY_FAIL:
            return {
                loading:false,
                error: action.payload
            }
        
        case BOOKING_PAY_RESET:
            return {}
        
        
        default:
            return state
    }
}