import { configureStore } from "@reduxjs/toolkit";
import AuthReducer from './slices/AuthSlice'

const store=configureStore({
    reducer:{
        auth:AuthReducer
    }
})

export default store