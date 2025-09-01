import { createSlice } from "@reduxjs/toolkit";

const constantSlice=createSlice({
    name: "constants",
    initialState: {
        itemCategories: [],
    },
    reducers: {
        setItemCategories(state, action) {
            state.itemCategories = action.payload;
        },
    },
})

export const { setItemCategories } = constantSlice.actions;
export default constantSlice.reducer;