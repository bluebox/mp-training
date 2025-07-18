import React, { useReducer } from "react";
import { OrderContext } from "./OrderContext";
const initialState = {
  orders: [],
  loading: false,
  error: null,
};

function orderReducer(state, action) {
  if (action.type === "FETCH_ORDERS_REQUEST") {
    return { ...state, loading: true, error: null };
  } else if (action.type === "FETCH_ORDERS_SUCCESS") {
    return { ...state, loading: false, orders: action.payload };
  } else if (action.type === "FETCH_ORDERS_FAILURE") {
    return { ...state, loading: false, error: action.payload };
  } else if (action.type === "UPDATE_ORDER") {
    const updatedOrders = state.orders.map((order) => {
      if (order.orderId === action.payload.orderId) {
        return action.payload;
      } else {
        return order;
      }
    });
    return { ...state, orders: updatedOrders };
  } else {
    return state;
  }
}

export function OrderProvider(props) {
  const [state, dispatch] = useReducer(orderReducer, initialState);

  return (
    <OrderContext.Provider value={{ state, dispatch }}>
      {props.children}
    </OrderContext.Provider>
  );
}
