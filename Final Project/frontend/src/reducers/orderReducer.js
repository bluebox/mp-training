import {

    ORDER_DETAILS_REQUEST,
    ORDER_DETAILS_SUCCESS,
    ORDER_DETAILS_FAIL,

    ORDER_LIST_MY_REQUEST,
    ORDER_LIST_MY_SUCCESS,
    ORDER_LIST_MY_FAIL,
    ORDER_LIST_MY_RESET,

    ORDER_LIST_REQUEST,
    ORDER_LIST_SUCCESS,
    ORDER_LIST_FAIL,

}
from '../constants/orderConstants'

export const orderDetailsReducer = (state = {loading: true, orderItems: [], shippingAddress: {} }, action) => {
    switch (action.type) {
        case ORDER_DETAILS_REQUEST:
            return {
                ...state,
                loading: true
            }

        case ORDER_DETAILS_SUCCESS:
            return {
                loading: false,
                order: action.payload
            }

        case ORDER_DETAILS_FAIL:
            return {
                loading: false,
                error: action.payload
            }


        default:
            return state
    }
}

export const orderListMyReducer = (state = { orders: [] }, action) => {
    switch (action.type) {
        case ORDER_LIST_MY_REQUEST:
            return {
                loading: true
            }

        case ORDER_LIST_MY_SUCCESS:
            return {
                loading: false,
                orders: action.payload
            }

        case ORDER_LIST_MY_FAIL:
            return {
                loading: false,
                error: action.payload
            }

        case ORDER_LIST_MY_RESET:
            return {
                orders: []
            }

        default:
            return state
    }
}

export const orderListReducer = (state = { orders: [], loading: true }, action) => {
    switch (action.type) {
        case ORDER_LIST_REQUEST:
            return {
                loading: true
            }

        case ORDER_LIST_SUCCESS:
            return {
                loading: false,
                orders: action.payload
            }

        case ORDER_LIST_FAIL:
            return {
                loading: false,
                error: action.payload
            }
        default:
            return state
    }
}