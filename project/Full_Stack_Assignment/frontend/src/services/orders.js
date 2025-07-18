import axios from "axios";

const BASE_URL = "http://localhost:8082/api/order-service";

export function fetchOrdersByCriteria(criteria) {
  return axios.post(`${BASE_URL}/search`, criteria, {
    headers: {
      "Content-Type": "application/json",
    },
  });
}

export function fetchOrderDetails(orderId, customerId) {
  return axios.get(`${BASE_URL}/order-details`, {
    params: { orderId, customerId },
  });
}

export function updateOrder(payload) {
  return axios.post(`${BASE_URL}/update-order`, payload, {
    headers: {
      "Content-Type": "application/json",
    },
  });
}
