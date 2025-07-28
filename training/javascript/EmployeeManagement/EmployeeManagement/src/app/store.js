import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../features/feature/auth/authSlice';
import locationReducer from '../features/feature/location/locationSlice';
import profileReducer from '../features/feature/profile/profileSlice';

const store = configureStore({
  reducer: {
    auth: authReducer,
    location: locationReducer,
    profile: profileReducer,
  },
});

export default store;
