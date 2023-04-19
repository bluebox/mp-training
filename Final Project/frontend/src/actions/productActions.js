import {
     PRODUCT_LIST_FAILURE,
     PRODUCT_LIST_REQUEST, 
     PRODUCT_LIST_SUCCESS,
     PRODUCT_DETAIL_FAILURE,
     PRODUCT_DETAIL_REQUEST, 
     PRODUCT_DETAIL_SUCCESS,
     PRODUCT_CREATE_REVIEW_FAILURE,
     PRODUCT_CREATE_REVIEW_REQUEST, 
     PRODUCT_CREATE_REVIEW_SUCCESS,
    } 
    from "../constants/productConstants";
import axios from 'axios';


export const listProducts = (keyword='', page='') => async (dispatch) =>{
    try{
        dispatch({type: PRODUCT_LIST_REQUEST})
        const {data} = await axios.get(`http://127.0.0.1:8000/api/products?${keyword}&${page}`)
        
        dispatch({type: PRODUCT_LIST_SUCCESS, payload: data})

    }catch(error){
        dispatch({
            type: PRODUCT_LIST_FAILURE,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail: error.message,
        })
    }
}

export const detailProducts = (id) => async(dispatch) =>{
    try{
        dispatch({type: PRODUCT_DETAIL_REQUEST})
        const {data} = await axios.get(`http://127.0.0.1:8000/api/product/${id}/`)
        dispatch({
            type: PRODUCT_DETAIL_SUCCESS,
            payload: data,
        })
    }catch(error){
        dispatch({
            type: PRODUCT_DETAIL_FAILURE,
            payload: error.response && error.response.data.detail
            ? error.response.data.detail: error.message
        })
    }
}


export const createProductReview = (product_id, review) => async(dispatch, getState) =>{
    try{
        dispatch({type: PRODUCT_CREATE_REVIEW_REQUEST})
        const {userLogin: {userInfo},} = getState()
        const config = {
            headers : {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}`
            }
        }
        const {data} = await axios.post(
            `http://127.0.0.1:8000/api/reviews/${product_id}/`,
            review,
            config
        )
        dispatch({
            type: PRODUCT_CREATE_REVIEW_SUCCESS,
            payload: data,
        })

    }catch(error){
        dispatch({
            type: PRODUCT_CREATE_REVIEW_FAILURE,
            payload: error.response && error.response.data && error.response.data.rating
            ? error.response.data.rating[0]
            :error.response.data.non_field_errors
            ?error.response.data.non_field_errors[0] : error.message
        })
    }
}