import axios from 'axios'
import { 
    CART_SAVE_PAYMENT_METHOD, 
    CART_ITEM_FAILURE,
    CART_ITEM_REQUEST,
    CART_ITEM_SUCCESS
} from '../constants/cartConstants'

export const getCartItems = () => async(dispatch, getState) => {
    try{
        dispatch({
            type: CART_ITEM_REQUEST
        })
    
        const {userLogin: {userInfo},} = getState()
        const config = {
            headers : {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        const {data} = await axios.get('http://127.0.0.1:8000/api/cart/',config)
        dispatch({
            type: CART_ITEM_SUCCESS,
            payload: data
        })
        localStorage.setItem('cartItems', JSON.stringify(data.cartItems))
    }catch(error){
        dispatch({
            type: CART_ITEM_FAILURE,
            payload: error.response && error.response.data.detail?
            error.response.data.detail: error.message
        })
    }


}

export const savePaymentMethod = (data) => (dispatch) =>{
    dispatch({
        type: CART_SAVE_PAYMENT_METHOD,
        payload: data,
    })

    localStorage.setItem('paymentMethod', JSON.stringify(data))
}