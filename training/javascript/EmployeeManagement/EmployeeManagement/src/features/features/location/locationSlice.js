// src/redux/locationSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

// Example external API base URL
const BASE_URL = "https://countriesnow.space/api/v0.1";

// Async Thunks
export const fetchCountries = createAsyncThunk("location/fetchCountries", async () => {
  const res = await axios.get(`${BASE_URL}/countries/positions`);
  return res.data.data.map((c) => c.name);
});

export const fetchStates = createAsyncThunk("location/fetchStates", async (country) => {
  const res = await axios.post(`${BASE_URL}/countries/states`, { country });
  return res.data.data.states.map((s) => s.name);
});

export const fetchCities = createAsyncThunk("location/fetchCities", async ({ country, state }) => {
  const res = await axios.post(`${BASE_URL}/countries/state/cities`, { country, state });
  return res.data.data;
});

const locationSlice = createSlice({
  name: "location",
  initialState: {
    countries: [],
    states: [],
    cities: [],
    loading: false,
    error: null,
  },
  reducers: {
    resetStatesAndCities: (state) => {
      state.states = [];
      state.cities = [];
    },
    resetCities: (state) => {
      state.cities = [];
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchCountries.fulfilled, (state, action) => {
        state.countries = action.payload;
      })
      .addCase(fetchStates.fulfilled, (state, action) => {
        state.states = action.payload;
      })
      .addCase(fetchCities.fulfilled, (state, action) => {
        state.cities = action.payload;
      });
  },
});

export const { resetStatesAndCities, resetCities } = locationSlice.actions;
export default locationSlice.reducer;
