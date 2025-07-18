import axios from "axios";

const BASE_URL = "http://localhost:8082/api/cart";

export const getCartItems = (userId) => {
  return axios.get(`${BASE_URL}/${userId}/items`);
};

export const addItemToCart = (
  userId,
  productId,
  quantity,
  price,
  productName
) => {
  return axios.post(`${BASE_URL}/add`, {
    userId,
    productId,
    quantity,
    price,
    productName,
  });
};

export const removeCartItem = (cartItemId) => {
  return axios.delete(`${BASE_URL}/item/${cartItemId}`);
};

export const clearCart = (userId) => {
  return axios.delete(`${BASE_URL}/${userId}/clear`);
};

export const checkoutCart = (userId, { address }) => {
  return axios.post(`${BASE_URL}/${userId}/checkout`, { address });
};
