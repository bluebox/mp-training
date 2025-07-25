import { createSlice } from "@reduxjs/toolkit";

const recordsSlice = createSlice({
  name: "records",
  initialState: [],
  reducers: {
    setRecords: (_, action) => action.payload,
    addRecord: (state, action) => {
      state.push(action.payload);
    },
    updateRecord: (state, action) => {
      const index = state.findIndex(r => r.id === action.payload.id);
      if (index !== -1) state[index] = action.payload;
    },
    deleteRecord: (state, action) => {
      return state.filter(r => r.id !== action.payload);
    },
  },
});

export const { setRecords, addRecord, updateRecord, deleteRecord } = recordsSlice.actions;
export default recordsSlice.reducer;



