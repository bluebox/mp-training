import { createSlice } from "@reduxjs/toolkit";

const orderSlice=createSlice({
    name: "orders",
    initialState: [],
    reducers: {
        setOrders: (state, action) => {
            return action.payload;
        },
        addOrder: (state, action) => {
            state.push(action.payload);
        },
        updateOrderStatus: (state, action) => {
            const { orderId, newStatus } = action.payload;
            const orderIndex = state.findIndex(order => order.orderId === orderId);
            if (orderIndex !== -1) {
                state[orderIndex].status = newStatus;
            }
        }
    },
});

export const { setOrders, addOrder, updateOrderStatus } = orderSlice.actions;
export default orderSlice.reducer;