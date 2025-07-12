import { makeRequest } from "./AxiosTemplate";

export const createMappingRequest = async ({
  categoryId,
  productId,
  requestedBy,
}) => {
  return makeRequest(
    "POST",
    "/api/category-product-mapping-requests/create",
    {},
    {},
    {
      categoryId: parseInt(categoryId),
      productId: parseInt(productId),
      requestedBy: parseInt(requestedBy),
    }
  );
};

export const getMappingRequests = async () => {
  return makeRequest(
    "GET",
    "api/category-product-mapping-requests/search",
    {},
    {},
    {}
  );
};
