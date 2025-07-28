// src/redux/authSlice.js
import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  access: localStorage.getItem("access") || null,
  refresh: localStorage.getItem("refresh") || null,
  role: localStorage.getItem("role") || null,
  username: localStorage.getItem("username") || null,
  isAuthenticated: !!localStorage.getItem("access"),
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    loginSuccess: (state, action) => {
      state.access = action.payload.access;
      state.refresh = action.payload.refresh;
      state.role = action.payload.role;
      state.username = action.payload.username;
      state.isAuthenticated = true;

      localStorage.setItem("access", action.payload.access);
      localStorage.setItem("refresh", action.payload.refresh);
      localStorage.setItem("role", action.payload.role);
      localStorage.setItem("username", action.payload.username);
    },
    logout: (state) => {
      state.access = null;
      state.refresh = null;
      state.role = null;
      state.username = null;
      state.isAuthenticated = false;

      localStorage.clear();
    },
  },
});

export const { loginSuccess, logout } = authSlice.actions;
export default authSlice.reducer;
