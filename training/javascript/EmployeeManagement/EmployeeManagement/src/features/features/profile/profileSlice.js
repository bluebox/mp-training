// src/redux/profileSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "../utils/axiosInstance";

// Thunks
export const fetchUserProfile = createAsyncThunk("profile/fetch", async (_, thunkAPI) => {
  try {
    const response = await axios.get("/employee/profile/");
    return response.data.data;
  } catch (error) {
    return thunkAPI.rejectWithValue(error.response.data);
  }
});

export const updateUserProfile = createAsyncThunk("profile/update", async (updateData, thunkAPI) => {
  try {
    const response = await axios.put("/employee/profile/", updateData);
    return response.data;
  } catch (error) {
    return thunkAPI.rejectWithValue(error.response.data);
  }
});

const profileSlice = createSlice({
  name: "profile",
  initialState: {
    loading: false,
    profile: null,
    error: null,
    updateMessage: null,
  },
  reducers: {
    clearProfileMessages: (state) => {
      state.error = null;
      state.updateMessage = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchUserProfile.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchUserProfile.fulfilled, (state, action) => {
        state.loading = false;
        state.profile = action.payload;
      })
      .addCase(fetchUserProfile.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload?.message || "Failed to load profile";
      })
      .addCase(updateUserProfile.fulfilled, (state, action) => {
        state.updateMessage = action.payload.message;
      })
      .addCase(updateUserProfile.rejected, (state, action) => {
        state.error = action.payload?.message || "Update failed";
      });
  },
});

export const { clearProfileMessages } = profileSlice.actions;
export default profileSlice.reducer;
