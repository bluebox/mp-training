import { createSlice } from "@reduxjs/toolkit";
import { deleteItem, updateItem } from "../../../services/itemService";

const itemSlice=createSlice({
    name: "item",
    initialState: [],
    reducers: {
        setItems: (state, action) => {
            return action.payload;
        },
        addItems: (state, action) => {
            state.push(action.payload);
        },
        updateItems: (state, action) => {
            const index = state.findIndex(item => item.id === action.payload.id);
            if (index !== -1) {
                state[index] = action.payload.item;
            }
            return state;
        },
        deleteItems: (state, action) => {
            console.log(action.payload)
            return state.filter(item => item.id != action.payload)
        }
    }
})




export const { setItems, addItems, updateItems, deleteItems } = itemSlice.actions;
export default itemSlice.reducer;