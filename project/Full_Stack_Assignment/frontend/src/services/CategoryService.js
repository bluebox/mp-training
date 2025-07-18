import { makeRequest } from "./AxiosTemplate";

export const getAllCategories = () => {
  return makeRequest("GET", "/categories/search", {}, {}, {});
};
