import { 
    JOB_CART_ADD_ITEM,

    JOB_CART_ADD_ITEM_REQUEST,
    JOB_CART_ADD_ITEM_SUCCESS,
    JOB_CART_ADD_ITEM_FAIL,
    JOB_CART_ADD_ITEM_RESET,

    GET_CART_ITEMS_REQUEST,
    GET_CART_ITEMS_SUCCESS,
    GET_CART_ITEMS_FAIL,
    GET_CART_ITEMS_RESET,

    DELETE_CART_ITEM_REQUEST,
    DELETE_CART_ITEM_SUCCESS,
    DELETE_CART_ITEM_FAIL,

 } from "../constants/jobCartConstants";

export const cartReducer = (state={cartItems:[]}, action) => {
    switch(action.type){
        case JOB_CART_ADD_ITEM:
            const item = action.payload
            const existItem = state.cartItems.find(x => x.job_id === item.job_id)

            if(existItem) {
                return {
                    ...state, 
                    cartItems: state.cartItems.map(x => 
                        x.job_id === existItem.job_id ? item : x)
                } 

            }else{
                return {
                    ...state, 
                    cartItems:[...state.cartItems, item]
                }
            }

        default:
            return state
    }
}


export const addCartItems = (state = {}, action) => {
    switch (action.type) {
        case JOB_CART_ADD_ITEM_REQUEST:
            return {loading: true}
        
        case JOB_CART_ADD_ITEM_SUCCESS:
            return {loading: false, addedItem:action.payload }

        case JOB_CART_ADD_ITEM_FAIL:
            return {loading: false, error:action.payload}

        case JOB_CART_ADD_ITEM_RESET:
            return {loading: false, addedItem: action.payload}

        default:
            return state
    
    } 
}

export const getCartItems = (state = {jobCartItems:[], loading:true}, action) => {
    switch (action.type) {
        case GET_CART_ITEMS_REQUEST:
            return {loading: true, load:true}
        
        case GET_CART_ITEMS_SUCCESS:
            return {loading: false, jobCartItems:action.payload, load:false }

        case GET_CART_ITEMS_FAIL:
            return {loading: false, error:action.payload, load:false}

        case GET_CART_ITEMS_RESET:
            return {loading: false, jobCartItems: action.payload, load:false}

        default:
            return state
    
    } 
}

export const deleteCartItem = (state = {}, action) => {
    switch (action.type) {
        case DELETE_CART_ITEM_REQUEST:
            return {deleting: true}
        
        case DELETE_CART_ITEM_SUCCESS:
            return {deleting: false, deleted: true}

        case DELETE_CART_ITEM_FAIL:
            return {deleting: false, error:action.payload}

        default:
            return state
    
    } 
}