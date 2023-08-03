import axios from 'axios'
import { WISHLIST_ADD_ITEM,
        WISHLIST_REMOVE_ITEM,
        WISHLIST_SAVE_BOOKING_ADDRESS,
        WISHLIST_SAVE_PAYMENT_METHOD 

    } from '../constants/wishlistConstants'

export const addToWishlist = (id, qty) => async (dispatch, getState) => {
    const {data} = await axios.get(`http://127.0.0.1:8000/api/product/${id}/`)
    dispatch({
        type: WISHLIST_ADD_ITEM,
        payload:{
            product:data._id,
            name: data.name,
            image:data.image,
            price:data.price,
            number_of_screens: data.number_of_screens,
            qty
        }
    })
    
    localStorage.setItem('wishlistItems', JSON.stringify(getState().wishlist.wishlistItems))
}

export const removeFromWishlist = (id) => (dispatch,getState) => {
    dispatch({
    type:WISHLIST_REMOVE_ITEM,
    payload: id,
})

localStorage.setItem('wishlistItems', JSON.stringify(getState().wishlist.wishlistItems))

}

export const saveBookingAddress = (data) => (dispatch) => {
    dispatch({
    type:WISHLIST_SAVE_BOOKING_ADDRESS,
    payload: data,
})

localStorage.setItem('bookingAddress', JSON.stringify(data))

}

export const savePaymentMethod = (data) => (dispatch) => {
    dispatch({
    type:WISHLIST_SAVE_PAYMENT_METHOD,
    payload: data,
})

localStorage.setItem('paymentMethod', JSON.stringify(data))

}