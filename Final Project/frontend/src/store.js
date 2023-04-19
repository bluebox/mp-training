import {createStore, combineReducers, applyMiddleware} from 'redux'
import thunk from 'redux-thunk'
import {composeWithDevTools} from 'redux-devtools-extension'
import { productListReducer,
     productDetailReducer,
     productCreateReviewReducer 
    } from './reducers/productReducers'
import { cartReducer} from './reducers/cartReducers'
import { 
    orderDetailsReducer,
    orderListMyReducer,
    orderListReducer,

} from './reducers/orderReducer'
import { userLoginReducer, 
    userDetailReducer, 
    userListReducer, 
} from './reducers/userReducers'
const reducer = combineReducers({
    productList: productListReducer,
    productDetails: productDetailReducer,
    cart: cartReducer,
    userLogin: userLoginReducer,
    userDetails: userDetailReducer,
    userList: userListReducer,
    productCreateReview: productCreateReviewReducer,
    orderDetail: orderDetailsReducer,
    orderList: orderListReducer,
    orderListMy: orderListMyReducer,
})


const userInfoFromStorage = localStorage.getItem('userInfo') ?
    JSON.parse(localStorage.getItem('userInfo')) : null





const initialState = {
    userLogin: { userInfo: userInfoFromStorage },
}
const middleware = [thunk]
const store = createStore(reducer, initialState, composeWithDevTools(applyMiddleware(...middleware)))

export default store 