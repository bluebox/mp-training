import {
    ADD_ADDRESS_REQUEST,
    ADD_ADDRESS_SUCCESS,
    ADD_ADDRESS_FAIL,
    ADD_ADDRESS_RESET,

    GET_ADDRESS_REQUEST,
    GET_ADDRESS_SUCCESS,
    GET_ADDRESS_FAIL,
    
    EDIT_ADDRESS_REQUEST,
    EDIT_ADDRESS_SUCCESS,
    EDIT_ADDRESS_FAIL,
    EDIT_ADDRESS_RESET,

    DELETE_ADDRESS_REQUEST,
    DELETE_ADDRESS_SUCCESS,
    DELETE_ADDRESS_FAIL,

} from '../constants/addressConstants';
import axios from 'axios';

export const addNewAddress = (values) => async (dispatch, getState) => {
    try{
        dispatch({
            type: ADD_ADDRESS_REQUEST
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

        const {data} = await axios.post(
            'http://127.0.0.1:8000/api/users/addAddress/',
            values,
            config
        )
        
        dispatch({
            type: ADD_ADDRESS_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: ADD_ADDRESS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const resetAddedAddress = () => async (dispatch) => {

    dispatch({
        type: ADD_ADDRESS_RESET,
        payload: false
    })

}


export const getAddressDetails = () => async (dispatch, getState) => {
    try{
        dispatch({
            type: GET_ADDRESS_REQUEST
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
            `http://127.0.0.1:8000/api/users/getAddress/`,
            config
        )

        console.log(data)
        
        dispatch({
            type: GET_ADDRESS_SUCCESS,
            payload: data
        })



    }catch (error) {
        dispatch({
            type: GET_ADDRESS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 


export const editAddress = (addressId, values) => async (dispatch, getState) => {
    try{
        dispatch({
            type: EDIT_ADDRESS_REQUEST
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

        const {data} = await axios.put(
            `http://127.0.0.1:8000/api/users/editAddress/${addressId}`,
            values,
            config
        )
        
        dispatch({
            type: EDIT_ADDRESS_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: EDIT_ADDRESS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 

export const deleteUserAddress = (address_id) => async (dispatch, getState) => {
    try{
        dispatch({
            type: DELETE_ADDRESS_REQUEST
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
            `http://127.0.0.1:8000/api/users/deleteAddress/${address_id}`,
            config
        )
        
        dispatch({
            type: DELETE_ADDRESS_SUCCESS,
            payload: data
        })

        

    }catch (error) {
        dispatch({
            type: DELETE_ADDRESS_FAIL,
            payload: error.response && error.response.data.detail
                ? error.response.data.detail
                : error.message,
        })
    }


} 

