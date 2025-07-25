import { createSlice } from "@reduxjs/toolkit";

const configSlice = createSlice({
  name: "config",
  initialState: { maxRecords: 5, uniquePhone: false },
  reducers: {
    setConfig: (_, action) => action.payload,
  },
});

export const { setConfig } = configSlice.actions;
export default configSlice.reducer;
