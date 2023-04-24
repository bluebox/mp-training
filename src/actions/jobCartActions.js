import axios from 'axios';
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

 } from '../constants/jobCartConstants';

export const addToMyJobCart = (id) => async(dispatch, getState) => {
    const {data} = await axios.get(`http://127.0.0.1:8000/api/posts/${id}`)
    console.log(data)
    const image = data.company.image
    console.log(image)
    dispatch({
        type: JOB_CART_ADD_ITEM,
        payload:{
            job_id:data.id,
            role:data.role, 
            image:image,    
            job_type:data.job_type,
            location:data.location,
        }
    })

    localStorage.setItem('jobCartItems', JSON.stringify(getState().jobCart.cartItems))
}  


export const addJobPostToCart = (id, resume) => async (dispatch, getState) => {
    try{
        dispatch({
            type: JOB_CART_ADD_ITEM_REQUEST
        })

        const {
            userLogin: {userInfo},
        } = getState()

        const config = {
            headers:{
                'Content-Type': 'multipart/form-data',
                Authorization: `Bearer ${userInfo.token}` 
            }
        }

        const {data} = await axios.post(
            'http://127.0.0.1:8000/api/users/addToCart/',
            {'postId': id, 'resume': resume },
            config
        )
        
        dispatch({
            type: JOB_CART_ADD_ITEM_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: JOB_CART_ADD_ITEM_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const resetAddedPost = () => async (dispatch) => {

    dispatch({
        type: JOB_CART_ADD_ITEM_RESET,
        payload: false
    })

}


export const getJobPostsOfAUser = () => async (dispatch, getState) => {
    try{
        dispatch({
            type: GET_CART_ITEMS_REQUEST
        })

        const {
            userLogin: {userInfo},
        } = getState()

        const config = {
            headers:{
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}` 
            }
        }

        const {data} = await axios.get(
            'http://127.0.0.1:8000/api/users/getForCart/',
            config
        )
        
        dispatch({
            type: GET_CART_ITEMS_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: GET_CART_ITEMS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const deleteAppliedJobPost = (job_post_id) => async (dispatch, getState) => {
    try{
        dispatch({
            type: DELETE_CART_ITEM_REQUEST
        })

        const {
            userLogin: {userInfo},
        } = getState()

        const config = {
            headers:{
                'Content-Type': 'application/json',
                Authorization: `Bearer ${userInfo.token}` 
            }
        }

        const {data} = await axios.delete(
            `http://127.0.0.1:8000/api/users/deleteFromCart/${job_post_id}`,
            config
        )
        
        dispatch({
            type: DELETE_CART_ITEM_SUCCESS,
            payload: data
        })
    }catch (error) {
        dispatch({
            type: DELETE_CART_ITEM_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }

}