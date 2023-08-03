import {combineReducers, applyMiddleware } from 'redux'
import { legacy_createStore as createStore} from 'redux'
import thunk from 'redux-thunk'
// import reducer from "./reducers/index"
// import { configureStore } from '@reduxjs/toolkit'
import { movieListReducer, 
    movieDetailsReducer, 
    movieDeleteReducer, 
    movieCreateReducer,
    movieUpdateReducer,
    movieReviewCreateReducer,
    } from './reducers/movieReducers'
import { composeWithDevTools } from 'redux-devtools-extension'
import { wishlistReducer } from './reducers/wishlistReducers'
import { userLoginReducer,
    userRegisterReducer,
    userDetailsReducer,
    userUpdateProfileReducer,
    userListReducer,
    userDeleteReducer,
    userUpdateReducer,
}   from './reducers/userReducers'

import { bookingCreateReducer, bookingDetailsReducer, bookingOrderPayReducer } from './reducers/bookingReducers'
   

const reducer = combineReducers({
    movieList: movieListReducer,
    movieDetails: movieDetailsReducer,
    movieDelete: movieDeleteReducer,
    movieCreate: movieCreateReducer,
    movieUpdate: movieUpdateReducer,
    movieReviewCreate: movieReviewCreateReducer,


    wishlist : wishlistReducer,
    userLogin: userLoginReducer,
    userRegister: userRegisterReducer,
    userDetails: userDetailsReducer,
    userUpdateProfile: userUpdateProfileReducer,
    userList: userListReducer,
    userDelete:userDeleteReducer,
    userUpdate:userUpdateReducer,
    bookingCreate:bookingCreateReducer,
    bookingDetails:bookingDetailsReducer,
    bookingOrderPay: bookingOrderPayReducer,

})

const wishlistItemsFromStorage = localStorage.getItem('wishlistItems') ?
        JSON.parse(localStorage.getItem('wishlistItems')) : []

const userInfoFromStorage = localStorage.getItem('userInfo') ?
        JSON.parse(localStorage.getItem('userInfo')) : null

const bookingAddressFromStorage = localStorage.getItem('bookingAddress') ?
JSON.parse(localStorage.getItem('bookingAddress')) : {}

const initialState = {
    wishlist: {wishlistItems: wishlistItemsFromStorage,
    bookingAddress: bookingAddressFromStorage,}, 
    userLogin: {userInfo: userInfoFromStorage}
}

const middleware = [thunk]

const store = createStore(reducer, initialState,
    composeWithDevTools(applyMiddleware(...middleware)))

export default store