import { WISHLIST_ADD_ITEM ,
     WISHLIST_REMOVE_ITEM,
    WISHLIST_SAVE_BOOKING_ADDRESS,

    WISHLIST_SAVE_PAYMENT_METHOD,

    WISHLIST_CLEAR_ITEMS,
    } from '../constants/wishlistConstants'

// import { WISHLIST_CLEAR_ITEMS } from '../constants/wishlistConstants'

export const wishlistReducer = (state = { wishlistItems: [], bookingAddress:{}}, action) => {
    switch (action.type) {
        case WISHLIST_ADD_ITEM:
            const item = action.payload
            const existItem = state.wishlistItems.find(x=> x.movie === item.movie)

            if(existItem){
                return{
                    ...state,
                    wishlistItems: state.wishlistItems.map(x =>
                        x.movie === existItem.movie ? item : x)
                }
            } else {
                return {
                    ...state,
                    wishlistItems:[...state.wishlistItems, item]
                }
            }
        
            case WISHLIST_REMOVE_ITEM:
                return{
                    ...state,
                    wishlistItems:state.wishlistItems.filter(x => x.movie !== action.payload)
                }

            case WISHLIST_SAVE_BOOKING_ADDRESS:
                return{
                    ...state,
                    bookingAddress: action.payload
                }

            case WISHLIST_SAVE_PAYMENT_METHOD:
                return {
                    ...state,
                    paymentMethod: action.payload
                }

            case WISHLIST_CLEAR_ITEMS:
                return {
                    ...state,
                    wishlistItems: []
                }

        default:
            return state
    }
}