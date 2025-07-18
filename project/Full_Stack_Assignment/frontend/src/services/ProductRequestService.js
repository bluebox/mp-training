import { makeRequest } from "./AxiosTemplate";

const API_BASE = "/api/product-requests";

export const createProductRequest = (data) =>
  makeRequest("POST", `${API_BASE}/create`, {}, {}, data);

export const searchProductRequests = (criteria) =>
  makeRequest("POST", `${API_BASE}/search`, {}, {}, criteria);

export const updateProductRequest = (data) =>
  makeRequest("PUT", `${API_BASE}/update`, {}, {}, data);
