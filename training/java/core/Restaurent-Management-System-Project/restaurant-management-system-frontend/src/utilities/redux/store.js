import { configureStore } from "@reduxjs/toolkit";
import constantSliceReducer from "./slices/constantSlice";
import employeeSliceReducer from "./slices/employeeSlice";
import orderSliceReducer from "./slices/orderSlice";
import itemSliceReducer from "./slices/itemSlice";
import loadingSliceReducer from "./slices/loadingSlice";

const store=configureStore({
  reducer: {
    "constants": constantSliceReducer,
    "employees": employeeSliceReducer,
    "orders": orderSliceReducer,
    "items": itemSliceReducer,
    "loading": loadingSliceReducer
  }
});

export default store;