import { makeRequest } from "./AxiosTemplate";


export const getCategoryRequests = () => {
  return makeRequest("GET", "/categories/request/search", {}, {}, {});
};


export const createCategoryRequest = (requestData) => {
  return makeRequest("POST", "/categories/request", {}, {}, requestData);
};




export const processCategoryRequest = (payload) => {
  return makeRequest("POST", "/categories/request/process", {}, {}, payload);
};


export const approveCategoryRequests = ({ requestIds, approvedBy, status }) => {
  return makeRequest("POST", "/categories/request/update", {}, {}, {
    requestIds,
    approvedBy,
    status,
  });



  

};
