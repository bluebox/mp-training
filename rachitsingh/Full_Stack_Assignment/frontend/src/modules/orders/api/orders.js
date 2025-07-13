import axios from "axios";

const BASE_URL = "http://localhost:8080/api/order-service";

export const fetchOrdersByCriteria = (criteria) =>
  axios.post(`${BASE_URL}/search`, criteria).then((res) => res.data);

export const fetchOrderDetails = (orderId, customerId) =>
  axios
    .get(`${BASE_URL}/order-details`, {
      params: { orderId, customerId },
    })
    .then((res) => res.data);

export const updateOrder = async (payload) => {
  const response = await fetch(
    "http://localhost:8080/api/order-service/update-order",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    }
  );

  if (!response.ok) {
    throw new Error("Failed to update order");
  }
};
