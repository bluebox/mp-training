import { makeRequest } from "./AxiosTemplate";

const BASE_API = "/api/product-approval";

export const getApprovedProducts = () => {
  return makeRequest("GET", `${BASE_API}/products`);
};

export const getPendingRequests = () => {
  return makeRequest("GET", `${BASE_API}/pending-requests`, {}, {}, {});
};

export const approveRequests = (productRequestIds, managerId) => {
  // note: body is an array with one object inside per your original code
  return makeRequest("POST", `${BASE_API}/approve`, {}, {}, [
    { productRequestIds, managerId },
  ]);
};

export const rejectRequests = (productRequestIds, managerId) => {
  return makeRequest("POST", `${BASE_API}/reject`, {}, {}, [
    { productRequestIds, managerId },
  ]);
};
