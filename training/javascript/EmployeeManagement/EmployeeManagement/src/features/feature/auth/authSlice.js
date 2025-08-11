import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import * as yup from "yup";

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
const PasswordFormat=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/
const LoginSchema=yup.object().shape(
  {
    username:yup.string().required("Required"),
    password:yup.string().min(8,"Minimum length must be 8").matches(PasswordFormat,"Please enter atleast 1 Upper Case,1 LowerCase,1 Special,1 Number").required("Required")

  }
);

export const { loginSuccess, logout } = authSlice.actions;
export default authSlice.reducer;
export {LoginSchema}
