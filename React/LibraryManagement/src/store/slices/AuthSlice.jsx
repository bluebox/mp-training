import { createSlice } from '@reduxjs/toolkit';

const AuthSlice=createSlice({
   name:'auth',
   initialState:{
      isAuthenticated:true
   },
   reducers:{
       setIsAuthenticated:(state,action)=>{
          state.isAuthenticated=action.payload
       }
   }
})

export const {setIsAuthenticated} =AuthSlice.actions
export default AuthSlice.reducer