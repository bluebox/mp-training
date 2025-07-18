import { useContext, useEffect } from "react";
import { OrderContext } from "../context/OrderContext";
import {
  fetchOrdersByCriteria,
  fetchOrderDetails,
  updateOrder,
} from "../api/orders";

export function useOrders(criteria) {
  const { state, dispatch } = useContext(OrderContext);

  useEffect(() => {
    dispatch({ type: "FETCH_ORDERS_REQUEST" });

    fetchOrdersByCriteria(criteria)
      .then((data) => {
        dispatch({ type: "FETCH_ORDERS_SUCCESS", payload: data });
      })
      .catch((error) => {
        dispatch({ type: "FETCH_ORDERS_FAILURE", payload: error.message });
      });
  }, [criteria, dispatch]);

  function getOrderDetails(orderId, customerId) {
    return fetchOrderDetails(orderId, customerId);
  }

  function handleUpdateOrder(updatePayload) {
    return updateOrder(updatePayload).then(() => {
      return fetchOrdersByCriteria({
        customerId: updatePayload.customerId,
      }).then((data) => {
        dispatch({ type: "FETCH_ORDERS_SUCCESS", payload: data });
      });
    });
  }

  return {
    orders: state.orders,
    loading: state.loading,
    error: state.error,
    getOrderDetails,
    handleUpdateOrder,
  };
}
