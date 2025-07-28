import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

export const refreshToken = createAsyncThunk(
  "auth/refreshToken",
  async (_, { rejectWithValue }) => {
    try {
      const refresh = localStorage.getItem("refresh");
      const response = await axios.post("http://127.0.0.1:8000/api/token/refresh/", {
        refresh,
      });

      const access = response.data.access;
      localStorage.setItem("access", access);

      return {access};
    } catch (error) {
      return rejectWithValue(error.response?.data || "Token refresh failed");
    }
  }
);

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
  extraReducers: (builder) => {
    builder.addCase(refreshToken.fulfilled, (state, action) => {
      state.access = action.payload.access;
      state.isAuthenticated = true;
    });
    builder.addCase(refreshToken.rejected, (state) => {
      state.access = null;
      state.refresh = null;
      state.isAuthenticated = false;
      localStorage.clear();
    });
  },
});
export const selectUser = (state) => ({
  role: state.auth.role,
  username: state.auth.username,
});
export const { loginSuccess, logout } = authSlice.actions;
export default authSlice.reducer;

