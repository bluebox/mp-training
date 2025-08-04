import { createSlice } from '@reduxjs/toolkit';

const AuthSlice=createSlice({
   name:'auth',
   initialState:{
      isAuthenticated:true,
      user:null
   },
   reducers:{
       setIsAuthenticated:(state,action)=>{
          state.isAuthenticated=action.payload
       },
       setuser:(state,action)=>{
         state.user=action.payload
       }
   }
})

export const {setIsAuthenticated,setuser} =AuthSlice.actions
export default AuthSlice.reducer