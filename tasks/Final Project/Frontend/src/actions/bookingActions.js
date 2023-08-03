import axios from 'axios'

import { BOOKING_CREATE_REQUEST,
    BOOKING_CREATE_SUCCESS,
    BOOKING_CREATE_FAIL,

    BOOKING_DETAILS_REQUEST,
    BOOKING_DETAILS_SUCCESS,
    BOOKING_DETAILS_FAIL,

    BOOKING_PAY_REQUEST,
    BOOKING_PAY_SUCCESS,
    BOOKING_PAY_FAIL,
    BOOKING_PAY_RESET,
} from '../constants/bookingConstants'

import { WISHLIST_CLEAR_ITEMS } from '../constants/wishlistConstants'

export const getBookingDetails = (id) => async(dispatch, getState) => {
    try{
        dispatch({
            type:BOOKING_DETAILS_REQUEST
        })
    
            const {
                userLogin: {userInfo},
             } = getState()
            
            const config = {
                headers:{
                    'Content-type': 'application/json',
                    Authorization: `Bearer ${userInfo.token}`
                }
            }
    
            const {data} = await axios.get(
                `http://127.0.0.1:8000/booking/api/${id}/`,
                config
            )
    
            dispatch({
                type:BOOKING_DETAILS_SUCCESS,
                payload:data,
            })
    
        }catch(error){
            dispatch({
                type:BOOKING_DETAILS_FAIL,
                payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
            })
        }
    }

    export const createBooking = (booking) => async(dispatch, getState) => {
        console.log(booking)
        try{
            dispatch({
                type:BOOKING_CREATE_REQUEST
            })
        
                const {
                    userLogin: {userInfo},
                 } = getState()
                
                const config = {
                    headers:{
                        'Content-type': 'application/json',
                        Authorization: `Bearer ${userInfo.token}`
                    }
                }
        
                const {data} = await axios.post(
                    `http://127.0.0.1:8000/booking/api/add/`,
                    booking,
                    config
                )
        
                dispatch({
                    type:BOOKING_CREATE_SUCCESS,
                    payload:data,
                })
    
                dispatch({
                    type:WISHLIST_CLEAR_ITEMS,
                    payload:data,
                })
                console.log(data)
                localStorage.removeItem('wishlistItems')
        
            }catch(error){
                dispatch({
                    type:BOOKING_CREATE_FAIL,
                    payload: error.response && error.response.data.detail
                    ? error.response.data.detail
                    : error.message,
                })
            }
        }

export const payBookingOrder = (id, paymentResult) => async(dispatch, getState) => {
            try{
                dispatch({
                    type:BOOKING_PAY_REQUEST
                })
            
                    const {
                        userLogin: {userInfo},
                     } = getState()
                    
                    const config = {
                        headers:{
                            'Content-type': 'application/json',
                            Authorization: `Bearer ${userInfo.token}`
                        }
                    }
            
                    const {data} = await axios.put(
                        `http://127.0.0.1:8000/booking/api/${id}/pay/`,
                        paymentResult,
                        config
                    )
            
                    dispatch({
                        type:BOOKING_PAY_SUCCESS,
                        payload:data,
                    })
            
                }catch(error){
                    dispatch({
                        type:BOOKING_PAY_FAIL,
                        payload: error.response && error.response.data.detail
                        ? error.response.data.detail
                        : error.message,
                    })
                }
            }
