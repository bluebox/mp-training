import {CART_ITEM_REQUEST, CART_ITEM_SUCCESS, CART_ITEM_FAILURE} from "../constants/cartConstants";

export const cartReducer = (state = {cartItems:[], loading: true}, action) => {
    switch(action.type) {
        case CART_ITEM_REQUEST:
            return {
                ...state, 
                loading: true
            }

        case CART_ITEM_SUCCESS:
            return {
                loading: false,
                cart: action.payload
            }
        
        case CART_ITEM_FAILURE:
            return {
                loading: false,
                error: action.payload
            }
        
        default:
            return state
    }
    
}