import { configureStore } from "@reduxjs/toolkit";
import recordsReducer from "./recordsSlice";
import configReducer from "./configSlice";
import authReducer from "./authSlice";

const store = configureStore({
  reducer: {
    records: recordsReducer,
    config: configReducer,
    auth: authReducer,
  },
});

export default store;
